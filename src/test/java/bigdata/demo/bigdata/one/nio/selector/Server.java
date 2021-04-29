package bigdata.demo.bigdata.one.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

// 需要将Selector设置在服务器端
public class Server {

    public static void main(String[] args) throws IOException {

        // 开启服务器端的通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 绑定要监听的端口号
        ssc.bind(new InetSocketAddress(8070));
        // 设置为非阻塞
        ssc.configureBlocking(false);
        // 开启Selector
        Selector selc = Selector.open();
        // 将ssc绑定到Selector上
        // 绑定需要让Selector过滤的事件
        ssc.register(selc, SelectionKey.OP_ACCEPT);
        // 实际开发中，服务器开启之后应该是不关闭的
        // 用while(true)模拟
        while (true) {
            // 如果服务器一直运行，那么就会接收到大量的连接
            // 这些连接有可能是有用连接，也有可能是无用连接
            // 需要筛选掉无用连接
            selc.select();
            // 筛选完成之后，剩余的连接都能够触发服务器端的对应的操作
            // 这些连接也就是有用连接
            // 有用连接可能触发服务器的accept/read/write操作
            // 但是不意味着这次会触发服务器端所有的操作
            // 所以需要确定这一次到底触发了服务器端的什么操作
            Set<SelectionKey> set = selc.selectedKeys();
            // 遍历这个集合，然后根据不同的事件类型来进行处理
            Iterator<SelectionKey> it = set.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                // 可接受事件
                if (key.isAcceptable()) {
                    // 先从事件中将对应的通道拿出来
                    ServerSocketChannel sscx = (ServerSocketChannel) key.channel();
                    // 接收连接
                    SocketChannel sc = sscx.accept();
                    // 设置非阻塞
                    sc.configureBlocking(false);
                    // 如果读取数据，那么需要注册一个可读事件
                    // sc.register(selc, SelectionKey.OP_READ);
                    // 如果写出数据，那么需要注册一个可写事件
                    // sc.register(selc, SelectionKey.OP_WRITE);
                    // 如果既需要读又需要写
                    // 后一次的register会覆盖前一次的register
                    // sc.register(selc, SelectionKey.OP_WRITE + SelectionKey.OP_READ);
                    // sc.register(selc, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                    sc.register(selc, SelectionKey.OP_WRITE ^ SelectionKey.OP_READ);
                }
                // 可读事件
                if (key.isReadable()) {
                    // 先从事件中将通道获取出来
                    SocketChannel sc = (SocketChannel) key.channel();
                    // 准备一个字节缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    // 读取数据
                    sc.read(buffer);
                    // 解析数据
                    byte[] array = buffer.array();
                    System.out.println(new String(array, 0, buffer.position()));
                    // read事件已经被处理完，如果这个通道继续保留这个read事件，那么下一次会被重复处理
                    // 所以需要考虑移除read事件
                    // key.interestOps()表示获取这个通道上注册的所有的事件
                    // sc.register(selc, key.interestOps() - SelectionKey.OP_READ);
                    // sc.register(selc, key.interestOps() & SelectionKey.OP_WRITE);
                    sc.register(selc, key.interestOps() ^ SelectionKey.OP_READ);
                }
                // 可写事件
                if (key.isWritable()) {
                    // 先从事件中将通道获取出来
                    SocketChannel sc = (SocketChannel) key.channel();
                    // 准备字节缓冲区
                    ByteBuffer buffer = ByteBuffer.wrap("hello client".getBytes());
                    // 写出数据
                    sc.write(buffer);
                    // write事件已经被处理完，如果这个通道继续保留这个write事件，那么下一次就会被重复处理
                    // 所以需要考虑移除write事件
                    // sc.register(selc, key.interestOps() - SelectionKey.OP_WRITE);
                    // sc.register(selc, key.interestOps() & SelectionKey.OP_READ);
                    sc.register(selc, key.interestOps() ^ SelectionKey.OP_WRITE);
                }
                // 事件处理完成之后需要移除
                it.remove();
            }
        }
    }

}