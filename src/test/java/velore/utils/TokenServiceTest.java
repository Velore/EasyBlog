package velore.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import velore.po.User;
import velore.po.UserType;
import velore.security.TokenService;

import javax.annotation.Resource;

/**
 * @author Velore
 * @date 2022/3/3
 **/
@SpringBootTest
public class TokenServiceTest {

    @Resource
    TokenService tokenService;

    @Test
    public void generateTest(){
        User user = new User();
        user.setId(2);
        user.setUserType(UserType.ADMIN);
        String token = tokenService.generate(user);
        System.out.println(token);
    }

    @Test
    public void generateAndVerifyTest() {
        User user = new User();
        user.setId(2);
        user.setUserType(UserType.ADMIN);
        String token = tokenService.generate(user);
        System.out.println(token);
        try {
            tokenService.verify(token);
            System.out.println("token verify success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTokenIdTest() {
        User user = new User();
        user.setId(2);
        user.setUserType(UserType.ADMIN);
        String token = tokenService.generate(user);
        System.out.println(tokenService.getTokenId(token));
    }

    @Test
    public void refreshTest() {
        User user = new User();
        user.setId(2);
        user.setUserType(UserType.ADMIN);
        String token = tokenService.generate(user);
        System.out.println(token);
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(tokenService.refresh(token));
    }
}