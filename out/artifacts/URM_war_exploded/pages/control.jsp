<%@ page import="java.util.List" %>
<%@ page import="po.User" %>
<%@ page import="po.Resource" %>
<%@ page import="serviceImpl.resource.ResourceServiceImpl" %>
<%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/23
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>管理页面</title>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/control.css">
    <%
        User user = (User) request.getSession().getAttribute("user");
    %>
</head>
<body style="background-color: #6699CC">
<div id = "header"></div>
    <hr/>
    <div style="width: 100%;display: flex;">
        <div style="width: 15%;border: 3px solid white;margin-left: 1%;text-align: center;">
            <br/>
            <%
                ResourceServiceImpl resourceService = new ResourceServiceImpl();
                List<Resource> menuResourcelist = resourceService.getMenuResourceByUserId(user.getId());
                for (Resource menuResource : menuResourcelist) {
            %>
            <p style="font-size: 30px;color: white;text-align: center;"><%=menuResource.getResourceName()%></p>
            <%
                List<Resource> btnResourceList = resourceService.selectBtnResourcesByUserIdAndMenuResourceId(user.getId(),
                        menuResource.getId());
                for (Resource btnResource : btnResourceList) {
            %>
            <button style="color: white;font-size: 20px;" onclick=document.getElementById("updateUserPage").innerHTML='';document.getElementById("selectUserPage").innerHTML='';document.getElementById("messageSpan").innerHTML='';$("#page").load("<%="../pages/"%><%=btnResource.getResourceType()%><%=".jsp"%>") type="button" class="btn btn-outline-primary">
                <%=btnResource.getResourceName()%>
            </button>
            <br/>
            <%
                }
            %>
            <%
                };
            %>
        </div>
        <div style="width: 80%;display: block;margin-left: 2%">
            <h1><span id="messageSpan" class="label label-danger">${requestScope.message}</span></h1>
            <div id="page" style="width: 100%;">
            </div>
            <div id="selectUserPage" style="width: 100%;">
                <div style="padding: 100px 100px 10px;width: 50%;">
                <%
                    User selectUser = (User) request.getAttribute("selectUser");
                    if(selectUser!=null) {
                %>
                    <div class="input-group">
                        <span class="input-group-addon">用户名</span>
                        <input name="userName" type="text" class="form-control" value="<%=selectUser.getUserName()%>" disabled>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon">昵称</span>
                        <input name="userName" type="text" class="form-control" value="<%=selectUser.getNickname()%>" disabled>
                    </div>
                    <br/>
                <%
                    };
                %>
            </div>
        </div>
            <div id="updateUserPage" style="width: 100%;">
                <div style="padding: 100px 100px 10px;width: 50%;">
                    <%
                        User updateUser = (User) request.getAttribute("updateUser");
                        if(updateUser!=null) {
                    %>
                    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/UpdateUserServlet">
                        <div class="input-group">
                            <span class="input-group-addon">用户名</span>
                            <input name="userName" type="text" class="form-control" value="<%=updateUser.getUserName()%>">
                        </div>
                        <br/>
                        <div class="input-group">
                            <span class="input-group-addon">昵称</span>
                            <input name="nickname" type="text" class="form-control" value="<%=updateUser.getNickname()%>">
                        </div>
                        <br/>
                        <div class="input-group">
                            <span class="input-group-addon">密码</span>
                            <input name="password" type="password" class="form-control" value="<%=updateUser.getPassword()%>">
                        </div>
                        <br/>
                        <div class="input-group">
                            <span class="input-group-addon">确认密码</span>
                            <input type="password" class="form-control" value="<%=updateUser.getNickname()%>">
                        </div>
                        <br/>
                        <div class="btn-group">
                            <button type="submit" class="btn btn-default">提交</button>
                        </div>
                    </form>
                    <%
                        };
                    %>
                </div>
            </div>
    </div>
</body>
<script>
    $("#header").load("../pages/header.jsp");
</script>
</html>
