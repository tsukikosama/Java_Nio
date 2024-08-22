package com.weilai.Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class SelectSockets {
    public static int prot_number = 9898;

    public static void main(String[] args) throws Exception {
//        String[] arr = new String[0];
//        arr[0] =
        new SelectSockets().go();
    }

    public void go() throws Exception {
        int port = prot_number;
//        if (argv.length > 0){
//            port = Integer.parseInt(argv[0]);
//        }
        System.out.println("监听端口" + port);
        //打开channel
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        //获取ServerSocket实例
        ServerSocket ssk = socketChannel.socket();
        //获取一个selector选择器 用于管理多个通道
        Selector selector = Selector.open();
        //绑定端口
        ssk.bind(new InetSocketAddress(port));
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //注册到selector 并且监听OP_ACCEPT事件
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            //阻塞等待通道准备就绪
            int n = selector.select();
            if (n == 0) {
                continue;
            }
            // 返回一个包含这些准备就绪的通道对应的 SelectionKey 集合
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    sayHello(channel);
                }
                //当前的SelectionKey 是否准备就绪
                if (key.isReadable()) {
                    readDataFromSocket(key);
                }
            }
        }
    }

    protected void registerChannel(Selector selector, SelectableChannel channel, int ops) throws Exception {
        if (channel == null) {
            return; // could happen
        }
    //设置为阻塞队列
        channel.configureBlocking(false);
    //将该通道注册到selector上
        channel.register(selector, ops);
    }

    private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    protected void readDataFromSocket(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        buffer.clear(); // Empty buffer
        while ((count = socketChannel.read(buffer)) > 0) {
            buffer.flip(); // Make buffer readable
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            buffer.clear(); // Empty buffer
        }
        if (count < 0) {
            socketChannel.close();
        }
    }

    private void sayHello(SocketChannel channel) throws Exception {
        buffer.clear();
        buffer.put("Hi there!\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }

}
