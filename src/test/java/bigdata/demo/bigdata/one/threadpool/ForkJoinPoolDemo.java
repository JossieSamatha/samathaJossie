package bigdata.demo.bigdata.one.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分差合并池
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 求1-100000000000L的和
        // main所在的类默认就是一个线程类 - 主线程
        // 一个线程只能分配到一个CPU核上
        // long sum = 0;
        // for (long i = 1; i < 100000000000L; i++) {
        //     sum += i;
        // }
        // System.out.println(sum);

        ForkJoinPool p = new ForkJoinPool();
        ForkJoinTask<Long> t = p.submit(new Sum(1, 10L));
        System.out.println(t.get());
        p.shutdown();

    }

}

// 泛型表示的是结果类型
class Sum extends RecursiveTask<Long> {

    private long start;
    private long end;

    public Sum(long start, long end) {
        this.start = start;
        this.end = end;
    }

    // 将分叉合并的逻辑放在compute方法中
    @Override
    protected Long compute() {
        // 如果范围依然比较大，那么就继续拆分
        // 如果范围比较小，那么就需要将这个范围内的数字进行求和
        if (end - start <= 10000) {
            // 如果范围内不足10000个数字，任务范围比较小
            // 求和
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 如果范围超过10000，那么认为这个范围比较大，那么需要继续拆分
            long mid = (start + end) / 2;
            Sum left = new Sum(start, mid);
            Sum right = new Sum(mid + 1, end);
            // 分叉
            left.fork();
            right.fork();
            Sum a=new Sum(1L,9L);
            System.out.printf(a.fork().toString());
            // 合并
            return left.join() + right.join();
        }
    }
}
