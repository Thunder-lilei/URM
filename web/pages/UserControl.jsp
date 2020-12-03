<%@ page import="java.util.List" %>
<%@ page import="po.User" %>
<%@ page import="serviceImpl.user.UserServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="serviceImpl.resource.ResourceServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/12/2
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 0px 100px 10px;width: 50%;">
    <!-- 按钮触发模态框 -->
    <div style="display: flex;">
        <%
            ResourceServiceImpl resourceService = new ResourceServiceImpl();
            String btnResourceAddUser = "AddUser";
            String btnResourceDeleteUser = "DeleteUser";
            String btnResourceSelectUser = "SelectUser";
            String btnResourceUpdateUser = "UpdateUser";
            String menuResourceUserControl = "UserControl";
            User userLogin = (User) request.getSession().getAttribute("user");
            if (resourceService.selectBtnResourceIdByUserIdAndBtnResourceType(userLogin.getId(),btnResourceAddUser) != 0) {
                %>
        <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addUserModal">
            +新增
        </button>
                <%
            };
            if (resourceService.selectBtnResourceIdByUserIdAndBtnResourceType(userLogin.getId(),btnResourceSelectUser) != 0) {
        %>
        <form style="margin-left: 10%;" class="form-inline  mb-2" method="post" action="${pageContext.request.contextPath}/SelectUserServlet">
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
    <div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="addUserModelLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="addUserModalLabel">
                        新增用户
                    </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                </div>
                <h1><span class="label label-info">添加用户信息</span></h1>
                <br/>
                <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/AddUserServlet">
                    <div class="input-group">
                        <span class="input-group-addon">用户名</span>
                        <input name="userName" type="text" class="form-control" required>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon">昵称</span>
                        <input name="nickName" type="text" class="form-control" required>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon">密码</span>
                        <input name="password" type="password" class="form-control" required>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon">确认密码</span>
                        <input type="password" class="form-control" required>
                    </div>
                    <br/>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary">
                        提交
                    </button>
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
         * @Date 10:45 2020/12/2
         * @Param [request, response]
         * @return void
         * 检查是否在查询某个用户
         * 显示所有用户信息
         * 获取当前页码
         **/
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList = new ArrayList<>();
        List<User> selectUserList = (List<User>) request.getSession().getAttribute("selectUserList");
        List<User> userPage = (List<User>) request.getSession().getAttribute("userPage");
        Integer pageNowSession = (Integer) request.getSession().getAttribute("pageNow");
        Integer pageNow = 1;
        if (pageNowSession != null) {pageNow = pageNowSession;}
        Integer pageSize = 10;
        if (selectUserList != null) {
            userList = selectUserList;
        }else if (userPage != null){
            userList = userPage;
        }else {
            userList = userService.selectUserByPage(1,pageSize);
        }
        if (resourceService.selectBtnResourceIdByUserIdAndBtnResourceType(userLogin.getId(),menuResourceUserControl) != 0) {
        for (User user : userList) {
            %>
    <div style="display: flex;">
        <div class="input-group">
            <span style="background-color: #00B271;color: white" class="input-group-addon">用户名</span>
            <input style="background-color: #D7FFF0" name="userName" type="text" class="form-control" value="<%=user.getUserName()%>" disabled>
            <span style="background-color: #00B271;color: white" class="input-group-addon">昵称</span>
            <input style="background-color: #D7FFF0" name="userName" type="text" class="form-control" value="<%=user.getNickname()%>" disabled>
        </div>

        <!-- 按钮触发模态框 -->
        <%
            if (resourceService.selectBtnResourceIdByUserIdAndBtnResourceType(userLogin.getId(),btnResourceDeleteUser) != 0) {
        %>
        <button type="button" data-toggle="modal" data-target="#deleteUser<%=user.getId()%>" class="btn btn-danger">删除</button>
        <%
            };
        %>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="deleteUser<%=user.getId()%>" tabindex="-1" role="dialog" aria-labelledby="deleteUserModalLabel<%=user.getId()%>" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <h1 style="margin:5% 0 0 35%;"><span class="label label-danger">确认删除</span></h1>
                    <br/>
                    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/DeleteUserServlet">
                        <div class="input-group">
                            <input name="userId" type="text" class="form-control" value="<%=user.getId()%>" hidden required>
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
            if (resourceService.selectBtnResourceIdByUserIdAndBtnResourceType(userLogin.getId(),btnResourceUpdateUser) != 0) {
        %>
        <button type="button" data-toggle="modal" data-target="#updateUser<%=user.getId()%>" class="btn btn-primary">修改</button>
        <%
            };
        %>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="updateUser<%=user.getId()%>" tabindex="-1" role="dialog" aria-labelledby="updateUserModalLabel<%=user.getId()%>" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/UpdateUserServlet">
                        <div class="input-group">
                            <span class="input-group-addon">用户名</span>
                            <input name="userName" type="text" class="form-control" value="<%=user.getUserName()%>">
                        </div>
                        <br/>
                        <div class="input-group">
                            <span class="input-group-addon">昵称</span>
                            <input name="nickname" type="text" class="form-control" value="<%=user.getNickname()%>">
                        </div>
                        <br/>
                        <div class="input-group">
                            <span class="input-group-addon">密码</span>
                            <input name="password" type="password" class="form-control" value="<%=user.getPassword()%>">
                        </div>
                        <br/>
                        <div class="input-group">
                            <span class="input-group-addon">确认密码</span>
                            <input type="password" class="form-control" value="<%=user.getPassword()%>">
                        </div>
                        <br/>
                        <div class="input-group">
                            <input name="userId" type="text" class="form-control" value="<%=user.getId()%>" hidden>
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

    </div>
    <br/>
            <%
        };
                };
                if (selectUserList == null) {
    %>
    <nav style="margin: 0 0 0 30% ;" aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/SelectUserPageServlet?pageNow=<%=pageNow-1%>&&pageSize=<%=pageSize%>" aria-label="Previous">
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
                int pages = ((userService.countUser()+pageSize-1)/pageSize);
                for (int i=1;i<=pages;i++) {
                   %>
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/SelectUserPageServlet?pageNow=<%=i%>&&pageSize=<%=pageSize%>"><%=i%></a>
            </li>
                   <%
                };
            %>
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/SelectUserPageServlet?pageNow=<%=pageNow+1%>&&pageSize=<%=pageSize%>" aria-label="Next">
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
    <button onclick="backUserControl()" type="button" class="btn btn-primary">返回</button>
    <script>
        /*
        清除查询的用户信息 避免再次加载
        重新加载用户管理页面
         */
        function backUserControl() {
            <%
            request.getSession().setAttribute("selectUserList",null);
            request.getSession().setAttribute("pageNow",1);
            %>
            $("#page").load("<%="../pages/UserControl.jsp"%>")
        }
    </script>
                    <%
        };
    %>
</div>