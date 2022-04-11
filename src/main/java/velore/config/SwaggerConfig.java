package velore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger接口管理 配置
 * @author Velore
 * @date 2022/3/2
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        ParameterBuilder builder = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<>();
        builder.name("token").description("登录令牌token")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false)
                .build();
        parameterList.add(builder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("All")
                .select()
                .apis(RequestHandlerSelectors.basePackage("velore.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameterList)
                ;

    }

    public ApiInfo apiInfo(){
        return new ApiInfo(
                "EasyBlog API",
                "easy blog",
                "v1.0",
                "https://github.com/Velore",
                new Contact("velore", "https://github.com/Velore", ""),
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
