/*
 * Created on 2008-3-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.model;
import com.boco.eoms.base.model.BaseObject;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSystemSubRoleRefPost extends BaseObject{
	private String id;
	private long postId;
	private String subRoleId;
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the postId.
	 */
	public long getPostId() {
		return postId;
	}
	/**
	 * @param postId The postId to set.
	 */
	public void setPostId(long postId) {
		this.postId = postId;
	}
	/**
	 * @return Returns the subRoleId.
	 */
	public String getSubRoleId() {
		return subRoleId;
	}
	/**
	 * @param subRoleId The subRoleId to set.
	 */
	public void setSubRoleId(String subRoleId) {
		this.subRoleId = subRoleId;
	}
	/* (non-Javadoc)
	 * @see com.boco.eoms.base.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.boco.eoms.base.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
