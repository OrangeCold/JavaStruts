package leetcode.thread.entity;

/**
 * @author chenzihan
 * @date 2019-12-31 16:37
 */
public class FooBar {
    private int n;

    private volatile boolean canFoo;
    private volatile boolean canBar;

    public FooBar(int n) {
        this.n = n;
        this.canBar = false;
        this.canFoo = false;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this){
                while (!canFoo){
                    this.wait();
                }
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
        }
    }
}
