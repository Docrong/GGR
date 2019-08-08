<%@page contentType="text/html;charset=ISO8859_1" %>
<%@page import="com.boco.eoms.resmanage.query.*" %>
<%@page import="java.util.*" %>
<%@include file="../power.jsp" %>
<%
    /**
     *@ E-DIS (ËÄ´¨Ê¡)
     *@ Copyright : (c) 2003
     *@ Company : BOCO.
     *@ ×ÊÁÏ²éÑ¯Ä£¿é
     *@ author LiuYang
     *@ version 1.0
     *@ date    2003-05-09
     **/
%>
<html>
<head>
    <title>Ìõ¼þÑ¡Ôñ</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO8859_1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<script language=javascript>
    function goSearch() {
        condition.action = "queryResult.jsp";
    }

    function goStat() {
        condition.action = "statSelect.jsp";
    }

    function gofilter() {
        condition.action = "conditionSelect.jsp";
        condition.submit();
    }
</script>
<body>
<table bgcolor="#dddddd" width='100%'>
    <tr>
        <td align=center width='100%' colspan=2><font size=2><B>²éÑ¯Ìõ¼þÑ¡Ôñ</B></font></td>
    </tr>
    <form name=condition method=post>
        <tr class=td_label>
                <%
//String classid = request.getParameter("class");
//String typeid = request.getParameter("type");
String sId = null;
if(request.getParameter("id") != null)
	sId = request.getParameter("id");
else
	sId = "2";
String classid = "102";
String typeid = "33";
int manufid = 0;
if(request.getParameter("manuf") != null)
	manufid = Integer.parseInt(request.getParameter("manuf"));

//out.println("Type :"+typeid+"...class:"+classid);
int cityid = 0;
if(request.getParameter("city") != null)
	cityid = Integer.parseInt(request.getParameter("city"));
else
	cityid = ug.getCity();
queryRes qre = new queryRes();
Vector manuV = new Vector();
if(Integer.parseInt(classid) == 106 || Integer.parseInt(classid) == 104)
	manuV = qre.getPowerMaunf();
else
	manuV = qre.getManufacturer();

manufacturer manu = new manufacturer();
out.println("<tr class=td_label><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸öÉè±¸³§¼Ò:</font></td><td align=left><select name=manuf><option value=0>--È«²¿--</option>");
for(int a = 0; a < manuV.size(); a ++)
{
	manu = (manufacturer)manuV.get(a);
	if(manu.getId() == manufid)
		out.println("<option value="+manu.getId()+" selected>"+manu.getName()+"</option>");
	else
		out.println("<option value="+manu.getId()+">"+manu.getName()+"</option>");
}
out.println("</select></td></tr>");

Vector ctV = new Vector();
ctV = qre.getCity(cityid);
cityClass ct = new cityClass();
if(Integer.parseInt(typeid) == 33)
	out.println("<tr class=td_label><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö³ÇÊÐ:</font></td><td align=left><select name=city onchange='gofilter()'><option value=0>--È«²¿--</option>");
else
	out.println("<tr class=td_label><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö³ÇÊÐ:</font></td><td align=left><select name=city><option value=0>--È«²¿--</option>");

for(int a = 0; a < ctV.size(); a ++)
{
	ct = (cityClass)ctV.get(a);
	if(ct.getId() == cityid)
		out.println("<option value="+ct.getId()+" selected>"+ct.getName()+"</option>");
	else
		out.println("<option value="+ct.getId()+">"+ct.getName()+"</option>");
}
out.println("</select></td></tr>");

Vector otV = new Vector();
otV = qre.getOtherType(classid,typeid);
otherType ot = new otherType();

for(int i = 0; i < otV.size(); i ++)
{
	ot = (otherType)otV.get(i);
	switch(ot.getType())
	{
		case 1:
			out.println("<tr class=td_label><td align=right width='50%'><font size=2>ÇëÄúÑ¡ÔñÒ»¸ö"+ot.getName()+":</font></td><td align=left><select name="+ot.getId()+"><option value=0>--È«²¿--</option>");
			Vector tp = new Vector();
			tp = qre.getSelectOption(ot,cityid);
			//out.println("<script>alert('city:"+cityid+"')</script>");
			//out.println("Field :"+ot.getField()+"...Flag:"+ot.getFlag()+"..Table:"+ot.getTable());
			for(int a = 0; a < tp.size(); a ++)
			{
				publicClass pb = new publicClass();
				pb = (publicClass)tp.get(a);
				if(ot.getField().compareTo(ot.getFlag()) == 0)
					out.println("<option value='"+pb.getName()+"'>"+pb.getName()+"</option>");
				else
					out.println("<option value='"+pb.getId()+"'>"+pb.getName()+"</option>");
			}
			out.println("</select></td></tr>");
			break;
		case 2:
			out.println("<tr class=td_label><td align=right width='50%'><font size=2>ÇëÄúÊäÈë"+ot.getName()+":</font></td><td align=left><input type=input name="+ot.getId()+"></td></tr>");
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
	}
}
%>
        <tr class=td_label>
            <input type=hidden name=class value=<%=classid%>>
            <input type=hidden name=type value=<%=typeid%>>
            <input type=hidden name=id value=<%=sId%>>
            <td width='100%' align=center colspan=2><input type=submit name=go value='²éÑ¯'
                                                           onclick='javascript:goSearch()'></td>
            <!--<td width='50%' align=center><input type=submit name=submit value='Í³¼Æ' onclick='javascript:goStat()'></td>-->
        </tr>
    </form>
</table>
</body>
</html>
