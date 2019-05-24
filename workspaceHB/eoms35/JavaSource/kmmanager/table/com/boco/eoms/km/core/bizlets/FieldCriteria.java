package com.boco.eoms.km.core.bizlets;

import com.boco.eoms.km.core.util.Operate;

public class FieldCriteria {

	private String criteria;
	private String value; //值
	private String fieldName;
	private String pattern;
	private String criteriaPattern;
	private String field;

	public FieldCriteria(String fieldName, String criteria, String value,
			String pattern, String criteriaPattern, String field) {
		this.fieldName = fieldName;
		this.criteria = criteria;
		this.value = value;
		this.pattern = pattern;
		this.criteriaPattern = criteriaPattern;
		this.field = field;
	}

	public String getMatchString() {
		// 将操作名称转换为操作ID
		int i = Operate.convertStringToInt(criteria);
		switch (i) {
			case Operate.NOT_NULL: // 1
			case Operate.NULL: // 11
				return this.criteria;
			case Operate.MATCH: // 10
				return this.value;
			case Operate.EQUAL: // 2
			case Operate.NOT_EQUAL: // 3
			case Operate.LESSER: // 4
			case Operate.LESSER_EQUAL: // 5
			case Operate.GREATER: // 7
			case Operate.GREATER_EQUAL: // 7
				if (this.value == null)
					return null;
				else
					return new String(this.criteria + this.value);
			case Operate.LIKE: // 8
			case Operate.BETWEEN: // 9
			default:
				return null;
		}
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String s) {
		criteria = s;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String s) {
		value = s;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String s) {
		fieldName = s;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String s) {
		pattern = s;
	}

	public String getCriteriaPattern() {
		return criteriaPattern;
	}

	public void setCriteriaPattern(String s) {
		criteriaPattern = s;
	}

	public String getField() {
		return field;
	}

	public void setField(String s) {
		field = s;
	}

	public static void main(String args[]) {
		System.out.println("FieldCriteria.main() null operator==>"
				+ Operate.convertStringToInt("quarter"));
		System.out.println("FieldCriteria.main() 2 operator==>"
				+ Operate.convertIntToString(2));
	}
}