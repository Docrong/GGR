
package com.boco.eoms.message.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.message.dao.IMmsOuterConfig;
import com.boco.eoms.message.model.MmsContent;
import com.boco.eoms.message.util.MmsOuterConfigImpl;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.webapp.form.MmsSendForm;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-7-7 下午16:06:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public final class MmsSendAction extends BaseAction {
    
    /**
     * 及时发送彩信
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward sendMms(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) getBean("ItawCommonsAccessoriesManager");
		
    	MmsSendForm mmsForm = (MmsSendForm)form;
    	String mobiles = mmsForm.getMobiles();
    	String subject = mmsForm.getSubject();
    	String content = mmsForm.getAccessories();
    	String txtContent = mmsForm.getTxtContent();

		TawCommonsAccessories accessories = null;
    	List accessoriesList = mgr.getAllFileById(content);
    	Iterator it = accessoriesList.iterator();
		String rootFilePath = AccessoriesMgrLocator
		.getTawCommonsAccessoriesManagerCOS().getFilePath("Mms");
		
		IMmsOuterConfig mmsOuter = new MmsOuterConfigImpl();
		List mmsContentList = new ArrayList();
		MmsContent mmContent = new MmsContent();
		mmContent.setContent(txtContent);
		mmContent.setContentType(MsgConstants.MMS_TYPE_TEXT);

    	while(it.hasNext()) {
    		MmsContent mmsContent = new MmsContent(); 
    		accessories = (TawCommonsAccessories)it.next();
    		String fileName = accessories.getAccessoriesName();
    		String fileType = "";
    		if(fileName != null && !fileName.equals("")) {
    			fileType = fileName.substring(fileName.lastIndexOf(".")+1);
    			if(fileType.equalsIgnoreCase("gif")) {
    	    		mmsContent.setContentType(MsgConstants.MMS_TYPE_GIF);
    			} else if (fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("jpeg")){
    				mmsContent.setContentType(MsgConstants.MMS_TYPE_JPEG);
    			} else if (fileType.equalsIgnoreCase("txt")){
    				mmsContent.setContentType(MsgConstants.MMS_TYPE_TEXT);
    			} 
    		}
    		String fileUrl = rootFilePath + fileName; 
    		mmsContent.setContent(fileUrl);
    		mmsContentList.add(mmsContent);
    	}
		int code = mmsOuter.sendMms(mobiles, subject, mmsContentList);
		if(code == 1000) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}    	
    }
}
