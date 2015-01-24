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
    <title><fmt:message key="labelClientlistorder.title"/></title>
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
<table>
    <c:forEach var="elem" items="${orderList}">
        <tr>

            <td><c:out value="${ elem.number }"/></td>
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${ elem.insertDate }"/></td>
            <td>
                <li><a href="<c:url value="/do/clientaddproduct?orderId=${ elem.id }"/>"> <fmt:message
                        key="label.MyClick"/>
                </a> <fmt:message key="labelClientlistorder.body1"/>
                </li>
            </td>
            <td><c:out value="  orderId=${ elem.id }"/></td>

            <td>
                <c:if test="${empty elem.sumPaid }">
                    <fmt:message key="labelClientlistorder.sumPaid"/>=0.00
                </c:if>
                <c:if test="${not empty elem.sumPaid }">
                    <fmt:message key="labelClientlistorder.sumPaid"/> =${ elem.sumPaid }
                </c:if>

            </td>
            <td>
                <form method="POST"
                      action="<c:url value="/do/clientpayorder?order=${ elem.id }&sumPaid=${ elem.sumPaid }"/>">
                    <input type="hidden" name="Order Id" value="${ elem.id }">
                    <button type="submit" class="Order"><fmt:message key="labelClientlistorder.body3"/></button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="<c:url value="/do/clientlistorder"/>" method="post">
    <fmt:message key="labelClientlistorder.body4"/>
    <input type="text" name="NUMBER">
    <br/>
    <input type="submit" value=<fmt:message key="labelClientlistorder.button"/>/>
</form>
</body>
</html>