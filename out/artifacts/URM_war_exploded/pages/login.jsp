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
    <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>
    <link rel='stylesheet' href="../css/login.css">
</head>
<body style="background-color: #F4606C">
    <div style="margin: 10% 0 0 0;width: 100%" class="container">
        <div class="row">
            <div class="col-md-offset-3 col-md-6">
                <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/LoginServlet">
                    <span class="heading">用户登录</span>
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" id="inputEmail3" placeholder="用户名">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" name="password" id="inputPassword3" placeholder="密　码">
                        <i class="fa fa-lock"></i>
                        <a href="#" class="fa fa-question-circle"></a>
                    </div>
                    <div class="form-group">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            <button style="width: 50%;" type="button" class="btn btn-default">返回主页</button>
                        </a>
                        <button style="width: 50%;" type="submit" class="btn btn-default">登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<div style="width: 100%;text-align: center">
    <h1><span class="label label-danger">${requestScope.message}</span></h1>
</div>
</body>
</html>