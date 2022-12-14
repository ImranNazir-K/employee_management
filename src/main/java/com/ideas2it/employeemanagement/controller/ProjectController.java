
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
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * performs operations like create, Display, Update, Delete for projects.
 *
 * @author IMRAN NAZIR K
 *
 * @version 5.0
 */
@Controller
public class ProjectController extends HttpServlet {

	@Autowired
	private ProjectService projectService;
	private Logger logger = LoggerFactory.getFactory();

	public ProjectController() {
	}

    /**
     * Gets project form for creating Project.
     * 
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/getProjectForm")
	private String getProjectForm(Model model) {
		ProjectDTO project = new ProjectDTO();

		logger.info("Request got for creating Project");
		model.addAttribute("project", project);
		model.addAttribute("submit", "create");
		model.addAttribute("title", "CREATE PROJECT");
		model.addAttribute("formAction", "createProject");
		return "projectForm.jsp";
	}

	/**
	 * Creates project.
	 *  
	 * @param project as ProjectDTO instance.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttributes the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@PostMapping("/createProject")
	private String createProject(@RequestBody 
			@ModelAttribute("project") ProjectDTO project, Model model,
			RedirectAttributes redirectAttribute) {
		int projectId = 0;
		String response = "redirect:/error.jsp";

		try {
			logger.info("creating a project");
			projectId = projectService.createProject(project);
			logger.info(Constants.PROJECT_CREATED + projectId);
			redirectAttribute.addAttribute("link", "projectManagement.jsp");
			redirectAttribute.addAttribute("message", Constants.PROJECT_CREATED
					+ projectId);
			response = "redirect:/message.jsp";
		} catch (EMSException exception) {
			logger.error(Constants.CREATE_PROJECT_EXCEPTION);
			redirectAttribute.addAttribute("link", "employeemanagement.jsp");
			redirectAttribute.addAttribute("error", exception.getMessage());
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
	 * @return response page as String.s
	 */
	@GetMapping("/displayProject")
	private String displayProject(Model model,
			RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
		logger.info("Request got to Displaying Projects");
		try {
			
			if (projectService.isDbIsEmpty()) {
				logger.info(Constants.NO_PROJECTS);
				redirectAttribute.addAttribute("link", "projectManagement.jsp");
				redirectAttribute.addAttribute("message",
						Constants.NO_PROJECTS);
				response = "redirect:/message.jsp";
			} else {
				response = "displayProjects.jsp";
			}
		} catch (EMSException exception) {
			logger.error(Constants.DISPLAY_PROJECT_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DISPLAY_PROJECT_EXCEPTION);
			redirectAttribute.addAttribute("link", "projectManagement.jsp");
		}
		return response;
	}

