package com.boco.eoms.testcard.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.MessageResources;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl;
import com.boco.eoms.testcard.model.TawActivateStat;
import com.boco.eoms.testcard.model.TawStat;
import com.boco.eoms.testcard.model.TawTestcard;
import com.boco.eoms.testcard.util.MyTreeMap;
import com.boco.eoms.testcard.util.StaticValue;
import com.boco.eoms.testcard.util.TestCardMgrLocator;

public class TawTestcardDAO extends DAO {
	/**
	 * @see ���췽��
	 * @see �����ݿ��l��
	 * @param ds
	 *            DataSource ���Դ����Struts������ṩ��
	 */
	public TawTestcardDAO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	public void insert(TawTestcard tawTestcard) throws SQLException {
		String sql;
		sql = "INSERT INTO taw_testcard (leave,cardtype,fromCanton,fromCountry,fromOpe,"
				+ "fromCrit,fromCity,toCanton,toCountry,toOpe,toCrit,toCity,iccid,msisdn,"
				+ "imsi,pin1,pin2,puk1,puk2,password,operation,begintime,endtime,intime,"
				+ "state,oldno,deleted,offer,msgcenterno,lasttesttime,testresult,dealresult,adder,telnum,position,cardpackage,exes,volumenum,pagenum,msisdn1,imsi1,phone_number)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tawTestcard.getLeave());
			pstmt.setString(2, tawTestcard.getCardType());
			pstmt.setString(3, tawTestcard.getFromCanton());
			pstmt.setString(4, tawTestcard.getFromCountry());
			pstmt.setString(5, tawTestcard.getFromOpe());
			pstmt.setString(6, tawTestcard.getFromCrit());
			pstmt.setString(7, tawTestcard.getFromCity());
			pstmt.setString(8, tawTestcard.getToCanton());
			pstmt.setString(9, tawTestcard.getToCountry());
			pstmt.setString(10, tawTestcard.getToOpe());
			pstmt.setString(11, tawTestcard.getToCrit());
			pstmt.setString(12, tawTestcard.getToCity());
			pstmt.setString(13, tawTestcard.getIccid());
			pstmt.setString(14, tawTestcard.getMsisdn());
			pstmt.setString(15, tawTestcard.getImsi());
			pstmt.setString(16, tawTestcard.getPin1());
			pstmt.setString(17, tawTestcard.getPin2());
			pstmt.setString(18, tawTestcard.getPuk1());
			pstmt.setString(19, tawTestcard.getPuk2());
			pstmt.setString(20, tawTestcard.getPassword());
			pstmt.setString(21, tawTestcard.getOperation());
			pstmt.setString(22, tawTestcard.getBegintime());
			pstmt.setString(23, tawTestcard.getEndtime());
			pstmt.setString(24, tawTestcard.getIntime());
			pstmt.setString(25, tawTestcard.getState());
			pstmt.setString(26, tawTestcard.getOldNo());
			pstmt.setInt(27, 0);
			pstmt.setString(28, tawTestcard.getOffer());
			pstmt.setString(29, tawTestcard.getMsgcenterno());
			pstmt.setString(30, tawTestcard.getLasttesttime());
			pstmt.setString(31, tawTestcard.getTestresult());
			pstmt.setString(32, tawTestcard.getDealresult());
			pstmt.setString(33, tawTestcard.getAdder());
			pstmt.setString(34, tawTestcard.getTelnum());
			pstmt.setString(35, tawTestcard.getPosition());
			pstmt.setString(36, tawTestcard.getCardpackage());
			pstmt.setString(37, tawTestcard.getExes());
			pstmt.setString(38, tawTestcard.getVolumenum());
			pstmt.setString(39, tawTestcard.getPagenum());
			pstmt.setString(40, tawTestcard.getMsisdn1());
			pstmt.setString(41, tawTestcard.getImsi1());
			pstmt.setString(42, tawTestcard.getPhoneNumber());

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
			tawTestcard = null;
			close(conn);
		}
	}

	public void update(TawTestcard tawTestcard) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;

		// System.out.println(tawTestcard.getId());
		try {
			conn = ds.getConnection();
			String sql = "UPDATE taw_testcard SET leave=?,cardtype=? ,fromCanton=? ,fromCountry=? ,fromOpe=? ,fromCrit=? ,fromCity=? ,toCanton=? ,toCountry=? ,toOpe=? ,toCrit=? ,toCity=? ,msisdn=? ,imsi=? ,pin1=? ,pin2=?,puk1=?,puk2=? ,password=?,operation=? ,begintime=? ,endtime=? ,state=?,oldno=?,offer=? ,msgcenterno=?,lasttesttime=?,testresult=?,dealresult=?,telnum=?,position=?,cardpackage=?,exes=?,volumenum=?,pagenum=?,msisdn1=?,imsi1=?,is_alive=?,iccid=? ,adder=?, intime=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tawTestcard.getLeave());
			pstmt.setString(2, tawTestcard.getCardType());
			pstmt.setString(3, tawTestcard.getFromCanton());
			pstmt.setString(4, tawTestcard.getFromCountry());
			pstmt.setString(5, tawTestcard.getFromOpe());
			pstmt.setString(6, tawTestcard.getFromCrit());
			pstmt.setString(7, tawTestcard.getFromCity());
			pstmt.setString(8, tawTestcard.getToCanton());
			pstmt.setString(9, tawTestcard.getToCountry());
			pstmt.setString(10, tawTestcard.getToOpe());
			pstmt.setString(11, tawTestcard.getToCrit());
			pstmt.setString(12, tawTestcard.getToCity());
			pstmt.setString(13, tawTestcard.getMsisdn());
			pstmt.setString(14, tawTestcard.getImsi());
			pstmt.setString(15, tawTestcard.getPin1());
			pstmt.setString(16, tawTestcard.getPin2());
			pstmt.setString(17, tawTestcard.getPuk1());
			pstmt.setString(18, tawTestcard.getPuk2());
			pstmt.setString(19, tawTestcard.getPassword());
			pstmt.setString(20, tawTestcard.getOperation());
			pstmt.setString(21, tawTestcard.getBegintime());
			pstmt.setString(22, tawTestcard.getEndtime());
			pstmt.setString(23, tawTestcard.getState());
			pstmt.setString(24, tawTestcard.getOldNo());
			pstmt.setString(25, tawTestcard.getOffer());
			pstmt.setString(26, tawTestcard.getMsgcenterno());
			pstmt.setString(27, tawTestcard.getLasttesttime());
			pstmt.setString(28, tawTestcard.getTestresult());
			pstmt.setString(29, tawTestcard.getDealresult());
			pstmt.setString(30, tawTestcard.getTelnum());
			pstmt.setString(31, tawTestcard.getPosition());
			pstmt.setString(32, tawTestcard.getCardpackage());
			pstmt.setString(33, tawTestcard.getExes());
			pstmt.setString(34, tawTestcard.getVolumenum());
			pstmt.setString(35, tawTestcard.getPagenum());
			pstmt.setString(36, tawTestcard.getMsisdn1());
			pstmt.setString(37, tawTestcard.getImsi1());
			if(tawTestcard.getIsAlive()==null||tawTestcard.getIsAlive().equals("")){
				pstmt.setString(38,0+"");
			}else{
				pstmt.setString(38, tawTestcard.getIsAlive());}
			pstmt.setString(39, tawTestcard.getIccid());
			pstmt.setString(40, tawTestcard.getAdder());
			pstmt.setString(41, tawTestcard.getIntime());
			pstmt.setInt(42, tawTestcard.getId());
					pstmt.executeUpdate();
			close(pstmt);
			conn.commit();
		} catch (SQLException e) {
			close(pstmt);
			e.printStackTrace();
		} finally {
			tawTestcard = null;
			close(conn);
		}
	}

	public void activateTestCard(TawTestcard tawTestcard) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;

		// System.out.println(tawTestcard.getId());
		try {
			conn = ds.getConnection();
			String sql = "UPDATE taw_testcard SET leave=?,cardType=? ,fromCanton=? ,fromCountry=? ,fromOpe=? ,fromCrit=? ,fromCity=? ,toCanton=? ,toCountry=? ,toOpe=? ,toCrit=? ,toCity=? ,msisdn=? ,imsi=? ,pin1=? ,pin2=?,puk1=?,puk2=? ,password=?,operation=? ,begintime=? ,endtime=? ,state=?,oldno=?,offer=? ,msgcenterno=?,lasttesttime=?,testresult=?,dealresult=?,telnum=?,position=?,cardpackage=?,exes=?,volumenum=?,pagenum=?,msisdn1=?,imsi1=?,is_alive=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tawTestcard.getLeave());
			pstmt.setString(2, tawTestcard.getCardType());
			pstmt.setString(3, tawTestcard.getFromCanton());
			pstmt.setString(4, tawTestcard.getFromCountry());
			pstmt.setString(5, tawTestcard.getFromOpe());
			pstmt.setString(6, tawTestcard.getFromCrit());
			pstmt.setString(7, tawTestcard.getFromCity());
			pstmt.setString(8, tawTestcard.getToCanton());
			pstmt.setString(9, tawTestcard.getToCountry());
			pstmt.setString(10, tawTestcard.getToOpe());
			pstmt.setString(11, tawTestcard.getToCrit());
			pstmt.setString(12, tawTestcard.getToCity());
			pstmt.setString(13, tawTestcard.getMsisdn());
			pstmt.setString(14, tawTestcard.getImsi());
			pstmt.setString(15, tawTestcard.getPin1());
			pstmt.setString(16, tawTestcard.getPin2());
			pstmt.setString(17, tawTestcard.getPuk1());
			pstmt.setString(18, tawTestcard.getPuk2());
			pstmt.setString(19, tawTestcard.getPassword());
			pstmt.setString(20, tawTestcard.getOperation());
			pstmt.setString(21, tawTestcard.getBegintime());
			pstmt.setString(22, tawTestcard.getEndtime());
			pstmt.setString(23, tawTestcard.getState());
			pstmt.setString(24, tawTestcard.getOldNo());
			pstmt.setString(25, tawTestcard.getOffer());
			pstmt.setString(26, tawTestcard.getMsgcenterno());
			pstmt.setString(27, tawTestcard.getLasttesttime());
			pstmt.setString(28, tawTestcard.getTestresult());
			pstmt.setString(29, tawTestcard.getDealresult());
			pstmt.setString(30, tawTestcard.getTelnum());
			pstmt.setString(31, tawTestcard.getPosition());
			pstmt.setString(32, "");
			// pstmt.setString(32,
			// eventdao.findName(tawTestcard.getCardpackage()));
			pstmt.setString(33, tawTestcard.getExes());
			pstmt.setString(34, tawTestcard.getVolumenum());
			pstmt.setString(35, tawTestcard.getPagenum());
			pstmt.setString(36, tawTestcard.getMsisdn1());
			pstmt.setString(37, tawTestcard.getImsi1());
			pstmt.setString(38, StaticValue.STATUS_ALIVE);
			pstmt.setInt(39, tawTestcard.getId());
			pstmt.executeUpdate();
			close(pstmt);
			conn.commit();
		} catch (SQLException e) {
			close(pstmt);
			e.printStackTrace();
		} finally {
			tawTestcard = null;
			close(conn);
		}
	}

	public void editupdate(TawTestcard tawTestcard) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			String sql = "UPDATE taw_testcard (leave,cardtype,fromCanton,fromCountry,fromOpe,"
					+ "fromCrit,fromCity,toCanton,toCountry,toOpe,toCrit,toCity,iccid,msisdn,"
					+ "imsi,pin1,pin2,puk1,puk2,password,operation,begintime,endtime,intime,"
					+ "state,oldno,offer,msgcenterno,lasttesttime,testresult,dealresult,adder,telnum,position,cardpackage,exes,volumenum,pagenum)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, StaticMethod.null2String(tawTestcard.getLeave()));
			stmt.setString(2, StaticMethod.null2String(tawTestcard
					.getCardType()));
			stmt.setString(3, StaticMethod.null2String(tawTestcard
					.getFromCanton()));
			stmt.setString(4, StaticMethod.null2String(tawTestcard
					.getFromCountry()));
			stmt.setString(5, StaticMethod
					.null2String(tawTestcard.getFromOpe()));
			stmt.setString(6, StaticMethod.null2String(tawTestcard
					.getFromCrit()));
			stmt.setString(7, StaticMethod.null2String(tawTestcard
					.getFromCity()));
			stmt.setString(8, StaticMethod.null2String(tawTestcard
					.getToCanton()));
			stmt.setString(9, StaticMethod.null2String(tawTestcard
					.getToCountry()));
			stmt
					.setString(10, StaticMethod.null2String(tawTestcard
							.getToOpe()));
			stmt.setString(11, StaticMethod
					.null2String(tawTestcard.getToCrit()));
			stmt.setString(12, StaticMethod
					.null2String(tawTestcard.getToCity()));
			stmt
					.setString(13, StaticMethod.null2String(tawTestcard
							.getIccid()));
			stmt.setString(14, StaticMethod
					.null2String(tawTestcard.getMsisdn()));
			stmt.setString(15, StaticMethod.null2String(tawTestcard.getImsi()));
			stmt.setString(16, StaticMethod.null2String(tawTestcard.getPin1()));
			stmt.setString(17, StaticMethod.null2String(tawTestcard.getPin2()));
			stmt.setString(18, StaticMethod.null2String(tawTestcard.getPuk1()));
			stmt.setString(19, StaticMethod.null2String(tawTestcard.getPuk2()));
			stmt.setString(20, StaticMethod.null2String(tawTestcard
					.getPassword()));
			stmt.setString(21, StaticMethod.null2String(tawTestcard
					.getOperation()));
			stmt.setString(22, StaticMethod.null2String(tawTestcard
					.getBegintime()));
			stmt.setString(23, StaticMethod.null2String(tawTestcard
					.getEndtime()));
			stmt.setString(24, StaticMethod
					.null2String(tawTestcard.getIntime()));
			stmt
					.setString(25, StaticMethod.null2String(tawTestcard
							.getState()));
			stmt
					.setString(26, StaticMethod.null2String(tawTestcard
							.getOldNo()));
			stmt
					.setString(27, StaticMethod.null2String(tawTestcard
							.getOffer()));
			stmt.setString(28, StaticMethod.null2String(tawTestcard
					.getMsgcenterno()));
			stmt.setString(29, StaticMethod.null2String(tawTestcard
					.getLasttesttime()));
			stmt.setString(30, StaticMethod.null2String(tawTestcard
					.getTestresult()));
			stmt.setString(31, StaticMethod.null2String(tawTestcard
					.getDealresult()));
			stmt
					.setString(32, StaticMethod.null2String(tawTestcard
							.getAdder()));
			stmt.setString(33, StaticMethod
					.null2String(tawTestcard.getTelnum()));
			stmt.setString(34, StaticMethod.null2String(tawTestcard
					.getPosition()));
			stmt.setString(35, StaticMethod.null2String(tawTestcard
					.getCardpackage()));
			stmt.setString(36, StaticMethod.null2String(tawTestcard.getExes()));
			stmt.setString(37, StaticMethod.null2String(tawTestcard
					.getVolumenum()));
			stmt.setString(38, StaticMethod.null2String(tawTestcard
					.getPagenum()));
			stmt.executeUpdate();
			close(stmt);
			conn.commit();
		} catch (SQLException e) {
			close(stmt);
			e.printStackTrace();
		} finally {
			tawTestcard = null;
			close(conn);
		}
	}

	public void delete(String iccid) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "UPDATE taw_testcard SET deleted=1 where iccid='"
					+ iccid + "'";
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
  
	public TawTestcard retrieve(String iccid) throws SQLException {
		TawTestcard tawTestcard = null;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		// iccid=StaticMethod.getPageString(iccid);
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT id,leave,cardType ,fromCanton ,fromCountry ,fromOpe ,fromCrit ,fromCity ,toCanton ,toCountry ,toOpe ,toCrit ,toCity ,iccid ,msisdn ,imsi ,pin1,pin2,puk1,puk2,password ,operation ,begintime ,endtime ,intime,state,oldno,offer,msgcenterno,lasttesttime,testresult,dealresult,adder,telnum,position,cardpackage,exes,volumenum,pagenum,msisdn1,imsi1,phone_number,is_alive FROM taw_testcard WHERE iccid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iccid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tawTestcard = new TawTestcard();
				populate(tawTestcard, rs);
				// tawTestcard.setId(rs.getInt(1));
				tawTestcard.setLeave(rs.getString(2));
				tawTestcard.setCardType(rs.getString(3));
				tawTestcard.setFromCanton(rs.getString(4));
				tawTestcard.setFromCountry(rs.getString(5));
				tawTestcard.setFromOpe(rs.getString(6));
				tawTestcard.setFromCrit(rs.getString(7));
				tawTestcard.setFromCity(rs.getString(8));
				tawTestcard.setToCanton(rs.getString(9));
				tawTestcard.setToCountry(rs.getString(10));
				tawTestcard.setToOpe(rs.getString(11));
				tawTestcard.setToCrit(rs.getString(12));
				tawTestcard.setToCity(rs.getString(13));
				tawTestcard.setOldNo(rs.getString(27));
				tawTestcard.setMsgcenterno(rs.getString(29));
				// tawTestcard.setLasttesttime(rs.getString(30));
				tawTestcard.setTestresult(rs.getString(31));
				tawTestcard.setDealresult(rs.getString(32));
				tawTestcard.setAdder(rs.getString(33));
				tawTestcard.setTelnum(rs.getString(34));
				tawTestcard.setPosition(rs.getString(35));
				tawTestcard.setCardpackage(rs.getString(36));
				tawTestcard.setExes(rs.getString(37));
				tawTestcard.setVolumenum(rs.getString(38));
				tawTestcard.setPagenum(rs.getString(39));
				tawTestcard.setMsisdn1(rs.getString(40));
				tawTestcard.setImsi1(rs.getString(41));
				tawTestcard.setPhoneNumber(rs.getString(42));
				tawTestcard.setIsAlive(rs.getString(43));
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
		return tawTestcard;
	}
	

	public TawTestcard retrieveMsisdn(String msisdn) throws SQLException {
		TawTestcard tawTestcard = null;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		// iccid=StaticMethod.getPageString(iccid);
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT id,leave,cardType ,fromCanton ,fromCountry ,fromOpe ,fromCrit ,fromCity ,toCanton ,toCountry ,toOpe ,toCrit ,toCity ,iccid ,msisdn ,imsi ,pin1,pin2,puk1,puk2,password ,operation ,begintime ,endtime ,intime,state,oldno,offer,msgcenterno,lasttesttime,testresult,dealresult,adder,telnum,position,cardpackage,exes,volumenum,pagenum,msisdn1,imsi1,phone_number,is_alive FROM taw_testcard WHERE msisdn=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, msisdn);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tawTestcard = new TawTestcard();
				populate(tawTestcard, rs);
				// tawTestcard.setId(rs.getInt(1));
				tawTestcard.setLeave(rs.getString(2));
				tawTestcard.setCardType(rs.getString(3));
				tawTestcard.setFromCanton(rs.getString(4));
				tawTestcard.setFromCountry(rs.getString(5));
				tawTestcard.setFromOpe(rs.getString(6));
				tawTestcard.setFromCrit(rs.getString(7));
				tawTestcard.setFromCity(rs.getString(8));
				tawTestcard.setToCanton(rs.getString(9));
				tawTestcard.setToCountry(rs.getString(10));
				tawTestcard.setToOpe(rs.getString(11));
				tawTestcard.setToCrit(rs.getString(12));
				tawTestcard.setToCity(rs.getString(13));
				tawTestcard.setOldNo(rs.getString(27));
				tawTestcard.setMsgcenterno(rs.getString(29));
				// tawTestcard.setLasttesttime(rs.getString(30));
				tawTestcard.setTestresult(rs.getString(31));
				tawTestcard.setDealresult(rs.getString(32));
				tawTestcard.setAdder(rs.getString(33));
				tawTestcard.setTelnum(rs.getString(34));
				tawTestcard.setPosition(rs.getString(35));
				tawTestcard.setCardpackage(rs.getString(36));
				tawTestcard.setExes(rs.getString(37));
				tawTestcard.setVolumenum(rs.getString(38));
				tawTestcard.setPagenum(rs.getString(39));
				tawTestcard.setMsisdn1(rs.getString(40));
				tawTestcard.setImsi1(rs.getString(41));
				tawTestcard.setPhoneNumber(rs.getString(42));
				tawTestcard.setIsAlive(rs.getString(43));
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
		return tawTestcard;
	}
	
	
	public TawTestcard retrievetelnum(String telnum) throws SQLException {
		TawTestcard tawTestcard = null;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		// iccid=StaticMethod.getPageString(iccid);
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT id,leave,cardType ,fromCanton ,fromCountry ,fromOpe ,fromCrit ,fromCity ,toCanton ,toCountry ,toOpe ,toCrit ,toCity ,iccid ,msisdn ,imsi ,pin1,pin2,puk1,puk2,password ,operation ,begintime ,endtime ,intime,state,oldno,offer,msgcenterno,lasttesttime,testresult,dealresult,adder,telnum,position,cardpackage,exes,volumenum,pagenum,msisdn1,imsi1,phone_number,is_alive FROM taw_testcard WHERE phone_number=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, telnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tawTestcard = new TawTestcard();
				populate(tawTestcard, rs);
				// tawTestcard.setId(rs.getInt(1));
				tawTestcard.setLeave(rs.getString(2));
				tawTestcard.setCardType(rs.getString(3));
				tawTestcard.setFromCanton(rs.getString(4));
				tawTestcard.setFromCountry(rs.getString(5));
				tawTestcard.setFromOpe(rs.getString(6));
				tawTestcard.setFromCrit(rs.getString(7));
				tawTestcard.setFromCity(rs.getString(8));
				tawTestcard.setToCanton(rs.getString(9));
				tawTestcard.setToCountry(rs.getString(10));
				tawTestcard.setToOpe(rs.getString(11));
				tawTestcard.setToCrit(rs.getString(12));
				tawTestcard.setToCity(rs.getString(13));
				tawTestcard.setOldNo(rs.getString(27));
				tawTestcard.setMsgcenterno(rs.getString(29));
				// tawTestcard.setLasttesttime(rs.getString(30));
				tawTestcard.setTestresult(rs.getString(31));
				tawTestcard.setDealresult(rs.getString(32));
				tawTestcard.setAdder(rs.getString(33));
				tawTestcard.setTelnum(rs.getString(34));
				tawTestcard.setPosition(rs.getString(35));
				tawTestcard.setCardpackage(rs.getString(36));
				tawTestcard.setExes(rs.getString(37));
				tawTestcard.setVolumenum(rs.getString(38));
				tawTestcard.setPagenum(rs.getString(39));
				tawTestcard.setMsisdn1(rs.getString(40));
				tawTestcard.setImsi1(rs.getString(41));
				tawTestcard.setPhoneNumber(rs.getString(42));
				tawTestcard.setIsAlive(rs.getString(43));
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
		return tawTestcard;
	}

	// ���Id����
	public TawTestcard retrieve(int id) throws SQLException {
		TawTestcard tawTestcard = null;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
			// 26 27 28 29 30 31 32
			String sql = "SELECT id,leave,cardType,fromCanton,fromCountry,fromOpe,fromCrit,fromCity,toCanton,toCountry,toOpe,toCrit,toCity ,iccid ,msisdn ,imsi ,pin1,pin2,puk1,puk2,password,operation,begintime,endtime,intime,state,oldno,offer,msgcenterno,lasttesttime,testresult,dealresult,cardpackage,exes FROM taw_testcard WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tawTestcard = new TawTestcard();
				populate(tawTestcard, rs);
				tawTestcard.setId(rs.getInt(1));
				tawTestcard.setLeave(rs.getString(2));
				tawTestcard.setCardType(rs.getString(3));
				tawTestcard.setFromCanton(rs.getString(4));
				tawTestcard.setFromCountry(rs.getString(5));
				tawTestcard.setFromOpe(rs.getString(6));
				tawTestcard.setFromCrit(rs.getString(7));
				tawTestcard.setFromCrit(rs.getString(8));
				tawTestcard.setToCanton(rs.getString(9));
				tawTestcard.setToCountry(rs.getString(10));
				tawTestcard.setToCrit(rs.getString(12));
				tawTestcard.setToCity(rs.getString(13));
				tawTestcard.setOldNo(rs.getString(26));
				tawTestcard.setMsgcenterno(rs.getString(29));
				tawTestcard.setLasttesttime(rs.getString(30));
				tawTestcard.setTestresult(rs.getString(31));
				tawTestcard.setDealresult(rs.getString(32));
				tawTestcard.setCardpackage(rs.getString(33));
				tawTestcard.setExes(rs.getString(34));
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
		return tawTestcard;
	}

	public List list(int offset, int limit, int[] size, String condition)
			throws SQLException {
		ArrayList list = new ArrayList();
		
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TawTestcard tawTestcard = null;
		ID2NameServiceImpl mgr = (ID2NameServiceImpl) ApplicationContextHolder
				.getInstance().getBean("id2nameService");
		TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
		size[0] = this.getLengh(condition);
		if (!condition.equals("") && !condition.equals(null)) {
			condition = " where " + condition.substring(4);
		}

		try {
			conn = ds.getConnection();
			String sql = "SELECT leave,cardType ,fromCanton ,fromCountry ,fromOpe ,fromCrit ,fromCity ,toCanton ,toCountry ,toOpe ,toCrit ,toCity ,iccid ,msisdn ,imsi ,pin1,pin2 ,puk1,puk2,operation ,begintime ,endtime ,intime,state,oldno,cardpackage,adder,exes,telnum,imsi1,msisdn1,is_alive,phone_number,id FROM taw_testcard"
					+ condition + " order by iccid, phone_number desc";
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
				tawTestcard = new TawTestcard();
				populate(tawTestcard, rs);
				tawTestcard.setToOpe(rs.getString(10));
				tawTestcard.setFromCrit(rs.getString(6));
				tawTestcard.setFromCity(rs.getString(7));
				tawTestcard.setFromCountry(rs.getString(4));
				tawTestcard.setBegintime(rs.getString(21));
				tawTestcard.setEndtime(rs.getString(22));
				tawTestcard.setIccid(rs.getString(13));
				if (rs.getString(1) != null && !"".equals(rs.getString(1))) {
					// tawTestcard.setLeave(tawEventDicDAO.findName(rs.getString(1)));
					tawTestcard.setLeave(mgr.id2Name(rs.getString(1),
							"ItawSystemDictTypeDao"));

				} else {
					tawTestcard.setLeave("");
				}
				tawTestcard.setOldNo(rs.getString(25));
				tawTestcard.setAdder(rs.getString(27));
				tawTestcard.setCardpackage(rs.getString(26));
				tawTestcard.setExes(rs.getString(28));
				tawTestcard.setTelnum(rs.getString(29));
				tawTestcard.setFromOpe(rs.getString(5));
				tawTestcard.setImsi1(rs.getString(30));
				tawTestcard.setMsisdn1(rs.getString(31));
				tawTestcard.setIsAlive(rs.getString(32));
				System.out.println("asssssssssssss"+rs.getString(33)+rs.getString("phone_number")+"as");
				tawTestcard.setPhoneNumber(rs.getString("phone_number"));
							tawTestcard.setId(rs.getInt(34));

				if (!(tawTestcard.getImsi1() == (null))
						&& !(tawTestcard.getImsi1().trim().equals(""))) {
					tawTestcard.setImsi(tawTestcard.getImsi() + " / "
							+ tawTestcard.getImsi1());
				}
				if (!(tawTestcard.getMsisdn1() == (null))
						&& !(tawTestcard.getMsisdn1().trim().equals(""))) {
					tawTestcard.setMsisdn(tawTestcard.getMsisdn() + " / "
							+ tawTestcard.getMsisdn1());
				}
				// try {
				// tawTestcard.setFromOpe(tawEventDicDAO.findName(rs.getString(5)));
				// }
				// catch (Exception e) {e.printStackTrace();}
				try {
					if (tawTestcard.getState().equals("0")) {
						tawTestcard.setState(TestCardMgrLocator.getAttributes().getNormalState());
					} else if (tawTestcard.getState().equals("1")) {
						tawTestcard.setState(TestCardMgrLocator.getAttributes().getDowntimeState());
					} else if (tawTestcard.getState().equals("-1")) {
						tawTestcard.setState("未申请");
					} else if (tawTestcard.getState().equals("2")) {
						tawTestcard.setState(TestCardMgrLocator.getAttributes().getLostState());
					} else if (tawTestcard.getState().equals("3")) {
						tawTestcard.setState(TestCardMgrLocator.getAttributes().getLendingState());
					} else if (tawTestcard.getState().equals("4")) {
						tawTestcard.setState(TestCardMgrLocator.getAttributes().getUseState());
					} else if (tawTestcard.getState().equals("5")) {
						tawTestcard.setState(TestCardMgrLocator.getAttributes().getScrappedState());
					} else if (tawTestcard.getState().equals("6")) {
						tawTestcard.setState(TestCardMgrLocator.getAttributes().getRegistrationState());
					} else if (tawTestcard.getState().equals("8")) {
						tawTestcard.setState(TestCardMgrLocator.getAttributes().getQuestionState());
					} else if (tawTestcard.getState().equals(
							StaticValue.STATUS_WAIT)) {
						tawTestcard.setState(TestCardMgrLocator.getAttributes().getPendingState());
					} else if (tawTestcard.getState().equals(
							StaticValue.STATUS_PASS)) {
						tawTestcard.setState(TestCardMgrLocator.getAttributes().getAuditedState());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if(!"".equals(StaticMethod.null2String(tawTestcard.getIsAlive()))){
					
					if (tawTestcard.getIsAlive().equals(
							StaticValue.STATUS_ALIVE)) {
						tawTestcard.setIsAlive("已激活");
					} else if (tawTestcard.getIsAlive().equals(
							StaticValue.STATUS_UNALIVE)) {
						tawTestcard.setIsAlive("未激活");
					}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (StaticMethod.null2String(rs.getString(2)).equals("0")) {
						tawTestcard.setCardType("国际出访卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("1")) {
						tawTestcard.setCardType("国际来访卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("2")) {
						tawTestcard.setCardType("省际来访卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("3")) {
						tawTestcard.setCardType("省际出访卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("4")) {
						tawTestcard.setCardType("本地测试卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("5")) {
						tawTestcard.setCardType("省内来访卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("6")) {
						tawTestcard.setCardType("省内出访卡");
					}
					/*
					 * if (rs.getString(2).equals("0")) {
					 * tawTestcard.setCardType("国内卡"); } else {
					 * tawTestcard.setCardType("国际卡"); }
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}
				   try{
				    	Integer.parseInt(tawTestcard.getCardpackage());
				    	tawTestcard.setCardpackage(tawEventDicDAO.findName(tawTestcard.getCardpackage()));	
				    } catch (Exception e) {
				    	tawTestcard.setCardpackage(tawTestcard.getCardpackage());	
					}
//				tawTestcard.setCardpackage(cardpackage)
				list.add(tawTestcard);
				tawTestcard = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			tawEventDicDAO = null;
			tawTestcard = null;
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
			String sql = "SELECT count(*) from taw_testcard where deleted=0 "
					+ condition;
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				length = rs.getInt(1);
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

	// -------------Ψһֵ���ع���-------------
	public int ifIccid(String type, String value) throws SQLException {
		int length = 0;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT count(*) from taw_testcard where deleted=0 and "
					+ type + "='" + value + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				length = rs.getInt(1);
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

	// -------------�ж������ȷ�Թ���-------------
	public int ifRight(String tableNmae, String type, String value)
			throws SQLException {
		int length = 0;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT count(*) from " + tableNmae
					+ " where deleted=0 and " + type + "='" + value + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				length = rs.getInt(1);
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

	public List getStorageIdList() throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select id from taw_eventdic where parent_id=11";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getObject(1));
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
		return list;
	}

	public List getStorageIdListSystem() throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select dictid from taw_system_dicttype where parentdictid ='10401'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getObject(1));
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
		return list;
	}

	public String getStorage(String _storageid) throws SQLException {
		String storagename = "";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT name FROM taw_eventdic where id= "
					+ _storageid;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				storagename = (String) rs.getObject(1);
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
		return storagename;
	}

	public String getState(String _iccid) throws SQLException {
		String state = "";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT state FROM taw_testcard where iccid= "
					+ _iccid;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				state = (String) rs.getObject(1);
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
		return state;
	}

	public int getId(String _iccid) throws SQLException {
		int id = 0;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT id FROM taw_testcard where iccid='" + _iccid
					+ "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
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
		return id;
	}

	// ͳ�Ʋֿ��й���state�������
	public int getStatNum(int _state, String _storageid) {
		int StatNum = 0;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from taw_testcard where leave = '"
					+ _storageid + "'and state = '" + _state + "'";
			if (_state == 5) {
				sql = sql + " and deleted='1'";
			} else {
				sql = sql + " and deleted='0'";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StatNum = rs.getInt(1);
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
		return StatNum;
	}

	public int getActStatNum(int _state, String _storageid) {
		int StatNum = 0;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from taw_testcard where leave = '"
					+ _storageid + "'and is_alive = '" + _state + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StatNum = rs.getInt(1);
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
		return StatNum;
	}

	// ͳ��
	public List statall() throws Exception {
		int[] stateTypeArr = { 0, 1, 2, 3, 5 };
		// ��ȡ�
		ID2NameServiceImpl mgr = (ID2NameServiceImpl) ApplicationContextHolder
				.getInstance().getBean("id2nameService");
		List storageidlist = this.getStorageIdListSystem();
		List statresult = new ArrayList();
		if (!storageidlist.isEmpty()) {
			int[] stattemp = new int[5];
			for (int i = 0; i < storageidlist.size(); i++) {
				String storage = // this.getStorage(storageidlist.get(i).toString());
				mgr.id2Name(storageidlist.get(i).toString(),
						"ItawSystemDictTypeDao");
				for (int j = 0; j < stateTypeArr.length; j++) {
					int temp = this.getStatNum(stateTypeArr[j], storageidlist
							.get(i).toString());
					stattemp[j] = temp;
				}
				TawStat st = new TawStat();
				st.setLeave(StaticMethod.nullObject2int(storageidlist.get(i)));
				st.setStorage(storage);
				st.setNaturalnum(stattemp[0]);// ����
				st.setPausenum(stattemp[1]);// ͣ����
				st.setLosenum(stattemp[2]);// ��ʧ��
				st.setLoannum(stattemp[3]);// �����
				st.setScrapnum(stattemp[4]);// ������
				int temp = stattemp[0] + stattemp[1] + stattemp[3];
				st.setSummation(temp);
				statresult.add(st);
			}
		}
		return statresult;
	}

	public List statActall() throws Exception {
		int unrequest = new Integer(StaticValue.STATUS_UNREQUEST).intValue();
		int pass = new Integer(StaticValue.STATUS_PASS).intValue();
		int unalive = new Integer(StaticValue.STATUS_UNALIVE).intValue();
		int alive = new Integer(StaticValue.STATUS_ALIVE).intValue();
		int[] stateTypeArr = { unrequest, pass, unalive, alive };// 未申请、已审批、未激活、已激活
		// ��ȡ�
		ID2NameServiceImpl mgr = (ID2NameServiceImpl) ApplicationContextHolder
				.getInstance().getBean("id2nameService");
		List storageidlist = this.getStorageIdListSystem();
		List statresult = new ArrayList();
		if (!storageidlist.isEmpty()) {
			int[] stattemp = new int[5];
			for (int i = 0; i < storageidlist.size(); i++) {
				String storage = // this.getStorage(storageidlist.get(i).toString());
				mgr.id2Name(storageidlist.get(i).toString(),
						"ItawSystemDictTypeDao");
				for (int j = 0; j < stateTypeArr.length; j++) {
					int temp = 0;
					if (j > 1) {
						temp = this.getActStatNum(stateTypeArr[j], storageidlist
								.get(i).toString());
					} else {
						temp = this.getStatNum(stateTypeArr[j], storageidlist
								.get(i).toString());
					}
					stattemp[j] = temp;
				}
				TawActivateStat st = new TawActivateStat();
				st.setLeave(StaticMethod.nullObject2int(storageidlist.get(i)));
				st.setStorage(storage);
				st.setUnapplynum(stattemp[0]);// 未申请����
				st.setApplyednum(stattemp[1]);// 已审批ͣ����
				st.setUnalivenum(stattemp[2]);// 未激活��ʧ��
				st.setAlivenum(stattemp[3]);// 已激活������
				int temp = stattemp[2] + stattemp[3];
				st.setSummation(temp);
				statresult.add(st);
			}
		}
		return statresult;
	}

	/**
	 * TawTestcardDAO
	 */
	public TawTestcardDAO() {
	}

	public int[] insert1(List list) throws SQLException {
		int[] counts = null;
		com.boco.eoms.db.util.BocoConnection conn = null;
		try {
			// ,Units,Operator,Note,Intime,State
			conn = ds.getConnection();
			String sql = "INSERT INTO taw_testcard (leave,cardtype,fromCanton,fromCountry,fromOpe,"
					+ "fromCrit,fromCity,toCanton,toCountry,toOpe,toCrit,toCity,iccid,msisdn,"
					+ "imsi,pin1,pin2,puk1,puk2,password,operation,begintime,endtime,intime,"
					+ "state,oldno,deleted,offer,msgcenterno,lasttesttime,testresult,dealresult,adder,telnum,position,cardpackage,exes,volumenum,pagenum)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			int intList = list.size();
			for (int i = 0; i < intList; i++) {
				TawTestcard tawTestcard = (TawTestcard) list.get(i);
				stmt.setString(1, StaticMethod.null2String(tawTestcard
						.getLeave()));
				stmt.setString(2, StaticMethod.null2String(tawTestcard
						.getCardType()));
				stmt.setString(3, StaticMethod.null2String(tawTestcard
						.getFromCanton()));
				stmt.setString(4, StaticMethod.null2String(tawTestcard
						.getFromCountry()));
				stmt.setString(5, StaticMethod.null2String(tawTestcard
						.getFromOpe()));
				stmt.setString(6, StaticMethod.null2String(tawTestcard
						.getFromCrit()));
				stmt.setString(7, StaticMethod.null2String(tawTestcard
						.getFromCity()));
				stmt.setString(8, StaticMethod.null2String(tawTestcard
						.getToCanton()));
				stmt.setString(9, StaticMethod.null2String(tawTestcard
						.getToCountry()));
				stmt.setString(10, StaticMethod.null2String(tawTestcard
						.getToOpe()));
				stmt.setString(11, StaticMethod.null2String(tawTestcard
						.getToCrit()));
				stmt.setString(12, StaticMethod.null2String(tawTestcard
						.getToCity()));
				stmt.setString(13, StaticMethod.null2String(tawTestcard
						.getIccid()));
				stmt.setString(14, StaticMethod.null2String(tawTestcard
						.getMsisdn()));
				stmt.setString(15, StaticMethod.null2String(tawTestcard
						.getImsi()));
				stmt.setString(16, StaticMethod.null2String(tawTestcard
						.getPin1()));
				stmt.setString(17, StaticMethod.null2String(tawTestcard
						.getPin2()));
				stmt.setString(18, StaticMethod.null2String(tawTestcard
						.getPuk1()));
				stmt.setString(19, StaticMethod.null2String(tawTestcard
						.getPuk2()));
				stmt.setString(20, StaticMethod.null2String(tawTestcard
						.getPassword()));
				stmt.setString(21, StaticMethod.null2String(tawTestcard
						.getOperation()));
				stmt.setString(22, StaticMethod.null2String(tawTestcard
						.getBegintime()));
				stmt.setString(23, StaticMethod.null2String(tawTestcard
						.getEndtime()));
				stmt.setString(24, StaticMethod.null2String(tawTestcard
						.getIntime()));
				stmt.setString(25, StaticMethod.null2String(tawTestcard
						.getState()));
				stmt.setString(26, StaticMethod.null2String(tawTestcard
						.getOldNo()));
				stmt.setInt(27, 0);
				stmt.setString(28, StaticMethod.null2String(tawTestcard
						.getOffer()));
				stmt.setString(29, StaticMethod.null2String(tawTestcard
						.getMsgcenterno()));
				stmt.setString(30, StaticMethod.null2String(tawTestcard
						.getLasttesttime()));
				stmt.setString(31, StaticMethod.null2String(tawTestcard
						.getTestresult()));
				stmt.setString(32, StaticMethod.null2String(tawTestcard
						.getDealresult()));
				stmt.setString(33, StaticMethod.null2String(tawTestcard
						.getAdder()));
				stmt.setString(34, StaticMethod.null2String(tawTestcard
						.getTelnum()));
				stmt.setString(35, StaticMethod.null2String(tawTestcard
						.getPosition()));
				stmt.setString(36, StaticMethod.null2String(tawTestcard
						.getCardpackage()));
				stmt.setString(37, StaticMethod.null2String(tawTestcard
						.getExes()));
				stmt.setString(38, StaticMethod.null2String(tawTestcard
						.getVolumenum()));
				stmt.setString(39, StaticMethod.null2String(tawTestcard
						.getPagenum()));
				stmt.addBatch();
			}
			// counts = stmt.execute();
			conn.commit();
			close(stmt);
		} catch (SQLException ex) {
			rollback(conn);
			ex.printStackTrace();
		} finally {
			close(conn);
		}
		return counts;
	}

	public HashMap getPartId() throws SQLException {
		HashMap map = new HashMap();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select id,iccid from taw_testcard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(StaticMethod.strFromDBToPage(rs.getString(2)), Integer
						.toString(rs.getInt(1)));
				// System.out.println(StaticMethod.strFromDBToPage(rs.getString(2)));
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
		return map;
	}

	public List loadInTestcard(String _leave) throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select volumenum,pagenum,fromCountry,fromOpe,fromCrit,fromcity,iccid,msisdn,imsi,cardType,state,cardpackage,position,exes,adder,operation,oldNo,offer,msgcenterno from taw_testcard where cardtype in(1,2,4,5) and leave="
					+ _leave;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TawTestcard tawTestcard = new TawTestcard();
				populate(tawTestcard, rs);
				tawTestcard.setVolumenum(rs.getString(1));
				tawTestcard.setPagenum(rs.getString(2));
				tawTestcard.setFromCountry(rs.getString(3));
				tawTestcard.setFromOpe(rs.getString(4));
				tawTestcard.setFromCrit(rs.getString(5));
				tawTestcard.setFromCity(rs.getString(6));
				tawTestcard.setIccid(rs.getString(7));
				tawTestcard.setMsisdn(rs.getString(8));
				tawTestcard.setImsi(rs.getString(9));
				tawTestcard.setCardType(rs.getString(10));
				tawTestcard.setState(rs.getString(11));
				tawTestcard.setCardpackage(rs.getString(12));
				tawTestcard.setPosition(rs.getString(13));
				tawTestcard.setExes(rs.getString(14));
				tawTestcard.setAdder(rs.getString(15));
				tawTestcard.setOperation(rs.getString(16));
				tawTestcard.setOldNo(rs.getString(17));
				tawTestcard.setOffer(rs.getString(18));
				tawTestcard.setMsgcenterno(rs.getString(19));
				list.add(tawTestcard);
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
		return list;
	}

	public List loadOutTestcard(String _leave) throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select fromCountry,fromOpe,fromCrit,fromCity,iccid,msisdn,telnum,cardType,state,cardpackage,toCrit,toCity,adder,intime,operation from taw_testcard where cardtype in (0,3,6) and leave='"
					+ _leave + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TawTestcard tawTestcard = new TawTestcard();
				populate(tawTestcard, rs);
				tawTestcard.setFromCountry(rs.getString(1));
				tawTestcard.setFromOpe(rs.getString(2));
				tawTestcard.setFromCrit(rs.getString(3));
				tawTestcard.setFromCity(rs.getString(4));
				tawTestcard.setIccid(rs.getString(5));
				tawTestcard.setMsisdn(rs.getString(6));
				tawTestcard.setTelnum(rs.getString(7));
				tawTestcard.setCardType(rs.getString(8));
				tawTestcard.setState(rs.getString(9));
				tawTestcard.setCardpackage(rs.getString(10));
				tawTestcard.setToCrit(rs.getString(11));
				tawTestcard.setToCity(rs.getString(12));
				tawTestcard.setAdder(rs.getString(13));
				tawTestcard.setIntime(rs.getString(14));
				tawTestcard.setOperation(rs.getString(15));
				list.add(tawTestcard);
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
		return list;
	}

	/**
	 * @see �༶������
	 * @param layer
	 *            ������ݵĲ���
	 * @return
	 */
	public MyTreeMap getStrTree() {
		MyTreeMap myTree = new MyTreeMap();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql;
			sql = "select layer,name,radix from taw_testcard_tree order by layer ,radix";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				myTree.init(rs.getString(1), rs.getString(2));
			}
			close(rs);
			close(pstmt);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			close(conn);
		}
		return myTree;
	}

	/**
	 * @see �༶������
	 * @param layer
	 *            ������ݵĲ���
	 * @return
	 */
	public String getMsisdn(String iccid) {
		String msisdn = "";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql;
			sql = "select msisdn from taw_testcard where iccid='" + iccid + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				msisdn = rs.getString(1);
			}
			close(rs);
			close(pstmt);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			close(conn);
		}
		return msisdn;
	}

	public String getLeave(String iccid) {
		String leave = "";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql;
			sql = "select leave from taw_testcard where iccid='" + iccid + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				leave = rs.getString(1);
			}
			close(rs);
			close(pstmt);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			close(conn);
		}
		return leave;
	}

	public List clearlist(int offset, int limit, int[] size, String condition)
			throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ID2NameServiceImpl mgr = (ID2NameServiceImpl) ApplicationContextHolder
				.getInstance().getBean("id2nameService");
		TawTestcard tawTestcard = null;
		TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
		size[0] = this.getClearlengh(condition);
		if (!condition.equals("") && !condition.equals(null)) {
			condition = " where " + condition.substring(4);
		}

		try {
			conn = ds.getConnection();
			String sql = "SELECT leave,cardType ,fromCanton ,fromCountry ,fromOpe ,fromCrit ,fromCity ,toCanton ,toCountry ,toOpe ,toCrit ,toCity ,iccid ,msisdn ,imsi ,pin1,pin2 ,puk1,puk2,operation ,begintime ,endtime ,intime,state,oldno,cardpackage,adder,exes,telnum,imsi1,msisdn1 FROM taw_testcard "
					+ condition
					+ "and state in ('0','1','2','6') order by iccid desc";
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
				tawTestcard = new TawTestcard();
				populate(tawTestcard, rs);
				tawTestcard.setToOpe(rs.getString(10));
				tawTestcard.setFromCrit(rs.getString(6));
				tawTestcard.setFromCity(rs.getString(7));
				tawTestcard.setFromCountry(rs.getString(4));
				tawTestcard.setBegintime(rs.getString(21));
				tawTestcard.setEndtime(rs.getString(22));
				tawTestcard.setIccid(rs.getString(13));
				if (rs.getString(1) != null && !"".equals(rs.getString(1))) {
					tawTestcard.setLeave(mgr.id2Name(rs.getString(1),
							"ItawSystemDictTypeDao"));
				} else {
					tawTestcard.setLeave("");
				}
				tawTestcard.setOldNo(rs.getString(25));
				tawTestcard.setAdder(rs.getString(27));
				tawTestcard.setCardpackage(rs.getString(26));
				tawTestcard.setExes(rs.getString(28));
				tawTestcard.setTelnum(rs.getString(29));
				tawTestcard.setFromOpe(rs.getString(5));
				tawTestcard.setImsi1(rs.getString(30));
				tawTestcard.setMsisdn1(rs.getString(31));
				if (!(tawTestcard.getImsi1() == (null))
						&& !(tawTestcard.getImsi1().trim().equals(""))) {
					tawTestcard.setImsi(tawTestcard.getImsi() + " / "
							+ tawTestcard.getImsi1());
				}
				if (!(tawTestcard.getMsisdn1() == (null))
						&& !(tawTestcard.getMsisdn1().trim().equals(""))) {
					tawTestcard.setMsisdn(tawTestcard.getMsisdn() + " / "
							+ tawTestcard.getMsisdn1());
				}
				// try {
				// tawTestcard.setFromOpe(tawEventDicDAO.findName(rs.getString(5)));
				// }
				// catch (Exception e) {e.printStackTrace();}
				try {
					if (tawTestcard.getState().equals("0")) {
						tawTestcard.setState("正常");
					} else if (tawTestcard.getState().equals("1")) {
						tawTestcard.setState("停机");
					} else if (tawTestcard.getState().equals("2")) {
						tawTestcard.setState("遗失");
					} else if (tawTestcard.getState().equals("3")) {
						tawTestcard.setState("借出");
					} else if (tawTestcard.getState().equals("4")) {
						tawTestcard.setState("使用");
					} else if (tawTestcard.getState().equals("5")) {
						tawTestcard.setState("报废");
					} else if (tawTestcard.getState().equals("6")) {
						tawTestcard.setState("SIM卡注册失败");
					} else if (tawTestcard.getState().equals("8")) {
						tawTestcard.setState("待接收");
					} else if (tawTestcard.getState().equals(
							StaticValue.STATUS_WAIT)) {
						tawTestcard.setState("待审核");
					} else if (tawTestcard.getState().equals(
							StaticValue.STATUS_PASS)) {
						tawTestcard.setState("已审核");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if (StaticMethod.null2String(rs.getString(2)).equals("0")) {
						tawTestcard.setCardType("国际出访卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("1")) {
						tawTestcard.setCardType("国际来访卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("2")) {
						tawTestcard.setCardType("省际来访卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("3")) {
						tawTestcard.setCardType("省际出访卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("4")) {
						tawTestcard.setCardType("本地测试卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("5")) {
						tawTestcard.setCardType("省内来访卡");
					} else if (StaticMethod.null2String(rs.getString(2))
							.equals("6")) {
						tawTestcard.setCardType("省内出访卡");
					}
					/*
					 * if (rs.getString(2).equals("0")) {
					 * tawTestcard.setCardType("国内卡"); } else {
					 * tawTestcard.setCardType("国际卡"); }
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}
				list.add(tawTestcard);
				tawTestcard = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			tawEventDicDAO = null;
			tawTestcard = null;
			close(conn);
		}
		return list;
	}

	public int getClearlengh(String condition) throws SQLException {
		int length = 0;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT count(*) from taw_testcard where deleted=0 and state in ('0','1','2','6') "
					+ condition;
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				length = rs.getInt(1);
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

	public void deleteMsisdn(String msisdn) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "UPDATE taw_testcard SET deleted=1 where msisdn='"
					+ msisdn + "'";
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

	public int getMsisdnCount(String msisdn, String leave) {
		int result = 0;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = ds.getConnection();

			sql = "select count(*) from taw_testcard where msisdn='" + msisdn
					+ "'";
			if (leave != null && !"".equals(leave)) {
				sql += " and leave='" + leave + "'";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			// rollback(conn); liquan modify
			e.printStackTrace();
		} finally {
			close(conn);

			sql = null;
			rs = null;
		}
		return result;
	}

	public void updateMsisdn(TawTestcard tawTestcard) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;

		// System.out.println(tawTestcard.getId());
		try {
			conn = ds.getConnection();
			String sql = "UPDATE taw_testcard SET leave=?,cardType=? ,fromCanton=? ,fromCountry=? ,fromOpe=? ,fromCrit=? ,fromCity=? ,toCanton=? ,toCountry=? ,toOpe=? ,toCrit=? ,toCity=?,imsi=? ,pin1=? ,pin2=?,puk1=?,puk2=? ,password=?,operation=? ,begintime=? ,endtime=? ,state=?,oldno=?,offer=? ,msgcenterno=?,lasttesttime=?,testresult=?,dealresult=?,telnum=?,position=?,cardpackage=?,exes=?,volumenum=?,pagenum=?,msisdn1=?,imsi1=? where msisdn=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tawTestcard.getLeave());
			pstmt.setString(2, tawTestcard.getCardType());
			pstmt.setString(3, tawTestcard.getFromCanton());
			pstmt.setString(4, tawTestcard.getFromCountry());
			pstmt.setString(5, tawTestcard.getFromOpe());
			pstmt.setString(6, tawTestcard.getFromCrit());
			pstmt.setString(7, tawTestcard.getFromCity());
			pstmt.setString(8, tawTestcard.getToCanton());
			pstmt.setString(9, tawTestcard.getToCountry());
			pstmt.setString(10, tawTestcard.getToOpe());
			pstmt.setString(11, tawTestcard.getToCrit());
			pstmt.setString(12, tawTestcard.getToCity());
			// pstmt.setString(13, tawTestcard.getMsisdn());
			pstmt.setString(13, tawTestcard.getImsi());
			pstmt.setString(14, tawTestcard.getPin1());
			pstmt.setString(15, tawTestcard.getPin2());
			pstmt.setString(16, tawTestcard.getPuk1());
			pstmt.setString(17, tawTestcard.getPuk2());
			pstmt.setString(18, tawTestcard.getPassword());
			pstmt.setString(19, tawTestcard.getOperation());
			pstmt.setString(20, tawTestcard.getBegintime());
			pstmt.setString(21, tawTestcard.getEndtime());
			pstmt.setString(22, tawTestcard.getState());
			pstmt.setString(23, tawTestcard.getOldNo());
			pstmt.setString(24, tawTestcard.getOffer());
			pstmt.setString(25, tawTestcard.getMsgcenterno());
			pstmt.setString(26, tawTestcard.getLasttesttime());
			pstmt.setString(27, tawTestcard.getTestresult());
			pstmt.setString(28, tawTestcard.getDealresult());
			pstmt.setString(29, tawTestcard.getTelnum());
			pstmt.setString(30, tawTestcard.getPosition());
			pstmt.setString(31, "");
			// pstmt.setString(32,
			// eventdao.findName(tawTestcard.getCardpackage())) ;
			pstmt.setString(32, tawTestcard.getExes());
			pstmt.setString(33, tawTestcard.getVolumenum());
			pstmt.setString(34, tawTestcard.getPagenum());
			pstmt.setString(35, tawTestcard.getMsisdn1());
			pstmt.setString(36, tawTestcard.getImsi1());
			pstmt.setString(37, tawTestcard.getMsisdn());
			pstmt.executeUpdate();
			close(pstmt);
			conn.commit();
		} catch (SQLException e) {
			close(pstmt);
			e.printStackTrace();
		} finally {
			tawTestcard = null;
			close(conn);
		}
	}

	/*
	 * 将用户输入的号码段转换成具体的号码，并入库（测试卡的状态为未激活）
	 */
	public void insertNotAlive(TawTestcard tawTestcard) throws SQLException {
		String sql;
		sql = "INSERT INTO taw_testcard (leave,is_alive,phone_number,state,deleted)"
				+ "VALUES (?,?,?,?,?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tawTestcard.getLeave());
			pstmt.setString(2, tawTestcard.getIsAlive());
			pstmt.setString(3, tawTestcard.getPhoneNumber());
			pstmt.setString(4, tawTestcard.getState());
			pstmt.setInt(5,0);
			
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
			tawTestcard = null;
			close(conn);
		}
	}

}
