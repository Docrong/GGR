package com.boco.eoms.eva.util;

import org.displaytag.decorator.TableDecorator;
import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.eva.model.EvaAuditInfo;

public class AuditInfoListDisplaytagDecorator extends TableDecorator{
	
	public String getUserId() {
		EvaAuditInfo auditInfo = (EvaAuditInfo)getCurrentRowObject();
		return EOMSMgr.getOrgMgrs().getUserMgr().getUser(auditInfo.getAuditUser()).getUsername();
	}

	public String getReplyresult() {
		EvaAuditInfo auditInfo = (EvaAuditInfo) getCurrentRowObject();
		String status = "";
		try {
			status = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-eva",
									"templateStatus"), auditInfo.getStatus());
		} catch (DictServiceException e) {
			status = Util.idNoName();
		}
		return status;
	}
}
