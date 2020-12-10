package UtilTest;

import org.junit.Test;
import util.MD5Util;

/**
 * <h3>URM</h3>
 * <p>md5测试</p>
 *
 * @author : 李雷
 * @date : 2020-12-01 15:40
 **/
public class MD5Test {
    @Test
    public void MD5Test() {
        String passsword = "1";
        System.out.println(MD5Util.md5(passsword));
    }
}
