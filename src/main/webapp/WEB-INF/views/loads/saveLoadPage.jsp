<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%@include file="/WEB-INF/views/navbar.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeListBox.jsp" %>
    <%@include file="/WEB-INF/views/loads/includeScriptsForLoadSaving.jsp" %>
    <title>${empty editableLoad.id ? 'Add' : 'Edit'} load</title>
</head>

<body>
<hr>
<form onsubmit="return compare()" method="post" action="/load/saveLoad" modelAttribute="editableLoad">
    <input type="hidden" name="id" value="${editableLoad.id}"/>
    <input type="hidden" name="creationDate" value="${editableLoad.creationDate}">
    <input type="hidden" name="status" value="${editableLoad.status}">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <table align="center" id="saveLoadTable" class="table table-bordered table-width">
        <td align="center" colspan="2" style="font-size: xx-large">
            ${empty editableLoad.id ? 'Add' : 'Edit'} load
        </td>
        <tr>
            <th align="left">Title</th>
            <td>
                <input class="form-control" name="title" value="${editableLoad.title}" placeholder="Title"
                       maxlength="255" autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Description</th>
            <td>
                <input class="form-control" name="description" value="${editableLoad.description}"
                       placeholder="Description"
                       maxlength="255" autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Pick up location</th>
            <td>
                <select class="select" id="pickUpLocation" name="pickUpLocationId" required>
                    <option value="${editableLoad.pickUpLocation.id}">${editableLoad.pickUpLocation.name == null ? ""
                            : editableLoad.pickUpLocation.name}</option>
                    <c:forEach items="${locations}" var="location">
                        <c:if test="${location.name != editableLoad.pickUpLocation.name
                        && location.name != editableLoad.pickUpLocation.name}">
                            <option value="${location.id}">${location.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th align="left">Delivery location</th>
            <td>
                <select class="select custom-select" id="deliveryLocation" name="deliveryLocationId" required>
                    <option value="${editableLoad.deliveryLocation.id}">${editableLoad.deliveryLocation.name == null ? ""
                            : editableLoad.deliveryLocation.name}</option>
                    <c:forEach items="${locations}" var="location">
                        <c:if test="${location.name != editableLoad.deliveryLocation.name
                        && location.name != editableLoad.deliveryLocation.name}">
                            <option value="${location.id}">${location.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th align="left">Weight (kg)</th>
            <td>
                <input class="form-control" id="vehicleWeight" type="number" name="weight"
                       value="${editableLoad.weight}"
                       oninput="getVehicles()" min="1" max="22000" placeholder="Weight" autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Vehicle registration number</th>
            <td>
                <select class="select custom-select" id="vehicles" name="regNum" onchange="getDrivers()">
                    <c:if test="${editableLoad.vehicle.registrationNumber != null}">
                        <option>${editableLoad.vehicle.registrationNumber}</option>
                    </c:if>
                </select>
            </td>
        </tr>
        <tr>
            <th align="left">Primary driver</th>
            <td>
                <select class="select custom-select" id="primaryDriver" name="primaryDriverLicense">
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
                <select class="select custom-select" id="secondDriver" name="coDriverLicense">
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
            <td colspan="2" align="center">
                <input class="btn btn-outline-success waves-effect" style="font-size: medium" type="submit"
                       value="Save"/>
            </td>
        </tr>
    </table>
</form>
</body>
<script src="/resources/js/getDriversAndVehicles.js"></script>
</html>
