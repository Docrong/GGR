package com.boco.eoms.duty.faultrecord.qo;

import com.boco.eoms.common.oo.QueryDataObject;
import com.boco.eoms.common.util.StaticMethod;
import java.sql.Timestamp;

/**
 * <p>
 * Title: ���ϼ�¼
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author zhangxiaobo
 * @version 1.0
 */

public class FaultrecordQO extends QueryDataObject {

	public FaultrecordQO() {
	}

	private String poName = "faultrecord";

	private String clauseSql = " where faultrecord.delFlag=0";

	private String orderSql = " order by faultrecord.insertTime desc";

	public String getSql() {
		String sql = " from Faultrecord as faultrecord" + clauseSql + orderSql;
		return sql;
	}

	/** ����ʱ�� */
	private String insertTimeFrom;

	public void setInsertTimeFrom(String insertTimeFrom) {
		if (insertTimeFrom != null && !insertTimeFrom.equals("")) {
			clauseSql += " and faultrecord.insertTime >= '" + insertTimeFrom
					+ "'";
		}
		this.insertTimeFrom = insertTimeFrom;
	}

	/** ����ʱ�� */
	private String insertTimeTo;

	public void setInsertTimeTo(String insertTimeTo) {
		if (insertTimeTo != null && !insertTimeTo.equals("")) {
			clauseSql += " and faultrecord.insertTime <= '" + insertTimeTo
					+ "'";
		}
		this.insertTimeTo = insertTimeTo;
	}

	/** ������ */
	private String userId;

	public void setUserId(String userId) {
		if (userId != null && !userId.equals("")) {
			clauseSql += " and faultrecord.userId like '%" + userId + "%'";
		}
		this.userId = userId;
	}

	/** �������� */
	private int deptId;

	public void setDeptId(int deptId) {
		if (deptId > 0) {
			clauseSql += " and faultrecord.deptId=" + deptId;
		}
		this.deptId = deptId;
	}

	/***************************************************************************
	 * �����Ǹ��?���е�����
	 **************************************************************************/

	/** �澯ʱ�� */
	private String startTime;

	public void setStartTime(String startTime) {
		if (startTime != null && !startTime.equals("")) {
			clauseSql += " and faultrecord.startTime = '" + startTime + "'";
		}
		this.startTime = startTime;
	}

	/** ��Ԫ��� */
	private String networkName;

	public void setNetworkName(String networkName) {
		this.clauseSql += addLikeClause(poName, "networkName", networkName);
		this.networkName = networkName;
	}

	/** �豸���� */
	private int devicetype;

	public void setDevicetype(int devicetype) {
		if (devicetype != 0) {
			this.clauseSql += addEqualIntClause(poName, "devicetype", ""
					+ devicetype);
			this.devicetype = devicetype;
		}
	}

	/** ���ϵ�Ԫ���� */
	private int faultUnitLevel;

	public void setFaultUnitLevel(int faultUnitLevel) {
		if (faultUnitLevel != 0) {
			this.clauseSql += addEqualIntClause(poName, "faultUnitLevel", ""
					+ faultUnitLevel);
			this.faultUnitLevel = faultUnitLevel;
		}
	}

	/** ���ϼ��� */
	private int faultLevel;

	public void setFaultLevel(int faultLevel) {
		if (faultLevel != 0) {
			this.clauseSql += addEqualIntClause(poName, "faultLevel", ""
					+ faultLevel);
			this.faultLevel = faultLevel;
		}
	}

	/** �����걨�� */
	private String declareUser;

	public void setDeclareUser(String declareUser) {
		this.clauseSql += addLikeClause(poName, "declareUser", declareUser);
		this.declareUser = declareUser;
	}

	/** ���ϴ����� */
	private String dealUser;

	public void setDealUser(String dealUser) {
		this.clauseSql += addLikeClause(poName, "dealUser", dealUser);
		this.dealUser = dealUser;
	}

}