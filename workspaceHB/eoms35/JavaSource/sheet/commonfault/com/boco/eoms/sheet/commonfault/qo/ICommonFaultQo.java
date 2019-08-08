/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.qo;

import java.util.Map;

import com.boco.eoms.sheet.base.qo.IWorkSheetQO;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ICommonFaultQo extends IWorkSheetQO {
    public abstract String getClauseSql(Map map);

}
