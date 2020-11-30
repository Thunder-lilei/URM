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
<header>
    <title>管理页面</title>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../css/control.css">
    <%
        User user = (User) request.getSession().getAttribute("user");
    %>
</header>
<body style="background-color: F4606C">
<div id = "header"></div>
    <hr/>
    <div style="width: 100%;display: flex;">
        <div style="width: 15%;border: 3px solid white;">
            <%
                ResourceServiceImpl resourceService = new ResourceServiceImpl();
                List<Resource> menuResourcelist = resourceService.getMenuResourceByUserId(user.getId());
                for (Resource menuResource : menuResourcelist) {
            %>
            <p style="font-size: 25px;color: white;text-align: center;"><%=menuResource.getResourceName()%></p>
            <%
                List<Resource> btnResourceList = resourceService.selectBtnResourcesByUserIdAndMenuResourceId(user.getId(),
                        menuResource.getId());
                for (Resource btnResource : btnResourceList) {
            %>
            <a href="${pageContext.request.contextPath}/<%=btnResource.getResourceType()%>PageServlet?id=<%=btnResource.getId()%>"> <p style="color: white ;text-align: center"><%=btnResource.getResourceName()%></p> </a>
            <%
                }
            %>
            <br/>
            <%
                };
            %>
        </div>
        <div style="width: 85%;display: block;">
            <div style="width: 100%;">
                <p style="color: red;text-align: center">${requestScope.message}</p>
            </div>
        </div>
    </div>
</body>
<script>
    $("#header").load("../pages/header.jsp");
</script>
</html>
