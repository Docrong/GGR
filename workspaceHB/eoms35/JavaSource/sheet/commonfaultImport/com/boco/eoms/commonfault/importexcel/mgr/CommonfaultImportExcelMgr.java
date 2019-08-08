package com.boco.eoms.commonfault.importexcel.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commonfault.importexcel.model.CommonfaultImportExcel;
import com.boco.eoms.commons.file.exception.FMException;

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
public interface CommonfaultImportExcelMgr {

    /**
     * 取使用表格导入撤销工单 列表
     *
     * @return 返回使用表格导入撤销工单列表
     */
    public List getCommonfaultImportExcels();

    /**
     * 根据主键查询使用表格导入撤销工单
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public CommonfaultImportExcel getCommonfaultImportExcel(final String id);

    /**
     * 保存使用表格导入撤销工单
     *
     * @param commonfaultImportExcel 使用表格导入撤销工单
     */
    public void saveCommonfaultImportExcel(CommonfaultImportExcel commonfaultImportExcel);

    /**
     * 根据主键删除使用表格导入撤销工单
     *
     * @param id 主键
     */
    public void removeCommonfaultImportExcel(final String id);

    /**
     * 根据条件分页查询使用表格导入撤销工单
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回使用表格导入撤销工单的分页列表
     */
    public Map getCommonfaultImportExcels(final Integer curPage, final Integer pageSize,
                                          final String whereStr);

    public Map mappingCommonfaultExcel(String excelPath) throws FMException;

}