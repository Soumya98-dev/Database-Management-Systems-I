<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Clients</h2></caption>
            <tr>
                <th>ClientID</th>                
                <th>FirstName</th>
                <th>LastName</th>
                <th>Password</th>
                <th>Address</th>
                <th>CreditCardInfo</th>
                <th>PhoneNumber</th>
                <th>Email</th>

            </tr>
            <c:forEach var="clients" items="${listClient}">
                <tr style="text-align:center">
                    <td><c:out value="${clients.clientID}" /></td>                   
                    <td><c:out value="${clients.firstName}" /></td>
                    <td><c:out value="${clients.lastName}" /></td>
                     <td><c:out value="${clients.password}" /></td>
                    <td><c:out value="${clients.address}" /></td>
                    <td><c:out value="${clients.creditCardInfo}"/></td>
                    <td><c:out value="${clients.phoneNumber}" /></td>
                    <td><c:out value="${clients.email}" /></td>
                </tr>
            </c:forEach>
        </table>
       </div>
        
  <h1>List all administrators</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of administrators</h2></caption>
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
        </table>
	
	</div>
	
	 <h1>List all Contractors</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of contractors</h2></caption>
            <tr>
                <th>ContractorID</th> 
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>

            </tr>
            <c:forEach var="contractor" items="${listContractor}">
                <tr style="text-align:center">
                    <td><c:out value="${contractor.contractorID}" /></td>  
                    <td><c:out value="${contractor.username}" /></td>
                    <td><c:out value="${contractor.password}" /></td>
                    <td><c:out value="${contractor.email}" /></td>
                    
               
                </tr>
            </c:forEach>
        </table>
	
	</div>
</div>
</body>
</html>