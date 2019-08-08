package com.boco.eoms.knowledge.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.knowledge.service.KnowledgeClient;

public class KnowledgeAction extends BaseAction {
    public ActionForward sysKnowledgeUser(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KnowledgeClient.loadSynUserService().synchroniseUser();
        return mapping.findForward("success");
    }

    public ActionForward uploadFileToKnowledge(ActionMapping mapping, ActionForm form,
                                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fileURL = "http://10.32.2.5:9080/eoms35/测试上传知识库文件.doc";
        String categoryId = "3D11C030-DA39-0844-74FF-E66621799B76";
        String userId = "gzjdr";
        KnowledgeClient.loadUploadFileService().uploadByWebservice(fileURL, categoryId, userId);
        return mapping.findForward("success");
    }

    public ActionForward searchKnowledge(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String url = XmlManage.getFile("/config/knowledge-config.xml").getProperty("searchKnowledgeUrl");
        if (url.indexOf("?") > 0)
            url += "&";
        else
            url += "?";
        url += "userName=" + sessionform.getUserid();

        ActionForward af = new ActionForward();
        af.setPath(url);
        af.setRedirect(true);
        return af;
    }

    public ActionForward showKnowledgeIndexPage(ActionMapping mapping, ActionForm form,
                                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String url = XmlManage.getFile("/config/knowledge-config.xml").getProperty("knowledgeIndexPageUrl");
        if (url.indexOf("?") > 0)
            url += "&";
        else
            url += "?";
        url += "userName=" + sessionform.getUserid();

        ActionForward af = new ActionForward();
        af.setPath(url);
        af.setRedirect(true);
        return af;
    }
}
