package com.boco.eoms.db.hibernate;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.db.util.BocoConnection;

/**
 * <p>
 * Title: hibernate session and connection
 * </p>
 * <p>
 * Description: 璇旷敤threandlocal 鏉ヤ娇鐢╤ibernate镄剆ession 鍜宼ransction
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004.3.24
 * </p>
 * <p>
 * Company: boco oss eoms
 * </p>
 * 
 * @author 澶ф睙
 * @version 1.0
 */

public class HibernateUtil {

	private static final ThreadLocal sessionThread = new ThreadLocal();

	private static final ThreadLocal sessionCommitThread = new ThreadLocal();

	private static final ThreadLocal transactionThread = new ThreadLocal();

	private static ConnectionPool ds = ConnectionPool.getInstance();

	private static SessionFactory sf = null;
	static {
		try {
			// Configuration config = new Configuration().configure();
			// config.addClass(com.boco.eoms.worksheet.model.BaseWorkSheet.class);
//			sf = (SessionFactory) ApplicationContextHolder.getInstance()
//					.getBean("sessionFactory");
			// new HibernateTemplate(sf);
			// sf = config.buildSessionFactory();
	Configuration config = new Configuration().configure();
      //config.addClass(com.boco.eoms.worksheet.model.BaseWorkSheet.class);
      sf = config.buildSessionFactory();
		} catch (HibernateException ex) {
			throw new RuntimeException("Exception building SessionFactory: "
					+ ex.getMessage(), ex);
		}
	}

	/**
	 * 銮峰彇褰揿墠绾跨▼浣跨敤镄凷ession
	 * 濡傛灉链夊垯浣跨敤锛屽鏋沧病链夛紝灏辨柊鐢熸垚涓€涓?杩欎釜session浣跨敤镄勬垜浠嚜宸辩殑BocoConnection锛?
	 * 濡傛灉浠ュ悗闇€瑕佷娇鐢ㄥ彟澶栫殑杩炴帴锛岄偅涔埚彧瑕佹敼杩欎釜鍦版柟灏卞彲浠ヤ简銆?
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session currentSession() throws HibernateException {
		return currentSession(false);
	}

	/**
	 * 銮峰彇褰揿墠绾跨▼浣跨敤镄凷ession
	 * 濡傛灉链夊垯浣跨敤锛屽鏋沧病链夛紝灏辨柊鐢熸垚涓€涓?杩欎釜session浣跨敤镄勬垜浠嚜宸辩殑BocoConnection锛?
	 * 濡傛灉浠ュ悗闇€瑕佷娇鐢ㄥ彟澶栫殑杩炴帴锛岄偅涔埚彧瑕佹敼杩欎釜鍦版柟灏卞彲浠ヤ简銆?
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session currentSession(boolean flagUseLongActive)
			throws HibernateException {
		Session s = (Session) sessionThread.get();
		showSessionContent("currentSession");
		if (s == null) {
			BocoConnection conn = ds.getConnection();
			conn.setFlagUseLongActiveTime(flagUseLongActive);
			s = sf.openSession(conn);
			sessionThread.set(s);
		} else {
			BocoConnection conn = (BocoConnection) s.connection();
			try {
				if ((conn == null) || (conn.isClosed())) {
					conn = ds.getConnection();
					conn.setFlagUseLongActiveTime(flagUseLongActive);
					s = sf.openSession(conn);
					sessionThread.set(s);
				} else {
					conn.setFlagUseLongActiveTime(flagUseLongActive);
				}
			} catch (SQLException e) {
				System.out.println("session镄刢onnection鍑洪棶棰树简");
			}
		}
		return s;
	}

	/**
	 * 銮峰彇褰揿墠绾跨▼浣跨敤镄凷ession
	 * 濡傛灉链夊垯浣跨敤锛屽鏋沧病链夛紝灏辨柊鐢熸垚涓€涓?杩欎釜session浣跨敤镄勬垜浠嚜宸辩殑BocoConnection锛?
	 * 濡傛灉浠ュ悗闇€瑕佷娇鐢ㄥ彟澶栫殑杩炴帴锛岄偅涔埚彧瑕佹敼杩欎釜鍦版柟灏卞彲浠ヤ简銆?杩欎釜
	 * session浣跨敤镄勬槸涓€涓崟镫殑conn锛屽拰鎴戜滑鍦ㄦ櫘阃氱殑浜嫔姟涓殑conn鏄笉鍚岀殑锛岃繖涓猚onn鏄强镞舵彁浜ょ殑
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session currentCommitSession() throws HibernateException {
		Session s = (Session) sessionCommitThread.get();
		showSessionContent("currentCommitSession");
		if (s == null) {
			BocoConnection conn = ds.getConnection();
			s = sf.openSession(conn);
			sessionCommitThread.set(s);
		} else {
			BocoConnection conn = (BocoConnection) s.connection();
			try {
				if ((conn == null) || (conn.isClosed())) {
					conn = ds.getConnection();
					s = sf.openSession(conn);
					sessionCommitThread.set(s);
				}
			} catch (SQLException e) {
				System.out.println("session镄刢onnection鍑洪棶棰树简");
			}
		}
		return s;
	}

	/**
	 * 銮峰彇褰揿墠绾跨▼浣跨敤镄凷ession 濡傛灉链夊垯杩斿洖锛屽鏋沧病链夛紝灏辫繑锲瀗ull
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session getCurrentSession() throws HibernateException {
		showSessionContent("getCurrentSession");
		return (Session) sessionThread.get();
	}

	/**
	 * 鍚姩鎴栬€呭姞鍏ュ綋鍓峉ession镄凾ransaction
	 * 
	 * @return Transaction
	 * @throws HibernateException
	 */
	public static Transaction currentTransaction() throws HibernateException {
		Transaction tx = (Transaction) transactionThread.get();
		if (tx == null) {
			tx = currentSession().beginTransaction();
			transactionThread.set(tx);
		}
		return tx;
	}

