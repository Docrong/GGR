package com.huawei.boss.wsinterface.gate;

import javax.xml.rpc.*;

public class Test {
    public Test() {
    }

    public static void main(String[] args) {
        // TODO �Զ����ɵķ������
        try {
            CRMProcessSheetLocator crmProcessSheet = new CRMProcessSheetLocator();
            CRMProcessSheetSoap11BindingStub service = (CRMProcessSheetSoap11BindingStub) crmProcessSheet.getCRMProcessSheetHttpSoap11Endpoint();
            String optDetail = "";
            optDetail = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <opDetail> <recordInfo> <fieldInfo> <fieldChName>���粿����ϵ��</fieldChName> <fieldEnName>ndeptContact</fieldEnName> <fieldContent>��˼԰</fieldContent> </fieldInfo> <fieldInfo> <fieldChName>��ϵ�˵绰</fieldChName> <fieldEnName>ndeptContactPhone</fieldEnName> <fieldContent>15902700561</fieldContent> </fieldInfo> <fieldInfo> <fieldChName>������</fieldChName> <fieldEnName>dealResult</fieldEnName> <fieldContent>ͨ��</fieldContent> </fieldInfo> <fieldInfo> <fieldChName>����˵��</fieldChName> <fieldEnName>dealDesc</fieldEnName> <fieldContent>�����</fieldContent> </fieldInfo> <fieldInfo> <fieldChName>apnID</fieldChName> <fieldEnName>apnID</fieldEnName> <fieldContent> </fieldContent> </fieldInfo> <fieldInfo> <fieldChName>����ר�ߵ�·����</fieldChName> <fieldEnName>circuitCode</fieldEnName> <fieldContent>0</fieldContent> </fieldInfo><fieldInfo> <fieldChName>���Ա���</fieldChName> <fieldEnName>testReport</fieldEnName> <fieldContent></fieldContent> </fieldInfo> </recordInfo> </opDetail>"
            ;
            String result = service.replyWorkSheet(new Integer(32), new Integer(4),
                    "22702010082036194480", "HB_EOMS", "EOMS", "11111111",
                    "20091110 16:04:06", "", "bobo", "HB", "HB",
                    "13856478997", "2009-06-06", optDetail);
            System.out.print(result);
        } catch (Exception ex) {
        }

    }
}
