<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>${empty editableVehicle.id ? 'Add' : 'Edit'} vehicle</title>
    <%@include file="/WEB-INF/views/includeStyles.jsp"%>
    <%@include file="/WEB-INF/views/navbar.html"%>
</head>
<body>
<h1 align="center">${empty editableVehicle.id ? 'Add' : 'Edit'} vehicle</h1>
<hr>
<form method="post" action="/saveVehicle" modelAttribute = "editableVehicle">
    <table align="center"id="saveVehicleAndDriverTable" class="table table-bordered table-striped ">
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
                <input name="registrationNumber" value="${editableVehicle.registrationNumber}" placeholder="Enter the registration number" maxlength="7" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Capacity</th>
            <td>
                <input type="number" name="capacity" value="${editableVehicle.capacity}" placeholder="Enter the capacity" required/>
            </td>
        </tr>

        <c:if test="${!empty editableVehicle.status}">
            <tr>
                <th align="left">Status</th>
                <td>
                    <select name="status" class="soflow" onchange="getDriversAndVehicles();" required>
                        <option value="" selected disabled hidden>${editableVehicle.status}</option>
                        <option>BROKEN</option>
                        <option>WORKING</option>
                        <option>FREE</option>
                    </select>
                </td>
            </tr>
        </c:if>

        <td colspan="2">
            <input class="btn btn-success" type="submit" value="Save"/>
        </td>
    </table>
</form>
</body>

</html>