package com.boco.eoms.workbench.netdisk.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 * 
 * @struts.form name="tawSupplierkpiInfoForm"
 */
public class TawWorkbenchFolderForm extends BaseForm implements
		java.io.Serializable {

	protected String folderName;

	protected String folderPath;
	

	public String getFolderName() {
		return folderName;
	}



	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}



	public String getFolderPath() {
		return folderPath;
	}



	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}



	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

}