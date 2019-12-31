package leetcode.thread.entity;

/**
 * @author chenzihan
 * @date 2019-12-31 16:37
 */
public class FooBar {
    private volatile int n;

    private volatile boolean canFoo;

    public FooBar(int n) {
        this.n = n;
        this.canFoo = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        synchronized (this){
            for (int i = 0; i < n; i++) {
                if (!canFoo) {
                    this.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                canFoo = false;
                this.notifyAll();
            }

        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        synchronized (this){

            for (int i = 0; i < n; i++) {

                if (canFoo){
                    this.wait();
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                canFoo = true;
                this.notifyAll();

            }
        }

    }
}
