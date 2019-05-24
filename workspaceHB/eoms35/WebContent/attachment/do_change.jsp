<%@ page contentType="text/html; charset=gb2312" language="java"
import="java.util.*,com.pud.study.file.*,com.pud.study.file.appinfo.*,
java.io.File,com.boco.eoms.attachment.dao.TawAttachmentDAO,
com.boco.eoms.attachment.model.TawAttachment,com.boco.eoms.common.util.StaticMethod,
com.boco.eoms.db.util.ConnectionPool,com.boco.eoms.gzjh.util.gzUtil" errorPage="" %>

<html>
<head>
<title>�ļ��ϴ�����ҳ��</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/upload.css" type="text/css">
</head>
<body>
<%
String fileIdList = request.getParameter("filelist");
String idFile = request.getParameter("idfile");
System.out.println("fileIdList="+fileIdList+" ------------------------");
ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
TawAttachmentDAO tawAttachmentDAO = new TawAttachmentDAO(ds);
AppInformations appInformations = AppInformations.getInstance();
AppInformation aAppInformation = null;

// ����Request�����ȡ����ֵ֮
if(request.getParameter("app") != null)
{
	aAppInformation = appInformations.get(request.getParameter("app"));

	String type = request.getParameter("type");
	System.out.println("type="+type+" ------------------------");
        if (type != null && type.equals("replace"))
        {
          	String DBSrcName;
        	String SrcName = StaticMethod.dbNull2String(request.getParameter("srcname"));
		String fileId  = request.getParameter("fileid");
		List Lst_fileId = tawAttachmentDAO.list(fileId);
		if(Lst_fileId != null)
                {
			TawAttachment tawAttachment_S = (TawAttachment)Lst_fileId.get(0);
			DBSrcName = StaticMethod.dbNull2String(tawAttachment_S.getAttachmentFilename());
               		DBSrcName = DBSrcName.replaceAll("\r\n", "") ;
			DBSrcName = DBSrcName.replaceAll("\n", "") ;
                       	DBSrcName = DBSrcName.trim();
	        System.out.println("SrcName="+SrcName+" ------------------------");
		System.out.println("DBSrcName="+DBSrcName+" ------------------------");
                        if (!DBSrcName.equals(SrcName))
                        {
				out.println("<script language=\"javascript\">alert('�ļ��Ѿ��������˸���,����������!');location='do_change.jsp?app="+request.getParameter("app")+"&filelist="+fileIdList+"&idfile="+idFile+"';</script>");
                                return;
                        }
                }
        	if (SrcName == null || SrcName.equals(""))
                {
			out.println("<font size=12px>�����µ�¼ҳ��!</font>");
                        return;
                }
		// �½�һ��SmartUpload����
		SmartUpload su = new SmartUpload();

		// �ϴ���ʼ��
		su.initialize(pageContext);
		su.setMaxFileSize(aAppInformation.getSize());
System.out.println("aAppInformationSize="+aAppInformation.getSize());
		su.setAllowedFilesList(aAppInformation.getAllowedFile());
System.out.println("aAppInformationAllowedFile="+aAppInformation.getAllowedFile());
		su.setDeniedFilesList(aAppInformation.getDeniedFile());
System.out.println("aAppInformationDeniedFile="+aAppInformation.getDeniedFile());
System.out.println("aAppInformationPath="+aAppInformation.getPath());

		// �ϴ��ļ�
		try
		{
		  su.upload();
		}
		catch(Exception e)
		{
			out.println(e.getMessage()+"<br>");
		}

		// ���ϴ��ļ�ȫ�����浽ָ��Ŀ¼
		int count = su.save(aAppInformation.getPath(),1);
                System.out.println("Count="+String.valueOf(count)+"---------------------");

		// ��һ��ȡ�ϴ��ļ���Ϣ��ͬʱ�ɱ����ļ���
		for (int i=0;i<su.getFiles().getCount() ;i++)
		{
			com.pud.study.file.File file = su.getFiles().getFile(i);
                        System.out.println("filename="+file.getFieldName());

			if (file.isMissing()) continue;

			String tempfilename = file.getFileName().substring(0,file.getFileName().indexOf("."))+ gzUtil.getCurrentDateTime("yyyyMMddHHmmss") + "."+file.getFileExt();
			System.out.println("tempfilename="+tempfilename);

			try {
				file.saveAs(aAppInformation.getPath()+ File.separator +  tempfilename,su.SAVE_VIRTUAL);
			}catch(Exception e) {
				out.println("<font size=12px>�ϴ��ļ�ʧ��!</font>");
        	               	return;
			}
			file.romove(aAppInformation.getPath()+ File.separator + SrcName,1);

        	       //�����ļ�����
			tawAttachmentDAO.update(Integer.parseInt(fileIdList),file.getFileName(),tempfilename);
                        response.sendRedirect("do_change.jsp?app="+request.getParameter("app")+"&filelist="+fileIdList+"&idfile="+idFile);
		}
        }

	if (fileIdList != null)
	{
		List list = tawAttachmentDAO.list(fileIdList);
		if(list != null)
		{
			for (int i=0;i<list.size();i++)
			{
				TawAttachment tawAttachment = (TawAttachment)list.get(i);
       		          	String SrcFileName = StaticMethod.dbNull2String(tawAttachment.getAttachmentFilename());
               		        SrcFileName = SrcFileName.replaceAll("\r\n", "") ;
                       		SrcFileName = SrcFileName.replaceAll("\n", "") ;
	                       	SrcFileName = SrcFileName.trim();
	                       	System.out.println("SrcFileName="+SrcFileName+"----------------------");
				out.println("<form method=\"POST\" action='do_change.jsp?app="+request.getParameter("app")+"&filelist="+fileIdList+"&idfile="+idFile+"&fileid="+String.valueOf(tawAttachment.getAttachmentId())+"&type=replace&srcname="+SrcFileName+"' ENCTYPE=\"multipart/form-data\">");
				out.print("<a href='do_download.jsp?app="+aAppInformation.getAppCode()+"&fileid="+String.valueOf(tawAttachment.getAttachmentId())+"'>");
				out.println(StaticMethod.dbNull2String(tawAttachment.getAttachmentName())+"</a>");
				out.println("<input type=\"FILE\" name=\"FILE1\" size=\"30\">");
				out.println("<input type=\"submit\" value=\"�滻\" class=\"clsBtn\">");
				out.println("</form>");
			}
        	}
	}
}
%>
</body>
</html>
