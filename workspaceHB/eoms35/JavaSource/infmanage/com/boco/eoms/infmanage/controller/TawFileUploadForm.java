package com.boco.eoms.infmanage.controller;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class TawFileUploadForm extends ValidatorForm {
  public final static int ADD = 1;
  public final static int DEL = 2;
  private int strutsAction;
  private String strutsButton = "";
  private FormFile attachName;
  private String attachment;

  public int getStrutsAction() {
    return strutsAction;
  }
  public String getStrutsButton() {
    return strutsButton;
  }

  public FormFile getAttachName() {
    return attachName;
  }
  public void setAttachName(FormFile attachName) {
    this.attachName = attachName;
  }

  public String getAttachment() {
    return attachment;
  }
  public void setAttachment(String attachName) {
    this.attachment = attachment;
  }
}
