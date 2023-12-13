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
    <title><fmt:message key="mainPage"/></title>
    <style>
        th, td {
            border-style: groove;
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
</head>

<body>


<div style="font-size: 16px; text-align: start;">
    <a href=${pageContext.request.contextPath}/view/main> <fmt:message key="mainPage"/></a>
    <a href=${pageContext.request.contextPath}/view/main/master><fmt:message key="masterPage"/></a>
</div>
<div style="font-size: 16px; text-align: end;">
    <a>
        <c:if test="${requestScope.notLoggedUser}">
            <a href=${pageContext.request.contextPath}/view/login><fmt:message key="login"/></a>
        </c:if>
        <c:if test="${requestScope.logged}">
            <a href=${pageContext.request.contextPath}/view/logout><fmt:message key="logout"/></a>
        </c:if>

        <a class="btn" href="${pageContext.request.contextPath}/view/main?language=UA">UA</a>
        <a class="btn" href="${pageContext.request.contextPath}/view/main?language=EN">EN</a>
    </a>
</div>

<c:if test="${requestScope.notLogged}">
    <div class="w3-container">
        <fmt:message key="notLogged"/>
    </div>
</c:if>

<c:if test="${requestScope.noMasterForDay}">
    <div class="w3-container">
        <fmt:message key="noMasterForDay"/>
    </div>
</c:if>
<caption><h2 align="center"><fmt:message key="listOfServices"/></h2></caption>
<div>
    <table class="tableService sortable"
           align="center"
    <%--        border="1" cellpadding="5"--%>
           id="sortTableServices" data-filter-control="false" data-show-search-clear-button="true"
    >
        <thead>
        <tr>
            <th data-sortable="false"><fmt:message key="id"/></th>
            <th data-sortable="true"><fmt:message key="name"/></th>
            <th data-sortable="false"><fmt:message key="description"/></th>
            <th data-sortable="true"><fmt:message key="price"/></th>
            <th data-sortable="true"><fmt:message key="duration"/></th>
            <th data-sortable="false"><fmt:message key="order"/></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="list" items="${servicesList}">
            <tr>
                <th>${list.id}</th>
                <th>${list.name}</th>
                <th>${list.description}</th>
                <th>${list.price}</th>
                <th>${list.duration}</th>
                <th><a
                    <%--TODO CSS--%>
                    <%--                class="scroll-animite btn"--%>

                        href="${pageContext.request.contextPath}/view/order?service=${list.name}"><fmt:message
                        key="order"/></a></th>
            </tr>

        </c:forEach>
        </tbody>
    </table>
    <script>$('#sortTableServices').DataTable({
        searching: true, info: false,
        "oLanguage": {
            "sLengthMenu": "<fmt:message
                        key="onPage"/>",
            "sSearch": "<fmt:message
                        key="search"/>",

        },
    });
    </script>


</div>

</body>
</html>
