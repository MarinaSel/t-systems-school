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
<form method="post" action="/addVehicle" modelAttribute = "editableVehicle">
    <table>
        <tr>
            <td>
                <input type="hidden" name="id" value="${editableVehicle.id}"/>
            </td>
            <td>
                <input type="hidden" name="creationDate" value="${editableVehicle.creationDate}">
            </td>
        </tr>
        <tr>
            <th align="left">Registration number</th>
            <td>
                <input name="registrationNumber" value="${editableVehicle.registrationNumber}" maxlength="7" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Capacity</th>
            <td>
                <input type="number" name="capacity" value="${editableVehicle.capacity}" required/>
            </td>
        </tr>
        <c:if test="${!empty editableVehicle.status}">
            <tr>
                <th align="left">Status</th>
                <td>
                    <select name="status" required>
                        <option value="" selected disabled hidden>${editableVehicle.status}</option>
                        <option>UNWORKING</option>
                        <option>WORKING</option>
                    </select>
                </td>
            </tr>
        </c:if>

        <td>
            <input type="submit" value="Save"/>
        </td>
    </table>
    <p><a href="homePage">Home page</a></p>
</form>
</body>
</html>