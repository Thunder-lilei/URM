package serviceImpl.user;

import dao.user.UserDao;
import po.User;
import service.user.UserService;

/**
 * <h3>URM</h3>
 * <p>用户表的服务方法</p>
 *
 * @author : 李雷
 * @date : 2020-11-23 09:54
 **/
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDao();
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

}
