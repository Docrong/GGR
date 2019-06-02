package com.boco.eoms.sheet.base.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-11-18 15:52:09
 * </p>
 * 
 * @author 王建华
 * @version 1.0
 *  
 */
public class DraftListDisplaytagDecoratorHelper extends TableDecorator {
    
	public String getSheetId() {
		BaseMain main = (BaseMain) getCurrentRowObject();
		String url = (String) this.getPageContext().getAttribute("url");
    	String sheetId = main.getSheetId();

        return "<a onclick=openSheet('" + url
                + "?method=newShowNewInputSheetPage&draftId=" + main.getId() +"'); href='#'>" + sheetId + "</a>";
    }
    
    public String getTaskDisplayName(){
    	return "草稿";
    }
}
