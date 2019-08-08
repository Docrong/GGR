package com.boco.eoms.sheet.sheetdelete.service;

import com.boco.eoms.sheet.sheetdelete.model.WfSheetDeleteInfo;

public interface IWfSheetDeleteOperateManager {
    public boolean sendData(WfSheetDeleteInfo info) throws Exception;
}
