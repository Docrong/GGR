<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../../duty/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page import="java.util.*"%>
<HTML>
<HEAD>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META http-equiv="refresh" content="3">
<TITLE>outputMessage</TITLE>
</HEAD>

<script language="JavaScript">
function scrollchange()
{
	parent.mainFrame.window.scroll(0,60000);
	parent.mainFrame.document.bgColor="#ffffff";
return true;
}
</script>

<BODY onload="scrollchange()" bgcolor="#eeeeee">

<FORM method="post">

<%
request.setCharacterEncoding("UTF-8");
String Name =(String)session.getAttribute("UserName");
GregorianCalendar calendar =new GregorianCalendar();
String time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);

if (Name == null) {
	return;
}
String str1="<div style='font-size:11pt;line-height:15pt'>";
String str2=new String("<font color='blue'>"+Name+"</font>");
String str3=new String("<font color='red'>[悄悄的]</font>");
String str4=new String("<br>");
String str5=new String("TO");
String str6="</div>";

Integer MessageIndex =(Integer)session.getAttribute("MessageIndex");
if((MessageIndex==null )||( MessageIndex.intValue() >= 200 ))
	MessageIndex=new Integer(0);

synchronized(application) {
	String alone=new String("yes");
	Vector DisplayMessage=null;
	DisplayMessage =(Vector)application.getAttribute("Message");

	if(DisplayMessage !=null) {
		if(DisplayMessage.size()< MessageIndex.intValue()) {
			MessageIndex=new Integer(DisplayMessage.size());
		}

		if(MessageIndex.intValue()!= DisplayMessage.size()) {
			for(int i=MessageIndex.intValue();i<DisplayMessage.size();i=i+6) {
				int     aloneindex=i;
				int     nameindex=i+1;
				int     talkwithindex=i+2;
				String  Messagestr= (String)DisplayMessage.get(i+3);
				String  systemSpeak= (String)DisplayMessage.get(i+4);
				String  action2= (String)DisplayMessage.get(i+5);
				String  alonetag  = (String)DisplayMessage.get(aloneindex);

				if(alonetag==null)
					alonetag=new String("all");

				String  nametag =  (String)DisplayMessage.get(nameindex);

				String  talkwithtag =(String)DisplayMessage.get(talkwithindex);

				
				if(systemSpeak.compareTo("yes")==0) {
					Messagestr = str1+Messagestr+str6;
					%>
					<script language="JavaScript">
						parent.mainFrame.document.write("<%= Messagestr%>")
					</script>
					<%
				}

				else {
					
					if(alonetag.compareTo(alone)==0) {
					
						if(nametag.compareTo(Name)==0) {
							
							if (action2.compareTo(alone)==0) {
								Messagestr=str1+str3+str2+Messagestr+str4+str6;
							}
							
							else {
								Messagestr=str1+str3+str2+"对<font color='blue'>"+talkwithtag+"</font>说["+time+"]:"+Messagestr+str4+str6;
							}
							%>
							<script language="JavaScript">
								parent.privateFrame.document.write("<%= Messagestr%>")   
							</script>
							<%
						}else
						
						if((talkwithtag.compareTo(Name)==0)) {
							
							if (action2.compareTo(alone)==0) {
								Messagestr=str1+str3+"<font color='blue'>"+nametag+"</font>"+Messagestr+str4+str6;
							}
							
							else {
								Messagestr=str1+str3+"<font color='blue'>"+nametag+"</font>对"+str2+"说["+time+"]:"+Messagestr+str4+str6;
							}
							%>
							<script language="JavaScript">
								parent.privateFrame.document.write("<%= Messagestr%>")   
							</script>
							<%
						}

					}
					
					else {
						if(nametag.compareTo(Name)==0) {
							
							if (action2.compareTo(alone)==0) {
								Messagestr=str1+str2+Messagestr+str4+str6;
							}
						
							else {
								Messagestr=str1+str2+"对<font color='blue'>"+talkwithtag+"</font>说["+time+"]:"+Messagestr+str4+str6;
							}
							%>
							<script language="JavaScript">
								parent.mainFrame.document.write("<%= Messagestr%>")
							</script>
							<%

						}
						
						else if((talkwithtag.compareTo(Name)==0)) {
							
							if (action2.compareTo(alone)==0) {
								Messagestr=str1+"<font color='blue'>"+nametag+"</font>"+Messagestr+str4+str6;
							}
							
							else {
								Messagestr=str1+"<font color='blue'>"+nametag+"</font>对"+str2+"说["+time+"]:"+Messagestr+str4+str6;
							}
							%>
							<script language="JavaScript">
								parent.mainFrame.document.write("<%= Messagestr%>")
							</script>
							<%
						}

						else {
							
							if (action2.compareTo(alone)==0) {
								Messagestr= str1+"<font color='blue'>"+nametag+"</font>"+Messagestr+str4+str6;
							}
							else {
								Messagestr= str1+"<font color='blue'>"+nametag+"</font>"+"对<font color='blue'>"+talkwithtag+"</font>说["+time+"]："+Messagestr+str4+str6;
							}
							%>
							<script language="JavaScript">
								parent.mainFrame.document.write("<%= Messagestr%>")
							</script>
							<%
						}

					}
				}

				Integer count=new Integer(i+6);

				session.setAttribute("MessageIndex",count);

			}
		}
	}
	else
	out.println("Welcome to here!");
}

%>

</form>
</body>
</html>

