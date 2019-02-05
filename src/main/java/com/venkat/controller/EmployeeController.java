package com.venkat.controller;

import com.venkat.enity.Employee;
import com.venkat.service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*@WebServlet(
        name = "EmployeeController",
        urlPatterns = {"/employee"}
)*/
public class EmployeeController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private EmployeeService employeeService;

    public void init() {
        employeeService = new EmployeeService();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            if(action.contains("/new")){
                showNewForm(request, response);
            }else if(action.contains("/insert")){
                insertEmp(request, response);
            }else if(action.contains("/delete")){
                deleteBook(request, response);
            }else if(action.contains("/edit")){
                showEditForm(request, response);
            }else if(action.contains("/update")){
                updateEmp(request, response);
            }else{
                listEmployees(request, response);
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ClassNotFoundException {

        List<Employee> listEmp = employeeService.fetchEmployees();
        request.setAttribute("listEmp", listEmp);
        RequestDispatcher dispatcher = request.getRequestDispatcher("empList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("empForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, ClassNotFoundException {

        int id = Integer.parseInt(request.getParameter("id"));
        Employee emp = employeeService.getEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("empForm.jsp");
        request.setAttribute("emp", emp);
        dispatcher.forward(request, response);

    }

    private void insertEmp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String title = request.getParameter("title");

        Employee emp = new Employee(firstName, lastName, title);
        employeeService.addEmployee(emp);
        response.sendRedirect("list");
    }

    private void updateEmp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {

        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String title = request.getParameter("title");

        Employee emp = new Employee(firstName, lastName, title, id);
        employeeService.updateEmployee(emp);
        response.sendRedirect("list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {

        int id = Integer.parseInt(request.getParameter("id"));

        Employee emp = new Employee(id);
        employeeService.deleteEmployee(emp);
        response.sendRedirect("list");

    }


}
