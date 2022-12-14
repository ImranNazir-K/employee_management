
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServlet;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.LoggerFactory;
import com.ideas2it.employeemanagement.service.EmployeeService;

/**
 * performs operations like create, Display, Update, for employees
 *
 * @author IMRAN NAZIR K
 *
 * @version 5.0
 */
@Controller
public class EmployeeController extends HttpServlet {

	@Autowired
	private EmployeeService employeeService;
	private  Logger logger = LoggerFactory.getFactory();

	public EmployeeController() {
	}

	/**
	 * Gets the form for creating employee.
	 * 
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/getEmployeeForm")
	private String getEmployeeForm(Model model) {
		EmployeeDTO employee = new EmployeeDTO();

		logger.info("Request got for creating Employee");
		model.addAttribute("employee", employee);
		model.addAttribute("submit", "create");
		model.addAttribute("title", "CREATE EMPLOYEE");
		model.addAttribute("formAction", "createEmployee");
		return "employeeForm.jsp";
	}

	/**
	 * Creates employee.
	 * 
	 * @param employee as EmployeeDTO instance.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@PostMapping("/createEmployee")
	private String createEmployee(@RequestBody @ModelAttribute("employee")
			EmployeeDTO employee, Model model,
			RedirectAttributes redirectAttribute) {
		int employeeId = 0;
		String response = "redirect:/error.jsp";
		
		try {
			logger.info("creating Employee");
    		
    		if (isMailIdExists(employee.getEmployeeMailId())) {
    			model.addAttribute("message", Constants.EXISTING_MAIL);
    			model.addAttribute("submit", "create");
    			model.addAttribute("link", "createEmployee.jsp");
    			model.addAttribute("formAction", "createEmployee");
    			return  "employeeForm.jsp";
    		}
			
    		if (isContactNumberExists(employee.getEmployeeContactNumber())) {
    			model.addAttribute("message",
    					Constants.EXISTING_CONTACT_NUMBER);
    			model.addAttribute("link", "createEmployee.jsp"); 
    			model.addAttribute("submit", "create");
    			model.addAttribute("formAction", "createEmployee");
            	return  "employeeForm.jsp";
    		}
			employeeId = employeeService.createEmployee(employee);
			logger.info(Constants.EMPLOYEE_CREATED + employeeId);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
			redirectAttribute.addAttribute("message",
					Constants.EMPLOYEE_CREATED + employeeId);
			response = "redirect:/message.jsp";
		} catch (EMSException exception) {
			logger.error(Constants.CREATE_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
			redirectAttribute.addAttribute("error",
					Constants.CREATE_EMPLOYEE_EXCEPTION);
		}
		return response;
	}

    /**
     * checks whether the contact number of an employee is present in the
     * database.
     * 
	 * @param contactNumber contact number of an employee as long.
	 * 
	 * @return response page as String.
	 * 
	 * @throws EMSException if error occurred in database.
	 */
    private boolean isContactNumberExists(long contactNumber)
			throws EMSException {
    	boolean isExists = false;
    	
    	if (employeeService.isContactNumberExists(contactNumber)) {
    		logger.warn(Constants.EXISTING_CONTACT_NUMBER);
    		isExists = true;
    	}
        return isExists;
    }

    /**
     * checks whether Mail Id of an employee is present in the database.
     * 
     * @param mailId mailId of an employee as String.
     * 
     * @return response page as String.
     * 
     * @throws EMSException if error occurred in database.
     */
    private boolean isMailIdExists(String mailId) throws EMSException {
    	boolean isExists = false;
    		
    	if (employeeService.isMailIdExists(mailId)) {
    		isExists = true;
    	}
        return isExists;
    }

