package leetcode.thread.entity;

import java.util.concurrent.Semaphore;

/**
 * @author chenzihan
 * @date 2019-12-31 16:37
 */
public class FooBar {
    private int n;

    private Semaphore semaFoo = new Semaphore(1);
    private Semaphore semaBar = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printFoo.run() outputs "foo". Do not change or remove this line.
                semaFoo.acquire();
                printFoo.run();
                semaBar.release();

            }


    }

    public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printBar.run() outputs "bar". Do not change or remove this line.
                semaBar.acquire();
                printBar.run();
                semaFoo.release();

            }

    }
}
