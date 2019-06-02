/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service.impl;

import java.util.List;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:32:06
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class LinkServiceImpl extends LinkService {
	
	public List getLinksBycondition(String condition, String linkName) {
		
		return this.getLinkDAO().getLinksBycondition(condition, linkName);
	}

}
