package com.boco.eoms.workbench.infopub.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.workbench.infopub.model.Thread;

/**
 * <p>
 * Title:threadList.jsp页面分页显示checkbox
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 27, 2008 11:00:23 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadListDisplaytagDecorator extends TableDecorator {
	/**
	 * id属性的checkbox
	 * 
	 * @return 一个带有checkbox的属性
	 */
	public String getId() {		
		Thread thread = (Thread) getCurrentRowObject();
		return "<input type='checkbox' id='" + thread.getId()
				+ "' name='ids' value='" + thread.getId() + "'>";
	}

	/**
	 * 信息类型，重要，紧急
	 * 
	 * @return 紧急名称
	 */
	public String getThreadTypeId() {
		Thread thread = (Thread) getCurrentRowObject();
		String threadTypeName = "";
		try {
			threadTypeName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-workbench-infopub",
									"threadType"), thread.getThreadTypeId());
		} catch (DictServiceException e) {
			threadTypeName = Util.idNoName();
		}
		return threadTypeName;
	}

	/**
	 * 信息状态
	 * 
	 * @return
	 */
	public String getStatus() {
		Thread thread = (Thread) getCurrentRowObject();
		String threadTypeName = "";
		try {
			threadTypeName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-workbench-infopub",
									"auditStatus"), thread.getStatus());
		} catch (DictServiceException e) {
			threadTypeName = Util.idNoName();
		}
		return threadTypeName;
	}

	/**
	 * 所属专题
	 * 
	 * @return
	 */
	public String getForumsId() {
		Thread thread = (Thread) getCurrentRowObject();
		String forumName = "";
		try {
			ID2NameDAO forumsDao = (ID2NameDAO) ApplicationContextHolder
					.getInstance().getBean("forumsDao");
			forumName = forumsDao.id2Name(thread.getForumsId());
		} catch (Exception e) {
			forumName = Util.idNoName();
		}
		return forumName;
	}
}
