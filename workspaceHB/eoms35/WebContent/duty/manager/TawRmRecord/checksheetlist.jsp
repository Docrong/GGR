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
       intPageSize=60;
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
  String starttime=(String)request.getAttribute("STARTTIME");
  String endtime=(String)request.getAttribute("ENDTIME");
  String roomId=(String)request.getAttribute("ROOMID");
  String dutyId=(String)request.getAttribute("DUTYID");
  int i=0;
    int al=0;

       intRowCount=checkList.size();
        //System.out.println(intRowCount);
          //������ҳ��
         intPageCount = (intRowCount+intPageSize-1)/intPageSize;
         if(intPage>intPageCount) intPage = intPageCount;
%>

<HTML xmlns:v xmlns:BOCO="BOCO Inter-Telecom">
<html>
<head>
<title>δ�鵵����</title>
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
    <td colspan="9"><div align="center">ֵ��δ��ʱ�鵵����</div></td>
  </tr>
  <tr>
     <td align="right"  colspan="9" bgcolor="#aec2e8">

              <!--  -->
              <font color="#FFFFFF">ÿҳ</font><font color="#FFFFFF"><%=intPageSize%>�� ��</font><font color="#FFFFFF"><%=intRowCount%>��
              ��</font><font color="#FFFFFF"><%=intPage%>ҳ ��</font><font color="#FFFFFF"><%=intPageCount%>ҳ</font>            </td>
          </tr>
          <tr bgcolor="#EEECED">
            <td align="right" bgcolor="#aec2e8" colspan="9" >
                <A HREF ="checklist.do?page=1&dutyId=<%=dutyId%>"> ��ҳ</A>

                <%if(intPage>1){%>
                <a href="checklist.do?page=<%=intPage-1%>&dutyId=<%=dutyId%>">��һҳ</a>
                <%
                 }
                 %>
                <%
               if(intPage<intPageCount){
               %>

              <a href="checklist.do?page=<%=intPage+1%>&dutyId=<%=dutyId%>">��һҳ</a>
              <%
               }
               %>
              <A HREF ="checklist.do?page=<%=intPageCount%>&dutyId=<%=dutyId%>">βҳ</A> ת����
              <SELECT name="jumpPage"   onchange="location.href=this.options[this.selectedIndex].value" size="1">
                <% for(int l=1;l<=intPageCount;l++)  {
                 if(l!=intPage){
                  %>
                  <OPTION   value="checklist.do?page=<%=l%>&dutyId=<%=dutyId%>"> <%=l%></OPTION>

                <%}else{%>
                  <OPTION  selected <%=intPage%>><%=intPage%></OPTION>
                  <%}
                  }%>
              </select>            </td>
          </tr>
    </tr>
  <tr class="tr_show">
    <td width="29%" class= "clsfth" ><div align="center">������</div></td>
    <td width="28%" class= "clsfth" ><div align="center">�������� </div></td>
    <td width="19%" class= "clsfth" ><div align="center">ֵ��೤</div></td>
    <td width="24%" class= "clsfth" ><div align="center">ֵ������</div></td>
    
  </tr>
  <%

         if(intPageCount>0){
          al=((intPage-1)*intPageSize);
          i=0;
          int num=intPageSize*intPage;
          while(num>intRowCount){
            num=num-1;
          }

  for(int n=al;n<num;n++){
  List obj = (List) checkList.get(n);

  %>

  <tr class="tr_show">

    <td><div align="center"><a href="/EOMS_J2EE/newworksheet<%=new String(obj.get(5).toString().getBytes("819"),"gb2312")%><%=new String(obj.get(0).toString().getBytes("819"),"gb2312")%>"><%=new String(obj.get(1).toString().getBytes("819"),"gb2312")%></a></div></td>



    <td><div align="center"><%=new String(obj.get(2).toString().getBytes("819"),"gb2312")%></div></td>



    <td><div align="center"><%=new String(obj.get(3).toString().getBytes("819"),"gb2312")%></div></td>


    <td><div align="center"><%=new String(obj.get(4).toString().getBytes("819"),"gb2312")%></div></td>


   



  </tr>
  <%}}%>
<tr bgcolor="#EEECED">
  <td colspan="4" bgcolor="#aec2e8"><div align="center"><a href="checkdetail.do?starttime=<%=starttime%>&endtime=<%=endtime%>&roomId=<%=roomId%>">������ҳ</a></div></td>
</tr>
<tr bgcolor="#EEECED" >

            <td align="right"  colspan="9" bgcolor="#aec2e8">

              <!--  -->
              <font color="#FFFFFF">ÿҳ</font><font color="#FFFFFF"><%=intPageSize%>�� ��</font><font color="#FFFFFF"><%=intRowCount%>��
              ��</font><font color="#FFFFFF"><%=intPage%>ҳ ��</font><font color="#FFFFFF"><%=intPageCount%>ҳ</font>            </td>
          </tr>
          <tr bgcolor="#EEECED">
            <td align="right" bgcolor="#aec2e8" colspan="9" >
                <A HREF ="checklist.do?page=1&dutyId=<%=dutyId%>"> ��ҳ</A>

                <%if(intPage>1){%>
                <a href="checklist.do?page=<%=intPage-1%>&dutyId=<%=dutyId%>">��һҳ</a>
                <%
                 }
                 %>
                <%
               if(intPage<intPageCount){
               %>

              <a href="checklist.do?page=<%=intPage+1%>&dutyId=<%=dutyId%>">��һҳ</a>
              <%
               }
               %>
              <A HREF ="checklist.do?page=<%=intPageCount%>&dutyId=<%=dutyId%>">βҳ</A> ת����
              <SELECT name="jumpPage"   onchange="location.href=this.options[this.selectedIndex].value" size="1">
                <% for(int l=1;l<=intPageCount;l++)  {
                 if(l!=intPage){
                  %>
                  <OPTION   value="checklist.do?page=<%=l%>&dutyId=<%=dutyId%>"> <%=l%></OPTION>

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
