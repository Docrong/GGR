<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% 
  request.setAttribute("roleId","405");
  long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
		
<!--本模块的JS -->
<script type="text/javascript">
 //提交
 function performAddSubmit() {
 	//如果是草稿，则操作类型是草稿派发
 	if ("${draft}" != "") {
 		$('operateType').value = "3";
 	} else if ("${taskName}" == "RejectTask") {
 	//如果是驳回，则是重新派发
 		$('operateType').value = "54";
 		var form = document.getElementById("theform");
 		form.action = "${module}.do?method=performDealNew";
 	} else {
 	//其它为新建派发
 		$('operateType').value = "0";
 	}
 	
 }
 
 function selectLimit(obj){
    if($("mainNetTypeOne").value == null ||$("mainNetTypeOne").value ==""){
       // alert("请选择故障专业！");
        return false;
    }

    var temp1=$("${sheetPageName}mainNetTypeOne").value;
    var temp2=$("${sheetPageName}mainNetTypeTwo").value;
    var temp3=$("${sheetPageName}mainNetTypeThree").value;
    var temp4=$("${sheetPageName}mainFactory").value;
          
    Ext.Ajax.request({
		method:"get",
		url: "commonchangedeploy.do?method=newShowLimit&specialty1="+temp1+"&specialty2="+temp2+"&specialty3="+temp3+"&specialty4="+temp4+"&flowName=CommonChangeDeployProcess",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	    //$("${sheetPageName}sheetAcceptLimit").value = "";
        	    //$('${sheetPageName}sheetCompleteLimit').value = "";
           	}else{
           	     var times=<%=localTimes%>;
	        	var dt1 = new Date(times).add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
</script>