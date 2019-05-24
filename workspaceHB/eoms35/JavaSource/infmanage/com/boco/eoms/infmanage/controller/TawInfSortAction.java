package com.boco.eoms.infmanage.controller;

import java.util.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import org.apache.commons.beanutils.*;
import org.apache.struts.action.*;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.infmanage.dao.*;
import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.infmanage.bo.*;
//import com.boco.eoms.common.controller.SaveSessionBeanForm;
//import com.boco.eoms.jbzl.bo.TawDeptBO;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import org.apache.struts.util.LabelValueBean;

import org.apache.struts.action.Action;

public class TawInfSortAction
    extends Action
{
    private static int PAGE_LENGTH = 10;
    private String user_id = "";
    private int deptId;
    private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
        ConnectionPool.getInstance();

    public TawInfSortAction()
    {
    }

    public ActionForward execute(ActionMapping actionMapping,
                                 ActionForm actionForm
                                 , HttpServletRequest request,
                                 HttpServletResponse response)
    {
        ActionForward myforward = null;
        String myaction = actionMapping.getParameter();

        //session��ʱ����
        try
        {

    	    //	edit by wangheqi 2.7 to 3.5
   	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
   	      request.getSession().getAttribute("sessionform");
   	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
   	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
   	 	 //edit end

            if (saveSessionBeanForm == null)
                return actionMapping.findForward("timeout");
            user_id = saveSessionBeanForm.getUserid();
            deptId = Integer.parseInt(saveSessionBeanForm.getDeptid());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if ("".equalsIgnoreCase(myaction))
        {
            myforward = actionMapping.findForward("failure");
        }

        if ("ADD".equalsIgnoreCase(myaction))
        {
            myforward = performAdd(actionMapping, actionForm, request, response);
        }

        if ("SAVE".equalsIgnoreCase(myaction))
        {
            myforward = performSave(actionMapping, actionForm, request,
                                    response);
        }

        if ("LIST".equalsIgnoreCase(myaction))
        {
            myforward = performList(actionMapping, actionForm, request,
                                    response);
        }

        if ("UPDATE".equalsIgnoreCase(myaction))
        {
            myforward = performUpdate(actionMapping, actionForm, request,
                                      response);
        }

        if ("UPDATEDONE".equalsIgnoreCase(myaction))
        {
            myforward = performUpdatedone(actionMapping, actionForm, request,
                                          response);
        }

        if ("DEL".equalsIgnoreCase(myaction))
        {
            myforward = performDel(actionMapping, actionForm, request,
                                   response);
        }

        if ("DELDONE".equalsIgnoreCase(myaction))
        {
            myforward = performDeldone(actionMapping, actionForm, request,
                                       response);
        }
        return myforward;
    }

    /**
     * ��ʾ���ҳ��
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performAdd(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
    {
        TawInfSortForm form = (TawInfSortForm) actionForm;
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

        if (saveSessionBeanForm == null)
        {
            return actionMapping.findForward("timeout");
        }

        try
        {
            String sessionUserId = saveSessionBeanForm.getUserid();
            String sdomIds = "";

            if (!user_id.equalsIgnoreCase(StaticVariable.ADMIN))
            {
                //edit by wangheqi
                TawSystemAssignBo privBO = null;
            	//TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
                Vector domIds = new Vector();
                domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

                //domIds = tawVPBO.validatePriv(sessionUserId,
                //                              "/TawInfSort/add");

                if (domIds.size() <= 0)
                {
                    return actionMapping.findForward("nopriv");
                }
                else
                {
                    for (int i = 0; i < domIds.size(); i++)
                    {
                        sdomIds += domIds.get(i).toString() + ",";
                    }
                    sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
                }
            }
           form.setInfSortName("");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            actionMapping.findForward("failure");
        }
        finally
        {

        }
        return actionMapping.findForward("success");

    }

    /**
     * ִ����ӵĲ���
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performSave(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    {
        TawInfSortDAO tawInfSortDAO = new TawInfSortDAO(ds);
        TawInfSortForm form = (TawInfSortForm) actionForm;
        TawInfSort tawInfSort = new TawInfSort();
        try
        {
            HttpSession session = request.getSession();
    	    //	edit by wangheqi 2.7 to 3.5
   	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
   	      request.getSession().getAttribute("sessionform");
   	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
   	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
   	 	 //edit end

            if (saveSessionBeanForm == null)
            {
                return actionMapping.findForward("timeout");
            }

            org.apache.commons.beanutils.BeanUtils.populate(tawInfSort,
                org.apache.commons.beanutils.BeanUtils.describe(form));

            tawInfSortDAO.insert(tawInfSort);

            int sortId = tawInfSortDAO.getSortId(form.getInfSortName());
            String sortName = form.getInfSortName();

            tawInfSortDAO.insertTree(sortName, sortId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            actionMapping.findForward("failure");
        }
        finally
        {

        }
        return actionMapping.findForward("success");
    }

    /**
     * ���б����ʽ��ʾ��ѯ���
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performList(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    {
        TawInfSortForm form = (TawInfSortForm) actionForm;

        try
        {
            HttpSession httpSession = request.getSession();
    	    //	edit by wangheqi 2.7 to 3.5
   	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
   	      request.getSession().getAttribute("sessionform");
   	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
   	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
   	 	 //edit end

            if (saveSessionBeanForm == null)
            {
                return actionMapping.findForward("timeout");
            }

            String sessionUserId = saveSessionBeanForm.getUserid();
            String sdomIds = "";

            if (!user_id.equalsIgnoreCase(StaticVariable.ADMIN))
            {
                //edit by wangheqi
                TawSystemAssignBo privBO = null;
            	//TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
                Vector domIds = new Vector();
                domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

                //domIds = tawVPBO.validatePriv(sessionUserId,
                //                              "/TawInfSort/query");

                if (domIds.size() <= 0)
                {
                    return actionMapping.findForward("nopriv");
                }
                else
                {
                    for (int i = 0; i < domIds.size(); i++)
                    {
                        sdomIds += domIds.get(i).toString() + ",";
                    }
                    sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
                }
            }

            int length = PAGE_LENGTH;
            int offset;
            String pageOffset = request.getParameter("pager.offset");
            if (pageOffset == null || pageOffset.equals(""))
            {
                offset = 0;
            }
            else
            {
                offset = Integer.parseInt(pageOffset);
            }

            // �����ѯ���
            TawInfSortDAO tawInfSortDAO = new TawInfSortDAO(ds);
            ArrayList list = (ArrayList) tawInfSortDAO.getList(
                offset, length);

            int size = tawInfSortDAO.getSize("taw_inf_sort");
            String url = request.getContextPath() + "/infmanage" +
                actionMapping.getPath() + ".do";
            String pagerHeader = Pager.generate(offset, size, length, url);

            request.setAttribute("pagerHeader", pagerHeader);
            request.setAttribute("TAW_INF_SORT_LIST", list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
        return actionMapping.findForward("success");
    }

    /**
     * ��ʾ�޸������������Ϣҳ��
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performUpdate(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response)
    {
        TawInfSortForm form = (TawInfSortForm) actionForm;
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

        if (saveSessionBeanForm == null)
        {
            return actionMapping.findForward("timeout");
        }

        try
        {
            int sortId = StaticMethod.null2int(request.getParameter(
                "sortId"));
            String sdomIds = "";

            String sessionUserId = saveSessionBeanForm.getUserid();
            if (!user_id.equalsIgnoreCase(StaticVariable.ADMIN))
            {
                //edit by wangheqi
                TawSystemAssignBo privBO = null;
            	//TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
                Vector domIds = new Vector();
                domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

                //domIds = tawVPBO.validatePriv(sessionUserId,
                //                              "/TawInfSort/update");

                if (domIds.size() <= 0)
                {
                    return actionMapping.findForward("nopriv");
                }
                else
                {
                    for (int i = 0; i < domIds.size(); i++)
                    {
                        sdomIds += domIds.get(i).toString() + ",";
                    }
                    sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
                }

                boolean hasPriv = false;
                for (int i = 0; i < domIds.size(); i++)
                {
                    if (Integer.parseInt(domIds.get(i).toString()) ==
                        deptId)
                    {
                        hasPriv = true;
                        break;
                    }
                }
                if (!hasPriv)
                {
                    return actionMapping.findForward("nopriv");
                }
            }

            TawInfSortDAO tawInfSortDAO = new TawInfSortDAO(
                ds);
            TawInfSort tawInfSort = new TawInfSort();
            tawInfSort = tawInfSortDAO.getById(sortId);

            org.apache.commons.beanutils.BeanUtils.populate
                (form,
                 org.apache.commons.beanutils.BeanUtils.describe(
                tawInfSort));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            actionMapping.findForward("failure");
        }
        finally
        {

        }
        return actionMapping.findForward("success");
    }

    /**
     * ִ���޸ĵĲ���
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performUpdatedone(ActionMapping actionMapping,
                                           ActionForm actionForm,
                                           HttpServletRequest request,
                                           HttpServletResponse response)
    {
        //�����ж�Ȩ��,Updateʱ�Ѿ�������
        TawInfSortForm form = (TawInfSortForm) actionForm;
        TawInfSort tawInfSort = new TawInfSort();
        TawInfSortDAO tawInfSortDAO = new TawInfSortDAO(ds);

        try
        {
            HttpSession httpSession = request.getSession();
    	    //	edit by wangheqi 2.7 to 3.5
   	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
   	      request.getSession().getAttribute("sessionform");
   	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
   	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
   	 	 //edit end

            if (saveSessionBeanForm == null)
            {
                return actionMapping.findForward("timeout");
            }

            org.apache.commons.beanutils.BeanUtils.populate(tawInfSort,
                org.apache.commons.beanutils.BeanUtils.describe(form));
            tawInfSort.setInfSortId(form.getInfSortId());

            String sortName1 = tawInfSortDAO.getSortName(form.getInfSortId());
            tawInfSortDAO.update(tawInfSort);
            String sortName2 = tawInfSortDAO.getSortName(form.getInfSortId());
            tawInfSortDAO.updateTree(sortName1, sortName2);
        }
        catch (Exception e)
        {
            actionMapping.findForward("failure");
            e.printStackTrace();
        }
        finally
        {
            if (tawInfSort != null)
            {
                tawInfSort = null;
            }
            if (tawInfSortDAO != null)
            {
                tawInfSortDAO = null;
            }
            if (form != null)
            {
                form = null;
            }
        }
        return actionMapping.findForward("success");
    }

    /**
     * ��ʾɾ��ҳ��
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performDel(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
    {
        TawInfSortForm form = (TawInfSortForm) actionForm;

        try
        {
            HttpSession httpSession = request.getSession();
    	    //	edit by wangheqi 2.7 to 3.5
   	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
   	      request.getSession().getAttribute("sessionform");
   	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
   	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
   	 	 //edit end

            if (saveSessionBeanForm == null)
            {
                return actionMapping.findForward("timeout");
            }

            //�ж�Ȩ��
            String sessionUserId = saveSessionBeanForm.getUserid();
            String sdomIds = "";
            int sortId = StaticMethod.null2int(request.getParameter(
                "sortId"));

            if (!user_id.equalsIgnoreCase(StaticVariable.ADMIN))
            {
                //edit by wangheqi
                TawSystemAssignBo privBO = null;
            	//TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
                Vector domIds = new Vector();
                domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

                //domIds = tawVPBO.validatePriv(sessionUserId,
                //                              actionMapping.getPath());
                if (domIds.size() <= 0)
                {
                    return actionMapping.findForward("nopriv");
                }

                boolean hasPriv = false;
                for (int i = 0; i < domIds.size(); i++)
                {
                    if (Integer.parseInt(domIds.get(i).toString()) ==
                        deptId)
                    {
                        hasPriv = true;
                        break;
                    }
                }
                if (!hasPriv)
                {
                    return actionMapping.findForward("nopriv");
                }
            }

            //���KPI�ļ�¼Id�õ���ǰ��¼
            TawInfSortDAO tawInfSortDAO = new TawInfSortDAO(ds);
            TawInfSort tawInfSort = new TawInfSort();
            tawInfSort = tawInfSortDAO.getById(sortId);
            org.apache.commons.beanutils.BeanUtils.populate(form,
                org.apache.commons.beanutils.BeanUtils.describe(
                tawInfSort));

        }
        catch (Exception e)
        {
            e.printStackTrace();
            actionMapping.findForward("failure");
        }
        finally
        {

        }
        return actionMapping.findForward("success");
    }

    /**
     * ִ��ɾ�����
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performDeldone(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
    {
        TawInfSortForm form = (TawInfSortForm) actionForm;
        TawInfSortDAO tawInfSortDAO = new TawInfSortDAO(ds);

        try
        {
            HttpSession httpSession = request.getSession();
    	    //	edit by wangheqi 2.7 to 3.5
   	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
   	      request.getSession().getAttribute("sessionform");
   	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
   	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
   	 	 //edit end

            if (saveSessionBeanForm == null)
            {
                return actionMapping.findForward("timeout");
            }

            int sortId = StaticMethod.null2int(request.getParameter(
                "infSortId"));
            String sortName = StaticMethod.null2String(request.getParameter(
                "infSortName"));

            tawInfSortDAO.delete(sortId);
            tawInfSortDAO.deleteTree(sortName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            actionMapping.findForward("failure");
        }
        finally
        {
            if (tawInfSortDAO != null)
            {
                tawInfSortDAO = null;
            }
        }
        return actionMapping.findForward("success");
    }

}