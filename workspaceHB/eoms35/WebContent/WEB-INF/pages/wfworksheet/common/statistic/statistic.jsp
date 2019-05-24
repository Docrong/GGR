<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<style type="text/css">
.x-form-column{width:320px}
</style>
<script type="text/javascript" src="${app}/scripts/search/checkform.js"></script>
<script type="text/javascript">
function initPage(){
	//init Form validation and styles
	valider({form:'theform',vbtn:'method.save'});	    
	    		
	    		
}
window.onload=function(){initPage();}
</script>
<html:form action="/sheet.do?method=performStatistic" styleId="theform">




          <table border="0"  cellspacing="1" cellpadding="1" width="60%" align="center" class="table_show" >



            <tr class="tr_show">
              <td noWrap width="20%"><bean:message bundle="sheet" key="query.beginTime" />
              </td>
              <td width="80%" colspan=2>

            	<select name="kpiDefineName" >
                 <option value="-1">--select--</option> 
      	          <option value="kpi1">kpi1</option>
      	          <option value="2">2</option> 
               </select>       		
              </td>
            </tr>


            <tr class="tr_show">
              <td noWrap width="20%"><bean:message bundle="sheet" key="query.beginTime" />
              </td>
              <td width="80%" colspan=2>
	            <input type="text" name="beginTime" id="beginTime"  readonly="readonly" alt="timer:true"/>
              </td>
            </tr>

            <tr class="tr_show">
              <td noWrap width="20%"><bean:message bundle="sheet" key="query.endTime" />
              </td>
              <td width="80%" colspan=2>
	            <input type="text" name="endTime" id="endTime"  readonly="readonly" alt="timer:true"/>
              </td>
            </tr>
         

          </table>









  <p />	
  <!-- buttons -->
	<div class="form-btns">
     <html:button styleClass="btn" property="method.save" styleId="method.save">
				<fmt:message key="button.done"/>
     </html:button>
	</div>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
