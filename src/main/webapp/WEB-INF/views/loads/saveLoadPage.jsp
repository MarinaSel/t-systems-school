<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </td>
        </tr>

        <tr>
            <th align="left">Title</th>
            <td>
                <input name="title" value="${editableLoad.title}" placeholder="Title"
                       maxlength="255" autocomplete="off" required/>
            </td>
        </tr>

        <tr>
            <th align="left">Description</th>
            <td>
                <input name="description" value="${editableLoad.description}" placeholder="Description"
                       maxlength="255" autocomplete="off" required/>
            </td>
        </tr>

        <tr>
            <th align="left">Day of delivery</th>
            <td>
                <input name="dayOfDelivery" id="date" value="${editableLoad.dayOfDelivery}"
                       placeholder="Day of delivery" required readonly/>
            </td>
        </tr>
        <tr>
            <th align="left">Weight (kg)</th>
            <td>
                <input id="vehicleWeight" type="number" name="weight" value="${editableLoad.weight}"
                       oninput="getVehicles()" min="1" max="22000" placeholder="Weight" autocomplete="off" required/>
            </td>
        </tr>

        <tr>
            <th align="left">Vehicle registration number</th>
            <td>
                <select class="select" id="vehicles" name="regNum" onchange="getDrivers()">
                    <c:if test="${editableLoad.vehicle.registrationNumber != null}">
                        <option>${editableLoad.vehicle.registrationNumber}</option>
                    </c:if>
                    <c:forEach items="${freeVehicles}" var="vehicle">
                        <c:if test="${vehicle.registrationNumber != editableLoad.vehicle.registrationNumber}">
                            <option>${vehicle.registrationNumber}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <th align="left">Primary driver</th>
            <td>
                <select class="select" id="primaryDriver" name="primaryDriverLicense">
                    <option>${editableLoad.vehicle.primaryDriver.drivingLicenseNum == null ? ""
                            : editableLoad.vehicle.primaryDriver.drivingLicenseNum}</option>
                    <c:forEach items="${freeDrivers}" var="driver">
                        <c:if test="${driver.drivingLicenseNum != editableLoad.vehicle.primaryDriver.drivingLicenseNum
                        && driver.drivingLicenseNum != editableLoad.vehicle.coDriver.drivingLicenseNum}">
                            <option>${driver.drivingLicenseNum}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th align="left">Co-driver</th>
            <td>
                <select class="select" id="secondDriver" name="coDriverLicense">
                    <option>${editableLoad.vehicle.coDriver.drivingLicenseNum == null ? ""
                            : editableLoad.vehicle.coDriver.drivingLicenseNum}</option>
                    <c:forEach items="${freeDrivers}" var="driver">
                        <c:if test="${driver.drivingLicenseNum != editableLoad.vehicle.primaryDriver.drivingLicenseNum
                        && driver.drivingLicenseNum != editableLoad.vehicle.coDriver.drivingLicenseNum}">
                            <option>${driver.drivingLicenseNum}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input class="btn btn-success" type="submit" value="Save"/>
            </td>
        </tr>
    </table>
</form>
</body>
<script src="resources/js/getDriversAndVehicles.js"></script>
</html>
