package velore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import velore.po.Tag;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/16
 **/
public interface TagService extends IService<Tag> {

    int getCount();

    /**
     * 新增标签
     * @param tag tag
     * @return
     */
    int add(Tag tag);

    /**
     * 删除标签
     * @param tagId tagId
     * @return
     */
    int delete(Integer tagId);

    /**
     * 更新标签
     * @param tag tag
     * @return
     */
    int update(Tag tag);

    /**
     * 查询全部标签
     * @return tag list
     */
    List<Tag> queryAll();

    /**
     * 通过id查询标签
     * @param id id
     * @return
     */
    Tag queryById(Integer id);

    /**
     * 随机获取指定数量的tag
     * @param num num
     * @return
     */
    List<Tag> queryRandom(Integer num);

    /**
     * 通过标签名查询标签
     * @param tagName tagName
     * @return
     */
    List<Tag> queryLikeName(String tagName);
}
