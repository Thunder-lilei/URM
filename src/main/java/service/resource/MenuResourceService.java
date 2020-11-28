package service.resource;

import constant.ConstantClassColumn;
import constant.ConstantClassField;
import po.MenuResource;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>UserPowerControl</h3>
 * <p>FatherPowerImpl</p>
 *
 * @author : 李雷
 * @date : 2020-11-24 11:08
 **/
public class MenuResourceService {
    public static List<MenuResource> getAllMenuResource() {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<MenuResource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_menu_resource");
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                MenuResource menuResource = new MenuResource();
                menuResource.setId(rs.getInt(ConstantClassColumn.ID));
                menuResource.setName(rs.getString(ConstantClassColumn.NAME));
                list.add(menuResource);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static List<MenuResource> getMenuResourceByUserId(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<MenuResource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_menu_resource where id = " +
                    "ANY(select menu_resource_id from role_menu_resource where role_id = ANY(select role_id from " +
                    "role_user where user_id = ?))");
            pstat.setInt(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                MenuResource menuResource = new MenuResource();
                menuResource.setId(rs.getInt(ConstantClassColumn.ID));
                menuResource.setName(rs.getString(ConstantClassColumn.NAME));
                list.add(menuResource);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static Integer selectIdByName(String name) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        int result = ConstantClassField.RESULT_ZERO;
        try {
            PreparedStatement pstat = connection.prepareStatement("select id from sys_menu_resource_id where name = ?");
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

    public static String selectTypeById(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        String type = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("select type from sys_menu_resource where id = ?");
            pstat.setInt(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                type = rs.getString(ConstantClassColumn.TYPE);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

}
