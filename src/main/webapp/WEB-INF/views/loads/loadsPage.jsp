<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/WEB-INF/views/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/navbar.html" %>
    <%@include file="/WEB-INF/views/includeTablePag.jsp" %>
    <title>Loads</title>
</head>
<body class="body">
<a href="/getSaveLoadPage" class="btn btn-outline-primary waves-effect"><strong>Add new load</strong></a>
<hr>
<table id="tableScroll" class="table table-striped table-bordered" width="100%">
    <thead>
    <tr>
        <th class="th-sm">Title</th>
        <th class="th-sm">Description</th>
        <th class="th-sm">Day of delivery</th>
        <th class="th-sm">Weight (kg)</th>
        <th class="th-sm">Load status</th>
        <th class="th-sm">Actions</th>
        <th class="th-sm">Vehicle registration number</th>
        <th class="th-sm">Vehicle status</th>
        <th class="th-sm">Drivers names</th>
        <th class="th-sm">Driving licenses numbers</th>
        <th class="th-sm">Drivers statuses</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${loads}" var="load">
        <tr>
            <td>${load.title}</td>
            <td>${load.description}</td>
            <td align="center">${load.dayOfDelivery}</td>
            <td>${load.weight}</td>
            <td>${load.status}</td>
            <td align="center">
                <c:if test="${load.status != 'DONE'}">
                    <c:if test="${load.status != 'IN_PROGRESS'}">
                        <a href="/editLoad/${load.id}" class="btn btn-sm btn-outline-info waves-effect"
                           style="font-size: small">Edit</a>
                    </c:if>
                    <c:if test="${load.status == 'IN_PROGRESS'}">
                        <a href="/delivered/${load.id}"
                           class="btn btn-sm btn-outline-success waves-effect" style="font-size: small">Delivered</a>
                    </c:if>
                </c:if>
            </td>
            <td>${load.vehicle.registrationNumber}</td>
            <td>${load.vehicle.status}</td>
            <td>
                    ${load.vehicle.primaryDriver.user.firstName} ${load.vehicle.primaryDriver.user.lastName}
                <p>${load.vehicle.coDriver.user.firstName} ${load.vehicle.coDriver.user.lastName}</p>
            </td>
            <td>
                    ${load.vehicle.primaryDriver.drivingLicenseNum}
                <p>${load.vehicle.coDriver.drivingLicenseNum}</p>
            </td>
            <td>
                    ${load.vehicle.primaryDriver.status}
                <p>${load.vehicle.coDriver.status}</p>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
