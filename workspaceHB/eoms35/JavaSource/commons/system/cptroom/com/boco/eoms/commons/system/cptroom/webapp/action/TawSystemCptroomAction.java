
package com.boco.eoms.commons.system.cptroom.webapp.action;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.cptroom.webapp.form.TawSystemCptroomForm;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.commons.ui.util.UIConstants;

/**
 * Action class to handle CRUD on a TawSystemCptroom object
 *
 * @struts.action name="tawSystemCptroomForm" path="/tawSystemCptrooms" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawSystemCptroomForm" path="/editTawSystemCptroom" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="tawSystemCptroomForm" path="/saveTawSystemCptroom" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/tawSystemCptroom/tawSystemCptroomForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/tawSystemCptroom/tawSystemCptroomList.jsp"
 * @struts.action-forward name="search" path="/cptroom/tawSystemCptrooms.do" redirect="true"
 */
public final class TawSystemCptroomAction extends BaseAction {
	TawSystemCptroomBo cptroombo = TawSystemCptroomBo.getInstance();
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {

        ActionMessages messages = new ActionMessages();
        TawSystemCptroomForm tawSystemCptroomForm = (TawSystemCptroomForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");
        mgr.removeTawSystemCptroom(tawSystemCptroomForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("tawSystemCptroom.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward xedit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        TawSystemCptroomForm tawSystemCptroomForm = (TawSystemCptroomForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSystemCptroomForm.getId() != null) {
            ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");
            TawSystemCptroom tawSystemCptroom = mgr.getTawSystemCptroom(tawSystemCptroomForm.getId());
            tawSystemCptroom = (TawSystemCptroom) convert(tawSystemCptroomForm);
            tawSystemCptroom.setManager((tawSystemCptroomForm.getManager()));
            tawSystemCptroom.setTempmanager(((tawSystemCptroomForm.getTempmanager())));
            tawSystemCptroom.setDeptid(((tawSystemCptroomForm.getDeptid())));
            mgr.saveTawSystemCptroom(tawSystemCptroom);
            updateFormBean(mapping, request, tawSystemCptroomForm);
        }

        return null;
    }

    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSystemCptroomForm tawSystemCptroomForm = (TawSystemCptroomForm) form;
        boolean isNew = ("".equals(tawSystemCptroomForm.getId()) || tawSystemCptroomForm.getId() == null);

        ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");
        TawSystemCptroom tawSystemCptroom = (TawSystemCptroom) convert(tawSystemCptroomForm);
        String manager = tawSystemCptroomForm.getManager();
        String tempmanager = tawSystemCptroomForm.getTempmanager();
    	TawSystemCptroom cptroom = new TawSystemCptroom();
        String deptid = tawSystemCptroom.getDeptid();
        String parentid = tawSystemCptroom.getParentid();
        if(parentid == null || parentid.equals("")
				|| parentid.equals("-1")) {
        	tawSystemCptroom.setDeleted(new Integer(0));
        	tawSystemCptroom.setLeaf("1");
        } else {
        	cptroom = cptroombo.getTawSystemCptroomById(new Integer(parentid), 0);        	
        	tawSystemCptroom.setLeaf("1");
        	cptroom.setLeaf("0");
        	cptroom.setManager((manager));
        	cptroom.setTempmanager(((tempmanager)));
        	cptroom.setDeptid(((deptid)));
        	mgr.saveTawSystemCptroom(cptroom);
        }
        tawSystemCptroom.setDeleted(new Integer(0));
        tawSystemCptroom.setManager((manager));
        tawSystemCptroom.setTempmanager(((tempmanager)));
        tawSystemCptroom.setDeptid(((deptid)));
        
        mgr.saveTawSystemCptroom(tawSystemCptroom);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemCptroom.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);
            request.setAttribute("lastNewId", deptid);
            return null;
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemCptroom.updated"));
            saveMessages(request, messages);
            request.setAttribute("lastNewId", deptid);
            return null;
        }
    }
    /**
	 * ajax请求获取某节点的详细信息。 
	 */
	public String xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("id");
		TawSystemUser tawRmUserManager = null;
		TawSystemUser tawRmUserTempManager = null;
		ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");
		ITawSystemDeptManager mgrdept = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
		
		TawSystemCptroom _objOneOpt = new TawSystemCptroom();
		TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
		 
		_objOneOpt = mgr.getTawSystemCptroomById(new Integer(_strId), StaticVariable.UNDELETED);
		TawSystemCptroomForm tawSystemdeptForm = (TawSystemCptroomForm) convert(_objOneOpt);
		if( !(StaticMethod.null2String(_objOneOpt.getManager()).equals("null") || _objOneOpt.getManager().equals("")) ){
			String managername = _objOneOpt.getManager();
			String tempmanagername = _objOneOpt.getTempmanager();
			tawRmUserManager = userbo.getUserByuserid(managername);
			tawRmUserTempManager =  userbo.getUserByuserid(tempmanagername);
			tawSystemdeptForm.setManager(managername);
			   
			tawSystemdeptForm.setTempmanager(tempmanagername);
			tawSystemdeptForm.setManagerName(tawRmUserManager.getUsername());
			tawSystemdeptForm.setTempmanagerName(tawRmUserTempManager.getUsername());
			tawSystemdeptForm.setDeptName(mgrdept.id2Name(_objOneOpt.getDeptid()));
			
		}
		
		  
		
		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawSystemdeptForm);

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}
    
    /**
	 * 根据父节点的id得到所有子节点的JSON数据
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");
		
		TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
		JSONArray jsonRoot = treebo.getCptroomTree(nodeId,"");

		
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null; 
	}

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder("tawDemoMytableList").encodeParameterName(
    			org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);   // ҳ��Ĳ�����,����"tawDemoMytableList"��ҳ����displayTag��id
	    	final Integer pageSize = new Integer(25);   //ÿҳ��ʾ������
	    	final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName))?0:(new Integer(request.getParameter(pageIndexName)).intValue() - 1));  //��ǰҳ��

        ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");
        Map map = (Map)mgr.getTawSystemCptrooms(pageIndex,pageSize);	//map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
        List list = (List)map.get("result");
      //  request.setAttribute(Constants.TAWSYSTEMCPTROOM_LIST, list);
        request.setAttribute("resultSize", map.get("total"));

        return mapping.findForward("list");
    }
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }
    public String getDataFromJson(String json) {
		JSONArray jsonDept = JSONArray.fromString(json);
		String returnValue = "";
		for (Iterator it = jsonDept.iterator(); it.hasNext();) {
			JSONObject org = (JSONObject) it.next();
			// 部门id
			String id = org.getString(UIConstants.JSON_ID);

			returnValue = id;
			// 部门名称
			String name = org.getString(UIConstants.JSON_NAME);
			// 节点类型
			String nodeType = org.getString(UIConstants.JSON_NODETYPE);			
			
		}
		return returnValue;
	}
}
