<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Drivers</title>
    <%@include file="/WEB-INF/views/navbar.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeTablePag.jsp" %>
</head>
<body class="body">
<a href="/driver/addDriver" class="btn btn-outline-primary waves-effect"><strong>Add new driver</strong></a>
<hr>
<table id="tableScroll" class="table table-striped table-bordered" width="100%">
    <thead>
    <tr>
        <th class="th-sm textTableHeader">Name</th>
        <th class="th-sm textTableHeader">Login</th>
        <th class="th-sm textTableHeader">Driver's license number</th>
        <th class="th-sm textTableHeader">License end date</th>
        <th class="th-sm textTableHeader">Driver status</th>
        <th class="th-sm textTableHeader">Creation date</th>
        <th class="th-sm textTableHeader">Actions</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${drivers}" var="driver">
        <tr class="trThead">
            <td>${driver.user.firstName} ${driver.user.lastName}</td>
            <td>${driver.user.login}</td>
            <td>${driver.drivingLicenseNum}</td>
            <td align="center">${driver.licenseEndDate}</td>
            <td>${driver.status}</td>
            <td align="center">${driver.creationDate}</td>
            <td align="center">
                <c:if test="${driver.status != 'FIRED'}">
                    <a href="/driver/editDriver/${driver.id}" class="btn btn-sm btn-outline-info waves-effect"
                       style="font-size: small">Edit</a>
                </c:if>
                <c:if test="${driver.status == 'FREE' || driver.status == 'REST'}">
                    <a href="/driver/fireDriver/${driver.id}" class="btn btn-sm btn-outline-danger waves-effect"
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