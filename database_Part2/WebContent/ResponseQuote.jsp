<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Response a Quote</title>
</head>
<body>
<div align="center">
    <h1>Response a Quote</h1>
    <form action="ResponseQuote" method="post">
        <table border="1" cellpadding="5">
        
            <tr>
                    <th>RequestID:</th>
                    <td align="center" colspan="3">
                        <input type="number" name="RequestID" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
        
             <tr>
                    <th>ClientID:</th>
                    <td align="center" colspan="3">
                        <input type="number" name="ClientID" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
        
            <tr>
                    <th>ResponseDate:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="ResponseDate" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
           
            <tr>
                    <th>Price:</th>
                    <td align="center" colspan="3">
                        <input type="number" name="Price" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
             
             <tr>
                    <th>WorkPeriodFrom:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="WorkPeriodFrom" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
             
             <tr>
                    <th>WorkPeriodTo:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="WorkPeriodTo" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
           
           
           <tr>
                    <th>Note:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="Note" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
            
            
             <tr>
                    <td align="center" colspan="5">
                        <input type="submit" value="ResponseQuote" />
                    </td>
             </tr>
        </table>
    </form>
    </div>
</body>
</html>
