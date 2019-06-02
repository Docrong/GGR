package com.boco.eoms.gzjhhead.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.Hashtable;
import com.boco.eoms.common.properties.XMLProperties;
import java.io.File;
import org.jdom.Element;
import java.util.List;

public class GzBaseInfo {

  public static String FILE_PASSTYPE = "passtype";
  public static String FILE_PROVINCE = "province";
  public static String FILE_YEAR = "year";
  public static String FILE_MONTH = "month";
  public static String FILE_DAY = "day";
  public static String FILE_CYCLE = "cycle";
  public static String FILE_SYSTYPE = "systype";
  public static String FILE_ITEMID = "itemid";
  public static String FILE_NETINFO = "netinfo";
  public static String provinceNameStr = null;
  public static String sysPath = null;

  private static Hashtable provinceNameHash = null;
  private static Hashtable provinceHttpHash = null;
  private static Hashtable provinceUrlHash = null;
  private static Hashtable cycleHash = null;
  private static Hashtable sysTypeHash = null;
  private static Hashtable userTypeHash = null;
  private static Hashtable logTypeHash = null;
  private static Hashtable netTypeHash = null;

  private static GzBaseInfo gzBaseInfo = null;

  private static String[] provinceName = {
      "总部", "北京", "天津", "上海", "重庆", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "江苏",
      "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "广西", "海南", "四川",
      "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆"};
  public static String[] privinceId = {
      "ZB", "BJ", "TJ", "SH", "CQ", "HE", "SX", "NM", "LN", "JL", "HL", "JS",
      "ZJ", "AH", "FJ", "JX", "SD", "HA", "HB", "HN", "GD", "GX", "HI", "SC",
      "GZ", "YN", "XZ", "SN", "GS", "QH", "NX", "XJ"};
  private static String[] privinceUrl = {};

  private static String[] cycleId = {
      "D", "W", "M", "Q", "H", "Y"};
  private static String[] cycleName = {
      "日", "周", "月", "季", "半年", "年"};
  private static int[] gzCycle = {
      1, 2, 4, 5, 6, 7};

  public static String[] sysTypeId = {
      "JH", "FZ", "WX", "SB", "ZH", "ZN"};
  private static String[] sysTypeName = {
      "交换", "分组", "无线", "移动设备网管", "移动综合网管", "智能网"};

  private static int[] newTypeId ={
     1,5,6,3,4,2};

  public static int[] userTypeId = {
      0, 1};
  private static String[] userTypeName = {
      "内容用户", "接口用户"};

  public static String[] logTypeId = {
      "RF", "RS", "AS", "NL"};
  private static String[] logTypeName = {
      "省级上报作业执行情况", "省级上报作业计划", "总部)下发/修改作业计划大纲", "内部操作"};

  public static String[] netTypeId = {
      "WY", "JF"};
  private static String[] netTypeName = {
      "网元", "机房"};

  public GzBaseInfo(String _projecePath) {
    this.initProvinceName();
    this.getProvinceName();
    this.initCycle();
    this.initSysType();
    this.initUserType();
    this.initLogType();
    this.initNetType();
    this.initProvinceXml(_projecePath);
    sysPath = _projecePath;
  }

  /**
   * 初始化省份信息
   */
  private void initProvinceName() {
    provinceNameHash = new Hashtable();
    for (int i = 0; i < privinceId.length; i++) {
      provinceNameHash.put(privinceId[i], provinceName[i]);
    }
  }

  /**
   * 形成省份编号字符串
   */
  public static void getProvinceName() {

    String provinceStr = "";
    boolean flag = true;
    for (int i = 0; i < privinceId.length; i++) {
      if (flag) {
        provinceStr = privinceId[i];
        flag = false;
      }
      else {
        provinceStr = provinceStr + "," + privinceId[i];
      }
    }
    provinceNameStr = provinceStr;
  }

  /**
   * 初始化省份接口信息
   */
  private void initProvinceUrl() {
    provinceUrlHash = new Hashtable();
    for (int i = 0; i < privinceId.length; i++) {
      provinceUrlHash.put(privinceId[i], privinceUrl[i]);
    }
  }

  /**
   * 初始化周期信息
   */
  private void initCycle() {
    cycleHash = new Hashtable();
    for (int i = 0; i < cycleId.length; i++) {
      cycleHash.put(cycleId[i], cycleName[i]);
    }
  }

  /**
   * 初始化系统信息
   */
  private void initSysType() {
    sysTypeHash = new Hashtable();
    for (int i = 0; i < sysTypeId.length; i++) {
      sysTypeHash.put(sysTypeId[i], sysTypeName[i]);
    }
  }

  /**
   * 初始化用户类型信息
   */
  private void initUserType() {
    userTypeHash = new Hashtable();
    for (int i = 0; i < userTypeId.length; i++) {
      userTypeHash.put(String.valueOf(userTypeId[i]), userTypeName[i]);
    }
  }

  /**
   * 初始化用户类型信息
   */
  private void initLogType() {
    logTypeHash = new Hashtable();
    for (int i = 0; i < logTypeId.length; i++) {
      logTypeHash.put(logTypeId[i], logTypeName[i]);
    }
  }

