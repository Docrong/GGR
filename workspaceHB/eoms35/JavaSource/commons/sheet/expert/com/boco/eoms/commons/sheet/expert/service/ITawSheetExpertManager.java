/*
 * Created on 2008-5-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.sheet.expert.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.sheet.expert.model.TawSheetExpert;

/**
 * @author admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ITawSheetExpertManager extends Manager {

	/**
     * Retrieves all of the TawSheetExperts
     */
    public List getTawSheetExperts(TawSheetExpert tawSheetExpert);
    
    
    /**
     * Gets TawSheetExpert's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the TawSheetExpert's id
     * @return TawSheetExpert populated TawSheetExpert object
     */
    public TawSheetExpert getTawSheetExpert(final Integer id);
    /**
     * Saves a tawSheetExpert's information
     * @param tawSheetExpert the object to be saved
     */    
    public void saveTawSheetExpert(TawSheetExpert tawSheetExpert);

    /**
     * Removes a tawSheetExpert from the database by id
     * @param id the tawSheetExpert's id
     */
    public void removeTawSheetExpert(final Integer id); 
    /**
     * 根据专业ID查询专业信息
     * @param areaid
     * @return
     */
    public TawSheetExpert getExpertBySpecialId(String specialid);
    
    /**
     * 根据专业ID查询专家信息
     * @param areaid
     * @return
     */
    public List getExpertsBySpecialId(String specialid);
    /**
     * 根据专业userId更新
     * @param areaid
     * @return
     */
    public void updtaeExpertByExpertName(String expertName,String oldExpertName);
    /**
     * 根据专业userId删除
     * @param areaid
     * @return
     */
    public void deleteTawSheetExpert(String expertName);
    public void updateTawSheetExpert(String expertName);
}
