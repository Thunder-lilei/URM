<%@ page import="po.Role" %>
<%@ page import="serviceImpl.role.RoleServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="po.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/12/1
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 100px 100px 10px;width: 50%;">
    <%
        RoleServiceImpl roleService = new RoleServiceImpl();
        User selectRoleUser = (User) request.getSession().getAttribute("selectRoleUser");
        List<Role> roleList = new ArrayList<>();
        if(selectRoleUser!=null) {
            roleList = roleService.selectByUserId(selectRoleUser.getId());
        }else {
            roleList = roleService.selectAll();
        }
            for (Role role : roleList) {
            %>
    <div class="input-group">
        <span class="input-group-addon">角色类型</span>
        <input type="text" class="form-control" value="<%=role.getType()%>" disabled>
        <span class="input-group-addon">角色名称</span>
        <input type="text" class="form-control" value="<%=role.getRoleName()%>" disabled>
    </div>
    <br/>
            <%
            };
    %>
</div>
