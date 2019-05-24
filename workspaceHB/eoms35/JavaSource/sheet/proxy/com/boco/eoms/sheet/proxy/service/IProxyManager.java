package com.boco.eoms.sheet.proxy.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.proxy.model.Proxy;

public interface IProxyManager extends Manager{
	 /**
     * Saves a tawSheetExpert's information
     * @param tawSheetExpert the object to be saved
     */    
    public void saveProxy(Proxy proxy);

    /**
     * Saves a tawSheetExpert's information
     * @param tawSheetExpert the object to be saved
     */  
    public void removeProxy(final Integer id); 
    /**
     * Gets Proxy's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the Proxy's id
     * @return Proxy populated Proxy object
     */
    public Proxy getProxy(final Integer id);
    public List getProxyList(final Proxy proxy);

}
