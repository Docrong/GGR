package com.boco.eoms.commons.message.msg.bo;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;

import com.commerceware.cmpp.CMPP;

import com.commerceware.cmpp.cmppe_deliver_result;
import com.commerceware.cmpp.cmppe_login_result;
import com.commerceware.cmpp.cmppe_result;
import com.commerceware.cmpp.cmppe_submit;
import com.commerceware.cmpp.cmppe_submit_result;
import com.commerceware.cmpp.conn_desc;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.message.msg.dao.TawCommonMessageEmail;
import com.boco.eoms.commons.message.msg.dao.TawCommonMessageMobile;

public class TawCommonMessageSendTel {

	/**
	 * 
	 * @param con
	 * @return
	 */
	protected String readPa(conn_desc con) {
		cmppe_result cr = null;
		CMPP cmpp = new CMPP();
		String ret = "";
		try {
			cr = cmpp.readResPack(con);
			switch (cr.pack_id) {
			case CMPP.CMPPE_NACK_RESP:
				ret = "get nack pack";
				break;
			case CMPP.CMPPE_LOGIN_RESP:
				cmppe_login_result cl;
				cl = (cmppe_login_result) cr;
				ret = "------------login resp----------: STAT = " + cl.stat;
				break;

			case CMPP.CMPPE_LOGOUT_RESP:
				ret = "------------logout resp----------: STAT = " + cr.stat;
				break;

			case CMPP.CMPPE_SUBMIT_RESP:
				cmppe_submit_result sr;
				sr = (cmppe_submit_result) cr;
				System.out.println("------------submit resp----------: STAT = "
						+ sr.stat + " SEQ = " + sr.seq + "--us_count="
						+ sr.us_count + "---msg_id=" + new String(sr.msg_id));
				ret = sr.stat + "";
				break;

			case CMPP.CMPPE_DELIVER:
				cmppe_deliver_result cd = (cmppe_deliver_result) cr;
				if (cd.status_rpt == 1)
					System.out.println("Rpt status = " + cd.status_from_rpt);
				cmpp.cmpp_send_deliver_resp(con, cd.seq, cd.stat);
				ret = "Rpt status = " + cd.status_from_rpt;
				break;

			case CMPP.CMPPE_CANCEL_RESP:
				ret = "---------cancel-----------: STAT = " + cr.stat;
				break;

			case CMPP.CMPPE_ACTIVE_RESP:
				ret = "---------active resp-----------: STAT " + cr.stat;
				break;

			default:
				ret = "---------Error packet-----------";
				break;

			}

		} catch (Exception e) {
			BocoLog.info(this, e.getMessage());
			e.printStackTrace();

			try {
				System.in.read();
			} catch (Exception e1) {
			}

		}
		return ret;
	}

	/**
	 * 
	 * @param tel
	 * @param msg
	 * @return
	 */
	private cmppe_submit initCMPP(String tel, String msg, String sms_icp_id,
			String sms_icp_code) {

		cmppe_submit sub = new cmppe_submit();
		
		byte[] icp_id = StrToByte(sms_icp_id);
		byte svc_type[] = StrToByte(sms_icp_code);
		byte fee_type = 1;
		byte info_fee = 0;
		byte proto_id = 1;
		byte msg_mode = 1;
		byte priority = 0;
		byte fee_utype = 0;
		byte validate[] = { 0 };
		byte schedule[] = { 0 };

		String mobile[] = tel.split(",");
		System.out.println("mobile.length:" + mobile.length);
		byte[][] dst_addr = new byte[mobile.length + 1][CMPP.CMPPE_MAX_MSISDN_LEN];

		byte src_addr[] = StrToByte(sms_icp_code);
		for (int i = 0; i < mobile.length; i++) {
			byte[] tmp = StrToByte(mobile[i]);
			for (int j = 0; j < tmp.length; j++) {
				dst_addr[i][j] = tmp[j];
			}
		}

		byte du_count = (byte) mobile.length;

		BocoLog.info(this, String.valueOf(du_count));
		byte data_coding = 0xf;
		byte[] short_msg = StrToByte(msg);
		int sm_len = short_msg.length;

		BocoLog.info(this, "short message length:" + sm_len);
		BocoLog.info(this, "msg:" + new String(short_msg));
		try {
			sub.set_icpid(icp_id);
			sub.set_svctype(svc_type);
			sub.set_feetype(fee_type);
			sub.set_infofee(info_fee);
			sub.set_protoid(proto_id);
			sub.set_msgmode(msg_mode);
			sub.set_priority(priority);
			sub.set_validate(validate);
			sub.set_schedule(schedule);
			sub.set_feeutype(fee_utype);
			// sub.set_feeuser(fee_user);
			sub.set_srcaddr(src_addr);
			sub.set_dstaddr(dst_addr);
			sub.set_ducount(du_count);
			sub.set_msg(data_coding, sm_len, short_msg);
		} catch (Exception e) {
			BocoLog.error(this, e.getMessage(), e.fillInStackTrace());
		}
		return sub;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	private byte[] StrToByte(String str) {
		byte[] tmp = str.getBytes();
		byte[] ret = new byte[tmp.length + 1];
		for (int i = 0; i < tmp.length; i++) {
			ret[i] = tmp[i];
		}
		ret[tmp.length] = 0x0;
		return ret;
	}

	/**
	 * 
	 * @param tel
	 * @param msg
	 * @return
	 */
	public static void sendSms(String tel, String msg, String sms_host_ip,
			int sms_port, String sms_user, String sms_pwd, String sms_icp_id,
			String sms_icp_code) {
		int user_seq;
		CMPP p = new CMPP();
		TawCommonMessageSendTel sms = new TawCommonMessageSendTel();

		Date date = new Date();
		int s_min = (int) (date.getTime() / 1000);
		String ret = "";
		try {
			conn_desc con = new conn_desc();

			p.cmpp_connect_to_ismg(sms_host_ip, sms_port, con);

			p.cmpp_login(con, sms_user, sms_pwd, 2, 0x12, s_min);

			System.out.println(sms.readPa(con));
			cmppe_submit sub = sms.initCMPP(tel, msg, sms_icp_id, sms_icp_code);
			for (int test_count = 0; test_count < 1; test_count++) {
				user_seq = p.cmpp_submit(con, sub);
				ret = sms.readPa(con);
				BocoLog.info(TawCommonMessageSendTel.class, String
						.valueOf(user_seq));
				BocoLog.info(TawCommonMessageSendTel.class, ret);
			}

			p.cmpp_active_test(con);

			p.cmpp_logout(con);

		} catch (Exception e) {

			BocoLog.error(TawCommonMessageSendTel.class, e.getMessage());

		}

	}
	/**
	 * 返回MOBILE的SMS属性
	 * @return
	 */
	public static TawCommonMessageMobile getMessageMobileSms(){
	ApplicationContext ctx = new ClassPathXmlApplicationContext("config/message.xml");

	TawCommonMessageMobile preparator = (TawCommonMessageMobile)ctx.getBean("messagemobile");
	
	return preparator;
	}

}
