package com.boco.eoms.commons.hongxun.webapp.action;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.hongxun.dao.TawCommonHongXunDao;

import com.boco.eoms.commons.loging.BocoLog;

public class TawCommonHongXunAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();

		if ("REDADD".equalsIgnoreCase(myaction)) { // 所有未处理工单
			myforward = performRedAdd(mapping, actionForm, request, response);
		} else if ("REDSAVE".equalsIgnoreCase(myaction)) {
			myforward = performRedSave(mapping, actionForm, request, response);
		} else if ("REDLIST".equalsIgnoreCase(myaction)) {
			myforward = performRedList(mapping, actionForm, request, response);
		} else if ("REDQUERY".equalsIgnoreCase(myaction)) {
			myforward = performRedQuery(mapping, actionForm, request, response);
		} else if ("REDUPDATE".equalsIgnoreCase(myaction)) {
			myforward = performRedUpdate(mapping, actionForm, request, response);
		} else if ("REDUPDO".equalsIgnoreCase(myaction)) {
			myforward = performRedUpDo(mapping, actionForm, request, response);
		} else if ("REDSEND".equalsIgnoreCase(myaction)) {
			myforward = performRedSend(mapping, actionForm, request, response);
		} else if ("RED".equalsIgnoreCase(myaction)) {
			myforward = performRedAdd(mapping, actionForm, request, response);
		} else if ("REDONEGAOJIN".equalsIgnoreCase(myaction)) {
			myforward = performOneRedGaoJin(mapping, actionForm, request,
					response);
		} else if ("REDGAOJIN".equalsIgnoreCase(myaction)) {
			myforward = performRedGaoJin(mapping, actionForm, request, response);
		} else if ("TWOGAOJIN".equalsIgnoreCase(myaction)) {
			myforward = performTwoGaoJin(mapping, actionForm, request, response);
		} else if ("THREEGAOJIN".equalsIgnoreCase(myaction)) {
			 myforward = performThreeGaoJin(mapping, actionForm, request, response);
		}else if ("TWO".equalsIgnoreCase(myaction)) {
			myforward = performRedAdd(mapping, actionForm, request, response);
		} else if ("THREE".equalsIgnoreCase(myaction)) {
			 myforward = performRedAdd(mapping, actionForm, request, response);
		}
		else if ("COMADD".equalsIgnoreCase(myaction)) {
			 myforward = performComAdd(mapping, actionForm, request, response);
		}else if ("XIAOZUADD".equalsIgnoreCase(myaction)) {
			 myforward = performXiaozuAdd(mapping, actionForm, request, response);
		}else if ("COMSAVE".equalsIgnoreCase(myaction)) {
			 myforward = performComSave(mapping, actionForm, request, response);
		}
		else if ("COMLIST".equalsIgnoreCase(myaction)) {
			 myforward = performComList(mapping, actionForm, request, response);
		}else if ("XIAOZUSAVE".equalsIgnoreCase(myaction)) {
			 myforward = performXiaoSave(mapping, actionForm, request, response);
		}
		else if ("XIAOZULIST".equalsIgnoreCase(myaction)) {
			 myforward = performXiaozuList(mapping, actionForm, request, response);
		}else if ("MODELSEND".equalsIgnoreCase(myaction)) {
			 myforward = performModelSend(mapping, actionForm, request, response);
		}else if ("SENDGAOJIN".equalsIgnoreCase(myaction)) {
			 myforward = performSendgaojin(mapping, actionForm, request, response);
		}

		return myforward;

	}

	private ActionForward performRedAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		List xiaozuList = new ArrayList();
		List comList = new ArrayList();
		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
			.getInstance().getBean("tawCommonHongXunDao");
			xiaozuList=dao.getXiaozuAll();
			comList=dao.getComAll();
			request.setAttribute("xiaozuList", xiaozuList);
			request.setAttribute("comList", comList);

		} catch (Exception e) {
			BocoLog.error(this, "打开页面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	/**
	 * 添加人员
	 * 
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performRedSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String com_name = request.getParameter("com_name");
		String xiaozu_name = request.getParameter("xiaozu_name");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String zhize = request.getParameter("zhize");
		String remark = request.getParameter("remark");

		// List deptList = new ArrayList();
		// String deptListAll="";

		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			dao.insertRedUser(com_name, xiaozu_name, name, tel, zhize, remark);

			request.setAttribute("com_name", com_name);
			request.setAttribute("xiaozu_name", "");
			request.setAttribute("name", "");
			request.setAttribute("tel", "");
			request.setAttribute("zhize", "");
			request.setAttribute("remark", "");

		} catch (Exception e) {
			BocoLog.error(this, "添加人员出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	private ActionForward performRedList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String com_name = request.getParameter("com_name");
		String xiaozu_name = request.getParameter("xiaozu_name");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String zhize = request.getParameter("zhize");
		String remark = request.getParameter("remark");

		List redList = new ArrayList();
		// String deptListAll="";

		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			redList = dao.getRedUser(com_name, xiaozu_name, name, tel, zhize,
					remark);
			// tawInformationDao.insertRedUser(com_name,xiaozu_name,name,tel,zhize,remark);

			request.setAttribute("REDLIST", redList);
			// request.setAttribute("NAME",name);
			// request.setAttribute("MOBILE",mobile);
			// request.setAttribute("PHONE",phone);
			// request.setAttribute("TRUST",trust);
			// request.setAttribute("DEPTID",deptId);

		} catch (Exception e) {
			BocoLog.error(this, "查询出错 " + e.getMessage());
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	private ActionForward performRedQuery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		List xiaozuList = new ArrayList();
		List comList = new ArrayList();
		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
			.getInstance().getBean("tawCommonHongXunDao");
			xiaozuList=dao.getXiaozuAll();
			comList=dao.getComAll();
			request.setAttribute("xiaozuList", xiaozuList);
			request.setAttribute("comList", comList);

		} catch (Exception e) {
			BocoLog.error(this, "查询界面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");

	}

	private ActionForward performRedUpdate(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String id = null2String(request.getParameter("id"));
		String flag = null2String(request.getParameter("flag"));
		List xiaozuList = new ArrayList();
		List comList = new ArrayList();
		List redList = new ArrayList();
		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			redList = dao.getOneRedUser(id);
			Iterator iter = redList.iterator();
			Map map = (Map) iter.next();
			xiaozuList=dao.getXiaozuAll();
			comList=dao.getComAll();
			request.setAttribute("xiaozuList", xiaozuList);
			request.setAttribute("comList", comList);
			request.setAttribute("id", id);
			request.setAttribute("com_name", map.get("com_name").toString()
					.trim());
			request.setAttribute("xiaozu_name", map.get("xiaozu_name")
					.toString().trim());
			request.setAttribute("name", map.get("name").toString().trim());
			request.setAttribute("tel", map.get("tel").toString().trim());
			request.setAttribute("zhize", map.get("zhize").toString().trim());
			request.setAttribute("remark", map.get("remark").toString().trim());
			request.setAttribute("flag", flag);

		} catch (Exception e) {
			BocoLog.error(this, "更新界面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	private ActionForward performRedUpDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String com_name = request.getParameter("com_name");
		String xiaozu_name = request.getParameter("xiaozu_name");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String zhize = request.getParameter("zhize");
		String remark = request.getParameter("remark");
		String flag = request.getParameter("flag");

		// List redList = new ArrayList();
		// String deptListAll="";

		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			if (flag.equals("0")) {
				dao.redDel(id);
			} else if (flag.equals("1")) {
				dao.redDoUpdate(id, com_name, xiaozu_name, name, tel, zhize,
						remark);
			}

		} catch (Exception e) {
			BocoLog.error(this, "更新或者删除出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	private ActionForward performRedSend(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String id = null2String(request.getParameter("id"));

		List redList = new ArrayList();
		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			redList = dao.getOneRedUser(id);
			Iterator iter = redList.iterator();
			Map map = (Map) iter.next();

			request.setAttribute("id", id);
			request.setAttribute("com_name", map.get("com_name").toString()
					.trim());
			request.setAttribute("xiaozu_name", map.get("xiaozu_name")
					.toString().trim());
			request.setAttribute("name", map.get("name").toString().trim());
			request.setAttribute("tel", map.get("tel").toString().trim());
			request.setAttribute("zhize", map.get("zhize").toString().trim());
			request.setAttribute("remark", map.get("remark").toString().trim());

		} catch (Exception e) {
			BocoLog.error(this, "短信发送界面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	private ActionForward performOneRedGaoJin(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String id = null2String(request.getParameter("id"));
		String com_name = null2String(request.getParameter("com_name"));
		String xiaozu_name = null2String(request.getParameter("xiaozu_name"));
		String name = null2String(request.getParameter("name"));
		String tel = null2String(request.getParameter("tel"));
		String zhize = null2String(request.getParameter("zhize"));
		String remark = null2String(request.getParameter("remark"));
		List listtemp = new ArrayList();
		List redList = new ArrayList();

		try {
			listtemp.add(id);
			listtemp.add(com_name);
			listtemp.add(xiaozu_name);
			listtemp.add(name);
			listtemp.add(tel);
			listtemp.add(zhize);
			listtemp.add(remark);
			redList.add(listtemp);

			request.setAttribute("redList", redList);

		} catch (Exception e) {
			BocoLog.error(this, "短信发送出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	private ActionForward performRedGaoJin(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		List redList = new ArrayList();
		List List = new ArrayList();

		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			List = dao.getRedUser("", "", "", "", "", "");
			Iterator _objIterator = List.iterator();
			while (_objIterator.hasNext()) {
				List listtemp = new ArrayList();
				Map _objMap = (Map) _objIterator.next();

				listtemp.add(StaticMethod.nullObject2String(_objMap.get("id")));
				listtemp.add(StaticMethod.nullObject2String(_objMap
						.get("com_name")));
				listtemp.add(StaticMethod.nullObject2String(_objMap
						.get("xiaozu_name")));
				listtemp.add(StaticMethod
						.nullObject2String(_objMap.get("name")));
				listtemp
						.add(StaticMethod.nullObject2String(_objMap.get("tel")));
				listtemp.add(StaticMethod.nullObject2String(_objMap
						.get("zhize")));
				listtemp.add(StaticMethod.nullObject2String(_objMap
						.get("remark")));
				redList.add(listtemp);

			}

			request.setAttribute("redList", redList);

		} catch (Exception e) {
			BocoLog.error(this, "1级短信发送出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	private ActionForward performTwoGaoJin(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		List redList = new ArrayList();
		String zhize = request.getParameter("zhize");

		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			redList = dao.getTwoRedUser("'应急通信办公室','无线保障小组','传输保障小组','网管监控小组'",
					"", "", "", "", "", zhize.trim());

			request.setAttribute("redList", redList);
			// '应急通信办公室','无线保障小组','传输保障小组','网管监控小组'

		} catch (Exception e) {
			BocoLog.error(this, "2级短信发送出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	
	private ActionForward performComAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {

		} catch (Exception e) {
			BocoLog.error(this, "打开页面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	private ActionForward performXiaozuAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {

		} catch (Exception e) {
			BocoLog.error(this, "打开页面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}

	private ActionForward performThreeGaoJin(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response){

		List redList = new ArrayList();
		String model = request.getParameter("zhize");
		
		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
			.getInstance().getBean("tawCommonHongXunDao");
			redList = dao.getThreeRedUser("", "", "", "", "", "",
					model.trim());

			request.setAttribute("redList", redList);
			// '应急通信办公室','无线保障小组','传输保障小组','网管监控小组'

		} catch (Exception e) {
			BocoLog.error(this, "3级短信发送出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	
	private ActionForward performComSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String com_name = request.getParameter("com_name");

	

		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			dao.insertCom(com_name);

			

		} catch (Exception e) {
			BocoLog.error(this, "添加机构出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	private ActionForward performComList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		

		List redList = new ArrayList();
		

		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			redList = dao.getComList();
		

			request.setAttribute("REDLIST", redList);
			

		} catch (Exception e) {
			BocoLog.error(this, "查询出错 " + e.getMessage());
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	private ActionForward performXiaoSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String xiaozu_name = request.getParameter("xiaozu_name");

	

		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			dao.insertXiaozu(xiaozu_name);

			

		} catch (Exception e) {
			BocoLog.error(this, "添加机构出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	private ActionForward performModelSend(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		List xiaozuList = new ArrayList();
		List comList = new ArrayList();
		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
			.getInstance().getBean("tawCommonHongXunDao");
			xiaozuList=dao.getXiaozuAll();
			comList=dao.getComAll();
			request.setAttribute("xiaozuList", xiaozuList);
			request.setAttribute("comList", comList);

		} catch (Exception e) {
			BocoLog.error(this, "打开页面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	private ActionForward performXiaozuList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		

		List redList = new ArrayList();
		

		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonHongXunDao");
			redList = dao.getXiaozuList();
		

			request.setAttribute("REDLIST", redList);
			

		} catch (Exception e) {
			BocoLog.error(this, "查询出错 " + e.getMessage());
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	private ActionForward performSendgaojin(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String selvalue = null2String(request.getParameter("selvalue"));
		String times=null2String(request.getParameter("endDate"));
		String address1=null2String(request.getParameter("h_bigadd"));
		String address2=null2String(request.getParameter("h_smadd"));
		String something1=null2String(request.getParameter("h_bigev"));
		String something2=null2String(request.getParameter("h_smev"));
		String remark=null2String(request.getParameter("content"));
		

		List redList = new ArrayList();
		try {
			TawCommonHongXunDao dao = (TawCommonHongXunDao) ApplicationContextHolder
			.getInstance().getBean("tawCommonHongXunDao");
			
			String add2="";
			if(address2.equals("")){
				
			}else{
				add2=dao.getDictName(address2);	
			}
			
			String content=times+","+dao.getDictName(address1)+add2+","+dao.getDictName(something1)
			+dao.getDictName(something2)+","+remark;
			
			
			
			String [] coms=selvalue.split(",");
			for(int i=0;i<coms.length;i++){
			List list = new ArrayList();	
			list = dao.getRedUser(coms[i], "", "", "", "", "");	
			Iterator _objIterator = list.iterator();
			while (_objIterator.hasNext()) {
				List listtemp = new ArrayList();
				Map _objMap = (Map) _objIterator.next();

				listtemp.add(StaticMethod.nullObject2String(_objMap.get("id")));
				listtemp.add(StaticMethod.nullObject2String(_objMap
						.get("com_name")));
				listtemp.add(StaticMethod.nullObject2String(_objMap
						.get("xiaozu_name")));
				listtemp.add(StaticMethod
						.nullObject2String(_objMap.get("name")));
				listtemp
						.add(StaticMethod.nullObject2String(_objMap.get("tel")));
				listtemp.add(content);
				listtemp.add(StaticMethod.nullObject2String(_objMap
						.get("remark")));
				redList.add(listtemp);
				List listtemp1 = new ArrayList();
				
				listtemp1.add(StaticMethod.nullObject2String(_objMap.get("id")));
				listtemp1.add(StaticMethod.nullObject2String(_objMap
						.get("com_name")));
				listtemp1.add(StaticMethod.nullObject2String(_objMap
						.get("xiaozu_name")));
				listtemp1.add(StaticMethod
						.nullObject2String(_objMap.get("name")));
				listtemp1
						.add(StaticMethod.nullObject2String(_objMap.get("tel")));
				listtemp1.add("职责内容："+StaticMethod.nullObject2String(_objMap.get("zhize")));
				listtemp1.add(StaticMethod.nullObject2String(_objMap
						.get("remark")));
				
				redList.add(listtemp1);
			}
			
			
			}
			
			
			
			
			request.setAttribute("redList", redList);
			

		} catch (Exception e) {
			BocoLog.error(this, "短信发送界面出错 " + e.getMessage());
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}	
	public static String null2String(String s) {
		return s == null ? "" : s;
	}
}
