package com.venkat.service;

import com.venkat.common.DBConnection;
import com.venkat.enity.Employee;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeServiceTest {

    private Connection connection;

    /*@BeforeAll
    void setup(){
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    void teardown(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    void testDbConnection(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            assertNotNull(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fetchCitiesNotNull(){
        EmployeeService cityService = new EmployeeService();
        try {
            List<Employee> employees = cityService.fetchEmployees();
            assertNotNull(employees);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fetchCitiesSize(){
        EmployeeService cityService = new EmployeeService();
        try {
            List<Employee> employees = cityService.fetchEmployees();
            assertNotNull(employees);
            assertTrue(employees.size()>0);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
