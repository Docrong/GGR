package com.boco.eoms.commons.system.reported.displaytag.support;
import org.displaytag.decorator.TableDecorator;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.reported.model.TawSystemReported;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;

public class TawSystemReportedListDisplayTagDecorator extends TableDecorator {

	public String getUserId(){
		String userName = "";
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
		.getInstance().getBean("itawSystemUserManager");
		TawSystemReported tawSystemReported = (TawSystemReported)getCurrentRowObject(); 
		userName = userMgr.getUserByuserid(tawSystemReported.getUserId()).getUsername();
		return userName;
	}

	/**
	 * 模块ID
	 * @return
	 */
	public String getModelId(){
		TawSystemReported tawSystemReported = (TawSystemReported)getCurrentRowObject(); 
		String modelIdName = "";
		try {
			modelIdName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-reported",
									"modelId"), tawSystemReported.getModelId());
		} catch (DictServiceException e) {
			modelIdName = Util.idNoName();
		}
		return modelIdName;
	}
	
	/**
	 * 功能ID
	 * @return
	 */
	public String getFunctionId(){
		TawSystemReported tawSystemReported = (TawSystemReported)getCurrentRowObject(); 
		String functionIdName = "";
		try {
			functionIdName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-reported",
									"functionId"), tawSystemReported.getFunctionId());
		} catch (DictServiceException e) {
			functionIdName = Util.idNoName();
		}
		return functionIdName;
	}
}
