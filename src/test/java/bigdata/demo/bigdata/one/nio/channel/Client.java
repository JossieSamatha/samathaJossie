package bigdata.demo.bigdata.one.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public static void main(String[] args) throws IOException {

        // 开启客户端通道
        SocketChannel sc = SocketChannel.open();
        // 设置为非阻塞
        sc.configureBlocking(false);
        // 发起连接
        // 非阻塞：无论连上还是连不上，都不会在这儿等着而是会继续往下执行
        sc.connect(new InetSocketAddress("localhost", 8090));
        // 判断连接是否建立
        while (!sc.isConnected()) {
            // 如果连接没有建立，那么试图再次建立这个连接
            // 如果多次试图建立连接，都没有连上，那么说明这个连接无法建立
            // 无法建立的原因有很多，例如地址错误、服务器故障、网络故障等
            // finishConnect底层会根据当前的网络条件来自动进行计数
            // 如果计数多次依然连接失败，则会直接抛出异常
            sc.finishConnect();
        }
        // 连接建立
        // 构建一个字节缓冲区
        ByteBuffer buffer = ByteBuffer.wrap("hello server".getBytes());
        // 写出数据
        sc.write(buffer);
        // 关流
        sc.close();

    }

}