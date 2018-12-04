<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="/resources/image/favicon.png">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.3/css/mdb.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark unique-color-dark">
    <a class="navbar-brand" href="#">LOGISTIC COMPANY</a>
    <div class="collapse navbar-collapse" id="basicExampleNav">

        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/load/allLoads">Loads
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/driver/allDrivers">Drivers</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/vehicle/allVehicles">Vehicles</a>
            </li>
        </ul>
    </div>
    <form class="form-inline my-2 my-lg-0 ml-auto" action="/logout" method="post">
        <a class="nav-link" href="#" style="color: white">
            Signed in as <sec:authentication property="principal"></sec:authentication>
        </a>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <button class="btn btn-outline-white btn-md my-2 my-sm-0 ml-3" type="submit" style="font-size: small">
            <i class="fa fa-sign-out" aria-hidden="true"></i>Logout
        </button>
    </form>
</nav>
</body>
</html>