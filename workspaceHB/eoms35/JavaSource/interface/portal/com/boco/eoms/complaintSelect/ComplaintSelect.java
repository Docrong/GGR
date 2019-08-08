package com.boco.eoms.complaintSelect;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.sheet.complaint.model.ComplaintSel;
import com.boco.eoms.sheet.complaint.service.IComplaintSelectManager;


public class ComplaintSelect {
    /**
     * 连通测试
     *
     * @param isAlive
     * @param
     * @return isAliveResult(string类型 。 为空表示相应服务可用 ， 非空表示该服务当前出现问题 ， 错误返回信息用相应明文进行说明)
     * @throws 执行报错 RuleToolXMLException
     */
    public String isAlive() {
        // BocoLog.trace(this, 35, "收到握手信号");
        System.out.println("收到握手信号");
        String isAliveResult = "";

        return isAliveResult;
    }

    /**
     * 投诉工单号增加接口,可以使用工单号查询手机号,或者是手机号查询工单号,并且记录日志包含时间,谁提交查询等
     *
     * @param sheetId     string(80) 投诉工单工单号
     * @param customPhone string(80) 投诉手机号
     * @param userName    string(40) 用户名
     * @param passWord    string(400) 密码
     * @param remark      string(20) 备注
     * @throws 执行报错RuleToolXMLException
     */
    public String selValue(String sheetId, String customPhone,
                           String userName, String passWord, String remark) {
        String getphone = "";
        String getsheetids = "";
        String result = "";

        IComplaintSelectManager icomplaintselectmanager =
                (IComplaintSelectManager) ApplicationContextHolder.getInstance().getBean("iComplaintSelectManager");
        ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("ItawSystemUserSaveManagerFlush");
        TawSystemUser user = null;
        user = (TawSystemUser) userManager.getUserByuserid(userName);
        ComplaintSel complaintsel = new ComplaintSel();
        try {
//			if (user == null)// 用户不存在，抛异常
//				result = "用户不存在";
//			// TODO ldap中验证通过后,不再验证EOMS系统
//			 else if (!user.getPassword().equals(// 密码错误，抛异常
//			 new Md5PasswordEncoder().encodePassword(passWord,
//			 new String()))) {
//				 result = "密码错误";
//			 } else {
//				 if (null!=sheetId&&!"".equals(sheetId)) {
//					 getphone = icomplaintselectmanager.getPhoneBySheetId(sheetId);
//					 result = getphone;
//				 }
//				 if (null!=customPhone&&!"".equals(customPhone)) {
//					 getsheetids = icomplaintselectmanager.getSheetIdsByPhone("13971664134");
//					 result = getsheetids;
//				 }
//			 }
            if (null == user || null == user.getPassword()) {
                result = "用户不存在";
            } else {
                if (!user.getPassword().equals(// 密码错误，抛异常
                        new Md5PasswordEncoder().encodePassword(passWord,
                                new String()))) {
                    result = "密码错误";
                } else {
                    if (null != sheetId && !"".equals(sheetId) && (null == customPhone || "".equals(customPhone))) {//根据工单号查询手机号
                        getphone = icomplaintselectmanager.getPhoneBySheetId(sheetId);
                        result = getphone;
                        complaintsel.setCustomphone(getphone);
                        complaintsel.setSheetid(sheetId);
                        complaintsel.setUsername(userName);
                        complaintsel.setPassword(passWord);
                        complaintsel.setRemark(remark);
                        icomplaintselectmanager.saveSelComplaint(complaintsel);
                    }
                    if (null != customPhone && !"".equals(customPhone) && (null == sheetId || "".equals(sheetId))) {//根据手机号查询工单号
                        getsheetids = icomplaintselectmanager.getSheetIdsByPhone(customPhone);
                        result = getsheetids;
                        complaintsel.setCustomphone(customPhone);
                        complaintsel.setSheetid(getsheetids);
                        complaintsel.setUsername(userName);
                        complaintsel.setPassword(passWord);
                        complaintsel.setRemark(remark);
                        icomplaintselectmanager.saveSelComplaint(complaintsel);
                    }
                    if (null != sheetId && !"".equals(sheetId) && null != customPhone && !"".equals(customPhone)) {
                        result = "手机号和工单号不能全部填写!";
                    }

                }
            }
            System.out.println(result);

        } catch (RuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
