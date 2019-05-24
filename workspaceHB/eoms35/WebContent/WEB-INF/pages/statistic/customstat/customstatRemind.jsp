<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="java.util.List"%>
<% 
int year= StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")); 
int startyear = year-10;
int endyear = year+10;


int month= StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")); 
int quarte=0;
if(month==1||month==2||month==3){
    quarte=1;
}else if(month==4||month==5||month==6){
    quarte=2;
}else if(month==7||month==8||month==9){
    quarte=3;
}else if(month==10||month==11||month==12){
    quarte=4;
}


int week = StaticMethod.getWeekOfYear();


List namelist = (List)request.getAttribute("sheetnamelist");

%>
<script language="javascript">
function changetype(locationid){
  if(locationid ==1){
    document.getElementById("yeartime").style.display="block";//年
    document.getElementById("quartertime").style.display="none";//季
    document.getElementById("monthtime").style.display="none";//月
    document.getElementById("datetime").style.display="none";//日
    document.getElementById("weektime").style.display="none";//周
  }else if(locationid ==2){
    document.getElementById("yeartime").style.display="block";//年
    document.getElementById("quartertime").style.display="block";//季
    document.getElementById("monthtime").style.display="none";//月
    document.getElementById("datetime").style.display="none";//日
    document.getElementById("weektime").style.display="none";//周
  }else if(locationid ==3){
    document.getElementById("yeartime").style.display="block";//年
    document.getElementById("quartertime").style.display="none";//季
    document.getElementById("monthtime").style.display="block";//月
    document.getElementById("datetime").style.display="none";//日
    document.getElementById("weektime").style.display="none";//周
  }else if(locationid ==4){
    document.getElementById("yeartime").style.display="none";//年
    document.getElementById("quartertime").style.display="none";//季
    document.getElementById("monthtime").style.display="none";//月
    document.getElementById("datetime").style.display="block";//日
    document.getElementById("weektime").style.display="none";//周  
  }else if(locationid ==5){
    document.getElementById("yeartime").style.display="block";//年
    document.getElementById("quartertime").style.display="none";//季
    document.getElementById("monthtime").style.display="none";//月
    document.getElementById("datetime").style.display="none";//日
    document.getElementById("weektime").style.display="block";//周  
  }else{
    document.getElementById("yeartime").style.display="none";//年
    document.getElementById("quartertime").style.display="none";//季
    document.getElementById("monthtime").style.display="none";//月
    document.getElementById("datetime").style.display="none";//日
    document.getElementById("weektime").style.display="none";//周    
  }
}


function getdayofweek(a, b, c) {
/*
 * date1是当前日期
 * date2是当年第一天
 * d是当前日期是今年第多少天
 * 用d + 当前年的第一天的周差距的和在除以7就是本年第几周
*/
  var date1 = new Date(Number(a), parseInt(Number(b)) - 1, Number(c));
  var date2 = new Date(Number(a), 0, 1);
  var d = Math.round((date1.valueOf() - date2.valueOf()) / 86400000);
  return Math.ceil((d + ((date2.getDay() + 1) - 1)) / 7);
}

/* 
 * 比较日期大小
 * a,b格式均为yyyy-mm-dd
*/
function compare_time(a,b) {
   var arr=a.split("-");
   var starttime=new Date(arr[0],arr[1],arr[2]);
   var starttimes=starttime.getTime(); 
   var arrs=b.split("-"); 
   var endtime=new Date(arrs[0],arrs[1],arrs[2]);
   var endtimes=endtime.getTime();
   if(starttimes>=endtimes)//开始大于结束
   {
     return true;
   } 
   else{ 
    return false; 
   }
} 

