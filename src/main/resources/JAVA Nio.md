# JAVA Nio

### 缓冲区的基本属性

#####     属性

1. 容量

   缓冲区最大可以存储的数量

2. 上界

   缓冲区存储数据的尾部

3. 位置

   下一个要被读取的元素的位置 可以使用get 和put更新

4. 标记

   mark用来标记位置的 一开始是没有标记的 要使用mark来标记

### API

##### 存取

```java
public abstract class ByteBuffer 
 extends Buffer implements Comparable 
{ 
 // 获取元素
 public abstract byte get( ); 
    //获取指定位置的元素
 public abstract byte get (int index); 
      //存入元素
 public abstract ByteBuffer put (byte b);
 //在指定位置存入
 public abstract ByteBuffer put (int index, byte b); 
}
```

##### 翻转

Flip()可以把在填充状态的缓冲区翻转成一个可以读取数据的状态

##### 释放

布尔函数 hasRemaining()会在释放缓冲区时告诉您是否已经达到缓冲区的上界.

```java
for (int i = 0; buffer.hasRemaining( ), i++) { 
 myByteArray [i] = buffer.get( ); 
}
```

remaining()函数将告知您从当前位置到上界还剩余的元素数目.

```java
int count = buffer.remaining( ); 
for (int i = 0; i < count, i++) { 
 myByteArray [i] = buffer.get( ); 
}
```

##### 压缩

```
public abstract class ByteBuffer 
 extends Buffer implements Comparable 
{ 
 // This is a partial API listing 
 public abstract ByteBuffer compact( ); 
}
```

这个函数可以将position移动到指定的位置 位置之前的元素全部抛弃 只留下指定位置之后的元素

##### 标记

一开始是没有标记的，使用mark可以标记一个位置   reset可以会到上一次标记的位置。如果没有remak直接调用reset会报错 

##### 比较

 比较方法有两个 equals()和compareTo()

```java
public abstract class ByteBuffer 
 extends Buffer implements Comparable 
{ 
 // This is a partial API listing 
 public boolean equals (Object ob) 
 public int compareTo (Object ob) 
}
```

可以使用equals来比较两个缓冲区剩余内容是否相等，如果相等equal返回ture 否则返回false.

两个缓冲区相等的条件是

​	1.两个对象类型相同。包含不同数据类型的 buffer 永远不会相等，而且 buffer 绝不会等于非 buffer 对象。 

​	2.两个对象都剩余同样数量的元素。Buffer 的容量不需要相同，而且缓冲区中剩 余数据的索引也不必相同。但每个缓冲区中剩余元素的数目（从位置到上界）必须相 同。

​	3.在每个缓冲区中应被 Get()函数返回的剩余数据元素序列必须一致。

compareTo不允许不同对象做比较 会报错，equal只会返回false 返回小于0是不相等

##### 批量移动

```java
public abstract class CharBuffer 
 extends Buffer implements CharSequence, Comparable 
{ 
 // This is a partial API listing 
 public CharBuffer get (char [] dst) 
 public CharBuffer get (char [] dst, int offset, int length) 
 public final CharBuffer put (char[] src) 
 public CharBuffer put (char [] src, int offset, int length) 
 public CharBuffer put (CharBuffer src) 
 public final CharBuffer put (String src) 
 public CharBuffer put (String src, int start, int end) 
}
```

### 缓冲区

##### 创建缓冲区

```java
public abstract class CharBuffer 
 extends Buffer implements CharSequence, Comparable 
{ 
 // This is a partial API listing 
    //用来分配空间的
 public static CharBuffer allocate (int capacity) 
     //用来备份数组并且创建缓冲区的  数组或者缓冲区发生变话 对应的也会发生变话
 public static CharBuffer wrap (char [] array) 
     //定义缓冲区的初始属性 position和长度
 public static CharBuffer wrap (char [] array, int offset, int length) 
     //判断是否还有数组
 public final boolean hasArray( ) 
     //返回缓冲区对象的数组引用
 public final char [] array( ) 
     //返回数组的偏移
 public final int arrayOffset( ) 
}
```

