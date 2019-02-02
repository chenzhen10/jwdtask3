<%@ page contentType="text/html;charset=UTF-8" language="java"  errorPage="/error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<html>
<head>
    <title>Output</title>
</head>
<body>
<h4><i><fmt:message bundle="${loc}" key="locale.headTranslate"/></i> by STAX</h4>


<table border="3" cellspacing="5" cellpadding="5">
    <tr>
        <th><fmt:message bundle="${loc}" key="locale.headTableElement1"/></th>
        <th><fmt:message bundle="${loc}" key="locale.headTableElement2"/></th>
        <th><fmt:message bundle="${loc}" key="locale.headTableElement3"/></th>
        <th><fmt:message bundle="${loc}" key="locale.headTableElement4"/></th>
        <th><fmt:message bundle="${loc}" key="locale.headTableElement5"/></th>
    </tr>

    <c:forEach items="${menu}" var="menus">
        <tr>
            <td><c:out value="${menus.photoURL}"/></td>
            <td>${menus.name}</td>
            <td>${menus.description}</td>
            <td>${menus.portion}</td>
            <td>${menus.optPrice}</td>
        </tr>
    </c:forEach>
</table>


<form action="" method="post">
    <input type="hidden" name="local" value="ru_RU"/>
    <button>RU</button>
</form>
<form action="" method="post">
    <input type="hidden" name="local" value="en_US"/>
    <button>US</button>
</form>


</body>
</html>
