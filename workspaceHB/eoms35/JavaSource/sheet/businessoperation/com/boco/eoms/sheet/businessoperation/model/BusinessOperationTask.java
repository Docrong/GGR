
package com.boco.eoms.sheet.businessoperation.model;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.task.impl.TaskImpl;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="BusinessOperationTask.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessOperation_task"
 */
public class BusinessOperationTask extends  TaskImpl implements ITask{
}