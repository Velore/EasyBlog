package velore.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * blog tag
 * 多对多关系
 * @author Velore
 * @date 2022/3/2
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("article_tag")
@ApiModel("文章标签")
public class ArticleTag {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer articleId;

    private Integer tagId;
}
