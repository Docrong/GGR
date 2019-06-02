package com.boco.eoms.sheet.base.service.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator; 
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.ejb.service.SaveDataObjectImpl;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.util.EjbStaticMethod;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSStaticMethod;
import com.boco.eoms.sheet.base.util.HibernateConfigurationHelper;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceInfoManager;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;



/**
 * Bean implementation class for Enterprise Bean: SaveDataService
 */
public class SaveDataServiceBean implements javax.ejb.SessionBean  {

	private ThreadLocal serviceBeanId = new ThreadLocal();

	private javax.ejb.SessionContext mySessionCtx;

	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}

	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {

	}

	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}

	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}

	public void init(String mainServiceBeanId) throws Exception {
		serviceBeanId.set(mainServiceBeanId);
	}

	

	/**
	 * 单独保存或更新main信息
	 * 
	 * @param obj
	 * @param modelName
	 * @param mainServiceBeanId
	 * @return
	 * @throws Exception
	 */
	public void saveOrUpdateMain(Object obj, String modelName)
			throws Exception {
		PreparedStatement pst = null;
		Connection conn = null;
		try{
        //获取XA数据渿
		    conn = getConnection();
		// 现将wps端传输过来的BO对象转换成HashMap
		IBusinessFlowAdapterService businessFlowManager = (IBusinessFlowAdapterService) ApplicationContextHolder
				.getInstance().getBean("BusinessFlowAdapterService");
		Object model = businessFlowManager.transferMainObjectToMode(obj,
				modelName);
		HashMap mainMap=WPSStaticMethod.getNamesWithValueByMethods(model);
        //时间分片，add by qinmin
		String sendTime=StaticMethod.nullObject2String(mainMap.get("sendTime"));
		mainMap=getYYMMDD(mainMap,sendTime,"main");
		pst = saveOrUpdateSql(conn, mainMap, model);
		if(pst!=null){
		  pst.executeUpdate();
		 }
		else{
		  System.out.println("pst is null");
		}
        //流程互调,add by qinmin
		String parentSheetId=StaticMethod.nullObject2String(mainMap.get("parentSheetId"));
		if(!parentSheetId.equals("")){
		  String parentSheetName=StaticMethod.nullObject2String(mainMap.get("parentSheetName"));
		  this.saveSheetRelation(parentSheetId, parentSheetName, mainMap);
		}
	 } 
		catch (Exception e) {
		e.printStackTrace();
		this.getSessionContext().setRollbackOnly();
		throw e;
	  } 
		finally {
		if (null != pst)
			pst.close();
		if (null != conn)
			conn.close();
	  }
	}
	 
	/**
	 * 单独保存或更新业务数据link内容
	 * 
	 * @param obj
	 * @param modelName
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdateLink(Object obj, String modelName) throws Exception {
		PreparedStatement pst = null;
		Connection conn = null;
		String id = "";
		try{	
		 IBusinessFlowAdapterService businessFlowManager = (IBusinessFlowAdapterService) ApplicationContextHolder
				.getInstance().getBean("BusinessFlowAdapterService");
         //获取XA数据渿
		    conn = getConnection();	
		// 现将wps端传输过来的BO对象转换成HashMap
		Object model = businessFlowManager.transferLinkObjectToMode(obj,
				modelName);
		HashMap linkMap=WPSStaticMethod.getNamesWithValueByMethods(model);
        //时间分片，add by qinmin
		String operateTime=StaticMethod.nullObject2String(linkMap.get("operateTime"));
		linkMap=getYYMMDD(linkMap,operateTime,"link");
		//如果id为空，则生成一个id
		id = StaticMethod.nullObject2String(linkMap.get("id"));
		if(id.equals("")){
			id = UUIDHexGenerator.getInstance().getID();
			linkMap.put("id", id);
		}
		pst = saveOrUpdateSql(conn, linkMap, model);
		if(pst!=null){
		  pst.executeUpdate();
		 }
		else{
		  System.out.println("pst is null");
		}
	 } 
		catch (Exception e) {
		e.printStackTrace();
		this.getSessionContext().setRollbackOnly();
		throw e;
	  } 
		finally {
		if (null != pst)
			pst.close();
		if (null != conn)
			conn.close();
	  }
		return id;
	}
	 
	/**
	 * 保存或更新任务信息
	 * 
	 * @param taskMap
	 * @param taskBeanId
	 * @throws Exception
	 */
	public void saveOrUpdateTask(HashMap taskMap, String taskBeanId) throws Exception {
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			ITaskService taskService = (ITaskService) ApplicationContextHolder
					.getInstance().getBean(taskBeanId);
			ITask taskModel = taskService.getTaskModelObject();

			// 获取XA数据渿
			conn = getConnection();

			// 获取task任务信息
			HashMap taskBoMap = (HashMap) taskMap.get("task");
			HashMap processTemplateMap = (HashMap) taskMap.get("process");
			HashMap taskObjectMap = (HashMap) taskMap.get("taskBO");
			HashMap mainObjectMap=(HashMap)taskMap.get("mainBO");
			HashMap linkObjectMap=(HashMap)taskMap.get("linkBO");
			
//			HashMap map = WPSStaticMethod.setTaskModel(taskModel,
//					processTemplateMap, taskBoMap, taskObjectMap,mainObjectMap,linkObjectMap);	
			HashMap map=taskService.setTaskModel(taskModel, processTemplateMap, taskBoMap, 
					   taskObjectMap, mainObjectMap, linkObjectMap);
			String createTime=StaticMethod.nullObject2String(map.get("createTime"));
			map=getYYMMDD(map,createTime,"task");
			pst = saveOrUpdateSql(conn, map, taskModel);
			if(pst!=null){
			 pst.executeUpdate();
			}
			else{
			 System.out.println("pst is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getSessionContext().setRollbackOnly();
			throw e;
		} finally {
			if (null != pst)
				pst.close();
			if (null != conn)
				conn.close();
		}
	}
	/**
	 * 单独保存main信息
	 * 
	 * @param obj
	 * @param modelName
	 * @param mainServiceBeanId
	 * @return
	 * @throws Exception
	 */
	public void saveMain(Object obj, String modelName)
			throws Exception {
		PreparedStatement pst = null;
		Connection conn = null;
		try{
        //获取XA数据渿
		    conn = getConnection();
		// 现将wps端传输过来的BO对象转换成HashMap
		IBusinessFlowAdapterService businessFlowManager = (IBusinessFlowAdapterService) ApplicationContextHolder
				.getInstance().getBean("BusinessFlowAdapterService");
		Object model = businessFlowManager.transferMainObjectToMode(obj,
				modelName);
		HashMap mainMap=WPSStaticMethod.getNamesWithValueByMethods(model);
        //时间分片，add by qinmin
		String sendTime=StaticMethod.nullObject2String(mainMap.get("sendTime"));
		mainMap=getYYMMDD(mainMap,sendTime,"main");
		pst = saveSql(conn, mainMap, model);
		if(pst!=null){
		  pst.executeUpdate();
		 }
		else{
		  System.out.println("pst is null");
		}
        //流程互调,add by qinmin
		String parentSheetId=StaticMethod.nullObject2String(mainMap.get("parentSheetId"));
		if(!parentSheetId.equals("")){
		  String parentSheetName=StaticMethod.nullObject2String(mainMap.get("parentSheetName"));
		  this.saveSheetRelation(parentSheetId, parentSheetName, mainMap);
		}
	 } 
		catch (Exception e) {
		e.printStackTrace();
		this.getSessionContext().setRollbackOnly();
		throw e;
	  } 
		finally {
		if (null != pst)
			pst.close();
		if (null != conn)
			conn.close();
	  }
	}
	 
	/**
	 * 单独保存业务数据link内容
	 * 
	 * @param obj
	 * @param modelName
	 * @return
	 * @throws Exception
	 */
	public String saveLink(Object obj, String modelName) throws Exception {
		PreparedStatement pst = null;
		Connection conn = null;
		String id = "";
		try{	
		 IBusinessFlowAdapterService businessFlowManager = (IBusinessFlowAdapterService) ApplicationContextHolder
				.getInstance().getBean("BusinessFlowAdapterService");
         //获取XA数据渿
		    conn = getConnection();	
		// 现将wps端传输过来的BO对象转换成HashMap
		Object model = businessFlowManager.transferLinkObjectToMode(obj,
				modelName);
		HashMap linkMap=WPSStaticMethod.getNamesWithValueByMethods(model);
        //时间分片，add by qinmin
		String operateTime=StaticMethod.nullObject2String(linkMap.get("operateTime"));
		linkMap=getYYMMDD(linkMap,operateTime,"link");
		//如果id为空，则生成一个id
		id = StaticMethod.nullObject2String(linkMap.get("id"));
		if(id.equals("")){
			id = UUIDHexGenerator.getInstance().getID();
			linkMap.put("id", id);
		}
		pst = saveSql(conn, linkMap, model);
		if(pst!=null){
		  pst.executeUpdate();
		 }
		else{
		  System.out.println("pst is null");
		}
	 } 
		catch (Exception e) {
		e.printStackTrace();
		this.getSessionContext().setRollbackOnly();
		throw e;
	  } 
		finally {
		if (null != pst)
			pst.close();
		if (null != conn)
			conn.close();
	  }
		return id;
	}
	 
	/**
	 * 保存任务信息
	 * 
	 * @param taskMap
	 * @param taskBeanId
	 * @throws Exception
	 */
	public void saveTask(HashMap taskMap, String taskBeanId) throws Exception {
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			ITaskService taskService = (ITaskService) ApplicationContextHolder
					.getInstance().getBean(taskBeanId);
			ITask taskModel = taskService.getTaskModelObject();

			// 获取XA数据渿
			conn = getConnection();

			// 获取task任务信息
			HashMap taskBoMap = (HashMap) taskMap.get("task");
			HashMap processTemplateMap = (HashMap) taskMap.get("process");
			HashMap taskObjectMap = (HashMap) taskMap.get("taskBO");
			HashMap mainObjectMap=(HashMap)taskMap.get("mainBO");
			HashMap linkObjectMap=(HashMap)taskMap.get("linkBO");
			
//			HashMap map = WPSStaticMethod.setTaskModel(taskModel,
//					processTemplateMap, taskBoMap, taskObjectMap,mainObjectMap,linkObjectMap);	
			HashMap map=taskService.setTaskModel(taskModel, processTemplateMap, taskBoMap, 
					   taskObjectMap, mainObjectMap, linkObjectMap);
			String createTime=StaticMethod.nullObject2String(map.get("createTime"));
			map=getYYMMDD(map,createTime,"task");
			pst = saveSql(conn, map, taskModel);
			if(pst!=null){
			 pst.executeUpdate();
			}
			else{
			 System.out.println("pst is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getSessionContext().setRollbackOnly();
			throw e;
		} finally {
			if (null != pst)
				pst.close();
			if (null != conn)
				conn.close();
		}
	}
	/**
	 * 单独更新main信息
	 * 
	 * @param mainMap HashMap
	 * @return
	 * @throws Exception
	 */
	public void updateMain(HashMap mainMap,String modelName)
			throws Exception {
		Connection conn = null;
		try{
			Object mainModel = Class.forName(modelName).newInstance();
			conn = getConnection();
			int rs = updateSql(conn, mainMap, mainModel);
			System.out.println("--------------update "+rs+" rows--------------");
		}catch (Exception e) {
			e.printStackTrace();
			this.getSessionContext().setRollbackOnly();
			throw e;
		}finally {
			if (null != conn)
				conn.close();
		}
	}
	/**
	 * 单独更新main信息
	 * 
	 * @param linkMap HashMap
	 * @return
	 * @throws Exception
	 */
	public void updateLink(HashMap linkMap,String modelName)
			throws Exception {
		Connection conn = null;
		try{
			Object linkModel = Class.forName(modelName).newInstance();
			conn = getConnection();
			int rs = updateSql(conn, linkMap, linkModel);
			System.out.println("--------------update "+rs+" rows--------------");
		}catch (Exception e) {
			e.printStackTrace();
			this.getSessionContext().setRollbackOnly();
			throw e;
		}finally {
			if (null != conn)
				conn.close();
		}
	}
	/**
	 * 单独更新task信息
	 * 
	 * @param linkMap HashMap
	 * @return
	 * @throws Exception
	 */
	public void updateTask(HashMap taskMap,String taskBeanId)
			throws Exception {
		Connection conn = null;
		try{
			ITaskService taskService = (ITaskService) ApplicationContextHolder.getInstance().getBean(taskBeanId);
			Object taskModel = taskService.getTaskModelObject();
			conn = getConnection();
			int rs = updateSql(conn, taskMap, taskModel);
			System.out.println("--------------update "+rs+" rows--------------");
		}catch (Exception e) {
			e.printStackTrace();
			this.getSessionContext().setRollbackOnly();
			throw e;
		}finally {
			if (null != conn)
				conn.close();
		}
	}
    /**
     * 获取数据库链接
     * @return
     * @throws Exception
     */
	private Connection getConnection() throws Exception {
		String jndiName = "jdbc/eoms";
		Connection conn = null;

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.ibm.websphere.naming.WsnInitialContextFactory");
		InitialContext ic = new InitialContext(env);
		DataSource ds = (DataSource) ic.lookup(jndiName);
		conn = ds.getConnection();
		return conn;
	}
    /**
     * 保存sql
     * @param conn
     * @param map
     * @param obj
     * @return
     * @throws Exception
     */
	private PreparedStatement saveSql(Connection conn, HashMap map, Object obj)
	throws Exception {
		PreparedStatement pst;
		// 将任务表结构信息
		String tableName = HibernateConfigurationHelper.getTableName(obj.getClass());
		String columnNames = HibernateConfigurationHelper.getColumnNames(obj.getClass());
		String PkColumnName = HibernateConfigurationHelper.getPkColumnName(obj.getClass());
		String propNames = PkColumnName+","+ HibernateConfigurationHelper.getPropNamesWithoutPK(obj.getClass());
		System.out.println("tableName="+tableName+"columnNames="+columnNames+"PkColumnName="+PkColumnName+"propNames="+propNames);

		// 先查询是否存在任务记录
		try {
			String id = StaticMethod.nullObject2String(map.get(PkColumnName));
			String insertSql = "insert into " + tableName + "(";
			String value = "";
			String[] column = columnNames.split(",");
			for (int i = 0; i < column.length; i++) {
				String tempColumn = column[i];
				String propName = HibernateConfigurationHelper.getPropNameByColumnName(obj.getClass(), tempColumn);
				Object tempObject = map.get(propName);
				if (tempObject != null) {
					insertSql = insertSql + tempColumn + ",";
					if (!value.equals("")) {
						value = value + ",";
					}
					value = value + "?";
				}
			}
			if (insertSql.indexOf(",") > 0)
				insertSql = insertSql.substring(0, insertSql.lastIndexOf(","));
			value = ") values (" + value + ")";
			insertSql = insertSql + value;
			System.out.println("insertSql=" + insertSql);
			pst = conn.prepareStatement(insertSql);
			String[] prop = propNames.split(",");
			int k = 1;
			for (int i = 0; i < prop.length; i++) {
				Object tempObject = null;
				if (prop[i].equals(PkColumnName) && id.equals("")) {
					tempObject = UUIDHexGenerator.getInstance().getID();
				} else {
					tempObject = map.get(prop[i]);
				}
				if (tempObject != null) {
					System.out.println("propvalue  name=" + prop[i] + "values="	+ tempObject.toString());
					pst.setObject(k, tempObject);
					k++;
				} else {
					System.out.println("column name=" + prop[i]	+ "value is null");
				}
			} 
		} catch (Exception ex) {
			System.out.println("--------------savesql is error!-------------------"+ex.getMessage());
			ex.printStackTrace();
			throw ex;
		}
		return pst;
	}
    /**
     * 更新sql
     * @param conn
     * @param map
     * @param obj
     * @return
     * @throws Exception
     */
	private int updateSql(Connection conn, HashMap map, Object obj) throws Exception{
		String tableName = HibernateConfigurationHelper.getTableName(obj.getClass());
		String PkColumnName = HibernateConfigurationHelper.getPkColumnName(obj.getClass());

		String updateSql = "update " + tableName + " set ";
		String value = "";
		String id = "";
		String idValue = "";
		int arrSize = map.size();
		Iterator it = map.keySet().iterator();
		Object[] strArrColumnValue = new Object[arrSize];
		int i = 0;
		while(it.hasNext()){
			String tempColumn=(String)it.next();
			if(!tempColumn.equals("id")){
				Object tempValue=map.get(tempColumn);
				if(tempValue!=null){
					if (!value.equals("")) {
						value = value+",";
					}
					value = value + tempColumn + "=?";
					strArrColumnValue[i] = tempValue;
					i++;
				}
			}else{
				id = tempColumn;
				idValue = (String)map.get(tempColumn);
			}
		}
		updateSql = updateSql + value + " where " + PkColumnName + "= '"+ idValue+"'";
		System.out.println("updateSql="+updateSql);
		Statement stm = null;
		int result = 0;
		PreparedStatement pst;
		pst = conn.prepareStatement(updateSql);
		int k = 1;
		for (i = 0; i < strArrColumnValue.length; i++) {
			Object tempObject=strArrColumnValue[i];
			if(tempObject!=null){
				System.out.println("propvalue  values="+tempObject.toString());
				pst.setObject(k, tempObject);
				k++;
			}
		}
		try {
			result = pst.executeUpdate();
		} catch (Exception ex) {
			System.out.println("--------------updatesql is error!--------------"+ex.getMessage());
			ex.printStackTrace();
			throw ex;
		} finally {
			if (null != stm) {
				try {
					stm.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}
    /**
     * 保存或更新sql
     * @param conn
     * @param map
     * @param obj
     * @return
     * @throws Exception
     */
	private PreparedStatement saveOrUpdateSql(Connection conn, HashMap map, Object obj)
			throws Exception {
		PreparedStatement pst;
		// 将任务表结构信息
		String tableName = HibernateConfigurationHelper.getTableName(obj
				.getClass());
		String columnNames = HibernateConfigurationHelper.getColumnNames(obj
				.getClass());
		String PkColumnName = HibernateConfigurationHelper.getPkColumnName(obj
				.getClass());
		String propNames = PkColumnName+","
				+ HibernateConfigurationHelper.getPropNamesWithoutPK(obj
						.getClass());
		System.out.println("tableName="+tableName+"columnNames="+columnNames+
				 "PkColumnName="+PkColumnName+"propNames="+propNames);

		// 先查询是否存在任务记录
		int count = 0;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			String id = StaticMethod.nullObject2String(map.get(PkColumnName));
			if (!id.equals("")) {
				String selectSql = "select count(distinct " + PkColumnName
						+ ") from " + tableName + " where " + PkColumnName
						+ "=?";
				System.out.println("selectSql="+selectSql);
				smt = conn.prepareStatement(selectSql);
				smt.setString(1, id);
				rs = smt.executeQuery();
				if (rs.next()){
					count = rs.getInt(1);
				}

			}
			if (count != 0) {
				// here is update
				String updateSql = "update " + tableName + " set ";
				String[] column = columnNames.split(",");
				String value = "";
				for (int i = 0; i < column.length; i++) {
					String tempColumn=column[i];
					String propName =HibernateConfigurationHelper.getPropNameByColumnName(obj
							.getClass(),tempColumn);
					Object tempObject=map.get(propName);
					if(tempObject!=null){
					 if (!value.equals("")) {
						value = value + ", ";
					 }
					 value = value+column[i] + "=?";
				  }
				}
				updateSql = updateSql + value + " where " + PkColumnName + "= '"
						+ id+"'";
				System.out.println("updateSql="+updateSql);
				pst = conn.prepareStatement(updateSql);
				String[] prop = propNames.split(",");
				int k=1;
				for (int i = 0; i < prop.length; i++) {
					Object tempObject=map.get(prop[i]);
					  if(tempObject!=null){
						System.out.println("propvalue  name="+prop[i]+"values="+tempObject.toString());
						pst.setObject(k, tempObject);	
						k++;
					  }
					  else {
						System.out.println("column name="+prop[i]+"value is null"); 
					 }
				}

			} else {
				// here is insert
				
				String insertSql="insert into "+tableName+"(";
				String value="";
				String[] column = columnNames.split(",");
				for(int i=0;i<column.length; i++){
					String tempColumn=column[i];
					String propName =HibernateConfigurationHelper.getPropNameByColumnName(obj
							.getClass(),tempColumn);
					 Object tempObject=map.get(propName);
					 if(tempObject!=null){
						 insertSql=insertSql+ tempColumn+",";
						 
						 if (!value.equals("")) {
								value = value + ",";
							}
							value = value + "?";
					 }
				}
				if(insertSql.indexOf(",")>0)insertSql=insertSql.substring(0,insertSql.lastIndexOf(","));
				value = ") values (" + value + ")";
				insertSql=insertSql+value;
				System.out.println("insertSql="+insertSql);
				pst = conn.prepareStatement(insertSql);
				String[] prop = propNames.split(",");
				int k=1;
				for (int i = 0; i < prop.length; i++) {
				  Object tempObject=null;
				  if(prop[i].equals(PkColumnName)&&id.equals("")){
					   tempObject=UUIDHexGenerator.getInstance()
						.getID() ;
				  }
				  else {
					   tempObject=map.get(prop[i]);
				  }
				  if(tempObject!=null){
						System.out.println("propvalue  name="+prop[i]+"values="+tempObject.toString());
						pst.setObject(k,tempObject);	
						k++;
					  }
					  else {
						System.out.println("column name="+prop[i]+"value is null"); 
					 }
				 
			  } 
				
			}
		} catch (Exception ex) {
			System.out.println("--------------savesql is error!-------------------"+ex.getMessage());
			ex.printStackTrace();
			throw ex;
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (null != smt) {
				try {
					smt.close();
				} catch (SQLException e) {
				}
			}
		}
		return pst;
	}
	/**
	 * 根据操作类型，sheetKey更新task，目前应用于强制归档、强制作废、作废等功能
	 */
		
	public void updateTaskByStatus(String sheetKey,String taskBeanId) throws Exception{
		PreparedStatement pst = null;
		Connection conn = null;
		try{
			ITaskService taskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(taskBeanId);
			ITask taskModel = taskService.getTaskModelObject();
	       //获取XA数据渿
			conn = getConnection();
			String tableName = HibernateConfigurationHelper.getTableName(taskModel.getClass());
			String sql = "update " + tableName + " set taskStatus = " + Constants.TASK_STATUS_FINISHED +" where sheetKey = " + "'"+ sheetKey +"'";
			System.out.println("updateTaskByStatus sql is ----"+sql);
			pst = conn.prepareStatement(sql);
			if(pst!=null){
				 pst.executeUpdate();
			}
			else{
				 System.out.println("updateTaskByStatus pst is null");
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getSessionContext().setRollbackOnly();
			
		} finally {
			if (null != pst)
				pst.close();
			if (null != conn)
				conn.close();
		}
		
	}

	/**
	 * 修改task信息状态值。
	 */
		
	public void updateTaskState(String taskId,String taskState,String taskBeanId) throws Exception{
		PreparedStatement pst = null;
		Connection conn = null;
		try{
			ITaskService taskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(taskBeanId);
			ITask taskModel = taskService.getTaskModelObject();
	       //获取XA数据渿
			conn = getConnection();
			String tableName = HibernateConfigurationHelper.getTableName(taskModel.getClass());
			String sql = "update " + tableName + " set taskStatus = " + taskState +" where id = " + "'"+ taskId +"'";
			System.out.println("updateTaskState sql is ----"+sql);
			pst = conn.prepareStatement(sql);
			if(pst!=null){
				 pst.executeUpdate();
			}
			else{
				 System.out.println("updateTaskState pst is null");
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getSessionContext().setRollbackOnly();
			
		} finally {
			if (null != pst)
				pst.close();
			if (null != conn)
				conn.close();
		}
		
	}
	
	public void updateTaskBySubTaskFlag(String taskId,String subTaskFlag,String taskBeanId) throws Exception{
		PreparedStatement pst = null;
		Connection conn = null;
		try{
			System.out.println("updateTaskBySubTaskFlag begin");
			ITaskService taskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(taskBeanId);
			ITask taskModel = taskService.getTaskModelObject();
	       //获取XA数据渿
			conn = getConnection();
			String tableName = HibernateConfigurationHelper.getTableName(taskModel.getClass());
			String sql = "update " + tableName + " set subTaskFlag = '" + subTaskFlag +"' where id = " + "'"+ taskId +"'";
			System.out.println("updateTaskBySubTaskFlag sql is ----"+sql);
			pst = conn.prepareStatement(sql);
			if(pst!=null){
				 pst.executeUpdate();
			}
			else{
				 System.out.println("updateTaskBySubTaskFlag pst is null");
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getSessionContext().setRollbackOnly();
			
		} finally {
			if (null != pst)
				pst.close();
			if (null != conn)
				conn.close();
		}
		
	}	
	/**
	 * 时间字段分片管理，add by qinmin
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	private HashMap getYYMMDD(HashMap map,String dateTime,String tableFlag) throws Exception{
		String YY, MM, DD;		
		if(!dateTime.equals("")){
			int s = dateTime.indexOf(" ");
			String ReturnDateStr = dateTime.substring(0, s);
			Vector ss = StaticMethod.getVector(ReturnDateStr, "-");
			YY = ss.elementAt(0).toString();
			MM = ss.elementAt(1).toString();
			DD = ss.elementAt(2).toString();
		   if(tableFlag.equals("main")){
			 map.put("sendYear", StaticMethod.nullObject2Integer(YY));
			 map.put("sendMonth", StaticMethod.nullObject2Integer(MM));
			 map.put("sendDay", StaticMethod.nullObject2Integer(DD));
		   }else if (tableFlag.equals("link")){
		     map.put("operateYear", StaticMethod.nullObject2Integer(YY));
			 map.put("operateMonth", StaticMethod.nullObject2Integer(MM));
			 map.put("operateDay", StaticMethod.nullObject2Integer(DD)); 
		   }else if (tableFlag.equals("task")){
			 map.put("createYear", StaticMethod.nullObject2Integer(YY));
			 map.put("createMonth", StaticMethod.nullObject2Integer(MM));
			 map.put("createDay", StaticMethod.nullObject2Integer(DD));		   
		   }
		}		
	   return map;
	}
	
	/**
	 * 保存流程互调信息
	 * @param parentSheetKey 主调流程ID
	 * @param parentSheetBeanId 主调流程mainServiceId
	 * @param mainMap 被调流程mainObject
	 * @throws Exception
	 */
	private void saveSheetRelation(String parentSheetKey,String parentSheetBeanId,HashMap mainMap) throws Exception{
		 Connection conn = null;
		 PreparedStatement pst = null;
		 HashMap sheetRelationMap=new HashMap();	 
		 try{			 
		 conn=getConnection();;		 		 
		 IMainService parentMainService=(IMainService)ApplicationContextHolder.getInstance().getBean(parentSheetBeanId);
		 Object obj=parentMainService.getMainObject();
		 String selectCondition="id,sheetTemplateName,title,sheetId,piid";
		 BaseMain parentMain=(BaseMain)selectSql(conn,selectCondition,parentSheetKey,obj);
		 
		 String parentPhaseName = StaticMethod.nullObject2String(mainMap.get("parentPhaseName"));
		 System.out.println("互调：parentPhaseName："+parentPhaseName);
		 if(parentPhaseName.indexOf("@")!=-1){
			 sheetRelationMap.put("parentProcessId", parentPhaseName.substring(parentPhaseName.indexOf("@")+1));
			 sheetRelationMap.put("taskName", parentPhaseName.substring(0,parentPhaseName.indexOf("@")));
			 System.out.println("互调：parentProcessId："+parentPhaseName.substring(parentPhaseName.indexOf("@")+1));
			 System.out.println("互调：taskName："+parentPhaseName.substring(0,parentPhaseName.indexOf("@")));
		 }else{
			 sheetRelationMap.put("parentProcessId",parentMain.getPiid());
			 sheetRelationMap.put("taskName", parentPhaseName);
		 }
		 sheetRelationMap.put("parentId", parentMain.getId());
		 sheetRelationMap.put("parentFlowName", parentMain.getSheetTemplateName());
		 sheetRelationMap.put("parentTitle", parentMain.getTitle());
		 sheetRelationMap.put("parentSheetId", parentMain.getSheetId());

		 sheetRelationMap.put("currentId", StaticMethod.nullObject2String(mainMap.get("id")));
		 sheetRelationMap.put("currentFlowName", StaticMethod.nullObject2String(mainMap.get("sheetTemplateName")));
		 sheetRelationMap.put("currentTitle", StaticMethod.nullObject2String(mainMap.get("title")));
		 sheetRelationMap.put("currentSheetId", StaticMethod.nullObject2String(mainMap.get("sheetId")));
		 sheetRelationMap.put("currentProcessId", StaticMethod.nullObject2String(mainMap.get("piid")));
		 sheetRelationMap.put("userId", StaticMethod.nullObject2String(mainMap.get("sendUserId")));
		 sheetRelationMap.put("subRoleId", StaticMethod.nullObject2String(mainMap.get("sendRoleId")));
		 sheetRelationMap.put("invokeTime", StaticMethod.getTimestamp());
		 sheetRelationMap.put("invokeState", new Integer(Constants.INVOKE_STATE_RUNNING));
		 
		 String tempMode = StaticMethod.nullObject2String(mainMap.get("invokeMode"));
		 
		 if(tempMode.equals("asynchronism")){//asynchronism
			 sheetRelationMap.put("invokeState", new Integer(Constants.INVOKE_STATE_ASYNCHRONISM)); 
		 }else{//synchronization
			 sheetRelationMap.put("invokeState", new Integer(Constants.INVOKE_STATE_RUNNING));
		 }
		 
		 //上面已经加载taskName，此处屏蔽：2009.01.08
		// sheetRelationMap.put("taskName", StaticMethod.nullObject2String(mainMap.get("parentPhaseName")));
		 	 
		 TawSheetRelation sheetRelation=new TawSheetRelation();
		 pst=saveOrUpdateSql(conn, sheetRelationMap, sheetRelation);
		 if(pst!=null){
			  pst.executeUpdate();
		   }
	     }
		 catch (Exception e) {
			e.printStackTrace();
			this.getSessionContext().setRollbackOnly();
			throw e;
		 } 
		 finally {
		   if (null != pst) pst.close();
		   if (null != conn) conn.close();
		 }	
	}
	
	/**
	 * 保存流程互调信息
	 * @param parentSheetKey 主调流程ID
	 * @param parentSheetBeanId 主调流程mainServiceId
	 * @param mainMap 被调流程mainObject
	 * @throws Exception
	 */
	public void updateSheetRelationState(String sheetKey) throws Exception{
		PreparedStatement pst = null;
		Connection conn = null;
		System.out.println("updateSheetRelationState start");
		try{			
	        //获取XA数据渿
			conn = getConnection();
			String tableName = HibernateConfigurationHelper.getTableName(TawSheetRelation.class);
			String sql = "update " + tableName + " set invokeState = " + Constants.INVOKE_STATE_FINISH +" where currentId = " + "'"+ sheetKey +"'";
			System.out.println("updateTaskByStatus sql is ----"+sql);
			pst = conn.prepareStatement(sql);
			if(pst!=null){
				 pst.executeUpdate();
			}
			else{
				 System.out.println("updateSheetRelationState pst is null");
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getSessionContext().setRollbackOnly();
			
		} finally {
			if (null != pst)
				pst.close();
			if (null != conn)
				conn.close();
		}
	}
	
	/**
	 * 查询sql
	 * @param conn
	 * @param whereCondition
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private Object selectSql(Connection conn, String selectCondition,String  whereCondition, Object obj) throws Exception {
      PreparedStatement pst;
       // 将表结构信息
       String tableName = HibernateConfigurationHelper.getTableName(obj.getClass());
       String PkColumnName = HibernateConfigurationHelper.getPkColumnName(obj.getClass());



       PreparedStatement smt = null;
       ResultSet rs = null;
       try {
	     if (!whereCondition.equals("")) {
		 String selectSql = "select "+selectCondition+" from " + tableName + " where " + PkColumnName
				+ "=?";
		System.out.println("selectSql="+selectSql);
		smt = conn.prepareStatement(selectSql);
		smt.setString(1, whereCondition);
		rs = smt.executeQuery();
		if (rs.next()){
			EjbStaticMethod.populate(obj, rs,selectCondition);
		}
	 }
     return obj;
   } catch (Exception ex) {
	 System.out.println("--------------selectsql is error!-------------------"+ex.getMessage());
	 ex.printStackTrace();
	 throw ex;
   } finally {
	  if (null != rs) {
		try {
			rs.close();
		} catch (SQLException e) {
		}
	}
	if (null != smt) {
		try {
			smt.close();
		} catch (SQLException e) {
	  }
	}
   }
 }
	/**
	 * 调用接口
	 * @param mainBeanId
	 * @param sheetKey
	 * @param linkId
	 * @param linkBeanId
	 * @param interfaceType
	 * @param methodType
	 * @return
	 */
	public void invokeWfInterface(String mainBeanId,String sheetKey,String linkId,String linkBeanId,String interfaceType,String methodType,String sendType) throws Exception{
		//System.out.println("invokeWfInterface mehtod start!!!!!!!!");
		if(interfaceType!=null&&!interfaceType.equals("")&&methodType!=null&&!methodType.equals("")){
			//System.out.println("invokeWfInterface start!!!!!!!!");
			IWfInterfaceInfoManager wfInterfaceInfoManager = (IWfInterfaceInfoManager) ApplicationContextHolder
				.getInstance().getBean("iWfInterfaceInfoManager");
			try{
				wfInterfaceInfoManager.saveWfInterfaceInto(mainBeanId, linkBeanId, sheetKey, linkId, interfaceType, methodType, sendType);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("invokeWfInterface end<test>!!!!!!!!");
	}
//	public String getUUID() throws Exception{
//		SaveDataObjectImpl object = new SaveDataObjectImpl();
//		return object.getUUID();
//	}
	public String getUUID() throws Exception{
		return UUIDHexGenerator.getInstance().getID();
	}
	
	public String getDBdialect()
    throws Exception
{
    String hqlDialect = ApplicationContextHolder.getInstance().getHQLDialect().trim();
    return hqlDialect;
}
 
}
