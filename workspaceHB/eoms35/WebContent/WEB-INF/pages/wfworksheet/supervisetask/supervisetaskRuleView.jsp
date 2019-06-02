<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>



<table width="500" class="formTable">
	<caption>督办规则编辑</caption>
<input type="hidden" id="id" name="id" value="${object.id }" />
	<tr>
		<td>
			专业*
	    </td>
		<td>
	    	<eoms:comboBox name="major" id="major" initDicId="1010107" defaultValue="${object.major }">
	    		</eoms:comboBox>
	    </td>
		
	</tr>

	<tr>
		<td class="label">网络分类一级*</td>
		<td><eoms:comboBox name="mainNetSortOne" id="mainNetSortOne"
			sub="mainNetSortTwo" initDicId="1010104" defaultValue="${object.mainNetSortOne }" /></td>
		<td class="label">网络分类二级</td>
		<td><eoms:comboBox name="mainNetSortTwo" id="mainNetSortTwo" initDicId="${object.mainNetSortOne }"
			sub="mainNetSortThree" defaultValue="${object.mainNetSortTwo }" /></td>
	</tr>
	<tr>
		<td class="label">网络分类三级</td>
		<td><eoms:comboBox name="mainNetSortThree" id="mainNetSortThree" initDicId="${object.mainNetSortTwo }"
			defaultValue="${object.mainNetSortThree }" />
		</td>
		

	</tr>
	<tr>
		
	</tr>
	<tr>
		<td class="label">地市*</td>
		<td id="tdToDeptId"><select id="toDeptId" name="toDeptId"
			onchange="initCountry(this.value);showNoticeUserId();">

		</select></td>
		<td class="label">区县</td>
		<td id="tdMainFaultGenerantCitySubCode"><select
			id="mainFaultGenerantCitySubCode" name="mainFaultGenerantCitySubCode" onchange="showNoticeUserId();">

		</select></td>

	</tr>
	<tr>
		<td class="label">挂牌类型</td>
		<td>
			<input type="text" name=listedRegulationType id="listedRegulationType"
				class="text" value="${object.listedRegulationType }" />
		</td>
		<td class="label">挂牌周期</td>
		<td>
			<input type="text" id="listedRegulationCycle" name="listedRegulationCycle" value="${object.listedRegulationCycle }">
		</td>

	</tr>
	<tr>
		<td class="label">督办方式</td>
		<td class="content">
			<input type="checkbox" name="superviseType" value="IVR" <c:if test="${fn:contains(object.superviseType,'IVR') }">checked="checked"</c:if> >IVR
			<input type="checkbox" name="superviseType" value="SMS" <c:if test="${fn:contains(object.superviseType,'SMS') }">checked="checked"</c:if>>短信
		</td>
	</tr>

	<table class="table" width="100%" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th class="label">督办级别</th>
                <th class="label">受理超时(天)</th>
                <th class="label">处理超时(天)</th>
                <th class="label">督办对象</th>
            </tr>
            </thead>
            <tr>
            	<td class="label">1</td>
                <td>
					<input type="text" name="acceptOverTime1" id="acceptOverTime1" class="text" value="${object.acceptOverTime1 }"/>(0~9)
				</td>
                <td>
					<input type="text" name="dealOverTime1" id="dealOverTime1" class="text" value="${object.dealOverTime1 }">(0~9)
				</td>
                <td id="n1"></td>
            </tr>
            <tr>
            	<td class="label">2</td>
                 <td>
					<input type="text" name="acceptOverTime2" id="acceptOverTime2" class="text" value="${object.acceptOverTime2 }"/>(0~9)
				</td>
                <td>
					<input type="text" name="dealOverTime2" id="dealOverTime2" class="text" value="${object.dealOverTime2 }">(0~9)
				</td>
                <td id="n2"></td>
            </tr>
            <tr>
            	<td class="label">3</td>
                 <td>
					<input type="text" name="acceptOverTime3" id="acceptOverTime3" class="text" value="${object.acceptOverTime3 }"/>(0~9)
				</td>
                <td>
					<input type="text" name="dealOverTime3" id="dealOverTime3" class="text" value="${object.dealOverTime3 }">(0~9)
				</td>
                <td id="n3"></td>
            </tr>
            <tr>
            	<td class="label">4</td>
                 <td>
					<input type="text" name="acceptOverTime4" id="acceptOverTime4" class="text" value="${object.acceptOverTime4 }"/>(0~9)
				</td>
                <td>
					<input type="text" name="dealOverTime4" id="dealOverTime4" class="text" value="${object.dealOverTime4 }">(0~9)
				</td>
                <td id="n4"></td>
            </tr>

        </table>


</table>


















<%@ include file="/common/footer_eoms.jsp"%>