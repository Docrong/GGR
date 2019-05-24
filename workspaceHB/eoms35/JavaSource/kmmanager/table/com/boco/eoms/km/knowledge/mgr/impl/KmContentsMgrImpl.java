package com.boco.eoms.km.knowledge.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.km.cache.mgr.KmTableCacheMgr;
import com.boco.eoms.km.core.ElementTranslator;
import com.boco.eoms.km.core.bizlets.DataQueryExt;
import com.boco.eoms.km.core.crimson.util.XmlUtil;
import com.boco.eoms.km.core.dataservice.database.RelationalDB;
import com.boco.eoms.km.core.dataservice.map.EntityMap;
import com.boco.eoms.km.core.dataservice.map.FieldMap;
import com.boco.eoms.km.core.dataservice.sql.CopySQLBuilder;
import com.boco.eoms.km.core.dataservice.sql.InsertSQLBuilder;
import com.boco.eoms.km.core.dataservice.sql.QuerySQLBuilder;
import com.boco.eoms.km.core.dataservice.sql.UpdateSQLBuilder;
import com.boco.eoms.km.knowledge.dao.KmContentsDao;
import com.boco.eoms.km.knowledge.dao.jdbc.KmContentsDaoJdbc;
import com.boco.eoms.km.knowledge.mgr.KmContentsMgr;
import com.boco.eoms.km.knowledge.model.KmContents;
import com.boco.eoms.km.log.mgr.KmOperateDateLogMgr;
import com.boco.eoms.km.log.mgr.KmOperateLogMgr;
import com.boco.eoms.km.servlet.context.RequestContext;
import com.boco.eoms.km.statics.util.StatisticMethod;
import com.boco.eoms.km.util.DateTime;

/**
 * <p> Title:知识内容管理 Mgr </p>
 * <p> Description:知识内容管理 </p>
 * <p> Tue Mar 24 10:32:12 CST 2009 </p>
 * 
 * @author ZHANGXB
 * @version 1.0
 * 
 */
public class KmContentsMgrImpl implements KmContentsMgr {
	
	/**
	 * 知识内容 Hibernate DAO
	 */
	private KmContentsDao kmContentsDao;

	public KmContentsDao getKmContentsDao() {
		return kmContentsDao;
	}

	public void setKmContentsDao(KmContentsDao kmContentsDao) {
		this.kmContentsDao = kmContentsDao;
	}

	/**
	 * 知识内容 Jdbc DAO
	 */
	private KmContentsDaoJdbc kmContentsDaoJdbc;

	public KmContentsDaoJdbc getKmContentsDaoJdbc() {
		return kmContentsDaoJdbc;
	}

	public void setKmContentsDaoJdbc(KmContentsDaoJdbc kmContentsDaoJdbc) {
		this.kmContentsDaoJdbc = kmContentsDaoJdbc;
	}
	
	/**
	 * 知识模型缓存
	 */
	private KmTableCacheMgr kmTableCacheMgr;

	public KmTableCacheMgr getKmTableCacheMgr() {
		return kmTableCacheMgr;
	}

	public void setKmTableCacheMgr(KmTableCacheMgr kmTableCacheMgr) {
		this.kmTableCacheMgr = kmTableCacheMgr;
	}
	
	/**
	 * 操作日记录 DAO
	 */
	private KmOperateDateLogMgr kmOperateDateLogMgr;

	public KmOperateDateLogMgr getKmOperateDateLogMgr() {
		return kmOperateDateLogMgr;
	}

	public void setKmOperateDateLogMgr(KmOperateDateLogMgr kmOperateDateLogMgr) {
		this.kmOperateDateLogMgr = kmOperateDateLogMgr;
	}
	
	/**
	 * 操作记录表
	 */
	private KmOperateLogMgr kmOperateLogMgr;

	public KmOperateLogMgr getKmOperateLogMgr() {
		return kmOperateLogMgr;
	}

	public void setKmOperateLogMgr(KmOperateLogMgr kmOperateLogMgr) {
		this.kmOperateLogMgr = kmOperateLogMgr;
	}

