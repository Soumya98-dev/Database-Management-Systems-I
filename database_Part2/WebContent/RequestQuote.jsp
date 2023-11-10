<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Request a Quote</title>
</head>
<body>
<div align="center">
    <h1>Request a Quote</h1>
    <form action="RequestQuote" method="post">
        <table border="1" cellpadding="5">
        
             <tr>
                    <th>ClientID:</th>
                    <td align="center" colspan="3">
                        <input type="number" name="ClientID" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
        
            <tr>
                    <th>RequestDate:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="RequestDate" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
           
            <tr>
                    <th>Status:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="Status" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
           
           <tr>
                    <th>Note:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="Note" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
            <tr>
                    <th>Size:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="Size" size="45"  onfocus="this.value=''" />                        
                    </td>
             </tr>
             <tr>
                    <th>Height:</th>
                    <td align="center" colspan="3">
                        <input type="number" name="Height" size="45"  onfocus="this.value=''" />                        
                    </td>
            </tr>
            
            <tr>
                    <th>Location:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="Location" size="45"  onfocus="this.value=''" />                        
                    </td>
               </tr>
               <tr>
                    <th>Proximity To House:</th>
                    <td align="center" colspan="3">
                        <input type="number" name="Proximity" size="45"  onfocus="this.value=''" />                        
                    </td>
               </tr>
              
            
             <tr>
                    <td align="center" colspan="5">
                        <input type="submit" value="RequestQuote" />
                    </td>
             </tr>
        </table>
    </form>
    </div>
</body>
</html>
