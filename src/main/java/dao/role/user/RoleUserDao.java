package dao.role.user;

import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>用户角色表dao层</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 01:11
 **/
public class RoleUserDao {

    public Integer insertRoleUser(Integer roleId,Integer userId) {
        if (selectByRoleIdAndUserId(roleId,userId)) {
            return 0;
        }
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into role_user(role_id,user_id) values(?,?)");
            pstat.setInt(1,roleId);
            pstat.setInt(2,userId);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Boolean selectByRoleIdAndUserId(Integer roleId,Integer userId) {
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

    public List<Integer> selectRoleIdByUserId(Integer userId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Integer> roleIds = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT role_id FROM role_user where user_id = ?");
            pstat.setInt(1,userId);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                roleIds.add(rs.getInt("role_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roleIds;
    }

    public Integer deleteRoleUserByUserId(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("delete from role_user where user_id = ?");
            pstat.setInt(1,id);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
