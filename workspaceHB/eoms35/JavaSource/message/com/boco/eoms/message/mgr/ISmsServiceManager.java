
package com.boco.eoms.message.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.message.model.SmsService;


/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:39:18
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface ISmsServiceManager extends Manager {
    /**
     * Retrieves all of the smsServices
     */
    public List getSmsServices(SmsService smsService);

    /**
     * Gets smsService's information based on id.
     * @param id the smsService's id
     * @return smsService populated smsService object
     */
    public SmsService getSmsService(final String id);

    /**
     * Saves a smsService's information
     * @param smsService the object to be saved
     */
    public void saveSmsService(SmsService smsService);

    /**
     * Removes a smsService from the database by id
     * @param id the smsService's id
     */
    public void removeSmsService(final String id);
    public Map getSmsServices(final Integer curPage, final Integer pageSize);
    public Map getSmsServices(final Integer curPage, final Integer pageSize, final String whereStr);
    public List getNextLevelServices(String parentid, String delid);
    public List getCancelServices(String receiverId);
    public boolean hasService(String serviceId);
    public List getAllServices(String userId);
    
    public String xSaveXmlString(String xmlString);
    public void xDeleteByWebService(String id);
    public String xGetXmlString(String id);
    public String xGetAllServices();
    
    public List getUsersList(String serviceId);
    
}

