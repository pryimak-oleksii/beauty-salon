<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title><fmt:message key="404"/></title>
</head>
<body>
<%--<h3 align="center"><fmt:message key="404"/></h3>--%>

<style type="text/css" media="screen">
    .container {
        margin: 10px auto;
        max-width: 600px;
        text-align: center;
    }
    h1 {
        margin: 30px 0;
        font-size: 4em;
        line-height: 1;
        letter-spacing: -1px;
    }
</style>

<div class="container">
    <h1>404</h1>

    <p><strong>Page not found :(</strong></p>
    <p>The requested page could not be found.</p>
</div>

</body>
</html>
