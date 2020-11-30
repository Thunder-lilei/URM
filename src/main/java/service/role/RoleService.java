package service.role;

import po.Role;

import java.util.List;

/**
 * <h3>URM</h3>
 * <p>角色类service接口</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 15:41
 **/
public interface RoleService {
    Role selectByName(String name);
    Integer selectIdByName(String name);
    List<Role> selectAll();
}
