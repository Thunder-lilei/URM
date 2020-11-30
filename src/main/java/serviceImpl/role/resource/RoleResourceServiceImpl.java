package serviceImpl.role.resource;

import dao.resource.ResourceDao;
import dao.role.resource.RoleResourceDao;
import po.Resource;
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
    ResourceDao resourceDao = new ResourceDao();
    /*
     * @Author 李雷
     * @Description //TODO lilei
     * 为角色赋权的同时查看该按钮权限的菜单权限是否也也该角色关联
     * @Date 15:03 2020/11/30
     * @Param [resourceId, roleId]
     * @return java.lang.Integer
     **/
    @Override
    public Integer insertRoleResource(Integer resourceId,Integer roleId) {
        Resource resource = resourceDao.selectResourceById(resourceId);
        if (!ifSaveByRoleIdAndResourceId(roleId,resource.getMenuResourceId())) {roleResourceDao.addRoleResource(resource.getMenuResourceId(),roleId);}
        return roleResourceDao.addRoleResource(resourceId,roleId);
    }
    @Override
    public Boolean ifSaveByRoleIdAndResourceId(Integer roleId,Integer resourceId) {
        return roleResourceDao.ifSaveRoleResource(roleId,resourceId);
    }
}
