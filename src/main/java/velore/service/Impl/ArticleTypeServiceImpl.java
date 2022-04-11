package velore.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.RandomUtil;
import velore.bo.PageQueryBo;
import velore.dao.ArticleTypeMapper;
import velore.po.ArticleType;
import velore.service.base.ArticleTypeService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
    public int getTotal() {
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
        IPage<ArticleType> page = Page.of(
                queryBo.getCurrentPage(),
                queryBo.getPageSize(),
                queryBo.getTotalRecord(),
                queryBo.getTotalRecord() == 0);
        return baseMapper.selectPage(page, new LambdaQueryChainWrapper<>(this.baseMapper).getWrapper());
    }

    @Override
    public List<ArticleType> queryRandom(int num) {
        int bound = articleTypeService.getTotal();
        if(bound < num){
            num = bound;
        }
        Set<Integer> idSet = new TreeSet<>();
        List<ArticleType> articleTypeList = new ArrayList<>();
        while(idSet.size() < num){
            int randomId = RandomUtil.randomInt(bound);
            if(idSet.add(randomId)){
                ArticleType articleType = articleTypeService.queryById(randomId);
                if(articleType!=null) {
                    articleTypeList.add(articleType);
                    continue;
                }
                idSet.remove(randomId);
            }
        }
        return articleTypeList;
    }

    @Override
    public IPage<ArticleType> queryLikeName(String name, PageQueryBo queryBo) {
        IPage<ArticleType> page = Page.of(
                queryBo.getCurrentPage(),
                queryBo.getPageSize(),
                queryBo.getTotalRecord(),
                queryBo.getTotalRecord() == 0);
        LambdaQueryChainWrapper<ArticleType> wrapper = new LambdaQueryChainWrapper<>(this.baseMapper)
                .like(ArticleType::getName, name);
        return baseMapper.selectPage(page, wrapper.getWrapper());
    }
}
