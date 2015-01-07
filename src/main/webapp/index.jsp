<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 17.12.2014
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome MyWeb</title>
</head>
<body>
<form accept-charset="UTF-8" />
<a class="g-switcher jsxComponents-ToggleBlock-Switcher m-switcher_strong" data-qa="main-login-switcher" href="/do/login">

    Вход в личный кабинет Интернет магазин

</a>
</form>
Please, <a href="${pageContext.request.contextPath}/do/login">log in</a>

<jsp:text>Hello, Bender</jsp:text>
<%  %>
${ header["host"] }
<a> servletName=  ${pageContext.servletConfig.servletName} </a>
<%= new java.util.Date() %>
<a> <!-- comment [ <%= (new java.util.Date()).toLocaleString()  %> ] --></a>

<form action="do/login" method="post">

    Введите что-нибудь:<br/>
    <input type="text" name="anything" value="" /><br/>
    <input type="submit" value="Отправить" /><br/>
</form>

</body>
</html>
