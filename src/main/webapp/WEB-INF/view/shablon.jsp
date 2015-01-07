<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 21.12.2014
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="MyServlet" method="post">
    <input type="hidden" name="command" value="forward" />
    Введите что-нибудь:<br/>
    <input type="text" name="anything" value="" /><br/>
    <input type="submit" value="Отправить" /><br/>
</form>

</body>
</html>
