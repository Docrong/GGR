/**
 * com.boco.eoms.commons.system.dict.model.TawSystemDictType.java
 */
package com.boco.eoms.commons.system.dict.model;

// java standard library
import java.io.Serializable;

// eoms class
import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * <p>
 * <a href="TawSystemDictType.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="Taw_System_DictType"
 */
public class TawSystemDictType extends BaseObject implements Serializable {

    // properties
    private Integer id; // required

    private String dictId; 

    private String dictCode;

    private String dictName;

    private Integer moduleId; 

    private String moduleName; 

    private String dictRemark;

    private String parentDictId;

    private Integer sysType;
    
    private Integer leaf;

/**
 * 叶子节点
 * @return
 */
    public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	/**
     * Default Constructor
     */
    public TawSystemDictType() {
    }

    /**
     * @hibernate.id column="id" generator-class="native" unsaved-value="null"
     */
    public Integer getId() {
        return id;
    }

    /**
     * @hibernate.property length="50" not-null="true" unique="true"
     * @struts.validator type="required"
     * @eoms.show
     * @eoms.cn name="字典类型"
     * @return
     */
    public String getDictName() {
        return dictName;
    }

    public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	/**
     * @struts.validator type="required"
     * @hibernate.property length="50" not-null="true"
     */
    public Integer getModuleId() {
        return moduleId;
    }

    /**
     * @hibernate.property length="50" not-null="true"
     * @struts.validator type="required"
     * @eoms.show
     * @eoms.cn name="所属模块"
     * @return
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @hibernate.property length="256"
     * @eoms.show
     * @eoms.cn name="字典备注"
     * @return
     */
    public String getDictRemark() {
        return dictRemark;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public void setDictRemark(String dictRemark) {
        this.dictRemark = dictRemark;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
     */
    
    public boolean equals(Object o) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.base.model.BaseObject#hashCode()
     */

    public int hashCode() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.base.model.BaseObject#toString()
     */

    public String toString() {
        return null;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getParentDictId() {
        return parentDictId;
    }

    public void setParentDictId(String parentDictId) {
        this.parentDictId = parentDictId;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

}
