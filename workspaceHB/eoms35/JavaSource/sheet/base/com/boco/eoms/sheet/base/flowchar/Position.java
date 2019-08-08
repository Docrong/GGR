package com.boco.eoms.sheet.base.flowchar;

/**
 * @author zhangxb
 * @version 1.0
 * @see 坐标点
 * @since 2008-09-09
 */

public class Position {
    private int x;
    private int y;

    public Position() {
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
