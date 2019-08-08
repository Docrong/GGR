package com.boco.eoms.km.table.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.table.model.KmTableColumn;

/**
 * <p> Title:模型字段定义表 </p>
 * <p> Description:模型字段表 </p>
 * <p> Mon Mar 02 14:55:43 CST 2009 </p>
 *
 * @author 吕卫华
 * @version 1.0
 */

public interface KmTableColumnDao extends Dao {

    /**
     * 取模型字段定义表列表
     *
     * @return 返回模型字段定义表列表
     */
    public List getKmTableColumns();

    /**
     * 根据主键查询模型字段定义表
     *
     * @param id 主键
     * @return 返回某id的模型字段定义表
     */
    public KmTableColumn getKmTableColumn(final String id);

    /**
     * 保存模型字段定义表
     *
     * @param kmTableColumn 模型字段定义表
     */
    public void saveKmTableColumn(KmTableColumn kmTableColumn);

    /**
     * 根据id删除模型字段定义表
     *
     * @param id 主键
     */
    public void removeKmTableColumn(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmTableColumns(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    /**
     * 根据模型主键查询某模型的所有有效字段
     *
     * @param id 主键
     * @return 返回某id的对象
     * @author zhangxb
     */
    public List getKmTableColumnsByTableId(final String tableId);
}