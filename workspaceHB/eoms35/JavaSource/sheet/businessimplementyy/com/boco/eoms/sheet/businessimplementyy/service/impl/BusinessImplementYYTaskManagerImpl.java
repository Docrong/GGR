
package com.boco.eoms.sheet.businessimplementyy.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.impl.TaskServiceImpl;
//import com.boco.eoms.sheet.base.util.QuerySqlInit;
import com.boco.eoms.sheet.businessimplementyy.dao.IBusinessImplementYYTaskDAO;
import com.boco.eoms.sheet.businessimplementyy.service.IBusinessImplementYYTaskManager;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultMainDAO;

public class BusinessImplementYYTaskManagerImpl extends TaskServiceImpl implements  IBusinessImplementYYTaskManager{
	/**
	 * 获取本角色的已被组内其他人员抢单但未处理的工单
	 * 
	 * @param userId  用户ID
	 * @param startIndex
	 * @param length
	 * @author wangjianhua
	 * @date 2009-08-24
	 * @province 甘肃
	 * @return
	 * @throws Exception
	 */
//	public HashMap getAcceptTaskByRole(Map condition, String userId, Integer startIndex, Integer length) throws Exception{
//		HashMap taskMap=new HashMap();
//		String orderCondition = (String)condition.get("orderCondition");	
//		condition.put("userId", userId);
//		String sql = QuerySqlInit.getAcceptTaskByRole(condition);
//		StringBuffer hql = new StringBuffer();
//		if(!orderCondition.equals("")&&orderCondition!=null){
//			hql.append(sql).append(" order by "+orderCondition);
//		}
//		else{ 
//			hql.append(sql).append(" order by task.createTime desc");
//		}
//		
//		taskMap=this.getTaskDAO().getTaskListByCondition(hql.toString(),startIndex,length);
//		return taskMap;
//	}

	public Integer getCountOfBrother(String sheetKey, String parentLevelId) throws SheetException {
		IBusinessImplementYYTaskDAO ibusinessimplementyyTaskDAO = (IBusinessImplementYYTaskDAO)this.getTaskDAO();	
		
		Integer count = new Integer(0);
		try {
			count = ibusinessimplementyyTaskDAO.getCountOfBrother(this.getTaskModelObject(), sheetKey, parentLevelId);
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return count;
		}
	
}
