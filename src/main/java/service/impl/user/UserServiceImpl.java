package service.impl.user;

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
    public User selectByUsernameWithoutId(String username, Long id) {
        return userDao.selectByUsernameWithoutId(username,id);
    }

    @Override
    public User selectById(Long id) {return userDao.selectById(id);}

    @Override
    public Long selectIdByUsername(String username) {
        Long result = 0L;
        User user = userDao.selectByUsername(username);
        if(user!=null) {result = user.getId();}
        return result;
    }
    @Override
    public Integer addUser(User user) {
        if(userDao.selectByUsername(user.getUsername()) != null) {
            return 0;
        }
        return userDao.insertUser(user);
    }

    /*
     * @Author 李雷
     * @Description
     * 不能重名
     * @Date 13:43 2020/12/10
     * @Param [user]
     * @return java.lang.Integer
     **/
    @Override
    public Integer updateUser(User user) {
        if (selectByUsernameWithoutId(user.getUsername(),user.getId()) != null) {return 0;}
        return userDao.updateUserById(user);
    }

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
    public Integer deleteUser(Long id) {
        roleUserDao.deleteRoleUserByUserId(id);
        return userDao.deleteUserById(id);
    }

    @Override
    public Integer countUser() {return userDao.countUser();}

    @Override
    public List<User> selectUserByPage(Integer page,Integer pageSize) {return userDao.selectUserByPage((page-1)*pageSize,pageSize);}

    @Override
    public List<User> selectUserByKeyWord(String keyWord) {return userDao.selectUserByKeyWord(keyWord);}

}
