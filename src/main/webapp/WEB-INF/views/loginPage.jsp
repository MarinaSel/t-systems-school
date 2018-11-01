<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="/WEB-INF/views/navbarLogin.html" %>
    <%@include file="/WEB-INF/views/includeStyles.jsp" %>
    <title>Login</title>
</head>
<body>
<h1 align="center">Login to your account</h1>
<hr>
<form action="/loginPage" method='POST'>
    <table align="center" id="loginTable" class="table table-striped table-bordered">
        <tr>
            <td>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <th align="left">Login</th>
            <td>
                <input placeholder="Username" name="username">
            </td>
        </tr>
        <tr>
            <th align="left">Password</th>
            <td>
                <input type="password" placeholder="Password" name="password">
            </td>
        </tr>
        <td align="center" colspan="2">
            <input class="btn btn-info" type="submit" value="Login"/>
        </td>
    </table>
</form>
</body>
</html>
