package com.ggr.blog.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Comments")
@Table(name = "blog_comments")
public class Comments {
    @Id
    @GenericGenerator(name="system-uuid", strategy = "uuid.hex")
    @Column(name = "id",unique = true)
    private String id;
    @Column(name = "senddate")
    private String date;
    @Column(name = "agreenum")
    private String agree;
    @Column(name = "againstnum")
    private String against;
    @Column(name = "nextone")
    private String next;

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

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
