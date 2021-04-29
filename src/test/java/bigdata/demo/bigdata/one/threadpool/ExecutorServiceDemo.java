package bigdata.demo.bigdata.one.threadpool;

import java.util.concurrent.*;

public class ExecutorServiceDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 构建一个线程池
        // corePoolSize - 核心线程数量
        // maximumPoolSize - 总线程数量 = 核心线程数 + 临时线程数
        // keepAliveTime - 临时线程用完之后的存活时间
        // unit - 时间单位
        // workQueue - 工作队列
        // handler - 拒绝执行处理器 - 可以添加也可以不添加
        // 如果有明确的拒绝流程，那么可以添加
        // 如果没有拒绝流程，那么可以不写这个参数，底层自动的拒绝
        ExecutorService es = new ThreadPoolExecutor(
                5, // 表示有5个核心线程
                15, // 表示总线程数为15 = 5个核心线程 + 10 个临时线程
                5, TimeUnit.SECONDS, // 临时线程用完之后能够存活5s
                new ArrayBlockingQueue<>(5),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        // 指定拒绝流程
                        // 例如实际开发过程中，需要记录日志(时间、IP、内容等)，跳转页面等
                        System.out.println("拒绝执行线程" + r);
                    }
                }
        );

        // new thread(new ESThread()).start();
        // 提交任务，执行线程
        // 线程池：5个核心线程，工作队列为5，临时线程为10
        for (int i = 0; i < 22; i++) {
//            es.execute(只能是Runnable线程);没有返回值
            // es.execute(new ESThread());
            /**
             * submit可以使Callable也可以是Runnable
             */
            es.submit(new ESThread());
//            Future f=es.submit(new CDemo());
//            System.out.println(f.get());
        }

        // 如果线程池用完，可以选择关闭线程池
        es.shutdown();


    }

}

class ESThread implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("start");
            // 模拟：线程执行需要3s
            Thread.sleep(3000);
            System.out.println("finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class CThread implements Callable <Object>{
    @Override
    public Object call() throws Exception {
        return "success";
    }
}
