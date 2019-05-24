<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.kbs.model.TawInformation"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="java.util.List"%>
<%
TawInformation tawInformation = new TawInformation();
List list = (List) request.getAttribute("TAWINFORMATIONS");
int listNo=StaticMethod.nullObject2int(request.getParameter("listNo"));
int boardId=StaticMethod.nullObject2int(request.getParameter("boardId"));
int infoType=StaticMethod.nullObject2int(request.getParameter("infoType"));
StringBuffer str = new StringBuffer();
str.append("<table cellpadding='0' cellspacing='0' border='0' class='table_show' width='100%' >");
int divFlag=0;
for(int i=0;i<list.size();i++){
  tawInformation = (TawInformation) list.get(i);

  if (divFlag==1)
  {
    out.println("</table></td></tr>");
    divFlag=0;
  }
  str.append("<tr class='tr_show'>");
  str.append("<td width='45%'>");
  str.append("<input type='checkbox' name='chk_sel_id' id='" + tawInformation.getId() + "' value='"+tawInformation.getId()+"'>");
  str.append("<input type='hidden' name='chk_sel_pid' id='" + tawInformation.getId() + "' value='"+tawInformation.getParentId()+"'>&nbsp;&nbsp;&nbsp;");
  for(int j=0;j<tawInformation.getLayer();j++){
    str.append("&nbsp;&nbsp;");
  }
  str.append("<img id='img" + tawInformation.getId() + "' src='../images/nofollow.gif'");
  if (tawInformation.getChildNum()>0){
    str.append(" onclick='openChild("+tawInformation.getId()+"," + infoType + "," + boardId + ");'>");
  }
  else{
    str.append(">");
  }
  str.append("<a href='view.do?id=" + tawInformation.getId() + "&boardId=");
  str.append(tawInformation.getBoardId() + "&infoType=");
  str.append(tawInformation.getInfoType() + "' title='");
  str.append(tawInformation.getTopic() + "'>");
  String topic=tawInformation.getTopic();
  if (!"".equals(tawInformation.getImgName().trim())){
    topic = topic + "(Í¼)";
  }
  else if ("".equals(tawInformation.getBody().trim())){
    topic = topic + "(ÎÞÄÚÈÝ)";
  }
  byte[] bTemp=topic.getBytes();
  String  strTemp= "";
  int limitLen = 40-tawInformation.getLayer()*2;
  if (bTemp.length>limitLen){
    strTemp = new String(bTemp,0,limitLen);
    if ((strTemp + "1").equals("1")){
      limitLen = limitLen-1;
    }
    topic = new String(bTemp,0,limitLen) + "...";
  }
  str.append(topic);
  str.append("</a>");
  str.append("</td>");
  str.append("<td width='10%'>");
  str.append(tawInformation.getUserName());
  str.append("</td>");
  str.append("<td width='10%'>");
  str.append(tawInformation.getDeptName());
  str.append("</td>");
  str.append("<td width='19%'>");
  str.append(tawInformation.getDateTime());
  str.append("</td>");
  str.append("<td width='6%'>");
  str.append(tawInformation.getHitNum());
  str.append("</td>");
  str.append("<td width='10%'>");
  String att=StaticMethod.null2String(tawInformation.getAttachName());
  if (att.trim().equalsIgnoreCase(""))
    str.append("N");
  else
    str.append("Y");
  str.append("</td>");
  str.append("</tr>");
  if (tawInformation.getChildNum()>0)
  {
    str.append("<tr id='tr"+tawInformation.getId()+"'>");
    str.append("<td colspan=6 width=100% >");
    str.append("<table width='100%' cellpadding='0' cellspacing='0' class='table_show'>");
    divFlag=1;
  }
}
if (divFlag==1)
{
  out.println("</table></td></tr>");
  divFlag=0;
}
str.append("</table>");
%>
<script language="javascript">
parent.divtr<%=listNo%>.innerHTML = "<%=str%>";
</script>
