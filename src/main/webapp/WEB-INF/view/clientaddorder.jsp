
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
            <td><c:out value="${ elem.name }" /></td>
            <td><c:out value="${ elem.price }" /></td>
            <td><c:out value="${ elem.insertDate }" /></td>

        </tr>
    </c:forEach>
</table>
<form action="<c:url value="/do/clientaddorder"/>" method="post">
    Name: <input type="text" name="name">
    <br />
    Price: <input type="text" name="quantity" />
    <input type="submit" value="Submit" />
</body>
</html>