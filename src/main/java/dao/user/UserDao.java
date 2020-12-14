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
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_user where username = ? and status != 0");
            pstat.setString(1,username);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"),rs.getString("username"),
                        rs.getString("password"),rs.getString("nickname"),
                        rs.getTimestamp("create_time"),rs.getTimestamp("update_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return user;
    }

    public User selectById(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        User user = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_user where id = ? and status != 0");
            pstat.setInt(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"),rs.getString("username"),
                        rs.getString("password"),rs.getString("nickname"),
                        rs.getTimestamp("create_time"),rs.getTimestamp("update_time"));
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
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into sys_user (username,password,nickname) " +
                    "values(?,?,?)");
            pstat.setString(1,user.getUsername());
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
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("UPDATE sys_user SET username = ?,nickname = ?," +
                    "password = ? WHERE id = ? and status != 0");
            pstat.setString(1,user.getUsername());
            pstat.setString(2,user.getNickname());
            pstat.setString(3,user.getPassword());
            pstat.setInt(4,user.getId());
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        System.out.println(result);
        return result;
    }
    public List<User> selectAllUser() {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_user where status != 0");
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                User user = null;
                user = new User(rs.getInt("id"),rs.getString("username"),
                        rs.getString("password"),rs.getString("nickname"),
                        rs.getTimestamp("create_time"),rs.getTimestamp("update_time"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return userList;
    }
    public List<User> selectUserByPage(Integer pre,Integer end) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_user where status != 0 limit ?, ?");
            pstat.setInt(1,pre);
            pstat.setInt(2,end);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                User user = new User();
                user = new User(rs.getInt("id"),rs.getString("username"),
                        rs.getString("password"),rs.getString("nickname"),
                        rs.getTimestamp("create_time"),rs.getTimestamp("update_time"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return userList;
    }

    public List<User> selectUserByKeyWord(String keyWord) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_user WHERE CONCAT(username,nickname) " +
                    "LIKE \"%\"?\"%\"");
            pstat.setString(1,keyWord);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                User user = new User();
                user = new User(rs.getInt("id"),rs.getString("username"),
                        rs.getString("password"),rs.getString("nickname"),
                        rs.getTimestamp("create_time"),rs.getTimestamp("update_time"));
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

    public Integer countUser() {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT COUNT(*) FROM sys_user");
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
}
