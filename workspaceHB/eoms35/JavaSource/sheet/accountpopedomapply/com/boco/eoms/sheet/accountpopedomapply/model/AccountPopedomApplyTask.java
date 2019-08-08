
package com.boco.eoms.sheet.accountpopedomapply.model;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.task.impl.TaskImpl;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="AccountPopedomApplyTask.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="AccountPopedomApply_task"
 */
public class AccountPopedomApplyTask extends TaskImpl implements ITask {
}
