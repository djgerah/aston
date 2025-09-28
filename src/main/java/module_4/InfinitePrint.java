package module_4;

import java.util.concurrent.atomic.AtomicBoolean;

public class InfinitePrint {
    public static void main(String[] args) {
        Object monitor = new Object();
        AtomicBoolean isTurn = new AtomicBoolean(true);

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (monitor) {
                        while (!isTurn.get()) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException ignored) {
                            }
                        }

                        System.out.print("1");
                        isTurn.set(false);
                        monitor.notify();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (monitor) {
                        while (isTurn.get()) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException ignored) {
                            }
                        }

                        System.out.print("2");
                        isTurn.set(true);
                        monitor.notify();
                    }
                }
            }
        }).start();
    }
}