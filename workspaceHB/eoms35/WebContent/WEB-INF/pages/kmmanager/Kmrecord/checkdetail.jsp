<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import ="com.boco.eoms.wfworksheet.stopstation.vo.StopstationLinkVO,
                  com.boco.eoms.wfworksheet.base.util.StaticWorksheet,
                  com.boco.eoms.wfengine.util.StaticWfMethod,
                  com.boco.eoms.wfengine.util.WfAction,
                  com.boco.eoms.wfengine.task.model.WfTask,
                  com.boco.eoms.common.util.StaticMethod,
                  java.util.List,java.lang.Object,com.boco.eoms.wfengine.task.dao.TaskDAO,
                  com.boco.eoms.wfworksheet.stopstation.flowcontrol.StopstationFSMBO,
                  com.opensymphony.workflow.Workflow,
                 com.opensymphony.workflow.basic.BasicWorkflow,
                 com.opensymphony.workflow.spi.Step"%>

<%    int intPageSize=0;//ÿҳ������
      int intRowCount=0;//�ܹ��ж�����
      int intPageCount=0;//��ҳ��
      int intPage=0;
       java.lang.String strPage;
       intPageSize=20;
       strPage=request.getParameter("page");

      if(strPage==null)
       {
           intPage=1;
       }
      else
       {
          intPage=java.lang.Integer.parseInt(strPage);
          if(intPage<1)
               intPage=1;
       }
  List checkList=(List)request.getAttribute("CHECKLIST");
  List dateList=(List)request.getAttribute("DATELIST");
  String starttime=(String)request.getAttribute("STARTTIME");
  String endtime=(String)request.getAttribute("ENDTIME");
  String roomId=(String)request.getAttribute("ROOMID");
  //request.setAttribute("STARTDATE",starttime);
  //request.setAttribute("ENDDATE",endtime);

  //int num= Integer.parseInt((String)request.getAttribute("NUM"));

  //String flag_id=(String)request.getAttribute("FLAG");
  //String strtype=(String)request.getAttribute("DICT");
  int i=0;
    int al=0;

       intRowCount=dateList.size();
        //System.out.println(intRowCount);
          //������ҳ��
         intPageCount = (intRowCount+intPageSize-1)/intPageSize;
         if(intPage>intPageCount) intPage = intPageCount;
%>

