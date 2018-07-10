<%--
  Created by IntelliJ IDEA.
  User: yhj
  Date: 7/10/18
  Time: 8:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin</title>
</head>
<body>
<div>
    <h1>标题:${title}</h1>
    <h1>消息: ${message} </h1>
    <c:if test="${pageContext.request.userPrincial.name != null}">
        <h2>欢迎: ${pageContext.request.userPrincipal.name}
            | <a href="<c:url value="/j_spring_security_logout" />"> Logout</a></h2>
    </c:if>
</div>

</body>
</html>
