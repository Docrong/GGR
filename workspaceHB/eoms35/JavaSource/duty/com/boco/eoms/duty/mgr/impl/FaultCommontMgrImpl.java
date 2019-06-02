package com.boco.eoms.duty.mgr.impl;

import java.util.*;
import java.text.SimpleDateFormat;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.duty.model.FaultCommont;
import com.boco.eoms.duty.mgr.FaultCommontMgr;
import com.boco.eoms.duty.webapp.form.FaultCommontForm;
import com.boco.eoms.duty.dao.FaultCommontDao;
import com.boco.eoms.duty.dao.TawUserRoomDAO;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

/**
 * <p>
 * Title:通用故障记录
 * </p>
 * <p>
 * Description:通用故障记录功能
 * </p>
 * <p>
 * Mon Mar 23 15:39:20 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class FaultCommontMgrImpl implements FaultCommontMgr {
 
	private FaultCommontDao  faultCommontDao;
 	
	public FaultCommontDao getFaultCommontDao() {
		return this.faultCommontDao;
	}
 	
	public void setFaultCommontDao(FaultCommontDao faultCommontDao) {
		this.faultCommontDao = faultCommontDao;
	}
 	
    public List getFaultCommonts() {
    	return faultCommontDao.getFaultCommonts();
    }
    
    public FaultCommont getFaultCommont(final String id) {
    	return faultCommontDao.getFaultCommont(id);
    }
    
    public void saveFaultCommont(FaultCommont faultCommont) {
    	faultCommontDao.saveFaultCommont(faultCommont);
    }
    
    public void removeFaultCommont(final String id) {
    	faultCommontDao.removeFaultCommont(id);
    }
    
    public Map getFaultCommonts(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return faultCommontDao.getFaultCommonts(curPage, pageSize, whereStr);
	}
    
    /**
	 * 根据机房ID号获取机房人员名称
	 * @param roomId 机房名称
	 * @return 返回机房所在人员信息量
	 */
	public List getRoomUsers(int roomId) {
		com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
		.getInstance();
		List userList = new ArrayList();
		TawSystemUser user = null;
		try {
			TawUserRoomDAO tawUserRoomDAO = new TawUserRoomDAO(ds);
			Vector users = tawUserRoomDAO.getRoomUser(roomId, 0);
			for (int i=0;i*2<users.size();i++) {
				user = new TawSystemUser();
				user.setUserid(StaticMethod.nullObject2String(users.elementAt(i*2)));
				user.setUsername(StaticMethod.nullObject2String(users.elementAt(i*2+1)));
				userList.add(user);
			}
		} catch(java.sql.SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	/**
	 * 获取故障记录编号
	 * @param 
	 * @return 返回故障记录编号
	 */
	public String getFaultSequenceNo() {
		int countLength = 4;
		String sequenceNo = "";
		java.util.Date curDate = Calendar.getInstance().getTime();
		List list = faultCommontDao.getFaultSequenceNo();
	
		if(list.size()>0) sequenceNo = list.get(0).toString();
		if(sequenceNo==null||sequenceNo.equals("")) { // 不存在记录
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
			String curDateString2 = formatter2.format(curDate);
			String tmpStr = "1";

			int appendLength = countLength - tmpStr.length();
			for (int i = 0; i < appendLength; i++) {
				tmpStr = "0" + tmpStr;
			}
			sequenceNo = curDateString2 + "-" + tmpStr;
		} else {
			String lastSequenceNo = sequenceNo;
			int newCount = Integer.parseInt(lastSequenceNo
					.substring(lastSequenceNo.indexOf("-") + 1)) + 1;

			String tmpStr = String.valueOf(newCount);
			int appendLength = countLength - tmpStr.length();
			
			// 编号规则是年月日-本月工单编号自动增加
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
			String curDateString2 = formatter2.format(curDate);
			for (int i = 0; i < appendLength; i++) {
				tmpStr = "0" + tmpStr;
			}

			sequenceNo = curDateString2 + "-" + tmpStr;
		}

		return sequenceNo;
	}
	
	/**
	 * 根据Form中数据，获取查询条件
	 * @param faultCommtForm
	 * @return 返回 condition,字符串
	 */
	public String getSearchCondition(FaultCommontForm faultCommontForm){
		//拼SQL条件字串
		StringBuffer conditionStr = new StringBuffer();
		if (faultCommontForm.getTitle() != null &&!faultCommontForm.getTitle().equals("")) { // 主题查询条件
            conditionStr.append(" AND faultCommont.title like('%").append(faultCommontForm.getTitle() + "%') ");
        }
        if (faultCommontForm.getGreffier()!=null &&!faultCommontForm.getGreffier().equals("")&&!faultCommontForm.getGreffier().equals("-1")) { // 故障记录人查询条件
            conditionStr.append(" AND faultCommont.greffier='").append(faultCommontForm.getGreffier() + "' ");
        }
        if (faultCommontForm.getRoomId()!=null&&!faultCommontForm.getRoomId().equals("")) { // 机房查询条件
            conditionStr.append(" AND faultCommont.roomId=").append(faultCommontForm.getRoomId() + " ");
        }
        if (faultCommontForm.getFromBeginTime() != null &&!faultCommontForm.getFromBeginTime().equals("")) { // 故障开始时间查询条件
            conditionStr.append(" AND faultCommont.beginTime>= '").append(faultCommontForm.getFromBeginTime() + "' ");
        }
        if (faultCommontForm.getToBeginTime() != null &&!faultCommontForm.getToBeginTime().equals("")) { // 故障开始时间查询条件
            conditionStr.append(" AND faultCommont.beginTime<= '").append(faultCommontForm.getToBeginTime() + "' ");
        }
        if (faultCommontForm.getTypeId()!=null&&!faultCommontForm.getTypeId().equals("-1")&&!faultCommontForm.getTypeId().equals("")) { // 故障类别查询条件
            conditionStr.append(" AND faultCommont.typeId=").append(faultCommontForm.getTypeId() + " ");
        }
        if (faultCommontForm.getEstate() != null &&!faultCommontForm.getEstate().equals("")&&!faultCommontForm.getEstate().equals("-1")) { // 是否归 档查询条件
            conditionStr.append(" AND faultCommont.estate=").append(faultCommontForm.getEstate() + " ");
        }
        if (faultCommontForm.getFaultPhenomenon() != null &&!faultCommontForm.getFaultPhenomenon().equals("")) { // 故障现象查询条件
            conditionStr.append(" AND faultCommont.faultPhenomenon like('%").append(faultCommontForm.getFaultPhenomenon() + "%') ");
        }
        if (faultCommontForm.getAppEffect() != null &&!faultCommontForm.getAppEffect().equals("")) { // 影响业务情况
            conditionStr.append(" AND faultCommont.appEffect like('%").append(faultCommontForm.getAppEffect() + "%') ");
        }
        if (faultCommontForm.getFaultCause() != null &&!faultCommontForm.getFaultCause().equals("")) { // 故障原因
            conditionStr.append(" AND faultCommont.faultCause like('%").append(faultCommontForm.getFaultCause() + "%') ");
        }
        if (faultCommontForm.getSerialNos() != null &&!faultCommontForm.getSerialNos().equals("")) { // 重要故障上报工单号查询条件
            conditionStr.append(" AND faultCommont.serialNos like('%").append(faultCommontForm.getSerialNos() + "%') ");
        }
		
		return conditionStr.toString();
	}
	
	/**
	 * 返回统计数据
	 * @param 统计条件
	 * @return List 统计结果
	 */
	public List getStatList(String condition){
		return faultCommontDao.getStatList(condition);
	}
	
	/**
     * 获取故障记录数量
     * @return String 数量
     */
     public String getNum(String condition){
    	 return faultCommontDao.getNum(condition);
     }	
}