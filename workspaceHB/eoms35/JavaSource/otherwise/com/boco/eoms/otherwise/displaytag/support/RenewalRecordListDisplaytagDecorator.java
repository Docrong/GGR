package com.boco.eoms.otherwise.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.otherwise.model.TawRmRenewal;
import com.boco.eoms.otherwise.model.TawRmTestcard;
import com.boco.eoms.otherwise.service.ITawRmTestcardManager;

public class RenewalRecordListDisplaytagDecorator extends TableDecorator {
	
	/**
	 * id属性的checkbox
	 * 
	 * @return 一个带有checkbox的属性
	
	public String getId() {
		TawRmTestcard tawRmTestcard=(TawRmTestcard)getCurrentRowObject();
		return "<input type='checkbox' id='" + tawRmTestcard.getId()
				+ "' name='ids' value='" + tawRmTestcard.getId() + "'>";
	} */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getUserId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmRenewal tawRmRenewal=(TawRmRenewal)getCurrentRowObject();
		String userName="";
		userName=userManager.getUserByuserid(tawRmRenewal.getUserId()).getUsername();
		return userName;
	}

	public String getIccid(){
		ITawRmTestcardManager mgr=(ITawRmTestcardManager)ApplicationContextHolder.getInstance().getBean("ItawRmTestcardManager");
		TawRmRenewal tawRmRenewal=(TawRmRenewal)getCurrentRowObject();
		String testCardId=tawRmRenewal.getTestcardId();
		TawRmTestcard tawRmTestcard=mgr.getTawRmTestcard(testCardId);
		return tawRmTestcard.getIccid();
	}

	public String getMsisdn(){
		ITawRmTestcardManager mgr=(ITawRmTestcardManager)ApplicationContextHolder.getInstance().getBean("ItawRmTestcardManager");
		TawRmRenewal tawRmRenewal=(TawRmRenewal)getCurrentRowObject();
		String testCardId=tawRmRenewal.getTestcardId();
		TawRmTestcard tawRmTestcard=mgr.getTawRmTestcard(testCardId);
		return tawRmTestcard.getMsisdn();
	}

	public String getImsi(){
		ITawRmTestcardManager mgr=(ITawRmTestcardManager)ApplicationContextHolder.getInstance().getBean("ItawRmTestcardManager");
		TawRmRenewal tawRmRenewal=(TawRmRenewal)getCurrentRowObject();
		String testCardId=tawRmRenewal.getTestcardId();
		TawRmTestcard tawRmTestcard=mgr.getTawRmTestcard(testCardId);
		return tawRmTestcard.getImsi();
	}

}
