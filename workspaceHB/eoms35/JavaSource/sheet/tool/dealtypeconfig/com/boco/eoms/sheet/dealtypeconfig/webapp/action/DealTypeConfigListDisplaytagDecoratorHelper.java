	package com.boco.eoms.sheet.dealtypeconfig.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.sheet.dealtypeconfig.model.DealTypeConfig;


public class DealTypeConfigListDisplaytagDecoratorHelper extends TableDecorator{
	
	public String getId(){
		DealTypeConfig temForm = (DealTypeConfig) getCurrentRowObject();
		String url = temForm.getId();
		return url;
	}
	public String getFlowName(){
		DealTypeConfig temForm = (DealTypeConfig) getCurrentRowObject();
		String url = temForm.getFlowName();
		return url;
	}
	public String getTaskName(){
		DealTypeConfig temForm = (DealTypeConfig) getCurrentRowObject();
		String url = temForm.getTaskName();
		return url;
	}
	public String getTaskDisplayName(){
		DealTypeConfig temForm = (DealTypeConfig) getCurrentRowObject();
		String tmp = "";
		String url = "";
		if(temForm!=null){
			tmp = temForm.getTaskDisplayName();
			url = "<a href=dealtypeconfig.do?method=showInputPage&id="+temForm.getId()+"&taskName="+temForm.getTaskName()+">"+temForm.getTaskDisplayName()+ "</a>";
		}
		return url;
	}
	public String getDealPerformerType(){
		DealTypeConfig temForm = (DealTypeConfig) getCurrentRowObject();
		String url = "";
		if(temForm!=null){
			String dealPerformerType = temForm.getDealPerformerType();
			if(dealPerformerType==null||dealPerformerType.equals("")){
				url = "未配置";
			}else if(dealPerformerType.equals("0")){
				url = "分配给人员";
			}else{
				url="分配给角色";
			}
		}
		return url;
	}
	public String getUserId(){
		DealTypeConfig temForm = (DealTypeConfig) getCurrentRowObject();
		String url = temForm.getUserId();
		return url;
	}
}
