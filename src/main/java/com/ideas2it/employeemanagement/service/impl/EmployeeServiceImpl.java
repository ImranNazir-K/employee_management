
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dto.AthenticationRequest;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EmployeeManagementSystemException;
import com.ideas2it.employeemanagement.exceptions.UserNotFoundException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementSystemLoggerFactory;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.repository.EmployeeRepository;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.util.EmployeeManagementUtil;
import com.ideas2it.employeemanagement.util.JwtUtil;

/**
 * Class to perform CRUD operations for employee.
 *  
 * Does operations like Create, update, Display, Delete employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService,
		UserDetailsService {
	
    private static final Logger log = EmployeeManagementSystemLoggerFactory
    		.getFactory(EmployeeService.class);
    
	private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final JwtUtil jwtUtil;
    private final ProjectServiceImpl projectService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeManagementUtil employeeManagementUtil;
    
    public EmployeeServiceImpl(@Lazy AuthenticationManager
    		authenticationManager, EmployeeRepository employeeRepository,
    		JwtUtil jwtUtil,
            @Lazy ProjectServiceImpl projectService,
            @Lazy PasswordEncoder passwordEncoder,
            EmployeeManagementUtil employeeManagementUtil) {
        this.jwtUtil = jwtUtil;
    	this.authenticationManager = authenticationManager;
        this.employeeRepository = employeeRepository;
        this.projectService = projectService;
        this.passwordEncoder = passwordEncoder;
        this.employeeManagementUtil = employeeManagementUtil;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDto)
            throws EmployeeManagementSystemException {
        Employee employee = Mapper.toEmployee(employeeDto);

        if (null != employeeRepository.findByEmployeeMailId
                (employee.getEmployeeMailId())) {
            log.info(Constants.EXISTING_MAIL);
            throw new EmployeeManagementSystemException(Constants
            		.EXISTING_MAIL);
        }
        
        if (null != employeeRepository.getEmployeeByContactNumber
                (employee.getEmployeeContactNumber())) {
            log.info(Constants.EXISTING_CONTACT_NUMBER);
            throw new EmployeeManagementSystemException(Constants
            		.EXISTING_CONTACT_NUMBER);
        }
        
        employee.setPassword(passwordEncoder.encode(employee
        		.getEmployeeContactNumber()));
		log.info(Constants.EMPLOYEE_CREATED);
        return Mapper.employeeProjectToDto(employeeRepository.save(employee));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeDTO> getEmployees()
            throws EmployeeManagementSystemException {
        List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();
        List<Employee> employees = employeeRepository.findAll();
        
        if (!(employees.isEmpty())) {
            
            for (Employee employee : employees) {
                employeeDtos.add(Mapper.employeeProjectToDto(employee));
            }
        }
        return employeeDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO getEmployeeById(int employeeId) 
            throws EmployeeManagementSystemException {
        EmployeeDTO employee = Mapper.employeeProjectToDto
        		(employeeRepository.findById(employeeId).get());

        if (null != employee) {
        	return employee;
        } else {
        	log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
        	throw new EmployeeManagementSystemException(Constants
        			.EMPLOYEE_ID_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteEmployee(int employeeId)
            throws EmployeeManagementSystemException {
        if (employeeRepository.existsById(employeeId)) {
        	employeeRepository.deleteById(employeeId);
        	log.info(Constants.EMPLOYEE_DELETED);
        	return Constants.EMPLOYEE_DELETED;
        } else {
        	log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
        	throw new EmployeeManagementSystemException(Constants
        			.EMPLOYEE_ID_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDto)
    		throws EmployeeManagementSystemException {
    	int employeeId = employeeDto.getEmployeeId();
        Employee employee = Mapper.toEmployee(employeeDto);

        if (employeeManagementUtil.isMailIdExists(employee.getEmployeeMailId(),
        		employeeId)) { 
        	throw new EmployeeManagementSystemException(Constants
        			.EXISTING_MAIL);
        }

        if (employeeManagementUtil.isContactNumberExists(employee
        		.getEmployeeContactNumber(), employeeId)) {
            throw new EmployeeManagementSystemException(Constants
            		.EXISTING_CONTACT_NUMBER);
        }
        Employee employees = employeeRepository.findById(employeeId).get();
        employee.setProject(employees.getProject());
        employee.setPassword(employees.getPassword());
        log.info(Constants.EMPLOYEE_UPDATED);
        return Mapper.employeeProjectToDto(employeeRepository.save(employee));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO assignProject(int employeeId,
            int projectId) throws EmployeeManagementSystemException {
        EmployeeDTO employee = Mapper.employeeProjectToDto
                (employeeRepository.findById(employeeId).get());

        if (null != employee) {
            ProjectDTO project = projectService
                    .getProjectById(projectId);
            
            if (null != project) {
                return assignProjectToEmployee(employee, project);
            } else {
                log.warn(Constants.PROJECT_ID_NOT_FOUND);
                throw new EmployeeManagementSystemException(Constants
                		.PROJECT_ID_NOT_FOUND);
            }
        } else {
            log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
            throw new EmployeeManagementSystemException(Constants
            		.EMPLOYEE_ID_NOT_FOUND);
        }   
    }

    private EmployeeDTO assignProjectToEmployee(EmployeeDTO employee,
            ProjectDTO project)
            throws EmployeeManagementSystemException {
        Set<ProjectDTO> projects = new HashSet<ProjectDTO>();
        
        if (isProjectAlreadyAssigned(employee, project.getProjectId())) {
            log.warn(Constants.PROJECT_ALREADY_ASSIGNED);
            throw new EmployeeManagementSystemException(Constants
            		.PROJECT_ALREADY_ASSIGNED);
        } else {
            projects.add(project);
            employee.setProjects(projects);
            log.info(Constants.PROJECT_ASSIGNED);
            return Mapper.employeeProjectToDto(employeeRepository.save(Mapper
            		.dtoToEmployeeProject(employee)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProjectAlreadyAssigned(EmployeeDTO employee,
    		int projectId) throws EmployeeManagementSystemException {
        boolean isAlreadyAssigned = false;
        
        for (ProjectDTO project : employee.getProjects()) {

            if (projectId == project.getProjectId()) {
                isAlreadyAssigned = true;
                break;
            }
        }
        return isAlreadyAssigned;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO unAssignProject(int employeeId,
            int projectId) throws EmployeeManagementSystemException {
        EmployeeDTO employee = Mapper.employeeProjectToDto
                (employeeRepository.findById(employeeId).get());
  
        if (null != employee) {
            ProjectDTO project = projectService
                    .getProjectById(projectId);
            if (null != project) {
               return  unAssignProjectFromEmployee(employee, project);
            } else {
                log.warn(Constants.PROJECT_ID_NOT_FOUND);
                throw new EmployeeManagementSystemException(Constants
                		.PROJECT_ID_NOT_FOUND);
            }
        } else {
            log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
            throw new EmployeeManagementSystemException(Constants
            		.EMPLOYEE_ID_NOT_FOUND);
        }
    }
    
    private EmployeeDTO unAssignProjectFromEmployee(EmployeeDTO employee,
    		ProjectDTO projectDto) throws EmployeeManagementSystemException {
        Set<ProjectDTO> projects = new HashSet<ProjectDTO>();
        
        if (isProjectAlreadyAssigned(employee, projectDto.getProjectId())) {
            projects = employee.getProjects();

            for (ProjectDTO project : projects) {

                if (projectDto .getProjectId() == project.getProjectId()) {
                    projects.remove(project);
                    break;
                }
            }
            employee.setProjects(projects);
            log.info(Constants.PROJECT_UNASSIGNED);
            return Mapper.employeeProjectToDto(employeeRepository.save(Mapper
                    .dtoToEmployeeProject(employee)));
        } else {
            log.warn(Constants.PROJECT_NOT_ASSIGNED);
            throw new EmployeeManagementSystemException(Constants
            		.PROJECT_NOT_ASSIGNED);
        }        
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UserNotFoundException {
		Employee employee = employeeRepository.findByEmployeeMailId(username);
		
		if (null == employee) {
			throw new UserNotFoundException("Username Not Valid");
		} else {
			return new User(employee.getEmployeeMailId(),
					employee.getPassword(), new ArrayList<>());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String generateToken(AthenticationRequest authRequest) {
    	final String token;
    	
    	authenticationManager
    		.authenticate(new UsernamePasswordAuthenticationToken(authRequest
    		.getUsername(), authRequest.getPassword()));
    	Employee user = employeeRepository.findByEmployeeMailId(authRequest
    			.getUsername());
    	
    	if (null != user) {
    		token = jwtUtil.generateToken(authRequest.getUsername());
    	} else {
    		throw new EmployeeManagementSystemException
    		(Constants.INVALID_AUTHENTICATION);
    	}
		return token;
	}
}