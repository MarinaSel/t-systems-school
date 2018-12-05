<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%@include file="/WEB-INF/views/navbarForDriver.jsp" %>
    <%@include file="/WEB-INF/views/includes/includeStyles.jsp" %>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=<d5ee42b2-f24d-4ace-a155-4db206670417>"
            type="text/javascript"></script>
    <script src="https://yandex.st/jquery/2.2.3/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/yandexMap.js" type="text/javascript"></script>
    <script src="/resources/js/getAssignedLoads.js" type="text/javascript"></script>
    <title>Driver</title>
</head>

<body class="body">
<hr>
<div id="container">
    <div class="text driverContent">
        <h3 align="center">Your data</h3>
        <hr>
        Name: ${driver.user.firstName} ${driver.user.lastName}
        <br>Driver's license number: ${driver.drivingLicenseNum}
        <br>Status: ${driver.status}
        <br>License end date: ${driver.licenseEndDate}
    </div>

    <div class="actionsContent">
        <h3 align="center">Actions</h3>
        <hr style="width: 30%" align="center">

        <c:if test="${!empty vehicle}">
            <c:choose>
                <c:when test="${vehicle.status == 'BROKEN'}">
                    <a href="/home/repairedVehicle/${vehicle.id}" class="btn btn-sm btn-outline-success waves-effect"
                       style="font-size: small">Repaired</a>
                </c:when>
                <c:otherwise>
                    <a href="/home/brokenVehicle/${vehicle.id}" class="btn btn-sm btn-outline-danger waves-effect"
                       style="font-size: small">Broken</a>
                </c:otherwise>
            </c:choose>
        </c:if>

        <c:if test="${vehicle.status == 'WORKING'}">
            <a href="/home/allDelivered/${vehicle.id}"
               class="btn btn-sm btn-outline-success waves-effect" style="font-size: small">All Delivered</a>
        </c:if>

        <c:if test="${driver.status == 'FREE'}">
            <a href="/home/restDriver/${driver.id}" class="btn btn-sm btn-outline-info waves-effect"
               style="font-size: small">Rest</a>
        </c:if>
        <c:if test="${driver.status == 'REST'}">
            <a href="/home/freeDriver/${driver.id}" class="btn btn-sm btn-outline-info waves-effect"
               style="font-size: small">End rest</a>
        </c:if>

        <c:if test="${vehicle.status == 'FREE'}">
            <a href="/home/beginDelivery/${vehicle.id}" class="btn btn-sm btn-outline-success waves-effect"
               style="font-size: small">Begin delivery</a>
        </c:if>

        <c:if test="${vehicle.status == 'FREE' || (vehicle.status == 'BROKEN' && driver.status != 'WORKING')}">
            <a href="/home/rejectDelivery/${vehicle.id}" class="btn btn-sm btn-outline-success waves-effect"
               style="font-size: small">Reject delivery</a>
        </c:if>
    </div>

    <c:if test="${!empty vehicle}">
    <div class="text vehicleContent">
        <h3 align="center">Your vehicle</h3>
        <hr>
        Model: ${vehicle.model}
        <br>Registration number: ${vehicle.registrationNumber}
        <br>Capacity: ${vehicle.capacity}
        <br>Status: ${vehicle.status}
    </div>
</div>

<table class="table table-striped table-bordered" width="100%">
    <thead>
    <th colspan="6"><h3 align="center">Loads</h3></th>
    <tr>
        <th class="textTableHeader">Title</th>
        <th class="textTableHeader">Description</th>
        <th class="textTableHeader">Weight (kg)</th>
        <th class="textTableHeader">Status</th>
        <th class="textTableHeader">Actions</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${vehicle.loads}" var="load">
        <tr>
            <td>${load.title}</td>
            <td>${load.description}</td>
            <td align="center">${load.weight}</td>
            <td align="center">${load.status}</td>
            <td align="center">
                <c:if test="${load.status == 'IN_PROGRESS' && vehicle.status != 'BROKEN'}">
                    <a href="/home/delivered/${load.id}"
                       class="btn btn-sm btn-outline-success waves-effect" style="font-size: small">Delivered</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
<c:if test="${!empty vehicle}">
    <div onchange="init()" id="map"></div>
</c:if>
</body>
</html>
