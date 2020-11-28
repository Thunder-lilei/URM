package service.role;

import constant.ConstantClassColumn;
import constant.ConstantClassField;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h3>UserPowerControl</h3>
 * <p>RoleService</p>
 *
 * @author : 李雷
 * @date : 2020-11-26 09:25
 **/
public class RoleService {
    public static Integer selectIdByName(String name) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = ConstantClassField.RESULT_ZERO;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT id FROM sys_role where name = ?");
            pstat.setString(1,name);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                result = rs.getInt(ConstantClassColumn.ID);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
