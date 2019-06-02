package com.boco.eoms.filemanager.extra.statistic.dao;
import java.util.List;
import java.util.Calendar;
import org.hibernate.HibernateException;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.hibernate.Query;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.common.dao.HibernateDAO;
import org.hibernate.type.Type;
import com.boco.eoms.filemanager.extra.statistic.util.StaticStatistic;
import com.boco.eoms.db.util.BocoConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.boco.eoms.common.log.BocoLog;

public class AutoStatisticDAO extends HibernateDAO{
  public AutoStatisticDAO() {
  }


  public int getXYZ(String preSubId) throws HibernateException {
    String subId = preSubId + "%";
    int count = count("from StSubscription as stSubscription where " +
                      "stSubscription.subId like %"+subId+"%");
    return count;
  }

  public List listSubscribe(String userid, int deptid, int[] pagePra) throws
      HibernateException {
    Session s = HibernateUtil.currentSession();
    String hsql = "";
    hsql = "from StSubscription as stSubscription where " +
        "stSubscription.subscribeDept  = " + deptid +
        " and stSubscription.deleted = 0 ";

    if (pagePra[2] <= 0) {
      pagePra[2] = count(hsql);
    }
    Query query = s.createQuery(hsql);
    query.setCacheable(true);
    query.setFirstResult(pagePra[0]);
    query.setMaxResults(pagePra[1]);
    List list = query.list();
    return list;
  }

  public List listStatFaultsheetDetail(String link, String linkType) throws
      HibernateException {
    Session s = HibernateUtil.currentSession();
    String hsql = "";
    if (linkType.equals("both"))
      hsql = "from StFaultsheetDetail as stFaultsheetDetail where " +
          "stFaultsheetDetail.link  = '" + link + "'";
    else
      hsql = "from StFaultsheetDetail as stFaultsheetDetail where " +
          "stFaultsheetDetail.link  = '" + link +
          "' and stFaultsheetDetail.linkType = '" + linkType + "'";

    Query query = s.createQuery(hsql);
    query.setCacheable(true);
    List list = query.list();
    return list;

  }

