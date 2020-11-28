package service.user;

import po.User;
import pojo.UserPojo;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h3>URM</h3>
 * <p>用户表的服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-23 09:54
 **/
public class UserService {


    public static UserPojo selectByUsername(String username) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        UserPojo userPojo = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_user where user_name = ?");
            pstat.setString(1,username);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                userPojo = new UserPojo();
                userPojo.setId(rs.getInt("id"));
                userPojo.setUserName(rs.getString("user_name"));
                userPojo.setPassword(rs.getString("password"));
                userPojo.setNickname(rs.getString("nickname"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userPojo;
    }

    public static Integer selectIdByUsername(String username) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT id FROM sys_user where user_name = ?");
            pstat.setString(1,username);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                result = rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static String addUser(User user) {
        UserPojo userPojo = selectByUsername(user.getUserName());
        if(userPojo != null) {
            return "失败";
        }
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into sys_user (user_name,password,nickname) " +
                    "values(?,?,?)");
            pstat.setString(1,user.getUserName());
            pstat.setString(2,user.getPassword());
            pstat.setString(3, user.getNickname());
            if(pstat.executeUpdate() == 1) {return "成功";}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "失败";
    }

}
