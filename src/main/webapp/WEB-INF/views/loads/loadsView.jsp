<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/tableFixed.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/tableFixedLoads.css"/>

    <%@include file="/WEB-INF/views/navbar.html"%>
    <title>Loads</title>
</head>

<body>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Day of delivery</th>
            <th>Weight</th>
            <th>Load status</th>
            <th>Actions</th>
            <th>Vehicle registration number</th>
            <th>Vehicle status</th>
            <th>Drivers names</th>
            <th>Driving licenses numbers</th>
            <th>Drivers status</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${loads}" var="load">
            <tr>
                <td>${load.title}</td>
                <td>${load.description}</td>
                <td>${load.dayOfDelivery}</td>
                <td>${load.weight}</td>
                <td>${load.status}</td>

                <td>
                    <c:if test="${load.status != 'DONE'}">
                        <a href="/delivered/${load.id}" class="btn btn-success btn-sm">Delivered</a>
                        <a href="/editLoad/${load.id}" class="btn btn-info btn-sm">Edit</a>
                    </c:if>
                </td>

                <td>${load.vehicle.registrationNumber}</td>
                <td>${load.vehicle.status}</td>

                <td>
                    <c:forEach items="${load.vehicle.drivers}" var="driver">
                        <p>${driver.firstName} ${driver.lastName}</p>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${load.vehicle.drivers}" var="driver">
                        <p>${driver.drivingLicenseNum}</p>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${load.vehicle.drivers}" var="driver">
                        <p>${driver.status}</p>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>

        <td colspan="11">
            <a href="/getSaveLoadPage" class="btn btn-success">Add new load</a>
        </td>
        </tbody>

    </table>
</body>
</html>
