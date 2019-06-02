package com.boco.eoms.sparepart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.struts.util.LabelValueBean;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.model.EarlyWarning;
import com.boco.eoms.sparepart.model.TawPart;
import com.boco.eoms.sparepart.model.TawOrderPart;
import com.boco.eoms.sparepart.model.TawSparepart;
import com.boco.eoms.sparepart.model.TawStorage;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sparepart.util.EarlyWarningSQL;
/*import com.boco.eoms.wsdict.dao.TawWsDictDAO;
import com.boco.eoms.wsdict.model.TawWsDict;*/

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawQueryDAO extends DAO{

    public TawQueryDAO(ConnectionPool ds){
        super(ds);
    }

    /**
     * @see ���ͳ��
     * @param SQL
     * @param offset
     * @return
     * @throws SQLException
     */
    public List getStatPart(String SQL,int offset,int length) throws
          SQLException{
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select count(*),objecttype,nettype,subdept,necode,state,storage "+
                  " from taw_part "+SQL+
                  " group by  objecttype,nettype,subdept,necode,state,storage ";
            pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
            rs=pstmt.executeQuery();
            while(offset>0){
                rs.next();
                offset--;
            }
            int i=0;
            while((i++<length)&&rs.next()){
                TawPart sparepart=new TawPart();
                sparepart.setSum(StaticMethod.dbNull2String(rs.getString(1)));
                sparepart.setObjecttype(StaticMethod.dbNull2String(rs.getString(
                      2)));
                sparepart.setNettype(StaticMethod.dbNull2String(rs.getString(3)));
                sparepart.setSubdept(StaticMethod.dbNull2String(rs.getString(4)));
                sparepart.setNecode(StaticMethod.dbNull2String(rs.getString(5)));
                sparepart.setState(StaticMethod.dbNull2String(rs.getString(6)));
                sparepart.setStorage(StaticMethod.dbNull2String(rs.getString(7)));
                list.add(sparepart);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;
    }

    public int getStatPartCount(String SQL){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        int i=0;
        try{
            conn=ds.getConnection();
            String sql=
                  "select count(*),objecttype,nettype,subdept,necode,state,storage "+
                  " from taw_part "+SQL+
                  " group by  objecttype,nettype,subdept,necode,state,storage ";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            while(rs.next()){
                i++;
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return i;
    }

    public List getServicePart(String SQL,int offset,int length) throws
          SQLException{
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select count(*),nettype,subdept,necode,objecttype,"+
                  "sum(sersum),storage from part_service_stat "+SQL+
                  " group by nettype,subdept,necode,objecttype,storage";
            pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                         ResultSet.CONCUR_UPDATABLE);
             rs=pstmt.executeQuery();
             if(offset>0){
                 rs.absolute(offset);
             }
            int i=0;
            while((i++<length)&&rs.next()){
                TawPart tawPart=new TawPart();
                tawPart.setSum(rs.getString(1));
                tawPart.setSerSum(rs.getInt(6));
                populate(tawPart,rs);
                list.add(tawPart);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;
    }

    public int getServicePartCount(String SQL){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        int i=0;
        try{
            conn=ds.getConnection();
            String sql=
                  "select count(*),nettype,subdept,necode,objecttype,"+
                  "sum(sersum),storage from part_service_stat "+SQL+
                  " group by nettype,subdept,necode,objecttype,storage";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            while(rs.next()){
                i++;
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return i;
    }

    public List getChargePart(String condition,int offset,int length){
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select charge,nettype,subdept,necode,objecttype,supplier"+
                  ",storage,managecode,contract,operator from taw_charge "+
                  condition;
            pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                          ResultSet.CONCUR_UPDATABLE);
              rs=pstmt.executeQuery();
              if(offset>0){
                  rs.absolute(offset);
              }
            int i=0;
            while((i++<length)&&rs.next()){
                TawPart tawOrder=new TawPart();
                populate(tawOrder,rs);
                list.add(tawOrder);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;

    }

    public int getChargePartCount(String condition){
        int count=0;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select count(*) from taw_charge "+condition;

            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return count;
    }

    public List getOutPart(String SQL,int offset,int length) throws
          SQLException{
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select count(*),nettype,subdept,necode,objecttype,sum(outsum),"+
                  "storage from part_out_stat "+SQL+
                  " group by nettype,subdept,necode,objecttype,storage";
            pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                         ResultSet.CONCUR_UPDATABLE);
             rs=pstmt.executeQuery();
             if(offset>0){
                 rs.absolute(offset);
             }
            int i=0;
            while((i++<length)&&rs.next()){
                TawPart tawPart=new TawPart();
                tawPart.setSum(StaticMethod.dbNull2String(rs.getString(1)));
                tawPart.setOutSum(rs.getInt(6));
                populate(tawPart,rs);
                list.add(tawPart);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;
    }

    public int getOutPartCount(String condition){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        int i=0;
        try{
            conn=ds.getConnection();
            String sql=
                  "select count(*),nettype,subdept,necode,objecttype,sum(outsum),"+
                  "storage from part_out_stat "+condition+
                  " group by nettype,subdept,necode,objecttype,storage";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            while(rs.next()){
                i++;
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return i;
    }

    public List getLoanNum (String SQL,int offset,int length) throws SQLException{
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select nettype,subdept,necode,objecttype,storage,sum(loannum) from taw_part "+SQL+
                  "and loannum>0 group by nettype,subdept,necode,objecttype,storage order by sum(loannum) desc";
            pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                         ResultSet.CONCUR_UPDATABLE);
             rs=pstmt.executeQuery();
             if(offset>0){
                 rs.absolute(offset);
             }
            int i=0;
            while((i++<length)&&rs.next()){
                TawPart tawPart=new TawPart();
                tawPart.setLoannum(rs.getInt(6));
                populate(tawPart,rs);
                list.add(tawPart);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;
    }

    public int getLoanNumCount(String SQL){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        int i=0;
        try{
            conn=ds.getConnection();
            String sql=
                  "select nettype,subdept,necode,objecttype,storage,sum(loannum) from taw_part "+SQL+
                  "and loannum>0 group by nettype,subdept,necode,objecttype,storage";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            while(rs.next()){
                i++;
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return i;
    }


    public List getRepairNum (String SQL,int offset,int length) throws SQLException{
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select nettype,subdept,necode,objecttype,storage,sum(repairnum) from taw_part "+SQL+
                  "and repairnum>0 group by nettype,subdept,necode,objecttype,storage order by sum(repairnum) desc";
            pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                         ResultSet.CONCUR_UPDATABLE);
             rs=pstmt.executeQuery();
             if(offset>0){
                 rs.absolute(offset);
             }
            int i=0;
            while((i++<length)&&rs.next()){
                TawPart tawPart=new TawPart();
                tawPart.setRepairnum(rs.getInt(6));
                populate(tawPart,rs);
                list.add(tawPart);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;
    }
    public List getCheckNum (String SQL,int offset,int length) throws SQLException{
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select nettype,subdept,necode,objecttype,storage,sum(checksum) from taw_part "+SQL+
                  "and repairnum>0 group by nettype,subdept,necode,objecttype,storage order by sum(repairnum) desc";
            pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                         ResultSet.CONCUR_UPDATABLE);
             rs=pstmt.executeQuery();
             if(offset>0){
                 rs.absolute(offset);
             }
            int i=0;
            while((i++<length)&&rs.next()){
                TawPart tawPart=new TawPart();
                tawPart.setRepairnum(rs.getInt(6));
                populate(tawPart,rs);
                list.add(tawPart);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;
    }

    public int getRepairNumCount(String SQL){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        int i=0;
        try{
            conn=ds.getConnection();
            String sql=
                  "select nettype,subdept,necode,objecttype,storage,sum(repairnum) from taw_part "+SQL+
                  "and repairnum>0 group by nettype,subdept,necode,objecttype,storage";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            while(rs.next()){
                i++;
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return i;
    }
    
    public HashMap getEarlyWarning(){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        HashMap map = new HashMap();
        EarlyWarning earlyWarning = null;
        List list1 = null;
        List list2 = null;
        List list3 = null;
        List list4 = null;
        try{
            conn=ds.getConnection();
            EarlyWarningSQL sql = new EarlyWarningSQL();
            
            String date1 = this.getEarlyWarningTime(EarlyWarningSQL.OUT,EarlyWarningSQL.TYPE);
            sql.setTime1(date1);
            
            String date2 = this.getEarlyWarningTime(EarlyWarningSQL.IN,EarlyWarningSQL.TYPE);
            sql.setTime2(date2);
            
            String ratio = this.getUpdateRatio(EarlyWarningSQL.UPDATE,EarlyWarningSQL.TYPE);
            
            pstmt=conn.prepareStatement(sql.getEarlyWarningSQL());
            rs=pstmt.executeQuery();

            while(rs.next()){
            	earlyWarning = new EarlyWarning();
            	populate(earlyWarning,rs);
            	list1 = this.getOutAndIn(12,earlyWarning.getName(),date1);
            	list2 = this.getOutAndIn(13,earlyWarning.getName(),date2);            	
            	list3 = this.getStock(11,earlyWarning.getName());
            	list4 = this.getUpdate(ratio,earlyWarning.getName());
            	earlyWarning.setList1(list1);
            	earlyWarning.setList2(list2);
            	earlyWarning.setList3(list3);
            	earlyWarning.setList4(list4);  
            	map.put(earlyWarning.getName(),earlyWarning);                
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        if(map.size() == 0) map = null;
        
        return map;
    }
    
    public HashMap getBackTime(){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        PreparedStatement pstmt1=null;
        ResultSet rs1=null;
        HashMap map = new HashMap();
        List listNum = new ArrayList();
        EarlyWarning earlyWarning = null;
        List list5 = null;
        List tmpList = new ArrayList();
        /*
        try{
            conn=ds.getConnection();
            EarlyWarningSQL sql = null;
            TawWsDictDAO dao = new TawWsDictDAO(ds);
            List list = dao.getSelectBydictType(EarlyWarningSQL.COMPARE);
            LabelValueBean dict = null;
            TawWsDict dict1 = null;
            String date = "";
            String name = "";
            int num = 0;
            int tmp = 0;
            int id = 0;           
            for(int i = 0; i < list.size(); i++) {
            	sql = new EarlyWarningSQL();
            	dict = (LabelValueBean)list.get(i);
            	id = Integer.parseInt(dict.getValue())+EarlyWarningSQL.SUB;
            	dict1 = dao.retrieve(id,EarlyWarningSQL.TYPE);
            	date = StaticMethod.getDateString(-Integer.parseInt(dict1.getCode()));
            	sql.setTime3(date);
            	
            	pstmt=conn.prepareStatement(sql.getBack(dict1.getRemark()));
            	rs=pstmt.executeQuery();
            	while(rs.next()){
            		earlyWarning = new EarlyWarning();
            		populate(earlyWarning,rs);
            		listNum = this.getBack(dict1.getRemark(),14,date,earlyWarning.getName());
            		if(map.containsKey(earlyWarning.getName())) {
            			tmp = ((Integer)map.get(earlyWarning.getName())).intValue() + earlyWarning.getItem5();
            			map.remove(earlyWarning.getName());
            			earlyWarning.setItem5(tmp);  
            			tmpList = (List)map.get(earlyWarning.getName());
            			for(int n = 0; n < listNum.size(); n++) {
            				tmpList.add(listNum.get(n));
            			}
            			earlyWarning.setList5(tmpList);
            			
            			map.put(earlyWarning.getName(),earlyWarning);
            		} else {
            			earlyWarning.setList5(listNum);
            		}
            		
            		map.put(earlyWarning.getName(),earlyWarning);
                }
            }
            
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }*/
        if(map.size() == 0) map = null;
        
        return map;
    }
    
    public List getOutAndIn(int status,String deptName,String time){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List list = new ArrayList();
        EarlyWarning earlyWarning = null;
        TawSparepart tawSparepart = null;
        try{
            conn=ds.getConnection();
            EarlyWarningSQL sql = new EarlyWarningSQL();
            pstmt=conn.prepareStatement(sql.getOutAndIn(status,deptName,time));
            rs=pstmt.executeQuery();

            while(rs.next()){
            	tawSparepart = new TawSparepart();
            	populate(tawSparepart,rs);
            	list.add(tawSparepart);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        
        return list;
    }
    
    public List getStock(int status,String deptName){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List list = new ArrayList();
        EarlyWarning earlyWarning = null;
        TawStorage tawStorage = null;
        try{
            conn=ds.getConnection();
            EarlyWarningSQL sql = new EarlyWarningSQL();
            pstmt=conn.prepareStatement(sql.getStock(status,deptName));
            rs=pstmt.executeQuery();

            while(rs.next()){
            	tawStorage = new TawStorage();
            	populate(tawStorage,rs);
            	list.add(tawStorage);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        
        return list;
    }
    
    public List getUpdate(String ratio,String deptName){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List list = new ArrayList();
        EarlyWarning earlyWarning = null;
        TawStorage tawStorage = null;
        try{
            conn=ds.getConnection();
            EarlyWarningSQL sql = new EarlyWarningSQL();
            pstmt=conn.prepareStatement(sql.getUpdate(ratio,deptName));
            rs=pstmt.executeQuery();

            while(rs.next()){
            	tawStorage = new TawStorage();
            	populate(tawStorage,rs);
            	list.add(tawStorage);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        
        return list;
    }
    
    public List getBack(String name,int status,String time,String deptName){
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List list = new ArrayList();
        EarlyWarning earlyWarning = null;
        TawSparepart tawSparepart = null;
        try{
            conn=ds.getConnection();
            EarlyWarningSQL sql = new EarlyWarningSQL();
            pstmt=conn.prepareStatement(sql.getBack1(name,status,time,deptName));
            rs=pstmt.executeQuery();

            while(rs.next()){
            	tawSparepart = new TawSparepart();
            	populate(tawSparepart,rs);
            	list.add(tawSparepart);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        
        return list;
    }
    
    public String getEarlyWarningTime(int type1,int type2) {
    	String date = "";
    	/*TawWsDictDAO dao = new TawWsDictDAO(ds);
        TawWsDict dict  = null;*/
        String now = StaticMethod.getCurrentDateTime();
        try {
        	//dict = dao.retrieve(type1,type2);
        } catch(Exception e) {
        	
        }
       // date = StaticMethod.getDateString(-Integer.parseInt(dict.getCode()));
         
         return date;
    }
    
    public String getUpdateRatio(int type1,int type2) {
    	String ratio = "";
    	/*TawWsDictDAO dao = new TawWsDictDAO(ds);
        TawWsDict dict  = null;*/
        try {
        //	dict = dao.retrieve(type1,type2);
        } catch(Exception e) {
        	
        }
       // ratio = dict.getCode();
         
        return ratio;
    }
}
