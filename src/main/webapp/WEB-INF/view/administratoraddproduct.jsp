
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title> Products for Administrator </title>
</head>
<body>
${user33}
<br/>
<a href="logout">Logout</a>
<li> Current Products for Administrator </li>
<table>
    <c:forEach var="elem" items="${lst}">
        <tr>

            <td><c:out value="${ elem.id }" /></td>
            <td><c:out value="${ elem.name }" /></td>
            <td><c:out value="${ elem.price }" /></td>
            <td><c:out value="${ elem.insertDate }" /></td>

        </tr>
    </c:forEach>
</table>
<li> Add the New Product </li>
<form action="<c:url value="/do/administratoraddproduct"/>" method="post">
    Name: <input type="text" name="name">
    <br />
    Price: <input type="text" name="price" />
    <input type="submit" value="Submit" />
</body>
</html>