package com.boco.eoms.infmanage.controller;

import java.util.*;
import javax.servlet.http.*;
//import com.boco.eoms.weekly.dao.TawWeeklyDAO;
import java.util.ArrayList;
import org.apache.commons.beanutils.*;
import org.apache.struts.action.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.infmanage.dao.*;
import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.infmanage.bo.*;
//import com.boco.eoms.common.controller.SaveSessionBeanForm;
////import com.boco.eoms.jbzl.bo.TawDeptBO;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
//import com.boco.eoms.jbzl.bo.TawRmUserBO;
import org.apache.struts.util.LabelValueBean;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import java.io.*;
import java.text.SimpleDateFormat;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
public class TawInfAppuAction
    extends Action
{
    private static int PAGE_LENGTH = 10;
    private String user_id = "";
    private String userName = "";
    private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
        ConnectionPool.getInstance();

    public TawInfAppuAction()
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
            userName = saveSessionBeanForm.getUsername();
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

      if ("LISTTAKEN".equalsIgnoreCase(myaction))
      {
          myforward = performListTaken(actionMapping, actionForm, request,
                                  response);
      }

      if ("REMOVETAKEN".equalsIgnoreCase(myaction))
     {
         myforward = peformRemoveTaken(actionMapping, actionForm, request,
                                 response);
     }

        if ("TAKEN".equalsIgnoreCase(myaction))
        {
            myforward = performTaken(actionMapping, actionForm, request,
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

        if ("FILE".equalsIgnoreCase(myaction))
        {
            myforward = performFile(actionMapping, actionForm, request,
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
    public ActionForward performAdd(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
    {
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
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
//            	edit by wangheqi
                TawSystemAssignBo privBO = null;
            	//TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
                Vector domIds = new Vector();
                domIds = com.boco.eoms.base.util.StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

                //domIds = tawVPBO.validatePriv(sessionUserId,
                //                              actionMapping.getPath());

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
            //int regionId = tawBOO.getRegionId(saveSessionBeanForm.getDeptid());
            Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(), "-1");

            request.setAttribute("REGIONID", String.valueOf(regionId));

            //
            TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);
            int sortId = 0;
            if (StaticMethod.null2int(request.getParameter("sortId")) != 0)
            {
                sortId = StaticMethod.null2int(request.getParameter("sortId"));
            }
            else
            {
                sortId = StaticMethod.nullObject2int(request.getAttribute(
                    "sortId"));
            }

            String sortName = tawInfAppuDAO.getSortName(sortId);
            form.setInfUpName(userName);
            form.setInfSortName(sortName);
            form.setInfSortId(sortId);
            form.setInfUpId(user_id);
            form.setInfInfoId("");
            form.setInfInfoName("");
            form.setDeptName("");

            //���ʱ��
            String date = com.boco.eoms.common.util.StaticMethod.getLocalString();
            String year = date.substring(0, 4);
            String month = date.substring(5, 7);
            String day = date.substring(8, 10);
            String time = date.substring(11);
            String noon = "����";
            if (Integer.parseInt(time.substring(0, 2)) >= 12)
            {
                noon = "����";
            }
            String upTime = year + "��" + month + "��" + day + "��" + noon + " " +
                time;

            form.setInfUpTime(upTime);
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
        TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
        TawInfInfo tawInfInfo = new TawInfInfo();
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

            org.apache.commons.beanutils.BeanUtils.populate(tawInfInfo,
                org.apache.commons.beanutils.BeanUtils.describe(form));

            tawInfAppuDAO.insert(tawInfInfo);
            request.setAttribute("sortId",
                                 String.valueOf(tawInfInfo.getInfSortId()));

            // �������ϸ��������ߣ�����ޡ����ڣ�2004.6.10
            if (!request.getParameter("fileValue").equals(""))
            {
                int id = tawInfAppuDAO.getId(form.getInfUpTime());
                TawInfUpfile tawInfUpfile = new TawInfUpfile();
                TawInfUpfileDAO tawInfUpfileDAO = new TawInfUpfileDAO(ds);
                tawInfUpfile.setInfoId(id);
                tawInfUpfile.setInfUpfileName(request.getParameter("fileValue"));
                tawInfUpfile.setEncodename(request.getParameter("fileText"));
                tawInfUpfileDAO.insert(tawInfUpfile);
            }

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
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
        try
        {
            request.getSession().removeAttribute("tawInfMaintainerForm");
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

//            	edit by wangheqi
                TawSystemAssignBo privBO = null;
            	//TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
                Vector domIds = new Vector();
                domIds = com.boco.eoms.base.util.StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

                //domIds = tawVPBO.validatePriv(sessionUserId,
                //                              actionMapping.getPath());

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
//          edit by wangheqi 2.7 to 3.5
            //TawDeptBO tawBOO = new TawDeptBO(ds);
            //int regionId = tawBOO.getRegionId(saveSessionBeanForm.getDeptid());
            Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(), "-1");
                                            
            request.setAttribute("REGIONID", String.valueOf(regionId));

            int sortId = Integer.parseInt(request.getParameter("sortId"));
            TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);
            String sortName = tawInfAppuDAO.getSortName(sortId);

            request.setAttribute("sortId", request.getParameter("sortId"));
            form.setInfSortId(sortId);
            form.setInfSortName(sortName);

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
     * ִ�в�ѯ�Ĳ���
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
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
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
//            	edit by wangheqi
                TawSystemAssignBo privBO = null;
            	//TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
                Vector domIds = new Vector();
                domIds = com.boco.eoms.base.util.StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

                //domIds = tawVPBO.validatePriv(sessionUserId,
                //                              "/TawInfAppu/query");

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
            TawInfAppuBO tawInfAppuBO = new TawInfAppuBO(ds);
            String condition = tawInfAppuBO.getQueryCondition(form, sdomIds);

            TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);
            ArrayList list = (ArrayList) tawInfAppuDAO.getList(condition,
                offset, length);

            int size = tawInfAppuDAO.getSize(
                "taw_inf_info a,taw_inf_sort b,taw_rm_user c,taw_dept d",
                condition);
            String url = request.getContextPath() + "/infmanage" +
                actionMapping.getPath() + ".do";
            String pagerHeader = Pager.generate(offset, size, length, url);

            request.setAttribute("pagerHeader", pagerHeader);
            request.setAttribute("TAW_INF_INFO_LIST", list);
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
     * ִ�в�ѯ�Ĳ���
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performListTaken(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    {
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
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
//            	edit by wangheqi
                TawSystemAssignBo privBO = null;
            	//TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
                Vector domIds = new Vector();
                domIds = com.boco.eoms.base.util.StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

                //domIds = tawVPBO.validatePriv(sessionUserId,
                //                              "/TawInfAppu/query");

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
            //TawInfAppuBO tawInfAppuBO = new TawInfAppuBO(ds);
            //String condition = tawInfAppuBO.getQueryCondition(form, sdomIds);
            String condition = " ,taw_inf_take e where a.dept_id=d.dept_id and b.inf_sort_id=a.inf_sort_id and a.inf_up_id=c.user_id and a.id=e.info_id and e.taker_id='"+sessionUserId+"'";
            TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);
            ArrayList list = (ArrayList) tawInfAppuDAO.getList(condition,
                offset, length);

            int size = tawInfAppuDAO.getSize(
                "taw_inf_info a,taw_inf_sort b,taw_rm_user c,taw_dept d",
                condition);
            String url = request.getContextPath() + "/infmanage" +
                actionMapping.getPath() + ".do";
            String pagerHeader = Pager.generate(offset, size, length, url);

            request.setAttribute("pagerHeader", pagerHeader);
            request.setAttribute("TAW_INF_INFO_LIST", list);
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
     * ִ�в鿴�Ĳ���
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
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
        TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);
        TawInfInfo tawInfInfo = new TawInfInfo();
        //edit by wanheqi 2.7 to 3.5
        TawSystemUserRoleBo tawRmUserBO = TawSystemUserRoleBo.getInstance();
        //TawRmUserBO tawRmUserBO = new TawRmUserBO();
        String user="";
        String USER="";
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
            user=saveSessionBeanForm.getUserid();
            //��
            int id = StaticMethod.null2int(request.getParameter(
                "id"));
            int sortId = Integer.parseInt(request.getParameter("sortId"));
            String sortName = tawInfAppuDAO.getSortName(sortId);
            tawInfInfo = tawInfAppuDAO.getById(id);
            String locker=tawInfAppuDAO.getLocker(id);
            if(!locker.equals("0")){
              //TawWeeklyDAO tawWeeklyDAO=new TawWeeklyDAO(ds);
              USER=tawRmUserBO.getUsernameByUserid(locker);

            }
            org.apache.commons.beanutils.BeanUtils.populate(form,
                org.apache.commons.beanutils.BeanUtils.describe(
                tawInfInfo));
            form.setInfSortName(sortName);

            // �鿴���ϸ��������ߣ�����ޡ����ڣ�2004.6.10
            TawInfUpfile tawInfUpfile = new TawInfUpfile();
            TawInfUpfileDAO tawInfUpfileDAO = new TawInfUpfileDAO(ds);
            tawInfUpfile = tawInfUpfileDAO.retrieve(form.getId());
            if (tawInfUpfile != null)
            {
                request.setAttribute("attValue", tawInfUpfile.getInfUpfileName());
                request.setAttribute("attText", tawInfUpfile.getEncodename());
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
        request.setAttribute("locker",USER);
        return actionMapping.findForward("success");
    }
    /**
     * ִ�ж��ĵĲ���
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performTaken(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    {
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
        TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);
        String infoid=request.getParameter("infoid");
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

                  String user=saveSessionBeanForm.getUserid();
            if (saveSessionBeanForm == null)
            {
                return actionMapping.findForward("timeout");
            }
            String temp="";
            for(int i=0;i<infoid.length();i++){
              if(!infoid.substring(i,i+1).equals(",")){
                temp=temp+infoid.substring(i,i+1);
              }else{
                int id=Integer.parseInt(temp);
                int count=tawInfAppuDAO.getIfTaken("taw_inf_take","where info_id="+id+" and taker_id='"+user+"'");
                if(count==0){
                  tawInfAppuDAO.Taken(id,user);
                  temp="";
                }else{
                  temp="";
                }
              }
            }
            if(!temp.equals(""))
            {
              int id=Integer.parseInt(temp);
              int count=tawInfAppuDAO.getIfTaken("taw_inf_take","where info_id="+id+" and taker_id='"+user+"'");
              if(count==0){
                tawInfAppuDAO.Taken(id, user);
              }

            }

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
     * ɾ���ĵĲ���
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward peformRemoveTaken(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    {
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
        TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);
        String infoid=request.getParameter("infoid");
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

                  String user=saveSessionBeanForm.getUserid();
            if (saveSessionBeanForm == null)
            {
                return actionMapping.findForward("timeout");
            }
            tawInfAppuDAO.deletetaken(infoid,user);
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
     * ��ʾ�޸ĵ�ҳ��
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
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
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
        String user_id=saveSessionBeanForm.getUserid();
        //edit by wanheqi 2.7 to 3.5
        TawSystemUserRoleBo tawRmUserBO = TawSystemUserRoleBo.getInstance();
        //TawRmUserBO tawRmUserBO = new TawRmUserBO();
        try
        {
            int id = StaticMethod.null2int(request.getParameter(
                "id"));
            int deptId = StaticMethod.null2int(request.getParameter("deptId"));
            String sdomIds = "";

            String sessionUserId = saveSessionBeanForm.getUserid();
            if (!user_id.equalsIgnoreCase(StaticVariable.ADMIN))
            {
//            	edit by wangheqi
                TawSystemAssignBo privBO = null;
            	//TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
                Vector domIds = new Vector();
                domIds = com.boco.eoms.base.util.StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

                //domIds = tawVPBO.validatePriv(sessionUserId,
                //                              "/TawInfAppu/update");

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

            TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(
                ds);
            String locker=tawInfAppuDAO.getLocker(id);
            if(!locker.equals("0") && !locker.equals(user_id))
            {
              //TawWeeklyDAO tawWeeklyDAO=new TawWeeklyDAO(ds);
              String user=tawRmUserBO.getUsernameByUserid(user_id);
              request.setAttribute("error", "�Բ��𣬸����ϱ�"+user+"������ʱ�����޸�");
              return actionMapping.findForward("ifIccid");
            }
            TawInfInfo tawInfInfo = new TawInfInfo();
            tawInfAppuDAO.lock(user_id,id);
            tawInfInfo = tawInfAppuDAO.getById(id);

            int sortId = Integer.parseInt(request.getParameter("sortId"));
            String sortName = tawInfAppuDAO.getSortName(sortId);

            org.apache.commons.beanutils.BeanUtils.populate
                (form,
                 org.apache.commons.beanutils.BeanUtils.describe(
                tawInfInfo));
            form.setInfSortName(sortName);
            form.setInfUpName(userName);

            //  �޸����ϸ��������ߣ�����ޡ����ڣ�2004.6.10
            TawInfUpfile tawInfUpfile = new TawInfUpfile();
            TawInfUpfileDAO tawInfUpfileDAO = new TawInfUpfileDAO(ds);
            tawInfUpfile = tawInfUpfileDAO.retrieve(form.getId());
            if (tawInfUpfile != null)
            {
              String temp="";
              try{
                for(int i=0;i<tawInfUpfile.getEncodename().length();i++)
                {
                  if(!tawInfUpfile.getEncodename().substring(i,i+1).equals("."))
                  {
                    temp=temp+tawInfUpfile.getEncodename().substring(i,i+1);
                  }else{
                    //temp=temp+"00";
                    break;
                  }
                }
              }catch(Exception e){}
                request.setAttribute("attValue",tawInfUpfile.getInfUpfileName());
                request.setAttribute("attName",temp+"_"+ tawInfUpfile.getInfUpfileName());
                request.setAttribute("attText", tawInfUpfile.getEncodename());
                request.setAttribute("attTe","");
            }

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
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
        TawInfInfo tawInfInfo = new TawInfInfo();
        TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);

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
            int dept_id=Integer.parseInt(saveSessionBeanForm.getDeptid());
            org.apache.commons.beanutils.BeanUtils.populate(tawInfInfo,
                org.apache.commons.beanutils.BeanUtils.describe(form));
            tawInfAppuDAO.update(tawInfInfo);
            //�����      �����        2004.8.15
            tawInfAppuDAO.lock("0",tawInfInfo.getId());
            //�����߶�������
            Date puredate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(puredate);
            tawInfAppuDAO.insertMsg(form.getId(),dept_id,date);
            //�޸����ϸ���   ���ߣ������  ����ʱ�䣺2004.6.10
            TawInfUpfile tawInfUpfile = null;
            TawInfUpfileDAO tawInfUpfileDAO = new TawInfUpfileDAO(ds);
            tawInfUpfile = tawInfUpfileDAO.retrieve(form.getId());

            if (!request.getParameter("fileValue").equals(""))
            {
                if (tawInfUpfile == null)
                {
                    //�������ҳ�����и�����������ݿ���û�и����ϵĸ�����¼���򴴽�һ�����Ϣ
                    tawInfUpfile = new TawInfUpfile();
                    tawInfUpfile.setInfoId(tawInfInfo.getId());
                    tawInfUpfile.setInfUpfileName(request.getParameter(
                        "fileValue"));
                    tawInfUpfile.setEncodename(request.getParameter(
                        "fileText"));
                    tawInfUpfileDAO.insert(tawInfUpfile);
                }
                else
                {
                    //�������ҳ�����и�����������ݿ����и����ϵĸ�����¼�����޸ĸ�����Ϣ
                    tawInfUpfile.setInfoId(tawInfInfo.getId());
                    tawInfUpfile.setInfUpfileName(request.getParameter(
                        "fileValue"));
                    tawInfUpfile.setEncodename(request.getParameter(
                        "fileText"));
                    tawInfUpfileDAO.update(tawInfUpfile);
                }
            }
            else
            {
                if (tawInfUpfile != null)
                {
                    //���ҳ����û�и���������ݿ����иù����ĸ�����¼����ɾ��ø�����Ϣ
                    tawInfUpfileDAO.delete(tawInfInfo.getId());
                }
            }

            form.setId(0);
            form.setInfInfoId("");
            form.setInfInfoName("");
            form.setDeptName("");
            form.setInfUpName("");
            form.setInfUpTime("");
            form.setDeptId(0);
            form.setInfUpId("");
        }
        catch (Exception e)
        {
            actionMapping.findForward("failure");
            e.printStackTrace();
        }
        finally
        {
            if (tawInfInfo != null)
            {
                tawInfInfo = null;
            }
            if (tawInfAppuDAO != null)
            {
                tawInfAppuDAO = null;
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
    public ActionForward performDel(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
    {
        TawInfInfoForm form = (TawInfInfoForm) actionForm;

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
//            	edit by wangheqi
                TawSystemAssignBo privBO = null;
            	//TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
                Vector domIds = new Vector();
                domIds = com.boco.eoms.base.util.StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

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
            TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);
            TawInfInfo tawInfInfo = new TawInfInfo();
            tawInfInfo = tawInfAppuDAO.getById(id);

            int sortId = StaticMethod.null2int(request.getParameter("sortId"));
            String sortName = tawInfAppuDAO.getSortName(sortId);
            org.apache.commons.beanutils.BeanUtils.populate(form,
                org.apache.commons.beanutils.BeanUtils.describe(
                tawInfInfo));
            form.setInfSortName(sortName);

            // ɾ�����ϸ��������ߣ�����ޡ����ڣ�2004.6.10
            TawInfUpfile tawInfUpfile = new TawInfUpfile();
            TawInfUpfileDAO tawInfUpfileDAO = new TawInfUpfileDAO(ds);
            tawInfUpfile = tawInfUpfileDAO.retrieve(form.getId());
            if (tawInfUpfile != null)
            {
                request.setAttribute("attValue", tawInfUpfile.getInfUpfileName());
                request.setAttribute("attText", tawInfUpfile.getEncodename());
            }

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
    public ActionForward performDeldone(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
    {
        TawInfInfoForm form = (TawInfInfoForm) actionForm;
        TawInfAppuDAO tawInfAppuDAO = new TawInfAppuDAO(ds);

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

            tawInfAppuDAO.delete(id);


            String realPath = saveSessionBeanForm.getRealPath();
            String separator = File.separator;

            TawInfUpfileDAO tawInfUpfileDAO = new TawInfUpfileDAO(ds);
            String filename = tawInfUpfileDAO.getFilename(form.getId());
            if(!filename.equals(""))
            {
                String attArr[]=filename.split(",",0);
                for (int i=0;i<attArr.length;i++)
                {
                    String url = realPath + "infmanage" + separator + "upload" +
                        separator + attArr[i];

                    File fileObj = new File(url);
                    if (fileObj.exists())
                        fileObj.delete();
                }
            }
            tawInfUpfileDAO.delete(form.getId());
            form.setId(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            actionMapping.findForward("failure");
        }
        finally
        {
            if (tawInfAppuDAO != null)
            {
                tawInfAppuDAO = null;
            }
        }

        return actionMapping.findForward("success");
    }

    /**
     * ��ʾ��Ӹ�����ҳ��
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performFile(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
    {
        /*TawInfInfoForm form = (TawInfInfoForm) actionForm;
                 SaveSessionBeanForm saveSessionBeanForm =
            (SaveSessionBeanForm) request.getSession().getAttribute(
            "SaveSessionBeanForm");
                 if (saveSessionBeanForm == null)
                 {
            return actionMapping.findForward("timeout");
                 }
                 try
                 {
            String attValue = StaticMethod.dbNull2String(String.valueOf(
                request.
                getParameter("attValue")));
            String attText = StaticMethod.dbNull2String(String.valueOf(
                request.
                getParameter("attText")));
            String imgValue = StaticMethod.dbNull2String(String.valueOf(
                request.
                getParameter("imgValue")));
            String imgText = StaticMethod.dbNull2String(String.valueOf(
                request.
                getParameter("imgText")));
            request.setAttribute("attValue", attValue);
            request.setAttribute("attText", attText);
            request.setAttribute("imgValue", imgValue);
            request.setAttribute("imgText", imgText);
                 }
                 catch (Exception e)
                 {
            return actionMapping.findForward("failure");
                 }*/
        return actionMapping.findForward("success");

    }
}
