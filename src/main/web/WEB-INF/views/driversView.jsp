<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Drivers</title>
</head>
<body>
    <div align="left">
        <table border="1" cellpadding="5" bgcolor="#f5f5dc">
            <caption><h>Drivers</h></caption>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Driver's license number </th>
                <th>License end date</th>
                <th>Driver status</th>
                <th>Current location</th>
            </tr>
            <c:forEach var="driver" items="${drivers}">
                <td>${driver.firstName}</td>
                <td>${driver.lastName}</td>
                <td>${driver.drivingLicenseNum}</td>
                <td>${driver.licenseEndDate}</td>
                <td>${driver.driverStatus}</td>
                <td>${driver.currentCity}</td>
            </c:forEach>
        </table>
    </div>
    <form method = "GET" action = "/addNewDriver">
        <input type = "submit" value = "Add new driver "/>
    </form>
    </body>
</html>