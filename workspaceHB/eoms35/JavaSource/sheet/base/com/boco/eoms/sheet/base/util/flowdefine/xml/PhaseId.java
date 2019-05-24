/*
 * Created on 2007-11-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util.flowdefine.xml;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PhaseId {
	private ToPhaseId toPhaseId[];

	/**
	 * @return Returns the toPhaseId.
	 */
	public ToPhaseId[] getToPhaseId() {
		return toPhaseId;
	}

	/**
	 * @param toPhaseId
	 *            The toPhaseId to set.
	 */
	public void setToPhaseId(ToPhaseId[] toPhaseId) {
		this.toPhaseId = toPhaseId;
	}
	
	/**
	 * 步骤ID，如果是receive或者event，则为receive或者event的name
	 */
	private String id;
	
	/**
	 * type is 
	 * create 新增流程
	 * operate 普通的人工任务
	 * invoke 需要调用接口的任务任务
	 * event event的接口
	 */
	private String type;
	
	private String name;
	
	
	/**
	 * 是否将移交改为转审或者会审
	 * true : 显示
	 * false : 不显示
	 */
	private String displaytransferaudit;
	
	/**
	 * 是否显示确认操作
	 * true : 显示
	 * false : 不显示
	 */
	private String displayconfirm;
	
	
	/**
	 * 是否显示驳回操作
	 * true : 显示
	 * false : 不显示
	 */
	private String displayreject;
	
	
	/**
	 * 是否显示公共操操
	 * false : 不显示
	 * transference,phaseReply,split 表示为显示的操作
	 */
	private String displaycommonoperate;
	
	


	public String getDisplaycommonoperate() {
		return displaycommonoperate;
	}

	public void setDisplaycommonoperate(String displaycommonoperate) {
		this.displaycommonoperate = displaycommonoperate;
	}

	public String getDisplayconfirm() {
		return displayconfirm;
	}

	public void setDisplayconfirm(String displayconfirm) {
		this.displayconfirm = displayconfirm;
	}

	public String getDisplayreject() {
		return displayreject;
	}

	public void setDisplayreject(String displayreject) {
		this.displayreject = displayreject;
	}

	public String getDisplaytransferaudit() {
		return displaytransferaudit;
	}

	public void setDisplaytransferaudit(String displaytransferaudit) {
		this.displaytransferaudit = displaytransferaudit;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return 步骤ID.
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
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public List getToPhasesByPhaseId()throws Exception{
		List list = new ArrayList();
		if(this.getToPhaseId()!=null && this.getToPhaseId().length>0){
			for(int i=0;i<this.getToPhaseId().length;i++){
				list.add(this.getToPhaseId()[i]);
			}
		}
		return list;		
	}
}
