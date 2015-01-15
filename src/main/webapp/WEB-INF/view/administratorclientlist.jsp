
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>List of Client for Administrator</title>
</head>
<body>
${user33}
<br/>
<a href="logout">Logout</a>
<table>
    <c:forEach var="elem" items="${lst}">
        <tr>

            <td><c:out value="${ elem.id }" /></td>
            <td><c:out value="${ elem.name }" /></td>
            <td><c:out value="${ elem.insertDate }" /></td>
            <td><c:out value="${ elem.blackList }" /></td>
            <td><input type="checkbox" name="mark" checked="checked"  /> Mark</td>



        </tr>
    </c:forEach>
</table>
<form action="<c:url value="/do/administratorclientlist"/>" method="POST">
      <input type="submit" value="Save List" />
</body>
</html>