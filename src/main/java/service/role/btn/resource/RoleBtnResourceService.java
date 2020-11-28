package service.role.btn.resource;

import service.resource.BtnResourceService;
import service.role.menu.resource.RoleMenuResourceService;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h3>URM</h3>
 * <p>角色按钮资源表的服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-26 11:23
 **/
public class RoleBtnResourceService {
    public static Boolean ifSaveByBtnResourceIdAndRoleId(Integer btnResourceId,Integer roleId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        try {
            PreparedStatement pstat = connection.prepareStatement("select id from role_btn_resource where " +
                    "menu_resource_id = ? and role_id = ?");
            pstat.setInt(1,btnResourceId);
            pstat.setInt(2,roleId);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static Integer addRoleBtnResource(Integer btnResourceId,Integer roleId) {
        if(ifSaveByBtnResourceIdAndRoleId(btnResourceId,roleId)) {
            return 0;
        }
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into role_btn_resource(menu_resource_id," +
                    "role_id) values(?,?)");
            pstat.setInt(1,btnResourceId);
            pstat.setInt(2,roleId);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Integer menuResourceId = BtnResourceService.selectMenuResourceIdById(btnResourceId);
        if(!menuResourceId.equals(0)) {
            RoleMenuResourceService.addRoleMenuResource(menuResourceId,roleId);
        }
        return result;
    }

}
