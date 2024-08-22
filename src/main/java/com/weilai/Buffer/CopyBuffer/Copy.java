package com.weilai.Buffer.CopyBuffer;

import java.nio.CharBuffer;

/**
 * 复制缓冲区
 */
public class Copy {
    public static void main(String[] args) {
        char[] charArray = "hEllowQWQ 0.0 -.- OVO".toCharArray();
        CharBuffer wrap = CharBuffer.wrap(charArray);
        wrap.position(6).limit(charArray.length).mark();
        CharBuffer duplicate = wrap.duplicate();
        System.out.println(duplicate);
        duplicate.append('q');
        System.out.println(charArray);
        System.out.println(duplicate);
    }
}
