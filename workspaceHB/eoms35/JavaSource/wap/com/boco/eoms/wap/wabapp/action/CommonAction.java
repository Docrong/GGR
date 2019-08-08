package com.boco.eoms.wap.wabapp.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.wap.model.Parameter;
import com.boco.eoms.wap.util.WapUtil;
import com.boco.eoms.wap.util.XmlUtil;
import com.boco.eoms.wap.wabapp.form.CommonForm;

/**
 * wap上的流程和平台通用action
 *
 * @author xugengxian
 * @date Feb 19, 2009 8:55:28 AM
 */
public class CommonAction extends BaseAction {

    /**
     * 跳转到wap detail页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward detail(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        String type = StaticMethod.null2String(request.getParameter("type"));
        //拼接访问detail的url
        StringBuilder urlParam = new StringBuilder(UtilMgrLocator.getEOMSAttributes().getWapSheetUrl()).append(request.getParameter("url")
                .replace("*", "&"));
//		StringBuilder urlParam = new StringBuilder(request.getParameter("url")
//				.replace("*", "&"));
        urlParam.append("&type=interface&userName=" + userId);
        String urlMain = urlParam.substring(0, urlParam.indexOf("&"));
        String urlSub = urlParam.substring(urlParam.indexOf("&") + 1, urlParam.length());

        HttpURLConnection conn = (HttpURLConnection) new URL(urlMain).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
        osw.write(urlSub);
        osw.flush();
        osw.close();
        InputStream input = null;
        try {
            input = conn.getInputStream();
        } catch (IOException ex) {
            System.out.println("wap连接WPS工单action时出错--" + ex.getMessage());
            return mapping.findForward("failed");
        }

        //通过url读取xml资源，并用xml工具解析，最后获取各类参数（显示属性，隐藏字段，判断参数）
        XmlUtil xmlUtil = new XmlUtil(input);
        List attributes = xmlUtil.getAttributes();
        List hiddens = xmlUtil.getHiddens();
        List parameters = xmlUtil.getParameter();
        Iterator it = parameters.iterator();
        Parameter param = new Parameter();
        //从判断参数中取得工单状态taskStatus值
        String taskStatus = "";
        while (it.hasNext()) {
            param = (Parameter) it.next();
            taskStatus = "taskStatus".equalsIgnoreCase(param.getId()) ? param.getValue() : "";
            if (!"".equalsIgnoreCase(taskStatus)) break;
        }
        //从隐藏参数中取得任务名称staskName
        String taskName = "";
        Iterator hids = hiddens.iterator();
        while (hids.hasNext()) {
            param = (Parameter) hids.next();
            taskName = "taskName".equalsIgnoreCase(param.getId()) ? param.getValue() : "";
            if (!"".equalsIgnoreCase(taskName)) break;
        }
        //从隐藏参数中取得BEAN名称beanName
        String beanName = "";
        while (hids.hasNext()) {
            param = (Parameter) hids.next();
            beanName = "beanName".equalsIgnoreCase(param.getId()) ? param.getValue() : "";
            if (!"".equalsIgnoreCase(beanName)) break;
        }
        request.setAttribute("taskName", taskName);
        request.setAttribute("taskStatus", taskStatus);
        request.setAttribute("attributes", attributes);
        request.setAttribute("hiddens", hiddens);
        request.setAttribute("url", urlParam.toString());

        //根据类型跳转到平台或者流程页面
        if (beanName == null || "CommonFault".equals(beanName)) {
            return mapping.findForward("wpsDetail");
        } else if (beanName == null || "Complaint".equals(beanName)) {
            return mapping.findForward("complaintDetail");
        } else if (beanName == null || "CommonTask".equals(beanName)) {
            return mapping.findForward("commonTaskDetail");
        } else if (type.equals("infopub")) {
            return mapping.findForward("infopubdetail");
        }
        return null;
    }


    /**
     * 只要功能是显示所有的数据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward menu(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String id = request.getParameter("id");
        List list = new ArrayList();
        String flagNull = "0";
        HashMap mapNext = new HashMap();
        try {
            // 根据菜单id得到用户权限下的所有菜单

            list = PrivMgrLocator.getPrivMgr().listOpertionwap(
                    sessionform.getUserid(), sessionform.getDeptid(),
                    sessionform.getRolelist(),
                    PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION, id);
            for (Iterator itNext = list.iterator(); itNext.hasNext(); ) {
                TawSystemPrivOperation operationNext = (TawSystemPrivOperation) itNext.next();
                List wapLeafListNext = PrivMgrLocator.getPrivMgr().listOpertionwap(
                        sessionform.getUserid(), sessionform.getDeptid(),
                        sessionform.getRolelist(),
                        PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION, operationNext.getCode());
                if (!wapLeafListNext.isEmpty()) {
                    flagNull = "1";
                }
                mapNext.put(operationNext.getId(), wapLeafListNext);
            }
            request.setAttribute("MENULIST", list);
            request.setAttribute("MAPNEXT", mapNext);
            request.setAttribute("flagNull", flagNull);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("moreList");
    }

    /**
     * 忘记密码首页
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward pdIndex(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("pdIndex");
    }

    /**
     * 检验用户
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward checkUser(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CommonForm commonform = (CommonForm) form;
        String name = commonform.getName();
        String tel = commonform.getTel();
        TawSystemUser user = null;
        String mobile = "";
        ITawSystemUserManager userManager = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");

        if (name == null || "".equals(name)) {
            loginErrorReturn(request, response, "NameIsNull");
            return mapping.findForward("rewrite");

        } else {
            if (WapUtil.isMobileNumber(name)) {
                List list = userManager.getUserByIdMobile(name);
                if (list == null) {
                    loginErrorReturn(request, response, "UserNotFount");
                    return mapping.findForward("rewrite");
                } else {
                    user = (TawSystemUser) list.get(0);
                    mobile = user.getMobile();
                }
            } else {
                user = (TawSystemUser) userManager.getUserByuserid(name);
                if (user == null) {
                    loginErrorReturn(request, response, "UserNotFound");
                    return mapping.findForward("rewrite");
                } else {
                    mobile = user.getMobile();
                }

            }
            if (!mobile.equals(tel)) {
                loginErrorReturn(request, response, "NotUpdatePassword");
                return mapping.findForward("rewrite");
            }

            request.setAttribute("userid", user.getUserid());
        }
        return mapping.findForward("update");
    }


    /**
     * 返回在頁面時顯示的錯誤信息
     *
     * @param request
     * @param response
     * @param strError
     */
    private void loginErrorReturn(HttpServletRequest request,
                                  HttpServletResponse response, String strError) {
        request.setAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY, strError);
    }


    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("update");
    }

}
