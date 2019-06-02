package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteFile;

public interface ITawwpExecuteFileDao {
	/**
	 * 保存执行作业计划附件
	 * 
	 * @param _tawwpExecuteFile
	 *            TawwpExecuteFile 执行作业计划附件类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveExecuteFile(TawwpExecuteFile _tawwpExecuteFile);

	/**
	 * 删除执行作业计划附件
	 * 
	 * @param _tawwpExecuteFile
	 *            TawwpExecuteFile 执行作业计划附件类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteExecuteFile(TawwpExecuteFile _tawwpExecuteFile);

	/**
	 * 修改执行作业计划附件
	 * 
	 * @param _tawwpExecuteFile
	 *            TawwpExecuteFile 执行作业计划附件类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateExecuteFile(TawwpExecuteFile _tawwpExecuteFile);

	/**
	 * 查询执行作业计划附件信息
	 * 
	 * @param id
	 *            String 执行作业计划附件标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpExecuteFile 执行作业计划附件类
	 */
	public TawwpExecuteFile loadExecuteFile(String id);

	/**
	 * 查询所有执行作业计划附件信息
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 执行作业计划附件类列表
	 */
	public List listExecuteFile();
	
	public List listExecuteFile(String executeContentId);
	/**
	 * 获取与执行执行内容关联的附件对象
	 * 
	 * @param _fileCodeName
	 *            String 文件实际存储名
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 执行作业计划执行内容(整体)对象
	 * @throws Exception
	 *             异常
	 * @return TawwpExecuteFile 附件对象
	 */
	public TawwpExecuteFile filterTawwpExecuteFile(String _fileCodeName,
			TawwpExecuteContent _tawwpExecuteContent);
	public TawwpExecuteFile  loadExecuteFileByCode(String id );
}
