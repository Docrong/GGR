
package com.boco.eoms.message.dao.hibernate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.exolab.castor.core.exceptions.CastorException;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.message.dao.SmsApplyDao;
import com.boco.eoms.message.mgr.ISmsApplyManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.model.SmsServiceAdapter;
import com.boco.eoms.message.util.CommonUtil;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.MsgHelp;
import com.boco.eoms.message.util.SmsUtil;
import com.boco.eoms.prm.exceptions.PRMException;
import com.boco.eoms.prm.service.IPojo2PojoService;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:37:11
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
/**
 * @author zhike
 *
 */
/**
 * @author zhike
 *
 */
public class SmsApplyDaoHibernate extends BaseDaoHibernate implements SmsApplyDao {

    /**
     * @see com.boco.eoms.message.dao.SmsApplyDao#getSmsApplys(com.boco.eoms.message.model.SmsApply)
     */
    public List getSmsApplys(final SmsApply smsApply) {
        return getHibernateTemplate().find("from SmsApply");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (smsApply == null) {
            return getHibernateTemplate().find("from SmsApply");
        } else {
            // filter on properties set in the smsApply
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(smsApply).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SmsApply.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.message.dao.SmsApplyDao#getSmsApply(String id)
     */
    public SmsApply getSmsApply(final String id) {
        SmsApply smsApply = (SmsApply) getHibernateTemplate().get(SmsApply.class, id);
        if (smsApply == null) {
            throw new ObjectRetrievalFailureException(SmsApply.class, id);
        }

        return smsApply;
    }

    /**
     * @see com.boco.eoms.message.dao.SmsApplyDao#saveSmsApply(SmsApply smsApply)
     */    
    public void saveSmsApply(final SmsApply smsApply) {
        if ((smsApply.getId() == null) || (smsApply.getId().equals("")))
			getHibernateTemplate().save(smsApply);
		else
			getHibernateTemplate().saveOrUpdate(smsApply);
    }

    /**
     * @see com.boco.eoms.message.dao.SmsApplyDao#removeSmsApply(String id)
     */
    public void removeSmsApply(final String id) {
        getHibernateTemplate().delete(getSmsApply(id));
    }
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getSmsApplys(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the smsApply
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from SmsApply";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							int total = ((Integer) session.createQuery(queryCountStr).iterate()
									.next()).intValue();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
							query.setMaxResults(pageSize.intValue());
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", new Integer(total));
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    public Map getSmsApplys(final Integer curPage, final Integer pageSize) {
			return this.getSmsApplys(curPage,pageSize,null);
		}
    /**
     * 根据接收者ID、接收者类型、删除标志返回订制的服务对象List
     * @param receiverId 接收者ID
     * @param receiverType 接收者类型
     * @param deleted 删除标志
     * @return 服务对象List
     */
	public List list(String receiverId, String receiverType, String deleted) {
		List applyList = new ArrayList();
		String hql = "from SmsApply where receiverId='"+receiverId+"' and receiverType='"+receiverType+"' and deleted='"+deleted+"'";
		applyList = getHibernateTemplate().find(hql);
		return applyList;
	}
	/**
     * 根据删除标志返回订制的服务对象List
     * @param deleted 删除标志
     * @return 服务对象List
     */
	public List listByDeleted(String deleted) {
		List applyList = new ArrayList();
		String hql = "from SmsApply where deleted='"+deleted+"'";
		applyList = getHibernateTemplate().find(hql);
		return applyList;
	}
	/**
     * 根据服务接收者ID返回订制的服务对象List
     * @param receiverId 服务接收者ID
     * @return 服务对象List
     */
	public List listByReceiver(String receiverId) {
		List applyList = new ArrayList();
		String hql = "from SmsApply where receiverId='"+receiverId+"'";
		applyList = getHibernateTemplate().find(hql);
		return applyList;
	}
	/**
     * 根据服务ID返回订制的服务对象List
     * @param serviceId 服务ID
     * @return 服务对象List
     */
	public List listByServiceId(String serviceId) {
		List applyList = new ArrayList();
		String hql = "from SmsApply where serviceId='"+serviceId+"'";
		applyList = getHibernateTemplate().find(hql);
		return applyList;
	}
	/**
     * 根据当前用户ID返回订制的服务对象List
     * @param userId 当前操作用户ID
     * @return 服务对象List
     */
	public List listByUserId(String userId) {
		List applyList = new ArrayList();
		String hql = "from SmsApply where userId='"+userId+"'";
		applyList = getHibernateTemplate().find(hql);
		return applyList;
	}

	public List getApplysByCondition(String receiverId, String deleted) {
		List serviceList = null;
		List applyList = null;
		//未加入正反选标志时的代码
//		String shql = "from SmsService service where service.status='2' and service.id not in(select apply.serviceId from SmsApply apply where apply.receiverId='"+receiverId+"')";
//		String ahql = "from SmsApply apply where apply.deleted ='2' and apply.receiverId='"+receiverId+"'";
		//加入正反选标志后的代码
		String shql = "from SmsService service where service.status='2' and selStatus='false' and service.id not in(select apply.serviceId from SmsApply apply where apply.receiverId='"+receiverId+"')";
		String ahql = "from SmsApply apply where apply.deleted='2' and apply.receiverId='"+receiverId+"'";
		serviceList = getHibernateTemplate().find(shql);
		applyList = getHibernateTemplate().find(ahql);
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder.getInstance().getBean("Service2Apply");
		Iterator it = serviceList.iterator();
		while(it.hasNext()) {
			SmsService service = (SmsService)it.next();
			SmsApply apply = new SmsApply();
			try {
				pojo2pojo.p2p(service, apply);
				apply.setServiceId(apply.getServiceId().concat("#"));
			} catch (PRMException e) {
				e.printStackTrace();
			}
			applyList.add(apply);
		}
		return applyList;
	}

	public SmsApply getApply(String serviceId, String receiverId, String deleted){
		SmsApply smsApply = new SmsApply();
		List sList = null;
		List aList = null;
		String id = "";
		String hql = "";
		SmsService service = null;
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder.getInstance().getBean("Service2Apply");
//		根据serviceId是否以#结尾判断是service对象还是apply对象
		if(serviceId.endsWith("#")) {//SmsService对象
			id = serviceId.substring(0, serviceId.length()-1);
			hql = "from SmsService where id='"+id+"'";
			sList = getHibernateTemplate().find(hql);
			if(sList != null && sList.size() != 0 ) {
				service = (SmsService)sList.get(0);
			} else {
				return null;
			}
			try {
				pojo2pojo.p2p(service, smsApply);
			} catch (PRMException e) {
				e.printStackTrace();
			}
		} else {//SmsApply对象
			hql = "from SmsApply where  serviceId='"+serviceId+"' and receiverId='"+receiverId+"' and deleted='"+deleted+"'";
			
			aList = getHibernateTemplate().find(hql);
			if(aList != null && aList.size() != 0 ) {
				smsApply = (SmsApply)aList.get(0);
			} else {
				return null;
			}
		}
		return smsApply;
	}

	public List getDeletedApplys(String receiverId, String deleted) {
		String hql = "from SmsApply where receiverId='"+receiverId+"' and deleted='"+deleted+"'";		
		return getHibernateTemplate().find(hql);
	}
	/**方法不完善，可能有多条符合条件的记录*/
	public String getApplyStatus(String serviceId, String receiverId) {
		String deleted = "";
		List aList = null;
		SmsApply apply = new SmsApply();
		String hql = "from SmsApply where serviceId='"+serviceId+"' and receiverId='"+receiverId+"'";
		aList = getHibernateTemplate().find(hql);
		if(aList != null && aList.size() != 0) {
			apply = (SmsApply)aList.get(0);
		}
		deleted = apply.getDeleted();
		return deleted;
	}

	public SmsApply getSimpleApply(String serviceId, String receiverId, String deleted) {
		SmsApply apply = null;
		SmsApply returnApply = null;
		List applyList = null;
		String hql  = "from SmsApply where  serviceId='"+serviceId+"' and receiverId='"+receiverId+"' and deleted='"+deleted+"'";
		//new
		if(MsgConstants.ALL.equals(deleted)) {
			hql = "from SmsApply where  serviceId='"+serviceId+"' and receiverId='"+receiverId+"'";
		}
		//end
		applyList = getHibernateTemplate().find(hql);
		Iterator it = applyList.iterator();
		while(it.hasNext()) {
			apply = (SmsApply)it.next();
			if(apply.getDeleted().equals(MsgConstants.DELETED)) {
				returnApply = apply;
				break;
			} else if(apply.getDeleted().equals(MsgConstants.DIYED)){
				returnApply = apply;
				break;
			}
		}
//		if(applyList != null && applyList.size() !=0) {
			
//			apply = (SmsApply)applyList.get(0);
//		}
		return returnApply;
	}

	public List getApplyBySid(String serviceId) {
		String hql = "from SmsApply where serviceId='"+serviceId+"'";
		return getHibernateTemplate().find(hql);
	}

	public List getBySidDeleted(String serviceId, String deleted) {
		String hql = "from SmsApply where serviceId='"+serviceId+"' and deleted='"+deleted+"'";
		return getHibernateTemplate().find(hql);
	}

	public List getApplysByIsCycle(String isCycleSend) {
		String hql = "from SmsApply where isCycleSend='"+isCycleSend+"'";		
		return getHibernateTemplate().find(hql);
	}

	public List getCancelApplys(String receiverId) {
		String hql = "from SmsApply where receiverId='"+receiverId+"' and deleted='"+MsgConstants.DELETED+"'";				
		return getHibernateTemplate().find(hql);
	}
/**
 * 得到个性化全部列表
 */
	public String getAllApplys(String userId) {
		List list = getApplysByCondition(userId, MsgConstants.DELETED);
		
		SmsApply smsApply=null;
    	StringBuffer sb=new StringBuffer();
    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><message>");
		for(int i=0;i<list.size();i++){
			smsApply = (SmsApply)list.get(i);
			String serviceId = smsApply.getServiceId();
			String serviceName = smsApply.getName();
			sb.append("<service><id>"+serviceId+"</id>");
			sb.append("<name>"+serviceName+"</name></service>");
		}
		sb.append("</message>");
		return sb.toString();
	}
/**
 * 得到个性化详细
 */
	public String getApplyDetail(String userId,String serviceId) {
		
		//获取
		SmsApply smsApply =getApply(serviceId,userId,MsgConstants.DIYED);
		SmsServiceAdapter smsAdapter = MsgHelp.smsApply2Adapter(smsApply);
		File file = new File("test.xml");
		String filePath = "";
		String xmlString = "";
		try {
			filePath = StaticMethod.getFilePathForUrl("classpath:config/map.xml");
			Writer writer = new FileWriter(file);
			Mapping mapping = new Mapping();
			mapping.loadMapping(filePath);
			Marshaller marshal = new Marshaller(writer);
			marshal.setMapping(mapping);
			marshal.marshal(smsAdapter);
			xmlString = CommonUtil.getXMLString(file);
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ValidationException ve) {
			ve.printStackTrace();
		} catch(CastorException ce) {
			ce.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		} 
		return xmlString;
		
	}
	/**
	 * 个性化保存
	 * @param xmlString
	 */
	public void xsave(String xmlString){
		
		SmsUtil smsUtil=new SmsUtil();
		SmsApply SmsApply=smsUtil.ApplyService(xmlString);
		String userId = SmsApply.getUserId();
		SmsApply smsApply = new SmsApply();
		smsApply.setDeleted(MsgConstants.DIYED);
		smsApply.setSelStatus("true");
		smsApply.setReceiverId(userId);
		smsApply.setUserId(userId);
		saveSmsApply(smsApply);
	}
	
	/**
	 * 定制取消服务列表
	 */
	public String xsaveApplyWebList(String userId){
		
		
		ISmsApplyManager mgr = (ISmsApplyManager) ApplicationContextHolder.getInstance().getBean("IsmsApplyManager");
		ISmsServiceManager smgr = (ISmsServiceManager)ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
		SmsApply smsApply = null;
		SmsService smsService = null;
		StringBuffer sb=new StringBuffer();
		//获取
		//List applyList = mgr.getDeletedApplys(sessionForm.getUserid(), MsgConstants.DELETED);
		List applyList = mgr.getCancelApplys(userId);
		List serviceList = smgr.getCancelServices(userId);
		if(applyList != null && applyList.size() !=0) {			
				
		    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><message>");
				for(int i=0;i<applyList.size();i++){
					
					smsApply = (SmsApply)applyList.get(i);
					String serviceId = smsApply.getServiceId();
					String serviceName = smsApply.getName();
					sb.append("<service><id>"+serviceId+"</id>");
					sb.append("<name>"+serviceName+"</name></service>");
				}
				sb.append("</message>");
				
		} else if(serviceList != null && serviceList.size() !=0){
			
	    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><message>");
			for(int i=0;i<serviceList.size();i++){
				
				smsService = (SmsService)serviceList.get(i);
				String serviceId = smsService.getId();
				String serviceName = smsService.getName();
				sb.append("<service><id>"+serviceId+"</id>");
				sb.append("<name>"+serviceName+"</name></service>");
			}
			sb.append("</message>");				
				
			}
		 
		
		return sb.toString();
		
	}
	/**
	 * 定制取消服务保存
	 */
	public void xsaveApplyWeb(String servicesId,String userId){
		
		
		List applyList = null;
		SmsService sService = null;
		ISmsServiceManager mgr = (ISmsServiceManager) ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
		ISmsApplyManager applyMgr = (ISmsApplyManager) ApplicationContextHolder.getInstance().getBean("IsmsApplyManager");
		
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder.getInstance().getBean("Service2Apply");
		if(servicesId != null && !servicesId.equals("")) {
			List servicesList = MsgHelp.String2List(servicesId);
			//先删除所有之前取消的服务然后在新增开始，开始删除所有			
			//applyList = applyMgr.getDeletedApplys(userId, MsgConstants.DELETED);
			applyList = applyMgr.getCancelApplys(userId);
			List serviceList = mgr.getCancelServices(userId);
			Iterator it = applyList.iterator();
			while(it.hasNext()) {
				applyMgr.removeSmsApply(((SmsApply)it.next()).getId());
			}			
			//新增
			Iterator serviceIt = servicesList.iterator();
			while(serviceIt.hasNext()) {
				SmsApply smsApply = null;
				SmsService smsService = null;
				String serviceId = (String)serviceIt.next();
				sService = mgr.getSmsService(serviceId);
				smsApply = applyMgr.getApply(serviceId, userId, MsgConstants.DIYED);
				if(("true").equals(sService.getSelStatus())) {
					if(smsApply != null) {											
						applyMgr.removeSmsApply(smsApply.getId());
					}
				} else {					
					if(smsApply == null) {
						//将service对象复制到apply对象
						SmsApply apply = applyMgr.getApply(serviceId, userId, MsgConstants.DELETED);
						if (null == apply) {
							smsService = mgr.getSmsService(serviceId);
							smsApply = new SmsApply();
							try {
								pojo2pojo.p2p(smsService, smsApply);
							} catch (PRMException e) {
								e.printStackTrace();
							}
							smsApply.setReceiverId(userId);
							smsApply.setDeleted(MsgConstants.DELETED);				
							applyMgr.saveSmsApply(smsApply);
						}
					} else {
						// modify by sunshengtai at 08-2-9，原有代码删除后此人又相当于订制了服务，应该是设置删除标志为删除
						smsApply.setDeleted(MsgConstants.DELETED);
						applyMgr.saveSmsApply(smsApply);
					}
				}
			}		
		}
		
	}
	
}
