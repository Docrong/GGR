package com.boco.eoms.commons.statistic.customstat.mgr.impl;

import org.apache.log4j.Logger;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.statistic.base.model.StatisticFileInfo;
import com.boco.eoms.commons.statistic.customstat.dao.ICustomstatRemindDao;
import com.boco.eoms.commons.statistic.customstat.mgr.ICustomstatRemindManager;
import com.boco.eoms.commons.statistic.customstat.model.CustomstatRemind;
import com.boco.eoms.message.service.impl.MsgServiceImpl;

public class CustomstatRemindManagerImpl extends BaseManager implements ICustomstatRemindManager {
    public Logger logger = Logger.getLogger(this.getClass());
    private ICustomstatRemindDao customstatRemindDao;

    public void deleteCustomstatRemind(CustomstatRemind _customstatremind) {
        customstatRemindDao.deleteCustomstatRemind(_customstatremind);
    }

    public CustomstatRemind getCustomstatRemind(StatisticFileInfo _info) {
        CustomstatRemind customstatremind = new CustomstatRemind();
        customstatremind.setSubId(_info.getSubId());//条件一：SUB_ID
        String statType = "";
        if (_info.getReportType().equals("yearReport")) {
            statType = "1";
            customstatremind.setYear(_info.getReportYear().trim());
        } else if (_info.getReportType().equals("seasonReport")) {
            statType = "2";
            customstatremind.setYear(_info.getReportYear().trim());
            customstatremind.setQuarter(_info.getReportSeason().trim());
        } else if (_info.getReportType().equals("monthReport")) {
            statType = "3";
            customstatremind.setYear(_info.getReportYear().trim());
            customstatremind.setMonth(_info.getReportMonth().trim());
        } else if (_info.getReportType().equals("dailyReport")) {
            statType = "4";
            String yyyy = _info.getReportYear().trim();
            String MM = _info.getReportMonth().trim();
            if (MM.length() == 1) {
                MM = "0" + MM;
            }
            String dd = _info.getReportDate().trim();
            if (dd.length() == 1) {
                dd = "0" + dd;
            }
            customstatremind.setDate(yyyy + " " + MM + " " + dd);
        } else if (_info.getReportType().equals("weekReport")) {
            statType = "5";
            customstatremind.setYear(_info.getReportYear().trim());
            customstatremind.setWeek(_info.getReportWeek().trim());
        }
        customstatremind.setStatType(statType);    //条件二：类型    条件三：具体时间

        return customstatRemindDao.getCustomstatRemind(customstatremind);
    }

    public void saveCustomstatRemind(CustomstatRemind _customstatremind) {
        customstatRemindDao.saveCustomstatRemind(_customstatremind);

    }

    public void flush() {
        customstatRemindDao.flush();
    }


