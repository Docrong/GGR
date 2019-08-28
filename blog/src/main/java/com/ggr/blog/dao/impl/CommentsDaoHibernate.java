package com.ggr.blog.dao.impl;

import com.ggr.blog.dao.CommentsDao;
import com.ggr.blog.model.Comments;
import com.ggr.blog.util.StaticMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Transactional
@Repository("iCommentsDao")
public class CommentsDaoHibernate extends HibernateDaoSupport implements CommentsDao {


    // 不能直接使用 setSessionFactory 是因为在HibernateDaoSupport中被定义为final

    @Resource
    public void setSuperSessionFactory(SessionFactory sessionFactory) {

        super.setSessionFactory(sessionFactory);
    }


    private Log log = LogFactory.getLog(getClass());

    @Override
    public Comments getCommentsById(final String id) {
        log.info(this.getClass().getName() + "getCommentsById():");
        Comments t = null;
        if (id != null && !id.equals("")) {
            t = (Comments) getHibernateTemplate().find("from Comments where id='" + id + "'");
        }
        return t;
    }

    @Override
    public Map getCommentsList(final Map maptj) {
        HibernateCallback callback = new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String id = StaticMethod.nullObject2String(maptj.get("id"));
                String nickname = StaticMethod.nullObject2String(maptj.get("nickname"));
                String querySql = "from Comments where 1=1";
                Query query = session.createQuery(querySql);
                List list = query.list();
                Map map = new HashMap();
                map.put("result", list);

                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    @Override
    public void CommentsSave(final Comments t) {
        if (t.getId() == null ||t.getId().equals("")) {
            getHibernateTemplate().clear();
            getHibernateTemplate().save(t);
            getHibernateTemplate().flush();
        } else
            getHibernateTemplate().saveOrUpdate(t);
    }
}
