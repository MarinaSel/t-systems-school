<%@ page
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>

<html>
<head>
    <title>${empty editableDriver.id ? 'Add' : 'Edit'} driver</title>


    <%@include file="/WEB-INF/views/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/navbar.html" %>
    <%@include file="/WEB-INF/views/includeDatepicker.jsp" %>


</head>

<body>

<h1 align="center">${empty editableDriver.id ? 'Add' : 'Edit'} driver</h1>
<hr>
<form method="post" action="/saveDriver" modelAttribute="editableDriver">
    <table align="center" id="saveVehicleAndDriverTable" class="table table-striped table-bordered">
        <tr>
            <td>
                <input type="hidden" name="id" value="${editableDriver.id}"/>
            </td>
            <td>
                <input type="hidden" name="creationDate" value="${editableDriver.creationDate}">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </td>
        </tr>
        <tr>
            <th>First name</th>
            <td>
                <input name="firstName" maxlength="50" value="${editableDriver.user.firstName}"
                       placeholder="First name"
                       autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th>Last name</th>
            <td>
                <input name="lastName" maxlength="50" value="${editableDriver.user.lastName}"
                       placeholder="Second name"
                       autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th>Login</th>
            <td>
                <input name="login" maxlength="50" value="${editableUser.login}" placeholder="Login"
                       autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th>Password</th>
            <td>
                <input type="password" name="password" maxlength="50" value="${editableUser.password}"
                       placeholder="Password"
                       autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th>Driver's license number</th>
            <td>
                <input name="drivingLicenseNum" value="${editableDriver.drivingLicenseNum}"
                       placeholder="Driving license number"
                       title="Example: 12Ab345678" pattern="[0-9]{2}[A-Za-z]{2}[0-9]{6}"
                       maxlength="10" minlength="10" autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th>License end date</th>
            <td>
                <input name="licenseEndDate" id="date" value="${editableDriver.licenseEndDate}"
                       placeholder="Licence end date" required readonly="true"/>
            </td>
        </tr>
        <c:if test="${!empty editableDriver.status}">
            <tr>
                <th>Status</th>
                <td>
                    <select class="soflow" name="status">
                        <c:forEach items="${statuses}" var="status">
                            <option value="${status}" ${status == editableDriver.status ? 'selected="selected"' : ''}>${status}</option>
                        </c:forEach>
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