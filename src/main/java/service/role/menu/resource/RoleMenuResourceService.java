package service.role.menu.resource;

import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h3>URM</h3>
 * <p>角色菜单资源表的服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-26 14:37
 **/
public class RoleMenuResourceService {
    public static Integer addRoleMenuResource(Integer menuResourceId,Integer roleId) {
        if (RoleMenuResourceService.ifSaveByRoleIdAndMenuResourceId(roleId, menuResourceId)) {
            return 0;
        }
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into role_menu_resource(menu_resource_id," +
                    "role_id) values(?,?)");
            pstat.setInt(1,menuResourceId);
            pstat.setInt(2,roleId);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static Boolean ifSaveByRoleIdAndMenuResourceId(Integer roleId,Integer menuResourceId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        try {
            PreparedStatement pstat = connection.prepareStatement("select id from role_menu_resource " +
                    "where menu_resource_id = ? and role_id = ?");
            pstat.setInt(1,menuResourceId);
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
}
