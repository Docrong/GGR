/**
 *
 */
package com.boco.eoms.sheet.autodistribute.util;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.sheet.autodistribute.AutoDistributeConstans;
import com.boco.eoms.sheet.autodistribute.model.AutoDistribute;
import com.boco.eoms.sheet.autodistribute.service.IAutoDistributeManager;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;


/**
 * @author jialei
 *
 */
public class AutoDistributeUtil {
    /**
     * 得到动态分配任务的类型（不动态分配、平均分配、多劳多得）
     * @param processTemplateName
     * @return int
     */
    public static int AutoDistributeType(String processTemplateName) {
        IAutoDistributeManager mgr = (IAutoDistributeManager) ApplicationContextHolder
                .getInstance().getBean("iAutoDistributeManager");
        AutoDistribute ad = mgr.getAutoDistribute(processTemplateName);
        String autoType = ad.getAutoType();
        if (autoType.equals("101010602")) {
            return AutoDistributeConstans.AUTOTYPE_AVERAGE;
        } else if (autoType.equals("101010603")) {
            return AutoDistributeConstans.AUTOTYPE_THRESHOLD;
        } else {
            return AutoDistributeConstans.AUTOTYPE_NOTSUPPORT;
        }
    }

    /**
     * 得到动态分配任务的角色ID
     * @param processTemplateName
     * @return int
     */
    public static String AutoDistributeRoleId(String processTemplateName) {
        IAutoDistributeManager mgr = (IAutoDistributeManager) ApplicationContextHolder
                .getInstance().getBean("iAutoDistributeManager");
        AutoDistribute ad = mgr.getAutoDistribute(processTemplateName);
        return ad.getRoleId();
    }

    /**
     * 得到动态分配任务用户的阀值
     * @param processTemplateName
     * @return int
     */
    public static int getThresholdByRoleId(String processTemplateName) {
        IAutoDistributeManager mgr = (IAutoDistributeManager) ApplicationContextHolder
                .getInstance().getBean("iAutoDistributeManager");
        AutoDistribute ad = mgr.getAutoDistribute(processTemplateName);
        String threshold = ad.getThreshold();
        int tmpint = 0;
        try {
            tmpint = Integer.parseInt(threshold);
        } catch (Exception e) {
            e.printStackTrace();
            tmpint = 0;
        }
        return tmpint;
    }

    /**
     * 得到动态分配的处理人，平均分配
     *
     * @param processTemplateName
     * @param dealPerformer
     * @param exceptList 不需要派发的用户列表
     * @return String
     * @throws Exception
     */
    public static String getAutoDistributeUserAve(String processTemplateName, String dealPerformer, IMainService mainManager, ILinkService linkManager, ITaskService taskManager, List exceptList, String taskName) throws Exception {
        // 得到角色的所有用户
        ITawSystemUserRefRoleManager userRefRoleManager = (ITawSystemUserRefRoleManager) ApplicationContextHolder
                .getInstance().getBean("itawSystemUserRefRoleManager");
        List tmpUserList = userRefRoleManager.getUserIdBySubRoleids(dealPerformer);
        //去掉不应该发送的用户
        for (int i = 0; i < exceptList.size(); i++) {
            tmpUserList.remove(exceptList.get(i));
        }
        // 查询上几条由接口派发的工单，得到本任务的接收者
        String sql = "mainSendMode='101030501' or mainSendMode='101030503'";
        List mainList = mainManager.getMainsByCondition(sql);
        String tmpUserId = "";
        if (mainList != null && mainList.size() > 0 && tmpUserList.size() > 1) {
            for (int i = 0; i < mainList.size(); i++) {
                BaseMain main = (BaseMain) mainList.get(i);
                String sheetKey = main.getId();

                List linkList = linkManager.getLinksByMainId(sheetKey);
                int linkNum = 0;
                for (int j = 0; j < linkList.size(); j++) {
                    BaseLink tmpLink = (BaseLink) linkList.get(j);
                    if (tmpLink.getOperateType().intValue() == 61 || tmpLink.getOperateType().intValue() == 4) {
                        linkNum++;
                        tmpUserId = tmpLink.getOperateUserId();
                        if (tmpUserList.size() > 1 && tmpUserList.contains(tmpUserId)) {
                            tmpUserList.remove(tmpUserId);
                        }
                        break;
                    }
                }
                if (linkNum == 0) {
                    String condition = " sheetKey='" + sheetKey + "'"
                            + " and taskName='" + taskName + "' "
                            + " and (subTaskFlag is null or subTaskFlag = 'false' ) ";
                    List tmpTaskList = taskManager.getTasksByCondition(condition);
                    if (tmpTaskList != null && tmpTaskList.size() > 0) {
                        ITask task = (ITask) tmpTaskList.get(0);
                        tmpUserId = task.getTaskOwner();
                        if (task.getOperateType().equals("subrole")) {
                            if (!tmpUserId.equals(task.getOperateRoleId())) {
                                if (tmpUserList.size() > 1 && tmpUserList.contains(tmpUserId)) {
                                    tmpUserList.remove(tmpUserId);
                                }
                            }
                        } else {
                            if (tmpUserList.size() > 1 && tmpUserList.contains(tmpUserId)) {
                                tmpUserList.remove(tmpUserId);
                            }
                        }
                    }
                }
                if (tmpUserList.size() == 1)
                    break;
            }
        }
        return (String) tmpUserList.get(0);
    }

