package com.boco.eoms.commonfault.importexcel.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commonfault.importexcel.model.CommonfaultImportExcel;

/**
 * <p>
 * Title:使用表格导入撤销工单
 * </p>
 * <p>
 * Description:使用表格导入撤销工单
 * </p>
 * <p>
 * Tue Oct 26 10:55:09 CST 2010
 * </p>
 *
 * @author liulei
 * @version 3.5
 */
public interface CommonfaultImportExcelDao extends Dao {

    /**
     * 取使用表格导入撤销工单列表
     *
     * @return 返回使用表格导入撤销工单列表
     */
    public List getCommonfaultImportExcels();

    /**
     * 根据主键查询使用表格导入撤销工单
     *
     * @param id 主键
     * @return 返回某id的使用表格导入撤销工单
     */
    public CommonfaultImportExcel getCommonfaultImportExcel(final String id);

    /**
     * 保存使用表格导入撤销工单
     *
     * @param commonfaultImportExcel 使用表格导入撤销工单
     */
    public void saveCommonfaultImportExcel(CommonfaultImportExcel commonfaultImportExcel);

    /**
     * 根据id删除使用表格导入撤销工单
     *
     * @param id 主键
     */
    public void removeCommonfaultImportExcel(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getCommonfaultImportExcels(final Integer curPage, final Integer pageSize,
                                          final String whereStr);

}