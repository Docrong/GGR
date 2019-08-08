
package com.boco.eoms.sheet.softchange.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.modifytime.model.ModifyTime;
import com.boco.eoms.sheet.modifytime.service.IModifyTimeManager;
import com.boco.eoms.sheet.softchange.model.SoftChangeLink;
import com.boco.eoms.sheet.softchange.model.SoftChangeMain;

public class SoftChangeAction extends SheetAction {
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showDrawing(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("draw");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showPic(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("pic");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showKPI(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("kpi");
    }


    public ActionForward newPerformDeal(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newPerformDeal(mapping, form, request, response);


        String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
        try {

            if (taskName.equals("PlanTask")) {
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
                //SoftChangeMain main = (SoftChangeMain)baseSheet.getMainService().getMainDAO().loadSinglePO(sheetKey, baseSheet.getMainService().getMainObject());
                SoftChangeMain main = (SoftChangeMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
                List links = (List) baseSheet.getLinkService().getLinksByMainId(sheetKey);
                String local = "";
                for (int i = 0; i < links.size(); i++) {
                    SoftChangeLink link = (SoftChangeLink) links.get(i);
                    if (link.getOperateType().intValue() == 110) {
                        local = link.getLinkInvolvedCity();
                        break;
                    }
                }
                IModifyTimeManager mgr = (IModifyTimeManager) ApplicationContextHolder.getInstance().getBean("ImodifytimeManager");
                ModifyTime modifytime = new ModifyTime();
                modifytime.setBeginTime(StaticMethod.nullObject2String(request.getParameter("linkPlanStartTime")));
                modifytime.setDeleted("0");
                modifytime.setEndTime(StaticMethod.nullObject2String(request.getParameter("linkPlanEndTime")));
                modifytime.setFunctionary(StaticMethod.nullObject2String(request.getParameter("linkManager")));
                modifytime.setIntroduction("");
                modifytime.setLocal(local);
                //软件变更和网路数据管理需要保存变更网元
                modifytime.setMetNet(main.getMainCellInfo());
                modifytime.setRemarks("");
                modifytime.setSpecialtyOne(main.getMainNetTypeOne());
                modifytime.setSpecialtyTwo(main.getMainNetTypeTwo());
                modifytime.setSpecialtyThree(main.getMainNetTypeThree());
                modifytime.setTitle(main.getTitle());
                modifytime.setType("软件变更");
                mgr.saveModifyTime(modifytime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }
}
