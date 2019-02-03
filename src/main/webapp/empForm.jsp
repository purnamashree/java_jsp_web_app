<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee CRUD Application</title>
</head>
<body>
    <center>
        <h1>Employee CRUD Application</h1>
        <h2>
            <a href="/new">Add New Employee</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Employees</a>

        </h2>
    </center>
    <div align="center">
        <c:if test="${emp != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${emp == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${emp != null}">
                        Edit Employee
                    </c:if>
                    <c:if test="${emp == null}">
                        Add New Employee
                    </c:if>
                </h2>
            </caption>
                <c:if test="${emp != null}">
                    <input type="hidden" name="id" value="<c:out value='${emp.id}' />" />
                </c:if>
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="firstName" size="45"
                            value="<c:out value='${emp.firstName}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="lastName" size="45"
                            value="<c:out value='${emp.lastName}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="title" size="50"
                            value="<c:out value='${emp.title}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>
</body>
</html>