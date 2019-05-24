package com.boco.eoms.attachment.model;

public class TawAttachment {
  private int attachmentId;
  private String attachmentName;
  private String attachmentFilename;
  private int size;
  private String cruser;
  private String crtime;
  private int appId;

  public TawAttachment() {
  }
  public int getAppId() {
    return appId;
  }
  public void setAppId(int appId) {
    this.appId = appId;
  }
  public String getAttachmentFilename() {
    return attachmentFilename;
  }
  public void setAttachmentFilename(String attachmentFilename) {
    this.attachmentFilename = attachmentFilename;
  }
  public void setAttachmentId(int attachmentId) {
    this.attachmentId = attachmentId;
  }
  public int getAttachmentId() {
    return attachmentId;
  }
  public String getAttachmentName() {
    return attachmentName;
  }
  public void setAttachmentName(String attachmentName) {
    this.attachmentName = attachmentName;
  }
  public String getCrtime() {
    return crtime;
  }
  public void setCrtime(String crtime) {
    this.crtime = crtime;
  }
  public String getCruser() {
    return cruser;
  }
  public void setCruser(String cruser) {
    this.cruser = cruser;
  }
  public int getSize() {
    return size;
  }
  public void setSize(int size) {
    this.size = size;
  }

}