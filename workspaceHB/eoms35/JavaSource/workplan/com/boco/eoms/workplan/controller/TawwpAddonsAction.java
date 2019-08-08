package com.boco.eoms.workplan.controller;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: boco 亿阳</p>
 *
 * @author eoms
 * @version EOMS 3.5
 */

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.jdom.Attribute;
import org.jdom.Document;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.workplan.mgr.ITawwpAddonsMgr;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.mgr.ITawwpLogMgr;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpAddonsTableVO;
import com.boco.eoms.workplan.vo.TawwpAddonsElementVO;
import com.boco.eoms.workplan.vo.TawwpUpLoadVO;

import java.io.File;
import java.util.List;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.controller.TawRmCycleTableForm;
import com.jspsmart.upload.SmartUpload;

import java.util.Random;

public class TawwpAddonsAction extends BaseAction {

    /**
     * @param mapping  ActionMapping
     * @param form     ActionForm
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws IOException
     * @throws IOException
     * @throws Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws IOException, IOException, Exception {
        ActionForward myforward = null;

        // 获取请求的action属性
        String myaction = mapping.getParameter();

        try {
            /*
             * TawSystemSessionForm TawSystemSessionForm =
             * (TawSystemSessionForm) request. getSession().getAttribute(
             * "sessionform"); if (TawSystemSessionForm == null) { return
             * mapping.findForward("timeout"); }
             */
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 根据用户请求页面请求，进行页面跳转
        if (isCancelled(request)) {
            return mapping.findForward("cancel"); // 无效的请求，转向错误页面
        } else if ("".equalsIgnoreCase(myaction)) {
            myforward = mapping.findForward("failure"); // 条件为空，转向空页
        } else if ("ADDONSLIST".equalsIgnoreCase(myaction)) { // 附加表模版列表
            myforward = performAddonsList(mapping, form, request, response);
        }
        // 设计器
        else if ("ADDONSREMOVE".equalsIgnoreCase(myaction)) { // 附加表模版删除
            myforward = performAddonsRemove(mapping, form, request, response);
        } else if ("DESIGNADD".equalsIgnoreCase(myaction)) { // 附加表模版设计
            myforward = performAddonsDesignAdd(mapping, form, request, response);
        } else if ("DESIGNEDIT".equalsIgnoreCase(myaction)) { // 附加表模版编辑
            myforward = performAddonsDesignEdit(mapping, form, request,
                    response);
        } else if ("DESIGNSAVE".equalsIgnoreCase(myaction)) { // 附加表模版保存
            myforward = performAddonsDesignSave(mapping, form, request,
                    response);
        }
        // 附加表操作
        else if ("ADDONSREAD".equalsIgnoreCase(myaction)) { // 附加表增加、浏览、修改
            myforward = performAddonsRead(mapping, form, request, response);
        } else if ("ADDONSVIEW".equalsIgnoreCase(myaction)) { // 根据附加表模版id浏览
            myforward = performAddonsView(mapping, form, request, response);
        } else if ("ADDONSADDT2".equalsIgnoreCase(myaction)) { // 附加表增加第二元素
            myforward = performAddonsAddT2(mapping, form, request, response);
        } else if ("ADDONSSAVE".equalsIgnoreCase(myaction)) { // 附加表保存
            myforward = performAddonsSave(mapping, form, request, response);
        } else if ("ADDONSIMPORT".equalsIgnoreCase(myaction)) { // 附加表导入
            myforward = performAddonsImport(mapping, form, request, response);
        } else if ("ADDONSEXPORT".equalsIgnoreCase(myaction)) { // 附加表导出
            myforward = performAddonsExport(mapping, form, request, response);
        } else if ("ADDONSDELEMTERM".equalsIgnoreCase(myaction)) { // 附加表导出
            myforward = performAddonsDelEmterm(mapping, form, request, response);
        } else if ("ADDONSADD".equalsIgnoreCase(myaction)) { // EXCEL模板增加
            myforward = performAddonsAdd(mapping, form, request, response);
        } else if ("ADDONSEXCELSAVE".equalsIgnoreCase(myaction)) { // EXCEL模板保存
            myforward = performAddonsSaveExcel(mapping, form, request, response);
        } else if ("ADDONSDETAIL".equalsIgnoreCase(myaction)) { // EXCEL模板显示详细
            myforward = performAddonsDetail(mapping, form, request, response);
        } else {
            myforward = mapping.findForward("failure"); // 无效的请求，转向错误页面
        }
        return myforward;

