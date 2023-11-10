<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Client Response a Quote Response</title>
</head>
<body>
<div align="center">
    <h1>Client Response a Quote Response</h1>
    <form action="ClientResponse" method="post">
        <table border="1" cellpadding="5">
        
                    
            <tr>
                <th>ContractorID:</th>
                <td align="center" colspan="3">
                    <input type="number" name="ContractorID" size="45"  onfocus="this.value=''" />                        
                </td>
            </tr>
        
            <tr>
                <th>ResponseID:</th>
                <td align="center" colspan="3">
                    <input type="number" name="ResponseID" size="45"  onfocus="this.value=''" />                        
                </td>
            </tr>
            
            <tr>
                <th>ResponseDate:</th>
                <td align="center" colspan="3">
                    <input type="text" name="ResponseDate" size="45"  onfocus="this.value=''" />                        
                </td>
            </tr>
           
            <tr>
                <th>Status:</th>
                <td align="center" colspan="3">
                    <select name="Status" style="width: 100%;">
                        <option value="Accepted">Accepted</option>
                        <option value="Rejected">Rejected</option>
                        <option value="Pending">Pending</option>
                        <option value="RequestRevision">Request Revision</option>
                    </select>
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
                    <input type="submit" value="ClientResponse" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
