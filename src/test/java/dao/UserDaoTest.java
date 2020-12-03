package dao;

import dao.user.UserDao;
import org.junit.Test;
import po.User;

import java.util.List;

/**
 * <h3>URM</h3>
 * <p>User dao层 测试</p>
 *
 * @author : 李雷
 * @date : 2020-12-03 10:03
 **/
public class UserDaoTest {
    UserDao userDao = new UserDao();
    @Test
    public void selectUserByKeyWordTest(){
        List<User> userList = userDao.selectUserByKeyWord("老");
        for (User user : userList) {
            System.out.println(user.getUserName());
        }
    };
}
