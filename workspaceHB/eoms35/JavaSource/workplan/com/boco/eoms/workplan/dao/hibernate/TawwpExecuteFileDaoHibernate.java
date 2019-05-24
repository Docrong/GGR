package com.boco.eoms.workplan.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workplan.dao.ITawwpExecuteFileDao;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteFile;

public class TawwpExecuteFileDaoHibernate extends BaseDaoHibernate implements
		ITawwpExecuteFileDao {
	/**
	 * 保存执行作业计划附件
	 * 
	 * @param _tawwpExecuteFile
	 * TawwpExecuteFile 执行作业计划附件类 @ 操作异常
	 */
	public void saveExecuteFile(TawwpExecuteFile _tawwpExecuteFile) {
		getHibernateTemplate().save(_tawwpExecuteFile);
	}

	/**
	 * 删除执行作业计划附件
	 * 
	 * @param _tawwpExecuteFile
	 * TawwpExecuteFile 执行作业计划附件类 @ 操作异常
	 */
	public void deleteExecuteFile(final TawwpExecuteFile _tawwpExecuteFile) {
	//	getHibernateTemplate().delete(_tawwpExecuteFile);
	getHibernateTemplate().execute(new HibernateCallback() {
				
				public Object doInHibernate(Session session) { 
	
		    		Query query = session.createQuery(
	
		    				"delete from  TawwpExecuteFile   where id = :id"); 
	
		    		query.setParameter("id", _tawwpExecuteFile.getId()); 
	
		    		 
		    		
		    		query.executeUpdate(); 
		    		
		    		return ""; 
		    	}

			

	    	}); 
	}

	/**
	 * 修改执行作业计划附件
	 * 
	 * @param _tawwpExecuteFile
	 * TawwpExecuteFile 执行作业计划附件类 @ 操作异常
	 */
	public void updateExecuteFile(TawwpExecuteFile _tawwpExecuteFile) {
		getHibernateTemplate().update(_tawwpExecuteFile);
	}

	/**
	 * 查询执行作业计划附件信息
	 * 
	 * @param id
	 * String 执行作业计划附件标识 @ 操作异常
	 * @return TawwpExecuteFile 执行作业计划附件类
	 */
	public TawwpExecuteFile loadExecuteFile(String id) {
		return (TawwpExecuteFile) getHibernateTemplate().get(
				TawwpExecuteFile.class, id);
	}

	/**
	 * 查询所有执行作业计划附件信息 @ 操作异常
	 * @return List 执行作业计划附件类列表
	 */
	public List listExecuteFile() {
		String hSql = "";
		hSql = "from TawwpExecuteFile as tawwpexecutefile";

		return getHibernateTemplate().find(hSql);
	}
	
	public List listExecuteFile(String executeContentId) {
		String hSql = "";
		hSql = "from TawwpExecuteFile as tawwpexecutefile where tawwpExecuteContent='"+executeContentId+"'";

		return getHibernateTemplate().find(hSql);
	}
	/**
	 * 获取与执行执行内容关联的附件对象
	 * 
	 * @param _fileCodeName
	 *            String 文件实际存储名
	 * @param _tawwpExecuteContent
	 * TawwpExecuteContent 执行作业计划执行内容(整体)对象 @ 异常
	 * @return TawwpExecuteFile 附件对象
	 */
	public TawwpExecuteFile filterTawwpExecuteFile(String _fileCodeName,
			TawwpExecuteContent _tawwpExecuteContent) {
		 
		if (_tawwpExecuteContent != null
				&& _tawwpExecuteContent.getTawwpExecuteFiles() != null) {
			for (Iterator it = _tawwpExecuteContent.getTawwpExecuteFiles()
					.iterator(); it.hasNext();) {
				TawwpExecuteFile file = (TawwpExecuteFile) it.next();
				if (_fileCodeName.equals(file.getFileCodeName())) {
					return file;
				}
			}
		}
		return null;
		 
	}
	
	
	public TawwpExecuteFile  loadExecuteFileByCode(String codeId ){
		String hSql = "";
		TawwpExecuteFile tawwpExecuteFile = new TawwpExecuteFile();
		hSql = "from TawwpExecuteFile as tawwpexecutefile where tawwpexecutefile.fileCodeName ='"+codeId+"'";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hSql);
		for(int i=0;i<list.size();i++){
			tawwpExecuteFile = (TawwpExecuteFile)list.get(0);
		}
		return tawwpExecuteFile;
	}
}
