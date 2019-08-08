package com.example.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClientDemo {

    public static void main(String[] args) throws UnknownHostException, IOException {
        // TODO Auto-generated method stub
        //1.建立TCP连接
        String ip = "172.16.200.8";   //服务器端ip地址
//		System.getProperties().setProperty("http.proxyHost", "119.36.8.212");
//    	System.getProperties().setProperty("http.proxyPort", "4512");

        int port = 8901;        //端口号
        Socket sck = new Socket(ip, port);
        //2.传输内容
        String content = "这是一个java模拟客户端";
        byte[] bstream = content.getBytes("utf-8");  //转化为字节流
        OutputStream os = sck.getOutputStream();   //输出流
        os.write(bstream);
        //3.关闭连接
        sck.close();
    }

}

