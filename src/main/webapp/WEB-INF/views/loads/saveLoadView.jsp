<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <%@include file="/WEB-INF/views/navbar.html" %>

    <title>${empty editableLoad.id ? 'Add' : 'Edit'} load</title>
</head>

<body>
<h1 align="center">${empty editableLoad.id ? 'Add' : 'Edit'} load</h1>
<hr>
<form method="post" action="/saveLoad" modelAttribute="editableLoad">
    <table align="center" id="table" class="table table-striped table-bordered table-width">
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
                <input name="title" value="${editableLoad.title}"/>
            </td>
        </tr>

        <tr>
            <th align="left">Description</th>
            <td>
                <input name="description" value="${editableLoad.description}"/>
            </td>
        </tr>

        <tr>
            <th align="left">Day of delivery</th>
            <td>
                <input name="dayOfDelivery" id="dayOfDelivery" value="${editableLoad.dayOfDelivery}"/>
            </td>
        </tr>
        <tr>
            <th align="left">Weight</th>
            <td>
                <input id="vehicleWeight" type="number" name="weight" value="${editableLoad.weight}"
                       oninput="getVehicle()" required/>
            </td>
        </tr>


        <tr>
            <th align="left">Vehicles registration numbers</th>
            <td>
                <select class="select" id="vehicles" name="regNum" onchange="showDrivers();">
                    <option value="${editableLoad.vehicle.registrationNumber}"
                            selected>${editableLoad.vehicle.registrationNumber}</option>
                    <c:if test="${!empty editableLoad.id}">
                        <c:forEach items="${freeVehicles}" var="vehicle">
                            <option>${vehicle.registrationNumber}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </td>
        </tr>

        <tr>
            <th align="left">Primary driver</th>
            <td>
                <select id="primaryDriver" class="select" name="drivingLicenseNumPrimary" disabled="disabled" required>
                    <option value="" selected disabled hidden></option>
                    <c:if test="${!empty editableLoad.vehicle}">
                        <c:forEach items="${freeDrivers}" var="driver">
                            <option>${driver.drivingLicenseNum}</option>
                        </c:forEach>
                    </c:if>

                </select>
            </td>
        </tr>
        <tr>
            <th align="left">Second driver</th>
            <td>
                <select class="select" id="secondDriver" name="drivingLicenseNumSecond" disabled="disabled" required>
                    <option value="" selected disabled hidden></option>
                    <option></option>
                    <c:forEach items="${freeDrivers}" var="driver">
                        <option>${driver.drivingLicenseNum}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <td colspan="2">
            <input class="btn btn-success" type="submit" value="Save"/>
        </td>
    </table>
</form>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="resources/js/listBox.js"></script>
<script src="resources/js/datepicker.js"></script>
<script src="resources/js/getVehicles.js"></script>
<script src="resources/js/getDrivers.js"></script>
</html>
