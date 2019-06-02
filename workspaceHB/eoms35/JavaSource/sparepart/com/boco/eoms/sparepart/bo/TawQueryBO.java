package com.boco.eoms.sparepart.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.sql.*;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.controller.TawSparepartForm;
import com.boco.eoms.sparepart.controller.TawQueryForm;
import com.boco.eoms.sparepart.dao.TawStorageDAO;
import com.boco.eoms.sparepart.dao.TawOrderPartDAO;
import com.boco.eoms.sparepart.dao.TawSparepartDAO;
import com.boco.eoms.sparepart.model.EarlyWarning;
import com.boco.eoms.sparepart.model.TawPart;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.sparepart.dao.*;
import com.boco.eoms.common.util.*;

import java.lang.reflect.*;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawQueryBO extends BO{

    public TawQueryBO(ConnectionPool ds){
        super(ds);
    }

    public TawQueryBO(ConnectionPool ds,String str){
        super(ds,str);
    }
    /**
     * @see condition ��ѯͳ�Ƶ����
     * @param form
     * @return
     */
    public String getConditionLoad(TawQueryForm form){
        convert(form);
        String condition="";       
        int state = 0 ;
        if(!form.getDeptName().equals(""))
        {
        	condition=" where prop_dept like '"+form.getDeptName()+"'";
        	state = 1;
        }
        if(!form.getStoragename().equals(""))
        {
        	if(state==1){
        		condition=condition+" and storage_name like '"+form.getStoragename()+"'";
        	}else{
        		condition=" where storage_name like '"+form.getStoragename()+"'";
        		state=1;
        	}
        }
        if(!form.getSupplier().equals(""))
        {
        	if(state==1){
        		condition=condition+" and supplier like '"+form.getSupplier()+"'";        		
        	}else{
        		condition=" where supplier like '"+form.getSupplier()+"'";
        		state=1;
        	}        	
        }
        if(!form.getObjectname().equals(""))
        {
        	if(state==1){
        		condition=condition+" and objectname like '"+form.getObjectname()+"'";
        	}else{
        		condition=" where objectname like '"+form.getObjectname()+"'";
        		state=1;
        	}
        }
        if(!form.getSerialno().equals(""))
        {
        	if(state==1){
        		condition=condition+" and serialno like'"+form.getSerialno()+"'";
        	}else{
        		condition=" where serialno like '"+form.getSerialno()+"'";
        		state=1;
        	}
        }
        if(state==0){
            condition = " where (1=1) ";
          }
        return condition;
    }
    /**
     * @see condition ��ѯͳ�Ƶ����
     * @param form
     * @return
     */
    public String getCondition(TawQueryForm form){
        convert(form);
        String condition="";
        int state = 0 ;
        if(!form.getTimein().equals("")){
           condition = "where intime>='" + form.getTimein() + "' ";
           state = 1;
        }
        if(!form.getTimeend().equals("")){
          if(state==1){
            condition = condition + " and intime<='" + form.getTimeend() + "' ";
          }else{
            condition = "where intime<='" + form.getTimeend() + "' ";
            state=1;
          }
        }
        if(!form.getNettype().equals("")){
          if(state==1){
            condition = condition + " and nettype='" + form.getNettype() + "'";
          }else{
            condition = "where nettype='" + form.getNettype() + "'";
            state = 1;
          }
        }
        if( form.getSubdept()!=null&&!form.getSubdept().equals("")){
            if(state==1){
              condition = condition + " and subdept='" + form.getSubdept() + "'";
            }else{
              condition = "where subdept='" + form.getSubdept() + "'";
              state = 1;
            }
          }
        if(!form.getNecode().equals("")){
          if(state==1){
            condition = condition + " and necode='" + form.getNecode() + "'";
          }else{
            condition = "where necode='" + form.getNecode() + "'";
            state = 1;
          }
        }
        if(!form.getObjecttype().equals("ȫѡ")&&!form.getObjecttype().equals("")){
          if(state==1){
            condition = condition + " and objecttype='" + form.getObjecttype() +"'";
          }else{
            condition = "where objecttype='" + form.getObjecttype() +"'";
            state = 1;
          }
        }
        if(form.getStorage().equals("")){
        }else if(form.getStorage()!=null){
          if(state==1){
            condition = condition + " and storageid in (" + form.getStorage() + ")";
          }else{
            condition = "where storageid in (" + form.getStorage() + ")";
            state = 1;
          }
        }
        if(form.getDeptName()!=null&&!form.getDeptName().equals("")&&!form.getDeptName().equals("全选")&&form.getDeptName()!=null){
          if(state==1){
            condition = condition + " and dept_name = '" + form.getDeptName() + "'";
          }else{
            condition = "where dept_name ='" + form.getDeptName() + "'";
            state = 1;
          }
        }
        if(form.getStoragename()!=null&&!form.getStoragename().equals("")&&!form.getStoragename().equals("全选")&&form.getStoragename()!=null){
          if(state==1){
            condition = condition + " and storage ='" + form.getStoragename() + "'";
          }else{
            condition = "where storage = '" + form.getStoragename() + "'";
            state = 1;
          }
        }


        if(form.getState()!=null&&!form.getState().equals("")){
          if(state==1){
            condition = condition + " and stateid='" + form.getState() + "'";
          }else{
            condition = "where stateid='" + form.getState() + "'";
            state = 1;
          }
        }
        if(form.getSupplier()!=null&&!form.getSupplier().equals("")){
          if(state==1){
            condition = condition + " and supplierid='" + form.getSupplier() + "'";
          }else{
            condition = "where supplierid='" + form.getSupplier() + "'";
            state = 1;
          }
        }
        if(form.getContract()!=null&&!form.getContract().equals("")){
          if(state==1){
            condition = condition + " and contract like '%" + form.getContract() + "%'";
          }else{
            condition = "where contract like '%" + form.getContract() + "%'";
            state = 1;
          }
        }
        if(form.getObjectname()!=null&&!form.getObjectname().equals("")){
          if(state==1){
            condition = condition + " and objectname like '%" + form.getObjectname() + "%'";
          }else{
            condition = "where objectname like '%" + form.getObjectname() + "%'";
            state = 1;
          }
        }
        if(form.getSerialno()!=null&&!form.getSerialno().equals("")){
          if(state==1){
            condition = condition + " and serialno like '%" + form.getSerialno() + "%'";
          }else{
            condition = "where serialno like '%" + form.getSerialno() + "%'";
            state = 1;
          }
        }
        if(state==0){
          condition = "where (1=1) ";
        }
        //add by wqw 20070703
        if(form.getStartintime()!=null&&!form.getStartintime().equals("")){
        	condition=condition+" and intime >= '"+form.getStartintime()+" 00:00:00"+"'";
        }
        if(form.getEndintime()!=null&&!form.getEndintime().equals("")){
        	condition=condition+" and intime <= '"+form.getEndintime()+" 23:59:59"+"'";
        }
        if(form.getStartouttime()!=null&&!form.getStartouttime().equals("")){
        	condition=condition+" and outtime > '"+form.getStartouttime()+"'";
        }
        if(form.getEndouttime()!=null&&!form.getEndouttime().equals("")){
        	condition=condition+" and outtime < '"+form.getEndouttime()+"'";
        }
        return condition;
    }

    /**
     * @see ��òֿ���б�
     * @return �ֿ���б�
     */
    public List getStorage(){
        List list=null;
        TawStorageDAO tawStorageDAO=new TawStorageDAO(ds);
        try{
            list=tawStorageDAO.getList();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * @see ���ݲ�ѯ��sql����ַ�
     * @return
     */
    public String getOrderSql(TawQueryForm form){
        convert(form);
        String sql="vaw.order_part_state<>0 and vaw.startdate>='"+form.getTimein()+"' and vaw.startdate<='"+
              form.getTimeend()+"' ";
        if(!form.getOperator().equals("")){
            sql=sql+" and vaw.operater='"+form.getOperator()+"'";
        }
        if(!form.getProposer().equals("")){
            sql=sql+" and vaw.proposer='"+form.getProposer()+"'";
        }
        if(!form.getStorage().equals("")){
            sql=sql+" and vaw.storageid="+form.getStorage();
        }
        if(!form.getState().equals("")){
            sql=sql+" and vaw.order_part_state="+form.getState();
        }
        if(!form.getType().equals("")){
            sql=sql+" and vaw.order_type="+form.getType();
        }
        return sql;
    }

    public String getOrderDetailSql(TawQueryForm form){
        convert(form);
        String sql=" where order_part_state<>0 and startdate>='"+form.getTimein()+"' and startdate<='"+        
              form.getTimeend()+"' ";
        if(!form.getOperator().equals("")){
            sql=sql+" and operater='"+form.getOperator()+"'";
        }
        if(!form.getProposer().equals("")){
            sql=sql+" and proposer='"+form.getProposer()+"'";
        }
        if(!form.getStorage().equals("")){
            sql=sql+" and storageid="+form.getStorage();
        }
        if(!form.getState().equals("")){
            sql=sql+" and order_part_state="+form.getState();
        }
        if(!form.getType().equals("")){
            sql=sql+" and order_type="+form.getType();
        }
        return sql;
    }
    /**
     * @see ��ݵ��ݵ�orderId,���ض�Ӧ�ı�����Ϣ
     * @param orderId  ���ݵ�id,Ψһ
     * @return �����б�
     */
    public List getSparepart(int orderId){
        String StrId=Integer.toString(orderId);
        String sql=" where order_id='"+StrId+"'";
        TawOrderPartDAO tawOrderPartDAO=new TawOrderPartDAO(ds);
        //��ñ�����id�ַ�֮��
        String SumId=tawOrderPartDAO.getSparePartSumId(sql);
        //��ȡ������Ϣ�б�

        return getPart(SumId);
    }

    public List getPart(String condition){
        //��ȡ������Ϣ�б�
        String SQL=" where id in ("+condition+")";
        TawPartDAO tawPartDAO=new TawPartDAO(ds);
        List list=null;
        try{
            list=tawPartDAO.getSparepart(SQL);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public String getSumCharge(List sparepart){
        String rate="";
        double aa=0.0;
        for(int i=0;i<sparepart.size();i++){
            TawPart tawPart=(TawPart)sparepart.get(i);
            aa=aa+Double.parseDouble(tawPart.getCharge());
        }
        rate=Double.toString(aa);
        return rate;

    }

    public void convert(Object beans){
        //ת��
        try{
            MyBeanUtils.copyPropertiesFromPageToDB(beans);
        }
        catch(InvocationTargetException ex){
        }
        catch(IllegalAccessException ex){
        }
    }

    public List getEarlyWarning() throws Exception {
    	List list = new ArrayList();
    	TawQueryDAO dao = new TawQueryDAO(ds);
    	HashMap map1 = dao.getEarlyWarning();
    	HashMap map2 = dao.getBackTime();
    	EarlyWarning earlyWarning = null;
    	Set set = null;
    	Set set1 = null;
    	String[] str = null;
    	String[] str1 = null;
    	Collection c = null;
    	Iterator iterator = null;
    	int id = 1;
    	/***��map�е�������?list�У��Թ���ʾ**/
    	if(map1 != null && map2 != null) {
    		set = map1.keySet();
    		str = (String[])set.toArray();
    		for(int i = 0; i < str.length; i++) {
    			earlyWarning = (EarlyWarning)map1.get(str[i]);
    			if(map2.containsKey(str[i])) {
    				earlyWarning.setItem5(((EarlyWarning)map2.get(str[i])).getItem5());
    				earlyWarning.setList5(((EarlyWarning)map2.get(str[i])).getList5());
    			} else {
    				earlyWarning.setItem5(0);
    				earlyWarning.setList5(null);
    			}
    			earlyWarning.setId(id);
				list.add(earlyWarning);
				id++;
    		}

    		set1 = map2.keySet();
    		str1 = (String[])set1.toArray();
    		for(int j = 0; j < str1.length; j++) {
    			earlyWarning = (EarlyWarning)map2.get(str[j]);
    			if(map1.containsKey(str1[j])) {
    				continue;
    			} else {
    				earlyWarning.setId(id);
    				list.add(earlyWarning);
    				id++;
    			}
    		}
    	} else if(map1 == null && map2 != null) {
    		set1 = map2.keySet();
    		str1 = (String[])set1.toArray();
    		for(int j = 0; j < str1.length; j++) {
    			earlyWarning = (EarlyWarning)map2.get(str[j]);
				earlyWarning.setId(id);
				list.add(earlyWarning);
				id++;
    		}
    	} else if(map2 == null && map1 != null) {
    		c = map1.values();
    		iterator = c.iterator();
    		while(iterator.hasNext()) {
    			earlyWarning = (EarlyWarning)iterator.next();
    			earlyWarning.setId(id);
    			earlyWarning.setItem5(0);
    			earlyWarning.setList5(null);
				list.add(earlyWarning);
				id++;
    		}
    	} else {
    		throw new Exception("无预警信息!");
    	}

    	return list;
    }
}
