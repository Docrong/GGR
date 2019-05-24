package com.boco.eoms.check.model;

import java.io.Serializable;

import com.boco.eoms.common.oo.DataObject;

public class TawCheckModel implements Serializable,DataObject{
	private String id;
	private String modelName;
	private String modelPerson;
	private String modelType;
	private String modelTargerName;
	private String modelTarger;
	private String modelRemark;
	private String modelDeleted;
	private int targerModelType;
	private String tableName;
	private String modelAliasName;
	private int modelStartRow;
	private int modelStartCol;
	private String modelSort;
	private String typeFlag;
	private String modelFullScore;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModelDeleted() {
		return modelDeleted;
	}
	public void setModelDeleted(String modelDeleted) {
		this.modelDeleted = modelDeleted;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelPerson() {
		return modelPerson;
	}
	public void setModelPerson(String modelPerson) {
		this.modelPerson = modelPerson;
	}
	public String getModelRemark() {
		return modelRemark;
	}
	public void setModelRemark(String modelRemark) {
		this.modelRemark = modelRemark;
	}
	public String getModelTarger() {
		return modelTarger;
	}
	public void setModelTarger(String modelTarger) {
		this.modelTarger = modelTarger;
	}
	public String getModelTargerName() {
		return modelTargerName;
	}
	public void setModelTargerName(String modelTargerName) {
		this.modelTargerName = modelTargerName;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public int getTargerModelType() {
		return targerModelType;
	}
	public void setTargerModelType(int targerModelType) {
		this.targerModelType = targerModelType;
	}
	public String getModelAliasName() {
		return modelAliasName;
	}
	public void setModelAliasName(String modelAliasName) {
		this.modelAliasName = modelAliasName;
	}
	public int getModelStartCol() {
		return modelStartCol;
	}
	public void setModelStartCol(int modelStartCol) {
		this.modelStartCol = modelStartCol;
	}
	public int getModelStartRow() {
		return modelStartRow;
	}
	public void setModelStartRow(int modelStartRow) {
		this.modelStartRow = modelStartRow;
	}
	public String getModelSort() {
		return modelSort;
	}
	public void setModelSort(String modelSort) {
		this.modelSort = modelSort;
	}
	public String getTypeFlag() {
		return typeFlag;
	}
	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}
	public String getModelFullScore() {
		return modelFullScore;
	}
	public void setModelFullScore(String modelFullScore) {
		this.modelFullScore = modelFullScore;
	}
	
	

}