  public void delStatFaultsheetDetail(String link, String linkType) throws
      Exception {
    try {
      BocoConnection conn = null;
      PreparedStatement pstmt = null;
      Session s = HibernateUtil.currentSession();
      conn = (BocoConnection) s.connection();
      String sql = "";
      if (linkType.equals("both")) {
        sql = "delete  from st_faultsheet_detail where link  =? ";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, link);
      }
      else {
        sql =
            "delete  from st_faultsheet_detail where link  =? and link_type=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, link);
        pstmt.setString(2, linkType);
      }
      pstmt.executeUpdate();
    }
    catch (Exception e) {

      e.printStackTrace();
    }
  }

   public List listReport( int[] pagePra,int item) throws
         HibernateException {
       Session s = HibernateUtil.currentSession();
       String hsql = "";

         hsql = "from StTask as stTask where " +
              " stTask.result=1 and stTask.deleted = 0 and stTask.item= "+item+
              " order by stTask.beginTime desc";



         if (pagePra[2] <= 0) {
          pagePra[2] = count(hsql);
        }
        Query query = s.createQuery(hsql);
        query.setCacheable(true);
        query.setFirstResult(pagePra[0]);
        query.setMaxResults(pagePra[1]);
        List list = query.list();
        return list;
      }

      public List listRecollection(String userid, int deptid, int[] pagePra) throws
          HibernateException {
        Session s = HibernateUtil.currentSession();
        String hsql = "";
          hsql = "from StTask as stTask where " +
              "stTask.subscribeDept  = " + deptid +
              " and stTask.result=0 and stTask.deleted = 0 ";

          if (pagePra[2] <= 0) {
           pagePra[2] = count(hsql);
         }
         Query query = s.createQuery(hsql);
         query.setCacheable(true);
         query.setFirstResult(pagePra[0]);
         query.setMaxResults(pagePra[1]);
         List list = query.list();
         return list;
       }

       public List listTaskFromTaskId(String taskId) throws
              HibernateException {
            Session s = HibernateUtil.currentSession();
            String hsql = "";
              hsql = "from StTask as stTask where " +
                   "stTask.taskId='"+taskId+"' and stTask.deleted = 0 ";
             Query query = s.createQuery(hsql);
             query.setCacheable(true);
             List list = query.list();
             return list;
           }

   public List listAllSubscribe() throws
       HibernateException {
     Session s = HibernateUtil.currentSession();
     String hsql = "";
     hsql = "from StSubscription as stSubscription where " +
         " stSubscription.deleted = 0 ";
     Query query = s.createQuery(hsql);
     query.setCacheable(true);
     List list = query.list();
     return list;
   }

   public List listSubscribeFromSubId(String subId) throws
       HibernateException {
     Session s = HibernateUtil.currentSession();
     String hsql = "";
     hsql = "from StSubscription as stSubscription where " +
         " stSubscription.subId = '"+subId+ "' and stSubscription.deleted = 0";
     Query query = s.createQuery(hsql);
     query.setCacheable(true);
     List list = query.list();
     return list;
   }

   public List listReportFromTaskId(String taskId,int item) throws
      HibernateException {
    Session s = HibernateUtil.currentSession();
    String hsql = "";
    switch(item){
      case StaticStatistic.STAT_FAULT_CLASS:{
        hsql = "from StFaultclass as stFaultclass where " +
        " stFaultclass.taskId = '"+taskId+ "' and stFaultclass.deleted = 0";
        break;
      }
      case StaticStatistic.STAT_FAULT_SPECIALTY:{
       hsql = "from StFaultspecialty as stFaultspecialty where " +
       " stFaultspecialty.taskId = '"+taskId+ "' and stFaultspecialty.deleted = 0";
       break;
     }
     case StaticStatistic.STAT_FAULT_RETRANSMIT:{
       hsql = "from StFaultretransmit as stFaultretransmit where " +
       " stFaultretransmit.taskId = '"+taskId+ "' and stFaultretransmit.deleted = 0";
       break;
     }
     case StaticStatistic.STAT_FAULT_INTIME:{
       hsql = "from StFaultintime as stFaultintime where " +
       " stFaultintime.taskId = '"+taskId+ "' and stFaultintime.deleted = 0";
       break;
     }
     case StaticStatistic.STAT_FAULT_DETAIL:{
       hsql = "from StFaultdetail as stFaultdetail where " +
      " stFaultdetail.taskId = '"+taskId+ "'";
      break;
     }
     case StaticStatistic.STAT_APPLY_LOCAL:{
        hsql = "from StApplyLocal as stApplyLocal where " +
               " stApplyLocal.taskId = '"+taskId+ "' and stApplyLocal.deleted = 0";
        break;
      }
    case StaticStatistic.STAT_APPLY_PROVINCE:{
        hsql = "from StApplyprovince as stApplyprovince where " +
                    " stApplyprovince.taskId = '"+taskId+ "' and stApplyprovince.deleted = 0";
             break;
           }
    case StaticStatistic.STAT_APPLY_PROVINCE08:{
        hsql = "from StApplyprovince08 as stApplyprovince08 where " +
                    " stApplyprovince08.taskId = '"+taskId+ "' and stApplyprovince08.deleted = 0";
             break;
           }
    case StaticStatistic.STAT_APPLY_SPECIALTY:{
        hsql = "from StApplyspecialty as stApplyspecialty where  " +
           " stApplyspecialty.taskId = '"+taskId+ "' and stApplyspecialty.deleted = 0";
           break;
        }
     case StaticStatistic.STAT_APPLY_HLHT:{
        hsql = "from StApplyhlht as stApplyhlht where  " +
               " stApplyhlht.taskId = '"+taskId+ "' and stApplyhlht.deleted = 0";
        break;
      }
     case StaticStatistic.STAT_APPLY_COVER:{
             hsql = "from StApplycover as stApplycover where  " +
                " stApplycover.taskId = '"+taskId+ "' and stApplycover.deleted = 0";
                break;
             }
    case StaticStatistic.STAT_TASK_INTIME:{
              hsql = "from StTaskintime as stTaskintime where  " +
              " stTaskintime.taskId = '"+taskId+ "' and stTaskintime.deleted = 0";
              break;
        }
    case StaticStatistic.STAT_DATA_INTIME:{
            hsql = "from StDataintime as stDataintime where  " +
                     " stDataintime.taskId = '"+taskId+ "' and stDataintime.deleted = 0";
                     break;
               }
    case StaticStatistic.STAT_RESOURCE_INTIME:{
            hsql = "from StResourceintime as stResourceintime ,TawSheetorder tawSheetorder where  " +
                   " stResourceintime.taskId = '"+taskId+ "' and stResourceintime.deleted = 0"+
                   " and (stResourceintime.receiveDeptid=tawSheetorder.deptId "+
                   " or stResourceintime.sendDeptid=tawSheetorder.deptId)  "+
                   " order by tawSheetorder.order";
            break;
     }
     case StaticStatistic.STAT_APPLY_CITY:{
             hsql = "from StApplyCity as stApplyCity  where  " +
                    " stApplyCity.taskId = '"+taskId+ "' and stApplyCity.deleted = 0";
             break;
      }
      case StaticStatistic.STAT_APPLY_SW:{
        hsql = "from StApplySw as stApplySw  where  " +
                    " stApplySw.taskId = '"+taskId+ "' and stApplySw.deleted = 0";
          break;
     }
      case StaticStatistic.STAT_APPLY_GPRS:{
        hsql = "from StApplyGprs as stApplyGprs  where  " +
                    " stApplyGprs.taskId = '"+taskId+ "' and stApplyGprs.deleted = 0";
         break;
    }
      case StaticStatistic.STAT_APPLY_SMS:{
        hsql = "from StApplySms as stApplySms  where  " +
                    " stApplySms.taskId = '"+taskId+ "' and stApplySms.deleted = 0";
        break;
   }
      case StaticStatistic.STAT_APPLY_EXCHANGE:{
        hsql = "from StApplyExchange as stApplyExchange  where  " +
                    " stApplyExchange.taskId = '"+taskId+ "' and stApplyExchange.deleted = 0";
        break;
  }
      case StaticStatistic.STAT_APPLY_APTITUDE:{
        hsql = "from StApplyAptitude as stApplyAptitude  where  " +
                    " stApplyAptitude.taskId = '"+taskId+ "' and stApplyAptitude.deleted = 0";
       break;
 }
      case StaticStatistic.STAT_APPLY_SN:{
        hsql = "from StApplySn as stApplySn  where  " +
                    " stApplySn.taskId = '"+taskId+ "' and stApplySn.deleted = 0";
        break;
 }
      case StaticStatistic.STAT_APPLY_DH:{
        hsql = "from StApplyDh as stApplyDh  where  " +
                    " stApplyDh.taskId = '"+taskId+ "' and stApplyDh.deleted = 0";
        break;
      }
      case StaticStatistic.STAT_APPLY_YS:{
        hsql = "from StApplyYs as stApplyYs  where  " +
                    " stApplyYs.taskId = '"+taskId+ "' and stApplyYs.deleted = 0";
        break;
      }
      case StaticStatistic.STAT_APPLY_HYZL:{
        hsql = "from StApplyHyzl as stApplyHyzl  where  " +
                    " stApplyHyzl.taskId = '"+taskId+ "' and stApplyHyzl.deleted = 0";
        break;
      }
      case StaticStatistic.STAT_APPLY_WIRELESS:{
        hsql = "from StApplyWireless as stApplyWireless  where  " +
                    " stApplyWireless.taskId = '"+taskId+ "' and stApplyWireless.deleted = 0";
        break;
      }
      case StaticStatistic.STAT_APPLY_MY:{
        hsql = "from StApplyMy as stApplyMy  where  " +
                    " stApplyMy.taskId = '"+taskId+ "' and stApplyMy.deleted = 0";
        break;
      }
      case StaticStatistic.STAT_APPLY_LOCALTOTAL:{
          hsql = "from  StApplyprovinceTotal as St where  " +
                      " St.taskId = '"+taskId+ "' and St.deleted = 0";
          break;
        }
      case StaticStatistic.STAT_AAPLY_PROVINCE_TRANSFEE:{
            hsql = "from  StApplyprovinceDictcause as St where  " +
                        " St.taskId = '"+taskId+ "' and St.deleted = 0";
            break;
          }
          case StaticStatistic.STAT_AAPLY_PROVINCE_COMMBARIIRA: {
            hsql = "from  StApplyprovinceDictcause as St where  " +
                " St.taskId = '" + taskId + "' and St.deleted = 0";
            break;
          }
          case StaticStatistic.STAT_AAPLY_PROVINCE_BUSSINESSUSE: {
            hsql = "from  StApplyprovinceDictcause as St where  " +
                " St.taskId = '" + taskId + "' and St.deleted = 0";
            break;
          }
          case StaticStatistic.STAT_AAPLY_PROVINCE_BUSSINESSHANDLE: {
            hsql = "from  StApplyprovinceDictcause as St where  " +
                " St.taskId = '" + taskId + "' and St.deleted = 0";
            break;
          }
          case StaticStatistic.STAT_AAPLY_PROVINCE_SP: {
            hsql = "from  StApplyprovinceDictcause as St where  " +
                " St.taskId = '" + taskId + "' and St.deleted = 0";
            break;
          }
          case StaticStatistic.STAT_AAPLY_PROVINCE_SERVICEQUA: {
            hsql = "from  StApplyprovinceDictcause as St where  " +
                " St.taskId = '" + taskId + "' and St.deleted = 0";
            break;
          }
        //add by daizhigang 2008-4-24 ��湤������ط������ͳ��
          case StaticStatistic.STAT_APPLY_SATISF: {
              hsql = "from  StApplySatisf as St where  " +
                  " St.taskId = '" + taskId + "' and St.deleted = 0";
              break;
            }
          //end by daizhigang 208-4-24
        //add by daizhigang 2008-5-4 �ɵ����Ԥ�����鹤��ͳ��
          case StaticStatistic.STAT_APPLY_WORKLOAD: {
              hsql = "from  StApplyWorkload as St where  " +
                  " St.taskId = '" + taskId + "' and St.deleted = 0";
              break;
            }
          //end by daizhigang 2008-5-4

          case StaticStatistic.STAT_FAULT_CONMAINTENCECS: {
              hsql = "from  StFaultpackconmaintence as St where  " +
                  " St.taskId = '" + taskId + "' and St.deleted = 0";
              break;
            }
          case StaticStatistic.STAT_FAULT_CONMAINTENCEJH: {
              hsql = "from  StFaultpackconmaintence as St where  " +
                  " St.taskId = '" + taskId + "' and St.deleted = 0";
              break;
            }
          case StaticStatistic.STAT_FAULT_FAULTCAUSE: {
              hsql = "from  StFualtpackdictcause as St where  " +
                  " St.taskId = '" + taskId + "' and  St.deleted = 0 order by disorder";
              break;
            }
    }
    Query query = s.createQuery(hsql);
    query.setCacheable(true);
    List list = query.list();
    return list;
  }

  public List listStatApplysheetDetail(String link, String linkType) throws
        HibernateException {
      Session s = HibernateUtil.currentSession();
      String hsql = "";
      if (linkType.equals("both"))
        hsql = "from StApplyDetail as stApplyDetail where " +
            "stApplyDetail.link  = '" + link + "'";
      else
        hsql = "from StApplyDetail as stApplyDetail where " +
            "stApplyDetail.link  = '" + link +
            "' and stApplyDetail.linkType = '" + linkType + "'";

      Query query = s.createQuery(hsql);
      query.setCacheable(true);
      List list = query.list();
      return list;

    }
    public List listStatTasksheetDetail(String link, String linkType) throws
          HibernateException {
        Session s = HibernateUtil.currentSession();
        String hsql = "";
        if (linkType.equals("both"))
          hsql = "from StTaskDetail as stTaskDetail where " +
              "stTaskDetail.link  = '" + link + "'";
        else
          hsql = "from StTaskDetail as stTaskDetail where " +
              "stTaskDetail.link  = '" + link +
              "' and stTaskDetail.linkType = '" + linkType + "'";

        Query query = s.createQuery(hsql);
        query.setCacheable(true);
        List list = query.list();
        return list;

      }
      public List listStatDatasheetDetail(String link, String linkType) throws
                HibernateException {
              Session s = HibernateUtil.currentSession();
              String hsql = "";
              if (linkType.equals("both"))
                hsql = "from StDataDetail as stDataDetail where " +
                    "stDataDetail.link  = '" + link + "'";
              else
                hsql = "from StDataDetail as stDataDetail where " +
                    "stDataDetail.link  = '" + link +
                    "' and stDataDetail.linkType = '" + linkType + "'";

              Query query = s.createQuery(hsql);
              query.setCacheable(true);
              List list = query.list();
              return list;

            }

      public List listStatResourcesheetDetail(String link, String linkType) throws
         HibernateException {
          Session s = HibernateUtil.currentSession();
          String hsql = "";
          if (linkType.equals("both"))
                    hsql = "from StResourceDetail as stResourceDetail where " +
                        "stResourceDetail.link  = '" + link + "'";
          else
                    hsql = "from StResourceDetail as stResourceDetail where " +
                        "stResourceDetail.link  = '" + link +
                        "' and stResourceDetail.linkType = '" + linkType + "'";

                  Query query = s.createQuery(hsql);
                  query.setCacheable(true);
                  List list = query.list();
                  return list;

                }
      public List getNodeList(String year, String month) throws
          HibernateException {
           Session s = HibernateUtil.currentSession();
           String hsql = "";
           String endDay = "";
           endDay = getEndDay(year, month);
           if (month.length()<2)
                     hsql = "select applysheetSuit from ApplysheetSuit as applysheetSuit, ApplysheetMobile as applysheetMobile where " +
                         "applysheetSuit.finalTime  >= '" + year + "-0" +
                         month + "-01 00:00:00' and applysheetSuit.finalTime  < '" +
                         year + "-0" + month + "-"+endDay+" 23:59:59' and applysheetSuit.sendMobile "+
                         "= applysheetMobile.mobile";
           else
                     hsql = "select applysheetSuit from ApplysheetSuit as applysheetSuit, ApplysheetMobile as applysheetMobile where " +
                         "applysheetSuit.finalTime  >= '" + year + "-" +
                         month + "-01 00:00:00' and applysheetSuit.finalTime  < '" +
                         year + "-" + month +"-"+endDay+ " 23:59:59' and applysheetSuit.sendMobile "+
                         "= applysheetMobile.mobile";

                   System.out.println("----------sql:"+hsql);
                   Query query = s.createQuery(hsql);
                   query.setCacheable(true);
                   List list = query.list();
                   return list;

                 }

   //���ÿ�µ����һ��
         private String getEndDay(String year, String month) {
                String endDay = "";
                Calendar cal = Calendar.getInstance();
                //��
                cal.set(Calendar.YEAR, Integer.parseInt(year));
                //�£���ΪCalendar������Ǵ�0��ʼ������Ҫ-1
                cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
                //�գ���Ϊһ��
                cal.set(Calendar.DATE, 1);
                //�·ݼ�һ���õ��¸��µ�һ��
                cal.add(Calendar.MONTH, 1);
                //��һ���¼�һΪ�������һ��
                cal.add(Calendar.DATE, -1);
                endDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)); //�����ĩ�Ǽ���
                return endDay;
              }
     public void delete_StSheetcount() throws
                   HibernateException {
                String hsql = "from StSheetcount as stSheetcount " ;
                 delete(hsql);

               }


 	public List listStatApplysheetDetail08(String link, String linkType)
			throws HibernateException {
		Session s = HibernateUtil.currentSession();
		String hsql = "from StApplyDetail08 as stApplyDetail08 where stApplyDetail08.link  = '"
				+ link + "' and";
		String[] linkTypes = linkType.split(",");
		String condLinkType = "";
		for (int i = 0; i < linkTypes.length; i++) {
			condLinkType += " stApplyDetail08.linkType = '" + linkTypes[i] + "'";
			if (i < linkTypes.length - 1)
				condLinkType += " or ";
			else
				hsql += condLinkType;
		}

		Query query = s.createQuery(hsql);
		query.setCacheable(true);
		List list = query.list();
		return list;

	}
    //add by daizhigang 2008-4-24 ��湤������ط������ͳ��ɾ��
    public void delStatApplysheetDetail(String link, String linkType) throws
    Exception {
  try {
    BocoConnection conn = null;
    PreparedStatement pstmt = null;
    Session s = HibernateUtil.currentSession();
    conn = (BocoConnection) s.connection();
    String sql = "";
    if (linkType.equals("both")) {
      sql = "delete  from st_apply_detail where link  =? ";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, link);
    }
    else {
      sql =
          "delete  from st_apply_detail where link  =? and link_type=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, link);
      pstmt.setString(2, linkType);
    }
    pstmt.executeUpdate();
  }
  catch (Exception e) {

    e.printStackTrace();
  }
}

    //end by daizhigang 2008-4-24 ��湤������ط������ͳ��ɾ��


}
