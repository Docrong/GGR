
package com.boco.eoms.message.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.message.model.MmsContent;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-3-11 下午10:36:10
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public interface IMmsContentDao extends Dao {

    /**
     * Retrieves all of the mmsContents
     */
    public List getMmsContents(MmsContent mmsContent);

    /**
     * Gets mmsContent's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the mmsContent's id
     * @return mmsContent populated mmsContent object
     */
    public MmsContent getMmsContent(final String id);

    /**
     * Saves a mmsContent's information
     * @param mmsContent the object to be saved
     */    
    public void saveMmsContent(MmsContent mmsContent);

    /**
     * Removes a mmsContent from the database by id
     * @param id the mmsContent's id
     */
    public void removeMmsContent(final String id);
    
    /**
     * 根据对象删除
     * @param mmsContent 轮询对象
     */
    public void delete(MmsContent mmsContent);
       
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     */
    public Map getMmsContents(final Integer curPage, final Integer pageSize);
    public Map getMmsContents(final Integer curPage, final Integer pageSize, final String whereStr);
    
    public List retriveMmsContents(String monitorId);
}

