package velore.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Velore
 * @date 2022/3/3
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("blog_type")
public class BlogType implements Comparable<BlogType>{

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
     */
    private Integer level;

    /**
     * 类型描述
     */
    private String description;

    @Override
    public int compareTo(BlogType type) {
        return this.level - type.level;
    }
}
