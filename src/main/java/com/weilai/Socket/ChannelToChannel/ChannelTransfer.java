package com.weilai.Socket.ChannelToChannel;

import java.io.FileInputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelTransfer {
    public static void main(String[] argv)
            throws Exception {
        String[] arr = new String[1];
        arr[0] = "C:\\Users\\Administrator\\Desktop\\spring\\Nio\\blahblah.txt";

        catFiles(Channels.newChannel(System.out), arr);
    }

    // Concatenate the content of each of the named files to
// the given channel. A very dumb version of 'cat'.
    private static void catFiles(WritableByteChannel target,
                                 String[] files)
            throws Exception {
        for (int i = 0; i < files.length; i++) {
            //获取文件输入流
            FileInputStream fis = new FileInputStream(files[i]);
            //获取文件通道
            FileChannel channel = fis.getChannel();
            //写入文件
            channel.transferTo(0, channel.size(), target);
            //关闭通道
            channel.close();
            //关闭流
            fis.close();
        }
    }
}