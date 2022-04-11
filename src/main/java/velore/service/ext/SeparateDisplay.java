package velore.service.ext;

import velore.bo.PageQueryBo;

import java.util.List;

/**
 * 展示简要信息,展示具体信息
 * @author Velore
 * @date 2022/4/4
 **/
public interface SeparateDisplay<T> {

    /**
     * 返回可展示对象的简要信息<br/>
     * 一般用于首页或者其他页面展示列表
     * @param t t
     * @return brief
     */
    Object displayBrief(T t);

    /**
     * 一次返回多个Brief
     * @param list Object list
     * @return (Object)brief list
     */
    Object displayBrief(List<T> list);

    /**
     * 返回可展示对象的具体信息,
     * 一般用于点击列表的某一项查看具体的信息
     * @param t t
     * @return info
     */
    Object displayInfo(T t);

    /**
     * 返回可展示对象的具体信息<br/>
     * 展示对象中存在需要分页查询的属性
     * @param t t
     * @param queryBo queryBo
     * @return info
     */
    Object displayInfo(T t, PageQueryBo queryBo);
}
