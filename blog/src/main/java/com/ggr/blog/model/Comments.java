package com.ggr.blog.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 评论部分
 *
 * @author gr
 * @date 2019-08-27 09:39:11
 */
@Entity(name = "Comments")
@Table(name = "blog_comments")
public class Comments {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "id", unique = true)
    private String id;
    @Column(name = "senddate")
    private String date;//时间
    @Column(name = "agreenum")
    private String agree;//赞同
    @Column(name = "againstnum")
    private String against;//反对
    @Column(name = "lastid")
    private String lastid;//上一条
    @Column(name = "nickname")
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getAgainst() {
        return against;
    }

    public void setAgainst(String against) {
        this.against = against;
    }

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }
}
