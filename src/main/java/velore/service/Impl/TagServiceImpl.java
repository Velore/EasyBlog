package velore.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.RandomUtil;
import velore.bo.TagQueryBo;
import velore.dao.TagMapper;
import velore.po.Tag;
import velore.service.base.TagService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Velore
 * @date 2022/3/17
 **/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService{

    @Resource
    private TagService tagService;

    @Override
    public int getTotal() {
        return baseMapper.getTotal();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(Tag tag) {
        return baseMapper.insert(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Tag tag) {
        return baseMapper.updateById(tag);
    }

    @Override
    public int increase(Integer id) {
        Tag tag = tagService.queryById(id);
        tag.setArticleNum(tag.getArticleNum()+1);
        return tagService.update(tag);
    }

    @Override
    public int decrease(Integer id) {
        Tag tag = tagService.queryById(id);
        tag.setArticleNum(tag.getArticleNum()-1);
        return tagService.update(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer tagId) {
        return baseMapper.deleteById(tagId);
    }

    @Override
    public Tag queryById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<Tag> queryRandom(Integer num) {
        int bound = tagService.getTotal();
        if(bound < num){
            num = bound;
        }
        Set<Integer> idSet = new TreeSet<>();
        List<Tag> tagList = new ArrayList<>();
        while(idSet.size() < num){
            int randomId = RandomUtil.randomInt(bound);
            if(idSet.add(randomId)){
                Tag tag = tagService.queryById(randomId);
                if(tag != null){
                    tagList.add(tag);
                    continue;
                }
                idSet.remove(randomId);
            }
        }
        return tagList;
    }

    @Override
    public IPage<Tag> queryLikeName(TagQueryBo queryBo) {
        IPage<Tag> page = queryBo.getPage();
        LambdaQueryChainWrapper<Tag> wrapper = new LambdaQueryChainWrapper<>(this.baseMapper)
                .like(Tag::getName, queryBo.getTagName());
        return baseMapper.selectPage(page, wrapper.getWrapper());
    }
}
