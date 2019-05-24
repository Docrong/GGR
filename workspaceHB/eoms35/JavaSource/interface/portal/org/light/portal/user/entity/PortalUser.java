/**
 * PortalUser.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.light.portal.user.entity;

public class PortalUser  implements java.io.Serializable {
    private java.lang.String deptid;

    private java.lang.String deptname;

    private java.lang.String email;

    private java.lang.String id;

    private java.lang.String password;

    private java.lang.String postid;

    private java.lang.String postname;

    private java.lang.String role;

    private java.lang.String userId;

    private java.lang.String username;

    private java.lang.Integer version;

    public PortalUser() {
    }

    public PortalUser(
           java.lang.String deptid,
           java.lang.String deptname,
           java.lang.String email,
           java.lang.String id,
           java.lang.String password,
           java.lang.String postid,
           java.lang.String postname,
           java.lang.String role,
           java.lang.String userId,
           java.lang.String username,
           java.lang.Integer version) {
           this.deptid = deptid;
           this.deptname = deptname;
           this.email = email;
           this.id = id;
           this.password = password;
           this.postid = postid;
           this.postname = postname;
           this.role = role;
           this.userId = userId;
           this.username = username;
           this.version = version;
    }


    /**
     * Gets the deptid value for this PortalUser.
     * 
     * @return deptid
     */
    public java.lang.String getDeptid() {
        return deptid;
    }


    /**
     * Sets the deptid value for this PortalUser.
     * 
     * @param deptid
     */
    public void setDeptid(java.lang.String deptid) {
        this.deptid = deptid;
    }


    /**
     * Gets the deptname value for this PortalUser.
     * 
     * @return deptname
     */
    public java.lang.String getDeptname() {
        return deptname;
    }


    /**
     * Sets the deptname value for this PortalUser.
     * 
     * @param deptname
     */
    public void setDeptname(java.lang.String deptname) {
        this.deptname = deptname;
    }


    /**
     * Gets the email value for this PortalUser.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this PortalUser.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the id value for this PortalUser.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this PortalUser.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the password value for this PortalUser.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this PortalUser.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the postid value for this PortalUser.
     * 
     * @return postid
     */
    public java.lang.String getPostid() {
        return postid;
    }


    /**
     * Sets the postid value for this PortalUser.
     * 
     * @param postid
     */
    public void setPostid(java.lang.String postid) {
        this.postid = postid;
    }


    /**
     * Gets the postname value for this PortalUser.
     * 
     * @return postname
     */
    public java.lang.String getPostname() {
        return postname;
    }


    /**
     * Sets the postname value for this PortalUser.
     * 
     * @param postname
     */
    public void setPostname(java.lang.String postname) {
        this.postname = postname;
    }


    /**
     * Gets the role value for this PortalUser.
     * 
     * @return role
     */
    public java.lang.String getRole() {
        return role;
    }


    /**
     * Sets the role value for this PortalUser.
     * 
     * @param role
     */
    public void setRole(java.lang.String role) {
        this.role = role;
    }


    /**
     * Gets the userId value for this PortalUser.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this PortalUser.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the username value for this PortalUser.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this PortalUser.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the version value for this PortalUser.
     * 
     * @return version
     */
    public java.lang.Integer getVersion() {
        return version;
    }


    /**
     * Sets the version value for this PortalUser.
     * 
     * @param version
     */
    public void setVersion(java.lang.Integer version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PortalUser)) return false;
        PortalUser other = (PortalUser) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.deptid==null && other.getDeptid()==null) || 
             (this.deptid!=null &&
              this.deptid.equals(other.getDeptid()))) &&
            ((this.deptname==null && other.getDeptname()==null) || 
             (this.deptname!=null &&
              this.deptname.equals(other.getDeptname()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.postid==null && other.getPostid()==null) || 
             (this.postid!=null &&
              this.postid.equals(other.getPostid()))) &&
            ((this.postname==null && other.getPostname()==null) || 
             (this.postname!=null &&
              this.postname.equals(other.getPostname()))) &&
            ((this.role==null && other.getRole()==null) || 
             (this.role!=null &&
              this.role.equals(other.getRole()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDeptid() != null) {
            _hashCode += getDeptid().hashCode();
        }
        if (getDeptname() != null) {
            _hashCode += getDeptname().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getPostid() != null) {
            _hashCode += getPostid().hashCode();
        }
        if (getPostname() != null) {
            _hashCode += getPostname().hashCode();
        }
        if (getRole() != null) {
            _hashCode += getRole().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PortalUser.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "PortalUser"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deptid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "deptid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deptname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "deptname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "postid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "postname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("role");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "role"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.portal.light.org", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
