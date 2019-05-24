package com.boco.eoms.commons.hongxun.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;

public interface TawCommonHongXunDao extends Dao{
/**
 * 添加新用户
 * @param com_name
 * @param xiaozhu_name
 * @param name
 * @param tel
 * @param zhize
 * @param remark
 */	
	public void insertRedUser(String com_name, String xiaozhu_name, String name,
            String tel, String zhize, String remark);
	public List getRedUser(String com_name, String xiaozhu_name, String name,
			String tel, String zhize, String remark);
	
	public List getOneRedUser(String id);
	public void redDoUpdate(String id, String com_name, String xiaozu_name,
			String name, String tel, String zhize, String remark);
	public void redDel(String id);
	public List getTwoRedUser(String com_name, String xiaozhu_name,
			String name, String tel, String zhize, String remark, String model);
	public List getThreeRedUser(String com_name, String xiaozhu_name,
			String name, String tel, String zhize, String remark, String model);
	public void insertCom(String com_name);
	public List getComList();
	public List getXiaozuList();
	public void insertXiaozu(String xiaozu_name);
	public List getComAll();
	public List getXiaozuAll();
	public String getDictName(String dictId);
	
}
