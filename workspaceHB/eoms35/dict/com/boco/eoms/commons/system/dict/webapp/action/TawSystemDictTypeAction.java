package com.boco.eoms.commons.system.dict.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.dict.webapp.form.TawSystemDictTypeForm;
import com.boco.eoms.commons.ui.listitem.TawCommonsUIListItem;
import com.boco.eoms.workplan.cache.TawWorkplanCacheBean;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawSystemDictType object
 * 
 * @struts.action name="tawSystemDictTypeForm" path="/tawSystemDictTypes"
 *                scope="request" validate="false" parameter="method"
 *                input="mainMenu"
 * @struts.action name="tawSystemDictTypeForm" path="/editTawSystemDictType"
 *                scope="request" validate="false" parameter="method"
 *                input="list"
 * @struts.action name="tawSystemDictTypeForm" path="/saveTawSystemDictType"
 *                scope="request" validate="true" parameter="method"
 *                input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawSystemDictType/tawSystemDictTypeForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawSystemDictType/tawSystemDictTypeList.jsp"
 * @struts.action-forward name="search" path="/tawSystemDictTypes.html"
 *                        redirect="true"
 */
public final class TawSystemDictTypeAction extends BaseAction {

	private static String SYSDICTTYPE_LIST = "SysDictTypeList";

