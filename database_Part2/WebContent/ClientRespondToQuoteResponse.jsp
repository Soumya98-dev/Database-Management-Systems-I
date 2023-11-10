<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Client Respond to Quote Response Details</title>
</head>
<body>
    <div align="center">
        <form action="ClientRespondToQuoteResponse" method="post">
            <input type="submit" value="Click here"/>
        </form>
        <h2>List of Client respond to Quote Response</h2>
        <table border="1">
            <tr>
                <th>Client Response ID</th>
                <th>Contractor ID</th>     
                <th>Response ID</th>  
                <th>Response Date</th>
                <th>Status</th>
                <th>Note</th>
            </tr>
            <c:forEach var="clientResponse" items="${listQuoteResponses}">
                <tr>
                    <td><c:out value="${clientResponse.clientResponseID}" /></td>
                    <td><c:out value="${clientResponse.contractorID}" /></td>   
                    <td><c:out value="${clientResponse.responseID}" /></td>                     
                    <td><c:out value="${clientResponse.responseDate}" /></td>
                    <td><c:out value="${clientResponse.status}" /></td>
                    <td><c:out value="${clientResponse.note}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