##### 复制缓冲区

```java
public abstract class CharBuffer 
 extends Buffer implements CharSequence, Comparable 
{ 
 // This is a partial API listing 
    //创建一个新的缓冲区 数据舒徐和原缓冲区都是一样的 但是对复制的缓冲区修改也作用在源数组上
 public abstract CharBuffer duplicate( ); 
    //用来生成一个自读的缓冲区
 public abstract CharBuffer asReadOnlyBuffer( ); 
    //分割出来一个缓冲区 只会继承直接和自读属性
 public abstract CharBuffer slice( ); 
}
```

##### 字节缓冲区

```java
public abstract class ByteBuffer extends Buffer 
 implements Comparable 
{ 
 public static ByteBuffer allocate (int capacity) 
 public static ByteBuffer allocateDirect (int capacity) 
 public abstract boolean isDirect( ); 
 public static ByteBuffer wrap (byte[] array, int offset, int length) 
 public static ByteBuffer wrap (byte[] array) 
 public abstract ByteBuffer duplicate( ); 
41
 public abstract ByteBuffer asReadOnlyBuffer( ); 
 public abstract ByteBuffer slice( ); 
 public final boolean hasArray( ) 
 public final byte [] array( ) 
 public final int arrayOffset( ) 
 public abstract byte get( ); 
 public abstract byte get (int index); 
 public ByteBuffer get (byte[] dst, int offset, int length) 
 public ByteBuffer get (byte[] dst, int offset, int length) 
 public abstract ByteBuffer put (byte b); 
 public abstract ByteBuffer put (int index, byte b); 
 public ByteBuffer put (ByteBuffer src) 
 public ByteBuffer put (byte[] src, int offset, int length) 
 public final ByteBuffer put (byte[] src) 
 public final ByteOrder order( ) 
 public final ByteBuffer order (ByteOrder bo)
 public abstract CharBuffer asCharBuffer( ); 
 public abstract ShortBuffer asShortBuffer( );
 public abstract IntBuffer asIntBuffer( ); 
 public abstract LongBuffer asLongBuffer( ); 
 public abstract FloatBuffer asFloatBuffer( ); 
 public abstract DoubleBuffer asDoubleBuffer( ); 
 public abstract char getChar( ); 
 public abstract char getChar (int index); 
 public abstract ByteBuffer putChar (char value); 
 public abstract ByteBuffer putChar (int index, char value); 
 public abstract short getShort( ); 
 public abstract short getShort (int index); 
 public abstract ByteBuffer putShort (short value); 
 public abstract ByteBuffer putShort (int index, short value); 
 public abstract int getInt( ); 
 public abstract int getInt (int index); 
 public abstract ByteBuffer putInt (int value); 
 public abstract ByteBuffer putInt (int index, int value);
 public abstract long getLong( ); 
 public abstract long getLong (int index); 
 public abstract ByteBuffer putLong (long value); 
 public abstract ByteBuffer putLong (int index, long value);
public abstract float getFloat( ); 
public abstract float getFloat (int index); 
public abstract ByteBuffer putFloat (float value); 
 public abstract ByteBuffer putFloat (int index, float value); 
  
public abstract double getDouble( ); 
 public abstract double getDouble (int index); 
 public abstract ByteBuffer putDouble (double value); 
 public abstract ByteBuffer putDouble (int index, double value); 
 
public abstract ByteBuffer compact( ); 
 public boolean equals (Object ob) { 
 public int compareTo (Object ob) { 
 public String toString( ) 
 public int hashCode( )
 }
```

##### 直接缓冲区

```java
public abstract class ByteBuffer 
 extends Buffer implements Comparable 
{ 
 // This is a partial API listing 
 public static ByteBuffer allocate (int capacity) 
 public static ByteBuffer allocateDirect (int capacity) 
 public abstract boolean isDirect( ); 
}
```

