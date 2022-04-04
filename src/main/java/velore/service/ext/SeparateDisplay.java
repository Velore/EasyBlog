package velore.service.ext;

/**
 * 展示简要信息,展示具体信息
 * @author Velore
 * @date 2022/4/4
 **/
public interface SeparateDisplay<T> {

    /**
     * 返回可展示对象的简要信息,
     * 一般用于首页或者其他页面展示列表
     * @param t t
     * @return brief
     */
    Object displayBrief(T t);

    /**
     * 返回可展示对象的具体信息,
     * 一般用于点击列表的某一项查看具体的信息
     * @param t t
     * @return info
     */
    Object displayInfo(T t);
}