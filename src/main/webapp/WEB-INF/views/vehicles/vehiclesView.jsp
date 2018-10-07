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
</head>
<body>
<table border="2">
    <tr>
        <th>ID</th>
        <th>Registration number</th>
        <th>Capacity</th>
        <th>Status</th>

        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${vehicle}" var="vehicle">
        <tr>
            <td>${vehicle.id}</td>
            <td>${vehicle.registrationNumber}</td>
            <td>${vehicle.capacity}</td>
            <td>${vehicle.status}</td>

            <td><a href="/edit/${driver.id}">Edit</a></td>
            <td><a href="/removeVehicle/${vehicle.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="addVehicle">Add new vehicle</a></p>
<p><a href="homePage">Home page</a></p>
</body>
</html>
