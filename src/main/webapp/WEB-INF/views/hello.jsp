<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: yhj
  Date: 7/10/18
  Time: 8:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span>标题: ${title}</span> <br>
<span> 消息: ${message}</span><br>
<span>用户名: ${userName}</span>

<sec:authorize access="isFullyAuthenticated()">
    <label><a href="#">Create New User</a> | <a href="#">View existing Users</a></label>
</sec:authorize>
<sec:authorize access="isRememberMe()">
    <label><a href="#">View existing Users</a></label>
</sec:authorize>
</body>
</html>
