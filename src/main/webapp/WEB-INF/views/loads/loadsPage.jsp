<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/WEB-INF/views/navbar.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeTablePag.jsp" %>
    <title>Loads</title>
</head>
<body class="body">
<a href="/load/addLoad" class="btn btn-outline-primary waves-effect"><strong>Add new load</strong></a>
<a href="/load/doneLoads" class="btn btn-outline-success waves-effect"><strong>Done loads</strong></a>
<hr>
<table id="tableScroll" class="table table-striped table-bordered" width="100%">
    <thead>
    <tr>
        <th class="th-sm textTableHeader">Title</th>
        <th class="th-sm textTableHeader">Description</th>
        <th class="th-sm textTableHeader">Day of delivery and history</th>
        <th class="th-sm textTableHeader">Location</th>
        <th class="th-sm textTableHeader">Weight (kg)</th>
        <th class="th-sm textTableHeader">Load status</th>
        <th class="th-sm textTableHeader">Actions</th>
        <th class="th-sm textTableHeader">Vehicle registration number</th>
        <th class="th-sm textTableHeader">Vehicle status</th>
        <th class="th-sm textTableHeader">Drivers</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${loads}" var="load">
        <c:if test="${load.status != 'DONE'}">
            <tr>
                <td>${load.title}</td>
                <td>${load.description}</td>
                <td align="center">
                        ${load.dayOfDelivery}
                    <br>${load.history.vehicle.registrationNumber}
                    <br>${load.history.primaryDriver.user.firstName} ${load.history.primaryDriver.user.lastName}
                        ${load.history.primaryDriver.drivingLicenseNum}
                    <br>${load.history.coDriver.user.firstName} ${load.history.coDriver.user.lastName}
                        ${load.history.coDriver.drivingLicenseNum}
                </td>
                <td>Pick up location: ${load.pickUpLocation.name}
                    <p>Delivery location: ${load.deliveryLocation.name}</p></td>
                <td>${load.weight}</td>
                <td>${load.status}</td>
                <td align="center">
                    <c:if test="${load.status != 'DONE' && load.status != 'IN_PROGRESS'}">
                        <a href="/load/editLoad/${load.id}" class="btn btn-sm btn-outline-info waves-effect"
                           style="font-size: small">Edit</a>
                    </c:if>
                </td>
                <td>${load.vehicle.registrationNumber}</td>
                <td>${load.vehicle.status}</td>
                <td>
                        ${load.vehicle.primaryDriver.user.firstName} ${load.vehicle.primaryDriver.user.lastName}
                    <br>${load.vehicle.primaryDriver.drivingLicenseNum}
                    <p>${load.vehicle.primaryDriver.status}</p>
                    <br>${load.vehicle.coDriver.user.firstName} ${load.vehicle.coDriver.user.lastName}
                    <br>${load.vehicle.coDriver.drivingLicenseNum}
                    <br>${load.vehicle.coDriver.status}
                    <p></p>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
