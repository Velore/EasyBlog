package velore.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 标签
 * @author Velore
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("tag")
public class Tag implements Comparable<Tag>{

    /**
     * 标签id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 标签描述
     */
    private String description;

    /**
     * 使用该标签的文章数量
     */
    private Integer blogNum;

    @Override
    public int compareTo(Tag tag) {
        return blogNum - tag.blogNum;
    }
}