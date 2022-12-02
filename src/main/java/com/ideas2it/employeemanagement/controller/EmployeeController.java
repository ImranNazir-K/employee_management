
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.LoggerFactory;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * performs operations like create, Display, Update, for employees
 *
 * @author IMRAN NAZIR K
 *
 * @version 5.0
 */
public class EmployeeController extends HttpServlet {

    private static final EmployeeService employeeService = new EmployeeServiceImpl();
    private static final Logger logger = LoggerFactory.getFactory();

    public EmployeeController() {
    }

    /**
     * Handles the request made by the client and responds to that corresponding
     * request.
     * 
     * @param employeeDto as EmployeeDTO object.
     * 
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {

            case "/createEmployee":
                createEmployee(request, response);
                break;

            case "/updateAllDetails":
                updateEmployeeDetails(request, response);
                break;

            case "/assignProject":
                assignProject(request, response);
                break;

            case "/unAssignProject":
                unAssignProject(request, response);
                break;

            case "/deleteParticularEmployee":
                deleteParticularEmployee(request, response);
                break;

            default:
                logger.info(Constants.INVALID_REQUEST);
                request.setAttribute("link", "employeeManagement.jsp");
                request.setAttribute("error", "INVALID REQUEST");
                request.getRequestDispatcher("errorPage.jsp").forward(request,
                        response);
            }
        } catch (EMSException exception) {
            System.out.println("post");
            logger.error(exception.getMessage());
            request.setAttribute("link", "employeeManagement.jsp");
            request.setAttribute("error", exception.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request,
                    response);
        }
    }

    /**
     * Handles the request made by the client and responds to that corresponding
     * request.
     * 
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     * 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {

            case "/getEmployeeForm":
                getEmployeeForm(request, response);
                break;

            case "/updateEmployee":
                updateEmployee(request, response);
                break;

            case "/getEmployeeToUpdate":
                getEmployeeToUpdate(request, response);
                break;

            case "/displayEmployee":
                displayEmployee(request, response);
                break;

            case "/getAssignProjectForm":
                getAssignProjectForm(request, response);
                break;

            case "/getUnAssignProjectForm":
                getUnAssignProjectForm(request, response);
                break;

            case "/displayAllEmployees":
                displayAllEmployees(request, response);
                break;

            case "/displayParticularEmployee":
                displayParticularEmployee(request, response);
                break;

            case "/displayEmployeeProject":
                displayEmployeeProject(request, response);
                break;

            case "/deleteEmployee":
                deleteEmployee(request, response);
                break;

            case "/deleteAllEmployees":
                deleteAllEmployees(request, response);
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
            request.setAttribute("link", "employeeManagement.jsp");
            request.setAttribute("error", exception.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request,
                    response);
        }
    }

    /**
     * Forward the request for creating employee.
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
    private void getEmployeeForm(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        logger.info("Request got for creating Employee");
        request.setAttribute("formAction", "createEmployee");
        request.setAttribute("submit", "create");
        request.getRequestDispatcher("createEmployee.jsp").forward(request,
                response);
    }

    /**
     * creates employee.
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
    private void createEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("creating Employee");

        int employeeId;
        double salary = Double
                .parseDouble(request.getParameter("employeeSalary"));
        long contactNumber = Long
                .parseLong(request.getParameter("employeeContactNumber"));
        LocalDate dateOfBirth = LocalDate
                .parse(request.getParameter("employeeDateOfBirth"));
        String name = request.getParameter("employeeName");
        String mailId = request.getParameter("employeeMailId");
        String message = new String();
        isMailIdExists(request, response, mailId);
        isContactNumberExists(request, response, contactNumber);
        EmployeeDTO employeeDto = new EmployeeDTO();

        employeeDto = new EmployeeDTO(name, mailId, contactNumber, salary,
                dateOfBirth);

        employeeId = employeeService.createEmployee(employeeDto);
        message = Constants.EMPLOYEE_CREATED + employeeId;

        logger.info(message);
        request.setAttribute("message", message);
        request.setAttribute("link", "employeeManagement.jsp");
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }

    /**
     * checks whether the contact number of an employee is present in the
     * database.
     * 
     * @param request       HttpServletRequest object contains request made by
     *                      client.
     * @param response      HttpServletResponse object contains response made by
     *                      client.
     * @param contactNumber contactNumber of an employee.
     * 
     * @throws EMSException     if error occurred in database.
     * @throws IOException      if invalid input was given.
     * @throws ServletException if the request cannot be handled.
     */
    private void isContactNumberExists(HttpServletRequest request,
            HttpServletResponse response, long contactNumber)
            throws ServletException, IOException, EMSException {

        if (employeeService.isContactNumberExists(contactNumber)) {
            logger.warn(Constants.EXISTING_CONTACT_NUMBER);
            request.setAttribute("message", Constants.EXISTING_CONTACT_NUMBER);
            request.setAttribute("link", "createEmployee.jsp");
            request.setAttribute("submit", "create");
            request.setAttribute("formAction", "createEmployee");
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        }
    }

