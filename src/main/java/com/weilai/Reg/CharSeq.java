package com.weilai.Reg;

import java.nio.CharBuffer;

public class CharSeq {
    public static void main(String[] args) {
        StringBuffer stringBuffer =  new StringBuffer("OvO");
        CharBuffer charBuffer = CharBuffer.allocate(100);
        CharSequence charSequence = "QwQ";

        printCharSequence (charSequence);
//来源于StringBuffer
        charSequence = stringBuffer;
        printCharSequence (charSequence);
//更改StringBuffer
        stringBuffer.setLength (0);
        stringBuffer.append ("Goodbye cruel world");
//相同、“不变的”CharSequence产生了不同的结果
        printCharSequence (charSequence);
//从CharBuffer中导出CharSequence
        charSequence = charBuffer;
        charBuffer.put ("xxxxxxxxxxxxxxxxxxxx");
        charBuffer.clear( );
        charBuffer.put ("Hello World");
        charBuffer.flip( );
        printCharSequence (charSequence);
        charBuffer.mark( );
        charBuffer.put ("Seeya");
        charBuffer.reset( );
        printCharSequence (charSequence);
        charBuffer.clear( );
        printCharSequence (charSequence);
//更改基础CharBuffer会反映在只读的CharSequence接口上
    }

    private static void printCharSequence (CharSequence cs)
    {
        System.out.println ("length=" + cs.length( )
                + ", content='" + cs.toString( ) + "'");
    }
}
