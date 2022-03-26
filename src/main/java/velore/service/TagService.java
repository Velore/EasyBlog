package velore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import velore.po.Tag;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/16
 **/
public interface TagService extends IService<Tag> {

    /**
     * 新增标签
     * @param tag tag
     * @return
     */
    int addTag(Tag tag);

    /**
     * 删除标签
     * @param tagId tagId
     * @return
     */
    int deleteTag(Integer tagId);

    /**
     * 更新标签
     * @param tag tag
     * @return
     */
    int updateTag(Tag tag);

    /**
     * 查询全部标签
     * @return tag list
     */
    List<Tag> queryAllTag();

    /**
     * 通过id查询标签
     * @param id id
     * @return
     */
    Tag queryTagById(Integer id);

    /**
     * 通过标签名查询标签
     * @param tagName tagName
     * @return
     */
    List<Tag> queryTagsLikeName(String tagName);
}
