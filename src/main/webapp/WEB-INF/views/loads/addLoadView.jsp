<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Add load</title>
</head>
<body>
<h1>Add new vehicle</h1>
<form method="post" action="/addLoad" modelAttribute = "editableLoad">
    <table>

        <tr>
            <td>
                <input type="hidden" name="id" value="${editableLoad.id}"/>
            </td>
        </tr>

        <tr>
            <td>
                <input type="hidden" name="creationDate" value="${editableLoad.creationDate}">
            </td>
        </tr>

        <tr>
            <th align="left">Weight</th>
            <td>
                <input type="number" name="weight" value="${editableLoad.weight}" required/>
            </td>
        </tr>

        <c:if test="${!empty editableLoad.status}">
            <tr>
                <th align="left">Status</th>
                <td>
                    <select name="status" required>
                        <option value="" selected disabled hidden>${editableLoad.status}</option>
                        <option>CREATED</option>
                        <option>NOT_ASSIGNED</option>
                        <option>IN_PROGRESS</option>
                        <option>DONE</option>
                    </select>
                </td>
            </tr>
        </c:if>

        <td>
            <input type="submit" value="Save"/>
        </td>
    </table>

    <p><a href="homePage">Home page</a></p>
</form>
</body>
</html>
