package com.boco.eoms.commons.rule.util;

/**
 * <p>
 * Title:Rule用到的常量
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 19, 2007 8:01:44 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleConstants {

	/**
	 * 在执行业务规则后，RuleService将对应Rule存入输出参数outMap,
	 * 按map.put(RuleConstants.RULE_OUT_PARAMETER_MAP,Rule)存储，
	 * 二次开发人员可以RULE_OUT_PARAMETER_MAP为KEY在outMap中取Rule
	 * 
	 * @see RuleService#invoke
	 */
	public final static String RULE_OUT_PARAMETER_MAP = "RULE_OUT_PARAMETER_MAP*";

}
