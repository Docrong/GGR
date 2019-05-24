<%@ page language="java" pageEncoding="UTF-8" %>

<%@ page import ="java.util.Hashtable"%>

<%@ page import ="java.util.Enumeration"%>

<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>

<%@ page import ="com.boco.eoms.workplan.vo.TawwpExecuteCountVO"%>



<%

  Hashtable monthPlanVOHash = (Hashtable)request.getAttribute("count");

  Enumeration hashKeys = null;

  TawwpExecuteCountVO tawwpExecuteCountVO = null;

  TawwpMonthPlanVO tawwpMonthPlanVO = null;

  String monthPlanId = null;

  String webapp = request.getContextPath();



  java.lang.String monthIdsb = new java.lang.String(); 

  java.lang.String tStub = new java.lang.String();

  String currentDD = com.boco.eoms.common.util.StaticMethod.getCurrentDateTime("s" type="text/css">

<body topmargin=0 marginheight=0 leftmargin=0 marginwidth=0 >



<!--  正文开始   -->

<br>



  <table width="100%" align=center style="margin:0pt 0pt 2pt 0pt">

    <tr align=center>

      <td width="100%" align="center" valign="middle" class="table_title">

        目前需要执行的作业计划

      </td>

    </tr>

  </table>



  <table border="0"  cellspacing="1" cellpadding="1" class="table_show" align=center width="90%">

  <!-- <tr align=center>

      <td width="100%" align="center" valign="middle" class="table_title">

        作业计划

      </td>

    </tr> -->

    <%     

      String planArray[][] = new String[100][3];

      planArray[0][0]="Null";

      hashKeys = monthPlanVOHash.keys();

      int i = 0;

      while (hashKeys.hasMoreElements())

      {        

        monthPlanId = (String)(hashKeys.nextElement());

        planArray[i][0]= monthPlanId;        

        tawwpExecuteCountVO = (TawwpExecuteCountVO)monthPlanVOHash.get(monthPlanId);

        tawwpMonthPlanVO = tawwpExecuteCountVO.getTawwpMonthPlanVO();

        planArray[i][1]=tawwpMonthPlanVO.getName();        

        planArray[i][2]=tawwpMonthPlanVO.getNetName();             

        i=i+1;

      }

      if(planArray[0][0]=="Null" || planArray[0][0].equals("Null")){     

      }

      else{

     String[] planName = new String[i];

     planName[0]=planArray[0][1];

     for(int j=1; j<i; j++){

       int z = 0;

       while (planName[z]!=null){

         if(planArray[j][1]==planName[z]||planArray[j][1].equals(planName[z])){

           break;

         }

         else{

           z=z+1;          

           if(planName[z]!=null){}

           else{

             planName[z]=planArray[j][1];            

           }

         }

      }

      }

      for(int j=0; j<i; j++){

        monthIdsb="";

        if(planName[j]!=null){

          for(int k=0; k<i; k++){

            if(planName[j]==planArray[k][1] || planName[j].equals(planArray[k][1])){

               monthIdsb=monthIdsb+planArray[k][0]+";";

            }

            String currUser = (String)request.getAttribute("curruser");

            String userId = "";

            if("1".equals(tawwpMonthPlanVO.getStubFlag())){

               userId = tawwpMonthPlanVO.getUserByStub();

               tStub = tStub + userId + ";";

            }

            else{

               userId = currUser;

            }

          }

            %>

        <!--<tr class="tr_show">

          <td width="20%" class="clsthd2">

          

             <a href="<%=webapp%>/workplan/tawwpexecute/executebatchadds123.do?monthplanid=<%=monthIdsb%>&flag=dailyview&userbystub=<%=tStub%>&date=<%=currentDD%>">

                        

             <%=planName[j]%>          

            </a>

          </td>

        </tr>-->

    <%

        }

        }

    %>

    

    <tr align=center>

      <td width="100%" align="center" valign="middle" class="table_title">

        网元

      </td>

    </tr>

    <%

     String[] planNet = new String[i];

     planNet[0]=planArray[0][2];

     for(int j=1; j<i; j++){

       int z = 0;

       while (planNet[z]!=null){

         if(planArray[j][2]==planNet[z]||planArray[j][2].equals(planNet[z])){

           break;

         }

         else{

           z=z+1;           

           if(planNet[z]!=null){}

           else{

             planNet[z]=planArray[j][2];            

           }

         }

      }

      }     

      for(int j=0; j<i; j++){

        monthIdsb="";



        if(planNet[j]!=null){

          for(int k=0; k<i; k++){

            if(planNet[j]==planArray[k][2] || planNet[j].equals(planArray[k][2])){

               monthIdsb=monthIdsb+planArray[k][0]+";";

            }

            String currUser = (String)request.getAttribute("curruser");

            String userId = "";

            if("1".equals(tawwpMonthPlanVO.getStubFlag())){

               userId = tawwpMonthPlanVO.getUserByStub();

               tStub = tStub + userId + ";";

            }

            else{

               userId = currUser;

            }

          }

            %>

        <tr class="tr_show">

          <td width="20%" class="clsthd2">          

             <a href="<%=webapp%>/workplan/tawwpexecute/executebatchadds123.do?monthplanid=<%=monthIdsb%>&flag=dailyview&userbystub=<%=tStub%>&date=<%=currentDD%>">                       

             <%=planNet[j]%>          

            </a>

          </td>

        </tr>

    <%

        }

        }

        }

    %>

        

  </table>

<!--  正文结束  -->



</body>



