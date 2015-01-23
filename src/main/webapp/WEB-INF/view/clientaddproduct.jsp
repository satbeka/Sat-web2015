<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Products in the Order</title>
</head>
<body>
${user33}
<br/>
<a href="logout">Logout</a>
<li><c:out value="orderId=${orderId}"/></li>
<li><c:out value="clientId=${clientId}"/></li>
<form id="productlist" action="<c:url value="/do/clientaddproduct"/>" method="post">
<table>
    <c:forEach var="elem" items="${productList}">
        <tr>

            <td><c:out value="${ elem.id }"/></td>
            <td><c:out value="${ elem.name }"/></td>
            <td><c:out value="${ elem.price }"/></td>
            <td>  Quantity:  <input type="text" name="quantity" value="${ elem.quantity }">
            </td>
            <td>
                <input type="hidden" type="text" name="productId"
                       value="${elem.id}"
                        >
            </td>
            <td>
                <input type="checkbox" name="markId"
                        <c:if test="${ elem.quantity!=0 }">checked="checked"</c:if>
                       value="${elem.id}"
                        />
            </td>


        </tr>
    </c:forEach>
</table>
</form>
<tr>
    <button type="submit" class="button" form="productlist"> Save Products  </button>
</tr>
</body>
</html>