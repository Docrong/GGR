package com.boco.eoms.workbench.memo.model;


import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;

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
public class TawWorkbenchMemo extends BaseObject {  
	private String  id;
	private String deleted;
	private String title;
	private String  content;
	private String userid;
	private String userName;
	private String creattime;
	private String level;
	private String  sendflag;
	private String sendtime;
	private String reciever;
	private String sendmanner;
	
	public String getSendmanner() {
		return sendmanner;
	}
	public void setSendmanner(String sendmanner) {
		this.sendmanner = sendmanner;
	}
	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @eoms.cn name="ɾ���־"
	 * @return
	 */
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @eoms.cn name="������"
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @eoms.cn name="�������"
	 * @return
	 */
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="������"
	 * @return
	 */
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="����ʱ��"
	 * @return
	 */
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @eoms.cn name="��Ҫ�����"
	 * @return ���ͱ�־
	 */
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @eoms.cn name="���ͱ�־"
	 * @return 
	 */
	public String getSendflag() {
		return sendflag;
	}
	public void setSendflag(String sendflag) {
		this.sendflag = sendflag;
	}
	/**
	 * @hibernate.property length="50"
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
	/**
	 * @hibernate.property length="50"
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
	public String getUserName() {
		userName=DictMgrLocator.getId2NameService().id2Name(
				this.userid, "tawSystemUserDao");
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
