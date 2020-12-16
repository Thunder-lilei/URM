package service.impl.role;

import dao.role.RoleDao;
import dao.role.resource.RoleResourceDao;
import dao.role.user.RoleUserDao;
import po.Role;
import service.role.RoleService;

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
    public Role selectByType(String type) {return roleDao.selectByType(type);}

    @Override
    public Long selectIdByName(String name) {
        Long result = 0L;
        Role role = roleDao.selectByName(name);
        if (role!=null) {result = role.getId();};
        return result;
    }

    @Override
    public List<Role> selectAll() {
        return roleDao.selectAll();
    }

    @Override
    public String selectNameById(Long id) {return roleDao.selectById(id).getRoleName();}

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
    public List<Role> selectByUserId(Long userId) {
        List<Long> roleIds = roleUserDao.selectRoleIdByUserId(userId);
        List<Role> roleList = new ArrayList<>();
        for (Long l : roleIds) {
            roleList.add(roleDao.selectById(l));
        }
        return roleList;
    }

    @Override
    public List<Role> selectRoleByKeyWord(String keyWord) {return roleDao.selectRoleByKeyWord(keyWord);}

    @Override
    public Integer countRole() {return roleDao.countRole();}

    @Override
    public List<Role> selectRoleByPage(Integer page, Integer pageSize) {
        return roleDao.selectRoleByPage((page-1)*pageSize,pageSize);
    }

    /*
     * @Author 李雷
     * @Description //TODO lilei
     * @Date 13:54 2020/12/10
     * @Param [role]
     * @return java.lang.Integer
     * 添加前查询是否已存在
     **/
    @Override
    public Integer addRole(Role role) {
        if (selectByName(role.getRoleName()) != null || selectByType(role.getRoleType()) != null) {return 0;}
        return roleDao.addRole(role);
    }

    /*
     * @Author 李雷
     * @Description //TODO lilei
     * @Date 9:40 2020/12/1
     * @Param [id]
     * @return java.lang.Integer
     * 移除角色的同时移除角色下的所有权限
     **/
    @Override
    public Integer deleteRole(Long id) {
        roleResourceDao.deleteRoleResourceByRoleId(id);
        return roleDao.deleteRole(id);
    }
}
