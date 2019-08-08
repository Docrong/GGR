// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PreAllocatedNewMgrImpl.java

package com.boco.eoms.sheetflowline.mgr.impl;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheetflowline.dao.IPreAllocatedNewDao;
import com.boco.eoms.sheetflowline.mgr.IPreAllocatedNewMgr;
import com.boco.eoms.sheetflowline.model.PreAllocatedNew;
import com.boco.eoms.sheetflowline.model.PreAllocatedSepcail;

import java.util.*;

public class PreAllocatedNewMgrImpl
        implements IPreAllocatedNewMgr {

    private IPreAllocatedNewDao preallocatedNewDao;

    public PreAllocatedNewMgrImpl() {
    }

    public void setPreallocatedNewDao(IPreAllocatedNewDao preallocatedNewDao) {
        this.preallocatedNewDao = preallocatedNewDao;
    }

    public void deleteObject(PreAllocatedNew object)
            throws Exception {
        preallocatedNewDao.deleteObject(object);
    }

    public PreAllocatedNew getPreAllocated(String id)
            throws Exception {
        return preallocatedNewDao.getPreAllocated(id);
    }

    public Map listPreAllocated(Integer startIndex, Integer pasesize)
            throws Exception {
        TawSystemUserDao userDao = (TawSystemUserDao) ApplicationContextHolder.getInstance().getBean("tawSystemUserDao");
        List userList = userDao.getNoDelUser();
        Map userMap = new HashMap();
        if (userList != null && userList.size() > 0) {
            for (int u = 0; u < userList.size(); u++) {
                TawSystemUser user = (TawSystemUser) userList.get(u);
                userMap.put(user.getUserid(), user.getUsername());
            }

        }
        Map map = preallocatedNewDao.listPreAllocated(startIndex, pasesize);
        Map returnMap = new HashMap();
        List taskOvertimeList = (List) map.get("taskList");
        if (taskOvertimeList != null && taskOvertimeList.size() > 0) {
            for (int i = 0; i < taskOvertimeList.size(); i++) {
                PreAllocatedNew allocate = (PreAllocatedNew) taskOvertimeList.get(i);
                String specailJHUserId = StaticMethod.nullObject2String(allocate.getSpecailJHUserId());
                String specailJHUserArray[] = specailJHUserId.split(",");
                String specailJHUserName = "";
                if (specailJHUserArray != null && specailJHUserArray.length > 0) {
                    for (int j = 0; j < specailJHUserArray.length; j++) {
                        String userId = specailJHUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        specailJHUserName = specailJHUserName + userName + ",";
                    }

                }
                if (!"".equals(specailJHUserName))
                    allocate.setSpecailJHUserName(specailJHUserName.substring(0, specailJHUserName.length() - 1));
                String monitorJHUserId = StaticMethod.nullObject2String(allocate.getMonitorJHUserId());
                String monitorJHUserArray[] = monitorJHUserId.split(",");
                String monitorJHUserName = "";
                if (monitorJHUserArray != null && monitorJHUserArray.length > 0) {
                    for (int j = 0; j < monitorJHUserArray.length; j++) {
                        String userId = monitorJHUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        monitorJHUserName = monitorJHUserName + userName + ",";
                    }

                }
                if (!"".equals(monitorJHUserName))
                    allocate.setMonitorJHUserName(monitorJHUserName.substring(0, monitorJHUserName.length() - 1));
                String specailCSUserId = StaticMethod.nullObject2String(allocate.getSpecailCSUserId());
                String specailCSUserArray[] = specailCSUserId.split(",");
                String specailCSUserName = "";
                if (specailCSUserArray != null && specailCSUserArray.length > 0) {
                    for (int j = 0; j < specailCSUserArray.length; j++) {
                        String userId = specailCSUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        specailCSUserName = specailCSUserName + userName + ",";
                    }

                }
                if (!"".equals(specailCSUserName))
                    allocate.setSpecailCSUserName(specailCSUserName.substring(0, specailCSUserName.length() - 1));
                String monitorCSUserId = StaticMethod.nullObject2String(allocate.getMonitorCSUserId());
                String monitorCSUserArray[] = monitorCSUserId.split(",");
                String monitorCSUserName = "";
                if (monitorCSUserArray != null && monitorCSUserArray.length > 0) {
                    for (int j = 0; j < monitorCSUserArray.length; j++) {
                        String userId = monitorCSUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        monitorCSUserName = monitorCSUserName + userName + ",";
                    }

                }
                if (!"".equals(monitorCSUserName))
                    allocate.setMonitorCSUserName(monitorCSUserName.substring(0, monitorCSUserName.length() - 1));
                String specailSJUserId = StaticMethod.nullObject2String(allocate.getSpecailSJUserId());
                String specailSJUserArray[] = specailSJUserId.split(",");
                String specailSJUserName = "";
                if (specailSJUserArray != null && specailSJUserArray.length > 0) {
                    for (int j = 0; j < specailSJUserArray.length; j++) {
                        String userId = specailSJUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        specailSJUserName = specailSJUserName + userName + ",";
                    }

                }
                if (!"".equals(specailSJUserName))
                    allocate.setSpecailSJUserName(specailSJUserName.substring(0, specailSJUserName.length() - 1));
                String monitorSJUserId = StaticMethod.nullObject2String(allocate.getMonitorSJUserId());
                String monitorSJUserArray[] = monitorSJUserId.split(",");
                String monitorSJUserName = "";
                if (monitorSJUserArray != null && monitorSJUserArray.length > 0) {
                    for (int j = 0; j < monitorSJUserArray.length; j++) {
                        String userId = monitorSJUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        monitorSJUserName = monitorSJUserName + userName + ",";
                    }

                }
                if (!"".equals(monitorSJUserName))
                    allocate.setMonitorSJUserName(monitorSJUserName.substring(0, monitorSJUserName.length() - 1));
                String specailOtherUserId = StaticMethod.nullObject2String(allocate.getSpecailOtherUserId());
                String specailvUserArray[] = specailOtherUserId.split(",");
                String specailOtherUserName = "";
                if (specailvUserArray != null && specailvUserArray.length > 0) {
                    for (int j = 0; j < specailvUserArray.length; j++) {
                        String userId = specailvUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        specailOtherUserName = specailOtherUserName + userName + ",";
                    }

                }
                if (!"".equals(specailOtherUserName))
                    allocate.setSpecailOtherUserName(specailOtherUserName.substring(0, specailOtherUserName.length() - 1));
                String monitorOtherUserId = StaticMethod.nullObject2String(allocate.getMonitorOtherUserId());
                String monitorOtherUserArray[] = monitorOtherUserId.split(",");
                String monitorOtherUserName = "";
                if (monitorOtherUserArray != null && monitorOtherUserArray.length > 0) {
                    for (int j = 0; j < monitorOtherUserArray.length; j++) {
                        String userId = monitorOtherUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        monitorOtherUserName = monitorOtherUserName + userName + ",";
                    }

                }
                if (!"".equals(monitorOtherUserName))
                    allocate.setMonitorOtherUserName(monitorOtherUserName.substring(0, monitorOtherUserName.length() - 1));
            }

        }
        returnMap.put("taskList", taskOvertimeList);
        returnMap.put("taskTotal", (Integer) map.get("taskTotal"));
        return returnMap;
    }

    public void saveObject(PreAllocatedNew object)
            throws Exception {
        if (object.getId() == null || "".equals(object.getId()))
            preallocatedNewDao.saveObject(object);
        else
            preallocatedNewDao.updateObject(object);
    }

    public void updateObject(PreAllocatedNew object)
            throws Exception {
        preallocatedNewDao.updateObject(object);
    }

    public Integer executeHsql(String hsql)
            throws Exception {
        return preallocatedNewDao.executeHsql(hsql);
    }

    public Map listPreAllocated(Map object, Integer pageIndex, Integer pageSize)
            throws Exception {
        TawSystemUserDao userDao = (TawSystemUserDao) ApplicationContextHolder.getInstance().getBean("tawSystemUserDao");
        List userList = userDao.getNoDelUser();
        Map userMap = new HashMap();
        if (userList != null && userList.size() > 0) {
            for (int u = 0; u < userList.size(); u++) {
                TawSystemUser user = (TawSystemUser) userList.get(u);
                userMap.put(user.getUserid(), user.getUsername());
            }

        }
        Map map = preallocatedNewDao.listPreAllocated(object, pageIndex, pageSize);
        Map returnMap = new HashMap();
        List taskOvertimeList = (List) map.get("taskList");
        if (taskOvertimeList != null && taskOvertimeList.size() > 0) {
            for (int i = 0; i < taskOvertimeList.size(); i++) {
                PreAllocatedNew allocate = (PreAllocatedNew) taskOvertimeList.get(i);
                String specailJHUserId = StaticMethod.nullObject2String(allocate.getSpecailJHUserId());
                String specailJHUserArray[] = specailJHUserId.split(",");
                String specailJHUserName = "";
                if (specailJHUserArray != null && specailJHUserArray.length > 0) {
                    for (int j = 0; j < specailJHUserArray.length; j++) {
                        String userId = specailJHUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        specailJHUserName = specailJHUserName + userName + ",";
                    }

                }
                if (!"".equals(specailJHUserName))
                    allocate.setSpecailJHUserName(specailJHUserName.substring(0, specailJHUserName.length() - 1));
                String monitorJHUserId = StaticMethod.nullObject2String(allocate.getMonitorJHUserId());
                String monitorJHUserArray[] = monitorJHUserId.split(",");
                String monitorJHUserName = "";
                if (monitorJHUserArray != null && monitorJHUserArray.length > 0) {
                    for (int j = 0; j < monitorJHUserArray.length; j++) {
                        String userId = monitorJHUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        monitorJHUserName = monitorJHUserName + userName + ",";
                    }

                }
                if (!"".equals(monitorJHUserName))
                    allocate.setMonitorJHUserName(monitorJHUserName.substring(0, monitorJHUserName.length() - 1));
                String specailCSUserId = StaticMethod.nullObject2String(allocate.getSpecailCSUserId());
                String specailCSUserArray[] = specailCSUserId.split(",");
                String specailCSUserName = "";
                if (specailCSUserArray != null && specailCSUserArray.length > 0) {
                    for (int j = 0; j < specailCSUserArray.length; j++) {
                        String userId = specailCSUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        specailCSUserName = specailCSUserName + userName + ",";
                    }

                }
                if (!"".equals(specailCSUserName))
                    allocate.setSpecailCSUserName(specailCSUserName.substring(0, specailCSUserName.length() - 1));
                String monitorCSUserId = StaticMethod.nullObject2String(allocate.getMonitorCSUserId());
                String monitorCSUserArray[] = monitorCSUserId.split(",");
                String monitorCSUserName = "";
                if (monitorCSUserArray != null && monitorCSUserArray.length > 0) {
                    for (int j = 0; j < monitorCSUserArray.length; j++) {
                        String userId = monitorCSUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        monitorCSUserName = monitorCSUserName + userName + ",";
                    }

                }
                if (!"".equals(monitorCSUserName))
                    allocate.setMonitorCSUserName(monitorCSUserName.substring(0, monitorCSUserName.length() - 1));
                String specailSJUserId = StaticMethod.nullObject2String(allocate.getSpecailSJUserId());
                String specailSJUserArray[] = specailSJUserId.split(",");
                String specailSJUserName = "";
                if (specailSJUserArray != null && specailSJUserArray.length > 0) {
                    for (int j = 0; j < specailSJUserArray.length; j++) {
                        String userId = specailSJUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        specailSJUserName = specailSJUserName + userName + ",";
                    }

                }
                if (!"".equals(specailSJUserName))
                    allocate.setSpecailSJUserName(specailSJUserName.substring(0, specailSJUserName.length() - 1));
                String monitorSJUserId = StaticMethod.nullObject2String(allocate.getMonitorSJUserId());
                String monitorSJUserArray[] = monitorSJUserId.split(",");
                String monitorSJUserName = "";
                if (monitorSJUserArray != null && monitorSJUserArray.length > 0) {
                    for (int j = 0; j < monitorSJUserArray.length; j++) {
                        String userId = monitorSJUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        monitorSJUserName = monitorSJUserName + userName + ",";
                    }

                }
                if (!"".equals(monitorSJUserName))
                    allocate.setMonitorSJUserName(monitorSJUserName.substring(0, monitorSJUserName.length() - 1));
                String specailOtherUserId = StaticMethod.nullObject2String(allocate.getSpecailOtherUserId());
                String specailvUserArray[] = specailOtherUserId.split(",");
                String specailOtherUserName = "";
                if (specailvUserArray != null && specailvUserArray.length > 0) {
                    for (int j = 0; j < specailvUserArray.length; j++) {
                        String userId = specailvUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        specailOtherUserName = specailOtherUserName + userName + ",";
                    }

                }
                if (!"".equals(specailOtherUserName))
                    allocate.setSpecailOtherUserName(specailOtherUserName.substring(0, specailOtherUserName.length() - 1));
                String monitorOtherUserId = StaticMethod.nullObject2String(allocate.getMonitorOtherUserId());
                String monitorOtherUserArray[] = monitorOtherUserId.split(",");
                String monitorOtherUserName = "";
                if (monitorOtherUserArray != null && monitorOtherUserArray.length > 0) {
                    for (int j = 0; j < monitorOtherUserArray.length; j++) {
                        String userId = monitorOtherUserArray[j];
                        String userName = StaticMethod.nullObject2String(userMap.get(userId));
                        monitorOtherUserName = monitorOtherUserName + userName + ",";
                    }

                }
                if (!"".equals(monitorOtherUserName))
                    allocate.setMonitorOtherUserName(monitorOtherUserName.substring(0, monitorOtherUserName.length() - 1));
            }

        }
        returnMap.put("taskList", taskOvertimeList);
        returnMap.put("taskTotal", (Integer) map.get("taskTotal"));
        return returnMap;
    }

    public HashMap searchPreUser(String mainNetSortOne, String mainNetSortTwo, String mainFaultResponseLevel, String currentTime)
            throws Exception {
        HashMap resultMap = new HashMap();
        List specailNewList = new ArrayList();
        List specailList = preallocatedNewDao.searchSpecal(mainNetSortOne, mainNetSortTwo);
        for (int i = 0; specailList != null && specailList.size() > 0 && i < specailList.size(); i++) {
            PreAllocatedSepcail sepcail = (PreAllocatedSepcail) specailList.get(i);
            String netTypeTwo = StaticMethod.nullObject2String(sepcail.getNetTypeTwo());
            if (!"".equals(netTypeTwo) && netTypeTwo.indexOf(mainNetSortTwo) >= 0)
                specailNewList.add(sepcail);
            else if ("".equals(netTypeTwo))
                specailNewList.add(sepcail);
        }

        List preResultList = preallocatedNewDao.search(mainFaultResponseLevel, currentTime);
        resultMap.put("specailList", specailNewList);
        resultMap.put("preResultList", preResultList);
        return resultMap;
    }

    public List getLists(String hsql)
            throws Exception {
        return preallocatedNewDao.getLists(hsql);
    }

    public Map listNetPreAllocated(Integer startIndex, Integer length)
            throws Exception {
        IDownLoadSheetAccessoriesService mgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        Map dictMap = new HashMap();
        int netTypeRoot = StaticMethod.nullObject2int(XmlManage.getFile("/com/boco/eoms/sheetflowline/config/sheetflowline-util.xml").getProperty("netTypeRoot"));
        String sql = "select dictid as dictId,dictname as dictName from taw_system_dicttype where dictid like '" + netTypeRoot + "%' and (length(dictid)=9 or length(dictid)=11)";
        List dictResultList = mgr.getSheetAccessoriesList(sql);
        for (int i = 0; dictResultList != null && dictResultList.size() > 0 && i < dictResultList.size(); i++) {
            Map resultMap = (Map) dictResultList.get(i);
            String dictId = StaticMethod.nullObject2String(resultMap.get("dictId"));
            String dictName = StaticMethod.nullObject2String(resultMap.get("dictName"));
            dictMap.put(dictId, dictName);
        }

        Map map = preallocatedNewDao.listNetPreAllocated(startIndex, length);
        Map returnMap = new HashMap();
        List netPreAllocatedList = (List) map.get("taskList");
        if (netPreAllocatedList != null && netPreAllocatedList.size() > 0) {
            for (int i = 0; i < netPreAllocatedList.size(); i++) {
                PreAllocatedSepcail specail = (PreAllocatedSepcail) netPreAllocatedList.get(i);
                String netTypeOne = specail.getNetTypeOne();
                String netTypeTwo = specail.getNetTypeTwo();
                specail.setNetTypeOneName(StaticMethod.nullObject2String(dictMap.get(netTypeOne)));
                if (netTypeTwo != null && !"".equals(netTypeTwo)) {
                    String netTypeTwoName = "";
                    String netTypeTwos[] = netTypeTwo.split(",");
                    for (int k = 0; k < netTypeTwos.length; k++)
                        netTypeTwoName = netTypeTwoName + StaticMethod.nullObject2String(dictMap.get(netTypeTwos[k])) + ",";

                    if (!"".equals(netTypeTwoName))
                        netTypeTwoName = netTypeTwoName.substring(0, netTypeTwoName.length() - 1);
                    specail.setNetTypeTwoName(netTypeTwoName);
                }
            }

        }
        returnMap.put("taskList", netPreAllocatedList);
        returnMap.put("taskTotal", (Integer) map.get("taskTotal"));
        return returnMap;
    }

    public void saveNetObject(PreAllocatedSepcail object)
            throws Exception {
        preallocatedNewDao.saveNetObject(object);
    }

    public boolean ifExistSameRule(String specailType, String netTypeOne, String netTypeTwo)
            throws Exception {
        boolean existFlag = false;
        List ruleList = preallocatedNewDao.searchNetRule(specailType, netTypeOne, netTypeTwo);
        if (ruleList != null && ruleList.size() > 0)
            existFlag = true;
        return existFlag;
    }

    public PreAllocatedSepcail getNetPreAllocated(String id)
            throws Exception {
        return preallocatedNewDao.getNetPreAllocated(id);
    }
}
