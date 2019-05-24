
package com.boco.eoms.sheet.commontask.model;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.task.impl.TaskImpl;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="CommonTaskTask.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="CommonTask_task"
 */
public class CommonTaskTask extends  TaskImpl implements ITask{
	
		/**
		 * 上一级处理部门
		 */
		private String preDealDept;
		
		/**
		 * 上一级处理人员
		 */
		private String preDealUserId;

		public String getPreDealDept() {
			return preDealDept;
		}

		public void setPreDealDept(String preDealDept) {
			this.preDealDept = preDealDept;
		}

		public String getPreDealUserId() {
			return preDealUserId;
		}

		public void setPreDealUserId(String preDealUserId) {
			this.preDealUserId = preDealUserId;
		}
		
		
}
