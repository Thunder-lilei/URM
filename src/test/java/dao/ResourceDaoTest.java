package dao;

import dao.resource.ResourceDao;
import org.junit.Test;
import service.impl.resource.ResourceServiceImpl;

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
        String btnResourceAddUser = resourceService.getControlTypeByName("注册用户");
        String btnResourceDeleteUser = resourceService.getControlTypeByName("移除用户");
        String btnResourceSelectUser = resourceService.getControlTypeByName("查询用户");
        String btnResourceUpdateUser = resourceService.getControlTypeByName("更新用户信息");
        System.out.println(btnResourceAddUser+btnResourceDeleteUser+btnResourceSelectUser+btnResourceUpdateUser);
        System.out.println(resourceDao.selectBtnResourceIdByUserIdAndBtnControlType(2,"DeleteUser"));
    }
}
