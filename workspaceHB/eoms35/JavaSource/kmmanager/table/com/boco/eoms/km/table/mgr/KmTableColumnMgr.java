package com.boco.eoms.km.table.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.table.model.KmTableColumn;

/**
 * <p>
 * Title:模型字段定义表
 * </p>
 * <p>
 * Description:模型字段表
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 *
 * @author 吕卫华
 * @version 1.0
 */
public interface KmTableColumnMgr {

    /**
     * 取模型字段定义表 列表
     *
     * @return 返回模型字段定义表列表
     */
    public List getKmTableColumns();

    /**
     * 根据模型主键查询某模型的所有有效字段
     *
     * @param id 主键
     * @return 返回某id的对象
     * @author zhangxb
     */
    public List getKmTableColumnsByTableId(final String tableId);

    /**
     * 根据主键查询模型字段定义表
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmTableColumn getKmTableColumn(final String id);

    /**
     * 保存模型字段定义表
     *
     * @param kmTableColumn 模型字段定义表
     */
    public void saveKmTableColumn(KmTableColumn kmTableColumn);

    /**
     * 根据主键删除模型字段定义表
     *
     * @param id 主键
     */
    public void removeKmTableColumn(final String id);

    /**
     * 根据条件分页查询模型字段定义表
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回模型字段定义表的分页列表
     */
    public Map getKmTableColumns(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

}