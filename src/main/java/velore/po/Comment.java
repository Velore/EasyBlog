package velore.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 评论
 * @author Velore
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("comment")
@ApiModel("评论")
public class Comment {

    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评论所在的blog id
     */
    private Integer articleId;

    /**
     * 评论发布者id
     */
    private Integer userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 发送时间
     */
    private LocalDateTime createTime;
}
