package com.ggr.blog.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "blog_article")
@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
public class Article {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id",unique = true,nullable = true)
    private String id;
    @Column(name = "title")
    private String title;//标题
    @Column(name = "author")
    private String author;//作者
    @Column(name = "createdate")
    private String createdate;//日期
    @Column(name = "content1")
    private String content1;//文章主体
    @Column(name = "content2")
    private String content2;
    @Column(name = "content3")
    private String content3;
    @Column(name = "comments")
    private String comments;//评论,使用id进行分割

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
