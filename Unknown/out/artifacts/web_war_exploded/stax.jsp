<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Output</title>
</head>
<body>
<h4><i>Таблица</i> by STAX</h4>
<table border="3">
    <tr>
        <th>Фото</th>
        <th>Название</th>
        <th>Описание</th>
        <th>Порция<br>(грамм)</th>
        <th>Цена<br>(руб.)</th>
    </tr>

    <c:forEach items="${menu}" var="menus">
        <tr>
            <td>${menus.photoURL}</td>
            <td>${menus.name}</td>
            <td>${menus.description}</td>
            <td>${menus.portion}</td>
            <td>${menus.price}</td>
        </tr>
    </c:forEach>
</table>

<table>
    <tr>
        <td>
            <button>RU</button>
        </td>
        <td>
            <button>US</button>
        </td>
    </tr>
</table>

</body>
</html>
