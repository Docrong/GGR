package com.boco.eoms.sheet.netownershipwireless.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.netownershipwireless.job.dao.NetOwnershipwirelessSyncJDBC;
import com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipwireless;
import com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipAreawireless;
import com.boco.eoms.sheet.netownershipwireless.service.INetOwnershipwirelessManager;

/**
 * 网元同步
 *
 * @author weichao
 */
public class NetOwnershipwirelessSyncSchedule implements Job {

    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        boolean ifExceuteJob = NetOwnershipwirelessSyncJobStatic.isIfExcuteJob();
        if (!ifExceuteJob) {
            BocoLog.info(NetOwnershipwirelessSyncSchedule.class, "NetOwnershipwirelessSyncSchedule Job is running wait for next Schedule");
        } else {
            INetOwnershipwirelessManager mgr = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
            try {
                NetOwnershipwirelessSyncJDBC result = new NetOwnershipwirelessSyncJDBC();
                List list = result.query("netSync");
                Date d = new Date();
                List county = new ArrayList();
                Map countyMap = new HashMap();
                for (int i = 0; i < list.size(); i++) {
                    Map amap = (Map) list.get(i);
                    NetOwnershipwireless net = new NetOwnershipwireless();
                    String countys = StaticMethod.nullObject2String(amap.get("county"));
                    String citys = StaticMethod.nullObject2String(amap.get("city"));
                    net.setNetId(StaticMethod.nullObject2String(amap.get("netId")));
                    net.setNetName(StaticMethod.nullObject2String(amap.get("netName")));
                    net.setNetType(StaticMethod.nullObject2String(amap.get("netType")));
                    net.setCounty(countys);
                    net.setCountyId(StaticMethod.nullObject2String(amap.get("countyId")));
                    net.setCity(citys);
                    net.setCityId(StaticMethod.nullObject2String(amap.get("cityId")));
                    net.setSaveTime(d);
                    net.setIfAutotran("0");//默认为不自动移交
                    net.setDeleted(Integer.valueOf(0));
                    net.setNetNameByEdis(StaticMethod.nullObject2String(amap.get("netNameByEdis")));
                    //关联对应的区县和地市信息
                    if (county.contains(countys)) {
                        NetOwnershipAreawireless area = (NetOwnershipAreawireless) countyMap.get(countys);
                        net.setCityId(area.getCityId());
                        net.setCountyId(area.getCountyId());
                        net.setEomsCity(area.getEomsCity());
                        net.setEomsCityId(area.getEomsCityId());
                        net.setEomsCounty(area.getEomsCounty());
                        net.setEomsCountyId(area.getEomsCountyId());
                        mgr.saveOrUpdate(net);
                    } else {
                        if (null != countys || !"".equals(countys)) {//判断countys是否为空
                            List l = mgr.getAreabycountyId(" and county='" + countys + "'");//根据名字去表里查询对应的地域信息
                            if (l.size() > 0) {
                                NetOwnershipAreawireless areas = (NetOwnershipAreawireless) l.get(0);
                                county.add(countys);
                                countyMap.put(countys, areas);
                                net.setCityId(areas.getCityId());
                                net.setCountyId(areas.getCountyId());
                                net.setEomsCity(areas.getEomsCity());
                                net.setEomsCityId(areas.getEomsCityId());
                                net.setEomsCounty(areas.getEomsCounty());
                                net.setEomsCountyId(areas.getEomsCountyId());
                                mgr.saveOrUpdate(net);
                            } else {
                                System.out.println("网元同步的区县信息不存在-->" + countys);
                            }

                        } else if (null != citys || !"".equals(citys)) {//地市是否为空
                            List l = mgr.getAreabycountyId(" and city='" + citys + "'");//根据名字去表里查询对应的地域信息
                            if (l.size() > 0) {
                                NetOwnershipAreawireless areas = (NetOwnershipAreawireless) l.get(0);
                                county.add(countys);
                                countyMap.put(countys, areas);
                                net.setCityId(areas.getCityId());
                                net.setCounty(areas.getCity());
                                net.setCountyId(areas.getCityId());
                                net.setEomsCity(areas.getEomsCity());
                                net.setEomsCityId(areas.getEomsCityId());
                                net.setEomsCounty(areas.getEomsCity());
                                net.setEomsCountyId(areas.getEomsCityId());
                                mgr.saveOrUpdate(net);
                            } else {
                                System.out.println("网元同步的地市信息不存在-->" + citys);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                BocoLog.info(NetOwnershipwirelessSyncSchedule.class, "同步网元保存失败");
                NetOwnershipwirelessSyncJobStatic.setIfExcuteJob(true);
            }

            BocoLog.info(NetOwnershipwirelessSyncSchedule.class, "NetOwnershipwirelessSyncSchedule Job is running over");
            NetOwnershipwirelessSyncJobStatic.setIfExcuteJob(true);
        }
    }

}
