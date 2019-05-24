
package com.boco.eoms.commons.workdayset.webapp.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.role.util.RoleXmlSchema;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemSubRoleForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.workdayset.model.TawWorkdaySet;
import com.boco.eoms.commons.workdayset.service.ITawWorkdaySetManager;
import com.boco.eoms.commons.workdayset.webapp.form.TawWorkdaySetForm;

/**
 * Action class to handle CRUD on a TawWorkdaySet object
 * @struts.action name="tawWorkdaySetForm" path="/tawWorkdaySets" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawWorkdaySet/tawWorkdaySetTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawWorkdaySet/tawWorkdaySetForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawWorkdaySet/tawWorkdaySetList.jsp" contextRelative="true"
 */
public final class TawWorkdaySetAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
         //return mapping.findForward("search");
           return null;
    }
     public ActionForward main(ActionMapping mapping, ActionForm form,
     	HttpServletRequest request,HttpServletResponse response)
    	throws Exception {
         return mapping.findForward("main");
    }
 	/**
	 * 根据父节点的id得到扄1�7有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITawWorkdaySetManager mgr = (ITawWorkdaySetManager) getBean("tawWorkdaySetManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

	
    /**
	 * ajax保存
	 */
    public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawWorkdaySetForm tawWorkdaySetForm = (TawWorkdaySetForm) form;

		ITawWorkdaySetManager mgr = (ITawWorkdaySetManager) getBean("tawWorkdaySetManager");
		TawWorkdaySet tawWorkdaySet = (TawWorkdaySet) convert(tawWorkdaySetForm);
		mgr.saveTawWorkdaySet(tawWorkdaySet);
		// JSONUtil.print(response, "操作成功");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawWorkdaySetForm tawWorkdaySetForm = (TawWorkdaySetForm) form;

        ITawWorkdaySetManager mgr = (ITawWorkdaySetManager) getBean("tawWorkdaySetManager");
		mgr.removeTawWorkdaySet(tawWorkdaySetForm.getId());

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawWorkdaySetForm tawWorkdaySetForm = (TawWorkdaySetForm) form;

		if (tawWorkdaySetForm.getId() != null) {
			ITawWorkdaySetManager mgr = (ITawWorkdaySetManager) getBean("tawWorkdaySetManager");
			TawWorkdaySet tawWorkdaySet = (TawWorkdaySet) convert(tawWorkdaySetForm);

			mgr.saveTawWorkdaySet(tawWorkdaySet);
		   //mgr.updateTawWorkdaySet(tawWorkdaySet);
		}

		return null;
	}

     /**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		ITawWorkdaySetManager mgr = (ITawWorkdaySetManager) getBean("tawWorkdaySetManager");
		TawWorkdaySet tawWorkdaySet = mgr.getTawWorkdaySet(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawWorkdaySet);

		JSONUtil.print(response, jsonRoot.toString());
	}
	 /**
	 * 判断是否是工作日
	 */
	public boolean isWorkday(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawWorkdaySetForm tawWorkdaySetForm = (TawWorkdaySetForm) form;
		ITawWorkdaySetManager mgr = (ITawWorkdaySetManager) getBean("tawWorkdaySetManager");
		return mgr.isWorkday(tawWorkdaySetForm.getWorkDate(),tawWorkdaySetForm.getAreaId());
		}
	 /**
	 * 返回userid对应信息给前台
	 */
	public ActionForward getInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawWorkdaySetManager mgr = (ITawWorkdaySetManager) getBean("tawWorkdaySetManager");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		
		String deptid = sessionform.getDeptid();
		String areaid = mgr.getAreaId(deptid);
		String areaname = mgr.getAreaname(areaid); //areaname
		List listInfo = mgr.getInfo(areaid);
		JSONObject jsonRoot = new JSONObject();
		if(!listInfo.isEmpty()) {
		String date = "";
		
		int config = 0;
		int n_config = 0;
		boolean overwrite = false;
		boolean n_overwrite = false;
		String startt = "";
		TawWorkdaySet tanull = new TawWorkdaySet();
		listInfo.add(tanull);
		
		TawWorkdaySet taa = (TawWorkdaySet)listInfo.get(0);
		String oldDate = taa.getWorkDate();
		String o_stime =StaticMethod.nullObject2String(taa.getStartTime());
		if(("0").equals(taa.getStatus()))
			config = 1;
		else if(o_stime != null && "".equals(o_stime)) 		 
			config = 2;
		else 
			config = 0;
		if("0".equals(taa.getCover())) {
			overwrite = false; 
		}			
		else {
			overwrite = true;
		}			
		JSONArray jsonTime = new JSONArray();
		for(int i = 0;i<listInfo.size();i++){
    		TawWorkdaySet ta = (TawWorkdaySet)listInfo.get(i);
    		
    		date = ta.getWorkDate();//date
    		ta.setStatus(StaticMethod.nullObject2String(ta.getStatus()));
    		if(("0").equals(ta.getStatus()))
    			n_config = 1;
    		else
    			n_config = 0;//config
    		ta.setCover(StaticMethod.nullObject2String(ta.getCover()));
    		if(("0").equals(ta.getCover()))
    			n_overwrite = false; //overwrite
    		else 
    			n_overwrite = true;
    		String stime =StaticMethod.nullObject2String(ta.getStartTime());
    		if(stime!=null&&!("").equals(stime)) 		 
    		{
    			startt = ta.getStartTime()+"-"+ta.getEndTime();
    			n_config = 2;
    		}
    		else stime = "";
    		date =StaticMethod.nullObject2String(date);
    		if(date.equals(oldDate))
    		{
    			StaticMethod.nullObject2String(startt);
    			if(startt!=null && !("").equals(startt))
    			jsonTime.put(startt);	
    		}
    		else
    		{
    			JSONObject jsonItem = new JSONObject();
    			
    			jsonItem.put("config", config);
        		jsonItem.put("overwrite", overwrite);
        		jsonItem.put("time", jsonTime); 
        		jsonRoot.put(oldDate, jsonItem);
        		
        		JSONArray n_jsonTime = new JSONArray();
        		jsonTime = n_jsonTime;
        		StaticMethod.nullObject2String(startt);
    			if(startt!=null&&!("").equals(startt))
    			jsonTime.put(startt);	
    			
        		oldDate = date;
        		config = n_config;
        		overwrite = n_overwrite;
    		}   	  
    	}
		request.setAttribute("data", jsonRoot.toString());
		request.setAttribute("areaid", areaid);
		request.setAttribute("areaname", areaname);
		return mapping.findForward("main");
		} else	{
			request.setAttribute("data", jsonRoot.toString());
			request.setAttribute("areaid", areaid);
			request.setAttribute("areaname", areaname);
			return mapping.findForward("main");
		}
	}
	/**
	 * 相应保存按钮
	 */
    public ActionForward x_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
   
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userid = sessionform.getUserid();
	//	TawWorkdaySetForm tawWorkdaySetForm = (TawWorkdaySetForm) form;
		
		ITawWorkdaySetManager mgr = (ITawWorkdaySetManager) getBean("tawWorkdaySetManager");
		
		String datesJSON = StaticMethod.null2String(request.getParameter("data"));
		JSONArray datesJSONList = new JSONArray();
	
		if(datesJSON!=null&&!"".equals(datesJSON))
		{
		datesJSONList = JSONArray.fromString(datesJSON);
		String workday = "";
		String select  = "";
		String cover = "";
		String strToday=new Date().toString();

		for (int i = 0; i < datesJSONList.length(); i++) {
			JSONObject jsonObj = (JSONObject) datesJSONList.get(i);
			if (jsonObj.has("date"))
				workday =jsonObj.getString("date").toString();
			if (jsonObj.has("config"))
				select =jsonObj.getString("config").toString();			
			if (jsonObj.has("overwrite"))
				cover =jsonObj.getString("overwrite").toString();
		String deptid = sessionform.getDeptid();
		String areaid = mgr.getAreaId(deptid);

		if(cover.equals("false"))
	  {
		if(select.equals("1"))
	    {
	    	if(mgr.isWorkday(workday,areaid))
	    	{
	    		mgr.removeTawWorkdaySets(workday,areaid);
	    	    if(mgr.isWorkday(workday,areaid))
	    	    {
	    	    	this.saveTawWorkdaySet(userid, areaid, strToday, workday, "", "", "0","0"); 	    	    
	    	    }
	    	} 	
	    }
		else if(select.equals("0"))
		{
			
				mgr.removeTawWorkdaySets(workday,areaid);
			
		}
		else{
			mgr.removeTawWorkdaySets(workday,areaid);
	    	String time ="";
			String []start = new String[3];
	    	
	    	String []end =  new String[3];
	    	if (jsonObj.has("time"))
	    		time =jsonObj.getString("time").toString();	
	    	time = time.substring(1, time.length()-1);
	    	
	    	String[] timeformat = time.split(",");
	    	for(int j=0;j<timeformat.length;j++)
	    	{
	    		timeformat[j] = timeformat[j].substring(1, timeformat[j].length()-1);
	    		start[j]=timeformat[j].substring(0, timeformat[j].indexOf("-"));
	    		end[j]=timeformat[j].substring(timeformat[j].indexOf("-")+1, timeformat[j].length());
	    		if(start[j]!=null&&!"".equals(start[j])&&end[j]!=null&&!"".equals(end[j])){
					this.saveTawWorkdaySet(userid, areaid, strToday, workday, start[j], end[j], "1","0"); 	    	 
				}
	    	}
		}
	  }
		
		else if(cover.equals("true"))
		  {
			if(select.equals("1"))
		    {
				if(mgr.isWorkday(workday,areaid))
		    	{
		    		mgr.removeTawWorkdaySets(workday,areaid);
		    		
		    	    if(mgr.isWorkday(workday,areaid))
		    	    {
		    	    	List list = new   ArrayList();;
		    	    	TawWorkdaySet aa = this.getTawWorkdaySet(userid, areaid, strToday, workday, "", "", "0", "1");
		    	    	list.add(aa);
	   	    	 	    if(areaid.equals("101"))
	   	    	 	    {
	   	    	 	    	mgr.saveAllTawWorkdaySet(list);
	   	    	 	    }
	   	    	 	    else
	   	    	 	    	mgr.saveCityTawWorkdaySet(list);    
		    	    }
		    	    else{
		    	    	List lt = mgr.getSubAreaid(areaid);
		    	    	
		    	    	for(int g=0;g<lt.size();g++){
		    	    		String str1 = lt.get(g).toString();
		    	    		mgr.removeTawWorkdaySets(workday,str1);
		    	    	}
		    	    }
		    	} 	
		    }
			else if(select.equals("0"))
			{
				
					mgr.removeTawWorkdaySets(workday,areaid);
					 if(areaid.equals("101"))
	   	    	 	    {
	   	    	 	    	mgr.removeTawWorkdaySets(workday);
	   	    	 	    }
	   	    	 	    else{
	   	    	 	    List lt = mgr.getSubAreaid(areaid);
		    	    	
		    	    	for(int g=0;g<lt.size();g++){
		    	    		String str1 = lt.get(g).toString();
		    	    		mgr.removeTawWorkdaySets(workday,str1); 
		    	    	}
		    	    }
				
			}
			else{
				mgr.removeTawWorkdaySets(workday,areaid);
				 if(areaid.equals("101"))
  	    	 	    {
  	    	 	    	mgr.removeTawWorkdaySets(workday);
  	    	 	    }
  	    	 	    else{
  	    	 	    List lt = mgr.getSubAreaid(areaid);
	    	    	
	    	    	for(int g=0;g<lt.size();g++){
	    	    		String str1 = lt.get(g).toString();
	    	    		mgr.removeTawWorkdaySets(workday,str1); 
	    	    	}
	    	    }
		    	String time ="";
				String []start = new String[3];
		    	
		    	String []end =  new String[3];
		    	if (jsonObj.has("time"))
		    		time =jsonObj.getString("time").toString();	
		    	time = time.substring(1, time.length()-1);
		    	
		    	String[] timeformat = time.split(",");
		    	List list2 = new   ArrayList();
		    	for(int j=0;j<timeformat.length;j++)
		    	{
		    		timeformat[j] = timeformat[j].substring(1, timeformat[j].length()-1);
		    		start[j]=timeformat[j].substring(0, timeformat[j].indexOf("-"));
		    		end[j]=timeformat[j].substring(timeformat[j].indexOf("-")+1, timeformat[j].length());
		    		
		    		if(start[j]!=null&&!"".equals(start[j])&&end[j]!=null&&!"".equals(end[j])){
						this.saveTawWorkdaySet(userid, areaid, strToday, workday, start[j], end[j], "1","1"); 
						
		    	    	list2.add(this.getTawWorkdaySet(userid, areaid, strToday, workday,start[j], end[j], "1","1"));
		    	    	
					}
		    	}
		    	if(areaid.equals("101"))
	    	 	    {
	    	 	    	mgr.saveAllTawWorkdaySet(list2);
	    	 	    }
	    	 	    else
	    	 	    	mgr.saveCityTawWorkdaySet(list2);    
    	    }
			}
		  }
		}
		return this.getInfo(mapping, form, request, response);
	}
		//TawWorkdaySet tawWorkdaySet = (TawWorkdaySet) convert(tawWorkdaySetForm);
		//mgr.saveTawWorkdaySet(tawWorkdaySet);
		// JSONUtil.print(response, "操作成功");

    public void saveTawWorkdaySet(final String userid,final String areaid,final String createtime,final String workday,final String starttime,final String endtime,final String status,final String cover){
    	TawWorkdaySet tawworkdaySet = new TawWorkdaySet();
    	if(userid!="")
    		tawworkdaySet.setUserId(userid);
    	if(areaid!="")
    		tawworkdaySet.setAreaId(areaid);
    	if(createtime!="")
    		tawworkdaySet.setCreateTime(createtime);
    	if(workday!="" )
    		tawworkdaySet.setWorkDate(workday);
    	if(starttime!="")
    		tawworkdaySet.setStartTime(starttime);
    	if(endtime!="" )
    		tawworkdaySet.setEndTime(endtime);		
    	if(status!="")
    		tawworkdaySet.setStatus(status);
    	if(cover!="" )
    		tawworkdaySet.setCover(cover);
    	
    	ITawWorkdaySetManager mgr = (ITawWorkdaySetManager) getBean("tawWorkdaySetManager");
    	mgr.saveTawWorkdaySet(tawworkdaySet);	
    }
    public TawWorkdaySet getTawWorkdaySet(final String userid,final String areaid,final String createtime,final String workday,final String starttime,final String endtime,final String status,final String cover){
    	TawWorkdaySet tawworkdaySet = new TawWorkdaySet();
    	if(userid!="")
    		tawworkdaySet.setUserId(userid);
    	if(areaid!="")
    		tawworkdaySet.setAreaId(areaid);
    	if(createtime!="")
    		tawworkdaySet.setCreateTime(createtime);
    	if(workday!="" )
    		tawworkdaySet.setWorkDate(workday);
    	if(starttime!="")
    		tawworkdaySet.setStartTime(starttime);
    	if(endtime!="")
    		tawworkdaySet.setEndTime(endtime);		
    	if(status!="")
    		tawworkdaySet.setStatus(status);
    	if(cover!="" )
    		tawworkdaySet.setCover(cover);
    return tawworkdaySet;
    }
}
