package com.boco.eoms.partner.baseinfo.dao;

import com.boco.eoms.base.dao.Dao;
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
 * 
 */
public interface PartnerUserDao extends Dao {

    /**
    *
    *ȡ��f��Ϣ�б�
    * @return ������f��Ϣ�б�
    */
    public List getPartnerUsers();
    
    /**
    * �������ѯ��f��Ϣ
    * @param id ���
    * @return ����ĳid����f��Ϣ
    */
    public PartnerUser getPartnerUser(final String id);
    
    /**
    *
    * ������f��Ϣ    
    * @param partnerUser ��f��Ϣ
    * 
    */
    public void savePartnerUser(PartnerUser partnerUser);
    
    /**
    * ���idɾ����f��Ϣ
    * @param id ���
    * 
    */
    public void removePartnerUser(final String id);
    
    /**
    * ��ҳȡ�б�
    * @param curPage ��ǰҳ
    * @param pageSize ÿҳ��ʾ����
    * @param whereStr where���
    * @return map��totalΪ����,result(list) curPageҳ�ļ�¼
    */
    public Map getPartnerUsers(final Integer curPage, final Integer pageSize,
			final String whereStr);
    
    //判断人力信息userId 是否唯一
    public Boolean isunique(final String userId);
    
	//批量删除某地市某厂商下所有的人力信息，参数是treeNodeId
	public void removePartnerUserByTreeNodeId(final String treeNodeId);
	
}