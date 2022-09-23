package com.liang.learn;

import java.util.concurrent.*;

/**
 * @author liangyuchun
 * @date 2022/9/15 11:50
 */
public class ThreadCallerTest {
    final static ExecutorService exec = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        Future<?> future = exec.submit(() -> {
            long startTime = System.currentTimeMillis();
            System.out.println("start time:" + startTime);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("end time:" + endTime + " , run time:" + (endTime - startTime));
            return "complete。";
        });

        Object o = null;
        try {
            o = future.get(2, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException ignore) {
            System.out.println("run timeout！！！");
        } finally {
            if (o == null) {
                o = "null";
            }
            if (!future.isDone()) {
                future.cancel(true);
            }
        }


        System.out.println("get result -> " + o.toString());

        exec.shutdown();
    }
}
