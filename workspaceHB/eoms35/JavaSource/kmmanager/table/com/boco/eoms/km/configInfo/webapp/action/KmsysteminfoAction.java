/**
 * @see
 * <p>功能描述：用于值班机房参数配置等功能的类。</p>
 * @author 赵川
 * @version 2.0
 */



package com.boco.eoms.km.configInfo.webapp.action;


import java.util.*;



import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.*;

import com.boco.eoms.km.configInfo.model.*;
import com.boco.eoms.km.configInfo.webapp.form.KmsysteminfoForm;
import com.boco.eoms.km.configInfo.bo.KmsysteminfoBO;
import com.boco.eoms.km.configInfo.dao.*;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.*;
import com.boco.eoms.km.cptroom.bo.KmsystemCptroomBo;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;
import com.boco.eoms.km.duty.bo.KmassignworkBO;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;

//import com.boco.eoms.jbzl.dao.*;
//import com.boco.eoms.jbzl.model.*;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
//import com.boco.eoms.log.bo.logBO;

//import com.boco.eoms.jbzl.dao.*;

import com.boco.eoms.duty.bo.TawRmSysteminfoBO;

public class KmsysteminfoAction extends Action {
  private com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
  private static int PAGE_LENGTH = 20;
  //整合调整关于国际化
  static {
    ResourceBundle prop = ResourceBundle.getBundle(
        "resources.application_zh_CN");
    try {
      PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
    }
    catch (Exception e) {
    }
  }

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionForward myforward = null;
    String myaction = mapping.getParameter();

