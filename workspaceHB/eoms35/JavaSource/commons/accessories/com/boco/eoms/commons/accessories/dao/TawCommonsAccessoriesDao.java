package com.boco.eoms.commons.accessories.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;

/**
 * <p>Title:
 * </p>
 * <p>Description:附件管理DAO类
 * </p>
 * <p>Apr 10, 2007 10:57:17 AM</p>
 *
 * @author 秦敏
 * @version 1.0
 */
public interface TawCommonsAccessoriesDao extends Dao {

    /**
     * Retrieves all of the tawCommonsAccessoriess
     */
    public List getTawCommonsAccessoriess();

    /**
     * Gets tawCommonsAccessories's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     *
     * @param id the tawCommonsAccessories's id
     * @return tawCommonsAccessories populated tawCommonsAccessories object
     */
    public TawCommonsAccessories getTawCommonsAccessories(final String id);

    /**
     * Saves a tawCommonsAccessories's information
     *
     * @param tawCommonsAccessories the object to be saved
     */
    public void saveTawCommonsAccessories(
            TawCommonsAccessories tawCommonsAccessories);

    /**
     * Removes a tawCommonsAccessories from the database by id
     *
     * @param id the tawCommonsAccessories's id
     */
    public void removeTawCommonsAccessories(final String id);

    /**
     * 获取刚上传的文件信息
     *
     * @param accesspriesFileNames 文件名称
     * @return
     * @author 秦敏
     */
    public List getAllFileByName(String accesspriesFileNames);


    /**
     * 根据实际文件的编号查询文件信息（附件上传接口用）
     *
     * @param accesspriesFileName 文件的编号（类似20081214092334）
     * @return
     * @author 赵东亮
     */
    public List getFileByName(String accesspriesFileName);
}
