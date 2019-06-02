package com.boco.eoms.workplan.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentUserDao;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteContentUser;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 9:17:57 PM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public class TawwpExecuteContentUserDaoHibernate extends BaseDaoHibernate
		implements ITawwpExecuteContentUserDao {
	/**
	 * 保存执行作业计划执行内容(个人)
	 * 
	 * @param _tawwpExecuteContentUser
	 * TawwpExecuteContentUser 执行作业计划执行内容(个人)类 @ 操作异常
	 */
	public void saveExecuteContentUser(
			TawwpExecuteContentUser _tawwpExecuteContentUser) {
		getHibernateTemplate().save(_tawwpExecuteContentUser);
	}

	/**
	 * 删除执行作业计划执行内容(个人)
	 * 
	 * @param _tawwpExecuteContentUser
	 * TawwpExecuteContentUser 执行作业计划执行内容(个人)类 @ 操作异常
	 */
	public void deleteExecuteContentUser(
			TawwpExecuteContentUser _tawwpExecuteContentUser) {
		getHibernateTemplate().delete(_tawwpExecuteContentUser);
	}

	/**
	 * 修改执行作业计划执行内容(个人)
	 * 
	 * @param _tawwpExecuteContentUser
	 * TawwpExecuteContentUser 执行作业计划执行内容(个人)类 @ 操作异常
	 */
	public void updateExecuteContentUser(
			TawwpExecuteContentUser _tawwpExecuteContentUser) {
		getHibernateTemplate().update(_tawwpExecuteContentUser);
	}

	/**
	 * 查询执行作业计划执行内容(个人)信息
	 * 
	 * @param id
	 * String 执行作业计划执行内容(个人)标识 @ 操作异常
	 * @return TawwpExecuteContentUser 执行作业计划执行内容(个人)类
	 */
	public TawwpExecuteContentUser loadExecuteContentUser(String id) {
		return (TawwpExecuteContentUser) getHibernateTemplate().get(
				TawwpExecuteContentUser.class, id);
	}

	/**
	 * 查询所有执行作业计划执行内容(个人)信息 @ 操作异常
	 * @return List 执行作业计划执行内容(个人)类列表
	 */
	public List listExecuteContentUser() {

		String hSql = "";
		hSql = "from TawwpExecuteContentUser as tawwpexecutecontentuser";

		return getHibernateTemplate().find(hSql);
	}

	/**
	 * 获取由指定用户添加的执行作业计划执行内容(单一用户)
	 * 
	 * @param _userId
	 *            String 用户登录名
	 * @param _tawwpExecuteContent
	 * TawwpExecuteContent 执行作业计划执行内容(整体)对象 @ 异常
	 * @return TawwpExecuteContentUser 执行作业计划执行内容(单一用户)对象
	 */
	public TawwpExecuteContentUser filterTawwpExecuteContentUser(
			String _userId, TawwpExecuteContent _tawwpExecuteContent) {

	 
		if (_tawwpExecuteContent != null
				&& _tawwpExecuteContent.getTawwpExecuteContentUsers() != null) {
			for (Iterator it = _tawwpExecuteContent
					.getTawwpExecuteContentUsers().iterator(); it.hasNext();) {
				TawwpExecuteContentUser content = (TawwpExecuteContentUser) it
						.next();
				if (_userId.equals(content.getCruser())) {
					return content;
				}

			}
		}

		return null;
		 
	}

	public List listExeConUserforAddons(String modelplanid, String cruser,
			String formid, String startDate, String endDate) {
		String hSql = "from TawwpExecuteContentUser as tawwpexecutecontentuser where tawwpexecutecontentuser.crtime>='"+startDate+"'and tawwpexecutecontentuser.crtime<='"+endDate+"' ";
		if(modelplanid!=null&&!modelplanid.equals("0")){
			hSql = hSql + "and tawwpexecutecontentuser.tawwpExecuteContent.tawwpMonthPlan.tawwpModelPlan.id = '"+modelplanid+"' ";
		}
		if(cruser!=null&&!cruser.equals("")){
			hSql = hSql + "and tawwpexecutecontentuser.cruser like '%" + cruser + "%' ";
		}
		if(formid!=null&&!formid.equals("0")){
			hSql = hSql + "and tawwpexecutecontentuser.formId ='"+formid+"' ";
		}
		List list = getHibernateTemplate().find(hSql);
		return list;
	}
	
 
	 
}
