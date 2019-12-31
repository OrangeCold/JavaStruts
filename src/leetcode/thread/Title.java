package leetcode.thread;

import leetcode.thread.entity.Foo;

/**
 * @author chenzihan
 * @date 2019-12-31 15:47
 */
public class Title {

    /**
     * 按序打印
     */
    private void printSunxv() throws InterruptedException {
        Foo foo = new Foo();
        foo.first(new Runnable() {
            @Override
            public void run() {
                foo.one();
            }
        });
        foo.second(new Runnable() {
            @Override
            public void run() {
                foo.two();
            }
        });
        foo.third(new Runnable() {
            @Override
            public void run() {
                foo.three();
            }
        });
    }

    public static void main(String[] args) {
        Title title = new Title();
        try {
            title.printSunxv();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
