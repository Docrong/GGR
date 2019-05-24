package com.boco.eoms.extra.supplierkpi.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

/**
 * Generated by XDoclet/actionform. This class can be further processed with XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 *
 * @struts.form name="tawSupplierkpiItemForm" 
 */
public class TawSupplierkpiItemForm
    extends    BaseForm
    implements java.io.Serializable
{

    protected String assessContent;

    protected String assessNote;

    protected String createTime;

    protected String creator;

    protected String dataSource;

    protected String dataSourceImplManner;

    protected String dataType;

    protected String id;

    protected String isImpersonality;

    protected String itemType;

    protected String templateId;

    protected String planGeneCycle;

    protected String preActor;

    protected String statictsCycle;

    protected String writeManner;

    protected String unit;

    protected String assessRole;

    protected String isAssess;
    
    protected String kpiName;
    
    protected String assessMoment;
    
    protected String id2dataSource;
    protected String id2dataType;
    protected String id2statictsCycle;
    protected String id2writeManner;
    protected String id2unit;
    protected String id2isImpersonality;
    protected String id2itemType;
    
    protected int assessStatus;
    protected String dictId;
    protected int deleted;
    
    
    public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getAssessStatus() {
		return assessStatus;
	}
	public void setAssessStatus(int assessStatus) {
		this.assessStatus = assessStatus;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getId2dataSource() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getDataSource());
	}
    public String getId2dataType() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getDataType());
	}
    public String getId2statictsCycle() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getStatictsCycle());
	}
    public String getId2writeManner() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getWriteManner());
	}
    public String getId2unit() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getUnit());
	}
    public String getId2isImpersonality() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getIsImpersonality());
	}
    public String getId2itemType() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getItemType());
	}
    

    /** Default empty constructor. */
    public TawSupplierkpiItemForm() {}

    public String getAssessContent()
    {
        return this.assessContent;
    }
   /**
    * @struts.validator type="required"
    */

    public void setAssessContent( String assessContent )
    {
        this.assessContent = assessContent;
    }

    public String getAssessNote()
    {
        return this.assessNote;
    }
   /**
    * @struts.validator type="required"
    */

    public void setAssessNote( String assessNote )
    {
        this.assessNote = assessNote;
    }

    public String getCreateTime()
    {
        return this.createTime;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCreateTime( String createTime )
    {
        this.createTime = createTime;
    }

    public String getCreator()
    {
        return this.creator;
    }
   /**
    * @struts.validator type="required"
    */

    public void setCreator( String creator )
    {
        this.creator = creator;
    }

    public String getDataSource()
    {
        return this.dataSource;
    }
   /**
    * @struts.validator type="required"
    */

    public void setDataSource( String dataSource )
    {
        this.dataSource = dataSource;
    }

    public String getDataSourceImplManner()
    {
        return this.dataSourceImplManner;
    }
   /**
    * @struts.validator type="required"
    */

    public void setDataSourceImplManner( String dataSourceImplManner )
    {
        this.dataSourceImplManner = dataSourceImplManner;
    }

    public String getDataType()
    {
        return this.dataType;
    }
   /**
    * @struts.validator type="required"
    */

    public void setDataType( String dataType )
    {
        this.dataType = dataType;
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

    public String getIsImpersonality()
    {
        return this.isImpersonality;
    }
   /**
    * @struts.validator type="required"
    */

    public void setIsImpersonality( String isImpersonality )
    {
        this.isImpersonality = isImpersonality;
    }

    public String getItemType()
    {
        return this.itemType;
    }
   /**
    * @struts.validator type="required"
    */

    public void setItemType( String itemType )
    {
        this.itemType = itemType;
    }

    public String getTemplateId()
    {
        return this.templateId;
    }
   /**
    * @struts.validator type="required"
    */

    public void setTemplateId( String templateId )
    {
        this.templateId = templateId;
    }

    public String getPlanGeneCycle()
    {
        return this.planGeneCycle;
    }
   /**
    * @struts.validator type="required"
    */

    public void setPlanGeneCycle( String planGeneCycle )
    {
        this.planGeneCycle = planGeneCycle;
    }

    public String getPreActor()
    {
        return this.preActor;
    }
   /**
    * @struts.validator type="required"
    */

    public void setPreActor( String preActor )
    {
        this.preActor = preActor;
    }

    public String getStatictsCycle()
    {
        return this.statictsCycle;
    }
   /**
    * @struts.validator type="required"
    */

    public void setStatictsCycle( String statictsCycle )
    {
        this.statictsCycle = statictsCycle;
    }

    public String getWriteManner()
    {
        return this.writeManner;
    }
   /**
    * @struts.validator type="required"
    */

    public void setWriteManner( String writeManner )
    {
        this.writeManner = writeManner;
    }

    public String getUnit()
    {
        return this.unit;
    }
   /**
    * @struts.validator type="required"
    */

    public void setUnit( String unit )
    {
        this.unit = unit;
    }

    public String getAssessRole()
    {
        return this.assessRole;
    }
   /**
    * @struts.validator type="required"
    */

    public void setAssessRole( String assessRole )
    {
        this.assessRole = assessRole;
    }

    public String getIsAssess()
    {
        return this.isAssess;
    }
   /**
    * @struts.validator type="required"
    */

    public void setIsAssess( String isAssess )
    {
        this.isAssess = isAssess;
    }

    public String getKpiName() {
    	return kpiName;
    }

    public void setKpiName(String kpiName) {
    	this.kpiName = kpiName;
    }

	public String getAssessMoment() {
		return assessMoment;
	}
	public void setAssessMoment(String assessMoment) {
		this.assessMoment = assessMoment;
	}
	/* To add non XDoclet-generated methods, create a file named
           xdoclet-TawSupplierkpiItemForm.java 
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
