package bigdata.demo.bigdata.one.concurrentmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

        // 指定容量是14，实际容量是32
        // 指定容量是17，实际容量是32
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(14);
        //并发跳跃表映射 只能针对有序数据 适用于查询多增删少的场景
        ConcurrentSkipListMap<String,Integer> concurrentMap=new ConcurrentSkipListMap<>();
        concurrentMap.put("a",1);
        concurrentMap.put("d",9);
        concurrentMap.put("b",2);
        concurrentMap.put("c",3);
        //自动排序 {a=1, b=2, c=3, d=9}
        System.out.println(concurrentMap);
        //从指定键截取到末尾
        System.out.println(concurrentMap.tailMap("b"));
        //从开头截取到指定键位 [)含头不含尾
        System.out.println(concurrentMap.headMap("b"));
        System.out.println(concurrentMap.subMap("b","c"));

    }

}
