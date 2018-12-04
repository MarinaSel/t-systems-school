<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Vehicles</title>
    <%@include file="/WEB-INF/views/navbar.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeTablePag.jsp" %>

</head
<body class="body">
<a href="/vehicle/addVehicle" class="btn btn-outline-primary waves-effect"><strong>Add new vehicle</strong></a>
<hr>
<table id="tableScroll" class="table table-striped table-bordered" width="100%">
    <thead>
    <tr>
        <th class="th-sm textTableHeader">Model</th>
        <th class="th-sm textTableHeader">Issue date</th>
        <th class="th-sm textTableHeader">Registration number</th>
        <th class="th-sm textTableHeader">Capacity (kg)</th>
        <th class="th-sm textTableHeader">Status</th>
        <th class="th-sm textTableHeader">Actions</th>
        <th class="th-sm textTableHeader">Drivers names</th>
        <th class="th-sm textTableHeader">Drivers license numbers</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${vehicles}" var="vehicle">
        <tr>
            <td>${vehicle.model}</td>
            <td align="center">${vehicle.issueDate}</td>
            <td>${vehicle.registrationNumber}</td>
            <td>${vehicle.capacity}</td>
            <td>${vehicle.status}</td>
            <td align="center">
                <c:if test="${empty vehicle.primaryDriver}">
                    <a href="/vehicle/editVehicle/${vehicle.id}" class="btn btn-sm btn-outline-info waves-effect"
                       style="font-size: small">Edit</a>
                </c:if>
                <c:if test="${vehicle.status == 'BROKEN'}">
                    <a href="/vehicle/repairedVehicle/${vehicle.id}" class="btn btn-sm btn-outline-info waves-effect"
                       style="font-size: small">Repaired</a>
                </c:if>
                <c:if test="${vehicle.status == 'FREE'}">
                    <a href="/vehicle/brokenVehicle/${vehicle.id}" class="btn btn-sm btn-outline-danger waves-effect"
                       style="font-size: small">Broken</a>
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
