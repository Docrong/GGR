// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ITawSystemRoleWorkflowDAO.java

package com.boco.eoms.roleWorkflow.dao;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.roleWorkflow.model.TawSystemRoleWorkflow;
import java.util.List;

public interface ITawSystemRoleWorkflowDAO
	extends Dao
{

	public abstract List getTawSystemWorkflows();

	public abstract TawSystemRoleWorkflow getTawSystemWorkflow(long l);

	public abstract void saveTawSystemWorkflow(TawSystemRoleWorkflow tawsystemroleworkflow);

	public abstract void removeTawSystemWorkflow(long l)
		throws Exception;

	public abstract TawSystemRoleWorkflow getTawSystemWorkflowByName(String s)
		throws Exception;

	public abstract TawSystemRoleWorkflow getTawSystemWorkflowByBeanId(String s)
		throws Exception;
}
