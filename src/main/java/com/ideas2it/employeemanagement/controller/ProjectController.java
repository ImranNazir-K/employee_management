
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.LoggerFactory;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.service.impl.ProjectServiceImpl;

/**
 * performs operations like create, Display, Update, Delete for projects.
 *
 * @author IMRAN NAZIR K
 *
 * @version 5.0
 */
public class ProjectController extends HttpServlet {

    private static final ProjectService projectService = new ProjectServiceImpl();
    private static final Logger logger = LoggerFactory.getFactory();

    public ProjectController() {
    }

    /**
     * Handles the request made by the client and responds to that corresponding
     * request.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     * 
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getServletPath();

        try {
            switch (action) {

            case "/createProject":
                createProject(request, response);
                break;

            case "/updateProjectDetails":
                updateProjectDetails(request, response);
                break;

            case "/assignEmployee":
                assignEmployee(request, response);
                break;

            case "/unAssignEmployee":
                unAssignEmployee(request, response);
                break;

            case "/deleteParticularProject":
                deleteParticularProject(request, response);
                break;

            default:
                logger.info(Constants.INVALID_REQUEST);
                request.setAttribute("link", "employeeManagement.jsp");
                request.setAttribute("error", "INVALID REQUEST");
                request.getRequestDispatcher("errorPage.jsp").forward(request,
                        response);
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
            request.setAttribute("message", exception.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request,
                    response);
            exception.printStackTrace();
        }
    }

    /**
     * Handles the request made by the client and responds to that corresponding
     * request.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {

            case "/getProjectForm":
                getProjectForm(request, response);
                break;

            case "/updateProject":
                updateProject(request, response);
                break;

            case "/getProjectToUpdate":
                getProjectToUpdate(request, response);
                break;

            case "/getAssignEmployeeForm":
                getAssignEmployeeForm(request, response);
                break;

            case "/getUnAssignEmployeeForm":
                getUnAssignEmployeeForm(request, response);
                break;

            case "/displayProject":
                displayProject(request, response);
                break;

            case "/displayAllProjects":
                displayAllProjects(request, response);
                break;

            case "/displayParticularProject":
                displayParticularProject(request, response);
                break;

            case "/displayProjectEmployee":
                displayProjectEmployee(request, response);
                break;

            case "/deleteProject":
                deleteProject(request, response);
                break;

            case "/deleteAllProjects":
                deleteAllProjects(request, response);
                break;

            default:
                logger.info(Constants.INVALID_REQUEST);
                request.setAttribute("link", "employeeManagement.jsp");
                request.setAttribute("error", "INVALID REQUEST");
                request.getRequestDispatcher("errorPage.jsp").forward(request,
                        response);
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
            request.setAttribute("message", exception.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request,
                    response);
            exception.printStackTrace();
        }
    }

    /**
     * Forward the request for creating project.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void getProjectForm(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        logger.info("Request got for creating a project");

        request.setAttribute("formAction", "createProject");
        request.setAttribute("submit", "create");
        request.getRequestDispatcher("createProject.jsp").forward(request,
                response);
    }

    /**
     * Creates project.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void createProject(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("creating a project");

        int projectId;
        ProjectDTO projectDto = new ProjectDTO();
        String message = new String();
        String projectDescription = request.getParameter("projectDescription");
        String projectDomain = request.getParameter("projectDomain");
        String projectName = request.getParameter("projectName");

        projectDto = new ProjectDTO(projectName, projectDescription,
                projectDomain);
        projectId = projectService.createProject(projectDto);
        message = Constants.PROJECT_CREATED + projectId;

        request.setAttribute("message", message);
        request.setAttribute("link", "projectManagement.jsp");
        request.getRequestDispatcher("message.jsp").forward(request, response);
        logger.info(Constants.PROJECT_CREATED);
    }

    /**
     * checks whether the database is empty for update operation.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void updateProject(HttpServletRequest request,
            HttpServletResponse response)
            throws EMSException, ServletException, IOException {
        logger.info("Request got to Update Employee");

        if (projectService.isDbIsEmpty()) {
            logger.info(Constants.NO_EMPLOYEES);
            request.setAttribute("link", "projectManagement.jsp");
            request.setAttribute("message", Constants.NO_PROJECTS);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            request.getRequestDispatcher(
                    "updateProjectMenu.jsp").forward(request,
                    response);
        }
    }

    /**
     * Gets the particular project to update.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void getProjectToUpdate(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got for Updating all Details of an Project");

        int projectId = Integer.parseInt(request.getParameter("projectId"));

        if (projectService.isIdExists(projectId)) {
            ProjectDTO project = projectService.getParticularProject(projectId);
            request.setAttribute("project", project);
            request.setAttribute("formAction", "updateProjectDetails");
            request.setAttribute("submit", "update");
            request.getRequestDispatcher("createProject.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("message", Constants.ID_NOT_FOUND);
            request.getRequestDispatcher("updateEmployee.jsp").forward(request,
                    response);
        }
    }

    /**
     * updates the project.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void updateProjectDetails(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("updating all details of an Project");

        int projectId = Integer.parseInt(request.getParameter("projectId"));

        if (projectService.isIdExists(projectId)) {
            ProjectDTO projectDto = projectService
                    .getParticularProject(projectId);
            projectDto.setProjectName(request.getParameter("projectName"));
            projectDto.setProjectDomain(
                    request.getParameter("projectDescription"));
            projectDto.setProjectDescription(
                    request.getParameter("projectDomain"));

            projectService.updateProject(projectDto);
            request.setAttribute("message", Constants.PROJECT_UPDATED);
            request.setAttribute("link", "updateProjectMenu.jsp");
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
            logger.info(Constants.PROJECT_UPDATED);
        } else {
            request.setAttribute("message", Constants.ID_NOT_FOUND);
            request.getRequestDispatcher("updateEmployee.jsp").forward(request,
                    response);
        }
    }

    /**
     * Gets the request to assign Employee.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void getAssignEmployeeForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got for Assingning Employee");

        if (projectService.isEmployeeDbIsEmpty()) {
            logger.info(Constants.NO_EMPLOYEES);
            request.setAttribute("link", "updateProjectMenu.jsp");
            request.setAttribute("message", Constants.NO_EMPLOYEES);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("formAction", "assignEmployee");
            request.setAttribute("submit", "assign");
            request.getRequestDispatcher("assignOrUnAssign.jsp")
                    .forward(request, response);
        }

    }

    /**
     * Assigns Employee to an project.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void assignEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        if (projectService.isIdExists(projectId)) {

            if (projectService.isEmployeeIdExists(employeeId)) {
                assignEmployeeToProject(request, response, projectId,
                        employeeId);

            } else {
                employeeIdNotFound(request, response, "assignEmployee",
                        "assign");
            }
        } else {
            projectIdNotFound(request, response, "assignEmployee", "assign");
        }

    }

    /**
     * forwards the request when the projectId is not found in the database.
     * 
     * @param formAction  value for form action as String.
     * @param request     HttpServletRequest object contains request made by
     *                    client.
     * @param response    HttpServletResponse object contains response made by
     *                    client.
     * @param submitValue value for submit button as String.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void projectIdNotFound(HttpServletRequest request,
            HttpServletResponse response, String formAction, String submitValue)
            throws ServletException, IOException {
        logger.warn(Constants.PROJECT_ID_NOT_FOUND);
        request.setAttribute("message", Constants.PROJECT_ID_NOT_FOUND);
        request.getRequestDispatcher("assignOrUnAssign.jsp").forward(request,
                response);
    }

    /**
     * forwards the request when the employeeId is not found in the database.
     * 
     * @param formAction  value for form action as String.
     * @param request     HttpServletRequest object contains request made by
     *                    client.
     * @param response    HttpServletResponse object contains response made by
     *                    client.
     * @param submitValue value for submit button as String.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void employeeIdNotFound(HttpServletRequest request,
            HttpServletResponse response, String formAction, String submitValue)
            throws ServletException, IOException {
        logger.warn(Constants.ID_NOT_FOUND);
        request.setAttribute("message", Constants.ID_NOT_FOUND);
        request.getRequestDispatcher("assignOrUnAssign.jsp").forward(request,
                response);
    }

    /**
     * Assigns an Employee to a project.
     * 
     * @param employeeId id of an employee as int.s
     * @param request    HttpServletRequest object contains request made by
     *                   client.
     * @param response   HttpServletResponse object contains response made by
     *                   client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void assignEmployeeToProject(HttpServletRequest request,
            HttpServletResponse response, int projectId, int employeeId)
            throws EMSException, ServletException, IOException {
        Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

        logger.info("Assigning an employee to a project");

        if (projectService.isAlreadyAssigned(projectId, employeeId)) {
            logger.info(Constants.EMPLOYEE_ALREADY_ASSIGNED);
            request.setAttribute("message",
                    Constants.EMPLOYEE_ALREADY_ASSIGNED);
            request.setAttribute("link", "updateProjectMenu.jsp");
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
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
            request.setAttribute("message", Constants.EMPLOYEE_ASSIGNED);
            request.setAttribute("link", "updateProjectMenu.jsp");
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);

        }

    }

    /**
     * Gets the request to assign Employee.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void getUnAssignEmployeeForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got for Un-Assigning Employee");

        if (projectService.isEmployeeDbIsEmpty()) {
            logger.info(Constants.NO_EMPLOYEES);
            request.setAttribute("link", "updateProjectMenu.jsp");
            request.setAttribute("message", Constants.NO_EMPLOYEES);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("formAction", "unAssignEmployee");
            request.setAttribute("submit", "un-Assign");
            request.getRequestDispatcher("assignOrUnAssign.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Un-Assigns an employee from the Project.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void unAssignEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got for un-assigning Project");

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        if (projectService.isIdExists(projectId)) {

            if (projectService.isEmployeeIdExists(employeeId)) {
                unAssignEmployeeFromProject(request, response, employeeId,
                        projectId);
            } else {
                employeeIdNotFound(request, response, "unAssignEmployee",
                        "un-assign");
            }
        } else {
            projectIdNotFound(request, response, "unAssignEmployee",
                    "un-assign");
        }
    }

    /**
     * un-assigns an employee from a project.
     * 
     * @param employeeId id of an employee as int.
     * @param projectId  id of an project as int.
     * @param request    HttpServletRequest object contains request made by
     *                   client.
     * @param response   HttpServletResponse object contains response made by
     *                   client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void unAssignEmployeeFromProject(HttpServletRequest request,
            HttpServletResponse response, int employeeId, int projectId)
            throws EMSException, ServletException, IOException {
        Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

        logger.info("Un-Assigning an employee from a project");

        if (projectService.isAlreadyAssigned(projectId, employeeId)) {

            ProjectDTO project = projectService.getParticularProject(projectId);
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
            request.setAttribute("message", Constants.EMPLOYEE_UNASSIGNED);
            request.setAttribute("link", "updateProjectMenu.jsp");
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            logger.info(Constants.EMPLOYEE_NOT_ASSIGNED);
            request.setAttribute("message", Constants.PROJECT_NOT_ASSIGNED);
            request.setAttribute("link", "updateMenu.jsp");
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        }
    }

    /**
     * checks whether the database is empty for Delete operation.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void displayProject(HttpServletRequest request,
            HttpServletResponse response)
            throws EMSException, ServletException, IOException {
        logger.info("Request got to Displaying Projects");

        if (projectService.isDbIsEmpty()) {
            logger.info(Constants.NO_PROJECTS);
            request.setAttribute("link", "projectManagement.jsp");
            request.setAttribute("message", Constants.NO_PROJECTS);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            response.sendRedirect("displayProjects.jsp");
        }
    }

    /**
     * Displays all the projects.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void displayAllProjects(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        List<ProjectDTO> projects = new ArrayList<ProjectDTO>();

        logger.info("Request got for displaying All projects");
        projects = projectService.getAllProjects();
        logger.info("Displaying Particular Project");
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("displayAllProjects.jsp").forward(request,
                response);

    }

    /**
     * Displays particular project.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void displayParticularProject(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, EMSException, IOException {
        logger.info("Request got for displaying Particular Employee");

        int projectId = Integer.parseInt(request.getParameter("projectId"));
        List<ProjectDTO> projects = new ArrayList<ProjectDTO>();

        if (projectService.isIdExists(projectId)) {
            ProjectDTO project = projectService.getParticularProject(projectId);
            projects.add(project);
            logger.info("Displaying Particular Project");
            request.setAttribute("projects", projects);
            request.getRequestDispatcher("displayAllProjects.jsp")
                    .forward(request, response);
        } else {
            request.setAttribute("message", Constants.ID_NOT_FOUND);
            request.getRequestDispatcher("displayParticularProject.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Displays the employees assigned to an project.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void displayProjectEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got to display Employee of a project");

        int projectId = Integer.parseInt(request.getParameter("id"));

        ProjectDTO project = projectService.getParticularProject(projectId);
        logger.info("Displaying th Employees of a Project");
        request.setAttribute("employees", project.getEmployees());
        request.getRequestDispatcher("displayProjectEmployee.jsp")
                .forward(request, response);
    }

    /**
     * checks whether the database is empty for Delete operation.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void deleteProject(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got to Delete project");
        if (projectService.isDbIsEmpty()) {
            logger.info(Constants.NO_PROJECTS);
            request.setAttribute("link", "projectManagement.jsp");
            request.setAttribute("message", Constants.NO_PROJECTS);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            request.getRequestDispatcher("deleteProject.jsp").forward(request,
                    response);
        }
    }

    /**
     * Deletes particular project.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void deleteParticularProject(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got to delete Particular Employee");
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        if (projectService.isIdExists(projectId)) {
            projectService.deleteParticularProject(projectId);
            logger.info("Deleteting Particular Project");
            logger.info(Constants.PROJECT_DELETED);
            request.setAttribute("message", Constants.PROJECT_DELETED);
            request.setAttribute("link", "deleteProject.jsp");
            request.getRequestDispatcher("errorPage.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("message", Constants.ID_NOT_FOUND);
            request.getRequestDispatcher("deleteParticularProject.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Deletes all the projects.
     * 
     * @param request  HttpServletRequest object contains request made by
     *                 client.
     * @param response HttpServletResponse object contains response made by
     *                 client.
     * 
     * @throws EMSException     if error occurred in database
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void deleteAllProjects(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {

        logger.info("Request got to delete all Employees");

        projectService.deleteAllProjects();
        logger.info("Deleting all Projects");
        request.setAttribute("link", "projectManagement.jsp");
        request.setAttribute("message", Constants.PROJECTS_DELETED);
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }
}
