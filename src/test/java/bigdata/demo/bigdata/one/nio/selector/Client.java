package bigdata.demo.bigdata.one.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public static void main(String[] args) throws IOException {

        // 开启客户端通道
        SocketChannel sc = SocketChannel.open();
        // 发起连接
        sc.connect(new InetSocketAddress("localhost", 8070));
        // 写出数据
        sc.write(ByteBuffer.wrap("hello server".getBytes()));
        // 接收数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        sc.read(buffer);
        // 解析数据
        byte[] array = buffer.array();
        System.out.println(new String(array, 0, buffer.position()));
        // 关流
        sc.close();

    }

}