	/**
	 * 根据知识主键、知识模型、知识分类 查询知识内容
	 * 
	 * @param ID 知识ID
	 * @param TABLE_ID 知识模型ID
	 * @param THEME_ID 知识分类ID
	 * @return Map 知识内容键-值映射
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public Map getKmContents(String ID, String TABLE_ID, String THEME_ID) {
		Map map = new HashMap();
		try {
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByTableId(TABLE_ID);
			if(entityMap == null){
				return map;
			}

			// 定义：模型字段列表
			Iterator iterator = entityMap.fetchFieldList().iterator();
			boolean first = true;
			
			// 创建：查询知识内容的 SQL
			StringBuffer selectSQL = new StringBuffer("select ");
			while (iterator.hasNext()) {
				FieldMap fieldMap = (FieldMap) iterator.next();
				if (first) {
					selectSQL.append(fieldMap.getColumnName());
					first = false;
				} 
				else {
					selectSQL.append(',');
					selectSQL.append(fieldMap.getColumnName());
				}
			}
			selectSQL.append(" from ");
			selectSQL.append(entityMap.getTableName());
			selectSQL.append(" where ID=?");
			
			// 创建：数据库方言解析器
			RelationalDB datebase = RelationalDB.getInstance();
			int idType = entityMap.fetchFieldMap("ID").getColumnType();
			
			// 定义：查询参数值
			List valueList = new ArrayList();
			valueList.add(datebase.convStringToObject(ID, idType, ""));

			// 定义：查询参数类型
			List typeList = new ArrayList();
			typeList.add(new Integer(idType));
			
			// 查询：知识内容
			map = kmContentsDaoJdbc.view(selectSQL.toString(), valueList, typeList);
			
			// 判断：知识ID对应的知识内容存在，则更新知识内容的阅读次数
			if(map.size() >0){
				// 更新：知识阅读次数+1
				StringBuffer updateSQL = new StringBuffer("update ");
				updateSQL.append(entityMap.getTableName());
				updateSQL.append(" set READ_COUNT=READ_COUNT+1 where ID=?");
				kmContentsDaoJdbc.update(updateSQL.toString(), valueList, typeList);

				// 更新：根据知识主键更新知识中间表对应的知识记录的阅读次数
				kmContentsDao.updateKmContentsReadCountByContentId(ID);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据知识主键、知识模型、知识分类、知识版本号 查询知识内容
	 * 
	 * @param ID 知识ID
	 * @param TABLE_ID 知识模型ID
	 * @param THEME_ID 知识分类ID
	 * @return Map 知识内容键-值映射
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public Map getKmContentsHistory(String ID, String TABLE_ID, String THEME_ID, final String VERSION){
		Map map = new HashMap();
		try {		
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByTableId(TABLE_ID);
			if(entityMap == null){
				return map;
			}

			// 定义：模型字段列表
			Iterator iterator = entityMap.fetchFieldList().iterator();
			boolean first = true;
			
			// 创建：查询知识内容的 SQL
			StringBuffer selectSQL = new StringBuffer("select ");
			while (iterator.hasNext()) {
				FieldMap fieldMap = (FieldMap) iterator.next();
				if (first) {
					selectSQL.append(fieldMap.getColumnName());
					first = false;
				} 
				else {
					selectSQL.append(',');
					selectSQL.append(fieldMap.getColumnName());
				}
			}
			selectSQL.append(" from ");
			selectSQL.append(entityMap.getTableName());
			selectSQL.append("_HIS ");
			selectSQL.append(" where ID = ? and VERSION = ?");

			// 创建：数据库方言解析器
			RelationalDB datebase = RelationalDB.getInstance();
			int idType = entityMap.fetchFieldMap("ID").getColumnType();
			
			// 定义：查询参数值
			List valueList = new ArrayList();
			valueList.add(datebase.convStringToObject(ID, idType, ""));

			// 定义：查询参数类型
			List typeList = new ArrayList();
			typeList.add(new Integer(idType));
			
			// 查询：知识内容
			map = kmContentsDaoJdbc.view(selectSQL.toString(), valueList, typeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 根据知识主键、知识模型、知识分类 删除知识内容
	 * 
	 * @param ID 知识ID
	 * @param TABLE_ID 知识模型ID
	 * @param THEME_ID 知识分类ID
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public void removeKmContents(String ID, String TABLE_ID, String THEME_ID) {
		try {
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByTableId(TABLE_ID);
			if(entityMap == null){
				return;
			}
			
			// 创建：知识修改时间
			String MODIFY_TIME = StaticMethod.getLocalString();

			// 创建：删除知识内容的 SQL
			// 知识的状态：1草稿、2有效、3失效、4删除、5主管审核、6审核角色审核、7返回主管审核
			StringBuffer sql = new StringBuffer("update ");
			sql.append(entityMap.getTableName());
			sql.append(" set CONTENT_STATUS=4, MODIFY_TIME=? where ID =?");	

			// 创建：数据库方言解析器
			RelationalDB datebase = RelationalDB.getInstance();
			int idType = entityMap.fetchFieldMap("ID").getColumnType();
			int modifyTimeType = entityMap.fetchFieldMap("MODIFY_TIME").getColumnType();
			
			// 定义：查询参数值
			List valueList = new ArrayList();
			valueList.add(datebase.convStringToObject(MODIFY_TIME, modifyTimeType, ""));
			valueList.add(datebase.convStringToObject(ID, idType, ""));
			
			// 定义：查询参数类型
			List dataTypeList = new ArrayList();
			dataTypeList.add(new Integer(modifyTimeType));
			dataTypeList.add(new Integer(idType));

			// 删除：根据知识主键删知识表对应的知识记录
			Integer upsize = kmContentsDaoJdbc.update(sql.toString(), valueList, dataTypeList);
			
			if(upsize.intValue() >0){
				// 删除：根据知识主键删知识中间表对应的知识记录
				kmContentsDao.removeKmContentsByContentId(ID);					
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 根据知识模型所属分类 THEME_ID 查询知识条目列表(知识列表专用)
	 * 
	 * @param THEME_ID 知识模型所属分类
	 * @param curPage 当前所处的页码
	 * @param pageSize 每页显示最大记录条数
	 * 
	 * @return Map 知识内容对应的Map对象<字段名, 字段值>
	 */	
	public Map searchKmContentss(String THEME_ID, final boolean isLeaf, Integer curPage, Integer pageSize) {
		HashMap map = new HashMap();
		try {
			// 1、读取：知识的模型定义
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByThemeId(THEME_ID);
			if(entityMap == null){
				return map;
			}

			// 2、创建：数据库放言解析器
			RelationalDB datebase = RelationalDB.getInstance();
			int themeIdType = entityMap.fetchFieldMap("THEME_ID").getColumnType();
			
			// 3、定义：查询参数
			List valueList = new ArrayList();

			// 4、定义：查询参数类型
			List dataTypeList = new ArrayList();
			dataTypeList.add(new Integer(themeIdType));

			// 5、设置：查询 SQL 及查询参数值
			StringBuffer couSql = new StringBuffer("select count(ID) from ");
			couSql.append(entityMap.getTableName());

			StringBuffer selSql = new StringBuffer("select ");
			Iterator iterator = entityMap.fetchFieldList().iterator();
			boolean first = true;
			while (iterator.hasNext()) {
				FieldMap fieldMap = (FieldMap) iterator.next();
				if(first){
					selSql.append(fieldMap.getColumnName());
					first = false;
				}else{
					selSql.append(',');
					selSql.append(fieldMap.getColumnName());
				}
			}
			selSql.append(" from ");
			selSql.append(entityMap.getTableName());
			
			if(isLeaf){
				couSql.append(" where THEME_ID = ? and CONTENT_STATUS != 1 and CONTENT_STATUS != 4");
				selSql.append(" where THEME_ID = ? and CONTENT_STATUS != 1 and CONTENT_STATUS != 4");
				valueList.add(datebase.convStringToObject(THEME_ID, themeIdType, ""));
			}
			else{
				couSql.append(" where THEME_ID like ? and CONTENT_STATUS != 1 and CONTENT_STATUS != 4");
				selSql.append(" where THEME_ID like ? and CONTENT_STATUS != 1 and CONTENT_STATUS != 4");
				valueList.add(datebase.convStringToObject(THEME_ID+'%', themeIdType, ""));
			}			
			selSql.append(" order by CREATE_TIME desc");

			// 6、执行：查询对应的知识条目
			Integer total = kmContentsDaoJdbc.count(couSql.toString(), valueList, dataTypeList);
			List result = kmContentsDaoJdbc.list(selSql.toString(), valueList, dataTypeList, curPage, pageSize);

			// 7、设置：返回的数据
			map.put("total", total);
			map.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	
	/**
	 * (知识排行-知识增长-数量)     点击数量进入此列表
	 * 
	 * 根据知识模型所属分类 THEME_ID 查询知识条目列表(知识列表专用)
	 * 
	 * @param THEME_ID 知识模型所属分类
	 * @param curPage 当前所处的页码
	 * @param pageSize 每页显示最大记录条数
	 * 
	 * @return Map 知识内容对应的Map对象<字段名, 字段值>
	 */	
	public Map searchKmContentssLib(String THEME_ID, final boolean isLeaf, Integer curPage, Integer pageSize,String startDate,String endDate) {
		HashMap map = new HashMap();
		try {
			// 1、读取：知识的模型定义
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByThemeId(THEME_ID);
			if(entityMap == null){
				return map;
			}
			// 2、创建：数据库放言解析器
			RelationalDB datebase = RelationalDB.getInstance();
			int themeIdType = entityMap.fetchFieldMap("THEME_ID").getColumnType();
			
			// 3、定义：查询参数
			List valueList = new ArrayList();

			// 4、定义：查询参数类型
			List dataTypeList = new ArrayList();
			dataTypeList.add(new Integer(themeIdType));

			// 5、设置：查询 SQL 及查询参数值
			StringBuffer couSql = new StringBuffer("select count(ID) from ");
			couSql.append(entityMap.getTableName());

			StringBuffer selSql = new StringBuffer("select ");
			Iterator iterator = entityMap.fetchFieldList().iterator();
			boolean first = true;
			while (iterator.hasNext()) {
				FieldMap fieldMap = (FieldMap) iterator.next();
				if(first){
					selSql.append(fieldMap.getColumnName());
					first = false;
				}else{
					selSql.append(',');
					selSql.append(fieldMap.getColumnName());
				}
			}
			selSql.append(" from ");
			selSql.append(entityMap.getTableName());
			
			if(isLeaf){
				couSql.append(" where THEME_ID = ? and CONTENT_STATUS != 1 and CONTENT_STATUS != 4");
				selSql.append(" where THEME_ID = ? and CONTENT_STATUS != 1 and CONTENT_STATUS != 4");
				valueList.add(datebase.convStringToObject(THEME_ID, themeIdType, ""));
			}
			else{
				couSql.append(" where THEME_ID like ? and CONTENT_STATUS != 1 and CONTENT_STATUS != 4");
				
		  		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
		  			Date newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
		 			Date newEndDate = StatisticMethod.stringToDate(endDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
					
		 			selSql.append(" where THEME_ID like ? and CONTENT_STATUS != 1 and CONTENT_STATUS != 4 and create_time BETWEEN to_date('"+startDate+"', 'yyyy-MM-dd HH24:mi:ss') AND to_date('"+endDate+"', 'yyyy-MM-dd HH24:mi:ss')");//----------
		 		}
		 		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
		 			String newStartDate = startDate + " 00:00:00";
		 			String newEndDate = endDate + " 00:00:00";
					
		 			selSql.append(" where THEME_ID like ? and CONTENT_STATUS != 1 and CONTENT_STATUS != 4 and create_time BETWEEN to_date('"+startDate+"', 'yyyy-MM-dd HH24:mi:ss') AND to_date('"+endDate+"', 'yyyy-MM-dd HH24:mi:ss')");//----------
		 		} 
				
				valueList.add(datebase.convStringToObject(THEME_ID+'%', themeIdType, ""));
			}			
			selSql.append(" order by CREATE_TIME desc");

			// 6、执行：查询对应的知识条目
			Integer total = kmContentsDaoJdbc.count(couSql.toString(), valueList, dataTypeList);
			List result = kmContentsDaoJdbc.listLib(selSql.toString(), valueList, dataTypeList, curPage, pageSize,startDate,startDate);

			// 7、设置：返回的数据
			map.put("total", total);
			map.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	
	/**
	 * 根据知识模型所属分类 THEME_ID 查询某状态知识条目列表
	 * 
	 * @param THEME_ID 知识模型所属分类
	 * @param curPage 当前所处的页码
	 * @param pageSize 每页显示最大记录条数
	 * 
	 * @return Map 知识内容对应的Map对象<字段名, 字段值>
	 */	
	public Map searchKmContentss(String THEME_ID, final boolean isLeaf, String state,String userId, Integer curPage, Integer pageSize) {
		HashMap map = new HashMap();
		try {
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByThemeId(THEME_ID);
			if(entityMap == null){
				return map;
			}
			
			// 2、设置：查询条件
			RequestContext reqContext = new RequestContext();
			reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/value", THEME_ID);			
			if(!isLeaf){
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/operator", "like");
				reqContext.setEntityValue2("QueryCond/THEME_ID/criteria/likeRule", "begin");
			}
			//CONTENT_STATUS（知识状态）：1-草稿，2-有效，3-失效，4-删除
			reqContext.setEntityValue2("QueryCond/CONTENT_STATUS/criteria/value", state);
			reqContext.setEntityValue2("QueryCond/CONTENT_STATUS/criteria/operator", "=");
			reqContext.setEntityValue2("QueryCond/_order/col1/field", "CREATE_TIME");
			reqContext.setEntityValue2("QueryCond/CREATE_USER/criteria/value", userId);	
			// 3、转换：对查询条件进行格式转换
	        Element queryCond = DataQueryExt.transformCriteriaNode(reqContext.getQueryCond());

	        // 4、解析：查询条件
			ElementTranslator eleTranslator = new ElementTranslator(entityMap, queryCond);
			eleTranslator.parseExpandAll();			
			List fieldNodeList = eleTranslator.getFieldNodeList(); // 读取：SELECT 字段			
			List criteriaList = eleTranslator.getCriteriaNodeList(); // 读取：WHERE 条件			
			String orderBy = eleTranslator.getOrderByString(); // 读取：ORDER 条件
			
			// 5、定义：参数和参数类型列表
			List valueList = new ArrayList();
			List dataTypeList = new ArrayList();

			// 6、处理：生成 Select SQL
			QuerySQLBuilder sqlBuilder = new QuerySQLBuilder(entityMap, fieldNodeList, criteriaList, orderBy);
			String[] sql = sqlBuilder.buildSQL(valueList, dataTypeList);

			// 7、执行：查询对应的知识条目
			Integer total = kmContentsDaoJdbc.count(sql[0], valueList, dataTypeList);
			List result = kmContentsDaoJdbc.list(sql[1], valueList, dataTypeList, curPage, pageSize);

			// 8、设置：返回的数据
			map.put("total", total);
			map.put("result", result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 新增保存知识内容
	 * 
	 * @param reqContext 知识内容
	 */
	public void saveKmContents(RequestContext reqContext) {
		try {
			// 设置：知识创建时间
			java.util.Date currentDate = new java.util.Date();
			String CREATE_TIME = StaticMethod.date2String(currentDate);
			reqContext.setEntityValue2("TableInfo/CREATE_TIME", CREATE_TIME);

			// 读取：要插入数据库的数据
			Element tableInfo = reqContext.getTableInfo();

			// 读取：知识的模型定义
			String TABLE_ID = XmlUtil.getNodeValue(tableInfo, "TableInfo/TABLE_ID");

			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByTableId(TABLE_ID);
			if(entityMap == null){
				return;
			}

			// 解析：要存入数据库的数据实体
			ElementTranslator eleTranslator = new ElementTranslator(entityMap, tableInfo);
			List fieldNodeList = eleTranslator.parseInsert();
			
			// 定义：参数值和参数类型列表
			List valueList = new ArrayList();
			List dataTypeList = new ArrayList();

			// 生成：Insert SQL
			InsertSQLBuilder sqlBuilder = new InsertSQLBuilder(entityMap, fieldNodeList);
			String sql = sqlBuilder.buildSQL(valueList, dataTypeList);

			// 执行：将新增的知识插入对应的表
			kmContentsDaoJdbc.insert(sql, valueList, dataTypeList);

			// 读取：要保存到固定表 KM_CONTENTS中 的字段
			String ID = XmlUtil.getNodeValue(tableInfo, "TableInfo/ID");
			String THEME_ID = XmlUtil.getNodeValue(tableInfo, "TableInfo/THEME_ID");
			String CONTENT_TITLE = XmlUtil.getNodeValue(tableInfo, "TableInfo/CONTENT_TITLE");
			String CONTENT_KEYS = XmlUtil.getNodeValue(tableInfo, "TableInfo/CONTENT_KEYS");
			String CREATE_USER = XmlUtil.getNodeValue(tableInfo, "TableInfo/CREATE_USER");
			String CREATE_DEPT = XmlUtil.getNodeValue(tableInfo, "TableInfo/CREATE_DEPT");
			String CONTENT_STATUS = StaticMethod.null2String(XmlUtil.getNodeValue(tableInfo, "TableInfo/CONTENT_STATUS"));

			// 保存：将新增的知识保存到固定的 KM_CONTENTS 表
			KmContents kmContents = new KmContents();
			kmContents.setContentId(ID);
			kmContents.setTableId(TABLE_ID);
			kmContents.setThemeId(THEME_ID);
			kmContents.setContentTitle(CONTENT_TITLE);
			kmContents.setContentKeys(CONTENT_KEYS);
			kmContents.setCreateUser(CREATE_USER);
			kmContents.setContentStatus(CONTENT_STATUS);
			kmContents.setCreateTime(currentDate);
			kmContents.setCreateDept(CREATE_DEPT);
			kmContentsDao.saveKmContents(kmContents);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 修改知识条目
	 * 
	 * @param reqContext 知识条目内容，注意：reqContext 中必须包含 ID、TABLE_ID、
	 * 
	 */
	public void updateKmContents(RequestContext reqContext) {
		try {
			// 1、设置：知识修改时间
			String MODIFY_TIME = StaticMethod.getLocalString();
			reqContext.setEntityValue2("TableInfo/MODIFY_TIME", MODIFY_TIME);
			
			// 2、读取：要插入数据库的数据
			Element tableInfo = reqContext.getTableInfo();
			tableInfo.setAttribute("field", "SET");
			
			// 3、读取：知识的主键
			String ID = XmlUtil.getNodeValue(tableInfo, "TableInfo/ID");

			// 4、读取：知识的模型定义
			String TABLE_ID = XmlUtil.getNodeValue(tableInfo, "TableInfo/TABLE_ID");
			
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByTableId(TABLE_ID);
			if(entityMap == null){
				return;
			}

			// 5、解析：要存入数据库的数据实体
			ElementTranslator eleTranslator = new ElementTranslator(entityMap, tableInfo);			
			List whereFieldList = eleTranslator.getKeyCriteriaNodeList(true); // 读取：WHERE 条件
			eleTranslator.parseUpdate(true);			
			List updateFieldList = eleTranslator.getFieldNodeList(); // 读取：UPDATE 字段
			
			// 6、定义：参数和参数类型列表
			List valueList = new ArrayList();
			List dataTypeList = new ArrayList();

			// 7、处理：生成 Copy SQL 位置不能调换，必须在处理：生成 Update SQL前
			CopySQLBuilder copyBuilder = new CopySQLBuilder(entityMap);
			String copySql = copyBuilder.buildSQL(ID);
			
			// 8、执行：备份知识条目 位置不能调换，必须在处理：生成 Update SQL前
			kmContentsDaoJdbc.insert(copySql, valueList, dataTypeList);

			// 9、处理：生成 Update SQL
			UpdateSQLBuilder sqlBuilder = new UpdateSQLBuilder(entityMap, updateFieldList, whereFieldList);
			String updateSql = sqlBuilder.buildSQL(valueList, dataTypeList);

			// 10、执行：更新知识条目
			kmContentsDaoJdbc.update(updateSql, valueList, dataTypeList);
			
			// 读取：要保存到固定表 KM_CONTENTS中 的字段
			String CONTENT_TITLE = XmlUtil.getNodeValue(tableInfo, "TableInfo/CONTENT_TITLE");
			String CONTENT_KEYS = XmlUtil.getNodeValue(tableInfo, "TableInfo/CONTENT_KEYS");				
			String CONTENT_STATUS = StaticMethod.null2String(XmlUtil.getNodeValue(tableInfo, "TableInfo/CONTENT_STATUS"));
			
			// 11.2、查询中间表是否有对应的知识记录
			KmContents kmContents = kmContentsDao.getKmContentsByContentId(ID);

			if(kmContents.getId() != null && !kmContents.getId().equals("")){	
				// 11.3、更新：将新增的知识保存到固定的 KM_CONTENTS 表
				kmContents.setContentTitle(CONTENT_TITLE);
				kmContents.setContentKeys(CONTENT_KEYS);
				kmContents.setContentStatus(CONTENT_STATUS);
				kmContentsDao.saveKmContents(kmContents);	
			}
			else{
				// 读取：要保存到固定表 KM_CONTENTS中 的字段
				String THEME_ID = XmlUtil.getNodeValue(tableInfo, "TableInfo/THEME_ID");
				String CREATE_USER = XmlUtil.getNodeValue(tableInfo, "TableInfo/CREATE_USER");
				String CREATE_DEPT = XmlUtil.getNodeValue(tableInfo, "TableInfo/CREATE_DEPT");
				String CREATE_TIME = XmlUtil.getNodeValue(tableInfo, "TableInfo/CREATE_TIME");

				// 保存：将新增的知识保存到固定的 KM_CONTENTS 表
				kmContents.setContentId(ID);
				kmContents.setTableId(TABLE_ID);
				kmContents.setThemeId(THEME_ID);
				kmContents.setContentTitle(CONTENT_TITLE);
				kmContents.setContentKeys(CONTENT_KEYS);
				kmContents.setCreateUser(CREATE_USER);
				kmContents.setContentStatus(CONTENT_STATUS);
				kmContents.setCreateTime(DateTime.formatLocalToDate(CREATE_TIME));
				kmContents.setCreateDept(CREATE_DEPT);
				kmContentsDao.saveKmContents(kmContents);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 修改知识条目(用于审核修改状态)
	 * 
	 * @param reqContext 知识条目内容，注意：reqContext 中必须包含 ID、TABLE_ID、
	 * 
	 */
	public void updateKmContentsForAudit(RequestContext reqContext) {
		try {
			// 1、设置：知识修改时间
			String MODIFY_TIME = StaticMethod.getLocalString();
			reqContext.setEntityValue2("TableInfo/MODIFY_TIME", MODIFY_TIME);
			
			// 2、读取：要插入数据库的数据
			Element tableInfo = reqContext.getTableInfo();
			tableInfo.setAttribute("field", "SET");

			// 3、读取：知识的主键
			String ID = XmlUtil.getNodeValue(tableInfo, "TableInfo/ID");

			// 4、读取：知识的模型定义
			String TABLE_ID = XmlUtil.getNodeValue(tableInfo, "TableInfo/TABLE_ID");
			
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByTableId(TABLE_ID);
			if(entityMap == null){
				return;
			}
			
			// 5、解析：要存入数据库的数据实体
			ElementTranslator eleTranslator = new ElementTranslator(entityMap, tableInfo);			
			List criteriaList = eleTranslator.getKeyCriteriaNodeList(true); // 读取：WHERE 条件
			eleTranslator.parseUpdate(true);			
			List fieldNodeList = eleTranslator.getFieldNodeList(); // 读取：UPDATE 字段

			// 6、定义：参数和参数类型列表
			List valueList = new ArrayList();
			List dataTypeList = new ArrayList();

			// 7、处理：生成 Update SQL
			UpdateSQLBuilder sqlBuilder = new UpdateSQLBuilder(entityMap, fieldNodeList, criteriaList);
			String sql = sqlBuilder.buildSQL(valueList, dataTypeList);

			// 8、执行：更新知识条目
			kmContentsDaoJdbc.update(sql, valueList, dataTypeList);

			// 9.1、读取：将修改的知识保存到固定的 KM_CONTENTS 表
			String CONTENT_TITLE = XmlUtil.getNodeValue(tableInfo, "TableInfo/CONTENT_TITLE");
			String CONTENT_KEYS = XmlUtil.getNodeValue(tableInfo, "TableInfo/CONTENT_KEYS");				
			String CONTENT_STATUS = StaticMethod.null2String(XmlUtil.getNodeValue(tableInfo, "TableInfo/CONTENT_STATUS"));

			// 9.2、查询中间表是否有对应的知识记录
			KmContents kmContents = kmContentsDao.getKmContentsByContentId(ID);

			if(kmContents.getId() != null && !kmContents.getId().equals("")){	
				// 11.3、更新：将新增的知识保存到固定的 KM_CONTENTS 表
				kmContents.setContentTitle(CONTENT_TITLE);
				kmContents.setContentKeys(CONTENT_KEYS);
				kmContents.setContentStatus(CONTENT_STATUS);
				kmContentsDao.saveKmContents(kmContents);	
			}
			else{
				// 读取：要保存到固定表 KM_CONTENTS中 的字段
				String THEME_ID = XmlUtil.getNodeValue(tableInfo, "TableInfo/THEME_ID");
				String CREATE_USER = XmlUtil.getNodeValue(tableInfo, "TableInfo/CREATE_USER");
				String CREATE_DEPT = XmlUtil.getNodeValue(tableInfo, "TableInfo/CREATE_DEPT");
				String CREATE_TIME = XmlUtil.getNodeValue(tableInfo, "TableInfo/CREATE_TIME");

				// 保存：将新增的知识保存到固定的 KM_CONTENTS 表
				kmContents.setContentId(ID);
				kmContents.setTableId(TABLE_ID);
				kmContents.setThemeId(THEME_ID);
				kmContents.setContentTitle(CONTENT_TITLE);
				kmContents.setContentKeys(CONTENT_KEYS);
				kmContents.setCreateUser(CREATE_USER);
				kmContents.setContentStatus(CONTENT_STATUS);
				kmContents.setCreateTime(DateTime.formatLocalToDate(CREATE_TIME));
				kmContents.setCreateDept(CREATE_DEPT);
				kmContentsDao.saveKmContents(kmContents);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 根据知识条目的 ID、TABLE_ID、THEME_ID 查询知识条目列表
	 * 
	 * @param ID 知识的ID
	 * @param TABLE_ID 知识所属模型ID
	 * @param THEME_ID 知识模型所属分类ID
	 */	
	public Map searchKmContentss(RequestContext reqContext, Integer curPage, Integer pageSize) {
		HashMap map = new HashMap();
		try {
			// 读取：要插入数据库的数据
			Element inQueryCond = reqContext.getQueryCond();
			
			// 读取：知识的模型定义
			String TABLE_ID = XmlUtil.getNodeValue(inQueryCond, "QueryCond/TABLE_ID/criteria/value");
			
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByTableId(TABLE_ID);
			if(entityMap == null){
				return map;
			}
			
			//CONTENT_STATUS（知识状态）：1-草稿，2-有效，3-失效，4-删除
			reqContext.setEntityValue2("QueryCond/CONTENT_STATUS/criteria/value", "4");
			reqContext.setEntityValue2("QueryCond/CONTENT_STATUS/criteria/operator", "!=");

			//将页面输入的查询条件进行解析
	        Element queryCond = DataQueryExt.transformCriteriaNode(reqContext.getQueryCond());

			// 分析查询条件
			ElementTranslator eleTranslator = new ElementTranslator(entityMap, queryCond);
			eleTranslator.parseExpandAll();

			// 读取：SELECT 字段
			List fieldNodeList = eleTranslator.getFieldNodeList();
			// 读取：WHERE 条件
			List criteriaList = eleTranslator.getCriteriaNodeList();
			// 读取：ORDER 条件
			String orderBy = eleTranslator.getOrderByString();

			List valueList = new ArrayList();
			List dataTypeList = new ArrayList();

			// 创建：Select SQL
			QuerySQLBuilder sqlBuilder = new QuerySQLBuilder(entityMap, fieldNodeList, criteriaList, orderBy);
			String[] sql = sqlBuilder.buildSQL(valueList, dataTypeList);

			// 执行：查询知识条目
			Integer total = kmContentsDaoJdbc.count(sql[0], valueList, dataTypeList);
			List result = kmContentsDaoJdbc.list(sql[1], valueList, dataTypeList, curPage, pageSize);

			// 设置：返回的数据
			map.put("total", total);
			map.put("result", result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 组合查询
	 * 
	 * @param sql 
	 * @param curPage 
	 * @param pageSize 
	 */
	public Map complexQuery(String sql, Integer curPage, Integer pageSize){
		return kmContentsDao.complexQuery(sql, curPage, pageSize);
	}


	/**
	 * 根据知识条目的 ID、TABLE_ID、THEME_ID 失效知识条目
	 * 
	 * @param ID 知识的ID
	 * @param TABLE_ID 知识所属模型ID
	 * @param THEME_ID 知识模型所属分类ID
	 */
	public void overKmContents(String ID, String TABLE_ID, String THEME_ID) {
		try {
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByTableId(TABLE_ID);
			if(entityMap == null){
				return;
			}
			
			// 2、创建：UPDATE SQL
			StringBuffer sql = new StringBuffer("update ");
			sql.append(entityMap.getTableName());
			sql.append(" set CONTENT_STATUS=3, MODIFY_TIME=? where ID =?");	

			// 3、创建：知识修改时间
			String MODIFY_TIME = StaticMethod.getLocalString();
			
			// 4、创建：数据库放言解析器
			RelationalDB datebase = RelationalDB.getInstance();
			int idType = entityMap.fetchFieldMap("ID").getColumnType();
			int modifyTimeType = entityMap.fetchFieldMap("MODIFY_TIME").getColumnType();
			
			// 5、定义：参数和参数类型列表
			List valueList = new ArrayList();
			valueList.add(datebase.convStringToObject(MODIFY_TIME, modifyTimeType, ""));
			valueList.add(datebase.convStringToObject(ID, idType, ""));
			
			List dataTypeList = new ArrayList();
			dataTypeList.add(new Integer(modifyTimeType));
			dataTypeList.add(new Integer(idType));

			// 6、执行：失效知识条目
			kmContentsDaoJdbc.update(sql.toString(), valueList, dataTypeList);

			// 7、执行：更新订阅中间表的对应的知识状态 
			kmContentsDao.updateKmContentsContentStatus(ID, "3");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

    /**
     * 根据知识分类删除知识内容
     * 
     * @param THEME_ID 知识分类
     * @author ZHANGXB
     * @since 1.0
     */
	public void removeKmContentsByThemeId(String THEME_ID) {
		try {
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByThemeId(THEME_ID);
			if(entityMap == null){
				return;
			}

			// 创建：更新 SQL
			StringBuffer sql = new StringBuffer("update ");
			sql.append(entityMap.getTableName());
			sql.append(" set CONTENT_STATUS=4 where THEME_ID like ?");
			
			// 创建：数据库方言解析器
			RelationalDB datebase = RelationalDB.getInstance();
			int themeIdType = entityMap.fetchFieldMap("THEME_ID").getColumnType();
			
			// 创建：参数值列表
			List valueList = new ArrayList();
			valueList.add(datebase.convStringToObject(THEME_ID + '%', themeIdType, ""));
			
			// 创建：参数类型列表
			List dataTypeList = new ArrayList();
			dataTypeList.add(new Integer(themeIdType));

			// 删除：知识内容
			Integer upsize = kmContentsDaoJdbc.update(sql.toString(), valueList, dataTypeList);

			if(upsize.intValue() >0){
				// 删除：知识公共表知识内容
				kmContentsDao.removeKmContentsByThemeId(THEME_ID);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 修改知识条目的评论等级
	 * 
	 * @param TABLE_ID 知识所属模型ID
	 * @param ID 知识的ID
	 * @param grade 知识等级
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public void updateKmContentsGrade(final String TABLE_ID, final String ID, final int grade) {
		try {
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByTableId(TABLE_ID);
			if(entityMap == null){
				return;
			}
			
			// 创建：更新 SQL
			StringBuffer sql = new StringBuffer("UPDATE ");
			sql.append(entityMap.getTableName());

			switch (grade) {
			case 1:
				sql.append(" SET GRADE_ONE = GRADE_ONE+1 ");
				break;
			case 2:
				sql.append(" SET GRADE_TWO = GRADE_TWO+1 ");
				break;
			case 3:
				sql.append(" SET GRADE_THREE = GRADE_THREE+1 ");
				break;
			case 4:
				sql.append(" SET GRADE_FOUR = GRADE_FOUR+1 ");
				break;
			case 5:
				sql.append(" SET GRADE_FIVE = GRADE_FIVE+1 ");
				break;
			default:
				return;				
			}
			sql.append(" WHERE ID=?");
			
			// 创建：数据库方言解析器
			RelationalDB datebase = RelationalDB.getInstance();
			int idType = entityMap.fetchFieldMap("ID").getColumnType();
			
			// 创建：参数值列表
			List valueList = new ArrayList();
			valueList.add(datebase.convStringToObject(ID, idType, ""));
			
			// 创建：参数类型列表
			List dataTypeList = new ArrayList();
			dataTypeList.add(new Integer(idType));

			// 更新：知识内容评价级别
			Integer upsize = kmContentsDaoJdbc.update(sql.toString(), valueList, dataTypeList);
			
			if(upsize.intValue() >0){
				// 更新：知识公共表知识内容评价级别
				kmContentsDao.updateKmContentsGradeFlagByContentId(ID, grade);					
			}			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 根据知识主键、知识模型、知识分类 查询知识内容版本表
	 * 
	 * @param ID 知识主键
	 * @param TABLE_ID 知识模型
	 * @param THEME_ID 知识分类
	 * @author ZHANGXB
	 * @since 1.0
	 */
	public List getKmContentsHistoryList(final String ID, final String TABLE_ID, final String THEME_ID){
		List result = new ArrayList();
		try {		
			// 查询：知识模型
			EntityMap entityMap = kmTableCacheMgr.getKmEntityMapByTableId(TABLE_ID);
			if(entityMap == null){
				return result;
			}

			// 创建：查询SQL
			StringBuffer selSQL = new StringBuffer("select ID,TABLE_ID,THEME_ID,VERSION,");
			selSQL.append("CREATE_USER,CREATE_DEPT,CREATE_TIME,");
			selSQL.append("MODIFY_USER,MODIFY_DEPT,MODIFY_TIME from ");
			selSQL.append( entityMap.getTableName() );
			selSQL.append("_HIS where ID=? order by VERSION desc");

			// 创建：数据库方言解析器
			RelationalDB datebase = RelationalDB.getInstance();
			int idType = entityMap.fetchFieldMap("ID").getColumnType();
			
			// 创建：参数值列表
			List valueList = new ArrayList();
			valueList.add(datebase.convStringToObject(ID, idType, ""));
			
			// 创建：参数类型列表
			List dataTypeList = new ArrayList();
			dataTypeList.add(new Integer(idType));
									
			// 查询：知识内容版本表
			result = kmContentsDaoJdbc.listAllHistory(selSQL.toString(), valueList, dataTypeList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 查询某作者处于草稿状态的知识
	 * 
	 * @param operateUserId 知识作者
	 * @return
	 */
	public List getKmContentsIsDraftByCreateUser(final String operateUserId){
		// contentStatus = 1 草稿
		return kmContentsDao.getKmContentsByContentStatusAndCreateUser("1", operateUserId);
	}

	/**
	 * 查询某作者处于删除状态的知识
	 * 
	 * @param operateUserId 知识作者
	 * @return
	 */	
	public List getKmContentsIsDeletedByCreateUser(final String operateUserId) {
		// contentStatus = 4 已删除
		return kmContentsDao.getKmContentsByContentStatusAndCreateUser("4", operateUserId);
	}

	//下面的方法暂时没有使用到---------------------------------------------
	
	public void removeKmContents(final String id) {
		kmContentsDao.removeKmContents(id);
	}

	public Map getKmContentss(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmContentsDao.getKmContentss(curPage, pageSize, whereStr);
	}

	/**
	 * 根据知识创建人查询知识信息
	 * 
	 * @param createUser
	 * @return
	 */
	public List getKmContentsList(final String createUser) {
		return kmContentsDao.getKmContentsList(createUser);
	}

	/**
	 * 查询某模型下的所用知识
	 * @param themeId
	 * @return
	 */
	public List getKmContentsListByThemeId(final String themeId){
		return kmContentsDao.getKmContentsListByThemeId(themeId);
	}

	public List getKmContentsListLinkThemeId(final String themeId){
		return kmContentsDao.getKmContentsListLikeThemeId(themeId);
	}
	
	public void removeKmContentsByContentId(final String contentId) {
		kmContentsDao.removeKmContentsByContentId(contentId);
	}

	public KmContents getKmContentsByContentId(final String contentId){
		return kmContentsDao.getKmContentsByContentId(contentId);
	}

	public List getKmContentss() {
		return kmContentsDao.getKmContentss();
	}

	public KmContents getKmContents(final String id) {
		return kmContentsDao.getKmContents(id);
	}
	
	public void saveKmContents(KmContents kmContents){
		kmContentsDao.saveKmContents(kmContents);
	}

	public void deleteKmContentsByThemeId(final String themeId) {
		kmContentsDao.deleteKmContentsByThemeId(themeId);
	}

	public void deleteKmContents(){
		kmContentsDao.deleteKmContents();
	}
	public void revivifyAllKmContents(){
		kmContentsDao.revivifyAllKmContents();
	}
	public void revivifyKmContents(final String themeId){
		kmContentsDao.revivifyKmContents(themeId);
	}
}