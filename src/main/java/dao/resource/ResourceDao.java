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
    public Resource selectResourceByName(String name) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Resource resource = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_resource where " +
                    "resource_name = ?");
            pstat.setString(1,name);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                resource = new Resource(rs.getInt("id"),rs.getInt("menu_resource_id"),
                        rs.getString("control_type"),rs.getString("resource_type"),
                        rs.getString("resource_name"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return resource;
    }

    public Integer selectBtnResourceIdByUserIdAndBtnControlType(Integer userId, String controlType) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("select id from sys_resource where id = " +
                    "ANY(select resource_id from role_resource where role_id = ANY(select role_id from role_user " +
                    "where user_id = ?)) and control_type = ?");
            pstat.setInt(1,userId);
            pstat.setString(2,controlType);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                result = rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }

    public List<Resource> getAllResource(String type) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM sys_resource where menu_resource_id "+type+" 0");
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = null;
                resource = new Resource(rs.getInt("id"),rs.getInt("menu_resource_id"),
                        rs.getString("control_type"),rs.getString("resource_type"),
                        rs.getString("resource_name"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
                list.add(resource);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
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
                Resource resource = null;
                resource = new Resource(rs.getInt("id"),rs.getInt("menu_resource_id"),
                        rs.getString("control_type"),rs.getString("resource_type"),
                        rs.getString("resource_name"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
                list.add(resource);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return list;
    }

    public List<Resource> getMenuResourceByUserId(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_resource where menu_resource_id = 0 " +
                    "and id = ANY(select resource_id from role_resource where role_id = ANY(select role_id from role_user " +
                    "where user_id = ?)) order by id");
            pstat.setInt(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = null;
                resource = new Resource(rs.getInt("id"),rs.getInt("menu_resource_id"),
                        rs.getString("control_type"),rs.getString("resource_type"),
                        rs.getString("resource_name"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
                list.add(resource);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
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
                resource = new Resource(rs.getInt("id"),rs.getInt("menu_resource_id"),
                        rs.getString("control_type"),rs.getString("resource_type"),
                        rs.getString("resource_name"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return resource;
    }
    public List<Resource> selectBtnResourcesByUserIdAndMenuResourceId(Integer userId, Integer menuResourceId, String resource_type) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_resource where id = ANY(select " +
                    "resource_id from role_resource where role_id = ANY(select role_id from role_user where user_id = ?)) " +
                    "and menu_resource_id = ? and resource_type = ?");
            pstat.setInt(1,userId);
            pstat.setInt(2,menuResourceId);
            pstat.setString(3,resource_type);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = null;
                resource = new Resource(rs.getInt("id"),rs.getInt("menu_resource_id"),
                        rs.getString("control_type"),rs.getString("resource_type"),
                        rs.getString("resource_name"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
                list.add(resource);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return list;
    }

    public List<Integer> selectBtnResourcesIdByRoleIdAndMenuResourceId(Integer userId, Integer menuResourceId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select id from sys_resource where id = ANY(select " +
                    "resource_id from role_resource where role_id = ?) and menu_resource_id = ?");
            pstat.setInt(1,userId);
            pstat.setInt(2,menuResourceId);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return list;
    }

    public Resource selectResourceById(Integer id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Resource resource = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_resource where id = ?");
            pstat.setInt(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                resource = new Resource(rs.getInt("id"),rs.getInt("menu_resource_id"),
                        rs.getString("control_type"),rs.getString("resource_type"),
                        rs.getString("resource_name"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return resource;
    }

    public List<Resource> selectBtnResourceByRoleId(Integer roleId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> resourceList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from sys_resource where id = ANY(select " +
                    "resource_id from role_resource where role_id = ?) and menu_resource_id != 0");
            pstat.setInt(1,roleId);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = null;
                resource = new Resource(rs.getInt("id"),rs.getInt("menu_resource_id"),
                        rs.getString("control_type"),rs.getString("resource_type"),
                        rs.getString("resource_name"),rs.getTimestamp("create_time"),
                        rs.getTimestamp("update_time"));
                resourceList.add(resource);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return resourceList;
    }

    public Integer addResource(Resource resource) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("insert into sys_resource (menu_resource_id," +
                    "control_type,resource_type,resource_name) values(?,?,?,?)");
            pstat.setInt(1,resource.getMenuResourceId());
            pstat.setString(2,resource.getControlType());
            pstat.setString(3,resource.getResourceType());
            pstat.setString(4,resource.getResourceName());
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }
}
