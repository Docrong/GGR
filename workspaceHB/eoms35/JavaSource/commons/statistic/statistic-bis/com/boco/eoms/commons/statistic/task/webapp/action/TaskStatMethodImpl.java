package com.boco.eoms.commons.statistic.task.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.model.FieldDefine;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.webapp.action.BaseStatMethod;

public class TaskStatMethodImpl extends BaseStatMethod implements
		ITaskStatMethod {

	protected List handelResult(List listResult, ActionForm form,
			HttpServletRequest request, Sheet reportConfig, KpiDefine kpiDefine) throws Exception {

		//		
		//			
		List list = super.handelResult(listResult, form, request, reportConfig, kpiDefine);
		String statspecial = request.getParameter("statspecial");
		if (statspecial != null && statspecial.equals("1")) {
			Map guiyang = null;
			Map guiyang1 = null;
			Map guiyang2 = null;
			Map guiyang3 = null;
			for (int i = 0; i < list.size(); i++) {
				Map data = (Map) list.get(i);
				if (((String) data.get("s1")).equals("14")) {
					guiyang = data;
				} else if (((String) data.get("s1")).equals("1562")) {
					guiyang1 = data;
					list.remove(i);
					i--;
				} else if (((String) data.get("s1")).equals("1566")) {
					guiyang2 = data;
					list.remove(i);
					i--;
				} else if (((String) data.get("s1")).equals("1568")) {
					guiyang3 = data;
					list.remove(i);
					i--;
				}
			}
			if (guiyang != null && guiyang != null && guiyang2 != null
					&& guiyang3 != null) {
				for (int i = 0; i < kpiDefine.getAllFieldDefines().size(); i++) {
					String key = ((FieldDefine)kpiDefine.getAllFieldDefines().get(i)).getId();
					int def = 0;
					int isum = str2int((String) guiyang.get(key), def)
							+ str2int((String) guiyang1.get(key), def)
							+ str2int((String) guiyang2.get(key), def)
							+ str2int((String) guiyang3.get(key), def);
					 guiyang.put(key, Integer.toString(isum));
				}

			}
			return list;
		} else {
			return list;

		}
	}

	private int str2int(String s, int def) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return def;
		}

	}
}
