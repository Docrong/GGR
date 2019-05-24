
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
import com.boco.eoms.message.dao.SmsServiceDao;
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
 * Date:2008-5-5 下午03:36:21
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 *
 */
public class SmsServiceDaoHibernate extends BaseDaoHibernate implements SmsServiceDao {

    /**
     * @see com.boco.eoms.message.dao.SmsServiceDao#getSmsServices(com.boco.eoms.message.model.SmsService)
     */
    public List getSmsServices(final SmsService smsService) {
        return getHibernateTemplate().find("from SmsService");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (smsService == null) {
            return getHibernateTemplate().find("from SmsService");
        } else {
            // filter on properties set in the smsService
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(smsService).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SmsService.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.message.dao.SmsServiceDao#getSmsService(String id)
     */
    public SmsService getSmsService(final String id) {
        SmsService smsService = (SmsService) getHibernateTemplate().get(SmsService.class, id);
        if (smsService == null) {
            throw new ObjectRetrievalFailureException(SmsService.class, id);
        }

        return smsService;
    }

    /**
     * @see com.boco.eoms.message.dao.SmsServiceDao#saveSmsService(SmsService smsService)
     */    
    public void saveSmsService(final SmsService smsService) {
        if ((smsService.getId() == null) || (smsService.getId().equals("")))
			getHibernateTemplate().save(smsService);
		else
			getHibernateTemplate().saveOrUpdate(smsService);
    }

    /**
     * @see com.boco.eoms.message.dao.SmsServiceDao#removeSmsService(String id)
     */
    public void removeSmsService(final String id) {
        getHibernateTemplate().delete(getSmsService(id));
    }
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getSmsServices(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the smsService
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from SmsService";
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
    public Map getSmsServices(final Integer curPage, final Integer pageSize) {
			return this.getSmsServices(curPage,pageSize,null);
		}

	public List listAll(String deleted) {
		List serviceList = new ArrayList();
		String hql = "from SmsService where deleted='"+deleted+"'";
		serviceList = getHibernateTemplate().find(hql);
		
		return serviceList;
	}

	public List listByModuleId(String moduleId) {
		List serviceList = new ArrayList();
		String hql = "from SmsService where moduleId='"+moduleId+"'";
		serviceList = getHibernateTemplate().find(hql);
		return serviceList;
	}

	public List listByOuter(String userId, String pwd) {
		List serviceList = new ArrayList();
		String hql = "from SmsService where status=userId='"+userId+"' and password='"+pwd+"'";
		serviceList = getHibernateTemplate().find(hql);
		return serviceList;
	}	
	/**
	 * @see 根据父节点和删除标志得到下一级模块业务的部门信息
	 * @param parentid
	 * @param delid
	 * @return List 
	 */
	public List getNextLevelServices(String parentid, String delid) {
		String hql = " from SmsService service where service.parentId='"
			+ parentid + "'" + " and service.deleted='" + delid + "' order by substr(service.name,'0','1')";
			List list = new ArrayList();
			list = getHibernateTemplate().find(hql);
			return list;
	}

	public List getCancelServices(String receiverId) {
		String shql = "from SmsService service where service.status='"+MsgConstants.SERVICE+"' and service.selStatus='"+MsgConstants.POSITIVE+"' and service.id not in(select apply.serviceId from SmsApply apply where apply.deleted='2' and apply.receiverId='"+receiverId+"' and apply.selStatus='"+MsgConstants.POSITIVE+"')" ;
		return getHibernateTemplate().find(shql);
	}

	public boolean hasService(String serviceId) {
		boolean returnValue = false;
		List sList = null;
		String hql = "from SmsService where id='"+serviceId+"'";
		sList =  getHibernateTemplate().find(hql);
		if(sList != null && sList.size() > 0 ) {
			returnValue = true; 
		}
		return returnValue;
	}

	public List getAllServices(String userId) {
		String hql = "from SmsService where status='"+MsgConstants.SERVICE+"' and userId='"+userId+"'";		
		return getHibernateTemplate().find(hql);
	}
	
	
	
	//*******************************************
	//webservice部分

	public void xDeleteByWebService(String id) { 
		getHibernateTemplate().delete(getSmsService(id));
		
		ISmsApplyManager isms=(ISmsApplyManager)ApplicationContextHolder.getInstance().getBean("IsmsApplyManager");
		List applyList=isms.getApplyBySid(id);
		for(int i=0;i<applyList.size();i++){
			isms.removeSmsApply(((SmsApply)applyList.get(i)).getId());
		}
	
		
	}
	public String xGetAllServices() {

		SmsService smsService = null;
		String id = MsgHelp.defaultParentId;
    	StringBuffer sb=new StringBuffer();
    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><message>");
    	List list=new ArrayList();
    	
		String hql = "from SmsService where parentId='"+id+"'";
		list =getHibernateTemplate().find(hql);
		for(int i=0;i<list.size();i++){
			smsService = (SmsService)list.get(i);
			String serviceId = smsService.getId();
			String serviceName = smsService.getName();
			sb.append("<service><id>"+serviceId+"</id>");
			sb.append("<name>"+serviceName+"</name></service>");
		}
		sb.append("</message>");
		return sb.toString();
		
	}		

		

	public String xSaveXmlString(String xmlString) {
		
		SmsUtil su=new SmsUtil();
        SmsService sms=su.ServiceUtil(xmlString);
		boolean isNew = ("".equals(sms.getId()) || sms
				.getId() == null);
		ISmsServiceManager isms=(ISmsServiceManager)ApplicationContextHolder.
        getInstance().getBean("IsmsServiceManager");
		ISmsApplyManager applyMgr=(ISmsApplyManager)ApplicationContextHolder.
        getInstance().getBean("IsmsApplyManager");
		IPojo2PojoService pojo2pojo = (IPojo2PojoService)ApplicationContextHolder.
		getInstance().getBean("Service2Apply");
		String usersId = sms.getUsersId();
		String selStatus = sms.getSelStatus();
		SmsService service = new SmsService();
		String status = sms.getStatus();
		// 0为成功执行方法，1为失败
		String flag="0";
		
		if (isNew) {			
			String parentId = sms.getParentId();
			if (parentId == null || parentId.equals("") || parentId.equals("-1")) {
				sms.setLeaf("1");
				sms.setDeleted(MsgConstants.UNDELETED);
			} else {
				service = isms.getSmsService(parentId);
				sms.setLeaf("1");
				service.setLeaf("0");
				isms.saveSmsService(service);
			}			
			sms.setDeleted("0");
			sms.setStatus(status);
			isms.saveSmsService(sms);			
			
		} else {
			String serviceId = sms.getId();
			List aList = null;
			if(selStatus.equals("true")) {
				aList = applyMgr.getBySidDeleted(serviceId, MsgConstants.DIYED);
			} else {
				aList = applyMgr.getBySidDeleted(serviceId, MsgConstants.DELETED);
			}			
			Iterator ait = aList.iterator();
			while(ait.hasNext()) {
				applyMgr.removeSmsApply(((SmsApply)ait.next()).getId());
			}			
			sms.setStatus(status);
			isms.saveSmsService(sms);
		}
		if(("2").equals(status)) {
			List useridList = MsgHelp.String2List(usersId);
			Iterator it = useridList.iterator();
			while(it.hasNext()) {
				SmsApply smsApply = new SmsApply();
				smsApply.setReceiverId((String)it.next());
				if(selStatus.equals("true")) {
					smsApply.setDeleted(MsgConstants.DIYED);
				} else {
					smsApply.setDeleted(MsgConstants.DELETED);						
				}
				try {
					pojo2pojo.p2p(sms, smsApply);
				} catch (PRMException e) {
					e.printStackTrace();
					flag="1";
				}
				applyMgr.saveSmsApply(smsApply);
			}
		}
		return flag;
	}


	public String xGetXmlString(String id) {
		SmsService smsBean = getSmsService(id);
		//属性转移
		
		SmsServiceAdapter smsAdapter = MsgHelp.smsService2Adapter(smsBean);
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
	public String getUsersId(SmsService smsService) {
		String selStatus = smsService.getSelStatus();
		if("true".equals(selStatus)) {
			
		}
		return null;
	}

	public List getUsersList(String serviceId) {
		String hql="from SmsApply where serviceId='"+serviceId+"'";
		List usersList=getHibernateTemplate().find(hql);
		return usersList;
	}
	
	
}
