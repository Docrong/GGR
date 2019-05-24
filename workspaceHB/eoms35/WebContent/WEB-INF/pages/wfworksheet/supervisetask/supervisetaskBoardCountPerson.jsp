<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
	String startDate = com.boco.eoms.base.util.StaticMethod.getLocalString(-1);
	String endDate = com.boco.eoms.base.util.StaticMethod.getCurrentDateTime();;
 %>

<script type="text/javascript">
window.onload=function(){
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


<canvas id="canvas" width="400" height="400"></canvas>
<%@ include file="/common/footer_eoms.jsp"%>
