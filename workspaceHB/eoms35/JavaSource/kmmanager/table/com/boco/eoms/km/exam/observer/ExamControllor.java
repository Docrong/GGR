package com.boco.eoms.km.exam.observer;

import java.util.Date;
import java.util.Observable;

public class ExamControllor extends Observable implements Runnable {
    private static ExamControllor exemControllor = null;

    private ExamControllor() {
    }

    static {
        exemControllor = new ExamControllor();
        Thread thread = new Thread(exemControllor);
        thread.start();
    }

    public static ExamControllor getInstance() {
        return exemControllor;
    }


    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 * 60 * 5);//每五分钟检查一次是否考试时间到
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread examControllor running......");
            this.setChanged();
            this.notifyObservers();
        }
    }

    public static void main(String[] args) {
        //ExamControllor.getInstance();
    }

}