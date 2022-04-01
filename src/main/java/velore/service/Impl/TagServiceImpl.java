package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.RandomUtil;
import velore.dao.TagMapper;
import velore.po.Tag;
import velore.service.TagService;

import javax.annotation.Resource;
import java.util.*;

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
    public int add(Tag tag) {
        return baseMapper.insert(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer tagId) {
        return baseMapper.deleteById(tagId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Tag tag) {
        return baseMapper.updateById(tag);
    }

    @Override
    public List<Tag> queryAll() {
        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Tag queryById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<Tag> queryRandom(Integer num) {
        int bound = tagService.getCount();
        if(bound < num){
            num = bound;
        }
        Set<Integer> idSet = new TreeSet<>();
        List<Tag> tagList = new ArrayList<>();
        while(idSet.size() < num){
            idSet.add(RandomUtil.randomInt(bound));
        }
        for(Integer i : idSet){
            tagList.add(tagService.queryById(i));
        }
        return tagList;
    }

    @Override
    public List<Tag> queryLikeName(String tagName) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.like("name", tagName);
        return baseMapper.selectList(wrapper);
    }
}
