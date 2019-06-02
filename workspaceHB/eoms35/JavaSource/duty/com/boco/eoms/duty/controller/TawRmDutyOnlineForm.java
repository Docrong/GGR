package com.boco.eoms.duty.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class TawRmDutyOnlineForm   extends ActionForm{
	  private FormFile thisFile; 
	  public FormFile getThisFile() { 
	  return thisFile; 
	  } 

	  public void setThisFile(FormFile thisFile) { 
	  this.thisFile = thisFile; 
	  }

}
