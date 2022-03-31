package velore.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

/**
 * @author Velore
 * @date 2022/3/3
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("article_type")
@ApiModel("文章类型")
@Alias("ArticleType")
public class ArticleType implements Comparable<ArticleType>{

    /**
     * 标签id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 类型名
     */
    private String name;

    /**
     * 类型等级
     * 越大表示等级越高
     * 用于展示时的排序
     */
    private Integer level;

    /**
     * 类型描述
     */
    private String description;

    /**
     * 该类型下的文章数量
     * 用于统计和排序
     */
    private Integer articleNum;

    @Override
    public int compareTo(ArticleType type) {
        int mainComparator = this.level - type.level;
        //先根据 level 排序, 再根据 articleNum 排序
        return (mainComparator == 0)?(this.articleNum - type.articleNum):mainComparator;
    }
}
