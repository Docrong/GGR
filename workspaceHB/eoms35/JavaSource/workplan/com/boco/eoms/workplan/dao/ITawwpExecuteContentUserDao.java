package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteContentUser;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 9:16:36 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpExecuteContentUserDao {
	/**
	   * 保存执行作业计划执行内容(个人)
	   * @param _tawwpExecuteContentUser TawwpExecuteContentUser 执行作业计划执行内容(个人)类
	   * @ 操作异常
	   */
	  public void saveExecuteContentUser(TawwpExecuteContentUser  _tawwpExecuteContentUser) ;

	  /**
	   * 删除执行作业计划执行内容(个人)
	   * @param _tawwpExecuteContentUser TawwpExecuteContentUser 执行作业计划执行内容(个人)类
	   * @ 操作异常
	   */
	  public void deleteExecuteContentUser(TawwpExecuteContentUser _tawwpExecuteContentUser) ;
	  /**
	   * 修改执行作业计划执行内容(个人)
	   * @param _tawwpExecuteContentUser TawwpExecuteContentUser 执行作业计划执行内容(个人)类
	   * @ 操作异常
	   */
	  public void updateExecuteContentUser(TawwpExecuteContentUser _tawwpExecuteContentUser) ;

	  /**
	   * 查询执行作业计划执行内容(个人)信息
	   * @param id String 执行作业计划执行内容(个人)标识
	   * @ 操作异常
	   * @return TawwpExecuteContentUser 执行作业计划执行内容(个人)类
	   */
	  public TawwpExecuteContentUser loadExecuteContentUser(String id) ;

	  /**
	   * 查询所有执行作业计划执行内容(个人)信息
	   * @ 操作异常
	   * @return List 执行作业计划执行内容(个人)类列表
	   */
	  public List listExecuteContentUser() ;
	  /**
	   * 获取由指定用户添加的执行作业计划执行内容(单一用户)
	   * @param _userId String 用户登录名
	   * @param _tawwpExecuteContent TawwpExecuteContent 执行作业计划执行内容(整体)对象
	   * @ 异常
	   * @return TawwpExecuteContentUser 执行作业计划执行内容(单一用户)对象
	   */
	  public TawwpExecuteContentUser filterTawwpExecuteContentUser(String _userId,
	                                                  TawwpExecuteContent
	                                                  _tawwpExecuteContent)  ;

	public List listExeConUserforAddons(String modelplanid, String cruser,
			String formid, String startDate, String endDate);
	  
}