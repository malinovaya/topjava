<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<c:if test="${pageContext.request.getAttribute(\"mealsWithExceed\") != null}">
<table border=1>
    <tr align="center">
        <td>Description</td>
        <td>Calories</td>
        <td>Date and Time</td>
    </tr>
    <c:forEach var="meal" items="${pageContext.request.getAttribute(\"mealsWithExceed\")}">
        <tr style="${meal.exceed ? "color :red" : "color : green"}">
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
        </tr>
    </c:forEach>
    </c:if>


</table>
</body>
</html>