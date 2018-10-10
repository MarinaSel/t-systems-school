<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Loads</title>
</head>
<body>
<table border="2">
    <tr>
        <th>Load status</th>
        <th>Weight</th>
        <th>Edit</th>
    </tr>

    <c:forEach items="${loads}" var="load">
        <tr>
            <td>${load.weight}</td>
            <td>${load.status}</td>

            <td><a href="/editLoad/${load.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

<p><a href="/getAddLoadPage">Add new load</a></p>
</body>
</html>
