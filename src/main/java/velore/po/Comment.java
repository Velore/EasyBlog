package velore.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;
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
@ApiModel(value = "评论",description = "评论模块依赖于文章模块(Article)，因此不再维护评论的查询接口")
@Alias("Comment")
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
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


}
