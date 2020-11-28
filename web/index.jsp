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
</head>
<body>
<h1>主页</h1>
<h2>欢迎访问用户权限管理系统</h2>
<%
  if(request.getSession().getAttribute("user") !=null) {
%>
<h2 style="color: blue">${sessionScope.user.nickname}</h2>
<br>
<a href="${pageContext.request.contextPath}/LogoutServlet">登出</a>
<a href="${pageContext.request.contextPath}/pages/control.jsp">管理页面</a>
<%
}else {
%>
<a href="${pageContext.request.contextPath}/user/login.jsp">登录页面</a>
<%
  }
%>
<br>
</body>
</html>
