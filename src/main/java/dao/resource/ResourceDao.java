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
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM tbl_resource where " +
                    "resource_name = ?");
            pstat.setString(1,name);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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

    public Long selectBtnResourceIdByUserIdAndBtnControlType(Long userId, String controlType) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Long result = 0L;
        try {
            PreparedStatement pstat = connection.prepareStatement("select id from tbl_resource where id = " +
                    "ANY(select resource_id from tbl_role_resource where role_id = ANY(select role_id from tbl_role_user " +
                    "where user_id = ?)) and control_type = ?");
            pstat.setLong(1,userId);
            pstat.setString(2,controlType);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                result = rs.getLong("id");
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
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM tbl_resource where menu_resource_id "+type+" 0");
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = null;
                resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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

    public List<Resource> getResourceByMenuResourceId(Long id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM tbl_resource where menu_resource_id = ?");
            pstat.setLong(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = null;
                resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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

    public List<Resource> getBtnResourceByMenuResourceId(Long id,String resourceType) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM tbl_resource where menu_resource_id = ? " +
                    "and resource_type = ?");
            pstat.setLong(1,id);
            pstat.setString(2,resourceType);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = null;
                resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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


    public List<Resource> getMenuResourceByUserId(Long id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from tbl_resource where menu_resource_id = 0 " +
                    "and id = ANY(select resource_id from tbl_role_resource where role_id = ANY(select role_id from tbl_role_user " +
                    "where user_id = ?)) order by id");
            pstat.setLong(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = null;
                resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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
            PreparedStatement pstat = connection.prepareStatement("select * from tbl_resource where resource_name = ? and " +
                    "menu_resource_id = 0");
            pstat.setString(1,resource_name);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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
    public List<Resource> selectBtnResourcesByUserIdAndMenuResourceId(Long userId, Long menuResourceId, String resource_type) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from tbl_resource where id = ANY(select " +
                    "resource_id from tbl_role_resource where role_id = ANY(select role_id from tbl_role_user where user_id = ?)) " +
                    "and menu_resource_id = ? and resource_type = ?");
            pstat.setLong(1,userId);
            pstat.setLong(2,menuResourceId);
            pstat.setString(3,resource_type);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = null;
                resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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

    public List<Long> selectBtnResourcesIdByRoleIdAndMenuResourceId(Long userId, Long menuResourceId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Long> list = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select id from tbl_resource where id = ANY(select " +
                    "resource_id from tbl_role_resource where role_id = ?) and menu_resource_id = ?");
            pstat.setLong(1,userId);
            pstat.setLong(2,menuResourceId);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                list.add(rs.getLong("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return list;
    }

    public Resource selectResourceById(Long id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Resource resource = null;
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from tbl_resource where id = ?");
            pstat.setLong(1,id);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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

    public List<Resource> selectBtnResourceByRoleId(Long roleId) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> resourceList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from tbl_resource where id = ANY(select " +
                    "resource_id from tbl_role_resource where role_id = ?) and menu_resource_id != 0");
            pstat.setLong(1,roleId);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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

    public List<Resource> selectMenuResourceByPage(Integer pre,Integer end) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> resourceList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("select * from tbl_resource where menu_resource_id = 0 limit ?, ?");
            pstat.setInt(1,pre);
            pstat.setInt(2,end);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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

    public Integer countMenuResource() {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT COUNT(*) FROM tbl_resource where menu_resource_id = 0");
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

    public List<Resource> selectMenuResourceByKeyWord(String keyWord) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        List<Resource> resourceList = new ArrayList<>();
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM tbl_resource WHERE CONCAT(control_type,resource_name) " +
                    "LIKE \"%\"?\"%\" and menu_resource_id = 0");
            pstat.setString(1,keyWord);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Resource resource = new Resource();
                resource = new Resource(rs.getLong("id"),rs.getLong("menu_resource_id"),
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
            PreparedStatement pstat = connection.prepareStatement("insert into tbl_resource (menu_resource_id," +
                    "control_type,resource_type,resource_name) values(?,?,?,?)");
            pstat.setLong(1,resource.getMenuResourceId());
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

    public Integer deleteResource(Long id) {
        Connection connection = JdbcUtil.INSTANCE.getConnection();
        Integer result = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("delete from tbl_resource where id = ?");
            pstat.setLong(1,id);
            result = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.INSTANCE.closeConn(connection);
        }
        return result;
    }
}
