
package com.boco.eoms.sheet.itrequirement.service;


import java.util.List;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.IMainService;

public interface IITRequirementMainManager extends IMainService {

    /**
     * 查询暂存列表
     *
     * @return
     * @throws SheetException
     */
    public abstract List getTempSaveList(final Integer curPage, int[] aTotal, final Integer pageSize, String queryType)
            throws SheetException;
}

