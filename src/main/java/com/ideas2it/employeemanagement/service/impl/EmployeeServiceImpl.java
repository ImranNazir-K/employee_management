
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLoggerFactory;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.repository.EmployeeRepository;
import com.ideas2it.employeemanagement.service.EmployeeService;

/**
 * Validates all input given by users. Stores the employees in the 
 *      Database.
 * Does operations like Create, update, Display, Delete employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {
    
    private EmployeeRepository employeeRepository;
    private ProjectServiceImpl projectService;
    private PasswordEncoder passwordEncoder;
    
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
            @Lazy ProjectServiceImpl projectService,
            @Lazy PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.projectService = projectService;
        this.passwordEncoder = passwordEncoder;
    }
    
    Logger log = EMSLoggerFactory.getFactory(EmployeeService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<EmployeeDTO> createEmployee(EmployeeDTO employeeDto)
            throws EMSException {
        log.info("Request got for creating Employee");
        Employee employee = Mapper.toEmployee(employeeDto);

        if (null != employeeRepository.findByEmployeeMailId
                (employee.getEmployeeMailId())) {
            log.info(Constants.EXISTING_MAIL);
            throw new EMSException(Constants.EXISTING_MAIL,
                    HttpStatus.BAD_REQUEST);
        }
        
        if (null != employeeRepository.findByEmployeeContactNumber
                (employee.getEmployeeContactNumber())) {
            log.info(Constants.EXISTING_CONTACT_NUMBER);
            throw new EMSException(Constants.EXISTING_CONTACT_NUMBER,
                    HttpStatus.BAD_REQUEST);
        }
        
        employee.setPassword(passwordEncoder.encode(employee
        		.getEmployeeContactNumber()));
		log.info(Constants.EMPLOYEE_CREATED);
        return new ResponseEntity<EmployeeDTO>(Mapper.employeeProjectToDto
                (employeeRepository.save(employee)), HttpStatus.CREATED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<EmployeeDTO>> getEmployees()
            throws EMSException {
        List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();
        List<Employee> employees = employeeRepository.findAll();

        log.info("Request got to Display Employees");
        
        if (!(employees.isEmpty())) {
            
            for (Employee employee : employees) {
                employeeDtos.add(Mapper.employeeProjectToDto(employee));
            }
        } else {
            log.info(Constants.NO_EMPLOYEES);
            throw new EMSException(Constants.NO_EMPLOYEES,
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<EmployeeDTO>>(employeeDtos,
                HttpStatus.OK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<EmployeeDTO> getEmployee(int employeeId) 
            throws EMSException {
        log.info("Request got for displaying Particular Employee");
        
        if (!(employeeRepository.findAll().isEmpty())) {
            
            if (employeeRepository.existsById(employeeId)) {
                return new ResponseEntity<EmployeeDTO>(Mapper.employeeProjectToDto
                        (employeeRepository.findById(employeeId).get()),
                        HttpStatus.OK);
            } else {
                log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
                throw new EMSException(Constants.EMPLOYEE_ID_NOT_FOUND,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            log.info(Constants.NO_EMPLOYEES);
            throw new EMSException(Constants.NO_EMPLOYEES,
                    HttpStatus.NO_CONTENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<String> deleteEmployee(int employeeId)
            throws EMSException {
        log.info("Request got to delete particular Employee");
        
        if (!(employeeRepository.findAll().isEmpty())) {
            
            if (employeeRepository.existsById(employeeId)) {
                employeeRepository.deleteById(employeeId);
                log.info(Constants.EMPLOYEE_DELETED);
                throw new EMSException(Constants.EMPLOYEE_DELETED,
                        HttpStatus.OK);
            } else {
                log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
                throw new EMSException(Constants.EMPLOYEE_ID_NOT_FOUND,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            log.info(Constants.NO_EMPLOYEES);
            throw new EMSException(Constants.NO_EMPLOYEES,
                    HttpStatus.NO_CONTENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<EmployeeDTO> update(int employeeId,
            EmployeeDTO employeeDto) throws EMSException {
        Employee employee = Mapper.toEmployee(employeeDto);
        
        log.info("Request got for updating Employee");
        
        if (!(employeeRepository.findAll().isEmpty())) {

            if (employeeRepository.existsById(employeeId)) {
                return updateEmployee(employeeId, employee);
            } else {
                log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
                throw new EMSException(Constants.EMPLOYEE_ID_NOT_FOUND,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            log.info(Constants.NO_EMPLOYEES);
            throw new EMSException(Constants.NO_EMPLOYEES,
                    HttpStatus.NO_CONTENT);
        }
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public ResponseEntity<EmployeeDTO> updateEmployee(int employeeId,
            Employee employee) throws EMSException {

        if (isMailIdExists(employee.getEmployeeMailId(), employeeId)) {
            throw new EMSException(Constants.EXISTING_MAIL,
                    HttpStatus.BAD_REQUEST);
        }

        if (isContactNumberExists(employee.getEmployeeContactNumber(),
                employeeId)) {
            throw new EMSException(Constants.EXISTING_CONTACT_NUMBER,
                    HttpStatus.BAD_REQUEST);
        }
        employee.setProject(employeeRepository.findById(employeeId)
                .get().getProject());
        employee.setEmployeeId(employeeId);
        log.info(Constants.EMPLOYEE_UPDATED);
        return new ResponseEntity<EmployeeDTO>(Mapper.employeeProjectToDto
                (employeeRepository.save(employee)), HttpStatus.OK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMailIdExists(String employeeMailId, int employeeId) {
        boolean isExists = false;
        Employee employee = employeeRepository.findByEmployeeMailId
                (employeeMailId);
        
        if (null == employee) {
            isExists = true;
        } else if (employee.getEmployeeId() == employeeId){
            isExists = true;
        }
        return !isExists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isContactNumberExists(String contactNumber,
    		int employeeId) {
        boolean isExists = false;
        Employee employee = employeeRepository
                .findByEmployeeContactNumber(contactNumber);
        
        if (null == employee) {
            isExists = true;
        } else if (employee.getEmployeeId() == employeeId) {
            isExists = true;
        }
        return !isExists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<EmployeeDTO> assignProject(int employeeId,
            int projectId) throws EMSException {
        log.info("Request got for Assigning Project");
        
        if (!(employeeRepository.findAll().isEmpty())) {

            if (projectService.isProjectsAvailable()) {
                return assign(employeeId, projectId);
            } else {
                log.info(Constants.NO_PROJECTS);
                throw new EMSException(Constants.NO_PROJECTS,
                        HttpStatus.NO_CONTENT);
            }
        } else {
            log.info(Constants.NO_EMPLOYEES);
            throw new EMSException(Constants.NO_EMPLOYEES,
                    HttpStatus.NO_CONTENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<EmployeeDTO> assign(int employeeId, int projectId)
            throws EMSException {
        if (employeeRepository.existsById(employeeId)) {
            ProjectDTO project = projectService
                    .getParticularProject(projectId);
            
            if (null != project) {
                return assignProjectToEmployee(employeeId, project);
            } else {
                log.warn(Constants.PROJECT_ID_NOT_FOUND);
                throw new EMSException(Constants.PROJECT_ID_NOT_FOUND,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
            throw new EMSException(Constants.EMPLOYEE_ID_NOT_FOUND,
                    HttpStatus.NOT_FOUND);
        }   
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<EmployeeDTO> assignProjectToEmployee(int employeeId,
            ProjectDTO project)
            throws EMSException {
        Set<ProjectDTO> projects = new HashSet<ProjectDTO>();
        
        if (isProjectAlreadyAssigned(employeeId, project.getProjectId())) {
            log.warn(Constants.PROJECT_ALREADY_ASSIGNED);
            throw new EMSException(Constants.PROJECT_ALREADY_ASSIGNED,
                    HttpStatus.BAD_REQUEST);
        } else {
            EmployeeDTO employee = Mapper.employeeProjectToDto
                    (employeeRepository.findById(employeeId).get());
            projects.add(project);
            employee.setProjects(projects);
            log.info(Constants.PROJECT_ASSIGNED);
            return new ResponseEntity<EmployeeDTO>(Mapper.employeeProjectToDto
                    (employeeRepository.save(Mapper.dtoToEmployeeProject
                    (employee))), HttpStatus.OK);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProjectAlreadyAssigned(int employeeId, int projectId)
            throws EMSException {
        boolean isAlreadyAssigned = false;
        EmployeeDTO employee = Mapper.employeeProjectToDto
                (employeeRepository.findById(employeeId).get());

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
    public ResponseEntity<EmployeeDTO> unAssignProject(int employeeId,
            int projectId) throws EMSException {
        log.info("Request got for Un-Assigning Project");
        
        if (!(employeeRepository.findAll().isEmpty())) {

            if (projectService.isProjectsAvailable()) {
                return unAssign(employeeId, projectId);
            } else {
                log.info(Constants.NO_PROJECTS);
                throw new EMSException(Constants.NO_PROJECTS,
                        HttpStatus.NO_CONTENT);
            }
        } else {
            log.info(Constants.NO_EMPLOYEES);
            throw new EMSException(Constants.NO_EMPLOYEES, 
                    HttpStatus.NO_CONTENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<EmployeeDTO> unAssign(int employeeId, int projectId)
            throws EMSException {
        if (employeeRepository.existsById(employeeId)) {
            ProjectDTO project = projectService
                    .getParticularProject(projectId);
            if (null != project) {
               return  unAssignProjectFromEmployee(employeeId, project);
            } else {
                log.warn(Constants.PROJECT_ID_NOT_FOUND);
                throw new EMSException(Constants.PROJECT_ID_NOT_FOUND,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
            throw new EMSException(Constants.EMPLOYEE_ID_NOT_FOUND, 
                    HttpStatus.NOT_FOUND);
        }        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<EmployeeDTO> unAssignProjectFromEmployee
            (int employeeId, ProjectDTO projectDto) throws EMSException {
        Set<ProjectDTO> projects = new HashSet<ProjectDTO>();
        
        if (isProjectAlreadyAssigned(employeeId, projectDto.getProjectId())) {
            EmployeeDTO employee = Mapper.employeeProjectToDto
                    (employeeRepository.findById(employeeId).get());
            projects = employee.getProjects();

            for (ProjectDTO project : projects) {

                if (projectDto .getProjectId() == project.getProjectId()) {
                    projects.remove(project);
                    break;
                }
            }
            employee.setProjects(projects);
            log.info(Constants.PROJECT_UNASSIGNED);
            return new ResponseEntity<EmployeeDTO>(Mapper.employeeProjectToDto
                    (employeeRepository.save(Mapper
                    .dtoToEmployeeProject(employee))), HttpStatus.OK);
        } else {
            log.warn(Constants.PROJECT_NOT_ASSIGNED);
            throw new EMSException
                (Constants.PROJECT_NOT_ASSIGNED, HttpStatus.BAD_REQUEST);
        }        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO getParticularEmployee(int employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            return Mapper.employeeProjectToDto(employeeRepository
                    .findById(employeeId).get());
        } else {    
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmployeesAvailable() throws EMSException {
        return !employeeRepository.findAll().isEmpty();
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmployeeMailId(username);
		if (null == employee) {
			throw new UsernameNotFoundException("Username Not Valid");
		} else {
			return new User(employee.getEmployeeMailId(),
					employee.getPassword(), new ArrayList<>());
		}
	}
}