package com.boco.eoms.partner.baseinfo.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partner.baseinfo.model.PartnerUser;

/**
 * <p>
 * Title:��f��Ϣ
 * </p>
 * <p>
 * Description:��f��Ϣ
 * </p>
 * <p>
 * Tue Feb 10 17:33:14 CST 2009
 * </p>
 *
 * @author liujinlong
 * @version 3.5
 */
public interface PartnerUserMgr {

    /**
     * ȡ��f��Ϣ �б�
     *
     * @return ������f��Ϣ�б�
     */
    public List getPartnerUsers();

    /**
     * �������ѯ��f��Ϣ
     *
     * @param id ���
     * @return ����ĳid�Ķ���
     */
    public PartnerUser getPartnerUser(final String id);

    /**
     * ������f��Ϣ
     *
     * @param partnerUser ��f��Ϣ
     */
    public void savePartnerUser(PartnerUser partnerUser);

    /**
     * ������ɾ����f��Ϣ
     *
     * @param id ���
     */
    public void removePartnerUser(final String id);

    /**
     * ��������ҳ��ѯ��f��Ϣ
     *
     * @param curPage  ��ǰҳ
     * @param pageSize ÿҳ���¼����
     * @param whereStr ��ѯ���
     * @return ������f��Ϣ�ķ�ҳ�б�
     */
    public Map getPartnerUsers(final Integer curPage, final Integer pageSize,
                               final String whereStr);

    //判断人力信息userId 是否唯一
    public Boolean isunique(final String userId);

    //批量删除某地市某厂商下所有的人力信息，参数是treeNodeId
    public void removePartnerUserByTreeNodeId(final String treeNodeId);

}