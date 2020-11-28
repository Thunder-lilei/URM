package service.user;

import constant.ConstantClassColumn;
import constant.ConstantClassField;
import po.User;
import pojo.UserPojo;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h3>UserPowerControl</h3>
 * <p>UserServiceImpl</p>
 *
 * @author : 李雷
 * @date : 2020-11-23 09:54
 **/
public class UserService {
    private static final String MESSAGE_ADD_SUCCESS = "成功";
    private static final String MESSAGE_ADD_LOSE = "失败";


    public static UserPojo selectByUsername(String username) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        UserPojo userPojo = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_user where user_name = ?");
            pstat.setString(1,username);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                userPojo = new UserPojo();
                userPojo.setId(rs.getInt(ConstantClassColumn.ID));
                userPojo.setUserName(rs.getString(ConstantClassColumn.USERNAME));
                userPojo.setPassword(rs.getString(ConstantClassColumn.PASSWORD));
                userPojo.setNickname(rs.getString(ConstantClassColumn.NICKNAME));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userPojo;
    }

    public static Integer selectIdByUsername(String username) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = ConstantClassField.RESULT_ZERO;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT id FROM sys_user where user_name = ?");
            pstat.setString(1,username);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                result = rs.getInt(ConstantClassColumn.ID);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static String addUser(User user) {
        UserPojo userPojo = selectByUsername(user.getUserName());
        if(userPojo != null) {
            return MESSAGE_ADD_LOSE;
        }
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into sys_user (user_name,password,nickname) " +
                    "values(?,?,?)");
            pstat.setString(1,user.getUserName());
            pstat.setString(2,user.getPassword());
            pstat.setString(3, user.getNickname());
            if(pstat.executeUpdate() == 1) {return MESSAGE_ADD_SUCCESS;}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return MESSAGE_ADD_LOSE;
    }

}
