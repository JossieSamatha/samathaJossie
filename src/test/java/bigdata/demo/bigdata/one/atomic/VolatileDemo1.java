package bigdata.demo.bigdata.one.atomic;

// 可见性
public class VolatileDemo1 {

    public static void main(String[] args) {

        Data d = new Data();
        d.i = 10;

        // 线程A
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A线程已经启动了");
                while (d.i == 10) ;
                System.out.println("A线程已经结束了");
            }
        }).start();

        // 线程B
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 延迟线程B的启动
                    Thread.sleep(3000);
                    System.out.println("B线程已经启动了");
                    d.i = 20;
                    System.out.println("B线程已经结束了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}

class Data {
    volatile int i;
}
