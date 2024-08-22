package com.weilai.Buffer.DirectBuffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * 直接缓冲区 位于jvm栈之外的缓冲区
 */
public class Direct {
    public static void main(String[] args) {
        char[] charArray = "hEllowQWQ 0.0 -.- OVO".toCharArray();
        CharBuffer wrap = CharBuffer.wrap(charArray);
        //分配一个直接缓冲区 位于jvm之外的
        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
        //判断缓冲区是不是直接缓冲区的方法
        boolean direct = wrap.isDirect();
        System.out.println(direct);
    }
}
