package com.boco.eoms.km.log.mgr.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.km.log.dao.KmOperateDateLogDao;
import com.boco.eoms.km.log.mgr.KmOperateDateLogMgr;
import com.boco.eoms.km.log.model.KmOperateDateLog;
import com.boco.eoms.km.log.util.KmOperateDefine;
import com.boco.eoms.km.statics.mgr.KmOperateScoreMgr;
import com.boco.eoms.km.statics.model.KmOperateScore;

/**
 * <p>
 * Title:知识操作日统计表
 * </p>
 * <p>
 * Description:知识操作日统计表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class KmOperateDateLogMgrImpl implements KmOperateDateLogMgr {
	
	private KmOperateDateLogDao  kmOperateDateLogDao;
 	
	public KmOperateDateLogDao getKmOperateDateLogDao() {
		return this.kmOperateDateLogDao;
	}
 	
	public void setKmOperateDateLogDao(KmOperateDateLogDao kmOperateDateLogDao) {
		this.kmOperateDateLogDao = kmOperateDateLogDao;
	}
 	
	private KmOperateScoreMgr kmOperateScoreMgr;
	
    public KmOperateScoreMgr getKmOperateScoreMgr() {
		return kmOperateScoreMgr;
	}

	public void setKmOperateScoreMgr(KmOperateScoreMgr kmOperateScoreMgr) {
		this.kmOperateScoreMgr = kmOperateScoreMgr;
	}
	
	public List getKmOperateDateLogs() {
    	return kmOperateDateLogDao.getKmOperateDateLogs();
    }
    
    public KmOperateDateLog getKmOperateDateLog(final String id) {
    	return kmOperateDateLogDao.getKmOperateDateLog(id);
    }
    
    /**
     * 根据日期,人取日志记录
     * 
     */
    public KmOperateDateLog getKmOperateDateLog(final Date date, final String operateUser){
    	return kmOperateDateLogDao.getKmOperateDateLog(date, operateUser);
    }
    
    public void saveKmOperateDateLog(KmOperateDateLog kmOperateDateLog) {
    	kmOperateDateLogDao.saveKmOperateDateLog(kmOperateDateLog);
    }
    
    public void removeKmOperateDateLog(final String id) {
    	kmOperateDateLogDao.removeKmOperateDateLog(id);
    }
    
    public Map getKmOperateDateLogs(final Integer curPage, final Integer pageSize, final String whereStr) {
		return kmOperateDateLogDao.getKmOperateDateLogs(curPage, pageSize, whereStr);
	}
    
    /**
     * 添加操作日志提口,提供给其他模块操作
     * @param: date 操作日期
     * @param: operateId 操作ID
     * @param: operateUser 操作人
     * @param: operateDept 操作人部门
     * 
     * 注: operateId操作ID 包括:
     * 		1: add			新增
     * 		2: modify		修改
     * 		3: over			失效
    	 	4: delete		删除
    	 	5: up			上传
    	 	6: down			下载
    	 	7: use			引用
    	 	8: opinion		评论
    	 	9: advice		提出修改见议
    	 	10: auditOk		审核通过
    	 	11: auditBack	审核驳回
     */
    
    public void saveKmOperateDateLog(final Date date, final Integer operateId, final String operateUser, final String operateDept){
    	//如果输入的 operateId 为 null，则不进行日志记录
    	if(operateId == null){
    		return;
    	}

   	    //根据 opearteId 获取操作属性
    	int operateInt = operateId.intValue();
   		String operate = KmOperateDefine.getKmOperatePropertyName(operateInt);
   		
   		//如果 opearteId 没有对应操作属性，则不进行日志记录
   		if(operate == null){
   			return;
   		}

    	//判断当天用户是否已经有日操作记录
    	KmOperateDateLog kmOperateDateLog = this.getKmOperateDateLog(date, operateUser);   	
    	if(kmOperateDateLog == null){
    		kmOperateDateLog = new KmOperateDateLog();
    	}

   		//操作次数method
   		Method methodOfSetCount = null;
   		Method methodOfGetCount = null;
   		
   		//生成kmOperateScore对象的class,以反射方式注入属性值
   		Class kmOperateDateLogClass = kmOperateDateLog.getClass(); 
			
   		try{  			
   			methodOfSetCount = kmOperateDateLogClass.getDeclaredMethod("set"+operate.substring(0,1).toUpperCase()+operate.substring(1)+"Count", new Class[]{Integer.class});
   			methodOfGetCount = kmOperateDateLogClass.getDeclaredMethod("get"+operate.substring(0,1).toUpperCase()+operate.substring(1)+"Count", new Class[]{});   			
   			//取以前的操作次数
   			int oldCount = StaticMethod.nullObject2int(methodOfGetCount.invoke(kmOperateDateLog, new Object[]{}));   			
   			//操作次数 + 1
			Object[] objCount = {new Integer(oldCount+1)};
			//设置新的操作次数
   			methodOfSetCount.invoke(kmOperateDateLog, objCount);
  		}
   		catch(NoSuchMethodException e){
   			e.printStackTrace();
   		} 
   		catch (IllegalAccessException e){
   			e.printStackTrace();
		}
   		catch (InvocationTargetException e){
   			e.printStackTrace();
		}

  		//检索此操作对应的积分定义
   		KmOperateScore kmOperateScore = this.kmOperateScoreMgr.getKmOperateScore(operateId);
   		
   		if(kmOperateScore != null){
   			//取操作的积分
   			int operateScore = StaticMethod.nullObject2int(kmOperateScore.getScore());

   			if(operateScore != 0){	
   	   	   		//操作分数method
   	   	   		Method methodOfSetScore = null;
   	   	   		Method methodOfGetScore = null;

   	   	   		try{  			
   	    			methodOfSetScore = kmOperateDateLogClass.getDeclaredMethod("set"+operate.substring(0,1).toUpperCase()+operate.substring(1)+"Score", new Class[]{Integer.class});
   	    			methodOfGetScore = kmOperateDateLogClass.getDeclaredMethod("get"+operate.substring(0,1).toUpperCase()+operate.substring(1)+"Score", new Class[]{});
   	     	   	   	//取以前的操作积分
   	   	   			int oldScore = StaticMethod.nullObject2int(methodOfGetScore.invoke(kmOperateDateLog, new Object[]{}));
   	   	   			//原来的操作积分 + 本次的操作积分
   	   				Object[] objScore = {new Integer(oldScore + operateScore)};
   	   				//设置新的操作积分
   	   				methodOfSetScore.invoke(kmOperateDateLog, objScore);
   	   	  		}
   	   	   		catch(NoSuchMethodException e){
   	   	   			e.printStackTrace();
   	   	   		} 
   	   	   		catch (IllegalAccessException e){
   	   	   			e.printStackTrace();
   	   			}
   	   	   		catch (InvocationTargetException e){
   	   	   			e.printStackTrace();
   	   			}     				
   			} 	   		
   		}

   		//注入用户,部门,日期,操作编号
   		kmOperateDateLog.setOperateUser(operateUser);
   		kmOperateDateLog.setOperateDept(operateDept);
   		kmOperateDateLog.setOperateDate(date);
   		kmOperateDateLog.setOperateId(operateId);

   		// 保存对象
    	this.saveKmOperateDateLog(kmOperateDateLog);
    }
    
    
	/**
	 * 根据条件分页查询知识操作日统计表提供给其他统计模块
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param sqlStr 执行sql
	 * @return 返回要求数据的分页列表
	 * 
	 */
	public Map getKmOperateDateLogsForStatistic(final Integer curPage, final Integer pageSize, final String sqlStr){
		return this.kmOperateDateLogDao.getKmOperateDateLogsForStatistic(curPage, pageSize, sqlStr);
	}
    
}