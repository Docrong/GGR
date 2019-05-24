package com.boco.eoms.workbench.memo.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;

public class MemoListDisplaytagDecorator extends TableDecorator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getUserId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawWorkbenchMemo tawWorkbenchMemo=(TawWorkbenchMemo)getCurrentRowObject();
		String userName="";
		try {
			if(!tawWorkbenchMemo.getUserid().equals("")){
				userName=userManager.id2Name(tawWorkbenchMemo.getUserid());
			}
		} catch (DictDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userName;
	}


	public String getLevel(){
		TawWorkbenchMemo tawWorkbenchMemo=(TawWorkbenchMemo)getCurrentRowObject();
		String level = "";
		try {
			level = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("memo-dict",
									"level"), tawWorkbenchMemo.getLevel());
		} catch (DictServiceException e) {
			level = Util.idNoName();
		}
		return level;
	}


	public String getSendflag(){
		TawWorkbenchMemo tawWorkbenchMemo=(TawWorkbenchMemo)getCurrentRowObject();
		String sendflag = "";
		try {
			sendflag = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("memo-dict",
									"sendflag"), tawWorkbenchMemo.getSendflag());
		} catch (DictServiceException e) {
			sendflag = Util.idNoName();
		}
		return sendflag;
	}

	public String getSendmanner(){
		TawWorkbenchMemo tawWorkbenchMemo=(TawWorkbenchMemo)getCurrentRowObject();
		String sendmanner = "";
		try {
			sendmanner = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("memo-dict",
									"sendmanner"), tawWorkbenchMemo.getSendmanner());
		} catch (DictServiceException e) {
			sendmanner = Util.idNoName();
		}
		return sendmanner;
	}

}
