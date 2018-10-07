<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    </tr>
    <c:forEach items="${loads}" var="load">
        <tr>
            <td>${load.weight}</td>
            <td>${load.status}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
