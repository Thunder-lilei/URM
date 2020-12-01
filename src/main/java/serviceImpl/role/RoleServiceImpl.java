package serviceImpl.role;

import dao.role.RoleDao;
import dao.role.resource.RoleResourceDao;
import dao.role.user.RoleUserDao;
import po.Role;
import service.role.RoleService;
import util.JdbcUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>角色表的服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-26 09:25
 **/
public class RoleServiceImpl implements RoleService {
    RoleDao roleDao = new RoleDao();
    RoleUserDao roleUserDao = new RoleUserDao();
    RoleResourceDao roleResourceDao = new RoleResourceDao();

    @Override
    public Role selectByName(String name) {
        return roleDao.selectByName(name);
    }

    @Override
    public Integer selectIdByName(String name) {
        int result = 0;
        Role role = roleDao.selectByName(name);
        if (role!=null) {result = role.getId();};
        return result;
    }

    @Override
    public List<Role> selectAll() {
        return roleDao.selectAll();
    }

    @Override
    public String selectNameById(Integer id) {return roleDao.selectById(id).getRoleName();}

    /*
     * @Author 李雷
     * @Description //TODO lilei
     * @Date 10:11 2020/12/1
     * @Param [userId]
     * @return java.util.List<po.Role>
     * 通过用户id查询所有角色
     * 通过角色id查询所有角色信息
     **/
    @Override
    public List<Role> selectByUserId(Integer userId) {
        List<Integer> roleIds = roleUserDao.selectRoleIdByUserId(userId);
        List<Role> roleList = new ArrayList<>();
        for (Integer integer : roleIds) {
            roleList.add(roleDao.selectById(integer));
        }
        return roleList;
    }

    @Override
    public Integer addRole(Role role) {return roleDao.addRole(role);}

    /*
     * @Author 李雷
     * @Description //TODO lilei
     * @Date 9:40 2020/12/1
     * @Param [id]
     * @return java.lang.Integer
     * 移除角色的同时移除角色下的所有权限
     **/
    @Override
    public Integer deleteRole(Integer id) {
        roleResourceDao.deleteRoleResourceByRoleId(id);
        return roleDao.deleteRole(id);
    }
}
