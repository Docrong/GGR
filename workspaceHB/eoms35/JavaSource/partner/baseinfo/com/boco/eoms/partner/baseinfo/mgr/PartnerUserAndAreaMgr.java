package com.boco.eoms.partner.baseinfo.mgr;

import java.util.List;
import java.util.Map;

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
public interface PartnerUserAndAreaMgr {

    /**
     * ȡ��Ա������� �б�
     *
     * @return ������Ա��������б�
     */
    public List getPartnerUserAndAreas();

    /**
     * �������ѯ��Ա�������
     *
     * @param id ���
     * @return ����ĳid�Ķ���
     */
    public PartnerUserAndArea getPartnerUserAndArea(final String id);

    /**
     * ������Ա�������
     *
     * @param partnerUserAndArea ��Ա�������
     */
    public void savePartnerUserAndArea(PartnerUserAndArea partnerUserAndArea);

    /**
     * ������ɾ����Ա�������
     *
     * @param id ���
     */
    public void removePartnerUserAndArea(final String id);

    /**
     * ��������ҳ��ѯ��Ա�������
     *
     * @param curPage  ��ǰҳ
     * @param pageSize ÿҳ���¼����
     * @param whereStr ��ѯ���
     * @return ������Ա������еķ�ҳ�б�
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