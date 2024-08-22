package com.weilai.Socket.Pipes;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Random;

public class PipeTest {
    public static void main(String[] args) throws IOException {
        //获取字节输入通道
        WritableByteChannel wbc = Channels.newChannel(System.out);
        //获取字节输出通道
        ReadableByteChannel rbc = startWork(10);
        //申请缓冲区
        ByteBuffer bf = ByteBuffer.allocate(100);
        //读取数据进缓冲区
        while (rbc.read(bf) > 0) {
            //翻转
            bf.flip();
            //写入
            wbc.write(bf);
            bf.clear();
        }
    }

    private static ReadableByteChannel startWork(int reps) throws IOException {
        //打开管道
        Pipe pipe = Pipe.open();
        //读取数据
        Worker worker = new Worker (pipe.sink( ), reps);
        worker.start();
        return pipe.source();
    }

    private static class Worker extends Thread
    {
        WritableByteChannel channel;
        private int reps;
        Worker (WritableByteChannel channel, int reps)
        {
            this.channel = channel;
            this.reps = reps;
        }
        // Thread execution begins here
        public void run( )
        {
            ByteBuffer buffer = ByteBuffer.allocate (100);
            try {
                for (int i = 0; i < this.reps; i++) {
                    doSomeWork (buffer);
// channel may not take it all at once
                    while (channel.write (buffer) > 0) {
// empty
                    }
                }
                this.channel.close( );
            } catch (Exception e) {
// easy way out; this is demo code
                e.printStackTrace( );
            }
        }
        private String [] products = {
                "No good deed goes unpunished",
                "To be, or what?",
                "No matter where you go, there you are",
                "Just say \"Yo\"",
                "My karma ran over my dogma"
        };
        private Random rand = new Random( );
        private void doSomeWork (ByteBuffer buffer)
        {
            int product = rand.nextInt (products.length);
            buffer.clear( );
            buffer.put (products [product].getBytes( ));
            buffer.put ("\r\n".getBytes( ));
            buffer.flip( );
        }
    }
}
