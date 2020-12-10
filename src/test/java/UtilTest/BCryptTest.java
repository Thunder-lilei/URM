package UtilTest;

import org.junit.Test;
import util.BCrypt;

/**
 * <h3>URM</h3>
 * <p></p>
 *
 * @author : 李雷
 * @date : 2020-12-10 15:40
 **/
public class BCryptTest {
    @Test
    public void BCrypt() {
        String pwd = "1";
        String pwdB = BCrypt.hashpw(pwd,BCrypt.gensalt());
        System.out.println(pwdB);
    }
}
