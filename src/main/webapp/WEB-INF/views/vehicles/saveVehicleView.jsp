<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>${empty editableVehicle.id ? 'Add' : 'Edit'} vehicle</title>
    <script src="resources/js/listBox.js"></script>
    <script>
        function showDrivers(){
            var selectBox = document.getElementById('status');
            var userInput = selectBox.options[selectBox.selectedIndex].value;
            if(userInput == 'BROKEN'){
                document.getElementById('drivers').style.visibility = 'hidden';
            }
            else{
                document.getElementById('drivers').style.visibility = 'visible';
            }
            return false;
        };
    </script>
</head>
<body>
<h1>${empty editableVehicle.id ? 'Add' : 'Edit'} vehicle</h1>
<form method="post" action="/saveVehicle" modelAttribute = "editableVehicle">
    <table>
        <tr>
            <td>
                <input type="hidden" name="id" value="${editableVehicle.id}"/>
            </td>
            <td>
                <input type="hidden" name="creationDate" value="${editableVehicle.creationDate}">
            </td>
        </tr>
        <tr>
            <th align="left">Registration number</th>
            <td>
                <input name="registrationNumber" value="${editableVehicle.registrationNumber}" maxlength="7" required/>
            </td>
        </tr>
        <tr>
            <th align="left">Capacity</th>
            <td>
                <input type="number" name="capacity" value="${editableVehicle.capacity}" required/>
            </td>
        </tr>

        <c:if test="${!empty editableVehicle.status}">
            <tr>
                <th align="left">Status</th>
                <td>
                    <select name="status" id="status" onchange="showDrivers();" required>
                        <option value="" selected disabled hidden>${editableVehicle.status}</option>
                        <option>BROKEN</option>
                        <option>WORKING</option>
                        <option>FREE</option>
                    </select>
                </td>
            </tr>
        </c:if>


        <tr id="drivers">
            <th align="left">Primary driver</th>
            <td>
                <select class="select" name="drivingLicenseNum" style="width:100%">
                    <option value="" selected disabled hidden></option>
                    <c:forEach items="${drivers}" var="driver">
                        <option>${driver.drivingLicenseNum}</option>
                    </c:forEach>
                </select>
            </td>
            <th align="left">Second driver</th>
            <td>
                <select class="select" name="drivingLicenseNum" style="width:100%">
                    <option value="" selected disabled hidden></option>
                    <c:forEach items="${drivers}" var="driver">
                        <option>${driver.drivingLicenseNum}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <td>
            <input type="submit" value="Save"/>
        </td>
    </table>
    <p><a href="/vehicles">Vehicles</a></p>
    <p><a href="/homePage">Home</a> </p>
</form>
</body>
</html>