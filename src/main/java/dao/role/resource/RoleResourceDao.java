package dao.role.resource;

import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h3>URM</h3>
 * <p>角色资源表dao层</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 01:26
 **/
public class RoleResourceDao {
    public Integer addRoleResource(Integer resourceId,Integer roleId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into role_resource(resource_id," +
                    "role_id) values(?,?)");
            pstat.setInt(1,resourceId);
            pstat.setInt(2,roleId);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }

    public Boolean ifSaveRoleResource(Integer roleId,Integer resourceId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from role_resource " +
                    "where resource_id = ? and role_id = ?");
            pstat.setInt(1,resourceId);
            pstat.setInt(2,roleId);
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


    public Integer deleteRoleResourceByRoleId(Integer roleId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("delete from role_resource where role_id = ?");
            pstat.setInt(1,roleId);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }

    public Integer deleteRoleResourceByResourceId(Integer resourceId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("delete from role_resource where resource_id = ?");
            pstat.setInt(1,resourceId);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }

    public Integer deleteRoleResource(Integer resourceId,Integer roleId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("delete from role_resource where role_id = ? " +
                    "and resource_id = ?");
            pstat.setInt(1,roleId);
            pstat.setInt(2,resourceId);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }

}
