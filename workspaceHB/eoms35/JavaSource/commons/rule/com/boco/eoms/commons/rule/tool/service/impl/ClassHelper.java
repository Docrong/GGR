package com.boco.eoms.commons.rule.tool.service.impl;

//import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.tool.model.LabelBean;

//import com.boco.eoms.commons.rule.tool.model.RuleAnnotation;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 27, 2007 2:47:42 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class ClassHelper {

	/**
	 * 拼写className类所有method方法
	 * 
	 * @param className
	 *            包名+类名
	 * @return
	 * @throws RuleException
	 */
	public static List getMethodsForClass(String className)
			throws RuleException {
		// 去空格
		className = className.replaceAll(" ", "");
		List list = new ArrayList();
		Class cls;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuleException(e);
		}

		Method[] method = cls.getDeclaredMethods();
		if (method == null) {
			return list;
		}

		for (int i = 0; i < method.length; i++) {
			LabelBean lb = new LabelBean();
			String mdName = "";
			String mdValue = "";
			if (Modifier.isPublic(method[i].getModifiers())
					&& !Modifier.isStatic(method[i].getModifiers())) {

				//				for (Annotation annotation : method[i].getAnnotations()) {
				//					RuleAnnotation ra = (RuleAnnotation) annotation;
				//					mdName += ra.description() + ":";
				//				}

				mdName += method[i].getReturnType().getName() + " "
						+ method[i].getName() + "(";

				mdValue += method[i].getName() + "(";

				//				for (Class clss : method[i].getParameterTypes()) {
				//					mdName += clss.getName() + ",";
				//					mdValue += clss.getName() + ",";
				//				}
				if (mdName.endsWith(",")) {
					mdName = mdName.substring(0, mdName.length() - 1);

				}
				if (mdValue.endsWith(",")) {
					mdValue = mdValue.substring(0, mdValue.length() - 1);
					mdValue += ")";
				}
				if (!mdName.endsWith(")")) {
					mdName += ")";
				}
				if (!mdValue.endsWith(")")) {
					mdValue += ")";
				}
				//				for (Class clss : method[i].getExceptionTypes()) {
				//					mdName += ")throws " + clss.getName();
				//				}
			}
			lb.setName(mdName);
			lb.setValue(mdValue);
			list.add(lb);
		}
		return list;
	}

	public static void main(String args[]) throws RuleException {
		List list = ClassHelper
				.getMethodsForClass("com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample");
		for (Iterator it = list.iterator(); it.hasNext();) {
			LabelBean lb = (LabelBean) it.next();
			System.out.println("name:" + lb.getName());
			System.out.println("value:" + lb.getValue());
		}

	}

	//	@RuleAnnotation(description = "调用aa方法")
	public String aa(String kk, String k1) throws Exception {
		return null;
	}

}
