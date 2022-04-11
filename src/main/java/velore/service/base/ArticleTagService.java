package velore.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import velore.bo.PageQueryBo;
import velore.po.ArticleTag;
import velore.po.Tag;
import velore.vo.ArticleBrief;

import java.util.List;

/**
 * @author Velore
 * @date 2022/4/1
 **/
public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 添加文章标签对应关系
     * @param token token
     * @param articleTag articleTag
     * @return int
     */
    int add(String token, ArticleTag articleTag);

    /**
     * 删除文章标签对应关系
     * @param token token
     * @param id id
     * @return int
     */
    int delete(String token, Integer id);

    /**
     * 根据id查询
     * @param id id
     * @return ArticleTag
     */
    ArticleTag queryById(Integer id);

    /**
     * 根据文章id查询文章携带的标签
     * @param articleId articleId
     * @return Tag list
     */
    List<Tag> queryByArticleId(Integer articleId);

    /**
     * 根据标签id查询携带该标签的文章
     * @param tagId tagId
     * @param queryBo queryBo
     * @return ArticleTag list
     */
    IPage<ArticleBrief> queryByTagId(Integer tagId, PageQueryBo queryBo);
}
