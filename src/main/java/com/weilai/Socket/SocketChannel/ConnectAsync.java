package com.weilai.Socket.SocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ConnectAsync {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8999;
        if (args.length > 2){
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        //获取连接地址
        InetSocketAddress addr = new InetSocketAddress(host, port);
        //获取channel通道
        SocketChannel open = SocketChannel.open();
        //非阻塞
        open.configureBlocking(false);
        System.out.println("初始化连接");
        //建立连接
        open.connect(addr);
        while (!open.finishConnect()){
            System.out.println("连接成功咯OvO");
            doSomethingUseful();
        }
        System.out.println ("connection established");
        open.close( );
    }
    private static void doSomethingUseful( )
    {
        System.out.println ("doing something useless");
    }
}