##### 视图缓冲区

```java
public abstract class ByteBuffer 
 extends Buffer implements Comparable 
{ 
 // This is a partial API listing
 public abstract CharBuffer asCharBuffer( ); 
 public abstract ShortBuffer asShortBuffer( ); 
 public abstract IntBuffer asIntBuffer( ); 
 public abstract LongBuffer asLongBuffer( ); 
 public abstract FloatBuffer asFloatBuffer( ); 
 public abstract DoubleBuffer asDoubleBuffer( ); 
}
```

##### 数据元素视图

```java
public abstract class ByteBuffer 
 extends Buffer implements Comparable 
{ 
 public abstract char getChar( ); 
 public abstract char getChar (int index); 
 public abstract short getShort( ); 
 public abstract short getShort (int index); 
 public abstract int getInt( ); 
 public abstract int getInt (int index); 
 public abstract long getLong( ); 
 public abstract long getLong (int index); 
 public abstract float getFloat( ); 
 public abstract float getFloat (int index); 
 public abstract double getDouble( ); 
 public abstract double getDouble (int index); 
 public abstract ByteBuffer putChar (char value); 
 public abstract ByteBuffer putChar (int index, char value); 
 public abstract ByteBuffer putShort (short value); 
 public abstract ByteBuffer putShort (int index, short value); 
 public abstract ByteBuffer putInt (int value); 
 public abstract ByteBuffer putInt (int index, int value); 
 public abstract ByteBuffer putLong (long value); 
 public abstract ByteBuffer putLong (int index, long value); 
 public abstract ByteBuffer putFloat (float value); 
 public abstract ByteBuffer putFloat (int index, float value); 
 public abstract ByteBuffer putDouble (double value); 
 public abstract ByteBuffer putDouble (int index, double value); 
}	
```

get方法获取对应类型字节大小的数据 如果数据不住就会抛出一个异常 

例如getLong获取八个字节  getInt获取四个字节

```java
import java.nio.ByteBuffer;
/**
* 向 ByteBuffer 对象中获取和存放无符号值的工具类。
* 这里所有的函数都是静态的，并且带有一个 ByteBuffer 参数。
* 由于 java 不提供无符号原始类型，每个从缓冲区中读出的无符号值被升到比它大的
* 下一个基本数据类型中。
* getUnsignedByte()返回一个 short 类型, getUnsignedShort( )
* 返回一个 int 类型，而 getUnsignedInt()返回一个 long 型。 There is no
* 由于没有基本类型来存储返回的数据，因此没有 getUnsignedLong( )。
* 如果需要，返回 BigInteger 的函数可以执行。
* 同样，存放函数要取一个大于它们所分配的类型的值。
* putUnsignedByte 取一个 short 型参数，等等。
*
* @author Ron Hitchens (ron@ronsoft.com)
*/
public class Unsigned 
{ 
 public static short getUnsignedByte (ByteBuffer bb) 
 { 
 return ((short)(bb.get( ) & 0xff)); 
 } 
 public static void putUnsignedByte (ByteBuffer bb, int value) 
 { 
 bb.put ((byte)(value & 0xff)); 
 } 
 public static short getUnsignedByte (ByteBuffer bb, int position) 
 { 
 return ((short)(bb.get (position) & (short)0xff)); 
 } 
 public static void putUnsignedByte (ByteBuffer bb, int position, 
 int value) 
 { 
 bb.put (position, (byte)(value & 0xff)); 
 } 
 // ---------------------------------------------------------------
 public static int getUnsignedShort (ByteBuffer bb) 
 { 
 return (bb.getShort( ) & 0xffff); 
 } 
 public static void putUnsignedShort (ByteBuffer bb, int value) 
 { 
 bb.putShort ((short)(value & 0xffff)); 
 }
 public static int getUnsignedShort (ByteBuffer bb, int position) 
 { 
 return (bb.getShort (position) & 0xffff); 
 } 
 public static void putUnsignedShort (ByteBuffer bb, int position, 
 int value) 
 { 
 bb.putShort (position, (short)(value & 0xffff)); 
 } 
 // ---------------------------------------------------------------
 public static long getUnsignedInt (ByteBuffer bb) 
51
 { 
 return ((long)bb.getInt( ) & 0xffffffffL); 
 } 
 
 public static void putUnsignedInt (ByteBuffer bb, long value) 
 { 
 bb.putInt ((int)(value & 0xffffffffL)); 
 } 
 public static long getUnsignedInt (ByteBuffer bb, int position) 
 { 
 return ((long)bb.getInt (position) & 0xffffffffL); 
 } 
 public static void putUnsignedInt (ByteBuffer bb, int position, 
 long value) 
 { 
 bb.putInt (position, (int)(value & 0xffffffffL)); 
 } 
} 
```

