<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>REST API</title>
</head>
<body>
<form id="requestForm" action="getReport" method="post">
    <h2>Cities:</h2>
    <textarea id="input" name="input">Minsk, Houston, Vilnius, Munich</textarea>
    <br><br>
    <input type="submit" value="Request"/>
</form>
<br>
<p>Available cities: </p>
<ul>
    <c:forEach items="${cityNames}" var="name">
        <li>${name}</li>
    </c:forEach>
</ul>
</body>
</html>
