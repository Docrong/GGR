package com.cmcc.mm7.sample;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import com.boco.eoms.common.log.BocoLog;
import com.cmcc.mm7.vasp.common.MMConstants;
import com.cmcc.mm7.vasp.common.MMContent;
import com.cmcc.mm7.vasp.conf.MM7Config;
import com.cmcc.mm7.vasp.message.MM7RSRes;
import com.cmcc.mm7.vasp.message.MM7SubmitReq;
import com.cmcc.mm7.vasp.message.MM7SubmitRes;
import com.cmcc.mm7.vasp.service.MM7Sender;

public class ChartMMSend {
	private MM7Config config;

	public ChartMMSend() {
		config = new MM7Config("./mm7Config.xml");
		config.setConnConfigName("./ConnConfig.xml");
	}

	public ChartMMSend(String mm7ConfigFile, String connConfigNameFile) {
		config = new MM7Config(mm7ConfigFile);
		config.setConnConfigName(connConfigNameFile);
	}

	public void mmSend(Map map) {
		try {
			// SimpleDateFormat sdf = new
			// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			MM7SubmitReq submit = new MM7SubmitReq();
			// ��j��ʶ transactionID
			String transactionID = UtilMethod.nullObject2String(map
					.get("transactionID"));
			// ������� VASID
			String VASID = UtilMethod.nullObject2String(map.get("VASID"));
			// �����̴��� VASPID
			String VASPID = UtilMethod.nullObject2String(map.get("VASPID"));
			// ҵ�����serviceCode
			String serviceCode = UtilMethod.nullObject2String(map
					.get("serviceCode"));
			// ����ʼ������ַ senderAddress
			String senderAddress = UtilMethod.nullObject2String(map
					.get("senderAddress"));
			// �����ֻ�绰���� chargePartyID
			String chargePartyID;
			// Ŀ���ֻ�绰���� targetTelephoneNumber
			String targetTelephoneNumber = UtilMethod.nullObject2String(map
					.get("targetTelephoneNumber"));
			if (map.containsKey("chargePartyID"))
				chargePartyID = UtilMethod.nullObject2String(map
						.get("chargePartyID"));
			else
				chargePartyID = targetTelephoneNumber;
			// �������� subject
			String subject = UtilMethod.nullObject2String(map.get("subject"),
					"Subject");
			submit.setTransactionID(transactionID);
			submit.setVASID(VASID);
			submit.setVASPID(VASPID);
			submit.setServiceCode(serviceCode);
			submit.setSenderAddress(senderAddress);
			submit.setSubject(subject);
			submit.setChargedPartyID(chargePartyID);
			submit.setChargedParty((byte) 1);
			submit.setDeliveryReport(true);
			submit.addTo(targetTelephoneNumber);
			 
			MMContent content = (MMContent)map.get("contentList");
			// ���ò��ŷ�������
			content.setContentType(MMConstants.ContentType.MULTIPART_MIXED);
//			for (MMContent sub : list) {
//				content.addSubContent(sub);
//			}
			submit.setContent(content);
			Calendar calendar = Calendar.getInstance();
			calendar.set(calendar.MINUTE, calendar.get(calendar.MINUTE) + 2);
			SimpleDateFormat format = new SimpleDateFormat("hh-mm-ss");
			submit.setExpiryDate(calendar.getTime());
			System.out.println("ExpireTime: "
					+ format.format(submit.getExpiryDateAbsolute()));
			MM7Sender mm7Sender = new MM7Sender(config);
			MM7RSRes rsRes;
			rsRes = (MM7RSRes) mm7Sender.send(submit);
			if (rsRes instanceof MM7SubmitRes) {
				MM7SubmitRes submitRes = (MM7SubmitRes) rsRes;
				 BocoLog.info(this,55, " ���⣺"+subject+" Ŀ��绰��"+targetTelephoneNumber+" ���ش��룺" + submitRes.getTransactionID()+"after!!submitRes.statuscode="
							+ rsRes.getStatusCode() + ";submitRes.statusText="
							+ rsRes.getStatusText());
				System.out.print(" ���⣺"+subject);
				System.out.print(" Ŀ��绰��"+targetTelephoneNumber);
				System.out.print(" ���ش��룺" + submitRes.getTransactionID());
				System.out.print("after!!submitRes.statuscode="
						+ rsRes.getStatusCode() + ";submitRes.statusText="
						+ rsRes.getStatusText());
				System.out.println("");
				
			} else {
				 BocoLog.info(this,55," ���⣺"+subject+ " Ŀ��绰��"+targetTelephoneNumber+"����ȷ��Ϣ��rsRes.statuscode="
							+ rsRes.getStatusCode() + ";rsRes.statusText="
							+ rsRes.getStatusText());
				System.out.print(" ���⣺"+subject);
				System.out.print(" Ŀ��绰��"+targetTelephoneNumber);
				System.out.print("����ȷ��Ϣ��rsRes.statuscode="
						+ rsRes.getStatusCode() + ";rsRes.statusText="
						+ rsRes.getStatusText());
				
				System.out.println("");
			}
		} catch (Exception ex) {
			System.out.println("Catch a Exception when send MMS:\n "
					+ ex.toString());
			return;
		}
	}
}