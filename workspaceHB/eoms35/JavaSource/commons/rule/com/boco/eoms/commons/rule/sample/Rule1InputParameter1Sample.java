package com.boco.eoms.commons.rule.sample;


/**
 * <p>
 * Title:模拟Rule1Parameter1对应Rule1的参数parameter1的sample
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 9:26:16 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Rule1InputParameter1Sample {

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;

	//	@RuleAnnotation(description = "获取年龄")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	//	@RuleAnnotation(description = "获取名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rule1InputParameter1Sample(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Rule1InputParameter1Sample() {
		super();
	}

	/**
	 * 测试表达式service用
	 * 
	 * @return
	 */
	public String getResult() {
		return name + ":" + age;
	}

	/**
	 * 测试表达式service用
	 * 
	 * @return
	 */
	public String getStr(String str) {
		return str;
	}

}
