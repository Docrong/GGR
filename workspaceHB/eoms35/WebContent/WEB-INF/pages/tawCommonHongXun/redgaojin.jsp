<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*,java.text.SimpleDateFormat,com.boco.eoms.message.service.impl.MsgServiceImpl,org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%
  //String model = StaticMethod.getGBString(request.getParameter("model"));
  //String mobiles = request.k("mobiles");

  List redList=(List)request.getAttribute("redList");

%>
<style>
#tabs {
	width:90%;
}
#tabs .x-tabs-item-body {
	display:none;
	padding:10px;
}
</style>


<content tag="heading"></content>
<div id="tabs">
<div id="form" class="tab-content">
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff" >
<table border="0" width="95%" cellspacing="1">
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('数据发送')}</td>
 </tr>
 <tr>
     <td align="center" width="100%" valign="center" height="100%">
     <p align="center"></p>
      <p align="center"><FONT size=20 color="#00bbcc" face="宋体">

  <%
   String msg = "<h1>操作成功</h1>";
   try{
   msg=new String(msg.getBytes("ISO8859-1"),"utf-8");
     String model="";
     String mobiles="";
     String result="";
       for(int n=0;n<redList.size();n++){
        List obj = (List) redList.get(n);
        mobiles = StaticMethod.getGBString(obj.get(4).toString());
        model = obj.get(5).toString();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
		MsgServiceImpl   msgService = new MsgServiceImpl();
		msgService.sendMsgByCondition(model,mobiles);
      }
      }catch (Exception E) {
      System.out.println(E);
      msg = "<h1>* 发送失败！*</h1>";
      msg=new String(msg.getBytes("ISO8859-1"),"utf-8");
    }
  out.print(msg);
%></FONT></p>
     </td>
 </tr>
</table>

  

</body>
</html>
