<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>${empty editableVehicle.id ? 'Add' : 'Edit'} vehicle</title>
</head>
<body>
<h1>${empty editableVehicle.id ? 'Add' : 'Edit'} vehicle</h1>
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
                        <option>BROKEN</option>
                        <option>WORKING</option>
                        <option>FREE</option>
                    </select>
                </td>
            </tr>
        </c:if>

        <td>
            <input type="submit" value="Save"/>
        </td>
    </table>
    <p><a href="/getAddVehicleView">Home page</a></p>
</form>
</body>
</html>