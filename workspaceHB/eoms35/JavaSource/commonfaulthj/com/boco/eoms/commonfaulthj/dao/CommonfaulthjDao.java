package com.boco.eoms.commonfaulthj.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commonfaulthj.model.Commonfaulthj;

/**
 * <p>
 * Title:commonfaulthj
 * </p>
 * <p>
 * Description:commonfaulthj
 * </p>
 * <p>
 * Thu Dec 18 15:28:05 CST 2014
 * </p>
 *
 * @author zhoupan
 * @version 3.5
 */
public interface CommonfaulthjDao extends Dao {

    /**
     * 取commonfaulthj列表
     *
     * @return 返回commonfaulthj列表
     */
    public List getCommonfaulthjs();

    /**
     * 根据主键查询commonfaulthj
     *
     * @param id 主键
     * @return 返回某id的commonfaulthj
     */
    public Commonfaulthj getCommonfaulthj(final String id);

    /**
     * 保存commonfaulthj
     *
     * @param commonfaulthj commonfaulthj
     */
    public void saveCommonfaulthj(Commonfaulthj commonfaulthj);

    /**
     * 根据id删除commonfaulthj
     *
     * @param id 主键
     */
    public void removeCommonfaulthj(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getCommonfaulthjs(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    public Map getMapList(final Integer curPage, final Integer pageSize,
                          final String whereStr);

}