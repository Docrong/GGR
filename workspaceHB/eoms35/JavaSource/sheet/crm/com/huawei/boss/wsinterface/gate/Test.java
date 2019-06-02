package com.huawei.boss.wsinterface.gate;

import javax.xml.rpc.*;

public class Test {
  public Test() {
  }
  public static void main(String[] args) {
    // TODO 自动生成的方法存根
                try {
                  CRMProcessSheetLocator crmProcessSheet = new  CRMProcessSheetLocator();
                  CRMProcessSheetSoap11BindingStub service = (CRMProcessSheetSoap11BindingStub) crmProcessSheet.getCRMProcessSheetHttpSoap11Endpoint();
                  String optDetail = "";
                  optDetail = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <opDetail> <recordInfo> <fieldInfo> <fieldChName>网络部门联系人</fieldChName> <fieldEnName>ndeptContact</fieldEnName> <fieldContent>高思园</fieldContent> </fieldInfo> <fieldInfo> <fieldChName>联系人电话</fieldChName> <fieldEnName>ndeptContactPhone</fieldEnName> <fieldContent>15902700561</fieldContent> </fieldInfo> <fieldInfo> <fieldChName>处理结果</fieldChName> <fieldEnName>dealResult</fieldEnName> <fieldContent>通过</fieldContent> </fieldInfo> <fieldInfo> <fieldChName>处理说明</fieldChName> <fieldEnName>dealDesc</fieldEnName> <fieldContent>已完成</fieldContent> </fieldInfo> <fieldInfo> <fieldChName>apnID</fieldChName> <fieldEnName>apnID</fieldEnName> <fieldContent> </fieldContent> </fieldInfo> <fieldInfo> <fieldChName>传输专线电路代号</fieldChName> <fieldEnName>circuitCode</fieldEnName> <fieldContent>0</fieldContent> </fieldInfo><fieldInfo> <fieldChName>测试报告</fieldChName> <fieldEnName>testReport</fieldEnName> <fieldContent></fieldContent> </fieldInfo> </recordInfo> </opDetail>"
                      ;
                  String result = service.replyWorkSheet(new Integer(32), new Integer(4),
                      "22702010082036194480", "HB_EOMS", "EOMS", "11111111",
                      "20091110 16:04:06", "", "bobo", "HB", "HB",
                      "13856478997", "2009-06-06", optDetail);
                  System.out.print(result);
                }
                catch (Exception ex) {
                }

  }
}
