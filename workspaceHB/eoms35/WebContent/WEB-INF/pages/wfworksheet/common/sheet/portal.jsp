<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/sheet-view.js"></script>
<div id="loading">
	<div class="loading-indicator">&#160;Loading...</div>
</div> 
<div id="sheet-portal-page">
  <iframe id="portal-north" name="north" frameborder="no" src="" width="0" height="0"> 	
  </iframe>
  <iframe id="portal-south" name="portal-south" frameborder="no" src="${app}/sheet/${module}/${module}.do?method=showListsendundo" width="0" height="0">
  </iframe>
</div>
<%@ include file="/common/footer_eoms.jsp"%>
