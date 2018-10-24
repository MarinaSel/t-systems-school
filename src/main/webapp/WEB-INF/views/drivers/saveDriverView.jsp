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


    <%@include file="/WEB-INF/views/includeStyles.jsp"%>
    <%@include file="/WEB-INF/views/navbar.html"%>
    <%@include file="/WEB-INF/views/includeDatepicker.jsp"%>


</head>

<body>

<h1 align="center">${empty editableDriver.id ? 'Add' : 'Edit'} driver</h1>
<hr>
    <form method="post" action="/saveDriver" modelAttribute = "editableDriver">
        <table align="center" id="saveVehicleAndDriverTable" class="table table-striped table-bordered" >
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
                    <input name="licenseEndDate" id = "date" value="${editableDriver.licenseEndDate}" placeholder="Choose the licence end date" required readonly = "true"/>
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
</html>