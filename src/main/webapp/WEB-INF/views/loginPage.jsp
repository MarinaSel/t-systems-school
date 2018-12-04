<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="/WEB-INF/views/navbarLogin.html" %>
    <%@include file="/WEB-INF/views/includes/includeStyles.jsp" %>
    <title>Login</title>
</head>
<body>
<hr>
<div align="center">
    <form class="text-center border border-light p-5" id="loginTable" method="post" action="/loginPage">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <p class="h4 mb-4">Login to your account</p>
        <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="Username" name="username">
        <input type="password" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Password"
               name="password">
        <c:if test="${!empty error}">
            <div class="error">${error}</div>
        </c:if>
        <button class="btn btn-outline-default waves-effect" style="font-size: medium" type="submit">
            <i class="fa fa-sign-in"></i> <strong>Sign in</strong></button>
    </form>
</div>
</body>
</html>
