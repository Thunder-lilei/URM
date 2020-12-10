package service.role.user;

/**
 * <h3>URM</h3>
 * <p>角色用户类service接口</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 15:39
 **/
public interface RoleUserService {
    Integer insertRoleUser(Integer roleId,Integer userId);

    Integer deleteRoleUser(Integer roleId,Integer userId);

    Boolean selectByRoleIdAndUserId(Integer roleId,Integer userId);
}