	/**
	 * Displays all the projects.
	 * 
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/displayAllProjects")
	private String displayAllProjects(Model model,
			RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		List<ProjectDTO> projects = new ArrayList<ProjectDTO>();

		logger.info("Request got for displaying All projects");
		try {
			logger.info("Displaying Particular Project");
			projects = projectService.getAllProjects();
			logger.info(projects);
			model.addAttribute("projects", projects);
			response = "displayAllProjects.jsp";
		} catch (EMSException exception) {
			logger.warn(Constants.DISPLAY_PROJECT_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DISPLAY_PROJECT_EXCEPTION);
			redirectAttribute.addAttribute("link", "projectManagement.jsp");
		}
		return response;
	}

	/**
	 * Displays the particular project.
	 * 
	 * @param projectId id of an project as int.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/displayParticularProject")
	private String displayParticularProject(@RequestParam("projectId")
			int projectId, Model model, RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		List<ProjectDTO> projects = new ArrayList<ProjectDTO>();
		
		logger.info("Request got for displaying Particular Employee");
		try {
			
			if (projectService.isIdExists(projectId)) {
				ProjectDTO project = projectService
						.getParticularProject(projectId);
				projects.add(project);
				logger.info("Displaying Particular Project");
				model.addAttribute("projects", projects);
				response = "displayAllProjects.jsp";
			} else {
				logger.info( Constants.PROJECT_ID_NOT_FOUND);
				model.addAttribute("message", Constants.PROJECT_ID_NOT_FOUND);
				response = "displayParticularProject.jsp";
			}
		} catch (EMSException exception) {
			logger.warn(Constants.DISPLAY_PROJECT_EXCEPTION);
			logger.error(exception.getMessage());
			model.addAttribute("error", Constants.DISPLAY_PROJECT_EXCEPTION);
			model.addAttribute("link", "projectManagement.jsp");
		}
		return response;
	}

	/**
	 * Displays the employees assigned to an project.
	 * 
	 * @param projectId id of an project as int.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/displayProjectEmployee")
	private String displayProjectEmployee(@RequestParam("projectId")
			int projectId, Model model, RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
		logger.info("Request got to display Employee of a project");
		try {
			ProjectDTO project = projectService.getParticularProject(projectId);
			logger.info("Displaying th Employees of a Project");
			model.addAttribute("employees", project.getEmployees());
			response = "displayProjectEmployee.jsp";
		} catch (EMSException exception) {
			logger.warn(Constants.DISPLAY_PROJECT_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DISPLAY_PROJECT_EXCEPTION);
			redirectAttribute.addAttribute("link", "projectManagement.jsp");
		}
		return response;
	}

    /**
     * checks whether the database is empty to update project.
     * 
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages. 
	 * @return response page as String.
	 */
	@GetMapping("/updateProject")
    private String updateProject(Model model,
    		RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
        logger.info("Request got to Update Project");
        try {
        	
			if (projectService.isDbIsEmpty()) {
			    logger.info(Constants.NO_PROJECTS);
			    redirectAttribute.addAttribute("link", "projectManagement.jsp");
			    redirectAttribute.addAttribute("message",
			    		Constants.NO_PROJECTS);
			    response = "redirect:/message.jsp";
			} else {
			    response = "updateProjectMenu.jsp";
			}
		} catch (EMSException exception) {
			logger.warn(Constants.UPDATE_PROJECT_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.UPDATE_PROJECT_EXCEPTION);
			redirectAttribute.addAttribute("link", "projectManagement.jsp");
		}
        return response;
    }

    /**
     * Gets the particular project to update.
     * 
	 * @param projectId id of an project as int.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 *  
	 * @return response page as String.
	 */
	@GetMapping("/getProjectToUpdate")
    private String getProjectToUpdate(@RequestParam("projectId") int projectId,
    		Model model, RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
        ProjectDTO project = new ProjectDTO();
        
        logger.info("Request got for Updating all Details of an Project");
        try {
        	
			if (projectService.isIdExists(projectId)) {
			    project = projectService.getParticularProject(projectId);
			    model.addAttribute("project", project);
			    model.addAttribute("formAction", "updateProjectDetails");
			    model.addAttribute("submit", "update");
				model.addAttribute("title", "UPDATE PROJECT");
			    response = "projectForm.jsp";
			} else {
				model.addAttribute("message", Constants.PROJECT_ID_NOT_FOUND);
				logger.warn(Constants.PROJECT_ID_NOT_FOUND);
			    response = "updateProject.jsp";
			}
		} catch (EMSException exception) {
			logger.warn(Constants.UPDATE_PROJECT_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.UPDATE_PROJECT_EXCEPTION);
			redirectAttribute.addAttribute("link", "projectManagement.jsp");
		}
        return response;
    }

