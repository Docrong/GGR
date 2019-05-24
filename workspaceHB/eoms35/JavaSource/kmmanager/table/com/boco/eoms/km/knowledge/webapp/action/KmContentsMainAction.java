package com.boco.eoms.km.knowledge.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.dao.hibernate.TawSystemUserDaoHibernate;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.km.cptroom.bo.KmsystemCptroomBo;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;
import com.boco.eoms.km.duty.bo.KmassignworkBO;
import com.boco.eoms.km.duty.dao.KmrecordDAO;
import com.boco.eoms.km.expert.mgr.KmExpertPhotoMgr;
import com.boco.eoms.km.expert.mgr.KmExpertScoreMgr;
import com.boco.eoms.km.expert.model.KmExpertPhoto;
import com.boco.eoms.km.file.dao.hibernate.KmFileTreeDaoHibernate;
import com.boco.eoms.km.knowledge.mgr.KmContentsMainMgr;
import com.boco.eoms.km.knowledge.util.KmContentsConstants;
import com.boco.eoms.km.knowledge.webapp.form.KmContentsForm;
import com.boco.eoms.km.statics.mgr.ScoreOrderStatisticMgr;
import com.boco.eoms.km.table.dao.hibernate.KmTableGeneralDaoHibernate;

/**
 * <p>
 * Title:首页知识最新更新
 * </p>
 * <p>
 * Description:首页知识最新更新
 * </p>
 * 
 * 
 * @moudle.getAuthor() wangzhiyong
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmContentsMainAction extends BaseAction {

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
		return search(mapping, form, request, response);
	}

	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmContentsMainMgr kmContentsMainMgr = (KmContentsMainMgr) getBean("kmContentsMainMgr");
		int count = Integer.parseInt(request.getParameter("count"));
		String type = request.getParameter("type");
		List list = kmContentsMainMgr.getKmContentsMain(count, type);

		JSONArray json = new JSONArray();

		KmTableGeneralDaoHibernate kmTableGeneralDaoHibernate = (KmTableGeneralDaoHibernate) getBean("kmTableGeneralDao");
		TawSystemUserDaoHibernate tawSystemUserDaoHibernate = (TawSystemUserDaoHibernate) getBean("tawSystemUserDao");
		KmFileTreeDaoHibernate kmFileTreeDao = (KmFileTreeDaoHibernate) getBean("kmFileTreeDao");
		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			KmContentsForm kmContentsForm = (KmContentsForm) rowIt.next();
			JSONObject jitem = new JSONObject();
			if (kmContentsForm.getThemeId() == null
					|| "".equals(kmContentsForm.getThemeId())) {
				jitem.put("tableName", kmFileTreeDao.id2Name(kmContentsForm
						.getTableId()));
				System.out.println("aa"
						+ kmFileTreeDao.id2Name(kmContentsForm.getTableId()));
			} else {
				jitem.put("tableName", kmTableGeneralDaoHibernate
						.id2Name(kmContentsForm.getTableId()));
			}
			jitem.put("contentTitle", kmContentsForm.getContentTitle());
			jitem.put("createTime", kmContentsForm.getCreateTime() == null ? ""
					: kmContentsForm.getCreateTime());
			jitem.put("createUser", tawSystemUserDaoHibernate
					.id2Name(kmContentsForm.getCreateUser()));
			jitem.put("id", kmContentsForm.getId());
			jitem.put("themeId", kmContentsForm.getThemeId() == null ? ""
					: kmContentsForm.getThemeId());
			jitem.put("tableId", kmContentsForm.getTableId() == null ? ""
					: kmContentsForm.getTableId());
			json.put(jitem);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		return null;
	}

	public ActionForward showUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 读取：当前操作用户信息
		TawSystemSessionForm sessionform = this.getUser(request);
		String operateUserId = sessionform.getUserid();
		KmExpertScoreMgr kmExpertScoreMgr = (KmExpertScoreMgr) getBean("kmExpertScoreMgr");
		ScoreOrderStatisticMgr scoreOrderStatisticMgr = (ScoreOrderStatisticMgr) getBean("scoreOrderStatisticMgr");
		KmExpertPhotoMgr kmExpertPhotoMgr = (KmExpertPhotoMgr) getBean("kmExpertPhotoMgr");

		KmExpertPhoto kmExpertPhoto = kmExpertPhotoMgr
				.getKmExpertPhotoByUserId(operateUserId);
		int expertSum = kmExpertScoreMgr.getSumByexpertUserId(operateUserId);
		List list = scoreOrderStatisticMgr
				.getUserKnowledScoreOByUserId(operateUserId);
		JSONArray json = new JSONArray();
		JSONObject jitem = new JSONObject();
		jitem.put("expertSum", expertSum);
		jitem.put("userSum", list.get(0));
		if (kmExpertPhoto.getPhoto() != null
				&& !kmExpertPhoto.getPhoto().equals("")) {
			jitem.put("head", kmExpertPhoto.getPhoto());
		} else {
			jitem.put("head", "man.gif");
		}
		json.put(jitem);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		return null;
	}

	/**
	 * 首页知识积分排名
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mainScoreOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int orderNumber = StaticMethod.null2int(request
				.getParameter("orderNumber"));
		ScoreOrderStatisticMgr scoreOrderStatisticMgr = (ScoreOrderStatisticMgr) getBean("scoreOrderStatisticMgr");
		List list1 = scoreOrderStatisticMgr.getKnowledgeCountOrder(orderNumber);
		List list2 = scoreOrderStatisticMgr
				.getUserKnowledScoreOrder(orderNumber);
		List list3 = scoreOrderStatisticMgr.getUserScoreOrder(orderNumber);
		List list4 = scoreOrderStatisticMgr.getKnowledgeReadOrder(orderNumber);

		JSONArray json1 = new JSONArray();
		JSONArray json2 = new JSONArray();
		JSONArray json3 = new JSONArray();
		JSONArray json4 = new JSONArray();

		KmTableGeneralDaoHibernate kmTableGeneralDaoHibernate = (KmTableGeneralDaoHibernate) getBean("kmTableGeneralDao");
		TawSystemUserDaoHibernate tawSystemUserDaoHibernate = (TawSystemUserDaoHibernate) getBean("tawSystemUserDao");
		for (int i = 0; i < list1.size(); i++) {
			Object[] objs = (Object[]) list1.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("number", i);
			jitem.put("table", kmTableGeneralDaoHibernate.id2Name(objs[0]
					.toString()));
			jitem.put("order", objs[1].toString());
			json1.put(jitem);
		}

		for (int i = 0; i < list2.size(); i++) {
			Object[] objs = (Object[]) list2.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("number", i);
			jitem.put("user", tawSystemUserDaoHibernate.id2Name(objs[0]
					.toString()));
			jitem.put("order", objs[1].toString());
			json2.put(jitem);
		}

		for (int i = 0; i < list3.size(); i++) {
			Object[] objs = (Object[]) list3.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("number", i);
			jitem.put("user", tawSystemUserDaoHibernate.id2Name(objs[0]
					.toString()));
			jitem.put("order", objs[1].toString());
			json3.put(jitem);
		}
		
		for (int i=0;i<list4.size();i++) {
			Object[] objs = (Object[])list4.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("number", i);
			jitem.put("title", objs[0].toString());
			jitem.put("order", objs[1].toString());
			json4.put(jitem);
		}

		response.setCharacterEncoding("utf-8");
		response.getWriter().write(
				"[" + json1.toString() + "," + json2.toString() + ","
						+ json3.toString() + "]");

		return null;
	}

	/**
	 * 分页显示在线参加考试状态记录列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchAll(ActionMapping mapping, ActionForm form,
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

		String type = request.getParameter("type");
		KmContentsMainMgr kmContentsMainMgr = (KmContentsMainMgr) getBean("kmContentsMainMgr");
		Map map = (Map) kmContentsMainMgr.getKmContentsMains(pageIndex,
				pageSize, "", type);

		List list = (List) map.get("result");
		request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 显示当前在线值班人员列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showOnDuty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
		KmassignworkBO tawRmAssignworkBO = new KmassignworkBO();;
		KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
		KmsystemCptroom tawApparatusroom = new KmsystemCptroom();
		String strSelectRoomName = null;
		KmrecordDAO kmrecordDAO = new KmrecordDAO();
		TawSystemUser tawSystemUser = new TawSystemUser();
		Vector selectRoom = tawRmAssignworkBO.getRoomSelect();
		tawRmAssignworkBO = new KmassignworkBO(ds);
		int roomId = 0;
		String onDuty = "";
		if (selectRoom.size() > 0) {
			for (int i = 0; i < selectRoom.size(); i++) {
				tawApparatusroom = cptroomBO.getKmsystemCptroomById(
						new Integer((String) selectRoom.elementAt(i)),
						0);
				if (tawApparatusroom != null) {
					strSelectRoomName = StaticMethod.null2String(tawApparatusroom.getRoomname());
					roomId =  StaticMethod.nullObject2int(selectRoom.elementAt(i));
					onDuty = onDuty + strSelectRoomName+": <br>";
					int workSerial = kmrecordDAO.receivedrWorkSerial(roomId);
					List dutyUsers = kmrecordDAO.listDutymanInfo(workSerial);
					for (int j = 0; j < dutyUsers.size(); j++) {
						tawSystemUser = (TawSystemUser)dutyUsers.get(j);
						onDuty = onDuty + tawSystemUser.getUsername()+" ;";
					}
					onDuty = onDuty + "<br>";
				}
			}
		}
		JSONObject jitem = new JSONObject();
		jitem.put("text", onDuty);
		JSONArray json = new JSONArray();
		json.put(jitem);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		return null;
	}
	/**
	 * 创建atom源
	 */
	public ActionForward performGetAtomLists(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			KmContentsMainMgr kmContentsMainMgr = (KmContentsMainMgr) getBean("kmContentsMainMgr");
			int count = StaticMethod.null2int(request.getParameter("count"), 10);
//			String type = request.getParameter("type");
			List list = kmContentsMainMgr.getKmContentsMain(count, "1"); 
//			创建ATOM源
			Factory factory = null;
			try {
				factory = Abdera.getNewFactory();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Feed feed = factory.newFeed();
			KmTableGeneralDaoHibernate kmTableGeneralDaoHibernate = (KmTableGeneralDaoHibernate) getBean("kmTableGeneralDao");
			KmFileTreeDaoHibernate kmFileTreeDao = (KmFileTreeDaoHibernate) getBean("kmFileTreeDao");
			TawSystemUserDaoHibernate tawSystemUserDaoHibernate = (TawSystemUserDaoHibernate) getBean("tawSystemUserDao");
			for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
				KmContentsForm kmContentsForm = (KmContentsForm) rowIt.next();
				Entry entry = feed.insertEntry();
				entry.setSummary("");
				if (kmContentsForm.getThemeId() == null
						|| "".equals(kmContentsForm.getThemeId())) {
					entry.setSummary(kmFileTreeDao.id2Name(kmContentsForm
							.getTableId()));
				} else {
					entry.setSummary(kmTableGeneralDaoHibernate
							.id2Name(kmContentsForm.getTableId()));
				}
				entry.setTitle(kmContentsForm.getContentTitle());
//			获取当期url
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/kmmanager/kmContentss.do?method=detail";
				String themeId = kmContentsForm.getThemeId() == null ? "": kmContentsForm.getThemeId();
				String tableId = kmContentsForm.getTableId() == null ? "": kmContentsForm.getTableId();
				String url = basePath + "&ID=" + kmContentsForm.getId() + "&TABLE_ID=" + tableId + "&THEME_ID=" + themeId;
				entry.setContent(url);
				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = fmt.parse(kmContentsForm.getCreateTime());
				entry.setPublished(date);
				Person person = entry.addAuthor("");
				person.setName(tawSystemUserDaoHibernate.id2Name(kmContentsForm.getCreateUser()));
			}
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/kmmanager/kmContentss.do?method=searchAll&type=1";
			feed.setTitle(basePath);
//			每页显示条数
			feed.setText(list.size()+"");
			OutputStream os = response.getOutputStream();
			PrintStream ps = new PrintStream(os);
			feed.getDocument().writeTo(ps);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}