package com.boco.eoms.commons.job.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务订阅DAO
 * </p>
 * <p>Apr 10, 2007 10:34:11 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public interface TawCommonsJobsubscibeDao extends Dao {

	/**
	 * Retrieves all of the tawCommonsJobsubscibes
	 */
	public List getTawCommonsJobsubscibes( );

	/**
	 * Gets tawCommonsJobsubscibe's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawCommonsJobsubscibe's id
	 * @return tawCommonsJobsubscibe populated tawCommonsJobsubscibe object
	 */
	public TawCommonsJobsubscibe getTawCommonsJobsubscibe(final String id);
	
	public TawCommonsJobsubscibe getTawCommonsJobsubscibeForSubID(final String subId);

	/**
	 * Saves a tawCommonsJobsubscibe's information
	 * 
	 * @param tawCommonsJobsubscibe
	 *            the object to be saved
	 */
	public void saveTawCommonsJobsubscibe(
			TawCommonsJobsubscibe tawCommonsJobsubscibe);

	/**
	 * Removes a tawCommonsJobsubscibe from the database by id
	 * 
	 * @param id
	 *            the tawCommonsJobsubscibe's id
	 */
	public void removeTawCommonsJobsubscibe(final String id);

	
    /**
     * 根据订阅号获取指定的订阅信息
     * @param subId 订阅号
     * @return
     * @author 秦敏
     */
	public TawCommonsJobsubscibe getSubscibeJobById(String subId);

	/**
	 * 根据订阅号获取与该订阅号雷同的订阅号数目
	 * @param subId 订阅号 （只有前面三部分，例如：JOB-1-070410）
	 * @return
	 * @author 秦敏
	 */
	public int getCountNum(String subId);
	 /**
	  * 根据任务类型ID号查询任务订阅信息
	  * @param jobSortId 任务类型ID号
	  * @return
	  * @author 秦敏
	  */
	public List getSubscibeListBySortId(Integer jobSortId);
}
