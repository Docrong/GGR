package com.boco.eoms.workplan.model;

/**
 * <p>Title: 执行作业计划附件类</p>
 * <p>Description: 执行作业计划考核类信息，其中包括考核人，考核部门，审批内容，状态，时间等</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.io.*;
import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class
 * table="TAW_WP_EXECUTEFILE"
 */

public class TawwpExecuteFile
    implements Serializable, DataObject {

  private String id; //标识
  private String fileName; //文件名
  private String fileCodeName; //文件存储名
  private String fileSize; //文件长度
  private String crtime; //创建时间
  private String cruser; //创建人
  private TawwpExecuteContent tawwpExecuteContent=new TawwpExecuteContent(); //执行作业计划执行内容
  private TawwpExecuteContentUser tawwpExecuteContentUser=new TawwpExecuteContentUser(); //执行作业计划执行内容(个人)

  public TawwpExecuteFile() {
  }

  public TawwpExecuteFile(String _fileName, String _fileCodeName,
                          String _fileSize, String _crtime, String _cruser,
                          TawwpExecuteContent _tawwpExecuteContent,
                          TawwpExecuteContentUser _tawwpExecuteContentUser) {
    this.fileName = _fileName;
    this.fileCodeName = _fileCodeName;
    this.fileSize = _fileSize;
    this.crtime = _crtime;
    this.cruser = _cruser;
    this.tawwpExecuteContent = _tawwpExecuteContent;
    this.tawwpExecuteContentUser = _tawwpExecuteContentUser;
  }

  /**
   * @hibernate.id
   * column="ID"
   * length="32"
   * unsaved-value="null"
   * generator-class="uuid.hex"
   */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * @hibernate.property
   * column="FILENAME"
   * length="250"
   * not-null="true"
   * update="false"
   */
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * @hibernate.property
   * column="FILECODENAME"
   * length="50"
   * not-null="true"
   * update="false"
   */
  public String getFileCodeName() {
    return fileCodeName;
  }

  public void setFileCodeName(String fileCodeName) {
    this.fileCodeName = fileCodeName;
  }

  /**
   * @hibernate.property
   * column="FILESIZE"
   * length="15"
   * not-null="true"
   * update="false"
   */
  public String getFileSize() {
    return fileSize;
  }

  public void setFileSize(String fileSize) {
    this.fileSize = fileSize;
  }

  /**
   * @hibernate.property
   * column="CRUSER"
   * length="20"
   * not-null="true"
   * update="false"
   */
  public String getCruser() {
    return cruser;
  }

  public void setCruser(String cruser) {
    this.cruser = cruser;
  }

  /**
   * @hibernate.property
   * column="CRTIME"
   * length="19"
   * not-null="true"
   * update="false"
   */
  public String getCrtime() {
    return crtime;
  }

  public void setCrtime(String crtime) {
    this.crtime = crtime;
  }

  /**
   * @hibernate.many-to-one
   * column="EXECUTE_CONTENT_ID"
   * cascade="none"
   * not-null="true"
   */
  public TawwpExecuteContent getTawwpExecuteContent() {
    return tawwpExecuteContent;
  }
  public void setTawwpExecuteContent(TawwpExecuteContent tawwpExecuteContent) {
    this.tawwpExecuteContent = tawwpExecuteContent;
  }

  /**
   * @hibernate.many-to-one
   * column="EXECUTE_CONTENT_USER_ID"
   * cascade="none"
   * not-null="true"
   */
  public TawwpExecuteContentUser getTawwpExecuteContentUser() {
    return tawwpExecuteContentUser;
  }
  public void setTawwpExecuteContentUser(TawwpExecuteContentUser tawwpExecuteContentUser) {
    this.tawwpExecuteContentUser = tawwpExecuteContentUser;
  }

}
