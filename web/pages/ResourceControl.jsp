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
            String btnResourceDeleteResource = "DeleteResource";
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
                                List<Resource> menuResourceSelectList = resourceService.getAllMenuResource();
                                for (Resource resource : menuResourceSelectList) {
                                    %>
                            <option value="<%=resource.getId()%>"><%=resource.getResourceName()%></option>
                                    <%
                                };
                            %>
                        </select>
                    <span class="input-group-addon">选择菜单类型</span>
                    <select name="resourceType" class="custom-select custom-select-lg mb-3">
                        <option value="menu_btn" selected>菜单栏按钮</option>
                        <option value="page_btn">页面按钮</option>
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


    <%
        List<Resource> menuResourceList = resourceService.getAllMenuResource();
        for (Resource menuResource : menuResourceList) {
            %>
    <div style="display: flex">
        <div class="input-group">
            <span style="background-color: #00B271;color: white" class="input-group-addon">访问路径</span>
            <input style="background-color: #D7FFF0" name="username" type="text" class="form-control" value="<%=menuResource.getControlType()%>" disabled>
            <span style="background-color: #00B271;color: white" class="input-group-addon">菜单名称</span>
            <input style="background-color: #D7FFF0" name="userName" type="text" class="form-control" value="<%=menuResource.getResourceName()%>" disabled>
        </div>

        <%
            if (resourceService.selectBtnResourceIdByUserIdAndBtnControlType(userLogin.getId(),btnResourceDeleteResource) != 0) {
        %>
        <button type="button" data-toggle="modal" data-target="#deleteMenuResource<%=menuResource.getId()%>" class="btn btn-danger">删除</button>
        <%
            };
        %>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="deleteMenuResource<%=menuResource.getId()%>" tabindex="-1" role="dialog" aria-labelledby="deleteMenuResource<%=menuResource.getId()%>" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <h1 style="margin:5% 0 0 35%;"><span class="label label-danger">确认删除</span></h1>
                    <br/>
                    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/DeleteResourceServlet">
                        <input name="id" type="text" class="form-control" value="<%=menuResource.getId()%>" hidden required>
                        <br/>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button type="submit" class="btn btn-primary">
                                提交更改
                            </button>
                        </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>

    </div>


    <br/>
    <p>
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#showBtnResource<%=menuResource.getId()%>" aria-expanded="false" aria-controls="collapseExample">
            查看所属次级菜单
        </button>
    </p>
    <div class="collapse" id="showBtnResource<%=menuResource.getId()%>">
    <%
        List<Resource> btnResourceList = resourceService.getBtnResourceByMenuResourceId(menuResource.getId());
        for (Resource btnResource : btnResourceList) {
            %>
        <div style="display: flex">
            <div class="input-group">
                <span style="background-color: #00B271;color: white" class="input-group-addon">次级菜单访问路径</span>
                <input style="background-color: #D7FFF0" type="text" class="form-control" value="<%=btnResource.getControlType()%>" disabled>
                <span style="background-color: #00B271;color: white" class="input-group-addon">次级菜单名称</span>
                <input style="background-color: #D7FFF0" type="text" class="form-control" value="<%=btnResource.getResourceName()%>" disabled>
            </div>
            <%
                if (resourceService.selectBtnResourceIdByUserIdAndBtnControlType(userLogin.getId(),btnResourceDeleteResource) != 0) {
            %>
            <button type="button" data-toggle="modal" data-target="#deleteBtnResource<%=btnResource.getId()%>" class="btn btn-danger">删除</button>
            <%
                };
            %>
            <!-- 模态框（Modal） -->
            <div class="modal fade" id="deleteBtnResource<%=btnResource.getId()%>" tabindex="-1" role="dialog" aria-labelledby="deleteBtnResource<%=btnResource.getId()%>" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <h1 style="margin:5% 0 0 35%;"><span class="label label-danger">确认删除</span></h1>
                        <br/>
                        <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/DeleteResourceServlet">
                            <input name="id" type="text" class="form-control" value="<%=btnResource.getId()%>" hidden required>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                </button>
                                <button type="submit" class="btn btn-primary">
                                    提交更改
                                </button>
                            </div>
                        </form>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
        </div>
            <%
        };
    %>
    </div>
            <%
        };

    %>

</div>
