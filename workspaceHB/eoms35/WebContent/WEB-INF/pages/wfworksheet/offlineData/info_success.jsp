<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<script type="text/javascript">
	if('${type}'=='new'){
		var frame = window.opener.document.getElementById("frame1");
		frame.style.display="block";
	 	frame.src = "${app}/sheet/offlineData/offlineData.do?method=showList&sheetKey=${sheetKey}&actionForword=${actionForword}&stylepage=new" ; 	
		window.close();
	}else if('${type}'=='DEL'){
		var frame = parent.window.document.getElementById("frame1");
		frame.style.display="block";
 		frame.src = "${app}/sheet/offlineData/offlineData.do?method=showList&sheetKey=${sheetKey}&actionForword=${actionForword}&stylepage=new" ; 	
	}else if ('${type}'=='edit'){
		var frame = window.opener.parent.document.getElementById("frame1");
		frame.style.display="block";
 		frame.src = "${app}/sheet/offlineData/offlineData.do?method=showList&sheetKey=${sheetKey}&actionForword=${actionForword}&stylepage=new" ; 	
		window.close();
	}
</script>
<%@ include file="/common/footer_eoms.jsp"%>