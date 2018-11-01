<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <%@include file="/WEB-INF/views/navbar.html" %>
    <%@include file="/WEB-INF/views/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/includeDatepicker.jsp" %>
    <%@include file="/WEB-INF/views/includeListBox.jsp" %>
    <title>${empty editableLoad.id ? 'Add' : 'Edit'} load</title>
</head>

<body>
<h1 align="center">${empty editableLoad.id ? 'Add' : 'Edit'} load</h1>
<hr>
<form method="post" action="/saveLoad" modelAttribute="editableLoad">
    <table align="center" id="saveLoadTable" class="table table-striped table-bordered table-width">
        <tr>
            <td>
                <input type="hidden" name="id" value="${editableLoad.id}"/>
            </td>
            <td>
                <input type="hidden" name="creationDate" value="${editableLoad.creationDate}">
            </td>
            <td class="hidden">
                <input type="hidden" name="status" value="${editableLoad.status}">
            </td>
        </tr>

        <tr>
            <th align="left">Title</th>
            <td>
                <input name="title" value="${editableLoad.title}" placeholder="Enter the title"
                       maxlength="255" autocomplete="off" required/>
            </td>
        </tr>

        <tr>
            <th align="left">Description</th>
            <td>
                <input name="description" value="${editableLoad.description}" placeholder="Enter the description"
                       maxlength="255" autocomplete="off" required/>
            </td>
        </tr>

        <tr>
            <th align="left">Day of delivery</th>
            <td>
                <input name="dayOfDelivery" id="date" value="${editableLoad.dayOfDelivery}"
                       placeholder="Choose the day of delivery" required readonly/>
            </td>
        </tr>
        <tr>
            <th align="left">Weight (kg)</th>
            <td>
                <input id="vehicleWeight" type="number" name="weight" value="${editableLoad.weight}" oninput="getVehicle()"
                       min="1" max="22000" placeholder="Enter the weight"
                       autocomplete="off" required/>
            </td>
        </tr>


        <tr>
            <th align="left">Vehicles registration numbers</th>
            <td>
                <option value="${editableLoad.vehicle.registrationNumber}"
                        selected>${editableLoad.vehicle.registrationNumber}</option>
                <select class="select" id="vehicles" name="regNum" onchange="getDrivers();">
                        <c:forEach items="${freeVehicles}" var="vehicle">
                            <option>${vehicle.registrationNumber}</option>
                        </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <th align="left">Primary driver</th>
            <td>
                <select id="primaryDriver" class="select" name="drivingLicenseNumPrimary" disabled="disabled">
                    <option value="" selected disabled hidden></option>
                    <c:forEach items="${freeDrivers}" var="driver">
                        <option value="${driver.drivingLicenseNum}">${driver.firstName}${driver.lastName} </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th align="left">Second driver</th>
            <td>
                <select class="select" id="secondDriver" name="drivingLicenseNumSecond" disabled="disabled">
                    <option value="" selected disabled hidden></option>
                    <option></option>
                    <c:forEach items="${freeDrivers}" var="driver">
                        <option value="${driver.drivingLicenseNum}">${driver.firstName}${driver.lastName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <td colspan="2">
            <input class="btn btn-success" type="submit" value="Save"/>
        </td>
        <td>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </td>
    </table>
</form>
</body>
<script src="resources/js/getDriversAndVehicles.js"></script>
</html>
