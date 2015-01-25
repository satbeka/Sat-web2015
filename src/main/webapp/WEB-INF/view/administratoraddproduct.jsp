<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="indexcontent"/>
<html lang="${language}">
<head>
    <title><fmt:message key="labelAdministratoraddproduct.title"/></title>
</head>
<body>
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
<li><fmt:message key="labelAdministratoraddproduct.body1"/></li>
<table>
    <c:forEach var="elem" items="${lst}">
        <tr>

            <td><c:out value="${ elem.id }"/></td>
            <td><c:out value="${ elem.name }"/></td>
            <td><c:out value="${ elem.price }"/></td>
            <td><c:out value="${ elem.insertDate }"/></td>

        </tr>
    </c:forEach>
</table>
<li><fmt:message key="labelAdministratoraddproduct.body2"/></li>
<form action="<c:url value="/do/administratoraddproduct"/>" method="post">
    <fmt:message key="labelAdministratoraddproduct.body3"/> <input type="text" name="name">
    <br/>
    <fmt:message key="labelAdministratoraddproduct.body4"/> <input type="text" name="price"/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>