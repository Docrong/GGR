package com.boco.eoms.base.webapp.taglib;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ExportExcelTag extends BodyTagSupport {
 
  /** 应用模块编号*/
  private String sql= "";
  
  private String path;
   
  public int doStartTag() {
	javax.servlet.ServletRequest request = pageContext.getRequest();
	path = ((javax.servlet.http.HttpServletRequest)request).getContextPath();
    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag() throws JspTagException {

    try {
      String html = "";
        html = "<iframe id=\"UIFrame1-\" name=\"UIFrame1-\" class=\"uploadframe\" frameborder=\"0\" "
            + "scrolling=\"auto\" src=\"" + path + "/exportexcel/export.jsp?sql=" + sql
            + "\" style=\"height:80%;width:100%\"></iframe>";
      pageContext.getOut().write(html); 
    }
    catch (Exception e) {
    }
    return EVAL_PAGE;
  }

public String getSql() {
	return sql;
}

public void setSql(String sql) {
	this.sql = sql;
}

}