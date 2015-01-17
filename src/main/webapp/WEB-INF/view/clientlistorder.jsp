
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Orders for Client</title>
</head>
<body>
${user33}
<br/>
<a href="logout">Logout</a>
<table>
    <c:forEach var="elem" items="${orderList}">
        <tr>

            <td><c:out value="${ elem.id }" /></td>
            <td><c:out value="${ elem.NUMBER }" /></td>
            <td><c:out value="${ elem.date }" /></td>
            <td><c:out value="${ elem.sum }" /></td>
            <td><c:out value="${ elem.sum_paid }" /></td>
            <td><li><a href="<c:url value="/do/clientaddproduct"/>">Вход</a> добавить продукт</li></td>

        </tr>
    </c:forEach>
</table>
<form action="<c:url value="/do/clientlistorder"/>" method="post">
    Number: <input type="text" name="NUMBER">
    <br />
    <input type="submit" value="Submit" />
    </form>
</body>
</html>