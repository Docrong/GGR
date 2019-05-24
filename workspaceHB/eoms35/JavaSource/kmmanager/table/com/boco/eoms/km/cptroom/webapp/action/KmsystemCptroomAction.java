
package com.boco.eoms.km.cptroom.webapp.action;

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

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.cptroom.bo.KmsystemCptroomBo;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;
import com.boco.eoms.km.cptroom.service.IKmsystemCptroomManager;
import com.boco.eoms.km.cptroom.webapp.form.KmsystemCptroomForm;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.commons.ui.util.UIConstants;


public final class KmsystemCptroomAction extends BaseAction {
	KmsystemCptroomBo cptroombo = KmsystemCptroomBo.getInstance();
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
        KmsystemCptroomForm kmsystemCptroomForm = (KmsystemCptroomForm) form;

        // Exceptions are caught by ActionExceptionHandler
        IKmsystemCptroomManager mgr = (IKmsystemCptroomManager) getBean("IkmsystemCptroomManager");
        mgr.removeKmsystemCptroom(kmsystemCptroomForm.getId());

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
        KmsystemCptroomForm kmsystemCptroomForm = (KmsystemCptroomForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (kmsystemCptroomForm.getId() != null) {
            IKmsystemCptroomManager mgr = (IKmsystemCptroomManager) getBean("IkmsystemCptroomManager");
            KmsystemCptroom kmsystemCptroom = mgr.getKmsystemCptroom(kmsystemCptroomForm.getId());
            kmsystemCptroom = (KmsystemCptroom) convert(kmsystemCptroomForm);
            kmsystemCptroom.setManager((kmsystemCptroomForm.getManager()));
            kmsystemCptroom.setTempmanager(((kmsystemCptroomForm.getTempmanager())));
            kmsystemCptroom.setDeptid(((kmsystemCptroomForm.getDeptid())));
            mgr.saveKmsystemCptroom(kmsystemCptroom);
            updateFormBean(mapping, request, kmsystemCptroomForm);
        }

        return null;
    }

    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        KmsystemCptroomForm kmsystemCptroomForm = (KmsystemCptroomForm) form;
        boolean isNew = ("".equals(kmsystemCptroomForm.getId()) || kmsystemCptroomForm.getId() == null);

        IKmsystemCptroomManager mgr = (IKmsystemCptroomManager) getBean("IkmsystemCptroomManager");
        KmsystemCptroom kmsystemCptroom = (KmsystemCptroom) convert(kmsystemCptroomForm);
        String manager = kmsystemCptroomForm.getManager();
        String tempmanager = kmsystemCptroomForm.getTempmanager();
    	KmsystemCptroom cptroom = new KmsystemCptroom();
        String deptid = kmsystemCptroom.getDeptid();
        String parentid = kmsystemCptroom.getParentid();
        if(parentid == null || parentid.equals("")
				|| parentid.equals("-1")) {
        	kmsystemCptroom.setDeleted(new Integer(0));
        	kmsystemCptroom.setLeaf("1");
        } else {
        	cptroom = cptroombo.getKmsystemCptroomById(new Integer(parentid), 0);        	
        	kmsystemCptroom.setLeaf("1");
        	cptroom.setLeaf("0");
        	cptroom.setManager((manager));
        	cptroom.setTempmanager(((tempmanager)));
        	cptroom.setDeptid(((deptid)));
        	mgr.saveKmsystemCptroom(cptroom);
        }
        kmsystemCptroom.setDeleted(new Integer(0));
        kmsystemCptroom.setManager((manager));
        kmsystemCptroom.setTempmanager(((tempmanager)));
        kmsystemCptroom.setDeptid(((deptid)));
        
        mgr.saveKmsystemCptroom(kmsystemCptroom);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("kmsystemCptroom.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);
            request.setAttribute("lastNewId", deptid);
            return null;
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("kmsystemCptroom.updated"));
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
		IKmsystemCptroomManager mgr = (IKmsystemCptroomManager) getBean("IkmsystemCptroomManager");
		ITawSystemDeptManager mgrdept = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
		ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		
		KmsystemCptroom _objOneOpt = new KmsystemCptroom();
		TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
		 
