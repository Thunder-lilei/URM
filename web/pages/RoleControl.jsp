<%@ page import="service.impl.resource.ResourceServiceImpl" %>
<%@ page import="po.User" %>
<%@ page import="service.impl.role.RoleServiceImpl" %>
<%@ page import="po.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="po.Resource" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/12/3
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 0px 100px 10px;width: 50%;">
    <!-- 按钮触发模态框 -->
    <div style="display: flex;">
        <%
            ResourceServiceImpl resourceService = new ResourceServiceImpl();
            String btnResourceAddRole = "AddRole";
            String btnResourceDeleteRole = "DeleteRole";
            String btnResourceSelectRole = "SelectRole";
            String btnResourceAddRoleResource = "AddRoleResource";
            String btnResourceDeleteRoleResource = "DeleteRoleResource";
            String menuResourceRoleControl = "RoleControl";
            User userLogin = (User) request.getSession().getAttribute("user");
            if (resourceService.selectBtnResourceIdByUserIdAndBtnControlType(userLogin.getId(),btnResourceAddRole) != 0) {
        %>
        <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addRole">
            +新增
        </button>
        <%
            };
            if (resourceService.selectBtnResourceIdByUserIdAndBtnControlType(userLogin.getId(),btnResourceSelectRole) != 0) {
        %>
        <form style="margin-left: 10%;" class="form-inline  mb-2" method="post" action="${pageContext.request.contextPath}/SelectRoleServlet">
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
    <div class="modal fade" id="addRole" tabindex="-1" role="dialog" aria-labelledby="addRole" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2><span class="label label-info">添加角色</span></h2>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                </div>
                <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/AddRoleServlet">
                    <div class="input-group">
                        <span class="input-group-addon">角色类型</span>
                        <input name="type" type="text" class="form-control" required>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon">角色名称</span>
                        <input name="role_name" type="text" class="form-control" required>
                    </div>
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
        /*
         * @Author 李雷
         * @Description //TODO lilei
         * @Date 12:09 2020/12/4
         * @Param [request, response]
         * @return void
         **/
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> selectRoleList = (List<Role>) request.getSession().getAttribute("selectRoleList");
        List<Role> rolePage = (List<Role>) request.getSession().getAttribute("rolePage");
        Integer pageNowSession = (Integer) request.getSession().getAttribute("pageNow");
        Integer pageNow = 1;
        if (pageNowSession != null) {pageNow = pageNowSession;}
        Integer pageSize = 10;
        List<Role> roleList;
        if(selectRoleList!=null) {
            roleList = selectRoleList;
        }else if (rolePage != null){
            roleList = rolePage;
        }else {
            roleList = roleService.selectAll();
        }
        for (Role role : roleList) {
    %>
    <div style="display: flex">
    <div class="input-group">
        <span style="background-color: #00B271;color: white" class="input-group-addon">角色类型</span>
        <input style="background-color: #D7FFF0" name="username" type="text" class="form-control" value="<%=role.getRoleType()%>" disabled>
        <span style="background-color: #00B271;color: white" class="input-group-addon">角色名称</span>
        <input style="background-color: #D7FFF0" name="userName" type="text" class="form-control" value="<%=role.getRoleName()%>" disabled>
    </div>


        <!-- 按钮触发模态框 -->
        <%
            if (resourceService.selectBtnResourceIdByUserIdAndBtnControlType(userLogin.getId(),btnResourceDeleteRole) != 0) {
        %>
        <button type="button" data-toggle="modal" data-target="#deleteRole<%=role.getId()%>" class="btn btn-danger">删除</button>
        <%
            };
        %>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="deleteRole<%=role.getId()%>" tabindex="-1" role="dialog" aria-labelledby="deleteRole<%=role.getId()%>" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <h1 style="margin:5% 0 0 35%;"><span class="label label-danger">确认删除</span></h1>
                    <br/>
                    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/DeleteRoleServlet">
                        <div class="input-group">
                            <input name="roleId" type="text" class="form-control" value="<%=role.getId()%>" hidden required>
                        </div>
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

        <!-- 按钮触发模态框 -->
        <%
            if (resourceService.selectBtnResourceIdByUserIdAndBtnControlType(userLogin.getId(),btnResourceAddRoleResource) != 0) {
        %>
        <button type="button" data-toggle="modal" data-target="#addRoleResource<%=role.getId()%>" class="btn btn-primary">授权</button>
        <%
            };
        %>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="addRoleResource<%=role.getId()%>" tabindex="-1" role="dialog" aria-labelledby="addRoleResource<%=role.getId()%>" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2><span class="label label-info">角色授权</span></h2>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/AddRoleResourceServlet">
                        <input type="text" name="id" value="<%=role.getId()%>" hidden>
                        <%
                            List<Resource> menuResourceList = resourceService.getAllMenuResource();
                        %>
                        <%
                            for (Resource menuResource : menuResourceList) {
                        %>
                        <h2><span class="label label-info"><%=menuResource.getResourceName()%></span></h2>
                        <%
                            List<Resource> btnResourceList = resourceService.getResourceByMenuResourceId(menuResource.getId());
                            List<Integer> btnResourceId = resourceService.selectBtnResourcesIdByRoleIdAndMenuResourceId
                                    (role.getId(),menuResource.getId());
                            for (Resource btnResource : btnResourceList) {
                        %>
                        <div style="height: 5%;margin: 15px;" class="form-check">
                            <label style="width: 100%;height: 100%;" class="form-check-label">
                                <input name="btnResourceId" style="height: 25px;width: 25px;" type="checkbox" class="form-check-input" value="<%=btnResource.getId()%>"
                                    <%
                                    if (btnResourceId.contains(btnResource.getId())) {
                                        %>
                                       checked="checked"
                                    <%
                                    }
                                    %>
                                >
                                <span style="margin: 0 0 0 6% ;font-size: 20px;" class="label label-info"><%=btnResource.getResourceName()%></span>
                            </label>
                        </div>
                        <%
                                };
                            };
                        %>
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


        <!-- 按钮触发模态框 -->
        <%
            if (resourceService.selectBtnResourceIdByUserIdAndBtnControlType(userLogin.getId(),btnResourceDeleteRoleResource) != 0) {
        %>
        <button type="button" data-toggle="modal" data-target="#deleteRoleResource<%=role.getId()%>" class="btn btn-danger">撤权</button>
        <%
            };
        %>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="deleteRoleResource<%=role.getId()%>" tabindex="-1" role="dialog" aria-labelledby="deleteRoleResource<%=role.getId()%>" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2><span class="label label-danger">角色撤权</span></h2>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <br/>
                    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/DeleteRoleResourceServlet">
                        <input type="text" name="id" value="<%=role.getId()%>" hidden>
                        <%
                            List<Resource> menuResourceDeleteList = resourceService.getAllMenuResource();
                        %>
                        <%
                            for (Resource menuResource : menuResourceDeleteList) {
                        %>
                        <h2><span class="label label-info"><%=menuResource.getResourceName()%></span></h2>
                        <%
                            List<Resource> btnResourceList = resourceService.getResourceByMenuResourceId(menuResource.getId());
                            List<Integer> btnResourceId = resourceService.selectBtnResourcesIdByRoleIdAndMenuResourceId
                                    (role.getId(),menuResource.getId());
                            for (Resource btnResource : btnResourceList) {
                        %>
                        <div style="height: 5%;margin: 15px;" class="form-check">
                            <label style="width: 100%;height: 100%;" class="form-check-label">
                                <input name="btnResourceId" style="height: 25px;width: 25px;" type="checkbox" class="form-check-input" value="<%=btnResource.getId()%>"
                                    <%
                                    if (btnResourceId.contains(btnResource.getId())) {
                                        %>
                                       checked="checked"
                                    <%
                                    }
                                    %>
                                >
                                <span style="margin: 0 0 0 6% ;font-size: 20px;" class="label label-info"><%=btnResource.getResourceName()%></span>
                            </label>
                        </div>
                        <%
                                };
                            };
                        %>
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
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample<%=role.getId()%>" aria-expanded="false" aria-controls="collapseExample">
            查看所属权限
        </button>
    </p>
    <div class="collapse" id="collapseExample<%=role.getId()%>">
    <%
        List<Resource> resourceList = resourceService.selectBtnResourcesByRoleId(role.getId());
        for (Resource resource : resourceList) {
    %>
        <div class="input-group">
            <span style="background-color: #00B271;color: white" class="input-group-addon">权限名称</span>
            <input style="background-color: #D7FFF0" type="text" class="form-control" value="<%=resource.getResourceName()%>" disabled>
        </div>
    <%
        };
    %>
    </div>
    <br/>
    <%
        };
        //查询的时候不分页
        if (selectRoleList == null) {
    %>
    <nav style="margin: 0 0 0 30% ;" aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/SelectRolePageServlet?pageNow=<%=pageNow-1%>&&pageSize=<%=pageSize%>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <%
                /*
                 * @Author 李雷
                 * @Description //TODO lilei
                 * @Date 11:38 2020/12/2
                 * @Param [request, response]
                 * @return void
                 * 分页查询
                 **/
                int pages = ((roleService.countRole()+pageSize-1)/pageSize);
                for (int i=1;i<=pages;i++) {
            %>
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/SelectRolePageServlet?pageNow=<%=i%>&&pageSize=<%=pageSize%>"><%=i%></a>
            </li>
            <%
                };
            %>
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/SelectRolePageServlet?pageNow=<%=pageNow+1%>&&pageSize=<%=pageSize%>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
            <li class="page-item">
                <span aria-hidden="true">第<%=pageNow%>页</span>
            </li>
        </ul>
    </nav>
    <%
    }else {
    %>
    <button onclick="backRoleControl()" type="button" class="btn btn-primary">返回</button>
    <script>
        /*
        清除查询的用户信息 避免再次加载
        重新加载用户管理页面
         */
        function backRoleControl() {
            <%
            request.getSession().setAttribute("selectRoleList",null);
            request.getSession().setAttribute("pageNow",1);
            %>
            $("#page").load("<%="../pages/RoleControl.jsp"%>")
        }
    </script>
    <%
        };
    %>
</div>
