/**
 * @author 白强
 * @version 1.0
 * @see <p>静态类，保存全局变量。</p>
 */

package com.boco.eoms.common.util;

// import com.boco.eoms.worksheet.util.StaticWorksheet;

public interface StaticVariable {

    /**
     * @see 字典类型 FAULT_CLASS 紧急程度（比如：次要/一般/重要/紧急/来自领导投诉）
     */
    public static final int FAULTCLASS_CY = 1;

    public static final int FAULTCLASS_YB = 2;

    public static final int FAULTCLASS_ZY = 3;

    public static final int FAULTCLASS_JJ = 4;

    public static final int FAULTCLASS_LZLDTS = 5;

    // sms
    // public static final int HIETYPE_ACCEPT = StaticWorksheet.ACTION_ACCEPT;
    // public static final int HIETYPE_DEAL = StaticWorksheet.ACTION_BACKTOUP;
    // public static final int HIETYPE_OVER = StaticWorksheet.ACTION_HOLD;
    // public static final int HIETYPE_TRANSMIT =
    // StaticWorksheet.ACTION_TRANSMIT;
    // public static final int HIETYPE_AUDIT =
    // StaticWorksheet.ACTION_AUDITACCEPT;

    // added by jerry
    // oper type
    public static final int OPER_SEND = 1;

    public static final int OPER_AUDIT = 3;

    public static final int OPER_COPY = 2;

    // worksheet flow config
    public static final int OBJECT_DEPT = 1;

    public static final int OBJECT_USER = 2;

    public static final int OBJECT_GROUP = 3;

    /**
     * @see 角色配置中步骤说明
     */
    // public static final String TEMPTable = "t_taw_priv";
    /**
     * @see 角色配置中步骤说明
     */
    public static final String roleStep1 = "第一步：选择操作集合所属的应用模块";

    public static final String roleStep2 = "第二步：选择操作集合";

    public static final String roleStep3 = "第三步：选择操作集合对应的域集合";

    public static final String roleStep4 = "配置结束";

    /**
     * @see 用户权限配置中步骤说明
     */
    public static final String privStep1 = "第一步：选择操作集合所属的应用模块";

    public static final String privStep2 = "第二步：选择操作集合";

    public static final String privStep3 = "第三步：选择操作集合对应的域集合";

    public static final String privStep4 = "第四步：结束配置";

    /**
     * @see 省公司（系统部门的根节点）默认的部门id值
     */
    public static final int ProvinceID = 1;

    /**
     * @see 催办方式--短信。
     */
    public static final int HTYPE_SMS = 0;

    /**
     * @see 短信是否可用。
     */
    public static final String SendSMsgAvailable = "true";

    public static final String SendSMsgNotAvailable = "false";

    /**
     * @see 数据库类型。
     */
    public static final String ORACLE = "oracle";

    public static final String INFORMIX = "informix";

    /**
     * @see 编码类型。
     */
    public static final String GB2312 = "GB2312";

    public static final String ISO = "ISO-8859-1";

    /**
     * @see 操作类型，用于日至管理。
     */
    public static final String ERROR = "error";

    public static final String OPER = "oper";

    public static final String INFOR = "infor";

    public static final String WARN = "warn";

    /**
     * @see 操作有域值，即需要配置域信息。
     */
    public static final int HASDOM = 0;

    /**
     * @see 系统超级用户ID号
     */
    public static final String ADMIN = "admin";

    /**
     * @see 部门域类型
     */
    public static final int DOMAIN_DEPT = 1;

    /**
     * @see 机房域类型
     */
    public static final int DOMAIN_ROOM = 2;

    /**
     * @see 用户域类型
     */
    public static final int DOMAIN_USER = 3;

    /**
     * @see 信息发布域类型
     */
    public static final int DOMAIN_INFO = 8;

    /**
     * @see 业务论坛域类型
     */
    public static final int DOMAIN_BOARD = 9;

    /**
     * @see 其它域类型
     */
    public static final int DOMAIN_OTHER = 10;

    /**
     * @see 其它域类型
     */
    public static final int DOMAIN_NO = 11;

    /**
     * @see 操作权限类型
     */
    public static final int PRIV_OPER = 1;

    /**
     * @see 角色权限类型
     */
    public static final int PRIV_ROLE = 2;

    /**
     * @see 删除标记
     */
    public static final int DELETED = 1;

    /**
     * @see 未删除标记
     */
    public static final int UNDELETED = 0;

    /**
     * @see 隐藏标记
     */
    public static final int HIDE = 1;

    /**
     * @see 不隐藏标记
     */
    public static final int UNHIDE = 0;

    /**
     * @see 字典管理的节点值
     */
    public static final int DICTROOT = 1216;

    /**
     * @see 树形展示是的叶子标记
     */
    public static final int LEAF = 1;

    /**
     * @see 配置用户权限的节点值
     */
    public static final int userConfig = 1214;

