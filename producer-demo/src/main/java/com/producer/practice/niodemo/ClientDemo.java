package com.producer.practice.niodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8888));
        socketChannel.configureBlocking(false);

        Scanner scanner =new Scanner(System.in);
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        while (scanner.hasNextLine()){
            byteBuffer.clear();
            byteBuffer.put(scanner.next().getBytes());

            byteBuffer.flip();
            socketChannel.write(byteBuffer);

            byteBuffer.clear();
        }
    }
}
