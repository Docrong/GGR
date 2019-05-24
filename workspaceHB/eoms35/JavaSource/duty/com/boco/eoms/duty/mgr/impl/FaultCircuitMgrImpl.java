package com.boco.eoms.duty.mgr.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.duty.model.FaultCircuit;
import com.boco.eoms.duty.mgr.FaultCircuitMgr;
import com.boco.eoms.duty.webapp.form.FaultCircuitForm;
import com.boco.eoms.duty.dao.FaultCircuitDao;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.commons.system.dict.service.impl.TawSystemDictTypeManagerImpl;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;

/**
 * <p>
 * Title:线路故障记录
 * </p>
 * <p>
 * Description:线路故障记录功能
 * </p>
 * <p>
 * Sun Mar 29 12:55:57 CST 2009
 * </p>
 * @author 李江红
 * @version 3.5
 * 
 */
public class FaultCircuitMgrImpl implements FaultCircuitMgr {
 
	private FaultCircuitDao  faultCircuitDao;
 	
	public FaultCircuitDao getFaultCircuitDao() {
		return this.faultCircuitDao;
	}
 	
	public void setFaultCircuitDao(FaultCircuitDao faultCircuitDao) {
		this.faultCircuitDao = faultCircuitDao;
	}
 	
    public List getFaultCircuits() {
    	return faultCircuitDao.getFaultCircuits();
    }
    
    public FaultCircuit getFaultCircuit(final String id) {
    	return faultCircuitDao.getFaultCircuit(id);
    }
    
    public void saveFaultCircuit(FaultCircuit faultCircuit) {
    	faultCircuitDao.saveFaultCircuit(faultCircuit);
    }
    
    public void removeFaultCircuit(final String id) {
    	faultCircuitDao.removeFaultCircuit(id);
    }
    
    public Map getFaultCircuits(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return faultCircuitDao.getFaultCircuits(curPage, pageSize, whereStr);
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
		List list = faultCircuitDao.getFaultSequenceNo();
	
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
	public String getSearchCondition(FaultCircuitForm faultCircuitForm){
		//拼SQL条件字串
		StringBuffer conditionStr = new StringBuffer();
		if (faultCircuitForm.getTitle() != null &&!faultCircuitForm.getTitle().equals("")) { // 主题查询条件
            conditionStr.append(" AND faultCircuit.title like('%").append(faultCircuitForm.getTitle() + "%') ");
        }
        if (faultCircuitForm.getGreffier()!=null &&!faultCircuitForm.getGreffier().equals("")&&!faultCircuitForm.getGreffier().equals("-1")) { // 故障记录人查询条件
            conditionStr.append(" AND faultCircuit.greffier='").append(faultCircuitForm.getGreffier() + "' ");
        }
        if (faultCircuitForm.getRoomId()!=null&&!faultCircuitForm.getRoomId().equals("")) { // 机房查询条件
            conditionStr.append(" AND faultCircuit.roomId=").append(faultCircuitForm.getRoomId() + " ");
        }
        if (faultCircuitForm.getAgentId()!=null &&!faultCircuitForm.getAgentId().equals("")&&!faultCircuitForm.getAgentId().equals("-1")) { // 代维公司查询条件
            conditionStr.append(" AND faultCircuit.agentId=").append(faultCircuitForm.getAgentId() + " ");
        }
        if (faultCircuitForm.getFilialeId()!=null&&!faultCircuitForm.getFilialeId().equals("-1")&&!faultCircuitForm.getFilialeId().equals("")) { // 公司查询条件
            conditionStr.append(" AND faultCircuit.filialeId=").append(faultCircuitForm.getFilialeId() + " ");
        }
        if (faultCircuitForm.getPropertyRight()!=null&&!faultCircuitForm.getPropertyRight().equals("-1")&&!faultCircuitForm.getPropertyRight().equals("")) { // 光缆产权查询条件
            conditionStr.append(" AND faultCircuit.propertyRight=").append(faultCircuitForm.getPropertyRight() + " ");
        } 
        if (faultCircuitForm.getIsAppEffect()!=null&&!faultCircuitForm.getIsAppEffect().equals("-1")&&!faultCircuitForm.getIsAppEffect().equals("")) { // 是否影响业务查询条件
            conditionStr.append(" AND faultCircuit.isAppEffect=").append(faultCircuitForm.getIsAppEffect() + " ");
        } 
        if (faultCircuitForm.getFromBeginTime() != null &&!faultCircuitForm.getFromBeginTime().equals("")) { // 故障开始时间查询条件
            conditionStr.append(" AND faultCircuit.beginTime>= '").append(faultCircuitForm.getFromBeginTime() + "' ");
        }
        if (faultCircuitForm.getToBeginTime() != null &&!faultCircuitForm.getToBeginTime().equals("")) { // 故障开始时间查询条件
            conditionStr.append(" AND faultCircuit.beginTime<= '").append(faultCircuitForm.getToBeginTime() + "' ");
        }
        if (faultCircuitForm.getTypeId()!=null&&!faultCircuitForm.getTypeId().equals("-1")&&!faultCircuitForm.getTypeId().equals("")) { // 故障类别查询条件 
      	  conditionStr.append(" AND faultCircuit.typeIdList like('%#").append(faultCircuitForm.getTypeId() + "#%') ");
        }
        if (faultCircuitForm.getFromTo() != null &&!faultCircuitForm.getFromTo().equals("")) { // 段落查询条件
            conditionStr.append(" AND faultCircuit.fromTo like('%").append(faultCircuitForm.getFromTo() + "%') ");
        }
        if (faultCircuitForm.getSerialNos() != null &&!faultCircuitForm.getSerialNos().equals("")) { // 工单号查询条件
            conditionStr.append(" AND faultCircuit.serialNos like('%").append(faultCircuitForm.getSerialNos() + "%') ");
        }
        
        if (faultCircuitForm.getAppEffect() != null &&!faultCircuitForm.getAppEffect().equals("")) { // 影响业务情况查询条件
            conditionStr.append(" AND faultCircuit.appEffect like('%").append(faultCircuitForm.getAppEffect() + "%') ");
        }
		
        System.out.println("------线路故障查询条件："+conditionStr.toString());
		return conditionStr.toString();
	}
	
	/**
     * 获取故障记录数量
     * @return String 数量
     */
     public String getNum(String condition){
    	 return faultCircuitDao.getNum(condition);
     }	
}