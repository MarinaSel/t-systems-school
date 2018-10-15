<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
    <script src="resources/js/listBox.js"></script>

    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="resources/js/datepicker.js"></script>
    <script src="resources/js/getVehicles.js"></script>
    <script src="resources/js/getDrivers.js"></script>

    <title>${empty editableLoad.id ? 'Add' : 'Edit'} load</title>

    <script type="text/javascript">

    </script>
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
                <th align="left">Title</th>
                <td>
                    <input name="title" value="${editableLoad.title}"/>
                </td>
            </tr>

            <tr>
                <th align="left">Description</th>
                <td>
                    <input name="description" value="${editableLoad.description}"/>
                </td>
            </tr>

            <tr>
                <th align="left">Day of delivery</th>
                <td>
                    <input name="dayOfDelivery" id="dayOfDelivery" value="${editableLoad.dayOfDelivery}"/>
                </td>
            </tr>
            <tr>
                <th align="left">Weight</th>
                <td>
                    <input id="vehicleWeight" type="number" name="weight" value="${editableLoad.weight}" oninput="getVehicle()" required/>
                </td>
            </tr>


            <tr>
                <th align="left">Vehicles registration numbers</th>
                <td>
                    <select class="select" id="vehicles" name="regNum" style="width:100%" onchange="showDrivers();">
                        <option value="${editableLoad.vehicle.registrationNumber}" selected>${editableLoad.vehicle.registrationNumber}</option>
                        <c:forEach items="${freeVehicles}" var="vehicle">
                            <option>${vehicle.registrationNumber}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <th align="left">Primary driver</th>
                <td>
                    <select id="primaryDriver" class="select" name="drivingLicenseNum" style="width:100%" disabled="disabled">
                        <option value="" selected disabled hidden></option>
                        <c:forEach items="${freeDrivers}" var="driver">
                            <option>${driver.drivingLicenseNum}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="left">Second driver</th>
                <td>
                    <select class="select" id="secondDriver" name="drivingLicenseNum" style="width:100%" disabled="disabled">
                        <option value="" selected disabled hidden></option>
                        <option></option>
                        <c:forEach items="${freeDrivers}" var="driver">
                            <option>${driver.drivingLicenseNum}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <td>
            <input type="submit" value="Save"/>
            </td>
    </table>

    <p><a href="/loads">Loads</a></p>
    <p><a href="/homePage">Home</a></p>
    </form>
    </body>
    </html>
