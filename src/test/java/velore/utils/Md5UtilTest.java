package velore.utils;

import org.junit.jupiter.api.Test;

/**
 * @author Velore
 * @date 2022/3/2
 **/
public class Md5UtilTest {

    @Test
    public void encryptTest(){
        System.out.println(Md5Util.encrypt("111"));
    }

    @Test
    public void verifyTest(){
        String pwd = "111";
        System.out.println(Md5Util.verify(pwd, Md5Util.encrypt(pwd)));
    }
}
