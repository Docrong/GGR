package com.boco.eoms.sheet.commonfaultpack.webapp.action;

import java.util.Date;
import java.util.HashMap;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;
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
 * Date:2007-9-21 15:52:09
 * </p>
 *
 * @author zenghankai
 * @version 1.0
 */
public class faultpackListDisplaytagDecoratorHelper extends TableDecorator {

    public String getMainAlarmId() {
        CommonFaultPackMain main = (CommonFaultPackMain) getCurrentRowObject();
        String content = "";
        String url = (String) this.getPageContext().getAttribute("url");
        String taskName = (String) this.getPageContext().getAttribute("taskName");
        String alarmMethod = (String) this.getPageContext().getAttribute(
                "alarmMethod");
        if ("update".equals(alarmMethod)) {
            content = "<a onclick=openSheet('" + url
                    + "?method=showDetailPage&alarmMethod=update&alarmId=" + main.getId()
                    + "',1020); href='#'>回复</a>";
        } else if ("updateTime".equals(alarmMethod)) {
            content = "<a onclick=openSheet('" + url
                    + "?method=showDetailPage&alarmMethod=updateTime&alarmId=" + main.getId()
                    + "',1020); href='#'>回复</a>";
        } else {
            if ("0".equals(main.getMainAlarmState()))
                content = "<a onclick=openSheet('" + url
                        + "?method=showDetailPage&alarmId=" + main.getId()
                        + "',460); href='#'>查看</a>";
            else
                content = "<a onclick=openSheet('" + url
                        + "?method=showDetailPage&alarmId=" + main.getId()
                        + "',1020); href='#'>查看</a>";
        }
        if ("1".equals(main.getMainAlarmState()))
            content = "[已回复]" + content;
        return content;
    }

}
