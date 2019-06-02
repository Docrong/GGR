package com.boco.eoms.sheet.proxy.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.proxy.model.Proxy;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:29:53
 * </p>
 * 
 * @author 张影
 * @version 1.0
 * 
 */
public interface IProxyDAO extends Dao {

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
