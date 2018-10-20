<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vehicles</title>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <%@include file="/WEB-INF/views/navbar.html"%>
</head>
<body>
<table class="table table-striped table-bordered" >
    <thead>
    <tr>
        <th>Registration number</th>
        <th>Capacity</th>
        <th>Status</th>
        <th>Actions</th>
        <th>Drivers names</th>
        <th>Drivers license numbers</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${vehicles}" var="vehicle">
        <tr>
            <td>${vehicle.registrationNumber}</td>
            <td>${vehicle.capacity}</td>
            <td>${vehicle.status}</td>

            <td>
                <a href="/editVehicle/${vehicle.id}" class="btn btn-info btn-sm">Edit</a>
                <c:if test="${vehicle.status != 'BROKEN' && vehicle.status != 'WORKING'}">
                    <a href="/sent/${vehicle.id}" class="btn btn-success btn-sm">Begin delivery</a>
                </c:if>
            </td>
            <td><c:forEach items="${vehicle.drivers}" var="driver">
                <p>${driver.firstName} ${driver.lastName}</p>
            </c:forEach>
            </td>
            <td>
                <c:forEach items="${vehicle.drivers}" var="driver">
                    <p>${driver.drivingLicenseNum}</p>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
