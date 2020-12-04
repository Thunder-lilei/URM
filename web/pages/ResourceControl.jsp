<%@ page import="serviceImpl.resource.ResourceServiceImpl" %>
<%@ page import="po.User" %>
<%@ page import="po.Resource" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/12/3
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 0px 100px 10px;width: 50%;">
    <div style="display: flex;">
        <%
            ResourceServiceImpl resourceService = new ResourceServiceImpl();
            String btnResourceAddResource = "AddResource";
            String btnResourceDeleteResource = "DeleteRole";
            String btnResourceSelectResource = "SelectResource";
            String btnResourceUpdateResource = "UpdateResource";
            String menuResourceRoleControl = "RoleControl";
            User userLogin = (User) request.getSession().getAttribute("user");
            if (resourceService.selectBtnResourceIdByUserIdAndBtnControlType(userLogin.getId(),btnResourceAddResource) != 0) {
        %>
        <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addResource">
            +新增
        </button>
        <%
            };
            if (resourceService.selectBtnResourceIdByUserIdAndBtnControlType(userLogin.getId(),btnResourceSelectResource) != 0) {
        %>
        <form style="margin-left: 10%;" class="form-inline  mb-2" method="post" action="${pageContext.request.contextPath}/SelectResourceServlet">
            <div class="form-group mx-sm-3 mb-2">
                <label for="keyWord" class="sr-only">关键字</label>
                <input type="text" class="form-control" name="keyWord" id="keyWord" placeholder="关键字">
            </div>
            <button type="submit" class="btn btn-primary mb-2">查询</button>
        </form>
        <%
            };
        %>
    </div>

    <br/>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="addResource" tabindex="-1" role="dialog" aria-labelledby="addResource" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2><span class="label label-info">添加菜单</span></h2>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                </div>
                <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/AddResourceServlet">
                    <div class="input-group">
                        <span class="input-group-addon">菜单名称</span>
                        <input name="resourceName" type="text" class="form-control" required>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon">访问路径</span>
                        <input name="controlType" type="text" class="form-control" required>
                    </div>
                    <br/>
                    <span class="input-group-addon">选择父级菜单</span>
                        <select name="menuResourceId" class="custom-select custom-select-lg mb-3">
                            <option value="0" selected>无</option>
                            <%
                                List<Resource> menuResourceList = resourceService.getAllMenuResource();
                                for (Resource resource : menuResourceList) {
                                    %>
                            <option value="<%=resource.getId()%>"><%=resource.getResourceName()%></option>
                                    <%
                                };
                            %>
                        </select>
                    <br/>
                    <div class="btn-group">
                        <button type="submit" class="btn btn-default">提交</button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <br/>


</div>
