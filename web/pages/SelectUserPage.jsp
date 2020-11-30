<%@ page import="po.User" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/30
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 100px 100px 10px;width: 50%;">
    <%
        User selectUser = (User) request.getSession().getAttribute("selectUser");
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
</div>
