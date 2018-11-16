<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%@include file="/WEB-INF/views/navbarForDriver.html" %>
    <%@include file="/WEB-INF/views/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/includeListBox.jsp" %>
    <title>Driver</title>
</head>

<body class="body">
<hr>
<div id="container">
    <div class="text driverContent">
        <h3 align="center">Your data</h3>
        <hr>
        Name: ${driver.user.firstName} ${driver.user.lastName}
        <br>Login: ${driver.user.login}
        <br>Driver's license number: ${driver.drivingLicenseNum}
        <br>Status: ${driver.status}
        <br>License end date: ${driver.licenseEndDate}
    </div>

    <div class="actionsContent">
        <h3 align="center">Actions</h3>
        <hr style="width: 30%" align="center">

    </div>

    <div class="text vehicleContent">
        <h3 align="center">Your vehicle</h3>
        <hr>
        Model: ${vehicle.model}
        <br>Registration number: ${vehicle.registrationNumber}
        <br>Capacity: ${vehicle.capacity}
        <br>Status: ${vehicle.status}
    </div>
</div>

<table class="table table-striped table-bordered" width="100%">
    <thead>
    <th colspan="6"><h3 align="center">Loads</h3></th>
    <tr>
        <th class="textTableHeader">Title</th>
        <th class="textTableHeader">Description</th>
        <th class="textTableHeader">Day of delivery</th>
        <th class="textTableHeader">Weight (kg)</th>
        <th class="textTableHeader">Status</th>
        <th class="textTableHeader">Action</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${vehicle.loads}" var="load">
        <tr>
            <td>${load.title}</td>
            <td>${load.description}</td>
            <td align="center">${load.dayOfDelivery}</td>
            <td align="center">${load.weight}</td>
            <td align="center">${load.status}</td>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
