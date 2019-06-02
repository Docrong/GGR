package com.boco.eoms.duty.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.duty.model.TawRmLoanRecord;

public class LoanListDisplaytagDecorator extends TableDecorator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getUserId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmLoanRecord tawRmLoanRecord=(TawRmLoanRecord)getCurrentRowObject();
		String userName="";
		userName=userManager.getUserByuserid(tawRmLoanRecord.getUserId()).getUsername();
		return userName;
	}

}
