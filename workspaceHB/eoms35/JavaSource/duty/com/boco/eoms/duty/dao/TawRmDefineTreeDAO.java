package com.boco.eoms.duty.dao;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.sql.*;
import java.util.*;
import java.text.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.db.util.BocoConnection ;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;


public class TawRmDefineTreeDAO  extends DAO{
  public BocoConnection BocoConn;
  String dateFormat="yyyy-mm-dd";
  public TawRmDefineTreeDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
    init();
  }
  public TawRmDefineTreeDAO(BocoConnection conn) {
   init();
   BocoConn=conn;
   if(BocoConn==null)
     BocoConn=ds.getConnection();
    }

  private void init()
  {
   if("informix".equals(StaticMethod.getDbType()))
     dateFormat="%Y-%m-%d";
  }
//定义一些接口
  /*
  a.新增一个结点
  b.更新一个节点的sub_id以及sub_num,parm:parent_id,sub_id
  c.判断某个节点是否是别一个节点的子节点
  d.设置删除标识(其下的子节点也应该设置)
  e.修改名称,设置默认值,设置是否是叶子
  f.节点id的命名规则
    机房的下一节点:即parent_id=-1
    三位000-999
    在这一节点下的所有节点都是两位00-99
  */


 /**
  * @see 根据父结点得到其当前的子结点
  * @param parent_id
  * @return
  * @throws SQLException
  */
 public String getID(String parent_id,int room_id) throws SQLException {
         //com.boco.eoms.db.util.BocoConnection conn = null;
         PreparedStatement pstmt = null;
         PreparedStatement pstmtSub = null;
         ResultSet rs = null;
         int tableCount=0;
         String sId="";
   String sql=null;
   try {
     //conn = ds.getConnection();
     sql="SELECT MAX(node_Id) FROM taw_rm_defineTree where parent_id=? and room_id=?";
     pstmt = BocoConn.prepareStatement(sql);
     pstmt.setString(1,parent_id);
     pstmt.setInt(2,room_id);
     rs = pstmt.executeQuery();
     if(rs.next()) {
       tableCount = rs.getInt(1);
       tableCount++;
     }
     close(rs);
     close(pstmt);
     sId=String.valueOf(tableCount);
     char[] zeros={'0','0','0'};
     if("0".equals(parent_id))
     {
     if(sId.length()<3)
       sId=String.valueOf(zeros,0,3-sId.length())+sId;
     }else
     {
     if(sId.length()>2)
       sId = parent_id+sId.substring(sId.length()-2);
     else
       sId=parent_id+String.valueOf(zeros,0,2-sId.length())+sId;
     }
   } catch (SQLException e) {
     close(rs);
     close(pstmt);
     e.printStackTrace();
     throw new SQLException(e.getMessage());
   }
     return sId;
 }



 /**
  * @see 新增一个节点
  * @param tawRmDefineTree
  * @throws SQLException
  */
    public String insert(TawRmDefineTree tawRmDefineTree) throws SQLException {
      String sql;
      sql = "INSERT INTO  taw_rm_defineTree(node_id,name,room_id,parent_id,sub_id,defalut,isleaf,cycles,lines,specility) values(?,?,?,?,?,?,?,?,?,?)";
      //com.boco.eoms.db.util.BocoConnection conn = null;
      PreparedStatement pstmt = null;
      String node_id="";
      try {
        node_id=tawRmDefineTree.getParentId();
        node_id=getID(node_id,tawRmDefineTree.getRoomId());
        //conn = ds.getConnection();
        pstmt = BocoConn.prepareStatement(sql);
        pstmt.setString(1, node_id);
        pstmt.setString(2,tawRmDefineTree.getName());
        pstmt.setInt(3,tawRmDefineTree.getRoomId());
        pstmt.setString(4, tawRmDefineTree.getParentId());
        pstmt.setString(5,",");
        pstmt.setString(6,tawRmDefineTree.getDefalut());
        pstmt.setInt(7,tawRmDefineTree.getIsLeaf());
        pstmt.setString(8,tawRmDefineTree.getCycles());
        pstmt.setInt(9,tawRmDefineTree.getLines());
        pstmt.setString(10,tawRmDefineTree.getSpecility());
        pstmt.executeUpdate();

        pstmt.close();
      } catch (SQLException sqle) {
        sqle.printStackTrace();
        close(pstmt);
        throw new SQLException(sqle.getMessage());
      }
      return node_id;
    }

    /**
     * @see 更新sub_id & subNum
     * @param id
     * @throws SQLException
     */
    public void AddSubId(String  node_id,String sub_id,int room_id) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs=null;
      String sql=null;
      String subids="";
      try {
        sql="SELECT sub_id FROM taw_rm_definetree where node_id='"+node_id+"' and room_id="+room_id;
        pstmt = BocoConn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next())
        {
          subids=rs.getString("sub_id").trim();
          rs.close();
          pstmt.close();
          subids=subids+sub_id+",";
          sql = "UPDATE taw_rm_definetree SET sub_id='"+subids+"',subnum=subnum+"+1+" WHERE node_id='"+node_id+"' and room_id="+room_id;
          pstmt = BocoConn.prepareStatement(sql);
          pstmt.executeUpdate();
        }
        close(rs);
        close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(rs);
        close(pstmt);
        throw new SQLException(e.getMessage());
      }
    }
    /**
     * @see 删除一个结点时，更新父结点的sub_id
     * @param node_id String
     * @throws SQLException
     */
    public void DelSubId(String  node_id,int room_id) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs=null;
          /*
            String sql="UPDATE taw_physics_room SET approom=substr(approom,0,instr(approom,'"+roomId+"')) ||"
                       +"  substr(approom,instr(approom,'"+roomId+"')+"+length+") where approom like '%"+roomId+"%'";
         */
      int length=node_id.length()+2;
      String subids=null;
      String parentId=null;
      String sql="SELECT parent_id from taw_rm_definetree WHERE room_id="+room_id+" and node_id='"+node_id+"'";
      try {
        pstmt = BocoConn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next())
          parentId=rs.getString(1);
        rs.close();
        pstmt.close();
        sql="SELECT sub_id from taw_rm_definetree WHERE room_id="+room_id+" and node_id='"+parentId+"'";
        pstmt = BocoConn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next())
        {
          subids=rs.getString("sub_id").trim();
          rs.close();
          pstmt.close();
          subids=subids.substring(0,subids.indexOf(","+node_id+",")+1)+subids.substring(subids.indexOf(","+node_id+",")+length,subids.length());
          sql =
              "UPDATE taw_rm_definetree SET sub_id='"+subids+"',subnum=subnum-" + 1 + " WHERE room_id=" + room_id +
              " and node_id='"+parentId+"'";
          pstmt = BocoConn.prepareStatement(sql);
          pstmt.executeUpdate();
        }
        close(rs);
        close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(rs);
        close(pstmt);
        throw new SQLException(e.getMessage());
      }
    }

    /**
     * 设置删除标志
     * @param node_id
     * @throws SQLException
     */
    public void UptDeleted(String node_id,int room_id) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
      String sql="";
      try {
        sql = "UPDATE taw_rm_definetree SET deleted=? WHERE node_id like '"+node_id+"%' and room_id=?";
        //BocoConn = ds.getConnection();
        pstmt = BocoConn.prepareStatement(sql);
        pstmt.setInt(1,1);
        pstmt.setInt(2,room_id);
        pstmt.executeUpdate();
        close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(pstmt);
        throw new SQLException(e.getMessage());
      }
    }

    /**
     * @see 判断是已经保存到相应的rec表中
     * @param node_id
     * @return
     * @throws SQLException
     */
    public boolean IsExistINRec(String node_id,int room_id,String dutydateconf) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs=null;
            boolean have=false;
      String sql="";
      try {
        sql="SELECT id FROM taw_rm_definerec where "+dutydateconf+" and  node_id like '"+node_id+"%' and room_id="+room_id;
        //conn = ds.getConnection();
        pstmt = BocoConn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next())
           have=true;
         close(rs);
         close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(rs);
        close(pstmt);
        throw new SQLException(e.getMessage());
      }
      return have;
    }



    /**
     * @see 判断当前班次的记录是否存在
     * @param node_id
     * @param room_id
     * @param dutydate
     * @param workserial
     * @return
     * @throws SQLException
     */
    public boolean IsExistINRec(String node_id,int room_id,String dutydate,int workserial) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs=null;
            boolean have=false;
      String sql="";
      try {
        sql="SELECT id FROM taw_rm_definerec where dutydate='"+dutydate+"' and  node_id like '"+node_id+"%' and room_id="+room_id+" and workserial="+workserial;
        //conn = ds.getConnection();
        pstmt = BocoConn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next())
           have=true;
         close(rs);
         close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(rs);
        close(pstmt);
        throw new SQLException(e.getMessage());
      }
      return have;
    }

    /**
     * @see 判断是已经保存到相应的rec表中
     * @param node_id
     * @return
     * @throws SQLException
     */
    public boolean IsExistINRec(String node_id,int room_id) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs=null;
            boolean have=false;
      String sql="";
      try {
        sql="SELECT id FROM taw_rm_definerec where  node_id like '"+node_id+"%' and room_id="+room_id;
        //conn = ds.getConnection();
        pstmt = BocoConn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next())
           have=true;
         close(rs);
         close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(rs);
        close(pstmt);
        throw new SQLException(e.getMessage());
      }
      return have;
    }

    /**
     * @see 删除该结点,以及该结点下的所有结点
     * @param node_id
     * @throws SQLException
     */
    public void  delete(String node_id ,int room_id ) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
      String sql="";
      try {
        sql="DELETE  FROM taw_rm_definetree where node_id like '"+node_id+"%' and room_id ="+room_id;
        //conn = ds.getConnection();
        pstmt = BocoConn.prepareStatement(sql);
        pstmt.executeUpdate();
        close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(pstmt);
        throw new SQLException(e.getMessage());
      }
    }

    /**
     * @see 更新命名
     * @param node_id
     * @param node_name
     * @throws SQLException
     */
    public void UptNodeName(String node_id,String node_name,int room_id,String cycles,String specility) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
      String sql="";
      try {
        sql = "UPDATE taw_rm_definetree SET name=? , cycles=? ,specility=? WHERE node_id =? and room_id=? ";
        //conn = ds.getConnection();
        pstmt = BocoConn.prepareStatement(sql);
        pstmt.setString(1,node_name);
        pstmt.setString(2,cycles);
        pstmt.setString(3,specility);
        pstmt.setString(4,node_id);
        pstmt.setInt(5,room_id);
        pstmt.executeUpdate();
        close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(pstmt);
        throw new SQLException(e.getMessage());
      }
    }

    /**
     *
     * @param node_id String
     * @param node_name String
     * @param defalut String
     * @param line int
     * @param room_id int
     * @throws SQLException
     */
    public void UptNode(String node_id,String node_name,String defalut,int line,int room_id) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
      String sql="";
      try {
        sql = "UPDATE taw_rm_definetree SET name=? ,defalut=? ,lines=? WHERE node_id =? and room_id=? ";
        //conn = ds.getConnection();
        pstmt = BocoConn.prepareStatement(sql);
        pstmt.setString(1,node_name);
        pstmt.setString(2,defalut);
        pstmt.setInt(3,line);
        pstmt.setString(4,node_id);
        pstmt.setInt(5,room_id);
        pstmt.executeUpdate();
        close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(pstmt);
        throw new SQLException(e.getMessage());
      }
    }

    /**
     * @see 设置缺省值
     * @param node_id
     * @param defaultValue
     * @throws SQLException
     */
    public void UptNodeFault(String node_id,String defaultValue,int room_id) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
      String sql="";
      try {
        sql = "UPDATE taw_rm_definetree SET default=? WHERE node_id =? and room_id =?";
        //conn = ds.getConnection();
        pstmt = BocoConn.prepareStatement(sql);
        pstmt.setString(1,defaultValue);
        pstmt.setString(2,node_id);
        pstmt.setInt(3,room_id);
        pstmt.executeUpdate();
        close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(pstmt);
        throw new SQLException(e.getMessage());
      }
    }
    /**
     * @see 根据父结点得到其下子结点
     * @param parent_id
     * @param deleted
     * @throws SQLException
     */
    public Vector getNodes(int room_id,String parent_id,int deleted ) throws SQLException {
            //com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs=null;
            Vector nodeVect=new Vector();
      String sql="";
      TawRmDefineTree  tawRmDefineTree=null;
      BocoConnection Conn=null;
      try {
        sql = "SELECT *  FROM taw_rm_definetree  WHERE room_id=? and parent_id =? and deleted=? order by id";
        Conn = ds.getConnection();
        pstmt = Conn.prepareStatement(sql);
        pstmt.setInt(1,room_id);
        pstmt.setString(2,parent_id);
        pstmt.setInt(3,deleted);
        rs=pstmt.executeQuery();
        while(rs.next())
        {
          tawRmDefineTree=new TawRmDefineTree();
          populate(tawRmDefineTree,rs);
          tawRmDefineTree.setIsLeaf(rs.getInt("isleaf"));
          tawRmDefineTree.setSubNum(rs.getInt("subnum"));
          tawRmDefineTree.setLines(rs.getInt("lines"));
          nodeVect.add(tawRmDefineTree);
        }
        close(rs);
        close(pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
        close(rs);
        close(pstmt);
        throw new SQLException(e.getMessage());
      }finally
      {
      close(Conn);
      }
      return nodeVect;
    }
    /**
     * @see 根据机房id,得到整个树
     * @param room_id
     * @return
     */
    public HashMap getTree(int room_id,String parent_id)
    {
      HashMap treeHash = new HashMap();
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "";
      TawRmDefineTree tawRmDefineTree=null;
      try {
        sql =
            "SELECT * FROM taw_rm_definetree WHERE room_id=? and deleted=? and node_id like '"+parent_id+"%'  order by id";
        //BocoConn = ds.getConnection();
        pstmt = BocoConn.prepareStatement(sql);
        pstmt.setInt(1,room_id);
        pstmt.setInt(2,0);   //未删除
        rs = pstmt.executeQuery();
        while (rs.next()) {
           tawRmDefineTree=new TawRmDefineTree();
           populate(tawRmDefineTree,rs);
           treeHash.put(tawRmDefineTree.getNodeId(),tawRmDefineTree);
        }
        close(rs);
        close(pstmt);
      }
      catch (SQLException e) {
        e.printStackTrace();
        close(rs);
        close(pstmt);
      }
      return treeHash;
    }
    /**
     * @see 初始化taw_rm_definerec
     * @param roomId int
     * @param workserial int
     */
    public void InitRec(int roomId,int workserial)
   {
     PreparedStatement pstmt = null;
     String sql = "";
     try {
       sql =
           "INSERT INTO Taw_rm_definerec (room_id,Workserial,node_id,Duty_record) select "
           +roomId+","+workserial+",node_id,defalut FROM Taw_rm_defineTree where room_id=? and deleted=?";
       //BocoConn = ds.getConnection();
       pstmt = BocoConn.prepareStatement(sql);
       pstmt.setInt(1,roomId);
       pstmt.setInt(2, 0);
       pstmt.executeUpdate();
       close(pstmt);
     }
     catch (SQLException e) {
       e.printStackTrace();
       close(pstmt);

     }
   }
   private int i=1;
   /**
    * @see 递归循环产生树
    * @param room_id int
    * @param parent_id String
    * @param parent_tree String
    * @param treeStr String
    * @throws SQLException
    * @return String
    */
   public String GenTree(int room_id,String parent_id,String parent_tree,String treeStr) throws SQLException
   {
     try{
       TawRmDefineTree tawRmDefineTree = null;
       String folderOrItem = null;
       if (parent_id == null)
         parent_id = "0";
       if(parent_tree==null)
         parent_tree = "0";
       if (treeStr == null)
         treeStr = "Tree = new Array;\n";
       int tmp1=1;
       //if (!"0".equals(parent_id)) { //结点本身
       if (i!=1) { //结点本身
         TawRmDefineTree tmptawRmDefineTree = getEntity(room_id, parent_id);
         if(tmptawRmDefineTree==null)
         {
           System.out.println("parent_id=" + parent_id);
           throw new Exception("找不到相应的结点!");
         }
         if (tmptawRmDefineTree.getIsLeaf() == 0) //不是页子
         {
           folderOrItem = "|JavaScript:oc(" + i + ", 0);|Folder";
           treeStr += "Tree[" + (i - 1) + "]='" + i + "|" + parent_tree + "|" +
               tmptawRmDefineTree.getName() + folderOrItem + "|" +
               tmptawRmDefineTree.getNodeId() + "|"+tmptawRmDefineTree.getCycles()+"';\n";
         }
         else
          {
            folderOrItem = "|#|Item";
            treeStr += "Tree[" + (i - 1) + "]='" + i + "|" + parent_tree + "|" +
                tmptawRmDefineTree.getName() + folderOrItem + "|" +
                tmptawRmDefineTree.getNodeId() + "|"+tmptawRmDefineTree.getDefalut()+"|"+tmptawRmDefineTree.getLines()+"';\n";
          }
         tmp1=i;
         i++;
       }else
       {
         tmp1 = 0;
       }
       Vector rootVect = getNodes(room_id, parent_id, 0); //子结点
       for (Iterator itr = rootVect.iterator(); itr.hasNext(); ) {
         tawRmDefineTree = (TawRmDefineTree) itr.next();
         if (tawRmDefineTree.getIsLeaf() == 0) { //不是页子
           treeStr += "Tree[" + (i - 1) + "]='" + i + "|" + tmp1 + "|" +
               tawRmDefineTree.getName() + "|JavaScript:oc(" + i +
               ", 0);|Folder" + "|" + tawRmDefineTree.getNodeId() + "|"+tawRmDefineTree.getCycles()+"';\n";
           int tmpParent=i;
           i++;
           String sub_ids = tawRmDefineTree.getSubId();
           String[] sub_id = sub_ids.split(",");
           for (int j = 1; j < sub_id.length; j++)
           {
             if(!IsDelete(sub_id[j],room_id))
                treeStr = GenTree(room_id, sub_id[j], String.valueOf(tmpParent),
                                 treeStr);
           }
         }
         else {
           treeStr += "Tree[" + (i - 1) + "]='" + i + "|" + tmp1 + "|" +
               tawRmDefineTree.getName() + "|#|Item" + "|" +
               tawRmDefineTree.getNodeId() + "|"+tawRmDefineTree.getDefalut()+"|"+tawRmDefineTree.getLines()+"';\n";
           i++;
         }
       }
     }catch(Exception ex)
     {
     ex.printStackTrace();
     throw new SQLException(ex.getMessage());
     }
     return treeStr;
   }



   private int j=0;
   public String GenTable(int room_id,String parent_id,String treeStr,String action,HashMap colshash,HashMap rowshash) throws SQLException
   {
     try{
       TawRmDefineTree tawRmDefineTree = null;
       if (treeStr == null)
         treeStr = "";
         TawRmDefineTree tmptawRmDefineTree=null;
         if(j!=0)//结点本身
         {
           tmptawRmDefineTree = getEntity(room_id, parent_id);
           if (tmptawRmDefineTree == null) {
             System.out.println("parent_id=" + parent_id);
             throw new Exception("找不到相应的结点!");
           }
           //if (tmptawRmDefineTree.getIsLeaf() == 0) //不是页子
           if(!colshash.containsKey(tmptawRmDefineTree.getNodeId()))
           {
             if(treeStr.lastIndexOf("</td>")!=treeStr.length()-5)
                treeStr+="\n<tr class='tr_show'>\n";
             treeStr += "<td  bgcolor=\"#FCFEAD\"  width='10%' prop='1111' rowspan='" +
                 rowshash.get(tmptawRmDefineTree.getNodeId()) + "'>" + tmptawRmDefineTree.getName() +
                 "</td>";
           }
           else{
             if(treeStr.lastIndexOf("</td>")!=treeStr.length()-5)
                treeStr+="\n<tr class='tr_show'>\n";
             treeStr += "<td bgcolor=\"#FCFEAD\"  width='10%' prop='0000'>" +
                 tmptawRmDefineTree.getName() + "</td>\n<td class='clsfth' colspan=" +colshash.get(tmptawRmDefineTree.getNodeId())+">";
             /*
             if("View".equals(action))
                treeStr+=tmptawRmDefineTree.getDefalut()+"&nbsp;</td>\n</tr>\n";
             else if("Edit".equals(action))
              */
                treeStr+="<textarea  rows='"+(tmptawRmDefineTree.getLines()==0?1:tmptawRmDefineTree.getLines())+"' style=width:100%>"+tmptawRmDefineTree.getDefalut()+"</textarea></td></tr>\n";
           }
         }else
         {
           j++;
           colshash=getNodeCols(room_id,parent_id);
           rowshash=this.getNodesRows(room_id,parent_id);
         }
       Vector rootVect = getNodes(room_id, parent_id, 0); //子结点
       for (Iterator itr = rootVect.iterator(); itr.hasNext(); ) {
         tawRmDefineTree = (TawRmDefineTree) itr.next();
         //if (tawRmDefineTree.getIsLeaf() == 0) { //不是页子
         if(!colshash.containsKey(tawRmDefineTree.getNodeId()))
         {
           if(treeStr.lastIndexOf("</td>")!=treeStr.length()-5)
              treeStr+="\n<tr class='tr_show'>\n";
           treeStr +="\n<td bgcolor=\"#FCFEAD\" width='10%' prop='2222' rowspan='"+rowshash.get(tawRmDefineTree.getNodeId())+"'>"+tawRmDefineTree.getName()+"</td>";
           String sub_ids = tawRmDefineTree.getSubId();
           String[] sub_id = sub_ids.split(",");
           for (int j = 1; j < sub_id.length; j++)
             if(!this.IsDelete(sub_id[j],room_id))
             treeStr = GenTable(room_id, sub_id[j],
                               treeStr,action,colshash,rowshash);
         }
         else {
           if(treeStr.lastIndexOf("</td>")!=treeStr.length()-5)
              treeStr+="\n<tr class='tr_show'>\n";
           treeStr += "<td bgcolor=\"#FCFEAD\"  width='10%' prop='3333'%>" +
               tawRmDefineTree.getName() + "</td>\n<td class='clsfth' colspan=" +colshash.get(tawRmDefineTree.getNodeId())+">";
           /*
           if("View".equals(action))
              treeStr+=tawRmDefineTree.getDefalut()+"&nbsp;</td>\n</tr>\n";
           else if("Edit".equals(action))
           }
           */
          treeStr+="<textarea  rows='"+(tawRmDefineTree.getLines()==0?1:tawRmDefineTree.getLines())+"' style=width:100%>"+tawRmDefineTree.getDefalut()+"</textarea></td></tr>\n";
         }
       }
     }catch(Exception ex)
     {
     ex.printStackTrace();
     throw new SQLException(ex.getMessage());
     }
     return treeStr;
   }


   /**
    * @see 根据room_id,node_id
    * @param room_id int
    * @param node_id String
    * @return TawRmDefineTree
    */
   public TawRmDefineTree getEntity(int room_id,String node_id)
   {
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = "";
     TawRmDefineTree tawRmDefineTree=null;
     BocoConnection Conn=null;
     try {
       sql =
           "SELECT * FROM taw_rm_definetree WHERE room_id=? and node_id=?  order by id";
       Conn = ds.getConnection();
       pstmt = Conn.prepareStatement(sql);
       pstmt.setInt(1,room_id);
       pstmt.setString(2,node_id);
       rs = pstmt.executeQuery();
       if (rs.next()) {
          tawRmDefineTree=new TawRmDefineTree();
          populate(tawRmDefineTree,rs);
          tawRmDefineTree.setIsLeaf(rs.getInt("isleaf"));
          tawRmDefineTree.setSubNum(rs.getInt("subnum"));
          tawRmDefineTree.setLines(rs.getInt("lines"));
       }
       close(rs);
       close(pstmt);
     }
     catch (SQLException e) {
       e.printStackTrace();
       close(rs);
       close(pstmt);
     }finally
     {
       close(Conn);
     }
     return tawRmDefineTree;
   }
   /**
    * @see 返回某一个结点的最大cols数
    * @param room_id int
    * @param node_id String
    * @return int
    */
   public int getMaxNodeCols(int room_id,String node_id)
   {
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = "";
     int cols=0;
     BocoConnection Conn=null;
     try {
       sql =
           "select max(length(node_id)) from taw_rm_definetree  where room_id="+room_id+" and node_id like '"+node_id+"%'";
       Conn = ds.getConnection();
       pstmt = Conn.prepareStatement(sql);
       rs = pstmt.executeQuery();
       if (rs.next()) {
          cols=(rs.getInt(1)-3)/2+1;
       }
       close(rs);
       close(pstmt);
     }
     catch (SQLException e) {
       e.printStackTrace();
       close(rs);
       close(pstmt);
     }finally
      {
      close(Conn);
      }
     return cols;
   }

   public HashMap getNodeCols(int room_id,String node_id)
   {
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = "";
     int cols=0;
     HashMap colsHash=new HashMap();
     BocoConnection Conn=null;
     try {
       sql =
           "select node_id from taw_rm_definetree where  room_id="+room_id+
           "  and node_id like '"+node_id+"%' and (isleaf=1 or (isleaf=0 and subnum=0))";
       int maxcols=getMaxNodeCols(room_id,node_id);
       String node=null;
       Conn = ds.getConnection();
       pstmt = Conn.prepareStatement(sql);
       rs = pstmt.executeQuery();
       while (rs.next()) {
        node= rs.getString(1);
        if(node.length()>3)
        {
          cols = maxcols-(node.length() - 3) / 2 ;
          colsHash.put(node,new Integer(cols));
        }
       }
       close(rs);
       close(pstmt);
     }
     catch (SQLException e) {
       e.printStackTrace();
       close(rs);
       close(pstmt);
     }finally
     {
     close(Conn);
     }
     return colsHash;
   }
   /**
    * @see 得以一个结点所占的Rows
    * @param room_id int
    * @param node_id String
    * @return HashMap
    */
   public int getNodeRow(int room_id,String node_id)
   {
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = "";
     int rows=0;
     BocoConnection Conn=null;
     try {
       sql =
           "select count(*) from taw_rm_definetree where  room_id="+
            room_id+"  and node_id like '"+node_id+"%' and (isleaf=1 or (isleaf=0 and subnum=0)) and deleted=0";
       Conn = ds.getConnection();
       pstmt = Conn.prepareStatement(sql);
       rs = pstmt.executeQuery();
       if (rs.next()) {
        rows= rs.getInt(1);
       }
       close(rs);
       close(pstmt);
     }
     catch (SQLException e) {
       e.printStackTrace();
       close(rs);
       close(pstmt);
     }finally
     {
     close(Conn);
     }
     return rows;
   }

   /**
    * @see 得到当前结点下的所有目录结点的Rows
    * @param room_id int
    * @param node_id String
    * @return HashMap
    */
   public HashMap getNodesRows(int room_id,String node_id)
   {
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = "";
     int row=0;
     HashMap rowsHash=new HashMap();
     BocoConnection Conn=null;
     try {
       sql =
           "select node_id from taw_rm_definetree where  room_id="+room_id+" and isleaf=0 and node_id like '"+node_id+"%'";
       String node=null;
       Conn = ds.getConnection();
       pstmt = Conn.prepareStatement(sql);
       rs = pstmt.executeQuery();
       while (rs.next()) {
        node= rs.getString(1);
        row=getNodeRow(room_id,node);
        rowsHash.put(node,new Integer(row));
       }
       close(rs);
       close(pstmt);
     }
     catch (SQLException e) {
       e.printStackTrace();
       close(rs);
       close(pstmt);
     }finally
     {
     close(Conn);
     }
     return rowsHash;
   }


   public  HashMap defsOfMonth(int room_id,String node_id,String month)
   {
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = "";
     Calendar cal=new GregorianCalendar();
     HashMap monthHashWork=new HashMap();
     String monthFlag=null;
     try {
       /*
       if (StaticMethod.getDbType().equals(StaticVariable.ORACLE)) {
         String sql1 =
             "alter session set NLS_DATE_FORMAT='YYYY-MM-DD'";
         pstmt = BocoConn.prepareStatement(sql1);
         pstmt.executeUpdate();
         pstmt.close();
       }*/
       sql =
           "select distinct dutyDate,workserial,duty_man from Taw_rm_defineRec where  room_id="+room_id+" and node_id like '"+node_id+"%'"
            +" and to_char(dutydate,'"+dateFormat+"')>='"+month+"-01' and  to_char(dutydate,'"+dateFormat+"')<='"+month+"-31'";//oracle :to_char(dutydate,'YYYY-MM-DD')
       pstmt = BocoConn.prepareStatement(sql);
       rs = pstmt.executeQuery();
       while (rs.next()) {
         cal=StaticMethod.String2Cal(rs.getString(1));
         monthFlag=String.valueOf(cal.get(Calendar.DATE));
         if(monthHashWork.containsKey(monthFlag))
         {
           monthHashWork.put(monthFlag,String.valueOf(monthHashWork.get(monthFlag))+","+rs.getString(2)+":"+rs.getString(3));
         }else
            monthHashWork.put(monthFlag,rs.getString(2)+":"+rs.getString(3));
       }
       close(rs);
       close(pstmt);
     }
     catch (SQLException e) {
       e.printStackTrace();
       close(rs);
       close(pstmt);
     }
     return monthHashWork;
   }



   public  HashMap getRecordOfMonth(int room_id,String node_id,String month)
   {
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = "";
     HashMap colsHash=new HashMap();
     String node=null;
     try {
       sql =
           "select node_id from taw_rm_definetree where  room_id="+room_id+" and parent_id= '"+node_id+"'";
       pstmt = BocoConn.prepareStatement(sql);
       rs = pstmt.executeQuery();
       while (rs.next()) {
        node= rs.getString(1);
        colsHash.put(node,defsOfMonth(room_id,node,month));
       }
       close(rs);
       close(pstmt);
     }
     catch (SQLException e) {
       e.printStackTrace();
       close(rs);
       close(pstmt);
     }
     return colsHash;
   }

   /**
    * @see 判断当前班次的记录是否存在
    * @param node_id
    * @param room_id
    * @param dutydate
    * @param workserial
    * @return
    * @throws SQLException
    */
   public boolean IsDelete(String node_id,int room_id) throws SQLException {
           //com.boco.eoms.db.util.BocoConnection conn = null;
           PreparedStatement pstmt = null;
           ResultSet rs=null;
           boolean have=false;
           BocoConnection Conn=null;

     String sql="";
     try {
       sql="SELECT deleted FROM taw_rm_definetree where node_id = '"+node_id+"' and room_id="+room_id;
       Conn = ds.getConnection();
       pstmt =
           Conn.prepareStatement(sql);
       rs=pstmt.executeQuery();
       if(rs.next())
       {
         have=rs.getInt(1)==0?false:true;
       }
        close(rs);
        close(pstmt);
     } catch (SQLException e) {
       e.printStackTrace();
       close(rs);
       close(pstmt);
       throw new SQLException(e.getMessage());
     }finally
     {
     close(Conn);
     }
     return have;
   }
   /**
    * @see 根据机房id，取出周期==>值班计划的hashMap
    * @param roomId int
    * @return HashMap
    */
   public HashMap getPlanMapCicle(int roomId)
   {
     PreparedStatement pstmt = null;
     ResultSet rs=null;
     String sql = "";
     HashMap planMapCicle=new HashMap();
     String cycles=null;
     try {
       sql =
           "SELECT id,name,node_id,cycles,specility FROM  taw_rm_definetree  WHERE  room_id ="+roomId+" and parent_id= '0' and deleted=0";
       //conn = ds.getConnection();
       pstmt = BocoConn.prepareStatement(sql);
       rs=pstmt.executeQuery();
       while (rs.next())
       {
         cycles=rs.getString(4);
         if(planMapCicle.get(cycles)==null)
         {
           Vector planVect=new Vector();
           planVect.add(rs.getString(3));
           planVect.add(rs.getString(2));
           planVect.add(rs.getString(5));
           planMapCicle.put(cycles,planVect);
         }else
         {
           Vector tmpVect=(Vector)(planMapCicle.get(cycles));
           tmpVect.add(rs.getString(3));
           tmpVect.add(rs.getString(2));
           tmpVect.add(rs.getString(5));
           planMapCicle.put(cycles,tmpVect);
         }
       }
       close(rs);
       close(pstmt);
     }
     catch (SQLException e) {
       e.printStackTrace();
       close(rs);
       close(pstmt);
     }
     return planMapCicle;
   }

   /*
   public boolean hasChild(int room_id,String node_id,int deleted)
   {
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     boolean have = false;
     String sql = "";
     try {
       sql = "SELECT COUNT(*) FROM taw_rm_definerec where node_id like '" +
           node_id + "%'";
       //conn = ds.getConnection();
       pstmt = BocoConn.prepareStatement(sql);
       rs = pstmt.executeQuery(sql);
       if (rs.next())
         have = true;
     }
     catch (SQLException e) {
       e.printStackTrace();
       close(rs);
       close(pstmt);
     }
     return have;
   }
      }
     */


//***************************************************TawRmDefineRecDAO************************************//
   /**
    * @see 新增填写记录
    * @param tawRmDefineTree
    * @throws SQLException
    */
      public void insertRec(TawRmDefineRec tawRmDefineRec) throws SQLException {
        String sql;
        sql = "INSERT INTO Taw_rm_defineRec(workserial,dutydate,room_id,node_id,duty_man,duty_record) values(?,?,?,?,?,?)";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
          conn=ds.getConnection();
          pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1, tawRmDefineRec.getWorkserial());
          //pstmt.setString(2,tawRmDefineRec.getDutyDate().replaceAll("\\.0",""));//日期型
          pstmt.setString(2,StaticMethod.getDateString(tawRmDefineRec.getDutyDate(),0));
          pstmt.setInt(3,tawRmDefineRec.getRoomId());
          pstmt.setString(4, tawRmDefineRec.getNodeId());
          pstmt.setString(5,tawRmDefineRec.getDutyMan());
          pstmt.setString(6,tawRmDefineRec.getDutyRecord());
          pstmt.executeUpdate();
          conn.commit();
          pstmt.close();
        } catch (SQLException sqle) {
          conn.rollback();
          sqle.printStackTrace();
          close(pstmt);
          throw new SQLException(sqle.getMessage());
        } finally
        {
          close(conn);
        }
      }

      /**
       * @see 更新记录结点
       * @param id int
       * @param dutydate String
       * @param duty_man String
       * @param duty_record String
       * @throws SQLException
       */
      public void UptNodeRec(int id,String dutydate,String duty_man,String duty_record) throws SQLException {
              com.boco.eoms.db.util.BocoConnection conn = null;
              PreparedStatement pstmt = null;
        String sql="";

        try {
          sql = "UPDATE taw_rm_definerec SET dutydate=? ,duty_record=? ,duty_man =? WHERE id =?";
          conn = ds.getConnection();
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1,StaticMethod.getDateString(dutydate,0));
          pstmt.setString(2,duty_record);
          pstmt.setString(3,duty_man);
          pstmt.setInt(4,id);
          pstmt.executeUpdate();
          conn.commit();
          close(pstmt);
        } catch (SQLException e) {
          conn.rollback();
          e.printStackTrace();
          close(pstmt);
          throw new SQLException(e.getMessage());
        }finally
        {
         close(conn);
        }
      }

      private int k=0;
      /**
       * @see 查看，修改，填写值班作业
       * @param room_id int
       * @param parent_id String
       * @param treeStr String
       * @param action String
       * @param colshash HashMap
       * @param rowshash HashMap
       * @param rechash HashMap
       * @param dutydate String
       * @param workserial int
       * @throws SQLException
       * @return String
       */
      public String GenTableRec(int room_id,String parent_id,String treeStr,String action,HashMap colshash,HashMap rowshash,HashMap rechash,String dutydate,int workserial) throws SQLException
      {
        try{
          TawRmDefineTree tawRmDefineTree = null;
          TawRmDefineRec  tawRmDefineRec=null;
          if (treeStr == null)
            treeStr = "";
            TawRmDefineTree tmptawRmDefineTree=null;
            if(k!=0)//结点本身
            {
              tmptawRmDefineTree = getEntity(room_id, parent_id);
              if (tmptawRmDefineTree == null) {
                System.out.println("parent_id=" + parent_id);
                throw new Exception("找不到相应的结点!");
              }
              //if (tmptawRmDefineTree.getIsLeaf() == 0) //不是页子
              if(!colshash.containsKey(tmptawRmDefineTree.getNodeId()))
              {
                if(treeStr.lastIndexOf("</td>")!=treeStr.length()-5)
                   treeStr+="\n<tr class='tr_show'>\n";
                treeStr += "<td class='clsfth'   width='10%' prop='1111' rowspan='" +
                    rowshash.get(tmptawRmDefineTree.getNodeId()) + "'>" + tmptawRmDefineTree.getName() +
                    "</td>";
              }
              else{
                if(treeStr.lastIndexOf("</td>")!=treeStr.length()-5)
                   treeStr+="\n<tr class='tr_show'>\n";
                treeStr += "<td class='clsfth'  width='10%' prop='0000'>" +
                    tmptawRmDefineTree.getName() + "</td>\n<td  colspan=" +colshash.get(tmptawRmDefineTree.getNodeId())+">";
                tawRmDefineRec=(TawRmDefineRec)rechash.get(tmptawRmDefineTree.getNodeId());
                String realValue=tmptawRmDefineTree.getDefalut();
                int id=0;
                if(tawRmDefineRec!=null)
                {
                  id = tawRmDefineRec.getId();
                  if (!"".equals(tawRmDefineRec.getDutyRecord())) {
                    realValue = tawRmDefineRec.getDutyRecord();
                  }
                }
                if("View".equals(action))
                {
                  treeStr += realValue + "&nbsp;</td>\n</tr>\n";
                }
                else if("Edit".equals(action))
                {
                  treeStr += "<textarea name='dutyrecord' rows='"+(tmptawRmDefineTree.getLines()==0?1:tmptawRmDefineTree.getLines())+"' "+(tawRmDefineRec==null?"disabled style='width:100%' color='#808080'":"style=width:100%")+">" +
                      realValue + "</textarea></td></tr>\n";
                  if(tawRmDefineRec!=null)
                  {
                    treeStr += "<input type='hidden' name='nodeId' value='" +
                        tmptawRmDefineTree.getNodeId() + "'>";
                    treeStr += "<input type='hidden' name='Id' value='" + id + "'>";
                  }
                }else if("New".equals(action))
                {
                  treeStr += "<textarea name='dutyrecord' rows='"+(tmptawRmDefineTree.getLines()==0?1:tmptawRmDefineTree.getLines())+"' style=width:100%>" +
                      realValue + "</textarea></td></tr>\n";
                    treeStr += "<input type='hidden' name='nodeId' value='" +
                        tmptawRmDefineTree.getNodeId() + "'>";
                    treeStr += "<input type='hidden' name='Id' value='" + id + "'>";
                }
              }
            }else
            {
              k++;
              colshash=getNodeCols(room_id,parent_id);
              rowshash=this.getNodesRows(room_id,parent_id);
            }
          Vector rootVect = getNodes(room_id, parent_id, 0); //子结点
          for (Iterator itr = rootVect.iterator(); itr.hasNext(); ) {
            tawRmDefineTree = (TawRmDefineTree) itr.next();
            //if (tawRmDefineTree.getIsLeaf() == 0) { //不是页子
            if(!colshash.containsKey(tawRmDefineTree.getNodeId()))
            {
              if(treeStr.lastIndexOf("</td>")!=treeStr.length()-5)
                 treeStr+="\n<tr class='tr_show'>\n";
              treeStr +="\n<td class='clsfth'  width='10%' prop='2222' rowspan='"+rowshash.get(tawRmDefineTree.getNodeId())+"'>"+tawRmDefineTree.getName()+"</td>";
              String sub_ids = tawRmDefineTree.getSubId();
              String[] sub_id = sub_ids.split(",");
              for (int j = 1; j < sub_id.length; j++)
              {
                /*
                if("Edit".equals(action) && IsDelete(sub_id[j],room_id))
                    continue;
                */
                if(!IsDelete(sub_id[j],room_id))
                   treeStr = GenTableRec(room_id, sub_id[j],
                                      treeStr, action, colshash, rowshash, rechash,
                                      "", 0);
              }
            }
            else {
              if(treeStr.lastIndexOf("</td>")!=treeStr.length()-5)
                 treeStr+="\n<tr class='tr_show'>\n";
              treeStr += "<td class='clsfth' width='10%' prop='3333'%>" +
                  tawRmDefineTree.getName() + "</td>\n<td  colspan=" +colshash.get(tawRmDefineTree.getNodeId())+">";

              tawRmDefineRec = (TawRmDefineRec) rechash.get(tawRmDefineTree.getNodeId());
              String realValue = tawRmDefineTree.getDefalut();
              int id=0;
              if (tawRmDefineRec != null)
              {
                id = tawRmDefineRec.getId();
                if (!"".equals(tawRmDefineRec.getDutyRecord())) {
                  realValue = tawRmDefineRec.getDutyRecord();
                }
              }
              if("View".equals(action))
              {
                treeStr+=realValue+"&nbsp;</td>\n</tr>\n";
               }
              else if("Edit".equals(action))
              {
                treeStr += "<textarea name='dutyrecord' rows='"+(tawRmDefineTree.getLines()==0?1:tawRmDefineTree.getLines())+"' "+(tawRmDefineRec==null?"disabled style='width:100%' color='#808080' ":"style=width:100%")+">" +
                    realValue + "</textarea></td></tr>\n";
                if(tawRmDefineRec!=null)
                {
                  treeStr += "<input type='hidden' name='nodeId' value='" +
                      tawRmDefineTree.getNodeId() + "'>";
                  treeStr += "<input type='hidden' name='Id' value='" + id + "'>";
                }
              }else if("New".equals(action))
               {
                 treeStr += "<textarea name='dutyrecord' rows='" +
                     (tawRmDefineTree.getLines() == 0 ? 1 :
                      tawRmDefineTree.getLines()) + "' style=width:100%>" +
                     realValue + "</textarea></td></tr>\n";
                 treeStr += "<input type='hidden' name='nodeId' value='" +
                     tawRmDefineTree.getNodeId() + "'>";
                 treeStr += "<input type='hidden' name='Id' value='" + id + "'>";
               }

            }
          }
        }catch(Exception ex)
        {
        ex.printStackTrace();
        throw new SQLException(ex.getMessage());
        }
        return treeStr;
      }

      /**
       * @see 得以某个作业计划下所填写的内容
       * @param roomId int
       * @param nodeId String
       * @param dutydate String
       * @param workserial int
       * @return HashMap
       */
      public HashMap getDutyRecord(int roomId,String nodeId,String conf)
      {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        String sql = "";
        TawRmDefineRec   tawRmDefineRec=null;
        HashMap recHash=new HashMap();
        BocoConnection Conn=null;
        try {
          sql =
              "SELECT id,node_id,duty_record FROM  taw_rm_definerec  WHERE "+conf+" and room_id ="+roomId+" and node_id like '"+nodeId+"%'";
          Conn = ds.getConnection();
          pstmt = Conn.prepareStatement(sql);
          rs=pstmt.executeQuery();
          while (rs.next())
          {
          tawRmDefineRec=new TawRmDefineRec();
          tawRmDefineRec.setId(rs.getInt(1));
          tawRmDefineRec.setNodeId(rs.getString(2));
          tawRmDefineRec.setDutyRecord(StaticMethod.dbNull2String(rs.getString(3)));
          recHash.put(rs.getString(2),tawRmDefineRec);
          }
          close(rs);
          close(pstmt);
        }
        catch (SQLException e) {
          e.printStackTrace();
          close(rs);
          close(pstmt);
        }finally
        {
        close(Conn);
        }
        return recHash;
      }


      /**
       * @see 取得月份列表
       * @param roomId
       * @return
       */
      public Vector getHisMonth(int roomId)
      {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        String sql = "";
        Vector monthVect=new Vector();
        try {
          /*
          if (StaticMethod.getDbType().equals(StaticVariable.ORACLE)) {
            String sql1 =
                "alter session set NLS_DATE_FORMAT='YYYY-MM-DD'";
            pstmt = BocoConn.prepareStatement(sql1);
            pstmt.executeUpdate();
            pstmt.close();
          }*/
          sql =
              "select distinct(substr(to_char(dutydate,'"+dateFormat+"'),0,7)) from taw_rm_definerec where room_id=?";
          //conn = ds.getConnection();
          pstmt = BocoConn.prepareStatement(sql);
          pstmt.setInt(1, roomId);
          rs=pstmt.executeQuery();
          while (rs.next())
          {
            monthVect.add(rs.getString(1));
          }
          close(rs);
          close(pstmt);
        }
        catch (SQLException e) {
          e.printStackTrace();
          close(rs);
          close(pstmt);
        }
        return monthVect;
      }

      /**
       * @see 根据node_id,room_id,dutydate,cycle得到条件
       * @param parent_id
       * @param roomId
       * @param dutydate
       * @param workserial
       * @param cycle
       * @return
       * @throws SQLException
       */
      public String GenConf(String parent_id,int roomId,String dutydate,int workserial,String cycle) throws SQLException
      {
        String dutydateconf=null;
        if(dutydate==null || "null".equalsIgnoreCase(dutydate))
        {
          DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          dutydate = formatter.format(new java.util.Date());
        }
        String[] dates=dutydate.split("-");
        String month=dates[1];
        String date=dates[2];
        if(dates[1].length()==1)
           month="0"+dates[1];
        if(dates[2].length()==1)
           date="0"+dates[2];
        dutydate=dates[0]+"-"+month+"-"+date;
        PreparedStatement pstmt=null;
        Calendar cal = StaticMethod.String2Cal(dutydate);
         try {
           if("workserial".equals(cycle))
           {
             dutydateconf="workserial="+workserial;
           }else if("day".equals(cycle))
           {
             dutydateconf="to_char(dutydate,'"+dateFormat+"')='"+dutydate+"'";
           }else if("week".equals(cycle))
           {
             DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             int daysOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 2;
             cal.add(Calendar.DATE, -daysOfWeek);
             String weekStart=formatter.format(cal.getTime());
             cal.add(Calendar.DATE, daysOfWeek);
             cal.add(Calendar.DATE, 6 - daysOfWeek);
             String weekEnd=formatter.format(cal.getTime());
             dutydateconf="to_char(dutydate,'"+dateFormat+"')>='"+weekStart+"' and to_char(dutydate,'"+dateFormat+"')<='"+weekEnd+"' ";
           }else if ("month".equals(cycle))
           {
             DateFormat formatter = new SimpleDateFormat("yyyy-MM");
             String yearmonth=formatter.format(cal.getTime());
             dutydateconf="to_char(dutydate,'"+dateFormat+"')>='"+yearmonth+"-01' and to_char(dutydate,'"+dateFormat+"')<='"+yearmonth+"-31' ";
           }else if("quarter".equals(cycle))
           {
             DateFormat formatter = new SimpleDateFormat("yyyy-MM");
             String quarterStart="";
             String quarterEnd="";
             int monthOfYear=cal.get(Calendar.MONTH)+1;
             switch(monthOfYear%3)
             {
             case 0:    //3,6,9,12月
               quarterEnd=formatter.format(cal.getTime());
               cal.add(Calendar.MONTH,-2);
               quarterStart=formatter.format(cal.getTime());
               dutydateconf="to_char(dutydate,'"+dateFormat+"')>='"+quarterStart+"-01' and to_char(dutydate,'"+dateFormat+"')<='"+quarterEnd+"-31'";
               break;
             case 1:   //1,4,7,10月
               quarterStart=formatter.format(cal.getTime());
               cal.add(Calendar.MONTH,2);
               quarterEnd=formatter.format(cal.getTime());
               dutydateconf="to_char(dutydate,'"+dateFormat+"')>='"+quarterStart+"-01' and to_char(dutydate,'"+dateFormat+"')<='"+quarterEnd+"-31'";
               break;
             case 2:   //2,5,8,11月
               cal.add(Calendar.MONTH,-1);
               quarterStart=formatter.format(cal.getTime());
               cal.add(Calendar.MONTH,2);
               quarterEnd=formatter.format(cal.getTime());
               dutydateconf="to_char(dutydate,'"+dateFormat+"')>='"+quarterStart+"-01' and to_char(dutydate,'"+dateFormat+"')<='"+quarterEnd+"-31'";
             }
           }else if("halfyear".equals(cycle))
           {
             int monthOfYear=cal.get(Calendar.MONTH)+1;
             int year=cal.get(Calendar.YEAR);
             if(monthOfYear>6)
             {
               dutydateconf="to_char(dutydate,'"+dateFormat+"')>='"+year+"-01-01' and to_char(dutydate,'"+dateFormat+"')<='"+year+"-06-31'";
             }else
             {
               dutydateconf="to_char(dutydate,'"+dateFormat+"')>='"+year+"-07-01' and to_char(dutydate,'"+dateFormat+"')<='"+year+"-12-31'";
             }
           }else if("year".equals(cycle))
           {
             int year=cal.get(Calendar.YEAR);
             dutydateconf="to_char(dutydate,'"+dateFormat+"')>='"+year+"-01-01' and to_char(dutydate,'"+dateFormat+"')<='"+year+"-12-31'";
           }
          } catch (Exception e) {
           e.printStackTrace();
           pstmt.close();
           throw new SQLException(e.getMessage());
          }
         return dutydateconf;
        }
        /**
         * @see 取得一个机房在一个时间段里的应填写的值班记录个数
         * @param roomId
         * @return
         */
        /*
        public int getAllPlanNums(int roomId,String YearMonth)
        {
          PreparedStatement pstmt = null;
          ResultSet rs=null;
          String sql = "";
          int allcount=0;
          try {
            sql =
                "select node_id,cycles from taw_rm_definetree where cycles in('day','workserial') and  parent_id=0 and room_id="+roomId;
            pstmt = BocoConn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while (rs.next())
            {
              allcount+=getPlanNums(roomId,YearMonth,rs.getString(1),rs.getString(2));
            }
            close(rs);
            close(pstmt);
          }
          catch (SQLException e) {
            e.printStackTrace();
            close(rs);
            close(pstmt);
          }
          return allcount;
        }
         */
       /*
        * @see 取得一个机房在一个时间段里的应填写的值班记录个数
        * @param roomId
        * @return
        */
       public int getAllPlanNums(int roomId,String starttime,String endtime)
       {
         PreparedStatement pstmt = null;
         ResultSet rs=null;
         PreparedStatement pstmt1 = null;
         ResultSet rs1=null;
         String sql = "";
         int allcount=0;
         int num=0;
         try {
           sql =
               "select count(cycles),cycles from taw_rm_definetree where cycles in('day','workserial') and  parent_id=0 and deleted=0 and room_id="
               +roomId+" group by cycles";
           pstmt = BocoConn.prepareStatement(sql);
           rs=pstmt.executeQuery();
           while (rs.next())
           {
             if("day".equals(rs.getString(2)))
             {
               num=0;
               Calendar cal= StaticMethod.String2Cal(endtime);
               num=cal.get(cal.DATE);
               allcount+=num*rs.getInt(1);
             }else
             {
               num=0;
               sql="select count(id) from taw_rm_assignwork where room_id="+roomId+" and to_char(dutydate,'"+dateFormat+"')>='"+starttime+"' and to_char(dutydate,'"+dateFormat+"')<='"+endtime+"'";
               pstmt1 = BocoConn.prepareStatement(sql);
               rs1=pstmt1.executeQuery();
               if(rs1.next())
                 num=rs1.getInt(1);
               allcount+=num*rs.getInt(1);
               close(rs1);
               close(pstmt1);
             }
           }
           close(rs);
           close(pstmt);
         }
         catch (SQLException e) {
           e.printStackTrace();
           close(rs);
           close(pstmt);
         }
         return allcount;
       }


       public boolean isHavePlan(int roomId,String starttime ,String endtime)
       {
         PreparedStatement pstmt = null;
         ResultSet rs=null;
         PreparedStatement pstmt1 = null;
         ResultSet rs1=null;
         String sql = "";
         int num=0;
         boolean flag=false;
         try {
           sql =
               "select distinct cycles from taw_rm_definetree where cycles in('day','workserial') and  parent_id=0 and deleted=0 and room_id="
               +roomId;
           pstmt = BocoConn.prepareStatement(sql);
           rs=pstmt.executeQuery();
           while (rs.next())
           {
             if("day".equals(rs.getString(1)))
             {
              flag= true;
              break;
             }else
             {
               sql="select count(id) from taw_rm_assignwork where room_id="+roomId+" and to_char(dutydate,'"+dateFormat+"')>='"+starttime+"' and to_char(dutydate,'"+dateFormat+"')<='"+endtime+"'";
               pstmt1 = BocoConn.prepareStatement(sql);
               rs1=pstmt1.executeQuery();
               if(rs1.next())
                 num=rs1.getInt(1);
               if(num>0)
               {
                 flag = true;
                 break;
               }
               close(rs1);
               close(pstmt1);
             }
           }
           close(rs);
           close(pstmt);
         }
         catch (SQLException e) {
           e.printStackTrace();
           close(rs);
           close(pstmt);
         }
         return flag;
       }

        /*
        public int getPlanNums(int roomId,String YearMonth,String nodeId,String cycles)
        {
          PreparedStatement pstmt = null;
          ResultSet rs=null;
          String sql = "";
          int num=0;
          try {
            if("day".equals(cycles))
            {
              try{
                Calendar cal= StaticMethod.String2Cal(YearMonth);
                num=cal.get(cal.DAY_OF_MONTH);
              }catch(Exception ex)
              {
              ex.printStackTrace();
              }
            }else if("workserial".equals(cycles))
            {
            sql="select count(id) from taw_rm_assignwork where room_id="+roomId+" and to_char(dutydate)>='"+YearMonth+"-01' and dutydate<='"+YearMonth+"-31'";
            pstmt = BocoConn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next())
              num=rs.getInt(1);
            close(rs);
            close(pstmt);
            }
          }
          catch (SQLException e) {
            e.printStackTrace();
            close(rs);
            close(pstmt);
          }
          return num;
        }
        */
      public int getAllRealPlanNums(int roomId,String starttime ,String endtime)
        {
          PreparedStatement pstmt = null;
          ResultSet rs=null;
          String sql = "";
          int allcount=0;
          try {
            sql =
                "select distinct workserial,substr(node_id,0,3) from taw_rm_definerec where room_id="+roomId+" and to_char(dutydate,'"+dateFormat+"')>='"+starttime
                +"' and to_char(dutydate,'"+dateFormat+"')<='"+endtime+"' and substr(node_id,0,3) in ("
                +"select node_id from taw_rm_definetree where cycles ='workserial' and room_id="+roomId
                +" and parent_id=0 and deleted=0)";
            pstmt = BocoConn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
              allcount++;
            }
            close(rs);
            close(pstmt);
            sql =
                "select distinct dutydate,substr(node_id,0,3) from taw_rm_definerec where room_id="+roomId+" and to_char(dutydate,'"+dateFormat+"')>='"+starttime
                +"' and to_char(dutydate,'"+dateFormat+"')<='"+endtime+"' and substr(node_id,0,3) in ("
                +"select node_id from taw_rm_definetree where cycles ='day' and room_id="+roomId
                +" and parent_id=0 and deleted=0)";
            pstmt = BocoConn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
             allcount++;
            }
            close(rs);
            close(pstmt);
          }
          catch (SQLException e) {
            e.printStackTrace();
            close(rs);
            close(pstmt);
          }
          return allcount;
        }
      public void IsIdentify()
      {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        String sql = "";
        int count=0;
        try {
          sql ="select count(*)  from taw_rm_definerec a where room_id<> (select room_id from taw_rm_assignwork b where a.workserial=b.id)" ;
          pstmt = BocoConn.prepareStatement(sql);
          rs = pstmt.executeQuery();
          if (rs.next()) {
            count=rs.getInt(1);
          }
          close(rs);
          close(pstmt);
          if(count!=0)
          {
            try{
            sql="update taw_rm_definerec a set room_id=(select room_id from taw_rm_assignwork b where a.workserial=b.id)"
                +"  where room_id<> (select room_id from taw_rm_assignwork c where a.workserial=c.id) ";
            pstmt = BocoConn.prepareStatement(sql);
            pstmt.executeUpdate();
            BocoConn.commit();
            }catch(SQLException sqle)
            {
            sqle.printStackTrace();
            BocoConn.rollback();
            }
          }
        }catch (SQLException sqle)
        {
          sqle.printStackTrace();
          close(rs);
          close(pstmt);
        }
      }
      }
