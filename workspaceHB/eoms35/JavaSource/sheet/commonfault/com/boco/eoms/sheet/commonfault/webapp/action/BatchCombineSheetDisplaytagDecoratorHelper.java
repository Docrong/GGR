package com.boco.eoms.sheet.commonfault.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.util.GeneralUtil;

/**
 * 合并工单列表特殊字段显示
 *
 * @author weichao
 * on 20150527
 */
public class BatchCombineSheetDisplaytagDecoratorHelper extends TableDecorator {

    public String getId() {
        CommonFaultMain main = (CommonFaultMain) getCurrentRowObject();
        String inputStr = "";
        //	inputStr = "<input name=\"mainCheckId\" type=\"checkbox\" onclick=\"selectedMain(this)\"  value=\"" + main.getId() + ";"
        //		+ GeneralUtil.getLevel(main.getMainFaultResponseLevel()) + ";"
        //		+ main.getMainIfCombine() +"\" id=\"" + main.getId() + "\"/>";
        return inputStr;
    }

    public String getSendOrgType() {
        CommonFaultMain main = (CommonFaultMain) getCurrentRowObject();
        String inputStr = "";
//		inputStr = "<input name=\"visCheckId\" type=\"checkbox\" onclick=\"selectedVis(this)\"   value=\"" + main.getId() + ";"
        //			+ GeneralUtil.getLevel(main.getMainFaultResponseLevel()) + ";"
        //		+ main.getMainIfCombine()+ "\" id=\"vis" + main.getId() + "\"/>";
        return inputStr;
    }

    public String getSendDeptId() {
        CommonFaultMain main = (CommonFaultMain) getCurrentRowObject();
        Integer level = GeneralUtil.getLevel(main.getMainFaultResponseLevel());
        String inputStr = String.valueOf(level) + "级";
        return inputStr;

    }

    public String getSheetId() {
        CommonFaultMain main = (CommonFaultMain) getCurrentRowObject();
        String returnUrl = "";
        String url = (String) getPageContext().getAttribute("url");
        String sheetKey = main.getId();
        returnUrl = "<a  href='" + url + "?method=showMainDetailPage&sheetKey=" + sheetKey + "'>" + main.getSheetId() + "</a>";
        return returnUrl;
    }

    public String getStatus() {
        CommonFaultMain main = (CommonFaultMain) getCurrentRowObject();
        String inputStr = "";
//		inputStr = "<a onclick=deleteSome('"+main.getMainIfCombine()+"','"+main.getId()+"',this); href='#'>撤销副单</a>";

        return inputStr;
    }
}
