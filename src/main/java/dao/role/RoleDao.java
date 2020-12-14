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
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM tbl_role where role_name = ?");
            pstat.setString(1,name);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                role = new Role(rs.getInt("id"),rs.getString("role_name"),
                        rs.getString("role_type"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return role;
    }
    public Role selectByType(String type) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Role role = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM tbl_role where role_type = ?");
            pstat.setString(1,type);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                role = new Role(rs.getInt("id"),rs.getString("role_name"),
                        rs.getString("role_type"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return role;
    }
    public List<Role> selectAll() {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Role role = null;
        List<Role> roleList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM tbl_role");
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                role = new Role(rs.getInt("id"),rs.getString("role_name"),
                        rs.getString("role_type"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
                roleList.add(role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return roleList;
    }
    public Role selectById(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Role role = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM tbl_role where id = ?");
            pstat.setInt(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                role = new Role(rs.getInt("id"),rs.getString("role_name"),
                        rs.getString("role_type"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return role;
    }

    public List<Role> selectRoleByPage(Integer pre,Integer end) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Role> roleList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from tbl_role limit ?, ?");
            pstat.setInt(1,pre);
            pstat.setInt(2,end);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt("id"),rs.getString("role_name"),
                        rs.getString("role_type"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
                roleList.add(role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return roleList;
    }

    public List<Role> selectRoleByKeyWord(String keyWord) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Role> roleList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM tbl_role WHERE CONCAT(role_type,role_name) " +
                    "LIKE \"%\"?\"%\"");
            pstat.setString(1,keyWord);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role = new Role(rs.getInt("id"),rs.getString("role_name"),
                        rs.getString("role_type"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
                roleList.add(role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return roleList;
    }

    public Integer countRole() {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT COUNT(*) FROM tbl_role");
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }

    public Integer addRole(Role role) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into tbl_role (role_type,role_name) values(?,?)");
            pstat.setString(1,role.getRoleType());
            pstat.setString(2,role.getRoleName());
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }

    public Integer deleteRole(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("delete from tbl_role where id = ?");
            pstat.setInt(1,id);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }
}
