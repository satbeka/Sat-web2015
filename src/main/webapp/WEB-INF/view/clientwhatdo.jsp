<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="indexcontent"/>
<html lang="${language}">
<head>
    <title><fmt:message key="labelclientwhatdo.title"/></title>
</head>

<body>

<header>

    ${user33}
    <br/>

    <form>
        <select id="language" name="language" onchange="submit()">
            <option value="es" ${language == 'es' ? 'selected' : ''}>Español</option>
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>

        </select>
    </form>
    <a href="logout"><fmt:message key="label.logout"/></a>
    </div>

    <ul>
        <li><a href="/do/clientlistorder" class="login-button"><fmt:message key="label.MyClick"/> </a><fmt:message
                key="labelclientwhatdo.body1"/></li>
    </ul>


    </div>


    </div>
</header>

</div>


</body>
</html>
