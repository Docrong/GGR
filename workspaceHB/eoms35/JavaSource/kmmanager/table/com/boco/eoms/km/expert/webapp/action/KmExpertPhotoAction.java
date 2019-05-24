package com.boco.eoms.km.expert.webapp.action;

import java.awt.Rectangle;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.expert.mgr.KmExpertBasicMgr;
import com.boco.eoms.km.expert.mgr.KmExpertPhotoMgr;
import com.boco.eoms.km.expert.model.KmExpertPhoto;
import com.boco.eoms.km.expert.util.KmExpertPhotoConstants;
import com.boco.eoms.km.expert.webapp.form.KmExpertCetForm;
import com.boco.eoms.km.expert.webapp.form.KmExpertPhotoForm;
import com.boco.eoms.km.file.util.FileUploadProcessor;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:基本信息
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmExpertPhotoAction extends BaseAction {
 
	/**
	 * 未指定方法时默认调用的方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return uploadPhoto(mapping, form, request, response);
	}
	
	public ActionForward detailPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmExpertPhotoForm kmExpertPhotoForm = (KmExpertPhotoForm) form;
		
		KmExpertPhotoMgr kmExpertPhotoMgr = (KmExpertPhotoMgr) getBean("kmExpertPhotoMgr");
		KmExpertPhoto kmExpertPhoto = kmExpertPhotoMgr.getKmExpertPhotoByUserId(kmExpertPhotoForm.getUserId());
		
		request.setAttribute("photoName", kmExpertPhoto.getPhoto());
		request.setAttribute("step", "1");
		return mapping.findForward("detailPhoto");
	}

	public ActionForward uploadPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmExpertPhotoForm kmExpertPhotoForm = (KmExpertPhotoForm) form;
		
		KmExpertPhotoMgr kmExpertPhotoMgr = (KmExpertPhotoMgr) getBean("kmExpertPhotoMgr");
		KmExpertPhoto kmExpertPhoto = kmExpertPhotoMgr.getKmExpertPhotoByUserId(kmExpertPhotoForm.getUserId());
		
		request.setAttribute("photoName", kmExpertPhoto.getPhoto());
		request.setAttribute("step", "1");
		return mapping.findForward("uploadPhoto");
	}

	public ActionForward uploadPhotoDo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmExpertPhotoForm kmExpertPhotoForm = (KmExpertPhotoForm) form;
		FormFile formFile = kmExpertPhotoForm.getFile();
		
		if(formFile == null){			
			return uploadPhoto(mapping, form, request, response);
		}

		String upFileName = formFile.getFileName();
		String uploadTime = StaticMethod.getCurrentDateTime("yyMMddHHmmssS");
		String expand = upFileName.substring(upFileName.lastIndexOf('.') + 1);// 文件扩展名
		String photoName = uploadTime + "." + expand; // 映射文件名

		String filePath =  "/images/head/photo/" + photoName;

		String newPath = KmExpertPhotoConstants.getPhysicalPath(filePath, 1, this.servlet.getServletContext());

		FileUploadProcessor.processUploadedFile(formFile, newPath);
		
		request.setAttribute("photoName", photoName);
		request.setAttribute("filePath", filePath);
		request.setAttribute("step", "2");

		return mapping.findForward("uploadPhoto");
	}
	
	public ActionForward zoomPhotoDo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	    int imageWidth = Integer.parseInt(request.getParameter("txt_width"));
	    int imageHeight = Integer.parseInt(request.getParameter("txt_height"));
	    int cutTop = Integer.parseInt(request.getParameter("txt_top"));
	    int cutLeft = Integer.parseInt(request.getParameter("txt_left"));
	    int dropWidth = Integer.parseInt(request.getParameter("txt_DropWidth"));
	    int dropHeight = Integer.parseInt(request.getParameter("txt_DropHeight"));
	    float imageZoom = Float.parseFloat(request.getParameter("txt_Zoom"));
	    String picture = request.getParameter("picture");

//	    System.out.println("imageWidth : " + imageWidth);
//	    System.out.println("imageHeight : " + imageHeight);
//	    System.out.println("cutTop : " + cutTop);
//	    System.out.println("cutLeft : " + cutLeft);
//	    System.out.println("dropWidth : " + dropWidth);
//	    System.out.println("dropHeight : " + dropHeight);
//	    System.out.println("imageZoom : " + imageZoom);
//	    System.out.println("picture : " + picture);
	    
		String fromPath =  "/images/head/photo/" + picture;
		String toPath =  "/images/head/photo/zoom/" + picture;

		String newFromPath = KmExpertPhotoConstants.getPhysicalPath(fromPath, 1, this.servlet.getServletContext());
		String newToPath = KmExpertPhotoConstants.getPhysicalPath(toPath, 1, this.servlet.getServletContext());

	    File inputFile = new File(newFromPath);
	    File outputFile = new File(newToPath);

	    Rectangle rect = new Rectangle(cutLeft, cutTop, dropWidth, dropHeight);
	    
	    KmExpertPhotoConstants.cut(inputFile, outputFile, imageWidth, imageHeight, rect);
	    
	    KmExpertPhotoForm kmExpertPhotoForm = (KmExpertPhotoForm) form;

	    KmExpertPhoto kmExpertPhoto = new KmExpertPhoto();
	    kmExpertPhoto.setPhoto(picture); //头像名称
	    kmExpertPhoto.setUserId(kmExpertPhotoForm.getUserId()); //头像代表的用户
	    kmExpertPhoto.setCreateUser(this.getUserId(request)); //上传人
	    kmExpertPhoto.setCreateTime(new java.util.Date()); //上传时间
	    
		KmExpertPhotoMgr kmExpertPhotoMgr = (KmExpertPhotoMgr) getBean("kmExpertPhotoMgr");
		kmExpertPhotoMgr.saveKmExpertPhoto(kmExpertPhoto);
		
		request.setAttribute("photoName", picture);
		request.setAttribute("filePath", picture);
		request.setAttribute("step", "3");

		return mapping.findForward("uploadPhoto");
	}

	public ActionForward getUserPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmExpertPhotoMgr kmExpertPhotoMgr = (KmExpertPhotoMgr) getBean("kmExpertPhotoMgr");
		KmExpertPhoto kmExpertPhoto = kmExpertPhotoMgr.getKmExpertPhotoByUserId(this.getUserId(request));
		if(kmExpertPhoto.getPhoto() != null && !kmExpertPhoto.getPhoto().equals("")){
			request.setAttribute("userPhoto", kmExpertPhoto.getPhoto());
		}
		else{
			request.setAttribute("userPhoto", "main.jpg");
		}
		return mapping.findForward("getUserPhoto");
	}
}