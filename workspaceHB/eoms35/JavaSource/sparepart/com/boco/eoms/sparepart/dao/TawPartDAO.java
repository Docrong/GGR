package com.boco.eoms.sparepart.dao;

import java.util.*;
import java.sql.*;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.model.*;
import com.boco.eoms.common.util.*;

/**
 * <p>Title: ��Ʒ����</p>
 * <p>Description: EOMS��ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.7
 */

public class TawPartDAO extends DAO{

    public TawPartDAO(){
    }

    public TawPartDAO(ConnectionPool ds){
        super(ds);
    }

    /**
     * @see
     * @param condition
     * @param offset
     * @param length
     * @return
     */
    public List getPartList(String condition,int offset,int length){
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
//            String sql="SELECT  id ,objecttype,nettype,subdept,version,necode,supplier,"+
//                  "state,stateid,storage,objectname,to_char(intime,'YYYY-MM-DD hh24:mi:ss') intime,contract,operator,note,"+
//                  "serialno,proform,managecode,units,productcode,position,dept_name FROM taw_part "+
//                  condition;
            String sql="select id, objectname, operator,to_char(intime,'YYYY-MM-DD hh24:mi:ss') intime, note, necode,nettype, subdept, objecttype,state,storage,supplier,version,"+
                        "serialno,managecode,productcode,units, contract,"+
                        "supplierid,position,proform,loannum,repairnum,money,sheetid,"+
                        "nettypeid,subdeptid,"+
                        "necodeid,stateid,storageid,objecttypeid,outtime,accessory,thefile,proposer,company,companyid,objtype,repair_endtime,"+
                        "repairtime,fixe,checksum,parttype,describe FROM taw_part "+
          //  String sql="SELECT  * FROM taw_part "+
                        condition;
            pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
            rs=pstmt.executeQuery();
            if(offset>0){
                rs.absolute(offset);
            }
            int recCount=0;
            while((recCount++<length)&&rs.next()){
                TawPart tawPart=new TawPart();
                this.populate(tawPart,rs);
                tawPart.setIntime(tawPart.getIntime());
                System.out.print(rs.getString("intime"));
                list.add(tawPart);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;

    }
    /**add by wqw Ϊ�˻�ȡ��ʷ��¼
     * @see
     * @param condition
     * @param offset
     * @param length
     * @return
     */
    public List getOldPartList(String condition){
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();         
            String sql="select * from vaw_sp_order_detail "+condition;
            pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
            rs=pstmt.executeQuery();
            
            
            while(rs.next()){
            	VawOrderDetail vawOrderDetail=new VawOrderDetail();
            	
                //this.populate(vawOrderDetail,rs);               
            	vawOrderDetail.setCharge(rs.getString("charge"));
            	vawOrderDetail.setOperater(rs.getString("operater"));
            	vawOrderDetail.setProposer(rs.getString("proposer"));
            	vawOrderDetail.setProp_tel(rs.getString("prop_tel"));
            	vawOrderDetail.setProp_dept(rs.getString("prop_dept"));
            	vawOrderDetail.setStartdate(rs.getString("startdate"));
            	vawOrderDetail.setOrder_id(rs.getString("order_id"));
            	vawOrderDetail.setStorageid(rs.getString("storageid"));
            	vawOrderDetail.setStorage_name(rs.getString("storage_name"));
            	vawOrderDetail.setObjectname(rs.getString("objectname"));
            	vawOrderDetail.setSerialno(rs.getString("serialno"));
            	vawOrderDetail.setOrg_serial_no(rs.getString("org_serial_no"));
            	vawOrderDetail.setNew_serial_no(rs.getString("new_serial_no"));
            	vawOrderDetail.setSupplier(rs.getString("supplier"));
            	vawOrderDetail.setSheetid(rs.getString("sheetid"));
            	vawOrderDetail.setOrder_type(rs.getString("order_type"));
            	vawOrderDetail.setOrder_name(rs.getString("order_name"));
            	vawOrderDetail.setOrder_part_state(rs.getString("order_part_state"));
            	vawOrderDetail.setOrder_part_state_name(rs.getString("order_part_state_name"));            	
                list.add(vawOrderDetail);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;

    }
    /**
     *
     * @param SQL
     * @return
     * @throws SQLException
     */
    public List getSparepart(String SQL) throws SQLException{
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select * from taw_part "+
//                  "SELECT  id ,objecttype,nettype,subdept,necode,supplier,"+
//                  "state,storage,objectname,intime,contract,operator,"+
//                  "serialno,note,managecode,units,productcode,supplierid,"+
//                  "position,contract,money, "+
//                  "proform, proform_flag, "+
//                  "warranty_flag,warranty_name,"+
//                  "stopproduct_flag,stopproduct_name,"+
//                  "dept_id,dept_name,version "+
//                  ",nettypeid,necodeid,supplierid,stateid,storageid,objecttypeid "+
//                  ",org_serial_no,accessory,thefile,sheetid,outtime,proposer, companyid,objtype,repair_endtime,repairtime,fixeid "+
//                  " FROM taw_part "+
                  SQL;
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                TawPart tawPart=new TawPart();
                populate(tawPart,rs);
                tawPart.setIntime(rs.getString("intime"));
                System.out.print(rs.getString("intime"));
                tawPart.setOuttime(rs.getString("outtime"));
                
            switch (StaticMethod.null2int(tawPart.getState())){
            case 11:
                tawPart.setState("新件入库");
              case 12:
                tawPart.setState("维修入库");
              case 13:
                tawPart.setState("维修出库");
              case 14:
                tawPart.setState("其他出库");
              case 15:
                tawPart.setState("报废出库");
              case 16:
                tawPart.setState("扩容出库");
              case 17:
                  tawPart.setState("其他出库");
              case 19:
                  tawPart.setState("其他入库");                  
            }
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

    public HashMap getCname(int id){
        HashMap map=new HashMap();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select id,cname from taw_sp_classmsg where parent_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            while(rs.next()){
                map.put(StaticMethod.strFromDBToPage(rs.getString(2)),
                        Integer.toString(rs.getInt(1)));
                // System.out.println(StaticMethod.strFromDBToPage(rs.getString(2)));
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
        return map;
    }

    public HashMap getStorage() throws SQLException{
        HashMap map=new HashMap();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select id,storagename from taw_sp_storage";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                map.put(StaticMethod.strFromDBToPage(rs.getString(2)),
                        Integer.toString(rs.getInt(1)));
                //System.out.println(StaticMethod.strFromDBToPage(rs.getString(2)));
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
        return map;
    }

    public HashMap getType() throws SQLException{
        HashMap map=new HashMap();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select id,cname from taw_sp_tree";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                map.put(StaticMethod.strFromDBToPage(rs.getString(2)),
                        Integer.toString(rs.getInt(1)));
                //System.out.println(StaticMethod.strFromDBToPage(rs.getString(2)));
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
        return map;
    }

    public HashMap getPartId() throws SQLException{
        HashMap map = new HashMap();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select id,serialno from taw_sp_sparepart";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                map.put(StaticMethod.strFromDBToPage(rs.getString(2)),
                        Integer.toString(rs.getInt(1)));
                //System.out.println(StaticMethod.strFromDBToPage(rs.getString(2)));
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
        return map;
    }
    public HashMap getPartId_formanagecode() throws SQLException{
        HashMap map = new HashMap();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select id,managecode from taw_sp_sparepart";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                map.put(StaticMethod.strFromDBToPage(rs.getString(2)),
                        Integer.toString(rs.getInt(1)));
                //System.out.println(StaticMethod.strFromDBToPage(rs.getString(2)));
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
        return map;
    }    

