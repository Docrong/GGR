package com.boco.eoms.km.table.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.table.dao.KmTableGeneralDao;
import com.boco.eoms.km.table.mgr.KmTableGeneralMgr;
import com.boco.eoms.km.table.model.KmTableColumn;
import com.boco.eoms.km.table.model.KmTableGeneral;
import com.boco.eoms.km.table.util.KmTableGeneralConstants;
import com.boco.eoms.km.table.util.KmTableUtil;

/**
 * <p>
 * Title:模块信息表
 * </p>
 * <p>
 * Description:模块信息表
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 * 
 * @author 吕卫华
 * @version 1.0
 * 
 */
public class KmTableGeneralMgrImpl implements KmTableGeneralMgr {

	private KmTableGeneralDao kmTableGeneralDao;

	public KmTableGeneralDao getKmTableGeneralDao() {
		return this.kmTableGeneralDao;
	}

	public void setKmTableGeneralDao(KmTableGeneralDao kmTableGeneralDao) {
		this.kmTableGeneralDao = kmTableGeneralDao;
	}

	public List getKmTableGenerals() {
		return kmTableGeneralDao.getKmTableGenerals();
	}

	/**
	 * 取开放的模型信息 列表
	 * 
	 * @return 返回模型信息表的列表
	 */
	public List getOpenKmTableGenerals() {
		return kmTableGeneralDao.getOpenKmTableGenerals();
	}

	public KmTableGeneral getKmTableGeneral(final String id) {
		return kmTableGeneralDao.getKmTableGeneral(id);
	}

	public Map getKmTableGenerals(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return kmTableGeneralDao.getKmTableGenerals(curPage, pageSize, whereStr);
	}

	public Map getKmTableGenerals(final Integer curPage, final Integer pageSize) {
		return kmTableGeneralDao.getKmTableGenerals(curPage, pageSize);
	}

	public void saveKmTableGeneral(KmTableGeneral kmTableGeneral) {
		boolean isNew = (null == kmTableGeneral.getId() || ""
				.equals(kmTableGeneral.getId()));
		if (isNew) {
			// 保存新节点
			kmTableGeneralDao.saveKmTableGeneral(kmTableGeneral);

		} else {
			kmTableGeneralDao.saveKmTableGeneral(kmTableGeneral);
		}
	}

	/**
	 * 根据模型分类themeId查询模型信息表
	 * 
	 * @param themeId
	 *            模型分类
	 * @return 返回某模型分类themeId的对象
	 * @author zhangxb
	 */
	public KmTableGeneral getKmTableGeneralByThemeId(final String themeId) {
		return kmTableGeneralDao.getKmTableGeneralByThemeId(themeId);
	}

	/**
	 * 根据模型id删除模型定义、模型字段，并解除与模型绑定的模型分类
	 * 
	 * @param id 主键
	 * @author zhangxb
	 */
	public void removeKmTableGeneral(final String id) {
		kmTableGeneralDao.removeKmTableGeneral(id);
	}

	public List getNextLevelKmTableGenerals(String parentNodeId) {
		return kmTableGeneralDao.getNextLevelKmTableGenerals(parentNodeId);
	}

	public String getUsableNodeId(String parentNodeId) {
		return kmTableGeneralDao.getUsableNodeId(parentNodeId, 
				parentNodeId.length()+ KmTableGeneralConstants.NODEID_BETWEEN_LENGTH);
	}

	public boolean isHasNextLevel(String parentNodeId) {
		boolean flag = false;
		List list = kmTableGeneralDao.getNextLevelKmTableGenerals(parentNodeId);
		if (list.iterator().hasNext()) {
			flag = true;
		}
		return flag;
	}



