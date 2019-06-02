
<jsp:directive.page import="com.boco.eoms.sequence.ISequenceFacade"/>
<jsp:directive.page import="com.boco.eoms.sequence.util.SequenceLocator"/>
<jsp:directive.page import="com.boco.eoms.sequence.Sequence"/>
<jsp:directive.page import="com.boco.eoms.sequence.exception.SequenceNotFoundException"/>
<jsp:directive.page import="com.boco.eoms.sequence.test.Helper"/><%@ include file="/common/taglibs.jsp"%>

<%@ include file="/common/header_eoms_form.jsp"%>
<script>
	function aa()
	{
		alert("kkkk");
	}
</script>

<%=com.boco.eoms.base.util.StaticMethod.getFileUrl("classpath:config/applicationContext-all.xml")%>

<%
	request.setAttribute("defaultId","2");
	java.util.Date date=new java.util.Date();
	request.setAttribute("date",date);
 %>
 <br>
 <bean:write name="defaultId"/>
 <br>
<eoms:id2nameDB id="admin" beanId="tawSystemUserDao" />
<br>
id2nameXML11111111111111<eoms:dict key="dict-sheet-common" dictId="major" itemId="1" beanId="id2nameXML" />

<br>
id2nameXML<eoms:dict key="sample" dictId="major" itemId="1" beanId="id2nameXML" />
id2descriptionXML<eoms:dict key="dict-sheet-common" dictId="yesOrNot" itemId="1" beanId="id2nameXML" />
<br>

id2descriptionXML<eoms:dict key="sample" dictId="major" itemId="1" beanId="id2descriptionXML" />
<br>
dictId2descriptionXML<eoms:dict key="sample" dictId="major" beanId="dictId2descriptionXML" />
<br>
<eoms:dict key="sample" dictId="major" isQuery="true" selectId="dd" beanId="selectXML" onchange="aa();"/>
<br>

<eoms:dict key="sample" dictId="major" isQuery="true" defaultId="${defaultId }" selectId="aa" beanId="selectXML"/>
<br><br><br>
Relation XML Select Test
<eoms:dict key="sample-relation" relationId="majorrelation" selectId="A1" beanId="selectXML" subid="A2" onchange="aa();"/>

<eoms:dict key="sample-relation" relationId="majorrelation" selectId="A2" beanId="selectXML" subid="A3"/>
<eoms:dict key="memo-dict" dictId="level" beanId="selectXML" selectId="memodictlevel"/>
<select id="A3">
	<option>Select</option>
</select>

<eoms:id2nameDB id="101" beanId="tawSystemAreaDao" />
<br>
date:<bean:write name="date" format="yyyy-MM-dd hh:mm:ss"/>

<%
	if(request.getParameter("aa")!=null){
	Helper helper = new Helper();
	ISequenceFacade sequenceFacade=SequenceLocator.getSequenceFacade();
	Sequence dutySequence = null;
		try {
			dutySequence = sequenceFacade.getSequence("duty");
		} catch (SequenceNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 100000; i++) {

			sequenceFacade.put(helper, "outtenthousand",
					new Class[] { java.lang.String.class },
					new Object[] { "1threadduty" + i }, null, dutySequence);
		}
		dutySequence.setChanged();
		sequenceFacade.doJob(dutySequence);
		 for (int i = 0; i < 100000; i++) {
			SequenceLocator.getSequenceFacade().put(helper, "outtenthousand",
					new Class[] { java.lang.String.class },
					new Object[] { "2threadduty" + i }, null, dutySequence);
		}
		 dutySequence.setChanged();
		
		sequenceFacade.doJob(dutySequence);
	}
	
 %>

