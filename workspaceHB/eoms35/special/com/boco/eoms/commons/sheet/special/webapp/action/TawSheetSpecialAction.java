
package com.boco.eoms.commons.sheet.special.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.sheet.special.model.TawSheetSpecial;
import com.boco.eoms.commons.sheet.special.service.ITawSheetSpecialManager;
import com.boco.eoms.commons.sheet.special.webapp.form.TawSheetSpecialForm;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.commons.ui.listitem.TawCommonsUIListItem;
import com.boco.eoms.commons.ui.util.JSONUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
/**
 * Action class to handle CRUD on a TawSheetSpecial object
 * 
 * @struts.action name="tawSheetSpecialForm" path="/tawSheetSpecials" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawSheetSpecialForm" path="/editTawSheetSpecial" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="tawSheetSpecialForm" path="/saveTawSheetSpecial" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/tawSheetSpecial/tawSheetSpecialForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/tawSheetSpecial/tawSheetSpecialList.jsp"
 * @struts.action-forward name="search" path="/tawSheetSpecials.do" redirect="true"
 */
public final class TawSheetSpecialAction extends BaseAction {
   

    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {

        ActionMessages messages = new ActionMessages();
    	String speid = request.getParameter("id");
    	

        // Exceptions are caught by ActionExceptionHandler
        ITawSheetSpecialManager mgr = (ITawSheetSpecialManager) getBean("ItawSheetSpecialManager");
        TawSheetSpecial sepcial = (TawSheetSpecial)mgr.getSpecialByspecialId(speid);
        List sameLevelspe = (ArrayList) mgr.getSameLevelspecial(sepcial.getParspeid(), new Integer(sepcial.getOrdercode()));
	
		if ((sameLevelspe == null || sameLevelspe.size() <= 1) && !sepcial.getParspeid().equals("-1")) {
			TawSheetSpecial parSpe = (TawSheetSpecial) mgr
					.getSpecialByspecialId(sepcial.getParspeid());
			parSpe.setLeaf(StaticVariable.AREA_DEFAULT_LEAFONE);
			mgr.saveTawSheetSpecial(parSpe,"");
		}
		if (sameLevelspe != null && sameLevelspe.size() > 0) {
			List sonSpeList = (ArrayList) mgr.getAllSonspecialByspecialid(speid);
			for ( int i=0;i<sonSpeList.size();i++) {
				TawSheetSpecial sonspecial = (TawSheetSpecial)sonSpeList.get(i);
				mgr.removeSpecialAndRefuser(sonspecial);
			}
			mgr.removeSpecialAndRefuser(sepcial);
		}else {
			 mgr.removeSpecialAndRefuser(sepcial);
		}
      messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("tawSheetSpecial.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return null;
    }

    public ActionForward xedit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        TawSheetSpecialForm tawSheetSpecialForm = (TawSheetSpecialForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSheetSpecialForm.getId() != null) {
            ITawSheetSpecialManager mgr = (ITawSheetSpecialManager) getBean("ItawSheetSpecialManager");
           
            TawSheetSpecial tawSheetSpecial = (TawSheetSpecial) convert(tawSheetSpecialForm);
            mgr.saveTawSheetSpecial(tawSheetSpecial,tawSheetSpecialForm.getNewuserid());
            updateFormBean(mapping, request, tawSheetSpecialForm);
        }

        return null;
    }

    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSheetSpecialForm tawSheetSpecialForm = (TawSheetSpecialForm) form;
        boolean isNew = ("".equals(tawSheetSpecialForm.getId()) || tawSheetSpecialForm.getId() == null);

