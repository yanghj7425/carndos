<%--
  Created by IntelliJ IDEA.
  User: YHJ
  Date: 2018/7/10
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>dba Page</title>
</head>
<body>

<c:url value="/logout" var="logoutUrl"/>
<form action="${logoutUrl}" method="post" id="logoutFrom">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

this is dba Page |

<c:if test="${pageContext.request.userPrincipal.name != null}">

    <span> ${pageContext.request.userPrincipal.name}</span>| <a href="javaScript:formSubmit()">Logout</a>
    
</c:if>

</body>
<script>
    function formSubmit() {
        document.getElementById("logoutFrom").submit();
    }
</script>
</html>
