package com.boco.eoms.infmanage.controller;

import java.util.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import org.apache.commons.beanutils.*;
import org.apache.struts.action.*;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.infmanage.dao.*;
import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.infmanage.bo.*;
//import com.boco.eoms.common.controller.SaveSessionBeanForm;
//import com.boco.eoms.jbzl.bo.TawDeptBO;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import org.apache.struts.util.LabelValueBean;

public class TawInfIpAction
    extends Action
{
    private static int PAGE_LENGTH = 10;
    private String user_id = "";
    private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
        ConnectionPool.getInstance();

    public TawInfIpAction()
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

        if ("QUERY".equalsIgnoreCase(myaction))
        {
            myforward = performQuery(actionMapping, actionForm, request,
                                     response);
        }

        if ("LIST".equalsIgnoreCase(myaction))
        {
            myforward = performList(actionMapping, actionForm, request,
                                    response);
        }

        if ("VIEW".equalsIgnoreCase(myaction))
        {
            myforward = performView(actionMapping, actionForm, request,
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
     * ��ʾ��ӵ�ҳ��
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    private ActionForward performAdd(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    {
        TawInfIpForm form = (TawInfIpForm) actionForm;
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
                //                              "/TawInfIp/add");

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

            request.setAttribute("SDOMIDS", sdomIds);

            //edit by wangheqi 2.7 to 3.5
            //TawDeptBO tawBOO = new TawDeptBO(ds);
            Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(),"-1");
            request.setAttribute("REGIONID", String.valueOf(regionId));
            form.setDeptName("");
            form.setUserId("");
            form.setUserName("");
            form.setUserAddress("");
            form.setUserTel("");
            form.setUserType("");
            form.setDevPort("");
            form.setDevId("");
            form.setDeptId(0);
            form.setUserLogic("");
            form.setLogicPort("");
            form.setRemark("");
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
    private ActionForward performSave(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
    {
        TawInfIpDAO tawInfIpDAO = new TawInfIpDAO(ds);
        String deptId = request.getParameter("dept_Id");
        TawInfIpForm form = (TawInfIpForm) actionForm;
        TawInfIp tawInfIp = new TawInfIp();
        form.setDeptId(new Integer(deptId).intValue());
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

            org.apache.commons.beanutils.BeanUtils.populate(tawInfIp,
                org.apache.commons.beanutils.BeanUtils.describe(form));

            tawInfIpDAO.insert(tawInfIp);
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
     * ��ʾ��ѯ��ҳ��
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    private ActionForward performQuery(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response)
    {
        TawInfIpForm form = (TawInfIpForm) actionForm;
        try
        {
            request.getSession().removeAttribute("tawInfIpForm");
    	    //	edit by wangheqi 2.7 to 3.5
   	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
   	      request.getSession().getAttribute("sessionform");
   	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
   	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
   	 	 //edit end

            if (saveSessionBeanForm == null)
                return actionMapping.findForward("timeout");

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
                //                              "/TawInfIp/query");

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

            request.setAttribute("SDOMIDS", sdomIds);
            form.setDeptName("");
            //edit by wangheqi 2.7 to 3.5
            //TawDeptBO tawBOO = new TawDeptBO(ds);
            Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(),"-1");
            request.setAttribute("REGIONID", String.valueOf(regionId));
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
    private ActionForward performList(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
    {
        TawInfIpForm form = (TawInfIpForm) actionForm;

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
                //                              "/TawInfIp/query");

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
            TawInfIpBO tawInfIpBO = new TawInfIpBO(ds);
            String condition = tawInfIpBO.getQueryCondition(form, sdomIds);

            TawInfIpDAO tawInfIpDAO = new TawInfIpDAO(ds);
            ArrayList list = (ArrayList) tawInfIpDAO.getList(condition,
                offset, length);

            int size = tawInfIpDAO.getSize("taw_inf_ip",
                                           condition);
            String url = request.getContextPath() + "/infmanage" +
                actionMapping.getPath() + ".do";
            String pagerHeader = Pager.generate(offset, size, length, url);

            request.setAttribute("pagerHeader", pagerHeader);
            request.setAttribute("TAW_INF_IP_LIST", list);
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
     * �鿴�û�IP��ַ����
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    private ActionForward performView(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
    {
        TawInfIpForm form = (TawInfIpForm) actionForm;
        TawInfIpDAO tawInfIpDAO = new TawInfIpDAO(ds);
        TawInfIp tawInfIp = new TawInfIp();
        try
        {
            // �жϳ�ʱ
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

            //��
            int id = StaticMethod.null2int(request.getParameter(
                "id"));
            tawInfIp = tawInfIpDAO.getById(id);
            org.apache.commons.beanutils.BeanUtils.populate(form,
                org.apache.commons.beanutils.BeanUtils.describe(
                tawInfIp));
            form.setLogicPort(tawInfIp.getLogicPort());
            System.out.println(form.getLogicPort());
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
     * ��ʾ�޸�ҳ��
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    private ActionForward performUpdate(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
    {
        TawInfIpForm form = (TawInfIpForm) actionForm;
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
            int id = StaticMethod.null2int(request.getParameter(
                "id"));
            int deptId = StaticMethod.null2int(request.getParameter("deptId"));
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
                //                              "/TawInfIp/update");

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

            TawInfIpDAO tawInfIpDAO = new TawInfIpDAO(
                ds);
            TawInfIp tawInfIp = new TawInfIp();
            tawInfIp = tawInfIpDAO.getById(id);

            org.apache.commons.beanutils.BeanUtils.populate
                (form,
                 org.apache.commons.beanutils.BeanUtils.describe(
                tawInfIp));
            form.setLogicPort(tawInfIp.getLogicPort());
            System.out.println(form.getLogicPort());
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
    private ActionForward performUpdatedone(ActionMapping actionMapping,
                                            ActionForm actionForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response)
    {
        //�����ж�Ȩ��,Updateʱ�Ѿ�������
        TawInfIpForm form = (TawInfIpForm) actionForm;
        TawInfIp tawInfIp = new TawInfIp();
        TawInfIpDAO tawInfIpDAO = new TawInfIpDAO(ds);

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

            org.apache.commons.beanutils.BeanUtils.populate(tawInfIp,
                org.apache.commons.beanutils.BeanUtils.describe(form));
            tawInfIpDAO.update(tawInfIp);
            form.setUserId("");
            form.setUserName("");
            form.setDeptId(0);
            form.setDeptName("");
            form.setUserAddress("");
            form.setUserTel("");
            form.setUserType("");
            form.setDevPort("");
            form.setDevId("");
            form.setUserLogic("");
            form.setLogicPort("");
            form.setRemark("");
        }
        catch (Exception e)
        {
            actionMapping.findForward("failure");
            e.printStackTrace();
        }
        finally
        {
            if (tawInfIp != null)
            {
                tawInfIp = null;
            }
            if (tawInfIpDAO != null)
            {
                tawInfIpDAO = null;
            }
            if (form != null)
            {
                form = null;
            }
        }
        return actionMapping.findForward("success");
    }

    /**
     * ��ʾɾ���ҳ��
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    private ActionForward performDel(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    {
        TawInfIpForm form = (TawInfIpForm) actionForm;

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
            int deptId = StaticMethod.null2int(request.getParameter(
                "deptId"));
            int id = StaticMethod.null2int(request.getParameter(
                "id"));

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
            TawInfIpDAO tawInfIpDAO = new TawInfIpDAO(ds);
            TawInfIp tawInfIp = new TawInfIp();
            tawInfIp = tawInfIpDAO.getById(id);
            org.apache.commons.beanutils.BeanUtils.populate(form,
                org.apache.commons.beanutils.BeanUtils.describe(
                tawInfIp));

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
     * ִ��ɾ��Ĳ���
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    private ActionForward performDeldone(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response)
    {
        TawInfIpForm form = (TawInfIpForm) actionForm;
        TawInfIpDAO tawInfIpDAO = new TawInfIpDAO(ds);

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

            int id = StaticMethod.null2int(request.getParameter(
                "id"));

            tawInfIpDAO.delete(id);
            form.setId(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            actionMapping.findForward("failure");
        }
        finally
        {
            if (tawInfIpDAO != null)
            {
                tawInfIpDAO = null;
            }
        }
        return actionMapping.findForward("success");
    }
}