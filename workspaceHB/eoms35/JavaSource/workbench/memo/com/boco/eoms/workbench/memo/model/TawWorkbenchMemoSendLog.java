package com.boco.eoms.workbench.memo.model;

import com.boco.eoms.base.model.BaseObject;
  
/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>  
 * <a href="TawWorkbenchMemo.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_workbench_memo"
 */
public class TawWorkbenchMemoSendLog  extends BaseObject {
	private String id;

	private String memoId;

	private String reciever;

	private String sendtime;

	private String sendmanner;
	
	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @hibernate.memoId column="memoId" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getMemoId() {
		return memoId;
	}

	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}
	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @eoms.cn name="������"
	 * @return
	 */
	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @eoms.cn name="���ͷ�ʽ"
	 * @return
	 */
	public String getSendmanner() {
		return sendmanner;
	}

	public void setSendmanner(String sendmanner) {
		this.sendmanner = sendmanner;
	}
	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @eoms.cn name="����ʱ��"
	 * @return
	 */
	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
