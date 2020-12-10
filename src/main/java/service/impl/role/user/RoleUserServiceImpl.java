package service.impl.role.user;

import dao.role.user.RoleUserDao;
import service.role.user.RoleUserService;

/**
 * <h3>URM</h3>
 * <p>角色用户表的服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-26 09:47
 **/
public class RoleUserServiceImpl implements RoleUserService {
    RoleUserDao roleUserDao = new RoleUserDao();
    @Override
    public Integer insertRoleUser(Integer roleId,Integer userId) {
        return roleUserDao.insertRoleUser(roleId,userId);
    }

    @Override
    public Integer deleteRoleUser(Integer roleId, Integer userId) {return roleUserDao.deleteRoleUser(roleId,userId);}

}
