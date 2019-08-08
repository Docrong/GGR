package com.boco.eoms.partner.baseinfo.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partner.baseinfo.model.PartnerDept;

/**
 * <p>
 * Title:����
 * </p>
 * <p>
 * Description:����
 * </p>
 * <p>
 * Mon Feb 09 10:57:12 CST 2009
 * </p>
 *
 * @author liujinlong
 * @version 3.5
 */
public interface PartnerDeptDao extends Dao {

    /**
     * ȡ�����б�
     *
     * @return ���س����б�
     */
    public List getPartnerDepts();

    /**
     * �������ѯ����
     *
     * @param id ���
     * @return ����ĳid�ĳ���
     */
    public PartnerDept getPartnerDept(final String id);

    /**
     * ���泧��
     *
     * @param partnerDept ����
     */
    public void savePartnerDept(PartnerDept partnerDept);

    /**
     * ���idɾ����
     *
     * @param id ���
     */
    public void removePartnerDept(final String id);

    /**
     * ��ҳȡ�б�
     *
     * @param curPage  ��ǰҳ
     * @param pageSize ÿҳ��ʾ����
     * @param whereStr where���
     * @return map��totalΪ����,result(list) curPageҳ�ļ�¼
     */
    public Map getPartnerDepts(final Integer curPage, final Integer pageSize,
                               final String whereStr);

    /**
     * 由字段treeId得到partnerDept
     */
    public PartnerDept getPartnerDeptByTreeId(final String treeId);

    /**
     * 由字段treeNodeId得到partnerDept
     */
    public PartnerDept getPartnerDeptByTreeNodeId(final String treeNodeId);

}