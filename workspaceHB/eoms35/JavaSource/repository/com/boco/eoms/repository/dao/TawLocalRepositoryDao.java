package com.boco.eoms.repository.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.repository.model.TawLocalRepository;
import com.boco.eoms.repository.model.TawLocalRepositoryUp;

/**
 * <p>
 * Title:tawLocalRepository
 * </p>
 * <p>
 * Description:tawLocalRepository
 * </p>
 * <p>
 * Fri Oct 30 09:14:26 CST 2009
 * </p>
 *
 * @author 李锋
 * @version 1.0
 */
public interface TawLocalRepositoryDao extends Dao {

    /**
     * 取tawLocalRepository列表
     *
     * @return 返回tawLocalRepository列表
     */
    public List getTawLocalRepositorys();

    /**
     * 根据主键查询tawLocalRepository
     *
     * @param id 主键
     * @return 返回某id的tawLocalRepository
     */
    public TawLocalRepository getTawLocalRepository(final String id);

    public TawLocalRepositoryUp getTawLocalRepositorybySheetkey(final String sheetkey);

    /**
     * 根据查询tawLocalRepository
     *
     * @param net 网元名称
     * @return 返回某net的tawLocalRepository
     */
    public TawLocalRepository getTawLocalRepositorybyname(final String name);

    /**
     * 保存tawLocalRepository
     *
     * @param tawLocalRepository tawLocalRepository
     */
    public void saveTawLocalRepository(TawLocalRepository tawLocalRepository);

    /**
     * 更新tawLocalRepository
     *
     * @param tawLocalRepository tawLocalRepository
     */
    public void updateTawLocalRepository(TawLocalRepository tawLocalRepository);

    /**
     * 保存tawLocalRepositoryUpgrade
     *
     * @param tawLocalRepositoryUpgrade tawLocalRepositoryUpgrade
     */
    public void saveTawLocalRepositoryUp(TawLocalRepositoryUp tawLocalRepositoryUp);

    /**
     * 根据id删除tawLocalRepository
     *
     * @param id 主键
     */
    public void removeTawLocalRepository(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getTawLocalRepositorys(final Integer curPage, final Integer pageSize,
                                      final String whereStr);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getTawLocalRepositorysHistory(final Integer curPage, final Integer pageSize,
                                             final String whereStr);
}