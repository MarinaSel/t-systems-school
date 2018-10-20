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
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <%@include file="/WEB-INF/views/navbar.html"%>
</head>

<body>
   <table class="table table-striped table-bordered">
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
            <tr>
                <td>${driver.firstName}</td>
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