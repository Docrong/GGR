
package com.boco.eoms.message.mgr;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.message.model.SmsSpSubmit;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:39:09
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface ISmsSpSubmitManager extends Manager {
    /**
     * Retrieves all of the smsSpSubmits
     */
    public List getSmsSpSubmits(SmsSpSubmit smsSpSubmit);

    /**
     * Gets smsSpSubmit's information based on id.
     * @param id the smsSpSubmit's id
     * @return smsSpSubmit populated smsSpSubmit object
     */
    public SmsSpSubmit getSmsSpSubmit(final String id);

    /**
     * Saves a smsSpSubmit's information
     * @param smsSpSubmit the object to be saved
     */
    public void saveSmsSpSubmit(SmsSpSubmit smsSpSubmit);

    /**
     * Removes a smsSpSubmit from the database by id
     * @param id the smsSpSubmit's id
     */
    public void removeSmsSpSubmit(final String id);
   
}

