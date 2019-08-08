package readme;

import java.io.File;
import java.util.Date;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.PropertyFile;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.message.dao.ISmsOuterConfig;
import com.boco.eoms.message.util.MsgHelp;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitRepMessage;
import com.huawei.insa2.util.Args;
import com.huawei.insa2.util.Cfg;
import com.huawei.smproxy.SMProxy;

public class SmsOuterConfigImpl_HuaWei implements ISmsOuterConfig {
    static SMProxy myProxy = null;


    /**
     * 发送短信方法
     *
     * @param mobiles 手机号
     * @param content 消息内容
     * @return 是否成功标示，短信网关使用的是华为提供的jar，目前短信配置提取来自于boco.xml配置文件；
     */
    public boolean sendSms(String mobiles, String content) {
        boolean returnStr = true;
        String ret = "OK";

        //移动手机短信不能超过140，否则发送短信出错！
//	    mobiles=StaticMethod.FixedStr(mobiles,135,140,".");
        //移动手机短信不能超过140，否则发送短信出错！

        try {
            if (!(StaticMethod.nullObject2String(mobiles).equals("") ||
                    StaticMethod.nullObject2String(content).equals(""))) {
                if (myProxy == null) {
                    Args args = initConfig();
                    if (args == null) {
                        ret = "配置文件不存在";
                        returnStr = false;
                    }
                    myProxy = new SMProxy(args);
                } else if (myProxy.getConnState() != null) {
                    Args args = initConfig();
                    if (args == null) {
                        ret = "配置文件不存在";
                        returnStr = false;
                    }
                    myProxy = new SMProxy(args);
                }
                if (myProxy != null) {
                    //发送短信
                    synchronized (myProxy) {
                        CMPPSubmitMessage submitMsg = initCMPP(mobiles, content);
                        System.out.println("submit start");
                        CMPPSubmitRepMessage submitRepMsg = (CMPPSubmitRepMessage) myProxy.
                                send(
                                        submitMsg);
                        System.out.println("submit end");
                        if (submitRepMsg.getResult() != 0) {
                            ret = "短信发送失败:error result=" + submitRepMsg.getResult();
                            returnStr = false;
                            //短信派发失败后的预防处理！,我们系统原有的失败处理方式，在三期上不知如何处理，暂时注释
	              /*
	              String[] dest_Terminal_Id = this.checkTel(mobiles).split(",");
	              for(int i =0;i<dest_Terminal_Id.length ;i++){
	                  if(dest_Terminal_Id[i].length()>0){//如果电话号码不为空
	                  RecordSet rs = new RecordSet();
	                  String sql ="insert into taw_sms_newadd (tel_no,hie_content) values('"+
	                        dest_Terminal_Id[i]+"','Re:"+content+"')";
	                  try{
	                    rs.execute(StaticMethod.strFromPageToDB(sql));
	                  }catch(Exception  e){}
	                  returnStr=false; 
	                  BocoLog.info(this,1000,"Send_Msg_Error:"+sql);
	                  }
	              }*/
                            ////短信派发失败后的预防处理！

                        }
                    }
                } else {
                    ret = "连接短信网关失败";
                    returnStr = false;
                }
            } else {
                ret = "手机号或短信内容为空";
                returnStr = false;
            }
        } catch (Exception e) {
            ret = e.getMessage();
            e.printStackTrace(); //异常处理
        }
        BocoLog.info(this, mobiles + "手机号发送短信内容为：" + content + "时:" + ret);
        return returnStr;
    }


    private CMPPSubmitMessage initCMPP(String tel, String msg) {
        CMPPSubmitMessage submitMsg = null;
        try {
            if (StaticMethod.nullObject2String(tel) == "" ||
                    StaticMethod.nullObject2String(msg) == "") {
                return null;
            } else {
                int pk_Total = 1;
                int pk_Number = 1;
                int registered_Delivery = 1;
                int msg_Level = 3;
                String service_Id = MsgHelp.getSingleProperty("//message/msg/sms_icp_code");
                int fee_UserType = 2;
                String fee_Terminal_Id = "";
                int tp_Pid = 0;
                int tp_Udhi = 0;
                int msg_Fmt = 15;
                String msg_Src = MsgHelp.getSingleProperty("//message/msg/sms_icp_id");
                String fee_Type = "01";
                String fee_Code = "10";
                Date valid_Time = null;
                Date at_Time = null;
                String src_Terminal_Id = MsgHelp.getSingleProperty("//message/msg/sms_icp_code");
                tel = this.checkTel(tel);//手机号码合法性检查
                String[] dest_Terminal_Id = tel.split(",");

                //移动手机号不能超过11，否则发送短信出错！
                for (int i = 0; i < dest_Terminal_Id.length; i++) {
                    if (dest_Terminal_Id[i].length() > 11) {
                        dest_Terminal_Id[i] = dest_Terminal_Id[i].substring(0, 11);
                    }
                }
                //移动手机号不能超过11，否则发送短信出错！

                byte[] msg_Content = msg.getBytes();
                String reserve = "";

                submitMsg =
                        new CMPPSubmitMessage(
                                pk_Total,
                                pk_Number,
                                registered_Delivery,
                                msg_Level,
                                service_Id,
                                fee_UserType,
                                fee_Terminal_Id,
                                tp_Pid,
                                tp_Udhi,
                                msg_Fmt,
                                msg_Src,
                                fee_Type,
                                fee_Code,
                                valid_Time,
                                at_Time,
                                src_Terminal_Id,
                                dest_Terminal_Id,
                                msg_Content,
                                reserve
                        );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return submitMsg;
    }

    public Args initConfig() {
        Args args = null;
        PropertyFile prop = PropertyFile.getInstance();
        String configfile = prop.getFilePath() + File.separator + "SMProxy.xml";
        System.out.println("配置文件：" + configfile);
        File file = new File(configfile);
        try {
            if (file.exists()) {
                args = new Cfg(configfile, false).getArgs("ismg");
                System.out.println("args:" + args.toString());
            }
            file = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return args;
    }


    /**
     * 手机合法性校验
     *
     * @param tel 手机号
     * @return
     */
    public String checkTel(String tel) {
        String mobeil = "";
        boolean flag = false;
        int length = tel.length();
        for (int i = 0; i < length; i++) {
            int v = (int) tel.charAt(i);

            if (v == 44 && flag) {
                mobeil += tel.charAt(i);
                flag = false;
            } else if (v >= 48 && v <= 57) {
                mobeil += tel.charAt(i);
                flag = true;
            }
        }
        return mobeil;
    }


    public boolean sendVoice(String t_no, String t_alloc_time, String t_finish_time, String t_content, String t_tel_num, String t_tel_num2, String dispatch_tel) {
        // TODO 自动生成方法存根
        return false;
    }
}
