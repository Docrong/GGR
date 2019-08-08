package com.boco.eoms.km.knowledge.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
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
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.km.knowledge.mgr.KmContentsMgr;
import com.boco.eoms.km.knowledge.mgr.KmContentsSubscribeMgr;
import com.boco.eoms.km.knowledge.model.KmContents;
import com.boco.eoms.km.knowledge.model.KmContentsSubscribe;
import com.boco.eoms.km.knowledge.model.KmContentsSubscribeTable;
import com.boco.eoms.km.knowledge.util.KmContentsConstants;
import com.boco.eoms.km.knowledge.webapp.form.KmContentsForm;
import com.boco.eoms.km.table.mgr.KmTableColumnMgr;
import com.boco.eoms.km.table.mgr.KmTableGeneralMgr;
import com.boco.eoms.km.table.mgr.KmTableThemeMgr;
import com.boco.eoms.km.table.model.KmTableGeneral;
import com.boco.eoms.km.table.model.KmTableTheme;

/**
 * 知识订阅的Action
 *
 * @author Administrator
 */
public class KmContentsSubscribeAction extends BaseAction {

    /**
     * 未指定方法时默认调用的方法
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("list");
    }


    /**
     * 订阅知识
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward subscribe(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("subscribed");
    }

    /**
     * 查询订阅的知识内容（根据订阅的作者）
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchSubscribe(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        KmContentsSubscribeMgr kmContentsSubscribeMgr = (KmContentsSubscribeMgr) getBean("kmContentsSubscribeMgr");
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        //等到当前登录用户的信息
        String userName = this.getUser(request).getUserid();
        String subscribeUser = userName;
        //查询当前用户所订阅的创建者
        List listAllKmContents = new ArrayList();
        List list = kmContentsSubscribeMgr.listKmContentsSubscribe(subscribeUser);
        for (int i = 0; i < list.size(); i++) {
            KmContentsSubscribe kmContentsSubscribe = (KmContentsSubscribe) list.get(i);
            String createUser = kmContentsSubscribe.getCreateUser();
            List listKmContents = kmContentsMgr.getKmContentsList(createUser);
            //将所用的知识结果集放到一个list中
            listAllKmContents.addAll(listKmContents);
        }
        request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, listAllKmContents);
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("listSubscribe");
    }


    /**
     * 查询订阅的知识内容（根据订阅的分类）
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchSubscribeTable(ActionMapping mapping, ActionForm form,
                                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        KmContentsSubscribeMgr kmContentsSubscribeMgr = (KmContentsSubscribeMgr) getBean("kmContentsSubscribeMgr");
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        //等到当前登录用户的信息
        String userName = this.getUser(request).getUserid();
        String subscribeUser = userName;
        //查询当前用户所订阅的创建者
        List listAllKmContents = new ArrayList();
        List list = kmContentsSubscribeMgr.listKmContentsSubscribeTable(subscribeUser);
        for (int i = 0; i < list.size(); i++) {
            KmContentsSubscribeTable kmContentsSubscribeTable = (KmContentsSubscribeTable) list.get(i);
            String themeId = kmContentsSubscribeTable.getThemeId();
            if (themeId.length() > 3) {//如果订阅的是子分类
                List listKmContents = kmContentsMgr.getKmContentsListByThemeId(themeId);
                listAllKmContents.addAll(listKmContents);
            } else {//如果订阅的是根分类
                List listKmContents = kmContentsMgr.getKmContentsListLinkThemeId(themeId);
                listAllKmContents.addAll(listKmContents);
            }
        }
        request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, listAllKmContents);
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("listSubscribe");
    }

    /**
     * 根据创建人
     * 订阅订阅知识
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward subscribeCreateUser(ActionMapping mapping, ActionForm form,
                                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        //String createUser= "[]";
        //String createUserName = "";
        KmContentsSubscribeMgr kmContentsSubscribeMgr = (KmContentsSubscribeMgr) getBean("kmContentsSubscribeMgr");
        //获得当前登录人信息 即订阅人信息
        String subscribeUser = this.getUser(request).getUserid();
        //查询当前登录人的订阅信息
        List list = kmContentsSubscribeMgr.listKmContentsSubscribe(subscribeUser);
        List listCreateUser = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            KmContentsSubscribe kmContentsSubscribe = (KmContentsSubscribe) list.get(i);
            String createUser = kmContentsSubscribe.getCreateUser();
            listCreateUser.add(createUser);
        }
        request.setAttribute("listCreateUser", listCreateUser);
        request.setAttribute("resultSize", String.valueOf(list.size()));
        request.setAttribute(KmContentsConstants.KMCONTENTSSUBSCRIBE_LIST, list);
        request.setAttribute("pageSize", pageSize);

//		//将createUser转化为中文名字
//		for(int i=0;i<list.size();i++){
//			KmContentsSubscribe kmContentsSubscribe = (KmContentsSubscribe)list.get(i);
//			createUserName = createUserName + 
//							DictMgrLocator.getId2NameService()
//							.id2Name(kmContentsSubscribe.getCreateUser(),"tawSystemUserDao") 
//							+ ",";
//		}
//		if(createUserName.endsWith(",")){
//			createUserName = createUserName.substring(0, createUserName.length() - 1);
//		}
//		
//		//将createUser以及createUserName转化为json格式 用于页面显示
//		JSONArray jsonRoot = new JSONArray();
//		for(int i=0;i<list.size();i++){
//			KmContentsSubscribe kmContentsSubscribe = (KmContentsSubscribe)list.get(i);
//			JSONObject jitem = new JSONObject();
//			jitem.put("id", kmContentsSubscribe.getCreateUser());
//			jitem.put("name", DictMgrLocator.getId2NameService()
//					.id2Name(kmContentsSubscribe.getCreateUser(),"tawSystemUserDao"));
//			jsonRoot.put(jitem);
//		}
//		createUser = jsonRoot.toString();
//		
//		request.setAttribute("createUser", createUser);
//		request.setAttribute("createUserName", createUserName);
        return mapping.findForward("subscribeCreateUser");
    }

    /**
     * 根据id删除根据创建人订阅的信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward removeCreateUser(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("ID");
        KmContentsSubscribeMgr kmContentsSubscribeMgr = (KmContentsSubscribeMgr) getBean("kmContentsSubscribeMgr");
        kmContentsSubscribeMgr.removeKmContentsSubscribeById(id);
        return mapping.findForward("success");
    }

    /**
     * 根据分类
     * 根据模型定义
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward subscribeTable(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //查询所有根据分类订阅的信息 并进行分页显示
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmContentsSubscribeMgr kmContentsSubscribeMgr = (KmContentsSubscribeMgr) getBean("kmContentsSubscribeMgr");
        //获得当前登录人信息 即订阅人信息
        String subscribeUser = this.getUser(request).getUserid();
        //查询当前登录人的分类订阅信息
        List list = kmContentsSubscribeMgr.listKmContentsSubscribeTable(subscribeUser);
        request.setAttribute("resultSize", String.valueOf(list.size()));
        request.setAttribute(KmContentsConstants.KMCONTENTSSUBSCRIBETABLE_LIST, list);
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("subscribeTable");
    }

    /**
     * 根据创建人
     * 保存订阅的知识信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveSubscribe(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmContentsSubscribeMgr kmContentsSubscribeMgr = (KmContentsSubscribeMgr) getBean("kmContentsSubscribeMgr");
        //登录人员即订阅人的名字
        String subscribeUser = this.getUser(request).getUserid();
        KmContentsForm kmContentsForm = (KmContentsForm) form;
        String cu = kmContentsForm.getCreateUser();
        String[] createUsers = cu.split(",");
        //删除该用户以前的订阅的知识信息
        //kmContentsSubscribeMgr.removeKmContentsSubscribe(subscribeUser);
        if (createUsers != null) {
            for (int i = 0; i < createUsers.length; i++) {
                String createUser = createUsers[i];
                KmContentsSubscribe kmContentsSubscribe = new KmContentsSubscribe();
                //格式化时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = formatter.format(new Date());
                kmContentsSubscribe.setCreateTime(date);
                kmContentsSubscribe.setSubscribeUser(subscribeUser);
                kmContentsSubscribe.setCreateUser(createUser);
                kmContentsSubscribeMgr.saveKmContentsSubscribe(kmContentsSubscribe);
            }
        }
        return mapping.findForward("success");
    }

    /**
     * 根据模型
     * 保存根据模型定义的信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveSubscribeTable(ActionMapping mapping, ActionForm form,
                                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmContentsSubscribeMgr kmContentsSubscribeMgr = (KmContentsSubscribeMgr) getBean("kmContentsSubscribeMgr");
        //登录人员即订阅人的名字
        String subscribeUser = this.getUser(request).getUserid();
        KmContentsForm kmContentsForm = (KmContentsForm) form;

        String ti = kmContentsForm.getThemeId();
        String[] themeIds = ti.split(",");
        //删除该用户以前的订阅的知识信息
        //kmContentsMgr.removeKmContentsSubscribe(subscribeUser);
        if (themeIds != null) {
            for (int i = 0; i < themeIds.length; i++) {
                String themeId = themeIds[i];
                KmContentsSubscribeTable kmContentsSubscribeTable = new KmContentsSubscribeTable();
                //格式化时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = formatter.format(new Date());
                kmContentsSubscribeTable.setCreateTime(date);
                kmContentsSubscribeTable.setSubscribeUser(subscribeUser);
                kmContentsSubscribeTable.setThemeId(themeId);
                kmContentsSubscribeMgr.saveKmContentsSubscribeTable(kmContentsSubscribeTable);
                System.out.print("invoke");
            }
        }
        return mapping.findForward("success");
    }

    /**
     * 根据id删除根据分类订阅的信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward removeTable(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("ID");
        KmContentsSubscribeMgr kmContentsSubscribeMgr = (KmContentsSubscribeMgr) getBean("kmContentsSubscribeMgr");
        kmContentsSubscribeMgr.removeKmContentsSubscribeTableById(id);
        return mapping.findForward("success");
    }


}