function SubmitCheck(){
  if(document.forms[0].statname.value=='0'){
    alert( '${eoms:a2u("请选择报表名称")}' );
    return false;
  }  
  //判断接收时间不可以小于报表生成时间
  if(document.forms[0].stattype.value=='0'){
    alert( '${eoms:a2u("请选择报表类型")}' );
    return false;  
  }else if(document.forms[0].stattype.value=='1'){
    var year = Number(document.forms[0].year.value);
    var acceptyear = Number(document.forms[0].acceptdate.value.substring(0,4));
    if(year>=acceptyear){
      alert( '${eoms:a2u("接收时间应晚于报表生成时间")}' );
      return false; 
    }
  }else if(document.forms[0].stattype.value=='2'){
    var year = Number(document.forms[0].year.value);
    var quarter = Number(document.forms[0].quarter.value);
    
    var acceptyear = Number(document.forms[0].acceptdate.value.substring(0,4));
    var acceptmonth = Number(document.forms[0].acceptdate.value.substring(5,7));
    var acceptquarter = 0;
    if(acceptmonth==1||acceptmonth==2||acceptmonth==3){
      acceptquarter=1;
    }else if(acceptmonth==4||acceptmonth==5||acceptmonth==6){
      acceptquarter=2;
    }else if(acceptmonth==7||acceptmonth==8||acceptmonth==9){
      acceptquarter=3;
    }else if(acceptmonth==10||acceptmonth==11||acceptmonth==12){
      acceptquarter=4;
    }
    if(year>acceptyear||(year==acceptyear&&quarter>=acceptquarter)){
      alert( '${eoms:a2u("接收时间应晚于报表生成时间")}' );
      return false; 
    }
    
  }else if(document.forms[0].stattype.value=='3'){
    var year = Number(document.forms[0].year.value);
    var month = Number(document.forms[0].month.value);
    var acceptyear = Number(document.forms[0].acceptdate.value.substring(0,4));
    var acceptmonth = Number(document.forms[0].acceptdate.value.substring(5,7));
    if(year>acceptyear||(year==acceptyear&&month>=acceptmonth)){
      alert( '${eoms:a2u("接收时间应晚于报表生成时间")}' );
      return false; 
    }
    
  }else if(document.forms[0].stattype.value=='4'){
    var date = document.forms[0].date.value;
    var acceptdate = document.forms[0].acceptdate.value.substring(0,10);
    if(compare_time(date,acceptdate)){
      alert( '${eoms:a2u("接收时间应晚于报表生成时间")}' );
      return false; 
    }    
  }else if(document.forms[0].stattype.value=='5'){
    var year = Number(document.forms[0].year.value);
    var week = Number(document.forms[0].week.value);
    
    var acceptyear = Number(document.forms[0].acceptdate.value.substring(0,4));
    var acceptmonth = Number(document.forms[0].acceptdate.value.substring(5,7));
    var acceptday = Number(document.forms[0].acceptdate.value.substring(8,10));
    var acceptweek = getdayofweek(acceptyear,acceptmonth,acceptday);
    if(year>acceptyear||(year==acceptyear&&week>=acceptweek)){
      alert( '${eoms:a2u("接收时间应晚于报表生成时间")}' );
      return false; 
    }  
  }
  
  
  if(document.forms[0].saveuser.value==null||document.forms[0].saveuser.value==''){
    alert( '${eoms:a2u("请选择接收人")}' );
    return false;  
  }
  
  var checkboxSelected=false;
  if(document.forms[0].accepttype!=null){
    for(var i=0;i<document.forms[0].accepttype.length;i++){
      if(document.forms[0].accepttype[i].checked){
        checkboxSelected=true;
      }
    }
  }
  if(checkboxSelected==false){
    alert( '${eoms:a2u("请选择接收方式")}' );
    return false;
  }
  
 
}
</script>

<script type="text/javascript">
var	userTreeAction='${app}/xtree.do?method=dept';//部门树
var userByDeptTree='${app}/xtree.do?method=userFromDept';//部门用户树
var	roleTree='${app}/xtree.do?method=roleTree'; //角色树
var subroleFromDept='${app}/xtree.do?method=subroleFromDept'; //部门角色树
var	areaTree='${app}/xtree.do?method=areaTree'; //地域树

Ext.onReady(function(){
	//v = new eoms.form.Validation({form:"customstatremindForm"});
	
	//部门用户树
	userByDeptTree = new xbox({
		btnId:'userByDeptTreeBtn',
		treeDataUrl:userByDeptTree,treeRootId:'-1',treeRootText:'${eoms:a2u("人员")}',treeChkMode:'',treeChkType:'user',
		showChkFldId:'userByDeptName',saveChkFldId:'saveuser' 
	});
})
</script>


