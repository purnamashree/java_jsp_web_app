<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <script src="./js/bootstrap.min.js"></script>
    <title>Resume Upload application</title>
</head>
<body>
    <div class="container">
    <center>
        <h1>Resume Upload application</h1>
        <h2>
            <a href="/new">Upload Resumes</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Resumes</a>

        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Uploaded Resumes</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Content</th>
            </tr>
            <c:forEach var="resume" items="${listResumes}">
                <tr>
                    <td><c:out value="${resume.id}" /></td>
                    <td><c:out value="${resume.name}" /></td>
                    <td>
                        <a href="/download?id=<c:out value='${resume.id}' />">Download</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${resume.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </div>
</body>
</html>
