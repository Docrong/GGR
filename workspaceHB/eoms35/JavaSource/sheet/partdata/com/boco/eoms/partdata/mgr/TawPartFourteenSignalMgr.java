package com.boco.eoms.partdata.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partdata.model.TawPartFourteenSignal;

/**
 * <p>
 * Title:14位信令点
 * </p>
 * <p>
 * Description:14位信令点范围
 * </p>
 * <p>
 * Tue Jul 06 14:18:23 CST 2010
 * </p>
 *
 * @author Josh
 * @version 3.5
 */
public interface TawPartFourteenSignalMgr {

    /**
     * 取14位信令点 列表
     *
     * @return 返回14位信令点列表
     */
    public List getTawPartFourteenSignals();

    /**
     * 根据主键查询14位信令点
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public TawPartFourteenSignal getTawPartFourteenSignal(final String id);

    /**
     * 保存14位信令点
     *
     * @param tawPartLac 14位信令点
     */
    public void saveTawPartFourteenSignal(TawPartFourteenSignal tawPartFourteenSignal);

    /**
     * 根据主键删除14位信令点
     *
     * @param id 主键
     */
    public void removeTawPartFourteenSignal(final String id);

    /**
     * 根据条件分页查询14位信令点
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回14位信令点的分页列表
     */
    public Map getTawPartFourteenSignals(final Integer curPage, final Integer pageSize,
                                         final String whereStr);

    /**
     * 根据起始终止值得到当前数据存在的数据
     *
     * @param startNum 开始值
     * @param endNum   结束值
     * @return 返回14位信令点的FORM 包含在MAP中
     */
    public Map getExistTawPartFourteenSignals(final Integer startNum, final Integer endNum);

    /**
     * 根据条件分页查询14位信令点
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回14位信令点的分页列表
     */
    public Map getAllTawPartFourteenSignals(final String whereStr);

}