<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<html>
<head>
    <script src="resources/js/listBox.js"></script>
    <title>${empty editableLoad.id ? 'Add' : 'Edit'} load</title>
</head>
<body>
<h1>${empty editableLoad.id ? 'Add' : 'Edit'} load</h1>
<form method="post" action="/saveLoad" modelAttribute = "editableLoad">
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
            <td>
                <input type="hidden" name="status" value="${editableLoad.status}">
            </td>
        </tr>

        <tr>
            <th align="left">Weight</th>
            <td>
                <input type="number" name="weight" value="${editableLoad.weight}" onchange="" required/>
            </td>
        </tr>

        <c:if test="${editableLoad.status == 'NOT_ASSIGNED'}">
            <tr>
                <th align="left">Vehicles registration numbers</th>
                <td>
                    <select name="regNum" class="select" id="vehicle" style="width:100%">
                        <option value="" selected disabled hidden></option>
                        <c:forEach items="${freeVehicles}" var="vehicle">
                            <option>${vehicle.registrationNumber}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </c:if>

        <c:if test="${editableLoad.status = 'IN_PROGRESS'}">
            <tr>
                <th align="left">Status</th>
                <td>
                    <select name="status" required>
                        <option value="" selected disabled hidden>${editableLoad.status}</option>
                        <option>DONE</option>
                    </select>
                </td>
            </tr>
        </c:if>

        <td>
            <input type="submit" value="Save"/>
        </td>
    </table>

    <p><a href="/loads">Loads</a></p>
</form>
</body>
</html>
