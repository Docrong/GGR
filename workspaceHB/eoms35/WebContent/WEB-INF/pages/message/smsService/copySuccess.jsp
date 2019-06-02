<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
${param.name}
<%
String serviceId = request.getParameter("id");
 %>
 <script type="text/javascript">
<!--
function copyToClipBoard() {
	var clipBoardContent='<%=serviceId%>';
	if(window.clipboardData) {
	  window.clipboardData.clearData();
	  window.clipboardData.setData("Text", clipBoardContent);
	  alert('<bean:message key="smsService.copysuccess"/>');
	} else if(navigator.userAgent.indexOf("Opera") != -1) {
		window.location = clipBoardContent;
	} else if (window.netscape) {
		try {
			netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
		} catch (e) {
			alert("<bean:message key="smsService.alert"/>");
		return false;
		}
	var clip = Components.classes["@mozilla.org/widget/clipboard;1"].createInstance(Components.interfaces.nsIClipboard);
	if (!clip)
	return;
	var trans = Components.classes["@mozilla.org/widget/transferable;1"].createInstance(Components.interfaces.nsITransferable);
	if (!trans)
	return;
	trans.addDataFlavor('text/unicode');
	var str = new Object();
	var len = new Object();
	var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
	var copytext = clipBoardContent;
	str.data = copytext;
	trans.setTransferData("text/unicode",str,copytext.length*2);
	var clipid = Components.interfaces.nsIClipboard;
	if (!clip)
	return false;
	clip.setData(trans,null,clipid.kGlobalClipboard);
	}
}

//-->
</script>
<script type="text/javascript">
	//刷新父框架中的整颗树
	//parent.AppFrameTree.refreshTree();
	//刷新父框架中当前选中的节点
	parent.AppFrameTree.reloadNode();
	copyToClipBoard();
</script>

<script type="text/javascript">
<!--

//-->
</script>


<div class="sheet-success">
	<div class="header"><h1>${eoms:a2u('操作成功！')}</h1></div>
	<div class="content">
	${eoms:a2u('服务ID：')}<%=serviceId%>${eoms:a2u('已成功复制到剪切板！此ID是该服务的唯一标识！')}<br/>
	</div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>