package com.boco.eoms.sheet.offlineData.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.offlineData.dao.IOfflineDataMainDAO;
import com.boco.eoms.sheet.offlineData.service.IOfflineDataMainManager;

/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 *
 * @author liuyang
 * @version 3.5
 */

public class OfflineDataMainManagerImpl extends MainService implements IOfflineDataMainManager {
    /**
     *
     */
    public HashMap getListByHqlBy(final Integer curPage, final Integer pageSize, final String hql, final String condictions, final String modelname) throws Exception {
        return ((IOfflineDataMainDAO) this.getMainDAO()).getListByHqlBy(curPage, pageSize, hql, condictions, modelname);
    }

    /**
     * 根据类名、更新的值和id更新动态表中的值
     */
    public void updateDynamicModel(String modelname, String condiction, String condictionValue, String id) throws Exception {
        ((IOfflineDataMainDAO) this.getMainDAO()).updateDynamicModel(modelname, condiction, condictionValue, id);

    }

    /**
     * 根据类名，工单主键，查询条件和查询条件的值释放资源模块中已经选择的资源
     */
    public void updateResourceClear(String modelname, String sheetKey, String contidion, String condictionValue) throws Exception {
        ((IOfflineDataMainDAO) this.getMainDAO()).updateResourceClear(modelname, sheetKey, contidion, condictionValue);
    }

    /**
     * 根据类名，工单主键，选中的记录的id将工单中选中的资源在资源模块中更新为占用
     */
    public void updateResourceOccupied(String modelname, String sheetKey, String id) throws Exception {
        ((IOfflineDataMainDAO) this.getMainDAO()).updateResourceOccupied(modelname, sheetKey, id);
    }

    /**
     * 解析字典值，返回每个字典值的大类型和小类型的组合的list
     */
    public List analyzeDictionary(String mainResourceType, String mainHLRResource, String mainMSCResource) {
        List list = new ArrayList();
        if (!"".equals(mainHLRResource)) {
            String[] hLRResource = mainHLRResource.split(",");
            for (int i = 0; i < hLRResource.length; i++) {
                String key = mainResourceType + hLRResource[i];
                list.add(key);
            }
        } else if (!"".equals(mainMSCResource)) {
            String[] mscResource = mainMSCResource.split(",");
            for (int i = 0; i < mscResource.length; i++) {
                String key = mainResourceType + mscResource[i];
                list.add(key);
            }
        }
        return list;
    }

    /**
     * 根据拼写的hql去查询
     */
    public List getListByHql(final String hql) throws Exception {
        return this.getMainDAO().getMainListBySql(hql);
    }

    /**
     * 根据页面的参数拼写查询的语句并得到返回的HashMap
     */
    public HashMap getListByCondiction(final Integer curPage, final Integer pageSize, Map condiction, String modelname) throws Exception {
        //页面属性
        Iterator names = condiction.keySet().iterator();
        String hql = "";
        while (names.hasNext()) {
            String name = StaticMethod.nullObject2String(names.next());
            String nameValue = StaticMethod.nullObject2String(condiction.get(name));
            if (name.equals("actionForword") || name.equals("modelname") || name.equals("method")) {
                continue;
            } else if (!"".equals(nameValue)) {
                if (hql.length() > 0) {
                    hql = hql + " and ";
                }
                hql = hql + " " + name + " like'%" + nameValue + "%'";
            }
        }
        String hqls = "";
        String condictions = "";
        if ("".equals(hql)) {
            hqls = "from " + modelname + " where isOccupation = 0";
            condictions = " where isOccupation = 0";
        } else {
            hqls = "from " + modelname + " where (" + hql + ") and isOccupation = 0";
            condictions = " where (" + hql + ") and isOccupation = 0";
        }
        //获得查询的记录
        Map map = new HashMap();
        map = ((IOfflineDataMainDAO) this.getMainDAO()).getListByHqlBy(curPage, pageSize, hqls, condictions, modelname);
        return (HashMap) map;
    }

    /**
     * 根据拼写的hql去更新
     */
    public void updateModelByHql(String hql) throws Exception {
        ((IOfflineDataMainDAO) this.getMainDAO()).updateModelByHql(hql);
    }


}