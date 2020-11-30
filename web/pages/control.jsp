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
<body style="background-color: F4606C">
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
            <button style="color: white;font-size: 20px;" onclick=document.getElementById("messageSpan").innerHTML='';$("#page").load("<%="../pages/"%><%=btnResource.getResourceType()%><%=".jsp"%>") type="button" class="btn btn-outline-primary">
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
        </div>
    </div>
</body>
<script>
    $("#header").load("../pages/header.jsp");
</script>
</html>
