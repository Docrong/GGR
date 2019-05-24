package com.boco.eoms.check.util;
import java.util.Vector;

import com.boco.eoms.check.model.*;
public class TawCheckSCOREMethod {
public static double getScore(double targer,TawCheckTarger tawCheckTarger)
{
	double score=0;
	if(97.79==targer){
		System.out.println("asdfsadsdf");
	}
	try
	{
//规则算分规则
if(tawCheckTarger.getTarger_prin().equals("0"))
{
	  if(targer>tawCheckTarger.getTarger_base())
	  {
		  score=(targer-tawCheckTarger.getTarger_base())/tawCheckTarger.getTarger_add()*tawCheckTarger.getTarger_score_add()+tawCheckTarger.getTarger_score_base();
		  if(score>tawCheckTarger.getTarger_score_full() || score==tawCheckTarger.getTarger_score_full())
		  {
			  score=tawCheckTarger.getTarger_score_full();
		  }
	  }
	  else if(targer<tawCheckTarger.getTarger_base())
	  {
		  score=tawCheckTarger.getTarger_score_base()-(tawCheckTarger.getTarger_base()-targer)/tawCheckTarger.getTarger_del()*tawCheckTarger.getTarger_score_del();
		  if((score<0 || score==0)&&tawCheckTarger.getTarger_score_base()>=0)
		  {
			  score=0;
		  }		  
	  }
	  else if(targer==tawCheckTarger.getTarger_base())
	  {
		  score=tawCheckTarger.getTarger_score_base();
	  }
}
//不规则算分规则
else if(tawCheckTarger.getTarger_prin().equals("1"))
{
	  if(targer>tawCheckTarger.getTarger_base())
	  {
		  score=tawCheckTarger.getTarger_score_base()-(targer-tawCheckTarger.getTarger_base())/tawCheckTarger.getTarger_del()*tawCheckTarger.getTarger_score_del();
		  if(score<0 || score==0)
		  {
			  score=0;
		  }	
	  }
	  else if(targer<tawCheckTarger.getTarger_base())
	  {
		  score=tawCheckTarger.getTarger_score_base()+(tawCheckTarger.getTarger_base()-targer)/tawCheckTarger.getTarger_add()*tawCheckTarger.getTarger_score_add();
		  if(score>tawCheckTarger.getTarger_score_full() || score==tawCheckTarger.getTarger_score_full())
		  {
			  score=tawCheckTarger.getTarger_score_full();
		  }
	  }
	  else if(targer==tawCheckTarger.getTarger_base())
	  {
		  score=tawCheckTarger.getTarger_score_base();
	  }	
}
	}catch(Exception e)
	{e.printStackTrace();}
	return score;
}

public static String getStr2str(String str){
	String strVar = "";
	try{
		java.util.StringTokenizer st = new java.util.StringTokenizer(str,",");
		while(st.hasMoreTokens()){
			strVar += "'"+st.nextToken()+"',";
		}
		if(strVar.length()>1){
			strVar = strVar.substring(0,strVar.length()-1);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return strVar;
}

public static int getStrNum(String str){
	int num = 0;
	try{
		java.util.StringTokenizer st = new java.util.StringTokenizer(str,",");
		num = st.countTokens();
	}catch(Exception e){
		e.printStackTrace();
	}
	return num;
}
/**
 * 根据ne_id获得地市中文名称
 * @param ne_id
 * @return
 */
public String getZhLaber(int ne_id)
	{
		String ZH = "";
		
		try {
			if (ne_id == -1128953347){	
				ZH = "全省";
			}else if (ne_id == -1744891292){	
				ZH = "保定";
			}else if(ne_id == 1948613501){
				ZH = "沧州";
			}else if(ne_id == 867223656){
				ZH = "承德";
			}else if(ne_id == -402456014){
				ZH = "邯郸";
			}else if(ne_id == 91461549){
				ZH = "衡水";
			}else if(ne_id == 414603468){
				ZH = "廊坊";
			}else if(ne_id == -1458085134){
				ZH = "秦皇岛";
			}else if(ne_id == -578147474){
				ZH = "石家庄";
			}else if(ne_id == 1478501655){
				ZH = "唐山";
			}else if(ne_id == 202839087){
				ZH = "邢台";
			}else if(ne_id == -75534827){
				ZH = "张家口";
			}else {
				ZH = "未知地市";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ZH;
		}
	public static String[] getAreaNams(){
		String areaNames[]={"邯郸","石家庄","保定","张家口","承德","唐山","廊坊","沧州","衡水",	"邢台","秦皇岛","全省"};
		return areaNames;
	}
	public static String getareaId(String name){
		String id="";
		String areaIds[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
		String areaNames[]=getAreaNams();
		for(int i=0;i<areaNames.length;i++){
			if(areaNames[i].equals(name)){
				id=areaIds[i];
				break;
			}
		}
		return id;
	}
	public static boolean checkUserRoles(String userId){
		boolean flag=false;
		String roleUserIds[]={"yuanyanbin","wangliying","jiacuiran","duanshuxia","admin"};
		for(int i=0;i<roleUserIds.length;i++){
			if(userId.equals(roleUserIds[i])){
				flag=true;
				break;
			}
		}
		return flag;
	}
}