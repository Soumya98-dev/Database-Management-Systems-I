<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All Client list</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of People</h2></caption>
        <tr>
            <th>Client_ID</th>
            <th>Password</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Address</th>
            <th>CreditCardInfo</th>
            <th>PhoneNumber</th>
            <th>Email</th>

        </tr>
        <c:forEach var="clients" items="${listClient}">
            <tr style="text-align:center">
                <td><c:out value="${clients.Client_ID}" /></td>
                <td><c:out value="${clients.Password}" /></td>
                <td><c:out value="${clients.FirstName}" /></td>
                <td><c:out value="${clients.LastName}" /></td>
                <td><c:out value="${clients.Address}" /></td>
                <td><c:out value="${clients.CreditCardInfo}"/></td>
                <td><c:out value="${clients.PhoneNumber}" /></td>
                <td><c:out value="${clients.Email}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>