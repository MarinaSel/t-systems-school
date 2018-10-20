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

    <link rel="stylesheet" type="text/css" href="resources/css/selectForStatus.css"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <%@include file="/WEB-INF/views/navbar.html"%>
    <script src="resources/js/datepicker.js"></script>
</head>

<body>

<h1 align="center">${empty editableDriver.id ? 'Add' : 'Edit'} driver</h1>
<hr>
    <form method="post" action="/saveDriver" modelAttribute = "editableDriver">
        <table align="center" class="table table-striped table-bordered" style="width: 400px">
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
</html>