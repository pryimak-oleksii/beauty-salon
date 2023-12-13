<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div style="font-size: 16px; text-align: start;">
    <a href=${pageContext.request.contextPath}/view/main> <fmt:message key="mainPage"/></a>
    <a href=${pageContext.request.contextPath}/view/main/master><fmt:message key="masterPage"/></a>
</div>
<div style="font-size: 16px; text-align: end;">
    <a href=${pageContext.request.contextPath}/view/login><fmt:message key="login"/></a>

    <a class="btn" href="${pageContext.request.contextPath}/view/main/master?language=UA">UA</a>
    <a class="btn" href="${pageContext.request.contextPath}/view/main/master?language=EN">EN</a>
    </a>
</div>

    <a>not found</a>
</body>
</html>
