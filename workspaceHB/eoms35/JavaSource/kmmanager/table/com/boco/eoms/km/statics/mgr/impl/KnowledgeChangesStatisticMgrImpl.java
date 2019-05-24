package com.boco.eoms.km.statics.mgr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.statics.dao.KnowledgeChangesStatisticDao;
import com.boco.eoms.km.statics.mgr.KnowledgeChangesStatisticMgr;
import com.boco.eoms.km.statics.model.KnowledgeChangesStatistic;

/**
 * <p>
 * Title:知识库知识变更情况统计表
 * </p>
 * <p>
 * Description:知识库知识变更情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class KnowledgeChangesStatisticMgrImpl implements KnowledgeChangesStatisticMgr {
 
	//调用知识操作日统计表接口,读取所需数据
	private KnowledgeChangesStatisticDao knowledgeChangesStatisticDao;

	public KnowledgeChangesStatisticDao getKnowledgeChangesStatisticDao() {
		return knowledgeChangesStatisticDao;
	}

	public void setKnowledgeChangesStatisticDao(
			KnowledgeChangesStatisticDao knowledgeChangesStatisticDao) {
		this.knowledgeChangesStatisticDao = knowledgeChangesStatisticDao;
	}
	
	/**
	 * 根据条件分页查询知识库知识变更情况统计表
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @return 返回知识库知识变更情况统计表的分页列表
	 */
	public Map getKnowledgeChangesStatistics(final Integer curPage, final Integer pageSize) {
    	StringBuffer sql = new StringBuffer("select distinct t.table_id, " +
    			"p.operate_id, count(t.operate_id) " +
    			"from km_operate_log t,km_operate_score p where t.operate_id = p.id group by t.table_id,p.operate_id");										
    	System.out.println("sql = "+sql);
    	
    	 //调用知识操作日统计表接口,取日志统计
    	 // @param curPage: 当前页码
    	 // @param pageSize: 每页记录数
    	 // @param sql: 执行sql
		//Map map = this.kmOperateDateLogMgr.getKmOperateDateLogsForStatistic(curPage, pageSize, sql.toString());
    	
    	Map map = this.knowledgeChangesStatisticDao.getKnowledgeChangesStatistics(curPage, pageSize);
		
		//遍历取回数据集合,封装以List<PersonalAuditStatistic>中
		List list = (List)map.get("result");
		List list_result = new ArrayList();
		for (int i = 0; i < list.size(); i++){
			Object [] objs = (Object[])list.get(i);
			KnowledgeChangesStatistic knowledgeChangesStatistic = new KnowledgeChangesStatistic();
			//取得tableId 
			String tableId = objs[0]!=null?objs[0]+"":"";
			//TODO 需调用知识管理接口 根据tableId得到知识库名及库维护人,添充下面两个属性.
			knowledgeChangesStatistic.setBaseName("");
			knowledgeChangesStatistic.setUserName("");
			
			//根据操作类型添充其他属性
			int operateId = objs[1]!=null?Integer.parseInt(objs[1]+""):0;
			switch (operateId){
			case 1: {
				knowledgeChangesStatistic.setAddCount(objs[2] != null ? (Integer)objs[2] : new Integer(0));break;
			}
			case 2: {
				knowledgeChangesStatistic.setModifyCount(objs[2] != null ? (Integer)objs[2] : new Integer(0));break;
			}
			case 3: {
				knowledgeChangesStatistic.setOverCount(objs[2] != null ? (Integer)objs[2] : new Integer(0));break;
			}
			case 4: {
				knowledgeChangesStatistic.setDeleteCount(objs[2] != null ? (Integer)objs[2] : new Integer(0));break;
			}
			
			}
			list_result.add(knowledgeChangesStatistic);
			
		}
		map.put("result", list_result);
		
		 //返回结果
		return map;
	}
	
}