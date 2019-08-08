package com.boco.eoms.sheet.commonfault.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;

public class BatchClaimSheetDisplaytagDecoratorHelper extends TableDecorator {

    public String getId() {
        CommonFaultMain main = (CommonFaultMain) getCurrentRowObject();
        String inputStr = "";
        inputStr = "<input name=\"mainCheckId\" type=\"checkbox\"  value=\"" + main.getId() + "\" id=\"" + main.getId() + "\"/>";
        return inputStr;
    }

    public String getSendOrgType() {
        CommonFaultMain main = (CommonFaultMain) getCurrentRowObject();
        String inputStr = "";
        inputStr = "<input name=\"visCheckId\" type=\"checkbox\"  value=\"" + main.getId() + "\" id=\"vis" + main.getId() + "\"/>";
        return inputStr;
    }
}
