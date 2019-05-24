
package com.boco.eoms.commons.sheet.special.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.sheet.special.model.TawSheetSpecial;
import com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialDao;
import com.boco.eoms.commons.sheet.special.dao.TawSheetSpecialJdbc;
import com.boco.eoms.commons.sheet.special.service.ITawSheetSpecialManager;
/**
 * 
 * @author panlong
 *下午05:38:39
 */
public class TawSheetSpecialManagerImpl extends BaseManager implements ITawSheetSpecialManager {
    private TawSheetSpecialDao dao;
    private TawSheetSpecialJdbc daojdbc;

  

    public TawSheetSpecialDao getDao() {
		return dao;
	}

	public void setDao(TawSheetSpecialDao dao) {
		this.dao = dao;
	}

	public TawSheetSpecialJdbc getDaojdbc() {
		return daojdbc;
	}

	public void setDaojdbc(TawSheetSpecialJdbc daojdbc) {
		this.daojdbc = daojdbc;
	}

	/**
     * @see com.boco.eoms.commons.sheet.special.service.ITawSheetSpecialManager#getTawSheetSpecials(com.boco.eoms.commons.sheet.special.model.TawSheetSpecial)
     */
    public List getTawSheetSpecials(final TawSheetSpecial tawSheetSpecial) {
        return dao.getTawSheetSpecials(tawSheetSpecial);
    }

    /**
     * @see com.boco.eoms.commons.sheet.special.service.ITawSheetSpecialManager#getTawSheetSpecial(String id)
     */
    public TawSheetSpecial getTawSheetSpecial(final Integer id) {
        return dao.getTawSheetSpecial(id);
    }

    /**
     * @see com.boco.eoms.commons.sheet.special.service.ITawSheetSpecialManager#saveTawSheetSpecial(TawSheetSpecial tawSheetSpecial)
     */
    public void saveTawSheetSpecial(TawSheetSpecial tawSheetSpecial,String touserid) {
    	String speid = "";
    	speid = tawSheetSpecial.getSpeid();
    	if( daojdbc.isExitsSpecialRefUser(speid) ){
			daojdbc.removeSpecialRefUser(speid);
		}
    	if( touserid == null || touserid.equals("") ){
    		dao.saveTawSheetSpecial(tawSheetSpecial);
    	}else{
    		dao.saveTawSheetSpecial(tawSheetSpecial);
    		String []str = touserid.split(",");
    		for( int i=0;i< str.length;i++){
    			daojdbc.saveSpecialRefUser(speid, str[i]);
    		}
    	}
        
    }

    /**
     * @see com.boco.eoms.commons.sheet.special.service.ITawSheetSpecialManager#removeTawSheetSpecial(String id)
     */
    public void removeTawSheetSpecial(final Integer id) { 
        dao.removeTawSheetSpecial(id);
    }
    /**
     * 
     */
    public Map getTawSheetSpecials(final Integer curPage, final Integer pageSize) {
        return dao.getTawSheetSpecials(curPage, pageSize,null);
    }
    public Map getTawSheetSpecials(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSheetSpecials(curPage, pageSize, whereStr);
    }
    
    /**
	 * 根据地域ID得到最大的专业ID
	 * @param deptid
	 * @return
	 */
    public String getMaxSpecialId(String paraspeid){
		String newspeid = StaticVariable.AREA_DEFAULT_STRVAL;
		long areaidvar = StaticVariable.AREA_DEFAULT_LONGVAL;
		
		newspeid = daojdbc.getMaxSpecialId(paraspeid, paraspeid.length() + StaticVariable.AREAID_BETWEEN_LENGTH);
		if (newspeid.equals(paraspeid)) {
			newspeid = paraspeid+StaticVariable.AREAID_NOSON;
		} else {
			areaidvar = Long.valueOf(newspeid).longValue();
			if (newspeid.compareTo(paraspeid + StaticVariable.AREAID_IF_MAXID) < StaticVariable.AREA_DEFAULT_INTVAL) {
				newspeid = String.valueOf(areaidvar + StaticVariable.AREA_DEFAULT_INTHVAL);
			} else {
				newspeid = StaticVariable.AREAID_MAXID;
			}
		}
		return newspeid;
	}
    
    /**
     * 根据专业ID查询专业信息
     * @param areaid
     * @return
     */
    public TawSheetSpecial getSpecialByspecialId(String specialid){
    	return dao.getSpecialByspecialId(specialid);
    }
    /**
     * 查询某专业的下一级专业信息
     * @param specialid
     * @return
     */
    public List getSonspecialByspecialId(String specialid){
    	return dao.getSonspecialByspecialId(specialid);
    }
    /**
     * 查询同级专业信息
     * @param parentspecialid
     * @param ordercode
     * @return
     */
    public List getSameLevelspecial(String parentspecialid,Integer ordercode){
    	return dao.getSameLevelspecial(parentspecialid,ordercode);
    }
    /**
     * 查询某专业名称是否存在
     * @param specialname
     * @return
     */
    public boolean isExitspecialName(String specialid){
    	return dao.isExitspecialName(specialid);
    }
    /**
     * 查询某专业的所有子专业信息
     * @param specialid
     * @return
     */
    public List getAllSonspecialByspecialid(String specialid){
    	return dao.getAllSonspecialByspecialid(specialid);
    }
    /**
     * 删除专业以及对应的关联
     */
    public void removeSpecialAndRefuser(TawSheetSpecial tawSheetSpecial){
    	 daojdbc.removeTawSheetSpecial(tawSheetSpecial.getSpeid());
    	 daojdbc.removeSpecialRefUser(tawSheetSpecial.getSpeid());
    }
    
	/**
	 * 某专业下的人员列表
	 * @param speid
	 */
	public List getSpecialRefUserList(String speid){
		return daojdbc.getSpecialRefUserList(speid);
	}
}
