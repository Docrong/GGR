package com.boco.eoms.sheet.supervisetask.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.commontask.model.CommonTaskMain;
import com.boco.eoms.sheet.listedregulation.model.ListedRegulationMain;
import com.boco.eoms.sheet.listedregulation.model.ListedRegulationTask;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskRecord;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskRule;

public interface SuperviseTaskDao extends IMainDAO {

	public Map supervisetaskRuleList( Integer curPage,  Integer pageSize, Map maptj);
	public Map supervisetaskRecordList( Integer curPage,  Integer pageSize, Map maptj);
	
	public void savesupervisetaskRule(SuperviseTaskRule t);
	public void savesupervisetaskRecord(SuperviseTaskRecord t);
	
	public SuperviseTaskRecord getSuperviseTaskRecordById(String id);
	public SuperviseTaskRule getSuperviseTaskRuleById(String id);
	
	public ListedRegulationMain getListedRegulationMainById( String id);
	public ListedRegulationMain getListedRegulationMainBySheetId(String sheetId);
	public ListedRegulationTask getListedRegulationTaskById( String id);
	public CommonTaskMain getCommonTaskMainById(String id);	
	
	public SuperviseTaskRecord getSuperviseTaskRecordBySheetId( String sheetId);
	
	public Map supervisetaskRuleList2( Integer curPage,  Integer pageSize, Map maptj);
	
	public Map getBoardDetail2( Integer curPage,  Integer pageSize, Map maptj)throws Exception;
}
