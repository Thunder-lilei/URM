package service.resource;

import pojo.BtnResourcePojo;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>按钮资源表的服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-24 14:21
 **/
public class BtnResourceService {
    public static Integer selectBtnReourceIdByName(String name) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT id FROM sys_btn_resource where " +
                    "btn_resource_name = ?");
            pstat.setString(1,name);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static List<BtnResourcePojo> selectBtnResourcesByUserIdAndMenuResourceName(Integer userId, String name) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<BtnResourcePojo> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_btn_resource where " +
                    "menu_resource_name = ? and id = ANY(SELECT id FROM sys_btn_resource where id = ANY(SELECT " +
                    "btn_resource_id FROM role_btn_resource where role_id = ANY(select id FROM role_user where user_id = ?)))");
            pstat.setString(1,name);
            pstat.setInt(2,userId);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                BtnResourcePojo power = new BtnResourcePojo();
                power.setId(rs.getInt("id"));
                power.setBtnResourceName(rs.getString("btn_resource_name"));
                power.setMenuResourceName(rs.getString("menu_resource_name"));
                power.setUrl(rs.getString("url"));
                power.setBtnResourceNickname(rs.getString("btn_resource_nickname"));
                list.add(power);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static Integer selectMenuResourceIdById(Integer powerId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        String menuResourceName = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("select menu_resource_name from sys_btn_resource " +
                    "where id = ?");
            pstat.setInt(1,powerId);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                menuResourceName = rs.getString("menu_resource_name");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int result = MenuResourceService.selectIdByName(menuResourceName);
        return result;
    }

    public static Integer addBtnResource(BtnResourcePojo btnResourcePojo) {
        if (!selectBtnReourceIdByName(btnResourcePojo.getBtnResourceName()).equals(0)) {
            return 0;
        }
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into sys_btn_resource(btn_resource_name" +
                    ",menu_resource_name,url,btn_resource_type,btn_resource_nickname) values(?,?,?,?,?)");
            pstat.setString(1,btnResourcePojo.getBtnResourceName());
            pstat.setString(2,btnResourcePojo.getMenuResourceName());
            pstat.setString(3,btnResourcePojo.getUrl());
            pstat.setString(4,btnResourcePojo.getBtnResourceType());
            pstat.setString(5,btnResourcePojo.getBtnResourceNickname());
            result = pstat.executeUpdate();
            System.out.println(result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
