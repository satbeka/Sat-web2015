
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
            <td><li><a href="<c:url value="/do/clientaddproduct?orderId=${ elem.id }"/>">Вход</a>  Корзина в Ордере  </li></td>
            <td><c:out value="  orderId=${ elem.id }"/></td>

            <td>
                <c:if test="${empty elem.sumPaid }">
                    <c:out value=" sumPaid=0.00"/>
                </c:if>
                <c:if test="${not empty elem.sumPaid }">
                    <c:out value=" sumPaid=${ elem.sumPaid }"/>
                </c:if>

            </td>
            <td>
                <form method="POST" action="<c:url value="/do/clientlistorder"/>">
                    <input type="hidden" name="orderId" value="${ elem.id }">
                    <input type="hidden" name="sumPaid" value="${ elem.sumPaid }">
                    <button type="submit" class="Order">  Оплатить Ордер </button>
                </form>
            </td>
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