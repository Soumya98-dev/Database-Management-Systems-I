<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All Administrator list</title>
</head>
<body>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Administrator</h2></caption>
        <tr>
            <th>AdminID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Email</th>
        </tr>
        <c:forEach var="admin" items="${listAdministrator}">
            <tr style="text-align:center">
                <td><c:out value="${admin.adminID}" /></td>
                <td><c:out value="${admin.username}" /></td>
                <td><c:out value="${admin.password}" /></td>
                <td><c:out value="${admin.email}" /></td>
            </tr>
        </c:forEach>
        <c:if test="${empty listAdministrator}">
            <tr>
                <td colspan="3">No administrators found.</td>
            </tr>
        </c:if>
    </table>
</div>

</body>
</html>
