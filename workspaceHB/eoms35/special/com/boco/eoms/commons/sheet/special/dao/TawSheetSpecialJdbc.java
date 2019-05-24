package com.boco.eoms.commons.sheet.special.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;

/**
 *
 *panlong
 *下午04:41:34
 */
public interface TawSheetSpecialJdbc extends Dao {

	/*
     * 得到最大的专业ID
     */
	public String getMaxSpecialId(String paraspeid,int len);
	
	/**
	 * 保存专业与用户关联
	 * @param speid
	 * @param userid
	 * @return
	 */
	public void saveSpecialRefUser(String speid,String userid);
	/**
	 * 查询专业用户表是否已经存在
	 * @param speid
	 * @return
	 */
	public boolean isExitsSpecialRefUser(String speid);
	
	/**
	 * 删除某专业对应的专业专家记录
	 * @param speid
	 */
	public void removeSpecialRefUser(String speid);
	
	/**
	 * 删除某专业已经子专业
	 * @param speid
	 */
	public void removeTawSheetSpecial(String speid);
	
	/**
	 * 得到该专业的所有专家
	 * @param speid
	 * @return
	 */	
	public List getSpecialRefUserList(String speid);
}

