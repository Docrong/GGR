package com.boco.eoms.commons.system.user.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

public class UserDisplaytagDecorator extends TableDecorator{
	
	public String getUserId() {
		TawSystemUser tawSystemUser = (TawSystemUser) getCurrentRowObject();
		return tawSystemUser.getSavetime().toString();
	}

	public String getSex() {
		TawSystemUser tawSystemUser = (TawSystemUser) getCurrentRowObject();
		String sex = "";
		try {
			sex = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-user",
									"userSex"), tawSystemUser.getSex());
		} catch (DictServiceException e) {
			sex = Util.idNoName();
		}
		return sex;
	}
}
