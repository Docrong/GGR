package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.listitem.TawCommonsUIListItem;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiDictManager;

public final class TawSupplierkpiDictAction extends BaseAction {

	// AJAX方式进行搜索请求时的数据处理
	public ActionForward xsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String dictid = request.getParameter("dictId");

		ITawSupplierkpiDictManager mgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		List _objDictTypeList = new ArrayList();
		_objDictTypeList = mgr.getDictSonsByDictId(dictid);
		List itemList = new ArrayList();

		for (Iterator rowIt = _objDictTypeList.iterator(); rowIt.hasNext();) {
			TawSupplierkpiDict dictType = (TawSupplierkpiDict) rowIt.next();
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
}
