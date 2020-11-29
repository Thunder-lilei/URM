package dao.role;

import po.Role;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_role where name = ?");
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
}
