<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexsey
  Date: 12.09.2022
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title><fmt:message key="orderDone"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
</head>
<body>

<div style="font-size: 16px; text-align: start;">
    <a href=${pageContext.request.contextPath}/view/main> <fmt:message key="mainPage"/></a>
    <a href=${pageContext.request.contextPath}/view/main/master><fmt:message key="masterPage"/></a>
</div>
<div style="font-size: 16px; text-align: end;">
    <a href=${pageContext.request.contextPath}/view/logout><fmt:message key="logout"/></a>

    <a class="btn" href="${pageContext.request.contextPath}/view/order/done?language=UA">UA</a>
    <a class="btn" href="${pageContext.request.contextPath}/view/order/done?language=EN">EN</a>
    </a>
</div>

<h2 align="center"><fmt:message key="thxForOrder"/></h2>
<h3 align="center"><fmt:message key="orderDetails"/></h3>
<c:forEach var="orderInfo" items="${order}">
    <h4 align="center"><fmt:message key="id"/> ${orderInfo.id}</h4>
    <h4 align="center"><fmt:message key="masterName"/> ${orderInfo.masterName}</h4>
    <h4 align="center"><fmt:message key="serviceName"/>${orderInfo.serviceName}</h4>
    <h4 align="center"><fmt:message key="duration"/>${orderInfo.serviceDuration}</h4>
<%--    <h2 align="center"><fmt:message key="price"/>${orderInfo.price}</h2>--%>
</c:forEach>
<a href=${pageContext.request.contextPath}/view/main> <fmt:message key="mainPage"/></a>
<button onclick="window.print()"><fmt:message key="print"/></button>
</body>
</html>
