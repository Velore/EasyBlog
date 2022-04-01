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
public class Article implements Comparable<Article>{

    /**
     * blog id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 类型
     */
    private Integer articleType;
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
     * 用于排序
     */
    private Integer views;
    /**
     * 点赞数
     * 用于排序
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
     * 用于排序
     */
    private Boolean recommend;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 发布时间
     * 用于排序
     */
    private LocalDateTime publishTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Override
    public int compareTo(Article o) {
        // views排序权值2
        // likeNum排序权值3
        int[] baseRank = {2, 3};
        int recommendRank = 2;
        int[] rank1 = (this.recommend)?
                new int[]{baseRank[0]*recommendRank, baseRank[1]*recommendRank} :baseRank;
        int[] rank2 = (o.recommend)?
                new int[]{baseRank[0]*recommendRank, baseRank[1]*recommendRank} :baseRank;
        return (this.views*rank1[0] + this.likeNum*rank1[1] - o.views*rank2[0] - o.likeNum*rank2[1])%2;
    }
}
