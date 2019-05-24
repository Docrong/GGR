package com.boco.eoms.workbench.commission.mgr.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.workbench.commission.dao.ITawWorkbenchCommissionPresetDao;
import com.boco.eoms.workbench.commission.mgr.ITawWorkbenchCommissionPresetMgr;
import com.boco.eoms.workbench.commission.model.TawWorkbenchCommissionPreset;

public class TawWorkbenchCommissionPresetMgrImpl implements
		ITawWorkbenchCommissionPresetMgr {

	private ITawWorkbenchCommissionPresetDao tawWorkbenchCommissionPresetDao;

	public ITawWorkbenchCommissionPresetDao getTawWorkbenchCommissionPresetDao() {
		return tawWorkbenchCommissionPresetDao;
	}

	public void setTawWorkbenchCommissionPresetDao(
			ITawWorkbenchCommissionPresetDao tawWorkbenchCommissionPresetDao) {
		this.tawWorkbenchCommissionPresetDao = tawWorkbenchCommissionPresetDao;
	}

	public TawWorkbenchCommissionPreset getCommissionPreset(String id) {
		return tawWorkbenchCommissionPresetDao.getCommissionPreset(id);
	}

	public List listCommissionPresets(String userId, String moduleId) {
		return tawWorkbenchCommissionPresetDao.listCommissionPresets(userId,
				moduleId);
	}

	public void saveCommissionPrese(
			TawWorkbenchCommissionPreset commissionPreset) {
		tawWorkbenchCommissionPresetDao.saveCommissionPrese(commissionPreset);
	}

	public JSONArray listCommissionPresetsJSON(String userId, String moduleId) {
		JSONArray json = new JSONArray();
		List list = tawWorkbenchCommissionPresetDao.listCommissionPresets(
				userId, moduleId);
		for (int i = 0; i < list.size(); i ++) {
			TawWorkbenchCommissionPreset preset = (TawWorkbenchCommissionPreset) list.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("id", preset.getTrustorId());
			jitem.put("text", preset.getTrustorName());
			jitem.put("leaf", true);
			jitem.put("checked", false);
			json.put(jitem);
		}
		return json;
	}
	
	public void delCommissionPresets(String userId, String moduleId) {
		tawWorkbenchCommissionPresetDao.delCommissionPresets(userId, moduleId);
	}

}