### Channel

##### 通道基础

```java
package java.nio.channels;
public interface Channel
{
   public boolean isOpen( );
   public void close( ) throws IOException;
}
```

##### 使用通道

```java
public class test {

    /**
     * This code copies data from stdin to stdout. Like the 'cat'
     * command, but without any useful options.
     */
    public static void main(String[] argv)
            throws IOException {
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        channelCopy1(source, dest);
// alternatively, call channelCopy2 (source, dest);
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
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1) {
// Prepare the buffer to be drained
            buffer.flip();
// Write to the channel; may block
            dest.write(buffer);
// If partial transfer, shift remainder down
// If buffer is empty, same as doing clear( )60
            buffer.compact();
        }
// EOF will leave buffer in fill state
        buffer.flip();
// Make sure that the buffer is fully drained
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
        while (src.read(buffer) != -1) {
// Prepare the buffer to be drained
            buffer.flip();
// Make sure that the buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
// Make the buffer empty, ready for filling
            buffer.clear();
        }
    }
}
```

##### 关闭通道

```java
package java.nio.channels;
public interface Channel
{
    //判断通道是否打开
public boolean isOpen( );
    //关闭通道
public void close( ) throws IOException;
}
```

##### Scatter/Gather

```java
public interface ScatteringByteChannel extends ReadableByteChannel
{
public long read (ByteBuffer [] dsts)
throws IOException;
public long read (ByteBuffer [] dsts, int offset, int length)
throws IOException;
}
public interface GatheringByteChannel
extends WritableByteChannel
{
public long write(ByteBuffer[] srcs)
throws IOException;
public long write(ByteBuffer[] srcs, int offset, int length)
throws IOException;
}	
```

##### 文件通道

基本的文件访问方法

```java
public abstract class FileChannel
extends AbstractChannel
implements ByteChannel, GatheringByteChannel, ScatteringByteChannel
{
// This is a partial API listing
    //返回当前位置
public abstract long position( )
    //移动到指定位置 
public abstract void position (long newPosition)
    //读取数据
public abstract int read (ByteBuffer dst)
    //读取指定数据的位置 如果到达末尾会返回-1
public abstract int read (ByteBuffer dst, long position)
    //写入数据
public abstract int write (ByteBuffer src)
    //在指定位置写入 数据 如果位置大于文件最大的位置 将会出现文件空洞
public abstract int write (ByteBuffer src, long position)
    //返回文件的大小
public abstract long size( )
    //截取指定大小的文件 如果文件比size大 size之后的数据全部回被抛弃
public abstract void truncate (long size)
    //强制将通道全部的数据写回文件中
public abstract void force (boolean metaData)
}
```

##### 文件锁定

