<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="registration"/></title>
</head>

<body>

<div style="font-size: 16px; text-align: end;">
    <a class="btn" href="${pageContext.request.contextPath}/view/registration?language=UA">UA</a>
    <a class="btn" href="${pageContext.request.contextPath}/view/registration?language=EN">EN</a>
</div>
<form class="w3-container" align="center" method="post"
      action="${pageContext.request.contextPath}/view/registration">
    <p>
        <label>
            <input
            <%--                    TODO ADD CSS--%>
            <%--                    class="profileEditorFields loginField" --%>
                    type="text" required
            <%--                       placeholder="email"--%>
                    placeholder="<fmt:message key="name"/>"
                    name="name">
        </label>
    </p>

    <p>
        <label>
            <input
            <%--                    TODO ADD CSS--%>
            <%--                    class="profileEditorFields loginField" --%>
                    type="text" required
            <%--                       placeholder="email"--%>
                    placeholder="<fmt:message key="surname"/>"
                    name="surname">
        </label>
    </p>


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


    <p>
        <label>
            <input
            <%--                    TODO ADD CSS--%>
            <%--                    class="profileEditorFields loginField" --%>
                    type="password" required
                    placeholder="<fmt:message key="repeatPassword"/>"
                    name="repeatPassword">
        </label>
    </p>

    <div>
        <input
        <%--                    TODO ADD CSS--%>
        <%--                    class="profileEditorFields loginField" --%>
        <%--                    class="btn editProfileBtn" --%>
        <%--                    id="loginSubmitBtn" --%>
                type="submit"
                value="<fmt:message key="registration"/>">
    </div>

    <c:if test="${requestScope.notFound}">
        <div class="w3-container">
            <p><fmt:message key="invalidData"/></p>
        </div>
    </c:if>



    <c:if test="${requestScope.incorrectEmailOrPass}">
        <div class="w3-container">
            <p><fmt:message key="incorrectEmailOrPass"/></p>
        </div>
    </c:if>

    <c:if test="${requestScope.wrongData}">
        <div class="w3-container">
            <fmt:message key="incorrectData"/>
        </div>
    </c:if>

</form>


</body>
</html>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Registration Page</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form action="${pageContext.request.contextPath}/view/registration">--%>
<%--    Name:--%>
<%--    <p>--%>
<%--        <label>--%>
<%--            <input class="profileEditorFields loginField" type="text" required--%>
<%--                   placeholder=Name--%>
<%--                   name="name">--%>
<%--        </label>--%>
<%--    </p>--%>

<%--    Surname:--%>
<%--    <p>--%>
<%--        <label>--%>
<%--            <input class="profileEditorFields loginField" type="text" required--%>
<%--                   placeholder=Surname--%>
<%--                   surname="surname">--%>
<%--        </label>--%>
<%--    </p>--%>

<%--    Password:--%>
<%--    <p>--%>
<%--        <label>--%>
<%--            <input class="profileEditorFields loginField" type="password" required--%>
<%--                   placeholder=Password--%>
<%--                   name="password">--%>
<%--        </label>--%>
<%--    </p>--%>
<%--    Repeat password:--%>
<%--    <p>--%>
<%--        <label>--%>
<%--            <input class="profileEditorFields loginField" type="password" required--%>
<%--                   placeholder=Password--%>
<%--                   name="password">--%>
<%--        </label>--%>
<%--    </p>--%>

<%--    Enter your e-mail:--%>
<%--    <p>--%>
<%--        <label>--%>
<%--            <input--%>
<%--                   placeholder=E-mail--%>
<%--                   name="email">--%>

<%--        </label>--%>
<%--    </p>--%>

<%--    <a href=${pageContext.request.contextPath}/done.jsp>--%>
<%--        <button>Register</button>--%>
<%--    </a>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>