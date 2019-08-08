package com.boco.eoms.partner.baseinfo.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.partner.baseinfo.model.PartnerUserAndArea;

/**
 * <p>
 * Title:��Ա�������
 * </p>
 * <p>
 * Description:��Ա�������
 * </p>
 * <p>
 * Tue Mar 10 16:24:32 CST 2009
 * </p>
 *
 * @author liujinlong
 * @version 3.5
 */
public interface PartnerUserAndAreaDao extends Dao {

    /**
     * ȡ��Ա��������б�
     *
     * @return ������Ա��������б�
     */
    public List getPartnerUserAndAreas();

    /**
     * �������ѯ��Ա�������
     *
     * @param id ���
     * @return ����ĳid����Ա�������
     */
    public PartnerUserAndArea getPartnerUserAndArea(final String id);

    /**
     * ������Ա�������
     *
     * @param partnerUserAndArea ��Ա�������
     */
    public void savePartnerUserAndArea(PartnerUserAndArea partnerUserAndArea);

    /**
     * ���idɾ����Ա�������
     *
     * @param id ���
     */
    public void removePartnerUserAndArea(final String id);

    /**
     * ��ҳȡ�б�
     *
     * @param curPage  ��ǰҳ
     * @param pageSize ÿҳ��ʾ����
     * @param whereStr where���
     * @return map��totalΪ����,result(list) curPageҳ�ļ�¼
     */
    public Map getPartnerUserAndAreas(final Integer curPage, final Integer pageSize,
                                      final String whereStr);

    //由userId得到人员地域信息
    public PartnerUserAndArea getObjectByUserId(String userId);

    /**
     * 判断人力地域表userId是否唯一
     */
    public Boolean isunique(final String userId);

}