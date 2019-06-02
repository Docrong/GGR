<%@ page language="java" contentType="text/html;charset=gb2312"%>

<%
String app = request.getContextPath();
 %>
<html>
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=GB2312">
<script type="text/javascript" src="ajax/prototype.js"></script>
<script type="text/javascript" src="xloadtree/xtree2.js"></script>
<script type="text/javascript" src="xloadtree/xloadtree2.js"></script>
<link type="text/css" rel="stylesheet" href="xloadtree/xtree2.css">
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<style type="text/css">
.webfx-tree-children {
	background-repeat:	repeat-y;
	background-image:	url("<%=app%>/webtool/scripts/xloadtree/images/xp/I.png");
	background-position-y:	1px !important;	/* IE only */
	font:			icon;
}
</style>
<script type="text/javascript">
  function init(){
    new Ajax.Request("<%=app%>/webtool/configfiles/wap-config.xml", {onComplete:loadtree, onFailure: reportError});
  }
  function loadtree(orqst){
    //alert(orqst.responseText);
    var doc = orqst.responseXML;
    
    //alert(doc);

/*    
    //DBCons
    var DBConsName = doc.getElementsByTagName("wapDBConnections")[0].getAttribute("name");
    var DBConsNode = new WebFXTreeItem(DBConsName,"<%=app %>/webtool/wapdbconnection?act=list");
    //alert(tree.target);
    //DBConsNode.setTarget("rightFrame");
	tree.add(DBConsNode);
	
	//DBCon
	var arrDBCon = doc.getElementsByTagName("wapDBConnection");
	for(i=0;i<arrDBCon.length;i++){
	  var name = arrDBCon[i].getAttribute("dbName");
	  var dbid = arrDBCon[i].getAttribute("dbId");
	  var node = new WebFXTreeItem(name,"<%=app%>/webtool/wapdbconnection?act=view&flag=view&dbId="+dbid);
	  
	  DBConsNode.add(node);
	}
*/	
	//wap page
	var arrWapNode = doc.getElementsByTagName("wapNode");
	for(i=0;i<arrWapNode.length;i++){
	  var name = arrWapNode[i].getAttribute("name");
	  var id = arrWapNode[i].getAttribute("wapNodeID");
	  var node = new WebFXTreeItem(name,"<%=app%>/webtool/wapnode?act=view&flag=view&wapNodeId="+id);
	  tree.add(node);
	  
	  var arrCards = arrWapNode[i].getElementsByTagName("card");
	  for(j=0;j<arrCards.length;j++){
	    var cardname = arrCards[j].getAttribute("name");
	    var cardid = arrCards[j].getAttribute("cardId");
	    var cardnode = new WebFXTreeItem(cardname,"<%=app%>/webtool/card?act=view&flag=view&wapCardId="+cardid+"&wapNodeId="+id);
	    node.add(cardnode);
	  }
	}
	
  }        
  function reportError(request){
     alert('wokao,Sorry. There was an error:'+
                '\nreadyState:'+request.readyState+
                '\nstatus:'+request.status);
  }
  function reloadtree(){
    setTimeout('location.reload()',500);
    tree.expandAll();
  }
</script>
</head>
  
<body onload="init();" id="tree">
    <div>
      <script type="text/javascript">
		webFXTreeConfig.rootIcon		= "xloadtree/images/xp/folder.png";
		webFXTreeConfig.openRootIcon	= "xloadtree/images/xp/openfolder.png";
		webFXTreeConfig.folderIcon		= "xloadtree/images/xp/folder.png";
		webFXTreeConfig.openFolderIcon	= "xloadtree/images/xp/openfolder.png";
		webFXTreeConfig.fileIcon		= "xloadtree/images/xp/file.png";
		webFXTreeConfig.lMinusIcon		= "xloadtree/images/xp/Lminus.png";
		webFXTreeConfig.lPlusIcon		= "xloadtree/images/xp/Lplus.png";
		webFXTreeConfig.tMinusIcon		= "xloadtree/images/xp/Tminus.png";
		webFXTreeConfig.tPlusIcon		= "xloadtree/images/xp/Tplus.png";
		webFXTreeConfig.iIcon			= "xloadtree/images/xp/I.png";
		webFXTreeConfig.lIcon			= "xloadtree/images/xp/L.png";
		webFXTreeConfig.tIcon			= "xloadtree/images/xp/T.png";
		
        var tree = new WebFXTree("主菜单","<%=app%>/webtool/nodelist");
        tree.write();
        tree.setTarget("rightFrame");
        tree.toggle();
        tree.expand();

      </script>
    </div>
  </body>
</html>
