<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">

function findSubscribe() {
  var subscribeId = document.forms[0].subscribeId.value;
  if(subscribeId==1){
    var url="${app}/kmmanager/kmContentsSubscribes.do?method=searchSubscribe";
    window.location.href = url;
  }else if(subscribeId==2){
    var url="${app}/kmmanager/kmContentsSubscribes.do?method=searchSubscribeTable";
    window.location.href = url;
  }
}

</script>
<div class="list-title">	
</div>
<html:form action="/kmContentsSubscribes.do?method=saveSubscribe"
	styleId="kmContentsForm" method="post" 
	> 
请选择订阅条件
<eoms:dict key="dict-kmmanager" dictId="subscribe" isQuery="false"
		  selectId="subscribeId" beanId="selectXML"
		onchange="findSubscribe();" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
