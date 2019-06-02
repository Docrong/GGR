
package com.boco.eoms.commons.sheet.special.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.sheet.special.model.TawSheetSpecial;
import com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialDao;
/**
 * 
 * @author panlong
 *下午05:39:11
 */
public interface ITawSheetSpecialManager extends Manager {
    /**
     * Retrieves all of the tawSheetSpecials
     */
    public List getTawSheetSpecials(TawSheetSpecial tawSheetSpecial);

    /**
     * Gets tawSheetSpecial's information based on id.
     * @param id the tawSheetSpecial's id
     * @return tawSheetSpecial populated tawSheetSpecial object
     */
    public TawSheetSpecial getTawSheetSpecial(final Integer id);

    /**
     * Saves a tawSheetSpecial's information
     * @param tawSheetSpecial the object to be saved
     */
    public void saveTawSheetSpecial(TawSheetSpecial tawSheetSpecial,String touserid);
    
    public void removeSpecialAndRefuser(TawSheetSpecial tawSheetSpecial);

    /**
     * Removes a tawSheetSpecial from the database by id
     * @param id the tawSheetSpecial's id
     */
    public void removeTawSheetSpecial(final Integer id);
    public Map getTawSheetSpecials(final Integer curPage, final Integer pageSize);
    public Map getTawSheetSpecials(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
	 * 根据地域ID得到最大的专业ID
	 * 
	 * @param deptid
	 * @return
	 */
	public String getMaxSpecialId(String parareaid);
	
	 /**
     * 根据专业ID查询专业信息
     * @param areaid
     * @return
     */
    public TawSheetSpecial getSpecialByspecialId(String specialid);
    /**
     * 查询某专业的下一级专业信息
     * @param specialid
     * @return
     */
    public List getSonspecialByspecialId(String specialid);
    /**
     * 查询同级专业信息
     * @param parentspecialid
     * @param ordercode
     * @return
     */
    public List getSameLevelspecial(String parentspecialid,Integer ordercode);
    /**
     * 查询某专业名称是否存在
     * @param specialname
     * @return
     */
    public boolean isExitspecialName(String specialid);
    /**
     * 查询某专业的所有子专业信息
     * @param specialid
     * @return
     */
    public List getAllSonspecialByspecialid(String specialid);
    
    /**
	 * 某专业下的人员列表
	 * @param speid
	 */
	public List getSpecialRefUserList(String speid);
}

