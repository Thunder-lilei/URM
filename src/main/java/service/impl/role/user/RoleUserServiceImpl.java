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
    /*
     * @Author 李雷
     * @Description //TODO lilei
     * 添加前查看是否已添加
     * @Date 13:51 2020/12/10
     * @Param [roleId, userId]
     * @return java.lang.Integer
     **/
    @Override
    public Integer insertRoleUser(Long roleId,Long userId) {
        if (selectByRoleIdAndUserId(roleId,userId)) {return 0;}
        return roleUserDao.insertRoleUser(roleId,userId);
    }

    @Override
    public Integer deleteRoleUser(Long roleId, Long userId) {return roleUserDao.deleteRoleUser(roleId,userId);}

    @Override
    public Boolean selectByRoleIdAndUserId(Long roleId, Long userId) {return roleUserDao.selectByRoleIdAndUserId(roleId,userId);}

}
