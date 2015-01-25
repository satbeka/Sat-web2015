<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sorry MyWeb</title>
</head>
<body>

<jsp:text>Sorry, you try to decide problem! Please, Try again later!</jsp:text>
${ header["host"] }
<a> servletName= ${pageContext.servletConfig.servletName} </a>

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