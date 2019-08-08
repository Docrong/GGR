<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    String startDate = com.boco.eoms.base.util.StaticMethod.getLocalString(-1);
    String endDate = com.boco.eoms.base.util.StaticMethod.getCurrentDateTime();
    ;
%>
<link rel="stylesheet" href="${app}/supervisetask/css/index.css">
<script src="${app}/supervisetask/js/jquery-3.2.1.min.js"></script>
<!-- -->
<script type="text/javascript">
    // data中 className 为必传字段 其中e-pass代表已经处理 e-already正在处理 e-undo 待处理
    <%--
    var data = [{
      position : "责任人",  //流转级别
      person : "张光",  //人员
      time : "5月24日 12:00", //时间
      phone : "13140168392", //电话
      statue : "pass",  //状态
      order : "1",  //顺序
      className : "e-pass" //这个必须传，代表已经处理过的单子
    },{
      position : "一级督办",  //流转级别
      person : "张光",  //人员
      time : "5月24日 12:00", //时间
      phone : "13140168392", //电话
      statue : "already",  //状态
      order : "2",  //顺序
      className : "e-already" //这个必须传，代表正在处理的单子
    },{
      position : "二级督办",  //流转级别
      person : "张光",  //人员
      time : "5月24日 12:00", //时间
      phone : "13140168392", //电话
      statue : "pass",  //状态
      order : "3",  //顺序
      className : "e-undo" //这个必须传，代表待处理的单子
    },{
      position : "三级督办",  //流转级别
      person : "张光",  //人员
      time : "5月24日 12:00", //时间
      phone : "13140168392", //电话
      statue : "pass",  //状态
      order : "4",  //顺序
      className : "e-undo" //这个必须传，代表待处理的单子
    },{
      position : "四级督办",  //流转级别
      person : "张光",  //人员
      time : "5月24日 12:00", //时间
      phone : "13140168392", //电话
      statue : "pass",  //状态
      order : "5",  //顺序
      className : "e-undo" //这个必须传，代表待处理的单子
    }];
    --%>


    $(function () {
        //未对数据排序  如果需要排序  请先排好顺序
        var data2 = document.getElementById("data2");
        var data = eval('(' + data2.value + ')');

        var dataList1 = "", dataList0 = "", dataLength = 0, j = 0;
        if (data) {
            dataLength = data.length;
        } else {
            alert("无数据");
            return false;
        }

        for (var i = 0; i < dataLength; i++) {
            j++;
            //求余后是0  也就是上部分  两块的拼接
            if (i % 2) {
                dataList1 += '<div class="e-timeline-block ' + data[i].className + '">' +
                    '  <div class="e-line ' + data[i].className + '-bg ' + data[i].className + j + '"></div>' +
                    '  <div class="e-timeline-content p-finish-before">' +
                    '    <p class="e-personal">' + data[i].position + '：' + data[i].person + '</p>' +
                    '    <div class="p-content">' +
                    '       <p class="e-detail">联系方式：' + data[i].phone + '</p>' +
                    '       <p class="e-detail">任务到达时间：' + data[i].time + '</p>' +
                    '    </div>' +
                    '  </div>' +
                    '</div>'
            } else {  //求余后是1  也就是下部分  三块的拼接
                dataList0 += '<div class="e-timeline-block ' + data[i].className + '">' +
                    '  <div class="e-line ' + data[i].className + '-bg ' + data[i].className + j + '"></div>' +
                    '  <div class="e-timeline-content p-finish-before">' +
                    '    <p class="e-personal">' + data[i].position + '：' + data[i].person + '</p>' +
                    '    <div class="p-content">' +
                    '       <p class="e-detail">联系方式：' + data[i].phone + '</p>' +
                    '       <p class="e-detail">任务到达时间：' + data[i].time + '</p>' +
                    '    </div>' +
                    '  </div>' +
                    '</div>'
            }
        }
        $(".e-top").html(dataList1);
        $(".e-bottom").html(dataList0);
        //移除第五条线
        $(".e-bottom").find(".e-timeline-block").eq(2).find(".e-line").addClass("e-line-last");
    })

    function gotoWorkflowDetail(workflowType, sheetid) {
        if (workflowType == 'commonfault') {
            url = '../commontask/commontask.do?method=showDetailPage&sheetKey=8a8a8a81682b55a901682b8e6eb5002d';
        } else if (workflowType == 'listedregulation') {
            url = '../commontask/commontask.do?method=showDetailPage&sheetKey=8a8a8a81682b55a901682b8e6eb5002d';
        }

        window.open(url);
    }
</script>
<div class="e-procedure clearfix">
    <div class="e-header clearfix">
        <div class="e-about e-float">
            <span class="e-tooltip-pass">已督办</span><span class="e-tooltip-already">即将督办</span><span
                class="e-tooltip-undo">后面未督办</span>
            <input type="hidden" id="data2" name="data2" value='${resultList }'>
        </div>
        <a href="####" class="e-href e-float-r" onclick="gotoWorkflowDetail('${workflowType}','${sheetid}')">查看工单详情></a>
    </div>
    <!-- 上侧两个内容  -->
    <div class="e-top clearfix">
        <!-- <div class="e-timeline-block e-already">
					  <div class="e-line e-already-bg e-already-2"></div>
            <div class="e-timeline-content p-finish-before">
                <p class="e-personal">责任人:张光</p>
                <div class="p-content">
									<p class="e-detail">联系方式:13140168392</p>
									<p class="e-detail">任务到达时间: 3月21日 12:28</p>
                </div>
            </div>
        </div>

        <div class="e-timeline-block e-undo">
					  <div class="e-line e-undo-bg e-undo-4"></div>
            <div class="e-timeline-content p-finish-before">
							<p class="e-personal">责任人:张光</p>
							<div class="p-content">
								<p class="e-detail">联系方式:13140168392</p>
								<p class="e-detail">任务到达时间: 3月21日 12:28</p>
							</div>
						</div>
        </div> -->
    </div>

    <!-- 下侧三个内容  -->
    <div class="e-bottom">
        <!-- <div class="e-timeline-block e-pass">
				  	<div class="e-line e-pass-bg e-pass-1"></div>
            <div class="e-timeline-content p-finish-before">
							<p class="e-personal">责任人:张光</p>
							<div class="p-content">
								<p class="e-detail">联系方式:13140168392</p>
								<p class="e-detail">任务到达时间: 3月21日 12:28</p>
							</div>
						</div>
        </div>

        <div class="e-timeline-block e-undo">
				  	<div class="e-line e-undo-bg e-undo-3"></div>
            <div class="e-timeline-content p-finish-before">
							<p class="e-personal">责任人:张光</p>
							<div class="p-content">
								<p class="e-detail">联系方式:13140168392</p>
								<p class="e-detail">任务到达时间: 3月21日 12:28</p>
							</div>
						</div>
        </div>

        <div class="e-timeline-block e-undo">
					  <div class="e-line e-line-last e-undo-bg e-undo-5"></div>
            <div class="e-timeline-content p-finish-before">
							<p class="e-personal">责任人:张光</p>
							<div class="p-content">
								<p class="e-detail">联系方式:13140168392</p>
								<p class="e-detail">任务到达时间: 3月21日 12:28</p>
							</div>
						</div>
        </div> -->
    </div>
</div>


<%@ include file="/common/footer_eoms.jsp" %>
