package com.boco.eoms.duty.bo;

import java.sql.SQLException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.bo.BO;
import com.boco.eoms.duty.model.TawRmReliefRecord;
import com.boco.eoms.duty.service.ITawRmReliefRecordManager;

public class TawRmReliefRecordBO extends BO {
	ITawRmReliefRecordManager manager=null;
	public TawRmReliefRecordBO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}
	
	/**
	 * @see 插入交接班情况
	 * @param handoverId  交班人员
	 * @param successorId 接班人员
	 * @param roomId      机房id
	 * @param time		  交接班时间
	 * @param lbProblem   遗留问题
	 * @param nuiteFlag   日志是否合并
	 * @param createTime  创建时间，取当前
	 * @throws SQLException
	 */
	
	public void insertReliefRecord(String handoverId,String successorId,String roomId,String time,String lbProblem,String nuiteFlag,String userId,String workSerial,String createTime){
		manager=(ITawRmReliefRecordManager)ApplicationContextHolder.getInstance().getBean("ITawRmReliefRecordManager");
		TawRmReliefRecord tawRmReliefRecord=new TawRmReliefRecord();
		tawRmReliefRecord.setHandoverId(handoverId);
		tawRmReliefRecord.setSuccessorId(successorId);
		tawRmReliefRecord.setRoomId(roomId);
		tawRmReliefRecord.setTime(time);
		tawRmReliefRecord.setLbProblem(lbProblem);
		tawRmReliefRecord.setNuiteFlag(nuiteFlag);
		tawRmReliefRecord.setUserId(userId);
		tawRmReliefRecord.setWorkSerial(workSerial);
		tawRmReliefRecord.setCreateTime(createTime);
		manager.saveTawRmReliefRecord(tawRmReliefRecord);
	}
}
