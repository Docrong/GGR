<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
	String startDate = com.boco.eoms.base.util.StaticMethod.getLocalString(-1);
	String endDate = com.boco.eoms.base.util.StaticMethod.getCurrentDateTime();;
 %>

<script type="text/javascript"><!--
     function fixRowspan() { 
        var tb = document.getElementById("taskList"); 
        var row_span_num = 1; 
        var first_row_title = ""; 
        var first_row_obj = null; 
        for ( var i = 1; i < tb.rows.length; i++) { //合并单元格
            var first_new_row_title = tb.rows[i].cells[0].innerHTML; 
            if (first_row_title != "" && first_row_title == first_new_row_title) { 
                tb.rows[i].deleteCell(0); 
                row_span_num++; 
                first_row_obj.setAttribute("rowSpan", row_span_num); 
                first_row_obj.innerHTML = first_row_title.replace("/", "<br/>"); 
            } else { 
                if (first_row_title != "") { 
                    first_row_obj.setAttribute("rowSpan", row_span_num); 
                    first_row_obj.innerHTML = first_row_title.replace("/", "<br/>"); 
                    row_span_num = 1; 
                } 
                first_row_obj = tb.rows[i].cells[0]; 
                first_row_title = first_new_row_title; 
            } 
        }
        for(var i=0;i<tb.rows[0].cells.length;i++){//标题居中
        	tb.rows[0].cells[i].style="text-align:center";
        }
   
    } 
    
    window.onload=function(){
    fixRowspan();
    	draw21();
    }
    
    function draw21(id) {
        var canvas = document.querySelector('#canvas'),
		context = canvas.getContext('2d');
 
 
	// 定义边长、颜色、边数、canvas大小
	var length = 80, fillColor = '#000', vertices = 6, size = 300;
	canvas.width = size;
	canvas.height = size;
 
	var getDegree = function(vertices, index) {
	console.log(vertices+','+index);console.log(360 / vertices * (i + 0.5) + 90);
		return 360 / vertices * (i + 0.5) + 90;
	}

	var getRadian = function(degree) {
	    return degree * Math.PI / 180;
	};
	
    context.beginPath();
    for (var i = 0; i < vertices; i++) {
    	// 计算偏转
    	var degree = getDegree(vertices, i),
    		radian = getRadian(degree);
 console.log('degree:'+degree+',radian:'+radian)
    	// 增加1/3的canvas大小位移量以免被边缘挡住
        var x = Math.cos(radian) * length + size / 3;
        var y = Math.sin(radian) * length + size / 3 ;
        console.log('x:'+x+',y:'+y);
        context.lineTo(x, y);
    }
    context.closePath();
    context.stroke();

        }
</script>




</div>


<display:table name="taskList" cellspacing="0" cellpadding="0" style="text-align:center;"
	id="taskList" pagesize="${pageSize}" class="listTable" export="false"
	size="${total}" partialList="true" requestURI="supervisetask.do">
	
	<display:column title="任务状态" style="text-align:center;" >
		<c:choose>
			<c:when test="${status==0 }">即将进入本级督办序列</c:when>
			<c:when test="${status==1 }">已进入本级督办序列</c:when>
			<c:when test="${status==2 }">已进入上级督办序列</c:when>
		</c:choose>
	</display:column>
	<display:column title="任务下发方式" style="text-align:center;">
		${taskList.workflowType}
	</display:column>
	<display:column title="生成任务历时" style="text-align:center;">
		${taskList.costtime}
	</display:column>
	<display:column title="工单号" style="text-align:center;">
		${taskList.sheetid }<a href="../supervisetask/supervisetask.do?method=supervisetaskBoardDetail3&sheetId=123">点击进入三级视图</a>
	</display:column>
	<display:column title="生成任务执行人" style="text-align:center;">
		<eoms:id2nameDB id="${taskList.senduserid }" beanId="tawSystemUserDao" />
	</display:column>
	<display:column title="联系方式" style="text-align:center;">
		${taskList.sendContact} 
	</display:column>
	
</display:table>

<canvas id="canvas" width="400" height="400"></canvas>
	

<%@ include file="/common/footer_eoms.jsp"%>