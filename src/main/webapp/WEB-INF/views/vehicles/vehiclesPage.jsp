<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Vehicles</title>

    <%@include file="/WEB-INF/views/navbar.html" %>
    <%@include file="/WEB-INF/views/includeStyles.jsp" %>
    <link rel="stylesheet" type="text/css" href="resources/css/vehicles.css"/>
</head>
<body>
<a href="/getSaveVehiclePage" class="btn btn-outline-primary waves-effect"><strong>Add new vehicle</strong></a>
<hr>
<table class="table table-striped table-bordered tableView vehicles">
    <thead>
    <tr>
        <th>Model</th>
        <th>Issue date</th>
        <th>Registration number</th>
        <th>Capacity (kg)</th>
        <th>Status</th>
        <th>Actions</th>
        <th>Drivers names</th>
        <th>Drivers license numbers</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${vehicles}" var="vehicle">
        <tr>
            <td>${vehicle.model}</td>
            <td>${vehicle.issueDate}</td>
            <td>${vehicle.registrationNumber}</td>
            <td>${vehicle.capacity}</td>
            <td>${vehicle.status}</td>
            <td>
                <c:if test="${vehicle.primaryDriver == null || vehicle.coDriver == null}">
                    <a href="/editVehicle/${vehicle.id}" class="btn btn-sm btn-outline-info waves-effect"
                       style="font-size: small">Edit</a>
                </c:if>
                <c:if test="${vehicle.status != 'BROKEN' && vehicle.status != 'WORKING'
                && (vehicle.primaryDriver != null || vehicle.coDriver != null) && !empty vehicle.loads}">
                    <a href="/sent/${vehicle.id}" class="btn btn-sm btn-outline-success waves-effect"
                       style="font-size: small">Begin delivery</a>
                </c:if>
            </td>
            <td>
                    ${vehicle.primaryDriver.user.firstName} ${vehicle.primaryDriver.user.lastName}
                <p>${vehicle.coDriver.user.firstName} ${vehicle.coDriver.user.lastName}</p>
            </td>
            <td>
                    ${vehicle.primaryDriver.drivingLicenseNum}
                <p>${vehicle.coDriver.drivingLicenseNum}</p>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
