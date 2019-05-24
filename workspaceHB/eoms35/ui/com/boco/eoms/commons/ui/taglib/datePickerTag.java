package com.boco.eoms.commons.ui.taglib;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;
import com.boco.eoms.commons.loging.BocoLog;


/**
 * 
 */
public class datePickerTag extends TagSupport {

	private static final long serialVersionUID = 1L;	
	
	protected String bindingto = null;

	protected String format = "yyyy-mm-dd";

	protected boolean showtime = true;

	public int doEndTag() {
      try {
    	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
    	String base = request.getContextPath()+"/scripts/form";
    	String showTimeStr = "";
    	
    	if(showtime){
    	  showTimeStr = "";
    	}
    	else {
    	  showTimeStr = ",false";
    	}
    	
    	StringBuffer t = new StringBuffer();
    	    	
    	t.append("<img src=\""+base+"/img/date.gif\" alt=\"\" ");
    	t.append("onclick=\"javascript:popUpCalendar(this, $('"+bindingto+"'), '"+format+"', -1,-1"+showTimeStr+")\"/>");
        
        pageContext.getOut().println(t);
        
      } catch (IOException ignored) { 
    	  BocoLog.error(this, ignored.getMessage());
      }
      return EVAL_PAGE;
    }

	public String getBindingto() {
		return bindingto;
	}

	public void setBindingto(String bindingto) {
		this.bindingto = bindingto;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean getShowtime() {
		return showtime;
	}

	public void setShowtime(boolean showtime) {
		this.showtime = showtime;
	}

	
}