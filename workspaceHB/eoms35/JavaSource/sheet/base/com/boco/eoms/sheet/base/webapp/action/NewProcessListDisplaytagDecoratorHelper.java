package com.boco.eoms.sheet.base.webapp.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-2-24 15:52:09
 * </p>
 *
 * @author wangjianhua
 * @version 1.0
 */
public class NewProcessListDisplaytagDecoratorHelper extends TableDecorator {
    public String getSheetId() {
        Map taskMap = (HashMap) getCurrentRowObject();
        String url = (String) this.getPageContext().getAttribute("url");
        if (url == null)
            url = this.getUrl(taskMap);

        String sheetKey = StaticMethod.nullObject2String(taskMap.get("sheetKey")).trim();
        String taskName = StaticMethod.nullObject2String(taskMap.get("taskName")).trim();
        String id = StaticMethod.nullObject2String(taskMap.get("id")).trim();
        String operateRoleId = StaticMethod.nullObject2String(taskMap.get("operateRoleId")).trim();
        String TKID = StaticMethod.nullObject2String(taskMap.get("id")).trim();
        String processId = StaticMethod.nullObject2String(taskMap.get("processId")).trim();
        String taskStatus = StaticMethod.nullObject2String(taskMap.get("taskStatus")).trim();
        String preLinkId = StaticMethod.nullObject2String(taskMap.get("preLinkId")).trim();
        String sheetId = StaticMethod.nullObject2String(taskMap.get("sheetId")).trim();
        return "<a  href='" + url
                + "?method=newShowDetailPage&sheetKey=" + sheetKey
                + "&taskId=" + id + "&taskName=" + taskName
                + "&operateRoleId=" + operateRoleId + "&TKID=" + TKID
                + "&piid=" + processId
                + "&taskStatus=" + taskStatus + "&preLinkId=" + preLinkId + "' >" + sheetId + "</a>";
    }

    public String getTaskDisplayName() {
        Map taskMap = (HashMap) getCurrentRowObject();
        String name = StaticMethod.nullObject2String(taskMap.get("taskDisplayName")).trim();
        String subTaskFlag = StaticMethod.nullObject2String(taskMap.get("subTaskFlag")).trim();
        if (subTaskFlag != null && subTaskFlag.equals("true")) {
            name = name + "(子任务)";
        }
        return name;
    }

    public Date getSendTime() {
        Map taskMap = (HashMap) getCurrentRowObject();
        Date sendTime = (Date) taskMap.get("sendTime");
        return sendTime;
    }

    public Date getCompleteTimeLimit() {
        Map taskMap = (HashMap) getCurrentRowObject();
        Date completeTimeLimit = (Date) taskMap.get("completeTimeLimit");
        return completeTimeLimit;
    }

    private String getUrl(Map task) {
        String url = "";


        return url;
    }

    /**
     * 超时提醒
     */
    public String getAlarm() {
        return "";
    }

    /**
     * 根据超时提醒改变行的样式
     */
    public String addRowClass() {

        Map taskMap = (HashMap) getCurrentRowObject();
        String urlstr = "";
        String overtimeType = StaticMethod.nullObject2String(taskMap.get("overtimeType")).trim();
        if (overtimeType.equals("1")) {
            urlstr = "serious";
        } else if (overtimeType.equals("2")) {
            urlstr = "alert colorrow";
        }
        return urlstr;
    }
}
