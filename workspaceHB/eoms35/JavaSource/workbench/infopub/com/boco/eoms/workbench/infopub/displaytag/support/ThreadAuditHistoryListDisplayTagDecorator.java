package com.boco.eoms.workbench.infopub.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.util.Constants;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;

/**
 * <p>
 * Title:审核列表displaytag
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 3, 2008 8:34:42 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadAuditHistoryListDisplayTagDecorator extends TableDecorator {
	/**
	 * id属性的checkbox
	 * 
	 * @return 一个带有checkbox的属性
	 */
	public String getId() {
		ThreadAuditHistory auditHistory = (ThreadAuditHistory) getCurrentRowObject();
		if (InfopubConstants.AUDIT_NO_PASS.equals(auditHistory.getStatus())
				|| InfopubConstants.AUDIT_PASS.equals(auditHistory.getStatus())) {
			return "";
		}
		return "<input type='checkbox' id='" + auditHistory.getId()
				+ "' name='ids' value='" + auditHistory.getId() + "'>";
	}

	/**
	 * 组织名称
	 * 
	 * @return 组织名称
	 */
	public String getOrgId() {
		ThreadAuditHistory auditHistory = (ThreadAuditHistory) getCurrentRowObject();
		// 若为角色则显示角色名称
		if (StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(auditHistory
				.getOrgType())) {
			ITawSystemSubRoleManager roleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemSubRoleManager");
			return roleMgr.getTawSystemSubRole(auditHistory.getOrgId())
					.getSubRoleName();

		}
		// 若为用户则显示用户名称
		else if (StaticVariable.PRIV_ASSIGNTYPE_USER.equals(auditHistory
				.getOrgType())) {
			ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
					.getInstance().getBean("itawSystemUserManager");
			return userMgr.getUserByuserid(auditHistory.getOrgId())
					.getUsername();
		}
		return Constants.ID_NO_NAME;

	}

	/**
	 * 取审核状态
	 * 
	 * @return 审核状态
	 */
	public String getStatus() {
		ThreadAuditHistory auditHistory = (ThreadAuditHistory) getCurrentRowObject();
		String status = "";
		try {
			status = (String) DictMgrLocator.getDictService().itemId2name(
					Util.constituteDictId("dict-workbench-infopub",
							"auditStatus"), auditHistory.getStatus());
		} catch (DictServiceException e) {
			status = Util.idNoName();
		}
		return status;
	}

	/**
	 * 信息主题
	 * 
	 * @return 信息主题
	 */
	public String getThreadId() {
		ThreadAuditHistory auditHistory = (ThreadAuditHistory) getCurrentRowObject();
		ID2NameService id2NameService = (ID2NameService) ApplicationContextHolder
				.getInstance().getBean("id2nameService");
		return id2NameService.id2Name(auditHistory.getThreadId(), "threadDao");
	}
	/**
	 * 执行状态
	 * @return 执行状态
	 */
	public String getIsCurrent() {
		ThreadAuditHistory auditHistory = (ThreadAuditHistory) getCurrentRowObject();
		if(auditHistory.getIsCurrent().equals("true")){
			return "是";
		}
		return "否";
	}
}