    /*
     * 根据完整的定制对象生成信息定制数据
     * 其中需要配置一个变量：ServiceId
     * 这是定制服务ID（需要到系统中定制一个即时发送给全体人员的服务,然后将ID拷贝过来，同时需要将该服务开启）
     */
    public void setCustomstatRemind(CustomstatRemind _customstatremind) throws Exception {
        // 接收人
        String SaveUser = "";
        if (_customstatremind.getSaveUser() != null && !_customstatremind.getSaveUser().equals("")) {
            String[] users = _customstatremind.getSaveUser().split(",");
            for (int i = 0; i < users.length; i++) {
                SaveUser = SaveUser + "1," + users[i] + "#";
            }
            SaveUser.substring(0, SaveUser.length() - 1);
        }
        // 接收时间
        String SaveTime = "";
        if (_customstatremind.getAcceptDate() != null && !_customstatremind.getAcceptDate().equals("")) {
            SaveTime = _customstatremind.getAcceptDate();
        }
        // 唯一标识
        String buizId = _customstatremind.getId();

        // MSG
        String StatName = _customstatremind.getStatName();
        String StatTypeName = "";
        String StatTime = "";
        if (_customstatremind.getStatType() != null && !_customstatremind.getStatType().equals("")) {
            String StatType = _customstatremind.getStatType();
            if (StatType.equals("1")) {
                StatTypeName = "年报表";
                StatTime = _customstatremind.getYear() + "年";
            } else if (StatType.equals("2")) {
                StatTypeName = "季报表";
                StatTime = _customstatremind.getYear() + "年,第" + _customstatremind.getQuarter() + "季度";
            } else if (StatType.equals("3")) {
                StatTypeName = "月报表";
                StatTime = _customstatremind.getYear() + "年" + _customstatremind.getMonth() + "月";
            } else if (StatType.equals("4")) {
                StatTypeName = "日报表";
                StatTime = "日期：" + _customstatremind.getDate();
            } else if (StatType.equals("5")) {
                StatTypeName = "周报表";
                StatTime = _customstatremind.getYear() + "年,第" + _customstatremind.getWeek() + "周";
            }
        }
        String msg = "您订阅了报表：<" + StatName + ">_类型：" + StatTypeName + "(" + StatTime + "),请登陆EOMS系统来看您所订阅的报表";

        // Subject邮件标题
        String subject = "报表订阅提醒";

        // Addresser发邮件地址
        String addresser = "eoms@boco.com.cn";
        // AccessoriesUrl附件地址
        String accessoriesUrl = _customstatremind.getExcelUrl();


        // ServiceId定制服务ID（需要到系统中定制一个即时发送给全体人员的服务）
        String MsgServiceId = "8aa081c21ef84e85011ef8599fc4000c";
        String EmailServiceId = "8aa081c21ef84e85011ef85a669e000d";
        // 判断并发送消息或邮件
        MsgServiceImpl msgService = new MsgServiceImpl();
        String[] Accepttype = _customstatremind.getAccepttype().split(",");
        for (int i = 0; i < Accepttype.length; i++) {
            // 发短信
            if (Accepttype[i].equals("1")) {
                logger.info("\n发送短信 " + "服务id: " + MsgServiceId + "," + "发送内容: " + msg + "," + "消息服务的业务流水号: " + buizId +
                        "," + "发送人员或者部门id: " + SaveUser + "," + "发送时间点: " + SaveTime);
                msgService.sendMsg(MsgServiceId, msg, buizId, SaveUser, SaveTime);
            }
            // 发邮件
            if (Accepttype[i].equals("2")) {
                logger.info("\n发邮件 " + "服务id: " + EmailServiceId + "," + "邮件主题: " + subject + "," + "发送内容: " + msg +
                        "," + "消息服务的业务流水号: " + buizId + "," + "发邮件地址: " + addresser + "发送人员或者部门id: " + SaveUser +
                        "发送时间点: " + SaveTime + "附件url： " + accessoriesUrl);
                msgService.sendEmail(EmailServiceId, subject, msg, buizId, addresser, SaveUser, SaveTime, accessoriesUrl);
            }
        }

        // msgService.sendMsg("8aa082a81ddc0473011ddc3ffacb0013", "2008年11月26日完成测试邮件服务器功能，进一步完善了消息平台的功能，接下来还有彩信和语音部分需要完善。敬请期待下一个版本的功能。", "00000000000000000000000000000000", "1,zhiban2#1,zhiban3#1,zhiban1", "2008-12-09 12:00:00");
//		 msgService.sendEmail("8a9a8ae41dfb963e011dfb9988a20005", "关于测试邮件服务器主题问题","2008年11月26日完成测试邮件服务器功能，进一步完善了消息平台的功能，接下来还有彩信和语音部分需要完善。",
//		 "00000000000000000000000000000001", "peijianwen", "1,bizheng#1,zhuling","2008-11-27 10:05:00","F:\\readmail.txt");

        // 记录到另外一个记录表

        // 删除定制记录
        this.saveCustomstatRemind(_customstatremind);//增加了附件的url
//		this.deleteCustomstatRemind(_customstatremind);

        //刷新内存
//		this.flush();
    }

    public ICustomstatRemindDao getCustomstatRemindDao() {
        return customstatRemindDao;
    }

    public void setCustomstatRemindDao(ICustomstatRemindDao customstatRemindDao) {
        this.customstatRemindDao = customstatRemindDao;
    }


}
