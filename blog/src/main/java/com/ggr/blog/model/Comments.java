package com.ggr.blog.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 评论部分
 *
 * @author gr
 * @date 2019-08-27 09:39:11
 */
@Entity(name = "Comments")
@Table(name = "blog_comments")
@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
public class Comments {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id", unique = true)
    private String id;
    @Column(name = "senddate")
    private String senddate;//时间
    @Column(name = "agreenum")
    private String agreenum;//赞同
    @Column(name = "againstnum")
    private String againstnum;//反对
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

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getAgreenum() {
        return agreenum;
    }

    public void setAgreenum(String agreenum) {
        this.agreenum = agreenum;
    }

    public String getAgainstnum() {
        return againstnum;
    }

    public void setAgainstnum(String againstnum) {
        this.againstnum = againstnum;
    }

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }
}
