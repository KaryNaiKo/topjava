<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        .exceded {
            color: red;
        }
        .not-exceded {
            color: green;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Описание</th>
        <th>Время</th>
        <th>Каллории</th>
    </tr>
    <c:forEach var="meal" items="${mealList}">
        <jsp:useBean id="meal" scope="request" class="ru.javawebinar.topjava.model.MealTo"/>
        <tr class="${meal.excess ? "exceded" : "not-exceded"}">
            <td>${meal.id}</td>
            <td>${meal.description}</td>
<%--            <td>${meal.dateTime}</td>--%>
            <td>${fn:replace(meal.dateTime, "T", " ")}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>