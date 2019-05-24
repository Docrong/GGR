package com.boco.eoms.duty.faultrecord.bo;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.apache.struts.action.DynaActionForm;

import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.duty.faultrecord.dao.FaultrecordStatDAO;
import com.boco.eoms.duty.faultrecord.model.Faultrecord;
import com.boco.eoms.duty.faultrecord.qo.FaultrecordQO;
import com.boco.eoms.duty.faultrecord.vo.FaultrecordVO;
import com.boco.eoms.duty.dao.IFaultrecordDao;

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

public class FaultrecordBO {

	Map actionFormMap;

	private IFaultrecordDao faultrecordDao;

	public void setActionFormMap(Map map) {
		this.actionFormMap = map;
	}

	public void setFaultrecordDao(IFaultrecordDao faultrecordDao) {
		this.faultrecordDao = faultrecordDao;
	}

	/**
	 * �����¼��ϸ��Ϣ
	 * 
	 * @throws Exception
	 *             �����쳣
	 */
	public void saveInfo() throws Exception {
		Faultrecord faultrecord = new Faultrecord();
		MyBeanUtils.populate(faultrecord, actionFormMap);
		faultrecord.setInsertTime(StaticMethod.getTimestamp()); // ����ʱ��
		faultrecordDao.saveFaultrecord(faultrecord);
	}

	/**
	 * �����¼��ϸ��Ϣ
	 * 
	 * @throws Exception
	 *             �����쳣
	 */
	public void updateDelFlag() throws Exception {
		Faultrecord faultrecord = new Faultrecord();
		MyBeanUtils.populate(faultrecord, actionFormMap);
		faultrecord.setDelFlag(1); // ����ʱ��
		faultrecordDao.saveFaultrecord(faultrecord);
	}

	/**
	 * @�޸�һ���¼��ɾ����
	 * @�����ɾ��û��ʹ��
	 * @throws Exception
	 */
	public void deleteInfo() throws Exception {
		String _id = StaticMethod.nullObject2String(this.actionFormMap
				.get("id"));
		faultrecordDao.removeFaultrecord(_id);
	}

	/**
	 * @�鿴��¼��ϸ��Ϣ
	 * @param _id
	 *            String ��¼�����
	 * @throws Exception
	 * @return
	 */
	public FaultrecordVO viewInfo(String _id) throws Exception {
		FaultrecordVO vo = new FaultrecordVO();
		Faultrecord faultrecord = faultrecordDao.getFaultrecord(_id);
	      MyBeanUtils.copyProperties(vo, faultrecord);
		return vo;
	}

	/**
	 * @��ѯ������Ϣ
	 * @param pagePra
	 *            int[] ҳ��
	 * @throws Exception
	 * @return
	 */
	public Map searchInfo(Integer curPage, Integer pageSize, String hSql)
			throws Exception {
		return faultrecordDao.getFaultrecords(curPage, pageSize, hSql);
	}

	/**
	 * @��ѯ������Ϣ
	 * @param pagePra
	 *            int[] ҳ��
	 * @throws Exception
	 * @return
	 */
	public Map searchInfo(Integer curPage, Integer pageSize)
			throws Exception {
		FaultrecordQO faultrecordQO = new FaultrecordQO();
		MyBeanUtils.populate(faultrecordQO, actionFormMap);
		String hSql = faultrecordQO.getSql();
		return faultrecordDao.getFaultrecords(curPage, pageSize, hSql);
	}

	public String getStatSql() {
		String sql = "";
		String insertTimeFrom = StaticMethod.nullObject2String(actionFormMap
				.get("insertTimeFrom"));
		String insertTimeTo = StaticMethod.nullObject2String(actionFormMap
				.get("insertTimeTo"));
		String deptId = StaticMethod.nullObject2String(actionFormMap
				.get("deptId"), "0");
		String userId = StaticMethod.nullObject2String(actionFormMap
				.get("userId"), "0");
		String startTime = StaticMethod.nullObject2String(actionFormMap
				.get("startTime"));
		String networkName = StaticMethod.nullObject2String(actionFormMap
				.get("networkName"));
		int deviceType = StaticMethod.nullObject2int(actionFormMap
				.get("devicetype"));
		int faultUnitLevel = StaticMethod.nullObject2int(actionFormMap
				.get("faultUnitLevel"));
		int faultLevel = StaticMethod.nullObject2int(actionFormMap
				.get("faultLevel"));
		String declareUser = StaticMethod.nullObject2String(actionFormMap
				.get("declareUser"));
		String dealUser = StaticMethod.nullObject2String(actionFormMap
				.get("dealUser"));

		sql = "select dept_id,user_id,start_time,network_name,device_type,fault_unit_level,fault_level,declare_user,"
				+ " deal_user,count(*) totleNum from Duty_Faultrecord exp "
				+ " where exp.del_flag=0 and exp.insert_time>='"
				+ insertTimeFrom
				+ "' and exp.insert_time<='"
				+ insertTimeTo + "'";

		if (!deptId.equals("0")) {
			sql += " and dept_id in ('" + deptId + "')";
		}
		if (!userId.equals("0") && !"".equals(userId)) {
			sql += " and user_id in ('" + userId + "')";
		}
		if (!"".equals(startTime)) {
			sql += " and start_time='" + startTime + "'";
		}
		if (!"".equals(networkName)) {
			sql += " and network_name like '%" + networkName + "%'";
		}
		if (deviceType != 0) {
			sql += " and exp.device_type=" + deviceType;
		}
		if (faultUnitLevel != 0) {
			sql += " and exp.fault_unit_level=" + faultUnitLevel;
		}
		if (faultLevel != 0) {
			sql += " and exp.fault_level=" + faultLevel;
		}
		if (!"".equals(declareUser)) {
			sql += " and declare_user like '%" + declareUser + "%'";
		}
		if (!"".equals(dealUser)) {
			sql += " and deal_user like '%" + dealUser + "%'";
		}
		sql += " group by dept_id,user_id,start_time,network_name,device_type,fault_unit_level,fault_level,declare_user,";
		sql += " deal_user order by totleNum desc";
		return sql;
	}

	public Map getResultStat(String sql, Integer curPage, Integer pageSize) throws Exception {
		FaultrecordStatDAO DAO = new FaultrecordStatDAO();
		
		Map map = DAO.getStatResult(sql, curPage, pageSize);
		return map;
	}
}
