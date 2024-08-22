package com.weilai.Buffer.ViewBuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;


public class View {
    public static void main(String[] args) {
        //申请缓冲区 字符顺序使用大端排序
        ByteBuffer allocate = ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);
        //转成char类型的视图缓冲区 获取视图缓冲区
        CharBuffer charBuffer = allocate.asCharBuffer();
        allocate.put(0,(byte) 0);
        allocate.put(1,(byte) 'h');
        allocate.put(2,(byte) 0);
        allocate.put(3,(byte) 'I');
        allocate.put(4,(byte) 0);
        allocate.put(5,(byte) '!');
        allocate.put(6,(byte) 0);
        System.out.println(allocate);
        System.out.println(charBuffer);
    }
}
