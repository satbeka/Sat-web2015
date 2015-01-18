<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Products for Client Order</title>
</head>
<body>
${user33}
<br/>
<a href="logout">Logout</a>
<td><c:out value="orderId"/></td>
<table>
    <c:forEach var="elem" items="${productList}">
        <tr>

            <td><c:out value="${ elem.id }"/></td>
            <td><c:out value="${ elem.name }"/></td>
            <td><c:out value="${ elem.price }"/></td>
            <td>
                <li>Quantity: <input type="text" name="quantity"> /></li>
            </td>
            <td>
                <input type="checkbox" name="MarkId"
                        <c:if test="${ elem.mark==1 }">
                            checked="checked"
                        </c:if>
                       value="${elem.id}"
                        /> Mark
            </td>


        </tr>
    </c:forEach>
</table>
<form action="<c:url value="/do/clientaddproduct"/>" method="post">
    <input type="submit" value="Submit">Save</input>
</form>
</body>
</html>