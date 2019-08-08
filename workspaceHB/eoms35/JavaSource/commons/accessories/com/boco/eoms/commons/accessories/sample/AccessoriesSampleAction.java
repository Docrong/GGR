package com.boco.eoms.commons.accessories.sample;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;

/**
 * <p>Title:附件管理组件sample类
 * </p>
 * <p>Description:
 * </p>
 * <p>Apr 23, 2007 10:24:17 AM</p>
 *
 * @author 秦敏
 * @version 1.0
 */
public final class AccessoriesSampleAction extends BaseAction {
    /**
     * 附件下载
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author 秦敏
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) getBean("ItawCommonsAccessoriesManager");
        List filelist = mgr.getTawCommonsAccessoriess();
        String files = "";
        for (int i = 0; i < filelist.size(); i++) {
            TawCommonsAccessories temp = (TawCommonsAccessories) filelist.get(i);
            files = files + "," + temp.getAccessoriesName();
        }
        if (files.indexOf(",") == 0) files = files.substring(1);
        TawCommonsAccessories file = new TawCommonsAccessories();
        files = "'20080917165455.xls','20080917165459.doc'";
        file.setAccessoriesName(files);
        request.setAttribute("file", file);
        return mapping.findForward("view");
    }

    /**
     * 附件上传
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author 秦敏
     */
    public ActionForward upload(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("upload");
    }
} 

