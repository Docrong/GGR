package com.boco.eoms.duty.faultrecord.dao;

import java.util.List;import org.hibernate.classic.Session;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import com.boco.eoms.common.dao.HibernateDAO;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.duty.faultrecord.model.Faultrecord;

/**
 * <p>Title: ���ϼ�¼</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author zhangxiaobo
 * @version 1.0
 */

public class FaultrecordDAO extends HibernateDAO {

  /**
   * �����¼��ϸ��Ϣ
   * @param faultrecord Faultrecord ��¼����
   * @throws HibernateException �����쳣
   */
  public void saveInfo(Faultrecord faultrecord) throws HibernateException {
    this.save(faultrecord);
  }

  /**
   * �޸ļ�¼��ϸ��Ϣ
   * @param faultrecord Faultrecord ��¼����
   * @throws HibernateException �����쳣
   */
  public void updateInfo(Faultrecord faultrecord) throws HibernateException {
    this.update(faultrecord);
  }

  /**
   * ɾ���¼��ϸ��Ϣ
   * @param _id String ��¼ID
   * @param _delFlag int ɾ����
   * @throws Exception �����쳣
   */
  public void updateDelFlag(String _id, int _delFlag) throws HibernateException {
    Faultrecord faultrecord = (Faultrecord)this.load(_id, Faultrecord.class);
    faultrecord.setDelFlag(_delFlag);
    this.update(faultrecord);
  }

  /**
   * ɾ���¼��ϸ��Ϣ
   * @param _id String ��¼ID
   * @throws HibernateException �����쳣
   */
  public void deleteInfo(String _id) throws HibernateException {
    this.delete(_id);
  }

  /**
   * �鿴��¼��ϸ��Ϣ
   * @param _id String ��¼ID
   * @return Faultrecord ��¼����
   * @throws HibernateException �����쳣
   */
  public Faultrecord viewInfo(String _id) throws HibernateException {
    Faultrecord faultrecord = (Faultrecord)this.load(_id, Faultrecord.class);
    return faultrecord;
  }

  /**
   * ��ѯ������Ϣ
   * @param condition String ��ѯ���
   * @param pagePra int[] ��ҳ����
   * @throws Exception �����쳣
   * @return List ��ѯ�б�
   */
  public List searchInfo(String condition, int[] pagePra) throws
      HibernateException {
    Session s = HibernateUtil.currentSession();
    if (pagePra[2] <= 0) {
      pagePra[2] = count(condition);
    }
    Query query = s.createQuery(condition);
    query.setCacheable(true);
    query.setFirstResult(pagePra[0]);
    query.setMaxResults(pagePra[1]);
    return query.list();
  }

  /**
   * ��ѯĳ���ŵ�ĳ����ӵ�������Ϣ
   * @param pagePra int[] ��ҳ����
   * @throws Exception �����쳣
   * @return List ��ѯ�б�
   */
  public List listInfo(int[] pagePra, String userId, String deptId) throws
      HibernateException {
    String hSql = "from Faultrecord as faultrecord ";
    hSql += "where faultrecord.delFlag = 0 and faultrecord.userId='" + userId;
    hSql += "' and faultrecord.deptId='" + deptId;
    hSql += "' order by faultrecord.insertTime desc";

    Session s = HibernateUtil.currentSession();
    if (pagePra[2] <= 0) {
      pagePra[2] = count(hSql);
    }
    Query query = s.createQuery(hSql);
    query.setCacheable(true);
    query.setFirstResult(pagePra[0]);
    query.setMaxResults(pagePra[1]);
    return query.list();
  }
}
