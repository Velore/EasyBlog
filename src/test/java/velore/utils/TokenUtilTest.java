package velore.utils;

import org.junit.jupiter.api.Test;
import velore.po.User;
import velore.po.UserType;

/**
 * @author Velore
 * @date 2022/3/3
 **/
public class TokenUtilTest {

    @Test
    public void generateTest(){
        User user = new User();
        user.setId(2);
        user.setUserType(UserType.ADMIN.getValue());
        String token = TokenUtil.generate(user);
        System.out.println(token);
    }

    @Test
    public void generateAndVerifyTest() {
        User user = new User();
        user.setId(2);
        user.setUserType(UserType.ADMIN.getValue());
        String token = TokenUtil.generate(user);
        System.out.println(token);
        try {
            TokenUtil.verify(token);
            System.out.println("token verify success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTokenIdTest() {
        User user = new User();
        user.setId(2);
        user.setUserType(UserType.ADMIN.getValue());
        String token = TokenUtil.generate(user);
        System.out.println(TokenUtil.getTokenId(token));
    }

    @Test
    public void refreshTest() {
        User user = new User();
        user.setId(2);
        user.setUserType(UserType.ADMIN.getValue());
        String token = TokenUtil.generate(user);
        System.out.println(token);
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(TokenUtil.refresh(token));
    }
}