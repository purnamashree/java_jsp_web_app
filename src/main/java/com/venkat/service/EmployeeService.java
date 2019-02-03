package com.venkat.service;

import com.venkat.common.DBConnection;
import com.venkat.enity.Employee;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements Serializable {

    public boolean addEmployee(Employee employee) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO employee (first_name, last_name, title) VALUES (?, ?, ?)";
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setString(3, employee.getTitle());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        connection.close();
        return rowInserted;
    }

    public List<Employee> fetchEmployees() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        List<Employee> employees = new ArrayList<Employee>();
        String sql = "SELECT * FROM employee";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("emp_id"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setTitle(resultSet.getString("title"));
            employees.add(employee);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return employees;
    }

    public boolean deleteEmployee(Employee employee) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM employee where emp_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, employee.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        connection.close();
        return rowDeleted;
    }

    public boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE employee SET first_name = ?, last_name = ?, title = ?";
        sql += " WHERE emp_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setString(3, employee.getTitle());
        statement.setInt(4, employee.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        connection.close();
        return rowUpdated;
    }

    public Employee getEmployee(int id) throws SQLException, ClassNotFoundException {
        Employee employee = null;
        String sql = "SELECT * FROM employee WHERE emp_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String title = resultSet.getString("title");

            employee = new Employee(firstName, lastName, title, id);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return employee;
    }
}
