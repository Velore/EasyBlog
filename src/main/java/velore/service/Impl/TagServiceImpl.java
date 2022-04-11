package velore.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.bo.TagQueryBo;
import velore.dao.TagMapper;
import velore.po.Tag;
import velore.service.base.TagService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Velore
 * @date 2022/3/17
 **/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService{

    @Resource
    private TagService tagService;

    @Override
    public List<Integer> getTotal() {
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
        // 获取全部id
        List<Integer> idList = tagService.getTotal();
        // 打乱id的顺序
        Collections.shuffle(idList);
        // 如果要查询的数量num大于总id数, 则返回全部id
        // 否则返回乱序后排在最前面的num个id
        num = Math.min(num, idList.size());
        return baseMapper.selectBatchIds(idList.subList(0, num));
    }

    @Override
    public IPage<Tag> queryLikeName(TagQueryBo queryBo) {
        IPage<Tag> page = queryBo.getPage();
        LambdaQueryChainWrapper<Tag> wrapper = new LambdaQueryChainWrapper<>(this.baseMapper);
        if(queryBo.getTagName()!=null) {
            wrapper.like(Tag::getName, queryBo.getTagName());
        }
        return baseMapper.selectPage(page, wrapper.getWrapper());
    }
}
