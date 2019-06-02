package com.boco.eoms.otherwise.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.otherwise.model.TawRmTestcard;

public class RenewDateDisplaytagDecorator extends TableDecorator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * id属性的radio
	 * 
	 * @return 一个带有radio的属性
	 */
	public String getId() {
		TawRmTestcard tawRmTestcard=(TawRmTestcard)getCurrentRowObject();
		return "<input type='radio' id='" + tawRmTestcard.getId()
				+ "' name='id' value='" + tawRmTestcard.getId() + "'>";
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
