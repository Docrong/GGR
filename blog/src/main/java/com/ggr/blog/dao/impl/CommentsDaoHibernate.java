package com.ggr.blog.dao.impl;

import com.ggr.blog.dao.CommentsDao;
import com.ggr.blog.model.Comments;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository("iCommentsDao")
public class CommentsDaoHibernate extends HibernateDaoSupport  implements CommentsDao {

//    @Autowired
//    @Qualifier(value = "oracleDataSource")
//    private DataSource ds;

    @Autowired
    LocalSessionFactoryBean sessionFactory;

    protected Log log = LogFactory.getLog(getClass());

    @Override
    public Comments getCommentsById(String id) throws SQLException {
        log.info(this.getClass().getName());
//        Connection conn=null;
//        try {
//            conn =ds.getConnection();
//            String querysql="select * from taw_system_area";
//            Statement sm = null;
//            sm = conn.createStatement();
//            ResultSet rs=sm.executeQuery(querysql);
//            while (rs.next()) {
//                String  str1=rs.getString(1);
//                System.out.println(str1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            conn.close();
//        }

        System.out.println("sessionFactory321");




        return null;
    }
}
