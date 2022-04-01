package velore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import velore.po.ArticleTag;

import java.util.List;

/**
 * @author Velore
 * @date 2022/4/1
 **/
public interface ArticleTagService extends IService<ArticleTag> {

    int getCount();

    int add(ArticleTag articleTag);

    int delete(Integer id);

    ArticleTag queryById(Integer id);

    ArticleTag queryByArticleIdAndTagId(Integer articleId, Integer tagId);

    List<ArticleTag> queryByArticleId(Integer id);

    List<ArticleTag> queryByTagId(Integer id);
}
