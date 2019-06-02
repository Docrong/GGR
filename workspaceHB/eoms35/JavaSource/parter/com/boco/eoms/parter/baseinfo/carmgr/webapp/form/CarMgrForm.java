package com.boco.eoms.parter.baseinfo.carmgr.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.system.dict.service.bo.TawSystemDictBo;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;

/**
 * Generated by XDoclet/actionform. This class can be further processed with XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 *
 * @struts.form name="carMgrForm" 
 */
public class CarMgrForm
    extends    BaseForm
    implements java.io.Serializable
{

    protected String carName;

    protected String carTypes;
    
    protected String carNum;

    protected String parterCor;

    protected String id;

    /** Default empty constructor. */
    public CarMgrForm() {}

    public String getCarName()
    {
        return this.carName;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCarName( String carName )
    {
        this.carName = carName;
    }

    public String getCarTypes()
    {
        return this.carTypes;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCarTypes( String carTypes )
    {
        this.carTypes = carTypes;
    }

    public String getCarNum()
    {
        return this.carNum;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCarNum( String carNum )
    {
        this.carNum = carNum;
    }

    public String getParterCor()
    {
        return this.parterCor;
    }
   /**
    * @struts.validator type="required"
    */

    public void setParterCor( String parterCor )
    {
        this.parterCor = parterCor;
    }

    public String getId()
    {
        return this.id;
    }
   /**
    */

    public void setId( String id )
    {
        this.id = id;
    }

        /* To add non XDoclet-generated methods, create a file named
           xdoclet-CarMgrForm.java 
           containing the additional code and place it in your metadata/web directory.
        */
    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *                                                javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

    }

}