        /**
         * 附加表模版列表
         *
         * @param mapping
         *            ActionMapping
         * @param actionForm
         *            ActionForm
         * @param request
         *            HttpServletRequest
         * @param response
         *            HttpServletResponse
         * @return ActionForward
         */
    }

    private ActionForward performAddonsDetail(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) {
        // TODO Auto-generated method stub

        return mapping.findForward("success");
    }

    private ActionForward performAddonsSaveExcel(ActionMapping mapping,
                                                 ActionForm form, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub

        // SmartUpload mySmartUpload = new SmartUpload();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // response.setContentType("text/html; charset=utf-8");
        TawRmCycleTableForm tawRmCycleTableForm = (TawRmCycleTableForm) form;

        FormFile file = tawRmCycleTableForm.getThisFile();

        String id = tawRmCycleTableForm.getId();

        String name = "";
        // new String(.getBytes("ISO-8859-1"), "UTF-8");
        String remark = "";
        // new String(.getBytes("ISO-8859-1"), "UTF-8");
        String loginType = UtilMgrLocator.getEOMSAttributes().getLoginType();
        if ("sso".equals(loginType)) {
            remark = tawRmCycleTableForm.getRemark();
            name = tawRmCycleTableForm.getName();
        } else {
            remark = new String(tawRmCycleTableForm.getRemark().getBytes(
                    "ISO-8859-1"), "UTF-8");
            name = new String(tawRmCycleTableForm.getName().getBytes(
                    "ISO-8859-1"), "UTF-8");
        }
        String model = tawRmCycleTableForm.getModel();
        // 取随机数
        Random r = new Random();
        int iRandom = r.nextInt(900000000); // 取10之中的随机一个
        String myTag = (1000000000 - iRandom) + "_";

        String url = "";
        if (file != null && !file.getFileName().equals("")) {

            try {
                String filePath = TawwpStaticVariable.wwwDir
                        + "/workplan/tawwpfile/model" + "/";
                TawwpUtil.mkDir(filePath);
                url = "/workplan/tawwpfile/model" + "/" + myTag + ".xls";

                InputStream stream = file.getInputStream(); // 把文件读入
                OutputStream outputStream = new FileOutputStream(filePath
                        + myTag + ".xls"); // 建立一个上传文件的输出流

                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                stream.close();
                //

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
        if (id != null && !id.equals("")) {
            TawwpAddonsTableVO tawwpAddonsTableVO = tawwpAddonsMgr
                    .viewAddonsTableVO(id);
            if (url.equals("")) {
                url = tawwpAddonsTableVO.getUrl();
            }
            tawwpAddonsMgr.editAddonsTable(id, name, remark, model, "", url);
        } else {
            tawwpAddonsMgr.addAddonsTable(name, remark, model, "", url);
        }
        // 日志
        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");

        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getUserid();// 当前用户名

        tawwpLogMgr.addLog(userId, "addAddonsTable", "");
        return mapping.findForward("success");
    }

    private ActionForward performAddonsAdd(ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) {
        // TODO Auto-generated method stub

        return mapping.findForward("success");
    }

    private ActionForward performAddonsList(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        ActionMessages messages = new ActionMessages();
        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
        try {
            List list = tawwpAddonsMgr.listAddonsByWorkPlan(); // 获取附加表集合
            request.setAttribute("addonslist", list);

            saveMessages(request, messages);
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e);
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }

    }

    // **********************附加表设计**********************//

    /**
     * 附加表设计
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     */
    private ActionForward performAddonsDesignAdd(ActionMapping mapping,
                                                 ActionForm actionForm, HttpServletRequest request,
                                                 HttpServletResponse response) {

        ActionMessages messages = new ActionMessages();
        try {
            String headElementAmount = request
                    .getParameter("headelementamount");
            String bodyElementAmount = request
                    .getParameter("bodyelementamount");
            // 设置不为空
            if (headElementAmount == null || headElementAmount.equals("")) {
                headElementAmount = "1";
            }
            if (bodyElementAmount == null || bodyElementAmount.equals("")) {
                bodyElementAmount = "1";
            }

            request.setAttribute("headelementamount", headElementAmount);
            request.setAttribute("bodyelementamount", bodyElementAmount);
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e);
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }

    }

    /**
     * 附加表设计编辑
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */
    private ActionForward performAddonsDesignEdit(ActionMapping mapping,
                                                  ActionForm actionForm, HttpServletRequest request,
                                                  HttpServletResponse response) throws Exception {
        String addonsid = request.getParameter("addonsid"); // 附加表ID
        String headElementAmount = request.getParameter("headelementamount");
        String bodyElementAmount = request.getParameter("bodyelementamount");
        String headAfter = request.getParameter("headafter");
        String bodyAfter = request.getParameter("bodyafter");
        // 设置不为空
        if (headElementAmount == null || headElementAmount.equals("")) {
            headElementAmount = "1";
        }
        if (bodyElementAmount == null || bodyElementAmount.equals("")) {
            bodyElementAmount = "1";
        }
        if (headAfter == null || headAfter.equals("")) {
            headAfter = "1";
        }
        if (bodyAfter == null || bodyAfter.equals("")) {
            bodyAfter = "1";
        }

        TawwpAddonsTableVO tawwpAddonsTableVO = new TawwpAddonsTableVO();
        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");

        tawwpAddonsTableVO = tawwpAddonsMgr.viewAddonsTableVO(addonsid);
        if (tawwpAddonsTableVO.getUrl() != null
                && tawwpAddonsTableVO.getUrl().endsWith(".xml")) {
            Document doc = tawwpAddonsMgr.getSAXBDoc(tawwpAddonsTableVO
                    .getUrl());
            request.setAttribute("doc", doc);
            request.setAttribute("headelementamount", headElementAmount);
            request.setAttribute("bodyelementamount", bodyElementAmount);
            request.setAttribute("headafter", headAfter);
            request.setAttribute("bodyafter", bodyAfter);
        }
        request.setAttribute("tawwpAddonsTableVO", tawwpAddonsTableVO);

        return mapping.findForward("success");
    }

    /**
     * 附加表模板删除
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     */
    private ActionForward performAddonsRemove(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {
        // 获得参数
        String addonsid = request.getParameter("addonsid"); // 附加表ID
        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
        ActionMessages messages = new ActionMessages();
        try {

            tawwpAddonsMgr.removeAddonsTable(addonsid);
            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            // 获取当前用户的session中的信息
            TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String userId = TawSystemSessionForm.getUserid(); // 当前用户名

            tawwpLogMgr.addLog(userId, "removeAddonsTable", "");

            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e);
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
    }

    /**
     * 附加表删除一个循环格
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     */
    private ActionForward performAddonsDelEmterm(ActionMapping mapping,
                                                 ActionForm actionForm, HttpServletRequest request,
                                                 HttpServletResponse response) {
        ActionMessages messages = new ActionMessages();
        try {

            String addonsid = request.getParameter("addonsid");
            String action = request.getParameter("action");
            String model = request.getParameter("model");
            String reaction = request.getParameter("reaction");
            String myid = request.getParameter("myid");
            String window = request.getParameter("window");

            request.setAttribute("myid", myid);
            request.setAttribute("reaction", reaction);
            request.setAttribute("model", model);
            request.setAttribute("window", window);
            request.setAttribute("addonsid", addonsid);
            request.setAttribute("action", action);

            // 返回页面
            ActionForward actionForward = new ActionForward(
                    "/tawwpaddons/addonsread.do?action=edit&reaction="
                            + reaction + "&model=" + model + "&myid=" + myid
                            + "&addonsid=" + addonsid + "&window=" + window);
            // addonsread.do?action=edit&myid=wweqsxxx&model=50&addonsid=8a8097c10706d961010706db1fc20001&reaction=/tawwpaddons/addonslist.do&window=new
            actionForward.setRedirect(true);
            return actionForward;
        } catch (Exception e) {
            generalError(request, e);
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }

    }

    /**
     * 附加表模板保存
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     * @throws IOException
     * @throws Exception
     */

    private ActionForward performAddonsDesignSave(ActionMapping mapping,
                                                  ActionForm actionForm, HttpServletRequest request,
                                                  HttpServletResponse response) throws IOException, Exception {
        ActionMessages messages = new ActionMessages();
        try {
            ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
            // 获得请求变量
            //
            String value = request.getParameter("value");
            String value_e = StaticMethod.strFromBeanToPage(request
                    .getParameter("value"));
            String name = StaticMethod.strFromBeanToPage(request
                    .getParameter("name"));
            String type = request.getParameter("type");
            String text = request.getParameter("text");
            String model = request.getParameter("model");
            String action = request.getParameter("action");

            String url = request.getParameter("url");

            int headElementAmount = Integer.parseInt(request
                    .getParameter("headelementamount"));
            int bodyElementAmount = Integer.parseInt(request
                    .getParameter("bodyelementamount"));

            String[] elementAttribute = TawwpStaticVariable.elementAttribute;

            Element titleElement = new Element("title");

            titleElement.setAttribute(new Attribute("value", value_e));
            titleElement.setAttribute(new Attribute("name", name));
            titleElement.setAttribute(new Attribute("type", type));

            Document myDocument = new Document(titleElement);

            Element headElement = new Element("head");
            Element headElesElement = new Element("elements");

            for (int i = 0; i < headElementAmount; i++) { // 获取头元素数做循环
                if (!(request.getParameter("del" + i) + "").equals("on")) {
                    Element headEleElesElement = new Element("element");
                    for (int e = 0; e < elementAttribute.length; e++) { // 元素的各个属性循环
                        headEleElesElement.setAttribute(new Attribute(
                                elementAttribute[e],
                                StaticMethod.strFromBeanToPage(request
                                        .getParameter(elementAttribute[e] + i)
                                        + "")));
                    }
                    headElesElement.addContent(headEleElesElement);
                }

            }
            headElement.addContent(headElesElement);

            Element bodyElement = new Element("body");
            Element bodyElesElement = new Element("elements");
            Element bodyEmtermElement = new Element("emterm");

            for (int i = 0; i < bodyElementAmount; i++) { // 获取头元素数做循环
                if (!(request.getParameter("del_" + i) + "").equals("on")) {
                    Element bodyEleEmtermElement = new Element("element");
                    for (int e = 0; e < elementAttribute.length; e++) { // 元素的各个属性循环
                        String myString = StaticMethod
                                .strFromBeanToPage(request
                                        .getParameter(elementAttribute[e] + "_"
                                                + i));

                        bodyEleEmtermElement.setAttribute(new Attribute(
                                elementAttribute[e],

                                myString));

                    }
                    bodyEmtermElement.addContent(bodyEleEmtermElement);
                }
            }
            bodyElesElement.addContent(bodyEmtermElement);
            bodyElement.addContent(bodyElesElement);

            myDocument.getRootElement().addContent(headElement);
            myDocument.getRootElement().addContent(bodyElement);

            XMLOutputter outp = new XMLOutputter();
            // 取随机数
            Random r = new Random();
            int iRandom = r.nextInt(900000000); // 取10之中的随机一个

            String myTag = (1000000000 - iRandom) + "_";
            if (action.equals("add")) {
                TawwpUtil.mkDir(TawwpStaticVariable.rootDir
                        + TawwpStaticVariable.rootOriginXMLDir + model);
                outp.output(myDocument, new FileOutputStream(
                        TawwpStaticVariable.rootDir
                                + TawwpStaticVariable.rootOriginXMLDir + model
                                + "/" + myTag + name + ".xml"));
                url = TawwpStaticVariable.rootOriginXMLDir + model + "/"
                        + myTag + name + ".xml";
                tawwpAddonsMgr.addAddonsTable(value, text, model, "", url);
                // 日志
                ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
                // 获取当前用户的session中的信息
                TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                        .getSession().getAttribute("sessionform");
                String userId = TawSystemSessionForm.getUserid(); // 当前用户名
                tawwpLogMgr.addLog(userId, "addAddonsTable", "");

            } else if (action.equals("edit")) {
                String addonsid = request.getParameter("addonsid");
                outp.output(myDocument, new FileOutputStream(
                        TawwpStaticVariable.rootDir + url));

                tawwpAddonsMgr.editAddonsTable(addonsid, value, text, model,
                        "", url);
                // 日志
                ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
                // 获取当前用户的session中的信息
                TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                        .getSession().getAttribute("sessionform");
                String userId = TawSystemSessionForm.getUserid(); // 当前用户名
                tawwpLogMgr.addLog(userId, "editAddonsTable", "");

            }

            request.setAttribute("model", model);
            return mapping.findForward("success");
        } catch (Exception e) {
            generalError(request, e);
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }

    }

    // ***********************附加表操作*************************//

    /**
     * 附加表保存（包括增加及修改）
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */
    private ActionForward performAddonsSave(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {

        // 获得列表元素的大小
        String action = request.getParameter("action");
        String myid = request.getParameter("myid");
        String url = request.getParameter("url");
        String reaction = request.getParameter("reaction");
        String model = request.getParameter("model");
        // String window = request.getParameter("window");
        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
        // 从文件构造一个Document
        Document doc = tawwpAddonsMgr.getSAXBDoc(url);
        // 得到根元素
        Element root = doc.getRootElement();
        // 获得elements集合
        Element body = root.getChild("body").getChild("elements");
        // emterms
        List emtermsList = body.getChildren();
        // start
        String value_temp = "";
        //
        Vector v = new Vector();

        for (int e = 0; e < emtermsList.size(); e++) {
            Element emterm = (Element) emtermsList.get(e);
            java.util.List emtermLists = emterm.getChildren();
            for (int i = 0; i < emtermLists.size(); i++) {
                TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
                Element element = (Element) emtermLists.get(i);
                eleForm = eleForm.getElement(element);
                // 修改获得的数据
                // informix
                value_temp = StaticMethod.strFromBeanToPage(request
                        .getParameter(eleForm.getName() + "_" + e));
                v.add(value_temp);
            }
        }
        // StaticMethod.strFromBeanToPage(
        // 返回url
        url = tawwpAddonsMgr.saveAddons(action, model, url, v, myid);
        // 日志
        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getUserid(); // 当前用户名
        tawwpLogMgr.addLog(userId, "saveAddons", "");

        request.setAttribute("url", url);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 重新定向
        if (reaction != null) {
            // 返回到制定页面页面
            ActionForward actionForward = new ActionForward(reaction);
            return actionForward;
        } else {
            return mapping.findForward("success");
        }

    }

    /**
     * 附加表增加及修改界面
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */
    private ActionForward performAddonsRead(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {

        // 获得参数
        // String myid = request.getParameter("myid");
        String addonsid = request.getParameter("addonsid");
        String executeId = request.getParameter("executeId");
        String path = TawwpStaticVariable.wwwDir
                + "/workplan/tawwpfile/execute" + "/";

        String filePath = "";
        String url = "";
        String formDataId = "";
        String fileUrl = "";
        try {
            ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
            // 如果其他模块调用附加表显示
            if (addonsid != null && !addonsid.equals("")) {

                TawwpAddonsTableVO tawwpAddonsTableVO = tawwpAddonsMgr
                        .viewAddonsTableVO(addonsid);
                fileUrl = tawwpAddonsTableVO.getUrl();

            } else {
                ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

                TawwpExecuteContent tawwpExecuteContent = new TawwpExecuteContent();
                tawwpExecuteContent = tawwpExecuteMgr
                        .loadExecuteContent(executeId);
                TawwpAddonsTableVO tawwpAddonsTableVO = tawwpAddonsMgr
                        .viewAddonsTableVO(tawwpExecuteContent.getFormId());
                url = TawwpStaticVariable.wwwDir + tawwpAddonsTableVO.getUrl();

                // 如果已经执行
                if (tawwpExecuteContent.getFormDataId() != null
                        && !tawwpExecuteContent.getFormDataId().equals("")) {
                    formDataId = tawwpExecuteContent.getFormDataId();
                }
                filePath = path + formDataId + ".xls";
                File file = new File(filePath);
                // 如果文件不存在
                if (!file.exists()) {
                    Random r = new Random();
                    int iRandom = r.nextInt(900000000); // 取10之中的随机一个
                    String myTag = (1000000000 - iRandom) + "_";
                    TawwpUtil.mkDir(path);
                    formDataId = TawwpUtil.getCurrentDateTime("yyyyMMddHHmmss")
                            + myTag;

                    // 附加表重新赋值
                    filePath = path + formDataId + ".xls";
                    FileInputStream inputStream = new FileInputStream(url);
                    FileOutputStream outputStream = new FileOutputStream(
                            filePath);
                    byte[] readBytes = new byte[1024];
                    int readLength = inputStream.read(readBytes);
                    while (readLength != -1)// 读取数据到文件输出流
                    {
                        outputStream.write(readBytes, 0, readLength);
                        outputStream.flush();
                        readLength = inputStream.read(readBytes);
                    } // 关闭相关对象
                    inputStream.close();
                    outputStream.close();
                    // 修改附加表地址路径
                    tawwpExecuteMgr.saveExcel(executeId, formDataId);
                }
                fileUrl = "/workplan/tawwpfile/execute/" + formDataId + ".xls";
            }
            // 返回页面
            ActionForward actionForward = new ActionForward(
                    "../addons/ntko/showDetail.jsp?path=" + fileUrl);
            actionForward.setRedirect(true);
            return actionForward;

        } catch (Exception e) {

            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
    }

    /*
     * private ActionForward performAddonsRead(ActionMapping mapping ,
     * ActionForm actionForm, HttpServletRequest request , HttpServletResponse
     * response) throws Exception { String rootSaveXMLDir =
     * TawwpStaticVariable.rootSaveXMLDir; //硬盘实际目录 String rootDir =
     * TawwpStaticVariable.rootDir; //web目录 //获得参数 String model =
     * request.getParameter("model"); String reaction =
     * request.getParameter("reaction"); String myid =
     * request.getParameter("myid"); String addonsid =
     * request.getParameter("addonsid"); String action =
     * request.getParameter("action"); String window =
     * request.getParameter("window");
     *
     * StringBuffer StrBuffer = new StringBuffer("");
     *
     * String url = ""; try { String fileName = rootSaveXMLDir + model + "/" +
     * myid + ".xml"; String strFileName = rootDir + fileName; File file = new
     * File(strFileName); //获得TawSystemSessionForm TawSystemSessionForm
     * TawSystemSessionForm = (TawSystemSessionForm) request.
     * getSession().getAttribute("sessionform");
     *
     * //判断文件是否存在 if (file.exists()) { //修改 url = fileName; if
     * (action.equals("edit")) { action = "edit"; } } else { //增加
     * ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr)
     * getBean("tawwpAddonsMgr"); TawwpAddonsTableVO tawwpAddonsTableVO =
     * tawwpAddonsMgr. viewAddonsTableVO(addonsid); url =
     * tawwpAddonsTableVO.getUrl(); if (action.equals("edit")) { action = "add"; } }
     *
     * String xmltype = tawwpAddonsMgr.getAddonsType(url); //获取 StrBuffer =
     * (StringBuffer) tawwpAddonsMgr. getAddonsHtmlBuffer(url,
     * TawSystemSessionForm.getUserid(), action);
     *
     * request.setAttribute("strbuffer", StrBuffer);
     * request.setAttribute("model", model); request.setAttribute("myid", myid);
     * request.setAttribute("xmltype", xmltype);
     * request.setAttribute("reaction", reaction);
     * request.setAttribute("action", action); request.setAttribute("url", url);
     * request.setAttribute("window", window);
     *
     * return mapping.findForward("success"); } catch (Exception e) {
     * generalError(request, e); return mapping.findForward("failure"); } }
     */
    private ActionForward performAddonsView(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {

        // 获得参数
        String action = request.getParameter("action");
        String reaction = request.getParameter("reaction");
        String addonsid = request.getParameter("addonsid");

        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
        TawwpAddonsTableVO tawwpAddonsTableVO = tawwpAddonsMgr
                .viewAddonsTableVO(addonsid);

        String model = tawwpAddonsTableVO.getModel();
        String url = tawwpAddonsTableVO.getUrl();

        try {
            String xmltype = tawwpAddonsMgr.getAddonsType(url);
            TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");

            StringBuffer StrBuffer = (StringBuffer) tawwpAddonsMgr
                    .getAddonsHtmlBuffer(url, TawSystemSessionForm.getUserid(),
                            action);

            request.setAttribute("strbuffer", StrBuffer);
            request.setAttribute("action", action);
            request.setAttribute("model", model);
            request.setAttribute("url", url);
            request.setAttribute("xmltype", xmltype);
            request.setAttribute("reaction", reaction);

            return mapping.findForward("success");

        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
    }

    /**
     * 附加表增加第二种特殊表格的横向元素
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */
    private ActionForward performAddonsAddT2(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {

        String rootSaveXMLDir = TawwpStaticVariable.rootSaveXMLDir; // 硬盘实际目录
        String rootDir = TawwpStaticVariable.rootDir; // web目录
        try {
            ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
            String addonsid = request.getParameter("addonsid");
            String url = request.getParameter("url");
            String action = request.getParameter("action");
            String model = request.getParameter("model");
            String reaction = request.getParameter("reaction");
            String myid = request.getParameter("myid");
            String window = request.getParameter("window");

            String strFileName = rootDir + rootSaveXMLDir + model + "/" + myid
                    + ".xml";
            File file = new File(strFileName);
            // 获得TawSystemSessionForm
            TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");

            // 判断文件是否存在
            if (file.exists()) { // 修改
                url = rootSaveXMLDir + model + "/" + myid + ".xml";
                if (action == null) {
                    action = "edit";
                }

            } else { // 增加
                TawwpAddonsTableVO tawwpAddonsTableVO = tawwpAddonsMgr
                        .viewAddonsTableVO(addonsid);
                url = tawwpAddonsTableVO.getUrl();
                if (action == null) {
                    action = "add";
                }
            }

            // 日志
            ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
            String userId = TawSystemSessionForm.getUserid(); // 当前用户名
            tawwpLogMgr.addLog(userId, "addAddonsT2Element", "");

            request.setAttribute("myid", myid);
            request.setAttribute("reaction", reaction);
            request.setAttribute("model", model);
            request.setAttribute("window", window);
            request.setAttribute("addonsid", addonsid);
            request.setAttribute("action", action);

            // 返回页面
            ActionForward actionForward = new ActionForward(
                    "/tawwpaddons/addonsread.do?action=edit&reaction="
                            + reaction + "&model=" + model + "&myid=" + myid
                            + "&addonsid=" + addonsid + "&window=" + window);
            // addonsread.do?action=edit&myid=wweqsxxx&model=50&addonsid=8a8097c10706d961010706db1fc20001&reaction=/tawwpaddons/addonslist.do&window=new
            actionForward.setRedirect(true);
            return actionForward;
        } catch (Exception e) {
            generalError(request, e);
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }
    }

    /**
     * 通过Excel导入附加表
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */

    private ActionForward performAddonsImport(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        String addonsid = request.getParameter("addonsid");
        String reaction = request.getParameter("reaction");

        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");

        TawwpAddonsTableVO tawwpAddonsTableVO = tawwpAddonsMgr
                .viewAddonsTableVO(addonsid);

        String model = tawwpAddonsTableVO.getModel();
        String url = tawwpAddonsTableVO.getUrl();

        String _importXMLUrl = "";
        ActionMessages messages = new ActionMessages();
        if (actionForm instanceof TawwpUpLoadVO) { // 如果form是uploadsForm
            String encoding = request.getCharacterEncoding();
            if ((encoding != null) && (encoding.equalsIgnoreCase("utf-8"))) {
                response.setContentType("text/html; charset=gb2312"); // 如果没有指定编码，编码格式为gb2312
            }
            TawwpUpLoadVO theForm = (TawwpUpLoadVO) actionForm;
            FormFile file = theForm.getTheFile(); // 取得上传的文件
            // 如果文件不为空
            if (file != null) {
                // 如果扩展名正确
                if (TawwpUtil.upCase(file.getFileName()).endsWith("xls")) {
                    String timeTag = TawwpUtil
                            .getCurrentDateTime("yyyyMMddHHmmss");
                    // 写数据
                    try {
                        InputStream stream = file.getInputStream(); // 把文件读入
                        String filePath = request.getRealPath("/"); // 取当前系统路径

                        String sourceExcel = (filePath
                                + TawwpStaticVariable.tmpUpDir + timeTag + ".xls");
                        OutputStream bos = new FileOutputStream(sourceExcel); // 建立一个上传文件的输出流

                        int bytesRead = 0;
                        byte[] buffer = new byte[8192];
                        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                            bos.write(buffer, 0, bytesRead); // 将文件写入服务器
                        }
                        bos.close();
                        stream.close();

                        // 取随机数
                        Random r = new Random();
                        int iRandom = r.nextInt(90000000); // 取90000000之中的随机一个
                        String myid = TawwpUtil
                                .getCurrentDateTime("yyyyMMddhhmmss")
                                + (100000000 - iRandom) + "_";

                        _importXMLUrl = tawwpAddonsMgr.importAddonsFromExcel(
                                sourceExcel, url, model, myid);
                        // 日志
                        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
                        // 获取当前用户的session中的信息
                        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                                .getSession().getAttribute("sessionform");
                        String userId = TawSystemSessionForm.getUserid(); // 当前用户名
                        tawwpLogMgr.addLog(userId, "importAddonsFromExcel", "");

                    } catch (Exception e) {
                        System.err.print(e);
                        return mapping.findForward("failure");
                    }
                    // 返回页面
                    request.setAttribute("url", _importXMLUrl);
                    ActionForward actionForward = new ActionForward(reaction);
                    actionForward.setRedirect(true);
                    return actionForward;

                } else {
                    messages.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("submit.title.failure"));
                    saveMessages(request, messages);
                    return mapping.findForward("failure");
                }

            } else {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "submit.title.failure"));
                saveMessages(request, messages);
                return mapping.findForward("failure");
            }

        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "submit.title.failure"));
            saveMessages(request, messages);
            return mapping.findForward("failure");
        }

    }

    /**
     * 导出附加表
     *
     * @param mapping    ActionMapping
     * @param actionForm ActionForm
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */

    private ActionForward performAddonsExport(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
        String url = request.getParameter("url");
        String reaction = request.getParameter("reaction");
        String _exportUrl = tawwpAddonsMgr.exportAddonsToExcel(url);
        // 日志
        ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
        // 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getUserid(); // 当前用户名
        tawwpLogMgr.addLog(userId, "exportAddonsToExcel", "");

        // 返回页面
        request.setAttribute("url", _exportUrl);
        ActionForward actionForward = new ActionForward(reaction + "?url="
                + _exportUrl);
        actionForward.setRedirect(true);

        return actionForward;

    }

    /**
     * 管理错误信息，如果有异常出现，则进行处理，将事务回滚
     *
     * @param request HttpServletRequest 请求对象
     * @param e       Exception 异常对象
     */
    private void generalError(HttpServletRequest request, Exception e) {
        ActionMessages aes = new ActionMessages();
        aes.add("EOMS_WORKPLAN_ERROR", new ActionMessage("error.general", e
                .getMessage()));
        saveMessages(request, aes);
        BocoLog.error(this, 2, "[WORKPLAN_ADDONS] Error -", e);
    }

    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

}
