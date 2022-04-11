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
import velore.utils.TokenUtil;
import velore.vo.request.CommentRequest;

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
    private LocalDateTime createTime;

    public Comment(String token, CommentRequest request){
        articleId = request.getArticleId();
        userId = TokenUtil.getTokenId(token);
        content = request.getContent();
    }
}
