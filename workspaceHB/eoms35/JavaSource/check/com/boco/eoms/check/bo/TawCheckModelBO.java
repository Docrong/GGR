package com.boco.eoms.check.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.boco.eoms.check.dao.TawCheckBatchDAO;
import com.boco.eoms.check.dao.TawCheckModelDAO;
import com.boco.eoms.check.dao.TawCheckTargerDAO;
import com.boco.eoms.check.model.TawCheckModel;
import com.boco.eoms.check.model.TawCheckModelAttatch;
import com.boco.eoms.check.model.TawCheckTarger;
import com.boco.eoms.check.qo.TawCheckModelQO;
import com.boco.eoms.check.vo.TawCheckModelVO;
import com.boco.eoms.common.bo.BO;
import com.boco.eoms.common.util.StaticMethod;
//import com.boco.eoms.wsdict.dao.TawWsDictDAO;
import com.boco.eoms.check.util.TawCheckSCOREMethod;
public class TawCheckModelBO extends BO{
	Map formMap;

	public void setModelForm(Map map) {
		this.formMap = map;
	}

	public void SaveModel() {
		try {
//			TawWsDictDAO tawWsDictDAO = new TawWsDictDAO();
			TawCheckModel tawCheckModel = new TawCheckModel();
			TawCheckModelDAO tawCheckModelDAO = new TawCheckModelDAO();
			com.boco.eoms.common.util.MyBeanUtils.populate(tawCheckModel,
					formMap);
			if(tawCheckModel.getModelTarger().length()>0){
				String strVar = TawCheckSCOREMethod.getStr2str(tawCheckModel.getModelTarger());
//				tawCheckModel.setModelTargerName(tawWsDictDAO.getCode2DictName(strVar,17));
			}
			String dictWord = "SYSTEM.DICTTYPE.checkModel"+tawCheckModel.getModelType();
			String tableName = StaticMethod.null2String(StaticMethod.getNodeName(dictWord));
			tawCheckModel.setTableName(tableName);
			tawCheckModelDAO.save(tawCheckModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List getList(int[] pagePra) {
		ArrayList list = new ArrayList();
		ArrayList targerList = new ArrayList();
		try {
			TawCheckModelQO tawCheckModelQO = new TawCheckModelQO();
			TawCheckModelDAO tawCheckModelDAO = new TawCheckModelDAO();
			tawCheckModelQO.setTargerModelType(StaticMethod.nullObject2int(formMap
					.get("targerModelType")));
			tawCheckModelQO.setModelType(StaticMethod.nullObject2int(formMap
					.get("modelType")));
			String modelName = StaticMethod.nullObject2String(formMap.get("modelName"));
			if(!modelName.equals("")){
				tawCheckModelQO.setModelName(modelName);
			}
			String hsql = tawCheckModelQO.getHsql();
			list = (ArrayList)tawCheckModelDAO.getList(hsql, pagePra);
			for (int i = 0; i < list.size(); i++) {
				TawCheckModel tawCheckModel = (TawCheckModel) list.get(i);
				TawCheckModelVO tawCheckModelVO = new TawCheckModelVO();
				com.boco.eoms.common.util.MyBeanUtils
						.copyPropertiesFromDBToPage(tawCheckModelVO,
								tawCheckModel);
				targerList.add(tawCheckModelVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return targerList;
	}
	
	public List getAllList(int[] pagePra) {
		ArrayList list = new ArrayList();
		ArrayList targerList = new ArrayList();
		try {
			TawCheckModelQO tawCheckModelQO = new TawCheckModelQO();
			TawCheckModelDAO tawCheckModelDAO = new TawCheckModelDAO();
			String hsql = tawCheckModelQO.getHsql();
			list = (ArrayList)tawCheckModelDAO.getList(hsql, pagePra);
			for (int i = 0; i < list.size(); i++) {
				TawCheckModel tawCheckModel = (TawCheckModel) list.get(i);
				TawCheckModelVO tawCheckModelVO = new TawCheckModelVO();
				com.boco.eoms.common.util.MyBeanUtils
						.copyPropertiesFromDBToPage(tawCheckModelVO,
								tawCheckModel);
				targerList.add(tawCheckModelVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return targerList;
	}
	public void delTawCheckModel(String id)
	   {
		   TawCheckModel tawCheckModel=new TawCheckModel();
		   TawCheckModelDAO tawCheckModelDAO=new TawCheckModelDAO();
		   try
		   {
			   tawCheckModel=tawCheckModelDAO.loadTarger(id);
			   tawCheckModelDAO.delModel(tawCheckModel);
	       }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	   }
	public TawCheckModel getTawCheckModel(String id)
	   {
		   TawCheckModel tawCheckModel=new TawCheckModel();
		   TawCheckModelDAO tawCheckModelDAO=new TawCheckModelDAO();
		   try
		   {
			   tawCheckModel=tawCheckModelDAO.loadTarger(id);
		     }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		 return tawCheckModel;
	   }
	
	public TawCheckModel getTawCheckModelByModelAliasName(String modelAliasName)
	   {
		   TawCheckModel tawCheckModel=new TawCheckModel();
		   TawCheckModelDAO tawCheckModelDAO=new TawCheckModelDAO();
		   try
		   {
			   String sql="from TawCheckModel where modelAliasName='"+modelAliasName+"'";
			   List list=tawCheckModelDAO.getTargerList(sql);
			   if(list.size()>0){
				   tawCheckModel=(TawCheckModel)list.get(0);
			   }
		     }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		 return tawCheckModel;
	   }	
	public  String[] getAllExcelSheetNames(String [] selectNames,String zhuanye){
		
		String retNames[]=null;
		Vector vector=new Vector();
		TawCheckModel tawCheckModel=new TawCheckModel();
		TawCheckModelDAO tawCheckModelDAO=new TawCheckModelDAO();
		   try
		   {
			   String sql="from TawCheckModel ";
			   if(zhuanye.equals("1")){//��ά
				   sql+=" where to_number(modelSort)>10 and to_number(modelSort)<=10";
			   }else{//���
				   sql+=" where to_number(modelSort)<=10 or to_number(modelSort)>=80";				   
			   }
			   List list=tawCheckModelDAO.getTargerList(sql);
			   for(int i=0;i<list.size();i++){
				   tawCheckModel=(TawCheckModel)list.get(i);
				   String modelAliasName=tawCheckModel.getModelAliasName();
				   if(modelAliasName!=null){
				   for(int j=0;j<selectNames.length;j++){
						if(modelAliasName.equals(selectNames[j])){
							vector.add(modelAliasName);
							break;
						}
					}
				   }
			   }
		     }catch(Exception e) {
			   e.printStackTrace();
		   }
		retNames=(String[])vector.toArray(new String[0]);
		return retNames;
	}
	public void updateModelAttatch(Map map){
		TawCheckBatchDAO dao=new TawCheckBatchDAO(ds);
		dao.save(map);
	}
	public TawCheckModelAttatch getAttatchFile(Map map){
		TawCheckModelAttatch model=new TawCheckModelAttatch();
		TawCheckBatchDAO dao=new TawCheckBatchDAO(ds);
		model=dao.getFile(map);
		return model;
	}
}
