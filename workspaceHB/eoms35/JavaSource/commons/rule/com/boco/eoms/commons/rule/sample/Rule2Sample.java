package com.boco.eoms.commons.rule.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.config.model.RuleEngine;
import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.service.RuleService;

/**
 * <p>
 * Title:规则service
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 10:06:50 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Rule2Sample extends RuleService {

	/**
	 * @param ruleListeners
	 * @param rules
	 * @param rule
	 * @param xmlPath
	 */
	public Rule2Sample(List ruleListeners, RuleEngine rules, Rule rule,
			String xmlPath) {
		super(ruleListeners, rules, rule, xmlPath);
	}

	/**
	 * 二次开发人员需实现的业务接口
	 * 
	 * @param map
	 *            根据配置文件中input的配置，二次开发人员取出配置的参数，
	 *            如sample配置key="rule1InputParameter1Sample",value="com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample"
	 *            key="rule1Parameter2InputSample",value="com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample"
	 *            这时二次发人员在execute中按这种方式取参数
	 *            com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample
	 *            param1=(com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample)
	 *            map.get("rule1InputParameter1Sample");
	 *            com.boco.eoms.commons.rule.sample.Rule1InputParameter2Sample
	 *            param2=(com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample)
	 *            map.get("rule1InputParameter2Sample");
	 * 
	 * @return 根据配置文件output的配置，二次开发人员需返回配置的参数 如sample配置
	 *         key="rule1OutputParameter1Sample",value="com.boco.eoms.commons.rule.sample.Rule1OutputParameter1Sample"
	 *         key="rule1OutputParameter2Sample",value="com.boco.eoms.commons.rule.sample.Rule1OutputParameter2Sample"
	 *         配置人员需按业务要求，返回
	 *         map.put("rule1OutputParameter1Sample",com.boco.eoms.commons.rule.sample.Rule1OutputParameter1Sample);
	 *         map.put("rule1OutputParameter2Sample",com.boco.eoms.commons.rule.sample.Rule1OutputParameter2Sample);
	 *         return map;
	 * @throws RuleException
	 */
	public Map execute(Map map, Rule rule) throws RuleException {
		// 规则业务
		// 取输入参数
		Rule1InputParameter1Sample inputParam1 = (Rule1InputParameter1Sample) map
				.get("rule1InputParameter1Sample");
		Rule1InputParameter2Sample inputParam2 = (Rule1InputParameter2Sample) map
				.get("rule1InputParameter2Sample");

		Map outMap = new HashMap();

		/**
		 * 测试优先级路由
		 */
		if (true) {
			return outMap;
		}
		Rule1OutputParameter1Sample outParam1 = new Rule1OutputParameter1Sample();
		Rule1OutputParameter2Sample outParam2 = new Rule1OutputParameter2Sample();

		// 模拟规则,年龄大于10数，姓名不为匿名，则outParam1设为可通过
		if (inputParam1.getAge().intValue() > 10
				&& !"anonym".equals(inputParam1.getName())) {
			// 为说明业务逻辑所以将setName放入判断条件
			outParam1.setName(inputParam1.getName());
			outParam1.setOk(true);
		} else {
			// 为说明业务逻辑所以将setName放入判断条件
			outParam1.setName(inputParam1.getName());
			outParam1.setOk(false);
		}
		// 性别为男则通过
		if ("male".equals(inputParam2.getSex())) {
			outParam2.setSex("male");
			outParam2.setOk(true);
		} else {
			outParam2.setSex("male");
			outParam2.setOk(false);
		}
		outMap.put("rule1OutputParameter1Sample", outParam1);
		outMap.put("rule1OutputParameter2Sample", outParam2);
		// 返回输出参数
		return outMap;
	}

}
