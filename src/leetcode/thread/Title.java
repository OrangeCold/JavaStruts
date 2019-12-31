package leetcode.thread;

import leetcode.thread.entity.Foo;
import leetcode.thread.entity.FooBar;

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

    /**
     * FooBar
     */
    private void printFooBar(int count) throws InterruptedException {
        FooBar fooBar = new FooBar(count);
        fooBar.foo(new Runnable() {
            @Override
            public void run() {
                System.out.print("foo");
            }
        });
        fooBar.bar(new Runnable() {
            @Override
            public void run() {
                System.out.print("bar");
            }
        });

    }

    public static void main(String[] args) {
        Title title = new Title();
//        try {
//            title.printSunxv();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            title.printFooBar(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
