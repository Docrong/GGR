<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.List,org.apache.struts.util.LabelValueBean,com.boco.eoms.common.util.StaticMethod"%>
<%
List boardList = (List) request.getAttribute("BOARDS");
String boardId = StaticMethod.nullObject2String(request.getParameter("boardId"));
String listNum = StaticMethod.nullObject2String(request.getParameter("listNum"));
%>
<script language="javascript">
  var str="";
  str = '<table border="0" width="350" cellspacing="0" cellpadding="0"  background="<%=request.getContextPath()%>/images/top/bg001.gif" style="border-top:1px solid #ADAAA5;border-left:1px solid #ADAAA5;border-bottom:5px solid #CECFD6;border-right:5px solid #CECFD6;">';
  str = str + '<tr>';
  str = str + '    <td width="100%" height="30" align="center" class="table_title" onmousemove="movetree()" onmousedown="mouseDown();" onmouseup="javascript:mousedown=false;" onmouseout="javascript:mousedown=false;" style="cursor:hand;">';
  str = str + '      \u4F60\u8981\u5C06\u9009\u62E9\u7684\u4FE1\u606F\u5F52\u7C7B\u4E3A\u54EA\u4E00\u4E2A\u4E13\u9898\u4E2D';
  str = str + '    </td>';
  str = str + '  </tr>';
  str = str + '  <tr>';
  str = str + '    <td width="100%" height="90" valign="middle" align="center">';
  str = str + '      <table border="0" width="80%" height="90" cellspacing="1" cellpadding="1" class="table_show">';
  str = str + '        <tr class="tr_show">';
  str = str + '          <td width="100%" align="center">';
  str = str + '            <select name = "boardId">';
                <%
                if (boardList!=null)
                {
                  for (int i=0;i<boardList.size();i++)
                  {
                    LabelValueBean lableBean = (LabelValueBean) boardList.get(i);

                    out.println("str = str + '<option value=\"" + lableBean.getValue() + "\">" + lableBean.getLabel() + "</option>';");
                  }
                }
                %>
  str = str + '            </select>';
  str = str + '          </td>';
  str = str + '        </tr>';
  str = str + '      </table>';
  str = str + '    </td>';
  str = str + '  </tr>';
  str = str + '  <tr>';
  str = str + '    <td width="100%" height="30" align="right">';
  str = str + '      <input type="button" Class="clsbtn2" value="\u786E\u5B9A" onclick="return onClassifySave(<%=listNum%>,<%=boardId%>);">';
  str = str + '      <input type="button" Class="clsbtn2" value="\u53D6\u6D88" onclick="return onClassifyCancel()"> ';
  str = str + '    </td>'
  str = str + '  </tr>'
  str = str + '</table>'
  parent.div1.innerHTML = str;
</script>