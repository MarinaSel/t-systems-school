<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<html>
<head>
    <title>Edit driver</title>
    <script src="resources/js/dateP.js"></script>
</head>
<body>
<form method="POST" action="/editSave" >
    <table >
        <tr><th align="left">First name</th> <td> <input name="firstName" /></td></tr>
        <tr><th align="left">Second name</th> <td><input name="lastName" /></td></tr>
        <tr><th align="left">Driver's license number</th><td><input name="drivingLicenseNum"/></td></tr>
        <tr><th align="left">License end date</th><td><input name="licenseEndDate" id = "licenseEndDate_id"/></td></tr>
        <tr>
            <input type="submit" value="Edit save" />
        </tr>
    </table>
</form>
</body>
</html>