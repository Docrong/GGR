package com.boco.eoms.sheet.complaint.service.bo;

import java.text.SimpleDateFormat;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.service.IComplaintMainManager;
import com.boco.eoms.sheet.complaint.service.IComplaintTaskManager;
import com.boco.eoms.sheet.complaint.task.impl.ComplaintTask;

import java.util.Date;

/**
 * 休眠工单激活接口服务程序
 *
 * @author weichao
 */
public class ComplaintActivation {

    /**
     * 联通测试
     *
     * @param serSupplier
     * @param callTime
     * @return
     */
    public String isAlive(String serSupplier, String callTime) {
        System.out.println("收到crm握手信号");
        String isAliveResult = "";
        return isAliveResult;
    }

    public String activateSleepingSheet(String parentCorrelation, String DealDate) {
        System.out.println("ComplaintActivation starting............." + parentCorrelation);
        String flag = "0";
        IComplaintMainManager mainManager = (IComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
        IComplaintTaskManager taskManager = (IComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
        try {
            List list = mainManager.getMainsByCondition(" status='0' and  parentCorrelation='" + parentCorrelation + "' ");
            if (null != list && list.size() > 0) {
                ComplaintMain main = (ComplaintMain) list.get(0);
                System.out.println("find the crm to eoms sheet===" + main.getSheetId());
                main.setMainSleepStatus(Integer.valueOf("4"));//激活改变状态
                ComplaintTask task = (ComplaintTask) taskManager.getSinglePO(main.getMainSleepTkid());
                task.setTaskStatus("8");//重回T2待办
                Date completeDate = SheetUtils.stringToDate(DealDate);
                main.setSheetCompleteLimit(completeDate);
                main.setMainActivateTime(new Date());
                main.setMainActivateDealer("crm");
                task.setCompleteTimeLimit(completeDate);
                //保存状态
                mainManager.saveOrUpdateMain(main);
                taskManager.addTask(task);
            } else {
                System.out.println("can't find the eoms sheet by parentCorrelation==" + parentCorrelation);
                flag = "未找到对应的eoms工单!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = "eoms服务端未知错误";
        }

        return flag;
    }
}
