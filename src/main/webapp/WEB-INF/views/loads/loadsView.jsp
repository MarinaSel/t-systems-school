<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/WEB-INF/views/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/navbar.html" %>
    <link rel="stylesheet" type="text/css" href="resources/css/loads.css"/>

    <title>Loads</title>
</head>

<body>
<a href="/getSaveLoadPage" class="btn btn-success">Add new load</a>
<hr>
<table class="table table-striped table-bordered tableView loads">
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Day of delivery</th>
        <th>Weight (kg)</th>
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
                    <c:if test="${load.status != 'IN_PROGRESS'}">
                        <a href="/editLoad/${load.id}" class="btn btn-info btn-sm">Edit</a>
                    </c:if>
                    <c:if test="${load.status == 'IN_PROGRESS'}">
                        <a href="/delivered/${load.id}" class="btn btn-success btn-sm">Delivered</a>
                    </c:if>
                </c:if>
            </td>

            <td>${load.vehicle.registrationNumber}</td>
            <td>${load.vehicle.status}</td>

            <td>
                <c:forEach items="${load.vehicle.drivers}" var="driver">
                    <p>${driver.user.firstName} ${driver.user.lastName}</p>
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


    </tbody>

</table>
</body>
</html>
