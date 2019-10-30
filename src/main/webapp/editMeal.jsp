<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${meal.id == 0 ? "Create Meal" : "Edit meal"}</title>

</head>
<body>

${meal.id == 0 ? "Create Meal" : "Edit meal"}
<hr>

<form name="id" method="post" action="meals">
    <jsp:useBean id="meal" scope="request" class="ru.javawebinar.topjava.model.Meal"/>
    <p><b>ID</b><br>
        <input type="text" name="id" value="${meal.id}" disabled size="40">
    </p>
    <p><b>Description</b><br>
        <input type="text" name="description" value="${meal.description}" size="40">
    </p>
    <p><b>Date</b><br>
        <input type="datetime-local" name="date" value="${meal.dateTime}" size="40">
    </p>
    <p><b>Calories</b><br>
        <input type="text" name="calories" value="${meal.calories}" size="40">
    </p>

    <p><input type="submit" value="Отправить">
        <input type="reset" value="Очистить"></p>
</form>

</body>
</html>