    /**
     * updates the project.
     * 
	 * @param project as ProjectDTO instance.
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 *  
	 * @return response page as String.
	 */
	@PostMapping("/updateProjectDetails")
    private String updateProjectDetails(@RequestBody @ModelAttribute("project")
    		ProjectDTO project, Model model,
    		RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
        logger.info("updating all details of an Project");
        try {
			ProjectDTO projectDto = projectService.getParticularProject(project.getProjectId());
			project.setEmployees(projectDto.getEmployees());
        	projectService.updateProject(project);
        	logger.info(Constants.PROJECT_UPDATED);
        	redirectAttribute.addAttribute("message",
        			Constants.PROJECT_UPDATED);
        	redirectAttribute.addAttribute("link", "updateProjectMenu.jsp");
        	response = "redirect:/message.jsp";
        } catch (EMSException exception) {
        	logger.warn(Constants.UPDATE_PROJECT_EXCEPTION);
        	logger.error(exception.getMessage());
        	redirectAttribute.addAttribute("error",
        			Constants.UPDATE_PROJECT_EXCEPTION);
        	redirectAttribute.addAttribute("link", "projectManagement.jsp");
        }
        return response;
    }

    /**
     * Gets the request to assign Employee.
     * 
	 * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 * 
	 * @return response page as String.
	 */
	@GetMapping("/getAssignEmployeeForm")
    private String getAssignEmployeeForm(Model model,
    		RedirectAttributes redirectAttribute) {
		String response = "redirect:/error.jsp";
		
        logger.info("Request got for Assingning Employee");

        try {
        	
			if (projectService.isEmployeeDbIsEmpty()) {
			    logger.info(Constants.NO_EMPLOYEES);
			    redirectAttribute.addAttribute("link", "updateProjectMenu.jsp");
			    redirectAttribute.addAttribute("message",
			    		Constants.NO_EMPLOYEES);
			    response = "redirect:/message.jsp";
			} else {
				model.addAttribute("formAction", "assignEmployee");
				model.addAttribute("submit", "assign");
			    response = "assignOrUnAssign.jsp";
			}
		} catch (EMSException exception) {
			logger.warn(Constants.UPDATE_PROJECT_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.UPDATE_PROJECT_EXCEPTION);
			redirectAttribute.addAttribute("link", "projectManagement.jsp");
		}
        return response;
    }

    /**
     * Assigns Employee to an project.
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
	@PostMapping("/assignEmployee")
    private String assignEmployee(@RequestBody @RequestParam("employeeId")
    		int employeeId, @RequestParam("projectId") int projectId,
    		Model model, RedirectAttributes redirectAttribute) 
    				throws EMSException {
		String response = "redirect:/error.jsp";
		Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();
		 
        if (projectService.isIdExists(projectId)) {
			
            if (projectService.isEmployeeIdExists(employeeId)) {
                logger.info("Assigning an employee to a project");
                
                if (projectService.isAlreadyAssigned(projectId, employeeId)) {
                    logger.info(Constants.EMPLOYEE_ALREADY_ASSIGNED);
                    model.addAttribute("message",
                    		Constants.EMPLOYEE_ALREADY_ASSIGNED);
					model.addAttribute("formAction", "assignEmployee");
					model.addAttribute("submit", "assign");
					response = "assignOrUnAssign.jsp";
                } else {
                    EmployeeDTO employeeDto = projectService
                            .getParticularEmployee(employeeId);
                    ProjectDTO projectDto = projectService
                            .getParticularProject(projectId);
                    employees = projectDto.getEmployees();
                    employees.add(employeeDto);
                    projectDto.setEmployees(employees);
                    projectService.updateProject(projectDto);
                    logger.info(Constants.EMPLOYEE_ASSIGNED);
                    redirectAttribute.addAttribute("message",
                    		Constants.EMPLOYEE_ASSIGNED);
                    redirectAttribute.addAttribute("link", 
                    		"updateProjectMenu.jsp");
                    response = "redirect:/message.jsp";
                }
            } else {
        		logger.warn(Constants.ID_NOT_FOUND);
        		model.addAttribute("message", Constants.ID_NOT_FOUND);
        		model.addAttribute("formAction", "assignEmployee");
        		model.addAttribute("submit", "assign");
        		response = "assignOrUnAssign.jsp";
            }
        } else {
            logger.warn(Constants.PROJECT_ID_NOT_FOUND);
            model.addAttribute("message", Constants.PROJECT_ID_NOT_FOUND);
    		model.addAttribute("formAction", "assignEmployee");
    		model.addAttribute("submit", "assign");
    		response = "assignOrUnAssign.jsp";
        }
        return response;
    }

    /**
     * Gets the request to assign Employee.
     * 
     * @param model The Model instance contains the request made by 
	 *        the client.
	 * @param redirectAttribute the RedirectAttribute instance for
	 *        adding messages.
	 *  
     * @return response page as String.
     */
    @GetMapping("/getUnAssignEmployeeForm")
    private String getUnAssignEmployeeForm(Model model,
    		RedirectAttributes redirectAttribute) {
    	String response = "redirect:/error.jsp";
    	
        logger.info("Request got for Un-Assigning Employee");
        try {
        	
			if (projectService.isEmployeeDbIsEmpty()) {
			    logger.info(Constants.NO_EMPLOYEES);
			    redirectAttribute.addAttribute("link", 
			    		"updateProjectMenu.jsp");
			    redirectAttribute.addAttribute("message",
			    		Constants.NO_EMPLOYEES);
			    response = "redirect:/message.jsp";
			} else {
				model.addAttribute("formAction", "unAssignEmployee");
				model.addAttribute("submit", "un-Assign");
				response = "assignOrUnAssign.jsp";
			}
		} catch (EMSException exception) {
			logger.warn(Constants.UPDATE_PROJECT_EXCEPTION);
			logger.error(exception.getMessage());
			model.addAttribute("error", Constants.UPDATE_PROJECT_EXCEPTION);
			model.addAttribute("link", "projectManagement.jsp");
		}
        return response;
    }

