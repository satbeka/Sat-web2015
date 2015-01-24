<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%! int b; %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="indexcontent"/>
<html lang="${language}">
<head>
    <title><fmt:message key="labelAdministratorclientlist.title"/></title>
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

<form id="markclientlist" action="<c:url value="/do/administratorclientlist"/>" method="POST">
    <table>
        <c:forEach var="elem" items="${lst}">
            <tr>

                <td><c:out value="${ elem.id }"/></td>
                <td><c:out value="${ elem.name }"/></td>
                <td><c:out value="${ elem.insertDate }"/></td>
                <td><c:out value="${ elem.blackList }"/></td>
                <td><input type="checkbox" name="MarkId"
                        <c:if test="${ elem.blackList==1 }">
                            checked="checked"
                        </c:if>
                           value="${elem.id}"/> <fmt:message key="labelAdministratorclientlist.body1"/></td>


            </tr>
        </c:forEach>
    </table>
</form>
<tr>
    <button type="submit" class="button" form="markclientlist"><fmt:message
            key="labelAdministratorclientlist.button"/></button>
</tr>

</body>
</html>