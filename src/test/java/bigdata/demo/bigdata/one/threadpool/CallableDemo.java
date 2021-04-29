package bigdata.demo.bigdata.one.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 启动Callable线程
        // 方式一：将Callable线程包装成Runnable线程通过Thread来启动
        // Callable -> FutureTask -> RunnableFuture -> Runnable
        // FutureTask<String> f = new FutureTask<>(new CDemo());
        // new thread(f).start()
        // 方式二：将Callable利用线程池来启动
        ExecutorService es = new ThreadPoolExecutor(5, 10,
                5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        // 将结果封装成Future对象返回
        Future<String> f = es.submit(new CDemo());
        // 将结果从Future中解析处理
        System.out.println(f.get());
        // 关闭线程池
        es.shutdown();
    }

}

// 泛型定义返回值类型
class CDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "SUCCESS";
    }
}
