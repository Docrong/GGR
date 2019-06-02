/**
 * 
 */
package com.boco.eoms.commons.transaction.test;

// apache library
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;

// eoms class
import com.boco.eoms.commons.transaction.test.controller.TstTransAction;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class TstTransMain extends Thread {

    private static Log log;

    private ActionForm m_objForm;

    public void run() {
        execDAO(m_objForm);
    }

    public void execDAO(ActionForm _objForm) {
        TstTransAction _objTstTransAction = new TstTransAction();

        try {
            _objTstTransAction.execute(null, _objForm, null, null);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute _objTstTransAction.execute(...), error message is: ["
                                + e.getMessage() + "]");
            }
        }
    }

    static {
        log = LogFactory
                .getLog(com.boco.eoms.commons.transaction.test.TstTransMain.class);
    }

 
    /**
     * @return the m_objForm
     */
    public ActionForm getM_objForm() {
        return m_objForm;
    }

    /**
     * @param form the m_objForm to set
     */
    public void setM_objForm(ActionForm form) {
        m_objForm = form;
    }

}
