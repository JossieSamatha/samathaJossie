package bigdata.demo;

import org.springframework.boot.test.context.SpringBootTest;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class DemoApplicationTests {

    public static void main(String agrs[]) {
        /**
         * Buffer
         */
        //Buffer针对七种基本类型提供了七个实现类
        ByteBuffer byteBuffer = ByteBuffer.allocate(100000000);
        byteBuffer.put(0, Byte.valueOf("100"));
        System.out.println(byteBuffer);
        byteBuffer.position(0);
        Byte b = byteBuffer.get();
        System.out.println(b);
        System.out.println(byteBuffer);
        byteBuffer.put(1,Byte.valueOf("123"));
        byteBuffer.put(2,Byte.valueOf("123"));
        byteBuffer.put(3,Byte.valueOf("123"));
        //缓冲区的容量 容量位
        System.out.println(byteBuffer.capacity());
        //获取操作位 操作位
        System.out.println(byteBuffer.position());
        //指定操作位
        byteBuffer.position(0);
        //limit限制位 限制位 缓冲区刚创建 limit跟capacity是重合的
        System.out.println(byteBuffer.limit());
        //指定限制位 limit position能达到的最大下标
        byteBuffer.limit(byteBuffer.position());
        //缓冲区position归零
        byteBuffer.position(0);
        //反转缓冲区
        byteBuffer.flip();
        //遍历byteBuffer
         while(byteBuffer.position()<byteBuffer.limit()){
            //每次get 或 put position都会后挪一位
            System.out.println((char)byteBuffer.get());
        }
        while(byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }

        byte[] c=byteBuffer.array();
        IntBuffer intBuffer=IntBuffer.allocate(100);
        //转换从第0位到limit位
        System.out.println(new String (c,0,byteBuffer.limit()));
        //如果数据已知
        ByteBuffer byteBuffer1=ByteBuffer.wrap("hellow world".getBytes(StandardCharsets.UTF_8));
        System.out.println(byteBuffer1.get(0));
        //字节数组现在大小是100000000
        byte[] bytes=byteBuffer1.array();
        //转化过程产生很多空字节 所以需要限制位数
        System.out.println(new String(bytes));
        System.out.println(new String(bytes,0,byteBuffer1.position()));
        /**
         * Channel
         */
//        channel是面向Buffer进行操作  Buffer位单位 channel可以实现双向传输 常见Channel分类
//        1 文件 :FileChannel 2 UDP :DatagramChannel 3 TCP :socketChannel ServeSocketChannel


    }

}
