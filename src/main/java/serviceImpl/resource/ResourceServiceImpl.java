package serviceImpl.resource;

import dao.resource.ResourceDao;
import dao.role.resource.RoleResourceDao;
import po.Resource;
import serviceImpl.role.resource.RoleResourceServiceImpl;

import java.util.List;

/**
 * <h3>URM</h3>
 * <p>资源表service层</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 14:49
 **/
public class ResourceServiceImpl implements service.resource.ResourceService {
    ResourceDao resourceDao = new ResourceDao();
    RoleResourceDao roleResourceDao = new RoleResourceDao();

    @Override
    public Integer selectResourceIdByName(String name) {
        Resource resource = resourceDao.selectResourceByName(name);
        if (resource!=null) {return resource.getId();};
        return 0;
    }

    @Override
    public String getControlTypeByName(String name) {
        Resource resource = resourceDao.selectResourceByName(name);
        if (resource != null) {return resource.getResourceType();}
        return null;
    }

    @Override
    public List<Resource> getAllMenuResource() {return resourceDao.getAllResource("=");}

    @Override
    public Resource getResourceById(Integer id) {
        return resourceDao.selectResourceById(id);
    }

    @Override
    public List<Resource> getAllBtnResource() {return resourceDao.getAllResource("!=");}

    @Override
    public List<Resource> getResourceByMenuResourceId(Integer id) {return resourceDao.getResourceByMenuResourceId(id);}

    @Override
    public List<Resource> getBtnResourceByMenuResourceId(Integer id) {
        String resourceType = "menu_btn";
        return resourceDao.getBtnResourceByMenuResourceId(id,resourceType);
    }

    @Override
    public List<Resource> getMenuResourceByUserId(Integer id) {return resourceDao.getMenuResourceByUserId(id);}
    @Override
    public Integer selectMenuResourceIdByName(String resource_name) {
        Resource resource = resourceDao.selectMenuResourceByName(resource_name);
        if (resource!=null) {return resource.getId();};
        return 0;
    };
    @Override
    public List<Resource> selectBtnResourcesByUserIdAndMenuResourceId(Integer userId, Integer menuResourceId) {
        String resourceTypeMenuBtn = "menu_btn";
        return resourceDao.selectBtnResourcesByUserIdAndMenuResourceId(userId,menuResourceId,resourceTypeMenuBtn);
    }

    @Override
    public List<Integer> selectBtnResourcesIdByRoleIdAndMenuResourceId(Integer roleId, Integer menuResourceId) {
        return resourceDao.selectBtnResourcesIdByRoleIdAndMenuResourceId(roleId,menuResourceId);
    }

    @Override
    public Integer selectBtnResourceIdByUserIdAndBtnControlType(Integer userId, String resourceType) {
        return resourceDao.selectBtnResourceIdByUserIdAndBtnControlType(userId,resourceType);
    }

    @Override
    public List<Resource> selectBtnResourcesByRoleId(Integer roleId) {
        return resourceDao.selectBtnResourceByRoleId(roleId);
    }

    @Override
    public Integer addResource(Resource resource) {
        return resourceDao.addResource(resource);
    }

    /*
     * @Author 李雷
     * @Description //TODO lilei
     * @Date 11:24 2020/12/4
     * @Param [id]
     * @return java.lang.Integer
     * 删除资源的同时删除资源与角色的对应关系
     * 如果是父级资源还要删除对应的次级资源
     **/
    @Override
    public Integer deleteResource(Integer id) {
        roleResourceDao.deleteRoleResourceByResourceId(id);
        Resource resource = resourceDao.selectResourceById(id);
        if (resource.getMenuResourceId().equals(0)) {
            List<Resource> btnResourceList = resourceDao.getResourceByMenuResourceId(resource.getId());
            for (Resource btnResource : btnResourceList) {
                deleteResource(btnResource.getId());
            }
        }
        return resourceDao.deleteResource(id);
    }

}
