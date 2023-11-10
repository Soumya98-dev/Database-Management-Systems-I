<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <div align="center">
        <p>${errorOne}</p>
        <p>${errorTwo}</p>
        <form action="register" method="post">
            <table border="1" cellpadding="5">
                <tr>
                    <th>First name:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="FirstName" size="45"  onfocus="this.value=''" />                        
                    </td>
                </tr>
                
                <tr>
                    <th>Last name:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="LastName" size="45"  onfocus="this.value=''" />                        
                    </td>
                </tr>
                <tr>
                    <th>Password:</th>
                    <td align="center" colspan="3">                        
                        <input type="password" name="Password" size="45" onfocus="this.value=''" />                        
                    </td>
                </tr>
                <tr>
                    <th>Password Confirmation:</th>
                    <td align="center" colspan="3">
                       <input type="password" name="confirmation" size="45" onfocus="this.value=''" />
                    </td>
                </tr>
                  <tr>
                    <th>Email:</th>
                    <td align="center" colspan="3">
                       <input type="email" name="Email" size="45" onfocus="this.value=''" />
                    </td>
                </tr>
                <tr>
                    <th>Role:</th>
                    <td align="center" colspan="3">
                        <select name="Role" style="width: 100%;">
                            <option value="Client">Client</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="5">
                        <input type="submit" value="Register" />
                    </td>
                </tr>
            </table>
            <a href="login.jsp" target="_self">Return to Login Page</a>
        </form>
    </div>
</body>
</html>
