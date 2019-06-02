package com.boco.eoms.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import javax.servlet.jsp.*;
import java.util.*;

public class AttachmentTag
    extends BodyTagSupport {
  private String idList = "";

  private String idField = "";

  private String viewFlag = "";

  private String appCode = "";

  private String ids = "";

  protected String name;

  protected String property;

  protected String scope;

  protected String path;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

/*  public int doStartTag() {
    javax.servlet.ServletRequest request = pageContext.getRequest();
    if (request.getParameter(this.idList) != null) {
      this.ids = request.getParameter(this.idList);
    }
    else {
      this.ids = "";
    }
    try {
      if(name !=null && property !=null && scope !=null){
        this.ids = (String) RequestUtils.lookup(this.pageContext, name,
                                                property, scope);
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }

    return EVAL_BODY_INCLUDE;
  }
*/

public int doStartTag() {
  javax.servlet.ServletRequest request = pageContext.getRequest();

  path = ((javax.servlet.http.HttpServletRequest)request).getContextPath();


  if (request.getParameter(this.idList) != null) {
    this.ids = request.getParameter(this.idList);
  }
  else {
    this.ids = "";
  }
  try {
    if(name !=null && property !=null && scope !=null){
      this.ids = (String) RequestUtils.lookup(this.pageContext, name,
                                              property, scope);
    }
  }

  catch (Exception e) {
    e.printStackTrace();
  }

  return EVAL_BODY_INCLUDE;
}

 /* public int doEndTag() throws JspTagException {

    try {
      String html = "";
      if (!viewFlag.equals("Y")) {
        html = "<IFRAME ID=IFrame1  FRAMEBORDER=0 width='100%' height=100 "
            + "SCROLLING=yes SRC='../../attachment/do_upload.jsp?app=" + appCode
            + "&idfile=" + idField + "&filelist=" + ids + "'></IFRAME><input type='hidden' "
            + "name='" + idField + "'>";
      }
      else {
        html = "<IFRAME ID=IFrame1  FRAMEBORDER=0 width='100%' height=100 "
            + "SCROLLING=yes SRC='../../attachment/do_view.jsp?app=" + appCode
            + "&idfile=" + idField + "&filelist=" + ids + "'></IFRAME>";
      }
      pageContext.getOut().write(html);
    }
    catch (Exception e) {
    }
    return EVAL_PAGE;
  }
*/

 public int doEndTag() throws JspTagException {

    try {
      String html = "";
      if (!viewFlag.equals("Y")) {
        html = "<IFRAME ID=IFrame1  FRAMEBORDER=0 width='100%' height=100 "
            + "SCROLLING=yes SRC='" + path + "/attachment/do_upload.jsp?app=" + appCode
            + "&idfile=" + idField + "&filelist=" + ids + "'></IFRAME><input type='hidden' "
            + "name='" + idField + "'>";
      }
      else {
        html = "<IFRAME ID=IFrame1  FRAMEBORDER=0 width='100%' height=100 "
            + "SCROLLING=yes SRC='" + path + "/attachment/do_view.jsp?app=" + appCode
            + "&idfile=" + idField + "&filelist=" + ids + "'></IFRAME>";
      }
      pageContext.getOut().write(html);
    }
    catch (Exception e) {
    }
    return EVAL_PAGE;
  }

  public String getIdList() {
    return idList;
  }

  public void setIdList(String idList) {
    this.idList = idList;
  }

  public String getViewFlag() {
    return viewFlag;
  }

  public void setViewFlag(String viewFlag) {
    this.viewFlag = viewFlag;
  }

  public String getIdField() {
    return idField;
  }

  public void setIdField(String idField) {
    this.idField = idField;
  }

  public String getAppCode() {
    return appCode;
  }

  public void setAppCode(String appCode) {
    this.appCode = appCode;
  }

}