	/**
	 * 创建知识库表(Oracle)
	 * 
	 * @param kmTableGeneral
	 * 
	 */
	public void createModelForOracle(KmTableGeneral kmTableGeneral) {
		String tableName = kmTableGeneral.getTableName();

		// 组织创建表sql
		StringBuffer createSql = new StringBuffer();
		List sqlList = new ArrayList();
		List sqlHisList = new ArrayList();
		createSql.append("create table ");
		createSql.append(tableName);
		createSql.append("(ID VARCHAR2(32) not null");
		createSql.append(",THEME_ID VARCHAR2(32)");
		createSql.append(",TABLE_ID VARCHAR2(32)");
		// createSql.append(",CONTENT_ID VARCHAR2(32)");
		createSql.append(",CREATE_USER VARCHAR2(32)");
		createSql.append(",CREATE_DEPT VARCHAR2(32)");
		createSql.append(",CREATE_TIME TIMESTAMP(6)");
		createSql.append(",MODIFY_USER VARCHAR2(32)");
		createSql.append(",MODIFY_DEPT VARCHAR2(32)");
		createSql.append(",MODIFY_TIME TIMESTAMP(6)");
		createSql.append(",CONTENT_TITLE VARCHAR2(100)");
		createSql.append(",CONTENT_KEYS VARCHAR2(100)");
		createSql.append(",CONTENT_STATUS NUMBER(5) DEFAULT 0");
		createSql.append(",AUDIT_FLAG NUMBER(5) DEFAULT 0");
		createSql.append(",ROLESTR_FLAG NUMBER(5) DEFAULT 0");
		createSql.append(",LEVEL_FLAG NUMBER(5) DEFAULT 0");
		createSql.append(",IS_BEST NUMBER(5) DEFAULT 0");
		createSql.append(",IS_DELETED NUMBER(5) DEFAULT 0");
		createSql.append(",IS_PUBLIC NUMBER(5) DEFAULT 1");
		createSql.append(",GRADE_ONE NUMBER(5) DEFAULT 0");
		createSql.append(",GRADE_TWO NUMBER(5) DEFAULT 0");
		createSql.append(",GRADE_THREE NUMBER(5) DEFAULT 0");
		createSql.append(",GRADE_FOUR NUMBER(5) DEFAULT 0");
		createSql.append(",GRADE_FIVE NUMBER(5) DEFAULT 0");
		createSql.append(",READ_COUNT NUMBER(5) DEFAULT 0");
		createSql.append(",USE_COUNT NUMBER(5) DEFAULT 0");
		createSql.append(",MODIFY_COUNT NUMBER(5) DEFAULT 0");
		// 历史表sql
		StringBuffer createHisSql = new StringBuffer();
		createHisSql.append("create table ");
		createHisSql.append(tableName + "_HIS");
		createHisSql.append("(ID VARCHAR2(32) not null");
		createHisSql.append(",THEME_ID VARCHAR2(32)");
		createHisSql.append(",TABLE_ID VARCHAR2(32)");
		// createHisSql.append(",CONTENT_ID VARCHAR2(32)");
		createHisSql.append(",CREATE_USER VARCHAR2(32)");
		createHisSql.append(",CREATE_DEPT VARCHAR2(32)");
		createHisSql.append(",CREATE_TIME TIMESTAMP(6)");
		createHisSql.append(",MODIFY_USER VARCHAR2(32)");
		createHisSql.append(",MODIFY_DEPT VARCHAR2(32)");
		createHisSql.append(",MODIFY_TIME TIMESTAMP(6)");
		createHisSql.append(",CONTENT_TITLE VARCHAR2(100)");
		createHisSql.append(",CONTENT_KEYS VARCHAR2(100)");
		createHisSql.append(",CONTENT_STATUS NUMBER(5) DEFAULT 0");
		createHisSql.append(",AUDIT_FLAG NUMBER(5) DEFAULT 0");
		createHisSql.append(",ROLESTR_FLAG NUMBER(5) DEFAULT 0");
		createHisSql.append(",LEVEL_FLAG NUMBER(5) DEFAULT 0");
		createHisSql.append(",IS_BEST NUMBER(5) DEFAULT 0");
		createHisSql.append(",IS_DELETED NUMBER(5) DEFAULT 0");
		createHisSql.append(",IS_PUBLIC NUMBER(5) DEFAULT 1");
		createHisSql.append(",GRADE_ONE NUMBER(5) DEFAULT 0");
		createHisSql.append(",GRADE_TWO NUMBER(5) DEFAULT 0");
		createHisSql.append(",GRADE_THREE NUMBER(5) DEFAULT 0");
		createHisSql.append(",GRADE_FOUR NUMBER(5) DEFAULT 0");
		createHisSql.append(",GRADE_FIVE NUMBER(5) DEFAULT 0");
		createHisSql.append(",READ_COUNT NUMBER(5) DEFAULT 0");
		createHisSql.append(",USE_COUNT NUMBER(5) DEFAULT 0");
		createHisSql.append(",MODIFY_COUNT NUMBER(5) DEFAULT 0");
		createHisSql.append(",VERSION NUMBER(5)");

		// 历史sequence
		StringBuffer seqHisSql = new StringBuffer();
		seqHisSql.append("create sequence");
		seqHisSql.append(" S_" + tableName + "_H");
		seqHisSql.append(" minvalue 1");
		seqHisSql.append(" maxvalue 999999999");
		seqHisSql.append(" start with 1");
		seqHisSql.append(" increment by 1");
		seqHisSql.append(" cache 5");

		// 历史触发器
		StringBuffer trgHisSql = new StringBuffer();
		trgHisSql.append(" create or replace trigger ");
		trgHisSql.append(" T_" + tableName + "_H ");
		trgHisSql.append(" before insert on ");
		trgHisSql.append(tableName + "_HIS");
		trgHisSql.append(" for each row ");
		trgHisSql.append(" declare ");
		trgHisSql.append(" begin ");
		trgHisSql.append(" if :new.VERSION is null then ");
		trgHisSql.append(" select S_" + tableName + "_H.nextval into :new.VERSION from dual; ");
		trgHisSql.append(" end if;");
		trgHisSql.append(" end ");
		trgHisSql.append(" T_" + tableName + "_H ;");

		String commonStrHeader = "COMMENT ON COLUMN " + tableName + ".";
		sqlList.add(commonStrHeader + "THEME_ID IS '知识分类外键'");
		sqlList.add(commonStrHeader + "TABLE_ID IS '模型基本表外键'");
		sqlList.add(commonStrHeader + "CREATE_USER IS '创建人'");
		sqlList.add(commonStrHeader + "CREATE_DEPT IS '创建部门'");
		sqlList.add(commonStrHeader + "CREATE_TIME IS '创建时间'");
		sqlList.add(commonStrHeader + "MODIFY_USER IS '修改人'");
		sqlList.add(commonStrHeader + "MODIFY_DEPT IS '修改部门'");
		sqlList.add(commonStrHeader + "MODIFY_TIME IS '修改时间'");
		sqlList.add(commonStrHeader + "CONTENT_TITLE IS '知识标题'");
		sqlList.add(commonStrHeader + "CONTENT_KEYS IS '关键字'");
		sqlList.add(commonStrHeader + "CONTENT_STATUS IS '知识状态（字典）'");
		sqlList.add(commonStrHeader + "AUDIT_FLAG IS '知识等级（字典）'");
		sqlList.add(commonStrHeader + "LEVEL_FLAG IS '难易程度（字典）'");
		sqlList.add(commonStrHeader + "IS_BEST IS '是否推荐（字典）'");
		sqlList.add(commonStrHeader + "IS_PUBLIC IS '是否公开（字典）'");
		sqlList.add(commonStrHeader + "GRADE_ONE IS '知识评价：1星的次数'");
		sqlList.add(commonStrHeader + "GRADE_TWO IS '知识评价：2星的次数'");
		sqlList.add(commonStrHeader + "GRADE_THREE IS '知识评价：3星的次数'");
		sqlList.add(commonStrHeader + "GRADE_FOUR IS '知识评价：4星的次数'");
		sqlList.add(commonStrHeader + "GRADE_FIVE IS '知识评价：5星的次数'");
		sqlList.add(commonStrHeader + "READ_COUNT IS '知识被阅读的次数'");
		sqlList.add(commonStrHeader + "USE_COUNT IS '知识被引用的次数'");
		// sqlList.add(commonStrHeader+"CONTENT_XML IS '知识内容'");
		sqlList.add(commonStrHeader + "MODIFY_COUNT IS '知识被修改的次数'");

		String commonStrHeaderHis = "COMMENT ON COLUMN " + tableName + "_HIS.";
		sqlHisList.add(commonStrHeaderHis + "THEME_ID IS '知识分类外键'");
		sqlHisList.add(commonStrHeaderHis + "TABLE_ID IS '模型基本表外键'");
		sqlHisList.add(commonStrHeaderHis + "CREATE_USER IS '创建人'");
		sqlHisList.add(commonStrHeaderHis + "CREATE_DEPT IS '创建部门'");
		sqlHisList.add(commonStrHeaderHis + "CREATE_TIME IS '创建时间'");
		sqlHisList.add(commonStrHeaderHis + "MODIFY_USER IS '修改人'");
		sqlHisList.add(commonStrHeaderHis + "MODIFY_DEPT IS '修改部门'");
		sqlHisList.add(commonStrHeaderHis + "MODIFY_TIME IS '修改时间'");
		sqlHisList.add(commonStrHeaderHis + "CONTENT_TITLE IS '知识标题'");
		sqlHisList.add(commonStrHeaderHis + "CONTENT_KEYS IS '关键字'");
		sqlHisList.add(commonStrHeaderHis + "CONTENT_STATUS IS '知识状态（字典）'");
		sqlHisList.add(commonStrHeaderHis + "AUDIT_FLAG IS '知识等级（字典）'");
		sqlHisList.add(commonStrHeaderHis + "LEVEL_FLAG IS '难易程度（字典）'");
		sqlHisList.add(commonStrHeaderHis + "IS_BEST IS '是否推荐（字典）'");
		sqlHisList.add(commonStrHeaderHis + "IS_PUBLIC IS '是否公开（字典）'");
		sqlHisList.add(commonStrHeaderHis + "GRADE_ONE IS '知识评价：1星的次数'");
		sqlHisList.add(commonStrHeaderHis + "GRADE_TWO IS '知识评价：2星的次数'");
		sqlHisList.add(commonStrHeaderHis + "GRADE_THREE IS '知识评价：3星的次数'");
		sqlHisList.add(commonStrHeaderHis + "GRADE_FOUR IS '知识评价：4星的次数'");
		sqlHisList.add(commonStrHeaderHis + "GRADE_FIVE IS '知识评价：5星的次数'");
		sqlHisList.add(commonStrHeaderHis + "READ_COUNT IS '知识被阅读的次数'");
		sqlHisList.add(commonStrHeaderHis + "USE_COUNT IS '知识被引用的次数'");
		// sqlHisList.add(commonStrHeaderHis+"CONTENT_XML IS '知识内容'");
		sqlHisList.add(commonStrHeaderHis + "MODIFY_COUNT IS '知识被修改的次数'");
		sqlHisList.add(commonStrHeaderHis + "VERSION IS '知识版本号'");

		List list = getNextLevelKmTableGenerals(kmTableGeneral.getId());

		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmTableColumn kmTableColumn = (KmTableColumn) nodeIter.next();
			String colName = kmTableColumn.getColName();

			createSql.append("," + colName + KmTableUtil.getColumnTypeForOracle(kmTableColumn));
			createHisSql.append("," + colName + KmTableUtil.getColumnTypeForOracle(kmTableColumn));

			if (kmTableColumn.getColDefault() != null
					&& !kmTableColumn.getColDefault().equals("")) {
				createSql.append(" DEFAULT '" + kmTableColumn.getColDefault() + "'");
				createHisSql.append(" DEFAULT '" + kmTableColumn.getColDefault() + "'");
			}
			//是否可以为空
			// if(kmTableColumn.getIsNullable().intValue()==0){
			// createSql.append(" NOT NULL");
			// createHisSql.append(" NOT NULL");
			// }
			
			if (kmTableColumn.getIsUnique().intValue() == 1) {
				createSql.append(" UNIQUE");
				createHisSql.append(" UNIQUE");
			}
			String commentStr = "COMMENT ON COLUMN " + tableName + "." + colName + " IS '" + kmTableColumn.getColChname() + "'";
			String commentStrHis = "COMMENT ON COLUMN " + tableName + "_HIS." + colName + " IS '" + kmTableColumn.getColChname() + "'";
			sqlList.add(commentStr);
			sqlHisList.add(commentStrHis);
		}