    if (isCancelled(request)) {
      return mapping.findForward("cancel");
    }
    if ("".equalsIgnoreCase(myaction)) {
      myforward = mapping.findForward("failure");
    } else if ("VIEW".equalsIgnoreCase(myaction)) {
      myforward = performView(mapping, form, request, response);
    } else if ("EDIT".equalsIgnoreCase(myaction)) {
      myforward = performEdit(mapping, form, request, response);
    } else if ("ADD".equalsIgnoreCase(myaction)) {
      myforward = performAdd(mapping, form, request, response);
    } else if ("SAVE".equalsIgnoreCase(myaction)) {
      myforward = performSave(mapping, form, request, response);
    } else if ("REMOVE".equalsIgnoreCase(myaction)) {
      myforward = performRemove(mapping, form, request, response);
    } else if ("TRASH".equalsIgnoreCase(myaction)) {
      myforward = performTrash(mapping, form, request, response);
    } else if ("LIST".equalsIgnoreCase(myaction)) {
      myforward = performList(mapping, form, request, response);
    } else if ("SELECT_ROOM".equalsIgnoreCase(myaction)) {
      myforward = performSelectRoom(mapping, form, request, response);
    }else if ("GETROOM".equalsIgnoreCase(myaction)) {
		myforward = performGetRoom(mapping, form, request, response);
	}  else {
      myforward = mapping.findForward("failure");
    }
    return myforward;
  }


  private ActionForward performList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    return mapping.findForward("success");
  }

  private ActionForward performView(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    KmsysteminfoForm form = (KmsysteminfoForm) actionForm;
    return mapping.findForward("success");
  }

  /**
   * @see 保存机房参数
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performSave(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

	  TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");

	  KmsysteminfoDAO tawRmSysteminfoDAO=null;
      Kmsysteminfo tawRmSysteminfo=null;
      String exchangetime=null;
      KmsysteminfoBO tawRmSysteminfoBO=null;

    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    KmsysteminfoForm form = (KmsysteminfoForm) actionForm;
    try {
     
      tawRmSysteminfoDAO = new KmsysteminfoDAO(ds);
      tawRmSysteminfo = new Kmsysteminfo();
      org.apache.commons.beanutils.BeanUtils.populate(tawRmSysteminfo, org.apache.commons.beanutils.BeanUtils.describe(form));
      int strutsAction = form.getStrutsAction();
      int roomId = tawRmSysteminfo.getRoomId();
      if (strutsAction == KmsysteminfoForm.ADD) {
        if (tawRmSysteminfoDAO.retrieve(roomId+"") == null) {
          //该机房无记录，新增
          tawRmSysteminfoDAO.insert(tawRmSysteminfo);
        } else {
          sqlDuplicateError(request, "TawRmSysteminfo");
          return mapping.findForward("failure");
        }
      } else if (strutsAction == KmsysteminfoForm.EDIT) {
        //该机房有记录，修改
        tawRmSysteminfoDAO.update(tawRmSysteminfo);
      }
      //int exchangetime_roomid = form.getRoomId() ;
      //修改交接班时间
      exchangetime = request.getParameter("exchangetime");
      tawRmSysteminfoBO = new KmsysteminfoBO(ds);
      tawRmSysteminfoBO.updateTawRmExchange(roomId,exchangetime);
      //logbo = new logBO(ds);
      //boolean bool = logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),"值班系统参数设置",StaticVariable.OPER,request.getRemoteAddr());
      //
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmSysteminfoDAO=null;
      tawRmSysteminfo=null;
      exchangetime=null;
      tawRmSysteminfoBO=null;
      //logbo=null;

    }
    //给SAVEFLAG付值true
    request.setAttribute("SAVEFLAG","true");
    return mapping.findForward("success");
  }

  /**
   * @see 编辑机房参数
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performEdit(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    ArrayList list=null;
    ArrayList list_usernum=null;
    ArrayList list_minute=null;
    TawRmSysteminfoBO  tawRmSysteminfoBO=null;
    KmsysteminfoDAO tawRmSysteminfoDAO=null;
    Kmsysteminfo tawRmSysteminfo=null;
    
    //TawApparatusroomDAO tawApparatusroomDAO=null;
    //TawApparatusroom tawApparatusroom=null;
    String room_name=null;
    KmexchangeDAO tawRmExchangeDAO=null;
    Vector vector_exchangetime=null;

    KmsysteminfoForm form = (KmsysteminfoForm) actionForm;
    form.setStrutsAction(KmsysteminfoForm.EDIT);
    return mapping.findForward("success");
  }

  /**
   * @see 显示机房参数
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performAdd(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
    request.getSession().getAttribute("sessionform");
    ArrayList list=null;
    ArrayList list_usernum=null;
    ArrayList list_minute=null;
    ArrayList list_flag=null;
    ArrayList list_cycle=null;
    ArrayList list_stagger = null;
   // KmsysteminfoBO  kmsysteminfoBO=null;
    KmsysteminfoDAO kmsysteminfoDAO=null;
    Kmsysteminfo kmsysteminfo=null;
    KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
    KmsystemCptroom tawApparatusroom = null;    
    String room_name=null;
    KmexchangeDAO kmexchangeDAO=null;
    Vector vector_exchangetime=null;

    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    //从select-room中取得机房id
    int room_id = Integer.parseInt(request.getParameter("roomId"));
    request.setAttribute("roomId",String.valueOf(room_id));
    //传值list（短信通知）
    list = new ArrayList();
    list.add(new org.apache.struts.util.LabelValueBean ("无",String.valueOf(0)));
    list.add(new org.apache.struts.util.LabelValueBean ("值班员",String.valueOf(1)));
    list.add(new org.apache.struts.util.LabelValueBean ("管理员",String.valueOf(2)));
    list.add(new org.apache.struts.util.LabelValueBean ("值班员和管理员",String.valueOf(3)));
    request.setAttribute("EXREQUEST",list);
    request.setAttribute("EXANSWER",list);
    request.setAttribute("DUTYINFORM",list);
    list_flag = new ArrayList();
    list_flag.add(new org.apache.struts.util.LabelValueBean ("由班长交接班",String.valueOf(0)));
    list_flag.add(new org.apache.struts.util.LabelValueBean ("无需交接班",String.valueOf(1)));
    list_flag.add(new org.apache.struts.util.LabelValueBean ("任意人员交接班",String.valueOf(2)));
    list_flag.add(new org.apache.struts.util.LabelValueBean ("全部人员交接班",String.valueOf(3)));
    request.setAttribute("LISTFLAG",list_flag);
    //传值list（值班人数）
    list_usernum = new ArrayList();
    for (int i=1;i<21;i++)
    {
      list_usernum.add(new org.apache.struts.util.LabelValueBean (String.valueOf(i),String.valueOf(i)));
    }
    request.setAttribute("MAXDUTYNUM",list_usernum);
    //传值list（交接班最大误差时间）
    list_minute = new ArrayList();
    for (int i=1;i<61;i++)
    {
      list_minute.add(new org.apache.struts.util.LabelValueBean (String.valueOf(i),String.valueOf(i)));
    }
    request.setAttribute("MAXERRORTIME",list_minute);
    
    // 传值list （值班周期附加表设置操作时间）
    list_cycle =  new ArrayList();
    
    for(int i = 30;i<=480;i=i+30){
    	list_cycle.add(new org.apache.struts.util.LabelValueBean (String.valueOf(i),String.valueOf(i)));
    }
    request.setAttribute("CYCLETIME",list_cycle);
    
    // add by gong . at 2008-11-14  (增加一个值班公用时间 为了错班的时候两个班次公用的时间)
    list_stagger =  new ArrayList();
    for(int i = 0;i<=7;i++){
    	list_stagger.add(new org.apache.struts.util.LabelValueBean (String.valueOf(i),String.valueOf(i)));
    }
    request.setAttribute("STAGGER",list_stagger);
    try {
  //    kmsysteminfoBO = new KmsysteminfoBO(ds);
      //取得机房值班参数信息的对象
      kmsysteminfoDAO = new KmsysteminfoDAO(ds);
      kmsysteminfo = kmsysteminfoDAO.retrieve(room_id+"");
      //取得机房名称
      //tawApparatusroomDAO = new TawApparatusroomDAO(ds);
      tawApparatusroom = cptroomBO.getKmsystemCptroomById(new Integer(room_id),0);
      room_name = tawApparatusroom.getRoomname();
      request.setAttribute("ROOMNAME",room_name);
      //取得是否为保存，因为保存后返回的是本页面
      String saveflag = String.valueOf(request.getAttribute("SAVEFLAG"));
      if (saveflag.trim().equals("true")){
        request.setAttribute("SAVEFLAG","true");
      }
      else{
        request.setAttribute("SAVEFLAG","false");
      }
      if (kmsysteminfo == null){
      //新增机房值班信息
      KmsysteminfoForm form = (KmsysteminfoForm) actionForm;
      form.setStrutsAction(KmsysteminfoForm.ADD);
      }
      else{
        //机房值班信息已存在
        KmsysteminfoForm form = (KmsysteminfoForm) actionForm;
        form.setStrutsAction(KmsysteminfoForm.EDIT);
        //取得该机房交接班时间
        kmexchangeDAO = new KmexchangeDAO(ds);
        vector_exchangetime = kmexchangeDAO.getVectorExchangTime(room_id);
        request.setAttribute("exchangetime",vector_exchangetime) ;
        //将机房值班信息对象传给formform
        org.apache.commons.beanutils.BeanUtils.populate(form, org.apache.commons.beanutils.BeanUtils.describe(kmsysteminfo));
      }
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      list=null;
      list_usernum=null;
      list_minute=null;
  //    kmsysteminfoBO=null;
      kmsysteminfoDAO=null;
      kmsysteminfo=null;
      cptroomBO = null;
      //tawApparatusroomDAO=null;
      tawApparatusroom=null;
      room_name=null;
      kmexchangeDAO=null;
      vector_exchangetime=null;
      list_flag=null;
    }
    return mapping.findForward("success");
  }


  private ActionForward performRemove(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    return performView(mapping, actionForm, request, response);
  }

  /**
   * @see 选择要配置机房参数的机房
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performSelectRoom(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
    Vector SelectRoom=null;
    KmassignworkBO tawRmAssignworkBO=null;
	TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();          
    

    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    try {
      SelectRoom = new Vector();

      if(saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)){
        tawRmAssignworkBO = new KmassignworkBO(ds);
        SelectRoom = tawRmAssignworkBO.getRoomSelect();
      }
      else{
        SelectRoom = com.boco.eoms.base.util.StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
      }
     /* if (SelectRoom.size()>0){
       
      }
      else{
        return mapping.findForward("nopriv");
      }*/

    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      SelectRoom=null;
      tawRmAssignworkBO=null;
      privBO = null;
    }
    return mapping.findForward("success");
  }


  private ActionForward performTrash(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    return mapping.findForward("success");
  }


  private void sqlDuplicateError(HttpServletRequest request, String objName) {
    ActionErrors aes = new ActionErrors();
    aes.add(aes.GLOBAL_ERROR, new ActionError("errors.database.duplicate", objName));
    saveErrors(request, aes);
  }

  private void generalError(HttpServletRequest request, Exception e) {
    ActionErrors aes = new ActionErrors();
    aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e.getMessage()));
    saveErrors(request, aes);
    e.printStackTrace();
  }
  private ActionForward performGetRoom(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		Vector SelectRoom = null;
		Vector SelectRoomId = null;
		Vector SelectRoomName = null;
		KmassignworkBO kmassignworkBO = null;
		TawSystemPrivRegion tawSystemPrivRegion = null;
		
		TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		
		KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
		KmsystemCptroom kmspparatusroom = null;
		JSONArray json = new JSONArray();
	
		String strSelectRoomName = null;
		
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		try {
			SelectRoom = new Vector();
			SelectRoomName = new Vector();
			SelectRoomId = new Vector();
			
		//	if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
				kmassignworkBO = new KmassignworkBO(ds);
				SelectRoom = kmassignworkBO.getRoomSelect();
				kmspparatusroom = null;
				strSelectRoomName = "";
				Vector removeEle = new Vector();
				if (SelectRoom.size() > 0) {
					for (int i = 0; i < SelectRoom.size(); i++) {
						JSONObject jitem = new JSONObject();
						// tawSystemPrivRegion =
						// (TawSystemPrivRegion)SelectRoom.elementAt(i);
						kmspparatusroom = cptroomBO.getKmsystemCptroomById(
								new Integer((String) SelectRoom.elementAt(i)),
								0);
						if (kmspparatusroom != null) {
							strSelectRoomName = StaticMethod
									.null2String(kmspparatusroom.getRoomname());
							SelectRoomName.add(strSelectRoomName);
							SelectRoomId.add((String) SelectRoom.elementAt(i));
							jitem.put("id", (String) SelectRoom.elementAt(i));
							jitem.put("text", strSelectRoomName);
							jitem.put(UIConstants.JSON_NODETYPE, "cptroom");
							jitem.put("allowChild", true); 
							jitem.put("allowDelete", true);
							jitem.put("allowList", true);
							jitem.put("leaf", kmspparatusroom.getLeaf());
							json.put(jitem);
						} else {
							removeEle.add(SelectRoom.elementAt(i));
						}
					}
					SelectRoom.removeAll(removeEle);
				} else {
					return mapping.findForward("nopriv");
				}
		/*	} else {
				// tawValidatePrivBO = new TawValidatePrivBO(ds);
				// SelectRoom =
				// tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
				// 2051, 2);
				SelectRoom = StaticMethod
						.list2vector(privBO
								.getPermissions(
										saveSessionBeanForm.getUserid(),
										com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
										com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));

				if (SelectRoom.size() > 0) {
					// tawApparatusroomDAO = new TawApparatusroomDAO(ds);
					kmspparatusroom = null;
					strSelectRoomName = "";
					Vector removeEle = new Vector();
					for (int i = 0; i < SelectRoom.size(); i++) {
						JSONObject jitem = new JSONObject();
						tawSystemPrivRegion = (TawSystemPrivRegion) SelectRoom
								.elementAt(i);
						kmspparatusroom = cptroomBO.getKmsystemCptroomById(
								new Integer(tawSystemPrivRegion.getRegionid()),
								0);
						if (kmspparatusroom != null) {
							strSelectRoomName = StaticMethod
									.null2String(kmspparatusroom.getRoomname());
							SelectRoomName.add(strSelectRoomName);
							SelectRoomId.add(tawSystemPrivRegion.getRegionid());
							jitem.put("id", tawSystemPrivRegion.getRegionid());
							jitem.put("text", strSelectRoomName);
							jitem.put(UIConstants.JSON_NODETYPE, "cptroom");
							jitem.put("allowChild", true);
							jitem.put("allowDelete", true);
							jitem.put("allowList", true);
							jitem.put("leaf", kmspparatusroom.getLeaf());
							json.put(jitem);
						} else {
							removeEle.add(SelectRoom.elementAt(i));
						}
					}
					SelectRoom.removeAll(removeEle);
				} else {
					return mapping.findForward("nopriv");
				}
			}*/
			response.setContentType("text/xml;charset=UTF-8");
			response.getWriter().print(json.toString());
			request.setAttribute("SelectRoom", SelectRoomId);
			request.setAttribute("SelectRoomName", SelectRoomName);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			SelectRoom = null;
			SelectRoomName = null;
			kmassignworkBO = null;
			privBO = null;
			// tawValidatePrivBO = null;
			cptroomBO = null;
			// tawApparatusroomDAO = null;
			kmspparatusroom = null;
			strSelectRoomName = null;
		}
		return null;
	}
}
