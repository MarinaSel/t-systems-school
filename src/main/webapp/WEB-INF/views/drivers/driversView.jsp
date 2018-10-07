<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Drivers</title>
</head>
<body>
   <table border="2">
        <tr>
            <th>ID</th>
            <th>First name</th>
            <th>Second name</th>
            <th>Driver's license number</th>
            <th>License end date</th>
            <th>Driver status</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <c:forEach items="${drivers}" var="driver">
            <tr>
                <td>${driver.id}</td>
                <td>${driver.firstName}</td>
                <td>${driver.lastName}</td>
                <td>${driver.drivingLicenseNum}</td>
                <td>${driver.licenseEndDate}</td>
                <td>${driver.status}</td>

                <td><a href="/edit/${driver.id}">Edit</a></td>
                <td><a href="/removeDriver/${driver.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

   <p><a href="/getAddDriverView">Add new driver</a></p>
   <p><a href="homePage">Home page</a></p>
</body>
</html>