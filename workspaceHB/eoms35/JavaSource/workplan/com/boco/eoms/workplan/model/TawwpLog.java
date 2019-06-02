package com.boco.eoms.workplan.model;

/**
 * <p>Title: 作业计划日志类</p>
 * <p>Description: 作业计划日志类信息，其中包括执行人，执行时间，执行内容等</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.io.*;
import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class
 * table="TAW_WP_LOG"
 */

public class TawwpLog
    implements Serializable, DataObject {

  private String id; //标识
  private String cruser; //执行人
  private String crtime; //执行时间
  private String content; //执行内容
  private String logType; //执行类型

  public TawwpLog() {
  }

  public TawwpLog(String _cruser, String _crtime, String _content,
                  String _logType) {
    this.cruser = _cruser;
    this.crtime = _crtime;
    this.content = _content;
    this.logType = _logType;
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
   * not-null="false"
   * update="false"
   */
  public String getCrtime() {
    return crtime;
  }

  public void setCrtime(String crtime) {
    this.crtime = crtime;
  }

  /**
   * @hibernate.property
   * column="CONTENT"
   * length="200"
   * not-null="false"
   * update="false"
   */
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  /**
   * @hibernate.property
   * column="LOGTYPE"
   * length=1"
   * not-null="true"
   * update="false"
   */
  public String getLogType() {
    return logType;
  }

  public void setLogType(String logType) {
    this.logType = logType;
  }

  /**
   * 生成当前日志对象的xml字符串
   * @return String xml字符串
   */
  public String writeXML() {
    StringBuffer str = null;

    str = new StringBuffer();

    str.append("<VALUE>\n");
    str.append("<ID>");
    str.append(this.getId());
    str.append("</ID>\n");
    str.append("<CRTIME>");
    str.append(this.getCrtime());
    str.append("</CRTIME>\n");
    str.append("<CRUSER>");
    str.append(this.getCruser());
    str.append("</CRUSER>\n");
    str.append("<LOGCONTENT>");
    str.append(this.getContent());
    str.append("</LOGCONTENT>\n");
    str.append("<LOGTYPE>");
    str.append(this.getLogType());
    str.append("</LOGTYPE>\n");
    str.append("</VALUE>\n");
    str.append("\n");

    return str.toString();
  }

}
