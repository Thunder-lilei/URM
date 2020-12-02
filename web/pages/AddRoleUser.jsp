<%@ page import="java.util.List" %>
<%@ page import="po.Role" %>
<%@ page import="serviceImpl.role.RoleServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/12/1
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 0px 100px 10px;width: 50%;">
    <h1><span class="label label-info">授予用户角色</span></h1>
    <br/>
    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/AddRoleUserServlet">
        <div class="input-group">
            <span class="input-group-addon">用户名</span>
            <input name="userName" type="text" class="form-control" required>
        </div>
        <br/>
        <%
            RoleServiceImpl roleService = new RoleServiceImpl();
            List<Role> roleList = roleService.selectAll();
            for (Role role : roleList) {
                %>
        <div style="height: 5%;" class="form-check">
            <label style="width: 100%;height: 100%;" class="form-check-label">
                <input name="roleId" style="height: 25px;width: 25px;" type="checkbox" class="form-check-input" value="<%=role.getId()%>">
                <span style="margin: 0 0 0 5% ;font-size: 20px;" class="label label-info"><%=role.getRoleName()%></span>
            </label>
        </div>
                <%
            };
        %>
        <div class="btn-group">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </form>
</div>
