package service.user;

import po.User;

/**
 * <h3>URM</h3>
 * <p>用户类service接口</p>
 *
 * @author : 李雷
 * @date : 2020-11-29 15:40
 **/
public interface UserService {
    public User selectByUsername(String username);
    public Integer selectIdByUsername(String username);

    public Integer addUser(User user);
}
