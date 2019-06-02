package com.boco.eoms.sheet.interfaceBase.service;

import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;

public interface IWfInterfaceOperateManager {
	public boolean sendData(WfInterfaceInfo info);
	public boolean sendFlowInterfaceData(BaseMain main,BaseLink link,String interfaceType,String methodType,String serviceType);
	public boolean dealUnReadyData(BaseMain main, BaseLink link,String interfaceType,String methodType,String serviceType);
	
	
	public boolean sendData(WfInterfaceInfo info,BaseLink link);
}
