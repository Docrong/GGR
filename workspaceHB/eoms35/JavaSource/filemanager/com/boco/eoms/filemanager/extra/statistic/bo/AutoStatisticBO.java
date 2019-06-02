package com.boco.eoms.filemanager.extra.statistic.bo;
import com.boco.eoms.common.log.BocoLog;
import java.util.*;
import java.lang.Object;
import org.apache.struts.action.*;
import org.hibernate.HibernateException;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import com.boco.eoms.filemanager.extra.statistic.util.StaticStatistic;

import com.boco.eoms.filemanager.extra.statistic.model.*;
import com.boco.eoms.filemanager.extra.scheduler.SchedulerManager;

import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.filemanager.extra.statistic.dao.*;


public class AutoStatisticBO {
  Map actionFormMap;
  TawSystemSessionForm saveSessionBeanForm;
  String userId;
  int deptId;


  public AutoStatisticBO() {
  }




  public List listAllSubscribe() throws
       Exception {
     AutoStatisticDAO statisticDAO = new AutoStatisticDAO();
     List list = statisticDAO.listAllSubscribe();
     return list;
   }


  }