    /**
     * @see 用户类型，管理员类型，即有派发权限的能力
     */
    public static final int userAdmin = 1;

    public static final String userDegreeAdm = "管理员";

    public static final String userDegreeCom = "普通用户";

    /**
     * @see 更新角色+域的Action指令类
     */
    public static final String url_role = "TawUserPrivDom/updateRoleDom.do";

    /**
     * @see 更新操作+域的Action指令类
     */
    public static final String url_oper = "TawUserPrivDom/saveOperDom.do";

    /**
     * @see 更新细化角色后得到的操作+域的Action指令类
     */
    public static final String url_ro = "TawUserPrivDom/saveRoleOperDom.do";

    /**
     * @see 系统默认的空值
     */
    public static final String defaultnull = "-10";

    public static final int defnull = -10;

    // pyun information publish begin
    public static final int INFORM_APP_ID[] = {13, 18, 22, 23, 24, 21};

    public static final int INFORM_ADD[] = {1000, 1010, 1000, 1022};

    public static final int INFORM_DEL[] = {1001, 1011, 1000, 1023};

    public static final int INFORM_MODI[] = {1002, 1012, 1000, 1024};

    public static final int INFORM_READ[] = {1003, 1013, 1000, 1025};

    public static final int INFORM_ADD_CHK[] = {1004, 1014, 1000, 1026};

    public static final int INFORM_MODI_SELF[] = {1005, 1015, 1000, 1027};

    public static final int INFORM_CHK[] = {1006, 1016, 1000, 1021};

    public static final int INFORM_CLASSIFY[] = {1007, 1017, 1000, 1028};

    public static final int INFORM_TOP[] = {1008, 1018, 1000, 1029};

    public static final int INFORM_ROLL[] = {1009, 1019, 1000, 1030};

    public static final int INFORM_DOMAIN_TYPE = 7;

    // pyun information publish end

    // --interface for kefu begin -----
    public static final String INTERFACE_USER = "1860";

    public static final int INTERFACE_USERID = 6414;

    public static final int INTERFACE_DEPTID = 100;

    public static final String INTERFACE_DEPTNAME = "省网管中心";

    // ------interface for kefu end---------------------------

    // --工单模块专用全局变量 Modify By ZhouGuangYi Begin-----

    /**
     * @see 规范2定义的地区ID，地区代码和地区名称
     */
    // public static final int INTREGIONID = 26;
    // public static final String STRREGIONCODE = "XZ";
    // public static final String STRREGIONNAME = "西藏";
    /**
     * @see 字典类型 FAULT_TYPE 故障类型(规范定义)
     * @see 根据数据库定义配置此值
     */

    public static final int FAULTTYPE = 1;

    public static final int SPECIALITY = 406112;

    // public static final int SPECIALITY =1;
    // 重大故障上报工单的故障设备
    public static final int EQUIPID = 406114;

    public static final int EQUIPTYPE = 406113;

    public static final int MANU = 406115;

    // cheng 所属专业 局数据和任务工单用
    public static final int SPECIALTY = 406112;

    // 重大故障工单使用字典表 故障类型
    public static final int FAULTEQUIP = 19;

    // 软件补丁任务工单
    public static final int SOFTTYPE = 20;

    // 用户申告工单1860类型
    public static final int COMPLIANTTYPE = 7;

    /**
     * @see DEFAULTFAULTCODE默认的网络类型代码，OTHERFAULTCODE表示没有网络类型定义的工单。
     * @see 规范定义：重大故障上报工单、信息发布、电路调度单、安全策略调整工单、通用申请审批单的网络类型代码统一为“000”
     */
    public static final String DEFAULTFAULTCODE = "000";

    public static final String OTHERFAULTCODE = "999";

    /**
     * @see 字典类型 FAULT_CLASS 紧急程度（比如：一般/次要/重要/紧急）
     * @see 根据数据库定义配置此值
     */
    public static final int FAULTCLASS = 2;

    /**
     * @see 字典类型 ATISFACTION 满意程度（比如：满意/不满意）
     * @see 根据数据库定义配置此值
     */
    public static final int SATISFACTION = 3;

    /**
     * @see 默认整型错误码
     */
    public static final int INTERRORCODE = -10;

    /**
     * @see 默认字符串型错误码
     */
    public static final String STRERRORCODE = "-10";

    // --工单模块专用全局变量 Modify By ZhouGuangYi End-------

    /**
     * Added by Lihong Qi: 组织考核模块的起始和终止操作id
     */
    public static final int ASSESSSTARTOPERID = 11000;

    public static final int ASSESSENDOPERID = 11999;

    public static final int OLDPWDTIMES = 5;

    public static final int OLDINERRORTIMES = 5;

    public static final int LOGINERRORTIMES = 5;

    public static final int OLDPWDDAY = -90; // 超过90天短信提醒

    public static final int LOCKUSER = 1;

    public static final int UNLOCKUSER = 0;

    public static final String DB = "informix";

}
