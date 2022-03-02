package velore.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Velore
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("blog")
public class Blog {

    /**
     * blog id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 类型
     */
    private Integer blogType;
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
    private boolean visible;
    /**
     * 是否可评论
     */
    private boolean commentable;
    /**
     * 是否被推荐
     */
    private boolean recommend;
    /**
     * 状态
     */
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
