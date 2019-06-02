package com.boco.eoms.filemanager.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.filemanager.model.TawFileMgrScheme;

public interface ITawFileMgrSchemeDao extends Dao {

	/**
	 * Retrieves all of the tawSystemDepts
	 */
	public List getTawFileMgrScheme(TawFileMgrScheme tawFileMgrScheme);

	/**
	 * Gets tawSystemDept's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawSystemDept's id
	 * @return tawSystemDept populated tawSystemDept object
	 */
	public TawFileMgrScheme getTawFileMgrScheme(final Integer scheme_id);

	/**
	 * Saves a tawSystemDept's information
	 * 
	 * @param tawSystemDept
	 *            the object to be saved
	 */
	public void saveTawFileMgrScheme(TawFileMgrScheme tawFileMgrScheme);

	/**
	 * Removes a tawSystemDept from the database by id
	 * 
	 * @param id
	 *            the tawSystemDept's id
	 */
	public void removeTawFileMgrScheme(final Integer scheme_id);

	/**
	 * 根据部门ID和删除标志 查询某部门信息
	 * 
	 * @param deptid
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public TawFileMgrScheme getSchemeinfobyschemeid(String scheme_id);

	/**
	 * 查询所有的部门名称
	 * 
	 * @return
	 */
	public List getFileMgrScheme();

	/**
	 * 得到下一级子部门的部门信息
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public List getNextLevecScheme(String topic_id) ;

	/**
	 * 得到此用户派发和派发给此部门的本专题下的列表
	 * @param topic_id
	 * @param create_user
	 * @param accept_dept_id
	 * @return
	 */
	public List getNextLevecScheme(String topic_id, String create_user, String accept_dept_id) ;
	
	/**
	 * 其下是否有派发表
	 * @param topic_id
	 * @return
	 */
	public boolean notScheme(String topic_id);
}
