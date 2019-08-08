/*
 * Created on 2007-11-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.util;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.commons.fileconfig.service.impl.ParseXmlService;

/**
 * @author IBM
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RoleMapSchema {
    private static RoleMappings nodes = null;
    private static RoleMapSchema roleMapSchema = null;

    public static RoleMapSchema getInstance() throws Exception {
        if (roleMapSchema == null) {
            roleMapSchema = new RoleMapSchema();
            try {
                loadXml();
            } catch (Exception e) {
                throw e;
            }
        }
        return roleMapSchema;
    }

    private static void loadXml() throws Exception {
        try {
            nodes = (RoleMappings) ParseXmlService
                    .create()
                    .xml2object(RoleMappings.class, "classpath:com/boco/eoms/commons/system/role/util/RoleMapping.xml", "classpath:config/roleConfig.xml", false);
            int i = 0;

        } catch (ParseXMLException e) {
            e.printStackTrace();
            throw new Exception("读取配置文件'config/roleConfig.xml'出错");
        }
    }

    /**
     * 根据模块ID，获取对应的XML文件路径
     *
     * @param modelId
     * @return
     */
    public String getFilePathByModelId(String modelId) {
//		if(nodes!=null){
//			RoleModel[] mapping = nodes.getRoleModel();
//			for (int i=0;i<mapping.length;i++) {
//				RoleModel rm = (RoleModel)mapping[i];
//				if(rm.getModelId()==modelId)
//					return rm.getFilePath();
//			}
//		}
        return null;
    }

    /**
     * 根据模块ID，获取RoleFilter数组
     *
     * @param modelId
     * @return
     */
    public List getRoleMappingListById(String modelId) {
        if (nodes != null) {
            List roleList = nodes.getRoleModel();
            for (int i = 0; i < roleList.size(); i++) {
                RoleModel rm = (RoleModel) roleList.get(i);
                if (rm.getModelId().equals(modelId))
                    return rm.getRoleFilter();
            }
        }
        return null;
    }

    /**
     * 获取工单各个区分度对应的HTML控件ID
     *
     * @param flowId  流程名
     * @param sheetId 工单名
     * @return <String>
     */
    public HashMap getStyleIDsBySheet(String sheetId) {
        HashMap resultMap = new HashMap();

        if (nodes != null) {
            boolean isfind = false;
            List roleList = nodes.getRoleModel();
            for (int i = 0; i < roleList.size(); i++) {
                RoleModel rm = (RoleModel) roleList.get(i);
                List roleFilters = rm.getRoleFilter();
                for (int j = 0; roleFilters != null && j < roleFilters.size(); j++) {
                    RoleFilter filter = (RoleFilter) roleFilters.get(j);
                    List sheetInfos = filter.getSheetInfo();
                    if (sheetInfos == null)
                        continue;
                    for (int k = 0; k < sheetInfos.size(); k++) {
                        SheetInfo si = (SheetInfo) sheetInfos.get(k);
                        if (si.getName().equals(sheetId)) {
                            resultMap.put(filter.getBusinessName(), si.getStyleId());
                            isfind = true;
                            break;
                        }

                    }
                }
                if (isfind)
                    break;
            }
        }
        return resultMap;
    }

    public List getModelLists() {
        List modelList = null;
        if (nodes != null) {
            modelList = nodes.getRoleModel();
        }

        return modelList;
    }

    /**
     * 根绝模块编号获取DICTID
     *
     * @param modelId      模块名称
     * @param businessName 业务字段名称
     * @return
     */
    public String getDictIdByModelId(String modelId, String businessName) {
        if (nodes != null) {
            List roleList = nodes.getRoleModel();
            for (int i = 0; i < roleList.size(); i++) {
                RoleModel rm = (RoleModel) roleList.get(i);
                if (rm.getModelId().equals(modelId)) {
                    List rfList = rm.getRoleFilter();
                    for (int j = 0; j < rfList.size(); j++) {
                        RoleFilter rf = (RoleFilter) rfList.get(j);
                        if (rf.getBusinessName().equals(businessName))
                            return rf.getDictId();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取工单对应的流程ID
     *
     * @param flowId  流程名
     * @param sheetId 工单名
     * @return <String>
     */
    public String getModelIdBySheet(String sheetId) {

        if (nodes != null) {
            boolean isfind = false;
            List roleList = nodes.getRoleModel();
            for (int i = 0; i < roleList.size(); i++) {
                RoleModel rm = (RoleModel) roleList.get(i);
                List roleFilters = rm.getRoleFilter();
                for (int j = 0; j < roleFilters.size(); j++) {
                    RoleFilter filter = (RoleFilter) roleFilters.get(j);
                    List sheetInfos = filter.getSheetInfo();
                    if (sheetInfos == null)
                        continue;
                    for (int k = 0; k < sheetInfos.size(); k++) {
                        SheetInfo si = (SheetInfo) sheetInfos.get(k);
                        if (si.getName().equals(sheetId)) {
                            return rm.getModelId();
                        }

                    }
                }
            }
        }
        return null;
    }
}
