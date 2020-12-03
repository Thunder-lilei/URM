package dao;

import dao.resource.ResourceDao;
import org.junit.Test;
import serviceImpl.resource.ResourceServiceImpl;

/**
 * <h3>URM</h3>
 * <p>Resource dao层 Test</p>
 *
 * @author : 李雷
 * @date : 2020-12-03 10:29
 **/
public class ResourceDaoTest {
    ResourceDao resourceDao =  new ResourceDao();
    ResourceServiceImpl resourceService = new ResourceServiceImpl();

    @Test
    public void selectBtnResourceIdByUserIdAndBtnResourceType() {
        String btnResourceAddUser = resourceService.getResourceTypeByName("注册用户");
        String btnResourceDeleteUser = resourceService.getResourceTypeByName("移除用户");
        String btnResourceSelectUser = resourceService.getResourceTypeByName("查询用户");
        String btnResourceUpdateUser = resourceService.getResourceTypeByName("更新用户信息");
        System.out.println(btnResourceAddUser+btnResourceDeleteUser+btnResourceSelectUser+btnResourceUpdateUser);
        System.out.println(resourceDao.selectBtnResourceIdByUserIdAndBtnResourceType(2,"DeleteUser"));
    }
}
