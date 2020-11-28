<%--
Created by IntelliJ IDEA.
User: lilei
Date: 2020/11/23
Time: 17:01
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <title>登录</title>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
    <h1>登录页面</h1>
    <h2 style="color: red">${requestScope.message}</h2>
    <form method="post" action="${pageContext.request.contextPath}/LoginServlet">
        <label>
            <input type="text" name="username" placeholder="用户名" required/>
            <br>
            <input type="password" name="password" placeholder="密码" required/>
        </label>
        <br>
        <input type="submit" value="提交"/>
    </form>

    <a href="${pageContext.request.contextPath}/index.jsp">返回主页</a>
</body>
<%--<script src="../js/login.js"></script>--%>
</html>