		createSql.append(",primary key (ID))");
		createHisSql.append(",primary key (ID, VERSION))");

		kmTableGeneralDao.createModel(createSql.toString());
		kmTableGeneralDao.createModel(createHisSql.toString());
		kmTableGeneralDao.modifyModel(sqlList);
		kmTableGeneralDao.modifyModel(sqlHisList);
		kmTableGeneralDao.createModel(seqHisSql.toString());
		kmTableGeneralDao.createModel(trgHisSql.toString());

		// 将字段设置为可显示
		kmTableGeneralDao.upKmTableColumnsIsVisiblByTableId(kmTableGeneral.getId());
	}

	/**
	 * 创建知识库表(Informix)
	 * 
	 * @param kmTableGeneral
	 * 
	 */
	public void createModelForInformix(KmTableGeneral kmTableGeneral) {
		String tableName = kmTableGeneral.getTableName();

		// 组织创建表sql
		StringBuffer createSql = new StringBuffer();
		createSql.append("create table ");
		createSql.append(tableName);
		createSql.append("(ID VARCHAR(32) not null");
		createSql.append(",THEME_ID VARCHAR(32)");
		createSql.append(",TABLE_ID VARCHAR(32)");
		// createSql.append(",CONTENT_ID VARCHAR2(32)");
		createSql.append(",CREATE_USER VARCHAR(32)");
		createSql.append(",CREATE_DEPT VARCHAR(32)");
		createSql.append(",CREATE_TIME DATETIME YEAR TO SECOND");
		createSql.append(",MODIFY_USER VARCHAR(32)");
		createSql.append(",MODIFY_DEPT VARCHAR(32)");
		createSql.append(",MODIFY_TIME DATETIME YEAR TO SECOND");
		createSql.append(",CONTENT_TITLE VARCHAR(100)");
		createSql.append(",CONTENT_KEYS VARCHAR(100)");
		createSql.append(",CONTENT_STATUS INTEGER DEFAULT 0");
		createSql.append(",AUDIT_FLAG INTEGER DEFAULT 0");
		createSql.append(",ROLESTR_FLAG INTEGER DEFAULT 0");
		createSql.append(",LEVEL_FLAG INTEGER DEFAULT 0");
		createSql.append(",IS_BEST INTEGER DEFAULT 0");
		createSql.append(",IS_DELETED INTEGER DEFAULT 0");
		createSql.append(",IS_PUBLIC INTEGER DEFAULT 1");
		createSql.append(",GRADE_ONE INTEGER DEFAULT 0");
		createSql.append(",GRADE_TWO INTEGER DEFAULT 0");
		createSql.append(",GRADE_THREE INTEGER DEFAULT 0");
		createSql.append(",GRADE_FOUR INTEGER DEFAULT 0");
		createSql.append(",GRADE_FIVE INTEGER DEFAULT 0");
		createSql.append(",READ_COUNT INTEGER DEFAULT 0");
		createSql.append(",USE_COUNT INTEGER DEFAULT 0");
		createSql.append(",MODIFY_COUNT INTEGER DEFAULT 0");

		// 历史表sql
		StringBuffer createHisSql = new StringBuffer();
		createHisSql.append("create table ");
		createHisSql.append(tableName + "_HIS");
		createHisSql.append("(ID VARCHAR(32) not null");
		createHisSql.append(",THEME_ID VARCHAR(32)");
		createHisSql.append(",TABLE_ID VARCHAR(32)");
		// createHisSql.append(",CONTENT_ID VARCHAR2(32)");
		createHisSql.append(",CREATE_USER VARCHAR(32)");
		createHisSql.append(",CREATE_DEPT VARCHAR(32)");
		createHisSql.append(",CREATE_TIME DATETIME YEAR TO SECOND");
		createHisSql.append(",MODIFY_USER VARCHAR(32)");
		createHisSql.append(",MODIFY_DEPT VARCHAR(32)");
		createHisSql.append(",MODIFY_TIME DATETIME YEAR TO SECOND");
		createHisSql.append(",CONTENT_TITLE VARCHAR(100)");
		createHisSql.append(",CONTENT_KEYS VARCHAR(100)");
		createHisSql.append(",CONTENT_STATUS INTEGER DEFAULT 0");
		createHisSql.append(",AUDIT_FLAG INTEGER DEFAULT 0");
		createHisSql.append(",ROLESTR_FLAG INTEGER DEFAULT 0");
		createHisSql.append(",LEVEL_FLAG INTEGER DEFAULT 0");
		createHisSql.append(",IS_BEST INTEGER DEFAULT 0");
		createHisSql.append(",IS_DELETED INTEGER DEFAULT 0");
		createHisSql.append(",IS_PUBLIC INTEGER DEFAULT 1");
		createHisSql.append(",GRADE_ONE INTEGER DEFAULT 0");
		createHisSql.append(",GRADE_TWO INTEGER DEFAULT 0");
		createHisSql.append(",GRADE_THREE INTEGER DEFAULT 0");
		createHisSql.append(",GRADE_FOUR INTEGER DEFAULT 0");
		createHisSql.append(",GRADE_FIVE INTEGER DEFAULT 0");
		createHisSql.append(",READ_COUNT INTEGER DEFAULT 0");
		createHisSql.append(",USE_COUNT INTEGER DEFAULT 0");
		createHisSql.append(",MODIFY_COUNT INTEGER DEFAULT 0");
		createHisSql.append(",VERSION serial");

		// List list = getNextLevelKmTableGenerals(kmTableGeneral.getId());
		List list = kmTableGeneralDao.getKmTableColumnsUnVisiblByTableId(kmTableGeneral.getId());

		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmTableColumn kmTableColumn = (KmTableColumn) nodeIter.next();
			String colName = kmTableColumn.getColName();

			createSql.append("," + colName + KmTableUtil.getColumnTypeForInformix(kmTableColumn));
			createHisSql.append("," + colName + KmTableUtil.getColumnTypeForInformix(kmTableColumn));

			if (kmTableColumn.getColDefault() != null
					&& !kmTableColumn.getColDefault().equals("")) {
				createSql.append(" DEFAULT '" + kmTableColumn.getColDefault() + "'");
				createHisSql.append(" DEFAULT '" + kmTableColumn.getColDefault() + "'");
			}
			
			//是否可以为空
			// if(kmTableColumn.getIsNullable().intValue()==0){
			// createSql.append(" NOT NULL");
			// createHisSql.append(" NOT NULL");
			// }
			
			if (kmTableColumn.getIsUnique().intValue() == 1) {
				createSql.append(" UNIQUE");
				createHisSql.append(" UNIQUE");
			}
		}

		createSql.append(",primary key (ID))");
		createHisSql.append(",primary key (ID, VERSION))");

		kmTableGeneralDao.createModel(createSql.toString());
		kmTableGeneralDao.createModel(createHisSql.toString());

		// 将字段设置为可显示
		kmTableGeneralDao.upKmTableColumnsIsVisiblByTableId(kmTableGeneral.getId());
	}

	/**
	 * 创建知识库表(Oracle)
	 * 
	 * @param kmTableGeneral
	 * 
	 */
	public void modifyModelForOracle(KmTableGeneral kmTableGeneral) {
		String tableId = kmTableGeneral.getId();
		// List list = getNextLevelKmTableGenerals(kmTableGeneral.getId());
		List list = kmTableGeneralDao.getKmTableColumnsUnVisiblByTableId(tableId);

		if (list != null && list.size() > 0) {

			String tableName = kmTableGeneral.getTableName();

			String headStr = "alter table " + tableName + " add(";
			String headHisStr = "alter table " + tableName + "_HIS" + " add(";
			String headModifyStr = "alter table " + tableName + " modify(";
			String headModifyHisStr = "alter table " + tableName + "_HIS" + " modify(";

			List sqlList = new ArrayList();
			List sqlHisList = new ArrayList();
			for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
				KmTableColumn kmTableColumn = (KmTableColumn) nodeIter.next();
				String colName = kmTableColumn.getColName();

				String sqlStr = "";
				String sqlStrHis = "";
				if (kmTableGeneralDao.HasColumn(tableName, colName)) {
					sqlStr = headModifyStr + colName + KmTableUtil.getColumnTypeForOracle(kmTableColumn);
					sqlStrHis = headModifyHisStr + colName + KmTableUtil.getColumnTypeForOracle(kmTableColumn);
				} else {
					sqlStr = headStr + colName + KmTableUtil.getColumnTypeForOracle(kmTableColumn);
					sqlStrHis = headHisStr + colName + KmTableUtil.getColumnTypeForOracle(kmTableColumn);
				}

				if (kmTableColumn.getColDefault() != null
						&& !kmTableColumn.getColDefault().equals("")) {
					sqlStr += " DEFAULT '" + kmTableColumn.getColDefault() + "'";
					sqlStrHis += " DEFAULT '" + kmTableColumn.getColDefault() + "'";
				}

				//是否可以为空
				// if(kmTableColumn.getIsNullable().intValue()==0){
				// sqlStr+=" NOT NULL";
				// sqlStrHis+=" NOT NULL";
				// }

				if (kmTableColumn.getIsUnique().intValue() == 1) {
					sqlStr += " UNIQUE";
					sqlStrHis += " UNIQUE";
				}

				sqlStr += ")";
				sqlStrHis += ")";

				String commentStr = "COMMENT ON COLUMN " + tableName + "." + colName + " IS '" + kmTableColumn.getColChname() + "'";
				String commentStrHis = "COMMENT ON COLUMN " + tableName + "_HIS." + colName + " IS '" + kmTableColumn.getColChname() + "'";

				sqlList.add(sqlStr);
				sqlHisList.add(sqlStrHis);

				sqlList.add(commentStr);
				sqlHisList.add(commentStrHis);
			}

			kmTableGeneralDao.modifyModel(sqlList);
			kmTableGeneralDao.modifyModel(sqlHisList);

			// 将字段设置为可显示
			kmTableGeneralDao.upKmTableColumnsIsVisiblByTableId(kmTableGeneral.getId());
		}
	}

	/**
	 * 创建知识库表(Informix)
	 * 
	 * @param kmTableGeneral
	 * 
	 */
	public void modifyModelForInformix(KmTableGeneral kmTableGeneral) {
		String tableId = kmTableGeneral.getId();
		// List list = getNextLevelKmTableGenerals(kmTableGeneral.getId());
		List list = kmTableGeneralDao.getKmTableColumnsUnVisiblByTableId(tableId);

		if (list != null && list.size() > 0) {
			List sqlList = new ArrayList();
			List sqlHisList = new ArrayList();

			String tableName = kmTableGeneral.getTableName();

			String headStr = "alter table " + tableName + " add(";
			String headHisStr = "alter table " + tableName + "_HIS" + " add(";
			String headModifyStr = "alter table " + tableName + " modify(";
			String headModifyHisStr = "alter table " + tableName + "_HIS" + " modify(";

			for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
				String sqlStr = "";
				String sqlStrHis = "";

				KmTableColumn kmTableColumn = (KmTableColumn) nodeIter.next();
				String colName = kmTableColumn.getColName();

				if (kmTableGeneralDao.HasColumn(tableName, colName)) {
					sqlStr = headModifyStr + colName + KmTableUtil.getColumnTypeForInformix(kmTableColumn);
					sqlStrHis = headModifyHisStr + colName + KmTableUtil.getColumnTypeForInformix(kmTableColumn);
				} else {
					sqlStr = headStr + colName + KmTableUtil.getColumnTypeForInformix(kmTableColumn);
					sqlStrHis = headHisStr + colName + KmTableUtil.getColumnTypeForInformix(kmTableColumn);
				}

				if (kmTableColumn.getColDefault() != null
						&& !kmTableColumn.getColDefault().equals("")) {
					sqlStr += " DEFAULT '" + kmTableColumn.getColDefault() + "'";
					sqlStrHis += " DEFAULT '" + kmTableColumn.getColDefault() + "'";
				}
				
				//是否可以为空
				// if(kmTableColumn.getIsNullable().intValue()==0){
				// sqlStr+=" NOT NULL";
				// sqlStrHis+=" NOT NULL";
				// }

				if (kmTableColumn.getIsUnique().intValue() == 1) {
					sqlStr += " UNIQUE";
					sqlStrHis += " UNIQUE";
				}

				sqlStr += ")";
				sqlStrHis += ")";

				sqlList.add(sqlStr);
				sqlHisList.add(sqlStrHis);
			}

			kmTableGeneralDao.modifyModel(sqlList);
			kmTableGeneralDao.modifyModel(sqlHisList);

			// 将字段设置为可显示
			kmTableGeneralDao.upKmTableColumnsIsVisiblByTableId(tableId);
		}
	}

	public boolean HasTable(String name) {
		return kmTableGeneralDao.HasTable(name);
	}

	public boolean HasColumn(String TableName, String ColumnName) {
		return kmTableGeneralDao.HasColumn(TableName, ColumnName);
	}

	/**
	 * 根据模型主键查询所有字段类型是单选字典的字段
	 * @param tableId
	 * @return List
	 * @author zhangxb
	 */
	public List getSubDictColumn(String tableId) {
		return kmTableGeneralDao.getKmTableColumnsIsDictByTableId(tableId);
	}
}