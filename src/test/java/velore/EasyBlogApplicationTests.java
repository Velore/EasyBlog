package velore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import velore.service.UserService;

import javax.annotation.Resource;

@SpringBootTest
class EasyBlogApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.getCount());
    }

}
