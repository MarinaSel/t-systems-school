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
    <title>${empty editableDriver.id ? 'Add' : 'Edit'} driver</title>

    <script src="resources/js/datepicker.js"></script>
</head>

<body>

<h1>${empty editableDriver.id ? 'Add' : 'Edit'} driver</h1>

    <form method="post" action="/addDriver" modelAttribute = "editableDriver">
        <table>
            <tr>
                <td>
                    <input type="hidden" name="id" value="${editableDriver.id}"/>
                </td>
                <td>
                    <input type="hidden" name="creationDate" value="${editableDriver.creationDate}">
                </td>
            </tr>
            <tr>
                <th align="left">First name</th>
                <td>
                    <input name="firstName" maxlength="50" value="${editableDriver.firstName}" required/>
                </td>
            </tr>
            <tr>
                <th align="left">Second name</th>
                <td>
                    <input name="lastName" maxlength="50" value="${editableDriver.lastName}" required/>
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
                    <input name="licenseEndDate" id = "dayOfDelivery" value="${editableDriver.licenseEndDate}" required readonly = "true"/>
                </td>
            </tr>
            <c:if test="${!empty editableDriver.status}">
                <tr>
                    <th align="left">Status</th>
                    <td>
                        <select name="status" required>
                            <option value="" selected disabled hidden>${editableDriver.status}</option>
                            <option>FREE</option>
                            <option>WORK</option>
                            <option>REST</option>
                            <option>DRIVING</option>
                        </select>
                    </td>
                </tr>
            </c:if>
            <td>
                <input type="submit" value="Save"/>
            </td>
        </table>

        <p><a href="/getAddDriverView">Home page</a></p>
    </form>
</body>
</html>