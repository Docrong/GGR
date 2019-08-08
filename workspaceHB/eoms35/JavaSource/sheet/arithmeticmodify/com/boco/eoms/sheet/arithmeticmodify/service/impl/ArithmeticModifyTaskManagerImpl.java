
package com.boco.eoms.sheet.arithmeticmodify.service.impl;


import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.impl.TaskServiceImpl;
import com.boco.eoms.sheet.arithmeticmodify.dao.IArithmeticModifyTaskDAO;
import com.boco.eoms.sheet.arithmeticmodify.service.IArithmeticModifyTaskManager;


public class ArithmeticModifyTaskManagerImpl extends TaskServiceImpl implements IArithmeticModifyTaskManager {

    public Integer getCountOfBrother(String sheetKey, String parentLevelId) throws SheetException {
        IArithmeticModifyTaskDAO iarithmeticmodifyTaskDAO = (IArithmeticModifyTaskDAO) this.getTaskDAO();

        Integer count = new Integer(0);
        try {
            count = iarithmeticmodifyTaskDAO.getCountOfBrother(this.getTaskModelObject(), sheetKey, parentLevelId);
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return count;
    }
}
