package bigdata.demo.bigdata.one.nio.buffer;

import java.nio.ByteBuffer;

public class ByteBufferDemo2 {

    public static void main(String[] args) {

        // 构建缓冲区
        // 先构建缓冲区，然后再利用put方法填充数据
        // 这种方式适合于数据未知的数据
        // ByteBuffer buffer = ByteBuffer.allocate();
        // 如果数据已知
        // ByteBuffer buffer = ByteBuffer.wrap("Tedu bigdata".getBytes());
        // 获取position - 无论利用哪种方式构建缓冲区，只要没有进行get/put操作，那么position默认都是在第0位的
        // System.out.println(buffer.position());
        // 获取缓冲区底层对应的字节数组
        // byte[] array = buffer.array();
        // System.out.println(new String(array));

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello ".getBytes());
        buffer.put("big data".getBytes());
        // 字节数组大小现在是1024，但是实际数据大小不到1024个字节
        byte[] array = buffer.array();
        // 在转化的时候，都多出来很多空字节，所以需要限制位数
        // System.out.println(new String(array, 0, buffer.position()));
        buffer.flip();
        System.out.println(new String(array, 0, buffer.limit()));

    }

}
