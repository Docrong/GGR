package com.boco.eoms.sheet.businessimplement.webapp.action;

import java.util.Map;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper;

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
 * @author 曲静波
 * @version 1.0
 */
public class ProcessListCommonDisplaytagDecoratorHelper extends ProcessListDisplaytagDecoratorHelper {

    public String getId() {
        Map taskMap = (Map) getCurrentRowObject();
        String taskName = StaticMethod.nullObject2String(taskMap.get("taskName"));
        String sheetId = StaticMethod.nullObject2String(taskMap.get("sheetId"));
        String taskId = StaticMethod.nullObject2String(taskMap.get("id"));
        String inputStr = "<input name=\"batchIds\" type=\"checkbox\" disabled=\"disabled\"/>";
        if (taskName.equals("AffirmHumTask")) {
            inputStr = "<input name=\"batchIds\" type=\"checkbox\" onclick=\"selectedSelf(this)\" value=\"" + sheetId + "\" id=\"" + taskId
                    + "\" sheetId=\"" + sheetId + "\"/>";
        }
        return inputStr;
    }

}
