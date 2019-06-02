package com.boco.eoms.check.vo;

import com.boco.eoms.common.util.StaticMethod;


public class TawCheckModelVO {
	private String id;

	private String modelName;

	private String modelPerson;

	private String modelType;

	private String modelTargerName;

	private String modelTarger;

	private String modelRemark;

	private String modelDeleted;
	
	private String targer_model_type_name;
	
	private String modelTypeName;
	
	private String tableName;
	
	private String targerModelType;
	private String modelSort;
	private String modelFullScore;
	public String getTableName() {
		if(tableName == null){
			tableName = "";
		}
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getId() {
		if (id == null) {
			id = "";
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelDeleted() {
		if (modelDeleted == null) {
			modelDeleted = "";
		}
		return modelDeleted;
	}

	public void setModelDeleted(String modelDeleted) {
		this.modelDeleted = modelDeleted;
	}

	public String getModelName() {
		if (modelName == null) {
			modelName = "";
		}
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelPerson() {
		if (modelPerson == null) {
			modelPerson = "";
		}
		return modelPerson;
	}

	public void setModelPerson(String modelPerson) {
		this.modelPerson = modelPerson;
	}

	public String getModelRemark() {
		if (modelRemark == null) {
			modelRemark = "";
		}
		return modelRemark;
	}

	public void setModelRemark(String modelRemark) {
		this.modelRemark = modelRemark;
	}

	public String getModelTarger() {
		if (modelTarger == null) {
			modelTarger = "";
		}
		return modelTarger;
	}

	public void setModelTarger(String modelTarger) {
		this.modelTarger = modelTarger;
	}

	public String getModelTargerName() {
		if (modelTargerName == null) {
			modelTargerName = "";
		}
		return modelTargerName;
	}

	public void setModelTargerName(String modelTargerName) {
		this.modelTargerName = modelTargerName;
	}

	public String getModelType() {
		if (modelType == null) {
			modelType = "";
		}
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	
	public String getTarger_model_type_name() {
		String targerModelTypeName=""; 
	    try
	    {
//	    	TawWsDictBO tawWsDictBO=new TawWsDictBO();
//	    	TawWsDict tawWsDict=tawWsDictBO.getDict(Integer.parseInt(StaticMethod.getNodeName("SYSTEM.DICTTYPE.kpiType")),Integer.parseInt(targerModelType));
//	    	targerModelTypeName=tawWsDict.getDictName();		    	
	    }catch(Exception e)
	    {e.printStackTrace();}
		return targerModelTypeName;
	}
	
	public String getModelTypeName() {
		String modelTypeName="";
		try
		{
//	    	TawWsDictBO tawWsDictBO=new TawWsDictBO();
//	    	TawWsDict tawWsDict=tawWsDictBO.getDict(Integer.parseInt(StaticMethod.getNodeName("SYSTEM.DICTTYPE.kpiWebType")), Integer.parseInt(modelType));
//	    	modelTypeName=tawWsDict.getDictName();			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return modelTypeName;
	}

	public String getTargerModelType() {
		return targerModelType;
	}

	public void setTargerModelType(String targerModelType) {
		this.targerModelType = targerModelType;
	}

	public String getModelSort() {
		return modelSort;
	}

	public void setModelSort(String modelSort) {
		this.modelSort = modelSort;
	}

	public String getModelFullScore() {
		return modelFullScore;
	}

	public void setModelFullScore(String modelFullScore) {
		this.modelFullScore = modelFullScore;
	}


}