    /**
     * Un-Assigns an employee from the Project.
     * 
	 * @param employeeId id of an employee as int.
	 * @param projectId  id of an project as int.
     * @param model The Model instance contains the request made by 
	 * 		  the client.
     * @param redirectAttribute the RedirectAttribute instance for
     *        adding messages.
     * 
     * @return response page as String.
     * 
     * @throws EMSException EMSException if error occurred in database.
     */
    @PostMapping("/unAssignEmployee")
    private String unAssignEmployee(@RequestBody @RequestParam("projectId")
    		int projectId, @RequestParam("employeeId") int employeeId,
    		Model model, RedirectAttributes redirectAttribute)
    				throws EMSException {
    	String response = "redirect:/error.jsp";
    	Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

    	logger.info("Request got for un-assigning Employee");
        if (projectService.isIdExists(projectId)) {

            if (projectService.isEmployeeIdExists(employeeId)) {
                logger.info("Un-Assigning an employee from a project");
                
                if (projectService.isAlreadyAssigned(projectId, employeeId)) {

                    ProjectDTO project = projectService
                    		.getParticularProject(projectId);
                    employees = project.getEmployees();

                    for (EmployeeDTO employee : employees) {

                        if (employeeId == employee.getEmployeeId()) {
                            employees.remove(employee);
                            break;
                        }
                    }
                    project.setEmployees(employees);
                    projectService.updateProject(project);
                    logger.info(Constants.EMPLOYEE_UNASSIGNED);
                    redirectAttribute.addAttribute("message",
                    		Constants.EMPLOYEE_UNASSIGNED);
                    redirectAttribute.addAttribute("link", 
                    		"updateProjectMenu.jsp");
                    response = "redirect:/message.jsp";
                } else {
                    logger.info(Constants.EMPLOYEE_NOT_ASSIGNED);
					model.addAttribute("formAction", "unAssignEmployee");
					model.addAttribute("submit", "Un-Assign");
					response = "assignOrUnAssign.jsp";
                }
            } else {
        		logger.warn(Constants.ID_NOT_FOUND);
        		model.addAttribute("message", Constants.ID_NOT_FOUND);
        		model.addAttribute("formAction", "unAssignEmployee");
        		model.addAttribute("submit", "Un-Assign");
        		response = "assignOrUnAssign.jsp";
            }
        } else {
            logger.warn(Constants.PROJECT_ID_NOT_FOUND);
            model.addAttribute("message", Constants.PROJECT_ID_NOT_FOUND);
    		model.addAttribute("formAction", "unAssignEmployee");
    		model.addAttribute("submit", "un-assign");
    		response = "assignOrUnAssign.jsp";
        }
        return response;
    }

