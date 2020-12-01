package dao.user;

import po.User;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_user where user_name = ? and status != 0");
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
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
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
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }
    public Integer updateUserById(User user) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("UPDATE sys_user SET user_name = ?,nickname = ?,password = ? WHERE id = ? and status != 0");
            pstat.setString(1,user.getUserName());
            pstat.setString(2,user.getNickname());
            pstat.setString(3,user.getPassword());
            pstat.setInt(4,user.getId());
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }
    public List<User> selectAllUser() {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_user where status != 0");
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setCreateTime(rs.getTimestamp("create_time"));
                user.setUpdateTime(rs.getTimestamp("update_time"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return userList;
    }
    public Integer deleteUserById(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("UPDATE sys_user SET status = 0 WHERE id = ?");
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
