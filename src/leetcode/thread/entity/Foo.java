package leetcode.thread.entity;

/**
 * @author chenzihan
 * @date 2019-12-31 15:49
 */
public class Foo {

    private volatile boolean firstFinished;
    private volatile boolean secondFinished;

    public Foo() {
        firstFinished = false;
        secondFinished = false;
    }

    public void first(Runnable printFirst) throws InterruptedException {

        synchronized (this) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstFinished = true;
            this.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        synchronized (this) {
            while (!firstFinished) {
                this.wait();
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondFinished = true;
            this.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        synchronized (this) {
            while (!secondFinished) {
                this.wait();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    public void one() {
        System.out.println("one");
    }

    public void two() {
        System.out.println("two");
    }

    public void three() {
        System.out.println("three");
    }

}
