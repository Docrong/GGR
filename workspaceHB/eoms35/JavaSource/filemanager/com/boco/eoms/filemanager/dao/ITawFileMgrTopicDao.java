package com.boco.eoms.filemanager.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.filemanager.model.TawFileMgrTopic;

public interface ITawFileMgrTopicDao extends Dao {

	/**
	 * Retrieves all of the tawSystemDepts
	 */
	public List getTawFileMgrTopic(TawFileMgrTopic tawFileMgrTopic);

	/**
	 * Gets tawSystemDept's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawSystemDept's id
	 * @return tawSystemDept populated tawSystemDept object
	 */
	public TawFileMgrTopic getTawFileMgrTopic(final Integer topic_id);

	/**
	 * Saves a tawSystemDept's information
	 * 
	 * @param tawSystemDept
	 *            the object to be saved
	 */
	public void saveTawFileMgrTopic(TawFileMgrTopic tawFileMgrTopic);

	/**
	 * Removes a tawSystemDept from the database by id
	 * 
	 * @param id
	 *            the tawSystemDept's id
	 */
	public void removeTawFileMgrTopic(final Integer topic_id);

	/**
	 * 根据部门ID和删除标志 查询某部门信息
	 * 
	 * @param deptid
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public TawFileMgrTopic getTopicinfobytopicid(String topic_id);

	/**
	 * 查询所有的部门名称
	 * 
	 * @return
	 */
	public List getFileMgrTopic();

	/**
	 * 得到下一级子部门的部门信息
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public List getNextLevecTopic(String partopicid);

	/**
	 * 是否为叶子节点
	 * @param topic_id
	 * @return
	 */
	public boolean isLeaf(String topic_id);
	
	/**
	 * 根据结点Id取得结点名称
	 * @param topic_id
	 * @return
	 */
	public String getNameById(String topic_id);
}
