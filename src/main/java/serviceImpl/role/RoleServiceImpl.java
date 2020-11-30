package serviceImpl.role;

import dao.role.RoleDao;
import po.Role;
import service.role.RoleService;
import util.JdbcUtil;

import java.sql.Connection;
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
}
