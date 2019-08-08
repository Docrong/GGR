package com.boco.eoms.km.table.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.table.model.KmTableGeneral;

/**
 * <p> Title:模型信息表 </p>
 * <p> Description:模型信息表 </p>
 * <p> Mon Mar 02 14:55:43 CST 2009 </p>
 *
 * @author 吕卫华
 * @version 1.0
 */
public interface KmTableGeneralDao extends Dao {

    /**
     * 取模型信息表列表
     *
     * @return 返回模型信息表列表
     */
    public List getKmTableGenerals();

    /**
     * 根据主键查询模型信息表
     *
     * @param id 主键
     * @return 返回某id的模型信息表
     */
    public KmTableGeneral getKmTableGeneral(final String id);

    /**
     * 保存模型信息表，并将与模型关联的知识分类设置为已使用
     *
     * @param kmTableGeneral 模型信息表
     */
    public void saveKmTableGeneral(KmTableGeneral kmTableGeneral);

    /**
     * 根据模型id删除模型定义、模型字段，并解除与模型绑定的模型分类
     *
     * @param id 主键
     * @author zhangxb
     */
    public void removeKmTableGeneral(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmTableGenerals(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * 根据模型分类查询模型信息表
     *
     * @param themeId 模型分类
     * @return 返回模型信息
     */
    public KmTableGeneral getKmTableGeneralByThemeId(final String themeId);

    /**
     * 查询已创建、并且处于开放状态的未删除案例库模型
     *
     * @return 返回模型信息表的列表
     * @author zhangxb
     */
    public List getOpenKmTableGenerals();

    /**
     * 查询所有未删除案例库模型
     *
     * @return 返回模型信息表的列表
     * @author zhangxb
     */
    public Map getKmTableGenerals(final Integer curPage, final Integer pageSize);

    /**
     * 根据模型主键查询某模型的所有未删除的字段
     *
     * @param id 主键
     * @return 返回某id的对象
     * @author zhangxb
     */
    public List getNextLevelKmTableGenerals(final String parentTableId);

    /**
     * 生成一个可用的节点id
     *
     * @param parentNodeId 父节点id
     * @param length       节点长度
     * @return
     */
    public String getUsableNodeId(final String parentNodeId, final int length);

    /**
     * 根据模型主键查询所有字段类型是单选字典的字段
     *
     * @param tableId
     * @return List
     * @author zhangxb
     */
    public List getKmTableColumnsIsDictByTableId(final String tableId);

    /**
     * 根据模型主键查询所有未创建的有效字段
     *
     * @param tableId
     * @return List
     * @author zhangxb
     */
    public List getKmTableColumnsUnVisiblByTableId(final String tableId);

    /**
     * 根据模型主键将所有未创建的有效字段跟新为已创建
     *
     * @param tableId
     * @author zhangxb
     */
    public void upKmTableColumnsIsVisiblByTableId(final String tableId);

    /**
     * 执行创建模型的SQL
     *
     * @param createSql 创建模型的SQL
     */
    public void createModel(String createSql);

    /**
     * 执行修改模型的SQL
     *
     * @param sqlList 修改模型的SQL列表
     */
    public void modifyModel(List sqlList);

    /**
     * 判断一个表是否已经创建
     *
     * @param tableName 表名
     */
    public boolean HasTable(String tableName);

    /**
     * 判断表的某个字段是否存在
     *
     * @param tableName  表名
     * @param columnName 字段名
     */
    public boolean HasColumn(String tableName, String columnName);

}