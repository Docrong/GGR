<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="com.pud.study.file.*,
com.pud.study.file.appinfo.*,com.boco.eoms.db.util.ConnectionPool,
com.boco.eoms.common.util.StaticMethod,com.boco.eoms.workplan.mgr.ITawwpExecuteMgr,
com.boco.eoms.workplan.model.TawwpExecuteFile,com.boco.eoms.workplan.util.TawwpStaticVariable" %>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%
try
{
  String id = request.getParameter("id");
  SmartUpload su = new SmartUpload();
  ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr)ApplicationContextHolder.getInstance().getBean("tawwpExecuteMgr");
//  ITawwpExecuteFileDao tawwpExecuteFileDAO = new TawwpExecuteFileDAO();
  TawwpExecuteFile tawwpExecuteFile = tawwpExecuteMgr.loadExecuteFile(id);
  String path = TawwpStaticVariable.fileDir + StaticMethod.null2String(tawwpExecuteFile.getFileCodeName());
  su.initialize(pageContext);
  su.setContentDisposition(null);
  su.downloadFile(".." + request.getContextPath() + "/workplan"
                  + path.substring(2,path.length())
                  ,"",tawwpExecuteFile.getFileName());
}
catch(Exception e)
{
e.printStackTrace();
}
%>
