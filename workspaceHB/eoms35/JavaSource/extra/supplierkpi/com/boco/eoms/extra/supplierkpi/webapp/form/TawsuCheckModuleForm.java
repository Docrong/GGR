package com.boco.eoms.extra.supplierkpi.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 *
 * @struts.form name="tawsuCheckModuleForm" 
 */
public class TawsuCheckModuleForm
    extends    BaseForm
    implements java.io.Serializable
{

    protected String checkContent;

    protected String checkTime;

    protected String checkUser;

    protected String checkUsers;

    protected String crTime;

    protected String crUser;

    protected String id;

    /** Default empty constructor. */
    public TawsuCheckModuleForm() {}

    public String getCheckContent()
    {
        return this.checkContent;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCheckContent( String checkContent )
    {
        this.checkContent = checkContent;
    }

    public String getCheckTime()
    {
        return this.checkTime;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCheckTime( String checkTime )
    {
        this.checkTime = checkTime;
    }

    public String getCheckUser()
    {
        return this.checkUser;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCheckUser( String checkUser )
    {
        this.checkUser = checkUser;
    }

    public String getCheckUsers()
    {
        return this.checkUsers;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCheckUsers( String checkUsers )
    {
        this.checkUsers = checkUsers;
    }

    public String getCrTime()
    {
        return this.crTime;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCrTime( String crTime )
    {
        this.crTime = crTime;
    }

    public String getCrUser()
    {
        return this.crUser;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCrUser( String crUser )
    {
        this.crUser = crUser;
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
           xdoclet-TawsuCheckModuleForm.java 
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