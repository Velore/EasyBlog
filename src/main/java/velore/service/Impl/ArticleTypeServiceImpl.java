package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.dao.ArticleTypeMapper;
import velore.po.ArticleType;
import velore.service.ArticleTypeService;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/26
 **/
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeMapper, ArticleType> implements ArticleTypeService {

    @Override
    public int getCount() {
        return baseMapper.getCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addArticleType(ArticleType type) {
        return baseMapper.insert(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateArticleType(ArticleType type) {
        return baseMapper.updateById(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteArticleType(int id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public ArticleType queryArticleTypeById(int id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<ArticleType> queryAllArticleType() {
        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<ArticleType> queryRandomArticleType(int num) {
        return null;
    }

    @Override
    public List<ArticleType> queryArticleTypeLikeName(String name) {
        return null;
    }
}
