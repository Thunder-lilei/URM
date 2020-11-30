package serviceImpl.user;

import dao.role.user.RoleUserDao;
import dao.user.UserDao;
import po.User;
import service.user.UserService;

import java.util.List;

/**
 * <h3>URM</h3>
 * <p>用户表的服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-23 09:54
 **/
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDao();
    RoleUserDao roleUserDao = new RoleUserDao();
    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }
    @Override
    public Integer selectIdByUsername(String username) {
        int result = 0;
        User user = userDao.selectByUsername(username);
        if(user!=null) {result = user.getId();}
        return result;
    }
    @Override
    public Integer addUser(User user) {
        if(userDao.selectByUsername(user.getUserName()) != null) {
            return 0;
        }
        return userDao.insertUser(user);
    }

    @Override
    public Integer updateUser(User user) {return userDao.updateUserById(user);}

    @Override
    public List<User> selectAllUser() {return userDao.selectAllUser();}
    /*
     * @Author 李雷
     * @Description //TODO lilei
     * 删除与用户相关连的权限 保留用户信息
     * @Date 17:52 2020/11/30
     * @Param [id]
     * @return java.lang.Integer
     **/
    @Override
    public Integer deleteUser(Integer id) {
        roleUserDao.deleteRoleUserByUserId(id);
        return userDao.deleteUserById(id);
    }

}
