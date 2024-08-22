package com.weilai.Socket.ServerSocketChannel;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Block {

    public static final String GREETING = "Hello i ovo";

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 9999;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        //获取一个字节缓冲区
        ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());
        //获取一个socket渠道服务
        ServerSocketChannel open = ServerSocketChannel.open();
        //绑定端口
        open.socket().bind(new InetSocketAddress(port));
        //设定为非阻塞队列
        open.configureBlocking(false);
        while (true) {
            System.out.println("wait qwq");
            //返回channel对象
            SocketChannel sc = open.accept();
            //为空 休眠一秒
            if (sc == null){
                Thread.sleep(1000);
            }else{
                System.out.println("Incoming connection QvQ");
                //重置位置指针
                buffer.rewind();
                //写入数据
                sc.write(buffer);
                sc.close();
            }
        }

    }
}
