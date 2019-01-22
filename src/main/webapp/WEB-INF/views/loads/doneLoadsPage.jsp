<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/WEB-INF/views/navbar.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeStyles.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeTablePag.jsp" %>
    <title>Done loads</title>
</head>
<body class="body">
<a href="/load/allLoads" class="btn btn-outline-success waves-effect"><strong>Unfinished loads</strong></a>
<hr>
<table id="tableScroll" class="table table-striped table-bordered" width="100%">
    <thead>
    <tr>
        <th class="th-sm textTableHeader">Title</th>
        <th class="th-sm textTableHeader">Description</th>
        <th class="th-sm textTableHeader">Day of delivery</th>
        <th class="th-sm textTableHeader">Vehicle registration number</th>
        <th class="th-sm textTableHeader">Primary driver</th>
        <th class="th-sm textTableHeader">Co driver</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${doneLoads}" var="load">
        <tr>
            <td>${load.title}</td>
            <td>${load.description}</td>
            <td align="center">
                    ${load.dayOfDelivery}
            </td>
            <td>${load.history.vehicle.registrationNumber}</td>
            <td>${load.history.primaryDriver.user.firstName} ${load.history.primaryDriver.user.lastName}
                    ${load.history.primaryDriver.drivingLicenseNum}</td>
            <td>${load.history.coDriver.user.firstName} ${load.history.coDriver.user.lastName}
                    ${load.history.coDriver.drivingLicenseNum}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
