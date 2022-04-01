package velore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import velore.bo.ArticleQueryBo;
import velore.service.ArticleService;
import velore.service.UserService;

import javax.annotation.Resource;

@SpringBootTest
class EasyBlogApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private ArticleService articleService;

    @Test
    void contextLoads() {
//        System.out.println(userService.getCount());
        System.out.println(articleService.queryByQueryBo(new ArticleQueryBo()));
    }

}
