package velore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utils.Md5Util;
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
//        System.out.println(articleService.queryByQueryBo(new ArticleQueryBo()));
        System.out.println("admin1:"+Md5Util.encrypt("admin1"));
    }

}
