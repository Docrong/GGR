package com.boco.eoms.testcard.dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
import java.util.Date;

import com.boco.eoms.testcard.model.*;
import com.boco.eoms.testcard.util.TestCardMgrLocator;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.util.StaticMethod;


import org.apache.struts.upload.*;

public class TawTestcardManagerDAO extends DAO {
    /**
     * @param ds DataSource ���Դ����Struts������ṩ��
     * @see ���췽��
     * @see �����ݿ��l��
     */
    public TawTestcardManagerDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    public void insert(TawTestcardManager tawTestcardManager) throws SQLException {
        String sql;
        //-------------------����һ������Ϣ------------------
        sql = "INSERT INTO taw_testcard_manager (cardid,msisdn , dealer , lenddept , lender ,contect ,reason ,leantime ,belongtime ,returntime ,renewlimit ,state ,deleted,leave,checker,checktel,lenderid,fromcrit,fromcity ) VALUES (?, ?, ?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //-------------------���¸ÿ�״̬Ϊ������---------------------
        String SQL = "update taw_testcard set state=8 where iccid='" + tawTestcardManager.getCardid() + "'";
        PreparedStatement PSTMT = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            System.out.println(tawTestcardManager.getId());
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawTestcardManager.getCardid());
            pstmt.setString(2, tawTestcardManager.getMsisdn());
            pstmt.setString(3, tawTestcardManager.getDealer());
            pstmt.setString(4, tawTestcardManager.getLenddept());
            pstmt.setString(5, tawTestcardManager.getLender());
            pstmt.setString(6, tawTestcardManager.getContect());
            pstmt.setString(7, tawTestcardManager.getReason());
            pstmt.setTimestamp(8, StaticMethod.getTimestamp(tawTestcardManager.getLeantime()));
            pstmt.setTimestamp(9, StaticMethod.getTimestamp(tawTestcardManager.getBelongtime()));
            pstmt.setTimestamp(10, StaticMethod.getTimestamp(tawTestcardManager.getReturntime()));
            pstmt.setInt(11, tawTestcardManager.getRenewlimit());
            pstmt.setInt(12, 8);
            pstmt.setInt(13, 0);
            pstmt.setString(14, tawTestcardManager.getLeave());
            pstmt.setString(15, tawTestcardManager.getChecker());
            pstmt.setString(16, tawTestcardManager.getChecktel());
            pstmt.setString(17, tawTestcardManager.getLenderid());
            pstmt.setString(18, tawTestcardManager.getFromcrit());
            pstmt.setString(19, tawTestcardManager.getFromcity());
            pstmt.executeUpdate();
            PSTMT = conn.prepareStatement(SQL);
            PSTMT.execute();
            pstmt.close();
            conn.commit();
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            sql = null;
            SQL = null;
            tawTestcardManager = null;
            close(conn);
        }
    }


    public void touse(TawTestcardManager tawTestcardManager) throws SQLException {
        String sql;
        //-------------------����һ������Ϣ------------------
        sql = "INSERT INTO taw_testcard_manager (cardid,msisdn , dealer , lenddept , lender ,contect ,reason ,leantime ,belongtime ,returntime ,renewlimit ,state ,deleted,leave   ) VALUES (?,?,?, ?, ?, ?,?,?,?,?,?,?,?,?)";
        //-------------------���¸ÿ�״̬Ϊ���---------------------
        String SQL = "update taw_testcard set state=4 where iccid='" + tawTestcardManager.getCardid() + "'";
        PreparedStatement PSTMT = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawTestcardManager.getCardid());
            pstmt.setString(2, tawTestcardManager.getMsisdn());
            pstmt.setString(3, tawTestcardManager.getDealer());
            pstmt.setString(4, tawTestcardManager.getLenddept());
            pstmt.setString(5, tawTestcardManager.getLender());
            pstmt.setString(6, tawTestcardManager.getContect());
            pstmt.setString(7, tawTestcardManager.getReason());
            pstmt.setTimestamp(8, StaticMethod.getTimestamp(tawTestcardManager.getLeantime()));
            pstmt.setTimestamp(9, StaticMethod.getTimestamp(tawTestcardManager.getBelongtime()));
            pstmt.setTimestamp(10, StaticMethod.getTimestamp(tawTestcardManager.getReturntime()));
            pstmt.setInt(11, tawTestcardManager.getRenewlimit());
            pstmt.setInt(12, 4);
            pstmt.setInt(13, 0);
            pstmt.setString(14, tawTestcardManager.getLeave());
            pstmt.executeUpdate();
            PSTMT = conn.prepareStatement(SQL);
            PSTMT.execute();
            pstmt.close();
            conn.commit();
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            sql = null;
            SQL = null;
            tawTestcardManager = null;
            close(conn);
        }
    }


    public void update(TawTestcardManager tawTestcardManager) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "UPDATE taw_testcard_manager SET cardid=?,msisdn=? , dealer=? , lenddept=? , lender=? ,contect=? ,reason=? ,leantime=? ,belongtime=? ,returntime=? ,renewlimit=? ,state=? ,leave=?  where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawTestcardManager.getCardid());
            pstmt.setString(2, tawTestcardManager.getMsisdn());
            pstmt.setString(3, tawTestcardManager.getDealer());
            pstmt.setString(4, tawTestcardManager.getLenddept());
            pstmt.setString(5, tawTestcardManager.getLender());
            pstmt.setString(6, tawTestcardManager.getContect());
            pstmt.setString(7, tawTestcardManager.getReason());
            pstmt.setTimestamp(8, StaticMethod.getTimestamp(tawTestcardManager.getLeantime()));
            pstmt.setTimestamp(9, StaticMethod.getTimestamp(tawTestcardManager.getBelongtime()));
            pstmt.setTimestamp(10, StaticMethod.getTimestamp(tawTestcardManager.getReturntime()));
            pstmt.setInt(11, tawTestcardManager.getRenewlimit());
            pstmt.setString(12, tawTestcardManager.getState());
            pstmt.setString(13, tawTestcardManager.getLeave());
            pstmt.setInt(14, tawTestcardManager.getId());

            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            e.printStackTrace();
        } finally {
            tawTestcardManager = null;
            close(conn);
        }
    }

    //�����������������������·���ʵ��������������������������
    public void returnupdate(TawTestcardManager tawTestcardManager) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;


        Date dt = new Date();
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sTime = smpDateFormat.format(dt);


        try {
            conn = ds.getConnection();
            //----------------���뻹��ʱ��---------------
            String sql = "UPDATE taw_testcard_manager SET returntime=?,deleted=1,returner=?,manager=?,leantime=?  where id=?";
            //----------------���¿��״̬----------------
            String SQL = "update taw_testcard set state=0 where iccid=(select distinct cardid from taw_testcard_manager where id=" + tawTestcardManager.getId() + ")";
            pstmt = conn.prepareStatement(sql);
            pstmt.setTimestamp(1, StaticMethod.getTimestamp(tawTestcardManager.getReturntime()));
            pstmt.setString(2, tawTestcardManager.getReturner());
            pstmt.setString(3, tawTestcardManager.getManager());
            pstmt.setTimestamp(4, StaticMethod.getTimestamp(sTime));
            pstmt.setInt(5, tawTestcardManager.getId());

            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(SQL);
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    //�����������������������·���ʵ�ֲ��Կ����ղ������������������������
    public void accedeupdate(TawTestcardManager tawTestcardManager) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;

        Date dt = new Date();
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sTime = smpDateFormat.format(dt);

        try {
            conn = ds.getConnection();
            //----------------���뻹��ʱ��---------------
            String sql = "UPDATE taw_testcard_manager SET state=3,leantime=?  where  id=?";
            //----------------���¿��״̬----------------
            String SQL = "update taw_testcard set state=3 where iccid=(select distinct cardid from taw_testcard_manager where id=" + tawTestcardManager.getId() + ")";
            pstmt = conn.prepareStatement(sql);
            pstmt.setTimestamp(1, StaticMethod.getTimestamp(sTime));
            pstmt.setInt(2, tawTestcardManager.getId());

            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(SQL);
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    //-----------------������------------------
    public void renew(TawTestcardManager tawTestcardManager) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;

        Date date = new Date();
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sTime = smpDateFormat.format(date);


        try {
            conn = ds.getConnection();
            String sql = "UPDATE taw_testcard_manager SET renewlimit=?,belongtime=belongtime+?,reason=?,renewer=?,relenddept=?,leantime=? where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tawTestcardManager.getRenewlimit());
            pstmt.setInt(2, tawTestcardManager.getRenewlimit());
            pstmt.setString(3, tawTestcardManager.getReason());
            pstmt.setString(4, tawTestcardManager.getRenewer());
            pstmt.setString(5, tawTestcardManager.getRelenddept());
            pstmt.setTimestamp(6, StaticMethod.getTimestamp(sTime));
            pstmt.setInt(7, tawTestcardManager.getId());
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    public void delete(int id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "UPDATE taw_testcard_manager SET deleted=1 where id=" + id;
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    public TawTestcardManager retrieve(int id) throws SQLException {
        TawTestcardManager tawTestcardManager = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        TawTestcardDAO tawTestcardDAO = new TawTestcardDAO();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT id,cardid,msisdn,dealer,lenddept,lender,contect,reason,leantime,belongtime,returntime,renewlimit,state,leave,manager,checker FROM taw_testcard_manager WHERE id=" + id;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawTestcardManager = new TawTestcardManager();
                populate(tawTestcardManager, rs);
                tawTestcardManager.setLeave(tawTestcardDAO.getStorage(tawTestcardManager.getLeave()));
                //    tawTestcardManager.setCardid(rs.getString(2));
                //    tawTestcardManager.setImsii(rs.getString(3));
                //    tawTestcardManager.setDealer(rs.getString(4));
                //    tawTestcardManager.setLenddept(rs.getString(5));
                //    tawTestcardManager.setLender(rs.getString(6));
                //    tawTestcardManager.setContect(rs.getString(7));
                //    tawTestcardManager.setLeantime(rs.getString(9));
                //    tawTestcardManager.setBelongtime(rs.getString(10));
                //    tawTestcardManager.setReturntime(rs.getString(11));
                //    tawTestcardManager.setRenewlimit(rs.getInt(12));
                //    tawTestcardManager.setReason(rs.getString(8));
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return tawTestcardManager;
    }

    public List list(int offset, int limit, String condition) throws SQLException {
        ArrayList list = new ArrayList();
        TawTestcardDAO tawTestcardDAO = new TawTestcardDAO();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TawTestcardManager tawTestcardManager = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT id,cardid,msisdn , dealer , lenddept , lender ,contect ,reason ,leantime ,belongtime ,returntime ,renewlimit,leave FROM taw_testcard_manager WHERE " + condition + " order by returntime,belongtime";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) {
                int i = 0;
                while (i++ < offset) {
                    rs.next();
                }
            }
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                tawTestcardManager = new TawTestcardManager();
                populate(tawTestcardManager, rs);
                tawTestcardManager.setCardid(rs.getString(2));
                tawTestcardManager.setMsisdn(rs.getString(3));
                try {
//        if (tawTestcardManager.getState().equals("0")) {
//          tawTestcardManager.setState("��");
//        }
//        else if (tawTestcardManager.getState().equals("1")) {
//          tawTestcardManager.setState("ͣ��");
//        }
//        else if (tawTestcardManager.getState().equals("2")) {
//          tawTestcardManager.setState("��ʧ");
//        }
//        else if (tawTestcardManager.getState().equals("3")) {
//          tawTestcardManager.setState("���");
//        }
//        else if (tawTestcardManager.getState().equals("4")) {
//          tawTestcardManager.setState("ʹ��");
//        }
//        else if (tawTestcardManager.getState().equals("5")) {
//          tawTestcardManager.setState("����");
//        }
                    tawTestcardManager.setLeave(tawTestcardDAO.getStorage(tawTestcardManager.getLeave()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                list.add(tawTestcardManager);
                tawTestcardManager = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            tawTestcardManager = null;
            close(conn);
        }
        return list;
    }

    public List returnlist(int offset, int limit, int[] size, String condition) throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TawTestcardManager tawTestcardManager = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT id,cardid,msisdn , dealer , lenddept , lender ,contect ,reason ,leantime ,belongtime ,returntime ,renewlimit ,state,fromcrit,fromcity FROM taw_testcard_manager WHERE deleted=0 " + condition + " order by belongtime";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) {
                int i = 0;
                while (i++ < offset) {
                    rs.next();
                }
            }
            size[0] = this.getLengh(condition);
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                tawTestcardManager = new TawTestcardManager();
                populate(tawTestcardManager, rs);
                tawTestcardManager.setCardid(rs.getString(2));
                tawTestcardManager.setMsisdn(rs.getString(3));
//      try {
//        if (tawTestcardManager.getState().equals("0")) {
//          tawTestcardManager.setState("��正常");
//        }
//        else if (tawTestcardManager.getState().equals("1")) {
//          tawTestcardManager.setState("ͣ��停机");
//        }
//        else if (tawTestcardManager.getState().equals("2")) {
//          tawTestcardManager.setState("遗失��ʧ");
//        }
//        else if (tawTestcardManager.getState().equals("3")) {
//          tawTestcardManager.setState("借出���SIM");
//        }
//        else if (tawTestcardManager.getState().equals("4")) {
//          tawTestcardManager.setState("");
//        }
//        else if (tawTestcardManager.getState().equals("5")) {
//          tawTestcardManager.setState("报废����");
//        }
//        else if (tawTestcardManager.getState().equals("6")) {
//            tawTestcardManager.setState("SIM卡注册失败��ʧ��");
//          }
//        else if (tawTestcardManager.getState().equals("8")) {
//            tawTestcardManager.setState("�����");
//          }
//      }
//      catch (Exception ex) {
//        ex.printStackTrace();
//      }
                list.add(tawTestcardManager);
                tawTestcardManager = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            tawTestcardManager = null;
            close(conn);
        }
        return list;
    }

    public List returnBorrowList(int offset, int limit, int[] size, String condition) throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TawTestcardManager tawTestcardManager = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT id,cardid,msisdn , dealer , lenddept , lender ,contect ,reason ,leantime ,belongtime ,returntime ,renewlimit ,state,deleted FROM taw_testcard_manager WHERE deleted!=3 " + condition + " order by belongtime";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) {
                int i = 0;
                while (i++ < offset) {
                    rs.next();
                }
            }
            int recCount = 0;
            size[0] = this.getBorrowLengh(condition);
            while ((recCount++ < limit) && rs.next()) {
                tawTestcardManager = new TawTestcardManager();
                populate(tawTestcardManager, rs);
                tawTestcardManager.setCardid(rs.getString(2));
                tawTestcardManager.setMsisdn(rs.getString(3));
//	       if(rs.getInt(14)==1){
//	    	   tawTestcardManager.setState("");
//	       }
                try {
                    if (tawTestcardManager.getState().equals("0")) {
                        tawTestcardManager.setState(TestCardMgrLocator.getAttributes().getNormalState());
                    } else if (tawTestcardManager.getState().equals("1")) {
                        tawTestcardManager.setState(TestCardMgrLocator.getAttributes().getDowntimeState());
                    } else if (tawTestcardManager.getState().equals("2")) {
                        tawTestcardManager.setState(TestCardMgrLocator.getAttributes().getLostState());
                    } else if (tawTestcardManager.getState().equals("3")) {
                        tawTestcardManager.setState(TestCardMgrLocator.getAttributes().getLendingState());
                    } else if (tawTestcardManager.getState().equals("4")) {
                        tawTestcardManager.setState(TestCardMgrLocator.getAttributes().getUseState());
                    } else if (tawTestcardManager.getState().equals("5")) {
                        tawTestcardManager.setState(TestCardMgrLocator.getAttributes().getScrappedState());
                    } else if (tawTestcardManager.getState().equals("6")) {
                        tawTestcardManager.setState(TestCardMgrLocator.getAttributes().getRegistrationState());
                    } else if (tawTestcardManager.getState().equals("8")) {
                        tawTestcardManager.setState(TestCardMgrLocator.getAttributes().getQuestionState());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                list.add(tawTestcardManager);
                tawTestcardManager = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            tawTestcardManager = null;
            close(conn);
        }
        return list;
    }

    public List returnBorrowListNote(int offset, int limit, int[] size, int condition) throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TawTestcardManagerNote tawTestcardManagerNote = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT id,cardid,msisdn , dealer , lenddept , lender ,contect ,reason ,leantime ,belongtime ,returntime ,renewlimit ,state ,deleted ,renewer,relenddept FROM taw_testcard_manager_note WHERE deleted!=3 and parent_id=" + condition + " order by leantime";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) {
                int i = 0;
                while (i++ < offset) {
                    rs.next();
                }
            }
            int recCount = 0;
            size[0] = this.getNoteLengh(condition);
            while ((recCount++ < limit) && rs.next()) {
                tawTestcardManagerNote = new TawTestcardManagerNote();
                populate(tawTestcardManagerNote, rs);
                tawTestcardManagerNote.setCardid(rs.getString(2));
                tawTestcardManagerNote.setMsisdn(rs.getString(3));
                if (rs.getInt(14) == 1) {
                    tawTestcardManagerNote.setState("�ѹ黹");
                }
                try {
                    if (tawTestcardManagerNote.getState().equals("0")) {
                        tawTestcardManagerNote.setState("��");
                    } else if (tawTestcardManagerNote.getState().equals("1")) {
                        tawTestcardManagerNote.setState("ͣ��");
                    } else if (tawTestcardManagerNote.getState().equals("2")) {
                        tawTestcardManagerNote.setState("��ʧ");
                    } else if (tawTestcardManagerNote.getState().equals("3")) {
                        tawTestcardManagerNote.setState("���");
                    } else if (tawTestcardManagerNote.getState().equals("4")) {
                        tawTestcardManagerNote.setState("ʹ��");
                    } else if (tawTestcardManagerNote.getState().equals("5")) {
                        tawTestcardManagerNote.setState("����");
                    } else if (tawTestcardManagerNote.getState().equals("6")) {
                        tawTestcardManagerNote.setState("SIM��ע��ʧ��");
                    } else if (tawTestcardManagerNote.getState().equals("8")) {
                        tawTestcardManagerNote.setState("�����");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                list.add(tawTestcardManagerNote);
                tawTestcardManagerNote = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            tawTestcardManagerNote = null;
            close(conn);
        }
        return list;
    }

    public int getLengh(String condition) throws SQLException {
        int length = 0;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            if (!condition.equals("") && !condition.equals(null)) {
                condition = " where deleted=0 " + condition;
            }
            String sql = "SELECT count(*) from taw_testcard_manager " + condition;
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                length = rs.getInt(1);
                System.out.println(length);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return length;
    }

    public int getBorrowLengh(String condition) throws SQLException {
        int length = 0;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            if (!condition.equals("") && !condition.equals(null)) {
                condition = " where deleted!=3 " + condition;
            }
            String sql = "SELECT count(*) from taw_testcard_manager " + condition;
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                length = rs.getInt(1);
                System.out.println(length);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return length;
    }

    /**
     * ����ϴ����ļ��Ƿ��ظ�
     */
    public boolean check(FormFile File, String FilePath) {
        boolean flag = true;
        File theFile = new File(FilePath);
        String Filename[] = theFile.list();
        for (int i = 0; i < Filename.length; i++) {
            String aa = Filename[i];
            String bb = File.getFileName();
            if (aa.equals(bb)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void toscrap(TawTestcardManager tawTestcardManager) throws SQLException {
        String sql;
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

//   Date date = dateFormat.parse(str);
        //-------------------����һ������Ϣ------------------
        sql = "INSERT INTO taw_testcard_manager (cardid,msisdn, dealer , lenddept , lender ,contect ,reason ,leantime ,belongtime ,returntime ,renewlimit ,state ,deleted,leave,lenderid) VALUES (?,?,?, ?, ?, ?,?,?,?,?,?,?,?,?,?)";
        //-------------------���¸ÿ�״̬Ϊ���---------------------
        String SQL = "update taw_testcard set state=5,deleted=1 where iccid='" + tawTestcardManager.getCardid() + "'";
        PreparedStatement PSTMT = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawTestcardManager.getCardid());
            pstmt.setString(2, tawTestcardManager.getMsisdn());
            pstmt.setString(3, tawTestcardManager.getDealer());
            pstmt.setString(4, tawTestcardManager.getLenddept());
            pstmt.setString(5, tawTestcardManager.getLender());
            pstmt.setString(6, tawTestcardManager.getContect());
            pstmt.setString(7, tawTestcardManager.getReason());
//     Date date2 = dateFormat.parse(tawTestcardManager.getLeantime());
            pstmt.setTimestamp(8, StaticMethod.getTimestamp(tawTestcardManager.getLeantime()));
//     Date date = dateFormat.parse(tawTestcardManager.getBelongtime());
            pstmt.setTimestamp(9, StaticMethod.getTimestamp(tawTestcardManager.getBelongtime()));
//     Date date1 = dateFormat.parse(tawTestcardManager.getReturntime());
            pstmt.setTimestamp(10, StaticMethod.getTimestamp(tawTestcardManager.getReturntime()));
            pstmt.setInt(11, tawTestcardManager.getRenewlimit());
            pstmt.setInt(12, 5);
            pstmt.setInt(13, 1);
            pstmt.setString(14, tawTestcardManager.getLeave());
            pstmt.setString(15, tawTestcardManager.getLenderid());
            pstmt.executeUpdate();
            PSTMT = conn.prepareStatement(SQL);
            PSTMT.execute();
            pstmt.close();
            conn.commit();
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            sql = null;
            SQL = null;
            tawTestcardManager = null;
            close(conn);
        }
    }

    public List presentimentlist(int offset, int limit, int[] size, String sTime) throws SQLException {
        ArrayList list = new ArrayList();
        TawTestcardDAO tawTestcardDAO = new TawTestcardDAO();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TawTestcardManager tawTestcardManager = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT id,cardid,msisdn, dealer , lenddept , lender ,contect ,reason ,leantime ,belongtime ,returntime ,renewlimit,leave FROM taw_testcard_manager WHERE deleted=0 and state=3 and  to_date('" + sTime + "','YYYY-MM-DD HH24:MI:SS') > belongtime order by belongtime";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) {
                int i = 0;
                while (i++ < offset) {
                    rs.next();
                }
            }
            int recCount = 0;
            size[0] = this.getPresentimentLengh(sTime);
            while ((recCount++ < limit) && rs.next()) {
                tawTestcardManager = new TawTestcardManager();
                populate(tawTestcardManager, rs);
                tawTestcardManager.setCardid(rs.getString(2));
                tawTestcardManager.setMsisdn(rs.getString(3));
                try {
                    tawTestcardManager.setLeave(tawTestcardDAO.getStorage(tawTestcardManager.getLeave()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                list.add(tawTestcardManager);
                tawTestcardManager = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            tawTestcardManager = null;
            close(conn);
        }
        return list;
    }


    public int getPresentimentLengh(String condition) throws SQLException {
        int length = 0;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT count(*) from taw_testcard_manager where deleted=0 and state=3 and to_date('" + condition + "','YYYY-MM-DD HH24:MI:SS') > belongtime  ";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                length = rs.getInt(1);
                System.out.println(length);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return length;
    }

    public void inserttesting(TawTestcardTesting tawTestcardTesting) throws SQLException {
        String sql;
        //-------------------����һ�������Ϣ------------------
        sql = "INSERT INTO taw_testcard_testing (iccid,msisdn,testtime,outcome,conner,leave,accessories) VALUES (?, ?, ?,?, ?,?,?)";

        PreparedStatement PSTMT = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawTestcardTesting.getIccid());
            pstmt.setString(2, tawTestcardTesting.getMsisdn());
            pstmt.setTimestamp(3, StaticMethod.getTimestamp(tawTestcardTesting.getTesttime()));
            pstmt.setString(4, tawTestcardTesting.getOutcome());
            pstmt.setString(5, tawTestcardTesting.getConner());
            pstmt.setString(6, tawTestcardTesting.getLeave());
            pstmt.setString(7, tawTestcardTesting.getAccessories());
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            sql = null;
            tawTestcardTesting = null;
            close(conn);
        }
    }

    public int getNoteLengh(int condition) throws SQLException {
        int length = 0;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT count(*) from taw_testcard_manager_note  where deleted!=3 and parent_id=" + condition;
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                length = rs.getInt(1);
                System.out.println(length);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return length;
    }

    public String getUserName(String userid) throws SQLException {
        String username = "";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT username from taw_system_user where userid='" + userid + "'";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                username = rs.getString(1);
                //System.out.println(username);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return username;
    }

    public void clearinsert(String iccidlist, String newstate, String cleartime, String clearuser, String clearresan) throws SQLException {
        String[] carid = iccidlist.split(",");
        String newstatename = "";
        if (newstate.equals("0")) {
            newstatename = "正常";
        } else if (newstate.equals("1")) {
            newstatename = "停机";
        } else if (newstate.equals("2")) {
            newstatename = "遗失";
        } else if (newstate.equals("6")) {
            newstatename = "SIM卡注册失败";
        }
        String sql;
        //-------------------����һ������Ϣ------------------
        sql = "INSERT INTO taw_testcard_clear (iccid,msisdn,clearuser,cleartime,oldstate,newstate,clearyuanyin ) VALUES (?, ?, ?,?, ?,?,?)";
        //-------------------���¸ÿ�״̬Ϊ������---------------------
        String SQL = "update taw_testcard set state='" + newstate + "' where iccid='" + carid[1] + "'";
        PreparedStatement PSTMT = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, carid[1]);
            pstmt.setString(2, carid[2]);
            pstmt.setString(3, clearuser);
            pstmt.setString(4, cleartime);
            pstmt.setString(5, carid[0]);
            pstmt.setString(6, newstatename);
            pstmt.setString(7, clearresan);
            pstmt.executeUpdate();
            PSTMT = conn.prepareStatement(SQL);
            PSTMT.execute();
            pstmt.close();
            conn.commit();
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            sql = null;
            SQL = null;
            close(conn);
        }
    }

    public List returnClearNote(int offset, int limit, int[] size, String condition) throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TawTestcardClearNote tawTestcardClearNote = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT iccid,msisdn,clearuser,cleartime,oldstate,newstate,clearyuanyin FROM taw_testcard_clear WHERE  iccid='" + condition + "' order by cleartime";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) {
                int i = 0;
                while (i++ < offset) {
                    rs.next();
                }
            }
            int recCount = 0;
            size[0] = this.getClearnoteLengh(condition);
            while ((recCount++ < limit) && rs.next()) {
                tawTestcardClearNote = new TawTestcardClearNote();
                populate(tawTestcardClearNote, rs);
                tawTestcardClearNote.setIccid(rs.getString(1));
                tawTestcardClearNote.setMsisdn(rs.getString(2));
                tawTestcardClearNote.setClearuser(rs.getString(3));
                tawTestcardClearNote.setCleartime(rs.getString(4));
                tawTestcardClearNote.setOldstate(rs.getString(5));
                tawTestcardClearNote.setNewstate(rs.getString(6));
                tawTestcardClearNote.setClearyuanyin(rs.getString(7));

                list.add(tawTestcardClearNote);
                tawTestcardClearNote = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            tawTestcardClearNote = null;
            close(conn);
        }
        return list;
    }

    public int getClearnoteLengh(String condition) throws SQLException {
        int length = 0;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT count(*) from taw_testcard_clear  where  iccid='" + condition + "'";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                length = rs.getInt(1);
                System.out.println(length);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return length;
    }
}
