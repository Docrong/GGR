package com.ggr.leecode.temp;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test3 {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df=new SimpleDateFormat("yyyy--MM-dd HH:mm:ss");

        Date createtime=sdf.parse("2019-08-14 16:31:00.279");//T1将工单派发至T2的时间
        Date date=new Date();
        Date completetimelimit=sdf.parse("2019-08-16 09:33:59.0");//回复时限
        long dd=date.getTime()-createtime.getTime();//减掉流转时间
//        dd=0;
        Calendar cal = Calendar.getInstance();

        String tmhour="8";
        String tmmin="0";
        String modifytime="6";
        String examinetime="";
        Date tomorrow=completetimelimit;
        if(tmhour.equals("-1")){//-1表示和以前一样
            examinetime=sdf.format(completetimelimit);
        }else{
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_YEAR, 1);
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tmhour)+Integer.parseInt(modifytime));
            cal.set(Calendar.MINUTE, Integer.parseInt(tmmin));
            cal.set(Calendar.SECOND, 0);
            tomorrow=cal.getTime();
            examinetime=sdf.format(new Date(tomorrow.getTime()-dd));
        }
System.out.println(examinetime);
        System.out.println("ggr==end complaint CrmServicemanagerImpl()时限设置");
    }
}
