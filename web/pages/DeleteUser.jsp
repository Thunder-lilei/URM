<%@ page import="po.User" %>
<%@ page import="java.util.List" %>
<%@ page import="serviceImpl.user.UserServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/30
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 0px 100px 10px;width: 50%;">
    <h1><span class="label label-info">移除用户</span></h1>
    <br/>
    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/DeleteUserServlet">
        <%
            UserServiceImpl userService = new UserServiceImpl();
            List<User> userList = userService.selectAllUser();
            for (User user : userList) {
        %>
        <div style="height: 5%;" class="form-check">
            <label style="width: 100%;height: 100%;" class="form-check-label">
                <input name="userId" style="height: 25px;width: 25px;" type="checkbox" class="form-check-input" value="<%=user.getId()%>">
                <span style="margin: 0 0 0 5% ;font-size: 20px;" class="label label-info"><%=user.getNickname()%></span>
            </label>
        </div>
        <%
            };
        %>
        <br/>
        <div class="btn-group">
            <button type="submit" class="btn btn-default">移除</button>
        </div>
    </form>
</div>
