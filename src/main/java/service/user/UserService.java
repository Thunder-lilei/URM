package service.user;

import po.User;

import java.util.List;

/**
 * <h3>URM</h3>
 * <p>用户类service接口</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 15:40
 **/
public interface UserService {
    User selectByUsername(String username);

    User selectById(Integer id);

    Integer selectIdByUsername(String username);

    Integer addUser(User user);

    Integer updateUser(User user);

    List<User> selectAllUser();

    Integer deleteUser(Integer id);

    Integer countUser();

    List<User> selectUserByPage(Integer page,Integer pageSize);

    List<User> selectUserByKeyWord(String keyWord);
}
