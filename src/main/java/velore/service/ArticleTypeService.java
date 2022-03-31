package velore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import velore.po.ArticleType;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/26
 **/
public interface ArticleTypeService extends IService<ArticleType> {

    int getCount();

    /**
     * 添加
     * @param type type
     * @return int
     */
    int addArticleType(ArticleType type);

    /**
     * 更新
     * @param type type
     * @return int
     */
    int updateArticleType(ArticleType type);

    /**
     * 删除
     * @param id id
     * @return int
     */
    int deleteArticleType(int id);

    /**
     * 通过id查询
     * @param id id
     * @return
     */
    ArticleType queryArticleTypeById(int id);

    /**
     * 查询全部
     * @return
     */
    List<ArticleType> queryAllArticleType();

    /**
     * 随机获取指定数量
     * @param num num
     * @return
     */
    List<ArticleType> queryRandomArticleType(int num);

    /**
     * 通过名字模糊查询
     * @param name name
     * @return
     */
    List<ArticleType> queryArticleTypeLikeName(String name);
}
