package com.boco.eoms.km.exam.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamAttend;

/**
 * <p>
 * Title:考试信息
 * </p>
 * <p>
 * Description:考试信息
 * </p>
 * <p>
 * Mon May 11 10:55:38 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
 public interface KmExamAttendMgr {
 
	/**
	* 根据试卷id和参加考试人信息得到唯一考试信息
	* @param testID
	* @param attendUser
	* @return
	*/
	public KmExamAttend getKmExamAttend(final String testID,final String attendUser);
	
	/**
	 * 查询当前人参加考试的发布结果信息
	 * @param attendUser
	 * @return
	 */
	public List getKmExamAttends(final String attendUser,final String isPublic);
	
	/**
	 *
	 * 取考试信息 列表
	 * @return 返回考试信息列表
	 */
	public List getKmExamAttends();
    
	/**
	 * 根据主键查询考试信息
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public KmExamAttend getKmExamAttend(final String id);
    
	 public void saveKmExamAttend(final Map map);
	
	/**
	 * 保存考试信息
	 * @param kmExamAttend 考试信息
	 */
	public void saveKmExamAttend(KmExamAttend kmExamAttend);
    
	/**
	 * 根据主键删除考试信息
	 * @param id 主键
	 */
	public void removeKmExamAttend(final String id);
    
	/**
	 * 根据条件分页查询考试信息
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回考试信息的分页列表
	 */
	public Map getKmExamAttends(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}