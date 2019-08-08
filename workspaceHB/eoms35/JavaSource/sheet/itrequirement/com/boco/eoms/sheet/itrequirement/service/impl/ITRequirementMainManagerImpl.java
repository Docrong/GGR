
package com.boco.eoms.sheet.itrequirement.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.itrequirement.service.IITRequirementMainManager;

public class ITRequirementMainManagerImpl extends MainService implements IITRequirementMainManager {
    /**
     * @param curPage
     * @param pageSize
     * @return
     * @throws SheetException
     */
    public List getTempSaveList(Integer curPage, int[] aTotal, Integer pageSize, String queryType) throws SheetException {
        List list = null;

        try {
            String hsql = "select distinct alias from " + this.getMainObject().getClass().getName() + " alias where "
                    + "alias.templateFlag <> 1 and alias.status=" + Constants.SHEET_TEMPSAVE.intValue() + " and alias.deleted<>'1' order by alias.sendTime desc";
            list = this.getMainDAO().getQuerySheetByCondition(hsql, curPage, pageSize, aTotal, queryType);
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return list;
    }
}
