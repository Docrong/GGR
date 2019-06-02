package com.boco.eoms.otherwise.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.duty.model.TawRmWorkorderRecord;
import com.boco.eoms.otherwise.model.TawRmTestcard;

public class TestCardListDisplaytagDecorator extends TableDecorator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getUserId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmTestcard tawRmTestcard=(TawRmTestcard)getCurrentRowObject();
		String userName="";
		userName=userManager.getUserByuserid(tawRmTestcard.getUserId()).getUsername();
		return userName;
	}
	/**
	 * id属性的checkbox
	 * 
	 * @return 一个带有checkbox的属性
	 */
	public String getId() {
		TawRmTestcard tawRmTestcard=(TawRmTestcard)getCurrentRowObject();
		return "<input type='checkbox' id='" + tawRmTestcard.getId()
				+ "' name='ids' value='" + tawRmTestcard.getId() + "'>";
	}

	/**
	 * 测试卡状态
	 * @return
	 */
	public String getState(){
		TawRmTestcard tawRmTestcard=(TawRmTestcard)getCurrentRowObject();
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
