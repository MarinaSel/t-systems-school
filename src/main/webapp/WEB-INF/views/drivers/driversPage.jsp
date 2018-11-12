<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Drivers</title>
    <%@include file="/WEB-INF/views/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/navbar.html" %>
    <link rel="stylesheet" type="text/css" href="resources/css/drivers.css"/>
</head>
<body>
<a href="/getSaveDriverPage" class="btn btn-outline-primary waves-effect"><strong>Add new driver</strong></a>
<hr>
<table class="table table-striped table-bordered tableView drivers">
    <thead>
    <tr>
        <th>First name</th>
        <th>Second name</th>
        <th>Driver's license number</th>
        <th>License end date</th>
        <th>Driver status</th>
        <th>Creation date</th>
        <th>Actions</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${drivers}" var="driver">
        <tr class="trThead">
            <td>${driver.user.firstName}</td>
            <td>${driver.user.lastName}</td>
            <td>${driver.drivingLicenseNum}</td>
            <td align="center">${driver.licenseEndDate}</td>
            <td>${driver.status}</td>
            <td align="center">${driver.creationDate}</td>
            <td align="center">
                <c:if test="${driver.status != 'FIRED'}">
                    <a href="/editDriver/${driver.id}" class="btn btn-sm btn-outline-info waves-effect"
                       style="font-size: small">Edit</a>
                </c:if>
                <c:if test="${driver.status == 'FREE' || driver.status == 'REST'}">
                    <a href="/fireDriver/${driver.id}" class="btn btn-sm btn-outline-danger waves-effect"
                       style="font-size: small">Fire</a>
                </c:if>
                <c:if test="${driver.status == 'FIRED'}">
                    Fired on ${driver.dateOfFire}
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>