package com.boco.eoms.workplan.mgr.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.workplan.dao.ITawwpLogDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.mgr.ITawwpLogMgr;
import com.boco.eoms.workplan.model.TawwpLog;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpLogVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 11:29:22 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class TawwpLogMgrImpl implements ITawwpLogMgr {

	private ITawwpLogDao tawwpLogDao;

	/**
	 * @param tawwpLogDao the tawwpLogDao to set
	 */
	public void setTawwpLogDao(ITawwpLogDao tawwpLogDao) {
		this.tawwpLogDao = tawwpLogDao;
	}

	/**
	 * 业务逻辑：增加日志（ADD-LOG-001）
	 * 
	 * @param _cruser
	 *            String 操作人
	 * @param _content
	 *            String 操作内容
	 * @param _logType
	 *            String 操作类型
	 */
	public void addLog(String _cruser, String _content, String _logType) {

		// TawwpLogDAO tawwpLogDAO = new TawwpLogDAO();
		TawwpLog tawwpLog = new TawwpLog();

		try {
			tawwpLog = new TawwpLog(_cruser, TawwpUtil.getCurrentDateTime(),
					_content, _logType); // 封装日志数据对象
			tawwpLogDao.saveLog(tawwpLog); // 保存日志
		} catch (Exception e) {
			e.printStackTrace();
			// TawwpException("作业计划日志保存出现异常");
		}
	}

	/**
	 * 业务逻辑：删除日志（DELETE-LOG-001）
	 * 
	 * @param _id
	 *            String 日志标识
	 */
	public void removeLog(String _id) {

		// TawwpLogDAO tawwpLogDAO = new TawwpLogDAO();
		TawwpLog tawwpLog = null;

		try {
			tawwpLog = tawwpLogDao.loadLog(_id); // 封装日志数据对象
			tawwpLogDao.deleteLog(tawwpLog); // 删除日志
		} catch (Exception e) {
			e.printStackTrace();
			// TawwpException("作业计划日志删除出现异常");
		}
	}

	/**
	 * 业务逻辑：浏览日志（RECEIVE-LOG-001）
	 * 
	 * @param pagePra
	 *            int[] 分页信息
	 * @throws TawwpException
	 *             异常信息
	 * @return List 日志信息列表
	 */
	public List listLog(int[] pagePra) throws TawwpException {
		// TawwpLogDAO tawwpLogDAO = new TawwpLogDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		TawwpLog tawwpLog = null;
		TawwpLogVO tawwpLogVO = null;
		List list = null;
		List newList = new ArrayList();

		try {
			list = tawwpLogDao.listLog(pagePra); // 查询分页日志信息

			// 循环处理日志信息
			for (int i = 0; i < list.size(); i++) {
				tawwpLog = (TawwpLog) list.get(i);
				tawwpLogVO = new TawwpLogVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpLogVO, tawwpLog);

				tawwpLogVO.setCruserName(tawwpUtilDAO.getUserName(tawwpLogVO
						.getCruser()));
				tawwpLogVO.setLogTyepName(TawwpLogVO.getLogTypeName(tawwpLogVO
						.getLogType()));

				newList.add(newList);
			}
			return newList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划日志查询出现异常");
		}
	}

	/**
	 * 业务逻辑：将日志信息按时间段，导出到xml文件中（BACKUP-LOG-001）
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _enddate
	 *            String 结束时间
	 * @throws TawwpException
	 *             异常信息
	 * @return String 文件路径
	 */
	public String exportLog(String _startDate, String _enddate)
			throws TawwpException {

		// TawwpLogDAO tawwpLogDAO = new TawwpLogDAO();
		TawwpLog tawwpLog = null;
		List list = null;

		DataOutputStream output = null;
		StringBuffer str = null;
		String filePath = null;

		try {
			list = tawwpLogDao.searchLog(_startDate, _enddate); // 查询日志信息

			// 如果存在日志信息
			if (list.size() > 0) {
				str = new StringBuffer();
				filePath = TawwpLogVO.LOGPATH
						+ str
								.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>\n");
				str.append("<VALUES>\n");

				for (int i = 0; i < list.size(); i++) {
					tawwpLog = (TawwpLog) list.get(i);
					str.append(tawwpLog.writeXML()); // 获取日志信息xml
				}

				str.append("</VALUES>");
				output = new DataOutputStream(new FileOutputStream(new File(
						filePath))); // 按指定路径生成xml文件
				output.writeBytes(new String(str.toString().getBytes("GB2312"),
						"ISO8859-1")); // 进行编码处理
				output.close(); // 关闭输出流
			}

			return filePath;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划日志导出出现异常");
		}
	}

	/**
	 * 查询日志信息
	 * 
	 * @param _mapQuery
	 *            Map 日志查询条件
	 * @throws TawwpException
	 *             异常信息
	 * @return List 日志信息列表
	 */
	public List searchLog(Map _mapQuery) throws TawwpException {
		// TawwpLogDAO tawwpLogDAO = new TawwpLogDAO();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		List list = null;
		List newList = null;
		TawwpLog tawwpLog = null;
		TawwpLogVO tawwpLogVO = null;

		try {
			list = tawwpLogDao.searchLog(_mapQuery); // 查询符合条件的网元
			newList = new ArrayList();

			for (int i = 0; i < list.size(); i++) {
				tawwpLog = (TawwpLog) list.get(i);
				tawwpLogVO = new TawwpLogVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpLogVO, tawwpLog); // 日志数据转换
				tawwpLogVO.setCruserName(tawwpUtilDAO.getUserName(tawwpLogVO
						.getCruser()));
				newList.add(tawwpLogVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("作业计划日志查询出现异常");
		} finally {
			list = null;
		}

		return newList;
	}

	

}
