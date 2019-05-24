<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="org.w3c.dom.Document,
                 com.boco.eoms.common.resource.XmlUtil,
                 org.w3c.dom.Element,
                 com.boco.eoms.common.resource.Util"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="com.boco.eoms.filemanager.ReportTree"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<%
  String input_type="checkbox";
  String show_sign="accept";
  String reload = "false";
  String region="";
  String dept="";
  String chgType = "true";

  try{
    Document temDoc = XmlUtil.getDocument((InputStream)request.getInputStream());
    if(temDoc!=null){
        Element root = temDoc.getDocumentElement();
        input_type= root.getAttribute("input_type");
        show_sign = root.getAttribute("sign");
        reload = root.getAttribute("reload");
        region = root.getAttribute("region");
        dept   = root.getAttribute("dept");
        chgType = root.getAttribute("chgType");
   }

  }catch(Exception ex){
    ex.printStackTrace();
  }finally{
    String result="";
    if(Util.isNull(input_type)) input_type="checkbox";
    if(Util.isNull(show_sign)) show_sign="accept";
    if(Util.isNull(reload)) reload="false";
    if(Util.isNull(region)) region="false";
    if(Util.isNull(dept)) dept="true";
    if(Util.isNull(chgType)) chgType="true";

    
     Document retDoc = null;
       ReportTree templetTree=new ReportTree(request.getRealPath(".")+"/filemanager/topic.xml");
       retDoc=XmlUtil.getNewDoc(templetTree.getReportcXml("open","block"));
    if(retDoc!=null){
        Element root = retDoc.getDocumentElement();
        root.setAttribute("region",region);
        root.setAttribute("dept",dept);
        root.setAttribute("muti",input_type);
        String strDoc=XmlUtil.toString(retDoc.getFirstChild());
        out.print(strDoc);
    }else{
        out.print("<infoTree><node nodeType='root' ID='root' name='无法提取数据'/></infoTree>");
    }
  }
%>