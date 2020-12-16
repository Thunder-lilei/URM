package service.role.user;

/**
 * <h3>URM</h3>
 * <p>角色用户类service接口</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 15:39
 **/
public interface RoleUserService {
    Integer insertRoleUser(Long roleId,Long userId);

    Integer deleteRoleUser(Long roleId,Long userId);

    Boolean selectByRoleIdAndUserId(Long roleId,Long userId);
}
