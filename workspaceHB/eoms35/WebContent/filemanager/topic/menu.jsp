<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<meta name="MS.LOCALE" content="ZH-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<html>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/styles/default/theme.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/report/deeptree.css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/xmltree/xtree.js"></script>
<body onselectstart='event.returnValue=0' style="border:0" bgcolor="#EDF5FD" topmargin="0" leftmargin="0">
<table><tr><td id="tree" style="FONT-SIZE: 9pt; CURSOR: default; FONT-FAMILY: verdana">

<script language="JavaScript">
var xmlDom = new ActiveXObject("MSXML2.DOMDocument");
xmlDom.async = false;
var level=0;

init();
  function init()
  {
  	var urlXml = '<%=request.getAttribute("htmlXml")%>';
    var urlXsl="<%=request.getContextPath()%>/xmltree/transrsxml.xsl?r="+Math.random();
    xmlDom.load(urlXml);

	var xmlDoc;
	var xslDoc;
	xmlDoc = new ActiveXObject('Microsoft.XMLDOM');
	xmlDoc.async = false;
	xslDoc = new ActiveXObject('Microsoft.XMLDOM');
	xslDoc.async = false;
	xmlDoc.loadXML(urlXml);
	xslDoc.load(urlXsl);
	var strHTML = xmlDoc.firstChild.transformNode(xslDoc);
	//alert(strHTML);
	document.write(strHTML);
    //document.write(Transfrom(urlXml,urlXsl,"block"));
    //alert(Transfrom(urlXml,urlXsl,"block"));
  }

  //点击节点
  function NodeInfo(eSrc)
     {
         nodeId=eSrc.parentElement.nodeId;
         //alert(nodeId);
         //topicFolder=eSrc.parentElement.topicFolder;
         //alert(topicFolder);
         xtreeId=eSrc.parentElement.id;
         node=xmlDom.selectSingleNode("//*[@ID='"+nodeId+"']");
         if (parent.doc)
         {
             if (parent.doc.document.readyState == "complete")
             {
                 if(parent.doc.document.InfoTypeDetailForm)
                 {
					 parent.doc.document.InfoTypeDetailForm.hdId.value=nodeId;
					 parent.doc.document.InfoTypeDetailForm.actId.value="0";
					 parent.doc.document.InfoTypeDetailForm.submit();
	                 return true;
	             }
	             else
	             {
	             	alert("请重新刷新页面");
	             }
             }
             else
             {
                 alert("专题信息页面尚未装载完");
                 return false;
             }
         }
         else
         {
             alert("专题信息页面尚未装载完");
             return false;
         }
     }
</script>

</td></tr></table>
</body>
</html>