    public boolean update(List list) throws SQLException{
        boolean Flag = false;
        TawTreeDao dao=new TawTreeDao();
        HashMap supplier=getCname(6);
        HashMap state=getCname(10);
        HashMap storage=getStorage();
        HashMap proform=getCname(440);//��������״̬
        HashMap fixe=getCname(460);//�����
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        conn=ds.getConnection();
        try{
          int intList=list.size();
          for(int i=0;i<intList;i++){
              String sql = "update taw_sp_sparepart set nettype=?,necode=?,objecttype=?"
                  +",supplier=?,storageid=?,objectname=?,serialno=?,version=?,state=?"
                  +",proform=?,hwversion=?,updatetime=?,operator=?,position=?,note=?,subDept=? "
                  +" where id=?";

              PreparedStatement stmt=conn.prepareStatement(sql);
              TawPart tawPart=(TawPart)list.get(i);
              String sumId=dao.getTreeThreeId(StaticMethod.dbNStrRev(tawPart.getNettype()),StaticMethod.dbNStrRev(tawPart.getSubdept()),
                                              StaticMethod.dbNStrRev(tawPart.getNecode()),
                                              StaticMethod.dbNStrRev(tawPart.getObjecttype()));
              if(!sumId.equals("")){
                  String id[]=sumId.split("_");

                  stmt.setInt(1,Integer.parseInt(id[0]));
                  stmt.setInt(2,Integer.parseInt(id[2]));
                  stmt.setInt(3,Integer.parseInt(id[3]));
                  stmt.setInt(4,Integer.parseInt((String)supplier.get(tawPart.getSupplier())));
                  stmt.setInt(5,Integer.parseInt((String)storage.get(tawPart.getStorage())));
                  stmt.setString(6,StaticMethod.dbNStrRev(tawPart.getObjectname()));
                  stmt.setString(7,StaticMethod.dbNStrRev(tawPart.getSerialno()));
                  stmt.setString(8,StaticMethod.dbNStrRev(tawPart.getVersion()));
                  stmt.setInt(9,Integer.parseInt((String)state.get(tawPart.getState())));
//                  if ( tawPart.getProform().equals("��")){
//                      stmt.setInt(10,1);
//                    }else if ( tawPart.getProform().equals("����")){
//                      stmt.setInt(10,2);
//                    }
                  stmt.setInt(10,Integer.parseInt((String)proform.get(tawPart.getProform())));                  
//                    stmt.setInt(10,Integer.parseInt((String)state.get(tawPart.getProform())));
//                    stmt.setString(11,StaticMethod.dbNStrRev(tawPart.getHwversion()));
                  stmt.setString(11,StaticMethod.dbNStrRev(tawPart.getHwversion()));
                  stmt.setString(12,tawPart.getUpdatetime());
                  System.out.println(tawPart.getUpdatetime());
                  stmt.setString(13,StaticMethod.dbNStrRev(tawPart.getOperator()));
                  stmt.setString(14,StaticMethod.dbNStrRev(tawPart.getPosition()));
                  stmt.setString(15,StaticMethod.dbNStrRev(tawPart.getNote()));
                  stmt.setInt(16,Integer.parseInt(id[1]));
                  stmt.setInt(17,tawPart.getId());
                  stmt.executeUpdate();
                  conn.commit();
              }
          }
          close(pstmt);
          Flag = true;
        }
        catch(SQLException ex){
          rollback(conn);
          ex.printStackTrace();
        }
        finally{
          close(pstmt);
          close(conn);
        }
        return Flag;
    }

