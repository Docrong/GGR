// data中 className 为必传字段 其中e-pass代表已经处理 e-already正在处理 e-undo 待处理

var data = [{
  position : "一级督办",  //流转级别
  person : "张光",  //人员
  time : "5月24日 12:00", //时间
  phone : "13140168392", //电话
  statue : "pass",  //状态
  order : "1",  //顺序
  className : "e-pass" //这个必须传，代表已经处理过的单子
},{
  position : "责任人",  //流转级别
  person : "张光",  //人员
  time : "5月24日 12:00", //时间
  phone : "13140168392", //电话
  statue : "already",  //状态
  order : "2",  //顺序
  className : "e-already" //这个必须传，代表正在处理的单子
},{
  position : "一级督办",  //流转级别
  person : "张光",  //人员
  time : "5月24日 12:00", //时间
  phone : "13140168392", //电话
  statue : "pass",  //状态
  order : "3",  //顺序
  className : "e-undo" //这个必须传，代表待处理的单子
},{
  position : "一级督办",  //流转级别
  person : "张光",  //人员
  time : "5月24日 12:00", //时间
  phone : "13140168392", //电话
  statue : "pass",  //状态
  order : "4",  //顺序
  className : "e-undo" //这个必须传，代表待处理的单子
},{
  position : "一级督办",  //流转级别
  person : "张光",  //人员
  time : "5月24日 12:00", //时间
  phone : "13140168392", //电话
  statue : "pass",  //状态
  order : "5",  //顺序
  className : "e-undo" //这个必须传，代表待处理的单子
}]

$(function(){
  //未对数据排序  如果需要排序  请先排好顺序
  var dataList1 = "", dataList0 = "", dataLength = 0, j = 0;
  if (data) {
    dataLength = data.length;
  }else {
    alert("无数据");
    return false;
  }

  for (var i= 0; i<dataLength; i++) {
    j++;
    //求余后是0  也就是上部分  两块的拼接
    if (i % 2) {
       dataList1 += '<div class="e-timeline-block '+ data[i].className +'">'+
                    '  <div class="e-line '+ data[i].className +'-bg '+ data[i].className + j +'"></div>'+
                    '  <div class="e-timeline-content p-finish-before">'+
                    '    <p class="e-personal">'+ data[i].position +'：'+ data[i].person +'</p>'+
                    '    <div class="p-content">'+
                    '       <p class="e-detail">联系方式：'+ data[i].phone +'</p>'+
                    '       <p class="e-detail">任务到达时间：'+ data[i].time +'</p>'+
                    '    </div>'+
                    '  </div>'+
                    '</div>'
    }else {  //求余后是1  也就是下部分  三块的拼接
      dataList0 += '<div class="e-timeline-block '+ data[i].className +'">'+
                   '  <div class="e-line '+ data[i].className +'-bg '+ data[i].className + j +'"></div>'+
                   '  <div class="e-timeline-content p-finish-before">'+
                   '    <p class="e-personal">'+ data[i].position +'：'+ data[i].person +'</p>'+
                   '    <div class="p-content">'+
                   '       <p class="e-detail">联系方式：'+ data[i].phone +'</p>'+
                   '       <p class="e-detail">任务到达时间：'+ data[i].time +'</p>'+
                   '    </div>'+
                   '  </div>'+
                   '</div>'
    }
  }
  $(".e-top").html(dataList1);
  $(".e-bottom").html(dataList0);
  //移除第五条线
  $(".e-bottom").find(".e-timeline-block").eq(2).find(".e-line").addClass("e-line-last");
})
