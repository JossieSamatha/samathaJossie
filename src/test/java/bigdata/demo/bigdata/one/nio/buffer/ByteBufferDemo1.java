package bigdata.demo.bigdata.one.nio.buffer;

import java.nio.ByteBuffer;

public class ByteBufferDemo1 {

    public static void main(String[] args) {

        // 构建ByteBuffer对象
        // ByteBuffer底层是依靠字节数组来存储数据
        // 参数表示容量，容量实际上就是指定底层的字节数组的大小
        // 容量指定好之后不可变
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 获取容量位
        System.out.println(buffer.capacity());
        // 获取操作位
        System.out.println(buffer.position());

        // 添加数据
        buffer.put("abc".getBytes());
        buffer.put("def".getBytes());

        // 挪动操作位
        // 表示将操作位挪回第0位
        // buffer.position(0);

        // 获取数据
        // byte b = buffer.get();
        // System.out.println(b);

        // 将写入的数据进行遍历
        // 首先需要先将limit挪到position位置上
        // 其次需要将position归零
        // buffer.limit(buffer.position());
        // buffer.position(0);
        // 上述两步骤称之为反转缓冲区，等价于
        buffer.flip();
        // while (buffer.position() < buffer.limit()) {
        // 等价于
        while (buffer.hasRemaining()) {
            // 每get一次都会自动的向后挪一位
            byte b = buffer.get();
            System.out.println((char) b);
        }

    }

}
