package com.boco.eoms.workplan.mgr;

/**
 * <p>Title: 作业计划任务管理（智能巡检系统）接口业务逻辑</p>
 * <p>Description: 作业计划任务管理业务逻辑实现，包括网元获取，任务获取，执行内容获取</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.util.List;

import org.w3c.dom.Document;

import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteContentUser;
import com.boco.eoms.workplan.model.TawwpNet;

public interface ITawwpTaskManageInterfaceMgr {

	/**
	 * 获取接口传递的网元信息文件，通过分析，将网元信息导入到网元维护表中
	 * 
	 * @return boolean 操作是否完成 true：完成 false：失败
	 */
	public boolean saveNetInfor();

	/**
	 * 获取接口传递的任务信息文件，通过分析，将网元信息导入到网元维护表中
	 * 
	 * @param _netId
	 *            String 网元标识
	 * @return boolean 操作是否完成 true：完成 false：失败
	 */
	public List getTaskInfor(String _netId);

	/**
	 * 获取接口传递的执行信息文件，通过分析，获取TawwpExecuteContentUser
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 执行内容
	 * @param _tawwpNet
	 *            TawwpNet 网元
	 * @param _date
	 *            String 日期
	 * @return TawwpExecuteContentUser 保存的作业计划执行内容
	 */
	public TawwpExecuteContentUser saveExecuteInfor(
			TawwpExecuteContent _tawwpExecuteContent, TawwpNet _tawwpNet,
			String _date);

	/**
	 * 查询当前时间段内容是否有需要调用接口的执行内容，如果有循环调用接口信息，<br>
	 * 保存执行信息,并根据信息进行合格判断。如果不合格,则发送信息给执行人员
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _endDate
	 *            String 结束时间
	 * @return boolean 操作结果
	 */
	public boolean getExecuteInfor(String _startDate, String _endDate);

	// ext

	public String saveExecuteInfor(String _command, TawwpNet _tawwpNet,
			String _startDate);

	/**
	 * saveExecuteInfor
	 * 
	 * @param _document
	 *            Document
	 * @param _executePlanId
	 *            String
	 * @return TawwpExecuteContentUser
	 */
	public TawwpExecuteContentUser saveExecuteInfor(Document _document,
			String _executePlanId);

	public org.w3c.dom.Document getExecuteInfor(String _command,
			TawwpNet _tawwpNet, String _startDate);

	public boolean getExecuteInfor(String _endDate);

	/**
	 * 根据网元序列号获得网元下的元任务id
	 * 
	 * @param _netserialno
	 * @return
	 */
	public List getTaskByNet(String _netserialno);
}
