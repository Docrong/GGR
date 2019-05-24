package com.boco.eoms.km.rule.util;
import java.sql.Types;
/**
 * <p>
 * Title:规则库
 * </p>
 * <p>
 * Description:规则库
 * </p>
 * <p>
 * Fri Apr 17 16:06:45 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public class KmRuleConstants {
	
	/**
	 * list key
	 */
	public final static String KMRULE_LIST = "kmRuleList";
	/**
	 * 根结点
	 */
	public final static String TREE_ROOT_ID = "1";	
	
	public final static String[] idArray={
		"CREATE_USER","CREATE_DEPT","CREATE_TIME",
		"MODIFY_USER","MODIFY_DEPT","CONTENT_STATUS",
		"CONTENT_TITLE","CONTENT_KEYS","CONTENT_STATUS",
		"AUDIT_FLAG","ROLESTR_FLAG","LEVEL_FLAG",
		"IS_BEST","IS_PUBLIC","GRADE_ONE",
		"GRADE_TWO","GRADE_THREE","GRADE_FOUR",
		"GRADE_FIVE","READ_COUNT","USE_COUNT",
		"CONTENT_XML","MODIFY_COUNT"
	};
	public final static String[] nameArray={
		"创建人","创建部门","创建时间",
		"修改人","修改部门","修改时间",
		"知识标题","关键字","知识状态",
		"是否经过审核","知识等级","难易程度",
		"是否推荐","是否公开","知识评价：1星的次数",
		"知识评价：2星的次数","知识评价：3星的次数","知识评价：4星的次数",
		"知识评价：5星的次数","知识被阅读的次数","知识被引用的次数",
		"知识内容","知识被修改的次数"
	};
	public final static String[] typeArray={
		"普通文本","普通文本","日期时间",
		"普通文本","普通文本","日期时间",
		"普通文本","普通文本","字典",
		"字典","字典","字典",
		"字典","字典","数字类型",
		"数字类型","数字类型","数字类型",
		"数字类型","数字类型","数字类型",
		"大文本域","数字类型"
	};
	public final static int[] typeValueArray={
		Types.VARCHAR,Types.VARCHAR,Types.DATE,
		Types.VARCHAR,Types.VARCHAR,Types.DATE,
		Types.VARCHAR,Types.VARCHAR,Types.INTEGER,
		Types.INTEGER,Types.INTEGER,Types.INTEGER,
		Types.INTEGER,Types.INTEGER,Types.INTEGER,
		Types.INTEGER,Types.INTEGER,Types.INTEGER,
		Types.INTEGER,Types.INTEGER,Types.INTEGER,
		Types.VARCHAR,Types.INTEGER
	};
}