
package com.boco.eoms.workbench.contact.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
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
 * 
 */
public interface TawWorkbenchContactGroupDao extends Dao {

    /**
     * Retrieves all of the tawWorkbenchContactGroups
     */
    public List getTawWorkbenchContactGroups(TawWorkbenchContactGroup tawWorkbenchContactGroup);

    /**
     * Gets tawWorkbenchContactGroup's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawWorkbenchContactGroup's id
     * @return tawWorkbenchContactGroup populated tawWorkbenchContactGroup object
     */
    public TawWorkbenchContactGroup getTawWorkbenchContactGroup(final String id);

    /**
     * Saves a tawWorkbenchContactGroup's information
     * @param tawWorkbenchContactGroup the object to be saved
     */    
    public void saveTawWorkbenchContactGroup(TawWorkbenchContactGroup tawWorkbenchContactGroup);

    /**
     * Removes a tawWorkbenchContactGroup from the database by id
     * @param id the tawWorkbenchContactGroup's id
     */
    public void removeTawWorkbenchContactGroup(final String id);
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     */
    public Map getTawWorkbenchContactGroups(final Integer curPage, final Integer pageSize);
    public Map getTawWorkbenchContactGroups(final Integer curPage, final Integer pageSize, final String whereStr);
    public List getSonsById(String parentid);
    
    public List getNextLevecGroups(String nodis,String user_id, String deleted);
    public int getMaxGroupId();
    public  TawWorkbenchContactGroup getTawWorkbenchContactGroupById(final String id);
    
}

