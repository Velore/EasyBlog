package velore.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.bo.PageQueryBo;
import velore.dao.ArticleTypeMapper;
import velore.po.ArticleType;
import velore.service.base.ArticleTypeService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Velore
 * @date 2022/3/26
 **/
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeMapper, ArticleType>
        implements ArticleTypeService{

    @Resource
    private ArticleTypeService articleTypeService;

    @Override
    public List<Integer> getTotal() {
        return baseMapper.getTotal();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(ArticleType type) {
        return baseMapper.insert(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ArticleType type) {
        return baseMapper.updateById(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(int id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public ArticleType queryById(int id) {
        return baseMapper.selectById(id);
    }

    @Override
    public IPage<ArticleType> queryAll(PageQueryBo queryBo) {
        IPage<ArticleType> page = queryBo.getPage();
        System.out.println(page.getCurrent()+","+ page.getSize()+","+ page.getTotal());
        return baseMapper.selectPage(page, new LambdaQueryChainWrapper<>(this.baseMapper).getWrapper());
    }

    @Override
    public List<ArticleType> queryRandom(int num) {
        // 获取全部id
        List<Integer> idList = articleTypeService.getTotal();
        // 打乱id的顺序
        Collections.shuffle(idList);
        // 如果要查询的数量num大于总id数, 则返回全部id
        // 否则返回乱序后排在最前面的num个id
        num = Math.min(num, idList.size());
        return baseMapper.selectBatchIds(idList.subList(0, num));
    }

    @Override
    public IPage<ArticleType> queryLikeName(String name, PageQueryBo queryBo) {
        IPage<ArticleType> page = queryBo.getPage();
        LambdaQueryChainWrapper<ArticleType> wrapper = new LambdaQueryChainWrapper<>(this.baseMapper)
                .like(ArticleType::getName, name);
        return baseMapper.selectPage(page, wrapper.getWrapper());
    }
}
