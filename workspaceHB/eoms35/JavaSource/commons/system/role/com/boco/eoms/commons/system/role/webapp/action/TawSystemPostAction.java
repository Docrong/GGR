
package com.boco.eoms.commons.system.role.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;

import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.role.model.TawSystemPost;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemPostManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleRefPostManager;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemPostForm;
import com.boco.eoms.commons.ui.util.UIConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Action class to handle CRUD on a TawSystemPost object
 *
 * @struts.action name="tawSystemPostForm" path="/tawSystemPosts" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawSystemPostForm" path="/editTawSystemPost" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="tawSystemPostForm" path="/saveTawSystemPost" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/tawSystemPost/tawSystemPostForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/tawSystemPost/tawSystemPostList.jsp"
 * @struts.action-forward name="search" path="/tawSystemPosts.html" redirect="true"
 */
public final class TawSystemPostAction extends BaseAction {
	
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        return mapping.findForward("search");
    }
    
    public ActionForward addRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws Exception {
    	
    	TawSystemPostForm tawSystemPostForm = (TawSystemPostForm) form;
    	request.setAttribute("postId", new Long(tawSystemPostForm.getPostId()));

        long postId = tawSystemPostForm.getPostId();

        ActionForward forward = mapping.findForward("addRole");
		String path = forward.getPath() + "?method=addNew&postId=" + postId;// 加参数
		return new ActionForward(path, false);
    	
    }
    
    public ActionForward addNew(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws Exception {
    	
    	TawSystemPostForm tawSystemPostForm = (TawSystemPostForm) form;
    	request.setAttribute("postId", new Long(tawSystemPostForm.getPostId()));

        try {

//        	TawSystemPost tawSystemPost = (TawSystemPost) convert(tawSystemPostForm);

//          String levelList = orgLevelDAO.getLevelListString();
//          String posttypeList = orgPosttypeDAO.getPostypeListString();
//
//          request.setAttribute("LEVELLIST", levelList);
//          request.setAttribute("POSTTYPELIST", posttypeList);

          long parentId = tawSystemPostForm.getPostId();
//          tawSystemPostForm.setParentId(parentId);
//          OrgPostBO orgPostBO = new OrgPostBO();
          
//         ITawSystemPostManager mgr = (ITawSystemPostManager) getBean("ItawSystemPostManager");
          tawSystemPostForm.setParentId(parentId);

        } catch (Exception e) {
          return mapping.findForward("failure");
        }
        return mapping.findForward("add");
    	
    }
    

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {

        ActionMessages messages = new ActionMessages();
        TawSystemPostForm tawSystemPostForm = (TawSystemPostForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSystemPostManager mgr = (ITawSystemPostManager) getBean("ItawSystemPostManager");
        mgr.removeTawSystemPost(tawSystemPostForm.getPostId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("tawSystemPost.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);
        
        request.setAttribute("lastEditId", new Long(tawSystemPostForm.getPostId()));

        return mapping.findForward("search");
    }
    
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("id");
		ITawSystemPostManager mgr = (ITawSystemPostManager) getBean("ItawSystemPostManager");
		mgr.removeTawSystemPost(Long.parseLong(_strId));
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        TawSystemPostForm tawSystemPostForm = (TawSystemPostForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        long id = tawSystemPostForm.getPostId();
        if (id>0) {
//            updateFormBean(mapping, request, tawSystemPostForm);
            ITawSystemPostManager mgr = (ITawSystemPostManager) getBean("ItawSystemPostManager");
            TawSystemPost tawSystemPost = (TawSystemPost) convert(tawSystemPostForm);
            
            String subRoleIds = tawSystemPostForm.getSubroleids();
            System.out.println("subRoleIds="+subRoleIds);
            mgr.saveTawSystemPost(tawSystemPost,subRoleIds.split(","));
            
//            ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo) ApplicationContextHolder
//			.getInstance().getBean("iTawSystemUserRefRoleBo");
//			List tawRmUsers = refrolebo.getUsersbyroleids(String.valueOf(tawSystemPostForm.getPostId()));
//			
//			StringBuffer userIdArray = new StringBuffer();
//	        StringBuffer userNameArray = new StringBuffer();
//	        if (tawRmUsers.iterator().hasNext()) {
//	          for (Iterator it = tawRmUsers.iterator(); it.hasNext();) {
//	        	  TawSystemUser user = (TawSystemUser) it.next();
//	            userIdArray.append(user.getUserid()).append(",");
//	            userNameArray.append(user.getUsername()).append(",");
//	          }
//	          userIdArray.deleteCharAt(userIdArray.lastIndexOf(","));
//	          userNameArray.deleteCharAt(userNameArray.lastIndexOf(","));
//	        }


//	        request.setAttribute("userIdArray", userIdArray.toString());
//	        request.setAttribute("userNameArray", userNameArray.toString());
//	        request.setAttribute("workflowFlag",String.valueOf(tawSystemPostForm.getWorkflowFlag()));
        }
        else
        	throw new Exception("id=0");

        return null;
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSystemPostForm tawSystemPostForm = (TawSystemPostForm) form;
        boolean isNew = (tawSystemPostForm.getPostId()==0);

        ITawSystemPostManager mgr = (ITawSystemPostManager) getBean("ItawSystemPostManager");
        TawSystemPost tawSystemPost = (TawSystemPost) convert(tawSystemPostForm);

//        String userIds = tawSystemPostForm.getUserids();
        
        String subRoleIds = tawSystemPostForm.getSubroleids();
        System.out.println("subRoleIds="+subRoleIds);
        mgr.saveTawSystemPost(tawSystemPost,subRoleIds.split(","));

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemPost.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            request.setAttribute("lastNewId", new Long(tawSystemPostForm.getPostId()));
//            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemPost.updated"));
            saveMessages(request, messages);

            request.setAttribute("lastEditId", new Long(tawSystemPostForm.getPostId()));
//            return mapping.findForward("search");
        }
        return null;
    }
//    
//    public ActionForward saveNew(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request,
//            HttpServletResponse response)
//	throws Exception {
//	
//		// Extract attributes and parameters we will need
//		ActionMessages messages = new ActionMessages();
//		TawSystemPostForm tawSystemPostForm = (TawSystemPostForm) form;
//		boolean isNew = (tawSystemPostForm.getPostId()==0);
//		
//		ITawSystemPostManager mgr = (ITawSystemPostManager) getBean("ItawSystemPostManager");
//		TawSystemPost tawSystemPost = (TawSystemPost) convert(tawSystemPostForm);
//		tawSystemPost.setDeleted(new Integer(0));
//		tawSystemPost.setSingleId(StaticMethod.getCurrentDateTime());
//		tawSystemPost.setLeaf(new Integer(1));
//		tawSystemPost.setStructureFlag(mgr.getNewStructureFlag(tawSystemPost.getParentId()));
//		mgr.saveTawSystemPost(tawSystemPost);
//		
//		TawSystemDeptRefPost tawSystemDeptRefPost = new TawSystemDeptRefPost();
//		tawSystemDeptRefPost.setDeptId(tawSystemPostForm.getDeptId());
//		tawSystemDeptRefPost.setPostId(tawSystemPost.getPostId());
//		ITawSystemDeptRefPostManager mgrRef = (ITawSystemDeptRefPostManager) getBean("ItawSystemDeptRefPostManager");
//		mgrRef.saveTawSystemDeptRefPost(tawSystemDeptRefPost);
//		
//		mgr.setLeaf(tawSystemPost.getParentId(), new Integer(0));
//		
//		// add success messages
//		if (isNew) {
//			messages.add(ActionMessages.GLOBAL_MESSAGE,
//			       new ActionMessage("tawSystemPost.added"));
//			
//			// save messages in session to survive a redirect
//			saveMessages(request.getSession(), messages);
//			
//			request.setAttribute("lastNewId", new Long(tawSystemPostForm.getPostId()));
//		} else {
//			messages.add(ActionMessages.GLOBAL_MESSAGE,
//			       new ActionMessage("tawSystemPost.updated"));
//			saveMessages(request, messages);
//			
//			request.setAttribute("lastEditId", new Long(tawSystemPostForm.getPostId()));
//		}
//		return mapping.findForward("success");
//	}

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
//        String pageIndexName = new org.displaytag.util.ParamEncoder("tawDemoMytableList").encodeParameterName(
//    			org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);   // ҳ��Ĳ�����,����"tawDemoMytableList"��ҳ����displayTag��id
//	    	final Integer pageSize = 25;   //ÿҳ��ʾ������
//	    	final Integer pageIndex = GenericValidator.isBlankOrNull(request.getParameter(pageIndexName))?0:(Integer.parseInt(request.getParameter(pageIndexName)) - 1);  //��ǰҳ��
//
//        ITawSystemPostManager mgr = (ITawSystemPostManager) getBean("ItawSystemPostManager");
//        Map map = (Map)mgr.getTawSystemPosts(pageIndex,pageSize);	//map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
//        List list = (List)map.get("result");
//        request.setAttribute(Constants.TAWSYSTEMPOST_LIST, list);
//        request.setAttribute("resultSize", map.get("total"));
//
//        return mapping.findForward("list");
        
        
//        TawSystemPostForm actionform = (TawSystemPostForm) form;
//        try {
//          int postId = StaticMethod.null2int(request.getParameter("dept"));
//          ITawSystemPostManager mgr = (ITawSystemPostManager) getBean("ItawSystemPostManager");
//          TawSystemPost orgPost = mgr.getTawSystemPost(postId, StaticVariable.UNDELETED);
//          if (orgPost == null) {
//        	  actionform.setStrutsAction(4);
//          }
//        }
//        catch (Exception e) {
//            return mapping.findForward("failure");
//	      }
//	      return mapping.findForward("list");
    	

		return mapping.findForward("list");
    }
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }
    
	/**
	 * 根据父节点的id得到所有子节点的JSON数据
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
		String node = request.getParameter("node");
		String chkType = StaticMethod.null2String(request.getParameter("nodeType"));
		JSONArray json = new JSONArray();
		try {

			if ("".equals(chkType) || "dept".equals(chkType)) {
				ArrayList list = (ArrayList) deptbo.getNextLevecDepts(node,
						"0");

				for (int i = 0; i < list.size(); i++) {
					TawSystemDept subDept = (TawSystemDept) list.get(i);

					String subDeptID = subDept.getDeptId();
					String subDeptName = subDept.getDeptName();

					JSONObject jitem = new JSONObject();
					jitem.put("id", subDeptID);
					jitem.put("text", subDeptName);
					jitem.put(UIConstants.JSON_NODETYPE, "dept");
					jitem.put("allowChild", true);
					jitem.put("allowDelete", false);
					jitem.put("allowEdit", false);

					json.put(jitem);

				}

				ITawSystemPostManager mgr = (ITawSystemPostManager) ApplicationContextHolder
						.getInstance().getBean("ItawSystemPostManager");
				List postlist = mgr.getPostsByDeptId(node);
				if (postlist.size() > 0) {
					for (int j = 0; j < postlist.size(); j++) {
						TawSystemPost syspost = (TawSystemPost) postlist.get(j);

						JSONObject jitem = new JSONObject();
						jitem.put("id", syspost.getPostId());
						jitem.put("text", syspost.getPostName());
						jitem.put(UIConstants.JSON_NODETYPE, "post");
						jitem.put("iconCls", "post");
						jitem.put("leaf", 0);
						jitem.put("allowChild", false);
						jitem.put("allowDelete", true);

						json.put(jitem);
					}
				}
			} else if ("post".equals(chkType)) {
				ITawSystemSubRoleRefPostManager mgr = (ITawSystemSubRoleRefPostManager) ApplicationContextHolder
						.getInstance().getBean(
								"ItawSystemSubRoleRefPostManager");
				List subRoleList = mgr.getSubRoleByPostId(new Long(node)
						.longValue());
				for (int j = 0; j < subRoleList.size(); j++) {
					TawSystemSubRole subRole = (TawSystemSubRole) subRoleList
							.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", subRole.getId());
					jitem.put("text", subRole.getSubRoleName());
					jitem.put(UIConstants.JSON_NODETYPE, "subrole");
					jitem.put("iconCls", "role");
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowDelete", false);
					jitem.put("allowEdit", false);

					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成岗位树图时报错：" + ex);
			ex.printStackTrace();
		}
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(json.toString());
		return null;
	}
    
	/**
	 * ajax请求获取某节点的详细信息。
	 */
	public String xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = StaticMethod.nullObject2String(request.getParameter("id"));
		if("".equals(_strId))
			return null;
		
		ITawSystemPostManager mgrpost = (ITawSystemPostManager) getBean("ItawSystemPostManager");
		TawSystemPost post = mgrpost.getTawSystemPost(new Long(_strId).longValue());

		ITawSystemSubRoleRefPostManager mgr = (ITawSystemSubRoleRefPostManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemSubRoleRefPostManager");
		List subRoleList = mgr.getSubRoleByPostId(new Long(_strId).longValue());

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(post);
		
		JSONArray jsonList = new JSONArray();
        for (int i = 0; i < subRoleList.size(); i++) {
			TawSystemSubRole subrole = (TawSystemSubRole) subRoleList.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("id", subrole.getId());
			jitem.put("name", subrole.getSubRoleName());		
			jsonList.put(jitem);
		}
        jsonRoot.put("subRoles",jsonList);
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());	
		
		return null;
	}
	
    public ActionForward postView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws Exception {
    	String deptId = StaticMethod.nullObject2String(request.getParameter("deptId"));
    	System.out.println("deptid="+deptId);

    	List list = null;
        try {
        	if(!"".equals(deptId)){
	          ITawSystemPostManager mgr = (ITawSystemPostManager) getBean("ItawSystemPostManager");
	          list = mgr.getPostView(deptId.split(","));
	          request.setAttribute("total",new Integer(list.size()));
        	}
        	else
        		request.setAttribute("total",new Integer(0));
        	request.setAttribute("postList",list);     
        	request.setAttribute("pageSize",new Integer(20));

        } catch (Exception e) {
        	e.printStackTrace();
        }
        return mapping.findForward("view");
    	
    }
}