    /**
     * checks whether the database is empty for Delete operation.
     * 
     * @param model The Model instance contains the request made by 
	 * 	      the client.
     * @param redirectAttribute the RedirectAttribute instance for
     *        adding messages.
     *  
     * @return response page as String.
     */
    @GetMapping("/deleteProject")
    private String deleteProject(Model model,
    		RedirectAttributes redirectAttribute) {
    	String response = "redirect:/error.jsp";
    	
        logger.info("Request got to Delete project");
        try {
        	
			if (projectService.isDbIsEmpty()) {
			    logger.info(Constants.NO_PROJECTS);
			    redirectAttribute.addAttribute("link", "projectManagement.jsp");
			    redirectAttribute.addAttribute("message",
			    		Constants.NO_PROJECTS);
			    response = "redirect:/message.jsp";
			} else {
				response = "deleteProject.jsp";
			}
		} catch (EMSException exception) {
			logger.error(Constants.DELETE_PROJECTS_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DELETE_PROJECTS_EXCEPTION);
			redirectAttribute.addAttribute("link", "projectManagement.jsp");
		}
        return response;
    }

    /**
     * Deletes all the projects.
     * 
     * @param model The Model instance contains the request made by 
	 *        the client.
     * @param redirectAttribute the RedirectAttribute instance for
     *        adding messages.
     *  
     * @return response page as String.
     */
    @GetMapping("/deleteAllProjects")
    private String deleteAllProjects(Model model,
    		RedirectAttributes redirectAttribute) {
    	String response = "redirect:/error.jsp";
    	
        logger.info("Request got to delete all Employees");
        try {
			projectService.deleteAllProjects();
	        logger.info("Deleting all Projects");
	        logger.info(Constants.PROJECTS_DELETED);
	        redirectAttribute.addAttribute("link", "projectManagement.jsp");
	        redirectAttribute.addAttribute("message", Constants.PROJECTS_DELETED);
	        response = "redirect:/message.jsp";
		} catch (EMSException exception) {
			logger.error(Constants.DELETE_PROJECTS_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DELETE_PROJECTS_EXCEPTION);
			redirectAttribute.addAttribute("link", "projectManagement.jsp");
		}
        return response;
    }
    
    /**
     * Deletes particular project.
     * 
     * @param projectId id of an project as int.
     * @param model The Model instance contains the request made by 
	 *        the client.
     * @param redirectAttribute the RedirectAttribute instance for
     *        adding messages.
     * 
     * @return response page as String.
     */
    @GetMapping("/deleteParticularProject")
    private String deleteParticularProject(@RequestParam("projectId")
    		int projectId, Model model, RedirectAttributes redirectAttribute) {
    	String response = "redirect:/error.jsp";
    	
        logger.info("Request got to delete Particular Employee");
        try {
        	
			if (projectService.isIdExists(projectId)) {
			    projectService.deleteParticularProject(projectId);
			    logger.info("Deleteting Particular Project");
			    logger.info(Constants.PROJECT_DELETED);
			    redirectAttribute.addAttribute("message",
			    		Constants.PROJECT_DELETED);
			    redirectAttribute.addAttribute("link", "deleteProject.jsp");
			    response = "redirect:/message.jsp";
			} else {
				logger.warn(Constants.PROJECT_ID_NOT_FOUND);
				model.addAttribute("message", Constants.PROJECT_ID_NOT_FOUND);
				response = "deleteParticularProject.jsp";
			}
		} catch (EMSException exception) {
			logger.error(Constants.DELETE_PROJECT_EXCEPTION);
			logger.error(exception.getMessage());
			redirectAttribute.addAttribute("error",
					Constants.DELETE_PROJECT_EXCEPTION);
			redirectAttribute.addAttribute("link", "projectManagement.jsp");
		}
        return response;
    }
}
