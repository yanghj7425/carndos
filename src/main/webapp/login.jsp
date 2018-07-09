<%--
  Created by IntelliJ IDEA.
  User: yhj
  Date: 7/10/18
  Time: 1:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="_csrf_parameter" content="${_csrf.headerName}">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>登录</title>
</head>
<body>
<div class="error ${param.error == true ? '' : 'hide'}">
    登陆失败<br>

    ${param.error == true}
    ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
</div>

<%--


--%>
<form method="post" action="${pageContext.request.contextPath}/j_spring_security_check"
      style="width:260px; text-align: center;">
    <fieldset>

        <legend> 新的登录界面</legend>
        用户: <input type="text" name="j_username" style="width: 150px;"
                   value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"/>
        密码: <input type="password" name="j_password" style="width: 150px;"/>

        <%--


        --%>

        <input type="checkbox" name="_spring_security_remember_me"/> 一段时间内不必登录
        <input type="submit" value="登录"/>
        <input type="reset" value="重置"/>
    </fieldset>

</form>


</body>
</html>
