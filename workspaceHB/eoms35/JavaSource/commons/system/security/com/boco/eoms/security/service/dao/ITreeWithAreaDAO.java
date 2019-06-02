package com.boco.eoms.security.service.dao;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.security.service.model.TreeWithArea;

public interface ITreeWithAreaDAO {

	public void insert(TreeWithArea treeWithArea);

	public void logicDelete(String areaId);

	public void physicsDelete(String areaId);

	public List selectByAreaId(String areaId, int deleted);

	public List selectByParentareaid(String parentareaid, int deleted);

	public List selectByNotes(String notes, int deleted);

	public List selectByAreaName(String areaName, int deleted);
	
	public List selectAllArea(int deleted);
	
	 public String getMaxSubAreaid(String parentAreaId);

}
