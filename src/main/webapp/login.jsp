<%--
  Created by IntelliJ IDEA.
  User: yhj
  Date: 7/10/18
  Time: 1:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登陆</title>
</head>
<body>
<div class="error ${param.error == true ? '' : 'hide'}">
    登陆失败<br>
    ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
</div>
<%--
    特别要注意的是form表单的action是提交登陆信息的地址，这是security内部定义好的，
    同时自定义form时，要把form的action设置为/j_spring_security_check。
    注意这里要使用绝对路径，避免登陆页面存放的页面可能带来的问题。
--%>
<c:url var="loginUrl" value="/login" />
<form method="post" action="${loginUrl}"
      style="width:260px; text-align: center">
    <fieldset>
        <legend>新页面-登陆</legend>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <%--j_username，输入登陆名的参数名称，j_password，输入密码的参数名称，这两个正常情况下也不会修改--%>
        用户： <input type="text" name="username" style="width: 150px;"
                   value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"/><br/>
        密码： <input type="password" name="password" style="width: 150px;"/><br/>
        <%--
            _spring_security_remember_me，选择是否允许自动登录的参数名称。
            可以直接把这个参数设置为一个checkbox，无需设置value，Spring Security会自行判断它是否被选中，
            这也是security内部提供的，只需要配置，不需要自己实现。
        --%>

        <input type="checkbox" name="_spring_security_remember_me"/>两周之内不必登陆<br/>
        <input type="submit" value="登陆"/>
        <input type="reset" value="重置"/>
    </fieldset>
</form>
</body>
</html>
