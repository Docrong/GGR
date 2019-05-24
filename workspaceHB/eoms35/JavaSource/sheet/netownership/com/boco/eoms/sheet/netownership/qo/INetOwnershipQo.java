package com.boco.eoms.sheet.netownership.qo;

import java.util.Map;

public interface INetOwnershipQo {

	/**
	 * 根据QO的条件拼装查询语句
	 * @return 可执行的sql
	 */
	public  String getClauseSql(Map map,String queryType);
	
	/**
	 * 根据QO的条件拼装查询语句，事有权限的查询
	 * @return 可执行的sql
	 */
	//public abstract String getAclClauseSql(Map map, String userId, String deptId);
	/**
	 * 设置表的别名和表名
	 */
	public abstract void setPoNet(String poNet);
	public abstract String getPoNet();
	public abstract void setAliasNet(String aliasNet);
	public abstract String getAliasNet();

	
}

