package dao;

import org.junit.Test;
import po.Role;
import service.impl.role.RoleServiceImpl;

import java.util.List;

/**
 * <h3>URM</h3>
 * <p></p>
 *
 * @author : 李雷
 * @date : 2020-12-15 12:40
 **/
public class RoleDaoTest {
    RoleServiceImpl roleService = new RoleServiceImpl();
    @Test
    public void selectAll() {
        List<Role> roleList = roleService.selectByUserId(1);
        for (Role role : roleList) {
            System.out.println(role.getRoleName());
        }
    }
}
