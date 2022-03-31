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

import java.time.LocalDateTime;

/**
 * @author Velore
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("article")
@ApiModel("文章")
@Alias("Article")
public class Article {

    /**
     * blog id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 类型
     */
    private Integer blogTypeId;
    /**
     * 发布者
     */
    private Integer userId;
    /**
     *标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 浏览量
     */
    private Integer views;
    /**
     * 点赞数
     */
    private Integer likeNum;
    /**
     * 是否隐藏
     */
    private Boolean visible;
    /**
     * 是否可评论
     */
    private Boolean commentable;
    /**
     * 是否被推荐
     */
    private Boolean recommend;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
