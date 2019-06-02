package com.boco.eoms.km.expert.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertGroup;

/**
 * <p>
 * Title:基本信息
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public interface KmExpertGroupMgr {

	/**
	 * 根据主键查询专家团队基本信息
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmExpertGroup getKmExpertGroup(final String id);

	public KmExpertGroup getKmExpertGroupAndGroupMember(final String id);

	/**
	 * 根据专家团队主键查询专家团队组员信息
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public List getKmExpertGroupUsersByGroupId(final String groupId);

	/**
	 * 保存基本信息
	 * 
	 * @param kmExpertBasic
	 *            基本信息
	 */
	public void saveKmExpertGroup(KmExpertGroup kmExpertGroup);

	public void removeKmExpertGroup(final String id);

	/**
	 * 根据条件分页查询教育背景
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回教育背景的分页列表
	 */
	public Map getKmExpertGroups(final Integer curPage, final Integer pageSize,
			final String whereStr);
}