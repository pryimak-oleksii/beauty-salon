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
<caption><h2 align="center"><fmt:message key="listOfMasters"/></h2></caption>
<div>
    <table class="tableService sortable"
           align="center"
    <%--        border="1" cellpadding="5"--%>
           id="sortTableMasters" data-filter-control="false" data-show-search-clear-button="true"
    >
        <thead>
        <tr>
            <th data-sortable="true"><fmt:message key="id"/></th>
            <th data-sortable="true"><fmt:message key="name"/></th>
        <th data-sortable="true"><fmt:message key="rating"/></th>
        <th data-sortable="false"><fmt:message key="schedule"/></th>
        <th data-sortable="false"><fmt:message key="order"/></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="map" items="${mastersMap}">
        <tr>
            <th>${map.key.id}</th>
            <th>${map.key.name}</th>
            <th>${map.key.rating}</th>
            <th>${map.key.workingDays}</th>
            <th>                   <a
                        <%--TODO CSS--%>
                        <%--                class="scroll-animite btn"--%>

                            href="${pageContext.request.contextPath}/view/order?master=${map.key.name}"><fmt:message
                            key="order"/></a>


            </th>
        </tr>

    </c:forEach>
    </tbody>
</table>
    <script>$('#sortTableMasters').DataTable({
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


<%--<li><a--%>
<%--&lt;%&ndash;                    class="btnSort"&ndash;%&gt;--%>
<%--><fmt:message key="sort"/> <i--%>

<%--&lt;%&ndash;                    class="fa fa-angle-down"&ndash;%&gt;--%>
<%--></i></a>--%>
<%--    <ul--%>
<%--    &lt;%&ndash;                        class="subSort"&ndash;%&gt;--%>
<%--    >--%>

<%--        <li>--%>
<%--            <a href="${pageContext.request.contextPath}/view/main?sort=name"><fmt:message--%>
<%--                    key="name"/></a>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <a href="${pageContext.request.contextPath}/view/main?sort=rating"><fmt:message--%>
<%--                    key="rating"/></a>--%>
<%--        </li>--%>

<%--    </ul>--%>

<%--</li>--%>
<%--&lt;%&ndash;        class="serviceBlock">&ndash;%&gt;--%>
<%--<div--%>
<%--&lt;%&ndash;            class="mainmenuSort">&ndash;%&gt;--%>
<%--<div--%>
<%--&lt;%&ndash;                class="topSort">&ndash;%&gt;--%>

<%--</div>--%>
<%--</div>--%>

</body>
</html>
