package com.weilai.Socket.Close;

import java.io.IOException;

public interface Channel
{
    //判断通道是否打开
public boolean isOpen( );
    //关闭通道
public void close( ) throws IOException;
}