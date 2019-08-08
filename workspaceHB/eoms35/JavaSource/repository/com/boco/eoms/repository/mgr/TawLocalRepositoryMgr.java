package com.boco.eoms.repository.mgr;

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
public interface TawLocalRepositoryMgr {

    /**
     * 取tawLocalRepository 列表
     *
     * @return 返回tawLocalRepository列表
     */
    public List getTawLocalRepositorys();

    /**
     * 根据主键查询tawLocalRepository
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public TawLocalRepository getTawLocalRepository(final String id);

    public TawLocalRepository getTawLocalRepositorybyname(final String name);

    public TawLocalRepositoryUp getTawLocalRepositorybysheetkey(final String sheetkey);

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
     * 根据主键删除tawLocalRepository
     *
     * @param id 主键
     */
    public void removeTawLocalRepository(final String id);

    /**
     * 根据条件分页查询tawLocalRepository
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回tawLocalRepository的分页列表
     */
    public Map getTawLocalRepositorys(final Integer curPage, final Integer pageSize,
                                      final String whereStr);

    /**
     * 根据条件分页查询tawLocalRepositoryUp
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回tawLocalRepositoryUp的分页列表
     */
    public Map getTawLocalRepositorysHistory(final Integer curPage, final Integer pageSize,
                                             final String whereStr);


}