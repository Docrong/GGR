package com.hoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for apiResult complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="apiResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="info" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="list" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="msg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ok" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class ApiResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String info;
	protected List list;
	protected String msg;
	protected String ok;

	/**
	 * Gets the value of the info property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Sets the value of the info property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInfo(String value) {
		this.info = value;
	}

	/**
	 * Gets the value of the list property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the list property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List getList() {
		if (list == null) {
			list = new ArrayList();
		}
		return this.list;
	}

	/**
	 * Gets the value of the msg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets the value of the msg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMsg(String value) {
		this.msg = value;
	}

	/**
	 * Gets the value of the ok property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOk() {
		return ok;
	}

	/**
	 * Sets the value of the ok property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOk(String value) {
		this.ok = value;
	}

}
