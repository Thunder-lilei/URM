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
    public Integer selectBtnReourceIdByName(String name) {
        Resource resource = resourceDao.selectBtnReourceByName(name);
        if (resource!=null) {return resource.getId();};
        return 0;
    }
    public List<Resource> getAllMenuResource() {return resourceDao.getAllMenuResource();}
    public List<Resource> getMenuResourceByUserId(Integer id) {return resourceDao.getMenuResourceByUserId(id);}
    public Integer selectMenuResourceIdByName(String resource_name) {
        Resource resource = resourceDao.selectMenuResourceByName(resource_name);
        if (resource!=null) {return resource.getId();};
        return 0;
    };
    public List<Resource> selectBtnResourcesByUserIdAndMenuResourceId(Integer userId, Integer menuResourceId) {
        return resourceDao.selectBtnResourcesByUserIdAndMenuResourceId(userId,menuResourceId);
    }
}
