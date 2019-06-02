package com.boco.eoms.commons.rule.listener;

import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.commons.rule.config.model.Parameter;
import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.service.RuleConfigWrapper;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 1:54:27 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleCheckListener implements IRuleListener {

	public void after(Map map, Rule rule) throws RuleException {
		// 取输入参数
		for (Iterator outputIt = rule.getOutput().getParameters().iterator(); outputIt
				.hasNext();) {
			Parameter para = (Parameter) outputIt.next();
			// 验证map中的所存的对象与xml配置是否相符
			RuleConfigWrapper.checkMapType(map, para);
		}

	}

	public void before(Map map, Rule rule) throws RuleException {
		// 取输入参数
		for (Iterator inputIt = rule.getInput().getParameters().iterator(); inputIt
				.hasNext();) {
			Parameter para = (Parameter) inputIt.next();
			// 验证map中的所存的对象与xml配置是否相符
			RuleConfigWrapper.checkMapType(map, para);
		}

	}

}
