package com.boco.eoms.workbench.contact.util;

/**
 * <p>
 * Title:个人通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 15:59:30 AM
 * </p>
 * 
 * @author 龚玉峰
 * @version 3.5.1
 * 
 */
import org.apache.struts.action.ActionForm; 
import org.apache.struts.upload.FormFile; 
import org.apache.struts.action.ActionErrors; 
import org.apache.struts.action.ActionMapping; 
import javax.servlet.http.HttpServletRequest; 

public class FileInfo extends ActionForm { 
private FormFile thisFile; 
public FormFile getThisFile() { 
return thisFile; 
} 

public void setThisFile(FormFile thisFile) { 
this.thisFile = thisFile; 
} 

public ActionErrors validate(ActionMapping actionMapping, 
HttpServletRequest httpServletRequest) { 
/** @todo: finish this method, this is just the skeleton.*/ 
return null; 
} 

public void reset(ActionMapping actionMapping, 
HttpServletRequest servletRequest) { 
} 
} 
