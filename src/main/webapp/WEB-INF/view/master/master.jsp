
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="masterPage"/></title>
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
    <a href=${pageContext.request.contextPath}/view/master><fmt:message key="allServices"/></a>
</div>
<div style="font-size: 16px; text-align: end;">
    <a href=${pageContext.request.contextPath}/view/logout><fmt:message key="logout"/></a>
    <a class="btn" href="${pageContext.request.contextPath}/view/master?language=UA">UA</a>
    <a class="btn" href="${pageContext.request.contextPath}/view/master?language=EN">EN</a>
    </a>
</div>
<caption><h2 align="center"><fmt:message key="listOfOrders"/></h2></caption>
<div>
    <table class="tableService sortable"
           align="center"
    <%--        border="1" cellpadding="5"--%>
           id="sortTableOrders" data-filter-control="false" data-show-search-clear-button="true"
    >

        <thead>
        <tr>
            <th data-sortable="true"><fmt:message key="id"/></th>
            <th data-sortable="true"><fmt:message key="clientName"/></th>
            <th data-sortable="true"><fmt:message key="serviceName"/></th>
            <th data-sortable="true"><fmt:message key="status"/></th>
            <th data-sortable="false"><fmt:message key="reservationTime"/></th>
            <th data-sortable="false"><fmt:message key="serviceDuration"/></th>
            <th data-sortable="false"><fmt:message key="changeStatus"/></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="list" items="${masterList}">
            <tr>
                <th>${list.id}</th>
                <th>${list.clientName}</th>
                <th>${list.serviceName}</th>
                <th>${list.status}</th>
                <th>${list.reservationTime}</th>
                <th>${list.serviceDuration}</th>

                <th><a
                    <%--TODO CSS--%>
                        class="scroll-animite btn"

                        href="${pageContext.request.contextPath}/view/master/changeStatus?accept=${list.id}"><fmt:message
                        key="accept"/></a>

                </th>

            </tr>


        </c:forEach>
        </tbody>
    </table>
</div>

<script>$('#sortTableOrders').DataTable({
    searching: true, info: false,
    "oLanguage": {
        "sLengthMenu": "<fmt:message
                        key="onPage"/>",
        "sSearch": "<fmt:message
                        key="search"/>",

    },
});
</script>

</body>
</html>
