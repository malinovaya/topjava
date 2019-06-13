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
<table border=1 width="50%">
    <tr align="center">
        <td>ID</td>
        <td>Description</td>
        <td>Calories</td>
        <td>Date and Time</td>
        <td>Update</td>
        <td>Delete</td>
    </tr>
    <c:forEach var="meal" items="${pageContext.request.getAttribute(\"mealsWithExceed\")}">
        <tr style="${meal.exceed ? "color :red" : "color : green"}">
            <td>${meal.mealToId}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
            <td><form method="get" action="/topjava/meals"><button name="action" value="update">Update</button>
            </form>
            </td>
            <td><form method="get" action="/topjava/meals">
                <input type="hidden" name="mealToId" value="${meal.mealToId}" />
                <button name="action" value="delete">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </c:if>


</table>
</body>
</html>