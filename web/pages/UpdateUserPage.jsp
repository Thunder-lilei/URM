<%@ page import="po.User" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/30
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 0px 100px 10px;width: 50%;">
    <%
        User updateUser = (User) request.getSession().getAttribute("updateUser");
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
</div>