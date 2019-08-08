
package com.boco.eoms.bureaudata.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.bureaudata.model.TawBureauData;
import com.boco.eoms.bureaudata.service.ITawBureauDataManager;
import com.boco.eoms.bureaudata.util.Constacts;
import com.boco.eoms.bureaudata.webapp.form.TawBureauDataForm;

import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.taskplan.model.Taskplan;
import com.boco.eoms.taskplan.service.ITaskplanManager;
import com.boco.eoms.taskplan.webapp.form.TaskplanForm;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
import com.boco.eoms.workbench.memo.service.ITawWorkbenchMemoManager;

/**
 * Action class to handle CRUD on a TawBureauData object
 *
 * @struts.action name="tawBureauDataForm" path="/tawBureauDatas" scope="request"
 * validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="main"
 * path="/WEB-INF/pages/tawBureauData/tawBureauDataTree.jsp" contextRelative="true"
 * @struts.action-forward name="edit"
 * path="/WEB-INF/pages/tawBureauData/tawBureauDataForm.jsp" contextRelative="true"
 * @struts.action-forward name="list"
 * path="/WEB-INF/pages/tawBureauData/tawBureauDataList.jsp" contextRelative="true"
 */
public final class TawBureauDataAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {
        //return mapping.findForward("search");
        return null;
    }

    public ActionForward main(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("main");
    }

    /**
     * 根据父节点的id得到�?有子节点的JSON数据
     */
    public void xGetChildNodes(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = request.getParameter("node");

        ITawBureauDataManager mgr = (ITawBureauDataManager) getBean("ItawBureauDataManager");
        JSONArray json = mgr.xGetChildNodes(nodeId);

        JSONUtil.print(response, json.toString());
    }

    /**
     * ajax保存
     */
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        TawBureauDataForm tawBureauDataForm = (TawBureauDataForm) form;

        ITawBureauDataManager mgr = (ITawBureauDataManager) getBean("ItawBureauDataManager");
        TawBureauData tawBureauData = (TawBureauData) convert(tawBureauDataForm);
        tawBureauData.setCruser(sessionform.getUserid());
        String creattime = StaticMethod.getLocalString();
        tawBureauData.setCrusdata(creattime);
        mgr.saveTawBureauData(tawBureauData);

        return mapping.findForward("success");
    }

    /**
     * 根据模块或功能的编码，删除该对象
     */
    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TawBureauDataForm tawBureauDataForm = (TawBureauDataForm) form;

        ITawBureauDataManager mgr = (ITawBureauDataManager) getBean("ItawBureauDataManager");
        mgr.removeTawBureauData(tawBureauDataForm.getId());
        return mapping.findForward("success");

    }

    /**
     * ajax请求修改某节点的详细信息
     */
    public ActionForward xedit(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        TawBureauDataForm tawBureauDataForm = (TawBureauDataForm) form;

        if (tawBureauDataForm.getId() != null) {
            ITawBureauDataManager mgr = (ITawBureauDataManager) getBean("ItawBureauDataManager");
            TawBureauData tawBureauData = (TawBureauData) convert(tawBureauDataForm);
            tawBureauData.setCruser(sessionform.getUserid());
            String creattime = StaticMethod.getLocalString();
            tawBureauData.setCrusdata(creattime);
            mgr.saveTawBureauData(tawBureauData);
            //mgr.updateTawBureauData(tawBureauData);
        }

        return mapping.findForward("success");
    }

    /**
     * ajax请求获取某节点的详细信息
     */
    public void xget(ActionMapping mapping, ActionForm form,
                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String _strId = request.getParameter("id");
        ITawBureauDataManager mgr = (ITawBureauDataManager) getBean("ItawBureauDataManager");
        TawBureauData tawBureauData = mgr.getTawBureauData(_strId);

        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(tawBureauData);

        JSONUtil.print(response, jsonRoot.toString());
    }

    public ActionForward xsearch(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // --------------用于分页，得到当前页号-------------
        try {
            final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
            String pageIndexName = new org.displaytag.util.ParamEncoder(
                    Constacts.TAW_BUREAUDATALIST)
                    .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

            final Integer pageIndex = new Integer(GenericValidator
                    .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                    : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

            // 得到查询的条件
            String whereStr = " where 1=1";
            TawBureauData tawBureauData = null;
            ITawBureauDataManager mgr = (ITawBureauDataManager) getBean("ItawBureauDataManager");
            ITawSystemUserManager usermgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
            Map map = (Map) mgr.getTawBureauDatas(pageIndex,
                    pageSize, whereStr);

            List list = (List) map.get("result");
            List BureauDatalist = new ArrayList();
            for (Iterator it = list.iterator(); it.hasNext(); ) {
                tawBureauData = (TawBureauData) it.next();
                String producer = usermgr.id2Name(tawBureauData.getProducer());
                tawBureauData.setProducer(producer);
                String auditman = usermgr.id2Name(tawBureauData.getAuditman());
                tawBureauData.setAuditman(auditman);
                String cruer = usermgr.id2Name(tawBureauData.getCruser());
                tawBureauData.setCruser(cruer);
                tawBureauData.setBigNet(DictMgrLocator.getId2NameService().id2Name(tawBureauData.getBigNet(), "ItawSystemDictTypeDao"));
                tawBureauData.setNet(DictMgrLocator.getId2NameService().id2Name(tawBureauData.getNet(), "ItawSystemDictTypeDao"));
                tawBureauData.setFactory(DictMgrLocator.getId2NameService().id2Name(tawBureauData.getFactory(), "ItawSystemDictTypeDao"));
                tawBureauData.setType(DictMgrLocator.getId2NameService().id2Name(tawBureauData.getType(), "ItawSystemDictTypeDao"));

                BureauDatalist.add(tawBureauData);
            }

            // 分页

            request.setAttribute(Constacts.TAW_BUREAUDATALIST, BureauDatalist);
            request.setAttribute("resultSize", map.get("total"));
            request.setAttribute("pageSize", pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("list");
    }


    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawBureauDataForm tawBureauDataForm = (TawBureauDataForm) form;
        try {

            String id = StaticMethod.nullObject2String(request
                    .getParameter("id"));
            if (tawBureauDataForm.getId() != null) {
                ITawBureauDataManager mgr = (ITawBureauDataManager) getBean("ItawBureauDataManager");
                ITawSystemUserManager usermgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
                if (id != null && !id.equals("")) {
                    TawBureauData tawBureauData = mgr.getTawBureauData(id);
                    String producer = usermgr.id2Name(tawBureauData.getProducer());
                    //tawBureauData.setProducer(producer);
                    String auditman = usermgr.id2Name(tawBureauData.getAuditman());
                    //tawBureauData.setAuditman(auditman);
                    tawBureauDataForm = (TawBureauDataForm) convert(tawBureauData);
                    tawBureauDataForm.setAuditmanName(auditman);
                    tawBureauDataForm.setProducerName(producer);
                }
            }

            updateFormBean(mapping, request, tawBureauDataForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("edit");
    }
}