    /**
     * 得到动态分配的处理人，平均分配
     *
     * @param processTemplateName
     * @param userList 用户列表
     * @param exceptList 不需要派发的用户列表
     * @return String
     * @throws Exception
     */
    public static String getAutoDistributeUserAve(String processTemplateName, List userList, IMainService mainManager, ILinkService linkManager, ITaskService taskManager, List exceptList, String taskName) throws Exception {
        //去掉不应该发送的用户
        for (int i = 0; i < exceptList.size(); i++) {
            userList.remove(exceptList.get(i));
        }
        // 查询上几条由接口派发的工单，得到本任务的接收者
        String sql = "mainSendMode='101030501' or mainSendMode='101030503'";
        List mainList = mainManager.getMainsByCondition(sql);
        String tmpUserId = "";
        if (mainList != null && mainList.size() > 0 && userList.size() > 1) {
            for (int i = 0; i < mainList.size(); i++) {
                BaseMain main = (BaseMain) mainList.get(i);
                String sheetKey = main.getId();

                List linkList = linkManager.getLinksByMainId(sheetKey);
                int linkNum = 0;
                for (int j = 0; j < linkList.size(); j++) {
                    BaseLink tmpLink = (BaseLink) linkList.get(j);
                    if (tmpLink.getOperateType().intValue() == 61 || tmpLink.getOperateType().intValue() == 4) {
                        linkNum++;
                        tmpUserId = tmpLink.getOperateUserId();
                        if (userList.size() > 1 && userList.contains(tmpUserId)) {
                            userList.remove(tmpUserId);
                        }
                        break;
                    }
                }
                if (linkNum == 0) {
                    String condition = " sheetKey='" + sheetKey + "'"
                            + " and taskName='" + taskName + "' "
                            + " and (subTaskFlag is null or subTaskFlag = 'false' ) ";
                    List tmpTaskList = taskManager.getTasksByCondition(condition);
                    if (tmpTaskList != null && tmpTaskList.size() > 0) {
                        ITask task = (ITask) tmpTaskList.get(0);
                        tmpUserId = task.getTaskOwner();
                        if (task.getOperateType().equals("subrole")) {
                            if (!tmpUserId.equals(task.getOperateRoleId())) {
                                if (userList.size() > 1 && userList.contains(tmpUserId)) {
                                    userList.remove(tmpUserId);
                                }
                            }
                        } else {
                            if (userList.size() > 1 && userList.contains(tmpUserId)) {
                                userList.remove(tmpUserId);
                            }
                        }
                    }
                }
                if (userList.size() == 1)
                    break;
            }
        }
        return (String) userList.get(0);
    }