    /**
     * checks whether Mail Id of an employee is present in the database.
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
    private void isMailIdExists(HttpServletRequest request,
            HttpServletResponse response, String mailId)
            throws ServletException, IOException, EMSException {

        if (employeeService.isMailIdExists(mailId)) {

            logger.warn(Constants.EXISTING_MAIL);
            request.setAttribute("message", Constants.EXISTING_MAIL);
            request.setAttribute("submit", "create");
            request.setAttribute("formAction", "createEmployee");
            request.getRequestDispatcher("createEmployee.jsp").forward(request,
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
    private void displayEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws EMSException, ServletException, IOException {
        logger.info("Request got to Display Employee");
        if (employeeService.isDbIsEmpty()) {
            logger.info(Constants.NO_EMPLOYEES);
            request.setAttribute("link", "employeeManagement.jsp");
            request.setAttribute("message", Constants.NO_EMPLOYEES);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            request.getRequestDispatcher("displayEmployees.jsp").forward(request,
                    response);        }
    }

    /**
     * Displays all the employees.
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
    private void displayAllEmployees(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got for displaying all Employees");

        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        request.setAttribute("employees", employees);
        logger.info("Displaying Employees");
        logger.info(employees);
        request.getRequestDispatcher("displayAllEmployees.jsp").forward(request,
                response);

    }

    /**
     * Displays the particular Employee.
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
    private void displayParticularEmployee(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            NumberFormatException, EMSException {
        logger.info("Request got for displaying Particular Employee");

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();

        if (employeeService.isIdExists(employeeId)) {
            EmployeeDTO employee = employeeService
                    .getParticularEmployee(employeeId);
            employees.add(employee);
            request.setAttribute("employees", employees);
            logger.info("Displaying Particular Employee");
            logger.info(employees);
            request.getRequestDispatcher("displayAllEmployees.jsp")
                    .forward(request, response);
        } else {
            logger.info(Constants.ID_NOT_FOUND);
            request.setAttribute("message", Constants.ID_NOT_FOUND);
            request.getRequestDispatcher("displayParticularEmployee.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Displays the projects assigned to an employee.
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws EMSException
     */
    private void displayEmployeeProject(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got for Displaying the projects of an Employee");

        int employeeId = Integer.parseInt(request.getParameter("id"));

        EmployeeDTO employee = employeeService
                .getParticularEmployee(employeeId);
        logger.info("Displaying the projects of an  Employee");
        request.setAttribute("projects", employee.getProjects());
        request.getRequestDispatcher("displayEmployeeProject.jsp")
                .forward(request, response);
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
    private void updateEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws EMSException, ServletException, IOException {
        logger.info("Request got to Update Employee");
        if (employeeService.isDbIsEmpty()) {
            logger.info(Constants.NO_EMPLOYEES);
            request.setAttribute("link", "employeeManagement.jsp");
            request.setAttribute("message", Constants.NO_EMPLOYEES);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            request.getRequestDispatcher("updateMenu.jsp").forward(request,
                    response);
        }
    }

    /**
     * Gets the particular employee to update.
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws EMSException
     */
    private void getEmployeeToUpdate(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got for Updating the Details of an Employee");

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        EmployeeDTO employee = new EmployeeDTO();

        if (employeeService.isIdExists(employeeId)) {
            employee = employeeService.getParticularEmployee(employeeId);
            request.setAttribute("employee", employee);
            request.setAttribute("formAction", "updateAllDetails");
            request.setAttribute("submit", "update");
            request.getRequestDispatcher("createEmployee.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("message", Constants.ID_NOT_FOUND);
            request.getRequestDispatcher("updateEmployee.jsp").forward(request,
                    response);
        }
    }

    /**
     * updates the employee.
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
    private void updateEmployeeDetails(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Updating the Details of an Employee");

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        if (employeeService.isIdExists(employeeId)) {
            EmployeeDTO employee = employeeService
                    .getParticularEmployee(employeeId);
            employee.setEmployeeName(request.getParameter("employeeName"));
            employee.setEmployeeMailId(request.getParameter("employeeMailId"));
            employee.setEmployeeContactNumber(Long
                    .parseLong(request.getParameter("employeeContactNumber")));
            employee.setEmployeeSalary(
                    Double.parseDouble(request.getParameter("employeeSalary")));
            employee.setEmployeeDateOfBirth(LocalDate
                    .parse(request.getParameter("employeeDateOfBirth")));

            employeeService.updateEmployee(employee);
            logger.info(Constants.EMPLOYEE_UPDATED);
            request.setAttribute("message", Constants.EMPLOYEE_UPDATED);
            request.setAttribute("link", "updateMenu.jsp");
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            logger.warn(Constants.ID_NOT_FOUND);
            request.setAttribute("message", Constants.ID_NOT_FOUND);
            request.getRequestDispatcher("updateEmployee.jsp").forward(request,
                    response);
        }
    }

    /**
     * Forwards the request to assign project.
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
    private void getAssignProjectForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got for Assigning Project");

        if (employeeService.isProjectDbIsEmpty()) {
            logger.info(Constants.NO_PROJECTS);
            request.setAttribute("link", "updateMenu.jsp");
            request.setAttribute("message", Constants.NO_PROJECTS);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("formAction", "assignProject");
            request.setAttribute("submit", "assign");
            request.getRequestDispatcher("assignOrUnAssign.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Assigns project to an employee.
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
    private void assignProject(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        if (employeeService.isIdExists(employeeId)) {

            if (employeeService.isProjectIdExists(projectId)) {
                assignProjectToEmployee(request, response, employeeId,
                        projectId);

            } else {

                projectIdNotFound(request, response, "assignProject", "assign");
            }
        } else {
            employeeIdNotFound(request, response, "assignProject", "assign");
        }

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
        request.setAttribute("formAction", formAction);
        request.setAttribute("submit", submitValue);
        request.getRequestDispatcher("assignOrUnAssign.jsp").forward(request,
                response);
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
        request.setAttribute("formAction", formAction);
        request.setAttribute("submit", submitValue);
        request.getRequestDispatcher("assignOrUnAssign.jsp").forward(request,
                response);
    }

    /**
     * Assigns a project to an employee.
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
    private void assignProjectToEmployee(HttpServletRequest request,
            HttpServletResponse response, int employeeId, int projectId)
            throws EMSException, ServletException, IOException {
        logger.info("Assigning a project to an Employee");

        Set<ProjectDTO> projects = new HashSet<ProjectDTO>();

        if (employeeService.isAlreadyAssigned(employeeId, projectId)) {
            logger.info(Constants.PROJECT_ALREADY_ASSIGNED);
            request.setAttribute("message", Constants.PROJECT_ALREADY_ASSIGNED);
            request.setAttribute("link", "updateMenu.jsp");
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
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
            request.setAttribute("message", Constants.PROJECT_ASSIGNED);
            request.setAttribute("link", "updateMenu.jsp");
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        }
    }

    /**
     * Forwards the request to un-assign project.
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
    private void getUnAssignProjectForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got for Un-Assigning Project");

        if (employeeService.isProjectDbIsEmpty()) {
            logger.info(Constants.NO_PROJECTS);
            request.setAttribute("link", "updateMenu.jsp");
            request.setAttribute("message", Constants.NO_PROJECTS);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("formAction", "unAssignProject");
            request.setAttribute("submit", "un-Assign");
            request.getRequestDispatcher("assignOrUnAssign.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Un-assigns a project of an employee.
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
    private void unAssignProject(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        if (employeeService.isIdExists(employeeId)) {

            if (employeeService.isProjectIdExists(projectId)) {
                unAssignProjectFromEmployee(request, response, employeeId,
                        projectId);
            } else {
                projectIdNotFound(request, response, "unAssignProject",
                        "un-assign");
            }
        } else {
            employeeIdNotFound(request, response, "unAssignProject",
                    "un-assign");
        }
    }

    /**
     * Un-assigns a project from employee.
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
    private void unAssignProjectFromEmployee(HttpServletRequest request,
            HttpServletResponse response, int employeeId, int projectId)
            throws EMSException, ServletException, IOException {
        logger.info("Un-Assigning a project from an Employee");

        Set<ProjectDTO> projects = new HashSet<ProjectDTO>();

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
            request.setAttribute("link", "updateMenu.jsp");
            request.setAttribute("message", Constants.PROJECT_UNASSIGNED);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            logger.info(Constants.PROJECT_NOT_ASSIGNED);
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
    private void deleteEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got to Delete Employee");
        if (employeeService.isDbIsEmpty()) {
            logger.info(Constants.NO_EMPLOYEES);
            request.setAttribute("link", "employeeManagement.jsp");
            request.setAttribute("message", Constants.NO_EMPLOYEES);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            request.getRequestDispatcher("deleteEmployees.jsp").forward(request,
                    response);
        }
    }

    /**
     * Deletes all the Employees.
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
    private void deleteAllEmployees(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Deleting all Employees");

        employeeService.deleteAllEmployees();
        logger.info(Constants.EMPLOYEES_DELETED);
        request.setAttribute("link", "employeeManagement.jsp");
        request.setAttribute("message", Constants.EMPLOYEES_DELETED);
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }

    /**
     * Deletes particular Employee.
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
    private void deleteParticularEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        logger.info("Request got to delete particular Employee");
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        if (employeeService.isIdExists(employeeId)) {
            logger.info("Deleting an Employee");

            employeeService.deleteParticularEmployee(employeeId);
            logger.info(Constants.EMPLOYEE_DELETED);
            request.setAttribute("link", "deleteEmployees.jsp");
            request.setAttribute("message", Constants.EMPLOYEE_DELETED);
            request.getRequestDispatcher("message.jsp").forward(request,
                    response);
        } else {
            logger.warn(Constants.ID_NOT_FOUND);
            request.setAttribute("message", Constants.ID_NOT_FOUND);
            request.getRequestDispatcher("deleteParticularEmployee.jsp")
                    .forward(request, response);
        }
    }
}