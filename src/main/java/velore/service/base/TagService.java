package velore.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import velore.bo.TagQueryBo;
import velore.po.Tag;
import velore.service.ext.Countable;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/16
 **/
public interface TagService extends IService<Tag>, Countable {

    /**
     * 新增标签
     * @param tag tag
     * @return int
     */
    int add(Tag tag);

    /**
     * 更新标签
     * @param tag tag
     * @return int
     */
    int update(Tag tag);

    /**
     * articleNum++
     * @param id id
     * @return int
     */
    int increase(Integer id);

    /**
     * articleNum--
     * @param id id
     * @return int
     */
    int decrease(Integer id);

    /**
     * 删除标签
     * @param tagId tagId
     * @return int
     */
    int delete(Integer tagId);

    /**
     * 通过id查询标签
     * @param id id
     * @return tag
     */
    Tag queryById(Integer id);

    /**
     * 随机获取指定数量的tag
     * @param num num
     * @return tag list
     */
    List<Tag> queryRandom(Integer num);

    /**
     * 通过限定条件查询
     * @param queryBo queryBo
     * @return tag list
     */
    IPage<Tag> queryLikeName(TagQueryBo queryBo);
}
