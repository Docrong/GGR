package com.boco.eoms.workbench.networkcalendar.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 * 
 * @struts.form name="tawSupplierkpiInfoForm"
 */
public class NetworkcalendarForm extends BaseForm implements
		java.io.Serializable {

	private String taskhours;
	private String taskminutes;
	private String id;
	private String taskname="";
	private String tasktime;
	private String taskremarks;
	
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getTasktime() {
		return tasktime;
	}
	public void setTasktime(String tasktime) {
		this.tasktime = tasktime;
	}
	public String getTaskremarks() {
		return taskremarks;
	}
	public void setTaskremarks(String taskremarks) {
		this.taskremarks = taskremarks;
	}


	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}


	


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	public String getTaskhours() {
		return taskhours;
	}
	public void setTaskhours(String taskhours) {
		this.taskhours = taskhours;
	}
	public String getTaskminutes() {
		return taskminutes;
	}
	public void setTaskminutes(String taskminutes) {
		this.taskminutes = taskminutes;
	}




	

}
