<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Quote Request Details</title>
</head>
<body>
    <div align="center">
     <form action="QuoteRequest" method="post">
      <input type = "submit" value = "Click here"/>
     
	</form>
    <table border="1" cellpadding="5">
        <caption style="display: inline;" > <h2 style="display: inline;">List of Quote Requests</h2></caption>
        <table border="1">
            <tr>
                <th>Request ID</th>
                <th>Client ID</th>     
                <th>Request Date</th>
                <th>Status</th>
                <th>Note</th>
                <th>Size</th>
                <th>Height</th>
                <th>Location</th>
                <th>Proximity to House</th>
            </tr>
            <c:forEach var="request" items="${listRequests}">
                <tr>
                    <td><c:out value="${request.requestID}" /></td>
                    <td><c:out value="${request.clientID}" /></td>                    
                    <td><c:out value="${request.requestDate}" /></td>
                    <td><c:out value="${request.status}" /></td>
                    <td><c:out value="${request.note}" /></td>
                    <td><c:out value="${request.size}" /></td>
                    <td><c:out value="${request.height}" /></td>
                    <td><c:out value="${request.location}" /></td>
                    <td><c:out value="${request.proximityToHouse}" /></td>
                </tr>
            </c:forEach>
        </table>
  
</body>
</html>