  /**
   * 初始化网元类型信息
   */
  private void initNetType() {
    netTypeHash = new Hashtable();
    for (int i = 0; i < netTypeId.length; i++) {
      netTypeHash.put(String.valueOf(netTypeId[i]), netTypeName[i]);
    }
  }

  /**
   * 获取省份名称
   * @param _privinceId String 省份编号
   * @return String 省份名称
   */
  public static String getProvinceName(String _privinceId) {
    return (String) provinceNameHash.get(_privinceId);
  }

  /**
   * 获取省份接口
   * @param _privinceId String 省份编号
   * @return String 省份接口
   */
  public static String getProvinceUrl(String _privinceId) {
    return (String) provinceUrlHash.get(_privinceId);
  }

  /**
   *  获取周期
   * @param _cycleId String 周期编号
   * @return String 周期名称
   */
  public static String getCycle(String _cycleId) {
    return (String) cycleHash.get(_cycleId);
  }

  /**
   * 获取系统名称
   * @param _sysTypeId String 系统编号
   * @return String 系统名称
   */
  public static String getSysType(String _sysTypeId) {
    return (String) sysTypeHash.get(_sysTypeId);
  }

  /**
   * 获取用户类型名称
   * @param _userTypeId String 用户类型编号
   * @return String 用户类型名称
   */
  public static String getUserType(int _userTypeId) {
    return (String) userTypeHash.get(String.valueOf(_userTypeId));
  }

  /**
   * 获取日志类型名称
   * @param _logTypeId String 日志户类型编号
   * @return String 日志类型名称
   */
  public static String getLogType(String _logTypeId) {
    return (String) logTypeHash.get(_logTypeId);
  }

  /**
   * 获取网元类型名称
   * @param _netTypeId String 网元类型编号
   * @return String 网元类型名称
   */
  public static String getNetType(String _netTypeId) {
    return (String) netTypeHash.get(_netTypeId);
  }

  /**
   * 基础信息对象
   * @return GzBaseInfo 基础信息对象
   */
  public static GzBaseInfo getInstance(String _projecePath) {
    if (gzBaseInfo == null) {
      gzBaseInfo = new GzBaseInfo(_projecePath);
    }
    return gzBaseInfo;
  }

  public static GzBaseInfo getInstance() {
    return gzBaseInfo;
  }


  public static Hashtable analyseFileName(String _fileName) {

    Hashtable hashtable = null;

    if (_fileName.indexOf(".xls") > 0) {
      hashtable = new Hashtable();

      hashtable.put("passtype", _fileName.substring(0, 2));
      hashtable.put("province", _fileName.substring(3, 5));
      hashtable.put("year", _fileName.substring(6, 10));
      hashtable.put("month", _fileName.substring(10, 12));
      hashtable.put("day", _fileName.substring(12, 14));
      hashtable.put("cycle", _fileName.substring(15, 16));
      hashtable.put("systype", _fileName.substring(17, 19));
      hashtable.put("itemid", _fileName.substring(20, 23));
      hashtable.put("netinfo",
                    _fileName.substring(24, _fileName.indexOf(".xls")));
    }

    return hashtable;
  }

  public void initProvinceXml(String _projecePath) {

    XMLProperties properties = null;

    properties = new XMLProperties(_projecePath + File.separator + "provinceurl.xml");

    Element element = properties.getElement();

    List list = element.getChildren();

    provinceHttpHash = new Hashtable();
    provinceUrlHash = new Hashtable();

    for (int i = 0; i < list.size(); i++) {
      element = (Element) list.get(i);
      String provinceName = element.getAttributeValue("name");
      String provinceId = element.getAttributeValue("id");
      String http = element.getAttributeValue("http");
      String url = element.getAttributeValue("url");
      provinceHttpHash.put(provinceName, http);
      provinceUrlHash.put(provinceId, url);
    }
  }

  /**
   * 获取各省的http链接信息
   * @return Hashtable 各省的http链接信息
   */
  public static Hashtable getProvinceXml() {
    return provinceHttpHash;
  }

  /**
   * 获取各省的接口服务地址
   * @param _provinceId String 省编号
   * @return String 接口服务地址
   */
  public static String getProvinceXml(String _provinceId) {
    return (String) provinceUrlHash.get(_provinceId);
  }

  public static String getSysPath(){
    return sysPath;
  }

  /**
    /**
    * 返回作业计划标示
    * @param _cycleId  省份作业计划的周期
    * @return 作业计划周期编号
    */
   public static String getGzCycle(int _cycleId) {
     String gzCycleId = "";
     for (int i = 0; i < gzCycle.length; i++) {
       if (_cycleId == gzCycle[i]) {
         gzCycleId = cycleId[i];
         return gzCycleId;
       }

     }
     return gzCycleId;
   }
  /**
   * 取数值对应的系统类型
   * @param _typeId 数值
   * @return 系统类型
   */
  public static String getNewSysTypeName(int _typeId){
    String typeName="";
    for(int i=0;i<newTypeId.length;i++){
         if(_typeId == newTypeId[i]){
             typeName=sysTypeId[i];
         }
    }
    return typeName;
  }

  public static void main(String arg[]) {
    GzBaseInfo.analyseFileName("");
  }

}
