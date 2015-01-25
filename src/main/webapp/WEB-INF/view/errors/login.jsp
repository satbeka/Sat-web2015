<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Login or Password incorrect </title>

    <p><b>Request URI:</b> ${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}</p><br/>
</head>
<body>

<jsp:text>Login or Password incorrect! Please, Try again!</jsp:text>
${loginnotcorrect}
<br/>
${passwordnotcorrect}
<br/>
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