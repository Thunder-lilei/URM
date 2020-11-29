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
    public Integer selectBtnReourceIdByName(String name);
    public List<Resource> getAllMenuResource();
    public List<Resource> getMenuResourceByUserId(Integer id);
    public Integer selectMenuResourceIdByName(String resource_name);
    public List<Resource> selectBtnResourcesByUserIdAndMenuResourceId(Integer userId, Integer menuResourceId);
}
