<%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/28
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="">
<head>
  <meta charset="utf-8">
  <title>用户权限管理系统</title>
  <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: F4606C">
<div id = "header"></div>
<div style="width: 100%;text-align: center;color: white">
  <h2>欢迎访问用户权限管理系统</h2>
</div>

<%
  if(request.getSession().getAttribute("user") !=null) {
%>
<div style="width: 100%">
  <br>
  <div class="btn-group btn-group-lg" style="width: 100%;text-align: center">
    <a href="${pageContext.request.contextPath}/pages/control.jsp">
      <button type="button" class="btn btn-default">管理页面</button>
    </a>
  </div>
</div>
<%
}else {
%>
<div style="width: 100%;text-align: center" class="btn-group btn-group-lg">
  <a href="${pageContext.request.contextPath}/user/login.jsp"><button type="button" class="btn btn-default">登录</button></a>
</div>
<%
  }
%>
<br>
</body>
<script>
  $("#header").load("../pages/header.jsp");
</script>
</html>
