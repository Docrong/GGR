package com.boco.eoms.filemanager.extra.statistic.util;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public interface StaticStatistic {

  //added by wuhao begin SUN, MON, TUE, WED, THU, FRI and SAT
  public static final int SUN=1;
  public static final int MON=2;
  public static final int TUE=3;
  public static final int WED=4;
  public static final int THU=5;
  public static final int FRI=6;
  public static final int SAT=7;

  public static final int PAST_MON=-5;
  public static final int PAST_TUE=-4;
  public static final int PAST_WED=-3;
  public static final int PAST_THU=-2;
  public static final int PAST_FRI=-1;
  public static final int PAST_SAT=0;
  //ͳ������
  public static final int WEEKLY=1;
  public static final int MONTH=2;
  public static final int TIMETABLE=3;
   public static final int DAILY=4;

  //ͳ�����
  public static final int STAT_FAULT_CLASS=1;
  public static final int STAT_FAULT_SPECIALTY=2;
  public static final int STAT_FAULT_RETRANSMIT=3;
  public static final int STAT_FAULT_INTIME=4;
  public static final int STAT_FAULT_CONMAINTENCECS=68; //��άͳ�Ʊ���(����)
  public static final int STAT_FAULT_CONMAINTENCEJH=69; //��άͳ�Ʊ���(����)
  public static final int STAT_FAULT_FAULTCAUSE=70;//����ԭ��ͳ�Ʊ���
  public static final int STAT_FAULT_DETAIL=14;//���Ϲ���-��ϸ���
  public static final int STAT_FAULT=89;//���Ϲ���-�鵵��ʱ
  public static final int STAT_FAULT_BASE=90;//���Ϲ���-�鵵��ʱ(��վ)
  public static final int STAT_FAULT_WUXIAN=91;//���Ϲ���-�鵵��ʱ��������
  public static final int STAT_APPLY_LOCAL=25; //��湤��ʡ�����?�ܱ�1��
  public static final int STAT_APPLY_PROVINCE=6; //��湤������������Ͷ��ָ��
  public static final int STAT_APPLY_PROVINCE08=66; //��湤������������Ͷ��ָ��08
  public static final String STAT_APPLY_PROVINCE_SOLVE="���ʱ��";//��湤������������Ͷ��ָ��08
  public static final String STAT_APPLY_PROVINCE_TRANS="ת�ɼ�ʱ��";//��湤������������Ͷ��ָ��08
  public static final String STAT_APPLY_PROVINCE_REPLY="�ظ�׼ȷ��";//��湤������������Ͷ��ָ��08
  public static final String STAT_APPLY_PROVINCE_LINK_REINTIME="replyIntime";//��湤������������Ͷ��ָ��08
  public static final String STAT_APPLY_PROVINCE_LINK_REOUTTIME="replyOuttime";//��湤������������Ͷ��ָ��08
  public static final String STAT_APPLY_PROVINCE_LINK_TRINTIME="transIntime";//��湤������������Ͷ��ָ��08
  public static final String STAT_APPLY_PROVINCE_LINK_TROUTTIME="transOuttime";//��湤������������Ͷ��ָ��08
  public static final String STAT_APPLY_PROVINCE_LINK_SOINTIME="solveIntime";//��湤������������Ͷ��ָ��08
  public static final String STAT_APPLY_PROVINCE_LINK_SOOUTTIME="solveOuttime";//��湤������������Ͷ��ָ��08

  public static final int STAT_APPLY_SPECIALTY=7;   //��湤��-Ͷ������
  public static final int STAT_APPLY_HLHT=8;  //��湤��-��l��ͨ
  public static final int STAT_APPLY_COVER=9;  //��湤��-������
  public static final int STAT_APPLY_LOCALTOTAL=36;//ʡ������Ͷ��ָ���ܱ�

  public static final int STAT_TASK_INTIME=10;  //���񹤵�ͳ��
  public static final int STAT_DATA_INTIME=11;  //����ݹ���ͳ��
  public static final int STAT_RESOURCE_INTIME=12;  //��Դ����ͳ��
  public static final int STAT_APPLY_CITY=13;  //��湤��ʡ�����?�ܱ�2��
  public static final int STAT_APPLY_GPRS=15;  //��湤��ʡ�����?GPRS��
  public static final int STAT_APPLY_SMS=16;  //��湤��ʡ�����?SMS��
  public static final int STAT_APPLY_EXCHANGE=17;  //��湤��ʡ�����?������
  public static final int STAT_APPLY_APTITUDE=18;  //��湤��ʡ�����?������
  public static final int STAT_APPLY_SN=19;  //��湤��ʡ�����?���ڣ�
  public static final int STAT_APPLY_SW=20;  //��湤��ʡ�����?���⣩
  public static final int STAT_APPLY_DH=21;  //��湤��ʡ�����?���
  public static final int STAT_APPLY_YS=22;  //��湤��ʡ�����?ӵ��
  public static final int STAT_APPLY_HYZL=23;  //��湤��ʡ�����?��������
  public static final int STAT_APPLY_WIRELESS=24;  //��湤��ʡ�����?��������Ͷ�ߣ�
  public static final int STAT_APPLY_MY=26;  //��湤������-�߽�����
  public static final int STAT_SHEET_COUNT=30;  //���๤�����?����ͳ��
  public static final int STAT_APPLY_SCHEDULER = 31; //��湤����ѭ
  public static final int STAT_FAULT_SCHEDULER = 32;//���Ϲ�����ѭ
  public static final int STAT_TASK_SCHEDULER = 33;//���񹤵���ѭ
  public static final int STAT_APPLY_PROVINCET_TOTAL=36;//��湤��ʡ���ܱ�2
  public static final int STAT_AAPLY_PROVINCE_TRANSFEE=801;//��湤��ʡ�����?ͨ�ŷ��ã�
  public static final int STAT_AAPLY_PROVINCE_COMMBARIIRA=802;//��湤��ʡ�����?ͨ���ϰ���
  public static final int STAT_AAPLY_PROVINCE_BUSSINESSUSE=803;//��湤��ʡ�����?ҵ��ʹ�ã�
  public static final int STAT_AAPLY_PROVINCE_BUSSINESSHANDLE=804;//��湤��ʡ�����?ҵ����?
  public static final int STAT_AAPLY_PROVINCE_SP=805;//��湤��ʡ�����?SP�ࣩ
  public static final int STAT_AAPLY_PROVINCE_SERVICEQUA=806;//��湤��ʡ�����?��������
  public static final int STAT_FAULTSHEETPACK_CHECK=807;//���ϴ���ʼ� 
  //���ϼ���
  public static final int FAULT_CLASS_LEADER=6;
  public static final int FAULT_CLASS_STRICTNESS=5;
  public static final int FAULT_CLASS_URGENCY=4;
  public static final int FAULT_CLASS_IMPORTANT=3;
  public static final int FAULT_CLASS_SUBORDINATION=1;
  public static final int FAULT_CLASS_COMMON=2;

  //����רҵ����
public static final int FAULT_SPECIALTY_CS=1;
public static final int FAULT_SPECIALTY_SJ=6;
public static final int FAULT_SPECIALTY_DX=7;
public static final int FAULT_SPECIALTY_GPRS=8;
public static final int FAULT_SPECIALTY_CTW=14;
public static final int FAULT_SPECIALTY_BDW=15;
public static final int FAULT_SPECIALTY_HLHT=16;
public static final int FAULT_SPECIALTY_JF=17;
public static final int FAULT_SPECIALTY_XYW=18;
public static final int FAULT_SPECIALTY_WG=19;
public static final int FAULT_SPECIALTY_RJH=20;
public static final int FAULT_SPECIALTY_WLAQSJ=21;
public static final int FAULT_SPECIALTY_ZDBDCSXT=22;
public static final int FAULT_SPECIALTY_DLHJ=24;
public static final int FAULT_SPECIALTY_YH=25;
public static final int FAULT_SPECIALTY_QTWLSB=26;
public static final int FAULT_SPECIALTY_QTYW=27;
public static final int FAULT_SPECIALTY_VIP=28;
public static final int FAULT_SPECIALTY_JITUAN=29;

  //Ͷ��רҵ����
 public static final String APPLY_SPECIALTY_GPRS="GPRS";
 public static final String APPLY_SPECIALTY_NOTE="����";
 public static final String APPLY_SPECIALTY_FWXM="������Ŀ";
 public static final String APPLY_SPECIALTY_FWZL="������";
 public static final String APPLY_SPECIALTY_FGSN="����-����";
 public static final String APPLY_SPECIALTY_FGSW="����-����";
 public static final String APPLY_SPECIALTY_HLHT="��j��ͨ";
 public static final String APPLY_SPECIALTY_HYZL="����-������";
 public static final String APPLY_SPECIALTY_MY="����-����";
 public static final String APPLY_SPECIALTY_JH="����-����";
 public static final String APPLY_SPECIALTY_CARD="����ҵ��";
 public static final String APPLY_SPECIALTY_KQFW="�������";
 public static final String APPLY_SPECIALTY_OTHER="��������Ͷ��";
 public static final String APPLY_SPECIALTY_DATA="�����";
 public static final String APPLY_SPECIALTY_WLFG="���縲��";
 public static final String APPLY_SPECIALTY_NETWORK="������";
 public static final String APPLY_SPECIALTY_BJMY="����-�߽�����";
 public static final String APPLY_SPECIALTY_DH="����-��";
 public static final String APPLY_SPECIALTY_WXHY="����-������";
 public static final String APPLY_SPECIALTY_YWL="ҵ���� ";
 public static final String APPLY_SPECIALTY_YWLHF="ҵ����-����";
 public static final String APPLY_SPECIALTY_YWSY="ҵ��ʹ��";
 public static final String APPLY_SPECIALTY_YWSL="ҵ������";
 public static final String APPLY_SPECIALTY_ZCXT="֧��ϵͳ";
 public static final String APPLY_SPECIALTY_ZNWCL="������-����";
 public static final String APPLY_SPECIALTY_EKHY="������-������һ";
 public static final String APPLY_SPECIALTY_ZNWO="������-����";
 public static final String APPLY_SPECIALTY_WLD="����-��Ե����";
//��湤��Ͷ��ԭ��
 public static final int APPLY_COMPLIANTCAUSE_OTHER=1;
 public static final int APPLY_COMPLIANTCAUSE_USER=2;
 public static final int APPLY_COMPLIANTCAUSE_DATA=3;
 public static final int APPLY_COMPLIANTCAUSE_NETWORK=4;


 //��湤��ҵ�����
public static final int APPLY_OPERATE_IP=7;
public static final int APPLY_OPERATE_LTBL=6;
public static final int APPLY_OPERATE_HMD=5;
public static final int APPLY_OPERATE_LDJLJ=4;
public static final int APPLY_OPERATE_PTHY=3;
public static final int APPLY_OPERATE_FY=2;
public static final int APPLY_OPERATE_OTHER=1;

 //��湤�������Ӫ��
public static final int APPLY_SELL_DX=5;
public static final int APPLY_SELL_LT=4;
public static final int APPLY_SELL_WAT=3;
public static final int APPLY_SELL_TT=2;
public static final int APPLY_SELL_WET=1;
 //��湤�����η�
public static final int APPLY_DUTY_DX=6;
public static final int APPLY_DUTY_LT=5;
public static final int APPLY_DUTY_WAT=4;
public static final int APPLY_DUTY_TT=3;
public static final int APPLY_DUTY_WET=2;
public static final int APPLY_DUTY_YD=1;

 //��湤��������
public static final int APPLY_SETTLE_DO=3;
public static final int APPLY_SETTLE_NO=2;
public static final int APPLY_SETTLE_ING=1;

//��湤��EOMSͶ������
 public static final int APPLY_TYPE_GPRS=2301;
 public static final int APPLY_TYPE_SMS=2302;
 public static final int APPLY_TYPE_HLHT=2303;
 public static final int APPLY_TYPE_OTHERLINE=2304;
 public static final int APPLY_TYPE_EXCHAGE=2305;
 public static final int APPLY_TYPE_APTITUDE=2306;
 public static final int APPLY_TYPE_SN=2307;
 public static final int APPLY_TYPE_SW=2308;
 public static final int APPLY_TYPE_DH=2309;
 public static final int APPLY_TYPE_YS=2310;
 public static final int APPLY_TYPE_HYZL=2311;
 public static final int APPLY_TYPE_OTHERWIRELESS=2312;
 public static final int APPLY_TYPE_FWXM=2313;
 public static final int APPLY_TYPE_FWZL=2314;
 public static final int APPLY_TYPE_CARD=2315;
 public static final int APPLY_TYPE_KQFW=2316;
 public static final int APPLY_TYPE_DATA=2317;
 public static final int APPLY_TYPE_HW=2318;
 public static final int APPLY_TYPE_YWSY=2319;
 public static final int APPLY_TYPE_YWSL=2320;
 public static final int APPLY_TYPE_ZCXT=2321;
//��湤��EOMSͶ��������
public static final int APPLY_TYPE_CHILD_GPRS=23001;
public static final int APPLY_TYPE_CHILD_SMS=23002;
public static final int APPLY_TYPE_CHILD_HLHT=23003;
public static final int APPLY_TYPE_CHILD_OTHERLINE=23004;
public static final int APPLY_TYPE_CHILD_EXCHAGEMY=23005;
public static final int APPLY_TYPE_CHILD_EXCHAGEZB=23006;
public static final int APPLY_TYPE_CHILD_EXCHAGEQH=23007;
public static final int APPLY_TYPE_CHILD_EXCHAGEHY=23008;
public static final int APPLY_TYPE_CHILD_EXCHAGEOTHER=23009;
public static final int APPLY_TYPE_CHILD_APTITUDEEK=23010;
public static final int APPLY_TYPE_CHILD_APTITUDEV=23011;
public static final int APPLY_TYPE_CHILD_APTITUDECL=23012;
public static final int APPLY_TYPE_CHILD_APTITUDEOTHER=23013;
public static final int APPLY_TYPE_CHILD_ZSG=23014;
public static final int APPLY_TYPE_CHILD_ZSD=23015;
public static final int APPLY_TYPE_CHILD_ZSYF=23016;
public static final int APPLY_TYPE_CHILD_ZSWF=23017;
public static final int APPLY_TYPE_CHILD_GSG=23018;
public static final int APPLY_TYPE_CHILD_GSD=23019;
public static final int APPLY_TYPE_CHILD_GSYF=23020;
public static final int APPLY_TYPE_CHILD_GSWF=23021;
public static final int APPLY_TYPE_CHILD_DXYF=23022;
public static final int APPLY_TYPE_CHILD_DXWF=23023;
public static final int APPLY_TYPE_CHILD_SWXQ=23024;
public static final int APPLY_TYPE_CHILD_SWFYD=23025;
public static final int APPLY_TYPE_CHILD_SWXY=23026;
public static final int APPLY_TYPE_CHILD_SWGC=23027;
public static final int APPLY_TYPE_CHILD_SWYDF=23028;
public static final int APPLY_TYPE_CHILD_DHXQ=23029;
public static final int APPLY_TYPE_CHILD_DHFYD=23030;
public static final int APPLY_TYPE_CHILD_DHXY=23031;
public static final int APPLY_TYPE_CHILD_DHGC=23032;
public static final int APPLY_TYPE_CHILD_DHYDF=23033;
public static final int APPLY_TYPE_CHILD_YSXQ=23034;
public static final int APPLY_TYPE_CHILD_YSFYD=23035;
public static final int APPLY_TYPE_CHILD_YSXY=23036;
public static final int APPLY_TYPE_CHILD_YSGC=23037;
public static final int APPLY_TYPE_CHILD_YSYDF=23038;
public static final int APPLY_TYPE_CHILD_HYZLXQ=23039;
public static final int APPLY_TYPE_CHILD_HYZLFYD=23040;
public static final int APPLY_TYPE_CHILD_HYZLXY=23041;
public static final int APPLY_TYPE_CHILD_HYZLGC=23042;
public static final int APPLY_TYPE_CHILD_HYZLYDF=23043;
public static final int APPLY_TYPE_CHILD_OTHERWIRELESS=23044;
public static final int APPLY_TYPE_CHILD_FWXM=23045;
public static final int APPLY_TYPE_CHILD_FWZL=23046;
public static final int APPLY_TYPE_CHILD_CARD=23047;
public static final int APPLY_TYPE_CHILD_KQFW=23048;
public static final int APPLY_TYPE_CHILD_DATA=23049;
public static final int APPLY_TYPE_CHILD_HF=23050;
public static final int APPLY_TYPE_CHILD_YWSY=23051;
public static final int APPLY_TYPE_CHILD_YESL=23052;
public static final int APPLY_TYPE_CHILD_ZCXT=23053;


//��湤��EOMSͶ��ԭ��
public static final int APPLY_CAUSE_JF=1;
public static final int APPLY_CAUSE_NETWORKDATA=2;
public static final int APPLY_CAUSE_USERDATA=3;
public static final int APPLY_CAUSE_NETWORKFAULT=4;
public static final int APPLY_CAUSE_TERMINAL=5;
public static final int APPLY_CAUSE_TANSMITDATA=6;
public static final int APPLY_CAUSE_NEEDCHECK=7;
public static final int APPLY_CAUSE_FG=8;
public static final int APPLY_CAUSE_OTHER=9;

//��湤��EOMSͶ����ԭ��
 public static final int APPLY_CAUSE_CHILD_FGYH=1;
 public static final int APPLY_CAUSE_CHILD_FGDJ=2;
 public static final int APPLY_CAUSE_CHILD_FGJH=3;
 public static final int APPLY_CAUSE_CHILD_FGUNDO=4;
 public static final int APPLY_CAUSE_CHILD_NETWORKFAULT=5;
 public static final int APPLY_CAUSE_CHILD_NETWORKDATA=6;
 public static final int APPLY_CAUSE_CHILD_USERDATA=7;
 public static final int APPLY_CAUSE_CHILD_TERMINAL=8;
 public static final int APPLY_CAUSE_CHILD_JF=9;
 public static final int APPLY_CAUSE_CHILD_TANSMITDATA=10;
 public static final int APPLY_CAUSE_CHILD_NEEDCHECK=11;
 public static final int APPLY_CAUSE_CHILD_OTHER=12;

//������-�߽����Ρ�Ͷ������
 public static final int APPLY_MY_NORMAL=2401;
 public static final int APPLY_MY_SJMY=2402;
 public static final int APPLY_MY_SNMY=2403;
 public static final int APPLY_MY_OTHER=2404;
 public static final int APPLY_MY_SNMY_V=2400103;
 public static final int APPLY_MY_SNMY_JF=2400104;
 public static final int APPLY_MY_SNMY_DQ=2400105;
 public static final int APPLY_MY_SNMY_USER=2400106;



 //2007��Ͷ������
 //801ͨ�ŷ���
 public static final int APPLY_CF_MY_Fees = 80101;
 public static final int APPLY_CF_No_Regular = 80102;
 public static final int APPLY_CF_PP_CX = 80103;
 public static final int APPLY_CF_PP_DX = 80104;
 public static final int APPLY_CF_JT_Business = 80105;
 public static final int APPLY_CF_V = 80111;
 public static final int APPLY_CF_DisCount_UnDo = 80106;
 public static final int APPLY_CF_Local = 80109;
 public static final int APPLY_CF_IP_Fees = 80110;
 public static final int APPLY_CF_CaiLing = 80112;
 public static final int APPLY_CF_GPRS = 80113;
 //+
 public static final int APPLY_CF_Others=80114;
//802ͨ���ϰ�
 public static final int APPLY_ZA_HLHT = 80201;
 public static final int APPLY_ZA_MY = 80202;
 public static final int APPLY_ZA_No_Singal = 80203;
 public static final int APPLY_ZA_IP_Comm = 80204;
 public static final int APPLY_ZA_Status_NoProblem = 80205;
 public static final int APPLY_ZA_Comm_Qua = 80206;
//+
public static final int APPLY_ZA_V_CALL=80207;
//803ҵ��ʹ��
 public static final int APPLY_YWSY_PP_DX = 80301;
 public static final int APPLY_YWSY_PP_CX = 80302;
 public static final int APPLY_YWSY_WAP = 80303;
 public static final int APPLY_YWSY_GPRS = 80304;
 public static final int APPLY_YWSY_E = 80305;
 public static final int APPLY_YWSY_CaiLing = 80306;
 public static final int APPLY_YWSY_TDisPlay = 80308;
 public static final int APPLY_YWSY_HZ_SZ = 80307;
 public static final int APPLY_YWSY_Others = 80309;
 public static final int AAPLY_YWSY_JTBussiness=80310;
//804 ҵ�����
 public static final int APPLY_YWBL_Pay = 80401;
 public static final int APPLY_YWBL_NewCard_UnDo = 80402;
 public static final int APPLY_YWBL_HandleSelf_UnDo = 80403;
 //+
 public static final int APPLY_YWBL_ConductBusiness_UnProperty=80405;
 public static final int APPLY_YWBL_Cards = 80407;
//+
 public static final int APPLY_YWBL_ConductBusiness_Unauthorized=80408;
//805SP��
 public static final int APPLY_SP_UnloadSubscribe_Barrier = 80501;
 public static final int APPLY_SP_Subscribe_Suspicions = 80502;
 public static final int APPLY_SP_Fees_Suspicions = 80503;
 public static final int APPLY_SP_Exercise_Barrier = 80504;
 public static final int APPLY_SP_ServiceQua = 80505;
 public static final int APPLY_SP_QST = 80506;
//806 ������
 public static final int APPLY_SQ_BusinessP = 80601;
 public static final int APPLY_SQ_10086 = 80602;
 public static final int APPLY_SQ_CustomerMan = 80603;
 public static final int APPLY_SQ_TotalScore = 80604;
 public static final int APPLY_SQ_Publicize_NotDistinct = 80605;
 public static final int APPLY_SQ_MailChecks_UnRecive = 80606;
 public static final int APPLY_SQ_RubbishInfo = 80607;
 public static final int APPLY_SQ_UnSatisify_CustomerToResult  = 80608;
 public static final int APPLY_SQ_UnSatisify_CustomerToRegular = 80609;
//+
 public static final int APPLY_SQ_VIP_Service_AirPort=80610;
 //+
 public static final int APPLY_SQ_VIP_Service_Others=80611;
//+
 public static final int APPLY_SQ_UnRegularInfo=80508;
//��Ͷ���๤��Ͷ������
 public static final String APPLY_NotComplaint_YWSL = "31101";
 public static final String APPLY_NotComplaint_CustomerSuggestion = "31102";
 public static final String APPLY_NotComplaint_IntegrateSupport="31103";
 public static final String APPLY_NotComplaint_InterviewCall="31104";
 public static final String APPLY_NotComplaint_YWSLInner="312";
 public static final String APPLY_NotComplaint_UnSmoothRecording="313";



 //Ͷ��ԭ��
  public static int apply_wl_BJMY =91401;
  public static int apply_wl_HLHT =91402;
  public static int apply_wl_WLGZ =91403;
  public static int apply_wl_WLGJ =91404;
  public static int apply_wl_WLSJ =91405;
  public static int apply_wl_FGBH =91406;

  public static int apply_sjyw_sp  =91501;
  public static int apply_sjyw_DateSys  =91502;
  public static int apply_sjyw_PointMerchant  =91503;
  public static int apply_sjyw_CaiXinUnCut  =91504;
  public static int apply_sjyw_businessAcc = 91505;
  public static int apply_sjyw_businessFee = 91506;
  public static int apply_sjyw_businessInterface = 91507;

  public static int apply_rbinfo_UnRegularP = 91601;
  public static int apply_rbInfo_regularP=91602;
  public static int apply_rbInfo_mpnInPro=91603;
  public static int apply_rbInfo_mpnOutPro=91604;
  public static int apply_rbInfo_NotInnerNum=91605;

  public static int apply_cus_suggestion=91001;
  public static int apply_cus_consult =91002;
  public static int apply_cus_cusTerminal  =91003;
  public static int apply_cus_cuseOpertion  =91004;
  public static int apply_cus_volenceComplaint  =91005;
  public static int apply_cus_applyerr=91006;
  public static int apply_cus_unableuse= 91007;
  public static int apply_cus_blurCause=91008;

  public static int apply_man_employee  =91101;
  public static int apply_man_NotInnerEmployee  =91102;
  public static int apply_man_manufacturesMan  =91103;
  public static int apply_man_publicize  =91104;
  public static int apply_man_spModifyBussiness  =91105;
  public static int apply_man_employeeModifyBussiness  =91106;
  public static int apply_man_HLR  =91107;


  public static int apply_boss_Countfee   =91201;
  public static int apply_boss_cosys  =91202;
  public static int apply_boss_zwsys  =91203;
  public static int apply_boss_payFault=  91204;
  public static int apply_boss_SelfBussiness  =91205;
  public static int apply_boss_KFBarriar  =91206;
  public static int apply_boss_reportingErr=  91207;

//�ӿ�

  public static int apply_interface_boss_ismg = 91308;
  public static int apply_interface_selfBussiness = 91302;
  public static int apply_interface_bankInnet = 91303;


  public static int apply_interface_bosscl_wl=  9130101;
  public static int apply_interface_bosscl_ywzc  =9130102;
  public static int apply_interface_bossvc_wl  =9130401;
  public static int apply_interface_bossvs_ywzc  =9130402;
  public static int apply_interface_bossscp_v_ywzc  =913050101;
  public static int apply_interface_boosscp_v_wl  =913050102;
  public static int apply_interface_bossscp_other_ywzc = 913050201;
  public static int apply_interface_bossscp_other_wl = 913050202;
  public static int apply_interface_bosshlr_ywzc  =9130601;
  public static int apply_interface_bosshlr_wl  =9130602;
  public static int apply_interface_bossmisc_sj = 9130702;
  public static int apply_interface_bossmisc_ywzc = 9130701;
  
  
  
  public static final int STAT_APPLY_SATISF=808;//��湤������ط������ͳ��  add by daizhigang 2008-4-25 
  public static final int STAT_APPLY_WORKLOAD=809;//�ɵ����Ԥ�����鹤��ͳ��  add by daizhigang 2008-5-4
  public static int HOLD_SATISF_VERY = 1;
  public static int HOLD_SATISF_YES = 2;
  public static int HOLD_SATISF_NORMAL = 3;
  public static int HOLD_SATISF_NO = 4;
  public static int HOLD_SATISF_DESPOND = 5;



































































}
