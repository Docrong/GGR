package com.boco.eoms.commons.ui.taglib;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import com.boco.eoms.commons.loging.BocoLog;


/**
 * 
 */
public class treeTag extends TagSupport {

	private static final long serialVersionUID = 1L;	
	
	protected String name = null;

	protected String text = null;

	protected String url = null;
	
	protected boolean init = true;

	public int doEndTag() {
      try {
    	StringBuffer t = new StringBuffer();
    	    	
    	t.append("<script language=\"javascript\">");
    	t.append("var ").append(name).append(" = new WebFXLoadTree(\"").append(text).append("\", \"").append(url).append("\");");
        t.append(name).append(".write();");
    	t.append("</script>");
        
        pageContext.getOut().println(t);
        
      } catch (IOException ignored) {
    	  BocoLog.error(this, ignored.getMessage());
      }
      return EVAL_PAGE;
    }

	/**
	 * @return init
	 */
	public boolean isInit() {
		return init;
	}

	/**
	 * @param init 要设置的 init
	 */
	public void setInit(boolean init) {
		this.init = init;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text 要设置的 text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url 要设置的 url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}