```java
public abstract class FileChannel
extends AbstractChannel
implements ByteChannel, GatheringByteChannel, ScatteringByteChannel
{
// This is a partial API listing
//获取独占锁
public final FileLock lock( )
    //获取指定访问的锁position起始位置  position锁定的大小  第三个参数 true为共享锁 false为独占锁
public abstract FileLock lock (long position, long size,
boolean shared)
    //尝试获取锁
public final FileLock tryLock( )
    //同上
public abstract FileLock tryLock (long position, long size,
boolean shared)
}
```

```java
public abstract class FileLock
{
//判断锁是哪个通道创建的
public final FileChannel channel( )
    //锁的位置
public final long position( )
    //大小
public final long size( )
    //是否是共享锁  true为共享 false为独占锁
public final boolean isShared( )
    //查询是否和指定区域重叠
public final boolean overlaps (long position, long size)
    //判断是否有锁
public abstract boolean isValid( );
    //用来关闭流和释放资源
public abstract void release( ) throws IOException;
}
```

##### 内存映射文件

```java
public abstract class FileChannel
extends AbstractChannel
implements ByteChannel, GatheringByteChannel, ScatteringByteChannel
{
// This is a partial API listing
public abstract MappedByteBuffer map (MapMode mode, long position,long size)
public static class MapMode
{
public static final MapMode READ_ONLY
public static final MapMode READ_WRITE
public static final MapMode PRIVATE
}
}
```

##### Channel-to-Channel 传输

```java
public abstract class FileChannel
extends AbstractChannel
implements ByteChannel, GatheringByteChannel, ScatteringByteChannel
{
// This is a partial API listing
    //将文件内容读取给一个socket通道 
public abstract long transferTo (long position, long count,
WritableByteChannel target)
    //可以将数据从一个socket通道直接读到文件中 position + count 大于文件大小将会停止传输
public abstract long transferFrom (ReadableByteChannel src,
long position, long count)
}
```

### Socket通道

##### SelectableChannel

```java
public abstract class SelectableChannel
extends AbstractChannel
implements Channel
{
// This is a partial API listing
    //设置通道是否为阻塞模式 true为阻塞模式 false为非阻塞模式
public abstract void configureBlocking (boolean block)
throws IOException;
    //判断通道是否是阻塞模式
public abstract boolean isBlocking( );
    //返回获取一个对象 可以用来修改通道的阻塞模式  只有获取了这个对象才可以修改通道的阻塞模式
public abstract Object blockingLock( );
}
```

##### ServerSocketChannel

```java
public abstract class ServerSocketChannel
extends AbstractSelectableChannel
{
    //获取serversocketChannel渠道
public static ServerSocketChannel open( ) throws IOException
    //返回关联的serversocket
public abstract ServerSocket socket( );
    //返回channel用于数据交互
public abstract ServerSocket accept( ) throws IOException;
    //返回支持的操作集
public final int validOps( )
}
```

##### SocketChannel

```java
public abstract class SocketChannel
extends AbstractSelectableChannel
implements ByteChannel, ScatteringByteChannel, GatheringByteChannel
{
// This is a partial API listing
    //创建一个新的SocketChannel对象
public static SocketChannel open( ) throws IOException
    //返回之前的连接
public static SocketChannel open (InetSocketAddress remote)
throws IOException
    //返回基础的socket对象 
public abstract Socket socket( );
    //对远程端口的连接
public abstract boolean connect (SocketAddress remote)
throws IOException;
//判断是否在连接中
public abstract boolean isConnectionPending( );
    //是否连接完成
public abstract boolean finishConnect( ) throws IOException;
    //是否正在连接
public abstract boolean isConnected( );
    ////返回支持的操作集
public final int validOps( )
}
```

##### DatagramChannel(UDP/IP)

