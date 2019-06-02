package  com.boco.eoms.message.util;
 


import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
public class LongSms {
	
	 
	public static String TestSms(String oddrNumber,
			String  MsgContent 
	
	){
		String ret = "";
		try{
			String endpoint="http://10.184.14.197/LongSmsSendWebService/Service.asmx";
			String nameSpace = "http://tempuri.org/";
			String action = "TestSms";		
			
			Service  service = new Service();
			Call     call    = (Call) service.createCall();
			
			call.setTargetEndpointAddress( new java.net.URL(endpoint) );           
			call.setOperationName(new QName(nameSpace,action) );
			call.setUseSOAPAction(true);
			call.addParameter(new QName(nameSpace,"oddrNumber"),XMLType.XSD_STRING,ParameterMode.IN);
	    	call.addParameter(new QName(nameSpace,"MsgContent"),XMLType.XSD_STRING,ParameterMode.IN);
	    	 
	
	       	call.setUseSOAPAction(true);   
	    	call.setReturnType(XMLType.SOAP_STRING);    
	    	call.setProperty(call.SOAPACTION_USE_PROPERTY, new Boolean(true));
		    call.setProperty(call.SOAPACTION_URI_PROPERTY,"http://tempuri.org/TestSms");
			  
			ret = (String) call.invoke( new Object[] { 
				oddrNumber,
				MsgContent 
				 });
			
			//System.out.println("Sent 'TestSms!', got '" + ret + "'");		
		} catch (Exception e) {
			System.err.println(e.toString());			
		}
		return ret;
	}
	
	public static void main(String args[]){
		try{
			//LongSms longSms = new LongSms();
			LongSms.TestSms(
					"13878862861,13737094478","test by ccy test by ccytest by ccytest by ccytest by ccytest by ccytest by ccytest by ccytest by ccytest by ccytest by ccy"+
					"test by ccytest by ccytest by ccytest by ccytest by ccytest by ccy"
					 );
		}catch(Exception e){
			 e.printStackTrace();
		}
	}
}
