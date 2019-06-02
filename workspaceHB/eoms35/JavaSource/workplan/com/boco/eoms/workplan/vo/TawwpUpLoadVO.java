package com.boco.eoms.workplan.vo;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;
import org.apache.struts.upload.*;

public class TawwpUpLoadVO
    extends ActionForm {
  public static final String ERROR_PROPERTY_MAX_LENGTH_EXCEEDED =
      "org.apache.struts.webapp.upload.MaxLengthExceeded";
  protected FormFile theFile;
  public FormFile getTheFile() {
    return theFile;
  }

  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }

  public ActionErrors validate(ActionMapping mapping,
                               HttpServletRequest request) {
    ActionErrors errors = null;
//has the maximum length been exceeded?
    Boolean maxLengthExceeded = (Boolean)
        request.getAttribute(MultipartRequestHandler.
                             ATTRIBUTE_MAX_LENGTH_EXCEEDED);
    if ( (maxLengthExceeded != null) && (maxLengthExceeded.booleanValue())) {
      errors = new ActionErrors();
      errors.add(ERROR_PROPERTY_MAX_LENGTH_EXCEEDED,
                 new ActionError("maxLengthExceeded"));
    }
    return errors;

  }
}//这是相对应的form，还有其他属性可以设置，具体可以参考struts的上传例子。
