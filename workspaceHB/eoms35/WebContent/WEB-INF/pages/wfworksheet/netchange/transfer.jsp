<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
    var v = new eoms.form.Validation({form: 'theform'});
</script>

<div id="sheetform">
    <html:form action="/netchange.do?method=performTransferWorkItem" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/netchange/baseinputlinkhtmlnew.jsp" %>
        <br/>
        <input type="hidden" name="${sheetPageName}beanId" id="${sheetPageName}beanId" value="iNetChangeMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName" id="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.netchange.model.NetChangeMain"/> <!--mainè¡¨Modelå¯¹è±¡ç±»å-->
        <input type="hidden" name="${sheetPageName}linkClassName" id="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.netchange.model.NetChangeLink"/> <!--linkè¡¨Modelå¯¹è±¡ç±»å-->
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>

        <table class="listTable">
            <!-- <tr>
		     <td class="label">
		       <bean:message bundle="sheet" key="linkForm.acceptLimit"/>
		     </td>
            <td class="content">
                 <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
                   value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" 
                    id="${sheetPageName}nodeAcceptLimit" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于完成时限")}',allowBlank:true"/>
		    </td>
		    <td class="label">
		      <bean:message bundle="sheet" key="linkForm.completeLimit"/>
            </td>
            <td class="content">
                 <input class="text" onclick="popUpCalendar(this, this)" type="text" name="${sheetPageName}nodeCompleteLimit" readonly="readonly" value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:true"/>
          
		    </td>
		  </tr> -->
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.transmitReason"/>
                </td>
                <td colspan="3">
                    <textarea class="textarea max" name="${sheetPageName}transferReason"
                              id="${sheetPageName}transferReason"
                              alt="width:'500px'">${sheetLink.transferReason}</textarea>
                </td>
            </tr>

        </table>
        <fieldset>
            <legend>
                    ${eoms:a2u('请选择派发对象')}
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test" config="{{returnJSON:true}"
                              category="[{id:'dealPerformer',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择派发人')}'}]"
                              panels="[
                   {text:'${eoms:a2u('可选人员')}',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersBySubRole&subRoleId=${sheetLink.operateRoleId}'},
                   {text:'${eoms:a2u('备选子角色')}',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersByRole&roleId=${bigRoleId}&subRoleId=${sheetLink.operateRoleId}'}
                 ]"
                />
            </div>
        </fieldset>


        <div class="form-btns">
            <input type="submit" class="submit" name="method.save" id="method.save"
                   value="<bean:message bundle='sheet' key='button.done'/>">
            <!-- <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/netchange/netchange.do?method=performSaveInfo'" value="<bean:message bundle='sheet' key='button.save'/>" />
	    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/netchange/netchange.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" /> -->
            <input type="submit" class="submit"
                   onclick="v.passing=true;this.form.action='${app}/sheet/netchange/netchange.do?method=showListsendundo'"
                   value="<bean:message bundle='sheet' key='button.back'/>"/>


        </div>
    </html:form>

</div>

