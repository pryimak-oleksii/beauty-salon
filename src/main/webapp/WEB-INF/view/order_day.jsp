<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexsey
  Date: 31.08.2022
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>


<html>
<head>
    <title><fmt:message key="orderDate"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
</head>
<body>

<div style="font-size: 16px; text-align: end;">
    <a href=${pageContext.request.contextPath}/view/logout>Logout</a>
    <a class="btn" href="${pageContext.request.contextPath}/view/order?language=UA">UA</a>
    <a class="btn" href="${pageContext.request.contextPath}/view/order?language=EN">EN</a>
    </a>
</div>


<c:if test="${requestScope.dateIsInvalid}">
    <div class="w3-container">
        <fmt:message key="dateIsInvalid"/>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/view/order/date" class="w3-container" align="center">
    <label for="date"><fmt:message key="chooseDate"/></label>
    <input type="date" id="date" name="date"
           min=2022-01-01 max="2031-12-31">
    <input type="submit">
</form>


</body>
</html>
