package com.boco.eoms.sheet.sheetdelete.service.impl;

import java.util.Date;
import java.util.HashMap;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.sheetdelete.model.WfSheetDeleteInfo;
import com.boco.eoms.sheet.sheetdelete.service.IWfSheetDeleteInfoManager;
import com.boco.eoms.sheet.sheetdelete.service.IWfSheetDeleteOperateManager;

public class WfSheetDeleteOperateManager implements IWfSheetDeleteOperateManager {
    private IBusinessFlowService businessFlowService;


    public boolean sendData(WfSheetDeleteInfo info) throws Exception {
        boolean result = false;
        IWfSheetDeleteInfoManager infoManager = (IWfSheetDeleteInfoManager) ApplicationContextHolder
                .getInstance().getBean("iWfSheetDeleteInfoManager");
        HashMap sessionMap = new HashMap();
        sessionMap.put("userId", "admin");
        sessionMap.put("password", "1");
        try {
            result = businessFlowService.cancel(info.getPiid(), sessionMap);
        } catch (Exception e) {
            System.out.println("终止流程失败！" + info.getPiid());
        } finally {
            info.setIsSended("2");
            info.setSendTime(new Date());
            infoManager.saveOrUpdateWfSheetDeleteInfo(info);
        }
        if (result) {

            info.setIsSended("1");
            info.setSendTime(new Date());
            infoManager.saveOrUpdateWfSheetDeleteInfo(info);
        }
        return result;
    }

    public IBusinessFlowService getBusinessFlowService() {
        return businessFlowService;
    }

    public void setBusinessFlowService(IBusinessFlowService businessFlowService) {
        this.businessFlowService = businessFlowService;
    }

}
