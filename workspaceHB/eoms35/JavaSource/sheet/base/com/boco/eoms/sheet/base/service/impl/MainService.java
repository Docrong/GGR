package com.boco.eoms.sheet.base.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.boco.eoms.sheet.base.dao.hibernate.EomsSheetHibernateSupport;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.qo.IWorkSheetQO;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.QuerySqlInit;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetKeyInit;
/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-18 16:36:31
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public abstract class MainService extends BaseManager implements IMainService {

	private IMainDAO mainDAO;

	private BaseMain mainObject;

	private IWorkSheetQO workSheetQO;
	
	private String flowTemplateName;
	
	private String roleConfigPath;
	
	public String getRoleConfigPath() {
		return roleConfigPath;
	}

	public void setRoleConfigPath(String roleConfigPath) {
		this.roleConfigPath = roleConfigPath;
	}

	/**
	 * @return Returns the mainDAO.
	 */
	public IMainDAO getMainDAO() {
		return mainDAO;
	}

	/**
	 * @param mainDAO
	 *            The mainDAO to set.
	 */
	public void setMainDAO(IMainDAO mainDAO) {
		this.mainDAO = mainDAO;
	}


	/**
	 * @return Returns the mainObject.
	 */
	public BaseMain getMainObject() {
		return mainObject;
	}

	/**
	 * @param mainObject
	 *            The mainObject to set.
	 */
	public void setMainObject(BaseMain mainObject) {
		this.mainObject = mainObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.service.IMainService#getSingleMainPO(java.lang.String)
	 */
	public BaseMain getSingleMainPO(String id) throws Exception {
		IMainDAO dao = getMainDAO();
		BaseMain main = dao.loadSinglePO(id, this.getMainObject());
		return main;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.service.IMainService#addMain(java.lang.Object)
	 */
	public void addMain(Object obj) throws Exception {
        System.out.println("Eoms35 mainService addMain");
		mainDAO.saveOrUpdateMain(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.service.IMainService#addMain(java.lang.Object)
	 */
	public void addMainAndLink(Object mainObj,Object linkObj) throws Exception {
        //保存main信息
		this.addMain(mainObj);
		//保存link信息
		ILinkService linkService = (ILinkService) ApplicationContextHolder
		  .getInstance().getBean("LinkService");
		linkService.addLink(linkObj);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.service.IMainService#getHolds(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	public List getHolds(Map condition,Integer curPage, Integer pageSize)
			throws SheetException {
		List list = null;
		try {
			list = mainDAO.getHolds(condition,curPage, pageSize, this.getMainObject());
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.service.IMainService#getHoldsCount()
	 */
	public Integer getHoldsCount() throws SheetException {
		Integer count = new Integer(0);
		try {
			count = mainDAO.getHoldsCount(this.getMainObject());
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.service.IMainService#getStarterCount(java.lang.String)
	 */
	public Integer getStarterCount(String userId) throws SheetException {
		Integer count = new Integer(0);
		try {
			count = mainDAO.getStarterCount(userId, this.getMainObject());
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.service.IMainService#getStarterList(java.lang.String,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	public HashMap getStarterList(String userId, Integer curPage, Integer pageSize, HashMap condition)
			throws SheetException {
		HashMap map = new HashMap();
		try {
			map = mainDAO.getStarterList(userId, curPage, pageSize, this.getMainObject(), condition);
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return map;
	}

	/**
	 * 工单查询
	 * 
	 * @return 返回List结果
	 * @throws SheetException
	 */
	public List getQueryResult(String[] hsql, Map actionForm,Map condition, Integer curPage,
			Integer pageSize, int[] aTotal, String queryType) throws SheetException {
		// TODO Auto-generated method stub
		String sql=null;
		condition.put("pageSize", pageSize);
		if(hsql!=null) sql=hsql[0];			
		if (sql == null || sql.equals("")) {
		  hsql[0] = workSheetQO.getClauseSql(actionForm,condition);
		  sql = hsql[0];
		}
		List result = mainDAO.getQuerySheetByCondition(sql, curPage, pageSize, aTotal, queryType);
		System.out.println(aTotal[0]);
		return result;
	}
	
	public List getQueryResult(String[] hsql, Map condition, Integer curPage,
			Integer pageSize, int[] aTotal, String queryType) throws SheetException {
		// TODO Auto-generated method stub
		String sql=null;
		condition.put("pageSize", pageSize);
		if(hsql!=null) sql=hsql[0];			
		if (sql == null || sql.equals("")) {
		  //hsql[0] = workSheetQO.getClauseSql(actionForm,condition);
		  sql = hsql[0];
		}
		List result = mainDAO.getQuerySheetByCondition(sql, curPage, pageSize, aTotal, queryType);
		System.out.println(aTotal[0]);
		return result;
	}
	
	/**
	 * 工单列表查询
	 * 
	 * @return 返回List结果
	 * @throws SheetException
	 */
	public List getQueryAclListResult(String[] hsql, Map actionForm,Map condition, Integer curPage,
			Integer pageSize, int[] aTotal, String userId, String deptId) throws SheetException {
		// TODO Auto-generated method stub
		String sql=null;
		condition.put("pageSize", pageSize);
		if(hsql!=null) sql=hsql[0];			
		if (sql == null || sql.equals("")) {
		  hsql[0] = workSheetQO.getAclClauseSql(actionForm,condition);
		  sql = hsql[0];
		}
		List result = mainDAO.getQuerySheetByCondition(sql, curPage, pageSize, aTotal, "record");
		System.out.println(aTotal[0]);
		return result;
	}

		
	/**
	 * @return Returns the workSheetQO.
	 */
	public IWorkSheetQO getWorkSheetQO() {
		return workSheetQO;
	}
	/**
	 * @param workSheetQO The workSheetQO to set.
	 */
	public void setWorkSheetQO(IWorkSheetQO workSheetQO) {
		this.workSheetQO = workSheetQO;
	}
	/**
	 * @return Returns the flowTemplateName.
	 */
	public String getFlowTemplateName() {
		return flowTemplateName;
	}
	/**
	 * @param flowTemplateName The flowTemplateName to set.
	 */
	public void setFlowTemplateName(String flowTemplateName) {
		this.flowTemplateName = flowTemplateName;
	}
	
	/**
     * 获取工单流水号
     * @return
     * @throws SheetException
     */
    public String getSheetId() throws SheetException{
    	String sheetId="";
    	ITawSystemWorkflowManager wfManager=
    		(ITawSystemWorkflowManager)ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager"); 	
    	SheetAttributes sheetAttributes=(SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes");
    	String flowTemplateName =this.getFlowTemplateName();
    	if(flowTemplateName.indexOf(",") != -1){
    		flowTemplateName = flowTemplateName.substring(0,flowTemplateName.indexOf(","));
    	}    	
    	System.out.print("flowTemplateName==="+flowTemplateName);
    	TawSystemWorkflow workflow = wfManager.getTawSystemWorkflowByName(flowTemplateName);
    	
        if(workflow != null){ 
        	String flowId=StaticMethod.nullObject2String(workflow.getFlowId());
			if(flowId.length()==1) flowId="00"+flowId;
			else if(flowId.length()==2) flowId="0"+flowId;
	    	 String sheetIdKey = sheetAttributes.getRegionId() + "-"+ flowId + "-" + StaticMethod.getYYMMDD()+"-"+sheetAttributes.getServiceNum();
	    	 Object maxNumberObject = SheetKeyInit.maxNumber.get(sheetIdKey);
	    	 String serviceNum = sheetAttributes.getServiceNum();
	    	 System.out.print("sheetIdKey==="+"maxNumberObject==="+maxNumberObject);
	    	 if (maxNumberObject != null) {
	    		int maxNumberValue = ((Integer)maxNumberObject).intValue();
	    		maxNumberValue = maxNumberValue + 1;
	    		SheetKeyInit.maxNumber.put(sheetIdKey, new Integer(maxNumberValue));
	    		String strXYZ = String.valueOf(StaticMethod.nullObject2int(sheetAttributes.getSheetMaxSize()) + maxNumberValue);
	    		System.out.print("maxNumberObject != null strXYZ==="+strXYZ);
	    		sheetId = sheetIdKey + "-" + serviceNum + strXYZ.substring(1);
	    	 } else {
	    		 SheetKeyInit.maxNumber.put(sheetIdKey, new Integer(1));
	    		 String strXYZ = String.valueOf(StaticMethod.nullObject2int(sheetAttributes.getSheetMaxSize()) + 1);
	    		 System.out.print("strXYZ==="+strXYZ);
	    		 sheetId = sheetIdKey + "-" + serviceNum + strXYZ.substring(1);
	    	 }
            //	清楚前一天日期所存储的最大数
	        Calendar today = Calendar.getInstance();
	 	    today.add(Calendar.DAY_OF_MONTH, -1);
	 	    String preDate = DateFormatUtils.format(today.getTime(), "yyMMdd");
	 	    String preSheetIdKey = sheetAttributes.getRegionId() + "-" + flowId + "-" + preDate;
	 	    System.out.print("preSheetIdKey==="+preSheetIdKey);
	 	    if (SheetKeyInit.maxNumber.get(preSheetIdKey) != null) {
	 	    	SheetKeyInit.maxNumber.remove(preSheetIdKey);
	 	    }
	 	    
        }
        
        
    	return sheetId;
    }
/*    public String getSheetId() throws SheetException{
    	String sheetId="";
    	String sequenceName = "";
    	ITawSystemWorkflowManager wfManager=
    		(ITawSystemWorkflowManager)ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager"); 	
    	SheetAttributes sheetAttributes=(SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes");
    	String flowTemplateName =this.getFlowTemplateName();
    	if(flowTemplateName.indexOf(",") != -1){
    		flowTemplateName = flowTemplateName.substring(0,flowTemplateName.indexOf(","));
    	} 
    	if (flowTemplateName.length()>30) {
    		sequenceName = flowTemplateName.substring(0, 30);
    	} else {
    		sequenceName = flowTemplateName;
    	}
    	
    	int maxNumberValue = 1;
    	//通过读取seqence得到流水号最后部分的个数值   add by jialei 2009-07-30
    	try{
    		String sql = "select "+sequenceName+".NEXTVAL from dual";
    		HibernateTemplate ht = (HibernateTemplate)((EomsSheetHibernateSupport)this.getMainDAO()).getHibernateTemplate();
    		SessionFactory sf = ht.getSessionFactory();
    		SQLQuery sq = sf.getCurrentSession().createSQLQuery(sql);
    		List list = sq.list();
    		if(list!=null&&list.size()>0){
    			java.math.BigDecimal max = (java.math.BigDecimal)list.get(0);
    			maxNumberValue = max.intValue();
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
  	
    	System.out.println("flowTemplateName==="+flowTemplateName);
    	TawSystemWorkflow workflow = wfManager.getTawSystemWorkflowByName(flowTemplateName);
    	
        if(workflow != null){ 
        	String flowId=StaticMethod.nullObject2String(workflow.getFlowId());
			if(flowId.length()==1) flowId="00"+flowId;
			else if(flowId.length()==2) flowId="0"+flowId;
	    	String sheetIdKey = sheetAttributes.getRegionId() + "-"+ flowId + "-" + StaticMethod.getYYMMDD();
	    	System.out.println("sheetIdKey==="+"maxNumberObject==="+maxNumberValue);
	    	String strXYZ = String.valueOf(maxNumberValue);
	    	String maxSize = sheetAttributes.getSheetMaxSize();
	    	while(maxSize.length()>strXYZ.length()){
	    		strXYZ = "0"+strXYZ;
	    	}
	    	sheetId = sheetIdKey + "-" + strXYZ;
        }
        System.out.println("sheetId==="+sheetId);
    	return sheetId;
    }*/
    
    /**
	 * 通过工单的父流水号，获取工单mian对象
	 * @param parentSheetId 工单父流程流水号
	 * @return
	 * @throws SheetException
	 */
	public BaseMain getMainObjByParentSheetId(String parentCorrelation) throws SheetException{
	    BaseMain main=null;
	    String hql = "from "+this.getMainObject().getClass().getName()+" main where main.parentCorrelation='"+parentCorrelation+"' order by main.sendTime asc";
	    int count[] = {0};
	    try{
	       List list = this.getMainDAO().getQuerySheetByCondition(hql,new Integer(0),new Integer(15),count, "");
	       if(list!=null && list.size()>=1){
	           main = (BaseMain)list.get(0); 
	       }
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    return main;
	}
	
	
	 /**
	 * 通过工单的父流水号，获取工单mian列表
	 * @param parentSheetId 工单父流程流水号
	 * @return
	 * @throws SheetException
	 */
	public List getMainListByParentSheetId(String parentCorrelation) throws SheetException{
	    List list=new ArrayList();
	    String hql = "from "+this.getMainObject().getClass().getName()+" main where main.parentCorrelation='"+parentCorrelation+"' order by main.sendTime asc";
	    int count[] = {0};
	    try{
	       list = this.getMainDAO().getQuerySheetByCondition(hql,new Integer(0),new Integer(100),count, "");
	       
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    return list;
	}
	
	 /**
	 * 删除mian对象
	 * @param baseMain main对象
	 * @return
	 */
	public void removeMain(Object baseMain){
	    this.mainDAO.removeMain(baseMain);
	}
	
	/**
	 * 根据用户ID查找出他的所有模板
	 * @author wangjianhua
	 * @date 2008-07-22
	 * @param userId, curPage, pageSize, aTotal
	 * @return sheets列表
	 * @throws SheetException
	 */
	public List getTemplatesByUserIds(String userId, Integer curPage, Integer pageSize, int[] aTotal) throws SheetException{
		return this.mainDAO.getTemplatesByUserIds(userId, curPage, pageSize, aTotal, this.getMainObject());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.service.IMainService#getAllAttachmentsBySheet(java.lang.String)
	 */
	public List getAllAttachmentsBySheet(String where) throws SheetException{
		return this.mainDAO.getAllAttachmentsBySheet(where);
	}

	public  HashMap getListForAdmin(Map condition ,Integer startIndex,Integer length) throws Exception{
		HashMap taskMap=new HashMap();
		String orderCondition = (String)condition.get("orderCondition");	
		//增加条件main.deleted<>'1' ，过滤已经被删除或隐藏的工单 add by jialei
		String sql=" from "+this.getMainObject().getClass().getName()+" main where main.templateFlag <> 1 and main.templateFlag <> -1 and  main.status="+Constants.SHEET_RUN +" and main.deleted<>'1' " ;
		StringBuffer hql = new StringBuffer(sql);	
		if(length.intValue()!=-1){
		if(!orderCondition.equals("")&&orderCondition!=null){
			hql.append(" order by "+orderCondition);
		}
		else{ 
			hql.append(" order by main.sendTime desc");
		}	
		}
		taskMap=mainDAO.getMainListBySql(hql.toString(),startIndex,length);		
		return taskMap;
	} 

	public void saveOrUpdateMain(final BaseMain main){
		this.mainDAO.saveOrUpdateMain(main);
	}
	/*
	 * (non-Javadoc)
	 * zhangying
	 * @see com.boco.eoms.sheet.base.service.IMainService#getHoldsCount()
	 */
	public Integer getCancelCount() throws SheetException {
		Integer count = new Integer(0);
		try {
			count = mainDAO.getCancelCount(this.getMainObject());
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return count;
	}
	/* 取撤销列表
	 * (non-Javadoc)
	 *  zhangying
	 * @see com.boco.eoms.sheet.base.service.IMainService#getHolds(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	public List getCancelList(Integer curPage, Integer pageSize, HashMap condition)
			throws SheetException {
		List list = null;
		try {
			list = mainDAO.getCancelList(curPage, pageSize, this.getMainObject(),condition);
		} catch (Exception e) {
			throw new SheetException(e);
		}
		return list;
	}
	
	public BaseMain getMainBySheetId(String sheetId){
		return this.mainDAO.getMainBySheetId(sheetId, this.getMainObject());
	}

	public BaseMain getSinglePOByProcessId(String processId)
		throws Exception {
		return this.mainDAO.loadSinglePOByProcessId(processId, this.getMainObject());
	}
	/**
	 * 取隐藏工单列表
	 * 
	 * @author jialei
	 * @param condition
	 *            保存mian、link、task对象
	 * @param curPage
	 *            当前页
	 * @param pageSize
	 *            一页显示条数
	 * @return
	 * @throws SheetException
	 */
	public HashMap getHideList(Map condition, final Integer curPage,
			final Integer pageSize) throws SheetException{
		BaseMain mainObject = (BaseMain)condition.get("mainObject");
		BaseLink linkObject = (BaseLink)condition.get("linkObject");
		ITask taskObject = (ITask)condition.get("taskObject");
		String hqlstr = (String)condition.get("hql");
		String hql = "select distinct main from "+mainObject.getClass().getName()+" main,"+linkObject.getClass().getName()+" link,"+taskObject.getClass().getName()+" task where"
					+ " main.id=task.sheetKey and link.mainId=main.id "
					+ hqlstr
					+ " order by main.sendTime desc";
		HashMap resultMap=mainDAO.getMainListBySql(hql,curPage,pageSize);
		return resultMap;
		
	}
	public  List getMainsByCondition(String condition) {		
		String hql = " from "+this.getMainObject().getClass().getName()+ " where " + condition + " order by sendTime desc" ;
		List mains = mainDAO.getMainListBySql(hql);		
		return mains;
	}
	/**
	 * 清除当前session
	 */
	public void clearObjectOfCurrentSession() {
    	this.mainDAO.clearObjectOfCurrentSession();
    }
	/**
	 * 当有两个相同标识不同实体时执行
	 */
    public void mergeObject(Object obj){
    	this.mainDAO.mergeObject(obj);
    }
    
	/**
	 * 整合用,草稿列表
	 */
	public List getDraftList(String userId, final Integer curPage, final Integer pageSize, int[] aTotal, Object obj) throws SheetException {
		return this.mainDAO.getDraftListByUserIds(userId, curPage, pageSize,aTotal, obj);
	}
    
	
    public  int getXYZ(String id) throws Exception{
    	return this.mainDAO.getXYZ(id, this.getMainObject());
    }
    public  List getHoldedListForUser(Map condition, Integer curPage,
			Integer length, int[] aTotal, String userId, String deptId) throws Exception{
		List sheetList = new ArrayList();
		String orderCondition = (String) condition.get("orderCondition");
		condition.put("userId", userId);
		condition.put("deptId", deptId);
		String sql = QuerySqlInit.getHoldedListForUserSql(condition);
		StringBuffer hql = new StringBuffer(sql);
		if (length.intValue() != -1) {
			if (!orderCondition.equals("") && orderCondition != null) {
				hql.append("order by " + orderCondition);
			} else {
				hql.append("order by main.sendTime desc");
			}
		}
		sheetList = mainDAO.getQuerySheetByCondition(hql.toString(), curPage, length, aTotal, "");
		return sheetList;
	}
}
