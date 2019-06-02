/*
 * Created on 2007-11-12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ITawSystemWorkflowManager extends Manager{
	/**
     * Retrieves all of the tawSystemPosts
     */
    public List getTawSystemWorkflows();

    /**
     * Gets tawSystemPost's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param postId the tawSystemPost's postId
     * @return tawSystemPost populated tawSystemPost object
     */
    public TawSystemWorkflow getTawSystemWorkflow(final long id);

    /**
     * Saves a tawSystemPost's information
     * @param tawSystemPost the object to be saved
     */    
    public void saveTawSystemWorkflow(TawSystemWorkflow tawSystemWorkflow);

    /**
     * Removes a tawSystemPost from the database by postId
     * @param postId the tawSystemPost's postId
     */
    public void removeTawSystemWorkflow(final long id) throws Exception;
    
    /**
     * 根据流程名称查询流程信息
     * @param name 流程名称
     * @return
     * @throws Exception
     */
    public TawSystemWorkflow getTawSystemWorkflowByName(String name) throws SheetException;
    /**
     * 根据流程mainBeanId查询流程信息
     * @param mainBeanId
     * @return
     * @throws SheetException
     */
    public TawSystemWorkflow getTawSystemWorkflowByBeanId(String mainBeanId) throws SheetException;
}