	/**
	 * checks whether the database is empty for Delete operation.
     * 
     * @param redirectAttribute the RedirectAttribute instance for
     *        adding messages.
     * 
     * @return response page as String.
     */
	@GetMapping("/displayEmployee")
	private String displayEmployee(RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
		logger.info("Request got to Display Employee");
		try {

			if (employeeService.isDbIsEmpty()) {
				logger.info(Constants.NO_EMPLOYEES);
				redirectAttribute.addAttribute("link",
						"employeeManagement.jsp");
				redirectAttribute.addAttribute("message",
						Constants.NO_EMPLOYEES);
				response = "redirect:/message.jsp";
			} else {
				response = "displayEmployees.jsp";
			}
		} catch (EMSException exception) {
			logger.error(Constants.DISPLAY_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DISPLAY_EMPLOYEE_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * Displays all the employees.
	 * 
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/displayAllEmployees")
	private String displayAllEmployees(Model model,
			RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();

		logger.info("Request got for displaying all Employees");
		try {
			logger.info("Displaying Employees");
			employees = employeeService.getAllEmployees();
			model.addAttribute("employees", employees);
			response = "displayAllEmployees.jsp";
		} catch (EMSException exception) {
			logger.warn(Constants.DISPLAY_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DISPLAY_EMPLOYEE_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * Displays the particular Employee.
	 * 
	 * @param employeeId id of an employee as int.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/displayParticularEmployee")
	private String displayParticularEmployee(@RequestParam("employeeId") 
			int employeeId, Model model, RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();

		logger.info("Request got for displaying Particular Employee");
		try {

			if (employeeService.isIdExists(employeeId)) {
				EmployeeDTO employee = employeeService
						.getParticularEmployee(employeeId);
				employees.add(employee);
				model.addAttribute("employees", employees);
				logger.info("Displaying Particular Employee");
				response = "displayAllEmployees.jsp";
			} else {
				logger.info(Constants.ID_NOT_FOUND);
				model.addAttribute("message", Constants.ID_NOT_FOUND);
				response = "displayParticularEmployee.jsp";
			}
		} catch (EMSException exception) {
			logger.warn(Constants.DISPLAY_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DISPLAY_EMPLOYEE_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * Displays the projects assigned to an employee.
	 * 
	 * @param employeeId id of an employee as int.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/displayEmployeeProject")
	private String displayEmployeeProject(@RequestParam("employeeId")
			int employeeId, Model model, RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		logger.info("Request got for Displaying the projects of an Employee");

		try {
			EmployeeDTO employee = employeeService
					.getParticularEmployee(employeeId);
			logger.info("Displaying the projects of an  Employee");
			model.addAttribute("projects", employee.getProjects());
			response = "displayEmployeeProject.jsp";
		} catch (EMSException exception) {
			logger.warn(Constants.DISPLAY_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DISPLAY_EMPLOYEE_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * checks whether the database is empty for update operation.
	 * 
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/updateEmployee")
	private String updateEmployee(Model model,
			RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
		logger.info("Request got to Update Employee");
		try {

			if (employeeService.isDbIsEmpty()) {
				logger.info(Constants.NO_EMPLOYEES);
				redirectAttribute.addAttribute("link",
						"employeeManagement.jsp");
				redirectAttribute.addAttribute("message",
						Constants.NO_EMPLOYEES);
				response = "redirect:/message.jsp";
			} else {
				response = "updateMenu.jsp";
			}
		} catch (EMSException exception) {
			logger.warn(Constants.UPDATE_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.UPDATE_EMPLOYEE_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * Gets the particular employee to update.
	 * 
	 * @param employeeId id of an employee as int.
	 * @param model The Model instance contains the request made by 
	 * the client.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/getEmployeeToUpdate")
	private String getEmployeeToUpdate(@RequestParam("employeeId")
	        int employeeId, Model model, RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		EmployeeDTO employee = new EmployeeDTO();

		logger.info("Request got for Updating the Details of an Employee");
		try {

			if (employeeService.isIdExists(employeeId)) {
				employee = employeeService.getParticularEmployee(employeeId);
				model.addAttribute("employee", employee);
				model.addAttribute("formAction", "updateEmployeeDetails");
				model.addAttribute("submit", "update");
				model.addAttribute("title", "UPDATE EMPLOYEE");
				response = "employeeForm.jsp";
			} else {
				model.addAttribute("message", Constants.ID_NOT_FOUND);
				logger.warn(Constants.ID_NOT_FOUND);
				response = "updateEmployee.jsp";
			}
		} catch (EMSException exception) {
			logger.warn(Constants.UPDATE_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.UPDATE_EMPLOYEE_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * updates the employee.
	 * 
	 * @param employee as EmployeeDTO instance.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@PostMapping("/updateEmployeeDetails")
	private String updateEmployeeDetails(@RequestBody @ModelAttribute("employee")
			EmployeeDTO employee,Model model,
			RedirectAttributes redirectAttribute) {
		int employeeId = employee.getEmployeeId();
		String response = "redirect:/error.jsp";
		
		logger.info("Updating the Details of an Employee");
        if (employeeService.isMailIdExists(employee.getEmployeeMailId(),
        		employeeId)) {
			model.addAttribute("message", Constants.EXISTING_MAIL);
			model.addAttribute("submit", "update");
			model.addAttribute("link", "updateEmployee.jsp");
			model.addAttribute("formAction", "updateEmployeeDetails");
			return  "employeeForm.jsp";
        }
        
        if (employeeService.isContactNumberExists(employee
        		.getEmployeeContactNumber(), employeeId)) {
			model.addAttribute("message", Constants.EXISTING_CONTACT_NUMBER);
			model.addAttribute("submit", "update");
			model.addAttribute("link", "updateEmployee.jsp");
			model.addAttribute("formAction", "updateEmployeeDetails");
			return  "employeeForm.jsp";
        }

		try {
			EmployeeDTO employeeDto = employeeService.getParticularEmployee(employee.getEmployeeId());
			employee.setProjects(employeeDto.getProjects());
			employeeService.updateEmployee(employee);
			logger.info(Constants.EMPLOYEE_UPDATED);
			redirectAttribute.addAttribute("message",
					Constants.EMPLOYEE_UPDATED);
			redirectAttribute.addAttribute("link", "updateMenu.jsp");
			response = "redirect:/message.jsp";
		} catch (EMSException exception) {
			logger.warn(Constants.UPDATE_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.UPDATE_EMPLOYEE_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * Forwards to assign project page.
	 * 
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/getAssignProjectForm")
	private String getAssignProjectForm(Model model,
			RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
		logger.info("Request got for Assigning Project");
		try {

			if (employeeService.isProjectDbIsEmpty()) {
				logger.info(Constants.NO_PROJECTS);
				redirectAttribute.addAttribute("link", "updateMenu.jsp");
				redirectAttribute.addAttribute("message",
						Constants.NO_PROJECTS);
				response = "redirect:/message.jsp";
			} else {
				model.addAttribute("formAction", "assignProject");
				model.addAttribute("submit", "assign");
				response = "assignOrUnAssign.jsp";
			}
		} catch (EMSException exception) {
			logger.warn(Constants.UPDATE_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.UPDATE_EMPLOYEE_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * Assigns project to an employee.
	 * 
	 * @param employeeId id of an employee as int.
	 * @param projectId  id of an project as int.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 * 
	 * @throws EMSException if error occurred in database.
	 */
	@PostMapping("/assignProject")
	private String assignProject(@RequestBody @RequestParam("employeeId")
			int employeeId, @RequestParam("projectId") int projectId,
			Model model, RedirectAttributes redirectAttribute)
			throws EMSException {
		String response = new String();
		Set<ProjectDTO> projects = new HashSet<ProjectDTO>();

		if (employeeService.isIdExists(employeeId)) {

			if (employeeService.isProjectIdExists(projectId)) {
				logger.info("Assigning a project to an Employee");
				
				if (employeeService.isAlreadyAssigned(employeeId, projectId)) {
					logger.info(Constants.PROJECT_ALREADY_ASSIGNED);
					model.addAttribute("message",
							Constants.PROJECT_ALREADY_ASSIGNED);
					model.addAttribute("formAction", "assignProject");
					model.addAttribute("submit", "assign");
					response = "assignOrUnAssign.jsp";
				} else {
					EmployeeDTO employee = employeeService
							.getParticularEmployee(employeeId);
					ProjectDTO project = employeeService
							.getParticularProject(projectId);
					projects = employee.getProjects();
					projects.add(project);
					employee.setProjects(projects);
					employeeService.updateEmployee(employee);
					logger.info(Constants.PROJECT_ASSIGNED);
					redirectAttribute.addAttribute("message",
							Constants.PROJECT_ASSIGNED);
					redirectAttribute.addAttribute("link", "updateMenu.jsp");
					response = "redirect:/message.jsp";
				}
			} else {
				logger.warn(Constants.PROJECT_ID_NOT_FOUND);
				model.addAttribute("message", Constants.PROJECT_ID_NOT_FOUND);
				model.addAttribute("formAction", "assignProject");
				model.addAttribute("submit", "assign");
				response = "assignOrUnAssign.jsp";
			}
		} else {
			logger.warn(Constants.ID_NOT_FOUND);
			model.addAttribute("message", Constants.ID_NOT_FOUND);
			model.addAttribute("formAction", "assignProject");
			model.addAttribute("submit", "assign");
			response = "assignOrUnAssign.jsp";
		}
		return response;
	}

	/**
	 * Forwards to un-assign project page.
	 * 
	 * @param modelThe Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/getUnAssignProjectForm")
	private String getUnAssignProjectForm(Model model,
			RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
		logger.info("Request got for Un-Assigning Project");
		try {

			if (employeeService.isProjectDbIsEmpty()) {
				logger.info(Constants.NO_PROJECTS);
				redirectAttribute.addAttribute("link", "updateMenu.jsp");
				redirectAttribute.addAttribute("message",
						Constants.NO_PROJECTS);
				response = "redirect:/message.jsp";
			} else {
				model.addAttribute("formAction", "unAssignProject");
				model.addAttribute("submit", "un-Assign");
				response = "assignOrUnAssign.jsp";
			}
		} catch (EMSException exception) {
			logger.warn(Constants.UPDATE_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			model.addAttribute("error", Constants.UPDATE_EMPLOYEE_EXCEPTION);
			model.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * Un-assigns a project of an employee.
	 * 
	 * @param employeeId id of an employee as int.
	 * @param projectId  id of an project as int.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 * 
	 * @throws EMSException if error occurred in database.
	 */
	@PostMapping("/unAssignProject")
	private String unAssignProject(@RequestBody @RequestParam("employeeId")
			int employeeId, @RequestParam("projectId") int projectId,
			Model model, RedirectAttributes redirectAttribute)
		    throws EMSException {
		String response = new String();
		Set<ProjectDTO> projects = new HashSet<ProjectDTO>();
		
    	logger.info("Request got for un-assigning Project");
		if (employeeService.isIdExists(employeeId)) {

			if (employeeService.isProjectIdExists(projectId)) {
				logger.info("Un-Assigning a project from an Employee");
				
				if (employeeService.isAlreadyAssigned(employeeId, projectId)) {
					EmployeeDTO employee = employeeService
							.getParticularEmployee(employeeId);
					projects = employee.getProjects();

					for (ProjectDTO project : projects) {

						if (projectId == project.getProjectId()) {
							projects.remove(project);
							break;
						}
					}
					employee.setProjects(projects);
					employeeService.updateEmployee(employee);
					logger.info(Constants.PROJECT_UNASSIGNED);
					redirectAttribute.addAttribute("link", "updateMenu.jsp");
					redirectAttribute.addAttribute("message",
							Constants.PROJECT_UNASSIGNED);
					response = "redirect:/message.jsp";
				} else {
					logger.info(Constants.PROJECT_NOT_ASSIGNED);
					model.addAttribute("message",
							Constants.PROJECT_NOT_ASSIGNED);
					model.addAttribute("formAction", "unAssignProject");
					model.addAttribute("submit", "Un-Assign");
					response = "assignOrUnAssign.jsp";
				}
			} else {
				logger.warn(Constants.PROJECT_ID_NOT_FOUND);
				model.addAttribute("message", Constants.PROJECT_ID_NOT_FOUND);
				model.addAttribute("formAction", "unAssignProject");
				model.addAttribute("submit", "Un-Assign");
				response = "assignOrUnAssign.jsp";
			}
		} else {
			logger.warn(Constants.ID_NOT_FOUND);
			model.addAttribute("message", Constants.ID_NOT_FOUND);
			model.addAttribute("formAction", "unAssignProject");
			model.addAttribute("submit", "Un-Assign");
			response = "assignOrUnAssign.jsp";
		}
		return response;
	}

	/**
	 * checks whether the database is empty for Delete operation.
	 * 
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/deleteEmployee")
	private String deleteEmployee(Model model,
			RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
		logger.info("Request got to Delete Employee");
		try {

			if (employeeService.isDbIsEmpty()) {
				logger.info(Constants.NO_EMPLOYEES);
				redirectAttribute.addAttribute("link", 
						"employeeManagement.jsp");
				redirectAttribute.addAttribute("message",
						Constants.NO_EMPLOYEES);
				response = "redirect:/message.jsp";
			} else {
				response = "deleteEmployees.jsp";
			}
		} catch (EMSException exception) {
			logger.error(Constants.DELETE_EMPLOYEES_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DELETE_EMPLOYEES_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * Deletes all the Employees.
	 * 
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/deleteAllEmployees")
	private String deleteAllEmployees(Model model,
			RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
		logger.info("Deleting all Employees");
		try {
			employeeService.deleteAllEmployees();
			logger.info("Deleting all Projects");
			logger.info(Constants.EMPLOYEES_DELETED);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
			redirectAttribute.addAttribute("message",
					Constants.EMPLOYEES_DELETED);
			response = "redirect:/message.jsp";
		} catch (EMSException exception) {
			logger.error(Constants.DELETE_EMPLOYEES_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DELETE_EMPLOYEES_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}

	/**
	 * Deletes particular Employee.
	 * 
	 * @param employeeId id of an employee as int.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/deleteParticularEmployee")
	private String deleteParticularEmployee(@RequestParam("employeeId")
			int employeeId, Model model, RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
		logger.info("Request got to delete particular Employee");
		try {

			if (employeeService.isIdExists(employeeId)) {
				employeeService.deleteParticularEmployee(employeeId);
				logger.info("Deleting an Employee");
				logger.info(Constants.EMPLOYEE_DELETED);
				redirectAttribute.addAttribute("link", "deleteEmployees.jsp");
				redirectAttribute.addAttribute("message",
						Constants.EMPLOYEE_DELETED);
				response = "redirect:/message.jsp";
			} else {
				logger.warn(Constants.ID_NOT_FOUND);
				model.addAttribute("message", Constants.ID_NOT_FOUND);
				return "deleteParticularEmployee.jsp";
			}
		} catch (EMSException exception) {
			logger.error(Constants.DELETE_EMPLOYEE_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DELETE_EMPLOYEE_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeeManagement.jsp");
		}
		return response;
	}
}