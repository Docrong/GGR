package com.boco.eoms.sheet.branchindexreduction.service.impl;

import java.util.List;

import com.boco.eoms.sheet.branchindexreduction.dao.ISubtractTempTableDao;
import com.boco.eoms.sheet.branchindexreduction.model.SubtractTempTable;
import com.boco.eoms.sheet.branchindexreduction.service.ISubtractTempTableMgr;

public class SubtractTempTableMgrImpl implements ISubtractTempTableMgr{
	private ISubtractTempTableDao subtractTempTableDao;
	

	public ISubtractTempTableDao getSubtractTempTableDao() {
		return subtractTempTableDao;
	}

	public void setSubtractTempTableDao(ISubtractTempTableDao subtractTempTableDao) {
		this.subtractTempTableDao = subtractTempTableDao;
	}

	public List getInvalidTables(final String serialKey,String sheetId) {
		// TODO Auto-generated method stub
		return subtractTempTableDao.getInvalidTables(serialKey,sheetId);
	}

	public List getRepeatedTables(final String serialKey) {
		// TODO Auto-generated method stub
		return subtractTempTableDao.getRepeatedTables(serialKey);
	}

	public List getSubtractTempTablesByCondition(String condition) {
		// TODO Auto-generated method stub
		return subtractTempTableDao.getSubtractTempTablesByCondition(condition);
	}

	public List getUnCompletedTables(final String serialKey) {
		// TODO Auto-generated method stub
		return subtractTempTableDao.getUnCompletedTables(serialKey);
	}

	public void removeSubtractTable(String id) {
		// TODO Auto-generated method stub
		subtractTempTableDao.removeSubtractTempTable(id);
	}

	public void removeSubtractTable(String[] ids) {
		// TODO Auto-generated method stub
		for(int index = 0;index<ids.length;index++){
			subtractTempTableDao.removeSubtractTempTable(ids[index]);
		}
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.branchindexreduction.service.ISubtractTempTableMgr#saveSubtractTableBatch(java.util.List)
	 */
	public void saveSubtractTableBatch(List substractTempTable) {
		// TODO Auto-generated method stub
		if(substractTempTable != null && substractTempTable.size() > 0 ){
			for(int index = 0;index<substractTempTable.size();index++){
				SubtractTempTable item = (SubtractTempTable)substractTempTable.get(index);
				subtractTempTableDao.saveSubtractTempTable(item);
			}
		}
	}

	public void saveSubtractTable(SubtractTempTable substractTempTable) {
		// TODO Auto-generated method stub
		subtractTempTableDao.saveSubtractTempTable(substractTempTable);
	}

	public List getOmitTables(String serialKey,String sheetId) {
		// TODO Auto-generated method stub
		return subtractTempTableDao.getOmitTables(serialKey,sheetId);
	}

	public void removeBySerialKey(String serialKey) {
		// TODO Auto-generated method stub
		subtractTempTableDao.delBySerialKey(serialKey);
	}

	public void actionForContinue(String serialKey,String sheetId) {
		// TODO Auto-generated method stub
		//1.删除工单号不存在的数据
		subtractTempTableDao.delForInvalidTables(serialKey, sheetId);
		//2.删除重复数据保留最后一条
		subtractTempTableDao.delForRepeatTables(serialKey);
		//3.更新不符合规范的数据
		subtractTempTableDao.updateForUncompletedTables(serialKey);
		//4.遗漏的工单处理
		subtractTempTableDao.updateForOmitTables(serialKey,sheetId);
		//5.更新主表信息
		subtractTempTableDao.updateMainTable(serialKey);
		//6.删除临时表信息
		subtractTempTableDao.delBySerialKey(serialKey);
	}
	
	public void actionForCOmmon(String serialKey){
		subtractTempTableDao.updateMainTable(serialKey);
	}

	public List getEmptyTables(String serialKey) {
		// TODO Auto-generated method stub
		List emptyTables = subtractTempTableDao.getSubtractTempTablesByCondition(" where 1=1 and serialKey='"+serialKey+"' and (remark is null or remark = '') ");
		return emptyTables;
	}
}

