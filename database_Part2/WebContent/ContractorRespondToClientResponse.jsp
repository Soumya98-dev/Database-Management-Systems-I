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
     <form action="ContractorRespondToClientResponse" method="post">
      <input type = "submit" value = "Click here"/>
     
	</form>
    <table border="1" cellpadding="5">
        <caption style="display: inline;" > <h2 style="display: inline;">List of Client Responses</h2></caption>
        <table border="1">
            <tr>
                <th>Contractor Response ID</th>
                <th>Contractor ID</th>
                <th>ClientResponse ID</th>           
                <th>Response Date</th>
                <th>Status</th>
                <th>Note</th>
                <th>ModifiedPrice</th>
                <th>ModifiedWorkPeriodFrom</th>
                <th>ModifiedWorkPeriodTo</th>
               
            </tr>
            <c:forEach var="response" items="${listClientResponses}">
                <tr>
                    <td><c:out value="${response.contractorResponseID}" /></td>
                    <td><c:out value="${response.contractorID}" /></td>
                    <td><c:out value="${response.clientResponseID}" /></td>                    
                    <td><c:out value="${response.responseDate}" /></td>
                    <td><c:out value="${response.status}" /></td>
                    <td><c:out value="${response.note}" /></td>
                    <td><c:out value="${response.modifiedPrice}" /></td>
                    <td><c:out value="${response.modifiedWorkPeriodFrom}" /></td>
                    <td><c:out value="${response.modifiedWorkPeriodTo}" /></td>
                
                   
                   
                </tr>
            </c:forEach>
        </table>
  
</body>
</html>
