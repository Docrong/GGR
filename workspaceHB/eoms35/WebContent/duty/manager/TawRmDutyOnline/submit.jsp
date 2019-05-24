<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*,com.boco.eoms.base.util.StaticMethod,com.boco.eoms.duty.util.DutyMgrLocator,com.boco.eoms.duty.test.dao.GuiCamera"%>
 
<%
//String logout=(String)request.getParameter("logout");

//if (logout.equals("yes")) 
		try {
		 
			int random = (int)(Math.random()*100000);
			String timeTag = StaticMethod.getCurrentDateTime("yyyyMMddHH")+random+"";
			String uploadPath = DutyMgrLocator.getAttributes().getDutyRootPath()
					+ "/" ;
			 System.out.println(StaticMethod.getWebPath());
			 
			//String sysTemPaht = request.getRealPath("/");
			String sysTemPaht =StaticMethod.getWebPath();
			 // 取当前系统路径
			 String url = uploadPath+timeTag;
			 String path = sysTemPaht + "/"+url;
			 session.setAttribute("url",url);
			GuiCamera cam = new GuiCamera(path, "png");//

			cam.snapShot();

		} catch (Exception e) {
			e.printStackTrace();
		}

	synchronized (application) {
		String Name = (String) session.getAttribute("UserName");
		Vector outMessage=null;
		outMessage= (Vector)application.getAttribute("Message");
		String url = (String)application.getAttribute("url");
		url =  (String) session.getAttribute("url");
		System.out.println(url);
		
		if(outMessage==null)
		outMessage= new Vector(30,10);
		String path = request.getContextPath();
		System.out.println(path);
		String outstr="";
		outstr ="<font color=blue>"+Name+"</font>说: <br><img src='"+path+"/"+url+".png'"+">" ;
		String str1="no";
		String systemSpeak = "yes";
		outMessage.addElement(str1);
		outMessage.addElement(Name);
		outMessage.addElement(str1);
		outMessage.addElement(outstr);
		outMessage.addElement(systemSpeak);
		outMessage.addElement(str1);
		application.setAttribute("Message", outMessage);
		//session.destroy("Name");
		%>
		 <script language="JavaScript">
			window.close();
		</script>
		<%
	}

%>

