package service.role.user;

import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h3>URM</h3>
 * <p>角色用户表的服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-26 09:47
 **/
public class RoleUserService {
    public static Integer insertRoleUser(Integer roleid,Integer userid) {
        if (ifSaveByRoleIdAndUserId(roleid,userid)) {
            return 0;
        }
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into role_user(role_id,user_id) values(?,?)");
            pstat.setInt(1,roleid);
            pstat.setInt(2,userid);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static Boolean ifSaveByRoleIdAndUserId(Integer roleId,Integer userId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT id FROM role_user where role_id = ? " +
                    "and user_id = ?");
            pstat.setInt(1,roleId);
            pstat.setInt(2,userId);
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
