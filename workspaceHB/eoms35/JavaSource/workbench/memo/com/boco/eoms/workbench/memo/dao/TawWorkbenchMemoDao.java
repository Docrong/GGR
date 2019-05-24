
package com.boco.eoms.workbench.memo.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;

public interface TawWorkbenchMemoDao extends Dao {

    /**
     * Retrieves all of the tawWorkbenchMemos
     */
    public List getTawWorkbenchMemos(TawWorkbenchMemo tawWorkbenchMemo);

    /**
     * Gets tawWorkbenchMemo's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawWorkbenchMemo's id
     * @return tawWorkbenchMemo populated tawWorkbenchMemo object
     */
    public TawWorkbenchMemo getTawWorkbenchMemo(final String id);

    /**
     * Saves a tawWorkbenchMemo's information
     * @param tawWorkbenchMemo the object to be saved
     */    
    public void saveTawWorkbenchMemo(TawWorkbenchMemo tawWorkbenchMemo);

    /**
     * Removes a tawWorkbenchMemo from the database by id
     * @param id the tawWorkbenchMemo's id
     */
    public void removeTawWorkbenchMemo(final String id);
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     */
    public Map getTawWorkbenchMemos(final Integer curPage, final Integer pageSize);
    public Map getTawWorkbenchMemos(final Integer curPage, final Integer pageSize, final String whereStr);
    public String saveTawWorkbenchMemoReturnId(TawWorkbenchMemo tawWorkbenchMemo);
    /*
     * 
     */
    public boolean ifSystemUser(String user);
}

