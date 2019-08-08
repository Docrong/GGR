package com.boco.eoms.sheet.connectTest.bo;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;


public class ConnectTestBo {

    static private ConnectTestBo instance;

    static synchronized public ConnectTestBo getInstance() {
        if (instance == null) {
            instance = new ConnectTestBo();
        }
        return instance;
    }

    private ConnectTestBo() {

    }


    public void connectInfo() {
        try {
            System.out.println("----begin---connect--");
            String timeDate = getTime();
            System.out.println("----connectTime----" + timeDate);
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public String getTime() {

        String sysdate = "";
        String sql = "select sysdate from dual";
        try {
            IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            List countList = services.getSheetAccessoriesList(sql);
            if ((countList != null) && (countList.size() > 0)) {
                Map countMap = (Map) countList.get(0);
                sysdate = StaticMethod.nullObject2String(countMap.get("sysdate"));
            }
        } catch (Exception e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return sysdate;

    }


}
