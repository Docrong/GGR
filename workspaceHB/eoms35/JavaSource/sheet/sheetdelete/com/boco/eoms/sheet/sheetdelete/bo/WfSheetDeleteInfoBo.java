package com.boco.eoms.sheet.sheetdelete.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.RequestUtils;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.sheetdelete.model.WfSheetDeleteInfo;
import com.boco.eoms.sheet.sheetdelete.service.IWfSheetDeleteInfoManager;
import com.boco.eoms.sheet.sheetdelete.service.IWfSheetDeleteOperateManager;

public class WfSheetDeleteInfoBo {
    static private WfSheetDeleteInfoBo instance;

    static synchronized public WfSheetDeleteInfoBo getInstance() {
        if (instance == null) {
            instance = new WfSheetDeleteInfoBo();
        }
        return instance;
    }

    private WfSheetDeleteInfoBo() {
    }

    public void sendInfo() {
        try {
            IWfSheetDeleteInfoManager infoManager = (IWfSheetDeleteInfoManager) ApplicationContextHolder
                    .getInstance().getBean("iWfSheetDeleteInfoManager");

            List list = infoManager.getAllNotSendInfo();
            System.out.println("listSize:" + list.size());
            for (int i = 0; i < list.size(); i++) {
                try {
                    WfSheetDeleteInfo info = (WfSheetDeleteInfo) list.get(i);
                    String piid = info.getPiid();


                    System.out.println("piid:" + piid);
                    if (piid == null || piid.length() <= 0)
                        continue;
                    IWfSheetDeleteOperateManager operateMgr = (IWfSheetDeleteOperateManager) ApplicationContextHolder.getInstance().getBean("iWfSheetDeleteOperateManager");
                    boolean returnType = operateMgr.sendData(info);
                    System.out.println("returnType:" + returnType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = "2009-9-11";
            Date d = sdf.parse(dateString);

            GregorianCalendar c1 = new GregorianCalendar();
            c1.setTime(d);
            System.out.println(c1.getTime());

            GregorianCalendar c2 = new GregorianCalendar();
            c1.add(Calendar.MINUTE, 5);
            System.out.println(c1.getTime());
            System.out.println(c2.getTime());

            System.out.println(c1.getTimeInMillis());
            System.out.println(c2.getTimeInMillis());
            if (c1.getTimeInMillis() < c2.getTimeInMillis())
                System.out.println("time out");

            WfSheetDeleteInfoBo bo = new WfSheetDeleteInfoBo();
            System.out.println(bo.getClass().getName());
            bo.sendInfo();
        } catch (Exception e) {

        }

//		WfInterfaceInfoBo bo = new WfInterfaceInfoBo();
//		bo.sendInfo();

        // IWfInterfaceInfoManager iWfInterfaceInfoManager =
        // (IWfInterfaceInfoManager)ApplicationContextHolder.getInstance().getBean("IWfInterfaceInfoManager");
        // WfInterfaceInfo info=new WfInterfaceInfo();
        // info.setInterfaceType("replyWorkSheet");
        // info.setSheetType(new Integer(32));
        // info.setServiceType(new Integer(2));
        // info.setSerialNo("serialNo");
        // info.setIsSended(new Integer(0));
        // info.setOpType("opType");
        // info.setOpDesc("opDesc");
        // info.setCompProp("compProp");
        // info.setIsReplied(new Integer(0));
        // info.setIsCorrect(new Integer(0));
        // info.setIssueEliminatTime(new Date());
        // info.setAffectedAreas("affectedAreas");
        // info.setIssueEliminatReason("issudReason");
        // info.setLoginUserName("loginName");
        // info.setLoginUserPassWord("loginPassword");
        // info.setNetResCapacity("netRes");
        // info.setClientPgmCapacity("clientPgm");
        // info.setExpectFinishdays("2");
        // info.setCircuitCode("cCode");
        // iWfInterfaceInfoManager.saveWfInterfaceInfo(info);
    }
}
