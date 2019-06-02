/*
 * Created on 2007-8-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.adapter.service.wps;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.HashMap;

import com.boco.eoms.base.util.StaticMethod;
import com.ibm.bpe.api.QueryResultSet;
import com.ibm.bpe.api.QueryColumnInfo;
import com.ibm.task.api.HumanTaskManagerService;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

/**
 * @author IBM_USER
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WPSQueryHelper {
	private static final String SOURCE_CLASS = WPSQueryHelper.class.getName();

	/** Logger service */
	private static final Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

	/** boolean variable to identify whether the property files are loaded */
	private static boolean isLoaded = false;

	/**
	 * the Properties saves the properties returned for process instances query.
	 * key: name of the property when returned to client side value: name of the
	 * column to query for the property value
	 */
	private static Properties processInstance = new Properties();

	/**
	 * the Properties save the configurations to construct where clause of
	 * process instances query key: name of the property when specify a query in
	 * the client side value: the actual query format
	 */
	private static Properties processInstanceQuery = new Properties();

	/**
	 * the Properties saves the properties returned for activity workitem key:
	 * name of the property when returned to client side value: name of the
	 * column to query for the property value
	 */
	private static Properties activityWorkitem = new Properties();

	/**
	 * the Properties save the configurations to construct where clause of
	 * activity workitem query key: name of the property when specify a query in
	 * the client side value: the actual query format
	 */
	private static Properties activityWorkitemQuery = new Properties();

	private static Properties taskInstance = new Properties();
	
	private static Properties taskModel=new Properties();

	/**
	 * the property file used to config select clause for the process instances
	 * query
	 */
	private static String processInstancePropertyFile;

	/**
	 * the property file used to config where clause for the process instances
	 * query
	 */
	private static String processInstanceQueryPropertyFile;

	/**
	 * the property file used to config select clause for the activity workitem
	 * query
	 */
	private static String activityWorkitemPropertyFile;

	/**
	 * the property file used to config where clause for the activity workitem
	 * query
	 */
	private static String activityWorkitemQueryPropertyFile;

	private static String taskInstancePropertyFile;
	
	private static String taskModelPropertyFile;

	private static String activityInstanceId;

	private static HumanTaskManagerService humanTaskManager;

	/**
	 * @return Returns the taskModel.
	 */
	public static Properties getTaskModel() {
		return taskModel;
	}
	/**
	 * @param taskModel The taskModel to set.
	 */
	public static void setTaskModel(Properties taskModel) {
		WPSQueryHelper.taskModel = taskModel;
	}
	/**
	 * @return Returns the humanTaskManager.
	 */
	public HumanTaskManagerService getHumanTaskManager() {
		return humanTaskManager;
	}

	/**
	 * @param humanTaskManager
	 *            The humanTaskManager to set.
	 */
	public void setHumanTaskManager(HumanTaskManagerService humanTaskManager) {
		this.humanTaskManager = humanTaskManager;
	}

	/**
	 * @return Returns the activityWorkitemPropertyFile.
	 */
	public String getActivityWorkitemPropertyFile() {
		return activityWorkitemPropertyFile;
	}

	/**
	 * @param activityWorkitemPropertyFile
	 *            The activityWorkitemPropertyFile to set.
	 */
	public void setActivityWorkitemPropertyFile(
			String activityWorkitemPropertyFile) {
		this.activityWorkitemPropertyFile = activityWorkitemPropertyFile;
	}

	/**
	 * @return Returns the activityWorkitemQueryPropertyFile.
	 */
	public String getActivityWorkitemQueryPropertyFile() {
		return activityWorkitemQueryPropertyFile;
	}

	/**
	 * @param activityWorkitemQueryPropertyFile
	 *            The activityWorkitemQueryPropertyFile to set.
	 */
	public void setActivityWorkitemQueryPropertyFile(
			String activityWorkitemQueryPropertyFile) {
		this.activityWorkitemQueryPropertyFile = activityWorkitemQueryPropertyFile;
	}

	/**
	 * @return Returns the activityInstanceId.
	 */
	public String getActivityInstanceId() {
		return activityInstanceId;
	}

	/**
	 * @param activityInstanceId
	 *            The activityInstanceId to set.
	 */
	public void setActivityInstanceId(String activityInstanceId) {
		this.activityInstanceId = activityInstanceId;
	}

	/**
	 * @return Returns the processInstancePropertyFile.
	 */
	public String getProcessInstancePropertyFile() {
		return processInstancePropertyFile;
	}

	/**
	 * @param processInstancePropertyFile
	 *            The processInstancePropertyFile to set.
	 */
	public void setProcessInstancePropertyFile(
			String processInstancePropertyFile) {
		this.processInstancePropertyFile = processInstancePropertyFile;
	}

	/**
	 * @return Returns the processInstanceQueryPropertyFile.
	 */
	public String getProcessInstanceQueryPropertyFile() {
		return processInstanceQueryPropertyFile;
	}

	/**
	 * @param processInstanceQueryPropertyFile
	 *            The processInstanceQueryPropertyFile to set.
	 */
	public void setProcessInstanceQueryPropertyFile(
			String processInstanceQueryPropertyFile) {
		this.processInstanceQueryPropertyFile = processInstanceQueryPropertyFile;
	}

	/**
	 * @return Returns the taskInstancePropertyFile.
	 */
	public String getTaskInstancePropertyFile() {
		return taskInstancePropertyFile;
	}

	/**
	 * @param taskInstancePropertyFile
	 *            The taskInstancePropertyFile to set.
	 */
	public void setTaskInstancePropertyFile(String taskInstancePropertyFile) {
		this.taskInstancePropertyFile = taskInstancePropertyFile;
	}

	/**
	 * 
	 * @return
	 */
	public static String getProcessInstanceCountSelectClause() {
		String result = "COUNT(DISTINCT PROCESS_INSTANCE.PIID)";
		return result;
	}

	/**
	 * construct the select clause for process instance query by reading the
	 * corresponding property file
	 * 
	 * @return the select clause for the query
	 */
	public static String getProcessInstanceSelectClause() {
		String SOURCE_METHOD = "getProcessInstanceSelectClause";
		LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD);

		/** 1. Default clause to be returned */
		String result = "DISTINCT PROCESS_INSTANCE.PIID";

		/** 2. If property file not loaded, load the property files into memory */
		if (!isLoaded) {
			loadPropertyFiles();
		}

		/** 3. Go through the properties and construct the select clause */
		Iterator iterator = processInstance.values().iterator();
		while (iterator.hasNext()) {
			String value = (String) iterator.next();
			result = result + "," + value;
		}

		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
		return result;

	}

	/**
	 * Given the HashMap, construct the where clause together with the skeleton
	 * defined in the property file. The key of the HashMap is used to identify
	 * the skeleton in the property file, the value of the HashMap is used to
	 * construct the actual where clause
	 * 
	 * @param parameters
	 *            the passed HashMap
	 * @return actual where clause
	 */
	public static String getProcessInstanceWhereClause(HashMap parameters) {
		String SOURCE_METHOD = "getProcessInstanceWhereClause";
		LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD,
				new Object[] { parameters });

		String result = "PROCESS_INSTANCE.STATE = PROCESS_INSTANCE.STATE.STATE_RUNNING";
		/** 1. If property files not loaded yet, load them */
		if (!isLoaded) {
			loadPropertyFiles();
		}

		/**
		 * 2. Go through the keys of the HashMap, retrieve the value for each
		 * key,and replace the query skeleton defined in the property file
		 */
		Iterator iterator = parameters.keySet().iterator();
		while (iterator.hasNext()) {
			/** 2.1 Get the key */
			String key = (String) iterator.next();
			/** 2.2 Get the query value passed */
			Object queryValue = parameters.get(key);
			/** 2.3 Get the query skeleton defined in the property file */
			String querySkeleton = processInstanceQuery.getProperty(key);
			/** 2.4 Replace the query skeleton with the query value */
			if (querySkeleton != null) {
				String trimedQuerySkeleton = querySkeleton.substring(1,
						querySkeleton.length() - 1);
				StringTokenizer tokens = new StringTokenizer(
						trimedQuerySkeleton, "?");
				result = result + " and (" + tokens.nextToken()
						+ queryValue.toString() + tokens.nextToken() + ")";

			}
		}

		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
		return result;
	}

	/**
	 * Given the HashMap, construct the where clause together with the skeleton
	 * defined in the property file. The key of the HashMap is used to identify
	 * the skeleton in the property file, the value of the HashMap is used to
	 * construct the actual where clause
	 * 
	 * @param parameters
	 *            the passed HashMap
	 * @return actual where clause
	 */
	public static String getActivityWorkitemWhereClause(String viewName,
			HashMap parameters) {

		String SOURCE_METHOD = "getActivityWorkitemWhereClause";
		//		LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD,
		//				new Object[] { parameters });
		//
		//		String result = "";
		//		/** 1. If property files not loaded yet, load them */
		//		if (!isLoaded) {
		//			loadPropertyFiles();
		//		}
		//
		//		/**
		//		 * 2. Go through the keys of the HashMap, retrieve the value for each
		//		 * key,and replace the query skeleton defined in the property file
		//		 */
		String result = " ";
		String filter = "";
		Iterator iterator = parameters.keySet().iterator();
		while (iterator.hasNext()) {
			/** 2.1 Get the key */
			String key = (String) iterator.next();
			/** 2.2 Get the query value passed */
			String queryValue = StaticMethod.nullObject2String(parameters
					.get(key));
			/** 2.3 Get the query skeleton defined in the property file */
			/** String querySkeleton = activityWorkitemQuery.getProperty(key); */
			/** 2.4 Replace the query skeleton with the query value */
			/** 2.4 Replace the query skeleton with the query value */
			if (!queryValue.equals("")) {
				if (!filter.equals("")) {
					filter = filter + ",";
				}
				if (queryValue.indexOf(",") != -1) {
					filter += "'" + queryValue.replaceAll(",", "','") + "'";
				} else {
					filter += "'" + queryValue + "'";
				}
			}
		}
		if (!filter.equals("")) {
			if (filter.indexOf(",") != -1) {
				result = " and " + viewName + " in (" + filter + ")";
			} else {
				result = " and " + viewName + " =" + filter + "";
			}
		}

		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
		return result;
	}

	/**
	 * Parse the QueryResultSet for Process Instance Query and return the List
	 * representation
	 * 
	 * @param resultSet
	 *            the Query Result
	 * @return the List representation
	 */
	public static List parseProcessInstanceQueryResult(QueryResultSet resultSet) {
		String SOURCE_METHOD = "parseProcessInstanceQueryResult";
		LOGGER
				.entering(SOURCE_CLASS, SOURCE_METHOD,
						new Object[] { resultSet });

		List result = new ArrayList();
		result = parseQueryResult(resultSet, processInstance.keySet());

		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, resultSet);
		return result;
	}

	/**
	 * Parse the QueryResultSet for Activity Workitem Query and return the List
	 * representation
	 * 
	 * @param resultSet
	 *            the Query Result
	 * @return the List representation
	 */
	public static List parseActivityWorkitemQueryResult(QueryResultSet resultSet) {
		String SOURCE_METHOD = "parseActivityWorkitemQueryResult";
		LOGGER
				.entering(SOURCE_CLASS, SOURCE_METHOD,
						new Object[] { resultSet });

		List result = new ArrayList();
		result = parseQueryResult(resultSet, activityWorkitem.keySet());

		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, resultSet);
		return result;
	}

	/**
	 * Parse the QueryResultSet for Task Query and return the List
	 * representation
	 * 
	 * @param resultSet
	 *            the Query Result
	 * @return the List representation
	 */
	public static List parseTaskQueryResult(QueryResultSet resultSet,
			String taskBoName, HashMap taskField) throws Exception {
		String SOURCE_METHOD = "parseTaskQueryResult";
		LOGGER
				.entering(SOURCE_CLASS, SOURCE_METHOD,
						new Object[] { resultSet });

		List result = new ArrayList();
		result=parseQueryResult(resultSet,taskInstance.values());
		result=transferResultToTaskBO(result,taskInstance,taskBoName,taskField);
		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, resultSet);
		return result;
	}

	/**
	 * Construct the select clause for activity workitem query by reading the
	 * corresponding property file
	 * 
	 * @return the select clause for the query
	 */
	public static String getActivityWorkitemSelectClause() {
		String SOURCE_METHOD = "getActivityWorkitemSelectClause";
		LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD);

		/** 1. Default clause to be returned */
		String result = "DISTINCT ACTIVITY.AIID";

		/**
		 * 2. If property file not loaded yet, load the property files into
		 * memory
		 */
		if (!isLoaded) {
			loadPropertyFiles();
		}

		/** 3. go through the properties and construct the select clause */
		Iterator iterator = activityWorkitem.values().iterator();
		while (iterator.hasNext()) {
			String value = (String) iterator.next();
			result = result + "," + value;
		}

		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
		return result;
	}

	/**
	 * Load the property files
	 */
	private static void loadPropertyFiles() {
		String SOURCE_METHOD = "loadPropertyFiles()";
		LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD);

		/** 1. Load the ProcessInstance property file */
		loadPropertyFile(processInstance, processInstancePropertyFile);
		loadPropertyFile(processInstanceQuery, processInstanceQueryPropertyFile);

		/** 2. Load the Activity Workitem property file */
		loadPropertyFile(activityWorkitem, activityWorkitemPropertyFile);
		loadPropertyFile(activityWorkitemQuery,
				activityWorkitemQueryPropertyFile);

		loadPropertyFile(taskInstance, taskInstancePropertyFile);
		loadPropertyFile(taskModel, taskModelPropertyFile);

		/** 3. Set the isLoaded variable to true */
		isLoaded = true;

		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
	}

	/**
	 * load the specific property file and save the properties into Properties
	 * 
	 * @param properties
	 *            the Properties which is used to save the properties
	 * @param propertyFile
	 *            the property file
	 */
	private static void loadPropertyFile(Properties properties,
			String propertyFile) {
		String SOURCE_METHOD = "loadPropertyFile";
		String ERROR_MESSAGE = "";
		LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[] { properties,
				propertyFile });

		try {
			InputStream inputStream = WPSQueryHelper.class
					.getResourceAsStream(propertyFile);
			properties.load(inputStream);
		} catch (Exception e) {
			LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
					ERROR_MESSAGE + e.getCause());
		}

		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);

	}

	/**
	 * Given the QueryResultSet, retrieve the results and parse it simple Object
	 * type. Then combine them with the provided keys and return the List of
	 * HashMap
	 * 
	 * @param resultSet
	 *            the QueryResultSet
	 * @param keys
	 *            the provided keys
	 * @return List representation of the Query Result
	 */
	private static List parseQueryResult(QueryResultSet resultSet,
			Collection keys) {
		String SOURCE_METHOD = "parseQueryResult";
		LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[] { resultSet,
				keys });

		/** 1. Prepare for method call */
		List result = new ArrayList();
		List keyList = new ArrayList();
		keyList.addAll(keys);

		/** 2. Go through the ResultSet and retrieve the result for each column */
		while (resultSet.next()) {
			HashMap map = new HashMap();
			for (int i = 0; i < keyList.size(); i++) {
				/**
				 * skip the first column, which is not defined in the property
				 * file
				 */
				short type = resultSet.getColumnType(i + 2);
				String name = (String) keyList.get(i);
				Object value = resultSet.getObject(i + 2);
				if (value == null) {
					continue;
				}
				LOGGER.log(Level.FINER, "key name is " + name);
				if (type == QueryColumnInfo.TYPE_ID) {
					map.put(name, resultSet.getOID(i + 2).toString());

				} else if (type == QueryColumnInfo.TYPE_TIMESTAMP) {
					map.put(name, resultSet.getTimestamp(i + 2).getTime());
				} else {
					map.put(name, value);
				}
			}

			result.add(map);
		}

		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
		return result;
	}

	/**
	 * Given the QueryResultSet, retrieve the results and parse it simple Object
	 * type. Then combine them with the provided keys and return the List of
	 * HashMap
	 * 
	 * @param resultSet
	 *            the QueryResultSet
	 * @param keys
	 *            the provided keys
	 * @return List representation of the Query Result
	 */
	private static List transferResultToTaskBO(List resultList,
			Properties taskFields, String taskBoName, HashMap boField)
			throws Exception {
		List taskList = new ArrayList();
		for (int i = 0; i < resultList.size(); i++) {
			HashMap taskMap = new HashMap();
			HashMap tempMap = (HashMap) resultList.get(i);//查询结果
			String tkiid = (String) tempMap.get(activityInstanceId);
			com.ibm.task.api.ClientObjectWrapper variable = humanTaskManager
					.getInputMessage(tkiid);
			DataObject inputMessages = (DataObject) variable.getObject();
			DataObject taskDataObject = inputMessages.getDataObject(taskBoName);//获取taskBO对象
			if (!tempMap.isEmpty()) {
				Iterator iterator = boField.keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next().toString();
					String taskField = taskFields.getProperty(key);
					if (taskField != null && !taskField.equalsIgnoreCase("")) {
						//属于流程数据，从流程库里获取
						taskMap.put(key, tempMap.get(taskField));
					} else if (taskDataObject != null && !boField.isEmpty()) {
						//属于业务数据，从task对象中获取
						Property taskProperty = taskDataObject
								.getContainmentProperty().getType()
								.getProperty(key);
						//判断在task对象中是否存在该属性,存在则取值
						if (taskProperty != null
								&& !taskProperty.getName().equalsIgnoreCase("")) {
							Object object = taskDataObject.get(key);
							taskMap.put(key, object);
						}
					} else {
						continue;
					}
				}//while

			}//if
			taskList.add(taskMap);
		}//for
		return taskList;
	}

	/**
	 * Construct the select clause for activity workitem query by reading the
	 * corresponding property file
	 * 
	 * @return the select clause for the query
	 */
	public static String getTaskSelectClause() {
		String SOURCE_METHOD = "getActivityWorkitemSelectClause";
		LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD);

		/** 1. Default clause to be returned */
		String result = " DISTINCT TASK.TKIID,";

		/**
		 * 2. If property file not loaded yet, load the property files into
		 * memory
		 */
		if (!isLoaded) {
			loadPropertyFiles();
		}

		/** 3. go through the properties and construct the select clause */
		Iterator iterator = taskInstance.values().iterator();
		String temp = "";
		while (iterator.hasNext()) {
			String value = (String) iterator.next();
			if (!temp.equals("")) {
				temp = temp + ",";
			}
			temp = temp + value;
			//result = result + "," + value;
		}
		result = result + temp;
		LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
		return result;
	}

	public static String getPropertyValue(Properties properties,String key) {
		String value="";
		if (!isLoaded) {
			loadPropertyFiles();
		}
		value=properties.getProperty(key);
		return value;
	}
	/**
	 * @return Returns the taskModelPropertyFile.
	 */
	public  String getTaskModelPropertyFile() {
		return taskModelPropertyFile;
	}
	/**
	 * @param taskModelPropertyFile The taskModelPropertyFile to set.
	 */
	public  void setTaskModelPropertyFile(String taskModelPropertyFile) {
		WPSQueryHelper.taskModelPropertyFile = taskModelPropertyFile;
	}
}
