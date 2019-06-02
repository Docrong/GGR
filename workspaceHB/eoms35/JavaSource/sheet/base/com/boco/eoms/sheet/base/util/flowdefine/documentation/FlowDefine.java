/*
 * Created on 2008-1-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util.flowdefine.documentation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.StaticMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FlowDefine {
	private List toPhaseIdList;

	/**
	 * @return Returns the toPhaseIdList.
	 */
	public List getToPhaseIdList() {
		return toPhaseIdList;
	}

	/**
	 * @param toPhaseIdList
	 *            The toPhaseIdList to set.
	 */
	public void setToPhaseIdList(List toPhaseIdList) {
		this.toPhaseIdList = toPhaseIdList;
	}

	public FlowDefine(String flowDefine) {
		if (flowDefine != null && !flowDefine.equals("")) {
			JSONArray subRoleJSONList = new JSONArray();
			subRoleJSONList = JSONArray.fromString(flowDefine);

			List toPhaseIdLists = new ArrayList();

			for (int i = 0; i < subRoleJSONList.length(); i++) {
				JSONObject jsonObj = (JSONObject) subRoleJSONList.get(i);

				ToPhaseId tophaseid = new ToPhaseId();
				Method[] methods = tophaseid.getClass().getDeclaredMethods();
				for (int j = 0; methods.length > 0 && j < methods.length; j++) {
					Method method = methods[j];
					String name = method.getName();
					String returnType = method.getReturnType().getName();
					if (name.length() > 3 && name.indexOf("get") == 0) {
						String proName = name.substring(
								name.indexOf("get") + 4, name.length());
						String firstLeter = name.substring(
								name.indexOf("get") + 3, 4);
						proName = firstLeter.toLowerCase() + proName;

						if (jsonObj.has(proName)) {

							String setMethod = "set"
									+ StaticMethod.firstToUpperCase(proName);
							Method setterMethod;
							try {
								setterMethod = tophaseid.getClass()
										.getMethod(setMethod,
												new Class[] { String.class });
								setterMethod.invoke(tophaseid,
										new Object[] { jsonObj
												.getString(proName) });
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}

				toPhaseIdLists.add(tophaseid);
			}
			this.setToPhaseIdList(toPhaseIdLists);
		}
	}
}
