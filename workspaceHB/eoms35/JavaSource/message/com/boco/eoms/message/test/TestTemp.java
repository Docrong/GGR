package com.boco.eoms.message.test;

import java.rmi.RemoteException;
import java.text.ParseException;

import javax.xml.rpc.ServiceException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.util.SmsUtil;
import com.boco.eoms.message.service.MsgService;

public class TestTemp {

    /**
     * @param args
     * @throws ParseException
     */
    public void Test(String xmlString) throws ParseException {

//	 
//			try {
////				MsgServiceServiceLocator msgServiceServiceLocator =new MsgServiceServiceLocator();
////				com.boco.eoms.message.webservice.MsgService service = msgServiceServiceLocator.getSmsService();
////			    service.xSaveXmlString(xmlString);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


    }

    public static void main(String[] args) throws ParseException {
        //xmlString= "<?xml version="1.0" encoding="UTF-8"?>\n<SmsService><name>adadadada</name><msgType>1</msgType><count>1</count><interval>0</interval><startTime>2009-05-18 05:46:18</startTime><endTime>2009-05-18 05:46:18</endTime><sendDay>0</sendDay><sendHour>1</sendHour><sendMin>30</sendMin><sendStatus>2</sendStatus><cycleStatus>1</cycleStatus><cycleMonth>1</cycleMonth><cycleDay>1</cycleDay><cycleHour>1</cycleHour><regetAddr></regetAddr><regetProtocol>axis</regetProtocol><userId>wpadmin</userId><usersId>mengxianyu,xugengxian</usersId><status>2</status><remark>adadadada</remark></SmsService>";
        //String xmlString="<?xml version=\"1.0\" encoding=\"UTF-8\"><SmsService><id></id><name>gfgfgfg</name><parentId>8aa081972137641101213797c7660003</parentId><msgType>1</msgType><count>1</count><interval>1</interval><isSendImediat>1</isSendImediat><isSendNight>1</isSendNight><startTime>2009-08-11 11:11:22</startTime><endTime>2009-08-11 11:11:22</endTime><sendDay>1</sendDay><sendHour>1</sendHour><sendMin>1</sendMin><sendStatus>1</sendStatus><isCycleSend>1</isCycleSend><cycleStatus>1</cycleStatus><cycleMonth>1</cycleMonth><cycleDay>1</cycleDay><cycleHour>1</cycleHour><regetData>1</regetData><regetAddr>1</regetAddr><regetProtocol>1</regetProtocol><userId>sam</userId><status>1</status><selStatus>2</selstatus><deleted></deleted><leaf></leaf><remark>mengxianyu,hehe</remark></SmsService>";
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"><service><id></id><serviceName>很急</serviceName><createUserId>ppppppppp</createUserId><status>2</status><selStatus value=\"false\"><userId>sunshengtai,mengxianyu,zhuxiaoqin,wangheqi</userId></selStatus><msgType></msgType><count></count><interval></interval><isNightSend></isNightSend><isEmergency></isEmergency><startTime></startTime><endTime></endTime><sendStatus value=\"\"><sendDay></sendDay><sendHour></sendHour><sendMin></sendMin></sendStatus><isCycleSend value=\"yes\"><cycleStatus value=\"\"><cycleMonth></cycleMonth><cycleDay></cycleDay><cycleHour></cycleHour></cycleStatus><regetData><regetAddr regetProtocol=\"\"></regetAddr></regetData></isCycleSend></service>";
        //String xmlString ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><SmsService><id></id><name>easy</name><parentId>8aa081972137641101213797c7660003</parentId><msgType>1</msgType><count>1</count><interval>500</interval><selStatus>3</selStatus><isSendImediat>4</isSendImediat><isSendNight>1</isSendNight><startTime>2009-08-11 11:11:22</startTime><endTime>2009-08-11 11:11:22</endTime><sendDay>0</sendDay><sendHour>1</sendHour><sendMin>30</sendMin><sendStatus>2</sendStatus><cycleMonth>1</cycleMonth><cycleDay>1</cycleDay><cycleHour>1</cycleHour><regetAddr></regetAddr><regetProtocol>axis</regetProtocol><userId>wpadmin</userId><status>2</status><remark>pippo,michle</remark></SmsService>";
        //String xgx = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SmsService><id>8a8082ee2088999f0120891e4bf90341</id><name>qqq</name><parentId>8a8082ee2088999f0120891dc5b50340</parentId><msgType>1</msgType><count>1</count><interval>0</interval><isSendNight>1</isSendNight><startTime>2008-10-15 11:53:53</startTime><endTime>2008-10-15 11:53:53</endTime><sendDay>0</sendDay><sendHour>1</sendHour><sendMin>30</sendMin><sendStatus>2</sendStatus><cycleMonth>1</cycleMonth><cycleDay>1</cycleDay><cycleHour>1</cycleHour><regetAddr></regetAddr><regetProtocol>axis</regetProtocol><userId>wpadmin</userId><usersId>aa,bb,cc</usersId><status>2</status><deleted>0</deleted><leaf>1</leaf><remark>asdf</remark></SmsService>";
        //String id="8aa0819721389377012138aa2f620005";
        TestTemp tt = new TestTemp();
        tt.Test(xmlString);
//		  SmsUtil su=new SmsUtil();
//        SmsService sms=su.ServiceUtil(xgx);
//        System.out.println("=======" + sms.getId());
//       System.out.println("=======" + sms.getEndTime());
//       System.out.println("====cycleDay===" + sms.getCycleDay());

    }

}
