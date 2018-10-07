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

    <script src="resources/js/datepicker.js"></script>
</head>

<body>

<form method="post" action="/updatingDriver" modelAttribute = "editableDriver">
    <table>
        <tr>
            <td>
                <input type="hidden" name="id" value="${editableDriver.id}" maxlength="50" required/>
            </td>
        </tr>
        <tr>
            <th align="left">First name</th>
            <td>
                <input name="firstName" value="${editableDriver.firstName}" maxlength="50" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Second name</th>
            <td>
                <input name="lastName" value="${editableDriver.lastName}" maxlength="50" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Driver's license number</th>
            <td>
                <input name="drivingLicenseNum" value="${editableDriver.drivingLicenseNum}" required/>
            </td>
        </tr>
        <tr>
            <th align="left">License end date</th>
            <td>
                <input type="date"  name="licenseEndDate" value="${editableDriver.licenseEndDate}" id="licenseEndDate_id" required/>
            </td>
        </tr>
        <td>
            <input type="submit" value="Save"/>
        </td>
    </table>
</form>
</body>
</html>