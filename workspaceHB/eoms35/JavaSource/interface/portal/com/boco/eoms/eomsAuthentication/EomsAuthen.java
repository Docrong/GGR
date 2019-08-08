package com.boco.eoms.eomsAuthentication;

import java.util.HashMap;

import com.boco.eoms.commons.rule.tool.exception.RuleToolXMLException;
import com.boco.eoms.commons.system.user.util.UserMgrLocator;
import com.boco.eoms.util.InterfaceUtil;

public class EomsAuthen {
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
//	参数名称	参数类型	中文名称	限定	说明
//	serSupplier	string(16)	服务提供方	M	参见附录A.3
//	serCaller	string(16)	服务调用方	M	参见附录A.3
//	callerPwd	string(32)	口令	M,N	/
//	callTime	string(20)	服务调用时间	M	/
//	userName	string(20)	E-OMS用户名称	M	/
//	userPassword	string(20)	E-OMS用户密码	M	/


    /**
     * E-OMS系统提供该接口服务以供网管系统对E-OMS系统用户进行鉴权
     *
     * @param serSupplier  string(16) 服务提供方 M 参见附录A.3
     * @param serCaller    string(16) 服务调用方 M 参见附录A.3
     * @param callerPwd    string(32) 口令 M,N /
     * @param callTime     string(20) 服务调用时间 M /
     * @param userName     string(20)	E-OMS用户名称	M	/
     * @param userPassword string(20)	E-OMS用户密码	M	/
     * @return result errList(string类型。空串表示调用成功，并且该用户的帐户名与密码均正确，并且该用户具有派发故障工单权限。非空串表示认证失败或调用失败。若认证失败，其值为“用户名错”或“用户密码错”或“该用户无派发故障工单权限”；若调用失败，其值为错误描述。
     * @throws 执行报错RuleToolXMLException
     */
    public String eomsAuthentication(String serSupplier, String serCaller,
                                     String callerPwd, String callTime, String opDetail) throws Exception {
        System.out.println("告警eoms鉴权");
        System.out.println("opDetail＝" + opDetail);

        String eomsAuthenticationresult = "";
        boolean result = false;
        InterfaceUtil interfaceUtil = new InterfaceUtil();
        HashMap map = new HashMap();
        map = interfaceUtil.xmlElements(opDetail, map, "FieldContent");
        String userName = (String) map.get("userName");
        String userPassword = (String) map.get("userPassword");
        result = UserMgrLocator.getTawSystemUserMgr().getUser(userName, userPassword);
        if (result == false) {
            eomsAuthenticationresult = "验证失败";
        }
        return eomsAuthenticationresult;
    }

    // 参数名称 参数类型 中文名称 限定 说明
    // serSupplier string(16) 服务提供方 M 参见附录A.3
    // serCaller string(16) 服务调用方 M 参见附录A.3
    // callerPwd string(32) 口令 M,N /
    // callTime string(20) 服务调用时间 M /
    // alarmId
    // string(80) 网管告警ID M 网管系统简称_告警ID
    // alarmStatus Integer(2) 告警状态 M 1：自动清除；2：手工清除；
    // clearTime string(20) 告警清除时间 M /
    // staffNo string(32) 手工清除告警人员的工号 M,N /


}
