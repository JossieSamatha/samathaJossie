package bigdata.demo.bigdata.one.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    public static void main(String[] args) throws IOException {

        // 开启服务器端的通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 绑定要监听的端口
        ssc.bind(new InetSocketAddress(8090));
        // 设置非阻塞
        ssc.configureBlocking(false);
        // 接收连接
        // 非阻塞：无论是否接收到连接，都会继续往下执行
        SocketChannel sc = ssc.accept();
        // 判断是否接到连接
        while (sc == null) {
            // 如果没有接到连接，就在这儿等着来接连接
            sc = ssc.accept();
        }
        // 接到连接
        // 准备一个字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 读取数据
        // 会将接收到的数据放到buffer中
        sc.read(buffer);
        // 解析数据
        byte[] array = buffer.array();
        System.out.println(new String(array, 0, buffer.position()));
        // 关流
        ssc.close();

    }

}
