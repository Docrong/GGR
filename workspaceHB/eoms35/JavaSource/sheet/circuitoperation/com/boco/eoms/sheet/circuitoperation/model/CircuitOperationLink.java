
package com.boco.eoms.sheet.circuitoperation.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;

public class CircuitOperationLink extends BaseLink
{
  private String linkZContact;
  
  private String linkZContactTel;
  
  private String linkOperationDesc;

public String getLinkOperationDesc() {
	return linkOperationDesc;
}

public void setLinkOperationDesc(String linkOperationDesc) {
	this.linkOperationDesc = linkOperationDesc;
}

public String getLinkZContact() {
	return linkZContact;
}

public void setLinkZContact(String linkZContact) {
	this.linkZContact = linkZContact;
}

public String getLinkZContactTel() {
	return linkZContactTel;
}

public void setLinkZContactTel(String linkZContactTel) {
	this.linkZContactTel = linkZContactTel;
}
}
