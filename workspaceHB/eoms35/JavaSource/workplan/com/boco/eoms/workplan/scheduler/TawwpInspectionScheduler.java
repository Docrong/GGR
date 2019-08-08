package com.boco.eoms.workplan.scheduler;

import java.util.List;
import java.util.Iterator;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.workplan.intfacewebservices.Service1Locator;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.mgr.ITawwpNetMgr;
import com.boco.eoms.workplan.util.Inspection;
import com.boco.eoms.workplan.util.TawwpIntfaceUtil;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.util.WorkplanMgrLocator;
import com.boco.eoms.common.util.StaticMethod;

/**
 * add by 周春兴，龚玉峰
 */
public class TawwpInspectionScheduler implements Job {

    /**
     * 构造
     */
    public TawwpInspectionScheduler() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    public void execute(JobExecutionContext context)
            throws org.quartz.JobExecutionException {
        System.out
                .println("TawwpInspectionSchedulerbegin！！！===================>");

        String xpath = "//UDSObjectList/UDSObject/Attributes";
        TawwpIntfaceUtil tawwpIntfaceUtil = new TawwpIntfaceUtil();
        try {

            Service1Locator faultSheetLocator = new Service1Locator();
            com.boco.eoms.workplan.intfacewebservices.Service1Soap interSwitchEomsIP = faultSheetLocator
                    .getService1Soap();

            ITawwpNetMgr tawwpNetMgr = (ITawwpNetMgr) ApplicationContextHolder
                    .getInstance().getBean("tawwpNetMgr");
            ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) ApplicationContextHolder
                    .getInstance().getBean("tawwpExecuteMgr");

            org.w3c.dom.Document doc = interSwitchEomsIP.getNetInfo();// 获取网元对象

            List list = new java.util.ArrayList();
            list = tawwpIntfaceUtil.getList(xpath, list, doc, "getNetInfo"); // w網元list
            System.out.println("getNetInfo：size=======>：" + list.size());
            String NetId = "";
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Inspection inspection = new Inspection();
                    inspection = (Inspection) list.get(i);
                    // interSwitchEomsIP.getCmdTaskInfo(inspection.get, userID)
                    if (inspection.getNetID() != null
                            && !"".equals(inspection.getNetID())) {
                        // 根据网元序列号取网元id
                        NetId = StaticMethod.null2String(tawwpNetMgr
                                .loadNetBySerial(inspection.getNetID()));
                        if (!"".equals(NetId)) {
                            // 根据接口获取
                            List cmdTask = new java.util.ArrayList(); //
                            org.w3c.dom.Document cTask = interSwitchEomsIP
                                    .getTaskInfo(inspection.getNetID(), ""); // 获取任务列表
                            // System.out.println("作业计划智能轮训获取 获取任务列表");
                            cmdTask = tawwpIntfaceUtil.getList(xpath, cmdTask,
                                    cTask, "getTaskInfo"); // 任務list
                            System.out.println("getTaskInfo：size=====>："
                                    + cmdTask.size());
                            if (cmdTask.size() > 0) {
                                for (int j = 0; j < cmdTask.size(); j++) {
                                    List listTask = new java.util.ArrayList();
                                    Inspection inspectionTask = new Inspection();
                                    inspectionTask = (Inspection) cmdTask
                                            .get(j); // 任務詳細
                                    // System.out.println("作业计划智能轮训获取 任務詳細");
                                    String _startDate = tawwpIntfaceUtil
                                            .getLocalString();
                                    String date = TawwpUtil
                                            .getCurrentDateTime("yyyy-MM-dd");

                                    List cmdResultInfo = new java.util.ArrayList();
                                    org.w3c.dom.Document _doc = interSwitchEomsIP
                                            .getTaskCmdResultInfo(
                                                    inspectionTask.getTaskID(),
                                                    inspection.getNetID(), date
                                                            + " 00:00:00", date
                                                            + " 23:59:59"); // 获取任务执行结果的详细信息
                                    cmdResultInfo = tawwpIntfaceUtil.getList(
                                            xpath, cmdResultInfo, _doc,
                                            "getTaskCmdResultInfo"); // 詳細信息解析
                                    System.out
                                            .println("getTaskCmdResultInfo：size========》："
                                                    + cmdResultInfo.size());
                                    for (Iterator it = cmdResultInfo.iterator(); it
                                            .hasNext(); ) {
                                        Inspection workplanresult = (Inspection) it
                                                .next();
                                        System.out
                                                .println("workplanresult.getCmdID()："
                                                        + workplanresult
                                                        .getCmdID());
                                        System.out
                                                .println("workplanresult.getCmdName()："
                                                        + workplanresult
                                                        .getCmdName());
                                        System.out
                                                .println("workplanresult.getExecuteFTP()："
                                                        + workplanresult
                                                        .getExecuteFTP());
                                        System.out
                                                .println("workplanresult.getExecuteStatus()："
                                                        + workplanresult
                                                        .getExecuteStatus());
                                        System.out
                                                .println("workplanresult.getExecuteResult()："
                                                        + workplanresult
                                                        .getExecuteResult());
                                    }
                                    // 通过网元id和接口返回对象存储
                                    if (cmdResultInfo.size() > 0) {
                                        tawwpExecuteMgr.saveContentByComm(
                                                NetId, cmdResultInfo, inspectionTask.getTaskID());
                                    }

                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        System.out
                .println("TawwpInspectionSchedulerEND！！！===================>");
    }

    public void execute() throws org.quartz.JobExecutionException {
        System.out
                .println("TawwpInspectionSchedulerbegin！！！===================>");

        String xpath = "//UDSObjectList/UDSObject/Attributes";
        TawwpIntfaceUtil tawwpIntfaceUtil = new TawwpIntfaceUtil();
        try {
            Service1Locator faultSheetLocator = new Service1Locator();
            com.boco.eoms.workplan.intfacewebservices.Service1Soap interSwitchEomsIP = faultSheetLocator
                    .getService1Soap();
            ITawwpNetMgr tawwpNetMgr = (ITawwpNetMgr) ApplicationContextHolder
                    .getInstance().getBean("tawwpNetMgr");
            ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) ApplicationContextHolder
                    .getInstance().getBean("tawwpExecuteMgr");

            org.w3c.dom.Document doc = interSwitchEomsIP.getNetInfo();// 获取网元对象

            List list = new java.util.ArrayList();
            list = tawwpIntfaceUtil.getList(xpath, list, doc, "getNetInfo"); // w網元list
            System.out.println("getNetInfo：size=======>：" + list.size());
            String NetId = "";
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Inspection inspection = new Inspection();
                    inspection = (Inspection) list.get(i);
                    // interSwitchEomsIP.getCmdTaskInfo(inspection.get, userID)
                    if (inspection.getNetID() != null
                            && !"".equals(inspection.getNetID())) {
                        // 根据网元序列号取网元id
                        NetId = StaticMethod.null2String(tawwpNetMgr
                                .loadNetBySerial(inspection.getNetID()));
                        if (!"".equals(NetId)) {
                            // 根据接口获取
                            List cmdTask = new java.util.ArrayList(); //
                            org.w3c.dom.Document cTask = interSwitchEomsIP
                                    .getTaskInfo(inspection.getNetID(), ""); // 获取任务列表
                            // System.out.println("作业计划智能轮训获取 获取任务列表");
                            cmdTask = tawwpIntfaceUtil.getList(xpath, cmdTask,
                                    cTask, "getTaskInfo"); // 任務list
                            System.out.println("getTaskInfo：size=====>："
                                    + cmdTask.size());
                            if (cmdTask.size() > 0) {
                                for (int j = 0; j < cmdTask.size(); j++) {
                                    List listTask = new java.util.ArrayList();
                                    Inspection inspectionTask = new Inspection();
                                    inspectionTask = (Inspection) cmdTask
                                            .get(j); // 任務詳細
                                    // System.out.println("作业计划智能轮训获取 任務詳細");
                                    String _startDate = tawwpIntfaceUtil
                                            .getLocalString();
                                    String date = TawwpUtil
                                            .getCurrentDateTime("yyyy-MM-dd");

                                    List cmdResultInfo = new java.util.ArrayList();
                                    org.w3c.dom.Document _doc = interSwitchEomsIP
                                            .getTaskCmdResultInfo(
                                                    inspectionTask.getTaskID(),
                                                    inspection.getNetID(), date
                                                            + " 00:00:00", date
                                                            + " 23:59:59"); // 获取任务执行结果的详细信息
                                    cmdResultInfo = tawwpIntfaceUtil.getList(
                                            xpath, cmdResultInfo, _doc,
                                            "getTaskCmdResultInfo"); // 詳細信息解析
                                    System.out
                                            .println("getTaskCmdResultInfo：size========》："
                                                    + cmdResultInfo.size());
									/*for (Iterator it = cmdResultInfo.iterator(); it
											.hasNext();) {
										Inspection workplanresult = (Inspection) it
												.next();
										System.out
												.println("workplanresult.getCmdID()："
														+ workplanresult
																.getCmdID());
										System.out
												.println("workplanresult.getCmdName()："
														+ workplanresult
																.getCmdName());
										System.out
												.println("workplanresult.getExecuteFTP()："
														+ workplanresult
																.getExecuteFTP());
										System.out
												.println("workplanresult.getExecuteStatus()："
														+ workplanresult
																.getExecuteStatus());
										System.out
												.println("workplanresult.getExecuteResult()："
														+ workplanresult
										.getExecuteResult());
									}	*/

                                    // 通过网元id和接口返回对象存储
                                    if (cmdResultInfo.size() > 0) {
                                        tawwpExecuteMgr.saveContentByComm(
                                                NetId, cmdResultInfo, inspectionTask.getTaskID());
                                    }


                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        System.out
                .println("TawwpInspectionSchedulerEND！！！===================>");
    }

}
