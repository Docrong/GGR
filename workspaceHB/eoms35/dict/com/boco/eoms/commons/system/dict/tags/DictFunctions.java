package com.boco.eoms.commons.system.dict.tags;

import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;
import org.apache.log4j.Logger;
import net.sf.json.JSONArray;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;

/**
 * <p>Title: 字典方法类</p>
 * <p>Description: 字典相关方法 </p>
 * @author mios
 * @version 1.0
 */
public class DictFunctions {
	private static Logger logger = Logger.getLogger(DictFunctions.class);
	
	/**
	 * 将指定dictId的字典项的下级字典项输出为Javascript Array形式的字符串
	 * 用于在Ext框架中显示字典项
	 * 一般形如：
	 * [
	 * 	["是",1],
	 *  ["否",0]
	 * ]
	 * @param dictId
	 * @return String
	 */
	public static String subDict2Array(String dictId) throws JspException {
		JSONArray root = new JSONArray();
		try {
			ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDictTypeManager");
			List list = dictMgr.getDictSonsByDictid(dictId);
			for (Iterator it = list.iterator(); it.hasNext();) {
				TawSystemDictType item = (TawSystemDictType) it.next();
				JSONArray jitem = new JSONArray();
				jitem.put(item.getDictName());
				jitem.put(item.getDictId());
				root.put(jitem);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return root.toString();
	}
}
