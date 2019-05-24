//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawInformationAction.java
//
// Copyright 2003 Company
// Generated at Wed Apr 02 17:08:06 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------


package com.boco.eoms.infmanage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

public class TawFileUploadAction extends Action {
  private org.apache.commons.logging.Log __log = LogFactory.getFactory().getInstance(this.getClass());

  private static int PAGE_LENGTH = 20;
  static {
    ResourceBundle prop = ResourceBundle.getBundle("resources.application_zh_CN");
    try {
      PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
    } catch (Exception e) {
    }
  }

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionForward myforward = null;
    String myaction = mapping.getParameter();

    if (isCancelled(request)) {
      if (__log.isInfoEnabled()) {
        __log.info(" [TawFileUpload] " + mapping.getAttribute() + " - action was cancelled");
      }
      return mapping.findForward("cancel");
    }
    if (__log.isInfoEnabled()) {
      __log.info(" [TawFileUpload] action: "+myaction);
    }
    if ("".equalsIgnoreCase(myaction)) {
      myforward = mapping.findForward("failure");
    }else if ("ADD".equalsIgnoreCase(myaction)) {
      myforward = performAdd(mapping, form, request, response);
    } else if ("DEL".equalsIgnoreCase(myaction)) {
      myforward = performDel(mapping, form, request, response);
    } else {
      myforward = mapping.findForward("failure");
    }
    return myforward;
  }

  private ActionForward performAdd(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    TawFileUploadForm form = (TawFileUploadForm) actionForm;

      try
      {
  	    //	edit by wangheqi 2.7 to 3.5
 	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
 	      request.getSession().getAttribute("sessionform");
 	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
 	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
 	 	 //edit end

        if (saveSessionBeanForm==null)
        {
          return mapping.findForward("timeout");
        }

        Date puredate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = dateFormat.format(puredate);
        //edit by wangheqi 2.7 to 3.5
        String realPath= servlet.getServletConfig()
		.getServletContext().getRealPath("/");
        //String realPath= saveSessionBeanForm.getRealPath();
        String separator=File.separator;
        String attend=String.valueOf(puredate.getTime());
        attend=attend.substring(attend.length()-2,attend.length());

        FormFile file= form.getAttachName();
        String fileName=StaticMethod.dbNull2String(String.valueOf(request.getParameter("fileName")));
        String upType = StaticMethod.dbNull2String(String.valueOf(request.getParameter("upType")));

        String attValue = StaticMethod.dbNull2String(String.valueOf(request.getParameter("fileValue")));
        String attText = StaticMethod.dbNull2String(String.valueOf(request.getParameter("fileText")));

        String imgValue = StaticMethod.dbNull2String(String.valueOf(request.getParameter("imgValue")));
        String imgText = StaticMethod.dbNull2String(String.valueOf(request.getParameter("imgText")));



        int dotLocation=fileName.lastIndexOf('.');
        String fileName1="";
        if (dotLocation!=-1)
        {
          //fileName1 = fileName.substring(0, dotLocation) + "_" + date + fileName.substring(dotLocation);
          fileName1 = date +attend+ fileName.substring(dotLocation);
        }
        else
        {
          //fileName1 = fileName + "_" + date;
          fileName1 = date+attend;
        }

        InputStream stream = file.getInputStream();
        File uploadDir = new File(realPath + "infmanage" + separator + "upload");
        if (!uploadDir.exists())
        {
          uploadDir.mkdir();
        }
        OutputStream bos = new FileOutputStream(realPath + "infmanage" + separator + "upload" + separator + fileName1);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        bos.close();
        stream.close();

        if (upType.equals("1"))
        {
          if ("".equalsIgnoreCase(attText))
          {
            attText = attText + fileName;
            attValue = attValue + fileName1;
          }
          else
          {
            attText = attText + "," + fileName;
            attValue = attValue + "," + fileName1;
          }
        }
        else if (upType.equals("2"))
        {
          if ("".equalsIgnoreCase(imgText))
          {
            imgText = imgText + fileName;
            imgValue = imgValue + fileName1;
          }
          else
          {
            imgText = imgText + "," + fileName;
            imgValue = imgValue + "," + fileName1;
          }
        }

        request.setAttribute("attValue",attValue);
        request.setAttribute("attText",attText);
        request.setAttribute("imgValue",imgValue);
        request.setAttribute("imgText",imgText);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    return mapping.findForward("success");
  }

  private ActionForward performDel(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

    try
      {
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

        if (saveSessionBeanForm==null)
        {
          return mapping.findForward("timeout");
        }

        String realPath= saveSessionBeanForm.getRealPath();
        String separator=File.separator;

        String selectedFile=StaticMethod.dbNull2String(String.valueOf(request.getParameter("attachlist")));
        String url=realPath + "infmanage" + separator + "upload" + separator + selectedFile;

        File fileObj=new File(url);
        if (fileObj.exists())
           fileObj.delete();
        String attValue = StaticMethod.dbNull2String(String.valueOf(request.getParameter("fileValue")));
        String attText = StaticMethod.dbNull2String(String.valueOf(request.getParameter("fileText")));
        String imgValue = StaticMethod.dbNull2String(String.valueOf(request.getParameter("imgValue")));
        String imgText = StaticMethod.dbNull2String(String.valueOf(request.getParameter("imgText")));

        System.out.println("attValue = " + attValue);
        System.out.println("attText = " + attText);

        String[] a = attValue.split(",");
        String[] b = attText.split(",");
        String astr = "";
        String bstr ="";
        System.out.println("a = " + a.length);
        System.out.println("b = " + b.length);

        for (int i =0;i<a.length;i++)
        {
          System.out.println("a = " + a[i]);
          if (a[i].equals(selectedFile))
          {
            a[i] = "";
            b[i] = "";
          }
        }

        for (int i =0;i<a.length;i++)
        {
            astr = astr + "," + a[i];
            bstr = bstr + "," + b[i];
        }


        int dotLocation = selectedFile.lastIndexOf('.');
        String baseName = "", extName = "";
        String selectedFile1="";
        if (dotLocation != -1) {
          baseName = selectedFile.substring(0, dotLocation);
          extName = selectedFile.substring(dotLocation);
          selectedFile1 = baseName + extName;
        }
        else {
          selectedFile1 = selectedFile.substring(0, selectedFile.length());
        }
        System.out.println("selectedfile:" + selectedFile1);

        attValue = astr;
        attText = bstr;

        attValue=attValue.replaceAll(selectedFile,"");
        attValue=attValue.replaceAll(",,",",");
        attText = attText.replaceAll(selectedFile1, "");
        attText = attText.replaceAll(",,", ",");
        if (!attValue.equalsIgnoreCase(""))
        {
          if (attValue.substring(0, 0).equalsIgnoreCase(","))
          {
            attValue = attValue.substring(1, attValue.length() - 1);
            attText = attText.substring(1, attText.length() - 1);
          }
          if (attValue.substring(attValue.length() - 1).equalsIgnoreCase(","))
          {
            attValue = attValue.substring(0, attValue.length() - 1);
            attText = attText.substring(0, attText.length() - 1);
          }
        }

        imgValue=imgValue.replaceAll(selectedFile,"");
        imgValue=imgValue.replaceAll(",,",",");
        imgText = imgText.replaceAll(selectedFile1, "");
        imgText = imgText.replaceAll(",,", ",");
        if (!imgValue.equalsIgnoreCase(""))
        {
          if (imgValue.substring(0, 0).equalsIgnoreCase(","))
          {
            imgValue = imgValue.substring(1, imgValue.length() - 1);
            imgText = imgText.substring(1, imgText.length() - 1);
          }
          if (imgValue.substring(imgValue.length() - 1).equalsIgnoreCase(","))
          {
            imgValue = imgValue.substring(0, imgValue.length() - 1);
            imgText = imgText.substring(0, imgText.length() - 1);
          }
        }

        request.setAttribute("attValue", attValue);
        request.setAttribute("attText",attText);
        request.setAttribute("imgValue", imgValue);
        request.setAttribute("imgText",imgText);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

    return mapping.findForward("success");
  }

  private void generalError(HttpServletRequest request, Exception e) {
    ActionErrors aes = new ActionErrors();
    aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e.getMessage()));
    saveErrors(request, aes);
    e.printStackTrace();
    if (__log.isErrorEnabled()) {
      __log.error(" [TawFileUpload] Error - " + e.getMessage());
    }
  }
}