```java
public abstract class DatagramChannel
extends AbstractSelectableChannel
implements ByteChannel, ScatteringByteChannel, GatheringByteChannel
{
// This is a partial API listing
public static DatagramChannel open( ) throws IOException
public abstract DatagramSocket socket( );
public abstract DatagramChannel connect (SocketAddress remote)
throws IOException;
public abstract boolean isConnected( );
public abstract DatagramChannel disconnect( ) throws IOException;
public abstract SocketAddress receive (ByteBuffer dst)
throws IOException;
107
public abstract int send (ByteBuffer src, SocketAddress target)
public abstract int read (ByteBuffer dst) throws IOException;
public abstract long read (ByteBuffer [] dsts) throws IOException;
public abstract long read (ByteBuffer [] dsts, int offset,
int length)
throws IOException;
public abstract int write (ByteBuffer src) throws IOException;
public abstract long write(ByteBuffer[] srcs) throws IOException;
public abstract long write(ByteBuffer[] srcs, int offset,
int length)
throws IOException;
}
```

##### Pipe

```java
public abstract class Pipe
{
public static Pipe open( ) throws IOException
    //用户数据发送
public abstract SourceChannel source( );
    //用户数据读取
public abstract SinkChannel sink( );
119
public static abstract class SourceChannel
extends AbstractSelectableChannel
implements ReadableByteChannel, ScatteringByteChannel
public static abstract class SinkChannel
extends AbstractSelectableChannel
implements WritableByteChannel, GatheringByteChannel
}
```

##### 通道工具类

### 选择器

##### 选择键(SelectionKey)

```java
public abstract class SelectableChannel
extends AbstractChannel
implements Channel
{
// This is a partial API listing
    //注册到一个选择器上
public abstract SelectionKey register (Selector sel, int ops)
throws ClosedChannelException;
public abstract SelectionKey register (Selector sel, int ops,
Object att)
throws ClosedChannelException;
public abstract boolean isRegistered( );
//返回与该通道和选择器的注册关系  没有关系返回null
public abstract SelectionKey keyFor (Selector sel);
public abstract int validOps( );
public abstract void configureBlocking (boolean block)
throws IOException;
public abstract boolean isBlocking( );
public abstract Object blockingLock( );
}
```

##### selector API

```JAVA
public abstract class Selector
{
    //获取一个选择器
public static Selector open( ) throws IOException
    //判断选择器是否打开
public abstract boolean isOpen( );
    //关闭一个选择器
public abstract void close( ) throws IOException;
public abstract SelectionProvider provider( );
public abstract int select( ) throws IOException;
public abstract int select (long timeout) throws IOException;
public abstract int selectNow( ) throws IOException;
public abstract void wakeup( );
public abstract Set keys( );
public abstract Set selectedKeys( );
}
```

##### 建立选择器

```java
//获取一个选择器
Selector selector = Selector.open( );
//通道注册到选择器上 只读/只写/读和写
channel1.register (selector, SelectionKey.OP_READ);
channel2.register (selector, SelectionKey.OP_WRITE);
channel3.register (selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
// Wait up to 10 seconds for a channel to become ready
//休眠10秒
readyCount = selector.select (10000);
```

##### 使用选择键

```java
package java.nio.channels;
public abstract class SelectionKey
{
public static final int OP_READ
133
public static final int OP_WRITE
public static final int OP_CONNECT
public static final int OP_ACCEPT
    //返回与键相关的SelectableChannel
public abstract SelectableChannel channel( );
    //返回与键相关的Selector
public abstract Selector selector( );
public abstract void cancel( );
public abstract boolean isValid( );
public abstract int interestOps( );
public abstract void interestOps (int ops);
    
public abstract int readyOps( );
public final boolean isReadable( )
public final boolean isWritable( )
public final boolean isConnectable( )
public final boolean isAcceptable( )
    //将对象与通道关联
public final Object attach (Object ob)
public final Object attachment( )
}
```

##### 使用选择器

###### 选择过程

```java
public abstract class Selector
{
// This is a partial API listing
    //
public abstract Set keys( );
    //返回准备就绪的通道集合
public abstract Set selectedKeys( );
//
public abstract int select( ) throws IOException;
public abstract int select (long timeout) throws IOException;
public abstract int selectNow( ) throws IOException;
public abstract void wakeup( );
}
```