    public int[] insert(List list) throws SQLException{
        int[] counts=null;
        TawTreeDao dao=new TawTreeDao();
        HashMap supplier=getCname(6);
        HashMap state=getCname(10);
        HashMap storage=getStorage();
        HashMap proform=getCname(440);//��������״̬
        HashMap company=getCname(450);//����˾
        HashMap fixe=getCname(460);//�����
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        try{
            //,Units,Operator,Note,Intime,State
            conn=ds.getConnection();
            //edit by duanbin  2006-07-23 ȥ��hwversion�������ڣ�
            /*
            String sql= "insert into taw_sp_sparepart (nettype,necode,objecttype,supplier,storageid"
                +",objectname,serialno,version,state,proform,intime,operator"
                +",position,note,loannum,repairnum,subDept) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            */
            String sql= "insert into taw_sp_sparepart (nettype,necode,objecttype,supplier,storageid,"
                       +"objectname,serialno,version,state,proform,"
                       +"intime,operator,position,note,loannum,"
                       +"repairnum,subDept,proposer,company,objtype,"
                       +"repair_endtime,repairtime,fixe,managecode,deleted,units,parttype,describe,money) "
                       +"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt=conn.prepareStatement(sql);
            int intList=list.size();
            for(int i=0;i<intList;i++){
                TawPart tawPart=(TawPart)list.get(i);
                String sumId=dao.getTreeThreeId(StaticMethod.dbNStrRev(tawPart.getNettype()),StaticMethod.dbNStrRev(tawPart.getSubdept()),
                                                StaticMethod.dbNStrRev(tawPart.getNecode()),
                                                StaticMethod.dbNStrRev(tawPart.getObjecttype()));
                if(!sumId.equals("")){
                    String id[]=sumId.split("_");

                    stmt.setInt(1,Integer.parseInt(id[0]));
                    stmt.setInt(2,Integer.parseInt(id[2]));
                    stmt.setInt(3,Integer.parseInt(id[3]));
                    stmt.setInt(4,Integer.parseInt((String)supplier.get(tawPart.getSupplier())));
                    stmt.setInt(5,Integer.parseInt((String)storage.get(tawPart.getStorage())));
                    stmt.setString(6,StaticMethod.dbNStrRev(tawPart.getObjectname()));
                    stmt.setString(7,StaticMethod.dbNStrRev(tawPart.getSerialno()));
                    stmt.setString(8,StaticMethod.dbNStrRev(tawPart.getVersion()));
                    stmt.setInt(9,Integer.parseInt((String)state.get(tawPart.getState())));
//                    if ( tawPart.getProform().equals("��")){
//                      stmt.setInt(10,1);
//                    }else if ( tawPart.getProform().equals("����")){
//                      stmt.setInt(10,2);
//                    }
                    stmt.setInt(10,Integer.parseInt((String)proform.get(tawPart.getProform())));
//                    stmt.setInt(10,Integer.parseInt((String)state.get(tawPart.getProform())));
//                    stmt.setString(11,StaticMethod.dbNStrRev(tawPart.getHwversion()));
                    stmt.setString(11,tawPart.getEtime());
                    stmt.setString(12,StaticMethod.dbNStrRev(tawPart.getOperator()));
                    stmt.setString(13,StaticMethod.dbNStrRev(tawPart.getPosition()));
                    stmt.setString(14,StaticMethod.dbNStrRev(tawPart.getNote()));
                    stmt.setInt(15,0);
                    stmt.setInt(16,0);
                    stmt.setInt(17,Integer.parseInt(id[1]));
                    //wqw add 20070706
                    stmt.setString(18, StaticMethod.dbNStrRev(tawPart.getProposer()));
                    stmt.setInt(19, Integer.parseInt((String)company.get(tawPart.getCompany())));
                    stmt.setString(20, StaticMethod.dbNStrRev(tawPart.getObjtype()));
                    stmt.setString(21,StaticMethod.dbNStrRev(tawPart.getRepair_endtime()));
                    stmt.setString(22, StaticMethod.dbNStrRev(tawPart.getRepairtime()));
                    stmt.setInt(23, Integer.parseInt((String)fixe.get(tawPart.getFixe())));
                    stmt.setString(24, StaticMethod.dbNStrRev(tawPart.getManagecode()));
                    stmt.setString(25, "0");//ɾ���־
                    stmt.setString(26, tawPart.getUnits());//��λ
                    stmt.setInt(27, tawPart.getParttype());//�������
                    stmt.setString(28, tawPart.getDescribe());//��Ҫ��������
                    stmt.setString(29, tawPart.getMoney());//���
                    stmt.addBatch();
                }
            }
            counts=stmt.executeBatch();
            conn.commit();
            close(pstmt);
        }
        catch(SQLException ex){
            rollback(conn);
            ex.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
        return counts;
    }

}
