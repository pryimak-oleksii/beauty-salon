<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
</head>


<body>

<a href=${pageContext.request.contextPath}/view/login>Login</a>
<a href=${pageContext.request.contextPath}/view/main>MainPage</a>

<div
        <%--TODO CSS--%>
<%--        class="mainmenu"--%>
>
    <ul>
        <li><a
        <%--TODO CSS--%>
<%--                class="scroll-animite btn"--%>

               href="${pageContext.request.contextPath}/view/registration"><fmt:message key="registration"/></a></li>
        <li><a

<%--                class="scroll-animite btn"--%>
        <%--TODO CSS--%>
               href="${pageContext.request.contextPath}/view/login"><fmt:message key="login"/></a></li>
      </ul>
</div>

</body>
</html>