package com.boco.eoms.commonfault.importexcel.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commonfault.importexcel.model.CommonfaultImportExcel;
import com.boco.eoms.commonfault.importexcel.mgr.CommonfaultImportExcelMgr;
import com.boco.eoms.commonfault.importexcel.dao.CommonfaultImportExcelDao;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.service.IFMImportFileManager;

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
public class CommonfaultImportExcelMgrImpl implements CommonfaultImportExcelMgr {

    private CommonfaultImportExcelDao commonfaultImportExcelDao;
    private IFMImportFileManager fmImportFileManager;

    public CommonfaultImportExcelDao getCommonfaultImportExcelDao() {
        return this.commonfaultImportExcelDao;
    }

    public void setCommonfaultImportExcelDao(CommonfaultImportExcelDao commonfaultImportExcelDao) {
        this.commonfaultImportExcelDao = commonfaultImportExcelDao;
    }

    public List getCommonfaultImportExcels() {
        return commonfaultImportExcelDao.getCommonfaultImportExcels();
    }

    public CommonfaultImportExcel getCommonfaultImportExcel(final String id) {
        return commonfaultImportExcelDao.getCommonfaultImportExcel(id);
    }

    public void saveCommonfaultImportExcel(CommonfaultImportExcel commonfaultImportExcel) {
        commonfaultImportExcelDao.saveCommonfaultImportExcel(commonfaultImportExcel);
    }

    public void removeCommonfaultImportExcel(final String id) {
        commonfaultImportExcelDao.removeCommonfaultImportExcel(id);
    }

    public Map getCommonfaultImportExcels(final Integer curPage, final Integer pageSize,
                                          final String whereStr) {
        return commonfaultImportExcelDao.getCommonfaultImportExcels(curPage, pageSize, whereStr);
    }


    public IFMImportFileManager getFmImportFileManager() {
        return fmImportFileManager;
    }

    public void setFmImportFileManager(IFMImportFileManager fmImportFileManager) {
        this.fmImportFileManager = fmImportFileManager;
    }

    public Map mappingCommonfaultExcel(String excelPath) throws FMException {
        System.out.println(fmImportFileManager);
        Map map = null;
        map = this.fmImportFileManager
                .impt(
                        "classpath:com/boco/eoms/commonfault/importexcel/model/CommonfaultImportExcels.xml",
                        excelPath);

        return map;
    }

}