package com.boco.eoms.sheet.base.webapp.action;

import java.util.Date;

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
 * Date:2008-08-16 
 * </p>
 * 
 * @author 杨亮亮
 * @version 1.0
 *  
 */
public class NewAdminListDisplaytagDecoratorHelper extends TableDecorator{


	public String getSheetId() {

		BaseMain main = (BaseMain) getCurrentRowObject();
		String url = (String) this.getPageContext().getAttribute("url");

		return "<a  href='" + url
				+ "?method=newShowDetailPage&sheetKey=" + main.getId()
				+ "&isAdmin=true&entryAdmin=true' >" + main.getSheetId() + "</a>";
	}
	
    public Date getSendTime(){
    	BaseMain main = (BaseMain) getCurrentRowObject();
    	Date sendTime = main.getSendTime();    	
    	return sendTime;
    }		
	
/**
	public String getStatus() {
		BaseMain main = (BaseMain) getCurrentRowObject();
		String statusName = "";
		if(main.getStatus().intValue()==0){
			statusName = "运行中";
		}else if(main.getStatus().intValue()==1){
			statusName = "已归档";
		}else if(main.getStatus().intValue()==-1){
			statusName = "已撤销";
		}else if(main.getStatus().intValue()==-2){
			statusName = "已删除";
		}else if(main.getStatus().intValue()==-10){
			statusName = "强制归档";
		}else if(main.getStatus().intValue()==-11){
			statusName = "强制作废";
		}else if(main.getStatus().intValue()== -12){
			statusName = "作废";
		}else{
			statusName = "";
		}
		return statusName;
		
	}
*/
	
}
