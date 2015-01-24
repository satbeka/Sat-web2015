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
    <title><fmt:message key="labelClientaddproduct.title" /></title>
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
<li><c:out value="orderId=${orderId}"/></li>
<li><c:out value="clientId=${clientId}"/></li>
<div>"   "</div>
<div><fmt:message key="labelClientaddproduct.body1"/></div>
<form id="productlist" action="<c:url value="/do/clientaddproduct"/>" method="post">
<table>
    <c:forEach var="elem" items="${productList}">
        <tr>

            <td><c:out value="${ elem.id }"/></td>
            <td><c:out value="${ elem.name }"/></td>
            <td><c:out value="${ elem.price }"/></td>
            <td>  <fmt:message key="labelClientaddproduct.Quantity" /><input type="text" name="quantity" value="${ elem.quantity }">
            </td>
            <td>
                <input type="hidden" type="text" name="productId"
                       value="${elem.id}"
                        >
            </td>
            <td>
                <input type="checkbox" name="markId"
                        <c:if test="${ elem.quantity!=0 }">checked="checked"</c:if>
                       value="${elem.id}"
                        />
            </td>


        </tr>
    </c:forEach>
</table>
</form>
<tr>
    <button type="submit" class="button" form="productlist"><fmt:message key="labelClientaddproduct.button" /></button>
</tr>
</body>
</html>