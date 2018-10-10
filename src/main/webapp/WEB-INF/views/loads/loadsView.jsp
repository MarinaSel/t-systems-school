<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Loads</title>
</head>
<body>
<table border="2">
    <tr>
        <th>Weight</th>
        <th>Load status</th>
        <th>Vehicle registration number</th>
        <th>Edit</th>
        <th>Drivers' names</th>
        <th>Driving licenses numbers</th>
    </tr>

    <c:forEach items="${loads}" var="load">
        <tr>
            <td>${load.weight}</td>
            <td>${load.status}</td>
            <td>${load.vehicle.registrationNumber}</td>

            <td><a href="/editLoad/${load.id}">Edit</a></td>

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
        </tr>
    </c:forEach>
</table>

<p><a href="/getAddLoadPage">Add new load</a></p>
</body>
</html>
