package com.boco.eoms.workbench.contact.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;

/**
 * <p>
 * Title:个人通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 15:59:30 AM
 * </p>
 *
 * @author 龚玉峰
 * @version 3.5.1
 */
public interface TawWorkbenchContactDao extends Dao {

    /**
     * Retrieves all of the tawWorkbenchContacts
     */
    public List getTawWorkbenchContacts(TawWorkbenchContact tawWorkbenchContact);

    /**
     * Gets tawWorkbenchContact's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     *
     * @param id the tawWorkbenchContact's id
     * @return tawWorkbenchContact populated tawWorkbenchContact object
     */
    public TawWorkbenchContact getTawWorkbenchContact(final String id);

    /**
     * Saves a tawWorkbenchContact's information
     *
     * @param tawWorkbenchContact the object to be saved
     */
    public void saveTawWorkbenchContact(TawWorkbenchContact tawWorkbenchContact);

    /**
     * Removes a tawWorkbenchContact from the database by id
     *
     * @param id the tawWorkbenchContact's id
     */
    public void removeTawWorkbenchContact(final String id);

    /**
     * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
     */
    public Map getTawWorkbenchContacts(final Integer curPage,
                                       final Integer pageSize);

    public Map getTawWorkbenchContacts(final Integer curPage,
                                       final Integer pageSize, final String whereStr);

    public List getSonsById(String parentid);

    public List getNextLevecContacts(final String userid, final String id,
                                     final String deleted);

    public String getUserStrById(final String id);

    public String getTeleByUserId(final String userId);

    public String getEmailByUserId(final String userId);

    public List getContactName(final String contactName, final String userid);

    /**
     * 根据联系人姓名取符合此联系人的列表
     *
     * @param name   联系人姓名
     * @param userId 个人通迅录某人
     * @return 符合联系人姓名的列表
     */
    public List getContactsByName(String name, String userId);
}
