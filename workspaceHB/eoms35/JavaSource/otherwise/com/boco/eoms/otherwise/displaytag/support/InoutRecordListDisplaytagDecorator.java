package com.boco.eoms.otherwise.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.otherwise.model.TawRmInoutRecord;
import com.boco.eoms.otherwise.model.TawRmTestcard;
import com.boco.eoms.otherwise.service.ITawRmTestcardManager;

public class InoutRecordListDisplaytagDecorator extends TableDecorator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getUserId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmInoutRecord tawRmInoutRecord=(TawRmInoutRecord)getCurrentRowObject();
		String userName="";
		userName=userManager.getUserByuserid(tawRmInoutRecord.getUserId()).getUsername();
		return userName;
	}

	public String getBorrowerId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmInoutRecord tawRmInoutRecord=(TawRmInoutRecord)getCurrentRowObject();
		String userName="";
		try {
			userName=userManager.id2Name(tawRmInoutRecord.getBorrowerId());
		} catch (DictDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userName;
	}

	public String getIccid(){
		ITawRmTestcardManager mgr=(ITawRmTestcardManager)ApplicationContextHolder.getInstance().getBean("ItawRmTestcardManager");
		TawRmInoutRecord tawRmInoutRecord=(TawRmInoutRecord)getCurrentRowObject();
		String testCardId=tawRmInoutRecord.getTestcardId();
		TawRmTestcard tawRmTestcard=mgr.getTawRmTestcard(testCardId);
		return tawRmTestcard.getIccid();
	}

	public String getMsisdn(){
		ITawRmTestcardManager mgr=(ITawRmTestcardManager)ApplicationContextHolder.getInstance().getBean("ItawRmTestcardManager");
		TawRmInoutRecord tawRmInoutRecord=(TawRmInoutRecord)getCurrentRowObject();
		String testCardId=tawRmInoutRecord.getTestcardId();
		TawRmTestcard tawRmTestcard=mgr.getTawRmTestcard(testCardId);
		return tawRmTestcard.getMsisdn();
	}

	public String getImsi(){
		ITawRmTestcardManager mgr=(ITawRmTestcardManager)ApplicationContextHolder.getInstance().getBean("ItawRmTestcardManager");
		TawRmInoutRecord tawRmInoutRecord=(TawRmInoutRecord)getCurrentRowObject();
		String testCardId=tawRmInoutRecord.getTestcardId();
		TawRmTestcard tawRmTestcard=mgr.getTawRmTestcard(testCardId);
		return tawRmTestcard.getImsi();
	}

	public String getState(){
		ITawRmTestcardManager mgr=(ITawRmTestcardManager)ApplicationContextHolder.getInstance().getBean("ItawRmTestcardManager");
		TawRmInoutRecord tawRmInoutRecord=(TawRmInoutRecord)getCurrentRowObject();
		String testCardId=tawRmInoutRecord.getTestcardId();
		TawRmTestcard tawRmTestcard=mgr.getTawRmTestcard(testCardId);
		String state = "";
		try {
			state = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"useState"), tawRmTestcard.getState());
		} catch (DictServiceException e) {
			state = Util.idNoName();
		}
		return state;
	}
}
