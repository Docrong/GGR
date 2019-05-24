package com.boco.eoms.duty.mgr.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.FaultEquipment;
import com.boco.eoms.duty.mgr.FaultEquipmentMgr;
import com.boco.eoms.duty.webapp.form.FaultEquipmentForm;
import com.boco.eoms.duty.dao.FaultEquipmentDao;

/**
 * <p>
 * Title:设备故障记录
 * </p>
 * <p>
 * Description:设备故障记录
 * </p>
 * <p>
 * Sun Mar 29 09:02:44 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class FaultEquipmentMgrImpl implements FaultEquipmentMgr {
 
	private FaultEquipmentDao  faultEquipmentDao;
 	
	public FaultEquipmentDao getFaultEquipmentDao() {
		return this.faultEquipmentDao;
	}
 	
	public void setFaultEquipmentDao(FaultEquipmentDao faultEquipmentDao) {
		this.faultEquipmentDao = faultEquipmentDao;
	}
 	
    public List getFaultEquipments() {
    	return faultEquipmentDao.getFaultEquipments();
    }
    
    public FaultEquipment getFaultEquipment(final String id) {
    	return faultEquipmentDao.getFaultEquipment(id);
    }
    
    public void saveFaultEquipment(FaultEquipment faultEquipment) {
    	faultEquipmentDao.saveFaultEquipment(faultEquipment);
    }
    
    public void removeFaultEquipment(final String id) {
    	faultEquipmentDao.removeFaultEquipment(id);
    }
    
    public Map getFaultEquipments(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return faultEquipmentDao.getFaultEquipments(curPage, pageSize, whereStr);
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
		List list = faultEquipmentDao.getFaultSequenceNo();
	
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
	public String getSearchCondition(FaultEquipmentForm faultEquipmentForm){
		//拼SQL条件字串
		StringBuffer conditionStr = new StringBuffer();
		if (faultEquipmentForm.getTitle() != null &&!faultEquipmentForm.getTitle().equals("")) { // 主题查询条件
            conditionStr.append(" AND faultEquipment.title like('%").append(faultEquipmentForm.getTitle() + "%') ");
        }
        if (faultEquipmentForm.getGreffier()!=null &&!faultEquipmentForm.getGreffier().equals("")&&!faultEquipmentForm.getGreffier().equals("-1")) { // 故障记录人查询条件
            conditionStr.append(" AND faultEquipment.greffier='").append(faultEquipmentForm.getGreffier() + "' ");
        }
        if (faultEquipmentForm.getRoomId()!=null&&!faultEquipmentForm.getRoomId().equals("")) { // 机房查询条件
            conditionStr.append(" AND faultEquipment.roomId=").append(faultEquipmentForm.getRoomId() + " ");
        }
        if (faultEquipmentForm.getFromBeginTime() != null &&!faultEquipmentForm.getFromBeginTime().equals("")) { // 故障开始时间查询条件
            conditionStr.append(" AND faultEquipment.beginTime>= '").append(faultEquipmentForm.getFromBeginTime() + "' ");
        }
        if (faultEquipmentForm.getToBeginTime() != null &&!faultEquipmentForm.getToBeginTime().equals("")) { // 故障开始时间查询条件
            conditionStr.append(" AND faultEquipment.beginTime<= '").append(faultEquipmentForm.getToBeginTime() + "' ");
        }
        if (faultEquipmentForm.getFilialeId()!=null&&!faultEquipmentForm.getFilialeId().equals("-1")&&!faultEquipmentForm.getFilialeId().equals("")) { // 公司查询条件
            conditionStr.append(" AND faultEquipment.filialeId=").append(faultEquipmentForm.getFilialeId() + " ");
        }
        if (faultEquipmentForm.getEquipmentCorpId() != null &&!faultEquipmentForm.getEquipmentCorpId().equals("")&&!faultEquipmentForm.getEquipmentCorpId().equals("-1")) { // 设备厂商查询条件
            conditionStr.append(" AND faultEquipment.equipmentCorpId=").append(faultEquipmentForm.getEquipmentCorpId() + " ");
        }
        if (faultEquipmentForm.getStation() != null &&!faultEquipmentForm.getStation().equals("")) { // 故障现象查询条件
            conditionStr.append(" AND faultEquipment.station like('%").append(faultEquipmentForm.getStation() + "%') ");
        }
        if (faultEquipmentForm.getSerialNos() != null &&!faultEquipmentForm.getSerialNos().equals("")) { // 重要故障上报工单号查询条件
            conditionStr.append(" AND faultEquipment.serialNos like('%").append(faultEquipmentForm.getSerialNos() + "%') ");
        }
        if (faultEquipmentForm.getAppEffect() != null &&!faultEquipmentForm.getAppEffect().equals("")) { // 影响业务情况查询条件
            conditionStr.append(" AND faultEquipment.appEffect like('%").append(faultEquipmentForm.getAppEffect() + "%') ");
        }
		
		return conditionStr.toString();
	}
	
    /**
     * 获取故障记录数量
     * @return String 数量
     */
     public String getNum(String condition){
    	 return faultEquipmentDao.getNum(condition);
     }
	
}