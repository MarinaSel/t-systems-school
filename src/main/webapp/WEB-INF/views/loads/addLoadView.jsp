<%--
  Created by IntelliJ IDEA.
  User: Марина
  Date: 07.10.2018
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add load</title>
</head>
<body>
<h1>Add new vehicle</h1>
<form method="post" action="/addLoad" modelAttribute = "newLoad">
    <table>
        <tr>
            <th align="left">Weight</th>
            <td>
                <input type="number" name="weight" required/>
            </td>
        </tr>
        <td>
            <input type="submit" value="Save"/>
        </td>
    </table>
    <p><a href="homePage">Home page</a></p>
</form>
</body>
</html>
