<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<meta charset="utf-8">
<html>
<head>
    <title><fmt:message key="login"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
</head>

<body>

<div style="font-size: 16px; text-align: end;">
    <a class="btn" href="${pageContext.request.contextPath}/view/login?language=UA">UA</a>
    <a class="btn" href="${pageContext.request.contextPath}/view/login?language=EN">EN</a>
</div>


<form class="w3-container" align="center" method="post"
      action="${pageContext.request.contextPath}/view/login">
    <p>
        <label>
            <input
            <%--                    TODO ADD CSS--%>
            <%--                    class="profileEditorFields loginField" --%>
                    type="text" required
            <%--                       placeholder="email"--%>
                    placeholder="<fmt:message key="email"/>"
                    name="email">
        </label>
    </p>

    <p>
        <label>
            <input
            <%--                    TODO ADD CSS--%>
            <%--                    class="profileEditorFields loginField" --%>
                    type="password" required
                    placeholder="<fmt:message key="password"/>"
                    name="password">
        </label>
    </p>

    <div>
        <input
        <%--                    TODO ADD CSS--%>
        <%--                    class="profileEditorFields loginField" --%>
        <%--                    class="btn editProfileBtn" --%>
        <%--                    id="loginSubmitBtn" --%>
                type="submit"
                value="<fmt:message key="login"/>">


    </div>

    <a class="href" href="${pageContext.request.contextPath}/view/registration"><fmt:message key="registration"/></a>

        <c:if test="${requestScope.notFound}">
            <div class="w3-container">
                <p><fmt:message key="invalidData"/></p>
            </div>
        </c:if>

        <c:if test="${requestScope.wrongData}">
            <div class="w3-container">
                <fmt:message key="incorrectData"/>
            </div>
        </c:if>
    <c:if test="${requestScope.wrongPassword}">
        <div class="w3-container">
            <fmt:message key="incorrectData"/>
        </div>
    </c:if>


    </form>


</body>
</html>
