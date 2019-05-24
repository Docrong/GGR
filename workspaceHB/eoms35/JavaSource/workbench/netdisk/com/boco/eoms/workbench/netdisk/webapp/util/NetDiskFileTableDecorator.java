/**
 * 
 * @author wangbeiying
 */
package com.boco.eoms.workbench.netdisk.webapp.util;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFile;

/**
 * 
 * @author wangbeiying
 */
public class NetDiskFileTableDecorator extends TableDecorator {
	public String getFileOperation() {
		TawWorkbenchNetDiskFile file = (TawWorkbenchNetDiskFile)getCurrentRowObject();
		Object obj = getPageContext().getRequest().getAttribute("userIdSearch");
		String searchUserId = obj==null?"":(String)obj;
		String path = ((HttpServletRequest)(getPageContext().getRequest())).getContextPath();
		StringBuffer sb = new StringBuffer();
		//下载
		sb.append("<a href=\""+path+"/workbench/netdisk/tawWorkbenchFiles.do?method=download&mappingName="+file.getMappingName()+"&userId="+file.getUserId()+"\">下载</a>&nbsp;");
		if(searchUserId.equals(file.getUserId())){
			//删除
			sb.append("<a href=\"javascript:delFile('"+file.getMappingName()+"','"+file.getFolderMappingId()+"');\">删除</a>");
		}
		return sb.toString();
	}
	
	public String getUploadUserName() {
		TawWorkbenchNetDiskFile file = (TawWorkbenchNetDiskFile)getCurrentRowObject();
		ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("ItawSystemUserManagerFlush");
		String userName = "";
		try {
			userName = userMgr.id2Name(file.getUserId());
		} catch (DictDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userName;
	}
}
