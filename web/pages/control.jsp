<%@ page import="service.resource.MenuResourceService" %>
<%@ page import="po.MenuResource" %>
<%@ page import="java.util.List" %>
<%@ page import="service.resource.BtnResourceService" %>
<%@ page import="pojo.UserPojo" %>
<%@ page import="pojo.BtnResourcePojo" %>
<%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/23
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理页面</title>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <%
        UserPojo userPojo = (UserPojo) request.getSession().getAttribute("user");
    %>
</head>
<body>
	<header>
        <div style="display: flex;text-align: center">
            <div style="width: 50%;border: 1px solid blue;">
                <h2 style="color: blue">${sessionScope.user.nickname}</h2>
            </div>
            <div style="width: 50%;border: 1px solid blue;">
                <a href="${pageContext.request.contextPath}/LogoutServlet">登出</a>
                <br>
                <br>
                <a href="${pageContext.request.contextPath}/index.jsp">返回主页</a>
            </div>
        </div>
	</header>
    <hr/>
    <div style="width: 100%;display: flex; border: 1px solid blue;">
        <div style="width: 15%;border: 1px solid blue;">
            <%
                List<MenuResource> list = MenuResourceService.getMenuResourceByUserId(userPojo.getId());
                for (MenuResource menuResource : list) {
            %>
            <p style="font-size: 25px;color: cornflowerblue;text-align: center;"><%=menuResource.getName()%></p>
            <%
                List<BtnResourcePojo> btnResourcePojoList = BtnResourceService.selectBtnResourcesByUserIdAndMenuResourceName(userPojo.getId(),
                        menuResource.getName());
                for (BtnResourcePojo btnResourcePojo : btnResourcePojoList) {
            %>
            <a href="${pageContext.request.contextPath}<%=btnResourcePojo.getUrl()%>?id=<%=btnResourcePojo.getId()%>"> <p style="color: black ;text-align: center"><%=btnResourcePojo.getBtnResourceNickname()%></p> </a>
            <%
                }
            %>
            <br/>
            <%
                };
            %>
        </div>
        <div style="width: 85%;border: 1px solid blue;display: block;">
            <div style="width: 100%;">
                <p style="color: red;text-align: center">${requestScope.message}</p>
                <%
                    List<String> inputlist = (List<String>) (request.getSession().getAttribute("list"));
                    String action = (String) request.getSession().getAttribute("action");
                    if(inputlist !=null && action !=null) {
                        %>
                <form method="post" action="${pageContext.request.contextPath}<%=action%>">
                    <label>
                        <%
                            for (String s : inputlist) {
                            %>
                        <%=s%>
                            <%
                            };
                        %>
                    </label>
                    <br>
                    <input type="submit" value="提交"/>
                </form>
                <%
                    };
                %>
            </div>
        </div>
    </div>
</body>
</html>
