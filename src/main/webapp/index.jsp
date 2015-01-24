<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="indexcontent"/>
<html lang="${language}">
<head>
    <title><fmt:message key="label.title" /></title>
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

<br clear="all">
</div>

    <p>
        <fmt:message key="label.body1" />
    </p>

    <h3><fmt:message key="label.body2" /></h3>
    <ul>
        <li><fmt:message key="label.body3" /></li>
        <li><fmt:message key="label.body4" /></li>
        <li>Mark the Client to Blacklist</li>
        <li>Add a new product to the system</li>
        <li>Create a new Order</li>
        <li>Pay for the Order</li>
        <li>Add a new product and quantity to the Order</li>
        <li>View information of Pay sum of the Order</li>
    </ul>
    
    </td>

    <ul>
        <li><a href="<c:url value="/do/login"/>" class="login-button">Sign In </a>  an Account </li>
    </ul>


</div>



</div>
</header>

</div>


</body>
</html>
