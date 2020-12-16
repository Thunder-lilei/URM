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

    public Integer insertRoleUser(Long roleId,Long userId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into tbl_role_user(role_id,user_id) values(?,?)");
            pstat.setLong(1,roleId);
            pstat.setLong(2,userId);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }

    public Boolean selectByRoleIdAndUserId(Long roleId,Long userId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT id FROM tbl_role_user where role_id = ? " +
                    "and user_id = ?");
            pstat.setLong(1,roleId);
            pstat.setLong(2,userId);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return false;
    }

    public List<Long> selectRoleIdByUserId(Long userId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Long> roleIds = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT role_id FROM tbl_role_user where user_id = ?");
            pstat.setLong(1,userId);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                roleIds.add(rs.getLong("role_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return roleIds;
    }

    public Integer deleteRoleUserByUserId(Long id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("delete from tbl_role_user where user_id = ?");
            pstat.setLong(1,id);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }

    public Integer deleteRoleUser(Long roleId,Long userId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("delete from tbl_role_user where user_id = ? and role_id = ?");
            pstat.setLong(1,userId);
            pstat.setLong(2,roleId);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }
}
