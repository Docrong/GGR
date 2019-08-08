package com.boco.eoms.sheet.groupcheck.webapp.action;

import java.util.Date;
import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-22 20:25:13
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class GroupListDisplaytagDecoratorHelper extends TableDecorator {
    public String getFoundNum() {
        Map main = (Map) getCurrentRowObject();
        String feizi = StaticMethod.nullObject2String(main.get("feizi")).trim();
        String feimu = StaticMethod.nullObject2String(main.get("feimu")).trim();

        String found = "0";
        if ("0".equals(feizi)) {
            found = "0";
        } else {
            found = String.valueOf(Float.parseFloat(feizi) / Float.parseFloat(feimu) * 100);
            if (found.lastIndexOf(".") > 0 && found.lastIndexOf(".") + 3 < found.length()) {
                found = found.substring(0, found.lastIndexOf(".") + 3);
            }
        }
        return found + "%";
    }
}
