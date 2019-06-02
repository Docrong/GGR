package com.boco.eoms.sparepart.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.sparepart.controller.TawSparepartForm;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sparepart.model.TawPart;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.sparepart.model.TawSparepart;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawSparepartDAO extends DAO{
    public TawSparepartDAO(ConnectionPool ds){ 
        super(ds);
    }

    /**
     * @see insert new sparepart
     * @param form
     */
    public void insertPart(String managecode,String supplierid,
                           String objectname,String operator,String note,
                           String storageid,String serialno,String productcode,String version,
                           String[] id,int state,int proform,String position,
                           String contract,String money,String sheetid,
                           int warrantyFlag, int stopproductFlag,String accessory,String thefile,
                           String proposer,String company,String objtype,String repair_endtime,String repairtime,String fixe,String units){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql=
              "INSERT INTO taw_sp_sparepart(nettype,managecode,objecttype,necode,"+
              "supplier,objectname,operator,note,storageid,intime,state,serialno,"+
              " productcode,proform,position,loannum,repairnum,contract,money,sheetid, "+
              " warranty_flag, stopproduct_flag, org_serial_no,accessory,thefile,subDept,version, "+
              " proposer,company,objtype,repair_endtime,repairtime,fixe,units)"+
              " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,Integer.parseInt(id[0])); //objecttype==objectname
            pstmt.setString(2,managecode);
            pstmt.setInt(3,Integer.parseInt(id[3]));
            pstmt.setInt(4,Integer.parseInt(id[2]));
            pstmt.setInt(26,Integer.parseInt(id[1]));
            pstmt.setInt(5,Integer.parseInt(supplierid));
            pstmt.setString(6,objectname);
            pstmt.setString(7,operator);
            pstmt.setString(8,note);
            pstmt.setInt(9,Integer.parseInt(storageid));
            pstmt.setTimestamp(10,StaticMethod.getTimestamp());
            pstmt.setInt(11,state);
            pstmt.setString(12,serialno);
            pstmt.setString(13,productcode);
            pstmt.setInt(14,proform);
            pstmt.setString(15,position);
            pstmt.setInt(16,0);
            pstmt.setInt(17,0);
            pstmt.setString(18,contract);
            pstmt.setString(19,money);
            pstmt.setString(20,sheetid);
            pstmt.setInt(21, warrantyFlag);
            pstmt.setInt(22, stopproductFlag);
            pstmt.setString(23,serialno);
            pstmt.setString(24,accessory);
            pstmt.setString(25,thefile);
            pstmt.setString(27,version);
            pstmt.setString(28, proposer);
            pstmt.setString(29, company);
            pstmt.setString(30, objtype);
            pstmt.setString(31, repair_endtime);
            pstmt.setString(32, repairtime);
            pstmt.setString(33,fixe);
            pstmt.setString(34,units);
            pstmt.executeUpdate();
            conn.commit();            
        }
        catch(SQLException sqle){
            System.out.println(sqle);
            sqle.printStackTrace();
            rollback(conn);
        }
        finally{
            close(pstmt);
            close(conn);
        }
    }
    
    /**
     * �����������ר��.. dww 070804
     * @see insert new sparepart
     * @param form
     */
    public void insertPart_away(String managecode,String supplierid,
                           String objectname,String operator,String note,
                           String storageid,String serialno,String productcode,String version,
                           String[] id,int state,int proform,String position,
                           String contract,String money,String sheetid,
                           int warrantyFlag, int stopproductFlag,String accessory,String thefile,
                           String proposer,String company,String objtype,String repair_endtime,
                           String repairtime,String fixe,String deleted,String units,int partType,String describe){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql=
              "INSERT INTO taw_sp_sparepart(nettype,managecode,objecttype,necode,"+
              "supplier,objectname,operator,note,storageid,intime,state,serialno,"+
              " productcode,proform,position,loannum,repairnum,contract,money,sheetid, "+
              " warranty_flag, stopproduct_flag, org_serial_no,accessory,thefile,subDept,version, "+
              " proposer,company,objtype,repair_endtime,repairtime,fixe,deleted,units,parttype,describe)"+
              " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,Integer.parseInt(id[0])); //objecttype==objectname
            pstmt.setString(2,managecode);
            pstmt.setInt(3,Integer.parseInt(id[3]));
            pstmt.setInt(4,Integer.parseInt(id[2]));
            pstmt.setInt(26,Integer.parseInt(id[1]));
            pstmt.setInt(5,Integer.parseInt(supplierid));
            pstmt.setString(6,objectname);
            pstmt.setString(7,operator);
            pstmt.setString(8,note);
            pstmt.setInt(9,Integer.parseInt(storageid));
            pstmt.setTimestamp(10,StaticMethod.getTimestamp());
            pstmt.setInt(11,state);
            pstmt.setString(12,serialno);
            pstmt.setString(13,productcode);
            pstmt.setInt(14,proform);
            pstmt.setString(15,position);
            pstmt.setInt(16,0);
            pstmt.setInt(17,0);
            pstmt.setString(18,contract);
            pstmt.setString(19,money);
            pstmt.setString(20,sheetid);
            pstmt.setInt(21, warrantyFlag);
            pstmt.setInt(22, stopproductFlag);
            pstmt.setString(23,serialno);
            pstmt.setString(24,accessory);
            pstmt.setString(25,thefile);
            pstmt.setString(27,version);
            pstmt.setString(28, proposer);
            pstmt.setString(29, company);
            pstmt.setString(30, objtype);
            pstmt.setString(31, repair_endtime);
            pstmt.setString(32, repairtime);
            pstmt.setString(33,fixe);
            pstmt.setString(34, deleted);//���ɾ���־.
            pstmt.setString(35, units);//��ӵ�λ.          
            pstmt.setInt(36, partType);//�����������    
            pstmt.setString(37, describe);//�����Ҫ��������
            pstmt.executeUpdate();
            conn.commit();            
        }
        catch(SQLException sqle){
            System.out.println(sqle);
            sqle.printStackTrace();
            rollback(conn);
        }
        finally{
            close(pstmt);
            close(conn);
        }
    }

    public void insertPart1(TawPart tawPart){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;
        
        String sql=
              "INSERT INTO taw_sp_sparepart(nettype,managecode,objecttype,necode,"+
              "supplier,objectname,operator,note,storageid,intime,state,serialno,"+
              " productcode,proform,position,loannum,repairnum,contract,money,sheetid,"+
              " warranty_flag, stopproduct_flag,borrow_state,version,org_serial_no,subDept,"+
              " proposer,company,objtype,repair_endtime,repairtime,fixe )"+
              " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,tawPart.getNettypeid());
            pstmt.setInt(26,tawPart.getSubdeptid());
            pstmt.setString(2,tawPart.getManagecode());
            pstmt.setLong(3,tawPart.getObjecttypeid());
            pstmt.setInt(4,tawPart.getNecodeid());
            pstmt.setInt(5,Integer.parseInt(tawPart.getSupplierid()));
            pstmt.setString(6,tawPart.getObjectname());
            pstmt.setString(7,tawPart.getOperator());
            pstmt.setString(8,tawPart.getNote());
            pstmt.setInt(9,tawPart.getStorageid());
            pstmt.setTimestamp(10,StaticMethod.getTimestamp());
            pstmt.setInt(11,tawPart.getStateid());
            pstmt.setString(12,tawPart.getSerialno());
            pstmt.setString(13,tawPart.getProductcode());
            pstmt.setInt(14,tawPart.getProformFlag());
            pstmt.setString(15,tawPart.getPosition());
            pstmt.setInt(16,tawPart.getLoannum());
            pstmt.setInt(17,tawPart.getRepairnum());
            pstmt.setString(18,tawPart.getContract());
            pstmt.setString(19,tawPart.getMoney());
            pstmt.setString(20,tawPart.getSheetid());
            pstmt.setInt(21,tawPart.getWarrantyFlag());
            pstmt.setInt(22,tawPart.getStopproductFlag());
            pstmt.setInt(23,tawPart.getBorrowState());
            pstmt.setString(24,tawPart.getVersion());
            pstmt.setString(25,tawPart.getOrgSerialNo());
            
            pstmt.setString(26, tawPart.getProposer());
            pstmt.setString(27, tawPart.getCompany());
            pstmt.setString(28, tawPart.getObjtype());
            pstmt.setString(29, tawPart.getRepair_endtime());
            pstmt.setString(30, tawPart.getRepairtime());
            pstmt.setString(31, tawPart.getFixe());
            pstmt.executeUpdate();
            conn.commit();
            
        }
        catch(SQLException sqle){
            rollback(conn);
            sqle.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
    }

    /**
     * select table taw_sp_sparepart
     * @param SQL
     *  @return TawSparepart
     */
    public TawSparepart getSparepart(int id){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        TawSparepart spartpart=new TawSparepart();
        ResultSet rs=null;
        PreparedStatement pstmt=null;

        String sql="select * from taw_sp_sparepart where id="+id;
        System.out.println("sqprepqrtBo:"+sql);
        try{
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                //rs.next();
                populate(spartpart,rs);
            }

        }
        catch(SQLException sqle){
            rollback(conn);
            System.out.println(sqle);
            sqle.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
		return spartpart;
    }
    /**
     * select table taw_sp_sparepart
     * @param SQL
     *  @return TawSparepart
     */
    public int getSparepartIDbyManagecode(String managecode){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        TawSparepart spartpart=new TawSparepart();
        ResultSet rs=null;
        PreparedStatement pstmt=null;
        int id=0;
        String sql="select id from taw_sp_sparepart where managecode="+managecode;
        System.out.println("sqprepqrtBo:"+sql);
        try{
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
            	id=rs.getInt(1);
            }
            
        }
        catch(SQLException sqle){
            rollback(conn);
            System.out.println(sqle);
            sqle.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
		return id;
    }    
    /**
     * select table taw_sp_sparepart
     * @param SQL
     * @return Tawpart
     */
    public TawPart getSparepart_forTawPart(int id){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        TawPart part=new TawPart();
        ResultSet rs=null;
        PreparedStatement pstmt=null;

//        String sql="SELECT a.ID, a.org_serial_no, a.objecttype, a.nettype, a.subdept, a.necode,"+
//	          " a.VERSION, a.supplier, a.state, a.storageid, a.serialno, a.OPERATOR,"+
//	          " a.intime, a.outtime, a.note, a.objectname, a.productcode,"+
//	          " a.managecode, a.contract, a.units, b.cname, c.cname, i.cname,"+
//	          " d.cname, e.cname, DECODE (a.borrow_state, 2, '����', f.cname),"+
//	          " g.storagename, a.proform,"+
//	          " DECODE (a.proform, 1, '��', 2, '����'), a.POSITION, a.loannum,"+
//	          " a.repairnum, a.money, a.sheetid, a.warranty_flag,"+
//	          " DECODE (a.warranty_flag, 1, '������', 2, '������'),"+
//	          " a.stopproduct_flag,"+
//	          " DECODE (a.stopproduct_flag, 1, 'δͣ��', 2, '��ͣ��'), h.dept_id,"+
//	          " h.dept_name, a.borrow_state,"+
//	          " DECODE (a.borrow_state, 1, '����', 2, '����', 3, '���'),"+
//	          " a.accessory, a.thefile, a.fixe, a.repairtime, a.repair_endtime,"+
//	          " a.objtype, a.company, a.proposer"+
//	          " FROM TAW_SP_SPAREPART a,"+
//	          " TAW_SP_TREE b,"+
//	          " TAW_SP_TREE c,"+
//	          " TAW_SP_TREE d,"+
//	          " TAW_SP_TREE i,"+
//	          " TAW_SP_CLASSMSG e,"+
//	          " TAW_SP_CLASSMSG f,"+
//	          " TAW_SP_STORAGE g,"+
//	          " TAW_DEPT h"+
//	          " WHERE a.objecttype = b.ID"+
//	          " AND a.nettype = c.ID"+
//	          " AND a.subdept = i.ID"+
//	          " AND a.necode = d.ID"+
//	          " AND a.supplier = e.ID"+
//	          " AND a.state = f.ID"+
//	          " AND a.storageid = g.ID"+
//	          " AND g.dept_id = h.dept_id"+
//	          " AND a.id="+id;
        String sql="select * from taw_part_all where id="+id;
        System.out.println("sqprepqrtBo:"+sql);
        try{
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            //conn.commit();
            while(rs.next()){
            	populate(part,rs);
            }
            
        }
        catch(SQLException sqle){
            rollback(conn);
            System.out.println(sqle);
            sqle.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
		return part;
    }    
    /**
     * update table taw_sp_sparepart
     * @param SQL
     */
    public void updateSparePart(String SQL){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql="update taw_sp_sparepart "+SQL;
        System.out.println("sqprepqrtBo:"+sql);
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            conn.commit();
        }
        catch(SQLException sqle){
            rollback(conn);
            System.out.println(sqle);
            sqle.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
    }

    //���±�����Ϣ
    public void updatePart(TawPart tawPart){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql=
              "update taw_sp_sparepart set nettype=?,necode=?,objecttype=?,"+
              "objectname=?,Supplier=?,productcode=?,managecode=?,Serialno=?,"+
              "Operator=?,position=?,note=?,contract=?,money=?,"+
              "proform=?, warranty_flag=?, stopproduct_flag=?,subDept=?, "+
              "proposer=?,company=?,objtype=?,repair_endtime=?,repairtime=?,fixe=?,version=?,describe=?"+
              " where id=? ";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,tawPart.getNettype());
            pstmt.setString(2,tawPart.getNecode());
            pstmt.setString(3,tawPart.getObjecttype());
            pstmt.setString(4,tawPart.getObjectname());
            pstmt.setString(5,tawPart.getSupplier());
            pstmt.setString(6,tawPart.getProductcode());
            pstmt.setString(7,tawPart.getManagecode());
            pstmt.setString(8,tawPart.getSerialno());
            pstmt.setString(9,tawPart.getOperator());
            pstmt.setString(10,tawPart.getPosition());
            pstmt.setString(11,tawPart.getNote());
            pstmt.setString(12,tawPart.getContract());
            pstmt.setString(13,tawPart.getMoney());
            pstmt.setInt(14,tawPart.getProformFlag());
            pstmt.setInt(15,tawPart.getWarrantyFlag());
            pstmt.setInt(16,tawPart.getStopproductFlag());
            pstmt.setString(17,tawPart.getSubdept());
            
            pstmt.setString(18, tawPart.getProposer());
            pstmt.setString(19, tawPart.getCompany());
            pstmt.setString(20, tawPart.getObjtype());
            pstmt.setString(21, tawPart.getRepair_endtime());
            pstmt.setString(22, tawPart.getRepairtime());
            pstmt.setString(23, tawPart.getFixe());
            pstmt.setString(24, tawPart.getVersion());
            pstmt.setString(25, tawPart.getDescribe());
            
            pstmt.setInt(26,tawPart.getId());
            pstmt.executeUpdate();
            conn.commit();

        }
        catch(SQLException sqle){
            rollback(conn);
            sqle.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
    }

    //ɾ���
    public void deleteSparePart(String SQL){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql="delete from taw_sp_sparepart "+SQL;
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            conn.commit();
        }
        catch(SQLException sqle){
            rollback(conn);
            System.out.println(sqle);
            sqle.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
    }
    //ȡ�ñ���ԭ�еĽ�����
    public int getLoanNum (String id){
        int LoanNum = 0;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql = "select loannum from taw_sp_sparepart where id = " + id;
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                LoanNum = rs.getInt(1);
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
        return LoanNum;
    }

    //ȡ�ñ���ԭ�е�ά�޴���
    public int getRepairNum (String id){
        int RepairNum = 0;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql = "select repairnum from taw_sp_sparepart where id = " + id;
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                RepairNum = rs.getInt(1);
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
        return RepairNum;
    }

