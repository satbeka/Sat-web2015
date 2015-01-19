
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            
            <td><c:out value="${ elem.number }" /></td>
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${ elem.insertDate }" /></td>
            <td><c:out value="${ elem.sum }" /></td>
            <td><c:out value="${ elem.sumPaid }" /></td>
            <td><li><a href="<c:url value="/do/clientaddproduct?orderId=${ elem.id }"/>">Вход</a> добавить продукт в Ордер</li></td>
            <td>
                <form method="GET" action="<c:url value="/do/clientaddproduct"/>">
                <input type="hidden" name="orderId" value="${ elem.id }">
                <button type="submit" class="Order">добавить продукт в Ордер</button>
                </form>
            </td>

            <td><c:out value="orderId=${ elem.id }"/></td>
        </tr>
    </c:forEach>
</table>
<form action="<c:url value="/do/clientlistorder"/>" method="post">
    Укажите, Ваш Номер ордера (или система создаст его сама): <input type="text" name="NUMBER">
    <br />
    <input type="submit" value="Submit" />
    </form>
</body>
</html>