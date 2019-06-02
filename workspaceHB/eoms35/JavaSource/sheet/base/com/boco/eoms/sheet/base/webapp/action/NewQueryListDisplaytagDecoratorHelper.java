package com.boco.eoms.sheet.base.webapp.action;

import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
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
 *  
 */
public class NewQueryListDisplaytagDecoratorHelper extends TableDecorator {
	public String getTitle() {

		BaseMain main = (BaseMain) getCurrentRowObject();
		//String url = (String) this.getPageContext().getAttribute("url");
        String temTitle = main.getTitle();
        return temTitle;
		//return "<a onclick=openSheet('" + url
		//		+ "?method=showMainDetailPage&sheetKey=" + main.getId()
		//		+ "'); href='#'>" + main.getTitle() + "</a>";
	}
		
	public String getSheetId() {

		BaseMain main = (BaseMain) getCurrentRowObject();
		String url = (String) this.getPageContext().getAttribute("url");

		return "<a  href='" + url
				+ "?method=newShowMainDetailPage&sheetKey=" + main.getId()
				+ "' target=_blank>" + main.getSheetId() + "</a>";
	}

    public Date getSendTime(){
    	BaseMain main = (BaseMain) getCurrentRowObject();
    	Date sendTime = main.getSendTime();    	
    	return sendTime;
    }	
	
	public String getPiid() {
		BaseMain main = (BaseMain) getCurrentRowObject();
		String url = (String) this.getPageContext().getAttribute("url");
		if (main.getStatus().equals(new Integer(0))) {
			return "<a  href='" + url + "?method=showCancelInputPage&piid="
					+ main.getPiid() + "&sheetKey=" + main.getId()
					+ "' target=_blank>" + "撤消" + "</a>";
		} else {
			return "";
		}
	}
	

	public String getStatus() {
		BaseMain main = (BaseMain) getCurrentRowObject();
		String statusName = "";
		if(main.getStatus().intValue()== Constants.SHEET_RUN.intValue()){
			statusName = "运行中";
		}else if(main.getStatus().intValue()== Constants.SHEET_HOLD.intValue()){
			statusName = "已归档";
		}else if(main.getStatus().intValue()==Constants.SHEET_CANCEL.intValue()){
			statusName = "已撤销";
		}else if(main.getStatus().intValue()== Constants.SHEET_DELETE.intValue()){
			statusName = "已删除";
		}else if(main.getStatus().intValue()== Constants.ACTION_FORCE_HOLD){
			statusName = "强制归档";
		}else if(main.getStatus().intValue()== Constants.ACTION_FORCE_NULLITY){
			statusName = "强制作废";
		}else if(main.getStatus().intValue()== Constants.ACTION_NULLITY){
			statusName = "作废";
		}else{
			statusName = "";
		}
		return statusName;
		
	}
	
	
	public String getSendUserId(){
		BaseMain main = (BaseMain) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		String sendUserId=main.getSendUserId();
		String userName = service.id2Name(sendUserId,"tawSystemUserDao");
		return userName;
	}
	
}
