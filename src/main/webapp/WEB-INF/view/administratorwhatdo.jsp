<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="indexcontent"/>
<html lang="${language}">
<html>
<head>
    <title><fmt:message key="labelAdministratorwhatdo.title"/></title>
</head>

<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="es" ${language == 'es' ? 'selected' : ''}>Español</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>

    </select>
</form>
<header>

    ${user33}
    <br/>
    <a href="logout"><fmt:message key="label.logout"/></a>

    </div>

    <ul>
        <li><a href="/do/administratoraddproduct" class="login-button"><fmt:message
                key="label.MyClick"/> </a><fmt:message key="labelAdministratorwhatdo.body1"/></li>
        <li><a href="/do/administratoreditproduct" class="login-button"><fmt:message
                key="label.MyClick"/> </a><fmt:message key="labelAdministratorwhatdo.body2"/></li>
        <li><a href="/do/administratorclientlist" class="login-button"><fmt:message
                key="label.MyClick"/> </a><fmt:message key="labelAdministratorwhatdo.body3"/></li>
    </ul>


    </div>


    </div>
</header>

</div>


</body>
</html>