###### 停止选择过程

```java
public abstract class Selector
{
// This is a partial API listing
public abstract void wakeup( );
}
```

##### 管理选择键

```java
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

```

##### 异步关闭能力

```java
package com.weilai.Selector;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.util.List;
import java.util.LinkedList;
import java.io.IOException;

/**
 * Specialization of the SelectSockets class which uses a thread pool to service
 * channels. The thread pool is an ad-hoc implementation quicky lashed togther
 * in a few hours for demonstration purposes. It's definitely not production
 * quality.
 *
 * @author Ron Hitchens (ron@ronsoft.com)
 */
public class SelectSocketsThreadPool extends SelectSockets {
    private static final int MAX_THREADS = 5;
    private ThreadPool pool = new ThreadPool(MAX_THREADS);

    // -------------------------------------------------------------
    public static void main(String[] argv) throws Exception {
        new SelectSocketsThreadPool().go();
    }
// -------------------------------------------------------------

    /**
     * Sample data handler method for a channel with data ready to read. This
     * method is invoked from the go( ) method in the parent class. This handler
     * delegates to a worker thread in a thread pool to service the channel,
     * then returns immediately.
     *
     * @param key A SelectionKey object representing a channel determined by the
     *            selector to be ready for reading. If the channel returns an
     *            EOF condition, it is closed here, which automatically
     *            invalidates the associated key. The selector will then
     *            de-register the channel on the next select call.
     */
    protected void readDataFromSocket(SelectionKey key) throws Exception {

        WorkerThread worker = pool.getWorker();
        if (worker == null) {
// No threads available. Do nothing. The selection
// loop will keep calling this method until a
// thread becomes available. This design could
// be improved.
            return;
        }
// Invoking this wakes up the worker thread, then returns
        worker.serviceChannel(key);
    }
// ---------------------------------------------------------------

    /**
     * A very simple thread pool class. The pool size is set at construction
     * time and remains fixed. Threads are cycled through a FIFO idle queue.
     */
    private class ThreadPool {
        List idle = new LinkedList();

        ThreadPool(int poolSize) {
// Fill up the pool with worker threads
            for (int i = 0; i < poolSize; i++) {
                WorkerThread thread = new WorkerThread(this);
// Set thread name for debugging. Start it.
                thread.setName("Worker" + (i + 1));
                thread.start();
                idle.add(thread);
            }
        }

        /**
         * Find an idle worker thread, if any. Could return null.
         */
        WorkerThread getWorker() {
            WorkerThread worker = null;
            synchronized (idle) {
                if (idle.size() > 0) {
                    worker = (WorkerThread) idle.remove(0);
                }
            }
            return (worker);
        }

        /**
         * Called by the worker thread to return itself to the idle pool.
         */
        void returnWorker(WorkerThread worker) {
            synchronized (idle) {
                idle.add(worker);
            }
        }
    }

    /**
     * A worker thread class which can drain channels and echo-back the input.
     * Each instance is constructed with a reference to the owning thread pool
     * object. When started, the thread loops forever waiting to be awakened to
     * service the channel associated with a SelectionKey object. The worker is
     * tasked by calling its serviceChannel( ) method with a SelectionKey
     * object. The serviceChannel( ) method stores the key reference in the
     * thread object then calls notify( ) to wake it up. When the channel has
     * 147
     * been drained, the worker thread returns itself to its parent pool.
     */
    private class WorkerThread extends Thread {
        private ByteBuffer buffer = ByteBuffer.allocate(1024);
        private ThreadPool pool;
        private SelectionKey key;

        WorkerThread(ThreadPool pool) {
            this.pool = pool;
        }

        // Loop forever waiting for work to do
        public synchronized void run() {
            System.out.println(this.getName() + " is ready");
            while (true) {
                try {
// Sleep and release object lock
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
// Clear interrupt status
                    this.interrupted();
                }
                if (key == null) {
                    continue; // just in case
                }
                System.out.println(this.getName() + " has been awakened");
                try {
                    drainChannel(key);
                } catch (Exception e) {
                    System.out.println("Caught '" + e
                            + "' closing channel");
// Close channel and nudge selector
                    try {
                        key.channel().close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    key.selector().wakeup();
                }
                key = null;
// Done. Ready for more. Return to pool
                this.pool.returnWorker(this);
            }
        }

        /**
         * Called to initiate a unit of work by this worker thread on the
         * provided SelectionKey object. This method is synchronized, as is the
         * run( ) method, so only one key can be serviced at a given time.
         * Before waking the worker thread, and before returning to the main
         * selection loop, this key's interest set is updated to remove OP_READ.
         * This will cause the selector to ignore read-readiness for this
         * channel while the worker thread is servicing it.
         */
        synchronized void serviceChannel(SelectionKey key) {
            this.key = key;
            key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
            this.notify(); // Awaken the thread
        }

        /**
         * 148
         * The actual code which drains the channel associated with the given
         * key. This method assumes the key has been modified prior to
         * invocation to turn off selection interest in OP_READ. When this
         * method completes it re-enables OP_READ and calls wakeup( ) on the
         * selector so the selector will resume watching this channel.
         */
        void drainChannel(SelectionKey key) throws Exception {
            SocketChannel channel = (SocketChannel) key.channel();
            int count;
            buffer.clear(); // Empty buffer
// Loop while data is available; channel is nonblocking
            while ((count = channel.read(buffer)) > 0) {
                buffer.flip(); // make buffer readable
// Send the data; may not go all at once
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
// WARNING: the above loop is evil.
// See comments in superclass.
                buffer.clear(); // Empty buffer
            }
            if (count < 0) {
// Close channel on EOF; invalidates the key
                channel.close();
                return;
            }
// Resume interest in OP_READ
            key.interestOps(key.interestOps() | SelectionKey.OP_READ);
// Cycle the selector so this key is active again
            key.selector().wakeup();
        }
    }
}
```

