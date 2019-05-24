/**
 * 
 */
package com.boco.eoms.sheet.base.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * 工单隐藏时显示隐藏列表
 * @author jialei
 *
 */
public class HideListDisplaytagDecoratorHelper extends TableDecorator {
	
	public String getIsCheck() {
		BaseMain main = (BaseMain) getCurrentRowObject();
		String returnstr = "<input type=\"checkbox\" name=\"checksheet\" id=\""+main.getId()+"\" onclick=\"hidecheck(this)\"> ";
		return returnstr;
	}
	
	public String getTitle() {
		BaseMain main = (BaseMain) getCurrentRowObject();
        String temTitle = main.getTitle();
        return temTitle;
	}
		
	public String getSheetId() {

		BaseMain main = (BaseMain) getCurrentRowObject();
		String url = (String) this.getPageContext().getAttribute("url");
		String returnstr = "<a "
							+ " href='"
							+ url + "?method=showMainDetailPage&sheetKey=" + main.getId()
							+ "', 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no'>"+main.getSheetId() + "</a>";
		return returnstr;
	}
}
