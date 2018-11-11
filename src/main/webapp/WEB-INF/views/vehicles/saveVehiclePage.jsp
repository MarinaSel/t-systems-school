<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${empty editableVehicle.id ? 'Add' : 'Edit'} vehicle</title>
    <%@include file="/WEB-INF/views/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/navbar.html" %>
    <%@include file="/WEB-INF/views/includeDatepicker.jsp" %>
</head>
<body>
<h1 align="center">${empty editableVehicle.id ? 'Add' : 'Edit'} vehicle</h1>
<hr>
<form method="post" action="/saveVehicle" modelAttribute="editableVehicle">
    <input type="hidden" name="id" value="${editableVehicle.id}"/>
    <input type="hidden" name="creationDate" value="${editableVehicle.creationDate}">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <table align="center" id="saveVehicleAndDriverTable" class="table table-bordered">
        <tr>
            <th align="left">Model</th>
            <td>
                <input class="form-control" name="model" value="${editableVehicle.model}" placeholder="Vehicles model"
                       maxlength="10" autocomplete="off" required>
            </td>
        </tr>
        <tr>
            <th align="left">Issue date</th>
            <td>
                <input class="form-control" name="issueDate" id="issueDate" value="${editableVehicle.issueDate}"
                       placeholder="Issue date" required readonly="true">
            </td>
        </tr>
        <tr>
            <th align="left">Registration number</th>
            <td>
                <input class="form-control" name="registrationNumber" value="${editableVehicle.registrationNumber}"
                       placeholder="AA12345"
                       title="Example:AA12345 " pattern="[A-Za-z]{2}[0-9]{5}"
                       maxlength="7" minlength="7" autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Capacity (kg)</th>
            <td>
                <input class="form-control" name="capacity" type="number" value="${editableVehicle.capacity}"
                       placeholder="Capacity"
                       min="1" max="22000"
                       title="Must contains only digits and can't be null" autocomplete="off" required/>
            </td>
        </tr>
        <c:if test="${!empty editableVehicle.status}">
            <tr>
                <th align="left">Status</th>
                <td>
                    <select class="browser-default custom-select" name="status">
                        <c:forEach items="${statuses}" var="status">
                            <option value="${status}" ${status == editableVehicle.status ? 'selected="selected"' : ''}>${status}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </c:if>
        <td colspan="2">
            <input class="btn btn-dark-green" type="submit" value="Save"/>
        </td>
    </table>
</form>
</body>
</html>