	/**
	 * 鎻愪氦褰揿墠Session镄凾ransaction
	 * 
	 * @throws HibernateException
	 */
	public static void commitTransaction() throws HibernateException {
		Transaction tx = (Transaction) transactionThread.get();
		transactionThread.set(null);
		if (tx != null)
			tx.commit();
	}

	/**
	 * 锲炴粴褰揿墠Session镄凾ransaction
	 * 
	 * @throws HibernateException
	 */
	public static void rollbackTransaction() throws HibernateException {
		Transaction tx = (Transaction) transactionThread.get();
		transactionThread.set(null);
		if (tx != null)
			tx.rollback();
	}

	/**
	 * 鍏抽棴褰揿墠绾跨▼浣跨敤镄凷ession
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session s = (Session) sessionThread.get();
		showSessionContent("closeSession");
		if (s != null) {
			BocoConnection conn = (BocoConnection) s.connection();
			// System.out.println("closeSession conn thread id " +
			// sessionThread.hashCode());
			conn.close();
			s.clear();
			s.close();
		}
		sessionThread.set(null);
		closeCommitSession();
	}

	/**
	 * 鍏抽棴褰揿墠绾跨▼浣跨敤镄凷ession
	 * 
	 * @throws HibernateException
	 */
	public static void closeCommitSession() throws HibernateException {
		Session s = (Session) sessionCommitThread.get();
		showSessionContent("closeSession");
		if (s != null) {
			BocoConnection conn = (BocoConnection) s.connection();
			// System.out.println("closeSession conn thread id " +
			// sessionThread.hashCode());
			conn.close();
			s.clear();
			s.close();
		}
		sessionCommitThread.set(null);
	}

	private static void showSessionContent(String name) {
		/*
		 * try { System.out.println("--------" + name + "--begin-----------");
		 * System.out.println("--" + name + "---Session thread id :" +
		 * sessionThread.hashCode()); Session s = (Session) sessionThread.get();
		 * if (s != null) { BocoConnection conn = (BocoConnection)
		 * s.connection(); System.out.println("--" + name + "---conn timestamp :" +
		 * conn.getTimeStamp()); } System.out.println("--------" + name +
		 * "--end-----------"); }catch (Exception e){
		 * System.out.println("showSessionContent error"); }
		 */

	}

}
