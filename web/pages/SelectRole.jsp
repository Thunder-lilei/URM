<%@ page import="po.Role" %>
<%@ page import="serviceImpl.role.RoleServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="po.User" %>
<%@ page import="serviceImpl.resource.ResourceServiceImpl" %>
<%@ page import="po.Resource" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/12/1
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 0px 100px 10px;width: 50%;">
    <h1><span class="label label-info">角色列表</span></h1>
    <%
        RoleServiceImpl roleService = new RoleServiceImpl();
        User selectRoleUser = (User) request.getSession().getAttribute("selectRoleUser");
        List<Role> roleList;
        if(selectRoleUser!=null) {
            roleList = roleService.selectByUserId(selectRoleUser.getId());
            request.getSession().setAttribute("selectRoleUser",null);
        }else {
            roleList = roleService.selectAll();
        }
            for (Role role : roleList) {
            %>
    <div class="input-group">
        <span class="input-group-addon">角色类型</span>
        <input type="text" class="form-control" value="<%=role.getRoleType()%>" disabled>
        <span class="input-group-addon">角色名称</span>
        <input type="text" class="form-control" value="<%=role.getRoleName()%>" disabled>
    </div>
    <br/>
        <%
            ResourceServiceImpl resourceService = new ResourceServiceImpl();
            List<Resource> resourceList = resourceService.selectBtnResourcesByRoleId(role.getId());
            for (Resource resource : resourceList) {
            %>
    <div class="input-group">
        <span class="input-group-addon">权限名称</span>
        <input type="text" class="form-control" value="<%=resource.getResourceName()%>" disabled>
    </div>
            <%
            };
        %>
    <br/>
            <%
            };
    %>
</div>
