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
    <title> Login or Password incorrect </title>
</head>
<body>

<jsp:text>Login or Password incorrect! Please, Try again! </jsp:text>
${ header["host"] }
<a> servletName=  ${pageContext.servletConfig.servletName} </a>

Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.exception}
<br/>
Message from exception: ${pageContext.exception.message}
</body>
</html>