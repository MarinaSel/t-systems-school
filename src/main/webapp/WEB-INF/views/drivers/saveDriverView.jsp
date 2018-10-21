<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>

<html>
<head>
    <title>${empty editableDriver.id ? 'Add' : 'Edit'} driver</title>
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <%@include file="/WEB-INF/views/navbar.html"%>
</head>

<body>

<h1 align="center">${empty editableDriver.id ? 'Add' : 'Edit'} driver</h1>
<hr>
    <form method="post" action="/saveDriver" modelAttribute = "editableDriver">
        <table align="center"id="table-width" class="table table-striped table-bordered" >
            <tr>
                <td>
                    <input type="hidden" name="id" value="${editableDriver.id}"/>
                </td>
                <td>
                    <input type="hidden" name="creationDate" value="${editableDriver.creationDate}">
                </td>
            </tr>
            <tr>
                <th>First name</th>
                <td>
                    <input name="firstName" maxlength="50" value="${editableDriver.firstName}" placeholder="Enter the first name" required/>
                </td>
            </tr>
            <tr>
                <th>Last name</th>
                <td>
                    <input name="lastName" maxlength="50" value="${editableDriver.lastName}" placeholder="Enter the second name" required/>
                </td>
            </tr>
            <tr>
                <th>Driver's license number</th>
                <td>
                    <input name="drivingLicenseNum" value="${editableDriver.drivingLicenseNum}" placeholder="Enter the driving license number" required/>
                </td>
            </tr>
            <tr>
                <th>License end date</th>
                <td>
                    <input name="licenseEndDate" id = "dayOfDelivery" value="${editableDriver.licenseEndDate}" placeholder="Choose the licence end date" required readonly = "true"/>
                </td>
            </tr>
            <c:if test="${!empty editableDriver.status}">
                <tr>
                    <th>Status</th>
                    <td>
                        <select class="soflow" name="status" required>
                            <option value="" selected disabled hidden>${editableDriver.status}</option>
                            <option>FREE</option>
                            <option>WORK</option>
                            <option>REST</option>
                            <option>DRIVING</option>
                        </select>
                    </td>
                </tr>
            </c:if>
            <td colspan="2">
                <input class="btn btn-success" type="submit" value="Save"/>
            </td>
        </table>

    </form>
</body>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="resources/js/datepicker.js"></script>

</html>