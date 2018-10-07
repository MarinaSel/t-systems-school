<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add new vehicle</title>
</head>
<body>
<h1>Add new vehicle</h1>
<form method="post" action="/addVehicle" modelAttribute = "newVehicle">
    <table>
        <tr>
            <th align="left">Registration number</th>
            <td>
                <input name="registrationNumber" maxlength="7" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Capacity</th>
            <td>
                <input type="number" name="capacity" required/>
            </td>
        </tr>
        <td>
            <input type="submit" value="Save"/>
        </td>
    </table>
    <p><a href="homePage">Home page</a></p>
</form>
</body>
</html>