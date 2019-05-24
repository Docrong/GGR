package com.boco.eoms.sheet.base.transaction;

import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class SheetHibernateTransactionManager extends
  JtaTransactionManager {

	protected void doCommit(DefaultTransactionStatus arg0) {
		System.out.println("SheetHibernateTransactionManager Commit");
		//super.doCommit(arg0);
	}

	protected void doRollback(DefaultTransactionStatus arg0) {
		// TODO Auto-generated method stub
		System.out.println("SheetHibernateTransactionManager Rollback");
		//super.doRollback(arg0);
	}

	
	
}
