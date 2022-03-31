package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.dao.TagMapper;
import velore.po.Tag;
import velore.service.TagService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Velore
 * @date 2022/3/17
 **/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private TagService tagService;

    @Override
    public int getCount() {
        return baseMapper.getCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addTag(Tag tag) {
        return baseMapper.insert(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteTag(Integer tagId) {
        return baseMapper.deleteById(tagId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateTag(Tag tag) {
        return baseMapper.updateById(tag);
    }

    @Override
    public List<Tag> queryAllTag() {
        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Tag queryTagById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<Tag> queryRandomTag(Integer num) {
        return null;
    }

    @Override
    public List<Tag> queryTagsLikeName(String tagName) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.like("name", tagName);
        return baseMapper.selectList(wrapper);
    }
}
