<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add Driver</title>
</head>
<body>
    <form action="/addDriver" method="post">
        <table border="1" cellpadding="5" bgcolor="#f5f5dc" align="left">
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Driver's license number </th>
                <th>License end date</th>
                <th>Driver status</th>
                <th>Current location</th>
            </tr>
            <td><input type="text" name="firstname" value=${newDriver.firstName}></td>
            <td><input type="text" name="lastname" value=${newDriver.lastName}></td>
            <td><input type="text" name="drivingLicenseNum" value=${newDriver.drivingLicenseNum}></td>
            <td><input type="text" name="licenseEndDate" value=${newDriver.licenseEndDate}></td>
            <td><input type="text" name="driverStatus" value=${newDriver.driverStatus}></td>
            <td><input type="text" name="currentCity" value=${newDriver.currentCity}></td>
            <th colspan="2" align="center"><input type="submit" value="Add"></th>
        </table>
    </form>
</body>
</html>