<HTML xmlns:v xmlns:BOCO="BOCO Inter-Telecom">
<html>
<head>
<title>����</title>
<STYLE>
v\:*{behavior:url(#default#VML);}
</STYLE>

<SCRIPT LANGUAGE="JavaScript">

</SCRIPT>

<import namespace=BOCO implementation="<%=request.getContextPath()%>/css/button/genericButton.htc"/>
</head>


<body topmargin=0 marginheight=0 leftmargin=0 marginwidth=0 >
<br>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr>
    <td colspan="9"><div align="center">���˱�</div></td>
  </tr>
  <tr>
     <td align="right"  colspan="9" bgcolor="#aec2e8">

              <!--  -->
              <font color="#FFFFFF">ÿҳ</font><font color="#FFFFFF"><%=intPageSize%>�� ��</font><font color="#FFFFFF"><%=intRowCount%>��
              ��</font><font color="#FFFFFF"><%=intPage%>ҳ ��</font><font color="#FFFFFF"><%=intPageCount%>ҳ</font>            </td>
          </tr>
          <tr bgcolor="#EEECED">
            <td align="right" bgcolor="#aec2e8" colspan="9" >
                <A HREF ="checkdetail.do?page=1&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>"> ��ҳ</A>

                <%if(intPage>1){%>
                <a href="checkdetail.do?page=<%=intPage-1%>&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>">��һҳ</a>
                <%
                 }
                 %>
                <%
               if(intPage<intPageCount){
               %>

              <a href="checkdetail.do?page=<%=intPage+1%>&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>">��һҳ</a>
              <%
               }
               %>
              <A HREF ="checkdetail.do?page=<%=intPageCount%>&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>">βҳ</A> ת����
              <SELECT name="jumpPage"   onchange="location.href=this.options[this.selectedIndex].value" size="1">
                <% for(int l=1;l<=intPageCount;l++)  {
                 if(l!=intPage){
                  %>
                  <OPTION   value="checkdetail.do?page=<%=l%>&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>"> <%=l%></OPTION>

                <%}else{%>
                  <OPTION  selected <%=intPage%>><%=intPage%></OPTION>
                  <%}
                  }%>
              </select>            </td>
          </tr>
    </tr>
  <tr class="tr_show">
    <td width="14%" class= "clsfth" ><div align="center">���</div></td>
    <td width="20%" class= "clsfth" ><div align="center">�����鵵����ʼʱ��</div></td>
	<td width="21%" class= "clsfth" ><div align="center">�����鵵�������ʱ��</div></td>
    <td width="15%" class= "clsfth" ><div align="center">�೤</div></td>
    <td width="30%" class= "clsfth" ><div align="center">δ�鵵��������</div></td>
    </tr>
  <%

         if(intPageCount>0){
          al=((intPage-1)*intPageSize);
          i=0;
          int num=intPageSize*intPage;
          while(num>intRowCount){
            num=num-1;
          }

  for(int n=al;n<num;n++){%>

  <tr><td colspan="5" bgcolor="#FFFFCC"><div align="center"><font color="#cc00000">�������ڣ�<%=dateList.get(n).toString()%></font></div>
  </tr>
<% // List obj = (List) datekList.get(n);
int m=0;
      for(int q=0;q<checkList.size();q++){
	  List obj = (List) checkList.get(q);%>

	  <%
	  if(obj.get(0).toString().equals(dateList.get(n).toString())){
  %>

  <tr class="tr_show">

    <td><div align="center"><%=new String(obj.get(1).toString().getBytes("819"),"gb2312")%></div></td>



    <td><div align="center"><%=new String(obj.get(3).toString().getBytes("819"),"gb2312").substring(0,19)%></div></td>
	<td><div align="center"><%=new String(obj.get(4).toString().getBytes("819"),"gb2312").substring(0,19)%></div></td>



    <td><div align="center"><%=new String(obj.get(2).toString().getBytes("819"),"gb2312")%></div></td>


    <td><div align="center"><a href="checklist.do?dutyId=<%=new String(obj.get(1).toString().getBytes("819"),"gb2312")%>&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>"><%=new String(obj.get(5).toString().getBytes("819"),"gb2312")%></a></div></td>
  </tr>
  <%m++;}%>

 <% }%>
 <%if(m==0){%>
 <tr class="tr_show"><td colspan="5"><div align="center">��Ҫ����ɹ鵵</div></td></tr>
 <%m=0;}%>

  <%}}else{%>
 <tr><td colspan="5" bgcolor="#FFFFCC"><div align="center">��ѡ��Ĳ�ѯ����û������</div>
  </tr>
<%}%>
<tr bgcolor="#EEECED" >

            <td align="right"  colspan="9" bgcolor="#aec2e8">

              <!--  -->
              <font color="#FFFFFF">ÿҳ</font><font color="#FFFFFF"><%=intPageSize%>�� ��</font><font color="#FFFFFF"><%=intRowCount%>��
              ��</font><font color="#FFFFFF"><%=intPage%>ҳ ��</font><font color="#FFFFFF"><%=intPageCount%>ҳ</font>            </td>
          </tr>
          <tr bgcolor="#EEECED">
            <td align="right" bgcolor="#aec2e8" colspan="9" >
                <A HREF ="checkdetail.do?page=1&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>"> ��ҳ</A>

                <%if(intPage>1){%>
                <a href="checkdetail.do?page=<%=intPage-1%>&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>">��һҳ</a>
                <%
                 }
                 %>
                <%
               if(intPage<intPageCount){
               %>

              <a href="checkdetail.do?page=<%=intPage+1%>&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>">��һҳ</a>
              <%
               }
               %>
              <A HREF ="checkdetail.do?page=<%=intPageCount%>&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>">βҳ</A> ת����
              <SELECT name="jumpPage"   onchange="location.href=this.options[this.selectedIndex].value" size="1">
                <% for(int l=1;l<=intPageCount;l++)  {
                 if(l!=intPage){
                  %>
                  <OPTION   value="checkdetail.do?page=<%=l%>&starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>"> <%=l%></OPTION>

                <%}else{%>
                  <OPTION  selected <%=intPage%>><%=intPage%></OPTION>
                  <%}
                  }%>
              </select>            </td>
          </tr>
</table>
<p>
  <!--<table border="0" cellspacing="1" cellpadding="1" width="95%" align="center">


</table>
-->
</p>
<!---���Ľ���------------------------------------------------------------------------------------------>

</body>
</html>
