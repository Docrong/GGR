package com.boco.eoms.interfaces.log.vo;
import com.boco.eoms.interfaces.util.XMLParse;
public class InterfaceLogVO {

    XMLParse xml = XMLParse.getInstance();

    private String id;

    /** nullable persistent field */
    private int fromSystem;

    /** nullable persistent field */
    private String sheetId;

    /** nullable persistent field */
    private String sheetType;

    /** nullable persistent field */
    private String serSupplier;

    /** nullable persistent field */
    private String serCaller;

    /** nullable persistent field */
    private String serMethod;

    /** nullable persistent field */
    private String callTime;

    /** nullable persistent field */
    private String result;

    /** nullable persistent field */
    private String sourceData;

    private String fromSystemName;

     private String resultName;

    private String serCallerName;

    private String serSupplierName;

  public InterfaceLogVO() {
  }

  public String getId() {
      return this.id;
  }

  public void setId(String id) {
      this.id = id;
  }

  public int getFromSystem() {
      return this.fromSystem;
  }

  public void setFromSystem(int fromSystem) {
      this.fromSystem = fromSystem;
  }

  public String getSheetId() {
      return this.sheetId;
  }

  public void setSheetId(String sheetId) {
      this.sheetId = sheetId;
  }

  public String getSheetType() {
      return this.sheetType;
  }

  public void setSheetType(String sheetType) {
      this.sheetType = sheetType;
  }

  public String getSerSupplier() {
      return this.serSupplier;
  }

  public void setSerSupplier(String serSupplier) {
      this.serSupplier = serSupplier;
  }

  public String getSerCaller() {
      return this.serCaller;
  }

  public void setSerCaller(String serCaller) {
      this.serCaller = serCaller;
  }

  public String getSerMethod() {
      return this.serMethod;
  }

  public void setSerMethod(String serMethod) {
      this.serMethod = serMethod;
  }

  public String getCallTime() {
      return this.callTime;
  }

  public void setCallTime(String callTime) {
      this.callTime = callTime;
  }

  public String getResult() {
        return this.result;
  }

  public String getResultName() {
        if(this.result.equals("")){
          resultName="正常";
          return resultName;
        }
        else{
          resultName="异常";
          return resultName;
        }
    }

  public void setResult(String result) {
      this.result = result;
  }

  public String getSourceData() {
      return this.sourceData;
  }

  public void setSourceData(String sourceData) {
      this.sourceData = sourceData;
  }

  public String getFromSystemName() {
     switch(fromSystem){
       case 2 : fromSystemName="网管告警接口"; break;
       case 3 : fromSystemName="客服接口";break;
       case 4 : fromSystemName="省部接口";break;
       default : fromSystemName="";
     }
     return fromSystemName;
   }

}
