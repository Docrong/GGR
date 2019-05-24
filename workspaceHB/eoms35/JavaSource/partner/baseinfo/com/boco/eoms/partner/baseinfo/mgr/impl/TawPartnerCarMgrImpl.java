package com.boco.eoms.partner.baseinfo.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partner.baseinfo.model.TawPartnerCar;
import com.boco.eoms.partner.baseinfo.mgr.TawPartnerCarMgr;
import com.boco.eoms.partner.baseinfo.dao.TawPartnerCarDao;

/**
 * <p>
 * Title:车辆管理
 * </p>
 * <p>
 * Description:车辆管理
 * </p>
 * <p>
 * Thu Feb 05 13:54:40 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
public class TawPartnerCarMgrImpl implements TawPartnerCarMgr {
 
	private TawPartnerCarDao  tawPartnerCarDao;
 	
	public TawPartnerCarDao getTawPartnerCarDao() {
		return this.tawPartnerCarDao;
	}
 	
	public void setTawPartnerCarDao(TawPartnerCarDao tawPartnerCarDao) {
		this.tawPartnerCarDao = tawPartnerCarDao;
	}
 	
    public List getTawPartnerCars() {
    	return tawPartnerCarDao.getTawPartnerCars();
    }
    
    public TawPartnerCar getTawPartnerCar(final String id) {
    	return tawPartnerCarDao.getTawPartnerCar(id);
    }
    
    public void saveTawPartnerCar(TawPartnerCar tawPartnerCar) {
    	tawPartnerCarDao.saveTawPartnerCar(tawPartnerCar);
    }
    
    public void removeTawPartnerCar(final String id) {
    	tawPartnerCarDao.removeTawPartnerCar(id);
    }
    
    public Map getTawPartnerCars(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return tawPartnerCarDao.getTawPartnerCars(curPage, pageSize, whereStr);
	}

	public Boolean isunique(final String car_number) {
		// TODO 自动生成方法存根
		return tawPartnerCarDao.isunique(car_number);
	}

	public String name2Id(final String dictName,final  String parentDictId) {
		// TODO 自动生成方法存根
		return tawPartnerCarDao.name2Id(dictName, parentDictId);
	}
	
	public String[] getDictIdByParentId(final String parentDictId){
		return tawPartnerCarDao.getDictIdByParentId(parentDictId);
	}
	
}