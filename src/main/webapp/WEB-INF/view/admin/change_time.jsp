<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="changeTimeInfo"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
</head>

<body>


<div style="font-size: 16px; text-align: start;">
    <a href=${pageContext.request.contextPath}/view/admin> <fmt:message key="mainPage"/></a>
</div>

<c:if test="${requestScope.dateIsInvalid}">
    <div class="w3-container">
        <fmt:message key="dateIsInvalid"/>
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/view/admin/changeTime" class="w3-container" align="center">
    <label for="date"><fmt:message key="chooseDate"/></label>
    <input type="date" id="date" name="date"
           min=2022-01-01 max="2031-12-31">
    <input type="submit">
</form>

</body>
</html>
