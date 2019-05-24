<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="com.boco.eoms.resmanage.query.*"%>
<%@page import="com.boco.eoms.resmanage.operator.*"%>
<%@page import="mcs.common.db.*"%>
<%@page import="java.util.*"%>
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
</HEAD>
<BODY>
<TABLE id='MSC'>
<TR>
	<TD><%
	out.println("CITY:"+request.getParameter("city"));
	String a = "alramre-sfdsa-are";
//	out.println("FJDJS"+a.indexOf("-"));
	String tmp = mtools.removeString(a);
	out.println(a+":"+tmp);
	%></TD>
</TR>
<form name=test method=post>
<select name=city onchange='gofilter()' style='display:none'><option value=0>--全部--</option>
<option value=1>--全部--</option>
<option value=2>--全部--</option>
<option value=3>--全部--</option>
<option value=4>--全部--</option>
<option value=5>--全部--</option>
<option value=6>--全部--</option>
<option value=7>--全部--</option>
</select>
<select name=city onchange='gofilter()'><option value=0>--全部--</option>
<option value=1>--全部--</option>
<option value=2>--全部--</option>
<option value=3>--全部--</option>
<option value=4>--全部--</option>
<option value=5>--全部--</option>
<option value=6>--全部--</option>
<option value=7>--全部--</option>
</select>
</form>
<script>
function gofilter()
{
	for(i = 0; i < test.city.length; i ++)
	{
		if(test.city[i].style.display == '')
			alert("IST!"+i);
	}
	//alert(test.city.value);
}
</script>
</TABLE>
</BODY>
</HTML>