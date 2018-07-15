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
    <spam>标题:${title}</spam>
    <br>
    <span>消息: ${message} </span>


    <c:url value="/logout" var="logoutUrl"/>

    <form action="${logoutUrl}" method="post" id="logoutFrom">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>


    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <span>
                Welcome : ${pageContext.request.userPrincipal.name} | <a
                href="javascript:formSubmit()"> Logout</a>

        </span>
    </c:if>


</div>

</body>
<script>
    function formSubmit() {
        document.getElementById("logoutFrom").submit();
    }
</script>
</html>
