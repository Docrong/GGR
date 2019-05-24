/**
 * 
 */
package com.boco.eoms.commons.transaction.test.controller;

// java standard library
import java.sql.Connection;

// java extend library
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// hibernate library
import org.hibernate.Session;

// apache library
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

// eoms library
import com.boco.eoms.commons.transaction.controller.TransAction;
import com.boco.eoms.commons.transaction.test.bo.TstTransBO;
import com.boco.eoms.commons.transaction.test.form.TstActionForm;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class TstTransAction extends TransAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.transaction.controller.TransAction#executeProcess(
	 *      org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.sql.Connection,
	 *      net.sf.hibernate.Session)
	 */
	protected int executeProcess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			Connection _objConn, Session _objSession) throws Exception {
		int _iReturn = -10001;

		TstActionForm _ojbTstActionForm = (TstActionForm) form;
		String _strSimType = _ojbTstActionForm.getSimType();
		TstTransBO _objTstTransBO = new TstTransBO(m_objConn, m_objSession);

		if (_strSimType.equalsIgnoreCase("onlyjdbc")) {
			_iReturn = _objTstTransBO.execOnlyJDBCDAO();
		} else if (_strSimType.equalsIgnoreCase("onlyhibernate")) {
			_iReturn = _objTstTransBO.execOnlyHibernateDAO();
		} else if (_strSimType.equalsIgnoreCase("jdbctohibernate")) {
			_iReturn = _objTstTransBO.execJDBCAndHibernate();
		} else if (_strSimType.equalsIgnoreCase("hibernatetojdbc")) {
			_iReturn = _objTstTransBO.execHibernateAndJDBC();
		} else if (_strSimType.equalsIgnoreCase("simulation_01")) {
			_iReturn = _objTstTransBO.execSimulation_01(_ojbTstActionForm
					.getName(), _ojbTstActionForm.getRemark());
		}

		return _iReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.transaction.controller.TransAction#initEnvParams()
	 */
	protected void initEnvParams() {
		// super.initEnvParams();

		this.m_bNeedSession = true;
		this.m_bNeedConnection = true;
	}

}
