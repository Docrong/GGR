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
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
public class TawInfCmdAction
    extends Action
{
    private static int PAGE_LENGTH = 10;
    private String user_id = "";
    private int deptId;
    private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
        ConnectionPool.getInstance();

    public TawInfCmdAction()
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
        TawInfCmdForm form = (TawInfCmdForm) actionForm;
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
                //                              "/TawInfCmd/add");

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

            //
            Vector entries = new Vector(2);
            entries.add(new LabelValueBean("ȫ��", ""));
            entries.add(new LabelValueBean("��Ϊ08������", "��Ϊ08������"));
            entries.add(new LabelValueBean("�Ϻ�����1240������", "�Ϻ�����1240������"));
            form.setCmdSwich("");
            form.setCmdId("");
            form.setCmdName("");
            form.setCmdParam("");
            form.setParamScope("");
            form.setCmdDes("");

            form.setCollectionSwich(entries);
            form.setDeptId(deptId);
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
        TawInfCmdDAO tawInfCmdDAO = new TawInfCmdDAO(ds);
        TawInfCmdForm form = (TawInfCmdForm) actionForm;
        TawInfCmd tawInfCmd = new TawInfCmd();
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

            org.apache.commons.beanutils.BeanUtils.populate(tawInfCmd,
                org.apache.commons.beanutils.BeanUtils.describe(form));

            tawInfCmdDAO.insert(tawInfCmd);
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
    public ActionForward performQuery(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
    {
        TawInfCmdForm form = (TawInfCmdForm) actionForm;
        try
        {
            request.getSession().removeAttribute("tawInfCmdForm");
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
                //                              "/TawInfCmd/query");

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
            Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(),"0");

            //int regionId = tawBOO.getRegionId(saveSessionBeanForm.getDeptid());
            request.setAttribute("REGIONID", String.valueOf(regionId));

            Vector entries = new Vector(2);
            entries.add(new LabelValueBean("ȫ��", ""));
            entries.add(new LabelValueBean("��Ϊ08������", "��Ϊ08������"));
            entries.add(new LabelValueBean("�Ϻ�����1240������", "�Ϻ�����1240������"));

            form.setCollectionSwich(entries);
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
        TawInfCmdForm form = (TawInfCmdForm) actionForm;

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
                //                              "/TawInfCmd/query");

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
            TawInfCmdBO tawInfCmdBO = new TawInfCmdBO(ds);
            String condition = tawInfCmdBO.getQueryCondition(form);

            TawInfCmdDAO tawInfCmdDAO = new TawInfCmdDAO(ds);
            ArrayList list = (ArrayList) tawInfCmdDAO.getList(condition,
                offset, length);

            int size = tawInfCmdDAO.getSize("taw_inf_cmd",
                                            condition);
            String url = request.getContextPath() + "/infmanage" +
                actionMapping.getPath() + ".do";
            String pagerHeader = Pager.generate(offset, size, length, url);

            request.setAttribute("pagerHeader", pagerHeader);
            request.setAttribute("TAW_INF_CMD_LIST", list);
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
     * �鿴�����������Ϣ
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performView(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    {
        TawInfCmdForm form = (TawInfCmdForm) actionForm;
        TawInfCmdDAO tawInfCmdDAO = new TawInfCmdDAO(ds);
        TawInfCmd tawInfCmd = new TawInfCmd();
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
            tawInfCmd = tawInfCmdDAO.getById(id);
            org.apache.commons.beanutils.BeanUtils.populate(form,
                org.apache.commons.beanutils.BeanUtils.describe(
                tawInfCmd));
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
        TawInfCmdForm form = (TawInfCmdForm) actionForm;
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
                //                              "/TawInfCmd/update");

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

            TawInfCmdDAO tawInfCmdDAO = new TawInfCmdDAO(
                ds);
            TawInfCmd tawInfCmd = new TawInfCmd();
            tawInfCmd = tawInfCmdDAO.getById(id);

            org.apache.commons.beanutils.BeanUtils.populate
                (form,
                 org.apache.commons.beanutils.BeanUtils.describe(
                tawInfCmd));

            //
            Vector entries = new Vector(1);
            entries.add(new LabelValueBean("��Ϊ08������", "��Ϊ08������"));
            entries.add(new LabelValueBean("�Ϻ�����1240������", "�Ϻ�����1240������"));

            form.setCollectionSwich(entries);
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
        TawInfCmdForm form = (TawInfCmdForm) actionForm;
        TawInfCmd tawInfCmd = new TawInfCmd();
        TawInfCmdDAO tawInfCmdDAO = new TawInfCmdDAO(ds);

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

            org.apache.commons.beanutils.BeanUtils.populate(tawInfCmd,
                org.apache.commons.beanutils.BeanUtils.describe(form));
            tawInfCmdDAO.update(tawInfCmd);
            form.setCmdSwich("");
            form.setCmdId("");
            form.setCmdName("");
            form.setCmdParam("");
            form.setCmdDes("");
        }
        catch (Exception e)
        {
            actionMapping.findForward("failure");
            e.printStackTrace();
        }
        finally
        {
            if (tawInfCmd != null)
            {
                tawInfCmd = null;
            }
            if (tawInfCmdDAO != null)
            {
                tawInfCmdDAO = null;
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
        TawInfCmdForm form = (TawInfCmdForm) actionForm;

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
            TawInfCmdDAO tawInfCmdDAO = new TawInfCmdDAO(ds);
            TawInfCmd tawInfCmd = new TawInfCmd();
            tawInfCmd = tawInfCmdDAO.getById(id);
            org.apache.commons.beanutils.BeanUtils.populate(form,
                org.apache.commons.beanutils.BeanUtils.describe(
                tawInfCmd));

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
        TawInfCmdForm form = (TawInfCmdForm)actionForm;
        TawInfCmdDAO tawInfCmdDAO = new TawInfCmdDAO(ds);

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

            tawInfCmdDAO.delete(id);
            form.setId(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            actionMapping.findForward("failure");
        }
        finally
        {
            if (tawInfCmdDAO != null)
            {
                tawInfCmdDAO = null;
            }
        }
        return actionMapping.findForward("success");
    }
}