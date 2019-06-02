package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpStubUser;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 1:19:36 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpStubUserDao {
	/**
	 * 保存代理
	 * 
	 * @param _tawwpStubUser
	 *            TawwpStubUser 代理类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveStubUser(TawwpStubUser _tawwpStubUser);

	/**
	 * 删除代理
	 * 
	 * @param _tawwpStubUser
	 *            TawwpStubUser 代理类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteStubUser(TawwpStubUser _tawwpStubUser);

	/**
	 * 修改代理
	 * 
	 * @param _tawwpStubUser
	 *            TawwpStubUser 代理类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateStubUser(TawwpStubUser _tawwpStubUser);

	/**
	 * 查询代理信息
	 * 
	 * @param id
	 *            String 代理标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpStubUser 代理类
	 */
	public TawwpStubUser loadStubUser(String id);

	/**
	 * 查询代理的申请人
	 * 
	 * @param _cruser
	 *            String 申请人
	 * @throws Exception
	 *             操作异常
	 * @return List 代理列表
	 */
	public List loadStubUserByCrUser(String _cruser, String _currentDate);
	
	/**
	 * 查询代理的申请人
	 * add by gongyufeng
	 * @param _cruser
	 *            String 申请人
	 * @throws Exception
	 *             操作异常
	 * @return List 代理列表
	 */
	public List loadStubUserByCrUser(String _cruser, String _currentDate,String stubuser,String state);

	/**
	 * 查询代理的代理人
	 * 
	 * @param _stubuser
	 *            String 代理人
	 * @param _currentDate
	 *            String 当前时间
	 * @throws Exception
	 *             操作异常
	 * @return List 代理列表
	 */
	public List loadStubUserByStubUser(String _stubuser, String _currentDate);
	
	/**
	 * 查询代理的代理人
	 * add by gongyufeng for search
	 * @param _stubuser
	 *            String 代理人
	 * @param _currentDate
	 *            String 当前时间
	 * @throws Exception
	 *             操作异常
	 * @return List 代理列表
	 */
	public List loadStubUserByStubUser(String _cruser, String _currentDate,String stubuser,String state);

	/**
	 * 查询代理的审批人
	 * 
	 * @param _checkuser
	 *            String 审批人
	 * @throws Exception
	 *             操作异常
	 * @return List 代理列表
	 */
	public List loadStubUserByCheckUser(String _checkuser);

}
