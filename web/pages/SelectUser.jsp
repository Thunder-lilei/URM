<%@ page import="po.User" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/30
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 100px 100px 10px;width: 50%;">
    <h1><span class="label label-info">查询用户信息</span></h1>
    <br/>
    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/SelectUserServlet">
        <div class="input-group">
            <span class="input-group-addon">用户名</span>
            <input name="userName" type="text" class="form-control" required>
        </div>
        <br/>
        <div class="btn-group">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </form>
</div>