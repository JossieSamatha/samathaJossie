package bigdata.demo.bigdata.one.blockingqueue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        // PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
        // queue.put("r");
        // queue.put("a");
        // queue.put("t");
        // queue.put("w");
        // queue.put("o");
        // queue.put("d");
        // queue.put("k");
        // for (int i = 0; i < 7; i++) {
        //     System.out.println(queue.take());
        // }

        // 在创建队列的时候，如果希望队列中的元素能够按照年纪进行升序排序
        // 也就意味着在创建队列的时候需要额外指定比较规则
        Comparator<Student> c = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        PriorityBlockingQueue<Student> queue = new PriorityBlockingQueue<>(5, c);
        queue.put(new Student("Charles", 17, 80));
        queue.put(new Student("Jack", 16, 76));
        queue.put(new Student("Grace", 15, 82));
        queue.put(new Student("Mike", 18, 91));
        queue.put(new Student("Helen", 19, 70));
        // for (int i = 0; i < 5; i++) {
        //     System.out.println(queue.take());
        // }
        for (Student student : queue) {
            System.out.println(student);
        }

    }

}

class Student implements Comparable<Student> {

    private String name;
    private int age;
    private int score;

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }

    // 规则：按照分数降序排序
    @Override
    public int compareTo(Student o) {
        return o.getScore() - this.getScore();
    }
}
