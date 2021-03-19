package com.producer.practice.niodemo;

import com.sun.org.apache.bcel.internal.generic.Select;
import org.apache.catalina.Server;
import sun.misc.Unsafe;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class ServerDemo {

    private ByteBuffer writebuffer=ByteBuffer.allocate(1024);

    private ByteBuffer readbuffer=ByteBuffer.allocate(1024);

    private Selector selector;

    ServerDemo() throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8888));

        this.selector=Selector.open();
        //绑定channel到selector,注册accept事件
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws IOException {
        ServerDemo serverDemo=new ServerDemo();
        serverDemo.go();
    }


    public void go() throws IOException {
        Selector selector=this.selector;
        while(true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if((selectionKey.readyOps()&SelectionKey.OP_ACCEPT)==SelectionKey.OP_ACCEPT){
                    //accept事件就绪，拿到对应的serversocketchannel
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel channel = serverSocketChannel.accept();
                    channel.configureBlocking(false);

                    channel.register(selector, SelectionKey.OP_READ);
                }else if((selectionKey.readyOps()&SelectionKey.OP_CONNECT)==SelectionKey.OP_CONNECT){

                }else if((selectionKey.readyOps()&SelectionKey.OP_READ)==SelectionKey.OP_READ){
                    //拿到socketchannel
                    SocketChannel socketChannel= (SocketChannel) selectionKey.channel();

                    ByteBuffer buffer=ByteBuffer.allocate(1024);
                    buffer.clear();
                    int res=0;
                    while(true){
                        int reads=socketChannel.read(buffer);
                        if(reads==0){break;}
                        res+=reads;
                        System.out.println(res);
                    }
                    System.out.println("res:"+res+"-->"+new String(buffer.array()));

                    System.out.println(buffer);
                    buffer.clear();
                    System.out.println(buffer);
                    buffer.put("hi ".getBytes());
                    System.out.println(buffer);
                    buffer.flip();

                    socketChannel.write(buffer);
                    System.out.println(buffer);

                }else if((selectionKey.readyOps()&SelectionKey.OP_WRITE)==SelectionKey.OP_WRITE){
                    //拿到socketchannel
                    SocketChannel socketChannel= (SocketChannel) selectionKey.channel();

                    ByteBuffer buffer=ByteBuffer.allocate(1024);
                    buffer.put("祝好".getBytes());
                    int res=socketChannel.write(buffer);
                    System.out.println("res:"+res+"-->"+new String(buffer.array()));
                }
                iterator.remove();
            }
        }

    }
}
