package com.boco.eoms.commons.statistic.base.excelutil.sample;

public class SampleModel {

	private String name = null;
	
	private String attribute = null;
	
	private String profession = null;
	
	private String chinese = null;
	
	private String math = null;
	
	private String english = null;
	
	private String sum = null;

	public String getName() {
		return name;
	}
	
	public SampleModel()
	{
		
	}
	
	public SampleModel(String name,String attribute,String profession,String chinese,String math
			,String english,String sum)
	{
		this.name = name;
		this.attribute = attribute;
		this.profession = profession;
		this.chinese = chinese;
		this.math = math;
		this.english = english;
		this.sum = sum;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public String getMath() {
		return math;
	}

	public void setMath(String math) {
		this.math = math;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
	
}
