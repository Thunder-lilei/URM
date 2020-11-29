package service.role.resource;

/**
 * <h3>URM</h3>
 * <p>角色资源类service接口</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 15:38
 **/
public interface RoleResourceService {
    public Integer insertRoleResource(Integer resourceId,Integer roleId);

    public Boolean ifSaveByRoleIdAndMenuResourceId(Integer roleId,Integer resourceId);
}
