<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<html>

<head>
    <title>Add Driver</title>

    <script src="resources/js/datepicker.js"></script>
</head>

<body>

<h1>Add new driver</h1>

    <form method="post" action="/add" modelAttribute = "driver">
        <table>
            <tr>
                <th align="left">First name</th>
                <td>
                    <input name="firstName" maxlength="50" required/>
                </td>
            </tr>
            <tr>
                <th align="left">Second name</th>
                <td>
                    <input name="lastName" maxlength="50" required/>
                </td>
            </tr>
            <tr>
                <th align="left">Driver's license number</th>
                <td>
                    <input name="drivingLicenseNum" required/>
                </td>
            </tr>
            <tr>
                <th align="left">License end date</th>
                <td>
                    <input name="licenseEndDate" id = "licenseEndDate_id" required readonly = "true"/>
                </td>
            </tr>
            <td>
                <input type="submit" value="Save"/>
            </td>
        </table>

        <p><a href="homePage">Home page</a></p>
    </form>
</body>
</html>