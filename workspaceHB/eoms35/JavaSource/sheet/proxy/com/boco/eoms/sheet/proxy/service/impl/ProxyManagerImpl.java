package com.boco.eoms.sheet.proxy.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.proxy.dao.IProxyDAO;
import com.boco.eoms.sheet.proxy.model.Proxy;
import com.boco.eoms.sheet.proxy.service.IProxyManager;

public class ProxyManagerImpl extends BaseManager implements IProxyManager{
	private IProxyDAO dao;
	 /**
     * Saves a tawSheetExpert's information
     * @param tawSheetExpert the object to be saved
     */    
    public void saveProxy(Proxy proxy){
    	dao.saveProxy(proxy);
    }
    /**
     * Saves a tawSheetExpert's information
     * @param tawSheetExpert the object to be saved
     */  
    public void removeProxy(final Integer id){
    	dao.removeProxy(id);
    }
    
    public Proxy getProxy(final Integer id){
    	return dao.getProxy(id);
    }
	public IProxyDAO getDao() {
		return dao;
	}
	public void setDao(IProxyDAO dao) {
		this.dao = dao;
	}
	public List getProxyList(final Proxy proxy){
		return dao.getProxyList(proxy);
	}
    
	

}
