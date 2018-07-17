<%--
  Created by IntelliJ IDEA.
  User: YHJ
  Date: 2018/7/16
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="static/css/header/header.css" rel="stylesheet">
<link href="static/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
<div class="header">
    <div class="top">
        <div class="warp">
            <div class="welcome">
                ${welcome}
            </div>

            <div class="line">
                <span class="connect"> 24小时咨询热线 </span>
                <span class="glyphicon glyphicon-send" aria-hidden="true"></span> ${tel} <span> |  联系我们</span>
            </div>
            <div>
                <span class="manage">管理</span>

            </div>
        </div>
    </div>

    <div class="nva navbar-fixed-top">
        <div class="warp">
            <div class="logo">
                这里是一个 logo
            </div>
            <div class="menu">
            </div>
        </div>
    </div>
</div>
<script src="static/js/jquery/jquery-2.0.0.min.js"></script>
<script src="static/js/header/header.js"></script>