        ITawSheetSpecialManager mgr = (ITawSheetSpecialManager) getBean("ItawSheetSpecialManager");
        TawSheetSpecial tawSheetSpecial = (TawSheetSpecial) convert(tawSheetSpecialForm);
        String parSpeid = tawSheetSpecial.getParspeid();
        if (parSpeid == null
				|| parSpeid.equals(StaticVariable.AREA_DEFAULT_STRVAL)
				|| parSpeid.equals(StaticVariable.AREA_DEFAULT_PARENTVAL)) {
			tawSheetSpecial
					.setParspeid(StaticVariable.AREA_DEFAULT_PARENTVAL);
			tawSheetSpecial.setLeaf(StaticVariable.AREA_DEFAULT_LEAFONE);
			tawSheetSpecial
					.setOrdercode(StaticVariable.AREA_DEFAULT_LEAFONE);
			tawSheetSpecial.setSpeid(mgr
					.getMaxSpecialId(StaticVariable.AREA_DEFAULT_STRONE));
		} else {
			String newspeid = mgr.getMaxSpecialId(parSpeid);
			TawSheetSpecial parSpe = new TawSheetSpecial();
			parSpe = mgr.getSpecialByspecialId(parSpeid);
			if (tawSheetSpecial.getSpeid() == null
					|| tawSheetSpecial.getSpeid().equals("")) {
				tawSheetSpecial.setSpeid(newspeid);
			}
			
			tawSheetSpecial.setLeaf(StaticVariable.AREA_DEFAULT_LEAFONE);
			tawSheetSpecial.setOrdercode(String.valueOf(new Integer(parSpe.getOrdercode()).intValue()
					+ StaticVariable.AREA_DEFAULT_ORDERCODEVAL.intValue()));
			//TawSheetSpecial parConspe = (TawSheetSpecial)mgr.getSpecialByspecialId(parSpeid);
			parSpe.setLeaf(StaticVariable.AREA_DEFAULT_LEAFZERO);
            mgr.saveTawSheetSpecial(parSpe,null);
		}
        
        
        mgr.saveTawSheetSpecial(tawSheetSpecial,tawSheetSpecialForm.getNewuserid());

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSheetSpecial.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);
            request.setAttribute("lastNewId", parSpeid);
            return null;
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSheetSpecial.updated"));
            saveMessages(request, messages);
            request.setAttribute("lastEditId", tawSheetSpecial.getSpeid());
            return null;
        }
    }

   
	public ActionForward getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String nodeId = request.getParameter("node");
		TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
		JSONArray jsonRoot = treebo.getSpecialTree(nodeId, "");

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}
	public ActionForward xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String speid = request.getParameter("id");
		   ITawSheetSpecialManager mgr = (ITawSheetSpecialManager) getBean("ItawSheetSpecialManager");
		   TawSheetSpecial _objOneOpt = (TawSheetSpecial)mgr.getSpecialByspecialId(speid);
		   String type = request.getParameter("nodeType");
		   
		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(_objOneOpt);

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}
//	 AJAX方式进行搜索请求时的数据处理
	public ActionForward xsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'xsearch' method");
		}

		String dictid = request.getParameter("dictId");

		//ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		ITawSheetSpecialManager mgr = (ITawSheetSpecialManager) getBean("ItawSheetSpecialManager");
		List _objDictTypeList = new ArrayList();
		_objDictTypeList = mgr.getSonspecialByspecialId(dictid);
		//List list = _objSpecialManager.getSonspecialByspecialId(initDicId);
		List itemList = new ArrayList();
		
		for (Iterator rowIt = _objDictTypeList.iterator(); rowIt.hasNext();) {
			TawSheetSpecial specialObj = (TawSheetSpecial) rowIt.next();
			TawCommonsUIListItem uiitem = new TawCommonsUIListItem();
			uiitem.setItemId(String.valueOf(specialObj.getId()));
			uiitem.setText(specialObj.getSpecialname());
			uiitem.setValue(specialObj.getSpeid());
			itemList.add(uiitem);
		}

		response.setContentType("text/xml;charset=UTF-8");

		// 返回JSON对象
		response.getWriter().print(JSONUtil.list2JSON(itemList));
		return null;
	}
}
