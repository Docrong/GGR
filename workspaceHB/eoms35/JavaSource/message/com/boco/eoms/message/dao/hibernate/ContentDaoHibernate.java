package com.boco.eoms.message.dao.hibernate;

import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.message.dao.IContentDao;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-7-21 下午03:40:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class ContentDaoHibernate extends BaseDaoHibernate implements
		IContentDao {

	public String getSendContent(Map infoMap) {
		StringBuffer content = new StringBuffer();
		if(infoMap.containsKey("createrDeptName")) {
			content.append(infoMap.get("createrDeptName")+"在");
		}
		if(infoMap.containsKey("createTime")) {
			content.append(infoMap.get("createTime"));		
		}
		content.append("给您派发一条公告，");
		if(infoMap.containsKey("title")) {
			content.append("主题:");
			content.append(infoMap.get("title"));
		}
		if(infoMap.containsKey("suffix")) {
			content.append(infoMap.get("suffix"));
		}
		return content.toString();
	}

	
	
}