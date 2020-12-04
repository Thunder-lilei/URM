package service.resource;

import po.Resource;

import java.util.List;

/**
 * <h3>URM</h3>
 * <p>资源类service接口</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 15:35
 **/
public interface ResourceService {
    Integer selectResourceIdByName(String name);

    String getControlTypeByName(String name);

    List<Resource> getAllMenuResource();

    Resource getResourceById(Integer id);

    List<Resource> getAllBtnResource();

    List<Resource> getResourceByMenuResourceId(Integer id);

    List<Resource> getBtnResourceByMenuResourceId(Integer id);

    List<Resource> getMenuResourceByUserId(Integer id);

    Integer selectMenuResourceIdByName(String resource_name);

    List<Resource> selectBtnResourcesByUserIdAndMenuResourceId(Integer userId, Integer menuResourceId);

    List<Integer> selectBtnResourcesIdByRoleIdAndMenuResourceId(Integer roleId, Integer menuResourceId);

    Integer selectBtnResourceIdByUserIdAndBtnControlType(Integer userId,String controlType);

    List<Resource> selectBtnResourcesByRoleId(Integer roleId);

    Integer addResource(Resource resource);

    Integer deleteResource(Integer id);

}
