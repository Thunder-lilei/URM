package service.impl.resource;

import dao.resource.ResourceDao;
import dao.role.resource.RoleResourceDao;
import po.Resource;

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
    public Long selectResourceIdByName(String name) {
        Resource resource = resourceDao.selectResourceByName(name);
        if (resource!=null) {return resource.getId();};
        return 0L;
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
    public Resource getResourceById(Long id) {
        return resourceDao.selectResourceById(id);
    }

    @Override
    public List<Resource> getAllBtnResource() {return resourceDao.getAllResource("!=");}

    @Override
    public List<Resource> getResourceByMenuResourceId(Long id) {return resourceDao.getResourceByMenuResourceId(id);}

    @Override
    public List<Resource> getBtnResourceByMenuResourceId(Long id) {
        String resourceType = "menu_btn";
        return resourceDao.getBtnResourceByMenuResourceId(id,resourceType);
    }

    @Override
    public List<Resource> getMenuResourceByUserId(Long id) {return resourceDao.getMenuResourceByUserId(id);}
    @Override
    public Long selectMenuResourceIdByName(String resource_name) {
        Resource resource = resourceDao.selectMenuResourceByName(resource_name);
        if (resource!=null) {return resource.getId();};
        return 0L;
    };
    @Override
    public List<Resource> selectBtnResourcesByUserIdAndMenuResourceId(Long userId, Long menuResourceId) {
        String resourceTypeMenuBtn = "menu_btn";
        return resourceDao.selectBtnResourcesByUserIdAndMenuResourceId(userId,menuResourceId,resourceTypeMenuBtn);
    }

    @Override
    public List<Long> selectBtnResourcesIdByRoleIdAndMenuResourceId(Long roleId, Long menuResourceId) {
        return resourceDao.selectBtnResourcesIdByRoleIdAndMenuResourceId(roleId,menuResourceId);
    }

    @Override
    public Long selectBtnResourceIdByUserIdAndBtnControlType(Long userId, String resourceType) {
        return resourceDao.selectBtnResourceIdByUserIdAndBtnControlType(userId,resourceType);
    }

    @Override
    public List<Resource> selectBtnResourcesByRoleId(Long roleId) {
        return resourceDao.selectBtnResourceByRoleId(roleId);
    }

    @Override
    public List<Resource> selectMenuResourceByKeyWord(String keyWord) {return resourceDao.selectMenuResourceByKeyWord(keyWord);}

    @Override
    public Integer countMenuResource() {return resourceDao.countMenuResource();}

    @Override
    public List<Resource> selectMenuResourceByPage(Integer page, Integer pageSize) {return resourceDao.selectMenuResourceByPage((page-1)*pageSize,pageSize);}

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
    public Integer deleteResource(Long id) {
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
