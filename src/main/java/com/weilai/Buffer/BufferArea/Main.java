package com.weilai.Buffer.BufferArea;

import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * 创建缓冲区相关的例子
 */
public class Main {
    public static void main(String[] args) {
//        String s = "hello";
        char[] charArray = "hello".toCharArray();
        //分配空间
        CharBuffer charBuffer = CharBuffer.allocate (100);
        System.out.println(charBuffer.limit());
        System.out.println(charBuffer.position());
        /**
         * wrap提供数组作为一个缓冲区 去修改数组或者缓冲区 都会对另一个造成影响 是直接对对象进行操作
         * 此时的position为0 修改的结果为qello
         */
        CharBuffer wrap = CharBuffer.wrap(charArray).append("q");
        System.out.println(charArray);
        //使用append会让position向后移动一个位置
        //导致输出的结果为eSlo
        charArray[2] = 'S';
        System.out.println(wrap);
        //使用position(0) 可以让position重新回到0 输出qeSlo
        System.out.println(wrap.position(0));

        //获取缓冲区position为2  limit为最后一个字符
        CharBuffer wrap1 = CharBuffer.wrap(charArray, 2, charArray.length-2);
        System.out.println(wrap1);
        System.out.println(wrap1.arrayOffset());
        //hasArray 判断是否拥有缓存对象集合
        if (wrap.hasArray()){
            //array获取缓冲对象集合
            System.out.println(wrap.array());
            System.out.println(wrap.arrayOffset());

        }



    }

}