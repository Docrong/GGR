/**
 * 
 */
package com.boco.eoms.commons.system.priv.util;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class TawSystemPrivCommon {

	/**
	 * 获取当前用户id
	 */
	public static String getCurrentUserId() {
		String _strUserId = "1001";

		return _strUserId;
	}

	/**
	 * 获取当前用户名
	 */
	public static String getCurrentUserName() {
		String _strUserName = "";

		return _strUserName;
	}

	/**
	 * 判断指定用户是否是超级用户
	 */
	public static boolean isSuperAdmin(String _strUserId) {
		boolean _bReturn = true;

		return _bReturn;
	}

	/**
	 * 
	 */
	public static List convertList(List _objSrc) {
		List _objDest = new ArrayList();

		for (int i = 0; i < _objSrc.size(); i++) {
			TawSystemPrivUserAssign _objTmp1 = new TawSystemPrivUserAssign();
			TawSystemPrivOperation _objTmp2 = (TawSystemPrivOperation) _objSrc
					.get(i);
			_objTmp1.setCurrentprivid(_objTmp2.getCode());
			_objTmp1.setCurrentprivname(_objTmp2.getName());
			_objDest.add(_objTmp1);
		}

		return _objDest;
	}
}
