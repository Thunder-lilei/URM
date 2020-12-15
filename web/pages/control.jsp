<%@ page import="java.util.List" %>
<%@ page import="po.User" %>
<%@ page import="po.Resource" %>
<%@ page import="service.impl.resource.ResourceServiceImpl" %>
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
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                List<Resource> menuResourceList = resourceService.getMenuResourceByUserId(user.getId());
                for (Resource menuResource : menuResourceList) {
            %>
            <p>
                <button class="btn btn-primary" style="width: 80%;height: 60px;font-size: 20px;"
                        onclick=document.getElementById("messageSpan").innerHTML='';$("#page").load("<%="../pages/"%><%=menuResource.getControlType()%><%=".jsp"%>")
                        type="button" data-toggle="collapse" data-target="#collapseExample<%=menuResource.getId()%>" aria-expanded="false" aria-controls="collapseExample">
                    <%=menuResource.getResourceName()%>
                </button>
            </p>
            <br/>
            <div class="collapse" id="collapseExample<%=menuResource.getId()%>">
            <%
                List<Resource> btnResourceList = resourceService.selectBtnResourcesByUserIdAndMenuResourceId(user.getId(),menuResource.getId());
                for (Resource btnResource : btnResourceList) {
                    %>
                <div class="input-group">
                    <button style="font-size: 20px;color: white;text-align: center;"
                            onclick=document.getElementById("messageSpan").innerHTML='';$("#page").load("<%="../pages/"%><%=btnResource.getControlType()%><%=".jsp"%>")
                            type="button" class="btn btn-outline-primary">
                        <%=btnResource.getResourceName()%>
                    </button>
                </div>
            <br/>
                    <%
                };
            %>
            </div>
            <%
                };
            %>
        </div>
        <div style="width: 80%;display: block;margin-left: 2%">
            <h1><span id="messageSpan" class="label label-danger">${requestScope.message}</span></h1>
            <div id="page" style="width: 100%;">
            </div>
                <%
                    if(request.getAttribute("UserControlPage")!=null) {
                %>
                <script>
                    $("#page").load("<%="../pages/UserControl.jsp"%>")
                </script>
                <%
                    };
                    if(request.getAttribute("RoleControlPage")!=null) {
                %>
                <script>
                    $("#page").load("<%="../pages/RoleControl.jsp"%>")
                </script>
                <%
                    };
                    if(request.getAttribute("ResourceControlPage")!=null) {
                %>
                <script>
                    $("#page").load("<%="../pages/ResourceControl.jsp"%>")
                </script>
                <%
                    };
                %>
        </div>
    </div>
</body>
<script>
    $("#header").load("../pages/header.jsp");
</script>
</html>
