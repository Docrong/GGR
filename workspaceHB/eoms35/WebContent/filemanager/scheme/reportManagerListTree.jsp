<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    String url="";
    url=request.getParameter("url");
 %>
<meta name="MS.LOCALE" content="ZH-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<html>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/styles/red/theme.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/report/deeptree.css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/xmltree/xtree.js"></script>
<body  style="border:0" bgcolor="#f1f1f1" topmargin="0" leftmargin="0">
<table ><tr><td id="tree" style="FONT-SIZE: 9pt; CURSOR: default; FONT-FAMILY: verdana"></td></tr></table>
</body>
</html>
<script language="JavaScript">
    var tagUrl="<%=url%>";
    var roomId=-1;
    var currEle='';
	var urlXsl="<%=request.getContextPath()%>/xmltree/transrsxml.xsl";
	var xmlManage="<%=request.getContextPath()%>/filemanager/scheme/reportManagerTreeServer.jsp?r="+Math.random();

	var xslDoc;
	xslDoc = new ActiveXObject('Microsoft.XMLDOM');
	xslDoc.async = false;
	xslDoc.load(urlXsl);

 function reXml(temp){
  	if(temp!=null){
  		//document.write(Transfrom("",urlXsl,"none"));
         var strHTML = temp.firstChild.transformNode(xslDoc);
        document.all.tree.innerHTML=strHTML;
      }
  }

  function NodeInfo(node)
  {
    var topicId;
    var topicName;
    if(node.parentElement.valueType=='scheme'){
        var schemeId=node.parentElement.dept_id;
        topicId=node.parentElement.parent_id;
       parent.doc.location="<%=request.getContextPath()%>/filemanager/scheme/SchemeMgrAction.do?act=view&topicId="+topicId+"&schemeId="+schemeId;
      }else if(node.parentElement.appType=='region' && node.parentElement.region_id!='0'){
         topicId=node.parentElement.region_id;
         topicName= node.parentElement.name;
         parent.doc.location="<%=request.getContextPath()%>/filemanager/scheme/SchemeMgrAction.do?act=add&topicId="+topicId+"&topicName="+topicName;
      }
	ExpendNode(node.parentElement.id);
  }
</script>
<script type="text/javascript">
ReceiveUrl(xmlManage);
</script>