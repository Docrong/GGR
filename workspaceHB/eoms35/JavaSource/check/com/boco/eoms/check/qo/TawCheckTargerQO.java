package com.boco.eoms.check.qo;

public class TawCheckTargerQO {
	String clausesql=" where tawCheckTarger.targer_deleted=0 ";
	public String getHsql()
	{
   
		String hsql="";
		try
		{
			hsql="from TawCheckTarger tawCheckTarger"+clausesql+" order by tawCheckTarger.targer_name";
		}catch(Exception e)
		{e.printStackTrace();}
		return hsql;
	}
	private int kpiType;
	public void setKpiType(int kpiType)
	{
		if(kpiType!=0)
		{
			clausesql+=" and tawCheckTarger.targer_model_type="+kpiType;
		}
		this.kpiType=kpiType;
	}
	private int kpiWebType;
	public void setKpiWebType(int targer_type)
	{
		if(kpiType!=0)
		{
			clausesql+=" and tawCheckTarger.targer_type="+targer_type;
		}
		this.kpiWebType=kpiWebType;
	}
	private String type;
	public void setType(String type)
	{
		if(!type.equals("") && type!=null)
		{
			clausesql+=" and tawCheckTarger.targer_per='"+type+"'";
		}
		this.type=type;
	}
}
