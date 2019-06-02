package com.boco.eoms.interfaces.province.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IJTSheetInterfaceService {
    /**
     * 根据工单类型和集团工单流水号，查询省工单流水号
     * @param groupSheetId
     * @param sheetType
     * @return
     */
	public String getSheetIdByGroupSheetId(String groupSheetId,String sheetType);
	/**
	 * 新增集中化故障工单
	 * @return
	 */
	public String newCentralCommonfaultSheet(Map opDetailMap)throws Exception;
	/**
	 * 新增通用任务工单
	 * @return
	 */
	public String newCommonTaskSheet(Map opDetailMap,List attachList)throws Exception;
	/**
	 * 保存集团工单跟省工单之间的映射关系
	 * @param groupSheetId
	 * @param provinceSheetId
	 * @param sheetType
	 */
	public void saveSheetIdByGroupSheetId(String groupSheetId,String provinceSheetId,String sheetType);
}
