package com.work.ggr.hoo.entity;

import java.io.Serializable;

public class SimpleObject implements Serializable {
    private static final long serialVersionUID = -6414428095965735488L;
    private int i = 0;
    private float f = 0.0f;
    private String str = "";

    public SimpleObject() {
    }

    public float getFloat() {
        return f;
    }

    public void setFloat(float f) {
        this.f = f;
    }

    public int getInt() {
        return i;
    }

    public void setInt(int i) {
        this.i = i;
    }

    public String getString() {
        return str;
    }

    public void setString(String str) {
        this.str = str;
    }
}