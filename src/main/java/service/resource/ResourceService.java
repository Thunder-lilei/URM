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
    Long selectResourceIdByName(String name);

    String getControlTypeByName(String name);

    List<Resource> getAllMenuResource();

    Resource getResourceById(Long id);

    List<Resource> getAllBtnResource();

    List<Resource> getResourceByMenuResourceId(Long id);

    List<Resource> getBtnResourceByMenuResourceId(Long id);

    List<Resource> getMenuResourceByUserId(Long id);

    Long selectMenuResourceIdByName(String resource_name);

    List<Resource> selectBtnResourcesByUserIdAndMenuResourceId(Long userId, Long menuResourceId);

    List<Long> selectBtnResourcesIdByRoleIdAndMenuResourceId(Long roleId, Long menuResourceId);

    Long selectBtnResourceIdByUserIdAndBtnControlType(Long userId,String controlType);

    List<Resource> selectBtnResourcesByRoleId(Long roleId);

    List<Resource> selectMenuResourceByKeyWord(String keyWord);

    Integer countMenuResource();

    List<Resource> selectMenuResourceByPage(Integer page, Integer pageSize);

    Integer addResource(Resource resource);

    Integer deleteResource(Long id);

}