	// AJAX方式进行搜索请求时的数据处理
	public ActionForward xsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'xsearch' method");
		}

		String dictid = request.getParameter("dictId");

		ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		List _objDictTypeList = new ArrayList();
		_objDictTypeList = mgr.getDictSonsByDictid(dictid);
		List itemList = new ArrayList();

		for (Iterator rowIt = _objDictTypeList.iterator(); rowIt.hasNext();) {
			TawSystemDictType dictType = (TawSystemDictType) rowIt.next();
			TawCommonsUIListItem uiitem = new TawCommonsUIListItem();
			uiitem.setItemId(String.valueOf(dictType.getId()));
			uiitem.setText(dictType.getDictName());
			uiitem.setValue(dictType.getDictId());
			itemList.add(uiitem);
		}

		response.setContentType("text/xml;charset=UTF-8");

		// 返回JSON对象
		response.getWriter().print(JSONUtil.list2JSON(itemList));
		return null;
	}

	/**
	 * 根据父节点的id得到所有子节点的JSON数据 mios 070723
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");
		ArrayList list = new ArrayList();

		ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		list = mgr.getDictSonsByDictid(nodeId);

		JSONArray jsonRoot = new JSONArray();

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawSystemDictType _objDictType = (TawSystemDictType) rowIt.next();

			JSONObject jitem = new JSONObject();
			jitem.put("id", _objDictType.getDictId());
			jitem.put("dictId", _objDictType.getDictId());
			jitem.put("parentDictId", _objDictType.getParentDictId());
			jitem.put("text", _objDictType.getDictName());
			jitem.put("allowChild", "true");
			jitem.put("allowDelete", "true");
			jitem.put("qtip", "代码:" + _objDictType.getDictId() + "<br \\/>取值:"
					+ _objDictType.getDictCode() + "<br \\/>备注:"
					+ _objDictType.getDictRemark());
			jitem.put("qtipTitle", _objDictType.getDictName());
			jitem.put("leaf", _objDictType.getLeaf());
			jsonRoot.put(jitem);
		}

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSystemDictTypeForm tawSystemDictTypeForm = (TawSystemDictTypeForm) form;
		boolean isNew = ("".equals(tawSystemDictTypeForm.getId()) || tawSystemDictTypeForm
				.getId() == null);

		ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		TawSystemDictType tawSystemDictType = (TawSystemDictType) convert(tawSystemDictTypeForm);

		String paredictid = tawSystemDictType.getParentDictId();
		if (isNew) {

			if (paredictid == null || paredictid.equals("")
					|| paredictid.equals("-1")) {
				String firstdictid = mgr.getMaxDictid("1");
				tawSystemDictType.setParentDictId("-1");
				tawSystemDictType.setLeaf(Integer.valueOf(StaticVariable.LEAF));
				tawSystemDictType.setDictId(firstdictid);
				tawSystemDictType.setSysType(new Integer(0));
				tawSystemDictType.setDictCode(firstdictid);
			} else {
				String newdictid = mgr.getMaxDictid(paredictid);
				TawSystemDictType dictType = new TawSystemDictType();
				dictType = mgr.getDictTypeByDictid(paredictid);
				if (tawSystemDictType.getDictId() == null
						|| tawSystemDictType.getDictId().equals("")) {
					tawSystemDictType.setDictId(newdictid);
				}

				// 如果不填dictCode就赋dictId的值 edit by liqiuye 20080902
				if (null == tawSystemDictType.getDictCode()
						|| "".equals(tawSystemDictType.getDictCode())) {
					tawSystemDictType
							.setDictCode(tawSystemDictType.getDictId());
				} else {
					// 判断用户填写的code是否存在
					if (mgr.isCodeExist(tawSystemDictType.getDictCode(),
							tawSystemDictType.getDictId())) {
						JSONUtil
								.fail(response, "字典类型值为【"
										+ tawSystemDictType.getDictCode()
										+ "】的字典项已经存在");
						return;
					}
				}

				dictType.setLeaf(Integer.valueOf(StaticVariable.NOTLEAF));
				tawSystemDictType.setSysType(new Integer(dictType.getSysType()
						.intValue() + 1));
				tawSystemDictType.setLeaf(Integer.valueOf(StaticVariable.LEAF));

				mgr.saveTawSystemDictType(dictType);
			}
			//将该字典放到缓存中
			TawWorkplanCacheBean tawWorkplanCacheBean = (TawWorkplanCacheBean) ApplicationContextHolder
			.getInstance().getBean("TawWorkplanCacheBean");
			Map workplanMap = tawWorkplanCacheBean.getWorkplanUser(); 
			Map dictMap = (Map)workplanMap.get("dictMap");
			dictMap.put(tawSystemDictType.getDictId(),tawSystemDictType.getDictName());
			mgr.saveTawSystemDictType(tawSystemDictType);
			// response.setContentType("text/xml;charset=UTF-8");
			// response.getWriter().print("success");
			request.setAttribute("lastNewId", paredictid);
		} else {
			// 保存编辑的部门id，使转向后可刷新树图上的相应节点
			request.setAttribute("lastEditId", tawSystemDictType.getDictId());
		}
	}

	/**
	 * ajax请求获取某节点的详细信息。 mios 070724
	 */
	public String xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("id");
		ITawSystemDictTypeManager _objDictTypeMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		TawSystemDictType _objOne = (TawSystemDictType) _objDictTypeMgr
				.getDictTypeByDictid(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(_objOne);

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * ajax请求修改某节点的详细信息。 mios 070724
	 */
	public ActionForward xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemDictTypeForm _objDictTypeFrm = (TawSystemDictTypeForm) form;

		ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");

		if (_objDictTypeFrm.getId() != null) {
			TawSystemDictType tawSystemDictType = (TawSystemDictType) convert(_objDictTypeFrm);
			// 如果不填dictCode就赋dictId的值 edit by liqiuye 20080902
			if (null == tawSystemDictType.getDictCode()
					|| "".equals(tawSystemDictType.getDictCode())) {
				tawSystemDictType.setDictCode(tawSystemDictType.getDictId());
			} else {
				// 判断用户填写的code是否存在
				if (mgr.isCodeExist(tawSystemDictType.getDictCode(),
						tawSystemDictType.getDictId())) {
					JSONUtil.fail(response, "字典类型值为【"
							+ tawSystemDictType.getDictCode() + "】的字典项已经存在");
					return null;
				}
			}
			//将修改过的字典放到缓存中
			TawWorkplanCacheBean tawWorkplanCacheBean = (TawWorkplanCacheBean) ApplicationContextHolder
			.getInstance().getBean("TawWorkplanCacheBean");
			Map workplanMap = tawWorkplanCacheBean.getWorkplanUser(); 
			Map dictMap = (Map)workplanMap.get("dictMap");
			dictMap.put(tawSystemDictType.getDictId(),tawSystemDictType.getDictName());
			mgr.saveTawSystemDictType(tawSystemDictType);
		}

		return null;
	}

	/**
	 * 根据模块或功能的编码，删除该对象。
	 * 页面访问：http://hostname:port/webappname/tawSystemDictTypes.html?method=xdelete&id=inputvalue
	 */
	public String xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("id");
		TawSystemDictTypeForm dictform = (TawSystemDictTypeForm) form;
		ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		TawSystemDictType dicttype = new TawSystemDictType();
		dicttype = mgr.getDictTypeByDictid(_strId);
		String parentdictid = dicttype.getParentDictId();
		//将该字典在缓存中删除
		TawWorkplanCacheBean tawWorkplanCacheBean = (TawWorkplanCacheBean) ApplicationContextHolder
		.getInstance().getBean("TawWorkplanCacheBean");
		Map workplanMap = tawWorkplanCacheBean.getWorkplanUser(); 
		Map dictMap = (Map)workplanMap.get("dictMap");
		dictMap.remove(_strId);
		mgr.removeDictByDictid(_strId);
		boolean flag = mgr.isHaveSameLevel(parentdictid, String
				.valueOf(dicttype.getSysType()));
		if (!flag) {
			mgr.updateParentDictLeaf(parentdictid, StaticVariable.STRLEAF);
		}
		return null;
	}

}