		_objOneOpt = mgr.getKmsystemCptroomById(new Integer(_strId), StaticVariable.UNDELETED);
		KmsystemCptroomForm kmsystemdeptForm = (KmsystemCptroomForm) convert(_objOneOpt);
		if( !(StaticMethod.null2String(_objOneOpt.getManager()).equals("null") || _objOneOpt.getManager().equals("")) ){
			String managername = _objOneOpt.getManager();
			String tempmanagername = _objOneOpt.getTempmanager();
			tawRmUserManager = userbo.getUserByuserid(managername);
			tawRmUserTempManager =  userbo.getUserByuserid(tempmanagername);
			kmsystemdeptForm.setManager(managername);
			   
			kmsystemdeptForm.setTempmanager(tempmanagername);
			kmsystemdeptForm.setManagerName(tawRmUserManager.getUsername());
			kmsystemdeptForm.setTempmanagerName(tawRmUserTempManager.getUsername());
			//kmsystemdeptForm.setDeptName(mgrdept.id2Name(_objOneOpt.getDeptid()));
			String specialName="";
			if(((TawSystemDictType)dictMgr.getDictTypeByDictid(_objOneOpt.getDeptid()))!=null){
		    specialName = ((TawSystemDictType)dictMgr.getDictTypeByDictid(_objOneOpt.getDeptid())).getDictName();
			}
			kmsystemdeptForm.setDeptName(StaticMethod.null2String(specialName));
		}
		
		  
		
		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(kmsystemdeptForm);

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
		JSONArray json = new JSONArray();
		String nodeId = request.getParameter("node");
		IKmsystemCptroomManager mgr = (IKmsystemCptroomManager) getBean("IkmsystemCptroomManager");
		List list = mgr.getNextLevelCptrooms(nodeId, Constants.NOT_DELETED_FLAG);
		
		try {
			String chkType="";

			for (int i = 0; i < list.size(); i++) {
				KmsystemCptroom subCptroom = (KmsystemCptroom) list.get(i);
				String subCptroomID = subCptroom.getId().toString();
				String subCptroomName = subCptroom.getRoomname();
				int subCptroomLeaf = new Integer(subCptroom.getLeaf())
						.intValue();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subCptroomID);
				jitem.put("text", subCptroomName);
				jitem.put(UIConstants.JSON_NODETYPE,
						UIConstants.NODETYPE_CPTROOM);
				jitem.put("leaf", subCptroomLeaf);
				//jitem.put("allowChild", true);
				jitem.put("allowDelete", true);

				if (chkType.indexOf(UIConstants.NODETYPE_CPTROOM) > -1) {
					jitem.put("checked", false);
				}
				jitem.put("qtip", "机房负责人:" + subCptroom.getManager()
						+ "<br \\/>机房电话:" + subCptroom.getPhone()
						+ "<br \\/>备注:" + subCptroom.getNotes());
				jitem.put("qtipTitle", subCptroom.getRoomname());
				json.put(jitem);
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成机房树图时报错：" + ex);
		}
		JSONArray jsonRoot = json;
		
		//TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
	//	JSONArray jsonRoot = treebo.getCptroomTree(nodeId,"");

		
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null; 
	}

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder("tawDemoMytableList").encodeParameterName(
    			org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);   
	    	final Integer pageSize = new Integer(25);  
	    	final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName))?0:(new Integer(request.getParameter(pageIndexName)).intValue() - 1)); 

        IKmsystemCptroomManager mgr = (IKmsystemCptroomManager) getBean("IkmsystemCptroomManager");
        Map map = (Map)mgr.getKmsystemCptrooms(pageIndex,pageSize);	
        List list = (List)map.get("result");
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
