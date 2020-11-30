package dao.resource;

import po.Resource;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>资源表dao层</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 14:48
 **/
public class ResourceDao {
    public Resource selectBtnReourceByName(String name) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Resource resource = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_resource where " +
                    "btn_resource_name = ?");
            pstat.setString(1,name);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                resource.setId(rs.getInt("id"));
                resource.setMenuResourceId(rs.getInt("menu_resource_id"));
                resource.setResourceName(rs.getString("resource_name"));
                resource.setResourceType(rs.getString("resource_type"));
                resource.setCreateTime(rs.getTimestamp("create_time"));
                resource.setUpdateTime(rs.getTimestamp("update_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resource;
    }

    public List<Resource> getAllResource(String type) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_resource where menu_resource_id "+type+" 0");
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = new Resource();
                resource.setId(rs.getInt("id"));
                resource.setResourceName(rs.getString("resource_name"));
                resource.setResourceType(rs.getString("resource_type"));
                resource.setMenuResourceId(rs.getInt("menu_resource_id"));
                resource.setCreateTime(rs.getTimestamp("create_time"));
                resource.setUpdateTime(rs.getTimestamp("update_time"));
                list.add(resource);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Resource> getResourceByMenuResourceId(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_resource where menu_resource_id = ?");
            pstat.setInt(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = new Resource();
                resource.setId(rs.getInt("id"));
                resource.setResourceName(rs.getString("resource_name"));
                resource.setResourceType(rs.getString("resource_type"));
                resource.setMenuResourceId(rs.getInt("menu_resource_id"));
                resource.setCreateTime(rs.getTimestamp("create_time"));
                resource.setUpdateTime(rs.getTimestamp("update_time"));
                list.add(resource);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Resource> getMenuResourceByUserId(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_resource where menu_resource_id " +
                    "= 0 and id = ANY(select resource_id from role_resource where role_id = ?)");
            pstat.setInt(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = new Resource();
                resource.setId(rs.getInt("id"));
                resource.setResourceName(rs.getString("resource_name"));
                resource.setResourceType(rs.getString("resource_type"));
                resource.setMenuResourceId(rs.getInt("menu_resource_id"));
                resource.setCreateTime(rs.getTimestamp("create_time"));
                resource.setUpdateTime(rs.getTimestamp("update_time"));
                list.add(resource);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
    public Resource selectMenuResourceByName(String resource_name) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Resource resource = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_resource where resource_name = ? and " +
                    "menu_resource_id = 0");
            pstat.setString(1,resource_name);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                resource = new Resource();
                resource.setId(rs.getInt("id"));
                resource.setResourceName(rs.getString("resource_name"));
                resource.setResourceType(rs.getString("resource_type"));
                resource.setMenuResourceId(rs.getInt("menu_resource_id"));
                resource.setCreateTime(rs.getTimestamp("create_time"));
                resource.setUpdateTime(rs.getTimestamp("update_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resource;
    }
    public List<Resource> selectBtnResourcesByUserIdAndMenuResourceId(Integer userId, Integer menuResourceId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_resource where id = ANY(select " +
                    "resource_id from role_resource where role_id = ANY(select role_id from role_user where user_id = ?)) " +
                    "and menu_resource_id = ?");
            pstat.setInt(1,userId);
            pstat.setInt(2,menuResourceId);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = new Resource();
                resource.setId(rs.getInt("id"));
                resource.setResourceName(rs.getString("resource_name"));
                resource.setResourceType(rs.getString("resource_type"));
                resource.setMenuResourceId(rs.getInt("menu_resource_id"));
                resource.setCreateTime(rs.getTimestamp("create_time"));
                resource.setUpdateTime(rs.getTimestamp("update_time"));
                list.add(resource);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
