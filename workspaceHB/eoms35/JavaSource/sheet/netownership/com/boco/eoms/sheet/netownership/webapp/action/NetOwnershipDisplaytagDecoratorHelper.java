package com.boco.eoms.sheet.netownership.webapp.action;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.webapp.action.QueryListDisplaytagDecoratorHelper;
import com.boco.eoms.sheet.netownership.model.NetOwnership;
/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-22 20:25:13
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class NetOwnershipDisplaytagDecoratorHelper extends QueryListDisplaytagDecoratorHelper {
	
	public String getId() {
		NetOwnership main = (NetOwnership) getCurrentRowObject();
		String inputStr = "";
		inputStr = "<input name=\"batchIds\" type=\"checkbox\" onclick=\"selectedSelf(this)\" value=\"" + main.getId() + "\" id=\"" + main.getId() + "\"/>";		
		return inputStr; 
	}
	
	public String getIfAutotran() {
		NetOwnership main = (NetOwnership) getCurrentRowObject();
		String inputStr = "";
		if("1".equals(main.getIfAutotran())){
			inputStr = "是";
		}else if("0".equals(main.getIfAutotran())){
			inputStr = "否";
		}
		return inputStr; 
	}
	
	public String getDeleted(){
		NetOwnership main = (NetOwnership) getCurrentRowObject();
		return "<input type='button' class='btn' value='编辑' name='button' onclick='editx(\""+ main.getId()+"\");'/>&nbsp;";
//		"<input type='button' class='btn' value='删除' name='button' onclick='deletex(\""+ main.getId()+"\");'/>";       	
	}
	
	public String getTeamRoleId(){
		NetOwnership main = (NetOwnership) getCurrentRowObject();
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
		if("".equals(main.getTeamRoleId())||null == main.getTeamRoleId()){
			return "";
		}else{
			TawSystemSubRole role = mgr.getTawSystemSubRole(main.getTeamRoleId());
			return role.getSubRoleName();
		}
	}
	
	public String getCenterId(){
		NetOwnership main = (NetOwnership) getCurrentRowObject();
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");	
		if("".equals(main.getCenterId())||null == main.getCenterId()){
			return "";
		}else{
			TawSystemSubRole role = mgr.getTawSystemSubRole(main.getCenterId());
			return role.getSubRoleName();
		}
		
	}
	
	public String getCcObject(){
		NetOwnership main = (NetOwnership) getCurrentRowObject();
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");	
		if("".equals(main.getCcObject())||null == main.getCcObject()){
			return "";
		}else{
			TawSystemSubRole role = mgr.getTawSystemSubRole(main.getCcObject());
			return role.getSubRoleName();
		}
		
	}
	public String getZhuanye()
	{
		NetOwnership main = (NetOwnership)getCurrentRowObject();
		ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
		if ("".equals(main.getZhuanye()) || main.getZhuanye() == null)
			return "";
		else
			return mgr.getDictByDictId(main.getZhuanye()).getDictName();
	}
}
