<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Drivers</title>
    <%@include file="/WEB-INF/views/includeStyles.jsp"%>
    <%@include file="/WEB-INF/views/navbar.html"%>
    <link rel="stylesheet" type="text/css" href="resources/css/drivers.css"/>

</head>

<body>
<a href="/getSaveDriverView" class="btn btn-success">Add new driver</a>
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

        <tbody><c:forEach items="${drivers}" var="driver">
            <tr class="trThead">
                <td >${driver.firstName}</td>
                <td>${driver.lastName}</td>
                <td>${driver.drivingLicenseNum}</td>
                <td>${driver.licenseEndDate}</td>
                <td>${driver.status}</td>
                <td>${driver.creationDate}</td>

                <td>
                    <a href="/editDriver/${driver.id}" class="btn btn-info btn-sm">Edit</a>
                    <a href="/removeDriver/${driver.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>