### 正则表达式

##### CharSequence接口

```java
public interface CharSequence 
{
 int length( );
 char charAt (int index);
 public String toString( );
 CharSequence subSequence (int start, int end);
}
```

##### Pattern类

```java
public final class Pattern implements java.io.Serializable
{
public static final int UNIX_LINES
public static final int CASE_INSENSITIVE
public static final int COMMENTS
public static final int MULTILINE 
public static final int DOTALL
public static final int UNICODE_CASE
public static final int CANON_EQ
    //根据正则表达式进行匹配
public static boolean matches (String regex, CharSequence input)
public static Pattern compile (String regex)
public static Pattern compile (String regex, int flags)
public String pattern( )
public int flags( )
public String[] split (CharSequence input, int limit)
public String[] split (CharSequence input)
public Matcher matcher (CharSequence input)
}
```

### 字符集

```java
package java.nio.charset;
public abstract class Charset implements Comparable
{
    //检测jvm中字符集是否可用
 public static boolean isSupported (String charsetName)
 public static Charset forName (String charsetName)
 public static SortedMap availableCharsets( ) 
 public final String name( )
 public final Set aliases( )
 public String displayName( )
 public String displayName (Locale locale)
 public final boolean isRegistered( )
 public boolean canEncode( )
 public abstract CharsetEncoder newEncoder( ); 
 public final ByteBuffer encode (CharBuffer cb)
 public final ByteBuffer encode (String str)
 public abstract CharsetDecoder newDecoder( ); 
 public final CharBuffer decode (ByteBuffer bb)
 public abstract boolean contains (Charset cs);
 public final boolean equals (Object ob)
 public final int compareTo (Object ob)
 public final int hashCode( )
 public final String toString( )
}
```

