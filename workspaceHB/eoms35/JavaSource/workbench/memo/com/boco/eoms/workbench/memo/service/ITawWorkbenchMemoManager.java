
package com.boco.eoms.workbench.memo.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
import com.boco.eoms.workbench.memo.dao.TawWorkbenchMemoDao;

public interface ITawWorkbenchMemoManager extends Manager {
    /**
     * Retrieves all of the tawWorkbenchMemos
     */
    public List getTawWorkbenchMemos(TawWorkbenchMemo tawWorkbenchMemo);

    /**
     * Gets tawWorkbenchMemo's information based on id.
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
    
    
    public Map getTawWorkbenchMemos(final Integer curPage, final Integer pageSize);
    
    
    public Map getTawWorkbenchMemos(final Integer curPage, final Integer pageSize, final String whereStr);
    /** 
		查询某用户所有便笺 
		Parameters: 
		userId - 用户Id 
		Returns: 
		List 便笺列表

    */
    public List listTawWorkbenchMemo(final java.lang.String userId);
    /** 
    根据用户Id和所属象限查询便笺 
    Parameters: 
    userId - 用户Id 
    quadrant - 便笺所属象限，即重要紧急的级别（1：重要紧急，2：重要非紧急，3：紧急非重要，4：非重要非紧急） 
    Returns: 
    List 便笺列表
    */
    public List listTawWorkbenchMemo(final java.lang.String userId,
    		final int quadrant);
    /** 
    发送便笺 
    Parameters: 
    tawWorkbenchMemo - 便笺信息 
    recievers - 接收者 
    sendManner - 发送方式（短信、Email等） 
    Returns: 
    发送操作结果（true：发送成功，false：发送失败）
     */
    public boolean sendMemo(com.boco.eoms.workbench.memo.model.TawWorkbenchMemo tawWorkbenchMemo,
            java.lang.String recievers,
            java.lang.String sendManner,java.lang.String userid);
    /** 
    保存便笺发送记录 
    Parameters: 
    tawWorkbenchMemoLog - 便笺发送记录 
    Returns: 
    保存发送记录结果（true：保存成功，false：保存失败）
     */
    public boolean saveTawWorkbenchMemoLog(com.boco.eoms.workbench.memo.model.TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog);
    /** 
    查询某用户已发送便笺 
    Parameters: 
    userId - 用户Id 
    sendFlag - 发送标志（0：未发送，1：已发送） 
    Returns: 
    List 已发送便笺列表
	*/
    public List listTawWorkbenchMemoSend(java.lang.String userId,
            int sendFlag);
    public String saveTawWorkbenchMemoReturnId(TawWorkbenchMemo tawWorkbenchMemo);
    
     
    /*
     * 增加一个判断是否为系统内部人员的判断
     */
    
    public boolean ifSystemUser(String user);
}