/*�õ�state��ֵ*/
    public List getStateList() throws SQLException{
       ArrayList list=new ArrayList();
       com.boco.eoms.db.util.BocoConnection conn=null;
       PreparedStatement pstmt=null;
       ResultSet rs=null;
       int i=0;
       try{
           conn=ds.getConnection();
           String sql=
                 "SELECT distinct state FROM taw_sp_sparepart ";
           pstmt=conn.prepareStatement(sql);
           rs=pstmt.executeQuery();
           while(rs.next()){
               list.add(rs.getObject(++i));
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
    /*�õ�objType��ֵ*/
    public List getobjTypeList(String storage) throws SQLException{
       ArrayList list=new ArrayList();
       com.boco.eoms.db.util.BocoConnection conn=null;
       PreparedStatement pstmt=null;
       ResultSet rs=null;
       int i=0;
       try{
           conn=ds.getConnection();
           String sql=
                 "SELECT distinct objtype FROM taw_sp_sparepart where storageid=?";
           pstmt=conn.prepareStatement(sql);
           pstmt.setString(1, storage);
           rs=pstmt.executeQuery();
           while(rs.next()){
               list.add(rs.getString(1));
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
    /*�õ�Position��ֵ*/
    public List getPositionList(String storage) throws SQLException{
       ArrayList list=new ArrayList();
       com.boco.eoms.db.util.BocoConnection conn=null;
       PreparedStatement pstmt=null;
       ResultSet rs=null;
       int i=0;
       try{
           conn=ds.getConnection();
           String sql=
                 "SELECT distinct position FROM taw_sp_sparepart  where storageid=?";
           pstmt=conn.prepareStatement(sql);
           pstmt.setString(1, storage);
           rs=pstmt.executeQuery();
           while(rs.next()){
               list.add((String)rs.getString(1));
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
  /**
   * �����޸�����.
   * @param id
   */
  public void updateSpartpartRepairnum(int sparepart_id){
      com.boco.eoms.db.util.BocoConnection conn=null;
      CallableStatement call= null;
      
      try{
    	  conn=ds.getConnection();
    	  String sql =StaticMethod.getDBProcedure("Taw_Sparepart.Update_Sparepart_Repairsum(?)");
    	  call=conn.prepareCall(sql);
    	  call.setInt(1, sparepart_id);
    	  call.execute();
    	  call.close();
    	  conn.commit();
      }
      catch(SQLException e){
          close(call);
          rollback(conn);
          e.printStackTrace();
      }
      finally{
          close(conn);
      }
  }
  /**
   * ���½��ô���.
   * @param id
   */
  public void updateSpartpartLoannum(int sparepart_id){
      com.boco.eoms.db.util.BocoConnection conn=null;
      CallableStatement call= null;
      
      try{
    	  conn=ds.getConnection();
    	  String sql =StaticMethod.getDBProcedure("Taw_Sparepart.Update_Sparepart_Loansum(?)");
    	  call=conn.prepareCall(sql);
    	  call.setInt(1, sparepart_id);
    	  call.execute();
    	  call.close();
    	  conn.commit();
      }
      catch(SQLException e){
          close(call);
          rollback(conn);
          e.printStackTrace();
      }
      finally{
          close(conn);
      }
  }
  /**
   * ���¼�����.
   * @param id
   */
  public void updateSpartpartChecksum(int sparepart_id){
      com.boco.eoms.db.util.BocoConnection conn=null;
      CallableStatement call= null;
      
      try{
    	  conn=ds.getConnection();
    	  String sql =StaticMethod.getDBProcedure("Taw_Sparepart.Update_Sparepart_Checksum(?)");
    	  call=conn.prepareCall(sql);
    	  call.setInt(1, sparepart_id);
    	  call.execute();
    	  call.close();
    	  conn.commit();
      }
      catch(SQLException e){
          close(call);
          rollback(conn);
          e.printStackTrace();
      }
      finally{
          close(conn);
      }
  }  
}
