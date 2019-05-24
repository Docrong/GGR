package com.boco.eoms.check.qo;

public class TawCheckModelQO {
	String clausesql=" where tawCheckModel.modelDeleted=0";
	
	public String getHsql()
	{
   		String hsql="";
		try
		{
			hsql="from TawCheckModel tawCheckModel"+clausesql;
		}catch(Exception e)
		{e.printStackTrace();}
		return hsql;
	}
	
	private int targerModelType;
	public void setTargerModelType(int targerModelType)
	{
		if(targerModelType!=0)
		{
			clausesql+=" and tawCheckModel.targerModelType="+targerModelType;
		}
		this.targerModelType=targerModelType;
	}
	
	private int modelType ;

	public void setModelType(int modelType) {
		if(modelType != 0){
			clausesql+=" and tawCheckModel.modelType="+modelType;
		}
		this.modelType = modelType;
	}

	private String modelName;

	public void setModelName(String modelName) {
		if(!modelName.equals("")){
			clausesql+=" and tawCheckModel.modelName like '%"+modelName+"%'";
		}
		this.modelName = modelName;
	}
	
	
}
