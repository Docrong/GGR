<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    String url="";
    url=request.getParameter("url");
    String listType="SeparateReportList";     //列出需要上传处理的报表
    if(request.getParameter("listType")!=null)
        listType=request.getParameter("listType");  //列出汇总报表  CollectReportList
 %>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<meta name="MS.LOCALE" content="ZH-CN">
<html>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/styles/default/theme.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/report/deeptree.css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/xmltree/xtree.js"></script>
<body  style="border:0" bgcolor="#f1f1f1" topmargin="0" leftmargin="0">
<table ><tr><td id="tree" style="FONT-SIZE: 9pt; CURSOR: default; FONT-FAMILY: verdana">
<script language="JavaScript">
    var tagUrl="<%=url%>";
    var roomId=-1;
    var currEle='';
	var urlXsl="<%=request.getContextPath()%>/xmltree/transrsxml.xsl";
	var xmlManage="<%=request.getContextPath()%>/filemanager/topicTreeServer.jsp?r="+Math.random();

	var xslDoc;
	xslDoc = new ActiveXObject('Microsoft.XMLDOM');
	xslDoc.async = false;
	xslDoc.load(urlXsl);

 function reXml(temp){
  	if(temp!=null){
  		//document.write(Transfrom("",urlXsl,"none"));
         var strHTML = temp.firstChild.transformNode(xslDoc);
        document.write(strHTML);
  	}
  }

  function NodeInfo(node)
  {
    var topicId;
    var topicName;
    if(node.parentElement.nodeType=='root'){
        parent.doc.location="<%=request.getContextPath()%>/filemanager/ReportMgrAction.do?act=<%=listType%>";
    }if(node.parentElement.appType=='region'){
         topicId=node.parentElement.nodeId;
         parent.doc.location="<%=request.getContextPath()%>/filemanager/ReportMgrAction.do?act=<%=listType%>&topicId="+topicId;
      }
	ExpendNode(node.parentElement.id);
  }
</script>
<script type="text/javascript">
ReceiveUrl(xmlManage);
</script>
</td></tr></table>
</body>
</html>