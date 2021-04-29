package bigdata.demo.bigdata.one.serial;




import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class SerialDemo {

//    @Test
//    public void create() {
//
//        // 注意：在AVRO中要求属性的值不能为null
//        // 方式一：先创建对象后赋值
//        Student s1 = new Student();
//        s1.setName("Helen");
//        s1.setAge(19);
//        s1.setGender("female");
//        s1.setHeight(168.3);
//        s1.setWeight(57.1);
//        System.out.println(s1);
//        // 方式二：在创建对象的时候给值
//        Student s2 = new Student("Alex", 21, "male", 185.2, 70.5);
//        System.out.println(s2);
//        // 方式三：适合于枚举创建方式
//        Student s3 = new Student();
//        s3.put(0, "Mark");
//        s3.put(1, 17);
//        s3.put(2, "male");
//        s3.put(3, 181.8);
//        s3.put(4, 63.9);
//        System.out.println(s3);
//        // 方式四：适合于反射
//        Student s4 = new Student();
//        s4.put("name", "Peter");
//        s4.put("age", 15);
//        s4.put("gender", "male");
//        s4.put("height", 182.5);
//        s4.put("weight", 70.5);
//        System.out.println(s4);
//        // 方式五：建造者模式
//        Student s5 = Student.newBuilder()
//                .setName("Lily")
//                .setAge(17)
//                .setGender("female")
//                .setHeight(166.0)
//                .setWeight(50.0).build();
//        System.out.println(s5);
//        // 方式六：建造者模式
//        Student s6 = Student.newBuilder(s5).setName("Lucy").build();
//        System.out.println(s6);
//    }

//    // 序列化
//    @Test
//    public void serial() throws IOException {
//        // 创建对象
//        Student s1 = new Student("Peter", 17, "male", 178.8, 60.6);
//        Student s2 = new Student("John", 18, "male", 178.8, 60.6);
//        Student s3 = new Student("Charles", 22, "male", 178.8, 60.6);
//        // 创建序列化流
//        DatumWriter<Student> dw = new SpecificDatumWriter<>(Student.class);
//        // 创建文件流
//        DataFileWriter<Student> dfw = new DataFileWriter<>(dw);
//        // 指定文件
//        // 通过对象获取约束
//        // dfw.create(s1.getSchema(), new File("E:\\a.data"));
//        // dfw.create(Student.getClassSchema(), new File("E:\\a.data"));
//        dfw.create(Student.SCHEMA$, new File("E:\\a.data"));
//        // 序列化对象
//        dfw.append(s1);
//        dfw.append(s2);
//        dfw.append(s3);
//        // 关流
//        dfw.close();
//    }
//
//    // 反序列化
//    @Test
//    public void deSerial() throws IOException {
//        // 创建反序列化流
//        DatumReader<Student> dr = new SpecificDatumReader<>(Student.class);
//        // 创建文件流 - 指定文件
//        DataFileReader<Student> dfr = new DataFileReader<>(new File("E:\\a.data"), dr);
//        // 为了便于操作，将反序列化流设计成了迭代器模式
//        while (dfr.hasNext()) {
//            Student s = dfr.next();
//            System.out.println(s);
//        }
//        // 关流
//        dfr.close();
//    }

}
