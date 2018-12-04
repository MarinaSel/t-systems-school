<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>${empty editableDriver.id ? 'Add' : 'Edit'} driver</title>
    <%@include file="/WEB-INF/views/navbar.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeDatepicker.jsp" %>
    <script type="text/javascript" src="/resources/js/driverValidator.js"></script>
</head>
<body>
<hr>
<form onsubmit="return onDriverSubmit()" method="post" action="/driver/saveDriver" modelAttribute="editableDriver">
    <input type="hidden" name="id" id="driverId" value="${editableDriver.id}"/>
    <input type="hidden" name="user.id" value="${editableDriver.user.id}"/>
    <input type="hidden" name="creationDate" value="${editableDriver.creationDate}">
    <input type="hidden" name="status" value="${editableDriver.status}">
    <input type="hidden" name="user.login" value="${editableDriver.user.login}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table align="center" id="saveVehicleAndDriverTable" class="table table-bordered">
        <td align="center" colspan="2" style="font-size: xx-large">
            ${empty editableDriver.id ? 'Add' : 'Edit'} driver
        </td>
        <tr>
            <th>First name</th>
            <td>
                <input class="form-control" name="user.firstName" maxlength="50"
                       value="${editableDriver.user.firstName}"
                       placeholder="First name"
                       autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th>Last name</th>
            <td>
                <input class="form-control" name="user.lastName" maxlength="50" value="${editableDriver.user.lastName}"
                       placeholder="Second name"
                       autocomplete="off" required/>
            </td>
        </tr>
        <c:if test="${editableDriver.id == null}">
            <tr>
                <th>Login</th>
                <td>
                    <input class="form-control" id="login" name="user.login" maxlength="50"
                           value="${editableDriver.user.login}"
                           placeholder="Login"
                           autocomplete="off" required/>
                </td>
            </tr>
        </c:if>
        <tr>
            <th>Password</th>
            <td>
                <input oninput="confirmPasswords()" class="form-control" type="password" name="user.password"
                       id="password" maxlength="50" minlength="5"
                       value="${editableDriver.user.password}"
                       placeholder="Password"
                       autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th>Password confirmation
                <p><span id='passwordMessage'></span></p></th>
            <td>
                <input oninput="confirmPasswords()" class="form-control" type="password"
                       id="confirmation" maxlength="50"
                       value="${editableDriver.user.password}"
                       placeholder="Password confirmation"
                       autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th>Driver's license number</th>
            <td>
                <input class="form-control" name="drivingLicenseNum" value="${editableDriver.drivingLicenseNum}"
                       id="dln" placeholder="12AA345678"
                       title="Example: 12AA345678" pattern="[0-9]{2}[A-Za-z]{2}[0-9]{6}"
                       maxlength="10" minlength="10" autocomplete="off" required/>
            </td>
        </tr>
        <tr>
            <th>License end date</th>
            <td>
                <input class="form-control" name="licenseEndDate" id="date" value="${editableDriver.licenseEndDate}"
                       placeholder="License end date" onkeypress="return false" required/>
            </td>
        </tr>

        <td colspan="2" align="center">
            <input class="btn btn-outline-success waves-effect" style="font-size: medium" type="submit" value="Save"
                   onclick="clearDriverErrorMessage()"/>
            <p><span id="driverErrorMessage"></span></p>
        </td>
    </table>
</form>
</body>
</html>