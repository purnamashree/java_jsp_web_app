<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <script src="./js/bootstrap.min.js"></script>
    <title>Employee CRUD application</title>
</head>
<body>
    <div class="container">
    <center>
        <h1>Employee CRUD application</h1>
        <h2>
            <a href="/new">Add Employee</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Employees</a>

        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Employees</h2></caption>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Title</th>
            </tr>
            <c:forEach var="emp" items="${listEmp}">
                <tr>
                    <td><c:out value="${emp.id}" /></td>
                    <td><c:out value="${emp.firstName}" /></td>
                    <td><c:out value="${emp.lastName}" /></td>
                    <td><c:out value="${emp.title}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${emp.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${emp.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </div>
</body>
</html>
