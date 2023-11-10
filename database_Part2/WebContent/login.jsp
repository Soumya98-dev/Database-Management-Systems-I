<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login to Database</title>
</head>
<body>
    <center><h1>Welcome to Login page</h1></center>
    <div align="center">
        <p>${loginFailedStr}</p>
        <form action="login" method="post">
            <table border="1" cellpadding="5">
                <tr>
                    <th>Username:</th>
                    <td>
                        <input type="text" name="username" size="45" autofocus>
                    </td>
                </tr>
                <tr>
                    <th>Password:</th>
                    <td>
                        <input type="password" name="password" size="45">
                    </td>
                </tr>
                <tr>
                    <th>Role:</th>
                    <td>
                    
                         <select name="role" style="width: 100%;">
                            <option value="Client">Client</option>
                            <option value="Administrator">Administrator</option>
                            <option value="Contractor">Contractor</option>
                           
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Login">
                    </td>
                </tr>
            </table>
            <a href="register.jsp" target="_self">Register Here</a>
        </form>
    </div>
    
    <c:choose>
        <c:when test="${not empty userRole and userRole eq 'Client'}">
            <p>Welcome, Client! This content is specific to clients.</p>            
        </c:when>
        <c:when test="${not empty userRole and userRole eq 'Administrator'}">
            <p>Welcome, Administrator! This content is specific to administrators.</p>          
        </c:when>
        <c:when test="${not empty userRole and userRole eq 'Contractor'}">
            <p>Welcome, Contractor! This content is specific to contractor.</p>          
        </c:when>
       
    </c:choose>
</body>
</html>
