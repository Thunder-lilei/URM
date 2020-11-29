package serviceImpl.role.resource;

import dao.role.resource.RoleResourceDao;
import service.role.resource.RoleResourceService;

/**
 * <h3>URM</h3>
 * <p>角色资源服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 14:47
 **/
public class RoleResourceServiceImpl implements RoleResourceService {
    RoleResourceDao roleResourceDao = new RoleResourceDao();

    public Integer insertRoleResource(Integer resourceId,Integer roleId) {
        return roleResourceDao.addRoleResource(resourceId,roleId);
    }

    public Boolean ifSaveByRoleIdAndMenuResourceId(Integer roleId,Integer resourceId) {
        return roleResourceDao.ifSaveRoleResource(roleId,resourceId);
    }
}