<form name="customstatremindForm" method="post" action="stSubscriptions.do?method=customstatremindsave" onsubmit='return SubmitCheck()'>
<table class="formTable">
    <caption>${eoms:a2u("报表订阅发送")}</caption>
    <tr>
      <td width="100" class="label">
        ${eoms:a2u("报表名称")}
      </td>
      <td width="500" colspan="5">
        <select size=1 name="statname" class="select"">
          <option value="0" selected>${eoms:a2u("--请选择报表名称--")}</option>
          <%
          if(namelist!=null){
              for(int n=0;n<namelist.size();n++){
              String[] strs = (String[])namelist.get(n);
              String subId = strs[0];
              String sheetName = strs[1];
          %>
          <option value="<%=subId+","+sheetName %>"><%=sheetName %></option>
          <%  }
          }
          %>
        </select>
      </td>
    </tr>
    <tr>
      <td width="100" class="label">
        ${eoms:a2u("报表类型")}
      </td>
      <td width="500" colspan="5">
        <select size=1 name="stattype" class="select" onChange="changetype(document.customstatremindForm.stattype.options[document.customstatremindForm.stattype.selectedIndex].value)">
          <option value="0" selected>${eoms:a2u("--请选择报表类型--")}</option>
          <option value="1">${eoms:a2u("年")}</option>
          <option value="2">${eoms:a2u("季")}</option>
          <option value="3">${eoms:a2u("月")}</option>
          <option value="4">${eoms:a2u("日")}</option>
          <option value="5">${eoms:a2u("周")}</option>
        </select>
      </td>
    </tr>
    <tr>
      <td width="100" class="label">
        ${eoms:a2u("报表生成时间")}
      </td>
      <td width="500" colspan="5">
      <div id ="yeartime" style="display:none">
        ${eoms:a2u("年份：")}
        <select size=1 name="year" class="select"">
        <%
        for(int i=startyear;i<=endyear;i++){
            if(i==year){
        %>
            <option value="<%=i %>" selected><%=i %>${eoms:a2u("年")}</option>
        <%  }else{ %>
            <option value="<%=i %>"><%=i %>${eoms:a2u("年")}</option>
        <%  }
        } 
        %>
        </select>
      </div>
      
      <div id ="quartertime" style="display:none">
        ${eoms:a2u("季度：")}
        <select size=1 name="quarter" class="select"">
        <%
        for(int q=1;q<=4;q++ ){
            if(q==quarte){
        %>
                <option value="<%=q %>" selected>${eoms:a2u("第")}<%=q %>${eoms:a2u("季度")}</option>
        <%  }else{ %>
                <option value="<%=q %>">${eoms:a2u("第")}<%=q %>${eoms:a2u("季度")}</option>
        <%
            } 
        }
        %>
        </select>
      </div>

      <div id ="monthtime" style="display:none">
        ${eoms:a2u("月份：")}
        <select size=1 name="month" class="select"">
        <%
        for(int m=1;m<=12;m++){
            if(m==month){ 
        %>
                <option value="<%=m %>" selected><%=m %>${eoms:a2u("月份")}</option>
        <%  }else{%>
                <option value="<%=m %>"><%=m %>${eoms:a2u("月份")}</option>
        <%
            }
        } 
        %>
        </select>
      </div>

      <div id ="datetime" style="display:none">
        ${eoms:a2u("日期：")}
        <input type="text" name="date" size="20" value ="<%=StaticMethod.getCurrentDateTime("yyyy-MM-dd") %>" onclick="popUpCalendar(this, this,null,null,null,false,-1);" readonly="readonly" class="text"> 
      </div>

      <div id ="weektime" style="display:none">
        ${eoms:a2u("周：")}
        <select size=1 name="week" class="select"">
        <%
        for(int w =1;w<=53;w++){
            if(w==week){
        %>
                <option value="<%=w %>" selected>${eoms:a2u("第")}<%=w %>${eoms:a2u("周")}</option>
        <%  }else{%>
                <option value="<%=w %>">${eoms:a2u("第")}<%=w %>${eoms:a2u("周")}</option>
        <%
            }
        }
        %>
        </select>
      </div>
      </td>
    </tr>
    <tr>
      <td width="100" class="label">
        ${eoms:a2u("接收人")}
      </td>
      <td width="500" colspan="5">
	  
	  <input type="button" value="${eoms:a2u('部门人员选择')}" id="userByDeptTreeBtn" class="btn" />
  		  	<input type="text"  name="userByDeptName"  value="" id ="userByDeptName" class="text" />
  		  	<input type="hidden" id="saveuser" value="" name="saveuser" />
  		  	
	  </td>
	</tr>
	<tr>
      <td width="100" class="label">
        ${eoms:a2u("接收时间")}
      </td>
      <td width="500" colspan="5">
        <input type="text" name="acceptdate" size="20" value ="<%=StaticMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") %>" onclick="popUpCalendar(this, this,null,null,null,true,'<%=StaticMethod.getCurrentDateTime("yyyy-MM-dd")%>');" readonly="readonly" class="text"> 
	  </td>
	</tr>
	<tr>
      <td width="100" class="label">
        ${eoms:a2u("接收方式")}
      </td>
      <td width="500" colspan="5">
        <input name="accepttype" type="checkbox" id="accepttype" value="1" />${eoms:a2u("短信")}
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input name="accepttype" type="checkbox" id="accepttype" value="2" />${eoms:a2u("邮件")}
	  </td>
	</tr>
	<tr>
	  <td width="600" colspan="6">
	    <INPUT type="submit" value=${eoms:a2u("保存")} name="submit" Class="submit" >
	  </td>
	</tr>
</table>
</form>
