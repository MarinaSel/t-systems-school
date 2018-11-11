<%@ page contentType="text/html;charset=UTF-8" %>
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
<div align="center">
    <form class="text-center border border-light p-5" id="loginTable" method="post" action="/loginPage">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <p class="h4 mb-4">Login</p>
        <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="Username" name="username">
        <input type="password" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Password"
               name="password">
        <button class="btn btn-info btn-block my-4" type="submit"><i class="fa fa-sign-in"></i> Sign in</button>
    </form>
</div>
</body>
</html>