    /**
     * 得到动态分配的处理人，多劳多得
     *
     * @param processTemplateName
     * @param dealPerformer
     * @return String
     * @throws Exception
     */
    public static String getAutoDistributeUserThr(String processTemplateName, String dealPerformer, IMainService mainManager, ILinkService linkManager, ITaskService taskManager, String taskName) throws Exception {
        // 得到角色的所有用户
        ITawSystemUserRefRoleManager userRefRoleManager = (ITawSystemUserRefRoleManager) ApplicationContextHolder
                .getInstance().getBean("itawSystemUserRefRoleManager");
        List tmpUserList = userRefRoleManager.getUserIdBySubRoleids(dealPerformer);

        //临时存放符合条件的userId
        String tmpThresholdUser = "";
        //得到超过阀值或没有设置阀值的用户
        List removeList = new ArrayList();
        //得到阀值
        int thresholdvalue = AutoDistributeUtil.getThresholdByRoleId(processTemplateName);
        if (thresholdvalue != 0) {
            for (int i = 0; i < tmpUserList.size(); i++) {
                //得到该用户的待处理列表
                String tmpUserId = (String) tmpUserList.get(i);
                String condition = " taskOwner='" + tmpUserId + "'"
                        + " and (taskStatus='" + Constants.TASK_STATUS_READY + "'"
                        + " or taskStatus='" + Constants.TASK_STATUS_CLAIMED + "'"
                        + " or taskStatus='" + Constants.TASK_STATUS_INACTIVE + "')"
                        + " and taskName='" + taskName + "' ";
                List undoTaskList = taskManager.getTasksByCondition(condition);
                if (undoTaskList != null) {
                    if (thresholdvalue <= undoTaskList.size()) {
                        // 如果该用户的待处理任务已经超过阀值
                        removeList.add(tmpUserId);
                    }
                }
            }
            if (removeList.size() >= tmpUserList.size()) {
                removeList = new ArrayList();
            }
        }
        tmpThresholdUser = getAutoDistributeUserAve(processTemplateName, dealPerformer, mainManager, linkManager, taskManager, removeList, taskName);
        return tmpThresholdUser;
    }

    /**
     * 得到动态分配的处理人，多劳多得
     *
     * @param processTemplateName
     * @param userList 用户列表
     * @return String
     * @throws Exception
     */
    public static String getAutoDistributeUserThr(String processTemplateName, List userList, IMainService mainManager, ILinkService linkManager, ITaskService taskManager, String taskName) throws Exception {
        //临时存放符合条件的userId
        String tmpThresholdUser = "";
        //得到超过阀值或没有设置阀值的用户
        List removeList = new ArrayList();
        //得到阀值
        int thresholdvalue = AutoDistributeUtil.getThresholdByRoleId(processTemplateName);
        if (thresholdvalue != 0) {
            for (int i = 0; i < userList.size(); i++) {
                //得到该用户的待处理列表
                String tmpUserId = (String) userList.get(i);
                String condition = " taskOwner='" + tmpUserId + "'"
                        + " and (taskStatus='" + Constants.TASK_STATUS_READY + "'"
                        + " or taskStatus='" + Constants.TASK_STATUS_CLAIMED + "'"
                        + " or taskStatus='" + Constants.TASK_STATUS_INACTIVE + "')"
                        + " and taskName='" + taskName + "' ";
                List undoTaskList = taskManager.getTasksByCondition(condition);
                if (undoTaskList != null) {
                    if (thresholdvalue <= undoTaskList.size()) {
                        // 如果该用户的待处理任务已经超过阀值
                        removeList.add(tmpUserId);
                    }
                }
            }
            if (removeList.size() >= userList.size()) {
                removeList = new ArrayList();
            }
        }
        tmpThresholdUser = getAutoDistributeUserAve(processTemplateName, userList, mainManager, linkManager, taskManager, removeList, taskName);
        return tmpThresholdUser;
    }
}
