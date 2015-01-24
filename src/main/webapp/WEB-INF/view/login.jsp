<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="indexcontent"/>
<html lang="${language}">
<html>
<head>
    <title><fmt:message key="labelLogin.title1" /></title>
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="es" ${language == 'es' ? 'selected' : ''}>Español</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>

    </select>
</form>
<li><fmt:message key="labelLogin.title2" /></li>
<section>

    <div>

            <form action="<c:url value="/do/login"/>" method="post">
                <input type="hidden" name="command1" value="login55" />
                <input type="text" name="login" value="" /><br/>
                <input type="text" name="password" value="" /><br/>
                <input type="submit"
                       value="<fmt:message key="labelLogin.button" />" /><br/>
                ${wrongAction}
                <br/>

            </form>



    </div>

    </div>
</section>


</body>
</html>
