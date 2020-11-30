package dao.user;

import po.User;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h3>URM</h3>
 * <p>用户表dao层</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 00:49
 **/
public class UserDao {
    public User selectByUsername(String username) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        User user = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_user where user_name = ?");
            pstat.setString(1,username);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setCreateTime(rs.getTimestamp("create_time"));
                user.setUpdateTime(rs.getTimestamp("update_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public Integer insertUser(User user) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into sys_user (user_name,password,nickname) " +
                    "values(?,?,?)");
            pstat.setString(1,user.getUserName());
            pstat.setString(2,user.getPassword());
            pstat.setString(3, user.getNickname());
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public Integer updateUserById(User user) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("UPDATE sys_user SET user_name = ?,nickname = ?,password = ? WHERE id = ?");
            pstat.setString(1,user.getUserName());
            pstat.setString(2,user.getNickname());
            pstat.setString(3,user.getPassword());
            pstat.setInt(4,user.getId());
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
