<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="adminPage"/></title>
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

<div style="font-size: 16px; text-align: end;">
    <a href=${pageContext.request.contextPath}/view/logout><fmt:message key="logout"/></a>
    <a class="btn" href="${pageContext.request.contextPath}/view/admin?language=UA">UA</a>
    <a class="btn" href="${pageContext.request.contextPath}/view/admin?language=EN">EN</a>
    </a>
</div>

<c:if test="${requestScope.noMasterForDay}">
    <div class="w3-container">
        <fmt:message key="noMasterForDay"/>
    </div>
</c:if>

<h2 align="center"><fmt:message key="listOfOrders"/></h2>


<table
        class="tableService sortable"
        align="center"
<%--        border="1" cellpadding="5"--%>
        id="sortTableOrders" data-filter-control="false" data-show-search-clear-button="true"
>
    <thead>
    <tr>
        <th data-sortable="false"><fmt:message key="id"/></th>
        <th data-sortable="true"><fmt:message key="clientName"/></th>
        <th data-sortable="true"><fmt:message key="masterName"/></th>
        <th data-sortable="true"><fmt:message key="serviceName"/></th>
        <th data-sortable="true"><fmt:message key="status"/></th>
        <th data-sortable="false"><fmt:message key="reservationTime"/></th>
        <th data-sortable="false"><fmt:message key="changeStatus"/></th>
        <th data-sortable="false"><fmt:message key="changeTime"/></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="list" items="${ordersList}">
    <tr>
        <th>${list.id}</th>
        <th>${list.clientName}</th>
        <th>${list.masterName}</th>
        <th>${list.serviceName}</th>
        <th>${list.status}</th>
        <th>${list.reservationTime}</th>
        <th><a
            <%--TODO CSS--%>
                class="scroll-animite btn"

                href="${pageContext.request.contextPath}/view/admin/changeStatus?decline=${list.id}"><fmt:message
                key="cancel"/></a>
            <a
                <%--TODO CSS--%>
                    class="scroll-animite btn"

                    href="${pageContext.request.contextPath}/view/admin/changeStatus?payment=${list.id}"><fmt:message
                    key="payment"/></a>
                <%--            <a--%>
                <%--                &lt;%&ndash;TODO CSS&ndash;%&gt;--%>
                <%--                    class="scroll-animite btn"--%>

                <%--                    href="${pageContext.request.contextPath}/view/admin/changeTime?date=${list.id}"><fmt:message key="changeTime"/></a>--%>


        </th>
        <th>
                <%--            <form action="/action_page.php">--%>
                <%--            <label for="birthdaytime"><fmt:message key="changeTime"/></label>--%>
                <%--            <input type="datetime-local" id="birthdaytime" name="birthdaytime">--%>
                <%--            <input type="submit">--%>
                <%--        </form>--%>
                <%--    <form action="${pageContext.request.contextPath}/view/admin/changeTime?id=${list.id}">--%>
                <%--        <label for="changeTime"><fmt:message key="changeTime"/></label>--%>
                <%--        <input type="datetime-local" id="changeTime" name="changeTime">--%>
                <%--        <input type="submit">--%>
                <%--    </form>--%>

            <a
                <%--TODO CSS--%>
                    class="scroll-animite btn"

                    href="${pageContext.request.contextPath}/view/admin/changeTime?orderNumber=${list.id}"><fmt:message
                    key="changeTime"/></a>


                <%--            <form class="w3-container"--%>
                <%--&lt;%&ndash;                  align="center" method="post"&ndash;%&gt;--%>
                <%--                  action="${pageContext.request.contextPath}/view/admin/changeTime?timeStatus=${list.id}">--%>
                <%--                        <p>--%>

                <%--                    <label>--%>
                <%--                        <input--%>
                <%--                            &lt;%&ndash;                    TODO ADD CSS&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                    class="profileEditorFields loginField" &ndash;%&gt;--%>
                <%--                                type="datetime-local" required--%>
                <%--                                placeholder="<fmt:message key="changeTime"/>"--%>
                <%--                                name="changeTime">--%>
                <%--                    </label>--%>
                <%--                </p>--%>
                <%--                <div>--%>
                <%--                    <input--%>
                <%--                        &lt;%&ndash;                    TODO ADD CSS&ndash;%&gt;--%>
                <%--                        &lt;%&ndash;                    class="profileEditorFields loginField" &ndash;%&gt;--%>
                <%--                        &lt;%&ndash;                    class="btn editProfileBtn" &ndash;%&gt;--%>
                <%--                        &lt;%&ndash;                    id="loginSubmitBtn" &ndash;%&gt;--%>
                <%--                            type="submit"--%>
                <%--                            value="<fmt:message key="changeTime"/>">--%>
                <%--                </div>--%>

                <%--            </form>--%>

        </th>
    </tr>


    </c:forEach>
    </tbody>
</table>

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
