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

    Role selectByType(String type);

    Long selectIdByName(String name);

    List<Role> selectAll();

    String selectNameById(Long id);

    List<Role> selectByUserId(Long userId);

    List<Role> selectRoleByKeyWord(String keyWord);

    Integer countRole();

    List<Role> selectRoleByPage(Integer page, Integer pageSize);

    Integer addRole(Role role);

    Integer deleteRole(Long id);
}
