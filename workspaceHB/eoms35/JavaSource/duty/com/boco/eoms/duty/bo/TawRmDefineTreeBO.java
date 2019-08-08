package com.boco.eoms.duty.bo;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.db.util.BocoConnection;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;

import com.boco.eoms.common.util.StaticMethod;
//import com.boco.eoms.jbzl.dao.TawApparatusroomDAO;
//import com.boco.eoms.jbzl.dao.TawDeptDAO;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;

public class TawRmDefineTreeBO extends BO {
    public TawRmDefineTreeBO(ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param tawRmDefineTree
     * @throws SQLException
     * @see 新增结点
     */
    public String insert(TawRmDefineTree tawRmDefineTree) throws SQLException {
        BocoConnection conn = ds.getConnection();
        try {
            conn.setAutoCommit(false);
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            String node_id = tawRmDefineTreeDAO.insert(tawRmDefineTree);
            tawRmDefineTreeDAO.AddSubId(tawRmDefineTree.getParentId(), node_id, tawRmDefineTree.getRoomId());
            conn.commit();
            return node_id;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            conn.rollback();
            throw sqle;
        } finally {
            conn.close();
        }
    }

    /**
     * @param node_id
     * @throws SQLException
     * @see 删除一个结点
     */
    public void delete(String node_id, int room_id) throws SQLException {
        //com.boco.eoms.db.util.BocoConnection conn = null;

        BocoConnection conn = ds.getConnection();
        try {
            conn.setAutoCommit(false);
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            String[] nodeIds = node_id.split(",");
            for (int i = 0; i < nodeIds.length; i++) {
                if (tawRmDefineTreeDAO.IsExistINRec(nodeIds[i], room_id))
                    tawRmDefineTreeDAO.UptDeleted(nodeIds[i], room_id);
                else {
                    tawRmDefineTreeDAO.DelSubId(nodeIds[i], room_id);
                    tawRmDefineTreeDAO.delete(nodeIds[i], room_id);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    public Vector getNodes(int room_id, String parent_id, int deleted) throws SQLException {
        Vector nodeVect = null;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(ds);
            nodeVect = tawRmDefineTreeDAO.getNodes(room_id, parent_id, deleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nodeVect;
    }


    public String GenTable(int room_id, String parent_id, String treeStr, String action) throws SQLException {
        String strTree = null;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(ds);
            strTree = tawRmDefineTreeDAO.GenTable(room_id, parent_id, treeStr, action, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strTree;
    }

    public String GenTree(int room_id, String parent_id, String parent_tree, String treeStr) throws SQLException {
        String strTree = null;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(ds);
            strTree = tawRmDefineTreeDAO.GenTree(room_id, parent_id, parent_tree, treeStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strTree;
    }

    public void UptNode(String node_id, String node_name, String defalut, int line, int room_id) throws SQLException {
        BocoConnection conn = ds.getConnection();
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            tawRmDefineTreeDAO.UptNode(node_id, node_name, defalut, line, room_id);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    public void UptNodeName(String node_id, String node_name, int room_id, String cycle, String specility) throws SQLException {
        BocoConnection conn = ds.getConnection();
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            tawRmDefineTreeDAO.UptNodeName(node_id, node_name, room_id, cycle, specility);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    public HashMap getRecordOfMonth(int room_id, String node_id, String month) {
        BocoConnection conn = ds.getConnection();
        HashMap recordOfMonth = null;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            recordOfMonth = tawRmDefineTreeDAO.getRecordOfMonth(room_id, node_id, month);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return recordOfMonth;
    }

    public HashMap getPlanMapCicle(int roomId) {
        BocoConnection conn = ds.getConnection();
        HashMap PlanMapCicle = null;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            PlanMapCicle = tawRmDefineTreeDAO.getPlanMapCicle(roomId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return PlanMapCicle;
    }


    //***************************************************TawRmDefineRecBO************************************//

    public String GenTableRec(int room_id, String parent_id, String treeStr, String action, HashMap colshash, HashMap rowshash, HashMap rechash, String dutydate, int workserial, String Cycle) throws SQLException {
        String strTree = null;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(ds);
            String conf = tawRmDefineTreeDAO.GenConf(parent_id, room_id, dutydate, workserial, Cycle);
            rechash = tawRmDefineTreeDAO.getDutyRecord(room_id, parent_id, conf);
            strTree = tawRmDefineTreeDAO.GenTableRec(room_id, parent_id, treeStr, action, colshash, rowshash, rechash, dutydate, workserial);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strTree;
    }

    public void insertRec(String[] nodeIds, String[] dutyrecords, int roomId, String dutydate, int workserial, String dutyman) throws SQLException {
        TawRmDefineRec tawRmDefineRec = null;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(ds);
            for (int i = 0; i < nodeIds.length; i++) {
                tawRmDefineRec = new TawRmDefineRec();
                tawRmDefineRec.setNodeId(nodeIds[i]);
                tawRmDefineRec.setDutyRecord(dutyrecords[i]);
                tawRmDefineRec.setRoomId(roomId);
                tawRmDefineRec.setDutyDate(dutydate);
                tawRmDefineRec.setWorkserial(workserial);
                tawRmDefineRec.setDutyMan(dutyman);
                tawRmDefineTreeDAO.insertRec(tawRmDefineRec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UptNodeRec(String[] Ids, String[] duty_record, String dutydate, String duty_man) throws SQLException {
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(ds);
            for (int i = 0; i < Ids.length; i++) {
                tawRmDefineTreeDAO.UptNodeRec(Integer.parseInt(Ids[i]), dutydate, duty_man, duty_record[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean IsExistINRec(String node_id, int room_id, String dutydate) throws SQLException {
        BocoConnection conn = ds.getConnection();
        boolean ishave = false;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            ishave = tawRmDefineTreeDAO.IsExistINRec(node_id, room_id, dutydate);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return ishave;
    }

    public boolean IsExistINRec(String node_id, int room_id, String dutydate, int workserial) throws SQLException {
        BocoConnection conn = ds.getConnection();
        boolean ishave = false;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            ishave = tawRmDefineTreeDAO.IsExistINRec(node_id, room_id, dutydate, workserial);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return ishave;
    }


    public boolean IsExistINRec(String parent_id, int roomId, String dutydate, int workserial, String cycle) throws SQLException {
        BocoConnection conn = ds.getConnection();
        boolean ishave = false;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            String conf = tawRmDefineTreeDAO.GenConf(parent_id, roomId, dutydate, workserial, cycle);
            ishave = tawRmDefineTreeDAO.IsExistINRec(parent_id, roomId, conf);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conn.close();
        }
        return ishave;
    }

    public Vector getHisMonth(int roomId) {
        BocoConnection conn = ds.getConnection();
        Vector monthVect = null;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            monthVect = tawRmDefineTreeDAO.getHisMonth(roomId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return monthVect;
    }

    public HashMap roomMapPlanNums(List roomList, String starttime, String endtime) {
        BocoConnection conn = ds.getConnection();
        HashMap roomMapPlanNums = new HashMap();
        int roomId = -1;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            if (roomList != null && roomList.size() > 0) {
                for (int i = 0; i < roomList.size(); i++) {
                    org.apache.struts.util.LabelValueBean lb = (org.apache.struts.util.LabelValueBean) roomList.get(i);
                    roomId = Integer.parseInt(lb.getValue());
                    roomMapPlanNums.put(lb.getValue(), String.valueOf(tawRmDefineTreeDAO.getAllPlanNums(roomId, starttime, endtime)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return roomMapPlanNums;
    }

    public HashMap roomMapRealPlanNums(List roomList, String starttime, String endtime) {
        BocoConnection conn = ds.getConnection();
        HashMap roomMapRealPlanNums = new HashMap();
        int roomId = -1;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            if (roomList != null && roomList.size() > 0) {
                for (int i = 0; i < roomList.size(); i++) {
                    org.apache.struts.util.LabelValueBean lb = (org.apache.struts.util.LabelValueBean) roomList.get(i);
                    roomId = Integer.parseInt(lb.getValue());
                    roomMapRealPlanNums.put(lb.getValue(), String.valueOf(tawRmDefineTreeDAO.getAllRealPlanNums(roomId, starttime, endtime)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return roomMapRealPlanNums;
    }

    public HashMap deptMapRooms(String[] topdeptIds, String deptNames) {
        //BocoConnection conn=ds.getConnection();
        HashMap deptMapRooms = new HashMap();
        String[] deptName = deptNames.split(" ");
        //edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        //TawApparatusroomDAO  tawApparatusroomDAO=new TawApparatusroomDAO(ds);
        try {
            for (int j = 0; j < topdeptIds.length; j++) {
                List roomList = cptroomBO.getTawSystemCptroomList(topdeptIds[j], 0);
                //List roomList = tawApparatusroomDAO.getRoomNameSelect(topdeptIds[j],0);
                deptMapRooms.put(deptName[j], roomList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deptMapRooms;
    }

    public void IsIdentify() {
        BocoConnection conn = ds.getConnection();
        TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
        try {
            tawRmDefineTreeDAO.IsIdentify();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
    }


    public List isHavePlanOfRoom(List roomList, String starttime, String endtime) {
        BocoConnection conn = ds.getConnection();
        TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
        List realRoomList = new ArrayList();
        if (roomList != null && roomList.size() > 0) {
            for (int i = 0; i < roomList.size(); i++) {
                org.apache.struts.util.LabelValueBean lb = (org.apache.struts.util.LabelValueBean) roomList.get(i);
                if (tawRmDefineTreeDAO.isHavePlan(Integer.parseInt(lb.getValue()),
                        starttime, endtime))
                    realRoomList.add(lb);
            }
        }
        return realRoomList;
    }


    public String isHavePlanOfRoom(String roomIDs, String starttime, String endtime) {
        BocoConnection conn = ds.getConnection();
        String[] roomid = roomIDs.split(",");
        String realroom = "";
        TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
        for (int i = 0; i < roomid.length; i++) {
            if (tawRmDefineTreeDAO.isHavePlan(Integer.parseInt(roomid[i]),
                    starttime, endtime))
                realroom += roomid[i] + ",";
        }
        if (realroom.endsWith(","))
            realroom = realroom.substring(0, realroom.length() - 1);
        return realroom;
    }

    /**
     * roomId=146,tableId=3,nodeID=004,startTime=2004-08-31,endTime=2004-11-13
     *
     * @param roomId    int
     * @param tableId   int
     * @param startTime String
     * @param endTime   String
     * @param nodeID    String
     * @throws Exception
     */
    public void ExportData(int roomId, int tableId, String startTime, String endTime, String nodeID) throws Exception {
        BocoConnection BocoConn = ds.getConnection();
        BocoConn.setAutoCommit(false);
        PreparedStatement pstmt4 = null;
        ResultSet rs4 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String sql = null;
        try {
            sql = "select a.workserial,a.duty_record,b.dutydate ,a.record_sub from taw_rm_record_def a,taw_rm_assignwork b where a.workserial=b.id " +
                    " and a.room_id=" + roomId + " and a.table_id=" + tableId
                    + "  and b.room_id=" + roomId + " and to_char(b.dutydate)>='" + startTime + "' and to_char(b.dutydate)<='" + endTime + "'";
            pstmt4 = BocoConn.prepareStatement(sql);
            rs4 = pstmt4.executeQuery();
            String nodeId3 = "";
            while (rs4.next()) {
                nodeId3 = getNodeIdByNameTmp(BocoConn, rs4.getString(4), nodeID, roomId);
                if ("".equals(nodeId3))
                    continue;
                insertRecTmp(BocoConn, nodeId3, rs4.getString(2), roomId, dateFormat.format(rs4.getDate(3)), rs4.getInt(1), "");
            }
            rs4.close();
            pstmt4.close();
            BocoConn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        } finally {
            BocoConn.close();
        }
    }

    public void ExportData() throws Exception {
        BocoConnection BocoConn = ds.getConnection();
        BocoConn.setAutoCommit(false);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet rs3 = null;
        PreparedStatement pstmt4 = null;
        ResultSet rs4 = null;
        TawRmDefineTree tawRmDefineTree = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String sql = null;
        try {
            sql = "select id from taw_apparatusroom";
            pstmt = BocoConn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int roomId = 0;
            while (rs.next()) {
                roomId = rs.getInt(1);
                sql = "select table_id,table_desc,deleted from taw_rm_definetable where room_Id=" + roomId;
                pstmt1 = BocoConn.prepareStatement(sql);
                rs1 = pstmt1.executeQuery();
                while (rs1.next()) {
                    int table_id = 0;
                    String nodeId1 = null;
                    table_id = rs1.getInt(1);
                    tawRmDefineTree = new TawRmDefineTree();
                    tawRmDefineTree.setName(rs1.getString(2));
                    tawRmDefineTree.setRoomId(roomId);
                    tawRmDefineTree.setParentId("0");
                    tawRmDefineTree.setIsLeaf(0);
                    tawRmDefineTree.setCycles("workserial");
                    tawRmDefineTree.setSpecility("智能网");
                    tawRmDefineTree.setDeleted(rs1.getInt(3));
                    nodeId1 = insertTmp(BocoConn, tawRmDefineTree);
                    tawRmDefineTree = null;
                    sql = "select main_id,record_main,deleted from taw_rm_definemain where room_id=" + roomId + " and table_id=" + table_id;
                    pstmt2 = BocoConn.prepareStatement(sql);
                    rs2 = pstmt2.executeQuery();
                    int main_id = 0;
                    String nodeId2 = null;
                    while (rs2.next()) {
                        main_id = rs2.getInt(1);
                        tawRmDefineTree = new TawRmDefineTree();
                        tawRmDefineTree.setName(rs2.getString(2));
                        tawRmDefineTree.setRoomId(roomId);
                        tawRmDefineTree.setParentId(nodeId1);
                        tawRmDefineTree.setIsLeaf(0);
                        tawRmDefineTree.setDeleted(rs2.getInt(3));
                        nodeId2 = insertTmp(BocoConn, tawRmDefineTree);
                        tawRmDefineTree = null;
                        sql = "select sub_id,record_sub,defalut,deleted from taw_rm_definesub where room_id=" + roomId + " and table_id=" + table_id + " and main_id=" + main_id;
                        pstmt3 = BocoConn.prepareStatement(sql);
                        rs3 = pstmt3.executeQuery();
                        int sub_id = 0;
                        String nodeId3 = null;
                        while (rs3.next()) {
                            sub_id = rs3.getInt(1);
                            tawRmDefineTree = new TawRmDefineTree();
                            tawRmDefineTree.setName(rs3.getString(2));
                            tawRmDefineTree.setRoomId(roomId);
                            tawRmDefineTree.setParentId(nodeId2);
                            tawRmDefineTree.setIsLeaf(1);
                            tawRmDefineTree.setDeleted(rs3.getInt(4));
                            tawRmDefineTree.setLines(0);
                            tawRmDefineTree.setDefalut(rs3.getString(3));
                            nodeId3 = insertTmp(BocoConn, tawRmDefineTree);
                            tawRmDefineTree = null;
                            sql = "select a.workserial,a.duty_record,b.dutydate from taw_rm_record_def a,taw_rm_assignwork b where a.workserial=b.id " +
                                    " and a.room_id=" + roomId + " and a.table_id=" + table_id + " and a.main_id=" + main_id + " and a.sub_id=" + sub_id;
                            pstmt4 = BocoConn.prepareStatement(sql);
                            rs4 = pstmt4.executeQuery();
                            while (rs4.next()) {
                                insertRecTmp(BocoConn, nodeId3, rs4.getString(2), roomId, dateFormat.format(rs4.getDate(3)), rs4.getInt(1), "");
                            }
                            rs4.close();
                            pstmt4.close();
                        }
                        rs3.close();
                        pstmt3.close();
                    }
                    rs2.close();
                    pstmt2.close();
                }
                rs1.close();
                pstmt1.close();
            }
            rs.close();
            pstmt.close();
            BocoConn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rs.close();
            pstmt.close();
            throw new SQLException(e.getMessage());
        } finally {
            BocoConn.close();
        }
    }


    public String insertTmp(BocoConnection conn, TawRmDefineTree tawRmDefineTree) throws SQLException {
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            String node_id = tawRmDefineTreeDAO.insert(tawRmDefineTree);
            tawRmDefineTreeDAO.AddSubId(tawRmDefineTree.getParentId(), node_id, tawRmDefineTree.getRoomId());
            return node_id;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            conn.rollback();
            throw sqle;
        }
    }

    public void insertRecTmp(BocoConnection conn, String nodeIds, String dutyrecords, int roomId, String dutydate, int workserial, String dutyman) throws SQLException {
        TawRmDefineRec tawRmDefineRec = null;
        try {
            TawRmDefineTreeDAO tawRmDefineTreeDAO = new TawRmDefineTreeDAO(conn);
            tawRmDefineRec = new TawRmDefineRec();
            tawRmDefineRec.setNodeId(nodeIds);
            tawRmDefineRec.setDutyRecord(dutyrecords);
            tawRmDefineRec.setRoomId(roomId);
            tawRmDefineRec.setDutyDate(dutydate);
            tawRmDefineRec.setWorkserial(workserial);
            tawRmDefineRec.setDutyMan(dutyman);
            tawRmDefineTreeDAO.insertRec(tawRmDefineRec);
            System.out.println("Insert.....");
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        }
    }

    public String getNodeIdByNameTmp(BocoConnection conn, String nodeName, String parentId, int roomId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        String nodeId = "";
        try {
            sql =
                    "select node_id from taw_rm_definetree where  room_id=" +
                            roomId + "  and node_id like '" + parentId + "%' and name='" + nodeName + "'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nodeId = rs.getString(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            rs.close();
            pstmt.close();
        }
        return nodeId;
    }

}
