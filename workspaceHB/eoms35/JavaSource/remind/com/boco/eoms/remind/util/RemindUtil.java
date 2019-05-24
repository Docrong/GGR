package com.boco.eoms.remind.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-10-7 上午08:47:06
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class RemindUtil {

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public static List getCurrentRemindList(String userId) {
		List registeredList = RemindLocator.getRemindRegister().getRegister();
		List currentRemindList = new ArrayList();
		for (Iterator it = registeredList.iterator(); it.hasNext();) {
			String className = StaticMethod.nullObject2String(it.next());
			Class[] paramTypes = new Class[] { String.class };
			Object[] params = new Object[] { userId };
			try {
				List list = (ArrayList) MethodUtil.invoke(className,
						RemindConstants.GETREMINDLIST_METHODNAME, paramTypes,
						params);
				if (null != list && 0 < list.size()) {
					currentRemindList.addAll(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return currentRemindList;
	}
}
