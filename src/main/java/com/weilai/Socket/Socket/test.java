package com.weilai.Socket.Socket;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class test {

    /**
     * This code copies data from stdin to stdout. Like the 'cat'
     * command, but without any useful options.
     */
    public static void main(String[] argv)
            throws IOException {
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        channelCopy2(source, dest);
        source.close();
        dest.close();
    }
    /**
     * Channel copy method 1. This method copies data from the src
     * channel and writes it to the dest channel until EOF on src.
     * This implementation makes use of compact( ) on the temp buffer
     * to pack down the data if the buffer wasn't fully drained. This
     * may result in data copying, but minimizes system calls. It also
     * requires a cleanup loop to make sure all the data gets sent.
     */
    private static void channelCopy1(ReadableByteChannel src, WritableByteChannel dest)
            throws IOException {
        //分配一个直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        //如果不是-1 循环读取数据源
        while (src.read(buffer) != -1) {
            //把缓冲区中的内容翻转成可输出的字节
            buffer.flip();
            //写入通道中
            dest.write(buffer);
            //将缓冲区从读取切换成写入 同时保留未读的数据
            buffer.compact();
        }
        //可能还有未写完的数据  将缓冲区从写入模式切换回读取模式
        buffer.flip();
        //判断是缓冲区中是否还有数据  如果有就继续写入
        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }

    /**
     * Channel copy method 2. This method performs the same copy, but
     * assures the temp buffer is empty before reading more data. This
     * never requires data copying but may result in more systems calls.
     * No post-loop cleanup is needed because the buffer will be empty
     * when the loop is exited.
     */
    private static void channelCopy2(ReadableByteChannel src,
                                     WritableByteChannel dest)
            throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        //读取
        while (src.read(buffer) != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            //重置position remark limit 属性
            buffer.clear();
        }
    }
}

