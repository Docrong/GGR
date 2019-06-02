package com.boco.eoms.check.bo;
import com.boco.eoms.common.util.*;
import com.boco.eoms.check.bo.*;
import com.boco.eoms.check.dao.*;
import com.boco.eoms.check.model.*;
import com.boco.eoms.check.vo.*;
import com.boco.eoms.check.qo.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import javax.servlet.*;
import org.apache.struts.util.*;
import java.util.*;
//import com.boco.eoms.wsdict.bo.*;
/**
 * @see
 * <p>�����������ڲ��뿼��ָ�����ݵ��õķ���</p>
 * @author ��Ң
 * @version 3.5
 */
public class TawCheckTargerBO
{
	Map formMap;
   public void setTargerForm(Map map)
   {
	   this.formMap=map;
   }
   //�����ָ��
   public void SaveTarger()
   {
	 try
	 {
	  TawCheckTarger tawCheckTarger=new TawCheckTarger();
	  TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
//	  TawWsDictBO tawWsDcitBO=new TawWsDictBO();
	  com.boco.eoms.common.util.MyBeanUtils.populate(tawCheckTarger,formMap);
	  int kpiConType=this.getDictType("SYSTEM.DICTTYPE.kpiConType");
	  String targer_per="";
//	  String targer_per=tawWsDcitBO.getDictCode(kpiConType, tawCheckTarger.getTarger_name());
	  boolean flag=this.getFlag(targer_per);
	  if(flag==true)
	  {
		  tawCheckTarger.setTarger_per(targer_per);
		  tawCheckTargerDAO.save(tawCheckTarger);		  
	  }else if(flag==false)
	  {
		 String targerId=this.getTargerId(targer_per);
		 tawCheckTarger=this.getTarger(targerId);
		 this.updateTarger(tawCheckTarger);
	  }
     }catch(Exception e)
	 {e.printStackTrace();}
   }
   //���ڷ�ҳ��LIST��ʾ
   public List getList(int[] pagePra)
   {
	   ArrayList list=new ArrayList();
	   ArrayList targerList=new ArrayList();
	   try
	   {
		   TawCheckTargerQO tawCheckTargerQO=new TawCheckTargerQO();
		   TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
		   tawCheckTargerQO.setKpiType(StaticMethod.nullObject2int(formMap.get("targer_model_type")));
		   tawCheckTargerQO.setKpiWebType(StaticMethod.nullObject2int(formMap.get("targer_type")));
		   String hsql=tawCheckTargerQO.getHsql();
		   list=(ArrayList)tawCheckTargerDAO.getList(hsql,pagePra);
		     for (int i=0;i<list.size();i++)
		    {
			   TawCheckTarger tawCheckTarger=(TawCheckTarger)list.get(i);
			   TawCheckTargerVO tawCheckTargerVO =new TawCheckTargerVO();
			   com.boco.eoms.common.util.MyBeanUtils.copyPropertiesFromDBToPage(tawCheckTargerVO, tawCheckTarger);
			   targerList.add(tawCheckTargerVO);
		    }
	   }catch(Exception e)
	   {e.printStackTrace();}
	   return targerList;
   }
   public List getList(int targerModelType,int modelType)
   {
	   ArrayList list=new ArrayList();
	   ArrayList targerList=new ArrayList();
	   try
	   {
		   TawCheckTargerQO tawCheckTargerQO=new TawCheckTargerQO();
		   TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
		   tawCheckTargerQO.setKpiType(targerModelType);
		   tawCheckTargerQO.setKpiWebType(modelType);
		   String hsql=tawCheckTargerQO.getHsql();
		   list=(ArrayList)tawCheckTargerDAO.getTargerList(hsql);
		     for (int i=0;i<list.size();i++)
		    {
			   TawCheckTarger tawCheckTarger=(TawCheckTarger)list.get(i);
			   TawCheckTargerVO tawCheckTargerVO =new TawCheckTargerVO();
			   com.boco.eoms.common.util.MyBeanUtils.copyPropertiesFromDBToPage(tawCheckTargerVO, tawCheckTarger);
			   targerList.add(tawCheckTargerVO);
		    }
	   }catch(Exception e)
	   {e.printStackTrace();}
	   return targerList;
   }
   //ȡ��BOCO�ļ��е���ֵ
   public int getDictType(String type)
   {
	   int dictType=0;
	   try
	   {
		  dictType=Integer.parseInt(StaticMethod.getNodeName(type));   
	   }catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return dictType;
   }
   public TawCheckTargerVO getTargerVO(String id)
   {
	   TawCheckTargerVO tawCheckTargerVO=new TawCheckTargerVO();
	   TawCheckTarger tawCheckTarger=new TawCheckTarger();
	   TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
	   try
	   {
		   tawCheckTarger=tawCheckTargerDAO.loadTarger(id); 
		   com.boco.eoms.common.util.MyBeanUtils.copyPropertiesFromDBToPage(tawCheckTargerVO,tawCheckTarger);
	   }catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return tawCheckTargerVO;
   }
   //ɾ��ָ�����
   public void delTawCheckTarger(String id)
   {
	   TawCheckTarger tawCheckTarger=new TawCheckTarger();
	   TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
	   try
	   {
		   tawCheckTarger=tawCheckTargerDAO.loadTarger(id);
		   tawCheckTargerDAO.delTarger(tawCheckTarger);
       }catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   //���¸�ָ��
   public void updateTarger()
   {
	   try
	   {
		 TawCheckTarger tawCheckTarger=new TawCheckTarger();
		 TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
		 com.boco.eoms.common.util.MyBeanUtils.populate(tawCheckTarger,formMap);
		 tawCheckTargerDAO.updateTarger(tawCheckTarger);
	   }catch(Exception e)
	   {e.printStackTrace();}
   }
  //����ָ�����Ƿ�����ڿ���
  public boolean getFlag(String targer_per)
  {
	  boolean flag=true;
	  try
	  {
		TawCheckTargerQO tawCheckTargerQO=new TawCheckTargerQO();
		TawCheckTarger tawCheckTarger=new TawCheckTarger();
		TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
		tawCheckTargerQO.setType(targer_per);
		String hsql=tawCheckTargerQO.getHsql();
		ArrayList list=(ArrayList)tawCheckTargerDAO.getTargerList(hsql);;
	 if(list.size()>0)
	 {
			flag=false;
	 }
	  }catch(Exception e)
	  {e.printStackTrace();}
	  return flag;
  }
  //�õ�ָ�����
  public TawCheckTarger getTarger(String id)
  {
	   TawCheckTarger tawCheckTarger=new TawCheckTarger();
	   TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
	   try
	   {
		   tawCheckTarger=tawCheckTargerDAO.loadTarger(id); 
	   }catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return tawCheckTarger;
  }
  //�õ�ָ���ID
  public String getTargerId(String type)
  {
	   String targerId="";
	   TawCheckTargerQO tawCheckTargerQO=new TawCheckTargerQO();
	   tawCheckTargerQO.setType(type);
	   String hsql=tawCheckTargerQO.getHsql();
	   TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
	   TawCheckTarger tawCheckTarger=new TawCheckTarger();
	   try
	   {
		   ArrayList list=(ArrayList)tawCheckTargerDAO.getTargerList(hsql);
		   tawCheckTarger=(TawCheckTarger)list.get(0);
		   targerId=tawCheckTarger.getTarger_id(); 
	   }catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return targerId;
  }
  //�����ĸ���ָ��
  public void updateTarger(TawCheckTarger tawCheckTarger)
  {
	   try
	   {
		 TawCheckTargerDAO tawCheckTargerDAO=new TawCheckTargerDAO();
		 String targerName=tawCheckTarger.getTarger_per();
		 com.boco.eoms.common.util.MyBeanUtils.populate(tawCheckTarger,formMap);
		 tawCheckTarger.setTarger_per(targerName);
		 tawCheckTargerDAO.updateTarger(tawCheckTarger);
	   }catch(Exception e)
	   {e.printStackTrace();}
  }
}