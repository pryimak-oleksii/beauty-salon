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
    <a href=${pageContext.request.contextPath}/view/admin> <fmt:message key="mainPage"/></a>
</div>

<caption><h2 align="center"><fmt:message key="chooseNewTime"/></h2></caption>

<div>
    <table class="tableService sortable"
           align="center"
    <%--        border="1" cellpadding="5"--%>
           id="sortTableSlots" data-filter-control="false" data-show-search-clear-button="true"
    >

        <thead>
        <tr>
            <th data-sortable="true"><fmt:message key="time"/></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="list" items="${mastersTimeslotList}">
        <tr>
            <th>
                <a
                    <%--TODO CSS--%>
                    <%--                class="scroll-animite btn"--%>

                        href="${pageContext.request.contextPath}/view/admin/changeTime/accept?newTimeForOrder=${list}">${list}</a>

            </th>
            </c:forEach>
        </tbody>
    </table>
</div>


<%--<script>$('#sortTableSlots').DataTable({--%>
<%--    searching: true, info: false,--%>
<%--    "oLanguage": {--%>
<%--        "sLengthMenu": "<fmt:message--%>
<%--                        key="onPage"/>",--%>
<%--        "sSearch": "<fmt:message--%>
<%--                        key="search"/>",--%>

<%--    },--%>
<%--});--%>
<%--</script>--%>

</body>
</html>
