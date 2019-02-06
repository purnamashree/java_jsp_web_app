<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Resume Uploader</title>
<style>
    .upload {
      width: 320px;
      padding: 10px;
      border: 5px solid gray;
      margin: 0;
      }
</style>
</head>
<body>
    <center>
        <h3>Resume Uploader</h3>
        <div class="upload">
            <form method="post" action="${pageContext.request.contextPath }/uploader" enctype="multipart/form-data">
                <table border="0" cellpadding="2" cellspacing="2">
                    <tr>
                        <td valign="top">Resumes</td>
                        <td><input type="file" name="file" multiple="multiple" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><input type="submit" value="Upload" /></td>
                    </tr>
                </table>
            </form>
         </div>
    </center>
</body>
</html>