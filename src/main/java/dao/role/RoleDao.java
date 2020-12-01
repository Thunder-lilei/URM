package dao.role;

import po.Role;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>角色表dao层</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 01:01
 **/
public class RoleDao {
    public Role selectByName(String name) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Role role = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_role where role_name = ?");
            pstat.setString(1,name);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleName(rs.getString("role_name"));
                role.setType(rs.getString("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return role;
    }
    public List<Role> selectAll() {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Role role = null;
        List<Role> roleList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_role");
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleName(rs.getString("role_name"));
                role.setType(rs.getString("type"));
                roleList.add(role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roleList;
    }
    public Role selectById(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Role role = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_role where id = ?");
            pstat.setInt(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleName(rs.getString("role_name"));
                role.setType(rs.getString("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return role;
    }

    public Integer addRole(Role role) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into sys_role (type,role_name) values(?,?)");
            pstat.setString(1,role.getType());
            pstat.setString(2,role.getRoleName());
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Integer deleteRole(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("delete from sys_role where id = ?");
            pstat.setInt(1,id);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
