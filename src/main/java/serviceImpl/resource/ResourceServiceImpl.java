package serviceImpl.resource;

import dao.resource.ResourceDao;
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
    public List<Resource> getBtnResourceByMenuResourceId(Integer id) {return resourceDao.getResourceByMenuResourceId(id);}

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
        String resource_type_menu_btn = "menu_btn";
        return resourceDao.selectBtnResourcesByUserIdAndMenuResourceId(userId,menuResourceId,resource_type_menu_btn);
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
    public Integer addResource(Resource resource) {return resourceDao.addResource(resource);}
}
