package velore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author velore
 * @date 2022/3/2
 */
@MapperScan("velore.dao")
@SpringBootApplication
public class EasyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyBlogApplication.class, args);
    }

}
